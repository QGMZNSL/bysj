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
import com.sincinfo.zxks.bean.AvoidExamSet;
import com.sincinfo.zxks.bean.Syllabus;
import com.sincinfo.zxks.common.action.WebActionSupport;
import com.sincinfo.zxks.common.util.Page;
import com.sincinfo.zxks.zxksdbs.AvoidExamSetDbService;
import com.sincinfo.zxks.zxksdbs.SyllabusDbService;

/**
 * @ClassName: LevelAction
 * @Description: 免考设置 <br>
 * @author yuansh
 * @date   2012-01-26 15:45<br>
 * 
 */
public class AvoidExamSetAction extends WebActionSupport {
	/**
	 * @Fields serialVersionUID :
	 */
	private static final long serialVersionUID = -6217769699627471779L;

	private Page page = new Page();
	
	//免考
	private AvoidExamSet avoidExamSet;
	
	//课程
	private Syllabus syllabus;

	//用来传递查询结果
	private List<AvoidExamSet> avoidExamSetList;
	
	//课程列表
	private List<Syllabus> syllabusList;
	
	/**
	 * 初始化
	 */
	private void init() {
		if (avoidExamSet == null) {
			avoidExamSet = new AvoidExamSet();
			//department.setDepartmentGrade(getUser().getUserRole());
		}
		if (syllabus == null) {
			syllabus = new Syllabus();
			//department.setDepartmentGrade(getUser().getUserRole());
		}		
		initPageUrl();
		initSelect();
	}

	/**
	 * 初始化分页查询地址
	 */
	private void initPageUrl() {
		StringBuilder url = new StringBuilder();
		url.append(String.format("%1$s/plan/avoidExamSet_Show.do",request.getContextPath()));
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
		SyllabusDbService db = new SyllabusDbService();
		this.syllabusList = db.qry(null, page);
		return "Show";
	}
	
	/**
	 * 免考设置
	 * @return
	 */
	public String AvoidExamSet() {
		init();
		AvoidExamSetDbService db = new AvoidExamSetDbService();
		avoidExamSet.setSyllabusCode(syllabus.getSyllabusCode());
		this.avoidExamSetList = db.qry(avoidExamSet, page);
		SyllabusDbService db1 = new SyllabusDbService();
		syllabus=db1.qry(syllabus.getSyllabusCode());
		return "AvoidExamSet";
	}

	/**
	 * 进入添加页面
	 * 
	 * @return
	 */
	public String AddPre() {
		initSctForOpert();
		return "Add";
	}

	/**
	 * 保存添加
	 */
	public void Add() {
		AvoidExamSetDbService db = new AvoidExamSetDbService();
		boolean addFlag = db.save(avoidExamSet, 0);
		if (addFlag) {
			this.PostJs(String.format(
									"alert('添加成功！');location.href='%1$s/plan/avoidExamSet_AvoidExamSet.do?syllabus.syllabusCode="+avoidExamSet.getSyllabusCode()+"';",
									request.getContextPath()));
		} else {
			this.PostJs(String.format("alert('添加失败！请重试。');location.href='%1$s/plan/avoidExamSet_AvoidExamSet.do?syllabus.avoidCode="+avoidExamSet.getSyllabusCode()+"';",request.getContextPath()));
		}
	}

	/**
	 * 进入修改页面
	 * 
	 * @return
	 */
	public String EditPre() {
		initSctForOpert();
		AvoidExamSetDbService db = new AvoidExamSetDbService();
		this.avoidExamSet = db.qry(avoidExamSet.getAvoidCode());
		return "Edit";
	}

	/**
	 * 保存修改内容
	 */
	public void Edit() {
		AvoidExamSetDbService db = new AvoidExamSetDbService();
		boolean editFlag = db.save(avoidExamSet,1);
		if (editFlag) {
			this.PostJs(String.format(
									"alert('修改成功！');location.href='%1$s/plan/avoidExamSet_AvoidExamSet.do?avoidExamSet.syllabusCode="+avoidExamSet.getAvoidCode()+"';",
									request.getContextPath()));
		} else {
			this.PostJs(String.format("alert('修改失败！请重试。');location.href='%1$s/plan/avoidExamSet_AvoidExamSet.do?avoidExamSet.syllabusCode="+avoidExamSet.getAvoidCode()+"';",request.getContextPath()));
		}
	}
	
	/**
	 * 清除免考设置
	 */
	public void Clear() {
		AvoidExamSetDbService db = new AvoidExamSetDbService();
		boolean delFlag = db.Clear(avoidExamSet.getAvoidCode());
		if (delFlag) {
			this.PostJs(String.format(
									"alert('清除成功！');location.href='%1$s/plan/avoidExamSet_Show.do';",
									request.getContextPath()));
		} else {
			this.PostJs(String.format(
					"alert('清除失败！');location.href='%1$s/plan/avoidExamSet_Show.do';",
					request.getContextPath()));
		}
	}

	/**
	 * 删除免考设置
	 */
	public void Del() {
		AvoidExamSetDbService db = new AvoidExamSetDbService();
		boolean delFlag = db.del(syllabus.getSyllabusCode());
		if (delFlag) {
			this.PostJs(String.format(
									"alert('删除成功！');location.href='%1$s/plan/avoidExamSet_AvoidExamSet.do?avoidExamSet.avoidCode="+avoidExamSet.getAvoidCode()+"';",
									request.getContextPath()));
		} else {
			this.PostJs(String.format(
					"alert('删除失败！');location.href='%1$s/plan/avoidExamSet_AvoidExamSet.do?avoidExamSet.avoidCode="+avoidExamSet.getAvoidCode()+"';",
					request.getContextPath()));
		}
	}
	
	/**
	 * 设置职位查询的分页地址
	 */
	private void setUrl() {
		StringBuilder url = new StringBuilder();
		url.append(String.format(
				"location.href='%1$s/plan/avoidExamSet_Show.do';",request.getContextPath()));
		//url.append(String.format("?department.departmentCode=%1$s", department.getDepartmentCode()));
		page.setPath(url.toString());
	}
	
	/**
	 * 获取职位信息列表
	 * @return
	 */
	public String qry() {
		// 设置分页地址
		setUrl();
		
		// 获取点击的层次信息
		SyllabusDbService db = new SyllabusDbService();
		
		// 查询对应层次下的所有职位列表
		this.syllabusList = db.qry(syllabus, page);
		
		// 加载权限列表
				
		return "Show";
	}
	
	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public AvoidExamSet getAvoidExamSet() {
		return avoidExamSet;
	}

	public void setAvoidExamSet(AvoidExamSet avoidExamSet) {
		this.avoidExamSet = avoidExamSet;
	}

	public List<AvoidExamSet> getAvoidExamSetList() {
		return avoidExamSetList;
	}

	public void setAvoidExamSetList(List<AvoidExamSet> avoidExamSetList) {
		this.avoidExamSetList = avoidExamSetList;
	}

	public List<Syllabus> getSyllabusList() {
		return syllabusList;
	}

	public void setSyllabusList(List<Syllabus> syllabusList) {
		this.syllabusList = syllabusList;
	}

	public Syllabus getSyllabus() {
		return syllabus;
	}

	public void setSyllabus(Syllabus syllabus) {
		this.syllabus = syllabus;
	}

}
