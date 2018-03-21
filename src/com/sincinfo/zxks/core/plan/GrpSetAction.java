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
import com.sincinfo.zxks.bean.GrpSet;
import com.sincinfo.zxks.bean.ProSyllabus;
import com.sincinfo.zxks.common.action.WebActionSupport;
import com.sincinfo.zxks.common.util.Page;
import com.sincinfo.zxks.zxksdbs.BaseProDbService;
import com.sincinfo.zxks.zxksdbs.GrpSetDbService;
import com.sincinfo.zxks.zxksdbs.ProSyllabusDbService;

/**
 * @ClassName: GrpSetAction
 * @Description: 课程分组设置 <br>
 * @author yuansh
 * @date   2012-01-26 15:45<br>
 */
public class GrpSetAction extends WebActionSupport {
	/**
	 * @Fields serialVersionUID :
	 */
	private static final long serialVersionUID = -6217769699627471779L;

	private Page page = new Page();
	
	//课程分组设置
	private GrpSet grpSet;
	
	//专业
	private BasePro basepro;

	//用来传递查询结果
	private List<GrpSet> GrpSetList;
	
	//扩展：专业课程列表
	private List<ProSyllabus> proSyllabusList;
	
	/**
	 * 初始化
	 */
	private void init() {
		if (grpSet == null) {
			grpSet = new GrpSet();
			//department.setDepartmentGrade(getUser().getUserRole());
		}
		if (basepro == null) {
			basepro = new BasePro();
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
		url.append(String.format("%1$s/plan/grpSet_Show.do",request.getContextPath()));
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
	 * 查询课程分组列表
	 * 
	 * @return
	 */
	public String Show() {
			init();
			if(basepro!=null){
			grpSet.setProCode(basepro.getProCode());
			GrpSetDbService db = new GrpSetDbService();
			this.GrpSetList = db.qry(grpSet, page);
			BaseProDbService db1 = new BaseProDbService();
			this.basepro = db1.qry(basepro.getProCode());
		}
		return "Show";
	}

	/**
	 * 查询课程分组设置
	 * 
	 * @return
	 */
	public String GrpSettSyllabus() {
		init();
		grpSet.setProCode(basepro.getProCode());
		GrpSetDbService db = new GrpSetDbService();
		this.GrpSetList = db.qry(grpSet, page);
		ProSyllabusDbService db1 = new ProSyllabusDbService();
		this.proSyllabusList = db1.qryProSyllabus(basepro,page);
		this.grpSet = db1.qryGroup(grpSet.getSyllabusGroupCode());	
		BaseProDbService db2 = new BaseProDbService();
		this.basepro = db2.qry(basepro.getProCode());		
		return "GrpSettSyllabus";
	}
	
	/**
	 * 查询课程分组设置
	 * 
	 * @return
	 */
	public String GrpSetGroup() {
		init();
		grpSet.setProCode(basepro.getProCode());
		GrpSetDbService db = new GrpSetDbService();
		this.GrpSetList = db.qry1(grpSet, page);
		/*
		ProSyllabusDbService db1 = new ProSyllabusDbService();
		this.grpSet = db1.qryGroup(grpSet.getSyllabusGroupCode());	
		BaseProDbService db2 = new BaseProDbService();
		this.basepro = db2.qry(basepro.getProCode());		*/
		return "GrpSetGroup";
	}
	
	/**
	 * 进入添加页面
	 * 
	 * @return
	 */
	public String AddPre() {
		init();
		BaseProDbService db = new BaseProDbService();
		this.basepro = db.qry(basepro);			
		grpSet.setProCode(basepro.getProCode());		
		return "Add";
	}

	/**
	 * 保存添加
	 */
	public void Add() {
		GrpSetDbService db = new GrpSetDbService();
		boolean addFlag = db.save(grpSet, 0);
		if (addFlag) {
			this.PostJs(String.format(
									"alert('添加成功！');location.href='%1$s/plan/grpSet_Show.do?basepro.proCode="+grpSet.getProCode()+"';",
									request.getContextPath()));
		} else {
			this.PostJs(String.format("alert('添加失败！请重试。');location.href='%1$s/plan/grpSet_Show.do?basepro.proCode="+grpSet.getProCode()+"';",request.getContextPath()));
		}
	}
	
	/**
	 * 添加分组
	 */
	public void AddGroup() {
		GrpSetDbService db = new GrpSetDbService();
		boolean addFlag = db.save(grpSet, 0);
		if (addFlag) {
			this.PostJs(String.format(
									"alert('添加成功！');location.href='%1$s/plan/grpSet_Show.do?basepro.proCode=${basepro.proCode}';",
									request.getContextPath()));
		} else {
			this.PostJs(String.format("alert('添加失败！请重试。');location.href='%1$s/plan/grpSet_Show.do?basepro.proCode=${basepro.proCode}';",request.getContextPath()));
		}
	}	

	/**
	 * 进入修改页面
	 * 
	 * @return
	 */
	public String EditPre() {
		initSctForOpert();
		GrpSetDbService db = new GrpSetDbService();
		this.grpSet = db.qry(grpSet.getSyllabusGroupCode());
		BaseProDbService db1 = new BaseProDbService();
		this.basepro = db1.qry(grpSet.getProCode());	
		return "Edit";
	}

	/**
	 * 保存修改内容
	 */
	public void Edit() {
		GrpSetDbService db = new GrpSetDbService();
		boolean editFlag = db.save(grpSet,1);
		if (editFlag) {
			this.PostJs(String.format(
									"alert('修改成功！');location.href='%1$s/plan/grpSet_Show.do';",
									request.getContextPath()));
		} else {
			this.PostJs(String.format("alert('修改失败！请重试。');location.href='%1$s/plan/grpSet_Show.do';",request.getContextPath()));
		}
	}
	
	/**
	 * 更新群组中分组
	 */
	public void EditGroupGrp() {
		GrpSetDbService db = new GrpSetDbService();
		boolean addFlag = db.saveGroupGrp(grpSet);
		if (addFlag) {
			this.PostJs(String.format(
									"alert('添加成功！');location.href='%1$s/plan/proSyllabus_Show.do';",
									request.getContextPath()));
		} else {
			this.PostJs(String.format(
					"alert('添加失败！');location.href='%1$s/plan/proSyllabus_Show.do';",
					request.getContextPath()));
		}
	}		

	/**
	 * 删除层次
	 */
	public void Del() {
		GrpSetDbService db = new GrpSetDbService();
		boolean delFlag = db.del(grpSet.getSyllabusGroupCode());
		if (delFlag) {
			this.PostJs(String.format(
									"alert('删除成功！');location.href='%1$s/plan/grpSet_Show.do';",
									request.getContextPath()));
		} else {
			this.PostJs(String.format(
					"alert('删除失败！');location.href='%1$s/plan/grpSet_Show.do';",
					request.getContextPath()));
		}
	}
	
	/**
	 * 设置职位查询的分页地址
	 */
	private void setUrl() {
		StringBuilder url = new StringBuilder();
		url.append(String.format(
				"location.href='%1$s/plan/grpSet_Show.do';",request.getContextPath()));
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
		GrpSetDbService db = new GrpSetDbService();
		this.GrpSetList = db.qry(grpSet, page);
		BaseProDbService db1 = new BaseProDbService();
		if(basepro==null){
			basepro=new BasePro();
			basepro.setProCode(grpSet.getProCode());
			basepro.setProName(grpSet.getProName());
		}
		this.basepro = db1.qry(basepro);		
		return "Show";
	}
	
	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public GrpSet getGrpSet() {
		return grpSet;
	}

	public void setGrpSet(GrpSet grpSet) {
		this.grpSet = grpSet;
	}

	public BasePro getBasepro() {
		return basepro;
	}

	public void setBasepro(BasePro basepro) {
		this.basepro = basepro;
	}

	public List<GrpSet> getGrpSetList() {
		return GrpSetList;
	}

	public void setGrpSetList(List<GrpSet> GrpSetList) {
		this.GrpSetList = GrpSetList;
	}

	public List<ProSyllabus> getProSyllabusList() {
		return proSyllabusList;
	}

	public void setProSyllabusList(List<ProSyllabus> proSyllabusList) {
		this.proSyllabusList = proSyllabusList;
	}

}
