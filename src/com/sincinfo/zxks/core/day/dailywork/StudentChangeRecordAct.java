package com.sincinfo.zxks.core.day.dailywork;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import com.sincinfo.zxks.bean.BaseCity;
import com.sincinfo.zxks.bean.BaseStudentInfo;
import com.sincinfo.zxks.bean.BaseStudinfoChange;
import com.sincinfo.zxks.bean.BaseSwitch;
import com.sincinfo.zxks.bean.BaseUser;
import com.sincinfo.zxks.common.action.WebActionSupport;
import com.sincinfo.zxks.common.util.Log;
import com.sincinfo.zxks.common.util.Page;
import com.sincinfo.zxks.common.util.StringTool;
import com.sincinfo.zxks.core.day.dailywork.busi.StudentChangeRecordPdf;
import com.sincinfo.zxks.zxksdbs.StudentChangeRecordDbService;

/**
 * @ 考生基本信息变更记录 请求处理Action
 * @author guanm
 * 
 */
public class StudentChangeRecordAct extends WebActionSupport {
	private static final long serialVersionUID = -3417116574396145053L;
	private StudentChangeRecordDbService service;// 业务类
	private StudentChangeRecordPdf pdf;
	private Page page;// 分页

	private String cityCode;// 市区
	private String startDate;// 开始时间
	private String endDate;// 结束日期
	private String studExamCode;// 考生准考证号

	private String studCode;// 考生准考证号 用于显示考生详细变更信息
	private BaseStudentInfo stud;// 考生对象

	private List<BaseCity> cityList;// 城市集合
	private BaseCity city;// 市 对象

	private List<BaseStudinfoChange> dataList;// 数据list 分页后

	private String auditStatus; // 审核状态

	/* 考生修改信息来源 add by litian 2012-08-20 */
	private int fillinBy;

	public StudentChangeRecordAct() {
		service = new StudentChangeRecordDbService();
		pdf = new StudentChangeRecordPdf();
	}

	/**
	 * @see 进入默认页
	 * @return
	 */
	public String manager() {
		BaseUser user = super.getCOperUser();
		String userRole = user.getUserRole();
		if (userRole.equals("1")) {// 省用户
			cityList = service.getAllCity();
			cityCode = cityCode == null ? "" : cityCode;
		} else if (userRole.equals("2")) {// 市用户
			cityCode = user.getCityCode();
			city = service.getBaseCity(cityCode);
		}
		if (page == null) {
			page = new Page();
		}
		
		if (StringTool.isEmpty(startDate)) {
		    BaseSwitch sw = service.getSwitch("001");
		    if (sw != null) {
		        String tmp = sw.getSwitchValue();
		        if (tmp != null) {
		            String[] arr = tmp.split(":");
		            if (arr.length == 2) {
		                startDate = arr[0];
		            }
		        }
		    }
		}
		
		page.setPagecount(service.getChangeRecordCount(null, cityCode, startDate,
				null, this.auditStatus, this.fillinBy));
		page.setPath(String.format(
				"studcr_manager.do?fillinBy=%1$s&cityCode=%2$s", fillinBy,
				cityCode));
		String sql = page.getSql(service.getChangeRecordListSql(null, cityCode,
				startDate, null, this.auditStatus, this.fillinBy));
		dataList = service.formart(service.getObjList(sql,
				BaseStudinfoChange.class));

		return "manager";
	}

	/**
	 * @see 查询
	 * @return
	 */
	public String select() {
		startDate = startDate == null ? "" : startDate.trim();
		endDate = endDate == null ? "" : endDate.trim();
		studExamCode = studExamCode == null ? "" : studExamCode.trim();
		BaseUser user = super.getCOperUser();
		String userRole = user.getUserRole();
		if (userRole.equals("1")) {// 省用户
			cityList = service.getAllCity();
			cityCode = cityCode == null ? "" : cityCode.trim();
		} else if (userRole.equals("2")) {// 市用户
			cityCode = user.getCityCode();
			city = service.getBaseCity(cityCode);
		}
		if (page == null) {
			page = new Page();
		}
		page.setPagecount(service.getChangeRecordCount(studExamCode, cityCode,
				startDate, endDate, this.auditStatus, this.fillinBy));
		page
				.setPath(String
						.format(
								"studcr_select.do?fillinBy=%5$s&studExamCode=%1$s&cityCode=%2$s&startDate=%3$s&endDate=%4$s",
								studExamCode, cityCode, startDate, endDate, fillinBy));
		String sql = page.getSql(service.getChangeRecordListSql(studExamCode,
				cityCode, startDate, endDate, this.auditStatus, this.fillinBy));
		dataList = service.formart(service.getObjList(sql,
				BaseStudinfoChange.class));
		return "manager";

	}

	/**
	 * @see 查看考生 详细变更信息
	 */
	public String show() {

		if (studCode == null || studCode.equals("")) {
			return "error";
		}
		studCode = studCode.trim();
		stud = service.getStudByCode(studCode);
		BaseUser user = super.getCOperUser();
		String userRole = user.getUserRole();
		if (userRole.equals("1")) {// 省用户
			cityCode = cityCode == null ? "" : cityCode;
		} else if (userRole.equals("2")) {// 市用户
			cityCode = user.getCityCode();
			if (!stud.getCityCode().equals(cityCode)) {
				String alertInfo = "alert('没有权限，该考生不是本地市考生！');window.close();";
				super.PostJs(alertInfo);
				return "error";
			}
		}
		dataList = service.getObjList(service.getChangeRecordListSql(studCode,
				cityCode, null, null, this.auditStatus, this.fillinBy),
				BaseStudinfoChange.class);
		dataList = service.formart(dataList);

		return "show";
	}

	/**
	 * @see ajax导出考生基本信息变更 pdf
	 * @return
	 */
	public void ajaxGetPdf() {
		startDate = startDate == null ? "" : startDate.trim();
		endDate = endDate == null ? "" : endDate.trim();
		studExamCode = studExamCode == null ? "" : studExamCode.trim();
		BaseUser user = super.getCOperUser();
		String userRole = user.getUserRole();
		if (userRole.equals("1")) {// 省用户
			cityCode = cityCode == null ? "" : cityCode.trim();
		} else if (userRole.equals("2")) {// 市用户
			cityCode = user.getCityCode();
		}
		city = service.getBaseCity(cityCode);
		dataList = service.getObjList(service.getChangeRecordListSql(
				studExamCode, cityCode, startDate, endDate, this.auditStatus, this.fillinBy),
				BaseStudinfoChange.class);
		response.setCharacterEncoding("utf-8");
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			if (dataList == null || dataList.size() == 0) {
				pw.write("noData");
				return;
			}
			String[] url = pdf.makeStudentChangeRecordPdf(service
					.formart(dataList), user, studExamCode, cityCode,
					startDate, endDate, city);
			if (url == null || url.length == 0) {
				pw.write("error");
				return;
			}
			String str = String.format(
					"<a href='%1$s' target='_blank'>下载导出</a>", url[1]);
			pw.write(str);
		} catch (IOException e) {
			new Log().error(this.getClass(), "ajax导出pdf有误", e);
		} finally {
			if (pw != null) {
				pw.flush();
				pw.close();
			}
		}

	}

	public StudentChangeRecordDbService getService() {
		return service;
	}

	public void setService(StudentChangeRecordDbService service) {
		this.service = service;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getStudExamCode() {
		return studExamCode;
	}

	public void setStudExamCode(String studExamCode) {
		this.studExamCode = studExamCode;
	}

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

	public BaseCity getCity() {
		return city;
	}

	public void setCity(BaseCity city) {
		this.city = city;
	}

	public List<BaseStudinfoChange> getDataList() {
		return dataList;
	}

	public void setDataList(List<BaseStudinfoChange> dataList) {
		this.dataList = dataList;
	}

	public String getStudCode() {
		return studCode;
	}

	public void setStudCode(String studCode) {
		this.studCode = studCode;
	}

	public BaseStudentInfo getStud() {
		return stud;
	}

	public void setStud(BaseStudentInfo stud) {
		this.stud = stud;
	}

	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	public int getFillinBy() {
		return fillinBy;
	}

	public void setFillinBy(int fillinBy) {
		this.fillinBy = fillinBy;
	}

}
