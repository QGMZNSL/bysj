package com.sincinfo.zxks.core.day.dailywork;

import java.util.List;

import com.sincinfo.zxks.bean.BaseCity;
import com.sincinfo.zxks.bean.BaseExamination;
import com.sincinfo.zxks.bean.BaseStudentPassSyllabus;
import com.sincinfo.zxks.bean.BaseSyllabus;
import com.sincinfo.zxks.bean.BaseUser;
import com.sincinfo.zxks.common.action.WebActionSupport;
import com.sincinfo.zxks.common.util.Page;
import com.sincinfo.zxks.zxksdbs.StudScoreDisableDbService;

/**
 * @see 违纪考生处理  请求处理类 ssd_*
 * @author guanm
 *
 */
public class StudScoreDisableAction extends WebActionSupport{
	private StudScoreDisableDbService service ;//业务类
	private Page page;
	
	private List<BaseExamination> examList;//考试集合
	private List<BaseCity> cityList;//地市集合
	private BaseCity city;
	
	private String ssyllCode;//课程代码
	private String ssyllName;//课程名称
	private List<BaseSyllabus> syllabusList;//课程信息集合（分页后）
	
	private String examCode;//考试代码
	private String cityCode;//地市代码
	private String syllCodeName;
	private String syllCode;//课程代码
	private String studExamCode;//准考证号
	private String studName;//姓名
	private String studIdnum;//证件号码
	private String status;//状态   0未作废（成绩有效）  1已作废（成绩无效）
	private String disabledReason;//撤销原因；
	private String[] psIds;//id 集合
	
	private List<BaseStudentPassSyllabus> dataList;//违纪数据集合
	
	public StudScoreDisableAction(){
		service=new StudScoreDisableDbService();
	}
	
	/**
	 * @see 进入默认页
	 * @return
	 */
	public String manager(){
		examList=service.getALlExamination();
		BaseUser user=super.getCOperUser();
		String role = user.getUserRole();
		if (role.equals("1")) {// 省用户
			cityList = service.getAllCity();
		} else if (role.equals("2")) {// 市用户
			cityCode=user.getCityCode();
			city = service.getBaseCity(cityCode);
		}
		return "manager";
	}
	/**
	 * @see 选择课程
	 */
	public String seleSyll(){
		ssyllCode=ssyllCode==null?"":ssyllCode.trim();
		ssyllName=ssyllName==null?"":ssyllName.trim();
		
		if(page==null){
			page=new Page();
		}
		page.setPath(String.format("ssd_seleSyll.do?ssyllCode=%1$s&ssyllName=%2$s", ssyllCode,ssyllName));
		syllabusList=service.qrySctSyllabuses(ssyllCode, ssyllName, page);
		return "seleSyll";
	}
	/**
	 * @see 查询数据
	 * @return
	 */
	public String select(){
		examCode=examCode==null?"":examCode.trim();
		studExamCode=studExamCode==null?"":studExamCode.trim();
		studName=studName==null?"":studName.trim();
		studIdnum=studIdnum==null?"":studIdnum.trim();
		status=status==null?"0":status.trim();
		cityCode=cityCode==null?"":cityCode.trim();
		syllCodeName=syllCodeName==null?"":syllCodeName.trim();
		if(!syllCodeName.equals("")){
			syllCode=syllCodeName.substring(0,5);
		}
		syllCode=syllCode==null?"":syllCode.trim();
		
		if(studExamCode.equals("")){
			super.GoBack("请填写准考证号！");
			return null;
		}
		
		if(page==null){
			page=new Page();
		}
		page.setPagecount(service.getDataCount(examCode, cityCode, syllCode, studExamCode, studName, studIdnum, status));
		page.setPath(String.format("ssd_select.do?examCode=%1$s&cityCode=%2$s&syllCode=%3$s&studExamCode=%4$s&studName=%5$s&studIdnum=%6$s&status=%7$s", examCode, cityCode, syllCode, studExamCode, studName, studIdnum, status));
		String sql=page.getSql(service.getDataSql(examCode, cityCode, syllCode, studExamCode, studName, studIdnum, status));
		dataList=service.getObjList(sql, BaseStudentPassSyllabus.class);
		return this.manager();
	}
	
	/**
	 * @see 撤销成绩
	 * @return
	 */
	public String disScore(){
		if(psIds==null||psIds.length==0){
			super.GoBack("请至少选择一条记录！");
			return null;
		}
		disabledReason=disabledReason==null?"":disabledReason.trim();
		if(disabledReason.equals("")){
			super.GoBack("请填写撤销原因！");
			return null;
		}
		boolean isok=service.disScore(psIds, disabledReason, super.getCOperUser());
		if(isok==false){
//			super.Alert("撤销失败！");
			request.setAttribute("info", "作废成绩失败！");
			this.select();
		}else{
//			super.Alert("撤销成功！");
			request.setAttribute("info", "作废成绩成功！");
			this.select();
		}
		return "manager";
	}
	/**
	 * @see 恢复成绩
	 * @return
	 */
	public String reScore(){
		if(psIds==null||psIds.length==0){
			super.GoBack("请至少选择一条记录！");
			return null;
		}
		boolean isok=service.reScore(psIds);
		if(isok==false){
//			super.Alert("撤销失败！");
			request.setAttribute("info", "恢复成绩失败！");
			this.select();
		}else{
//			super.Alert("撤销成功！");
			request.setAttribute("info", "恢复成绩成功！");
			this.select();
		}
		return "manager";
	}
	
	public StudScoreDisableDbService getService() {
		return service;
	}

	public void setService(StudScoreDisableDbService service) {
		this.service = service;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public List<BaseExamination> getExamList() {
		return examList;
	}

	public void setExamList(List<BaseExamination> examList) {
		this.examList = examList;
	}

	public List<BaseCity> getCityList() {
		return cityList;
	}

	public void setCityList(List<BaseCity> cityList) {
		this.cityList = cityList;
	}

	public BaseCity getCity() {
		return city;
	}

	public void setCity(BaseCity city) {
		this.city = city;
	}

	public String getSsyllCode() {
		return ssyllCode;
	}

	public void setSsyllCode(String ssyllCode) {
		this.ssyllCode = ssyllCode;
	}

	public String getSsyllName() {
		return ssyllName;
	}

	public void setSsyllName(String ssyllName) {
		this.ssyllName = ssyllName;
	}

	public List<BaseSyllabus> getSyllabusList() {
		return syllabusList;
	}

	public void setSyllabusList(List<BaseSyllabus> syllabusList) {
		this.syllabusList = syllabusList;
	}

	public String getExamCode() {
		return examCode;
	}

	public void setExamCode(String examCode) {
		this.examCode = examCode;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getSyllCodeName() {
		return syllCodeName;
	}

	public void setSyllCodeName(String syllCodeName) {
		this.syllCodeName = syllCodeName;
	}

	public String getSyllCode() {
		return syllCode;
	}

	public void setSyllCode(String syllCode) {
		this.syllCode = syllCode;
	}

	public String getStudExamCode() {
		return studExamCode;
	}

	public void setStudExamCode(String studExamCode) {
		this.studExamCode = studExamCode;
	}

	public String getStudName() {
		return studName;
	}

	public void setStudName(String studName) {
		this.studName = studName;
	}

	public String getStudIdnum() {
		return studIdnum;
	}

	public void setStudIdnum(String studIdnum) {
		this.studIdnum = studIdnum;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<BaseStudentPassSyllabus> getDataList() {
		return dataList;
	}

	public void setDataList(List<BaseStudentPassSyllabus> dataList) {
		this.dataList = dataList;
	}

	public String getDisabledReason() {
		return disabledReason;
	}

	public void setDisabledReason(String disabledReason) {
		this.disabledReason = disabledReason;
	}

	public String[] getPsIds() {
		return psIds;
	}

	public void setPsIds(String[] psIds) {
		this.psIds = psIds;
	}
	
}
