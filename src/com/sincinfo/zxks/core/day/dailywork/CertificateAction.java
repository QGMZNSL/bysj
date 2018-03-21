package com.sincinfo.zxks.core.day.dailywork;

import java.util.List;

import com.sincinfo.zxks.bean.BaseCertificateQuery;
import com.sincinfo.zxks.bean.BaseGraduateStudentInfo;
import com.sincinfo.zxks.bean.SysCodeCertificate;
import com.sincinfo.zxks.common.action.WebActionSupport;
import com.sincinfo.zxks.common.util.StringTool;
import com.sincinfo.zxks.zxksdbs.CertificateManagerDbService;

/**
 * @see 证书查询 请求处理类 c_*
 * @author guanm
 * 
 */
public class CertificateAction extends WebActionSupport {
	private CertificateManagerDbService service;// 业务类

	private String diplomaNum;
	private String studIdnum;

	private BaseGraduateStudentInfo graduateBean;// 毕业信息对象

	private List<SysCodeCertificate> codeList;// 证书类型list
	private List<BaseCertificateQuery> certList;// 证书查询list

	// add by litian 2012-06-07 begin
	private String studOldCode;
	private List<String[]> oldSyllabusList;
	private String[] diplomaInfo;

	// add by litian 2012-06-07 end

	public CertificateAction() {
		service = new CertificateManagerDbService();
	}

	/**
	 * @see 进入默认页
	 * @return
	 */
	public String manager() {
		codeList = service.getCodeList();
		certList = service.getCertifiQuery();
		return "manager";
	}

	/**
	 * @see 自考毕业证书查询
	 * @return
	 */
	public String query() {
		diplomaNum = diplomaNum == null ? "" : diplomaNum.trim();
		studIdnum = studIdnum == null ? "" : studIdnum.trim();

		if (diplomaNum.equals("") && studIdnum.equals("")) {
			return null;
		}
		graduateBean = service.getGraduateStudentInfo(diplomaNum, studIdnum);
		if (graduateBean == null) {
			super.GoBack("输入信息有误，没有该证书！");
			return null;
		}
		return "query";
	}

	// add by litian for 证书查询增加学历文凭毕业证查询，2003年前成绩查询 at 2012-06-07 begin
	/**
	 * 进入学历文凭查询页面
	 * 
	 * @return
	 */
	public String goXLWP() {
		manager();

		return "queryXLWP";
	}

	/**
	 * 进行学历文凭查询
	 * 
	 * @return
	 */
	public String qryXLWP() {
		// 处理参数
		this.diplomaNum = StringTool.trim(this.diplomaNum);
		this.studIdnum = StringTool.trim(this.studIdnum);

		// 进行查询
		this.diplomaInfo = this.service.qryDiplomaInfo(this.diplomaNum,
				this.studIdnum);

		// 判断查询结果，无内容则返回
		if ( this.diplomaInfo == null || this.diplomaInfo.length != 7) {
			this.GoBack("没有查询到符合条件的成绩！");
			return null;
		}

		return "queryXLWPShow";
	}

	/**
	 * 进入2003年前成绩查询页面
	 * 
	 * @return
	 */
	public String go2003CJCX() {
		manager();

		return "query2003CJCX";
	}

	/**
	 * 进行2003年前成绩查询
	 * 
	 * @return
	 */
	public String qry2003CJCX() {
		// 处理参数
		this.studOldCode = StringTool.trim(this.studOldCode);

		// 进行查询
		this.oldSyllabusList = this.service
				.qryOldSyllabusScore(this.studOldCode);

		// 判断查询结果，无内容则返回
		if (this.oldSyllabusList == null || this.oldSyllabusList.size() == 0) {
			this.GoBack("没有查询到符合条件的成绩！");
			return null;
		}

		return "query2003CJCXShow";
	}

	// add by litian end

	public List<SysCodeCertificate> getCodeList() {
		return codeList;
	}

	public void setCodeList(List<SysCodeCertificate> codeList) {
		this.codeList = codeList;
	}

	public List<BaseCertificateQuery> getCertList() {
		return certList;
	}

	public void setCertList(List<BaseCertificateQuery> certList) {
		this.certList = certList;
	}

	public CertificateManagerDbService getService() {
		return service;
	}

	public void setService(CertificateManagerDbService service) {
		this.service = service;
	}

	public String getDiplomaNum() {
		return diplomaNum;
	}

	public void setDiplomaNum(String diplomaNum) {
		this.diplomaNum = diplomaNum;
	}

	public String getStudIdnum() {
		return studIdnum;
	}

	public void setStudIdnum(String studIdnum) {
		this.studIdnum = studIdnum;
	}

	public BaseGraduateStudentInfo getGraduateBean() {
		return graduateBean;
	}

	public void setGraduateBean(BaseGraduateStudentInfo graduateBean) {
		this.graduateBean = graduateBean;
	}

	public List<String[]> getOldSyllabusList() {
		return oldSyllabusList;
	}

	public void setOldSyllabusList(List<String[]> oldSyllabusList) {
		this.oldSyllabusList = oldSyllabusList;
	}

	public String[] getDiplomaInfo() {
		return diplomaInfo;
	}

	public void setDiplomaInfo(String[] diplomaInfo) {
		this.diplomaInfo = diplomaInfo;
	}

	public String getStudOldCode() {
		return studOldCode;
	}

	public void setStudOldCode(String studOldCode) {
		this.studOldCode = studOldCode;
	}

}
