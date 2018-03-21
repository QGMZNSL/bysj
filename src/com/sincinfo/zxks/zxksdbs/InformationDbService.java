package com.sincinfo.zxks.zxksdbs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.sincinfo.zxks.bean.BaseInformation;
import com.sincinfo.zxks.bean.BaseInformationClass;
import com.sincinfo.zxks.bean.BaseNewsArticle;
import com.sincinfo.zxks.bean.BaseUser;
import com.sincinfo.zxks.bean.SysConfig;
import com.sincinfo.zxks.common.db.DbUtil;
import com.sincinfo.zxks.common.log.OperatLog;
import com.sincinfo.zxks.common.log.OperateLogTool;
import com.sincinfo.zxks.common.util.Log;
/**
 * @see 须知管理  业务类
 * @author guanm
 *
 */
public class InformationDbService extends DbUtil{
	//须知视图 sql
	private final String inforSql="select t1.*,t2.class_name from base_information t1,base_information_class t2 where t1.class_id=t2.class_id ";
	
    /**
     * @see 获取须知分类  1 新生报名须知  2 考试报考须知
     */
	public List<BaseInformationClass> getInfoClass(){
		String sql="select * from base_information_class order by class_id";
		return super.getObjList(sql,BaseInformationClass.class);
	}
	/**
	 * @see 获取所有须知集合
	 */
	public String getAllInformation(){
		String sql="select * from ("+inforSql+") t order by class_id ,information_add_time desc ";
		return sql;
	}
	/**
	 * @see 获取须知总数
	 */
	public long getAllInfoCount(){
		String sql="select count(*) from base_information";
		return super.getNum(sql);
	}
	/**
	 * @see save须知对象
	 * @return info 信息  null 表示成功
	 */
	public String saveInfo(BaseInformation information,BaseUser user,String fileName,File file,OperatLog log){
		String info=null;
		if(information==null){
			return info="须知对象未创建";
		}
		information=this.trimObject(BaseInformation.class, information);
		if(file!=null&&fileName!=null){
			this.savefile(file, fileName);
		}
		StringBuilder sql1=new StringBuilder();
		StringBuilder sql2=new StringBuilder();
		sql1.append("insert into base_information(information_id,information_title,class_id,information_main_content,information_add_user,information_add_time,information_unaudit_status");
		if(fileName!=null&&!fileName.equals("")){
			sql1.append(",information_file");
		}
		sql1.append(")");		
		
		sql2.append(" values(seq_article.nextval ");
		sql2.append(" ,'"+information.getInformationTitle()+"'");
		sql2.append(" ,"+information.getClassId());
		sql2.append(" ,?");
		sql2.append(" ,'"+user.getUserName()+"'");
		sql2.append(" ,to_date('"+this.dateFormat(new Date())+"','yyyy-mm-dd hh24:mi:ss')");
		sql2.append(",'1'");
		if(fileName!=null&&!fileName.equals("")){
			sql2.append(",'"+fileName+"'");
		}
		sql2.append(")");
//		System.out.println(sql1.toString()+sql2.toString());
		int k=super.saveOrUpdateClob(sql1.toString()+sql2.toString(), information.getInformationMainContent());
		if(k<1){
			return info="对象未保存，sql错误";
		}
		log.setLogOptSql(sql1.toString()+sql2.toString());
		OperateLogTool.saveOptLog(log);
		return info;
	}
	
	/**
	 * @see 存放附件
	 */
	public void savefile(File file, String  fileName) {
		String[] paths=super.getPaths();
		File pfile = new File(paths[0]);// 文件根目录
//		File pfile = new File(this.getConfigFileserver("3").getSysCfgContent());//文件根目录
		File inforfile=new File(pfile,this.getConfigFileserver("25").getSysCfgContent());//须知附件存放地址
		File outfile = new File(inforfile, fileName);
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			if (!inforfile.exists()) {
				inforfile.mkdirs();
			}
			System.out.println(inforfile.getPath());
			if (!outfile.exists()) {
				outfile.createNewFile();
			}
			fis = new FileInputStream(file);
			fos = new FileOutputStream(outfile);
			byte[] b = new byte[10 * 1024];
			int k;
			while ((k = fis.read(b)) > 0) {
				fos.write(b, 0, k);
			}
		} catch (IOException e) {
			new Log().error(this.getClass(),"存放须知附件有误",e);
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					new Log().error(this.getClass(),"存放须知附件有误",e);
				}
			}
			if (fos != null) {
				try {
					fos.flush();
					fos.close();
				} catch (IOException e) {
					new Log().error(this.getClass(),"存放须知附件有误",e);
				}
			}
		}
	}
	
	/**
	 * @see 获取配置表中的文件存储路径
	 * @param  id=1表示文件根目录 id=25表示须知附件存放路径 返回 以“/”结尾
	 * 
	 */
	public SysConfig getConfigFileserver(String id) {
		String sql = "select * from sys_config where sys_cfg_id="+id;
		return super.getObject(sql, SysConfig.class);
	}
	
	/**
	 * @see 根据须知id获得  该须知的详细信息
	 */
	public BaseInformation getBaseInformationById(String inforid){
		try {
			Integer inid=Integer.parseInt(inforid);
			String sql="select * from ("+inforSql+") t where t.information_id="+inid;
			return super.getObject(sql,BaseInformation.class);
		} catch (NumberFormatException e) {
			new Log().error(this.getClass(),"须知id转化错误",e);
		}
		return null;
	}
	
	/**
	 * @see update须知对象
	 * @return
	 */
	public String update(BaseInformation information,BaseUser user,String fileName,File file,OperatLog log){
		String info=null;
		if(information==null){
			return info="须知对象未创建";
		}
		information=this.trimObject(BaseInformation.class, information);
		if(file!=null&&fileName!=null){
			this.savefile(file, fileName);
		}
		StringBuilder sql=new StringBuilder();
		sql.append("update base_information set ");
		sql.append(" information_title='"+information.getInformationTitle()+"'");
		sql.append(" ,class_id="+information.getClassId());
		sql.append(" ,information_main_content=?");
		sql.append(" ,information_add_user='"+user.getUserName()+"'");
		sql.append(" ,information_add_time=to_date('"+this.dateFormat(new Date())+"','yyyy-mm-dd hh24:mi:ss')");
		if(fileName!=null&&!fileName.equals("")){
			sql.append(",information_file='"+fileName+"'");
			this.savefile(file, fileName);
		}
		sql.append(" where information_id="+information.getInformationId());
		int k=super.saveOrUpdateClob(sql.toString(), information.getInformationMainContent());
		if(k<1){
			info="sql错误";
		}
		log.setLogOptSql(sql.toString());
		OperateLogTool.saveOptLog(log);
		return info;
	}
	/**
	 * @see  删除须知
	 */
	public boolean delete(String inforid,OperatLog log){
		boolean isok=false;
		try {
			Integer inid=Integer.parseInt(inforid);
			String sql="delete from base_information where information_id="+inid;
			int k=super.saveOrUpdate(sql);
			if(k>0){
				isok=true;
			}
			log.setLogOptSql(sql);
			OperateLogTool.saveOptLog(log);
			return isok;
		} catch (NumberFormatException e) {
			new Log().error(this.getClass(), "删除须知，id转换错误",e);
		}		
		return isok;
		
	}
	/**
	 * @see 去掉bean中String属性中空格
	 * @param <T>
	 * @param cls
	 * @param bean
	 * @return
	 */
	public <T> T trimObject(Class<T> cls, T bean) {
		Method[] ms = cls.getDeclaredMethods();
		try {
			for (Method md : ms) {
				if (md.getName().startsWith("get")) {
					if (md.getParameterTypes().length > 0) {
						continue;
					}
					if(md.getReturnType()!=String.class){
						continue;
					}
					String setName = "set" + md.getName().substring(3);

					Method setMd = cls.getMethod(setName, String.class);
					if (md.invoke(bean) != null) {
						setMd.invoke(bean, ((String) md.invoke(bean)).trim());
					}
				}
			}
		} catch (Exception e) {
			new Log().error(this.getClass(),"去空格错误",e);
		} 
		return bean;
	}
	/**
	 * @see 根据日期 获得该日期字符串 "yyyy-MM-dd hh:mm:ss"
	 */
	private String dateFormat(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}
}
