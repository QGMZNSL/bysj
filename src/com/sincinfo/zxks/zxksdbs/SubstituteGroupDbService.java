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

import com.sincinfo.zxks.bean.SubstituteGroup;
import com.sincinfo.zxks.common.db.DbUtil;
import com.sincinfo.zxks.common.util.Page;
import com.sincinfo.zxks.common.util.StringTool;

/**
 * @ClassName: SubstituteGroupDbService
 * @Description: 顶替分组管理
 * @author yuansh
 * @date 2012-01-26 15:58
 */
public class SubstituteGroupDbService {
	private DbUtil dbUtil = null;

	/**
	 * 构造
	 */
	public SubstituteGroupDbService() {
		this.dbUtil = new DbUtil();
	}

	/**
	 * 根据条件查询对应的所有报考层次列表，若不加限制则传递“0”
	 * @param code 暂未用不用传值
	 * @param page 分页对象
	 * @return
	 */
	public List<SubstituteGroup> qry(SubstituteGroup substituteGroup, Page page) {
		List<SubstituteGroup> substituteGroups = null;
		StringBuilder sql = new StringBuilder();
		StringBuilder sqlCount = new StringBuilder();
		sql.append("select d.* from base_substitute_group d where 1=1 ");
		sqlCount.append("select count(*) from base_substitute_group d where 1=1 ");
		if(!StringTool.isEmpty(substituteGroup)){
			if(!StringTool.isEmpty(substituteGroup.getSubstituteGroupId())){
				sql.append(String.format(" and substitute_group_id like '%%%1$s%%'",substituteGroup.getSubstituteGroupId()));
				sqlCount.append(String.format(" and substitute_group_id like '%%%1$s%%'",substituteGroup.getSubstituteGroupId()));
			}
			if(!StringTool.isEmpty(substituteGroup.getSubstituteGroupName())){
				sql.append(String.format(" and substitute_group_name like '%%%1$s%%'",substituteGroup.getSubstituteGroupName()));
				sqlCount.append(String.format(" and substitute_group_name like '%%%1$s%%'",substituteGroup.getSubstituteGroupName()));
			}
		}
		sql.append(" order by d.substitute_group_id asc");

		// 分页查询
		String sqlPage = page.setPagecount(dbUtil.getNum(sqlCount.toString()), sql.toString());
		substituteGroups = dbUtil.getObjList(sqlPage, SubstituteGroup.class);
		return substituteGroups;
	}

	/**
	 * 根据编号，查询报考层次
	 * @param levelCode 层次编号
	 * @return
	 */
	public SubstituteGroup qry(String levelCode) {
		SubstituteGroup substituteGroup = null;
		String sql = String.format("select * from base_substitute_group d where d.substitute_group_id = '%1$s'",levelCode);
		substituteGroup = dbUtil.getObject(sql, SubstituteGroup.class);
		return substituteGroup;
	}
	
	/**
	 * 根据编号，查询报考层次
	 * @param levelCode 层次编号
	 * @return
	 */
	public SubstituteGroup SubStituteSet(String levelCode) {
		SubstituteGroup substituteGroup = null;
		String sql = String.format("select * from base_substitute_group d where d.substitute_group_id = '%1$s'",levelCode);
		substituteGroup = dbUtil.getObject(sql, SubstituteGroup.class);
		return substituteGroup;
	}	

	/**
	 * 保存部门对象到数据库
	 * @param substituteGroup 顶替分组
	 * @param type      类型      0-insert, 1-update
	 * @return boolean
	 */
	public boolean save(SubstituteGroup substituteGroup, int type) {
		boolean saveFlag = false;
		StringBuilder sql = new StringBuilder();
		switch (type) {
		case 0:
			sql.append("insert into base_substitute_group");
			sql.append("(substitute_group_id,substitute_group_name)");
			sql.append(" values");
			sql.append(String.format(" ('%1$s','%2$s')",
				substituteGroup.getSubstituteGroupId(),substituteGroup.getSubstituteGroupName()));
			break;
		case 1:
			sql.append("update base_substitute_group");
			sql.append(String.format(" set substitute_group_name = '%1$s' ",substituteGroup.getSubstituteGroupName()));
			sql.append(String.format(" where substitute_group_id = '%1$s'", substituteGroup.getSubstituteGroupId()));
			break;
		default:
			break;
		}
		saveFlag = dbUtil.saveOrUpdate(sql.toString()) == 1;
		return saveFlag;
	}
	
	/**
	 * 保存顶替分组中的课程
	 * @param substituteGroup 顶替分组
	 * @param type      类型      0-insert, 1-update
	 * @return boolean
	 */
	public boolean saveGroupSyllabus(SubstituteGroup substituteGroup) {
		boolean saveFlag = false;
		String sqldel="delete from base_substitute_group_syllabus where substitute_group_id="+substituteGroup.getSubstituteGroupId();
		dbUtil.saveOrUpdate(sqldel);
		String[] syllabusCodes=substituteGroup.getSyllabusCode().split(", ");
		for(int i=0;i<syllabusCodes.length;i++){
			StringBuilder sql = new StringBuilder();
			sql.append("insert into base_substitute_group_syllabus");
			sql.append("(syllabus_code,substitute_group_id)");
			sql.append(" values");
			sql.append(String.format("('%1$s','%2$s')",
					syllabusCodes[i],substituteGroup.getSubstituteGroupId()));
			saveFlag = dbUtil.saveOrUpdate(sql.toString()) == 1;
		}
		return saveFlag;
	}	

	/**
	 * 删除用户部门（如果已经设置职位，则必须先清除职位才能删除部门）
	 * @param levelCode
	 * @return
	 */
	public boolean del(String levelCode) {
		boolean delFlag = false;
		StringBuilder sql = new StringBuilder();
		sql.append("delete from base_substitute_group");
		sql.append(String.format(" where substitute_group_id = '%1$s'",levelCode));
		delFlag = dbUtil.saveOrUpdate(sql.toString()) == 1;
		return delFlag;
	}
	
}
