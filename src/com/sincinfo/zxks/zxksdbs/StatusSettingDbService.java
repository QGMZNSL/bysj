/**
 * Copyright(c) 2012 西安云海信息技术有限责任公司 All Rights Reserved<br>
 * @Title: com.sincinfo.zxks.zxksdbs.StatusSettingDbService.java<br>
 * @Description: 考籍部分各种开关条件设置 <br>
 * <br>
 * @author litian<br>
 * @date Mar 27, 2012 9:08:25 AM 
 * @version V1.0   
 */
package com.sincinfo.zxks.zxksdbs;

import java.util.ArrayList;
import java.util.List;

import com.sincinfo.zxks.bean.BaseInformation;
import com.sincinfo.zxks.bean.BaseSwitch;
import com.sincinfo.zxks.common.db.DbUtil;
import com.sincinfo.zxks.common.util.Page;

/**
 * @ClassName: StatusSettingDbService
 * @Description: 考籍部分各种开关条件设置 <br>
 *               <br>
 * @author litian
 * @date Mar 27, 2012 9:08:25 AM<br>
 * 
 */
public class StatusSettingDbService {

	/**
	 * @Fields SWITCH_GRADUATE_OPENINFO : 毕业开关
	 */
	public final static String SWITCH_GRADUATE_OPENINFO = "001";
	/**
	 * @Fields SWTICH_GRADUATE_MIN_BEN_NUM : 毕业本科最少科目数
	 */
	public final static String SWTICH_GRADUATE_MIN_BEN_NUM = "002";
	/**
	 * @Fields SWTICH_GRADUATE_MIN_ZHUAN_NUM : 毕业专科最少科目数
	 */
	public final static String SWTICH_GRADUATE_MIN_ZHUAN_NUM = "003";
	/**
	 * @Fields SWTICH_GRADUATE_NOTICE : 毕业须知
	 */
	public final static String SWTICH_GRADUATE_NOTICE = "004";

	/**
	 * @Fields SWITCH_TRANSOUT_OPENINFO : 转出开关
	 */
	public final static String SWITCH_TRANSOUT_OPENINFO = "101";
	/**
	 * @Fields SWTICH_TRANSOUT_MIN_SYLLABUS_NUM : 转出最小课程数
	 */
	public final static String SWTICH_TRANSOUT_MIN_SYLLABUS_NUM = "102";
	/**
	 * @Fields SWTICH_TRANSOUT_NOTICE : 转出须知
	 */
	public final static String SWTICH_TRANSOUT_NOTICE = "103";

	/**
	 * @Fields SWITCH_TRANSIN_OPENINFO : 转入开关
	 */
	public final static String SWITCH_TRANSIN_OPENINFO = "201";
	/**
	 * @Fields SWTICH_TRANSIN_MIN_SYLLABUS_NUM : 转入最小课程数
	 */
	public final static String SWTICH_TRANSIN_MIN_SYLLABUS_NUM = "202";
	/**
	 * @Fields SWTICH_TRANSIN_NOTICE : 转入须知
	 */
	public final static String SWTICH_TRANSIN_NOTICE = "203";

	private DbUtil dbUtil;

	/**
	 * 初始化对象
	 */
	public StatusSettingDbService() {
		this.dbUtil = new DbUtil();
	}

	/**
	 * 利用传入的数据库操作对象创建一个实例
	 * 
	 * @param dbUtil
	 *            数据操作对象
	 */
	public StatusSettingDbService(DbUtil dbUtil) {
		this.dbUtil = new DbUtil();
	}

	/**
	 * 返回本对象中的DbUtil对象，用于防止一个功能中连续创建多个DbUtil而浪费资源
	 * 
	 * @return
	 */
	public DbUtil getDbUtil() {
		return this.dbUtil;
	}

	/**
	 * 根据ID取得配置
	 * 
	 * @param switchId
	 *            从静态变量中获取
	 * @return BaseSwitch
	 */
	public BaseSwitch qrySwitch(String switchId) {
		StringBuilder sql = new StringBuilder();
		sql.append("select s.* from base_switch s");
		sql.append(" where s.switch_code = '%1$s'");
		sql.append(" order by s.switch_code");
		BaseSwitch swch = this.dbUtil.getObject(String.format(sql.toString(),
				switchId), BaseSwitch.class);
		return swch;
	}

	/**
	 * 获取考籍各种开关设置列表
	 * 
	 * @return
	 */
	public List<BaseSwitch> qryAllStatusSwitches() {
		List<BaseSwitch> switches = null;
		StringBuilder sql = new StringBuilder();
		sql.append("select s.* from base_switch s");
		sql.append(" where s.city_code = '00'");
		sql.append(" order by s.switch_code");
		switches = this.dbUtil.getObjList(sql.toString(), BaseSwitch.class);
		return switches;
	}

	/**
	 * 获取考籍各种开关设置列表（根据类型0毕业 1转出2转入）
	 * 
	 * @param switchType
	 * @return
	 */
	public List<BaseSwitch> qryStatusSwitches(String switchType) {
		List<BaseSwitch> switches = null;
		StringBuilder sql = new StringBuilder();
		sql.append("select s.* from base_switch s");
		sql.append(" where s.city_code = '00'");
		sql.append(" and s.switch_type='%1$s'");
		sql.append(" order by s.switch_code");
		switches = this.dbUtil.getObjList(String.format(sql.toString(),
				switchType), BaseSwitch.class);
		return switches;
	}

	/**
	 * 根据须知id获取须知名称
	 * 
	 * @param noticeId
	 *            须知主键ID
	 * @return String 须知标题
	 */
	public String getNoticeTitle(String noticeId) {
		String sql = "select i.information_title from base_information i where i.information_id = '%1$s'";
		String title = this.dbUtil.getFirstCol(String.format(sql, noticeId));
		return title;
	}

	/**
	 * 根据须知id获取须知
	 * 
	 * @param noticeId
	 *            须知主键ID
	 * @return BaseInformation 须知
	 */
	public BaseInformation getNotice(String noticeId) {
		String sql = "select * from base_information i where i.information_id = '%1$s'";
		BaseInformation notice = this.dbUtil.getObject(String.format(sql,
				noticeId), BaseInformation.class);
		return notice;
	}

	/**
	 * @param inforClass
	 *            "3"-转出 "4"-转入 "5"-毕业
	 * @param inforTitle
	 *            标题模糊查询
	 * @param page
	 *            分页对象
	 * @return List<BaseInformation>
	 */
	public List<BaseInformation> qryNotices(String inforClass,
			String inforTitle, Page page) {
		List<BaseInformation> noticeList = null;
		StringBuilder sql = new StringBuilder();
		sql.append("select * from base_information i ");
		sql.append(String.format(" where i.class_id='%1$s'", inforClass));
		if (!"".equals(inforTitle)) {
			sql.append(String.format(
					" and i.information_title like '%%%1$s%%'", inforTitle));
		}
		String sqlCount = String.format("select count(*) from (%1$s)", sql
				.toString());
		sql.append(" order by i.information_add_time desc");

		// 分页查询
		String sqlPage = page.setPagecount(dbUtil.getNum(sqlCount), sql
				.toString());
		noticeList = this.dbUtil.getObjList(sql.toString(),
				BaseInformation.class);
		return noticeList;
	}

	/**
	 * 保存考籍转出的设置
	 * 
	 * @param tout_startDate
	 *            开始时间
	 * @param tout_endDate
	 *            结束时间
	 * @param tout_localSyllabusNum
	 *            课程数
	 * @param tout_noticeId
	 *            须知编号
	 * @return boolean
	 */
	public boolean saveTransOutSetting(String tout_startDate,
			String tout_endDate, String tout_localSyllabusNum,
			String tout_noticeId) {
		String sql = "update base_switch set switch_value='%2$s' where switch_type='1' and switch_code = '%1$s'";
		ArrayList<String> sqlList = new ArrayList<String>();
		sqlList.add(String.format(sql, SWITCH_TRANSOUT_OPENINFO, tout_startDate
				+ ":" + tout_endDate));
		sqlList.add(String.format(sql, SWTICH_TRANSOUT_MIN_SYLLABUS_NUM,
				tout_localSyllabusNum));
		sqlList.add(String.format(sql, SWTICH_TRANSOUT_NOTICE, tout_noticeId));
		int row = dbUtil.transExeSqls(sqlList);
		return row >= 1;
	}

	/**
	 * 保存考籍转入的设置
	 * 
	 * @param tin_startDate
	 *            开始时间
	 * @param tin_endDate
	 *            结束时间
	 * @param tin_localSyllabusNum
	 *            课程数
	 * @param tin_noticeId
	 *            须知编号
	 * @return boolean
	 */
	public boolean saveTransInSetting(String tin_startDate, String tin_endDate,
			String tin_localSyllabusNum, String tin_noticeId) {
		String sql = "update base_switch set switch_value='%2$s' where switch_type='2' and switch_code = '%1$s'";
		ArrayList<String> sqlList = new ArrayList<String>();
		sqlList.add(String.format(sql, SWITCH_TRANSIN_OPENINFO, tin_startDate
				+ ":" + tin_endDate));
		sqlList.add(String.format(sql, SWTICH_TRANSIN_MIN_SYLLABUS_NUM,
				tin_localSyllabusNum));
		sqlList.add(String.format(sql, SWTICH_TRANSIN_NOTICE, tin_noticeId));
		int row = dbUtil.transExeSqls(sqlList);
		return row >= 1;
	}
	
	/**
	 * 判断是否处于跨省转出时间内
	 * @return
	 */
	public boolean isTimeForTransOut() {
		String switchCode = "101";
		return chkTimeForStatus(switchCode);
	}

	/**
	 * 判断是否处于跨省转入时间内
	 * @return
	 */
	public boolean isTimeForTransIn() {
		String switchCode = "201";
		return chkTimeForStatus(switchCode);
	}

	/**
	 * 判断是否处于申办毕业时间内
	 * @return
	 */
	public boolean isTimeForGraduate() {
		String switchCode = "001";
		return chkTimeForStatus(switchCode);
	}
	
	/**
	 * 检查是否是考籍业务办理的时间
	 * @param switchCode 业务开关Code
	 * @return boolean
	 */
	private boolean chkTimeForStatus( String switchCode) {
		StringBuilder sql = new StringBuilder();
		sql.append("select case");
		sql.append(" when (to_date(substr(s.switch_value, 1, 10) || ' 00:00:00',");
		sql.append(" 'yyyy-mm-dd hh24:mi:ss') <= sysdate and");
		sql.append(" to_date(substr(s.switch_value, 12, 10) || ' 23:59:59',");
		sql.append(" 'yyyy-mm-dd hh24:mi:ss') >= sysdate) then");
		sql.append(" 1");
		sql.append(" else");
		sql.append(" 0");
		sql.append(" end as canDo");
		sql.append(" from base_switch s");
		sql.append(" where s.switch_code = '%1$s'");
		return "1".equals(dbUtil.getFirstCol(String.format(sql.toString(), switchCode)));
	}
}