/**
 * Copyright(c) 2012 西安云海信息技术有限责任公司 All Rights Reserved<br>
 * @Title: com.sincinfo.zxks.core.sys.LoginAction.java<br>
 * @Description: 报考层次管理<br>
 * <br>
 * @author yuansh<br>
 * @date 2012-02-11 12:26
 * @version V1.0   
 */
package com.sincinfo.zxks.core.plan;


import java.util.List;
import com.sincinfo.zxks.bean.Substitute;
import com.sincinfo.zxks.bean.Syllabus;
import com.sincinfo.zxks.common.action.WebActionSupport;
import com.sincinfo.zxks.common.util.Page;
import com.sincinfo.zxks.zxksdbs.SubstituteDBService;
import com.sincinfo.zxks.zxksdbs.SyllabusDbService;

/**
 * @ClassName: LevelAction
 * @Description: 课程顶替设置 <br>
 * @author yuansh
 * @date   2012-01-26 15:45<br>
 * 
 */
public class SubstituteAction extends WebActionSupport {
	/**
	 * @Fields serialVersionUID :
	 */
	private static final long serialVersionUID = -6217769699627471779L;

	private Page page = new Page();
	//免考
	private Substitute substitute;
	
	//课程
	private Syllabus syllabus;

	//用来传递查询结果
	private List<Substitute> substituteList;
	
	//课程列表
	private List<Syllabus> syllabusList;
	
	/**
	 * 初始化
	 */
	private void init() {
		if (substitute == null) {
			substitute = new Substitute();
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
		url.append(String.format("%1$s/plan/subStitute_Show.do",request.getContextPath()));
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
	 * 查询课程顶替列表
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
	public String SubStituteSet() {
		init();
		SubstituteDBService db = new SubstituteDBService();
		//在免考课程设置里边设置课程代码,这个课程代码来自于课程表里边的的课程代码
		substitute.setSyllabuscode(syllabus.getSyllabusCode());
		this.substituteList = db.qry(substitute,page);
		
		//查询课程表
		SyllabusDbService db1 = new SyllabusDbService();
		syllabus=db1.qry(syllabus.getSyllabusCode());//qrySUB(syllabus.getSyllabusCode());
		return "SubStituteSet";
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
		SubstituteDBService db=new SubstituteDBService();
		boolean addFlag = db.save(substitute, 0);
		if (addFlag) {
			this.PostJs(String.format(
				"alert('添加成功！');location.href='%1$s/plan/subStitute_SubStituteSet.do?syllabus.syllabusCode="+substitute.getSyllabuscode()+"';",
				request.getContextPath()));
		} else {
			this.PostJs(String.format("alert('添加失败！请重试。');location.href='%1$s/plan/subStitute_SubStituteSet.do?syllabus.syllabusCode="+substitute.getSyllabuscode()+"';",request.getContextPath()));
		}
	}

	/**
	 * 进入修改页面
	 * 
	 * @return
	 */
	public String EditPre() {
		initSctForOpert();
		SubstituteDBService db=new SubstituteDBService();
		this.substitute = db.qry(substitute.getSyllabuscode());
		return "Edit";
	}
	/**
	 * 保存修改内容
	 */
	public void Edit() {
		SubstituteDBService db=new SubstituteDBService();
		boolean editFlag = db.save(substitute,1);
		if (editFlag) {
			this.PostJs(String.format(
									"alert('修改成功！');location.href='%1$s/plan/subStitute_SubStituteSet.do?substitute.syllabusCode="+syllabus.getSyllabusCode()+"';",
									request.getContextPath()));
		} else {
			this.PostJs(String.format("alert('修改失败！请重试。');location.href='%1$s/plan/subStitute_SubStituteSet.do?substitute.syllabusCode="+syllabus.getSyllabusCode()+"';",request.getContextPath()));
		}
	}
	
	/**
	 * 清除顶替设置
	 */
	public void Clear() {
		SubstituteDBService db=new SubstituteDBService();
		boolean delFlag = db.Clear(syllabus.getSyllabusCode());
		if (delFlag) {
			this.PostJs(String.format(
									"alert('清除成功！');location.href='%1$s/plan/subStitute_Show.do';",
									request.getContextPath()));
		} else {
			this.PostJs(String.format(
					"alert('清除失败！');location.href='%1$s/plan/subStitute_Show.do';",
					request.getContextPath()));
		}
	}

	/**
	 * 删除顶替设置
	 */
	public void Del() {
		SubstituteDBService db = new SubstituteDBService();
		String syllabusCode=syllabus.getSyllabusCode();
		boolean delFlag = db.del(substitute.getSubstitutecode());
		if (delFlag) {
			this.PostJs(String.format(
					"alert('删除成功！');location.href='%1$s/plan/subStitute_SubStituteSet.do?syllabus.syllabusCode="+syllabusCode+"';",
					request.getContextPath()));
		} else {
			this.PostJs(String.format(
					"alert('删除失败！');location.href='%1$s/plan/subStitute_SubStituteSet.do?syllabus.syllabusCode="+syllabusCode+"';",
					request.getContextPath()));
		}
	}
	
	/**
	 * 设置职位查询的分页地址
	 */
	private void setUrl() {
		StringBuilder url = new StringBuilder();
		url.append(String.format(
				"location.href='%1$s/plan/subStitute_Show.do';",request.getContextPath()));
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
		
		// 获取点击的课程信息
		SyllabusDbService db = new SyllabusDbService();
		
		// 查询对应层次下的所有课程列表
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
	public Substitute getSubstitute() {
		return substitute;
	}

	public void setSubstitute(Substitute substitute) {
		this.substitute = substitute;
	}

	public List<Substitute> getSubstituteList() {
		return substituteList;
	}

	public void setSubstituteList(List<Substitute> substituteList) {
		this.substituteList = substituteList;
	}
}
