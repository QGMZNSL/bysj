
package com.sincinfo.zxks.core.plan;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.sincinfo.zxks.bean.BasePro;
import com.sincinfo.zxks.bean.PlanLevel;
import com.sincinfo.zxks.bean.ProSeq;
import com.sincinfo.zxks.bean.ProType;
import com.sincinfo.zxks.common.action.WebActionSupport;
import com.sincinfo.zxks.common.db.DbUtil;
import com.sincinfo.zxks.common.util.Page;
import com.sincinfo.zxks.zxksdbs.BaseProKjDbService;

/**
 * @ClassName: LevelAction
 * @Description: 专业设置--考籍  根据考务的专业设置修改而来 <br>
 *               <br>
 * @author guanm
 * 
 */
public class BaseproKjAction extends WebActionSupport {
	/**
	 * @Fields serialVersionUID :
	 */
	private static final long serialVersionUID = -6217769699627471779L;

	private Page page = new Page();
	
	//层次
	private BasePro basepro;

	//用来传递查询结果
	private List<BasePro> baseproList;
	
	//专业类型列表
	private List<ProType> protypelList;
	
	//专业分类列表
	private List<ProSeq> proseqList;
	
	//专业层次列表
	private List<PlanLevel> planlevelList;
	
	private String proCode;//专业代码
	private String[] proCodes;//专业代码数组
	/**
	 * 初始化
	 */
	private void init() {
		if (basepro == null) {
			basepro = new BasePro();
			//department.setDepartmentGrade(getUser().getUserRole());
		}
		initPageUrl();
		initSelect();
	}

	/**
	 * 初始化分页查询地址
	 */
	private void initPageUrl() {
		StringBuilder url = new StringBuilder();
		url.append(String.format("%1$s/plan/baseprokj_Show.do",request.getContextPath()));
		//url.append(String.format("?department.departmentGrade=%1$s", department.getDepartmentGrade()));
		page.setPath(url.toString());
	}

	/**
	 * 初始化查询条件的列表（根据用户级别）
	 */
	private void initSelect() {
		BaseProKjDbService db = new BaseProKjDbService();
		this.protypelList = db.qryProTypeClasses();
		this.proseqList = db.qryProSeqClasses();
		this.planlevelList = db.qryPlanLevelClasses();	
	}

	/**
	 * 初始化查询条件的列表（根据用户级别）
	 */
	private void initSctForOpert() {
		BaseProKjDbService db = new BaseProKjDbService();
		this.protypelList = db.qryProTypeClasses();
		this.proseqList = db.qryProSeqClasses();
		this.planlevelList = db.qryPlanLevelClasses();		
	}

	/**
	 * 查询层次列表
	 * 
	 * @return
	 */
	public String Show() {
		init();
		BaseProKjDbService db = new BaseProKjDbService();
		this.baseproList = db.qry(null, page);
		basepro=new BasePro();
		basepro.setAllowGraduate("1");
		return "Show";
	}

	/**
	 * 进入添加页面
	 * 
	 * @return
	 */
	public String AddPre() {
//		initSctForOpert();
		return "Add";
	}
	/**
	 * @see ajax根据专业代码获得专业名称
	 */
	public void getProName(){
		proCode=proCode==null?"":proCode.trim();
		PrintWriter pw=null;
		try {
			pw=response.getWriter();
			if(proCode.equals("")){
				pw.write("nocode");
				return ;
			}
			BaseProKjDbService db = new BaseProKjDbService();
			BasePro bean=db.qry(proCode);
			if(bean==null){
				pw.write("nodata");
				return ;
			}else{
				pw.write(bean.getProName());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 保存添加
	 */
	public void Add() {
		proCode=proCode==null?"":proCode.trim();
		BaseProKjDbService db = new BaseProKjDbService();
		boolean isok=db.doAllowGraduate(proCode);
		
		if(isok==false){
			this.PostJs(String.format(
					"alert('专业编号输出有误，添加失败！');location.href='%1$s/plan/baseprokj_AddPre.do';",
					request.getContextPath()));
		}else{
			BasePro bean=db.qry(proCode);
			this.PostJs(String.format(
					"alert('添加(%2$s-%3$s)成功！');location.href='%1$s/plan/baseprokj_AddPre.do';",
					request.getContextPath(),bean.getProCode(),bean.getProName()));
		}
		
	}

//	/**
//	 * 进入修改页面
//	 * 
//	 * @return
//	 */
//	public String EditPre() {
//		initSctForOpert();
//		BaseProKjDbService db = new BaseProKjDbService();
//		this.basepro = db.qry(basepro.getProCode());
//		return "Edit";
//	}
//
//	/**
//	 * 保存修改内容
//	 */
//	public void Edit() {
//		BaseProKjDbService db = new BaseProKjDbService();
//		boolean editFlag = db.save(basepro,1);
//		if (editFlag) {
//			this.PostJs(String.format(
//									"alert('修改成功！');location.href='%1$s/plan/baseprokj_Show.do';",
//									request.getContextPath()));
//		} else {
//			this.PostJs(String.format(
//					"alert('修改失败！');location.href='%1$s/plan/baseprokj_Show.do';",
//					request.getContextPath()));
//		}
//	}

	/**
	 * 删除层次
	 */
	public void Del() {
		BaseProKjDbService db = new BaseProKjDbService();
		boolean delFlag = db.del(basepro.getProCode());
		if (delFlag) {
			this.PostJs(String.format(
									"alert('删除成功！');location.href='%1$s/plan/baseprokj_Show.do';",
									request.getContextPath()));
		} else {
			this.PostJs("alert('删除失败！请重试。');location.back();");
		}
	}
	
	/**
	 * 设置分页地址
	 */
	private void setUrl() {
		StringBuilder url = new StringBuilder();
		url.append(String.format(
				"location.href=%1$s/plan/baseprokj_qry.do?1=1",request.getContextPath()));
		//url.append(String.format("?department.departmentCode=%1$s", department.getDepartmentCode()));
		page.setPath(url.toString());
	}
	
	/**
	 * 获取信息列表
	 * @return
	 */
	public String qry() {
		// 设置分页地址
		setUrl();
		
		initSctForOpert();
		
		// 获取点击的层次信息
		BaseProKjDbService db = new BaseProKjDbService();
		
		// 查询对应层次下的所有职位列表
		this.baseproList = db.qry(basepro, page);
		
		// 加载权限列表
		return "Show";
	}
	
	/**
	 * @see 批量设置禁止申办毕业的专业
	 */
	public void noAllowGraduate(){
		if(proCodes==null||proCodes.length==0){
			super.GoBack("请至少选择一条记录！");
			return ;
		}
		BaseProKjDbService db = new BaseProKjDbService();
		boolean isok=db.doNoAllowGraduate(proCodes);
		if(isok==false){
			this.PostJs(String.format(
					"alert('服务器繁忙，操作失败！');location.href='%1$s/plan/baseprokj_Show.do';",
					request.getContextPath()));
		}else{
			this.PostJs(String.format(
					"alert('设置成功！');location.href='%1$s/plan/baseprokj_Show.do';",
					request.getContextPath()));
		}
	}
	
	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public BasePro getBasepro() {
		return basepro;
	}

	public void setBasepro(BasePro basepro) {
		this.basepro = basepro;
	}

	public List<BasePro> getBaseproList() {
		return baseproList;
	}

	public void setBaseproList(List<BasePro> baseproList) {
		this.baseproList = baseproList;
	}

	public List<ProType> getProtypelList() {
		return protypelList;
	}

	public void setProtypelList(List<ProType> protypelList) {
		this.protypelList = protypelList;
	}

	public List<ProSeq> getProseqList() {
		return proseqList;
	}

	public void setProseqList(List<ProSeq> proseqList) {
		this.proseqList = proseqList;
	}

	public List<PlanLevel> getPlanlevelList() {
		return planlevelList;
	}

	public void setPlanlevelList(List<PlanLevel> planlevelList) {
		this.planlevelList = planlevelList;
	}
	
	public String getProCode() {
		return proCode;
	}

	public void setProCode(String proCode) {
		this.proCode = proCode;
	}

	public String[] getProCodes() {
		return proCodes;
	}

	public void setProCodes(String[] proCodes) {
		this.proCodes = proCodes;
	}
	
}
