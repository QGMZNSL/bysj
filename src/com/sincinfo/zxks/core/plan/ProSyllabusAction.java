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

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.sincinfo.zxks.bean.BaseAcademy;
import com.sincinfo.zxks.bean.BaseGraduateCondition;
import com.sincinfo.zxks.bean.BasePro;
import com.sincinfo.zxks.bean.BaseProSyllabus;
import com.sincinfo.zxks.bean.GraduateCondition;
import com.sincinfo.zxks.bean.PlanLevel;
import com.sincinfo.zxks.bean.ProSeq;
import com.sincinfo.zxks.bean.ProSyllabus;
import com.sincinfo.zxks.bean.ProType;
import com.sincinfo.zxks.bean.SingleEntity;
import com.sincinfo.zxks.bean.Syllabus;
import com.sincinfo.zxks.bean.Textbook;
import com.sincinfo.zxks.common.action.WebActionSupport;
import com.sincinfo.zxks.common.util.Page;
import com.sincinfo.zxks.common.util.StringTool;
import com.sincinfo.zxks.zxksdbs.BaseProDbService;
import com.sincinfo.zxks.zxksdbs.GraduateConditionDbService;
import com.sincinfo.zxks.zxksdbs.ProSyllabusDbService;
import com.sincinfo.zxks.zxksdbs.SyllabusDbService;
import com.sincinfo.zxks.zxksdbs.TextbookDbService;

/**
 * @ClassName: LevelAction
 * @Description: 专业课程关系设置 <br>
 * <br>
 * @author yuansh
 * @date 2012-02-05 15:45<br>
 * 
 */
public class ProSyllabusAction extends WebActionSupport {
	/**
	 * @Fields serialVersionUID :
	 */
	private static final long serialVersionUID = -6217769699627471779L;
	private Page page = new Page();
	// 专业
	private BasePro basepro;
	// 专业
	private ProSyllabus proSyllabus;
	// 用来传递查询结果
	private List<BasePro> baseproList;
	// 专业类型列表
	private List<ProType> protypelList;
	// 专业分类列表
	private List<ProSeq> proseqList;
	// 专业层次列表
	private List<PlanLevel> planlevelList;
	// 扩展：课程列表
	private List<ProSyllabus> proSyllabusList;
	// 课程
	private Syllabus syllabus;
	// 选择课程用
	private List<Syllabus> syllabusList;
	// 教材
	private Textbook textBook;
	// 用来传递教材表
	private List<Textbook> textbookList;
	// 出版时间
	ArrayList<SingleEntity> publistTimes;
	private List<BaseProSyllabus> proSyllList; // 专业课程集合
	private BaseGraduateCondition graduateCond; // 专业毕业信息
	private List<BaseAcademy> academyList; // 主考院校集合

	// 毕业条件
	private GraduateCondition graduateCondition;

	public ArrayList<SingleEntity> getPublistTimes() {
		return publistTimes;
	}

	public void setPublistTimes(ArrayList<SingleEntity> publistTimes) {
		this.publistTimes = publistTimes;
	}

	public ArrayList<SingleEntity> getPublists() {
		return publists;
	}

	public void setPublists(ArrayList<SingleEntity> publists) {
		this.publists = publists;
	}

	// 出版社
	ArrayList<SingleEntity> publists;

	/**
	 * 初始化
	 */
	private void init() {
		if (basepro == null) {
			basepro = new BasePro();
			// department.setDepartmentGrade(getUser().getUserRole());
		}
		initPageUrl();
		initSelect();
	}

	/**
	 * 初始化分页查询地址
	 */
	private void initPageUrl() {
		StringBuilder url = new StringBuilder();
		url.append(String.format("%1$s/plan/proSyllabus_Show.do",
				request.getContextPath()));
		// url.append(String.format("?department.departmentGrade=%1$s",
		// department.getDepartmentGrade()));
		page.setPath(url.toString());
	}

	/**
	 * 初始化查询条件的列表（根据用户级别）
	 */
	private void initSelect() {
		BaseProDbService db = new BaseProDbService();
		this.protypelList = db.qryProTypeClasses();
		this.proseqList = db.qryProSeqClasses();
		this.planlevelList = db.qryPlanLevelClasses();
	}

	/**
	 * 初始化教材列表中的时间与出版社
	 */
	private void initSelect1() {
		// SyllabusDbService db = new SyllabusDbService();
		// this.publistTimes=db.getPublishTimeList();
		// this.publists=db.getPublishList();
	}

	/**
	 * 初始化查询条件的列表（根据用户级别）
	 */
	private void initSctForOpert() {
		BaseProDbService db = new BaseProDbService();
		this.protypelList = db.qryProTypeClasses();
		this.proseqList = db.qryProSeqClasses();
		this.planlevelList = db.qryPlanLevelClasses();
	}

	/**
	 * 根据条件查询层次列表
	 * 
	 * @return
	 */
	public String Show() {
		init();
		BaseProDbService db = new BaseProDbService();
		basepro.setIsUse("1");
		this.baseproList = db.qry(basepro, page);
		return "Show";
	}

	/***
	 * 按照专业弹出子窗口 zhangjb 2012-09-28
	 * ***/

	public String viewSyllabus() {
		BaseProDbService db = new BaseProDbService();
		basepro = db.qryWindows(basepro.getProCode());
		proSyllabusList = (List<ProSyllabus>) db.qryProSyllabus(basepro, page);
		return "syllView";
	}

	/**
	 * 查询专业详细信息
	 * 
	 * @return
	 */
	public String findProDetail() {
		// 查询课程列表
		BaseProDbService db = new BaseProDbService();
		proSyllList = db.findProSyllabusList(basepro.getProCode());
		// 查询专业毕业信息
		graduateCond = db.findGraduateCond(basepro.getProCode());
		// 查询主考院校信息
		academyList = db.findAcademyList(basepro.getProCode());
		return "syllView";
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
	public void save() {
		ProSyllabusDbService db = new ProSyllabusDbService();
		if (StringTool.isEmpty(proSyllabus.getSubstituteCode())) {
			if (StringTool.isEmpty(proSyllabus.getSyllabusCode())) {
				this.GoBack("请先选择课程！");
			} else {
				boolean addFlag = db.save(proSyllabus, 0);
				if (addFlag) {
					this.PostJs(String
							.format("alert('添加成功！');location.href='%1$s/plan/proSyllabus_getProSList.do?basepro.proCode="
									+ proSyllabus.getProCode() + "';",
									request.getContextPath()));
				} else {
					this.PostJs(String
							.format("alert('添加失败！');location.href='%1$s/plan/proSyllabus_getProSList.do?basepro.proCode="
									+ proSyllabus.getProCode() + "';",
									request.getContextPath()));
				}
			}
		} else {
			boolean editFlag = db.save(proSyllabus, 1);
			if (editFlag) {
				this.PostJs(String
						.format("alert('修改成功！');location.href='%1$s/plan/proSyllabus_getProSList.do?basepro.proCode="
								+ proSyllabus.getProCode() + "';",
								request.getContextPath()));
			} else {
				this.PostJs(String
						.format("alert('修改失败！');location.href='%1$s/plan/proSyllabus_getProSList.do?basepro.proCode="
								+ proSyllabus.getProCode() + "';",
								request.getContextPath()));
			}
		}
	}

	/**
	 * 添加分组中课程
	 */
	public void AddGrpSyllabus() {
		ProSyllabusDbService db = new ProSyllabusDbService();
		boolean addFlag = db.save(proSyllabus, 0);
		if (addFlag) {
			this.PostJs(String
					.format("alert('添加成功！');location.href='%1$s/plan/proSyllabus_Show.do';",
							request.getContextPath()));
		} else {
			this.PostJs(String
					.format("alert('添加失败！');location.href='%1$s/plan/proSyllabus_Show.do';",
							request.getContextPath()));
		}
	}

	/**
	 * 添加群组中分组
	 */
	public void AddGroupGrp() {
		ProSyllabusDbService db = new ProSyllabusDbService();
		boolean addFlag = db.save(proSyllabus, 0);
		if (addFlag) {
			this.PostJs(String
					.format("alert('添加成功！');location.href='%1$s/plan/proSyllabus_Show.do';",
							request.getContextPath()));
		} else {
			this.PostJs(String
					.format("alert('添加失败！');location.href='%1$s/plan/proSyllabus_Show.do';",
							request.getContextPath()));
		}
	}

	/**
	 * 更新分组中课程
	 */
	public void EditGrpSyllabus() {
		ProSyllabusDbService db = new ProSyllabusDbService();
		boolean addFlag = db.saveGrpSyllabus(proSyllabus);
		if (addFlag) {
			this.PostJs(String
					.format("alert('添加成功！');location.href='%1$s/plan/proSyllabus_Show.do';",
							request.getContextPath()));
		} else {
			this.PostJs(String
					.format("alert('添加失败！');location.href='%1$s/plan/proSyllabus_Show.do';",
							request.getContextPath()));
		}
	}

	/**
	 * 进入修改页面
	 * 
	 * @return
	 */
	public void edit() {
		ProSyllabusDbService db = new ProSyllabusDbService();
		proSyllabus = db.getProSyllabus(proSyllabus.getSubstituteCode(),
				proSyllabus.getProCode());
		String msg = "new Array('" + proSyllabus.getSubstituteCode() + "','"
				+ proSyllabus.getProCode() + "','"
				+ proSyllabus.getSyllabusCode() + "','"
				+ proSyllabus.getSyllabusName() + "','"
				+ proSyllabus.getSyllabusCredit() + "','"
				+ proSyllabus.getTextbookCode() + "','"
				+ proSyllabus.getTextbookName() + "','"
				+ proSyllabus.getTextbookUnitary() + "','"
				+ proSyllabus.getExamUnitary() + "','"
				+ proSyllabus.getSyllabusRemark() + "','"
				+ proSyllabus.getCertSyllabus() + "')";
		ajaxReturn(msg);
	}

	/**
	 * 获取专业课程列表
	 * 
	 * @return
	 */
	public String getProSList() {
		ProSyllabusDbService db = new ProSyllabusDbService();
		basepro = db.getBasePro(basepro.getProCode());
		proSyllabusList = db.getProSyllabus(basepro.getProCode());
		return "syllabusSet";
	}

	/**
	 * 获取专业课程列表
	 * 
	 * @return
	 */
	public String getPSList() {
		ProSyllabusDbService db = new ProSyllabusDbService();
		basepro = db.getBasePro(proSyllabus.getProCode());
		proSyllabusList = db.getProSyllabus(proSyllabus.getProCode());
		return "syllabusOrderby";
	}

	/**
	 * 获取专业课程列表
	 * 
	 * @return
	 */
	public void editOrderby() {
		ProSyllabusDbService db = new ProSyllabusDbService();
		boolean falg = db.syllabausOrder(proSyllabus.getSubstituteCode(),
				proSyllabus.getProCode());
		if (falg) {
			this.PostJs(String
					.format("alert('保存成功！');location.href='%1$s/plan/proSyllabus_getProSList.do?basepro.proCode="
							+ proSyllabus.getProCode() + "';",
							request.getContextPath()));
		} else {
			this.PostJs(String
					.format("alert('保存失败！');location.href='%1$s/plan/proSyllabus_getProSList.do?basepro.proCode="
							+ proSyllabus.getProCode() + "';",
							request.getContextPath()));
		}
	}

	/**
	 * 保存修改内容
	 */
	public void editSave() {
		ProSyllabusDbService db = new ProSyllabusDbService();
		boolean editFlag = db.save(proSyllabus, 1);
		if (editFlag) {
			this.PostJs(String
					.format("alert('修改成功！');location.href='%1$s/plan/basepro_Show.do';",
							request.getContextPath()));
		} else {
			this.PostJs(String
					.format("alert('修改失败！');location.href='%1$s/plan/basepro_Show.do';",
							request.getContextPath()));
		}
	}

	/**
	 * 删除专业中的课程信息
	 */
	public void Del() {
		ProSyllabusDbService db = new ProSyllabusDbService();
		boolean delFlag = db.del(proSyllabus.getSubstituteCode());
		if (delFlag) {
			this.PostJs(String
					.format("alert('删除成功！');location.href='%1$s/plan/proSyllabus_getProSList.do?basepro.proCode="
							+ proSyllabus.getProCode() + "';",
							request.getContextPath()));
		} else {
			this.PostJs(String
					.format("alert('删除失败！请重试。');location.href='%1$s/plan/proSyllabus_getProSList.do?basepro.proCode="
							+ proSyllabus.getProCode() + "';",
							request.getContextPath()));
		}
	}

	/**
	 * 清除专业中的课程及分组信息
	 */
	public void Clear() {
		ProSyllabusDbService db = new ProSyllabusDbService();
		boolean delFlag = db.Clear(proSyllabus.getProCode());
		if (delFlag) {
			this.PostJs(String
					.format("alert('清除成功！');location.href='%1$s/plan/proSyllabus_Show.do';",
							request.getContextPath()));
		} else {
			this.PostJs(String
					.format("alert('清除失败！请重试。');location.href='%1$s/plan/proSyllabus_Show.do';",
							request.getContextPath()));
		}
	}

	/**
	 * 设置分页地址
	 */
	private void setUrl() {
		StringBuilder url = new StringBuilder();
		url.append(String.format("%1$s/plan/basepro_qry.do;",
				request.getContextPath()));
		// url.append(String.format("?department.departmentCode=%1$s",
		// department.getDepartmentCode()));
		page.setPath(url.toString());
	}

	/**
	 * 设置分页地址
	 */
	private void setUrl1() {
		StringBuilder url = new StringBuilder();
		url.append(String.format("%1$s/plan/proSyllabus_qrySyllabus.do;",
				request.getContextPath()));
		// url.append(String.format("?department.departmentCode=%1$s",
		// department.getDepartmentCode()));
		page.setPath(url.toString());
	}

	/**
	 * 获取信息列表
	 * 
	 * @return
	 */
	public String qry() {
		// 设置分页地址
		setUrl();

		// 初始化查询条件列表
		initSctForOpert();

		// 获取点击的层次信息
		BaseProDbService db = new BaseProDbService();

		// 查询对应层次下的所有职位列表
		this.baseproList = db.qry(basepro, page);

		// 加载权限列表
		return "Show";
	}

	/**
	 * 进入课程设置页面 yuansh
	 * 
	 * @return
	 */
	public String syllabusSetPre() {
		initPageUrl();
		BaseProDbService db = new BaseProDbService();
		this.basepro = db.qry(basepro.getProCode());
		BaseProDbService db1 = new BaseProDbService();
		this.proSyllabusList = db1.qryProSyllabus(basepro, page);//
		return "syllabusSet";
	}

	/**
	 * 获取信息列表
	 * 
	 * @return
	 */
	public String qrySyllabus() {
		// 设置分页地址
		setUrl1();

		// 获取点击的层次信息
		SyllabusDbService db = new SyllabusDbService();

		// 查询对应层次下的所有职位列表
		this.syllabusList = db.qry(syllabus, page);

		// 加载权限列表
		return "syllabusSel";
	}

	/**
	 * 获取信息列表
	 * 
	 * @return
	 */
	public String selProSyllabus() {
		// 设置分页地址
		page.setPath(String.format("%1$s/plan/proSyllabus_selProSyllabus.do;",
				request.getContextPath()));

		// 获取点击的层次信息
		SyllabusDbService db = new SyllabusDbService();

		// 查询对应层次下的所有职位列表
		this.syllabusList = db.qry(syllabus, page);

		// 加载权限列表
		return "selProSyllabus";
	}

	/**
	 * 跳转到备注修改页面
	 * 
	 * @return
	 */
	public String graCondEditPre() {

		GraduateConditionDbService db = new GraduateConditionDbService();
		String proCode = graduateCondition.getProCode();
		this.graduateCondition = db.qry(graduateCondition.getProCode());
		if (graduateCondition == null
				|| StringTool.isEmpty(graduateCondition
						.getGraduateConditionCredit())
				|| graduateCondition.getGraduateConditionCredit().length() == 0) {
			graduateCondition = new GraduateCondition();
			graduateCondition.setSaveType("0");
			graduateCondition.setProCode(proCode);
		} else {
			graduateCondition.setSaveType("1");
		}

		return "graCond";
	}

	/**
	 * 修改备注
	 * 
	 * @return
	 */
	public String graCondEdit() {
		GraduateConditionDbService db = new GraduateConditionDbService();
		boolean editFlag = db.save(graduateCondition,
				Integer.parseInt(graduateCondition.getSaveType()));
		if (editFlag) {
			this.Alert("修改成功。");
		} else {
			this.Alert("修改失败。");
		}
		this.PostJs(String
				.format("location.href='%1$s/plan/proSyllabus_graCondEditPre.do?graduateCondition.proCode="
						+ graduateCondition.getProCode() + "';",
						request.getContextPath()));
		return null;
	}

	/**
	 * 选择教材查询
	 * 
	 * @return
	 */
	public String qryTBook() {
		// 设置分页地址
		setUrl();
		TextbookDbService db = new TextbookDbService();
		this.textbookList = db.qry(textBook, page);
		// 初始化查询列表
		initSelect1();
		return "Proset";
	}

	/**
	 * ajax返回页面
	 * 
	 * @param ret
	 *            返回字符串
	 */
	private void ajaxReturn(String ret) {
		PrintWriter pw = null;
		response.setContentType("text/html;charset=utf-8");
		try {
			pw = response.getWriter();
			pw.write(ret);
			pw.flush();
		} catch (IOException e) {
		} finally {
			if (pw != null)
				pw.close();
		}
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public BasePro getBasepro() {
		return basepro;
	}

	public void setBasepro(BasePro basepro) {
		this.basepro = basepro;
	}

	public List<BasePro> getBaseproList() {
		return baseproList;
	}

	public void setBaseproList(List<BasePro> baseproList) {
		this.baseproList = baseproList;
	}

	public List<ProType> getProtypelList() {
		return protypelList;
	}

	public void setProtypelList(List<ProType> protypelList) {
		this.protypelList = protypelList;
	}

	public List<ProSeq> getProseqList() {
		return proseqList;
	}

	public void setProseqList(List<ProSeq> proseqList) {
		this.proseqList = proseqList;
	}

	public List<PlanLevel> getPlanlevelList() {
		return planlevelList;
	}

	public void setPlanlevelList(List<PlanLevel> planlevelList) {
		this.planlevelList = planlevelList;
	}

	public List<ProSyllabus> getProSyllabusList() {
		return proSyllabusList;
	}

	public void setProSyllabusList(List<ProSyllabus> proSyllabusList) {
		this.proSyllabusList = proSyllabusList;
	}

	public Syllabus getSyllabus() {
		return syllabus;
	}

	public void setSyllabus(Syllabus syllabus) {
		this.syllabus = syllabus;
	}

	public List<Syllabus> getSyllabusList() {
		return syllabusList;
	}

	public void setSyllabusList(List<Syllabus> syllabusList) {
		this.syllabusList = syllabusList;
	}

	public Textbook getTextBook() {
		return textBook;
	}

	public void setTextBook(Textbook textBook) {
		this.textBook = textBook;
	}

	public List<Textbook> getTextbookList() {
		return textbookList;
	}

	public void setTextbookList(List<Textbook> textbookList) {
		this.textbookList = textbookList;
	}

	public ProSyllabus getProSyllabus() {
		return proSyllabus;
	}

	public void setProSyllabus(ProSyllabus proSyllabus) {
		this.proSyllabus = proSyllabus;
	}

	public List<BaseProSyllabus> getProSyllList() {
		return proSyllList;
	}

	public void setProSyllList(List<BaseProSyllabus> proSyllList) {
		this.proSyllList = proSyllList;
	}

	public BaseGraduateCondition getGraduateCond() {
		return graduateCond;
	}

	public void setGraduateCond(BaseGraduateCondition graduateCond) {
		this.graduateCond = graduateCond;
	}

	public List<BaseAcademy> getAcademyList() {
		return academyList;
	}

	public void setAcademyList(List<BaseAcademy> academyList) {
		this.academyList = academyList;
	}

	public GraduateCondition getGraduateCondition() {
		return graduateCondition;
	}

	public void setGraduateCondition(GraduateCondition graduateCondition) {
		this.graduateCondition = graduateCondition;
	}

}