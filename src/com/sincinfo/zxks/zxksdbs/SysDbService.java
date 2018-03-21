/**
 * Copyright(c) 2012 西安云海信息技术有限责任公司 All Rights Reserved<br>
 * @Title: com.sincinfo.zxks.zxksdbs.SysQuery.java<br>
 * @Description: TODO(用一句话描述该文件做什么) <br>
 * <br>
 * @author litian<br>
 * @date Jan 18, 2012 11:20:42 AM 
 * @version V1.0   
 */
package com.sincinfo.zxks.zxksdbs;

import java.util.List;

import com.sincinfo.zxks.bean.BaseAcademy;
import com.sincinfo.zxks.bean.BaseCameraPlace;
import com.sincinfo.zxks.bean.BaseJoinhandsUnit;
import com.sincinfo.zxks.bean.BaseUser;
import com.sincinfo.zxks.bean.SysCode;
import com.sincinfo.zxks.common.db.DbUtil;
import com.sincinfo.zxks.common.log.OperatLog;
import com.sincinfo.zxks.common.log.OperateLogTool;
import com.sincinfo.zxks.common.util.MD5;
import com.sincinfo.zxks.common.util.Power;
import com.sincinfo.zxks.common.util.StringTool;
import com.sincinfo.zxks.core.day.datapreserve.busi.UserPre;

/**
 * @ClassName: SysQuery
 * @Description: 包括系统登录，及与操作员等相关的系统级别的数据库操作 <br>
 *               <br>
 * @author litian
 * @date Jan 18, 2012 11:20:42 AM<br>
 * 
 */
public class SysDbService {

	/**
	 * 自定义字典
	 */
	public static final String SYS_DICT_CUSTOM = "sys_code_custom";
	/**
	 * 民族字典
	 */
	public static final String SYS_DICT_FOLK = "sys_code_folk";
	/**
	 * 性别
	 */
	public static final String SYS_DICT_GENDER = "sys_code_gender";
	/**
	 * 户口
	 */
	public static final String SYS_DICT_HUKOU_CHARACTER = "sys_code_hukou_character";
	/**
	 * 证件类型
	 */
	public static final String SYS_DICT_IDNO_TYPE = "sys_code_idno_type";
	/**
	 * 职业
	 */
	public static final String SYS_DICT_OCCUPATION = "sys_code_occupation";
	/**
	 * 政治面貌
	 */
	public static final String SYS_DICT_POLITICS = "sys_code_politics";
	/**
	 * 文化程度
	 */
	public static final String SYS_DICT_SCHOOL_AGE = "sys_code_school_age";

	/**
	 * 获取操作员对象
	 * 
	 * @param userName
	 * @return
	 */
	public BaseUser qryOperator(String userName) {
		BaseUser user = null;
		String sql = String.format(
				"select * from base_user u where u.user_name='%1$s'", userName);
		DbUtil dbUtil = new DbUtil();
		user = dbUtil.getObject(sql, BaseUser.class);
		return user;
	}

	/**
	 * 登录一次，更新登录次数以及最后登录时间
	 * 
	 * @param userName
	 */
	public void loginOnce(String userName) {
		// 更改登录次数等状态
		String sql = "update base_user u set u.login_num=u.login_num+1,u.last_login_time=sysdate where u.user_name='%1$s'";
		DbUtil dbUtil = new DbUtil();
		dbUtil.saveOrUpdate(String.format(sql, userName));
	}

	/**
	 * 添加一条登陆成功日志
	 * 
	 * @param userName
	 *            用户名
	 * @param loginIp
	 *            登录ip
	 */
	public void loginLogSuccess(String userName, String loginIp) {
		StringBuilder sql = new StringBuilder();
		sql
				.append("insert into log_login_success(log_login_id, user_name, login_ip, login_time)");
		sql
				.append(" values ( SEQ_LOG_LOGIN_SUSS.Nextval, '%1$s','%2$s',sysdate)");
		DbUtil dbUtil = new DbUtil();
		dbUtil.saveOrUpdate(String.format(sql.toString(), userName, loginIp));
	}

	/**
	 * 添加一条登录失败日志
	 * 
	 * @param userName
	 *            用户名
	 * @param loginIp
	 *            登录ip
	 * @param loginPassword
	 *            错误密码
	 */
	public void loginLogFailure(String userName, String loginIp,
			String loginPassword) {
		StringBuilder sql = new StringBuilder();
		sql
				.append("insert into log_login_failure(log_login_id, user_name, login_ip, login_time, login_password)");
		sql
				.append(" values ( Seq_Log_Login_Fail.Nextval, '%1$s', '%2$s', sysdate, '%3$s')");
		DbUtil dbUtil = new DbUtil();
		dbUtil.saveOrUpdate(String.format(sql.toString(), userName, loginIp,
				loginPassword));
	}

	/**
	 * 设置跳转md5，用于查询
	 * 
	 * @param userName
	 * @param acrossMd5
	 */
	public void loginAcross(String userName, String acrossMd5) {
		String sql = "update base_user u set u.login_across='%2$s' where u.user_name='%1$s'";
		DbUtil dbUtil = new DbUtil();
		dbUtil.saveOrUpdate(String.format(sql, userName, acrossMd5));
	}

	/**
	 * 修改密码，并返回数据库保存结果
	 * 
	 * @param selfUser
	 *            操作员用户，其中密码保存为新密码
	 * @return boolean
	 */
	public boolean saveNewPwd(BaseUser selfUser) {
		String sql = String
				.format(
						"update base_user set user_password = '%2$s' where user_name = '%1$s'",
						selfUser.getUserName(), selfUser.getUserPassword());
		DbUtil dbUtil = new DbUtil();
		boolean flag = false;
		flag = dbUtil.saveOrUpdate(sql) == 1;
		return flag;
	}

	/**
	 * 查询系统字典表
	 * 
	 * @return List<SysCode>
	 */
	public List<SysCode> qrySysDict(String sysDictTable) {
		List<SysCode> sysDict = null;
		String sql = String
				.format(
						"select * from %1$s t where t.is_use = 1 order by t.list_order asc",
						sysDictTable);
		DbUtil dbUtil = new DbUtil();
		sysDict = dbUtil.getObjList(sql, SysCode.class);
		return sysDict;
	}

	/**
	 * 获取指定地市的主考院校列表
	 * 
	 * @param cityCode
	 *            地市代码
	 * @return List<BaseAcademy>
	 */
	public List<BaseAcademy> qryAcademys(String cityCode) {
		List<BaseAcademy> academyList = null;
		StringBuilder sql = new StringBuilder();
		sql.append("select a.*");
		sql.append(" from base_academy a");
		sql.append(" where a.is_use = 1");
		sql.append(" and a.city_code in (select c.city_code");
		sql.append(" from base_city c");
		sql.append(" where c.city_code = '%1$s'");
		sql.append(" or c.parent_region = '%1$s')");
		sql.append(" order by a.city_code asc, a.academy_code asc");
		DbUtil dbUtil = new DbUtil();
		academyList = dbUtil.getObjList(
				String.format(sql.toString(), cityCode), BaseAcademy.class);
		return academyList;
	}

	/**
	 * 获取指定地市的摄像点列表
	 * 
	 * @param cityCode
	 *            摄像点代码
	 * @return List<BaseCameraPlace>
	 */
	public List<BaseCameraPlace> qryCameraPlaces(String cityCode) {
		List<BaseCameraPlace> cameraList = null;
		StringBuilder sql = new StringBuilder();
		sql.append("select *");
		sql.append(" from base_camera_place p");
		sql.append(" where p.is_use = 1");
		sql.append(" and p.city_code in (select c.city_code");
		sql.append(" from base_city c");
		sql.append(" where c.city_code = '%1$s'");
		sql.append(" or c.parent_region = '%1$s')");
		sql.append(" order by p.city_code asc, p.camera_place_code asc");
		DbUtil dbUtil = new DbUtil();
		cameraList = dbUtil.getObjList(String.format(sql.toString(), cityCode),
				BaseCameraPlace.class);
		return cameraList;
	}
	
	/**
	 * 获取指定地市的合作开考单位
	 */
	public List<BaseJoinhandsUnit> qryJoinHandsUnit(String cityCode){
		StringBuilder sql=new StringBuilder();
		sql.append("select t.* ");
		sql.append(",(select c.city_name from base_city c where c.city_code=t.city_code) as city_name");
		sql.append(" from base_joinhands_unit t ");
		sql.append(" where t.city_code='%1$s'");
		sql.append(" and t.is_use='1'");
		sql.append(" order by t.unit_code");
		return new DbUtil().getObjList(String.format(sql.toString(),cityCode), BaseJoinhandsUnit.class);
	}

	/**
	 * 保存新用户对象
	 * 
	 * @param usrp
	 *            用户对象
	 * @param isNew
	 *            是否是新对象，true-执行insert false-执行update
	 * @param optLog
	 *            操作日志
	 * @return boolean 成功标记
	 */
	public boolean saveOperator(UserPre usrp, boolean isNew, OperatLog optLog) {
		boolean saveFlag = false;
		StringBuilder sql = new StringBuilder();
		if (isNew) {
			sql.append("insert into base_user");
			sql.append(" (user_name,");
			sql.append(" real_name,");
			sql.append(" gender,");
			sql.append(" user_role,");
			sql.append(" city_code,");
			sql.append(" position_code,");
			sql.append(" telephone,");
			sql.append(" postal_address,");
			sql.append(" power_array,");
			sql.append(" academy_code,");
			sql.append(" camera_place_code,");
			sql.append(" unit_code,");
			sql.append(" user_password,");
			sql.append(" init_password,");
			sql.append(" user_lock)");			
			sql.append(" values");
			sql.append(" ('");
			sql.append(usrp.getUserName());
			sql.append("', '");
			sql.append(usrp.getRealName());
			sql.append("', '");
			sql.append(usrp.getUserGender());
			sql.append("',");
			sql.append(usrp.getUserRole());
			sql.append(", '");
			sql.append(usrp.getCityCode());
			sql.append("', '");
			sql.append(usrp.getPositionCode());
			sql.append("', '");
			sql.append(usrp.getTelephone());
			sql.append("', '");
			sql.append(usrp.getPostalAddress());
			sql.append("', ");
//			System.out.println("usrp.getUserRole()="+usrp.getUserRole());
//			System.out.println("Power.USER_ROLE_PROV="+Power.USER_ROLE_PROV);
//			System.out.println("Power.USER_ROLE_CITY="+Power.USER_ROLE_CITY);
//			System.out.println("Power.USER_ROLE_AREA="+Power.USER_ROLE_AREA);
			if (Power.checkRole(Power.USER_ROLE_PROV, usrp.getUserRole())
					|| Power
							.checkRole(Power.USER_ROLE_CITY, usrp.getUserRole())
					|| Power
							.checkRole(Power.USER_ROLE_AREA, usrp.getUserRole())) {
				sql
						.append(String
								.format(
										"(select p.default_power_array from base_position p where p.position_code='%1$s')",
										usrp.getPositionCode()));
			} else {
				sql.append("0");
			}
			sql.append(", '");
			sql.append(usrp.getAcademyCode());
			sql.append("', '");
			sql.append(usrp.getCameraPlaceCode());
			sql.append("', '");
			sql.append(usrp.getUnitCode());
			sql.append("', '");
			sql.append(MD5.getMD5String(usrp.getUserPassword()));
			sql.append("', '");
			sql.append(usrp.getUserPassword());
			sql.append("', '");
			sql.append("".equals(StringTool.trim(usrp.getUserLock())) ? "0"
					: usrp.getUserLock());
			sql.append("')");
		} else {
			sql.append("update base_user u set");
			sql.append(String.format(" u.real_name      = '%1$s',", usrp
					.getRealName()));
			sql.append(String.format(" u.gender         = '%1$s',", usrp
					.getUserGender()));
			sql.append(String.format(" u.user_role      = '%1$s',", usrp
					.getUserRole()));
			sql.append(String.format(" u.city_code      = '%1$s',", usrp
					.getCityCode()));
			sql.append(String.format(" u.position_code  = '%1$s',", usrp
					.getPositionCode()));
			sql.append(String.format(" u.telephone      = '%1$s',", usrp
					.getTelephone()));
			sql.append(String.format(" u.postal_address = '%1$s',", usrp
					.getPostalAddress()));
			if (Power.checkRole(Power.USER_ROLE_PROV, usrp.getUserRole())
					|| Power
							.checkRole(Power.USER_ROLE_CITY, usrp.getUserRole())
					|| Power
							.checkRole(Power.USER_ROLE_AREA, usrp.getUserRole())) {
				sql
						.append(String
								.format(
										"  u.power_array = (select p.default_power_array from base_position p where p.position_code='%1$s'),",
										usrp.getPositionCode()));
			} else {
				sql.append("  u.power_array = '0',");
			}
			sql.append(String.format(" u.academy_code   = '%1$s',", usrp
					.getAcademyCode()));
			sql.append(String.format(" u.camera_place_code = '%1$s',", usrp
					.getCameraPlaceCode()));
			sql.append(String.format(" u.unit_code = '%1$s',", usrp
					.getUnitCode()));
			sql.append(String.format(" u.user_lock = '%1$s'", ""
					.equals(StringTool.trim(usrp.getUserLock())) ? "0" : usrp
					.getUserLock()));
			if (!"".equals(StringTool.trim(usrp.getUserPassword()))) {
				sql.append(String.format(", u.user_password  = '%1$s'", MD5
						.getMD5String(usrp.getUserPassword())));
			}
			sql.append(String.format(" where u.user_name = '%1$s'", usrp
					.getUserName()));
		}
		DbUtil dbUtil = new DbUtil();
		saveFlag = dbUtil.saveOrUpdate(sql.toString()) == 1;
        System.out.println("addsql="+sql);
		// 保存操作日志
		if (saveFlag) {
			optLog.setLogOptSql(sql.toString());
			OperateLogTool.saveOptLog(optLog);
		}

		return saveFlag;
	}

	/**
	 * 保存权限
	 * 
	 * @param usrp
	 *            用户对象 其中userName与powerArray不能为空
	 * @param optLog
	 *            操作日志
	 * @return boolean 成功标记
	 */
	public boolean savePower(UserPre usrp, OperatLog optLog) {
		boolean saveFlag = false;
		String sql = String
				.format(
						"update base_user u set u.power_array='%1$s' where u.user_name='%2$s'",
						usrp.getPowerArray(), usrp.getUserName());
		DbUtil dbUtil = new DbUtil();
		saveFlag = dbUtil.saveOrUpdate(sql) == 1;

		// 保存操作日志
		if (saveFlag) {
			optLog.setLogOptSql(sql.toString());
			OperateLogTool.saveOptLog(optLog);
		}
		
		return saveFlag;
	}

}
