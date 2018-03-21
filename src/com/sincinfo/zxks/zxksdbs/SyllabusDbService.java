/**
 * Copyright(c) 2012 西安云海信息技术有限责任公司 All Rights Reserved<br>
 * @Title: com.sincinfo.zxks.zxksdbs.DayDbService.java<br>
 * @Description: 报考层次管理 <br>
 * <br>
 * @author yuansh<br>
 * @date 2012-01-26 15:58
 * @version V1.0   
 */
package com.sincinfo.zxks.zxksdbs;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.sincinfo.zxks.bean.SingleEntity;
import com.sincinfo.zxks.bean.Syllabus;
import com.sincinfo.zxks.common.db.DbUtil;
import com.sincinfo.zxks.common.util.Page;
import com.sincinfo.zxks.common.util.StringTool;

/**
 * @ClassName: PlanDbService
 * @Description: 课程管理 <br>
 * @author yuansh
 * @date 2012-01-26 15:58<br>
 * 
 */
public class SyllabusDbService {
	private DbUtil dbUtil = null;

	/**
	 * 新建一个文件名
	 * 
	 * @return
	 */
	public String newFilename() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		Date currentDate = new Date();
		String filename = formatter.format(currentDate).toString();
		String allChar = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < 4; i++) {
			sb.append(allChar.charAt(random.nextInt(allChar.length())));
		}
		filename += sb.toString();
		return filename;
	}

	/**
	 * 获取保存文件地址
	 * 
	 * @return String[] [0]-物理地址 [1]-网络地址
	 */
	public String[] getPaths() {
		// 提取操作系统名
		String cfgTypeOs = System.getProperty("os.name").split(" ")[0]
				.toLowerCase();

		// 查询
		StringBuilder sql = new StringBuilder();
		sql.append("select (select c.sys_cfg_content from sys_config c");
		sql.append(" where c.sys_cfg_type='%1$s' and (c.sys_cfg_id = '1' or c.sys_cfg_id = '3')) as phyPath,");
		sql.append(" (select c.sys_cfg_content from sys_config c");
		sql.append(" where c.sys_cfg_type='%1$s' and (c.sys_cfg_id = '2' or c.sys_cfg_id = '4')) as netPath");
		sql.append(" from dual");
		DbUtil dbUtil = new DbUtil();
		String[] paths = dbUtil.getRsArray(
				String.format(sql.toString(), cfgTypeOs), 2);
		return paths;
	}

	/**
	 * 根据配置sys_config表主键，取得对应的content
	 * 
	 * @param cfgId
	 *            主键
	 * @return String 配置内容content
	 */
	public String getConfig(String cfgId) {
		String sql = String
				.format("select c.sys_cfg_content from sys_config c where c.sys_cfg_id = '%1$s'",
						cfgId);
		DbUtil dbUtil = new DbUtil();
		return dbUtil.getFirstCol(sql);
	}

	/**
	 * 构造
	 */
	public SyllabusDbService() {
		this.dbUtil = new DbUtil();
	}

	/**
	 * 根据条件查询对应的所有报考层次列表，若不加限制则传递“0”
	 * 
	 * @param code
	 *            暂未用不用传值
	 * @param page
	 *            分页对象
	 * @return
	 */
	public List<Syllabus> qry(Syllabus syllabus, Page page) {
		List<Syllabus> syllabuss = null;
		StringBuilder sql = new StringBuilder();
		StringBuilder sqlCount = new StringBuilder();
		sql.append("select d.*,(select textbook_name from base_textbook t where t.textbook_code=d.textbook_code)textbook_name,(select count(*) from base_avoid a where a.syllabus_code=d.syllabus_code)state,"
				+ "(select count(*) from base_substitute a where a.syllabus_code=d.syllabus_code)state1 from base_syllabus d where d.is_use='1' ");
		sqlCount.append("select count(*) from base_syllabus d where d.is_use='1' ");
		if (!StringTool.isEmpty(syllabus)) {
			if (!StringTool.isEmpty(syllabus.getSyllabusCode())) {
				sql.append(String.format(" and syllabus_code like '%%%1$s%%'",
						syllabus.getSyllabusCode()));
				sqlCount.append(String.format(
						" and syllabus_code like '%%%1$s%%'",
						syllabus.getSyllabusCode()));
			}
			if (!StringTool.isEmpty(syllabus.getSyllabusName())) {
				sql.append(String.format(" and syllabus_name like '%%%1$s%%'",
						syllabus.getSyllabusName()));
				sqlCount.append(String.format(
						" and syllabus_name like '%%%1$s%%'",
						syllabus.getSyllabusName()));
			}
			if (!StringTool.isEmpty(syllabus.getIsUse())) {
				sql.append(String.format(" and is_use like '%%%1$s%%'",
						syllabus.getIsUse()));
				sqlCount.append(String.format(" and is_use like '%%%1$s%%'",
						syllabus.getIsUse()));
			}
			if (!StringTool.isEmpty(syllabus.getIsgb())) {
				sql.append(String.format(" and is_gb='%1$s'",
						syllabus.getIsgb()));
				sqlCount.append(String.format(" and is_gb='%1$s'",
						syllabus.getIsgb()));
			}
			if (!StringTool.isEmpty(syllabus.getSyllabusType())) {
				sql.append(String.format(" and syllabus_type='%1$s'",
						syllabus.getSyllabusType()));
				sqlCount.append(String.format(" and syllabus_type='%1$s'",
						syllabus.getSyllabusType()));
			}
			if (!StringTool.isEmpty(syllabus.getState1())) {
				if ("0".equals(syllabus.getState1())) {
					sql.append(" and (select count(*) from base_avoid a where a.syllabus_code=d.syllabus_code)= 0");
					sqlCount.append(" and (select count(*) from base_avoid a where a.syllabus_code=d.syllabus_code)= 0");
				} else {
					sql.append(" and (select count(*) from base_avoid a where a.syllabus_code=d.syllabus_code)> 0");
					sqlCount.append(" and (select count(*) from base_avoid a where a.syllabus_code=d.syllabus_code)> 0");
				}
			}
			if (!StringTool.isEmpty(syllabus.getState())) {
				if ("0".equals(syllabus.getState())) {
					sql.append(" and (select count(*) from base_substitute a where a.syllabus_code=d.syllabus_code)= 0");
					sqlCount.append(" and (select count(*) from base_substitute a where a.syllabus_code=d.syllabus_code)= 0");
				} else {
					sql.append(" and (select count(*) from base_substitute a where a.syllabus_code=d.syllabus_code)> 0");
					sqlCount.append(" and (select count(*) from base_substitute a where a.syllabus_code=d.syllabus_code)> 0");
				}
			}
		}
		sql.append(" order by d.syllabus_code asc");
		// 分页查询
		String sqlPage = page.setPagecount(dbUtil.getNum(sqlCount.toString()),
				sql.toString());
		syllabuss = dbUtil.getObjList(sqlPage, Syllabus.class);
		return syllabuss;
	}

	/**
	 * 根据条件查询对应的所有报考层次列表，若不加限制则传递“0”
	 * 
	 * @param code
	 *            暂未用不用传值
	 * @param page
	 *            分页对象
	 * @return
	 */
	public List<Syllabus> qryForExcel(Syllabus syllabus) {
		List<Syllabus> syllabuss = null;
		StringBuilder sql = new StringBuilder();
		sql.append("select d.*,(select textbook_name from base_textbook t where t.textbook_code=d.textbook_code)textbook_name,(select count(*) from base_avoid a where a.syllabus_code=d.syllabus_code)state,"
				+ "(select count(*) from base_substitute a where a.syllabus_code=d.syllabus_code)state1 from base_syllabus d where d.is_use='1' ");
		if (!StringTool.isEmpty(syllabus)) {
			if (!StringTool.isEmpty(syllabus.getSyllabusCode())) {
				sql.append(String.format(" and syllabus_code like '%%%1$s%%'",
						syllabus.getSyllabusCode()));
			}
			if (!StringTool.isEmpty(syllabus.getSyllabusName())) {
				sql.append(String.format(" and syllabus_name like '%%%1$s%%'",
						syllabus.getSyllabusName()));
			}
			if (!StringTool.isEmpty(syllabus.getIsUse())) {
				sql.append(String.format(" and is_use like '%%%1$s%%'",
						syllabus.getIsUse()));
			}
			if (!StringTool.isEmpty(syllabus.getIsgb())) {
				sql.append(String.format(" and is_gb='%1$s'",
						syllabus.getIsgb()));
			}
			if (!StringTool.isEmpty(syllabus.getSyllabusType())) {
				sql.append(String.format(" and syllabus_type='%1$s'",
						syllabus.getSyllabusType()));
			}
			if (!StringTool.isEmpty(syllabus.getState1())) {
				if ("0".equals(syllabus.getState1())) {
					sql.append(" and (select count(*) from base_avoid a where a.syllabus_code=d.syllabus_code)= 0");
				} else {
					sql.append(" and (select count(*) from base_avoid a where a.syllabus_code=d.syllabus_code)> 0");
				}
			}
			if (!StringTool.isEmpty(syllabus.getState())) {
				if ("0".equals(syllabus.getState())) {
					sql.append(" and (select count(*) from base_substitute a where a.syllabus_code=d.syllabus_code)= 0");
				} else {
					sql.append(" and (select count(*) from base_substitute a where a.syllabus_code=d.syllabus_code)> 0");
				}
			}
		}
		sql.append(" order by d.syllabus_code asc");

		syllabuss = dbUtil.getObjList(sql.toString(), Syllabus.class);
		return syllabuss;
	}

	/**
	 * 根据条件查询对应的所有报考层次列表，若不加限制则传递“0”
	 * 
	 * @param code
	 *            暂未用不用传值
	 * @param page
	 *            分页对象
	 * @return
	 */
	public List<Syllabus> qry1(Syllabus syllabus, Page page) {
		List<Syllabus> syllabuss = null;
		StringBuilder sql = new StringBuilder();
		StringBuilder sqlCount = new StringBuilder();
		sql.append("select d.* from base_syllabus d where d.is_use='1' ");
		sqlCount.append("select count(*) from base_syllabus d where d.is_use='1' ");
		sql.append(" order by d.syllabus_code asc");
		// 分页查询
		String sqlPage = page.setPagecount(dbUtil.getNum(sqlCount.toString()),
				sql.toString());
		syllabuss = dbUtil.getObjList(sqlPage, Syllabus.class);
		return syllabuss;
	}

	/**
	 * 根据条件查询对应的所有报考层次列表，若不加限制则传递“0”
	 * 
	 * @param code
	 *            暂未用不用传值
	 * @param page
	 *            分页对象
	 * @return
	 */
	public List<Syllabus> qryGROP(Syllabus syllabus, Page page) {
		List<Syllabus> syllabuss = null;
		StringBuilder sql = new StringBuilder();
		StringBuilder sqlCount = new StringBuilder();
		sql.append("select d.*,(select textbook_name from base_textbook t where t.textbook_code=d.textbook_code) as textbook_name,(select count(*) from base_avoid a where a.syllabus_code=d.syllabus_code) as syllabusname"
				+ " from base_syllabus d where d.is_use='1' ");
		sqlCount.append("select count(*) from base_syllabus d where d.is_use='1' ");
		if (!StringTool.isEmpty(syllabus)) {
			if (!StringTool.isEmpty(syllabus.getSyllabusCode())) {
				sql.append(String.format(
						" and d.syllabus_code like '%%%1$s%%'",
						syllabus.getSyllabusCode()));
				sqlCount.append(String.format(
						" and d.syllabus_code like '%%%1$s%%'",
						syllabus.getSyllabusCode()));
			}
			if (!StringTool.isEmpty(syllabus.getSyllabusName())) {
				sql.append(String.format(
						" and d.syllabus_name like '%%%1$s%%'",
						syllabus.getSyllabusName()));
				sqlCount.append(String.format(
						" and d.syllabus_name like '%%%1$s%%'",
						syllabus.getSyllabusName()));
			}

		}
		sql.append(" order by d.syllabus_code asc");

		// 分页查询
		String sqlPage = page.setPagecount(dbUtil.getNum(sqlCount.toString()),
				sql.toString());
		syllabuss = dbUtil.getObjList(sqlPage, Syllabus.class);
		return syllabuss;
	}

	/**
	 * 保存部门对象到数据库
	 * 
	 * @param syllabus
	 *            报考层次
	 * @param type
	 *            类型 0-insert, 1-update
	 * @return boolean
	 */
	public boolean save(Syllabus syllabus, int type) {
		boolean saveFlag = false;
		StringBuilder sql = new StringBuilder();
		switch (type) {
		case 0:
			String s = "select count(*) from base_syllabus b where b.syllabus_code=?";
			Long l = dbUtil.getNum(s, syllabus.getSyllabusCode());
			if (l == 0) {
				sql.append("insert into base_syllabus");
				sql.append("(syllabus_code,syllabus_name,syllabus_english_name,syllabus_type,is_gb,syllabus_credit,textbook_code,is_use,remarks)");
				sql.append(" values");
				sql.append(String
						.format(" ('%1$s','%2$s','%3$s','%4$s','%5$s','%6$s','%7$s','%8$s','%9$s')",
								syllabus.getSyllabusCode(),
								syllabus.getSyllabusName(),
								"",// syllabus.getSyllabusenglishname(),
								syllabus.getSyllabusType(), syllabus.getIsgb(),
								syllabus.getSyllabuscredit(),
								syllabus.getTextbookCode(), "1",
								syllabus.getRemarks()));
			} else {
				sql.append("update base_syllabus");
				sql.append(String.format(" set syllabus_name = '%1$s',",
						syllabus.getSyllabusName()));
				// sql.append(String.format(" syllabus_english_name = '%1$s',",
				// syllabus.getSyllabusenglishname()));
				sql.append(String.format(" syllabus_type = '%1$s',",
						syllabus.getSyllabusType()));
				sql.append(String.format(" is_gb = '%1$s',", syllabus.getIsgb()));
				sql.append(String.format(" syllabus_credit = '%1$s',",
						syllabus.getSyllabuscredit()));
				sql.append(String.format(" textbook_code = '%1$s',",
						syllabus.getTextbookCode()));
				// sql.append(String.format(" syllabus_total_score = '%1$s',",
				// syllabus.getSyllabustotalscore()));
				// sql.append(String.format(" syllabus_pass_score = '%1$s',",
				// syllabus.getSyllabuspassscore()));
				sql.append(String.format(" is_use = '%1$s',", "1"));
				sql.append(String.format(" remarks = '%1$s'",
						syllabus.getRemarks()));
				sql.append(String.format(" where syllabus_code = '%1$s'",
						syllabus.getSyllabusCode()));
			}
			break;
		case 1:
			sql.append("update base_syllabus");
			sql.append(String.format(" set syllabus_name = '%1$s',",
					syllabus.getSyllabusName()));
			// sql.append(String.format(" syllabus_english_name = '%1$s',",
			// syllabus.getSyllabusenglishname()));
			sql.append(String.format(" syllabus_type = '%1$s',",
					syllabus.getSyllabusType()));
			sql.append(String.format(" is_gb = '%1$s',", syllabus.getIsgb()));
			sql.append(String.format(" syllabus_credit = '%1$s',",
					syllabus.getSyllabuscredit()));
			sql.append(String.format(" textbook_code = '%1$s',",
					syllabus.getTextbookCode()));
			// sql.append(String.format(" syllabus_total_score = '%1$s',",
			// syllabus.getSyllabustotalscore()));
			// sql.append(String.format(" syllabus_pass_score = '%1$s',",
			// syllabus.getSyllabuspassscore()));
			sql.append(String.format(" is_use = '%1$s',", "1"));
			sql.append(String.format(" remarks = '%1$s'", syllabus.getRemarks()));
			sql.append(String.format(" where syllabus_code = '%1$s'",
					syllabus.getSyllabusCode()));
			break;
		default:
			break;
		}
		saveFlag = dbUtil.saveOrUpdate(sql.toString()) == 1;
		if (type == 1 && saveFlag
				&& !StringTool.isEmpty(syllabus.getTextbookCode())) {
			String unitaryBook = dbUtil
					.getString(String
							.format("select unified_book from base_textbook where textbook_code = '%1$s'",
									syllabus.getTextbookCode()));
			dbUtil.saveOrUpdate(String
					.format("update base_pro_syllabus set textbook_code = '%1$s', textbook_unitary = '%2$s', exam_unitary = '%3$s' where syllabus_code = '%4$s'",
							syllabus.getTextbookCode(), unitaryBook,
							syllabus.getIsgb(), syllabus.getSyllabusCode()));
		} else if (type == 1 && saveFlag) {
			dbUtil.saveOrUpdate(String
					.format("update base_pro_syllabus set exam_unitary = '%1$s' where syllabus_code = '%2$s'",
							syllabus.getIsgb(), syllabus.getSyllabusCode()));
		}
		return saveFlag;
	}

	/**
	 * 根据课程代码，查询课程表
	 * 
	 * @param avoidCode
	 *            层次编号
	 * @return
	 */
	public Syllabus qry(String syllabuscode) {
		Syllabus syllabus = new Syllabus();
		String sql = String
				.format("select d.*,(select t.textbook_name from base_textbook t where t.textbook_code=d.textbook_code) as textbook_name from base_syllabus d where d.syllabus_code = '%1$s'",
						syllabuscode);
		syllabus = dbUtil.getObject(sql, Syllabus.class);
		return syllabus;
	}

	// /**
	// * 根据课程代码，查询课程可撤回可撤回表
	// * @param avoidCode 层次编号
	// * @return
	// */
	// public Syllabus qrySUB(String syllabuscode) {
	// Syllabus syllabus = null;
	// String sql =
	// String.format("select * from base_substitute d where d.syllabus_code = '%1$s'",syllabuscode);
	// syllabus = dbUtil.getObject(sql, Syllabus.class);
	// return syllabus;
	// }

	/**
	 * 保存部门对象到数据库
	 * 
	 * @param syllabus
	 *            报考层次
	 * @param type
	 *            类型 0-insert, 1-update
	 * @return boolean
	 */
	public boolean savesyllabus(Syllabus syllabus, int type) {
		boolean saveFlag = false;
		StringBuilder sql = new StringBuilder();
		switch (type) {
		case 0:
			sql.append("insert into base_syllabus");
			sql.append("(syllabus_code,syllabus_name,syllabus_english_name,syllabus_type,is_gb,syllabus_credit,textbook_code,is_use,remarks)");
			sql.append(" values");
			sql.append(String
					.format(" ('%1$s','%2$s','%3$s','%4$s','%5$s','%6$s','%7$s','%8$s','%9$s')",
							syllabus.getSyllabusCode(),
							syllabus.getSyllabusName(),
							syllabus.getSyllabusenglishname(),
							syllabus.getSyllabusType(), syllabus.getIsgb(),
							syllabus.getSyllabuscredit(),
							syllabus.getTextbookCode(), "1",
							syllabus.getRemarks()));
			break;
		case 1:
			sql.append("update base_syllabus");
			sql.append(String.format(" set syllabus_name = '%1$s',",
					syllabus.getSyllabusName()));
			sql.append(String.format(" syllabus_english_name = '%1$s',",
					syllabus.getSyllabusenglishname()));
			sql.append(String.format(" syllabus_type = '%1$s',",
					syllabus.getSyllabusType()));
			sql.append(String.format(" is_gb = '%1$s',", syllabus.getIsgb()));
			sql.append(String.format(" syllabus_credit = '%1$s',",
					syllabus.getSyllabuscredit()));
			sql.append(String.format(" textbook_code = '%1$s',",
					syllabus.getTextbookCode()));
			// sql.append(String.format(" syllabus_total_score = '%1$s',",
			// syllabus.getSyllabustotalscore()));
			// sql.append(String.format(" syllabus_pass_score = '%1$s',",
			// syllabus.getSyllabuspassscore()));
			sql.append(String.format(" is_use = '%1$s',", "1"));
			sql.append(String.format(" remarks = '%1$s'", syllabus.getRemarks()));
			sql.append(String.format(" where syllabus_code = '%1$s'",
					syllabus.getSyllabusCode()));
			break;
		default:
			break;
		}
		saveFlag = dbUtil.saveOrUpdate(sql.toString()) == 1;
		return saveFlag;
	}

	/**
	 * 删除用户部门（如果已经设置职位，则必须先清除职位才能删除部门）
	 * 
	 * @param syllabusCode
	 * @return
	 */
	public boolean del(String syllabusCode) {
		boolean delFlag = false;
		StringBuilder sql = new StringBuilder();
		sql.append("delete from base_syllabus");
		sql.append(String.format(" where syllabus_code = '%1$s'", syllabusCode));
		delFlag = dbUtil.saveOrUpdate(sql.toString()) == 1;
		return delFlag;
	}

	public boolean isUseDel(String syllabusCode) {
		boolean delFlag = false;
		StringBuilder sql = new StringBuilder();
		sql.append("update base_syllabus b set b.IS_USE='0'");
		sql.append(String.format(" where b.syllabus_code = '%1$s'",
				syllabusCode));
		delFlag = dbUtil.saveOrUpdate(sql.toString()) == 1;
		return delFlag;
	}

	/*
	 * 得到唯一不重复的时间列表
	 */
	public ArrayList<SingleEntity> getPublishTimeList() {
		ArrayList<SingleEntity> publistTimes = new ArrayList<SingleEntity>();
		String sql = "select count(distinct(publish_time)) from BASE_TEXTBOOK t";
		String sql1 = "select distinct(publish_time) from BASE_TEXTBOOK t";
		String[] ps = dbUtil.getRsArray(sql, sql1);
		for (int i = 0; i < ps.length; i++) {
			publistTimes.add(new SingleEntity(ps[i], ps[i]));
		}
		return publistTimes;
	}

	/*
	 * 得到唯一不重复的出生版社列表
	 */
	public ArrayList<SingleEntity> getPublishList() {
		ArrayList<SingleEntity> publists = new ArrayList<SingleEntity>();
		String sql = "select count(distinct(textbook_publisher)) from BASE_TEXTBOOK t";
		String sql1 = "select distinct(textbook_publisher) from BASE_TEXTBOOK t";
		String[] ps = dbUtil.getRsArray(sql, sql1);
		for (int i = 0; i < ps.length; i++) {
			publists.add(new SingleEntity(ps[i], ps[i]));
		}
		return publists;
	}
}