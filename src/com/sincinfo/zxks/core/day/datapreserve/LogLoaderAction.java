package com.sincinfo.zxks.core.day.datapreserve;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.sincinfo.zxks.bean.LogLogin;
import com.sincinfo.zxks.common.action.WebActionSupport;
import com.sincinfo.zxks.common.db.DbUtil;
import com.sincinfo.zxks.common.util.Page;
import com.sincinfo.zxks.common.util.StringTool;
import com.sincinfo.zxks.common.util.XLS;
import com.sincinfo.zxks.zxksdbs.LogLoaderService;

public class LogLoaderAction extends WebActionSupport {
	/**
	 * 
	 */
	private String loginName;
	private String loginIp;
	private String beginTime;
	private String endTime;
	private String logType;
	private List<String[]> logLoginLists;
	private static final long serialVersionUID = -279468123516628083L;
	private LogLoaderService logLoaderService = new LogLoaderService();
	DbUtil dbUtil = new DbUtil();;
	// 登陆日志的方法
	private List<LogLogin> logLoginList;
	// 登陆日志列表
	private Page page = new Page();

	public String getAllLogByDemand() {

		logType = StringTool.trim(logType);
		loginName = StringTool.trim(loginName);
		loginIp = StringTool.trim(loginIp);
		beginTime = StringTool.trim(beginTime);
		endTime = StringTool.trim(endTime);
		
		// 分页查询
		// 获得所有登陆成功的列表
		logLoginList = logLoaderService.getAllLogByDemand(page, logType,
				loginName, loginIp, beginTime, endTime);
		StringBuilder url = new StringBuilder();
		url.append(this.request.getContextPath());
		url.append("/manager/day/datapreserve/logLoader_getAllLogByDemand.do");
		page.setPath(url.toString());
		return "intoLog";
	}

	public String getAllLog() {
		// 获得全部日志
		logLoginList = logLoaderService.getAllLog(page);
		StringBuilder url = new StringBuilder();
		url.append(this.request.getContextPath());
		url.append("/manager/day/datapreserve/logLoader_getAllLog.do");
		page.setPath(url.toString());
		return "intoLog";
	}

	public void expXLS() {
		
		// 设置分页
		String[] paths = dbUtil.getPaths();
		//登陆日志XLS文件路径
		String subPath = dbUtil.getConfig("43");
		//获取当前日期格式化
		SimpleDateFormat dateformat1 = new SimpleDateFormat("yyyyMMddHHmmss");
		// 设置导出
		String a1 = dateformat1.format(new Date());
		//导出成xls文档数据类型和名字
		String fileName = "loginLog_" + a1 + ".xls";
			//导出
		String full = paths[0] + subPath + System.getProperty("file.separator");
			//导出全部的路径
		String down = paths[1] + subPath + "/" + fileName;
		XLS xls = new XLS();
		//需导出的列明
		String[] colNames = { "日志ID", "登录名", "登录密码", "登录IP", "登录时间" };

		// 列名
		logType = StringTool.trim(logType);
		//登陆类型
		loginName = StringTool.trim(loginName);
		//登陆名称
		loginIp = StringTool.trim(loginIp);
//		登陆IP
		beginTime = StringTool.trim(beginTime);
		//开始时间
		endTime = StringTool.trim(endTime);
		//结束时间
		List<String[]> logList = logLoaderService.getAllLogByDemandArr(logType,
				loginName, loginIp, beginTime, endTime);
		boolean hasSucess = true;
		//创建一个文件下载
		xls.creatOpenFile(full, fileName);
		hasSucess = xls.write1(null, "登陆日志", colNames, logList,
				XLS.MAX_SHEET_LINES);
		xls.closeFile();
		
		// 返回页面
		String retStr = null;
		if ( hasSucess) {
//			通过hasSucess值来判断是否进行下载
			retStr = String.format("<a href='%1$s' target='_blank'>下载</a>", down);
		} else {
			retStr = "error";
		}
		aJax(retStr);
	}

	public void aJax(String retStr) {
//		ajax 的编码方式
		response.setContentType("text/html;charese=utf-8");
		PrintWriter pw = null;
//		写文件
		try {
			pw = response.getWriter();
			pw.write(retStr);
			pw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pw != null) {
				pw.close();
			}
		}
	}

	public LogLoaderService getLogLoaderService() {
		return logLoaderService;
	}

	public void setLogLoaderService(LogLoaderService logLoaderService) {
		this.logLoaderService = logLoaderService;
	}

	public List<LogLogin> getLogLoginList() {
		return logLoginList;
	}

	public void setLogLoginList(List<LogLogin> logLoginList) {
		this.logLoginList = logLoginList;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public String getLogType() {
		return logType;
	}

	public void setLogType(String logType) {
		this.logType = logType;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public List<String[]> getLogLoginLists() {
		return logLoginLists;
	}

	public void setLogLoginLists(List<String[]> logLoginLists) {
		this.logLoginLists = logLoginLists;
	}
}
