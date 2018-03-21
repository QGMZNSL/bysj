package com.sincinfo.zxks.core.day.datapreserve;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.river.nbf.validate.annotation.Validate;

import com.sincinfo.zxks.bean.BaseCertificateQuery;
import com.sincinfo.zxks.bean.SysCodeCertificate;
import com.sincinfo.zxks.common.action.WebActionSupport;
import com.sincinfo.zxks.common.util.Page;
import com.sincinfo.zxks.zxksdbs.CertificateManagerDbService;
/**
 * @see 证书查询管理  请求处理类 qc_*
 * @author guanm
 *
 */
public class CertificateManagerAction extends WebActionSupport{
	
	private CertificateManagerDbService service;//业务类
	private Page page;//分页
	
	private String ctype;//证书类型
	private String cid;//查询证书编号
	private String cname;//查询证书名称
	private String curl;//查询证书访问地址
	private String isuse;//是否可用
	
	private List<SysCodeCertificate> codeList;//证书类型集合
	private List<BaseCertificateQuery> certList;//根据某个 证书类型  获得该类型下的证书查询
	private List<BaseCertificateQuery> dataList;//证书数据集合
	
	private BaseCertificateQuery cBean;//证书查询对象
	
	public CertificateManagerAction(){
		service=new CertificateManagerDbService();
	}
	
	/**
	 * @see 默认页
	 */
	public String manager(){
		ctype=ctype==null?"":ctype.trim();
		cid=cid==null?"":cid.trim();
		
		if(page==null){
			page=new Page();
		}
		page.setPagecount(service.getCertificateCount(ctype, cid));
		page.setPath(String.format("qc_manager.do?ctype=%1$s&cid=%2$s",ctype,cid));
		String sql=page.getSql(service.getCertificateSql(ctype, cid));
		dataList=service.getObjList(sql, BaseCertificateQuery.class);
		codeList=service.getCertificateList();
		if(!ctype.equals("")){
			certList=service.getCertificateQueryByType(ctype);
		}
		return "manager";
	}

	/**
	 * @see ajax获得某个类型下的证书集合
	 */
	public void ajaxGetCid(){
		ctype=ctype==null?"":ctype.trim();
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw=null;
		try {
			pw=response.getWriter();
			if(ctype.equals("")){
				pw.write("");
			}else{
				pw.write(service.ajaxGetStr(ctype));
			}
		} catch (IOException e) {
			pw.write("");
			e.printStackTrace();
		}
	}
	
	/**
	 * @see to添加页
	 * @return
	 */
	public String toAddCert(){
		codeList=service.getCertificateList();
		
		return "toadd";
	}
	
	/**
	 * @see do 添加
	 * @return
	 */
	public void doAddCert(){
		boolean isok=service.save(ctype.trim(), cname.trim(), curl.trim(), isuse.trim());
		if(isok==false){
			String alert=String.format("alert('添加失败！');location.href='%1$s/manager/day/datapreserve/qc_toAddCert.do'", request.getContextPath());
			super.PostJs(alert);
			return ;
		}else{
			String alert=String.format("alert('添加成功！');location.href='%1$s/manager/day/datapreserve/qc_manager.do'", request.getContextPath());
			super.PostJs(alert);
			return ;
		}
	}
	
	/**
	 * @see to 修改
	 * @return
	 */
	public String toEdit(){
		cid=cid==null?"":cid.trim();
		cBean=service.getCertBean(cid);
		codeList=service.getCertificateList();
		return "toedit";
	}
	/**
	 * @see do 修改
	 * @return
	 */
	public void doEdit(){
		boolean isok=service.update(cid.trim(), ctype.trim(), cname.trim(), curl.trim(), isuse.trim());
		if(isok==false){
			String alert=String.format("alert('修改失败！');location.href='%1$s/manager/day/datapreserve/qc_toEdit.do?cid=%2$s'", request.getContextPath(),cid);
			super.PostJs(alert);
			return ;
		}else{
			String alert=String.format("alert('修改成功！');location.href='%1$s/manager/day/datapreserve/qc_manager.do'", request.getContextPath());
			super.PostJs(alert);
			return ;
		}
	}
	/**
	 * @see 删除
	 * 
	 */
	public void delete(){
		cid=cid==null?"":cid.trim();
		boolean isok=service.delete(cid);
		if(isok==false){
			String alert=String.format("alert('删除失败！');location.href='%1$s/manager/day/datapreserve/qc_manager.do'", request.getContextPath());
			super.PostJs(alert);
			return ;
		}else{
			String alert=String.format("alert('删除成功！');location.href='%1$s/manager/day/datapreserve/qc_manager.do'", request.getContextPath());
			super.PostJs(alert);
			return ;
		}
	}
	
	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}
	@Validate(exp="s(1,50)")
	public String getCtype() {
		return ctype;
	}

	public void setCtype(String ctype) {
		this.ctype = ctype;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public List<BaseCertificateQuery> getDataList() {
		return dataList;
	}

	public void setDataList(List<BaseCertificateQuery> dataList) {
		this.dataList = dataList;
	}

	public List<SysCodeCertificate> getCodeList() {
		return codeList;
	}

	public void setCodeList(List<SysCodeCertificate> codeList) {
		this.codeList = codeList;
	}

	public List<BaseCertificateQuery> getCertList() {
		return certList;
	}

	public void setCertList(List<BaseCertificateQuery> certList) {
		this.certList = certList;
	}
	@Validate(exp="s(1,50)")
	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}
	
	public String getCurl() {
		return curl;
	}

	public void setCurl(String curl) {
		this.curl = curl;
	}

	public String getIsuse() {
		return isuse;
	}

	public void setIsuse(String isuse) {
		this.isuse = isuse;
	}

	public BaseCertificateQuery getCBean() {
		return cBean;
	}

	public void setCBean(BaseCertificateQuery bean) {
		cBean = bean;
	}
	
	
}
