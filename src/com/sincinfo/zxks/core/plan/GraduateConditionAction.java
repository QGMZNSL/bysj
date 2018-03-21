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


import java.util.List;

import com.sincinfo.zxks.bean.BasePro;
import com.sincinfo.zxks.bean.GraduateCondition;
import com.sincinfo.zxks.bean.GraduateGroup;
import com.sincinfo.zxks.bean.GraduateNeed;
import com.sincinfo.zxks.bean.GrpSet;
import com.sincinfo.zxks.bean.PlanLevel;
import com.sincinfo.zxks.bean.ProSyllabus;
import com.sincinfo.zxks.bean.Syllabus;
import com.sincinfo.zxks.common.action.WebActionSupport;
import com.sincinfo.zxks.common.util.Page;
import com.sincinfo.zxks.common.util.StringTool;
import com.sincinfo.zxks.zxksdbs.BaseProDbService;
import com.sincinfo.zxks.zxksdbs.GraduateConditionDbService;
import com.sincinfo.zxks.zxksdbs.GrpSetDbService;
import com.sincinfo.zxks.zxksdbs.PlanLevelDbService;
import com.sincinfo.zxks.zxksdbs.SyllabusDbService;

/**
 * @ClassName: GraduateConditionAction
 * @Description: 毕业条件设置 <br>
 * @author yuansh
 * @date   2012-01-26 15:45<br>
 * 
 */
public class GraduateConditionAction extends WebActionSupport {
	/**
	 * @Fields serialVersionUID :
	 */
	
	private Page page = new Page();
	
	private BasePro basePro;
	
	private GrpSet grpSet;
	
	public GrpSet getGrpSet() {
		return grpSet;
	}

	public void setGrpSet(GrpSet grpSet) {
		this.grpSet = grpSet;
	}

	//毕业条件
	private GraduateCondition graduateCondition;
	
	private GraduateGroup graduateGroup;
	
	private GraduateNeed graduateNeed;

	//用来传递查询结果
	private List<GraduateCondition> graduateConditionList;
	
	private List<GraduateGroup> graduateGroupList;
	
	private List<GraduateNeed> graduateNeedList;
	
	private List<PlanLevel> planlevelList;
	
	//用来传递查询结果
	private List<BasePro> baseProList;
	
	private List<Syllabus> syllabusList;
	//扩展：课程列表
	private List<ProSyllabus> proSyllabusList;
	
    //扩展：专业分组
	private List<GrpSet> grpSetList;
	
	/**
	 * 初始化
	 */
	private void init() {
		if (graduateCondition == null) {
			graduateCondition = new GraduateCondition();
		}
		if (basePro == null) {
			basePro = new BasePro();
		}			
		initPageUrl();
		initSelect();
	}

	/**
	 * 初始化分页查询地址
	 */
	private void initPageUrl() {
		StringBuilder url = new StringBuilder();
		url.append(String.format("%1$s/plan/level_Show.do",request.getContextPath()));
		//url.append(String.format("?department.departmentGrade=%1$s", department.getDepartmentGrade()));
		page.setPath(url.toString());
	}

	/**
	 * 初始化查询条件的列表（根据用户级别）
	 */
	private void initSelect() {
		PlanLevelDbService db=new PlanLevelDbService();
		planlevelList=db.qry(null, page);
	}

	/**
	 * 初始化查询条件的列表（根据用户级别）
	 */
	private void initSctForOpert() {

	}

	/**
	 * 查询专业列表
	 * @return
	 */
	public String Show() {
		init();
		BaseProDbService db = new BaseProDbService();
		this.baseProList = db.qry(null, page);
		return "Show";
	}

	/**
	 * 进入添加页面
	 * @return
	 */
	public String AddPre() {
		initSctForOpert();
		return "Add";
	}

	/**
	 * 保存添加
	 */
	public void Add1() {
		GraduateConditionDbService db = new GraduateConditionDbService();
		boolean addFlag = db.save1(graduateGroup);
		if (addFlag) {
			this.PostJs(String.format(
			"alert('添加成功！');location.href='%1$s/plan/graduateCondition_EditPre.do?graduateCondition.proCode="+graduateGroup.getProCode()+"';",request.getContextPath()));
		} else {
			this.PostJs(String.format("alert('添加失败！请重试。');location.href='%1$s/plan/graduateCondition_EditPre.do?graduateCondition.proCode="+graduateGroup.getProCode()+"';",request.getContextPath()));
		}
	}
	
	/**
	 * 保存添加
	 */
	public void Add2() {
		GraduateConditionDbService db = new GraduateConditionDbService();
		boolean addFlag = db.save2(graduateNeed);
		if (addFlag) {
			this.PostJs(String.format(
			"alert('添加成功！');location.href='%1$s/plan/graduateCondition_EditPre.do?graduateCondition.proCode="+graduateNeed.getProCode()+"';",request.getContextPath()));
		} else {
			this.PostJs(String.format("alert('添加失败！请重试。');location.href='%1$s/plan/graduateCondition_EditPre.do?graduateCondition.proCode="+graduateNeed.getProCode()+"';",request.getContextPath()));
		}
	}	

	/**
	 * 进入修改页面
	 * 
	 * @return
	 */
	public String EditPre() {
		init();
		GraduateConditionDbService db = new GraduateConditionDbService();
		String proCode=graduateCondition.getProCode();
		this.graduateCondition = db.qry(graduateCondition.getProCode());
		if(graduateCondition==null || StringTool.isEmpty(graduateCondition.getGraduateConditionCredit()) || graduateCondition.getGraduateConditionCredit().length()==0){
			graduateCondition=new GraduateCondition();
			graduateCondition.setSaveType("0");
			graduateCondition.setProCode(proCode);
		}else
			graduateCondition.setSaveType("1");
		graduateGroupList=db.qry1(proCode, page);
		graduateNeedList=db.qry2(proCode, page);
		//得到专业必考课程列表 yuansh 2012-02-21
		BaseProDbService db1 = new BaseProDbService();
		basePro.setProCode(proCode);
		this.proSyllabusList = db1.qryProSyllabus(basePro,page);
		//得到专业分组列表  yuansh 2012-02-22
		GrpSetDbService db2=new GrpSetDbService();
		if(grpSet==null){
			grpSet=new GrpSet();
			grpSet.setProCode(proCode);
		}
		this.grpSetList=db2.qry(grpSet, page);
		return "Edit";
	}

	/**
	 * 保存修改内容
	 */
	public void Edit() {
		GraduateConditionDbService db = new GraduateConditionDbService();
		boolean editFlag = db.save(graduateCondition,Integer.parseInt(graduateCondition.getSaveType()));
		if (editFlag) {
			this.PostJs(String.format(
					"alert('修改成功！');location.href='%1$s/plan/graduateCondition_Show.do';",request.getContextPath()));
		} else {
			this.PostJs(String.format("alert('修改失败！请重试。');location.href='%1$s/plan/graduateCondition_Show.do';",request.getContextPath()));
		}
	}

	/**
	 * 删除层次
	 */
	public void Del() {
		GraduateConditionDbService db = new GraduateConditionDbService();
		boolean delFlag = db.del(graduateCondition.getProCode());
		if (delFlag) {
			this.PostJs(String.format(
					"alert('删除成功！');location.href='%1$s/plan/level_Show.do';",
					request.getContextPath()));
		} else {
			this.PostJs(String.format(
					"alert('删除失败！');location.href='%1$s/plan/level_Show.do';",
					request.getContextPath()));
		}
	}
	/**
	 * 删除层次
	 */
	public void Del1() {
		GraduateConditionDbService db = new GraduateConditionDbService();
		String proCode=graduateGroup.getProCode();
		boolean delFlag = db.del1(graduateGroup.getGraduateGroupCode());
		if (delFlag) {
			this.PostJs(String.format(
					"alert('删除成功！');location.href='%1$s/plan/graduateCondition_EditPre.do?graduateCondition.proCode="+proCode+"';",request.getContextPath()));
		} else {
			this.PostJs(String.format(
					"alert('删除失败！');location.href='%1$s/plan/graduateCondition_EditPre.do?graduateCondition.proCode="+proCode+"';",request.getContextPath()));
		}
	}	
	
	/**
	 * 删除层次
	 */
	public void Del2() {
		GraduateConditionDbService db = new GraduateConditionDbService();
		String proCode=graduateNeed.getProCode();
		boolean delFlag = db.del2(graduateNeed.getGraduateNeedCode());
		if (delFlag) {
			this.PostJs(String.format(
					"alert('删除成功！');location.href='%1$s/plan/graduateCondition_EditPre.do?graduateCondition.proCode="+proCode+"';",request.getContextPath()));
		} else {
			this.PostJs(String.format(
					"alert('删除失败！');location.href='%1$s/plan/graduateCondition_EditPre.do?graduateCondition.proCode="+proCode+"';",request.getContextPath()));
		}
	}	
	
	/**
	 * 设置职位查询的分页地址
	 */
	private void setUrl() {
		StringBuilder url = new StringBuilder();
		url.append(String.format("%1$s/plan/level_Show.do",request.getContextPath()));
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
		BaseProDbService db = new BaseProDbService();
		this.baseProList = db.qry(basePro, page);
		return "Show";
	}
	
	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public GraduateCondition getPlanlevel() {
		return graduateCondition;
	}

	public void setPlanlevel(GraduateCondition graduateCondition) {
		this.graduateCondition = graduateCondition;
	}

	public GraduateCondition getGraduateCondition() {
		return graduateCondition;
	}

	public void setGraduateCondition(GraduateCondition graduateCondition) {
		this.graduateCondition = graduateCondition;
	}

	public List<BasePro> getBaseProList() {
		return baseProList;
	}

	public void setBaseProList(List<BasePro> baseProList) {
		this.baseProList = baseProList;
	}

	public BasePro getBasePro() {
		return basePro;
	}

	public void setBasePro(BasePro basePro) {
		this.basePro = basePro;
	}

	public List<GraduateCondition> getGraduateConditionList() {
		return graduateConditionList;
	}

	public void setGraduateConditionList(
			List<GraduateCondition> graduateConditionList) {
		this.graduateConditionList = graduateConditionList;
	}

	public List<PlanLevel> getPlanlevelList() {
		return planlevelList;
	}

	public void setPlanlevelList(List<PlanLevel> planlevelList) {
		this.planlevelList = planlevelList;
	}

	public List<GraduateGroup> getGraduateGroupList() {
		return graduateGroupList;
	}

	public void setGraduateGroupList(List<GraduateGroup> graduateGroupList) {
		this.graduateGroupList = graduateGroupList;
	}

	public List<GraduateNeed> getGraduateNeedList() {
		return graduateNeedList;
	}

	public void setGraduateNeedList(List<GraduateNeed> graduateNeedList) {
		this.graduateNeedList = graduateNeedList;
	}

	public List<Syllabus> getSyllabusList() {
		return syllabusList;
	}

	public void setSyllabusList(List<Syllabus> syllabusList) {
		this.syllabusList = syllabusList;
	}

	public List<ProSyllabus> getProSyllabusList() {
		return proSyllabusList;
	}

	public void setProSyllabusList(List<ProSyllabus> proSyllabusList) {
		this.proSyllabusList = proSyllabusList;
	}

	public List<GrpSet> getGrpSetList() {
		return grpSetList;
	}

	public void setGrpSetList(List<GrpSet> grpSetList) {
		this.grpSetList = grpSetList;
	}

	public GraduateGroup getGraduateGroup() {
		return graduateGroup;
	}

	public void setGraduateGroup(GraduateGroup graduateGroup) {
		this.graduateGroup = graduateGroup;
	}

	public GraduateNeed getGraduateNeed() {
		return graduateNeed;
	}

	public void setGraduateNeed(GraduateNeed graduateNeed) {
		this.graduateNeed = graduateNeed;
	}


}
