/**
 * Copyright(c) 2012 西安云海信息技术有限责任公司 All Rights Reserved<br>
 * @Title: com.sincinfo.zxks.zxksdbs.DayDbService.java<br>
 * @Description: 服务于日常维护下的数据库操作（日常管理 信息发布 数据维护） <br>
 * <br>
 * @author litian<br>
 * @date Jan 20, 2012 8:47:20 AM 
 * @version V1.0   
 */
package com.sincinfo.zxks.zxksdbs;

import java.util.List;

import com.sincinfo.zxks.bean.BaseCity;
import com.sincinfo.zxks.bean.BaseDepartment;
import com.sincinfo.zxks.bean.BasePosition;
import com.sincinfo.zxks.bean.BaseUser;
import com.sincinfo.zxks.bean.SysModule;
import com.sincinfo.zxks.common.db.DbUtil;
import com.sincinfo.zxks.common.log.OperatLog;
import com.sincinfo.zxks.common.log.OperateLogTool;
import com.sincinfo.zxks.common.util.Page;
import com.sincinfo.zxks.common.util.Power;
import com.sincinfo.zxks.common.util.StringTool;
import com.sincinfo.zxks.core.day.datapreserve.busi.ModuleClass;

/**
 * @ClassName: DayDbService
 * @Description: 服务于日常维护下的数据库操作（日常管理 信息发布 数据维护） <br>
 *               <br>
 * @author litian
 * @date Jan 20, 2012 8:47:20 AM<br>
 * 
 */
public class DayDbService {
	private DbUtil dbUtil = null;

	/**
	 * 构造
	 */
	public DayDbService() {
		this.dbUtil = new DbUtil();
	}

	/**
	 * 根据部门级别查询对应级别下的所有部门信息列表，若不加限制则传递“0”
	 * 
	 * @param departmentGrade
	 *            部门级别 （“0”全部 1省2市 4考区）
	 * @param page
	 *            分页对象
	 * @return
	 */
	public List<BaseDepartment> qryDepartments(String departmentGrade, Page page) {
		List<BaseDepartment> departments = null;
		StringBuilder sql = new StringBuilder();
		sql.append("select d.*,");
		sql.append(" (select count(*) from base_position p");
		sql.append(" where p.department_code=d.department_code)");
		sql.append(" as postionCount");
		sql.append(" from base_department d");
		sql.append(String.format(" where d.department_grade %1$s", "0"
				.equals(StringTool.trim(departmentGrade)) ? " <> '0'" : String
				.format(" = '%1$s'", departmentGrade)));
		String sqlCount = String.format("select count(*) from (%1$s)", sql
				.toString());
		sql.append(" order by d.department_grade asc, d.department_code asc");

		// 分页查询
		String sqlPage = page.setPagecount(dbUtil.getNum(sqlCount), sql
				.toString());
		departments = dbUtil.getObjList(sqlPage, BaseDepartment.class);
		return departments;
	}

	/**
	 * 根据编号，查询一个部门
	 * 
	 * @param departmentCode
	 *            部门编号
	 * @return
	 */
	public BaseDepartment qryDepartment(String departmentCode) {
		BaseDepartment department = null;
		String sql = String
				.format(
						"select * from base_department d where d.department_code = '%1$s'",
						departmentCode);
		department = dbUtil.getObject(sql, BaseDepartment.class);
		return department;
	}

	/**
	 * 保存部门对象到数据库
	 * 
	 * @param department
	 *            用户部门
	 * @param type
	 *            类型 0-insert, 1-update
	 * @return boolean
	 */
	public boolean saveDepartment(BaseDepartment department, int type,
			OperatLog optLog) {
		boolean saveFlag = false;
		StringBuilder sql = new StringBuilder();
		switch (type) {
		case 0:
			sql.append("insert into base_department");
			sql.append(" (department_code, department_name, department_grade)");
			sql.append(" values");
			sql.append(String.format(" (SEQ_USER.Nextval, '%1$s', %2$s)",
					department.getDepartmentName(), department
							.getDepartmentGrade()));
			break;
		case 1:
			sql.append("update base_department");
			sql.append(String.format(" set department_name = '%1$s',",
					department.getDepartmentName()));
			sql.append(String.format(" department_grade = '%1$s'", department
					.getDepartmentGrade()));
			sql.append(String.format(" where department_code = '%1$s'",
					department.getDepartmentCode()));
			break;
		default:
			break;
		}
		saveFlag = dbUtil.saveOrUpdate(sql.toString()) == 1;

		// 执行成功，则保存日志
		if (saveFlag) {
			optLog.setLogOptSql(sql.toString());
			OperateLogTool.saveOptLog(optLog);
		}

		return saveFlag;
	}

	/**
	 * 删除用户部门（如果已经设置职位，则必须先清除职位才能删除部门）
	 * 
	 * @param departmentCode
	 * @return
	 */
	public boolean delDepartment(String departmentCode, OperatLog optLog) {
		boolean delFlag = false;
		StringBuilder sql = new StringBuilder();
		sql.append("delete from base_department");
		sql.append(String.format(" where department_code = '%1$s'",
				departmentCode));
		delFlag = dbUtil.saveOrUpdate(sql.toString()) == 1;

		// 执行成功，则保存日志
		if (delFlag) {
			optLog.setLogOptSql(sql.toString());
			OperateLogTool.saveOptLog(optLog);
		}

		return delFlag;
	}

	/**
	 * 查询指定部门下的所有职位
	 * 
	 * @param departmentCode
	 *            部门代码
	 * @return List<BasePosition>
	 */
	public List<BasePosition> qryPositions(String departmentCode, Page page) {
		List<BasePosition> positions = null;
		StringBuilder sql = new StringBuilder();
		sql.append("select *");
		sql.append(" from base_position p");
		sql.append(String.format(" where p.department_code = '%1$s'",
				departmentCode));
		String sqlCount = String.format("select count(*) from (%1$s)", sql
				.toString());
		sql.append(" order by p.position_code asc");

		// 分页查询
		String sqlPage = page.setPagecount(dbUtil.getNum(sqlCount), sql
				.toString());
		positions = dbUtil.getObjList(sqlPage, BasePosition.class);
		return positions;
	}

	/**
	 * 保存一个职位对象
	 * 
	 * @param position
	 *            职位对象
	 * @param saveType
	 *            保存方式 0-添加insert 1-修改update
	 * @param optLog
	 *            日志
	 * @return boolean
	 */
	public boolean savePosition(BasePosition position, int saveType,
			OperatLog optLog) {
		boolean saveFlag = false;
		StringBuilder sql = new StringBuilder();
		if (saveType == 0) {
			sql.append("insert into base_position");
			sql
					.append(" (position_code, position_name, default_power_array, department_code)");
			sql.append(" values");
			sql.append(String.format(
					" (SEQ_USER.nextVal, '%1$s', '%2$s', %3$s)", position
							.getPositionName(),
					position.getDefaultPowerArray(), position
							.getDepartmentCode()));
		} else if (saveType == 1) {
			sql.append("update base_position");
			sql.append(String.format(" set position_name = '%1$s', ", position
					.getPositionName()));
			sql.append(String.format(" default_power_array = '%1$s'", position
					.getDefaultPowerArray()));
			sql.append(String.format(" where position_code = '%1$s'", position
					.getPositionCode()));
		}
		saveFlag = dbUtil.saveOrUpdate(sql.toString()) == 1;

		// 执行成功，则保存日志
		if (saveFlag) {
			optLog.setLogOptSql(sql.toString());
			OperateLogTool.saveOptLog(optLog);
		}
		
		return saveFlag;
	}

	/**
	 * 删除一个职位对象
	 * 
	 * @param position
	 *            职位对象
	 * @return boolean
	 */
	public boolean delPosition(BasePosition position, OperatLog optLog) {
		return delPosition(position.getPositionCode(), optLog);
	}

	/**
	 * 删除一个职位对象
	 * 
	 * @param positionCode
	 *            职位编号
	 * @return boolean
	 */
	public boolean delPosition(String positionCode, OperatLog optLog) {
		boolean delFlag = false;
		String sql = String.format(
				"delete from base_position p where p.position_code='%1$s'",
				positionCode);
		try {
			delFlag = dbUtil.saveOrUpdate(sql) == 1;
		} catch (Exception e) {
			delFlag = false;
		}

		// 执行成功，则保存日志
		if ( delFlag) {
			optLog.setLogOptSql(sql);
			OperateLogTool.saveOptLog(optLog);
		}
		return delFlag;
	}

	/**
	 * 权限分类
	 * 
	 * @return List<ModuleClass>
	 */
	public List<ModuleClass> qryModuleClasses() {
		List<ModuleClass> mcList = null;
		StringBuilder sql = new StringBuilder();
		sql.append("select distinct m.class_id, m.class_name");
		sql.append(" from sys_module m");
		sql.append(" where m.module_use = '1'");
		sql.append(" order by m.class_id");
		mcList = dbUtil.getObjList(sql.toString(), ModuleClass.class);
		return mcList;
	}

	/**
	 * 获取子权限列表
	 * 
	 * @param classId
	 *            权限分类
	 * @param optUserRole
	 *            用户角色（操作员）
	 * @return List<SysModule>
	 */
	public List<SysModule> qrySysModules(String classId, String optUserRole) {
		List<SysModule> mcList = null;
		StringBuilder sql = new StringBuilder();
		sql.append("select *");
		sql.append(" from sys_module s");
		sql.append(" where s.module_use = '1'");
		sql.append(String.format(" and bitand(s.user_role, %1$s) = %1$s",
				optUserRole));
		sql.append(String.format(" and s.class_id = '%1$s'", classId));
		sql.append(" order by s.module_id");
//		System.out.println("sql="+sql);
		mcList = dbUtil.getObjList(sql.toString(), SysModule.class);
		return mcList;
	}

	/**
	 * 根据操作员所在地区级别，查看自己及子集
	 * 
	 * @param userRole
	 *            用户角色
	 * @param optRegion
	 *            操作员地市代码
	 * @return List<BaseCity>
	 */
	public List<BaseCity> qryCitys(String userRole, String optRegion) {
		List<BaseCity> citys = null;
		StringBuilder sql = new StringBuilder();
		sql.append("select *");
		sql.append(" from base_city c");
		if (!Power.checkRole(userRole, Power.USER_ROLE_PROV)) {
			sql.append(String.format(" where c.city_code = '%1$s'", optRegion));
			sql
					.append(String.format(" or c.parent_region = '%1$s'",
							optRegion));
		}
		sql.append(" order by c.city_code asc");
		citys = dbUtil.getObjList(sql.toString(), BaseCity.class);
		return citys;
	}

	/**
	 * 查询用户列表
	 * 
	 * @param userEntity
	 *            查询条件对象
	 * @param page
	 *            分页对象
	 * @return List<BaseUser>
	 */
	public List<BaseUser> qryUsers(BaseUser userEntity, Page page) {
		List<BaseUser> userList = null;
		StringBuilder sql = new StringBuilder();
		sql.append("select u.*,");
		sql.append(" (select c.city_name");
		sql.append(" from base_city c");
		sql.append(" where c.city_code=u.city_code) as cityName,");
		sql.append(" (select cp.camera_place_name");
		sql.append(" from base_camera_place cp");
		sql
				.append(" where cp.camera_place_code = u.camera_place_code) as cameraPlaceName,");
		sql.append(" (select a.academy_name");
		sql.append(" from base_academy a");
		sql.append(" where a.academy_code = u.academy_code) as academyName");
		sql.append(" from base_user u");
		sql.append(" where 1=1");
		if ("".equals(StringTool.trim(userEntity.getCityCode()))
				|| "610000".equals(userEntity.getCityCode())) {
			sql.append(" and (u.city_code is null or u.city_code = '610000')");
		} else {
			sql.append(" and u.city_code in (select c.city_code");
			sql.append(" from base_city c");
			sql.append(String.format(" where c.city_code = '%1$s'", userEntity
					.getCityCode()));
			sql.append(String.format(" or c.parent_region = '%1$s')",
					userEntity.getCityCode()));
		}
		sql.append(String.format(" and bitand(u.user_role, %1$s) = %1$s",
				userEntity.getUserRole()));
		if (!"".equals(StringTool.trim(userEntity.getUserLock()))) {
			sql.append(String.format(" and u.user_lock = '%1$s'", userEntity
					.getUserLock()));
		}
		if (!"".equals(StringTool.trim(userEntity.getUserName()))) {
			sql.append(String.format(" and u.user_name like '%%%1$s%%'",
					userEntity.getUserName()));
		}
		if (!"".equals(StringTool.trim(userEntity.getRealName()))) {
			sql.append(String.format(" and u.real_name like '%%%1$s%%'",
					userEntity.getRealName()));
		}
		String sqlCount = String.format("select count(*) from (%1$s)", sql
				.toString());
		sql.append(" order by u.user_name asc");

		// 分页查询
		String sqlPage = page.setPagecount(dbUtil.getNum(sqlCount), sql
				.toString());
		userList = dbUtil.getObjList(sqlPage, BaseUser.class);

		return userList;
	}

	/**
	 * 查询职位列表
	 * 
	 * @param userRole
	 *            用户级别 “1”-省 “2”-地市 “4”-考区
	 * @return List<BasePosition>
	 */
	public List<BasePosition> qryPostionByGrade(String userRole) {
		List<BasePosition> positionList = null;
		StringBuilder sql = new StringBuilder();
		sql.append("select p.*, d.department_name");
		sql.append(" from base_position p, base_department d");
		sql.append(" where p.department_code = d.department_code");
		sql.append(" and bitand(d.department_grade, %1$s) = %1$s");
		sql.append(" order by p.department_code asc, p.position_code asc");
		positionList = dbUtil.getObjList(String
				.format(sql.toString(), userRole), BasePosition.class);
		return positionList;
	}

	/**
	 * @see 根据职位编号 获得部门对象
	 */
	public BaseDepartment getDepartmentByPositionId(String positionCode) {
		StringBuilder sql = new StringBuilder();
		sql
				.append(" select d.* from base_department d where d.department_code=");
		sql
				.append("(select p.department_code from base_position p where p.position_code=");
		sql.append(positionCode + ")");
		return dbUtil.getObject(sql.toString(), BaseDepartment.class);
	}
}
