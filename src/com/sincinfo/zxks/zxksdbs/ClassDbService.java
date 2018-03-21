package com.sincinfo.zxks.zxksdbs;

import java.util.List;

import com.sincinfo.zxks.bean.BaseNewsClass;
import com.sincinfo.zxks.common.db.DbUtil;
import com.sincinfo.zxks.common.log.OperatLog;
import com.sincinfo.zxks.common.log.OperateLogTool;
import com.sun.org.apache.bcel.internal.generic.Select;

/**
 * ]@see 信息栏目管理业务类
 * 
 * @author guanm
 * 
 */
public class ClassDbService extends DbUtil {
	/**
	 * @see 获取所有栏目 按栏目id升序
	 */
	public List<BaseNewsClass> getAllClass() {
		String sql = "select * from base_news_class order by class_id";
		return super.getObjList(sql, BaseNewsClass.class);
	}
	/**
	 * @see save栏目对象  为空表示成功
	 * 
	 */
	public String saveClass(BaseNewsClass newsClass,int[] userRole,OperatLog log){
		String info=null;
		if(newsClass==null){
			return info="栏目对象为空";
		}
		int classid=newsClass.getClassId();
		boolean isok=this.checkClassId(classid);
		if(isok==false){
			return info="您所输入的栏目编号已存在";
		}
		if(userRole==null||userRole.length==0){
			return  info="请您选择栏目权限";
		}
		int urole=0;
		for(int i=0;i<userRole.length;i++){
			urole+=userRole[i];
		}
		
		StringBuilder sql=new StringBuilder();
		sql.append("insert into base_news_class(class_id,class_name");
		sql.append(",user_role,is_show");
		if(newsClass.getCustomUrl()!=null&&!newsClass.getCustomUrl().equals("")){
			sql.append(",custom_url");
		}
		sql.append(" ) values(?,?");
		sql.append(",?,?");
		if(newsClass.getCustomUrl()!=null&&!newsClass.getCustomUrl().equals("")){
			sql.append(",?");
		}
		sql.append(")");
		int k;
		if(newsClass.getCustomUrl()!=null&&!newsClass.getCustomUrl().equals("")){
			k=super.saveOrUpdate(sql.toString(),String.valueOf(newsClass.getClassId()),newsClass.getClassName(),String.valueOf(urole),newsClass.getIsShow(),newsClass.getCustomUrl());
		}else{
			k=super.saveOrUpdate(sql.toString(),String.valueOf(newsClass.getClassId()),newsClass.getClassName(),String.valueOf(urole),newsClass.getIsShow());
		}
		if(k<1){
			return info="服务器繁忙！";
		}
		log.setLogOptSql(sql.toString());
		OperateLogTool.saveOptLog(log);
		return info;
	}
	
	/**
	 * @see 根据栏目id获得该栏目对象
	 */
	public BaseNewsClass getBaseNewsClassById(String classid){
		String sql="select * from base_news_class where class_id=?";
		return super.getObject(sql,BaseNewsClass.class, classid);
	}
	
	/**
	 * @see do修改
	 */
	public String update(BaseNewsClass newsClass,int[] userRole,String classid,OperatLog log){
		String info=null;
		if(newsClass==null){
			return info="栏目对象为空";
		}
		int classidd=newsClass.getClassId();
		boolean isok=this.checkClassId2(classidd,classid);
		if(isok==false){
			return info="您所输入的栏目编号已存在";
		}
		if(userRole==null||userRole.length==0){
			return  info="请您选择栏目权限";
		}
		int urole=0;
		for(int i=0;i<userRole.length;i++){
			urole+=userRole[i];
		}
		if(newsClass.getCustomUrl()==null&&newsClass.getCustomUrl().equals("")){
			newsClass.setCustomUrl("");
		}
		StringBuilder sql=new StringBuilder();
		sql.append("update base_news_class set class_id=?,class_name=?,user_role=?,is_show=? ,custom_url=?");
		sql.append(" where class_id=?");
		int k=super.saveOrUpdate(sql.toString(),String.valueOf(newsClass.getClassId()),newsClass.getClassName(),String.valueOf(urole),newsClass.getIsShow(),newsClass.getCustomUrl(),classid);

		if(k<1){
			return info="服务器繁忙！";
		}
		log.setLogOptSql(sql.toString());
		OperateLogTool.saveOptLog(log);
		return info;
	}
	/**
	 * @see 删除栏目 null表示成功
	 */
	public String delete(String classid,OperatLog log){
		String info=null;
		String sql1="select count(*) from base_news_article where class_id=?";
		long count=super.getNum(sql1, classid);
		if(count>0){
			return info="此分类中已添加了信息，不能删除！";
		}
		String sql2="delete from base_news_class where class_id=?";
		int k=super.saveOrUpdate(sql2, classid);
		if(k<1){
			return info="服务器繁忙！";
		}
		log.setLogOptSql(sql2);
		OperateLogTool.saveOptLog(log);
		return null;
	}
	
	
	/**
	 * @see 检测用户所输入id是否已经存在 true 表示不存在  false 表示存在
	 */	
	private  boolean checkClassId(int classid){
		boolean isok=false;
		String sql="select class_id from base_news_class where class_id="+classid;
		Object obj=super.getString(sql);
		if(obj==null||obj.equals("")){
			isok=true;
		}
		return isok;
	}
	/**
	 * @see 检测用户所输入id是否已经存在(不包括自身) true 表示不存在  false 表示存在
	 * @ classidd 表示用户输入的id  classid表示现有id
	 */	
	private  boolean checkClassId2(int classidd,String classid){
		boolean isok=false;
		int c=Integer.parseInt(classid);
		if(classidd==c){
			return isok=true;
		}
		String sql="select class_id from base_news_class where class_id="+classidd;
		Object obj=super.getString(sql);
		if(obj==null||obj.equals("")){
			isok=true;
		}
		return isok;
	}
	
	
	
}
