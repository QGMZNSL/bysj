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

import java.util.List;

import com.sincinfo.zxks.bean.GraduateCondition;
import com.sincinfo.zxks.bean.GraduateGroup;
import com.sincinfo.zxks.bean.GraduateNeed;
import com.sincinfo.zxks.common.db.DbUtil;
import com.sincinfo.zxks.common.util.Page;
import com.sincinfo.zxks.common.util.StringTool;

/**
 * @ClassName: PlanDbService
 * @Description: 毕业条件设置 <br>
 * @author yuansh
 * @date 2012-01-26 15:58<br>
 */
public class GraduateConditionDbService {
	private DbUtil dbUtil = null;

	/**
	 * 构造
	 */
	public GraduateConditionDbService() {
		this.dbUtil = new DbUtil();
	}

	/**
	 * 根据条件查询对应的毕业条件列表，若不加限制则传递null
	 * @param code 暂未用不用传值
	 * @param page 分页对象
	 * @return
	 */
	public List<GraduateCondition> qry(GraduateCondition graduateCondition, Page page) {
		List<GraduateCondition> graduateConditionList = null;
		StringBuilder sql = new StringBuilder();
		StringBuilder sqlCount = new StringBuilder();
		sql.append("select d.* from base_graduate_condition d where 1=1 ");
		sqlCount.append("select count(*) from base_graduate_condition d where 1=1 ");
		sql.append(" order by d.pro_code asc");
		String sqlPage = page.setPagecount(dbUtil.getNum(sqlCount.toString()), sql.toString());
		graduateConditionList = dbUtil.getObjList(sqlPage, GraduateCondition.class);
		return graduateConditionList;
	}
	
	/**
	 * 根据条件查询对应的毕业条件列表，若不加限制则传递null
	 * @param code 暂未用不用传值
	 * @param page 分页对象
	 * @return
	 */
	public List<GraduateGroup> qry1(String proCode, Page page) {
		List<GraduateGroup> graduateGroupList = null;
		String sql=String.format("select d.*,(select s.syllabus_name from base_syllabus s where s.syllabus_code=d.syllabus_code)syllabus_name from base_graduate_group d where d.pro_code = '%1$s' order by d.pro_code asc",proCode);
		String sqlCount=String.format("select count(*) from base_graduate_group d where d.pro_code = '%1$s'",proCode);
		String sqlPage = page.setPagecount(dbUtil.getNum(sqlCount.toString()), sql.toString());
		graduateGroupList = dbUtil.getObjList(sqlPage,GraduateGroup.class);
		return graduateGroupList;
	}
	
	/**
	 * 根据条件查询对应的毕业条件列表，若不加限制则传递null
	 * @param code 暂未用不用传值
	 * @param page 分页对象
	 * @return
	 */
	public List<GraduateNeed> qry2(String proCode, Page page) {
		List<GraduateNeed> GraduateNeedList = null;
		String sql=String.format("select d.* from base_graduate_need d where d.pro_code = '%1$s' order by d.pro_code asc",proCode);
		String sqlCount=String.format("select count(*) from base_graduate_need d where d.pro_code = '%1$s'",proCode);
		String sqlPage = page.setPagecount(dbUtil.getNum(sqlCount.toString()), sql.toString());
		GraduateNeedList = dbUtil.getObjList(sqlPage, GraduateNeed.class);
		return GraduateNeedList;
	}	

	/**
	 * 根据编号，查询毕业条件
	 * @param proCode 专业编号
	 * @return
	 */
	public GraduateCondition qry(String proCode) {
		GraduateCondition graduateCondition = null;
		String sql = String.format("select * from base_graduate_condition d where d.pro_code = '%1$s'",proCode);
		graduateCondition = dbUtil.getObject(sql, GraduateCondition.class);
		return graduateCondition;
	}
	
	/**
	 * 保存部门对象到数据库
	 * @param graduateCondition 报考层次
	 * @param type 类型 0-insert, 1-update
	 * @return boolean
	 */
	public boolean save(GraduateCondition graduateCondition, int type) {
		boolean saveFlag = false;
		StringBuilder sql = new StringBuilder();
		switch (type) {
		case 0:
			sql.append("insert into base_graduate_condition");
			sql.append("(pro_code, graduate_condition_credit, graduate_condition_describe)");
			sql.append(" values");
			sql.append(String.format(" ('%1$s','%2$s','%3$s')",
					graduateCondition.getProCode(),graduateCondition.getGraduateConditionCredit(), graduateCondition.getGraduateConditionDescribe()));
			break;
		case 1:
			sql.append("update base_graduate_condition");
			sql.append(String.format(" set graduate_condition_credit = '%1$s',",graduateCondition.getGraduateConditionCredit()));
			sql.append(String.format(" graduate_condition_describe = '%1$s'", graduateCondition.getGraduateConditionDescribe()));
			sql.append(String.format(" where pro_code = '%1$s'", graduateCondition.getProCode()));
			break;
		default:
			break;
		}
		saveFlag = dbUtil.saveOrUpdate(sql.toString()) == 1;
		return saveFlag;
	}
	
	/**
	 * 保存部门对象到数据库
	 * @param graduateCondition 报考层次
	 * @return boolean
	 */
	public boolean save1(GraduateGroup graduateGroup) {
		boolean saveFlag = false;
		String xh= dbUtil.getString("select SEQ_GRADUATE_GROUP.nextval from dual");
		String[] ggcs=graduateGroup.getGraduateGroupCredit().split(",");
		String   ggc="0";
		if("1".equals(graduateGroup.getGraduateGroupTypeCode()))
			ggc="0";
		else if("2".equals(graduateGroup.getGraduateGroupTypeCode()))
			ggc=ggcs[0];
		else if("3".equals(graduateGroup.getGraduateGroupTypeCode()))
			ggc=ggcs[1]; 
		StringBuilder sql = new StringBuilder();
		sql.append("insert into base_graduate_group");
		sql.append("(graduate_group_code,pro_code,syllabus_group_code,graduate_group_type_code,syllabus_code,graduate_group_credit)");
		sql.append(" values");
		sql.append(String.format(" ('%1$s','%2$s','%3$s','%4$s','%5$s','%6$s')",
				xh,graduateGroup.getProCode(),graduateGroup.getSyllabusGroupCode(),graduateGroup.getGraduateGroupTypeCode(),
				graduateGroup.getSyllabusCode(),ggc));
		saveFlag = dbUtil.saveOrUpdate(sql.toString()) == 1;
		return saveFlag;
	}
	
	/**
	 * 保存部门对象到数据库
	 * @param graduateCondition 报考层次
	 * @return boolean
	 */
	public boolean save2(GraduateNeed graduateNeed) {
		boolean saveFlag = false;
		String xh= dbUtil.getString("select SEQ_GRADUATE_NEED.nextval from dual");
		StringBuilder sql = new StringBuilder();
		sql.append("insert into base_graduate_need");
		sql.append("(graduate_need_code, pro_code, graduate_need_name, is_must)");
		sql.append(" values");
		sql.append(String.format(" ('%1$s','%2$s','%3$s','%4$s')",
				xh,graduateNeed.getProCode(),graduateNeed.getGraduateNeedName(),graduateNeed.getIsMust()));
		saveFlag = dbUtil.saveOrUpdate(sql.toString()) == 1;
		return saveFlag;
	}

	/**
	 * 删除
	 * @param proCode
	 * @return
	 */
	public boolean del(String proCode) {
		boolean delFlag = false;
		StringBuilder sql = new StringBuilder();
		sql.append("delete from base_graduate_condition");
		sql.append(String.format(" where pro_code = '%1$s'",proCode));
		delFlag = dbUtil.saveOrUpdate(sql.toString()) == 1;
		return delFlag;
	}
	
	/**
	 * 删除
	 * @param proCode
	 * @return
	 */
	public boolean del1(String rid) {
		boolean delFlag = false;
		StringBuilder sql = new StringBuilder();
		sql.append("delete base_graduate_group ");
		sql.append(String.format(" where graduate_group_code = '%1$s'",rid));
		delFlag = dbUtil.saveOrUpdate(sql.toString()) == 1;
		return delFlag;
	}
	
	/**
	 * 删除
	 * @param proCode
	 * @return
	 */
	public boolean del2(String rid) {
		boolean delFlag = false;
		StringBuilder sql = new StringBuilder();
		sql.append("delete from base_graduate_need");
		sql.append(String.format(" where graduate_need_code = '%1$s'",rid));
		delFlag = dbUtil.saveOrUpdate(sql.toString()) == 1;
		return delFlag;
	}	
	
}
