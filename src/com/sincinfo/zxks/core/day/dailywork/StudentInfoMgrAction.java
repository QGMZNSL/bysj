/**
 * Copyright(c) 2012 西安云海信息技术有限责任公司 All Rights Reserved<br>
 * @Title: com.sincinfo.zxks.core.day.dailywork.StudentInfoMgrAction.java<br>
 * @Description: 考生信息管理 <br>
 * <br>
 * @author litian<br>
 * @date Feb 6, 2012 9:13:20 AM 
 * @version V1.0   
 */
package com.sincinfo.zxks.core.day.dailywork;

import java.util.List;

import com.sincinfo.zxks.bean.BaseCity;
import com.sincinfo.zxks.bean.BaseStudentInfo;
import com.sincinfo.zxks.bean.BaseStudinfoChange;
import com.sincinfo.zxks.bean.BaseStudinfoChangeType;
import com.sincinfo.zxks.bean.BaseUser;
import com.sincinfo.zxks.bean.SysCode;
import com.sincinfo.zxks.common.Constants;
import com.sincinfo.zxks.common.action.WebActionSupport;
import com.sincinfo.zxks.common.db.DbUtil;
import com.sincinfo.zxks.common.util.Page;
import com.sincinfo.zxks.common.util.StringTool;
import com.sincinfo.zxks.core.day.dailywork.busi.PhotoModify;
import com.sincinfo.zxks.zxksdbs.StudentMgrDbService;
import com.sincinfo.zxks.zxksdbs.SysDbService;

/**
 * @ClassName: StudentInfoMgrAction
 * @Description: 考生信息管理 <br>
 *               <br>
 * @author litian
 * @date Feb 6, 2012 9:13:20 AM<br>
 * 
 */
public class StudentInfoMgrAction extends WebActionSupport {

	private static final long serialVersionUID = -1273750595746875810L;

	// 数据操作
	private StudentMgrDbService smds = new StudentMgrDbService();

	// 分页对象
	private Page page = new Page();

	// 地市列表
	private List<BaseCity> cityList;

	// 地市代码
	private String cityCode;

	// 考生姓名
	private String studName;

	// 考生中准考证号
	private String studExamCode;

	// 考生证件号
	private String studIdnum;
	// 考生预报名号
	private String preapplyCode;

	// 考生列表
	private List<BaseStudentInfo> studList;

	// 考生对象
	private BaseStudentInfo student;

	// 操作员
	private BaseUser optUser;

	// 性别列表
	private List<SysCode> genderList;

	// 证件类型列表
	private List<SysCode> idNoTypeList;

	// 民族列表
	private List<SysCode> forkList;

	// 变更类型列表
	private List<BaseStudinfoChangeType> changeTypeList;

	/* 内容部分 */
	// 修改内容
	private String changeTypeCode;
	// 修改原因
	private String modifyReason;
	// 证明材料
	private String proveMaterial;

	// 证件类型
	private String oldIdnoType;
	private String newIdnoType;

	// 证件
	private String oldIdnum;
	private String newIdnum;

	// 姓名
	private String oldStudName;
	private String newStudName;

	// 性别
	private String oldGender;
	private String newGender;

	// 出生日期
	private String oldBirthday;
	private String newBirthday;

	// 民族
	private String oldStudFolk;
	private String newStudFolk;

	/* 照片相关 */
	private String phoUrl;
	private String initNetFilePath;
	private String netFilePath;
	private String saveUrl;

	/* 考生修改信息来源 add by litian 2012-08-20 */
	private int fillinBy;
	private List<BaseStudinfoChange> changeReasons;

	/**
	 * 初始化分页地址
	 */
	private void initUrl() {
		StringBuilder url = new StringBuilder();
		url.append(request.getContextPath());
		url.append("/day/dailywork/studMgr_qryStudent.do?ksgl=1");
		url.append(String.format("&cityCode=%1$s", this.cityCode.trim()));
		url.append(String.format("&studName=%1$s", this.studName.trim()));
		url.append(String
				.format("&studExamCode=%1$s", this.studExamCode.trim()));
		url.append(String.format("&studIdnum=%1$s", this.studIdnum.trim()));
		url.append(String.format("&fillinBy=%1$s", String
				.valueOf(this.fillinBy)));
		page.setPath(url.toString());
	}

	/**
	 * 初始化地市列表与当前属性值
	 */
	public void init() {
		this.optUser = this.getCOperUser();

		// 初始化表单值
		if ("610000".equals(this.optUser.getCityCode())) {
			this.cityCode = this.cityCode == null ? "" : this.cityCode.trim();
		} else {
			this.cityCode = this.cityCode == null ? this.optUser.getCityCode()
					: this.cityCode.trim();
		}
		this.studName = this.studName == null ? "" : this.studName.trim();
		this.studExamCode = this.studExamCode == null ? "" : this.studExamCode
				.trim();
		this.studIdnum = this.studIdnum == null ? "" : this.studIdnum.trim();

		this.cityList = smds.qryCityList1(this.optUser.getUserRole(),
				this.optUser.getCityCode());

		initUrl();
	}

	/**
	 * 按照属性值指定的条件进行查询考生
	 * 
	 * @return
	 */
	public String qryStudent() {
		init();

		// add by litian for 缺少参数
		if (!(this.fillinBy == 1 || this.fillinBy == 2)) {
			this.GoBack("系统异常！");
			return null;
		}

		// 查询考生列表
		this.studList = smds.qryStudentList(this.cityCode, this.studExamCode,
				this.studName, this.studIdnum,this.preapplyCode, this.page);

		return "studentsShow";
	}

	/**
	 * 重置考生密码
	 */
	public void resetStudPassword() {

		boolean resetFlag = this.smds.reseJtStudPwd(this.studExamCode);

		if (resetFlag) {
			// 成功
			this.Alert("重置考生密码成功！");
		} else {
			// 失败
			this.GoBack("重置考生密码失败！");
		}
		this.PostJs(String.format("location.href='%1$s';", this.session
				.get(Constants.CURR_PAGE)));
	}

	/**
	 * 进入修改页面
	 * 
	 * @return
	 */
	public String preEdit() {
		// 判断是否为考籍进入，如果是考籍进入则需要判断时间是否在申办毕业时间段内，如果是考务则不需要判断
		if (this.fillinBy == 2) {
			// 考籍，判断申办毕业时间
			if (!smds.duringGraApply()) {
				// 不在申办时间段内，提示并返回
				this.GoBack("不在申办毕业证时间范围内！");
				return null;
			}
		} else {
			// 考务，不做处理
		}

		// 查询考生信息
		this.student = smds.qryStudent(this.studExamCode);

		// 获取设置列表
		SysDbService sds = new SysDbService();
		this.genderList = sds.qrySysDict(SysDbService.SYS_DICT_GENDER);
		this.idNoTypeList = sds.qrySysDict(SysDbService.SYS_DICT_IDNO_TYPE);
		this.forkList = sds.qrySysDict(SysDbService.SYS_DICT_FOLK);
		this.changeTypeList = smds.qryChangeTypeList();

		// 处理照片路径
		DbUtil dbUtil = new DbUtil();
		String[] paths = dbUtil.getPaths();
		String subPath = dbUtil.getConfig("21");
		if (this.student != null) {
			if ("".equals(StringTool.trim(this.student.getStudPhotoFile1()))) {
				this.student.setStudPhotoFile1(request.getContextPath()
						+ "/noPhoto.jpg");
			} else {
				this.student.setStudPhotoFile1(paths[1] + "/" + subPath + "/"
						+ this.student.getStudPhotoFile1());
			}
		}
		changeReasons=smds.lBaseStudinfoChange(studExamCode);
		return "studentEdit";
	}

	/**
	 * 申请照片信息变更
	 * 
	 * @return
	 */
	public String applyPhotoChange() {
		// 查询考生信息
		this.student = smds.qryStudent(this.studExamCode);

		// 获取网络地址
		DbUtil dbUtil = new DbUtil();
		String[] paths = dbUtil.getPaths();
		String photo1Sub = dbUtil.getConfig("21");
		String photoModify = dbUtil.getConfig("33");
		String fileName = this.student.getStudExamCode() + ".jpg";
		this.phoUrl = dbUtil.getConfig("9");
		this.netFilePath = paths[1] + photoModify + "/" + fileName;
		if ("".equals(StringTool.trim(this.student.getStudPhotoFile1()))) {
			this.initNetFilePath = paths[1] + "noPhoto.jpg";
		} else {
			this.initNetFilePath = paths[1] + photo1Sub + "/"
					+ this.student.getStudPhotoFile1();
		}
		this.saveUrl = phoUrl + request.getContextPath()
				+ "/manager/day/dailywork/studPhoto/photoUpload.jsp";

		// 重要数据整理并保存在session中
		PhotoModify phoMod = new PhotoModify(this.student, this.changeTypeCode,
				this.modifyReason, this.proveMaterial, Integer
						.parseInt(StringTool.trim(this.fillinBy)));
		this.session.put(PhotoModify.STUD_MGR_PHOTO_MODIFY, phoMod);

		return "studentPhotoGather";
	}

	/**
	 * 保存（5个基础字段的申请）
	 */
	public void saveModifyApply() {
		this.changeTypeCode = StringTool.trim(this.changeTypeCode);
		this.changeTypeCode = this.changeTypeCode == null ? "1"
				: this.changeTypeCode;
		String oldInfo = null;
		String newInfo = null;

		// 判断变更类型，其中不考虑照片。变更照片单独处理。
		int ctypeCode = Integer.parseInt(this.changeTypeCode.trim());
		switch (ctypeCode) {
		case 1:
			oldInfo = this.oldStudName;
			newInfo = this.newStudName;
			break;
		case 2:
			oldInfo = this.oldIdnoType;
			newInfo = this.newIdnoType;
			break;
		case 3:
			oldInfo = this.oldIdnum;
			newInfo = this.newIdnum;
			break;
		case 4:
			oldInfo = this.oldGender;
			newInfo = this.newGender;
			break;
		case 5:
			oldInfo = this.oldBirthday;
			newInfo = this.newBirthday;
			break;
		case 6:
			oldInfo = this.oldStudFolk;
			newInfo = this.newStudFolk;
			break;
		default:
			break;
		}

		// 获取操作员
		this.optUser = this.getCOperUser();

		// 保存考生基础信息变更申请
		boolean saveFlag = this.smds.saveStudInfoChange(this.studExamCode,
				this.changeTypeCode, oldInfo.trim(), newInfo.trim(),
				this.modifyReason.trim(), this.proveMaterial.trim(),
				this.optUser.getUserName(), this.fillinBy);

		// 根据保存情况提示并进行跳转
		StringBuilder urlJs = new StringBuilder();
		urlJs.append("location.href = '");
		urlJs.append(request.getContextPath());
		urlJs.append("/day/dailywork/studMgr_qryStudent.do?ksgl=1");
		urlJs.append(String.format("&fillinBy=%1$s", String
				.valueOf(this.fillinBy)));
		urlJs.append(String.format("&cityCode=%1$s", this.cityCode));
		urlJs.append(String.format("&studExamCode=%1$s", this.studExamCode));
		urlJs.append("';");

		if (saveFlag) {
			this.Alert("考生信息变更申请保存成功！");
		} else {
			this.Alert("考生信息变更申请保存失败！");
		}
		this.PostJs(urlJs.toString());
	}

	/*---set/get---*/
	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public List<BaseCity> getCityList() {
		return cityList;
	}

	public void setCityList(List<BaseCity> cityList) {
		this.cityList = cityList;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getStudName() {
		return studName;
	}

	public void setStudName(String studName) {
		this.studName = studName;
	}

	public String getStudExamCode() {
		return studExamCode;
	}

	public void setStudExamCode(String studExamCode) {
		this.studExamCode = studExamCode;
	}

	public String getStudIdnum() {
		return studIdnum;
	}

	public void setStudIdnum(String studIdnum) {
		this.studIdnum = studIdnum;
	}

	public List<BaseStudentInfo> getStudList() {
		return studList;
	}

	public void setStudList(List<BaseStudentInfo> studList) {
		this.studList = studList;
	}

	public BaseStudentInfo getStudent() {
		return student;
	}

	public void setStudent(BaseStudentInfo student) {
		this.student = student;
	}

	public List<SysCode> getGenderList() {
		return genderList;
	}

	public void setGenderList(List<SysCode> genderList) {
		this.genderList = genderList;
	}

	public List<SysCode> getIdNoTypeList() {
		return idNoTypeList;
	}

	public void setIdNoTypeList(List<SysCode> idNoTypeList) {
		this.idNoTypeList = idNoTypeList;
	}

	public List<SysCode> getForkList() {
		return forkList;
	}

	public void setForkList(List<SysCode> forkList) {
		this.forkList = forkList;
	}

	public String getModifyReason() {
		return modifyReason;
	}

	public void setModifyReason(String modifyReason) {
		this.modifyReason = modifyReason;
	}

	public String getProveMaterial() {
		return proveMaterial;
	}

	public void setProveMaterial(String proveMaterial) {
		this.proveMaterial = proveMaterial;
	}

	public String getOldIdnum() {
		return oldIdnum;
	}

	public void setOldIdnum(String oldIdnum) {
		this.oldIdnum = oldIdnum;
	}

	public String getNewIdnoType() {
		return newIdnoType;
	}

	public void setNewIdnoType(String newIdnoType) {
		this.newIdnoType = newIdnoType;
	}

	public String getNewIdnum() {
		return newIdnum;
	}

	public void setNewIdnum(String newIdnum) {
		this.newIdnum = newIdnum;
	}

	public String getOldStudName() {
		return oldStudName;
	}

	public void setOldStudName(String oldStudName) {
		this.oldStudName = oldStudName;
	}

	public String getNewStudName() {
		return newStudName;
	}

	public void setNewStudName(String newStudName) {
		this.newStudName = newStudName;
	}

	public String getOldGender() {
		return oldGender;
	}

	public void setOldGender(String oldGender) {
		this.oldGender = oldGender;
	}

	public String getNewGender() {
		return newGender;
	}

	public void setNewGender(String newGender) {
		this.newGender = newGender;
	}

	public String getOldBirthday() {
		return oldBirthday;
	}

	public void setOldBirthday(String oldBirthday) {
		this.oldBirthday = oldBirthday;
	}

	public String getNewBirthday() {
		return newBirthday;
	}

	public void setNewBirthday(String newBirthday) {
		this.newBirthday = newBirthday;
	}

	public String getOldStudFolk() {
		return oldStudFolk;
	}

	public void setOldStudFolk(String oldStudFolk) {
		this.oldStudFolk = oldStudFolk;
	}

	public String getNewStudFolk() {
		return newStudFolk;
	}

	public void setNewStudFolk(String newStudFolk) {
		this.newStudFolk = newStudFolk;
	}

	public String getOldIdnoType() {
		return oldIdnoType;
	}

	public void setOldIdnoType(String oldIdnoType) {
		this.oldIdnoType = oldIdnoType;
	}

	public String getChangeTypeCode() {
		return changeTypeCode;
	}

	public void setChangeTypeCode(String changeTypeCode) {
		this.changeTypeCode = changeTypeCode;
	}

	public List<BaseStudinfoChangeType> getChangeTypeList() {
		return changeTypeList;
	}

	public void setChangeTypeList(List<BaseStudinfoChangeType> changeTypeList) {
		this.changeTypeList = changeTypeList;
	}

	public String getPhoUrl() {
		return phoUrl;
	}

	public void setPhoUrl(String phoUrl) {
		this.phoUrl = phoUrl;
	}

	public String getInitNetFilePath() {
		return initNetFilePath;
	}

	public void setInitNetFilePath(String initNetFilePath) {
		this.initNetFilePath = initNetFilePath;
	}

	public String getNetFilePath() {
		return netFilePath;
	}

	public void setNetFilePath(String netFilePath) {
		this.netFilePath = netFilePath;
	}

	public String getSaveUrl() {
		return saveUrl;
	}

	public void setSaveUrl(String saveUrl) {
		this.saveUrl = saveUrl;
	}

	public int getFillinBy() {
		return fillinBy;
	}

	public void setFillinBy(int fillinBy) {
		this.fillinBy = fillinBy;
	}

	public BaseUser getOptUser() {
		return optUser;
	}

	public void setOptUser(BaseUser optUser) {
		this.optUser = optUser;
	}

	public List<BaseStudinfoChange> getChangeReasons() {
		return changeReasons;
	}

	public void setChangeReasons(List<BaseStudinfoChange> changeReasons) {
		this.changeReasons = changeReasons;
	}

	public String getPreapplyCode() {
		return preapplyCode;
	}

	public void setPreapplyCode(String preapplyCode) {
		this.preapplyCode = preapplyCode;
	}
}