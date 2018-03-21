package com.sincinfo.zxks.core.day.dailywork;

import java.util.List;

import com.sincinfo.zxks.bean.BaseStudentInfo;
import com.sincinfo.zxks.bean.BaseStudinfoChange;
import com.sincinfo.zxks.bean.PassSyllabusQueryBean;
import com.sincinfo.zxks.common.action.WebActionSupport;
import com.sincinfo.zxks.zxksdbs.StudentChangeRecordDbService;
import com.sincinfo.zxks.zxksdbs.StudentInfoShowDbService;

/**
 * @see 考生信息查询显示 Action
 * @author guanm
 * 
 */
public class StudentInfoShowAct extends WebActionSupport {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -7271153806795884044L;

	private StudentChangeRecordDbService crService;// 基本信息变更 业务类
	private StudentInfoShowDbService shService;// 考生基本信息 业务类

	private String studExamCode;// 考生准考证

	private BaseStudentInfo stud;// 考生对象
	private List<PassSyllabusQueryBean> stuSyllabusDataList;// 考生下的合格课程信息
	private List<BaseStudinfoChange> stuChangeInfodataList;// 考生的基本信息变更记录

	private List<String[]> stuSiteUpList;// 考生报考信息
	private List<String[]> stuAvoidList;// 免考信息
	private List<String[]> stuSubstituteList;// 替换信息
	private List<String[]> transInfoList;// 转考信息
	private List<String[]> certList;// 持有证书
	private List<String[]> graduateList;// 毕业信息
	private List<String[]> deciplineList;// 违纪信息

	public StudentInfoShowAct() {
		crService = new StudentChangeRecordDbService();
		shService = new StudentInfoShowDbService();
	}

	public String show() {
		if (studExamCode == null || studExamCode.equals("")) {
			return "error";
		}
		studExamCode = studExamCode.trim();
		stud = crService.getStudByCode(studExamCode);
		if (stud == null) {
			super.GoBack("没有该考生！");
			return null;
		}
		// 判断该操作员 是否有权限 查询该考生
		// BaseUser user=super.getCOperUser();
		// String userRole = user.getUserRole();
		// if (userRole.equals("2")) {// 市用户
		// String cityCode = user.getCityCode();
		// if(!stud.getCityCode().equals(cityCode)){
		// super.GoBack("没有权限，该考生不是本地市考生！");
		// return null;
		// }
		// }
		// 格式化该考生的照片地址
		stud.setStudPhotoFile1(shService.getStudPhoto(stud.getStudPhotoFile1()));

		stuSyllabusDataList = shService.getDataList(stud);

		stuChangeInfodataList = shService.getObjList(crService
				.getChangeRecordListSql(studExamCode, null, null, null, null,
						-1), BaseStudinfoChange.class);
		stuChangeInfodataList = crService.formart(stuChangeInfodataList);

		// add by litian 2012-05-14 for 丰富详细列表信息
		// 报考记录
		this.stuSiteUpList = shService.qryStudSiteUpInfo(studExamCode);
		// 免考记录
		this.stuAvoidList = shService.qryStudAvoidList(studExamCode);
		// 替换记录
		this.stuSubstituteList = shService.qryStudSubstituteList(studExamCode);
		// 转考记录
		this.transInfoList = shService.qryStudTransInfoList(studExamCode);
		// 持有证书
		this.certList = shService.qryStudCertification(studExamCode);
		// 毕业记录
		this.graduateList = shService.qryStudGraduates(studExamCode);
		// 违纪信息
		this.deciplineList = shService.qryStudDeciplines(studExamCode);

		return "ok";
	}

	public StudentChangeRecordDbService getCrService() {
		return crService;
	}

	public void setCrService(StudentChangeRecordDbService crService) {
		this.crService = crService;
	}

	public StudentInfoShowDbService getShService() {
		return shService;
	}

	public void setShService(StudentInfoShowDbService shService) {
		this.shService = shService;
	}

	public String getStudExamCode() {
		return studExamCode;
	}

	public void setStudExamCode(String studExamCode) {
		this.studExamCode = studExamCode;
	}

	public BaseStudentInfo getStud() {
		return stud;
	}

	public void setStud(BaseStudentInfo stud) {
		this.stud = stud;
	}

	public List<PassSyllabusQueryBean> getStuSyllabusDataList() {
		return stuSyllabusDataList;
	}

	public void setStuSyllabusDataList(
			List<PassSyllabusQueryBean> stuSyllabusDataList) {
		this.stuSyllabusDataList = stuSyllabusDataList;
	}

	public List<BaseStudinfoChange> getStuChangeInfodataList() {
		return stuChangeInfodataList;
	}

	public void setStuChangeInfodataList(
			List<BaseStudinfoChange> stuChangeInfodataList) {
		this.stuChangeInfodataList = stuChangeInfodataList;
	}

	public List<String[]> getStuSiteUpList() {
		return stuSiteUpList;
	}

	public void setStuSiteUpList(List<String[]> stuSiteUpList) {
		this.stuSiteUpList = stuSiteUpList;
	}

	public List<String[]> getStuAvoidList() {
		return stuAvoidList;
	}

	public void setStuAvoidList(List<String[]> stuAvoidList) {
		this.stuAvoidList = stuAvoidList;
	}

	public List<String[]> getStuSubstituteList() {
		return stuSubstituteList;
	}

	public void setStuSubstituteList(List<String[]> stuSubstituteList) {
		this.stuSubstituteList = stuSubstituteList;
	}

	public List<String[]> getTransInfoList() {
		return transInfoList;
	}

	public void setTransInfoList(List<String[]> transInfoList) {
		this.transInfoList = transInfoList;
	}

	public List<String[]> getCertList() {
		return certList;
	}

	public void setCertList(List<String[]> certList) {
		this.certList = certList;
	}

	public List<String[]> getGraduateList() {
		return graduateList;
	}

	public void setGraduateList(List<String[]> graduateList) {
		this.graduateList = graduateList;
	}

	public List<String[]> getDeciplineList() {
		return deciplineList;
	}

	public void setDeciplineList(List<String[]> deciplineList) {
		this.deciplineList = deciplineList;
	}

}
