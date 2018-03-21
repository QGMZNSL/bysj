/**
 * Copyright(c) 2012 西安云海信息技术有限责任公司 All Rights Reserved<br>
 * @Title: com.sincinfo.zxks.core.sys.LoginAction.java<br>
 * @Description: 报考层次管理<br>
 * <br>
 * @author yuansh<br>
 * @date 2012-01-26 15:26
 * @version V1.0   
 */
package com.sincinfo.zxks.core.plan;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.sincinfo.zxks.bean.BasePro;
import com.sincinfo.zxks.bean.GraduateCondition;
import com.sincinfo.zxks.bean.PlanInfos;
import com.sincinfo.zxks.common.action.WebActionSupport;
import com.sincinfo.zxks.common.util.Log;
import com.sincinfo.zxks.common.util.Page;
import com.sincinfo.zxks.zxksdbs.BaseProDbService;
import com.sincinfo.zxks.zxksdbs.GraduateConditionDbService;
import com.sincinfo.zxks.zxksdbs.PlanInfosDbService;
import com.sincinfo.zxks.zxksdbs.PlanLevelDbService;

/**
 * @ClassName: LevelAction
 * @Description: 专业信息汇编 
 * @author yuansh
 * @date   2012-01-26 15:45
 * 
 */
public class PlanInfosAction extends WebActionSupport {
	/**
	 * @Fields serialVersionUID :
	 */
	private static final long serialVersionUID = -6217769699627471779L;

	private Page page = new Page();
	
	//专业信息汇编
	private PlanInfos planInfos;
	
	//专业信息
	private BasePro basePro;

	//用来传递查询结果
	private List<PlanInfos> planInfosList;
	
	//扩展：毕业条件
	private GraduateCondition graduateCondition;

	
	/**
	 * 初始化
	 */
	private void init() {
		if (planInfos == null) {
			planInfos = new PlanInfos();
		}
		if (basePro == null) {
			basePro = new BasePro();
		}		
		if (graduateCondition == null) {
			graduateCondition = new GraduateCondition();
		}	
		initPageUrl();
		initSelect();
	}

	/**
	 * 初始化分页查询地址
	 */
	private void initPageUrl() {
		StringBuilder url = new StringBuilder();
		url.append(String.format("%1$s/plan/planInfos_Show.do",request.getContextPath()));
		//url.append(String.format("?department.departmentGrade=%1$s", department.getDepartmentGrade()));
		page.setPath(url.toString());
	}

	/**
	 * 初始化查询条件的列表（根据用户级别）
	 */
	private void initSelect() {

	}

	/**
	 * 初始化查询条件的列表（根据用户级别）
	 */
	private void initSctForOpert() {

	}

	/**
	 * 查询层次列表
	 * @return
	 */
	public String Show() {
		init();
		planInfos.setCkType("0");
//		PlanInfosDbService db = new PlanInfosDbService();
//		this.planInfosList = db.qry(null, page);
		return "Show";
	}

	/**
	 * 设置职位查询的分页地址
	 */
	private void setUrl() {
		StringBuilder url = new StringBuilder();
		url.append(String.format(
				"location.href='%1$s/plan/planInfos_Show.do';",request.getContextPath()));
		//url.append(String.format("?department.departmentCode=%1$s", department.getDepartmentCode()));
		page.setPath(url.toString());
	}
	
	/**
	 * 获取职位信息列表
	 * @return
	 */
	public String qry() {
		// 设置分页地址
		init();
		PlanInfosDbService db = new PlanInfosDbService();
		this.planInfosList = db.qry(planInfos, page);
		if("1".equals(planInfos.getCkType())){
			BaseProDbService db1 = new BaseProDbService();
			basePro=db1.qry(planInfos.getCkProCode());
			GraduateConditionDbService db2=new GraduateConditionDbService();
			graduateCondition=db2.qry(planInfos.getCkProCode());
		};
		return "Show";
	}
	
	/**
	 * 获取职位信息列表
	 * @return
	 */
	public void expPdf() {
		// 设置分页地址
		init();
		PlanInfosDbService db = new PlanInfosDbService();
		this.planInfosList = db.qry(planInfos, page);
		if("1".equals(planInfos.getCkType())){
			BaseProDbService db1 = new BaseProDbService();
			basePro=db1.qry(planInfos.getCkProCode());
			GraduateConditionDbService db2=new GraduateConditionDbService();
			graduateCondition=db2.qry(planInfos.getCkProCode());
		};
		
		
		// 导出对应pdf文件
		String retFlag = "error";
		PlanInfosTool pit=new PlanInfosTool();
		String[] pdfPaths = pit.makeUpPlanInfos(planInfosList,basePro,graduateCondition,planInfos.getCkType());
		if (pdfPaths != null) {
			String downLink = "<a href='%1$s' target='_blank'>下载专业信息</a>";
			retFlag = String.format(downLink, pdfPaths[1]);
		} else if ( this.planInfosList == null || this.planInfosList.size() == 0) {
			retFlag = "noInfo";
		}

		// 返回下载pdf文件地址
		PrintWriter pw = null;
		response.setContentType("text/html;charset=UTF-8");
		try {
			pw = response.getWriter();
			pw.write(retFlag);
			pw.flush();
		} catch (IOException e) {
			new Log().error(this.getClass(), "生成专业信息PDF-ajax错误！", e);
		} finally {
			if (pw != null)
				pw.close();
		}		
	}	
	
	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public PlanInfos getPlanInfos() {
		return planInfos;
	}

	public void setPlanInfos(PlanInfos planInfos) {
		this.planInfos = planInfos;
	}

	public List<PlanInfos> getPlanlevelList() {
		return planInfosList;
	}

	public void setPlanlevelList(List<PlanInfos> planInfosList) {
		this.planInfosList = planInfosList;
	}

	public List<PlanInfos> getPlanInfosList() {
		return planInfosList;
	}

	public void setPlanInfosList(List<PlanInfos> planInfosList) {
		this.planInfosList = planInfosList;
	}

	public BasePro getBasePro() {
		return basePro;
	}

	public void setBasePro(BasePro basePro) {
		this.basePro = basePro;
	}

	public GraduateCondition getGraduateCondition() {
		return graduateCondition;
	}

	public void setGraduateCondition(GraduateCondition graduateCondition) {
		this.graduateCondition = graduateCondition;
	}

}
