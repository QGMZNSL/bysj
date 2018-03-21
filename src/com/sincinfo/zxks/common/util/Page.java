package com.sincinfo.zxks.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName: Page 
 * @Description: 分页功能 <br>
 * <br>
 * @author litian
 * @date Jan 31, 2012 2:51:17 PM<br>
 *  
*/
public class Page {
	private String formName = "specialPageForm";
	
	// 页码
	private long pagenum = 0;

	// 获得总记录数
	private long pagecount;

	// 表单显示方式一
	private String pageInfo;
	
	// 表单显示方式二
	private String pageForm;
	private String pageLink;

	// 总页数
	private long pages;

	// 每页显示记录数
	private long pagesize;

	// 基本路径
	private String path;
	
	// 传参列表
	private Map<String, String> paramMap;
	
	// 当前地址
	private String currPath;
	
	/**
	 * 构造方法
	 */
	public Page() {
		this.paramMap = new HashMap<String, String>();
		
		pages = 1;
		if (pagesize == 0) {
			pagesize = 10;// 默认条数
		}
	}

	/**
	 * 构造方法
	 */
	public Page(long pagenum, long pagecount, long pagesize, String path) {
		this.paramMap = new HashMap<String, String>();
		
		this.pagenum = pagenum;
		this.pagecount = pagecount;
		this.pagesize = pagesize;
		this.path = path;
	}

	/**
	 * 构造方法
	 */
	public Page(long pagenum, long pagecount, String path) {
		this.paramMap = new HashMap<String, String>();
		
		this.pagenum = pagenum;
		this.pagecount = pagecount;
		this.path = path;
	}

	/**
	 * 根据SQL及分页对象内部属性，生成对应的sql
	 * @param sql 传入的sql字符串
	 * @return 查询当页的sql
	 */
	public String getSql(String sql) {
		String sqlStr = String.format("select sp11.*,rownum r from (%1$s) sp11", sql);
		sqlStr = String.format("select sp12.* from (%1$s) sp12 where sp12.r>%2$s", sqlStr, (this.pagenum * this.pagesize));
		sqlStr = String.format("select * from (%1$s) where rownum<=%2$s", sqlStr, this.pagesize);
		return sqlStr;
	}
	
	/**
	 * 传入查询条件的分页参数（其中不包括与页码及总页数相关的内容）
	 * @param key 属性名
	 * @param value 属性值
	 */
	public void pushParam( String key, String value) {
		key = StringTool.trim(key);
		value = StringTool.trim(value);
		this.paramMap.put(key, value);
	}
	
	/**
	 * 根据传入地址，分析参数，返回?参数之前的部分，并将参数写入Page对象中的参数映射表中
	 * @param path 传入的带参地址
	 * @return String url（即，仅显示到.do或者.jsp部分）
	 */
	public String seperatePathParam( String path) {
		String onlyUrl = path;
		if (path!=null&& path.indexOf("?") > -1 ) {
			// 存在参数
			String[] temp = onlyUrl.split("\\?");
			onlyUrl = temp[0];
			String params = temp[1];
			
			// 判断参数部分是否为空
			if ( !"".equals(StringTool.trim(params))) {
				String[] paramArr = params.split("&");
				String[] tempKV = null;
				for ( String eachParam : paramArr) {
					tempKV = eachParam.split("=");
					this.paramMap.put(StringTool.trim(tempKV[0]), tempKV.length < 2 ? "" : StringTool.trim(tempKV[1]));
				}
			}
		}
		return onlyUrl;
	}
	
	/**
	 * 服务管理分页
	 * 
	 * @return
	 */
	public String getPageInfo() {
		// 解析url
		path = seperatePathParam(path);

		// 计算分页数值
		long pageUpNum = this.pagenum - 1;
		long pageDownNum = this.pagenum + 1;
		long pageFirstNum = 0;
		long pageLastNum = this.pages - 1;
		String pageSizeHide = String.format("<input type='hidden' name='page.pagesize' value='%1$s' />", this.pagesize);
		String pageNumHide = String.format("<input type='hidden' name='page.pagenum' id='pageNumInfo' value='%1$s' />", this.pagenum);
		
		// 遍历参数列表 ,paramBuff是参数组合的一组hidden表单
		StringBuilder paramBuff = new StringBuilder("");
		String paramMode = "<input type='hidden' name='%1$s' value='%2$s' />";
		Set<String> keys = paramMap.keySet();
		if ( !keys.isEmpty()) {
			Iterator<String> itKey = keys.iterator();
			String tmpKey = null;
			while ( itKey.hasNext()) {
				tmpKey = itKey.next();
				paramBuff.append(String.format(paramMode, tmpKey, paramMap.get(tmpKey)));
			}
		}
		
		// 连接显示部分
		StringBuilder pageInfoBuf = new StringBuilder();
		pageInfoBuf.append(String.format("<form name='%1$s' id='%1$s' action='%2$s' method='post' style='display:inline;'>", formName, path));
		// 保存重要参数
		pageInfoBuf.append(pageSizeHide);
		pageInfoBuf.append(pageNumHide);
		pageInfoBuf.append(paramBuff.toString());
		// 页面显示
		pageInfoBuf.append( String.format("共 %1$s 条记录&nbsp;&nbsp;%2$s / %3$s 页&nbsp;&nbsp;", this.pagecount,(this.pagenum + 1), pages));
		pageInfoBuf.append("&nbsp;<a href='#' onclick='changePage(\"first\");'>首页</a>");
		pageInfoBuf.append("&nbsp;<a href='#' onclick='changePage(\"prev\");'>上一页</a>");
		pageInfoBuf.append("&nbsp;<a href='#' onclick='changePage(\"next\");'>下一页</a>");
		pageInfoBuf.append("&nbsp;<a href='#' onclick='changePage(\"last\");'>尾页</a>");
		pageInfoBuf.append(String.format("<input type='text' class='inputText inputPage' id='jumpPagenum' value='%1$s' />", this.pagenum + 1));
		pageInfoBuf.append("<input class='inputButton inputButtonS' type='button' value='GO' onclick='jumpToPageNum();' />");
		// js部分
		pageInfoBuf.append("<script type='text/javascript'>");
		// 首页，上一页，下一页，尾页
		pageInfoBuf.append("function changePage( sign) {");
		if (this.pagenum == 0) {
			pageInfoBuf.append("if ( 'first' == sign) {");
			pageInfoBuf.append("return;");
			pageInfoBuf.append("} else if ( 'prev' == sign) {");
			pageInfoBuf.append("return;");
			pageInfoBuf.append("}");
		}
		if (this.pagenum + 1 == this.pages) {
			pageInfoBuf.append("if ( 'next' == sign) {");
			pageInfoBuf.append("return;");
			pageInfoBuf.append("} else if ( 'last' == sign) {");
			pageInfoBuf.append("return;");
			pageInfoBuf.append("}");
		}
		pageInfoBuf.append("var pagenum = document.getElementById('pageNumInfo');");
		pageInfoBuf.append("if ( 'first' == sign) {");
		pageInfoBuf.append(String.format("pagenum.value = %1$s;", pageFirstNum));
		pageInfoBuf.append("} else if ( 'prev' == sign) {");
		pageInfoBuf.append(String.format("pagenum.value = %1$s;", pageUpNum));
		pageInfoBuf.append("} else if ( 'next' == sign) {");
		pageInfoBuf.append(String.format("pagenum.value = %1$s;", pageDownNum));
		pageInfoBuf.append("} else if ( 'last' == sign) {");
		pageInfoBuf.append(String.format("pagenum.value = %1$s;", pageLastNum));
		pageInfoBuf.append("}");
		pageInfoBuf.append(String.format("document.getElementById('%1$s').submit();", this.formName));
		pageInfoBuf.append("}");
		// go跳转
		pageInfoBuf.append("function jumpToPageNum() {");
		pageInfoBuf.append(String.format("if( 0 == %1$s ) {", this.pagecount));
		pageInfoBuf.append("return;");
		pageInfoBuf.append("}");
		pageInfoBuf.append("var jumpPagenum = document.getElementById('jumpPagenum').value;");
		pageInfoBuf.append(String.format("if(jumpPagenum > %1$s){", this.pages));
		pageInfoBuf.append("alert('您输入的跳转页码大于总页数！');");
		pageInfoBuf.append("return;");
		pageInfoBuf.append("}");
		pageInfoBuf.append("if( isNaN(jumpPagenum)){");
		pageInfoBuf.append("alert('您输入的跳转页码有误！');");
		pageInfoBuf.append("return;");
		pageInfoBuf.append("}");
		pageInfoBuf.append("var pagenum = document.getElementById('pageNumInfo');");
		pageInfoBuf.append("pagenum.value = jumpPagenum - 1;");
		pageInfoBuf.append(String.format("document.getElementById('%1$s').submit();", this.formName));
		pageInfoBuf.append("}");
		
		pageInfoBuf.append("</script>");
		pageInfoBuf.append("</form>");
		
		this.pageInfo = pageInfoBuf.toString();
		
		return pageInfo;
	}


	/**
	 * 由于原分页代码的页数是由0开始因些增加此方法进行转换，对于需要用页数由1开始的程序
	 */
	public void decreasePageNum(int step) {
		if (this.pagenum > 0) {
			this.pagenum -= step;
		}
	}

	private void getPages() {
		this.pages = this.pagecount % this.pagesize == 0 ? this.pagecount
				/ this.pagesize : this.pagecount / this.pagesize + 1;

		if (this.pagenum >= pages) {
			this.pagenum = pages - 1;
		}

		if (pagenum < 0) {
			this.pagenum = 0;
		}

		if (pages < 1) {
			this.pages = 1;
		}
	}

	public long getPagenum() {
		return pagenum;
	}

	public void setPagenum(long pagenum) {
		this.pagenum = pagenum;
	}

	public long getPagecount() {
		return pagecount;
	}

	public String setPagecount(long pagecount, String sql) {
		this.pagecount = pagecount;
		getPages();
		return getSql(sql);
	}

	public void setPagecount(long pagecount) {
		this.pagecount = pagecount;
		getPages();
	}

	public long getPagesize() {
		return pagesize;
	}

	public void setPagesize1(long pagesize) {
		// this.pagesize = pagesize;
	}

	public void setPagesize(long pagesize) {
		this.pagesize = pagesize;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setPages(long pages) {
		this.pages = pages;
	}

	public void setPageInfo(String pageInfo) {
		this.pageInfo = pageInfo;
	}

	public String getPageForm() {
		// 解析url
		path = seperatePathParam(path);

		// 计算分页数值
		long pageUpNum = this.pagenum - 1;
		long pageDownNum = this.pagenum + 1;
		long pageFirstNum = 0;
		long pageLastNum = this.pages - 1;
		String pageSizeHide = String.format("<input type='hidden' name='page.pagesize' value='%1$s' />", this.pagesize);
		String pageNumHide = String.format("<input type='hidden' name='page.pagenum' id='pageNumInfo' value='%1$s' />", this.pagenum);
		
		// 遍历参数列表 ,paramBuff是参数组合的一组hidden表单
		StringBuilder paramBuff = new StringBuilder("");
		String paramMode = "<input type='hidden' name='%1$s' value='%2$s' />";
		Set<String> keys = paramMap.keySet();
		if ( !keys.isEmpty()) {
			Iterator<String> itKey = keys.iterator();
			String tmpKey = null;
			while ( itKey.hasNext()) {
				tmpKey = itKey.next();
				paramBuff.append(String.format(paramMode, tmpKey, paramMap.get(tmpKey)));
			}
		}
		
		// 连接显示部分
		StringBuilder pageInfoBuf = new StringBuilder();
		pageInfoBuf.append(String.format("<form name='%1$s' id='%1$s' action='%2$s' method='post' style='display:inline;'>", formName, path));
		// 保存重要参数
		pageInfoBuf.append(pageSizeHide);
		pageInfoBuf.append(pageNumHide);
		pageInfoBuf.append(paramBuff.toString());
		// js部分
		pageInfoBuf.append("<script type='text/javascript'>");
		// 首页，上一页，下一页，尾页
		pageInfoBuf.append("function changePage( sign) {");
		if (this.pagenum == 0) {
			pageInfoBuf.append("if ( 'first' == sign) {");
			pageInfoBuf.append("return;");
			pageInfoBuf.append("} else if ( 'prev' == sign) {");
			pageInfoBuf.append("return;");
			pageInfoBuf.append("}");
		}
		if (this.pagenum + 1 == this.pages) {
			pageInfoBuf.append("if ( 'next' == sign) {");
			pageInfoBuf.append("return;");
			pageInfoBuf.append("} else if ( 'last' == sign) {");
			pageInfoBuf.append("return;");
			pageInfoBuf.append("}");
		}
		pageInfoBuf.append("var pagenum = document.getElementById('pageNumInfo');");
		pageInfoBuf.append("if ( 'first' == sign) {");
		pageInfoBuf.append(String.format("pagenum.value = %1$s;", pageFirstNum));
		pageInfoBuf.append("} else if ( 'prev' == sign) {");
		pageInfoBuf.append(String.format("pagenum.value = %1$s;", pageUpNum));
		pageInfoBuf.append("} else if ( 'next' == sign) {");
		pageInfoBuf.append(String.format("pagenum.value = %1$s;", pageDownNum));
		pageInfoBuf.append("} else if ( 'last' == sign) {");
		pageInfoBuf.append(String.format("pagenum.value = %1$s;", pageLastNum));
		pageInfoBuf.append("}");
		pageInfoBuf.append(String.format("document.getElementById('%1$s').submit();", this.formName));
		pageInfoBuf.append("}");
		// go跳转
		pageInfoBuf.append("function jumpToPageNum() {");
		pageInfoBuf.append(String.format("if( 0 == %1$s ) {", this.pagecount));
		pageInfoBuf.append("return;");
		pageInfoBuf.append("}");
		pageInfoBuf.append("var jumpPagenum = document.getElementById('jumpPagenum').value;");
		pageInfoBuf.append(String.format("if(jumpPagenum > %1$s){", this.pages));
		pageInfoBuf.append("alert('您输入的跳转页码大于总页数！');");
		pageInfoBuf.append("return;");
		pageInfoBuf.append("}");
		pageInfoBuf.append("if( isNaN(jumpPagenum)){");
		pageInfoBuf.append("alert('您输入的跳转页码有误！');");
		pageInfoBuf.append("return;");
		pageInfoBuf.append("}");
		pageInfoBuf.append("var pagenum = document.getElementById('pageNumInfo');");
		pageInfoBuf.append("pagenum.value = jumpPagenum - 1;");
		pageInfoBuf.append(String.format("document.getElementById('%1$s').submit();", this.formName));
		pageInfoBuf.append("}");
		
		pageInfoBuf.append("</script>");
		pageInfoBuf.append("</form>");
		
		this.pageForm = pageInfoBuf.toString();
		
		return pageForm;
	}

	public void setPageForm(String pageForm) {
		this.pageForm = pageForm;
	}

	public String getPageLink() {
		// 连接显示部分
		StringBuilder pageInfoBuf = new StringBuilder();
		pageInfoBuf.append( String.format("共 %1$s 条记录&nbsp;&nbsp;%2$s / %3$s 页&nbsp;&nbsp;", this.pagecount,(this.pagenum + 1), pages));
		pageInfoBuf.append("&nbsp;<a href='#' onclick='changePage(\"first\");'>首页</a>");
		pageInfoBuf.append("&nbsp;<a href='#' onclick='changePage(\"prev\");'>上一页</a>");
		pageInfoBuf.append("&nbsp;<a href='#' onclick='changePage(\"next\");'>下一页</a>");
		pageInfoBuf.append("&nbsp;<a href='#' onclick='changePage(\"last\");'>尾页</a>");
		pageInfoBuf.append(String.format("<input type='text' class='inputText inputPage' id='jumpPagenum' value='%1$s' />", this.pagenum + 1));
		pageInfoBuf.append("<input class='inputButton inputButtonS' type='button' value='GO' onclick='jumpToPageNum();' />");

		this.pageLink = pageInfoBuf.toString();
		
		return pageLink;
	}

	public void setPageLink(String pageLink) {
		this.pageLink = pageLink;
	}

	public String getCurrPath() {

		// 遍历参数列表 ,paramBuff是参数组合的一组hidden表单
		StringBuilder paramBuff = new StringBuilder();
		String paramMode = "&%1$s=%2$s";
		Set<String> keys = paramMap.keySet();
		if ( !keys.isEmpty()) {
			Iterator<String> itKey = keys.iterator();
			String tmpKey = null;
			while ( itKey.hasNext()) {
				tmpKey = itKey.next();
				paramBuff.append(String.format(paramMode, tmpKey, paramMap.get(tmpKey)));
			}
		}
		
		// 复制当前地址
		this.currPath = this.path + "?t=" + new SimpleDateFormat("yyyyMMddhhmmss").format(new Date()) + paramBuff.toString();
		this.currPath += String.format("&%1$s=%2$s", "page.pagesize", this.pagesize);
		this.currPath += String.format("&%1$s=%2$s", "page.pagenum", this.pagenum);
		
		return currPath;
	}

	public void setCurrPath(String currPath) {
		this.currPath = currPath;
	}

}
