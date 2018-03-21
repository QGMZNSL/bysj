/**
 * Copyright(c) 2012 西安云海信息技术有限责任公司 All Rights Reserved<br>
 * @Title: com.sincinfo.zxks.core.day.datapreserve.PositionAction.java<br>
 * @Description: 职位部门管理 <br>
 * <br>
 * @author litian<br>
 * @date Jan 20, 2012 8:41:09 AM 
 * @version V1.0   
 */
package com.sincinfo.zxks.core.day.datapreserve;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.sincinfo.zxks.bean.BaseDepartment;
import com.sincinfo.zxks.bean.BasePosition;
import com.sincinfo.zxks.bean.BaseUser;
import com.sincinfo.zxks.bean.SingleEntity;
import com.sincinfo.zxks.common.Constants;
import com.sincinfo.zxks.common.action.WebActionSupport;
import com.sincinfo.zxks.common.log.OperatLog;
import com.sincinfo.zxks.common.util.Log;
import com.sincinfo.zxks.common.util.Page;
import com.sincinfo.zxks.core.day.datapreserve.busi.ModuleStruct;
import com.sincinfo.zxks.core.day.datapreserve.busi.ModuleTool;
import com.sincinfo.zxks.zxksdbs.DayDbService;

/**
 * @ClassName: PositionAction
 * @Description: 职位部门管理 <br>
 *               <br>
 * @author litian
 * @date Jan 20, 2012 8:41:09 AM<br>
 * 
 */
public class PositionAction extends WebActionSupport {
	/**
	 * @Fields serialVersionUID :
	 */
	private static final long serialVersionUID = -6217769699627471779L;

	private Page page = new Page();

	// 部门级别有1-省级 2-市级
	private List<SingleEntity> grades;

	// 部门
	private BaseDepartment department;

	// 用来传递查询结果
	private List<BaseDepartment> departmentList;

	// 职位列表
	private List<BasePosition> positionList;

	// 模块权限
	private List<ModuleStruct> msList;

	// 用来传递权限码
	private String[] pwrIndexes;

	// 用来保存职位对象
	private BasePosition position;

	// 判断保存是添加操作还是修改操作 0-添加 1-修改
	private String positionSaveType;

	// 部门级别（1省2市）
	private String departGrade;

	/**
	 * 初始化
	 */
	private void init() {
		if (department == null) {
			department = new BaseDepartment();
			department.setDepartmentGrade(getCOperUser().getUserRole());
		}
		initPageUrl();
		initSelect();
	}

	/**
	 * 初始化分页查询地址
	 */
	private void initPageUrl() {
		StringBuilder url = new StringBuilder();
		url.append(String.format("%1$s/day/datapreserve/pstMgr_departments.do",
				request.getContextPath()));
		url.append(String.format("?department.departmentGrade=%1$s", department
				.getDepartmentGrade()));
		page.setPath(url.toString());
	}

	/**
	 * 初始化查询条件的列表（根据用户级别）
	 */
	private void initSelect() {
		grades = new ArrayList<SingleEntity>();
		BaseUser optUser = getCOperUser();
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
	}

	/**
	 * 初始化查询条件的列表（根据用户级别）
	 */
	private void initSctForOpert() {
		grades = new ArrayList<SingleEntity>();
		BaseUser optUser = getCOperUser();
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
	}

	/**
	 * 查询部门列表
	 * 
	 * @return
	 */
	public String departments() {
		init();

		DayDbService dayDb = new DayDbService();
		this.departmentList = dayDb.qryDepartments(department
				.getDepartmentGrade(), page);

		return "departmentsShow";
	}

	/**
	 * 进入添加页面
	 * 
	 * @return
	 */
	public String departmentsAddPre() {
		initSctForOpert();
		return "departmentAdd";
	}

	/**
	 * 保存添加的部门
	 */
	public void departmentsAdd() {
		// 构造操作日志
		OperatLog optLog = this.getOptLog(OperatLog.DB_INSERT, "职位部门管理，保存部门信息");

		// 保存添加的部门
		DayDbService dayDb = new DayDbService();
		boolean addFlag = dayDb.saveDepartment(department, 0, optLog);

		// 判断执行结果
		if (addFlag) {
			this
					.PostJs(String
							.format(
									"alert('添加部门成功！');location.href='%1$s/day/datapreserve/pstMgr_departments.do';",
									request.getContextPath()));
		} else {
			this.PostJs("alert('添加部门失败！请重试。');history.back();");
		}
	}

	/**
	 * 进入修改页面
	 * 
	 * @return
	 */
	public String departmentsEditPre() {
		initSctForOpert();
		DayDbService dayDb = new DayDbService();
		this.department = dayDb.qryDepartment(department.getDepartmentCode());
		return "departmentEdit";
	}

	/**
	 * 保存修改内容
	 */
	public void departmentsEdit() {
		// 构造操作日志
		OperatLog optLog = this.getOptLog(OperatLog.DB_UPDATE, "职位部门管理，保存部门信息");

		// 保存修改的部门
		DayDbService dayDb = new DayDbService();
		boolean editFlag = dayDb.saveDepartment(department, 1, optLog);
		if (editFlag) {
			this
					.PostJs(String
							.format(
									"alert('修改部门成功！');location.href='%1$s/day/datapreserve/pstMgr_departments.do';",
									request.getContextPath()));
		} else {
			this.PostJs("alert('修改部门失败！请重试。');history.back();");
		}
	}

	/**
	 * 删除部门
	 */
	public void departmentsDel() {
		// 构造操作日志
		OperatLog optLog = this.getOptLog(OperatLog.DB_DELETE, "职位部门管理，删除部门信息");

		// 删除部门
		DayDbService dayDb = new DayDbService();
		boolean delFlag = dayDb.delDepartment(department.getDepartmentCode(), optLog);
		if (delFlag) {
			this
					.PostJs(String
							.format(
									"alert('删除部门成功！');location.href='%1$s/day/datapreserve/pstMgr_departments.do';",
									request.getContextPath()));
		} else {
			this.PostJs("alert('删除部门失败！请重试。');history.back();");
		}
	}

	/**
	 * 设置职位查询的分页地址
	 */
	private void setPositionUrl() {
		StringBuilder url = new StringBuilder();
		url.append(String.format(
				"%1$s/day/datapreserve/pstMgr_qryPositions.do", request
						.getContextPath()));
		url.append(String.format("?department.departmentCode=%1$s", department
				.getDepartmentCode()));
		page.setPath(url.toString());
	}

	/**
	 * 获取职位信息列表
	 * 
	 * @return
	 */
	public String qryPositions() {
		// 设置分页地址
		setPositionUrl();

		// 获取点击的部门信息
		DayDbService dayDb = new DayDbService();
		this.department = dayDb.qryDepartment(department.getDepartmentCode());

		// 查询对应部门下的所有职位列表
		this.positionList = dayDb.qryPositions(department.getDepartmentCode(),
				page);

		// 判断是否已设置
		for (BasePosition bp : this.positionList) {
			bp.setPowerSetedCount();
		}

		// 加载权限列表
		ModuleTool mt = new ModuleTool();
		this.msList = mt.moduleTreeInitial(departGrade);

		return "positionsShow";
	}

	/**
	 * 保存
	 */
	public void savePosition() {
		// 构造操作日志
		OperatLog optLog = this.getOptLog("", "职位部门管理，保存职位信息");
		
		// 构造完整的position对象
		this.position.setDefaultPowerArray(pwrLink());
		if ("0".equals(this.positionSaveType)) {
			// 执行添加
			optLog.setLogOptMethod(OperatLog.DB_INSERT);
			addPosition( optLog);
		} else if ("1".equals(this.positionSaveType)) {
			// 执行修改
			optLog.setLogOptMethod(OperatLog.DB_UPDATE);
			editPosition( optLog);
		} else {
			// do nothing
		}

		// 跳转到空白页面进行重定向
		String url = "%1$s/day/datapreserve/pstMgr_positionRedirect.do?department.departmentCode=%2$s&departGrade=%3$s";
		url = String.format(url, request.getContextPath(), this.department
				.getDepartmentCode(), this.departGrade);
		this.PostJs(String.format("location.href='%1$s';", url));
	}

	/**
	 * 保存添加的职位对象
	 */
	private void addPosition( OperatLog optLog) {
		boolean addFlag = false;
		DayDbService dayDb = new DayDbService();
		addFlag = dayDb.savePosition(this.position, 0, optLog);
		String message = addFlag ? "添加职位成功！" : "添加职位失败！";
		this.PostJs(String.format("alert('%1$s');", message));
	}

	/**
	 * 保存修改的职位对象
	 */
	private void editPosition( OperatLog optLog) {
		boolean editFlag = false;
		DayDbService dayDb = new DayDbService();
		editFlag = dayDb.savePosition(this.position, 1, optLog);
		String message = editFlag ? "修改职位成功！" : "修改职位失败！";
		this.PostJs(String.format("alert('%1$s');", message));
	}

	/**
	 * 防止在添加或者修改后刷新页面，跳到一个空白页面，<br>
	 * 这个空白页面防止重复操作数据库，如果用户点击上一步，则会继续跳转到新地址<br>
	 */
	public void positionRedirect() {
		String url = "%1$s/day/datapreserve/pstMgr_qryPositions.do?department.departmentCode=%2$s&departGrade=%3$s";
		url = String.format(url, request.getContextPath(), this.department
				.getDepartmentCode(), this.departGrade);
		try {
			response.sendRedirect(url);
		} catch (IOException e) {
			new Log().error(this.getClass(), "职位保存，response重定向失败！", e);
			this.PostJs(String.format("location.href='%1$s';", url));
		}
	}

	/**
	 * 删除一个职位
	 */
	public void delPosition() {
		// 构造操作日志
		OperatLog optLog = this.getOptLog(OperatLog.DB_DELETE, "职位部门管理，删除职位信息");
		
		// 删除操作
		boolean delFlag = false;
		DayDbService dayDb = new DayDbService();
		delFlag = dayDb.delPosition(this.position, optLog);

		// 提示
		String message = delFlag ? "删除职位成功！" : "删除职位失败！（若该职位已被使用，则无法删除。）";
		this.PostJs(String.format("alert('%1$s');", message));

		// 跳转到空白页面进行重定向
		String url = "%1$s/day/datapreserve/pstMgr_positionRedirect.do?department.departmentCode=%2$s";
		url = String.format(url, request.getContextPath(), this.department
				.getDepartmentCode());
		this.PostJs(String.format("location.href='%1$s';", url));
	}

	/**
	 * 连接权限码
	 * 
	 * @return
	 */
	private String pwrLink() {
		int[] power = new int[ModuleTool.MODULE_MAX_INDEX_COUNT];
		// idx直接表示位数（即下标+1）
		int point = 0;
		int pwrIndex = 0;
		boolean indexesIsNull = this.pwrIndexes == null
				|| this.pwrIndexes.length == 0;
		for (int idx = 1; idx <= power.length; idx++) {
			if (indexesIsNull) {
				power[idx - 1] = 0;
			} else {
				pwrIndex = Integer.parseInt(this.pwrIndexes[point]);
				// 位数相等时，位数-1的值变为1
				if (pwrIndex == idx) {
					power[idx - 1] = 1;
					point++;

					// 保存的权限位数组的指针位达到数组末尾时，结束循环
					if (point == this.pwrIndexes.length)
						break;
				}
			}
		}

		// 连接权限码
		StringBuilder pwrBuf = new StringBuilder();
		for (int p : power)
			pwrBuf.append(p);

		return pwrBuf.toString();
	}

	/*------get/set------*/
	public BaseDepartment getDepartment() {
		return department;
	}

	public void setDepartment(BaseDepartment department) {
		this.department = department;
	}

	public List<SingleEntity> getGrades() {
		return grades;
	}

	public void setGrades(List<SingleEntity> grades) {
		this.grades = grades;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public List<BaseDepartment> getDepartmentList() {
		return departmentList;
	}

	public void setDepartmentList(List<BaseDepartment> departmentList) {
		this.departmentList = departmentList;
	}

	public List<BasePosition> getPositionList() {
		return positionList;
	}

	public void setPositionList(List<BasePosition> positionList) {
		this.positionList = positionList;
	}

	public List<ModuleStruct> getMsList() {
		return msList;
	}

	public void setMsList(List<ModuleStruct> msList) {
		this.msList = msList;
	}

	public String[] getPwrIndexes() {
		return pwrIndexes;
	}

	public void setPwrIndexes(String[] pwrIndexes) {
		this.pwrIndexes = pwrIndexes;
	}

	public BasePosition getPosition() {
		return position;
	}

	public void setPosition(BasePosition position) {
		this.position = position;
	}

	public String getPositionSaveType() {
		return positionSaveType;
	}

	public void setPositionSaveType(String positionSaveType) {
		this.positionSaveType = positionSaveType;
	}

	public String getDepartGrade() {
		return departGrade;
	}

	public void setDepartGrade(String departGrade) {
		this.departGrade = departGrade;
	}

}
