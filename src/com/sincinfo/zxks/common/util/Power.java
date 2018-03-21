package com.sincinfo.zxks.common.util;

public class Power {

	/**
	 * 省级用户
	 */
	public static int USER_ROLE_PROV = 1;
	/**
	 * 市区用户
	 */
	public static int USER_ROLE_CITY = 2;
	/**
	 * 考区用户
	 */
	public static int USER_ROLE_AREA = 4;
	/**
	 * 主考院校用户
	 */
	public static int USER_ROLE_ACADEMY = 8;
	/**
	 * 摄像点用户
	 */
	public static int USER_ROLE_CAMERA = 16;

	/**
	 * 合作开考单位用户
	 */
	public static int USER_ROLE_UNIT = 32;
	/**
	 * “合作开考单位用户”
	 */
	public static String USER_ROLE_UNIT_NAME="合作开考单位用户";
	/**
	 * 权限判断
	 * 
	 * @param userAdm
	 *            用户权限码，从session中取<br>
	 * @param purview
	 *            权限码
	 * @return true:有权限 false:无权限
	 */
	public static boolean check(Object userAdmObj, int purview) {
		boolean result = false;
		String userAdm = "";
		try {
			if (userAdmObj != null) {
				userAdm = StringTool.trim(userAdmObj.toString());
			}

			if ('1' == userAdm.charAt(purview - 1)) {
				result = true;
			}
		} catch (Exception e) {
			// do nothing
		}
		return result;
	}
	
	/**
	 * 判断用户角色
	 * @param standardRole 标准的配置中的允许用户角色码
	 * @param userRole 用户自身的角色码,取本类常量
	 * @return
	 */
	public static boolean checkRole(int standardRole, int userRole) {
		return (standardRole & userRole) == userRole;
	}

	/**
	 * 判断用户角色
	 * @param standardRole 标准的配置中的允许用户角色码
	 * @param userRole 用户自身的角色码,取本类常量
	 * @return
	 */
	public static boolean checkRole(String standardRole, int userRole) {
		return checkRole(Integer.parseInt(standardRole), userRole);
	}
	

	/**
	 * 判断用户角色
	 * @param standardRole 标准的配置中的允许用户角色码
	 * @param userRole 用户自身的角色码,取本类常量
	 * @return
	 */
	public static boolean checkRole(int standardRole, String userRole) {
		return checkRole(standardRole, Integer.parseInt(userRole));
	}
	

	/**
	 * 判断用户角色
	 * @param standardRole 标准的配置中的允许用户角色码
	 * @param userRole 用户自身的角色码,取本类常量
	 * @return
	 */
	public static boolean checkRole(String standardRole, String userRole) {
		return checkRole(Integer.parseInt(standardRole), Integer.parseInt(userRole));
	}
}
