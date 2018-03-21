package com.sincinfo.zxks.common;

/**
 * @ClassName: Constants
 * @Description: 静态类 <br>
 *               <br>
 * @author litian
 * @date Jan 11, 2012 5:45:35 PM<br>
 * 
 */
public class Constants {

	/**
	 * @Fields ZK_OPERATOR : 后台管理端，保存在session中，记录操作员对象的key
	 */
	public static String ZK_OPERATOR = "zkOperator";

	/**
	 * @Fields ZKSTU_OPERATOR : 考生端，session记录用户的key
	 */
	public static String ZKSTU_OPERATOR = "zkstuStudent";

	/**
	 * @Fields STU_PREAPPLIER : 预报名端，session记录用户的key
	 */
	public static String STU_PREAPPLIER = "zkstuPreApplier";

	/**
	 * @Fields TREE_NODE_ID : 用来保存菜单项id
	 */
	public static String TREE_NODE_ID = "currTreeNodeId";
	
	/**
	 * @Fields NAVIGATION_NAME : 用来保存导航
	 */
	public static String NAVIGATION_NAME = "currNavigation";

	/**
	 * @Fields FUNCTION_ID : 功能编号
	 */
	public static String FUNCTION_ID = "currFunctionId";
	
	/**
	 * @Fields LOGIN_ACROSS_KEY : 跨包登录传参所用key
	 */
	public static String LOGIN_ACROSS_KEY = "zk_login_across";

	public static String PID_PAY ="陕西省自学考试";// "snzxks";//

	/**
	 * @Fields CURR_PAGE : 用来记录上次操作页面的地址
	 */
	public static String CURR_PAGE = "zk_current_page_path";
}
