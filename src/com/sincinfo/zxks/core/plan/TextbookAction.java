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
import com.sincinfo.zxks.bean.Textbook;
import com.sincinfo.zxks.common.action.WebActionSupport;
import com.sincinfo.zxks.common.util.Page;
import com.sincinfo.zxks.zxksdbs.TextbookDbService;


/**
 * @ClassName: LevelAction
 * @Description: 教材设置 <br>
 *               <br>
 * @author yuansh
 * @date   2012-01-26 15:45<br>
 * 
 */
public class TextbookAction extends WebActionSupport {
	/**
	 * @Fields serialVersionUID :
	 */
	private static final long serialVersionUID = -6217769699627471779L;

	private Page page = new Page();
	
	//层次
	private Textbook textbook;

	//用来传递查询结果
	private List<Textbook> textbookList;

	/**
	 * 初始化
	 */
	private void init() {
		if (textbook == null) {
			textbook = new Textbook();
			//department.setDepartmentGrade(getUser().getUserRole());
		}
		//initPageUrl();
		initSelect();
		initPageUrl2();
	}

	/**
	 * 初始化分页查询地址
	 */
//	private void initPageUrl() {
//		StringBuilder url = new StringBuilder();
//		url.append(String.format(
//		"location.href='%1$s/plan/textbook_Show.do';",request.getContextPath()));
//		//url.append(String.format("?department.departmentGrade=%1$s", department.getDepartmentGrade()));
//		page.setPath(url.toString());
//	}
	
	
	/**
	 * 初始化分页查询地址2
	 */
	private void initPageUrl2() {
		StringBuilder url = new StringBuilder();
		url.append(String.format("%1$s/plan/textbook_Show.do",request.getContextPath()));
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
	 * 查询列表
	 * 
	 * @return
	 */
	public String Show() {
		init();
		TextbookDbService db = new TextbookDbService();
		this.textbookList = db.qry(null, page);
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
	public void Add(){
		TextbookDbService db = new TextbookDbService();
		if(!db.checkSyllabusCode(textbook.getSyllabusCode())){
			this.GoBack("您输入的“课程代码”不存在！");
			return;
		}
		if(db.checkTextbookCode(textbook.getTextbookCode())){
			this.GoBack("您输入的“教材代码”已存在！");
			return;
		}
		boolean addFlag = db.save(textbook, 0);
		if (addFlag) {
			this.PostJs(String.format(
									"alert('添加成功！');location.href='%1$s/plan/textbook_Show.do';",
									request.getContextPath()));
		} else {
			this.PostJs(String.format(
					"alert('添加失败！');location.href='%1$s/plan/textbook_Show.do';",
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
		TextbookDbService db = new TextbookDbService();
		this.textbook = db.qry(textbook.getTextbookCode());
		return "Edit";
	}

	/**
	 * 保存修改内容
	 */
	public void Edit() {
		TextbookDbService db = new TextbookDbService();
		if(!db.checkSyllabusCode(textbook.getSyllabusCode())){
			this.GoBack("您输入的“课程代码”不存在！");
			return;
		}
		boolean editFlag = db.save(textbook,1);
		if (editFlag) {
			this.PostJs(String.format(
									"alert('修改成功！');location.href='%1$s/plan/textbook_Show.do';",
									request.getContextPath()));
		} else {
			this.PostJs(String.format(
					"alert('修改失败！');location.href='%1$s/plan/textbook_Show.do';",
					request.getContextPath()));
		}
	}

	/**
	 * 删除层次
	 */
	public void Del() {
		TextbookDbService db = new TextbookDbService();
		boolean delFlag = db.del(textbook.getTextbookCode());
		if (delFlag) {
			this.PostJs(String.format(
									"alert('删除成功！');location.href='%1$s/plan/textbook_Show.do';",
									request.getContextPath()));
		} else {
			this.PostJs(String.format(
					"alert('删除失败！');location.href='%1$s/plan/textbook_Show.do';",
					request.getContextPath()));
		}
	}
	
	/**
	 * 删除，实际为禁用
	 */
	public void isUseDel(){
		TextbookDbService db = new TextbookDbService();
		boolean delFlag = db.isUseDel(textbook.getTextbookCode());
		if (delFlag) {
			this.PostJs(String.format(
									"alert('删除成功！');location.href='%1$s/plan/textbook_Show.do';",
									request.getContextPath()));
		} else {
			this.PostJs(String.format(
					"alert('删除失败！');location.href='%1$s/plan/textbook_Show.do';",
					request.getContextPath()));
		}
	}
	
	/**
	 * 设置职位查询的分页地址
	 */
	private void setUrl() {
		StringBuilder url = new StringBuilder();
		url.append(String.format(
				"location.href='%1$s/plan/textbook_Show.do';",request.getContextPath()));
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
		TextbookDbService db = new TextbookDbService();
		this.textbookList = db.qry(textbook, page);
		return "Show";
	}
	
	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public Textbook getTextbook() {
		return textbook;
	}

	public void setTextbook(Textbook textbook) {
		this.textbook = textbook;
	}

	public List<Textbook> getTextbookList() {
		return textbookList;
	}

	public void setTextbookList(List<Textbook> textbookList) {
		this.textbookList = textbookList;
	}
}