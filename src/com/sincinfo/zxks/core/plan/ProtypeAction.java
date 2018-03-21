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
import com.sincinfo.zxks.bean.ProType;
import com.sincinfo.zxks.bean.PlanLevel;
import com.sincinfo.zxks.common.action.WebActionSupport;
import com.sincinfo.zxks.common.util.Page;
import com.sincinfo.zxks.zxksdbs.PlanLevelDbService;
import com.sincinfo.zxks.zxksdbs.ProTypeDbService;


/**
 * @ClassName: LevelAction
 * @Description: 专业类型设置 <br>
 *               <br>
 * @author yuansh
 * @date   2012-01-26 15:45<br>
 * 
 */
public class ProtypeAction extends WebActionSupport {
	/**
	 * @Fields serialVersionUID :
	 */
	private static final long serialVersionUID = -6217769699627471779L;

	private Page page = new Page();
	
	//专业类型
	private ProType protype;

	//用来传递查询结果
	private List<ProType> protypeList;
	
	//专业层次列表
	private List<PlanLevel> planlevelList;
	
	/**
	 * 初始化
	 */
	private void init() {
		if (protype == null) {
			protype = new ProType();
		}
		initPageUrl();
		initSelect();
	}

	/**
	 * 初始化分页查询地址
	 */
	private void initPageUrl() {
		StringBuilder url = new StringBuilder();
		url.append(String.format("%1$s/plan/protype_Show.do",request.getContextPath()));
		//url.append(String.format("?department.departmentGrade=%1$s", department.getDepartmentGrade()));
		page.setPath(url.toString());
	}

	/**
	 * 初始化查询条件的列表（根据用户级别）
	 */
	private void initSelect() {
      
	}

	/**
	 * 初始化查询条件的列表（根据用户级别），添加时也用
	 */
	private void initSctForOpert() {
		ProTypeDbService db = new ProTypeDbService();
		this.planlevelList = db.qryPlanLevelClasses();
	}

	/**
	 * 查询专业类型列表
	 * 
	 * @return
	 */
	public String Show() {
		init();
		ProTypeDbService db = new ProTypeDbService();
		this.protypeList = db.qry(null, page);
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
		ProTypeDbService db = new ProTypeDbService();
		boolean addFlag = db.save(protype, 0);
		if (addFlag) {
			this.PostJs(String.format(
									"alert('添加成功！');location.href='%1$s/plan/protype_Show.do';",
									request.getContextPath()));
		} else {
			this.PostJs(String.format(
					"alert('添加失败！');location.href='%1$s/plan/protype_Show.do';",
					request.getContextPath()));
		}
	}

	/**
	 * 进入修改页面
	 * 
	 * @return
	 */
	public String EditPre() {
		initSctForOpert();
		ProTypeDbService db = new ProTypeDbService();
		this.protype = db.qry(protype.getProTypeCode());
		return "Edit";
	}

	/**
	 * 保存修改内容
	 */
	public void Edit() {
		ProTypeDbService db = new ProTypeDbService();
		boolean editFlag = db.save(protype,1);
		if (editFlag) {
			this.PostJs(String.format(
									"alert('修改成功！');location.href='%1$s/plan/protype_Show.do';",
									request.getContextPath()));
		} else {
			this.PostJs(String.format(
					"alert('修改失败！请重新试');location.href='%1$s/plan/protype_Show.do';",
					request.getContextPath()));
		}
	}
	
	/**
	 * 将删除变更为禁用
	 */
	public void isUseDel(){
		ProTypeDbService db = new ProTypeDbService();
		boolean delFlag = db.isUseDel(protype.getProTypeCode());
		if (delFlag) {
			this.PostJs(String.format(
									"alert('删除成功！');location.href='%1$s/plan/protype_Show.do';",
									request.getContextPath()));
		} else {
			this.PostJs(String.format(
					"alert('删除失败！');location.href='%1$s/plan/protype_Show.do';",
					request.getContextPath()));
		}
	}

	/**
	 * 删除
	 */
	public void Del() {
		ProTypeDbService db = new ProTypeDbService();
		boolean delFlag = db.del(protype.getProTypeCode());
		if (delFlag) {
			this.PostJs(String.format(
									"alert('删除成功！');location.href='%1$s/plan/protype_Show.do';",
									request.getContextPath()));
		} else {
			this.PostJs(String.format(
					"alert('删除失败！请重试');location.href='%1$s/plan/protype_Show.do';",
					request.getContextPath()));
		}
	}
	
	/**
	 * 设置职位查询的分页地址
	 */
	private void setUrl() {
		StringBuilder url = new StringBuilder();
		url.append(String.format(
				"location.href='%1$s/plan/protype_Show.do';",request.getContextPath()));
		//url.append(String.format("?department.departmentCode=%1$s", department.getDepartmentCode()));
		page.setPath(url.toString());
	}
	
	/**
	 * 获取信息列表
	 * @return
	 */
	public String qry() {
		// 设置分页地址
		setUrl();
		
		// 获取点击的层次信息
		ProTypeDbService db = new ProTypeDbService();
		
		// 查询对应层次下的所有职位列表
		this.protypeList = db.qry(protype, page);
		
		// 加载权限列表
				
		return "Show";
	}
	
	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public ProType getProtype() {
		return protype;
	}

	public void setProtype(ProType protype) {
		this.protype = protype;
	}

	public List<ProType> getProtypeList() {
		return protypeList;
	}

	public void setProtypeList(List<ProType> protypeList) {
		this.protypeList = protypeList;
	}

	public List<PlanLevel> getPlanlevelList() {
		return planlevelList;
	}

	public void setPlanlevelList(List<PlanLevel> planlevelList) {
		this.planlevelList = planlevelList;
	}

}
