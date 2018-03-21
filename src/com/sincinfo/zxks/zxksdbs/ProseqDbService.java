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

import com.sincinfo.zxks.bean.ProSeq;
import com.sincinfo.zxks.common.db.DbUtil;
import com.sincinfo.zxks.common.util.Page;
import com.sincinfo.zxks.common.util.StringTool;

/**
 * @ClassName: PlanDbService
 * @Description: 专业类型设置 <br>
 *               <br>
 * @author yuansh
 * @date 2012-01-26 15:58<br>
 * 
 */
public class ProseqDbService {
	private DbUtil dbUtil = null;

	/**
	 * 构造
	 */
	public ProseqDbService() {
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
	public List<ProSeq> qry(ProSeq proseq, Page page) {
		List<ProSeq> planlevels = null;
		StringBuilder sql = new StringBuilder();
		StringBuilder sqlCount = new StringBuilder();
		sql.append("select d.* from base_pro_partitions d where d.is_use='1' ");
		sqlCount.append("select count(*) from base_pro_partitions d where d.is_use='1' ");
		if(!StringTool.isEmpty(proseq)){
			if(!StringTool.isEmpty(proseq.getProPartCode())){
				sql.append(String.format(" and pro_part_code like '%%%1$s%%'",proseq.getProPartCode()));
				sqlCount.append(String.format(" and pro_part_code like '%%%1$s%%'",proseq.getProPartCode()));
			}
			if(!StringTool.isEmpty(proseq.getProPartName())){
				sql.append(String.format(" and pro_part_name like '%%%1$s%%'",proseq.getProPartName()));
				sqlCount.append(String.format(" and pro_part_name like '%%%1$s%%'",proseq.getProPartName()));
			}
		}
		sql.append(" order by d.pro_part_code asc");

		// 分页查询
		String sqlPage = page.setPagecount(dbUtil.getNum(sqlCount.toString()), sql.toString());
		planlevels = dbUtil.getObjList(sqlPage, ProSeq.class);
		return planlevels;
	}

	/**
	 * 根据编号，查询报考层次
	 * 
	 * @param levelCode
	 *        层次编号
	 * @return
	 */
	public ProSeq qry(String proPartCode) {
		ProSeq proseq = null;
		String sql = String.format("select * from base_pro_partitions d where d.pro_part_code = '%1$s'",proPartCode);
		proseq = dbUtil.getObject(sql, ProSeq.class);
		return proseq;
	}

	/**
	 * 保存部门对象到数据库
	 * 
	 * @param proseq
	 *            报考层次
	 * @param type
	 *            类型 0-insert, 1-update
	 * @return boolean
	 */
	public boolean save(ProSeq proseq, int type) {
		boolean saveFlag = false;
		StringBuilder sql = new StringBuilder();
		switch (type) {
		case 0:
			String s="select count(*) from base_pro_partitions b where b.pro_part_code=?";
			Long l=dbUtil.getNum(s, proseq.getProPartCode());
			if(l==0){
				sql.append("insert into base_pro_partitions");
				sql.append("(pro_part_code,pro_part_name,is_use,remarks)");
				sql.append(" values");
				sql.append(String.format(" ('%1$s','%2$s','%3$s','%4$s')",
						proseq.getProPartCode(),proseq.getProPartName(), "1",proseq.getRemarks()));
			}
			else{
				sql.append("update  base_pro_partitions");
				sql.append(String.format(" set pro_part_name = '%1$s',",proseq.getProPartName()));
				sql.append(String.format(" is_use = '%1$s',", "1"));
				sql.append(String.format(" remarks = '%1$s'", proseq.getRemarks()));
				sql.append(String.format(" where pro_part_code = '%1$s'", proseq.getProPartCode()));
			}
			
			break;
		case 1:
			sql.append("update  base_pro_partitions");
			sql.append(String.format(" set pro_part_name = '%1$s',",proseq.getProPartName()));
			sql.append(String.format(" is_use = '%1$s',", "1"));
			sql.append(String.format(" remarks = '%1$s'", proseq.getRemarks()));
			sql.append(String.format(" where pro_part_code = '%1$s'", proseq.getProPartCode()));
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
	 * @param levelCode
	 * @return
	 */
	public boolean del(String proPartCode) {
		boolean delFlag = false;
		StringBuilder sql = new StringBuilder();
		sql.append("delete from base_pro_partitions");
		sql.append(String.format(" where pro_part_code = '%1$s'",proPartCode));
		delFlag = dbUtil.saveOrUpdate(sql.toString()) == 1;
		return delFlag;
	}
	
	public boolean isUseDel(String proPartCode){
		boolean delFlag = false;
		StringBuilder sql = new StringBuilder();
		sql.append("update base_pro_partitions b set b.IS_USE='0'");
		sql.append(String.format(" where b.pro_part_code = '%1$s'",proPartCode));
		delFlag = dbUtil.saveOrUpdate(sql.toString()) == 1;
		return delFlag;
	}
}