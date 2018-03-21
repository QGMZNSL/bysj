package com.sincinfo.zxks.core.day.message;

import java.util.List;

import org.apache.struts2.components.OptGroup;

import com.sincinfo.zxks.bean.BaseNewsClass;
import com.sincinfo.zxks.common.action.WebActionSupport;
import com.sincinfo.zxks.common.log.OperatLog;
import com.sincinfo.zxks.common.log.OperateLogTool;
import com.sincinfo.zxks.zxksdbs.ClassDbService;
/**
 * @see 信息管理栏目请求处理类
 * @author guanm
 *
 */
public class ClassAct extends WebActionSupport{
	private ClassDbService classService;//栏目业务类
	
	private List<BaseNewsClass> allClass;//所有栏目集合
	
	private BaseNewsClass newsClass;//栏目对象
	private int[] userRole;//权限多选框
	
	private String classid;//栏目id
	
	public ClassAct(){
		classService=new ClassDbService();
	}
	
	/**
	 * @see 进入栏目管理默认页
	 * @return
	 */
	public String manager(){
		allClass=classService.getAllClass();
		return "manager";
	}
	/**
	 * @see to增加页面
	 * @return
	 */
	public String toadd(){
		return "toaddok";
	}
	
	/**
	 * @see do增加页面
	 * @return
	 */
	public void doadd(){
		OperatLog log=this.getOptLog(OperatLog.DB_INSERT, "添加信息分类，id="+newsClass.getClassId());
		String info=classService.saveClass(newsClass, userRole,log);		
		if(info!=null){
			String alertStr=String
			.format(
					"alert('"+info+"');location.href='%1$s/manager/day/message/class_toadd.do';",
					request.getContextPath());
			super.PostJs(alertStr);
			return;
		}else{
			String alertStr=String
			.format(
					"alert('添加成功！');location.href='%1$s/manager/day/message/class_toadd.do';",
					request.getContextPath());
			super.PostJs(alertStr);
			return;
		}
	}
	
	/**
	 * @see to修改页面
	 * @return
	 */
	public String toedit(){
		if(classid==null||classid.equals("")){
			String alertStr=String
			.format(
					"alert('栏目编号有误');location.href='%1$s/manager/day/message/class_toadd.do';",
					request.getContextPath());
			super.PostJs(alertStr);
			return null;
		}
		newsClass=classService.getBaseNewsClassById(classid);
		return "toeditok";
	}
	/**
	 * @see do修改
	 * @return
	 */
	public void doedit(){
		OperatLog log=this.getOptLog(OperatLog.DB_UPDATE, "信息分类修改，id="+newsClass.getClassId());
		String info=classService.update(newsClass, userRole,classid,log);
		if(info!=null){
			String alertStr=String
			.format(
					"alert('"+info+"');location.href='%1$s/manager/day/message/class_toedit.do?classid="+classid+"';",
					request.getContextPath());
			super.PostJs(alertStr);
			return;
		}else{	
			String alertStr=String
			.format(
					"alert('修改成功！');location.href='%1$s/manager/day/message/class_toedit.do?classid="+newsClass.getClassId()+"';",
					request.getContextPath());
			super.PostJs(alertStr);
			return;
		}
	}
	/**
	 * @see 删除
	 * @return
	 */
	public void delete(){
		if(classid==null||classid.equals("")){
			String alertStr=String
			.format(
					"alert('该记录不存在！');location.href='%1$s/manager/day/message/class_manager.do';",
					request.getContextPath());
			super.PostJs(alertStr);
			return;
		}
		OperatLog log=this.getOptLog(OperatLog.DB_DELETE, "删除信息分类，id="+classid);
		String info=classService.delete(classid,log);
		if(info!=null){
			String alertStr=String
			.format(
					"alert('"+info+"');location.href='%1$s/manager/day/message/class_manager.do';",
					request.getContextPath());
			super.PostJs(alertStr);
			return;
		}else{
			String alertStr=String
			.format(
					"alert('删除成功！');location.href='%1$s/manager/day/message/class_manager.do';",
					request.getContextPath());
			super.PostJs(alertStr);
			return;
		}
	}

	public ClassDbService getClassService() {
		return classService;
	}

	public void setClassService(ClassDbService classService) {
		this.classService = classService;
	}

	public List<BaseNewsClass> getAllClass() {
		return allClass;
	}

	public void setAllClass(List<BaseNewsClass> allClass) {
		this.allClass = allClass;
	}

	public BaseNewsClass getNewsClass() {
		return newsClass;
	}

	public void setNewsClass(BaseNewsClass newsClass) {
		this.newsClass = newsClass;
	}

	public int[] getUserRole() {
		return userRole;
	}

	public void setUserRole(int[] userRole) {
		this.userRole = userRole;
	}

	public String getClassid() {
		return classid;
	}

	public void setClassid(String classid) {
		this.classid = classid;
	}
	
	
}
