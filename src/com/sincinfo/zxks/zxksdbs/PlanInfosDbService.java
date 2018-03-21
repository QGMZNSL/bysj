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

import com.sincinfo.zxks.bean.PlanInfos;
import com.sincinfo.zxks.common.db.DbUtil;
import com.sincinfo.zxks.common.util.Page;
import com.sincinfo.zxks.common.util.StringTool;

/**
 * @ClassName: PlanDbService
 * @Description: 专业信息汇编 <br>
 * @author yuansh
 * @date 2012-01-26 15:58<br>
 * 
 */
public class PlanInfosDbService {
	private DbUtil dbUtil = null;

	/**
	 * 构造
	 */
	public PlanInfosDbService() {
		this.dbUtil = new DbUtil();
	}

	/**
	 * 根据条件查询对应的所有报考层次列表，若不加限制则传递“0”
	 * @param code 暂未用不用传值
	 * @param page 分页对象
	 * @return
	 */
	public List<PlanInfos> qry(PlanInfos planInfos, Page page) {
		List<PlanInfos> planInfoss = null;
		StringBuilder sql = new StringBuilder();
		StringBuilder sqlCount = new StringBuilder();
		sql.append("select d.* from v_planInfos d where 1=1 ");
		sqlCount.append("select count(*) from v_planInfos d where 1=1 ");
		if(!StringTool.isEmpty(planInfos)){
			if(!StringTool.isEmpty(planInfos.getCkProCode())){
				sql.append(String.format(" and pro_code = '%1$s'",planInfos.getCkProCode()));
				sqlCount.append(String.format(" and pro_code = '%1$s'",planInfos.getCkProCode()));
			}
		}
		sql.append(" order by pro_code asc");

		// 分页查询
		String sqlPage = page.setPagecount(dbUtil.getNum(sqlCount.toString()), sql.toString());
		planInfoss = dbUtil.getObjList(sqlPage, PlanInfos.class);
		return planInfoss;
	}

	/**
	 * 根据编号，查询报考层次
	 * @param levelCode 层次编号
	 * @return
	 */
	public PlanInfos qry(String proCode) {
		PlanInfos planInfos = null;
		String sql = String.format("select * from v_planInfos d where d.pro_code = '%1$s'",proCode);
		planInfos = dbUtil.getObject(sql, PlanInfos.class);
		return planInfos;
	}


}
