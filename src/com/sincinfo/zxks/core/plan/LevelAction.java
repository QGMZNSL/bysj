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
import com.sincinfo.zxks.bean.PlanLevel;
import com.sincinfo.zxks.common.action.WebActionSupport;
import com.sincinfo.zxks.common.util.Page;
import com.sincinfo.zxks.zxksdbs.PlanLevelDbService;

/**
 * @ClassName: LevelAction
 * @Description: 报考层次设置 <br>
 *               <br>
 * @author yuansh
 * @date   2012-01-26 15:45<br>
 * 
 */
public class LevelAction extends WebActionSupport {
	/**
	 * @Fields serialVersionUID :
	 */
	private static final long serialVersionUID = -6217769699627471779L;

	private Page page = new Page();
	
	//层次
	private PlanLevel planlevel;

	//用来传递查询结果
	private List<PlanLevel> planlevelList;
	
	/**
	 * 初始化
	 */
	private void init() {
		if (planlevel == null) {
			planlevel = new PlanLevel();
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
		url.append(String.format("%1$s/plan/level_Show.do",request.getContextPath()));
		//url.append(String.format("?department.departmentGrade=%1$s", department.getDepartmentGrade()));
		page.setPath(url.toString());
	}

	/**
	 * 初始化查询条件的列表（根据用户级别）
	 */
	private void initSelect() {
	/*
		grades = new ArrayList<SingleEntity>();
		BaseUser optUser = getUser();
		int usrRole = 0;
		try {
			usrRole = Integer.parseInt(optUser.getUserRole());
		} catch (Exception e) {
			usrRole = -1;
		}

		switch (usrRole) {
		case 1:
			grades.add(new SingleEntity("0", "全部"));
			grades.add(new SingleEntity("1", "省级"));

		case 2:
			grades.add(new SingleEntity("2", "市级"));
			break;

		default:
			grades.add(new SingleEntity("-1", "---请选择---"));
			break;
		}
	*/	
	}

	/**
	 * 初始化查询条件的列表（根据用户级别）
	 */
	private void initSctForOpert() {
	/*
		grades = new ArrayList<SingleEntity>();
		BaseUser optUser = getUser();
		int usrRole = 0;
		try {
			usrRole = Integer.parseInt(optUser.getUserRole());
		} catch (Exception e) {
			usrRole = -1;
		}

		grades.add(new SingleEntity("", "---请选择---"));
		switch (usrRole) {
		case 1:
			grades.add(new SingleEntity("1", "省级"));
		case 2:
			grades.add(new SingleEntity("2", "市级"));
			break;
		default:
			break;
		}
	*/
	}

	/**
	 * 查询层次列表
	 * 
	 * @return
	 */
	public String Show() {
		init();
		PlanLevelDbService db = new PlanLevelDbService();
		this.planlevelList = db.qry(null, page);
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
		PlanLevelDbService db = new PlanLevelDbService();
		boolean addFlag = db.save(planlevel, 0);
		if (addFlag) {
			this.PostJs(String.format(
									"alert('添加成功！');location.href='%1$s/plan/level_Show.do';",request.getContextPath()));
		} else {
			this.PostJs(String.format("alert('添加失败！请重试。');location.href='%1$s/plan/level_Show.do';",request.getContextPath()));
		}
	}

	/**
	 * 进入修改页面
	 * 
	 * @return
	 */
	public String EditPre() {
		initSctForOpert();
		PlanLevelDbService db = new PlanLevelDbService();
		this.planlevel = db.qry(planlevel.getLevelCode());
		return "Edit";
	}

	/**
	 * 保存修改内容
	 */
	public void Edit() {
		PlanLevelDbService db = new PlanLevelDbService();
		boolean editFlag = db.save(planlevel,1);
		if (editFlag) {
			this.PostJs(String.format(
									"alert('修改成功！');location.href='%1$s/plan/level_Show.do';",
									request.getContextPath()));
		} else {
			this.PostJs(String.format("alert('修改失败！请重试。');location.href='%1$s/plan/level_Show.do';",request.getContextPath()));
		}
	}
	/**
	 * 删除，实际为禁用
	 */
	public void isUseDel(){
		PlanLevelDbService db = new PlanLevelDbService();
		boolean delFlag = db.isUseDel(planlevel.getLevelCode());
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
	public void Del() {
		PlanLevelDbService db = new PlanLevelDbService();
		boolean delFlag = db.del(planlevel.getLevelCode());
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
	 * 设置职位查询的分页地址
	 */
	private void setUrl() {
		StringBuilder url = new StringBuilder();
		url.append(String.format(
				"location.href='%1$s/plan/level_Show.do';",request.getContextPath()));
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
		PlanLevelDbService db = new PlanLevelDbService();
		
		// 查询对应层次下的所有职位列表
		this.planlevelList = db.qry(planlevel, page);
		
		// 加载权限列表
				
		return "Show";
	}
	
	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public PlanLevel getPlanlevel() {
		return planlevel;
	}

	public void setPlanlevel(PlanLevel planlevel) {
		this.planlevel = planlevel;
	}

	public List<PlanLevel> getPlanlevelList() {
		return planlevelList;
	}

	public void setPlanlevelList(List<PlanLevel> planlevelList) {
		this.planlevelList = planlevelList;
	}


}
