/**
 * Copyright(c) 2012 西安云海信息技术有限责任公司 All Rights Reserved<br>
 * @Title: com.sincinfo.zxks.core.sys.LoginAction.java<br>
 * @Description: 报考主考院校管理<br>
 * <br>
 * @author yuansh<br>
 * @date 2012-01-26 15:26
 * @version V1.0   
 */
package com.sincinfo.zxks.core.plan;
import java.util.List;
import com.sincinfo.zxks.bean.BaseAcademy;
import com.sincinfo.zxks.bean.BasePro;
import com.sincinfo.zxks.bean.BaseCity;
import com.sincinfo.zxks.common.action.WebActionSupport;
import com.sincinfo.zxks.common.util.Page;
import com.sincinfo.zxks.zxksdbs.BaseAcademyDbService;
import com.sincinfo.zxks.zxksdbs.BaseProDbService;


/**
 * @ClassName: LevelAction
 * @Description: 主考院校设置 <br>
 *               <br>
 * @author yuansh
 * @date   2012-01-26 15:45<br>
 * 
 */
public class AcademyAction extends WebActionSupport {
	/**
	 * @Fields serialVersionUID :
	 */
	private static final long serialVersionUID = -6217769699627471779L;

	//主考院校分页
	private Page page = new Page();
	
	private BaseAcademy baseacademy;
	
	//扩展：查询专业
	private BasePro basepro;

	//用来传递查询结果
	private List<BaseAcademy> baseacademyList;
	
	private List<BasePro> baseproList;
	
	private List<BaseCity> basecityList;
	

	/**
	 * 初始化
	 */
	private void init() {
		if (baseacademy == null) {
			baseacademy = new BaseAcademy();
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
		url.append(String.format("%1$s/plan/baseacademy_Show.do",request.getContextPath()));
		//url.append(String.format("?department.departmentGrade=%1$s", department.getDepartmentGrade()));
		page.setPath(url.toString());
	}
	
	/**
	 * 初始化专业分页查询地址
	 */	
	private void initPage1Url() {
		StringBuilder url = new StringBuilder();
		url.append(String.format("%1$s/plan/baseacademy_SelPro.do",request.getContextPath()));
		//url.append(String.format("?department.departmentGrade=%1$s", department.getDepartmentGrade()));
		page.setPath(url.toString());
	}	

	/**
	 * 初始化查询条件的列表（根据用户级别）
	 */
	private void initSelect() {
		BaseAcademyDbService DB=new BaseAcademyDbService();
		this.basecityList=DB.qryBaseCityClasses();

	}

	/**
	 * 初始化查询条件的列表（根据用户级别）
	 */
	private void initSctForOpert() {
		BaseAcademyDbService DB=new BaseAcademyDbService();
		this.basecityList=DB.qryBaseCityClasses();
	}

	/**
	 * 
	 * @return
	 */
	public String Show() {
		init();
		BaseAcademyDbService db = new BaseAcademyDbService();
		this.baseacademyList = db.qry(null, page);
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
		
		BaseAcademyDbService db = new BaseAcademyDbService();
		boolean addFlag = db.save(baseacademy, 0);
		if (addFlag) {
			this.PostJs(String.format(
									"alert('添加成功！');location.href='%1$s/plan/baseacademy_Show.do';",
									request.getContextPath()));
		} else {
			this.PostJs(String.format(
					"alert('添加失败！');location.href='%1$s/plan/baseacademy_Show.do';",
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
		BaseAcademyDbService db = new BaseAcademyDbService();
		this.baseacademy = db.qry(baseacademy.getAcademyCode());
		return "Edit";
	}

	/**
	 * 保存修改内容
	 */
	public void Edit() {
		BaseAcademyDbService db = new BaseAcademyDbService();
		boolean editFlag = db.save(baseacademy,1);
		if (editFlag) {
			this.PostJs(String.format(
									"alert('修改成功！');location.href='%1$s/plan/baseacademy_Show.do';",
									request.getContextPath()));
		} else {
			this.PostJs(String.format(
					"alert('修改失败！');location.href='%1$s/plan/baseacademy_Show.do';",
					request.getContextPath()));
		}
	}
	
	/**
	 * 进入专业设置页面
	 * 
	 * @return
	 */
	public String ProsetPre(){
		initPage1Url();
		BaseAcademyDbService db = new BaseAcademyDbService();
		this.baseacademy = db.qry(baseacademy.getAcademyCode());
		this.baseproList = db.qryPro(baseacademy,page);
		return "Proset";
	}

	/**
	 * 保存修改内容
	 */
	public void Proset(){
		BaseAcademyDbService db = new BaseAcademyDbService();
		boolean editFlag = db.save(baseacademy,1);
		if (editFlag) {
			this.PostJs(String.format(
									"alert('修改成功！');location.href='%1$s/plan/baseacademy_Show.do';",
									request.getContextPath()));
		} else {
			this.PostJs("alert('修改失败！请重试。');location.back();");
		}
	}	
	
	/**
	 * 进入选择专业页面
	 * 
	 * @return
	 */
	public String SelPro(){
		initPage1Url();
		BaseProDbService db = new BaseProDbService();
		this.baseproList = db.qry(null, page);
		return "SelPro";
	}
	
	/**
	 * 查询专业
	 * 
	 * @return
	 */
	public String qryPro(){
		initPage1Url();
		BaseProDbService db = new BaseProDbService();
		this.baseproList = db.qry(basepro, page);
		return "SelPro";
	}
	
	/**
	 * 保存添加
	 */
	public void AddPro() {	
		BaseAcademyDbService db = new BaseAcademyDbService();
		boolean addFlag = db.addPro(baseacademy.getAcademyCode(),baseacademy.getProCode());
		if (addFlag) {
			this.PostJs(String.format(
									"alert('添加成功！');location.href='%1$s/plan/baseacademy_ProsetPre.do?baseacademy.academyCode="+baseacademy.getAcademyCode()+"';",
									request.getContextPath()));
		} else {
			this.PostJs(String.format(
					"alert('添加失败！');location.href='%1$s/plan/baseacademy_ProsetPre.do?baseacademy.academyCode=${baseacademy.academyCode}';",
					request.getContextPath()));
		}
	}	

	/**
	 * 删除主考院校
	 */
	public void Del() {
		BaseAcademyDbService db = new BaseAcademyDbService();
		String academyCode=baseacademy.getAcademyCode();
		boolean delFlag = db.delPro(baseacademy.getProCode());
		if (delFlag) {
			this.PostJs(String.format(
					"alert('删除成功！');location.href='%1$s/plan/baseacademy_ProsetPre.do?baseacademy.academyCode="+academyCode+"';",
					request.getContextPath()));
		} else {
			this.PostJs(String.format(
					"alert('删除失败！');location.href='%1$s/plan/baseacademy_ProsetPre.do?baseacademy.academyCode="+academyCode+"';",
					request.getContextPath()));
		}
	}
	
	/**
	 * 设置职位查询的分页地址
	 */
	private void setUrl() {
		StringBuilder url = new StringBuilder();
		url.append(String.format(
				"location.href='%1$s/plan/baseacademy_Show.do';",request.getContextPath()));
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
		
		// 获取点击的主考院校信息
		BaseAcademyDbService db = new BaseAcademyDbService();
		
		// 查询对应主考院校下的所有职位列表
		this.baseacademyList = db.qry(baseacademy, page);
		
		// 加载权限列表
				
		return "Show";
	}
	
	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public BaseAcademy getBaseacademy() {
		return baseacademy;
	}

	public void setBaseacademy(BaseAcademy baseacademy) {
		this.baseacademy = baseacademy;
	}

	public List<BaseAcademy> getBaseacademyList() {
		return baseacademyList;
	}

	public void setBaseacademyList(List<BaseAcademy> baseacademyList) {
		this.baseacademyList = baseacademyList;
	}
	public List<BaseCity> getBasecityList() {
		return basecityList;
	}

   public List<BasePro> getBaseproList() {
		return baseproList;
	}
   
   public void setBasecityList(List<BaseCity> basecityList) {
		this.basecityList = basecityList;
	}

    public void setBaseproList(List<BasePro> baseproList) {
		this.baseproList = baseproList;
	}

	public BasePro getBasepro() {
		return basepro;
	}

	public void setBasepro(BasePro basepro) {
		this.basepro = basepro;
	}
    
}
