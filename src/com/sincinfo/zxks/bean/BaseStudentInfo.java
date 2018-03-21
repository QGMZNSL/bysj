package com.sincinfo.zxks.bean;

import org.river.nbf.validate.annotation.Validate;

public class BaseStudentInfo {
	public BaseStudentInfo(){}
	// 准考证号
	private String studExamCode;
	// Preapply_Code 预报名号
	private String preapplyCode;

	// Stud_Name 姓名
	private String studName;

	// Stud_Idnum 证件号码
	private String studIdnum;

	// Stud_IdNo_Type 证件类型
	private String studIdNoType;

	// Stud_Gender 性别
	private String studGender;

	// Stud_Birthday 出生日期
	private String studBirthday;

	// Stud_Folk 民族
	private String studFolk;

	// Stud_Politics 政治面貌
	private String studPolitics;
	// Stud_Politics 政治面貌名称
	private String studPoliticsName;

	// Stud_Occupation 职业
	private String studOccupation;
	// Stud_Occupation 职业名称
	private String studOccupationName;

	// Stud_SCHOOL_AGE 文化程度
	private String studSchoolAge;
	// Stud_SCHOOL_AGE 文化程度名称
	private String studSchoolAgeName;

	// Stud_ Hukou_Character 户籍
	private String studHukouCharacter;
	// Stud_ Hukou_Character 户籍名称
	private String studHukouCharacterName;

	// Stud_Hukou_Location 户籍所在地
	private String studHukouLocation;
	// Stud_Hukou_Location 户籍所在地名称
	private String studHukouLocationName;

	// Stud_Telephone 联系电话
	private String studTelephone;

	// Stud_Mobile_Phone 手机号码
	private String studMobilePhone;

	// Stud_Postal_Address 通讯地址
	private String studPostalAddress;

	// Stud_Postal_Code 邮政编码
	private String studPostalCode;

	// City_Code 市区代码
	private String cityCode;

	// Exam_Area_Code 考区代码
	private String examAreaCode;

	// First_Pro_Code 首次报考专业
	private String firstProCode;

	// Pro_Code 报考专业
	private String ProCode;

	// Stud_Type_Code 考生类别
	private String studTypeCode;

	// Level_Code 层次类型

	private String levelCode;
	// Stipend_Unit_Code 助学单位代码
	private String stipendUnitCode;
	// 助学单位名称
	private String stipendUnitName;

	// Stud_Password 登陆密码
	private String studPassword;

	// Initial_Password 初始密码
	private String initalPassword;

	// Trans_Status 转出状态
	private String transStatus;
	// Pre_Site_Date 预报名时间
	private String preSiteDate;

	// Stud_Photo_File_1 准考证照片文件
	private String studPhotoFile1;

	// Stud_Photo_File_2 毕业证照片文件
	private String studPhotoFile2;
	// Stud_Photo_File_3 二代身份证照片文件
	private String studPhotoFile3;
	// Camera_Place_Code 照片采集摄像点
	private String cameraPlaceCode;
	// Photo_Gather_Time 照片采集时间
	private String photoGatherTime;
	// Stud_Information_Confirm_Sign 考生信息确认状态
	private String studInformationConfirmSign;
	// Stud_Lock 考生锁定
	private String studLock;
	// Stud_Lock_Reason 考生锁定原因
	private String studLockReason;
	// Stud_Status 考生状态（完全转出、已被合并）
	private String studStatus;
	// Last_Login_Time 最后登陆时间
	private String lastLoginTime;
	// Login_Num 登陆次数
	private String loginNum;
	// Login_Across 跨包登陆md5串
	private String loginAcross;
	// Stud_Stop_Date 停考时间
	private String studStopDate;
	// Is_Graduate_Will_Last是否延期毕业
	private String isGraduateWillLast;
	// Is_Disabled 取消考籍（准考证及成绩作废）
	private String isDisabled;
	// Define_Code 自定义代码（预留）
	private String defineCode;
	// Stud_Special_Sign 特殊考生状态
	private String studSpecialSign;
	// Stud_Special_Sign_User 特殊考生设定操作员
	private String studSpecialSignUser;
	// Stud_Special_Sign_Time 特殊考生设定时间
	private String studSpecialSignTime;

	/* 扩展字段 */
	// 身份证件类型名称
	private String idTypeName;
	// 性别名称
	private String genderName;
	// 地市名称
	private String cityName;
	// 考区名称
	private String examAreaName;
	// 电子邮件
	private String studEmail;
	// 首次报考专业名称
	private String firstProName;
	// 报考层次名称
	private String levelName;
	// 民族名称
	private String studFolkName;
	// 证件类型名称
	private String studIdnoTypeName;
	// 专业名称
	private String proName; 
	
	//是否手动输入     默认0： 0未确认1不是 2是  by zhangjb 2012-8-13
	private String manualInput;
	private String examinationCode;//EXAMINATION_CODE考试编号
	private String diplomaNum;
	
	/**
	 * 返回sql，主要为了获取名称的子查询
	 * 
	 * @return
	 */
	public String createSqlWithoutWhere() {
		StringBuilder sql = new StringBuilder();
		sql.append("select i.*,");
		sql.append(" (select cg.name from sys_code_gender cg where cg.code = i.stud_gender) as genderName,");
		sql.append(" (select it.name from sys_code_idno_type it where it.code = i.stud_idno_type) as studIdnoTypeName,");
		sql.append(" (select bp.pro_name from base_pro bp where bp.pro_code=i.first_pro_code) as firstProName,");
		sql.append(" (select bp.pro_name from base_pro bp where bp.pro_code=i.pro_code) as proName,");
		sql.append(" (select l.level_name from base_level l where l.level_code=i.level_code) as levelName,");
		sql.append(" (select cf.name from sys_code_folk cf where cf.code=i.stud_folk) as studFolkName,");
		sql.append(" (select cp.name from sys_code_politics cp where cp.code=i.stud_politics) as studPoliticsName,");
		sql.append(" (select csa.name from sys_code_school_age csa where csa.code=i.stud_school_age) as studSchoolAgeName,");
		sql.append(" (select chc.name from sys_code_hukou_character chc where chc.code=i.stud_hukou_character) as studHukouCharacterName,");
		sql.append(" (select cr.region_name from sys_code_region cr where cr.region_code=i.stud_hukou_location) as studHukouLocationName,");
		sql.append(" (select co.name from sys_code_occupation co where co.code=i.stud_occupation) as studOccupationName,");
		sql.append(" (select bc.city_name from base_city bc where bc.city_code=i.city_code) as cityName,");
		sql.append(" (select DIPLOMA_NUM from BASE_GRADUATE_STUDENT_INFO where STUD_EXAM_CODE=i.STUD_EXAM_CODE and PRO_CODE=i.PRO_CODE) as diplomaNum,");
		sql.append(" (select bea.exam_area_name from base_exam_area bea where bea.exam_area_code=i.exam_area_code) as examAreaName,");
		sql.append(" (select bsu.stipend_unit_name from base_stipend_unit bsu where bsu.stipend_unit_code=i.stipend_unit_code) as stipendUnitName");
		sql.append(" from base_student_info i ");
		return sql.toString();
	}

	@Validate(exp = "null")
	public String getStudExamCode() {
		return studExamCode;
	}

	public void setStudExamCode(String studExamCode) {
		this.studExamCode = studExamCode;
	}

	public String getPreapplyCode() {
		return preapplyCode;
	}

	public void setPreapplyCode(String preapplyCode) {
		this.preapplyCode = preapplyCode;
	}

	public String getStudName() {
		return studName;
	}

	public void setStudName(String studName) {
		this.studName = studName;
	}

	@Validate(exp = "null")
	public String getStudIdnum() {
		return studIdnum;
	}

	public void setStudIdnum(String studIdnum) {
		this.studIdnum = studIdnum;
	}

	public String getStudIdNoType() {
		return studIdNoType;
	}

	public void setStudIdNoType(String studIdNoType) {
		this.studIdNoType = studIdNoType;
	}

	public String getStudGender() {
		return studGender;
	}

	public void setStudGender(String studGender) {
		this.studGender = studGender;
	}

	public String getStudBirthday() {
		return studBirthday;
	}

	public void setStudBirthday(String studBirthday) {
		this.studBirthday = studBirthday;
	}

	public String getStudFolk() {
		return studFolk;
	}

	public void setStudFolk(String studFolk) {
		this.studFolk = studFolk;
	}

	public String getStudPolitics() {
		return studPolitics;
	}

	public void setStudPolitics(String studPolitics) {
		this.studPolitics = studPolitics;
	}

	public String getStudOccupation() {
		return studOccupation;
	}

	public void setStudOccupation(String studOccupation) {
		this.studOccupation = studOccupation;
	}

	public String getStudHukouCharacter() {
		return studHukouCharacter;
	}

	public void setStudHukouCharacter(String studHukouCharacter) {
		this.studHukouCharacter = studHukouCharacter;
	}

	public String getStudHukouLocation() {
		return studHukouLocation;
	}

	public void setStudHukouLocation(String studHukouLocation) {
		this.studHukouLocation = studHukouLocation;
	}

	public String getStudPostalAddress() {
		return studPostalAddress;
	}

	public void setStudPostalAddress(String studPostalAddress) {
		this.studPostalAddress = studPostalAddress;
	}

	public String getStudPostalCode() {
		return studPostalCode;
	}

	public void setStudPostalCode(String studPostalCode) {
		this.studPostalCode = studPostalCode;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getExamAreaCode() {
		return examAreaCode;
	}

	public void setExamAreaCode(String examAreaCode) {
		this.examAreaCode = examAreaCode;
	}

	public String getFirstProCode() {
		return firstProCode;
	}

	public void setFirstProCode(String firstProCode) {
		this.firstProCode = firstProCode;
	}

	public String getProCode() {
		return ProCode;
	}

	public void setProCode(String proCode) {
		ProCode = proCode;
	}

	public String getStudTypeCode() {
		return studTypeCode;
	}

	public void setStudTypeCode(String studTypeCode) {
		this.studTypeCode = studTypeCode;
	}

	public String getLevelCode() {
		return levelCode;
	}

	public void setLevelCode(String levelCode) {
		this.levelCode = levelCode;
	}

	public String getStipendUnitCode() {
		return stipendUnitCode;
	}

	public void setStipendUnitCode(String stipendUnitCode) {
		this.stipendUnitCode = stipendUnitCode;
	}

	@Validate(exp = "null")
	public String getStudPassword() {
		return studPassword;
	}

	public void setStudPassword(String studPassword) {
		this.studPassword = studPassword;
	}

	public String getInitalPassword() {
		return initalPassword;
	}

	public void setInitalPassword(String initalPassword) {
		this.initalPassword = initalPassword;
	}

	public String getTransStatus() {
		return transStatus;
	}

	public void setTransStatus(String transStatus) {
		this.transStatus = transStatus;
	}

	public String getPreSiteDate() {
		return preSiteDate;
	}

	public void setPreSiteDate(String preSiteDate) {
		this.preSiteDate = preSiteDate;
	}

	

	public String getStudPhotoFile1() {
		return studPhotoFile1;
	}

	public void setStudPhotoFile1(String studPhotoFile1) {
		this.studPhotoFile1 = studPhotoFile1;
	}

	public String getStudPhotoFile2() {
		return studPhotoFile2;
	}

	public void setStudPhotoFile2(String studPhotoFile2) {
		this.studPhotoFile2 = studPhotoFile2;
	}

	public String getStudPhotoFile3() {
		return studPhotoFile3;
	}

	public void setStudPhotoFile3(String studPhotoFile3) {
		this.studPhotoFile3 = studPhotoFile3;
	}

	public String getCameraPlaceCode() {
		return cameraPlaceCode;
	}

	public void setCameraPlaceCode(String cameraPlaceCode) {
		this.cameraPlaceCode = cameraPlaceCode;
	}

	public String getPhotoGatherTime() {
		return photoGatherTime;
	}

	public void setPhotoGatherTime(String photoGatherTime) {
		this.photoGatherTime = photoGatherTime;
	}

	public String getStudInformationConfirmSign() {
		return studInformationConfirmSign;
	}

	public void setStudInformationConfirmSign(String studInformationConfirmSign) {
		this.studInformationConfirmSign = studInformationConfirmSign;
	}

	public String getStudLock() {
		return studLock;
	}

	public void setStudLock(String studLock) {
		this.studLock = studLock;
	}

	public String getStudLockReason() {
		return studLockReason;
	}

	public void setStudLockReason(String studLockReason) {
		this.studLockReason = studLockReason;
	}

	public String getStudStatus() {
		return studStatus;
	}

	public void setStudStatus(String studStatus) {
		this.studStatus = studStatus;
	}

	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getLoginNum() {
		return loginNum;
	}

	public void setLoginNum(String loginNum) {
		this.loginNum = loginNum;
	}

	public String getLoginAcross() {
		return loginAcross;
	}

	public void setLoginAcross(String loginAcross) {
		this.loginAcross = loginAcross;
	}

	public String getStudStopDate() {
		return studStopDate;
	}

	public void setStudStopDate(String studStopDate) {
		this.studStopDate = studStopDate;
	}

	public String getIsGraduateWillLast() {
		return isGraduateWillLast;
	}

	public void setIsGraduateWillLast(String isGraduateWillLast) {
		this.isGraduateWillLast = isGraduateWillLast;
	}

	public String getIsDisabled() {
		return isDisabled;
	}

	public void setIsDisabled(String isDisabled) {
		this.isDisabled = isDisabled;
	}

	public String getDefineCode() {
		return defineCode;
	}

	public void setDefineCode(String defineCode) {
		this.defineCode = defineCode;
	}

	public String getStudSpecialSign() {
		return studSpecialSign;
	}

	public void setStudSpecialSign(String studSpecialSign) {
		this.studSpecialSign = studSpecialSign;
	}

	public String getStudSpecialSignUser() {
		return studSpecialSignUser;
	}

	public void setStudSpecialSignUser(String studSpecialSignUser) {
		this.studSpecialSignUser = studSpecialSignUser;
	}

	public String getStudSpecialSignTime() {
		return studSpecialSignTime;
	}

	public void setStudSpecialSignTime(String studSpecialSignTime) {
		this.studSpecialSignTime = studSpecialSignTime;
	}

	public String getIdTypeName() {
		return idTypeName;
	}

	public void setIdTypeName(String idTypeName) {
		this.idTypeName = idTypeName;
	}

	public String getGenderName() {
		return genderName;
	}

	public void setGenderName(String genderName) {
		this.genderName = genderName;
	}

	public String getStudEmail() {
		return studEmail;
	}

	public void setStudEmail(String studEmail) {
		this.studEmail = studEmail;
	}

	public String getStudSchoolAge() {
		return studSchoolAge;
	}

	public void setStudSchoolAge(String studSchoolAge) {
		this.studSchoolAge = studSchoolAge;
	}

	public String getFirstProName() {
		return firstProName;
	}

	public void setFirstProName(String firstProName) {
		this.firstProName = firstProName;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public String getStudFolkName() {
		return studFolkName;
	}

	public void setStudFolkName(String studFolkName) {
		this.studFolkName = studFolkName;
	}

	public String getStudPoliticsName() {
		return studPoliticsName;
	}

	public void setStudPoliticsName(String studPoliticsName) {
		this.studPoliticsName = studPoliticsName;
	}

	public String getStudOccupationName() {
		return studOccupationName;
	}

	public void setStudOccupationName(String studOccupationName) {
		this.studOccupationName = studOccupationName;
	}

	public String getStudSchoolAgeName() {
		return studSchoolAgeName;
	}

	public void setStudSchoolAgeName(String studSchoolAgeName) {
		this.studSchoolAgeName = studSchoolAgeName;
	}

	public String getStudHukouCharacterName() {
		return studHukouCharacterName;
	}

	public void setStudHukouCharacterName(String studHukouCharacterName) {
		this.studHukouCharacterName = studHukouCharacterName;
	}

	public String getStudHukouLocationName() {
		return studHukouLocationName;
	}

	public void setStudHukouLocationName(String studHukouLocationName) {
		this.studHukouLocationName = studHukouLocationName;
	}

	public String getStudTelephone() {
		return studTelephone;
	}

	public void setStudTelephone(String studTelephone) {
		this.studTelephone = studTelephone;
	}

	public String getStudMobilePhone() {
		return studMobilePhone;
	}

	public void setStudMobilePhone(String studMobilePhone) {
		this.studMobilePhone = studMobilePhone;
	}

	public String getStipendUnitName() {
		return stipendUnitName;
	}

	public void setStipendUnitName(String stipendUnitName) {
		this.stipendUnitName = stipendUnitName;
	}

	public String getExamAreaName() {
		return examAreaName;
	}

	public void setExamAreaName(String examAreaName) {
		this.examAreaName = examAreaName;
	}

	public String getStudIdnoTypeName() {
		return studIdnoTypeName;
	}

	public void setStudIdnoTypeName(String studIdnoTypeName) {
		this.studIdnoTypeName = studIdnoTypeName;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getManualInput() {
		return manualInput;
	}

	public void setManualInput(String manualInput) {
		this.manualInput = manualInput;
	}

	public String getExaminationCode() {
		return examinationCode;
	}

	public void setExaminationCode(String examinationCode) {
		this.examinationCode = examinationCode;
	}

	public String getDiplomaNum() {
		return diplomaNum;
	}

	public void setDiplomaNum(String diplomaNum) {
		this.diplomaNum = diplomaNum;
	}
}