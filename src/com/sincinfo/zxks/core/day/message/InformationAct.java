package com.sincinfo.zxks.core.day.message;

import java.io.File;
import java.util.List;

import com.sincinfo.zxks.bean.BaseInformation;
import com.sincinfo.zxks.bean.BaseInformationClass;
import com.sincinfo.zxks.bean.BaseUser;
import com.sincinfo.zxks.common.action.WebActionSupport;
import com.sincinfo.zxks.common.log.OperatLog;
import com.sincinfo.zxks.common.log.OperateLogTool;
import com.sincinfo.zxks.common.util.Page;
import com.sincinfo.zxks.zxksdbs.InformationDbService;
/**
 * @see 须知管理请求处理类
 * @author guanm
 *
 */
public class InformationAct extends WebActionSupport{
	private InformationDbService infoService;// 须知Service
	private Page page;// 分页
	
	private List<BaseInformation> inforList;//须知集合
	private List<BaseInformationClass> infoClassList;//须知类别集合
	
	private BaseInformation information;//须知对象
	private File infoFile;//须知附件
	private String infoFileFileName;//须知附件名称

	private String inforid;//须知id
	
	public InformationAct() {
		infoService = new InformationDbService();
	}
	/**
	 * @see 进入须知  默认页面
	 * @return
	 */
	public String manager() {
		if (page == null) {
			page = new Page();
		}
		page.setPagecount(infoService.getAllInfoCount());
		page.setPath("information_manager.do");
		String sql=page.getSql(infoService.getAllInformation());
		inforList=infoService.getObjList(sql,BaseInformation.class);		
		return "manager";
	}
	
	/**
	 * @see to添加须知
	 * @return
	 */
	public  String  toadd(){
		infoClassList=infoService.getInfoClass();
		
		return "toaddok";
	}
	/**
	 * @see  do添加须知
	 * @return
	 */
	public void doadd(){
		OperatLog log=this.getOptLog(OperatLog.DB_INSERT, "添加须知，title="+information.getInformationTitle());
		BaseUser user=super.getCOperUser();
		String info=infoService.saveInfo(information, user, infoFileFileName, infoFile,log);
		if(info!=null){
			System.out.println(info);
			String alertStr=String
			.format(
					"alert('服务器繁忙！');location.href='%1$s/manager/day/message/information_toadd.do';",
					request.getContextPath());
			super.PostJs(alertStr);
			return;
		}else{
			String alertStr=String
			.format(
					"alert('添加成功！');location.href='%1$s/manager/day/message/information_toadd.do';",
					request.getContextPath());
			super.PostJs(alertStr);
			return;
		}
		
	}
	/**
	 * @see 查看须知详细信息
	 * @return
	 */
	public String query(){
		if(inforid==null||inforid.equals("")){
			return "error";
		}
		information=infoService.getBaseInformationById(inforid);
		if(information!=null){			
			return "queryok";
		}else{
			return "error";
		}
	}
	/**
	 * @see to修改页面
	 * @return
	 */
	public String toedit(){
		infoClassList=infoService.getInfoClass();
		String str=this.query();
		if(str.equals("queryok")){
			return "toeditok";
		}else{
			return "error";
		}
	}
	/**
	 * @see do修改
	 * @return
	 */
	public void doedit(){
		OperatLog log=this.getOptLog(OperatLog.DB_UPDATE, "修改须知，id="+information.getInformationId());
		BaseUser user=super.getCOperUser();
		String info=infoService.update(information, user, infoFileFileName, infoFile,log);
		if(info!=null){
			System.out.println(info);
			String alertStr=String
			.format(
					"alert('服务器繁忙！');location.href='%1$s/manager/day/message/information_manager.do';",
					request.getContextPath());
			super.PostJs(alertStr);
			return;
		}else{
			String alertStr=String
			.format(
					"alert('修改成功！');location.href='%1$s/manager/day/message/information_toedit.do?inforid="+information.getInformationId()+"';",
					request.getContextPath());
			super.PostJs(alertStr);
			return;
		}
	}
	/**
	 * @see do删除
	 */
	public void delete(){
		if(inforid==null||inforid.equals("")){
			String alertStr=String
			.format(
					"alert('该记录不存在！');location.href='%1$s/manager/day/message/information_manager.do';",
					request.getContextPath());
			super.PostJs(alertStr);
			return;
		}
		OperatLog log=this.getOptLog("DELETE", "删除须知，id="+inforid);
		boolean isok=infoService.delete(inforid,log);
		if(isok){
			String alertStr=String
			.format(
					"alert('操作成功！');location.href='%1$s/manager/day/message/information_manager.do';",
					request.getContextPath());
			super.PostJs(alertStr);
			return;
		}else{
			String alertStr=String
			.format(
					"alert('该须知正在使用！');location.href='%1$s/manager/day/message/information_manager.do';",
					request.getContextPath());
			super.PostJs(alertStr);
			return;
		}
	}
	
	

	public InformationDbService getInfoService() {
		return infoService;
	}

	public void setInfoService(InformationDbService infoService) {
		this.infoService = infoService;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public List<BaseInformation> getInforList() {
		return inforList;
	}

	public void setInforList(List<BaseInformation> inforList) {
		this.inforList = inforList;
	}
	public List<BaseInformationClass> getInfoClassList() {
		return infoClassList;
	}
	public void setInfoClassList(List<BaseInformationClass> infoClassList) {
		this.infoClassList = infoClassList;
	}
	public BaseInformation getInformation() {
		return information;
	}
	public void setInformation(BaseInformation information) {
		this.information = information;
	}
	public File getInfoFile() {
		return infoFile;
	}
	public void setInfoFile(File infoFile) {
		this.infoFile = infoFile;
	}
	public String getInfoFileFileName() {
		return infoFileFileName;
	}
	public void setInfoFileFileName(String infoFileFileName) {
		this.infoFileFileName = infoFileFileName;
	}
	public String getInforid() {
		return inforid;
	}
	public void setInforid(String inforid) {
		this.inforid = inforid;
	}
	
	
}
