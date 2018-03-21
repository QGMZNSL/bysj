package com.sincinfo.zxks.common.log;

/**
 * @ClassName: OperatorLog
 * @Description: 操作日志对象类 <br>
 *               <br>
 * @author litian
 * @date Jan 12, 2012 10:09:21 AM<br>
 * 
 */
public class OperatLog {
	/**
	 * @Fields DB_INSERT : 添加新数据的日志（对应字段logOptMethod）
	 */
	public static final String DB_INSERT = "INSERT";
	/**
	 * @Fields DB_UPDATE : 更新数据的日志（对应字段logOptMethod）
	 */
	public static final String DB_UPDATE = "UPDATE";
	/**
	 * @Fields DB_DELETE : 修改数据的日志（对应字段logOptMethod）
	 */
	public static final String DB_DELETE = "DELETE";
	
	/**
	 * @Fields logOptId : 日志编号
	 */
	private String logOptId;

	/**
	 * @Fields userName : 操作员用户名
	 */
	private String userName;

	/**
	 * @Fields projectName : 项目名
	 */
	private String projectName;

	/**
	 * @Fields nodeId : 对应功能所处菜单树节点的编号
	 */
	private String nodeId;

	/**
	 * @Fields logOptIp : 登陆ip
	 */
	private String logOptIp;

	/**
	 * @Fields logOptTime : 操作时间
	 */
	private String logOptTime;

	/**
	 * @Fields logOptMethod : 操作方式（包括：INSERT、UPDATE、DELETE）
	 */
	private String logOptMethod;

	/**
	 * @Fields logOptSql : 操作的sql语句
	 */
	private String logOptSql;

	/**
	 * @Fields remarks : 备注、其他说明
	 */
	private String remarks;

	public OperatLog() {
		this.logOptId = null;
		this.logOptIp = null;
		this.logOptMethod = null;
		this.logOptSql = null;
		this.logOptTime = null;
		this.nodeId = null;
		this.projectName = null;
		this.remarks = null;
		this.userName = null;
	}

	public OperatLog(String userName, String logOptIp, String nodeId,
			String projectName, String logOptMethod, String logOptSql,
			String remarks) {
		this.logOptId = null;
		this.logOptIp = logOptIp;
		this.logOptMethod = logOptMethod;
		this.logOptSql = logOptSql;
		this.logOptTime = "sysdate";
		this.nodeId = nodeId;
		this.projectName = projectName;
		this.remarks = remarks;
		this.userName = userName;
	}

	public String getLogOptId() {
		return logOptId;
	}

	public void setLogOptId(String logOptId) {
		this.logOptId = logOptId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getLogOptIp() {
		return logOptIp;
	}

	public void setLogOptIp(String logOptIp) {
		this.logOptIp = logOptIp;
	}

	public String getLogOptTime() {
		return logOptTime;
	}

	public void setLogOptTime(String logOptTime) {
		this.logOptTime = logOptTime;
	}

	public String getLogOptMethod() {
		return logOptMethod;
	}

	public void setLogOptMethod(String logOptMethod) {
		this.logOptMethod = logOptMethod;
	}

	public String getLogOptSql() {
		return logOptSql;
	}

	public void setLogOptSql(String logOptSql) {
		this.logOptSql = logOptSql;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
