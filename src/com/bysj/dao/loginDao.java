package com.bysj.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sincinfo.zxks.bean.UserBean;
import com.sincinfo.zxks.common.db.content;
import com.sincinfo.zxks.common.tree.HeadMenu;

public class loginDao {
	public List<UserBean> ChekcLogin(String username,String userpass) throws Exception{
		UserBean user = new UserBean();
		List<UserBean> userList = new ArrayList<UserBean>();
		Connection conn = content.connectDB();
		String sql = "SELECT * FROM base_user where user_name=? and user_pass=?";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, username);
		pstmt.setString(2, userpass);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			System.out.println("next");
			user.setUsername(rs.getString("user_name"));
			user.setUserpass(rs.getString("user_pass"));
			
			userList.add(user);
		}
		
		return userList;
	}
	
	public List<HeadMenu> findMenu() throws Exception{
		HeadMenu hm = new HeadMenu();
		List<HeadMenu> headMenuList = new ArrayList<HeadMenu>();
		Connection conn = content.connectDB();
		String sql = "SELECT * FROM base_user where user_name=? and user_pass=?";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			System.out.println("next");
			hm.setMenuId(rs.getInt("user_name"));
			hm.setMenuName(rs.getString("user_pass"));
			
			headMenuList.add(hm);
		}
		return headMenuList;
		
	}

}
