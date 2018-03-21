/**
 * Copyright(c) 2012 西安云海信息技术有限责任公司 All Rights Reserved<br>
 * @Title: com.sincinfo.zxks.core.day.dailywork.StudentQueryAction.java<br>
 * @Description: 系统考生查询 <br>
 * <br>
 * @author litian<br>
 * @date May 14, 2012 9:07:35 AM 
 * @version V1.0   
 */
package com.sincinfo.zxks.core.day.dailywork;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.sincinfo.zxks.bean.BaseCity;
import com.sincinfo.zxks.bean.BaseStudentInfo;
import com.sincinfo.zxks.bean.BaseUser;
import com.sincinfo.zxks.common.action.WebActionSupport;
import com.sincinfo.zxks.common.util.Page;
import com.sincinfo.zxks.common.util.StringTool;
import com.sincinfo.zxks.common.util.dbf.ExpDBF;
import com.sincinfo.zxks.core.day.dailywork.busi.HistoryScore;
import com.sincinfo.zxks.zxksdbs.StudentMgrDbService;

/**
 * @ClassName: StudentQueryAction
 * @Description: 系统考生查询 <br>
 *               <br>
 * @author litian
 * @date May 14, 2012 9:07:35 AM<br>
 * 
 */
public class StudentQueryAction extends WebActionSupport {

	private static final long serialVersionUID = 8761390217879983828L;

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

	// 考生列表
	private List<BaseStudentInfo> studList;

	// 考生对象
	private BaseStudentInfo student;

	// 操作员
	private BaseUser optUser;

	/* 考生修改信息来源 add by litian 2012-08-20 */
	private int fillinBy;

	private List<HistoryScore> historyScores;

	/**
	 * 初始化参数
	 */
	private void initParam() {
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

		this.cityList = smds.qryCityList(this.optUser.getUserRole(),
				this.optUser.getCityCode());
	}

	/**
	 * 初始化分页对象
	 */
	private void initPage() {
		this.page.setPath(String.format(
				"%1$s/day/dailywork/studQry_doStudentQry.do", request
						.getContextPath()));
		this.page.pushParam("fillinBy", String.valueOf(this.fillinBy));
		this.page.pushParam("cityCode", this.cityCode);
		this.page.pushParam("studExamCode", this.studExamCode);
		this.page.pushParam("studName", this.studName);
		this.page.pushParam("studIdnum", this.studIdnum);
	}

	/**
	 * 初始化
	 */
	private void initial() {
		this.optUser = this.getCOperUser();
		initParam();
		initPage();
	}

	/**
	 * 进入考生查询页面
	 * 
	 * @return
	 */
	public String preStudentQry() {
		initial();
		return "studentShow";
	}

	/**
	 * 进行考生查询
	 * 
	 * @return
	 */
	public String doStudentQry() {
		initial();

		// 查询考生列表
		this.studList = smds.qryStudentList(this.cityCode, this.studExamCode,
				this.studName, this.studIdnum,null, this.page);

		return "studentShow";
	}

	/**
	 * 考生历史考试成绩
	 * 
	 * @return
	 */
	public String studExamHistory() {
		// 处理入参
		this.studExamCode = StringTool.trim(this.studExamCode);
		if ("".equals(this.studExamCode)) {
			this.PostJs("alert('系统异常！');window.close();");
			return null;
		}

		// 获取考生历史成绩列表
		this.historyScores = this.smds.qryScoreHistory( this.studExamCode);
		
		// 如果为空，则表示尚未参加本省考试
		if ( this.historyScores == null || this.historyScores.size() == 0) {
			this.PostJs("alert('该考生没有在本省考试记录！');window.close();");
			return null;
		}
		
		// 获得考生姓名
		this.studName = this.smds.qryStudName( this.studExamCode);

		return "studentHistory";
	}
	
	/**
	 * 导出DBF
	 */
	public void expDBF() {
		String msg = "error";
		if (!StringTool.isEmpty(this.cityCode)) {
			List<String[]> datas = this.smds.expStuInfo(this.cityCode,
					this.studExamCode, this.studName, this.studIdnum);
			if (datas != null && datas.size() > 0) {
				String fieldArr = "zkzh_ksxm_zjhm_zjlx_ksxb_csrq_ksmz_zzmm_kszy_whcd_kshj_hjszd_lxdh_sjhm_txdz_yzbm_dsdm_kqdm_scbkzy_bkzy_kslb_zclx_ybmsj_ksbh";
				int[] fieldLengths = new int[] { 30, 30, 30, 10, 10, 20, 10,
						30, 30, 30, 30, 20, 30, 30, 50, 30, 30, 30, 30, 30, 30,
						30, 30, 10 };
				String[] dbfPaths = new String[2];
				String[] paths = this.smds.getDbUtil().getPaths();
				String subPath = "stuInfo";
				String fileName = String.format("%1$s.dbf", cityCode);
				String pdfDir = paths[0] + subPath;
				File dir = new File(pdfDir);
				if (!dir.exists()) {
					dir.mkdirs();
				}
				dbfPaths[0] = paths[0] + subPath
						+ System.getProperty("file.separator") + fileName;
				dbfPaths[1] = "";
				ExpDBF dbf = new ExpDBF();
				if (dbf.export(datas, fieldArr, dbfPaths[0], fieldLengths)) {
					// 判断文件是否生成完毕
					File dbfFile = new File(dbfPaths[0]);
					if (dbfFile.exists() && dbfFile.length() > 0) {
						dbfPaths[1] = paths[1] + subPath + "/" + fileName;
						// 处理返回串
						if (dbfPaths != null && dbfPaths.length == 2) {
							if (!StringTool.isEmpty(dbfPaths[1])) {
								msg = String
										.format("<a href='%1$s' target='_blank'>下载</a>",
												dbfPaths[1]);
							}
						} else {
							msg = "error";
						}
					} else {
						msg = "error";
					}
				} else {
					msg = "error";
				}
			} else {
				msg = "errorno";
			}
		} else {
			msg = "no";
		}
		ajaxReturn(msg);
	}

	/**
	 * ajax返回页面
	 * 
	 * @param ret
	 *            返回字符串
	 */
	private void ajaxReturn(String ret) {
		PrintWriter pw = null;
		response.setContentType("text/html;charset=utf-8");
		try {
			pw = response.getWriter();
			pw.write(ret);
			pw.flush();
		} catch (IOException e) {
		} finally {
			if (pw != null)
				pw.close();
		}
	}

	public StudentMgrDbService getSmds() {
		return smds;
	}

	public void setSmds(StudentMgrDbService smds) {
		this.smds = smds;
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

	public BaseUser getOptUser() {
		return optUser;
	}

	public void setOptUser(BaseUser optUser) {
		this.optUser = optUser;
	}

	public int getFillinBy() {
		return fillinBy;
	}

	public void setFillinBy(int fillinBy) {
		this.fillinBy = fillinBy;
	}

	public List<HistoryScore> getHistoryScores() {
		return historyScores;
	}

	public void setHistoryScores(List<HistoryScore> historyScores) {
		this.historyScores = historyScores;
	}

}
