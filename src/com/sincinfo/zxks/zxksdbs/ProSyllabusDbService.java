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

import java.util.ArrayList;
import java.util.List;

import com.sincinfo.zxks.bean.BasePro;
import com.sincinfo.zxks.bean.GrpSet;
import com.sincinfo.zxks.bean.PlanLevel;
import com.sincinfo.zxks.bean.ProSeq;
import com.sincinfo.zxks.bean.ProSyllabus;
import com.sincinfo.zxks.bean.ProType;
import com.sincinfo.zxks.bean.Syllabus;
import com.sincinfo.zxks.common.db.DbUtil;
import com.sincinfo.zxks.common.util.Page;
import com.sincinfo.zxks.common.util.StringTool;

/**
 * @ClassName: ProSyllabusDbService
 * @Description: 专业课程 <br>
 * @author yuansh
 * @date 2012-01-26 15:58<br>
 * 
 */
public class ProSyllabusDbService {

	private DbUtil dbUtil = null;

	/**
	 * 构造
	 */
	public ProSyllabusDbService() {
		this.dbUtil = new DbUtil();
	}

	/**
	 * 根据条件查询对应的所有专业设置列表，若不加限制则传递“0”
	 * 
	 * @param code
	 *            暂未用不用传值
	 * @param page
	 *            分页对象
	 * @return
	 */
	public List<BasePro> qry(BasePro basepro, Page page) {
		List<BasePro> basepros = null;
		StringBuilder sql = new StringBuilder();
		StringBuilder sqlCount = new StringBuilder();
		sql.append("select d.*,(select l.pro_type_name from base_pro_type l where l.pro_type_code=d.pro_type_code) as pro_type_name,"
				+ "(select m.pro_part_name from base_pro_partitions m where m.pro_part_code=d.pro_part_code) as pro_part_name,(select count(*) from base_pro_syllabus s where s.pro_code=d.pro_code)syllabus_set from base_pro d where 1=1 ");
		sqlCount.append("select count(*) from base_pro d where 1=1 ");
		if (!StringTool.isEmpty(basepro)) {
			if (!StringTool.isEmpty(basepro.getProCode())) {
				sql.append(String.format(" and pro_code like '%%%1$s%%'",
						basepro.getProCode()));
				sqlCount.append(String.format(" and pro_code like '%%%1$s%%'",
						basepro.getProCode()));
			}
			if (!StringTool.isEmpty(basepro.getProName())) {
				sql.append(String.format(" and pro_name like '%%%1$s%%'",
						basepro.getProName()));
				sqlCount.append(String.format(" and pro_name like '%%%1$s%%'",
						basepro.getProName()));
			}
			if (!StringTool.isEmpty(basepro.getProTypecode())) {
				sql.append(String.format(" and d.pro_type_code = '%1$s'",
						basepro.getProTypecode()));
				sqlCount.append(String.format(" and d.pro_type_code = '%1$s'",
						basepro.getProTypecode()));
			}
			if (!StringTool.isEmpty(basepro.getProPartcode())) {
				sql.append(String.format(" and d.pro_part_code = '%1$s'",
						basepro.getProPartcode()));
				sqlCount.append(String.format(" and d.pro_part_code = '%1$s'",
						basepro.getProPartcode()));
			}
			if (!StringTool.isEmpty(basepro.getLevelCode())) {
				sql.append(String.format(" and level_code = '%1$s'",
						basepro.getLevelCode()));
				sqlCount.append(String.format(" and level_code = '%1$s'",
						basepro.getLevelCode()));
			}
		}
		sql.append(" order by d.pro_code asc");

		/*
		 * 分页查询
		 */
		String sqlPage = page.setPagecount(dbUtil.getNum(sqlCount.toString()),
				sql.toString());
		basepros = dbUtil.getObjList(sqlPage, BasePro.class);
		return basepros;
	}

	/**
	 * 根据编号，查询报考层次
	 * 
	 * @param proCode
	 *            专业编号
	 * @return
	 */
	public BasePro qry(String proCode) {
		BasePro basepro = null;
		String sql = String
				.format("select d.*,(select l.pro_type_name from base_pro_type l where l.pro_type_code=d.pro_type_code) as pro_type_name,"
						+ "(select m.pro_part_name from base_pro_partitions m where m.pro_part_code=d.pro_part_code) as pro_part_name from base_pro d where d.pro_code = '%1$s'",
						proCode);
		basepro = dbUtil.getObject(sql, BasePro.class);
		return basepro;
	}

	/**
	 * 根据编号，查询课程分组
	 * 
	 * @param proCode
	 *            分组编号
	 * @return
	 */
	public GrpSet qryGroup(String syllabus_group_code) {
		GrpSet gs = null;
		String sql = String
				.format("select d.*  from base_syllabus_group d where d.syllabus_group_code = '%1$s'",
						syllabus_group_code);
		gs = dbUtil.getObject(sql, GrpSet.class);
		return gs;
	}

	/**
	 * 根据编号，查询专业课程
	 * 
	 * @param proCode
	 *            专业编号
	 * @return
	 */
	public List<ProSyllabus> qryProSyllabus(BasePro basepro, Page page) {
		List<ProSyllabus> syls = null;
		StringBuilder sql = new StringBuilder();
		StringBuilder sqlCount = new StringBuilder();
		sql.append(String
				.format("select d.*,s.syllabus_name from base_pro_syllabus d,base_syllabus s where s.syllabus_code=d.syllabus_code and d.pro_code= '%1$s'",
						basepro.getProCode()));
		sqlCount.append(String
				.format("select count(*) from base_pro_syllabus d where 1=1 and pro_code= '%1$s'",
						basepro.getProCode()));
		sql.append(" order by d.substitute_code asc");

		/**
		 * 分页查询
		 * */
		// String sqlPage =
		// page.setPagecount(dbUtil.getNum(sqlCount.toString()),
		// sql.toString());
		syls = dbUtil.getObjList(sql.toString(), ProSyllabus.class);
		return syls;
	}

	/**
	 * 保存部门对象到数据库
	 * 
	 * @param basepro
	 *            专业
	 * @param type
	 *            类型 0-insert, 1-update
	 * @return boolean
	 */
	public boolean save(ProSyllabus proSyllabus, int type) {
		boolean saveFlag = false;
		StringBuilder sql = new StringBuilder();
		// if(StringTool.isEmpty(proSyllabus.getSyllabusGroupCode()))proSyllabus.setSyllabusGroupCode(null);
		// if(StringTool.isEmpty(proSyllabus.getProSyllabusTypeCode()))proSyllabus.setProSyllabusTypeCode(null);
		String xh = dbUtil
				.getString("select seq_pro_syllabus.nextval from dual");
		switch (type) {
		case 0:
			String ordernum = dbUtil
					.getString(String
							.format("select max(syllabus_order+1) from base_pro_syllabus where pro_code='%1$s'",
									proSyllabus.getProCode()));
			if (StringTool.isEmpty(ordernum)) {
				ordernum = "0";
			}
			sql.append("insert into base_pro_syllabus");
			sql.append("(substitute_code,pro_code,syllabus_code,syllabus_credit,textbook_code,textbook_unitary,exam_unitary,syllabus_order,syllabus_remark,cert_syllabus)");
			sql.append(" values");// syllabus_group_code,
			sql.append(String
					.format("('%1$s','%2$s','%3$s','%4$s','%5$s','%6$s','%7$s','%8$s','%9$s','%10$s')",
							xh,
							proSyllabus.getProCode(),
							proSyllabus.getSyllabusCode(),
							proSyllabus.getSyllabusCredit(),// proSyllabus.getSyllabusGroupCode(),
							proSyllabus.getTextbookCode(),
							proSyllabus.getTextbookUnitary(),
							proSyllabus.getExamUnitary(), ordernum,
							proSyllabus.getSyllabusRemark(),
							proSyllabus.getCertSyllabus()));
			break;
		case 1:
			sql.append("update base_pro_syllabus set");
			sql.append(String.format(" syllabus_code = '%1$s',",
					proSyllabus.getSyllabusCode()));
			sql.append(String.format(" syllabus_credit = '%1$s',",
					proSyllabus.getSyllabusCredit()));
			sql.append(String.format(" textbook_code = '%1$s',",
					proSyllabus.getTextbookCode()));
			sql.append(String.format(" textbook_unitary = '%1$s',",
					proSyllabus.getTextbookUnitary()));
			sql.append(String.format(" syllabus_remark = '%1$s',",
					proSyllabus.getSyllabusRemark()));
			sql.append(String.format(" cert_syllabus = '%1$s',",
					proSyllabus.getCertSyllabus()));
			sql.append(String.format(" exam_unitary = '%1$s'",
					proSyllabus.getExamUnitary()));
			sql.append(String.format(" where substitute_code = '%1$s'",
					proSyllabus.getSubstituteCode()));
			sql.append(String.format(" and pro_code = '%1$s'",
					proSyllabus.getProCode()));
			break;
		default:
			break;
		}
		saveFlag = dbUtil.saveOrUpdate(sql.toString()) == 1;
		return saveFlag;
	}

	/**
	 * 保存分组中的课程
	 * 
	 * @param basepro
	 *            专业
	 * @return boolean
	 */
	public boolean saveGrpSyllabus(ProSyllabus proSyllabus) {
		boolean saveFlag = false;
		// if(StringTool.isEmpty(proSyllabus.getSyllabusGroupCode()))proSyllabus.setSyllabusGroupCode(null);
		// if(StringTool.isEmpty(proSyllabus.getProSyllabusTypeCode()))proSyllabus.setProSyllabusTypeCode(null);
		if (StringTool.isEmpty(proSyllabus.getSyllabusCode())) {
			StringBuilder sql = new StringBuilder();
			sql.append("update base_pro_syllabus set ");
			sql.append(" syllabus_group_code = '' ");
			sql.append(String.format(" where pro_code = '%1$s' and ",
					proSyllabus.getProCode()));
			sql.append(String.format(" syllabus_group_code = '%1$s' ",
					proSyllabus.getSyllabusGroupCode()));
			saveFlag = dbUtil.saveOrUpdate(sql.toString()) >= 0;
		} else {
			String[] dataCheckeds = proSyllabus.getSyllabusCode().split(", ");
			for (int i = 0; i < dataCheckeds.length; i++) {
				StringBuilder sql = new StringBuilder();
				sql.append("update base_pro_syllabus set ");
				sql.append(String.format(" syllabus_group_code = '%1$s' ",
						proSyllabus.getSyllabusGroupCode()));
				sql.append(String.format(" where pro_code = '%1$s' and ",
						proSyllabus.getProCode()));
				sql.append(String.format("  syllabus_code = '%1$s' ",
						dataCheckeds[i]));
				// System.out.println(sql.toString());
				saveFlag = dbUtil.saveOrUpdate(sql.toString()) == 1;
			}
		}
		return saveFlag;
	}

	/**
	 * 查询专业课程
	 * 
	 * @param substituteCode
	 * @param proCode
	 * @return
	 */
	public ProSyllabus getProSyllabus(String substituteCode, String proCode) {
		StringBuilder sql = new StringBuilder();
		sql.append("select bps.substitute_code, bps.pro_code, bps.syllabus_code, bs.syllabus_name,");
		sql.append(" bps.syllabus_credit, bps.textbook_code, bt.textbook_name, bps.textbook_unitary,");
		sql.append(" bps.exam_unitary, bps.syllabus_remark, bps.cert_syllabus");
		sql.append(" from base_pro_syllabus bps");
		sql.append(" left join base_syllabus bs on bps.syllabus_code = bs.syllabus_code");
		sql.append(" left join base_textbook bt on bps.textbook_code = bt.textbook_code");
		sql.append(String.format(" where bps.substitute_code = '%1$s'",
				substituteCode));
		sql.append(String.format(" and bps.pro_code = '%1$s'", proCode));
		return dbUtil.getObject(sql.toString(), ProSyllabus.class);
	}

	/**
	 * 删除专业课程
	 * 
	 * @param substitute_code
	 * @return
	 */
	public boolean del(String substitute_code) {
		boolean delFlag = false;
		StringBuilder sql = new StringBuilder();
		sql.append("delete from base_pro_syllabus");
		sql.append(String.format(" where substitute_code = '%1$s'",
				substitute_code));
		delFlag = dbUtil.saveOrUpdate(sql.toString()) >= 0;
		return delFlag;
	}

	/**
	 * 清除专业中的课程及分组信息
	 * 
	 * @param proCode
	 * @return
	 */
	public boolean Clear(String pro_code) {
		boolean delFlag = false;
		StringBuilder sql = new StringBuilder();
		sql.append("delete from base_pro_syllabus");
		sql.append(String.format(" where pro_code = '%1$s'", pro_code));
		delFlag = dbUtil.saveOrUpdate(sql.toString()) >= 0;
		StringBuilder sql1 = new StringBuilder();
		sql1.append("delete from base_syllabus_group");
		sql1.append(String.format(" where pro_code = '%1$s'", pro_code));
		delFlag = dbUtil.saveOrUpdate(sql1.toString()) >= 0;
		return delFlag;
	}

	/**
	 * 查询专业类型列表
	 * 
	 * @return List<ProType>
	 */
	public List<ProType> qryProTypeClasses() {
		List<ProType> pList = null;
		StringBuilder sql = new StringBuilder();
		sql.append("select pro_type_code, pro_type_name from base_pro_type order by pro_type_code ");
		pList = dbUtil.getObjList(sql.toString(), ProType.class);
		return pList;
	}

	/**
	 * 查询专业分类列表
	 * 
	 * @return List<ProSeq>
	 */
	public List<ProSeq> qryProSeqClasses() {
		List<ProSeq> pList = null;
		StringBuilder sql = new StringBuilder();
		sql.append("select pro_part_code, pro_part_name from base_pro_partitions order by pro_part_code ");
		pList = dbUtil.getObjList(sql.toString(), ProSeq.class);
		return pList;
	}

	/**
	 * 查询层次列表
	 * 
	 * @return List<PlanLevel>
	 */
	public List<PlanLevel> qryPlanLevelClasses() {
		List<PlanLevel> pList = null;
		StringBuilder sql = new StringBuilder();
		sql.append("select level_code,level_name from base_level order by level_code");
		pList = dbUtil.getObjList(sql.toString(), PlanLevel.class);
		return pList;
	}

	/**
	 * 查询课程列表
	 * 
	 * @return List<Syllabus>
	 */
	public List<Syllabus> qrysyllabusClasses() {
		List<Syllabus> SList = null;
		StringBuilder sql = new StringBuilder();
		sql.append("select syllabus_code,syllabus_name from base_level order by level_code");
		SList = dbUtil.getObjList(sql.toString(), Syllabus.class);
		return SList;
	}

	/**
	 * 根据protype_code得到level_code
	 * 
	 * @param proCode
	 * @return
	 */
	public String getLeveCode(String protypeCode) {
		String leve_code = "";
		StringBuilder sql = new StringBuilder();
		sql.append("select level_code from base_pro_type ");
		sql.append(String.format(" where pro_type_code= '%1$s'", protypeCode));
		leve_code = dbUtil.getString(sql.toString());
		return leve_code;
	}

	/**
	 * 根据编号，查询报考层次
	 * 
	 * @param proCode
	 *            专业编号
	 * @return
	 */
	public BasePro getBasePro(String proCode) {
		StringBuilder sql = new StringBuilder();
		sql.append("select bp.*, bpp.pro_part_name as pro_part_name, bpt.pro_type_name as pro_type_name");
		sql.append(" from base_pro bp");
		sql.append(" left join base_pro_partitions bpp on bp.pro_part_code = bpp.pro_part_code");
		sql.append(" left join base_pro_type bpt on bp.pro_type_code = bpt.pro_type_code");
		sql.append(String.format(" where bp.pro_code = '%1$s'", proCode));
		return dbUtil.getObject(sql.toString(), BasePro.class);
	}

	/**
	 * 获取专业课程列表
	 * 
	 * @param proCode
	 *            专业Id
	 * @return
	 */
	public List<ProSyllabus> getProSyllabus(String proCode) {
		StringBuilder sql = new StringBuilder();
		sql.append("select bps.substitute_code, bps.pro_code, bps.syllabus_code, bs.syllabus_name,");
		sql.append(" bps.syllabus_credit, bps.textbook_code, bt.textbook_name, bps.textbook_unitary,");
		sql.append(" bps.exam_unitary");
		sql.append(" from base_pro_syllabus bps");
		sql.append(" left join base_syllabus bs on bps.syllabus_code = bs.syllabus_code");
		sql.append(" left join base_textbook bt on bps.textbook_code = bt.textbook_code");
		sql.append(String.format(" where bps.pro_code = '%1$s'", proCode));
		sql.append(" order by bps.syllabus_order");
		return dbUtil.getObjList(sql.toString(), ProSyllabus.class);
	}

	/**
	 * 专业课程排序处理
	 * 
	 * @param substituteCode
	 * @param proCode
	 * @return
	 */
	public boolean syllabausOrder(String substituteCode, String proCode) {
		boolean falg = true;
		ArrayList<String> sqlList = new ArrayList<String>();
		String[] orders = substituteCode.split(",");
		String sql = "update base_pro_syllabus set syllabus_order = '%1$s' where substitute_code = '%2$s' and pro_code = '%3$s'";
		for (int i = 0; i < orders.length; i++) {
			sqlList.add(String.format(sql, i, orders[i], proCode));
		}
		falg = dbUtil.transExeSqls(sqlList) > 0;
		return falg;
	}

}
