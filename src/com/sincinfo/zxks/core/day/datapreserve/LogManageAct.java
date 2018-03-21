package com.sincinfo.zxks.core.day.datapreserve;


import com.sincinfo.zxks.common.action.WebActionSupport;


/**
 * @ClassName: LogManageAct 
 * @Description: 日志管理<br>
 * <br>
 * @author litian
 * @date Mar 5, 2012 3:13:01 PM<br>
 *  
*/
public class LogManageAct extends WebActionSupport{
	private static final long serialVersionUID = -6217769699627471779L;

	// 登陆用户名
	private String loginUserName;
	
	// 登陆ip
	private String loginIp;
	
	// 开始结束时间
	private String startDate;
	private String endDate;
	
	// 登陆日志类型（“”-全部 “success”-成功 “failure”-失败）
	private String loginLogType;
	
	/**
	 * 查询登陆日志
	 * @return
	 */
	public String loginLogs() {
		
		
		
		return "loginLogs";
	}
}
