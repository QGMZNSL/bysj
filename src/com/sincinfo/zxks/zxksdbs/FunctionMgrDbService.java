/**
 * Copyright(c) 2012 西安云海信息技术有限责任公司 All Rights Reserved<br>
 * @Title: com.sincinfo.zxks.zxksdbs.FunctionMgrDbService.java<br>
 * @Description: 数据维护功能，数据库操作 <br>
 * <br>
 * @author litian<br>
 * @date Feb 6, 2012 9:14:32 AM 
 * @version V1.0   
 */
package com.sincinfo.zxks.zxksdbs;

import java.util.List;

import com.sincinfo.zxks.bean.BasePlacePayMethod;
import com.sincinfo.zxks.common.db.DbUtil;
import com.sincinfo.zxks.common.log.OperatLog;
import com.sincinfo.zxks.common.log.OperateLogTool;

/**
 * @ClassName: FunctionMgrDbService
 * @Description: 数据维护功能，数据库操作 <br>
 *               <br>
 * @author litian
 * @date Feb 6, 2012 9:14:32 AM<br>
 * 
 */
public class FunctionMgrDbService {
	DbUtil dbUtil = null;

	public FunctionMgrDbService() {
		this.dbUtil = new DbUtil();
	}

	/**
	 * 查询地市下所有考区的交费方式设置
	 * 
	 * @param cityCode
	 *            地市代码
	 * @return List<BasePlacePayMethod> 交费配置列表
	 */
	public List<BasePlacePayMethod> qryPayMethodList(String cityCode) {
		StringBuilder sql = new StringBuilder(new BasePlacePayMethod().getSql());
		sql.append(" where t.city_code = '%1$s'");
		sql.append(" order by t.city_code asc, ");
		sql.append(" t.exam_area_code asc");
		List<BasePlacePayMethod> payMethodList = dbUtil.getObjList(String
				.format(sql.toString(), cityCode), BasePlacePayMethod.class);
		return payMethodList;
	}

	/**
	 * 更新对应交费开关状态
	 * 
	 * @param placePmethodId
	 *            主键
	 * @param payMethod
	 *            交费方式
	 * @param methodFlag
	 *            开关状态
	 * @param optLog
	 *            操作日志
	 * @return boolean 是否成功
	 */
	public boolean switchPMethod(String placePmethodId, int payMethod,
			int methodFlag, OperatLog optLog) {
		boolean switchFlag = false;
		StringBuilder sql = new StringBuilder();
		sql.append("update base_place_pay_method m");
		switch (payMethod) {
		case 1:
			// 更改网上交费开关状态
			sql.append(" set m.pay_method_1 = '%2$s'");
			break;
		case 2:
			// 更改现场交费开关状态
			sql.append(" set m.pay_method_2 = '%2$s'");
			break;
		case 3:
			// 更改银行交费开关状态
			sql.append(" set m.pay_method_3 = '%2$s'");
			break;
		default:
			return false;
		}
		sql.append(" where m.place_pmethod_id = '%1$s'");
		switchFlag = dbUtil.saveOrUpdate(String.format(sql.toString(),
				placePmethodId, methodFlag)) == 1;

		// 保存操作日志
		if (switchFlag) {
			optLog.setLogOptSql(sql.toString());
			OperateLogTool.saveOptLog(optLog);
		}

		return switchFlag;
	}
}
