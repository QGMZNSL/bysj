/**
 * Copyright(c) 2012 西安云海信息技术有限责任公司 All Rights Reserved<br>
 * @Title: com.sincinfo.zxks.zxksdbs.DayDbService.java<br>
 * @Description: 分组管理 <br>
 * <br>
 * @author yuansh<br>
 * @date 2012-01-26 15:58
 * @version V1.0   
 */
package com.sincinfo.zxks.zxksdbs;

import java.util.List;

import com.sincinfo.zxks.bean.GrpSet;
import com.sincinfo.zxks.common.db.DbUtil;
import com.sincinfo.zxks.common.util.Page;
import com.sincinfo.zxks.common.util.StringTool;

/**
 * @ClassName: PlanDbService
 * @Description: 课程分组设置 <br>
 * @author yuansh
 * @date 2012-01-26 15:58<br>
 * 
 */
public class GrpSetDbService {
	private DbUtil dbUtil = null;

	/**
	 * 构造
	 */
	public GrpSetDbService() {
		this.dbUtil = new DbUtil();
	}

	/**
	 * 根据条件查询对应的所有分组列表，若不加限制则传递“0”
	 * @param code 暂未用不用传值
	 * @param page 分页对象
	 * @return
	 */
	public List<GrpSet> qry(GrpSet grpSet, Page page) {
		List<GrpSet> grpSetList = null;
		StringBuilder sql = new StringBuilder();
		StringBuilder sqlCount = new StringBuilder();
		//sql.append("select d.*,(select p.pro_name from base_pro p where p.pro_code=d.pro_code)pro_name from base_syllabus_group d where 1=1 ");
		sql.append("select d.*,p.pro_name from base_syllabus_group d left join base_pro p on p.pro_code=d.pro_code where 1=1 ");
		sqlCount.append("select count(*) from base_syllabus_group d where 1=1 ");
		if(!StringTool.isEmpty(grpSet)){//&&!StringTool.isEmpty(GrpSet.getProName())
			if(!StringTool.isEmpty(grpSet.getProCode())){
				sql.append(String.format(" and d.pro_code = '%1$s'",grpSet.getProCode()));
				sqlCount.append(String.format(" and d.pro_code = '%1$s'",grpSet.getProCode()));
			}			
			if(!StringTool.isEmpty(grpSet.getGroupCode())){
				sql.append(String.format(" and syllabus_group_code like '%%%1$s%%'",grpSet.getGroupCode()));
				sqlCount.append(String.format(" and syllabus_group_code like '%%%1$s%%'",grpSet.getGroupCode()));
			}
			if(!StringTool.isEmpty(grpSet.getSyllabusGroupName())){
				sql.append(String.format(" and syllabus_group_name like '%%%1$s%%'",grpSet.getSyllabusGroupName()));
				sqlCount.append(String.format(" and syllabus_group_name like '%%%1$s%%'",grpSet.getSyllabusGroupName()));
			}
			if(!StringTool.isEmpty(grpSet.getProName())){
				sql.append(String.format(" and pro_name like '%%%1$s%%'",grpSet.getProName()));
//				sqlCount.append(String.format(" and pro_name like '%%%1$s%%'",GrpSet.getProName()));
			}
		}else{
			return grpSetList;
		}
		sql.append(" order by d.syllabus_group_code asc");

		// 分页查询
		String sqlPage = page.setPagecount(dbUtil.getNum(sqlCount.toString()), sql.toString());
		grpSetList = dbUtil.getObjList(sqlPage, GrpSet.class);
		return grpSetList;
	}
	
	/**
	 * 供设置子组用
	 * @param code 暂未用不用传值
	 * @param page 分页对象
	 * @return
	 */
	public List<GrpSet> qry1(GrpSet GrpSet, Page page) {
		List<GrpSet> GrpSets = null;
		StringBuilder sql = new StringBuilder();
		StringBuilder sqlCount = new StringBuilder();
		sql.append("select d.* from base_syllabus_group d where 1=1 ");
		sqlCount.append("select count(*) from base_syllabus_group d where 1=1 ");
		if(!StringTool.isEmpty(GrpSet)){
			if(!StringTool.isEmpty(GrpSet.getProCode())){
				sql.append(String.format(" and pro_code = '%1$s'",GrpSet.getProCode()));
				sqlCount.append(String.format(" and pro_code = '%1$s'",GrpSet.getProCode()));
			}
		}
		sql.append(" order by d.syllabus_group_code asc");

		// 分页查询
		String sqlPage = page.setPagecount(dbUtil.getNum(sqlCount.toString()), sql.toString());
		GrpSets = dbUtil.getObjList(sqlPage, GrpSet.class);
		return GrpSets;
	}	

	/**
	 * 根据编号，查询分组
	 * @param syllabusGroupCode
	 * 分组编号
	 * @return
	 */
	public GrpSet qry(String syllabusGroupCode) {
		GrpSet grpSet = null;
		String sql = String.format("select * from base_syllabus_group d where d.syllabus_group_code = '%1$s'",syllabusGroupCode);
		grpSet = dbUtil.getObject(sql, GrpSet.class);
		return grpSet;
	}

	/**
	 * 保存部门对象到数据库
	 * 
	 * @param GrpSet 分组
	 * @param type   类型 0-insert, 1-update
	 * @return boolean
	 */
	public boolean save(GrpSet GrpSet, int type) {
		boolean saveFlag = false;
		StringBuilder sql = new StringBuilder();
		String xh=dbUtil.getString("select seq_syllabus_group.nextval from dual");
		if(StringTool.isEmpty(GrpSet.getIsGroup()))GrpSet.setIsGroup("0");
		switch (type) {
		case 0:
			sql.append("insert into base_syllabus_group");
			sql.append("(syllabus_group_code,syllabus_group_name,syllabus_sep_code,is_group,group_code,pro_code,remarks)");
			sql.append(" values");
			sql.append(String.format(" ('%1$s','%2$s','%3$s','%4$s','%5$s','%6$s','%7$s')",
					xh,GrpSet.getSyllabusGroupName(), GrpSet.getSyllabusSepCode(),GrpSet.getIsGroup(),
					GrpSet.getGroupCode(),GrpSet.getProCode(),GrpSet.getRemarks()));
			break;
		case 1:
			sql.append("update base_syllabus_group");
			sql.append(String.format(" set syllabus_group_name = '%1$s',",GrpSet.getSyllabusGroupName()));
			sql.append(String.format(" syllabus_sep_code = '%1$s',",GrpSet.getSyllabusSepCode()));
			sql.append(String.format(" is_group = '%1$s',",GrpSet.getIsGroup()));
			sql.append(String.format(" group_code = '%1$s',",GrpSet.getGroupCode()));
			sql.append(String.format(" pro_code = '%1$s',",GrpSet.getProCode()));
			sql.append(String.format(" remarks = '%1$s'", GrpSet.getRemarks()));
			sql.append(String.format(" where syllabus_group_code = '%1$s'", GrpSet.getSyllabusGroupCode()));
			break;
		default:
			break;
		}
		saveFlag = dbUtil.saveOrUpdate(sql.toString()) == 1;
		return saveFlag;
	}
	
	/**
	 * 保存群组中的分组
	 * @param basepro 专业
	 * @return boolean
	 */
	public boolean saveGroupGrp(GrpSet grpSet) {
		boolean saveFlag = false;
//		if(StringTool.isEmpty(proSyllabus.getSyllabusGroupCode()))proSyllabus.setSyllabusGroupCode(null);
//		if(StringTool.isEmpty(proSyllabus.getProSyllabusTypeCode()))proSyllabus.setProSyllabusTypeCode(null);
		String[] dataCheckeds=grpSet.getSyllabusGroupCode().split(", ");
        for(int i=0;i<dataCheckeds.length;i++){
        	StringBuilder sql = new StringBuilder();
		    sql.append("update base_syllabus_group set ");
			sql.append(String.format(" group_code = '%1$s',is_group='1' ", grpSet.getGroupCode()));
			sql.append(String.format(" where pro_code = '%1$s' and ", grpSet.getProCode()));
			sql.append(String.format("  syllabus_group_code = '%1$s' ", dataCheckeds[i]));
			System.out.println(sql.toString());
			saveFlag = dbUtil.saveOrUpdate(sql.toString()) == 1;
        }
		return saveFlag;
	}	

	/**
	 * 删除用户部门（如果已经设置职位，则必须先清除职位才能删除部门）
	 * 
	 * @param syllabusGroupCode
	 * @return
	 */
	public boolean del(String syllabusGroupCode) {
		boolean delFlag = false;
		StringBuilder sql = new StringBuilder();
		sql.append("delete from base_syllabus_group");
		sql.append(String.format(" where syllabus_group_code = '%1$s'",syllabusGroupCode));
		delFlag = dbUtil.saveOrUpdate(sql.toString()) == 1;
		return delFlag;
	}
	
}
