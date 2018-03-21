package com.sincinfo.zxks.core.day.datapreserve;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import sun.util.logging.resources.logging;

import com.sincinfo.zxks.bean.BaseStrangeWord;
import com.sincinfo.zxks.common.action.WebActionSupport;
import com.sincinfo.zxks.common.log.OperatLog;
import com.sincinfo.zxks.common.util.Cn2Spell;
import com.sincinfo.zxks.common.util.Log;
import com.sincinfo.zxks.common.util.Page;
import com.sincinfo.zxks.zxksdbs.StrangeWordDbService;

/**
 * @see 生僻字请求处理类
 * @author guanm
 *
 */
public class StrangeWordAct extends WebActionSupport{
	
	private StrangeWordDbService service;//生僻字业务类
	private BaseStrangeWord bean;//生僻字对象
	private Page page;//分页对象
	
	private List<BaseStrangeWord> beanList;//生僻字对象集合
	
	private String where;//搜索条件
	private String selmethod;//搜索方式
	
	private String strangeid;//生僻字id
	
	private String hanzi;//生僻字 用于转拼音
	public StrangeWordAct(){
		service=new StrangeWordDbService();
	}
	/**
	 * @see 默认请求
	 * @return
	 */
	public String manager(){
		selmethod="shouzimu";
		where="1";
		select();
		return "manager";
	}
	/**
	 * @see 查询
	 */
	public String select(){
		if(selmethod==null||selmethod.trim().equals("")){
			return "error";
		}
		selmethod=selmethod.trim();
		if(where==null||where.trim().equals("")){
			return "error";
		}
		where=where.trim();
		if(page==null){
			page=new Page();
		}
		page.setPagesize(60);
		page.setPagecount(service.getResCount(selmethod, where));
		page.setPath(String.format("strangeword_select.do?selmethod=%1$s&where=%2$s",selmethod,where));
		String sql=page.getSql(service.getResSql(selmethod, where));
		beanList=service.getObjList(sql,BaseStrangeWord.class);
		return "selectok";
	}
	
	
	/**
	 * @see to添加
	 */
	public String toadd(){
		return "toaddok";
	}
	/**
	 * @see do添加
	 */
	public void doadd(){
		OperatLog log=this.getOptLog(OperatLog.DB_INSERT, "添加生僻字，wrod="+bean.getStrangeWord());
		String info =service.doadd(bean,log);
		if(info!=null){
			String alertStr=String
			.format(
					"alert('"+info+"');location.href='%1$s/manager/day/datapreserve/strangeword_toadd.do';",
					request.getContextPath());
			super.PostJs(alertStr);
			return;
		}else{
			String alertStr=String
			.format(
					"alert('添加成功');location.href='%1$s/manager/day/datapreserve/strangeword_toadd.do';",
					request.getContextPath());
			super.PostJs(alertStr);
			return;
		}
	}
	/**
	 * @see to修改
	 * @return
	 */
	public String toedit(){
		if(strangeid==null||strangeid.equals("")){
			return "error";
		}
		bean=service.getBaseStrangeWordById(strangeid);		
		return "toeditok";
		
	}
	/**
	 * @see do修改
	 * @return
	 */
	public void doedit(){
		if(bean==null||bean.getStrangeWordId()==null){
			return;
		}
		OperatLog log=this.getOptLog(OperatLog.DB_UPDATE, "修改生僻字，wrod="+bean.getStrangeWord());
		String info=service.doedit(bean,log);
		if(info!=null){
			String alertStr=String
			.format(
					"alert('"+info+"');location.href='%1$s/manager/day/datapreserve/strangeword_toedit.do?strangeid="+bean.getStrangeWordId()+"';",
					request.getContextPath());
			super.PostJs(alertStr);
			return;
		}else{
			String alertStr=String
			.format(
					"alert('修改成功!');location.href='%1$s/manager/day/datapreserve/strangeword_toedit.do?strangeid="+bean.getStrangeWordId()+"';",
					request.getContextPath());
			super.PostJs(alertStr);
			return;
		}
	}
	
	/**
	 * @see  ajax汉字转拼音
	 * @return
	 */
	public void getPY(){
		hanzi=hanzi==null?null:hanzi.trim();
		String meg=null;
		if(hanzi!=null&&!hanzi.equals("")){
			meg=Cn2Spell.converterToSpell(hanzi);
		}
		PrintWriter pw=null;
		try {
			pw=response.getWriter();
			pw.print(meg);
		} catch (IOException e) {
			new Log().error(this.getClass(), "ajax汉字转拼音",e);
		}finally{
			if(pw!=null){				
				pw.flush();
				pw.close();
			}
		}
	}
	
	
	public StrangeWordDbService getService() {
		return service;
	}
	public void setService(StrangeWordDbService service) {
		this.service = service;
	}
	public BaseStrangeWord getBean() {
		return bean;
	}
	public void setBean(BaseStrangeWord bean) {
		this.bean = bean;
	}
	public String getWhere() {
		return where;
	}
	public void setWhere(String where) {
		this.where = where;
	}
	public String getSelmethod() {
		return selmethod;
	}
	public void setSelmethod(String selmethod) {
		this.selmethod = selmethod;
	}
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public List<BaseStrangeWord> getBeanList() {
		return beanList;
	}
	public void setBeanList(List<BaseStrangeWord> beanList) {
		this.beanList = beanList;
	}
	public String getStrangeid() {
		return strangeid;
	}
	public void setStrangeid(String strangeid) {
		this.strangeid = strangeid;
	}
	public String getHanzi() {
		return hanzi;
	}
	public void setHanzi(String hanzi) {
		this.hanzi = hanzi;
	}
	
	
}
