/**
 * Copyright(c) 2012 西安云海信息技术有限责任公司 All Rights Reserved<br>
 * @Title: com.sincinfo.zxks.zxksdbs.PhotoGatherDbService.java<br>
 * @Description: 照片采集的数据库操作 <br>
 * <br>
 * @author litian<br>
 * @date Feb 2, 2012 9:47:48 AM 
 * @version V1.0   
 */
package com.sincinfo.zxks.zxksdbs;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import java.util.List;

import com.sincinfo.zxks.bean.BaseExamination;
import com.sincinfo.zxks.bean.BaseStudentInfo;
import com.sincinfo.zxks.bean.BaseStudentNoIdno;
import com.sincinfo.zxks.common.db.DbUtil;
import com.sincinfo.zxks.common.log.OperatLog;
import com.sincinfo.zxks.common.log.OperateLogTool;

/**
 * @ClassName: PhotoGatherDbService
 * @Description: 照片采集的数据库操作 <br>
 *               <br>
 * @author litian
 * @date Feb 2, 2012 9:47:48 AM<br>
 * 
 */
public class PhotoGatherDbService {
	DbUtil dbUtil;

	public PhotoGatherDbService() {
		dbUtil = new DbUtil();
	}
	
	public boolean isNull(String str) {
		if (str != null) {
			str = str.trim();
		}
		return str == null || str.equals("");
	}

	/**
	 * 根据预报名号查询考生预报名信息
	 * 
	 * @param preApplyCode
	 *            预报名号
	 * @return BaseStudentInfo
	 */
	public BaseStudentInfo qryStudInfo(String preApplyCode) {
		BaseStudentInfo stud = new BaseStudentInfo();
		StringBuilder sql = new StringBuilder();
		sql.append(stud.createSqlWithoutWhere());
		sql.append(String.format(" where i.preapply_code = '%1$s'",
				preApplyCode));
		//System.out.println("sql=="+sql);
		stud = dbUtil.getObject(sql.toString(), BaseStudentInfo.class);
		return stud;
	}
	
	public BaseStudentInfo qryStudInfo2(String preApplyCode) {
		BaseStudentInfo stud = new BaseStudentInfo();
		StringBuilder sql = new StringBuilder();
		sql.append(stud.createSqlWithoutWhere());
		sql.append(String.format(" where i.preapply_code = '%1$s' and i.MANUAL_INPUT='2'",
				preApplyCode));
		stud = dbUtil.getObject(sql.toString(), BaseStudentInfo.class);
		return stud;
	}

	public String getGenderName(String stud_gender){
		String sql="select cg.name from sys_code_gender cg where cg.code = ?";
		return dbUtil.getFirstCol(sql, stud_gender);
	}
	
	
	public BaseExamination qryBaseExamInfo() {
        BaseExamination baseInfo = null;
        String sql = String
                .format("select * from base_examination t where nonce = 1");
        baseInfo = dbUtil.getObject(sql, BaseExamination.class);
        return baseInfo;
    }
	
	/**
	 * 根据预身份证号查询考生预报名信息
	 * 
	 * @param preApplyCode
	 *            预报名号
	 * @return BaseStudentInfo
	 */
	public BaseStudentInfo qryStudInfoWithId(String studIdnum) {
		BaseStudentInfo stud = new BaseStudentInfo();
		StringBuilder sql = new StringBuilder();
		sql.append(stud.createSqlWithoutWhere());
		sql.append(String.format(" where i.STUD_IDNUM = '%1$s'",
				studIdnum));
		stud = dbUtil.getObject(sql.toString(), BaseStudentInfo.class);
		return stud;
	}
	
	public BaseStudentNoIdno qryStudNoIdno(String preapplyCore) {
		BaseStudentNoIdno baseStudentNoIdno;
		String sql="select * from BASE_STUDENT_NO_IDNO b where b.PREAPPLY_CODE=?";
		baseStudentNoIdno = dbUtil.getObject(sql, BaseStudentNoIdno.class, preapplyCore);
		return baseStudentNoIdno;
	}

	/**
	 * 取消考生照片
	 * 
	 * @param preApplyCode
	 *            预报名号
	 * @param optLog
	 *            操作日志
	 * @return boolean 是否执行成功
	 */
	public boolean cancelStudPhoto(String preApplyCode, OperatLog optLog) {
		boolean cancelFlag = false;
		StringBuilder sql = new StringBuilder();
		sql.append("update base_student_info i");
		sql.append(" set i.stud_photo_file_1   = '',");
		sql.append(" i.camera_place_code = '',");
		sql.append(" i.photo_gather_time = ''");
		sql.append(String.format(" where i.preapply_code = '%1$s'",
				preApplyCode));
		cancelFlag = dbUtil.saveOrUpdate(sql.toString()) == 1;

		// 保存操作日志
		if (cancelFlag) {
			optLog.setLogOptSql(sql.toString());
			OperateLogTool.saveOptLog(optLog);
		}

		return cancelFlag;
	}

	/**
	 * 保存考生照片
	 * 
	 * @param preApplyCode
	 *            预报名号
	 * @param photoFile
	 *            照片文件
	 * @param cameraPlaceCode
	 *            摄像点代码
	 * @param optLog
	 *            操作日志
	 * @return boolean 执行是否成功
	 */
	public boolean saveStudPhoto(String preApplyCode, String photoFile,
			String cameraPlaceCode, OperatLog optLog) {
		boolean saveFlag = false;
		StringBuilder sql = new StringBuilder();
		sql.append("update base_student_info i");
		sql.append(String.format(" set i.stud_photo_file_1   = '%1$s',",
				photoFile));
		sql.append(String.format(" i.camera_place_code = '%1$s',",
				cameraPlaceCode));
		sql.append(" i.photo_gather_time = sysdate");
		sql.append(String.format(" where i.preapply_code = '%1$s'",
				preApplyCode));
		saveFlag = dbUtil.saveOrUpdate(sql.toString()) == 1;

		// 保存操作日志
		if (saveFlag) {
			optLog.setLogOptSql(sql.toString());
			OperateLogTool.saveOptLog(optLog);
		}

		return saveFlag;
	}

	/**
	 * 保存考生照片(身份证)
	 * 
	 * @param preApplyCode
	 *            预报名号
	 * @param photoFile
	 *            照片文件
	 * @param optLog
	 *            操作日志
	 * @return boolean 执行是否成功
	 */
	public boolean saveStudIdcardPhoto(String studIdnum, String fileName,
			OperatLog optLog) {
		boolean saveFlag = false;
		StringBuilder sql = new StringBuilder();
		sql.append("update base_student_info i");
		sql.append(String.format(" set i.stud_photo_file_3   = '%1$s'",
				fileName));
		sql.append(String.format(" where i.STUD_IDNUM = '%1$s'",
				studIdnum));
		saveFlag = dbUtil.saveOrUpdate(sql.toString()) == 1;

		// 保存操作日志
		if (saveFlag) {
			optLog.setLogOptSql(sql.toString());
			OperateLogTool.saveOptLog(optLog);
		}

		return saveFlag;
	}

	/**
	 * 根据摄像点获取所属考区
	 * 
	 * @param cameraPlaceCode
	 *            摄像点代码
	 * @return String 考区代码
	 */
	public String getAreaFromCamera(String cameraPlaceCode) {
		String sql = "select cp.exam_area_code from base_camera_place cp where cp.camera_place_code = '%1$s'";
		DbUtil dbUtil = new DbUtil();
		return dbUtil.getFirstCol(String.format(sql, cameraPlaceCode));
	}

	/**
	 * 判断是否允许采集照片，至少在一个考试的正式报名时间段内
	 * 
	 * @return boolean
	 */
	public boolean canGather() {
		String sql = "select count(*) from base_examination e where e.PRE_APPLY_START <= %1$s and e.gather_end >= %1$s";
		sql = String.format(sql, "to_date(to_char(sysdate, 'yyyy-mm-dd'), 'yyyy-mm-dd')");
		DbUtil dbUtil = new DbUtil();
		return dbUtil.getNum(sql) >= 1;
	}

	/**
	 * 将个人信息部分更新为身份证上的数据
	 * 
	 * @param preApplyCode
	 *            预报名号
	 * @param updateSign
	 *            更新内容
	 * @param oldInfo
	 *            旧信息
	 * @param newInfo
	 *            新信息
	 * @param optLog
	 *            操作日志
	 * @return boolean
	 */
	public boolean updateFromIdcard(String sIdno, String sName,String sSex,String sBirthday,OperatLog optLog) {
		//) || pgds.isNull() || pgds.isNull() || pgds.isNull(sBirthday
		boolean flag = false;
		StringBuilder sql = new StringBuilder();
		sql.append("update base_student_info i");
		sql.append(String.format(" set i.STUD_NAME = '%1$s'", sName));
		sql.append(String.format(",i.STUD_GENDER = '%1$s'", sSex));
		sql.append(String.format(",i.STUD_BIRTHDAY = '%1$s'", sBirthday));
		sql.append(String.format(" where i.STUD_IDNUM = '%1$s'",sIdno));
		flag = dbUtil.saveOrUpdate(sql.toString()) == 1;

		// 保存操作日志
		if (flag) {
			optLog.setLogOptSql(sql.toString());
			OperateLogTool.saveOptLog(optLog);
		}
		return flag;
	}
	
	public boolean updateFromIdcard(BaseStudentInfo studentInfo) {
		boolean flag = false;
		StringBuilder sb = new StringBuilder();
		sb.append("update base_student_info i set");
		sb.append(String.format(" i.STUD_IDNUM = '%1$s'", studentInfo.getStudIdnum()));
		sb.append(String.format(",i.STUD_BIRTHDAY = '%1$s'", studentInfo.getStudBirthday()));
		sb.append(String.format(",i.STUD_NAME = '%1$s'", studentInfo.getStudName()));
		sb.append(String.format(",i.STUD_GENDER = '%1$s'", studentInfo.getStudGender()));
		sb.append(String.format(",i.STUD_FOLK = '%1$s'", studentInfo.getStudFolk()));
		sb.append(String.format(",i.STUD_POLITICS = '%1$s'", studentInfo.getStudPolitics()));
		sb.append(String.format(",i.STUD_SCHOOL_AGE = '%1$s'", studentInfo.getStudSchoolAge()));
		sb.append(String.format(",i.STUD_EMAIL = '%1$s'", studentInfo.getStudEmail()));
		sb.append(String.format(",i.STUD_OCCUPATION = '%1$s'", studentInfo.getStudOccupation()));
		sb.append(String.format(",i.STUD_HUKOU_CHARACTER = '%1$s'", studentInfo.getStudHukouCharacter()));
		sb.append(String.format(",i.STUD_HUKOU_LOCATION = '%1$s'", studentInfo.getStudHukouLocation()));
		sb.append(String.format(",i.STUD_TELEPHONE = '%1$s'", studentInfo.getStudTelephone()));
		sb.append(String.format(",i.STUD_MOBILE_PHONE = '%1$s'", studentInfo.getStudMobilePhone()));
		sb.append(String.format(",i.STUD_POSTAL_CODE = '%1$s'", studentInfo.getStudPostalCode()));
		sb.append(String.format(",i.STUD_POSTAL_ADDRESS = '%1$s'", studentInfo.getStudPostalAddress()));
		sb.append(String.format(" where i.PREAPPLY_CODE = '%1$s'",studentInfo.getPreapplyCode()));
		flag = dbUtil.saveOrUpdate(sb.toString()) == 1;
		// 保存操作日志
/*		if (flag && studentInfo.getManualInput()!=null && studentInfo.getManualInput().equals("2")) {
			String sql="select * from BASE_STUDENT_NO_IDNO b where b.PREAPPLY_CODE=?";
			BaseStudentNoIdno baseStuNoIdno = dbUtil.getObject(sql, BaseStudentNoIdno.class, studentInfo.getPreapplyCode());
			if(baseStuNoIdno==null){
				baseStuNoIdno=new BaseStudentNoIdno();
				baseStuNoIdno.setPreapplyCode(studentInfo.getPreapplyCode());
				baseStuNoIdno.setDocument(bsni.getDocument());
				baseStuNoIdno.setNoIdnoReason(bsni.getNoIdnoReason());
				flag=saveBaseStuNoIdno(baseStuNoIdno,optLog);
				if(flag){
					optLog.setLogOptSql(sb.toString()+"-"+sql);
					OperateLogTool.saveOptLog(optLog);
				}
			}
			else{
				baseStuNoIdno.setDocument(bsni.getDocument());
				baseStuNoIdno.setNoIdnoReason(bsni.getNoIdnoReason());
				sql=String.format("update BASE_STUDENT_NO_IDNO b set b.NO_IDNO_REASON='%1$s',b.DOCUMENT='%2$s' where b.PREAPPLY_CODE='%3$s'"
						,bsni.getNoIdnoReason(),bsni.getDocument(),studentInfo.getPreapplyCode());
				flag = dbUtil.saveOrUpdate(sql.toString()) == 1;
				if(flag){
					optLog.setLogOptSql(sb.toString()+"-"+sql);
					OperateLogTool.saveOptLog(optLog);
				}
			}
		}*/
//		System.out.println(sb.toString());
		return flag;
	}
	
	public boolean updateFromPreapplyCode(String preapplyCode, OperatLog optLog) {
		boolean flag = false;
		StringBuilder sql = new StringBuilder();
		sql.append("update base_student_info i");
		sql.append(" set i.STUD_INFORMATION_CONFIRM_SIGN = '1'");
		sql.append(String.format(" where i.PREAPPLY_CODE = '%1$s'",preapplyCode));
		flag = dbUtil.saveOrUpdate(sql.toString()) == 1;

		// 保存操作日志
		if (flag) {
			optLog.setLogOptSql(sql.toString());
			OperateLogTool.saveOptLog(optLog);
		}
		return flag;
	}
	
	public boolean saveBaseStuNoIdno(BaseStudentNoIdno baseStuNoIdno,OperatLog optLog) {
		boolean flag = false;
		StringBuilder sb;
		String sql;
		sql="select count(*) from BASE_STUDENT_NO_IDNO where PREAPPLY_CODE=?";
		Long l=dbUtil.getNum(sql,baseStuNoIdno.getPreapplyCode());
		if(l>0){
			sb = new StringBuilder();
			sb.append("update BASE_STUDENT_NO_IDNO");
			sb.append(" set NO_IDNO_REASON='%1$s',DOCUMENT='%2$s' where PREAPPLY_CODE='%3$s'");
			sql=String.format(sb.toString(),baseStuNoIdno.getNoIdnoReason(),baseStuNoIdno.getDocument(),baseStuNoIdno.getPreapplyCode());
		}
		else{
			sb = new StringBuilder();
			sb.append("insert into BASE_STUDENT_NO_IDNO");
			sb.append(" (PREAPPLY_CODE,NO_IDNO_REASON,DOCUMENT) values ('%1$s','%2$s','%3$s')");
			sql=String.format(sb.toString(),baseStuNoIdno.getPreapplyCode(),baseStuNoIdno.getNoIdnoReason(),baseStuNoIdno.getDocument());
		}
		flag = dbUtil.saveOrUpdate(sql) == 1;
		// 保存操作日志
		if (flag) {
			optLog.setLogOptSql(sql.toString());
			OperateLogTool.saveOptLog(optLog);
		}
		return flag;
	}
	
	public boolean updateBaseStuInfoManual2(String preapplyCode,OperatLog optLog){
		boolean flag = false;
		String sb;
		String sql;
		sb="update BASE_STUDENT_INFO b set b.MANUAL_INPUT=2 where b.PREAPPLY_CODE='%1$s'";
		sql=String.format(sb.toString(),preapplyCode);
		flag = dbUtil.saveOrUpdate(sql) == 1;
		// 保存操作日志
		if (flag) {
			optLog.setLogOptSql(sql.toString());
			OperateLogTool.saveOptLog(optLog);
		}
		return flag;
	}
	
	public boolean deleteBaseStuNoIdno(String preapplyCode, OperatLog optLog) {
		boolean flag = false;
		String sb;
		String sql;
		sb="update BASE_STUDENT_INFO b set b.MANUAL_INPUT=1 where b.PREAPPLY_CODE=?";//是否手动输入     默认0： 0未确认1不是 2是
		sql=String.format(sb.toString(),preapplyCode);
		flag = dbUtil.saveOrUpdate(sql) == 1;
		if(flag){
			sb="delete from BASE_STUDENT_NO_IDNO b where b.PREAPPLY_CODE=?";
			sql=String.format(sb.toString(),preapplyCode);
			flag = dbUtil.saveOrUpdate(sql) == 1;
		}
		// 保存操作日志
		if (flag) {
			optLog.setLogOptSql(sql.toString());
			OperateLogTool.saveOptLog(optLog);
		}
		return flag;
	}
		public int checkImg(String url)throws IOException{
	           File picture = new File(url);
	           int i=0;   //不符合照片标准的代号
	           double memory = picture.length()/1024.0;
	           System.out.println("memory="+memory);
	           if(memory >= 40 && memory < 60){
	        	   i=1;

	           }else if (memory <= 20 && memory>10){
	        	   i=2;
	           }else if(memory>=60 && memory < 80){
	        	   i=3;
	           }else if(memory>=80 || memory<=10){
	        	   i=4;
	           }
//	        	   else if(memory>=100){
//	        	   i=5;
//	           }
	           else{
	        	   i=0;
	           }
	           return i;
	}
		
	public List<String[]> lGenderName(){
		String sql="select cg.code,cg.name from sys_code_gender cg order by cg.code";
		return dbUtil.getRsArrayList(sql,2);
	}
	
	public List<String[]> lProCode(){
		String sql="select bp.pro_code,bp.pro_name from base_pro bp order by bp.pro_code";
		return dbUtil.getRsArrayList(sql,2);
	}
	
	public List<String[]> lLevelCode(){
		String sql="select l.level_code,l.level_name from base_level l where l.is_use=1 order by l.level_code";
		return dbUtil.getRsArrayList(sql,2);
	}
	
	public List<String[]> lStudFolk(){
		String sql="select cf.code,cf.name from sys_code_folk cf where cf.is_use=1 order by cf.code";
		return dbUtil.getRsArrayList(sql,2);
	}
	
	public List<String[]> lStudPolitics(){
		String sql="select cp.code,cp.name from sys_code_politics cp where cp.is_use=1 order by cp.code";
		return dbUtil.getRsArrayList(sql,2);
	}
	
	public List<String[]> lStudSchoolAge(){
		String sql="select csa.code,csa.name from sys_code_school_age csa where csa.is_use=1 order by csa.code";
		return dbUtil.getRsArrayList(sql,2);
	}
	
	public List<String[]> lStudHukouCharacter(){
		String sql="select chc.code,chc.name from sys_code_hukou_character chc where chc.is_use=1 order by chc.code";
		return dbUtil.getRsArrayList(sql,2);
	}
	
	public List<String[]> lCityCode(){
		String sql="select bc.city_code,bc.city_name from base_city bc order by bc.city_code";
		return dbUtil.getRsArrayList(sql,2);
	}
	
	public List<String[]> lExamAreaCode(String cityCode){
		String sql="select bea.exam_area_code,bea.exam_area_name from base_exam_area bea where bea.icity_code=? order by bea.exam_area_code";
		return dbUtil.getRsArrayList(sql,2,cityCode);
	}
	
	public List<String[]> lStudTypeCode(){
		String sql="select co.code,co.name from sys_code_occupation co order by co.code";
		return dbUtil.getRsArrayList(sql,2);
	}
	
	public List<String[]> lStipendUnitCode(){
		String sql="select bsu.stipend_unit_code,bsu.stipend_unit_name from base_stipend_unit bsu order by bsu.stipend_unit_code";
		return dbUtil.getRsArrayList(sql,2);
	}
	
	public List<String[]> lStudOccupation(){
		String sql="select co.code,co.name from sys_code_occupation co where co.is_use=1 order by co.code";
		return dbUtil.getRsArrayList(sql,2);
	}
	
	public List<String[]> lStudHukouLocation(){
		String sql="select cr.region_code,cr.province_name,cr.region_name from sys_code_region cr order by cr.region_code";
		return dbUtil.getRsArrayList(sql,3);
	}
}