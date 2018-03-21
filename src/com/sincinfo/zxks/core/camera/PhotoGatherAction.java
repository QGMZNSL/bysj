/**
 * Copyright(c) 2012 西安云海信息技术有限责任公司 All Rights Reserved<br>
 * @Title: com.sincinfo.zxks.core.camera.PhotoGatherAction.java<br>
 * @Description: 照片采集功能 <br>
 * <br>
 * @author litian<br>
 * @date Feb 2, 2012 8:38:05 AM 
 * @version V1.0   
 */
package com.sincinfo.zxks.core.camera;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import sun.misc.BASE64Decoder;

import com.alibaba.fastjson.JSON;

import com.sincinfo.zxks.bean.BaseExamination;
import com.sincinfo.zxks.bean.BaseStudentInfo;
import com.sincinfo.zxks.bean.BaseStudentNoIdno;
import com.sincinfo.zxks.bean.BaseUser;
import com.sincinfo.zxks.common.Constants;
import com.sincinfo.zxks.common.action.WebActionSupport;
import com.sincinfo.zxks.common.db.DbUtil;
import com.sincinfo.zxks.common.log.OperatLog;
import com.sincinfo.zxks.common.util.FileManager;
import com.sincinfo.zxks.common.util.Log;
import com.sincinfo.zxks.common.util.StringTool;
import com.sincinfo.zxks.zxksdbs.PhotoGatherDbService;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
/**
 * @ClassName: PhotoGatherAction
 * @Description: 照片采集功能 <br>
 *               <br>
 * @author litian
 * @date Feb 2, 2012 8:38:05 AM<br>
 * 
 */
/**
 * @author zhangjb
 *
 */
public class PhotoGatherAction extends WebActionSupport {

	private static final long serialVersionUID = -7391288117222812861L;

	private PhotoGatherDbService pgds = new PhotoGatherDbService();

	// 预报名号
	private String preapplyCode;

	// 考生信息
	private BaseStudentInfo studentInfo=new BaseStudentInfo();

	// 采集照片的方式
//	private int saveMethod;

	// 照片文件
	private File photoFile;

	// 证件中的照片字符串
	private String idcardPhotoBuff;

	// 证件信息（包含照片）
//	private IdCardEntity idCard;

	// 网络（ip部分）
//	private String phoUrl;

	// 身份证上的照片路径
	private String idcardnetPhotoPath;

	// 采集到的照片路径
	private String netPhotoPath;
	private String preApplyCode;

	private String PhotofileName;
	private String noIdnoReason;   
	private String document;
	
	
	// 保存功能入口
	private String saveUrl;
	
	private List<String[]> lGenderName;//性别---------------
	private List<String[]> lStudFolk; //民族---------------
	private List<String[]> lStudPolitics; //政治面貌---------------
	private List<String[]> lStudSchoolAge; //文化程度STUD_SCHOOL_AGE---------------
	private List<String[]> lStudHukouCharacter; //户籍STUD_HUKOU_CHARACTER---------------
	private List<String[]> lStudOccupation; //职业---------------
	private List<String[]> lStudHukouLocation; //户籍所在地---------------

	// 是否可以采集照片方式 0-both cloth 1-take photo only 2-update photo only 3-both
//	private String photoMethod = "0";

	// 用来更新个人信息
//	private String updateSign;
	private String messName;
	private String messSex;
	private String messBirthday;
	private String ssName;
	private String ssIdno;
	private String ssSex;
	private String ssBirthday;
	
	//2017-09-05 hunter
	//java bean aAbbb 形式大小写存在的问题
	

	private BaseUser optUser;
	public String getSsName() {
		return ssName;
	}

	public void setSsName(String ssName) {
		this.ssName = ssName;
	}

	public String getSsIdno() {
		return ssIdno;
	}

	public void setSsIdno(String ssIdno) {
		this.ssIdno = ssIdno;
	}

	public String getSsSex() {
		return ssSex;
	}

	public void setSsSex(String ssSex) {
		this.ssSex = ssSex;
	}

	public String getSsBirthday() {
		return ssBirthday;
	}

	public void setSsBirthday(String ssBirthday) {
		this.ssBirthday = ssBirthday;
	}

	private BaseStudentNoIdno baseStudentNoIdno=new BaseStudentNoIdno();
	
	
	/**
	 * 检查状态，判断现在是否可以采集照片zhangjb
	 * 
	 * @return
	 */
	public String view() {
		// 判断考试中，照片采集时间，如果当前时间不在条件时间内，则关闭页面
		if (!pgds.canGather()){
			this.GoBack("现在不是正式报名时间段，无法进行照片采集！");
			return null;
		}
		return "chkIdcard";
	}
	
	/**
	 * 检查状态，判断现在是否可以采集照片zhangjb
	 * 
	 * @return
	 */
	public String viewHtyt() {//华腾永泰身份证采集器的采集程序
		// 判断考试中，照片采集时间，如果当前时间不在条件时间内，则关闭页面
		if (!pgds.canGather()){
			this.GoBack("现在不是正式报名时间段，无法进行照片采集！");
			return null;
		}
		return "chkIdcardHtyt";
	}
	/**
	 * 进入手工输入页面（身份证信息采集）
	 * 
	 * @return
	 */
	public String hand(){
		return "chkIdcard_Hand";
	}
	
	/**
	 * 手工输入页面输入内容保存
	 * 
	 * @return
	 */
	public void manual(){
		studentInfo=pgds.qryStudInfo(baseStudentNoIdno.getPreapplyCode());
		baseStudentNoIdno.setNoIdnoReason(noIdnoReason);
		baseStudentNoIdno.setDocument(document);
		if(studentInfo==null){
			this.GoBack("人员信息在系统中不存在！");
			return;
		}
		else{
			this.optUser=super.getCOperUser();
			if (!studentInfo.getCityCode().equals(this.optUser.getCityCode())) {
				this.GoBack("该考生不属于本市！请考生到自己所属地市进行照片采集及信息确认！");
				return;
			}
			if ("1".equals(this.studentInfo.getStudInformationConfirmSign())) {
				this.GoBack("该考生信息已经确认！无法继续采集！");
				return;
			}
			BaseExamination baseExamination = pgds.qryBaseExamInfo(); // 获取本次考试信息
			if (!this.studentInfo.getExaminationCode().equals(baseExamination.getExaminationCode())) {
				this.GoBack("照片采集功能仅限于新考生！");
				return;
			}
/*			if(studentInfo.getManualInput().equals("2")){
				String js=String.format("alert('%1$s');location.href='%2$s/manager/camera/pho_view.do';",
						"该考生已经手工输入过，请通过“查询手工输入”按钮，查找该考生的信息！",request.getContextPath());
				super.PostJs(js);
				return;
			}*/
			OperatLog optLog = this.getOptLog(OperatLog.DB_INSERT, "身份证采集失败，保存手工输入原因！");
			boolean phoFlag =pgds.saveBaseStuNoIdno(baseStudentNoIdno, optLog);
			optLog = this.getOptLog(OperatLog.DB_UPDATE, "身份证采集失败，手工输入，修改BaseStudentInfo表是否手动输入为是！");
			if(phoFlag){
				phoFlag =  pgds.updateBaseStuInfoManual2(baseStudentNoIdno.getPreapplyCode(),optLog);
			}
	if(phoFlag && !noIdnoReason.equals("") && !document.equals("")&& !noIdnoReason.equals(null) && !document.equals(null)){
				String js=String.format("alert('%1$s');location.href='%2$s/manager/camera/pho_studShow.do?preapplyCode="+baseStudentNoIdno.getPreapplyCode()+"';",
						"手工输入成功！",request.getContextPath());
				super.PostJs(js);
				return;
			}else{
				if(noIdnoReason.equals("") || noIdnoReason.equals(null)){
					this.GoBack("手工输入失败:“未采集身份证原因”不能为空!");
				}
				if(document.equals("") || document.equals(null)){
					this.GoBack("手工输入失败:“证明材料”不能为空!");
				}
				return;
			}
		}
	}
	/**
	 * 进入已手工输入查询页面（身份证信息采集）
	 * 
	 * @return
	 */
	public String search(){
		return "chkIdcard_search";
	}
	/**
	 * 已手工输入信息查询的查询程序
	 * 
	 * @return
	 */
	public String dosearch(){
		if(pgds.isNull(preapplyCode)){
			this.GoBack("该页面无法显示，原因：没有参数！");
			return null;
		}
		this.studentInfo = pgds.qryStudInfo2(this.preapplyCode.trim());
		if(this.studentInfo==null){
			this.GoBack("该考生没有手工输入信息的记录！");
			return null;
		}
		this.optUser=super.getCOperUser();
		if (!this.studentInfo.getCityCode().equals(this.optUser.getCityCode())) {
			this.GoBack("该考生不属于本市！请考生到自己所属地市进行照片采集及信息确认！");
			return null;
		}
		BaseExamination baseExamination = pgds.qryBaseExamInfo(); // 获取本次考试信息
		if (!this.studentInfo.getExaminationCode().equals(baseExamination.getExaminationCode())) {
			this.GoBack("照片采集功能仅限于新考生！");
			return null;
		}
		if(!pgds.isNull(studentInfo.getManualInput()) && studentInfo.getManualInput().equals("2")){
			this.baseStudentNoIdno = pgds.qryStudNoIdno(studentInfo.getPreapplyCode());
			if(baseStudentNoIdno!=null){
				baseStudentNoIdno.setNoIdnoReason(baseStudentNoIdno.getNoIdnoReason().replace("\n", "<br/>"));
				baseStudentNoIdno.setDocument(baseStudentNoIdno.getDocument().replace("\n", "<br/>"));
			}
		}
		DbUtil dbUtil = new DbUtil();
		String[] paths = dbUtil.getPaths();
		String fileSep = System.getProperty("file.separator");
		if(!pgds.isNull(studentInfo.getStudPhotoFile1())){
			String photo1Sub = dbUtil.getConfig("21");
			netPhotoPath=paths[1] + fileSep + photo1Sub + fileSep+studentInfo.getStudPhotoFile1();
		}
		if(!pgds.isNull(studentInfo.getStudPhotoFile3())){
			String photo1Sub = dbUtil.getConfig("23");
			idcardnetPhotoPath=paths[1] + fileSep + photo1Sub + fileSep+studentInfo.getStudPhotoFile3();
		}
		return "chkIdcard_Ok";
	}
	
	/**
	 * 已手工输入信息查询的查询程序
	 * 
	 * @return
	 */
	public String studShow(){
		if(pgds.isNull(preapplyCode)){
			this.GoBack("该页面无法显示，原因：没有参数！");
			return null;
		}
		this.studentInfo = pgds.qryStudInfo(this.preapplyCode.trim());
		if(this.studentInfo==null){
			this.GoBack("该“新生报名号”系统中不存在！");
			return null;
		}
		this.optUser=super.getCOperUser();
		if (!this.studentInfo.getCityCode().equals(this.optUser.getCityCode())) {
			this.GoBack("该考生不属于本市！请考生到自己所属地市进行照片采集及信息确认！");
			return null;
		}
		if(!pgds.isNull(studentInfo.getManualInput()) && studentInfo.getManualInput().equals("2")){
			this.baseStudentNoIdno = pgds.qryStudNoIdno(studentInfo.getPreapplyCode());
			if(baseStudentNoIdno!=null){
				baseStudentNoIdno.setNoIdnoReason(baseStudentNoIdno.getNoIdnoReason().replace("\n", "<br/>"));
				baseStudentNoIdno.setDocument(baseStudentNoIdno.getDocument().replace("\n", "<br/>"));
			}
		}
		DbUtil dbUtil = new DbUtil();
		String[] paths = dbUtil.getPaths();
		String fileSep = System.getProperty("file.separator");
		if(!pgds.isNull(studentInfo.getStudPhotoFile1())){
			String photo1Sub = dbUtil.getConfig("21");
			netPhotoPath=paths[1] + fileSep + photo1Sub + fileSep+studentInfo.getStudPhotoFile1();
			//System.out.println("netPhotoPath1==="+netPhotoPath);
		}
		if(!pgds.isNull(studentInfo.getStudPhotoFile3())){
			String photo1Sub = dbUtil.getConfig("23");
			idcardnetPhotoPath=paths[1] + fileSep + photo1Sub + fileSep+studentInfo.getStudPhotoFile3();
		}
		return "chkIdcard_Ok";
	}
	
	/**
	 * 修改zhangjb
	 * 
	 * @return
	 */
	public String modify(){
		if(pgds.isNull(ssIdno)){
			this.GoBack("无法修改，原因：没有参数！");
			return null;
		}
		this.studentInfo = pgds.qryStudInfoWithId(this.ssIdno);
		if(studentInfo==null){
			this.GoBack("该身份证号码系统中不存在！");
			return null;
		}
		lGenderName=pgds.lGenderName();
		lStudFolk=pgds.lStudFolk();
		lStudPolitics=pgds.lStudPolitics();
		lStudSchoolAge=pgds.lStudSchoolAge();
		lStudHukouCharacter=pgds.lStudHukouCharacter();
		lStudOccupation=pgds.lStudOccupation();
		lStudHukouLocation=pgds.lStudHukouLocation();
		return "edit";
	}
	
	/**
	 * 身份证采集zhangjb
	 * 
	 * @return
	 */
	public String checkMess(){
		// 初始化查询条件
		if(pgds.isNull(ssIdno) || pgds.isNull(ssName) || pgds.isNull(ssSex) || pgds.isNull(ssBirthday) || pgds.isNull(idcardPhotoBuff)){
			this.GoBack("身份证采集失败，请重新采集！");
			return null;
		}
		// 查询考生
		this.studentInfo = pgds.qryStudInfoWithId(this.ssIdno.trim());
		if(studentInfo==null){
			this.GoBack("该身份证号码系统中不存在！");
			return null;
		}
		this.optUser=super.getCOperUser();
		if (!studentInfo.getCityCode().equals(this.optUser.getCityCode())) {
			this.GoBack("该考生不属于本市！请考生到自己所属地市进行照片采集及信息确认！");
			return null;
		}
		if ("1".equals(this.studentInfo.getStudInformationConfirmSign())) {
			this.GoBack("该考生信息已经确认！无法继续采集！");
			return null;
		}
		BaseExamination baseExamination = pgds.qryBaseExamInfo(); // 获取本次考试信息
		if (!this.studentInfo.getExaminationCode().equals(baseExamination.getExaminationCode())) {
			this.GoBack("照片采集功能仅限于新考生！");
			return null;
		}
		BASE64Decoder base64d = new BASE64Decoder();
		byte[] phoBytes = null;
		try {
			phoBytes = base64d.decodeBuffer(this.idcardPhotoBuff);
		} catch (IOException e) {
			new Log().error(this.getClass(), "解析证件照时，解码错误！", e);
			this.GoBack("解析证件照时，解码错误！");
			return null;
		}
		//保存二代身份证照片
		// 获得保存路径
		DbUtil dbUtil = new DbUtil();
		String[] paths = dbUtil.getPaths();
		String subPath = dbUtil.getConfig("23");
		String fileSep = System.getProperty("file.separator");
		String dir = paths[0] + fileSep + subPath + fileSep; // 本地全路径
		String fileName = ssIdno + ".jpeg"; // 照片文件名保存在数据库
		
		// 构造日志文件
		OperatLog optLog = this.getOptLog(OperatLog.DB_UPDATE, "二代身份证照片保存");

		// 并保存文件
		boolean phoFlag = FileManager.writeToFile(dir, fileName, phoBytes);
		boolean phoDbFlag = pgds.saveStudIdcardPhoto(ssIdno, fileName, optLog);
		if (phoFlag && phoDbFlag) {
		} else {
			this.GoBack("照片保存失败！");
			return null;
		}
		boolean bool=true;
		if(!studentInfo.getStudName().equals(ssName.trim())){
			bool=false;
			messName=ssName+"<br/><font style='color:red;'>（错误--系统姓名："+studentInfo.getStudName()+"）<br/>请点击下面的修改按钮，将系统中姓名修改成身份证上的姓名！</font>";
		}
		else{
			messName=ssName;
		}
		if(!studentInfo.getStudGender().equals(ssSex.trim())){
			bool=false;
			messSex=pgds.getGenderName(ssSex)+"<br/><font style='color:red;'>（错误--原系统中性别为："+studentInfo.getGenderName()+"）<br/>请点击下面的修改按钮，将系统中姓名修改成身份证上的姓别！</font>";
		}
		else{
			messSex=pgds.getGenderName(ssSex);
		}
		if(!studentInfo.getStudBirthday().equals(ssBirthday.trim())){
			bool=false;
			messBirthday=ssBirthday+"<br/><font style='color:red;'>（错误--原系统中系统出生日期为："+studentInfo.getStudBirthday()+"）<br/>请点击下面的修改按钮，将系统中出生日期修改成身份证上的出生日期！</font>";
		}
		else{
			messBirthday=ssBirthday;
		}
		optLog = this.getOptLog(OperatLog.DB_DELETE, "身份证采集失败，删除以手工方式输入的原因！");
		pgds.deleteBaseStuNoIdno(studentInfo.getPreapplyCode(), optLog);
		idcardnetPhotoPath=paths[1] + fileSep + subPath + fileSep+fileName;
		if(!pgds.isNull(studentInfo.getStudPhotoFile1())){
			String photo1Sub = dbUtil.getConfig("21");
			netPhotoPath=paths[1] + fileSep + photo1Sub + fileSep+studentInfo.getStudPhotoFile1();
		}
		if(bool){
			return "chkIdcard_Ok";
		}
		else return "chkIdcard_Wrong";
	}

	
	/**
	 * 更新相应数据（数据来源为二代身份证）
	 */
	public void changeMess(){
		// 初始化查询条件
		if(pgds.isNull(ssIdno) || pgds.isNull(ssName) || pgds.isNull(ssSex) || pgds.isNull(ssBirthday)){
			String js=String.format("alert('%1$s');location.href='%2$s/manager/camera/pho_view.do';", "身份证采集失败，请重新采集！",request.getContextPath());
			super.PostJs(js);
			return;
		}
		OperatLog optLog = this.getOptLog(OperatLog.DB_UPDATE, "更新个人信息为二代身份证信息！");
		boolean saveFlag=pgds.updateFromIdcard(ssIdno, ssName, ssSex, ssBirthday,optLog);
		this.studentInfo = pgds.qryStudInfoWithId(this.ssIdno);
		if ( saveFlag) {
			String js=String.format("alert('%1$s');location.href='%2$s/manager/camera/pho_studShow.do?preapplyCode="+studentInfo.getPreapplyCode()+"';",
					"更新个人信息为二代身份证信息成功！",request.getContextPath());
			super.PostJs(js);
			return;
		} else {
			String js=String.format("alert('%1$s');location.href='%2$s/manager/camera/pho_view.do';",
					"更新个人信息为二代身份证信息时失败！",request.getContextPath());
			super.PostJs(js);
			return;
		}
	}
	
	/**
	 * 取消照片
	 */
	public void cancelPhoto(){
		// 构造日志对象
		OperatLog optLog = this.getOptLog(OperatLog.DB_UPDATE, "摄像点，照片采集-撤销照片");

		// 查询考生
		this.studentInfo = pgds.qryStudInfo(this.preapplyCode);
		
		// 判断原先是否存在照片
		if ( "".equals(StringTool.trim(this.studentInfo.getStudPhotoFile1()))) {
			this.PostJs("alert('该考生无照片信息，无法撤销！');history.back();");
			return;
		}
		
		// 撤销照片
		boolean phoDbFlag = pgds.cancelStudPhoto(preapplyCode, optLog);
		if (phoDbFlag) {
			this.Alert("照片撤销成功！");
		} else {
			this.Alert("照片撤销失败！");
		}

		// 返回查询考生页面
		String urlJs = String
				.format(
						"location.href = '%1$s/manager/camera/pho_studShow.do?preapplyCode=%2$s';",
						request.getContextPath(), preapplyCode);
		this.PostJs(urlJs);
	}
	
	/**
	 * 全部信息修改zhangjb
	 * 
	 * @return
	 */
	
	public void update(){
		// 初始化查询条件
		if(pgds.isNull(studentInfo.getPreapplyCode())){
			this.GoBack("未修改成功，请重新修改！");
			return;
		}
//		OperatLog optLog = this.getOptLog(OperatLog.DB_UPDATE, "更新个人信息！");
		boolean saveFlag=pgds.updateFromIdcard(studentInfo);
		if ( saveFlag) {
			String js=String.format("alert('%1$s');location.href='%2$s/manager/camera/pho_studShow.do?preapplyCode="+studentInfo.getPreapplyCode()+"';",
					"更新考生信息成功！",request.getContextPath());
			super.PostJs(js);
			return;
		} else {
			this.GoBack("更新考生信息失败！");
			return;
		}
	}
		
	public String image(HttpServletRequest request){
		return "";
	}
	public void confirm(){
		if(pgds.isNull(preapplyCode)){
			String js=String.format("alert('%1$s');location.href='%2$s/manager/camera/pho_studShow.do?preapplyCode="+preapplyCode+"';",
					"无法确认，原因：没有参数！",request.getContextPath());
			super.PostJs(js);
			return;
		}
		OperatLog optLog = this.getOptLog(OperatLog.DB_UPDATE, "确认已发布的信息！");
		boolean saveFlag=pgds.updateFromPreapplyCode(preapplyCode,optLog);
		if ( saveFlag) {
			String js=String.format("alert('%1$s');location.href='%2$s/manager/camera/pho_studShow.do?preapplyCode="+preapplyCode+"';",
					"考生信息确认成功！",request.getContextPath());
			super.PostJs(js);
			return;
		} else {
			String js=String.format("alert('%1$s');location.href='%2$s/manager/camera/pho_studShow.do?preapplyCode="+preapplyCode+"';",
					"考生信息确认失败！",request.getContextPath());
			super.PostJs(js);
			return;
		}
	}
	
	/**
	 * 进入照片采集页面
	 * 
	 * @return
	 */
	public String preGather() {
		System.out.println("preapplyCode=="+preapplyCode);
		if(pgds.isNull(preapplyCode)){
			this.GoBack("无法修改，原因：没有参数！");
			return null;
		}
		// 查询考生
		this.studentInfo = pgds.qryStudInfo(preapplyCode);
		if(studentInfo==null){
			this.GoBack("该预报名号码系统中不存在！");
			return null;
		}
		this.optUser=super.getCOperUser();
		if (!studentInfo.getCityCode().equals(this.optUser.getCityCode())) {
			this.GoBack("该考生不属于本市！请考生到自己所属地市进行照片采集及信息确认！");
			return null;
		}
		if ("1".equals(this.studentInfo.getStudInformationConfirmSign())) {
			this.GoBack("该考生信息已经确认！无法继续采集！");
			return null;
		}
		DbUtil dbUtil = new DbUtil();
		String[] paths = dbUtil.getPaths();
		String fileSep = System.getProperty("file.separator");
		String photo1Sub = dbUtil.getConfig("21");
		netPhotoPath=paths[1] + fileSep + photo1Sub + fileSep+studentInfo.getStudPhotoFile1();
		//netPhotoPath="http://202.200.0.52:7001" + fileSep + photo1Sub + fileSep+studentInfo.getStudPhotoFile1();
		String phoUrl = dbUtil.getConfig("9");
//		String phoUrl = "http://localhost:7001";
		saveUrl = phoUrl + request.getContextPath()
		+ "/manager/camera/photo/photoUpload.jsp?preapplyCode="
		+ preapplyCode;
//		System.out.println("netPhotoPath="+netPhotoPath);
//		System.out.println("saveUrl="+saveUrl);
		this.session.put("PRE_APPLY_CODE_FOR_PHOTO", preapplyCode);
		// 获取网络地址
/*		DbUtil dbUtil = new DbUtil();
		String[] paths = dbUtil.getPaths();
		String photo1Sub = dbUtil.getConfig("21");
		String fileName = this.studentInfo.getPreapplyCode() + ".jpg";
		this.phoUrl = dbUtil.getConfig("9");
		this.netFilePath = paths[1] + photo1Sub + "/" + fileName;
		if ("".equals(StringTool.trim(this.studentInfo.getStudPhotoFile1()))) {
			this.initNetFilePath = paths[1] + "noPhoto.jpg";
		} else {
			this.initNetFilePath = paths[1] + photo1Sub + "/"
					+ this.studentInfo.getStudPhotoFile1();
		}
		this.saveUrl = phoUrl + request.getContextPath()
				+ "/manager/camera/photo/photoUpload.jsp?preapplyCode="
				+ preapplyCode;
		this.photoMethod = dbUtil.getConfig("10");

		// 将预报名号保存在session中
		this.session.put("PRE_APPLY_CODE_FOR_PHOTO", preapplyCode);*/

		return "photoGather";
	}
	

//	public String LoadImageToServer() throws Exception {
//		HttpSession session = request.getSession();
//		String preApplyCode = session.getAttribute("PRE_APPLY_CODE_FOR_PHOTO").toString();
//		String filePath = request.getParameter("url");
//	    String resultPath = "";          //上传后图片所在的路径
//	    String fileName = "";
//	    FileOutputStream out = null;     //文件输出流
//	    try {  //验证图片上传的格式是否正确
//	     File f = new File(filePath);
//	        if (!f.isFile()) {
//	        throw new Exception(" 不是图片文件!");
//	    }
//	     if (f != null && f.exists()) {          //这里的ImageIO属于java工厂类，在工厂类class里面，调用的System.gc()，频繁调用会造成dump，需要考虑优化
//	        BufferedImage image = ImageIO.read(f); // 读入文件
//	        if (image != null) {
//	        BufferedImage tag = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);  //构造一个类型为预定义图像类型之一的 BufferedImage
//	           tag.getGraphics().drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);                     //绘制所需要尺寸大小的图片
//	        /*
//	         * 以下生成图片上传后在服务器上的新路径
//	         */
//	           DbUtil dbUtil = new DbUtil();
//	           String[] paths = dbUtil.getPaths();
//	       	if ( !"".equals(StringTool.trim(preApplyCode))) {
//	       		// 构建路径
//	       		String serverPath = paths[0] + System.getProperty("file.separator") + dbUtil.getConfig("21") + System.getProperty("file.separator");
//	       		fileName = String.format("%1$s.jpg", preApplyCode);
//	       		resultPath = serverPath + fileName;
//	       		// 过滤路径
//	       		File file = new File(serverPath);
//	       		if (!file.exists()) {
//	       			file.mkdirs();
//	       		}  
//	       	}
//	        /*
//	         * 进行图片的绘制
//	         */
//	        out = new FileOutputStream(resultPath);
//	        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//	        JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(tag);
//	        param.setQuality(0.75f, true); //95%图像      
//	        param.setDensityUnit(1);                //像素尺寸单位.像素/英寸    
//	        param.setXDensity(300);                  //水平分辨率      
//	        param.setYDensity(300);                 //垂直分辨率
//	        encoder.setJPEGEncodeParam(param);
//	        encoder.encode(tag);
//	        int i = pgds.checkImg(resultPath);
//	        if(i!=0){
//	        	return "photoGather";
//	        }
//	        netPhotoPath=paths[1] + System.getProperty("file.separator") + dbUtil.getConfig("21") + System.getProperty("file.separator")+fileName;
//	        tag = null;
//	      }
//	     }
//	     f = null;
//
//	    } catch (Exception ex) {
//	     ex.printStackTrace();
//	    } finally {
//	     out.close();
//	     out = null;
//	    }
//	 // 构造操作日志
//	 	Object userObj = session.getAttribute(Constants.ZK_OPERATOR);
//	 	BaseUser optUser = (BaseUser) userObj;
//	 	OperatLog optLog = new OperatLog(optUser.getUserName(), request
//	 			.getRemoteAddr(), session.getAttribute(Constants.TREE_NODE_ID)
//	 			.toString(), request.getContextPath(), OperatLog.DB_UPDATE, "", "摄像点，照片采集");
//	 		
//	 	// 更新数据库对应字段
//	 	PhotoGatherDbService pgds1 = new PhotoGatherDbService();
//	 	if ( pgds1.saveStudPhoto(preApplyCode, fileName, StringTool.trim(optUser.getCameraPlaceCode()), optLog)) {
//	 		// 数据库更新成功
//	 	} else {
//	 		// 数据库更新失败
//	 			throw new Exception("error ：with db connection!");
//	 	}
//	    return "photoGather";
//	   } 
//	/**
//	 * 调用摄像头拍摄照片
//	 */
//	public String photoImg(){
//		Capture ca = new Capture();
//		HttpSession session = request.getSession();
//	    preApplyCode = session.getAttribute("PRE_APPLY_CODE_FOR_PHOTO").toString();
//		PhotofileName = "F:/tem/dev/simsun/PhotoImg/"+preApplyCode;
//		try {
//			 ca.toPhoto(preApplyCode);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return "photoGather"; 
//	}	
	/**
	 * 根据考生预报名号查询考生个人信息
	 * 
	 * @return
	 */

/*	public String studShow() {
		// 初始化查询条件
		this.preapplyCode = this.preapplyCode == null ? "" : this.preapplyCode;

		// 查询考生
		this.studentInfo = pgds.qryStudInfo(this.preapplyCode);

		
		if (this.studentInfo != null) {
			this.optUser = this.getCOperUser();
			// 判断是否属于本地市
			if ( !this.studentInfo.getCityCode().equals(this.optUser.getCityCode())) {
				this.Alert("该考生不属于本市！请考生到自己所属地市进行照片采集及信息确认！");
				this.PostJs(String.format(
						"location.href='%1$s/manager/camera/pho_studShow.do';",
						request.getContextPath()));
				return null;
			}
			
//			// 判断是否属于本考区
//			// 根据用户摄像点代码获取摄像点所属考区
//			String areaCode = pgds.getAreaFromCamera(optUser
//					.getCameraPlaceCode());
//			if (!this.studentInfo.getExamAreaCode().equals(areaCode)) {
//				this.Alert("该考生不属于本考区！请考生到自己所属考区进行照片采集及信息确认！");
//				this.PostJs(String.format(
//						"location.href='%1$s/manager/camera/pho_studShow.do';",
//						request.getContextPath()));
//				return null;
//			}

			// 判断用户是否已经信息确认过
			if ("1".equals(this.studentInfo.getStudInformationConfirmSign())) {
				this.Alert("该考生信息已经确认！无法继续采集！");
				this.PostJs(String.format(
						"location.href='%1$s/manager/camera/pho_studShow.do';",
						request.getContextPath()));
				return null;
			}
		}

		// 处理照片路径
		DbUtil dbUtil = new DbUtil();
		String[] paths = dbUtil.getPaths();
		String subPath = dbUtil.getConfig("21");
		if (this.studentInfo != null) {
			if (""
					.equals(StringTool.trim(this.studentInfo
							.getStudPhotoFile1()))) {
				this.studentInfo.setStudPhotoFile1(paths[1]
						+ "/noPhoto.jpg");
			} else {
				this.studentInfo.setStudPhotoFile1(paths[1] + "/" + subPath
						+ "/" + this.studentInfo.getStudPhotoFile1());
			}
		}

		return "showStudentInfo";
	}
*/
	/*----------set/get-----------*/
	public BaseStudentInfo getStudentInfo() {
		return studentInfo;
	}

	public void setStudentInfo(BaseStudentInfo studentInfo) {
		this.studentInfo = studentInfo;
	}

	public String getPreapplyCode() {
		return preapplyCode;
	}

	public void setPreapplyCode(String preapplyCode) {
		this.preapplyCode = StringTool.trim(preapplyCode);
	}

	public File getPhotoFile() {
		return photoFile;
	}

	public void setPhotoFile(File photoFile) {
		this.photoFile = photoFile;
	}

	public String getIdcardPhotoBuff() {
		return idcardPhotoBuff;
	}

	public void setIdcardPhotoBuff(String idcardPhotoBuff) {
		this.idcardPhotoBuff = idcardPhotoBuff;
	}

	public PhotoGatherDbService getPgds() {
		return pgds;
	}

	public void setPgds(PhotoGatherDbService pgds) {
		this.pgds = pgds;
	}

	public String getMessName() {
		return messName;
	}

	public void setMessName(String messName) {
		this.messName = messName;
	}

	public String getMessSex() {
		return messSex;
	}

	public void setMessSex(String messSex) {
		this.messSex = messSex;
	}

	public String getMessBirthday() {
		return messBirthday;
	}

	public void setMessBirthday(String messBirthday) {
		this.messBirthday = messBirthday;
	}

	
	public String getIdcardnetPhotoPath() {
		return idcardnetPhotoPath;
	}
	public void setIdcardnetPhotoPath(String idcardnetPhotoPath) {
		this.idcardnetPhotoPath = idcardnetPhotoPath;
	}
	public String getNetPhotoPath() {
		return netPhotoPath;
	}
	public void setNetPhotoPath(String netPhotoPath) {
		this.netPhotoPath = netPhotoPath;
	}
	public List<String[]> getLGenderName() {
		return lGenderName;
	}

	public void setLGenderName(List<String[]> genderName) {
		lGenderName = genderName;
	}

	public List<String[]> getLStudFolk() {
		return lStudFolk;
	}

	public void setLStudFolk(List<String[]> studFolk) {
		lStudFolk = studFolk;
	}

	public List<String[]> getLStudPolitics() {
		return lStudPolitics;
	}

	public void setLStudPolitics(List<String[]> studPolitics) {
		lStudPolitics = studPolitics;
	}

	public List<String[]> getLStudSchoolAge() {
		return lStudSchoolAge;
	}

	public void setLStudSchoolAge(List<String[]> studSchoolAge) {
		lStudSchoolAge = studSchoolAge;
	}

	public List<String[]> getLStudHukouCharacter() {
		return lStudHukouCharacter;
	}

	public void setLStudHukouCharacter(List<String[]> studHukouCharacter) {
		lStudHukouCharacter = studHukouCharacter;
	}

	public List<String[]> getLStudOccupation() {
		return lStudOccupation;
	}

	public void setLStudOccupation(List<String[]> studOccupation) {
		lStudOccupation = studOccupation;
	}

	public List<String[]> getLStudHukouLocation() {
		return lStudHukouLocation;
	}

	public void setLStudHukouLocation(List<String[]> studHukouLocation) {
		lStudHukouLocation = studHukouLocation;
	}

	public BaseStudentNoIdno getBaseStudentNoIdno() {
		return baseStudentNoIdno;
	}

	public void setBaseStudentNoIdno(BaseStudentNoIdno baseStudentNoIdno) {
		this.baseStudentNoIdno = baseStudentNoIdno;
	}
	
	public String getSaveUrl() {
		return saveUrl;
	}
	
	public void setSaveUrl(String saveUrl) {
		this.saveUrl = saveUrl;
	}	

	public String getPreApplyCode() {
		return preApplyCode;
	}

	public void setPreApplyCode(String preApplyCode) {
		this.preApplyCode = preApplyCode;
	}
	public String getPhotofileName() {
		return PhotofileName;
	}

	public void setPhotofileName(String photofileName) {
		PhotofileName = photofileName;
	}
	public String getNoIdnoReason() {
		return noIdnoReason;
	}

	public void setNoIdnoReason(String noIdnoReason) {
		this.noIdnoReason = noIdnoReason;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}
    
}