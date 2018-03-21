package com.sincinfo.zxks.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import com.sincinfo.zxks.common.action.WebActionSupport;
import com.sincinfo.zxks.common.db.DbConn;
import com.sincinfo.zxks.common.db.DbObjException;
import com.sincinfo.zxks.common.db.DbObjPool;

/**
 * @ClassName: HelloWorld 
 * @Description: 测试 <br>
 * <br>
 * @author litian
 * @date Jan 6, 2012 5:58:28 PM<br>
 *  
 */
public class DbUseInfo extends WebActionSupport {
	/**
	 * 通过获取数据连接，测试连接池及其对应对象管理类的运行情况，并返回查询结果
	 */
	public void dbUseInfo() {
		DbObjPool dbp = DbObjPool.getInstance();

		PrintWriter pw = null;
		try {
			response.setContentType("text/html;charset=utf-8");
			pw = response.getWriter();
			pw.write( dbp.toString("html"));
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if ( pw != null) {
				pw.close();
			}
		}
	}
}
