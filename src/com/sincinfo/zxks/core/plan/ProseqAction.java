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
import com.sincinfo.zxks.bean.ProSeq;
import com.sincinfo.zxks.common.action.WebActionSupport;
import com.sincinfo.zxks.common.util.Page;
import com.sincinfo.zxks.zxksdbs.ProseqDbService;


/**
 * @ClassName: ProseqAction
 * @Description: 专业分类设置 <br>
 *                           <br>
 * @author yuansh
 * @date   2012-01-26 15:45<br>
 * 
 */
public class ProseqAction extends WebActionSupport {
	/**
	 * @Fields serialVersionUID :
	 */
	private static final long serialVersionUID = -6217769699627471779L;

	private Page page = new Page();
	
	//专业分类
	private ProSeq proseq;

	//用来传递查询结果
	private List<ProSeq> proseqList;
	
	/**
	 * 初始化
	 */
	private void init() {
		if (proseq == null) {
			proseq = new ProSeq();
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
		url.append(String.format("%1$s/plan/proseq_Show.do",request.getContextPath()));
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
	 * 查询专业分类列表
	 * 
	 * @return
	 */
	public String Show() {
		init();
		ProseqDbService db = new ProseqDbService();
		this.proseqList = db.qry(null, page);
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
		ProseqDbService db = new ProseqDbService();
		boolean addFlag = db.save(proseq, 0);
		if (addFlag) {
			this.PostJs(String.format(
									"alert('添加成功！');location.href='%1$s/plan/proseq_Show.do';",
									request.getContextPath()));
		} else {
			this.PostJs(String.format(
					"alert('添加失败！请重新添加');location.href='%1$s/plan/proseq_Show.do';",
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
		ProseqDbService db = new ProseqDbService();
		this.proseq = db.qry(proseq.getProPartCode());
		return "Edit";
	}

	/**
	 * 保存修改内容
	 */
	public void Edit() {
		ProseqDbService db = new ProseqDbService();
		boolean editFlag = db.save(proseq,1);
		if (editFlag) {
			this.PostJs(String.format(
									"alert('修改成功！');location.href='%1$s/plan/proseq_Show.do';",
									request.getContextPath()));
		} else {
			this.PostJs(String.format(
					"alert('修改失败！');location.href='%1$s/plan/proseq_Show.do';",
					request.getContextPath()));
		}
	}
	
	/**
	 * 删除，实际为禁用
	 */
	public void isUseDel(){
		ProseqDbService db = new ProseqDbService();
		boolean delFlag = db.isUseDel(proseq.getProPartCode());
		if (delFlag) {
			this.PostJs(String.format(
									"alert('删除成功！');location.href='%1$s/plan/proseq_Show.do';",
									request.getContextPath()));
		} else {
			this.PostJs(String.format(
					"alert('删除失败！');location.href='%1$s/plan/proseq_Show.do';",
					request.getContextPath()));
		}
	}

	/**
	 * 删除层次
	 */
	public void Del() {
		ProseqDbService db = new ProseqDbService();
		boolean delFlag = db.del(proseq.getProPartCode());
		if (delFlag) {
			this.PostJs(String.format(
									"alert('删除成功！');location.href='%1$s/plan/proseq_Show.do';",
									request.getContextPath()));
		} else {
			this.PostJs("alert('删除失败！请重试。');location.back();");
		}
	}
	
	/**
	 * 设置职位查询的分页地址
	 */
	private void setUrl() {
		StringBuilder url = new StringBuilder();
		url.append(String.format(
				"location.href='%1$s/plan/proseq_Show.do';",request.getContextPath()));
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
		ProseqDbService db = new ProseqDbService();
		
		// 查询对应层次下的所有职位列表
		this.proseqList = db.qry(proseq, page);
		
		// 加载权限列表
				
		return "Show";
	}
	
	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public ProSeq getProseq() {
		return proseq;
	}

	public void setProseq(ProSeq proseq) {
		this.proseq = proseq;
	}

	public List<ProSeq> getProseqList() {
		return proseqList;
	}

	public void setProseqList(List<ProSeq> proseqList) {
		this.proseqList = proseqList;
	}



}
