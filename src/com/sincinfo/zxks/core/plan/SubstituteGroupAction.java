/**
 * Copyright(c) 2012 西安云海信息技术有限责任公司 All Rights Reserved<br>
 * @Title: com.sincinfo.zxks.core.sys.LoginAction.java<br>
 * @Description: 顶替分组
 * @author yuansh
 * @date 2012-01-26 15:26
 * @version V1.0   
 */
package com.sincinfo.zxks.core.plan;

import java.util.List;
import com.sincinfo.zxks.bean.SubstituteGroup;
import com.sincinfo.zxks.bean.Syllabus;
import com.sincinfo.zxks.common.action.WebActionSupport;
import com.sincinfo.zxks.common.util.Page;
import com.sincinfo.zxks.zxksdbs.SubstituteGroupDbService;
import com.sincinfo.zxks.zxksdbs.SyllabusDbService;

/**
 * @ClassName: LevelAction
 * @Description: 顶替分组 
 * @author       yuansh
 * @date   2012-01-26 15:45
 */
public class SubstituteGroupAction extends WebActionSupport {
	/**
	 * @Fields serialVersionUID :
	 */
	private static final long serialVersionUID = -6217769699627471779L;

	private Page page = new Page();
	
	//分组
	private SubstituteGroup substituteGroup;

	//用来传递查询结果
	private List<SubstituteGroup> substituteGroupList;
	
	private List<Syllabus> syllabusList;
	
	/**
	 * 初始化
	 */
	private void init() {
		if (substituteGroup == null) {
			substituteGroup = new SubstituteGroup();
		}
		initPageUrl();
		initSelect();
	}

	/**
	 * 初始化分页查询地址
	 */
	private void initPageUrl() {
		StringBuilder url = new StringBuilder();
		url.append(String.format("%1$s/plan/substituteGroup_Show.do",request.getContextPath()));
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
	 * 查询分组列表
	 * 
	 * @return
	 */
	public String Show() {
		init();
		SubstituteGroupDbService db = new SubstituteGroupDbService();
		this.substituteGroupList = db.qry(null, page);
		return "Show";
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
		SubstituteGroupDbService db = new SubstituteGroupDbService();
		boolean addFlag = db.save(substituteGroup, 0);
		if (addFlag) {
			this.PostJs(String.format(
				"alert('添加成功！');location.href='%1$s/plan/substituteGroup_Show.do';",request.getContextPath()));
		} else {
			this.PostJs(String.format("alert('添加失败！请重试。');location.href='%1$s/plan/substituteGroup_Show.do';",request.getContextPath()));
		}
	}

	/**
	 * 进入修改页面
	 * 
	 * @return
	 */
	public String EditPre() {
		initSctForOpert();
		SubstituteGroupDbService db = new SubstituteGroupDbService();
		this.substituteGroup = db.qry(substituteGroup.getSubstituteGroupId());
		return "Edit";
	}
	
	/**
	 * 进入课程顶替组设置页面
	 * @return
	 */
	public String SubStitutePre() {
		initSctForOpert();
		SubstituteGroupDbService db = new SubstituteGroupDbService();
		this.substituteGroup = db.qry(substituteGroup.getSubstituteGroupId());
		SyllabusDbService db1 = new SyllabusDbService();
		this.syllabusList = db1.qry1(null, page);
		return "SubStituteSet";
	}	
	
	/**
	 * 进入课程顶替组设置保存页面
	 * @return
	 */
	public void SubStituteSet() {
		SubstituteGroupDbService db = new SubstituteGroupDbService();
		boolean editFlag = db.saveGroupSyllabus(substituteGroup);
		if (editFlag) {
			this.PostJs(String.format("alert('修改成功！');location.href='%1$s/plan/substituteGroup_SubStitutePre.do?substituteGroup.substituteGroupId="+substituteGroup.getSubstituteGroupId()+"';",request.getContextPath()));
		} else {
			this.PostJs(String.format("alert('修改失败！请重试。');location.href='%1$s/plan/substituteGroup_SubStitutePre.do?substituteGroup.substituteGroupId="+substituteGroup.getSubstituteGroupId()+"';",request.getContextPath()));
		}
	}		

	/**
	 * 保存修改内容
	 */
	public void Edit() {
		SubstituteGroupDbService db = new SubstituteGroupDbService();
		boolean editFlag = db.save(substituteGroup,1);
		if (editFlag) {
			this.PostJs(String.format("alert('修改成功！');location.href='%1$s/plan/substituteGroup_Show.do';",request.getContextPath()));
		} else {
			this.PostJs(String.format("alert('修改失败！请重试。');location.href='%1$s/plan/substituteGroup_Show.do';",request.getContextPath()));
		}
	}

	/**
	 * 删除分组
	 */
	public void Del() {
		SubstituteGroupDbService db = new SubstituteGroupDbService();
		boolean delFlag = db.del(substituteGroup.getSubstituteGroupId());
		if (delFlag) {
			this.PostJs(String.format(
									"alert('删除成功！');location.href='%1$s/plan/substituteGroup_Show.do';",request.getContextPath()));
		} else {
			this.PostJs(String.format(
					"alert('删除失败！');location.href='%1$s/plan/substituteGroup_Show.do';",
					request.getContextPath()));
		}
	}
	
	/**
	 * 设置职位查询的分页地址
	 */
	private void setUrl() {
		StringBuilder url = new StringBuilder();
		url.append(String.format(
				"location.href='%1$s/plan/substituteGroup_Show.do';",request.getContextPath()));
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
		
		// 获取点击的分组信息
		SubstituteGroupDbService db = new SubstituteGroupDbService();
		
		// 查询对应分组下的所有职位列表
		this.substituteGroupList = db.qry(substituteGroup, page);
		
		// 加载权限列表
				
		return "Show";
	}
	
	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public SubstituteGroup getSubstituteGroup() {
		return substituteGroup;
	}

	public void setSubstituteGroup(SubstituteGroup substituteGroup) {
		this.substituteGroup = substituteGroup;
	}

	public List<SubstituteGroup> getSubstituteGroupList() {
		return substituteGroupList;
	}

	public void setSubstituteGroupList(List<SubstituteGroup> substituteGroupList) {
		this.substituteGroupList = substituteGroupList;
	}

	public List<Syllabus> getSyllabusList() {
		return syllabusList;
	}

	public void setSyllabusList(List<Syllabus> syllabusList) {
		this.syllabusList = syllabusList;
	}

}
