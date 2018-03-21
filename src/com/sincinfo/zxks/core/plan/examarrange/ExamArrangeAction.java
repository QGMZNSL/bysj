package com.sincinfo.zxks.core.plan.examarrange;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import jxl.Sheet;
import jxl.Workbook;
import com.sincinfo.zxks.bean.BaseSyllabusArrange;
import com.sincinfo.zxks.bean.BaseSyllabusTime;
import com.sincinfo.zxks.common.action.WebActionSupport;
import com.sincinfo.zxks.common.util.Page;
import com.sincinfo.zxks.common.util.StringTool;
import com.sincinfo.zxks.zxksdbs.ExamArrangeService;

public class ExamArrangeAction extends WebActionSupport{
	private static final long serialVersionUID = 2493944854488291529L;
	private ExamArrangeService service = null;
	public ExamArrangeAction() {
		this.service = new ExamArrangeService();
	}
	private List<String> lYear;
	private BaseSyllabusTime baseSyllabusTime=new BaseSyllabusTime();
	private List<Object[]> lo;
	private List<Object[]> losec;
	private String menutype;
	private String difftype;
	private Object[] showlist;
	private int tableLong;
	private int tableCol;
	private File excelFile;
	private String excelFileFileName;
	private String errortext;
	private String[] lie={"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
	private Page page = new Page();
	
	public String show(){
		lYear=service.lExaminationYear();
		if(StringTool.isEmpty(menutype)){
			this.PostJs(String.format(
					"alert('缺少参数！');location.href='%1$s/manager/plan/examarrange/exam_show.do?menutype=1';",
					request.getContextPath()));
			return null;
		}
		if(!menutype.equals("1") && !menutype.equals("2") && !menutype.equals("3") && !menutype.equals("4")){
			this.PostJs(String.format(
					"alert('缺少参数！');location.href='%1$s/manager/plan/examarrange/exam_show.do?menutype=1';",
					request.getContextPath()));
			return null;
		}
		if(menutype.equals("4")){
			baseSyllabusTime.setCountryProvince("1");
			baseSyllabusTime.setCommView("1");
		}
		else if(menutype.equals("3")){
			baseSyllabusTime.setCountryProvince("3");
			baseSyllabusTime.setCommView("1");//CommView("1")就会遍历公共课
		}
		else{
			baseSyllabusTime.setCountryProvince(menutype);
			baseSyllabusTime.setCommView("0");
		}
		StringBuffer sb = new StringBuffer();
		sb.append("/ZK_CORE/manager/plan/examarrange/exam_show.do");
        if(baseSyllabusTime.getApplyYear()==null){
 	        baseSyllabusTime.setApplyYear(service.getMaxYear());
        }
		this.page.pushParam("menutype",menutype);
		this.page.pushParam("baseSyllabusTime.examinationTime",baseSyllabusTime.getExaminationTime());
		this.page.pushParam("baseSyllabusTime.examinationDate",baseSyllabusTime.getExaminationDate());
		this.page.pushParam("baseSyllabusTime.applyYear",baseSyllabusTime.getApplyYear());
		this.page.pushParam("baseSyllabusTime.proCode",baseSyllabusTime.getProCode());
		this.page.pushParam("baseSyllabusTime.proName",baseSyllabusTime.getProName());
		this.page.pushParam("baseSyllabusTime.syllabusCode",baseSyllabusTime.getSyllabusCode());
		this.page.pushParam("baseSyllabusTime.syllabusName",baseSyllabusTime.getSyllabusName());
		this.page.pushParam("baseSyllabusTime.commView",baseSyllabusTime.getCommView());
		this.page.pushParam("baseSyllabusTime.examUnitary",baseSyllabusTime.getExamUnitary());
        page.setPath(sb.toString());
        String sql="";
        if(baseSyllabusTime.getCommView().equals("1")){
        	sql=service.shanxiBaseSyllabusTime(baseSyllabusTime);
        }
        else{
        	sql=service.getStrBaseSyllabusTime(baseSyllabusTime);
        }
        String sqlPage = page.setPagecount(service.getNum(String.format("select count(*) from (%1$s)",sql)), sql);
        int i,j,l,ll;
        List<String> ssss=service.distinctMonth(baseSyllabusTime);
		ll=ssss.size();
		String month;//EXAMINATION_CODE
		losec=new ArrayList<Object[]>();//标题
		List<String> lexamTimeCode=new ArrayList<String>();
		List<String[]> lsyllabus;
		List<BaseSyllabusArrange> lsec;
		String s_s;
		for(j=0;j<ll;j++){
			month=ssss.get(j);
			lsec=service.lBaseSyllabusArrange(baseSyllabusTime.getApplyYear(),month,baseSyllabusTime.getExaminationDate(),baseSyllabusTime.getExaminationTime());
			l=lsec.size();
			Object[] oo={lsec.size(),month,lsec};//
			losec.add(oo);
			for(i=0;i<l;i++){
				lexamTimeCode.add(lsec.get(i).getSyllabusArrangeId());
//				System.out.println(lsec.size()+"-"+str+"-"+lsec.get(i)[0]+"-"+lsec.get(i)[1]+"-"+lsec.get(i)[2]);
			}
		}
		List<String[]> lss=new ArrayList<String[]>();
		if(baseSyllabusTime.getCommView().equals("1")){
			lss=service.getRsArrayList(sqlPage, 2);//取唯一的专业号
		}
		else{
			lss=service.getRsArrayList(sqlPage, 4);//取唯一的专业号
		}
		l=lss.size();
		ll=lexamTimeCode.size();
		tableCol=ll+1;
		tableLong=tableCol*150;
		lo=new ArrayList<Object[]>();
		for(i=0;i<l;i++){
			String[] ss=lss.get(i);
			lsyllabus=new ArrayList<String[]>();
	        for(j=0;j<ll;j++){
	        	if(menutype.equals("3")){
	        		String[] asdf={service.getSyllabus4(lexamTimeCode.get(j),ss[0],baseSyllabusTime.getSyllabusCode(),baseSyllabusTime.getSyllabusName(),baseSyllabusTime.getExamUnitary(),"<br/>"),lexamTimeCode.get(j),ss[0],ss[1]};//传值供修改使用
	        		lsyllabus.add(asdf);
	        	}
	        	else if(menutype.equals("4")){
	        		String[] asdf={service.getSyllabus2(lexamTimeCode.get(j),ss[0],baseSyllabusTime.getSyllabusCode(),baseSyllabusTime.getSyllabusName(),"<br/>")};
	        		lsyllabus.add(asdf);
	        	}
	        	else{
	        		String[] asdf={service.getSyllabus13(lexamTimeCode.get(j),ss[0],baseSyllabusTime.getCountryProvince(),baseSyllabusTime.getSyllabusCode(),baseSyllabusTime.getSyllabusName(),"<br/>")};
	        		lsyllabus.add(asdf);
	        	}
	        }
	        if(baseSyllabusTime.getCommView().equals("1")){
	        	Object[] ool={ss[1]+"<br/>（"+ss[0]+"）",lsyllabus};
	        	lo.add(ool);
	        }
	        else{
	        	s_s=ss[3];//是否公共课：0、普通课程，1、公共课
		        if(s_s.equals("1")){
		        	Object[] ool={ss[1],lsyllabus};
		        	lo.add(ool);
		        }
		        else{
		        	Object[] ool={ss[1]+"<br/>（"+ss[0]+"）",lsyllabus};
		        	lo.add(ool);
		        }
	        }
		}
		if(baseSyllabusTime.getCountryProvince().equals("3")){
			return "difference";
		}
		else{
			return "show";
		}
	}
	
	public String excel(){
		lYear=service.lExaminationYear();
		return "excel";
	}
	
	public String autoExcel(){
		return "autoExcel";
	}
	
	public String up(){//Excel上传
		try{
			if(StringTool.isEmpty(menutype)){
				this.PostJs(String.format(
						"alert('上传失败！');location.href='%1$s/manager/plan/examarrange/exam_show.do?menutype=1';",
						request.getContextPath()));
				return null;
			}
			if(!menutype.equals("1") && !menutype.equals("2")){
				this.PostJs(String.format(
						"alert('上传失败！');location.href='%1$s/manager/plan/examarrange/exam_show.do?menutype=1';",
						request.getContextPath()));
				return null;
			}
			List<String> list_error=new ArrayList<String>();
			int upfilenum=0,beginline=0,endline=0,i_procode=0;
			if(excelFileFileName==null || excelFileFileName.equals("")){//---
				list_error.add("没有上传文件");
			}
			else{
				/*接收参数*/
				String examYear=request.getParameter("examYear");
				String sheet=request.getParameter("sheet");//---
				beginline=Integer.parseInt(request.getParameter("beginline"));//---
				endline=Integer.parseInt(request.getParameter("endline"));//---
				String name=excelFileFileName.substring(excelFileFileName.lastIndexOf("\\") + 1);
				i_procode=Integer.parseInt(request.getParameter("lie_procode"));
			    if(name==null || name.trim().equals("")){
			    	list_error.add("没有要上传的文件");
			    }
			    else{
			    	name=name.trim();
			    	if(name.lastIndexOf(".")==-1){
			    		list_error.add("上传文件没有后缀名");
			    	}
			        else{
			        	String contentType=name.substring(name.lastIndexOf(".")+1);
			        	contentType=contentType.trim();
	 			    	if(contentType==null ||contentType.equals("")){
	 			    		list_error.add("上传文件没有后缀名");
	 			    	}
	 			        else{
	 			        	contentType=contentType.toLowerCase();
	 			        	if(!contentType.equals("xls")){
	 			        		list_error.add("请上传后缀名为“xls”的文件");
	 			        	}
	 			        	else{
	 			        		InputStream is = new FileInputStream(excelFile);
	 			        		Workbook wb = Workbook.getWorkbook(is);
	 			        		Sheet st = wb.getSheet(sheet);
	 			        		if(st==null){
	 			        			list_error.add("没有找到你输入的“导入的Excel工作表名”");
	 			        		}
	 			        		else{
	 			        			int i,j,l;
	 			        			String lienum=request.getParameter("lienum");
	 			        			if(lienum==null || !StringTool.testInt(lienum)){
	 			        				list_error.add("没有点击页面上的“显示月份”按钮");
	 			        			}
	 			        			else{
	 			        				int i_lienum=Integer.parseInt(lienum);
	 			        				String syllabusArrangeId,month,day,examinationTime,beginTime,endTime,excellie;
	 			        				ArrayList<String> alist=new ArrayList<String>();
			 			        		List<String> idlist=new ArrayList<String>();
			 			        		List<String> lielist=new ArrayList<String>();
	 			        				boolean bool=true;
	 			        				String[] sql;
	 			        				for(i=1;i<i_lienum;i++){
	 			        					bool=true;
	 			        					syllabusArrangeId=request.getParameter("syllabusArrangeId"+i);
	 			        					month=request.getParameter("month"+i);
	 			        					day=request.getParameter("day"+i);
	 			        					examinationTime=request.getParameter("examinationTime"+i);
	 			        					beginTime=request.getParameter("beginTime"+i);
	 			        					endTime=request.getParameter("endTime"+i);
	 			        					excellie=request.getParameter("lie"+i);
	 			        					if(syllabusArrangeId!=null && excellie!=null && !StringTool.isEmpty(examYear) && StringTool.testInt(examYear) 
	 			        							 && !StringTool.isEmpty(month) && StringTool.testInt(month) 
	 			        							 && !StringTool.isEmpty(day) && StringTool.testInt(day) 
	 			        							 && !StringTool.isEmpty(examinationTime) && StringTool.testInt(examinationTime) 
	 			        							 && !StringTool.isEmpty(beginTime) && beginTime.indexOf(":")>0
	 			        							 && !StringTool.isEmpty(endTime) && endTime.indexOf(":")>0){
	 			        						sql=service.saveArrangeHand(syllabusArrangeId,examYear,month,day,examinationTime,beginTime,endTime);
	 			        						alist.add(sql[0]);
			 			        				idlist.add(sql[1]);
			 			        				lielist.add(excellie);
	 			        					}
	 			        					else{
	 			        						bool=false;
	 			        					}
	 			        				}
	 			        				if(bool){
	 			        					if(service.transExeSqls(alist)>0){
	 			        						boolean ifsave,ifupfile;
				 			        			String pro,procode,proname,syllabus,commSysllabus="0";
				 			        			List<String[]> li=new ArrayList<String[]>();
				 			        			l=idlist.size();
				 			        			String sqlId;
				 			        			alist=new ArrayList<String>();
				 			        			String id;
				 			        			for(i=0;i<l;i++){
				 			        				sqlId=idlist.get(i);
				 			        				excellie=lielist.get(i);
				 			        				if(!excellie.equals("")){
				 			        					id=service.getFirstCol(sqlId);
					 			        				if(service.countArrange(id, menutype)>0){
					 			        					alist.add(service.deleteArrange(id, menutype));
					 			        				}
					 			        				String[] ss={id,excellie};//id 是BASE_SYLLABUS_ARRANGE表的ID，主考试时间，excel列是Excel的第几列
					 			        				li.add(ss);
				 			        				}
				 			        			}
				 			        			if(alist.size()>0 && service.transExeSqls(alist)<1){
				 			        				list_error.add("前期上传的信息删除失败！");
				 			        			}
				 			        			else{
				 			        				l=li.size();
				 			        				int stsize=st.getRows();
					 			        			if(stsize<endline){
					 			        				endline=stsize;
					 			        			}
							 			   			for(i=(beginline-1);i<endline;i++){
							 			   				commSysllabus="0";
							 			   				pro=st.getCell(i_procode,i).getContents().trim().toUpperCase();
							 			   				if(pro.equals("")){
							 			   					list_error.add("Excel的第"+(i+1)+"行未上传，“专业”为空");
							 			   				}
							 			   				else{
							 			   					if(pro.equals("公共课")){
							 			   						procode="1";
							 			   						proname="公共课";
							 			   						commSysllabus="1";
							 			   					}
							 			   					else{
							 			   						String[] pp=service.getPro(pro);
							 			   						procode=pp[0];
							 			   						proname=pp[1];
							 			   						if(procode.equals("x") && proname.equals("x")){
							 			   							procode="2";
							 			   							proname=pro;
							 			   							commSysllabus="1";
							 			   						}
							 			   					}
							 			   					bool=true;
							 			   					ifupfile=true;
							 			   					for(j=0;j<l;j++){
							 			   						String[] ssii=li.get(j);
							 			   						syllabus=st.getCell(Integer.parseInt(ssii[1]),i).getContents().trim().toUpperCase();
							 			   						if(!syllabus.equals("")){
							 			   							bool=false;
							 			   							ifsave=service.saveSyllabus(proname, procode, syllabus, ssii[0], menutype,commSysllabus);
							 			   							if(!ifsave){
							 			   								ifupfile=false;
							 			   								list_error.add("Excel的第"+(i+1)+"行第"+lie[Integer.parseInt(ssii[1])]+"列上传失败");
							 			   							}
							 			   						}
							 			   					}
							 			   					if(bool){
							 			   						list_error.add("Excel的第"+(i+1)+"行未上传，没有课程内容");
							 			   					}
							 			   					else{
							 			   						if(ifupfile){
							 			   							upfilenum++;
							 			   						}
							 			   					}
							 			   				}	
							 			   			}
				 			        			}
	 			        					}
	 			        					else{
			 			        				list_error.add("保存考试时间失败！");
			 			        			}
	 			        				}
	 			        				else{
	 			        					list_error.add("“所在列”左边的时间信息未填写完整");
	 			        				}
	 			        			}
	 			        		}
	 			        	}
	 			        }
			        }
			    }
			}
			int recordnum;
			if((endline-beginline)<0){
				recordnum=0;
			}
			else if(endline==0 || beginline==0){
				recordnum=0;
			}
			else{
				recordnum=endline-beginline+1;
			}
			String str="<div style='font-size:14px;width:100%;font-weight:bold;' align='left'>" +
					"上传情况如下：<br/><br/>" +
					"1、共" +recordnum+"行数据<br/>" +
					"2、成功上传："+upfilenum+"条<br/>" +
					"</div><br/>";
			if(list_error.size()>0){
				str+="<br/><font style='font-size:14px;font-weight:bold;color:green;'>以下信息没有导入：</font><br/><br/>"+StringTool.getListStr(list_error,"<br/>");
			}
			else{
				str+="全部内容导入成功。";
			}
			errortext=str;
		}
		catch (Exception e){
			System.out.println("excel_upfile："+e.toString());
		}
		return "excelMess";
	}

	public String autoup(){//Excel上传
		try{
			if(StringTool.isEmpty(menutype)){
				this.PostJs(String.format(
						"alert('上传失败！');location.href='%1$s/manager/plan/examarrange/exam_show.do?menutype=1';",
						request.getContextPath()));
				return null;
			}
			if(!menutype.equals("1") && !menutype.equals("2")){
				this.PostJs(String.format(
						"alert('上传失败！');location.href='%1$s/manager/plan/examarrange/exam_show.do?menutype=1';",
						request.getContextPath()));
				return null;
			}
			List<String> list_error=new ArrayList<String>();
			int upfilenum=0,beginline=0,stsize=0,i_procode=0;
			if(excelFileFileName==null || excelFileFileName.equals("")){//---
				list_error.add("没有上传文件");
			}
			else{
				/*接收参数*/
				String sheet=request.getParameter("sheet");//---
				String name=excelFileFileName.substring(excelFileFileName.lastIndexOf("\\") + 1);
			    if(name==null || name.trim().equals("")){
			    	list_error.add("没有要上传的文件");
			    }
			    else{
			    	name=name.trim();
			    	if(name.lastIndexOf(".")==-1){
			    		list_error.add("上传文件没有后缀名");
			    	}
			        else{
			        	String contentType=name.substring(name.lastIndexOf(".")+1);
			        	contentType=contentType.trim();
	 			    	if(contentType==null ||contentType.equals("")){
	 			    		list_error.add("上传文件没有后缀名");
	 			    	}
	 			        else{
	 			        	contentType=contentType.toLowerCase();
	 			        	if(!contentType.equals("xls")){
	 			        		list_error.add("请上传后缀名为“xls”的文件");
	 			        	}
	 			        	else{
	 			        		InputStream is = new FileInputStream(excelFile);
	 			        		Workbook wb = Workbook.getWorkbook(is);
	 			        		Sheet st = wb.getSheet(sheet);
	 			        		if(st==null){
	 			        			list_error.add("没有找到你输入的“导入的Excel工作表名”");
	 			        		}
	 			        		else{
	 			        			int i,j,l;
	 			        			stsize=st.getRows();
		 			        		String year="0",month1="0",month2="0",
		 			        			day1="0",day2="0",day3="0",day4="0";
		 			        		String str=st.getCell(0,0).getContents().trim();
		 			        		if(str.length()>3 && StringTool.testInt(str.substring(0,4))){
		 			        			year=str.substring(0,4);
		 			        		}
		 			        		str=st.getCell(1,2).getContents().trim();
		 			        		str=str.replace("月","");
		 			        		if(StringTool.testInt(str)){
		 			        			month1=str;
		 			        		}
		 			        		str=st.getCell(5,2).getContents().trim();
		 			        		str=str.replace("月","");
		 			        		if(StringTool.testInt(str)){
		 			        			month2=str;
		 			        		}
		 			        		str=st.getCell(1,3).getContents().trim();
		 			        		day1=service.getDay(str);
		 			        		str=st.getCell(3,3).getContents().trim();
		 			        		day2=service.getDay(str);
		 			        		str=st.getCell(5,3).getContents().trim();
		 			        		day3=service.getDay(str);
		 			        		str=st.getCell(7,3).getContents().trim();
		 			        		day4=service.getDay(str);
		 			        		ArrayList<String> alist=new ArrayList<String>();
		 			        		List<String> idlist=new ArrayList<String>();
		 			        		boolean bool=false;
		 			        		String[] sql;
		 			        		String month="0",day="0";
		 			        		for(i=1;i<9;i++){
		 			        			if(i<5) month=month1;
		 			        			else month=month2;
		 			        			if(i==1 || i==2){
		 			        				day=day1;
		 			        			}
		 			        			else if(i==3 || i==4){
		 			        				day=day2;
		 			        			}
		 			        			else if(i==5 || i==6){
		 			        				day=day3;
		 			        			}
		 			        			else if(i==7 || i==8){
		 			        				day=day4;
		 			        			}
		 			        			str=st.getCell(i,4).getContents().trim();
		 			        			sql=service.saveArrange(str,year,month,day);
		 			        			if(sql[0].equals("")){
		 			        				bool=true;
		 			        			}
		 			        			else{
		 			        				alist.add(sql[0]);
		 			        				idlist.add(sql[1]);
		 			        			}
		 			        		}
		 			        		if(bool){
		 			        			list_error.add("获取考试时间失败！");
		 			        		}
		 			        		else{
		 			        			if(service.transExeSqls(alist)>0){
		 			        				boolean ifsave,ifupfile;
			 			        			String pro,procode,proname,syllabus,commSysllabus="0";
			 			        			List<String[]> li=new ArrayList<String[]>();
			 			        			l=idlist.size();
			 			        			String sqlId,id;
			 			        			alist=new ArrayList<String>();
			 			        			for(i=0;i<l;i++){
			 			        				sqlId=idlist.get(i);
			 			        				id=service.getFirstCol(sqlId);
			 			        				if(service.countArrange(id, menutype)>0){
			 			        					alist.add(service.deleteArrange(id, menutype));
			 			        				}
			 			        				String[] ss={id,(i+1)+""};
			 			        				li.add(ss);
			 			        			}
			 			        			if(alist.size()>0 && service.transExeSqls(alist)<1){
			 			        				list_error.add("前期上传的信息删除失败！");
			 			        			}
			 			        			else{
			 			        				l=li.size();//多此一举
			 			        				beginline=6;
						 			   			for(i=(beginline-1);i<stsize;i++){
						 			   				commSysllabus="0";
						 			   				pro=st.getCell(i_procode,i).getContents().trim().toUpperCase();
						 			   				if(pro.equals("")){
						 			   					list_error.add("Excel的第"+(i+1)+"行未上传，“专业”为空");
						 			   				}
						 			   				else{
						 			   					if(pro.equals("公共课")){
						 			   						procode="1";
						 			   						proname="公共课";
						 			   						commSysllabus="1";
						 			   					}
						 			   					else{
						 			   						String[] pp=service.getPro(pro);
						 			   						procode=pp[0];
						 			   						proname=pp[1];
						 			   						if(procode.equals("x") && proname.equals("x")){
						 			   							procode="2";
						 			   							proname=pro;
						 			   							commSysllabus="1";
						 			   						}
						 			   					}
						 			   					bool=true;
						 			   					ifupfile=true;
						 			   					for(j=0;j<l;j++){
						 			   						String[] ssii=li.get(j);
						 			   						syllabus=st.getCell(Integer.parseInt(ssii[1]),i).getContents().trim().toUpperCase();
						 			   						if(!syllabus.equals("")){
						 			   							bool=false;
						 			   							ifsave=service.saveSyllabus(proname, procode, syllabus, ssii[0], menutype,commSysllabus);
						 			   							if(!ifsave){
						 			   								ifupfile=false;
						 			   								list_error.add("Excel的第"+(i+1)+"行第"+lie[Integer.parseInt(ssii[1])]+"列上传失败");
						 			   							}
						 			   						}
						 			   					}
						 			   					if(bool){
						 			   						list_error.add("Excel的第"+(i+1)+"行未上传，没有课程内容");
						 			   					}
						 			   					else{
						 			   						if(ifupfile){
						 			   							upfilenum++;
						 			   						}
						 			   					}
						 			   				}	
						 			   			}
			 			        			}
		 			        			}
		 			        			else{
		 			        				list_error.add("保存考试时间失败！");
		 			        			}
		 			        		}	
	 			        		}
	 			        	}
	 			        }
			        }
			    }
			}
			int recordnum;
			if((stsize-beginline)<0){
				recordnum=0;
			}
			else if(stsize==0 || beginline==0){
				recordnum=0;
			}
			else{
				recordnum=stsize-beginline+1;
			}
			String str="<div style='font-size:14px;width:100%;font-weight:bold;' align='left'>" +
					"上传情况如下：<br/><br/>" +
					"1、共" +recordnum+"行数据<br/>" +
					"2、成功上传："+upfilenum+"条<br/>" +
					"</div><br/>";
			if(list_error.size()>0){
				str+="<br/><font style='font-size:14px;font-weight:bold;color:green;'>以下信息没有导入：</font><br/><br/>"+StringTool.getListStr(list_error,"<br/>");
			}
			else{
				str+="全部内容导入成功。";
			}
			errortext=str;
		}
		catch (Exception e){
			System.out.println("excel_upfile："+e.toString());
		}
		return "autoExcelMess";
	}
	
	public String add(){
		return "newchange";
	}
	
	public String mod(){
		baseSyllabusTime=service.getBaseSyllabusTime(baseSyllabusTime);
		return "modchange";
	}
	
	public void save(){
		baseSyllabusTime.setProName(service.getProName(baseSyllabusTime.getProCode()));
		baseSyllabusTime.setSyllabusName(service.getSyllabusName(baseSyllabusTime.getSyllabusCode()));
		int i=service.count(baseSyllabusTime);
		if(i>0){
			this.PostJs(String.format(
					"alert('增加失败：该信息系统中已存在！');location.href='%1$s/manager/plan/examarrange/change.jsp?examTimeCode="+baseSyllabusTime.getExaminationTimeCode()+"&proCode="+baseSyllabusTime.getProCode()+"&proName="+baseSyllabusTime.getProName()+"';",
					request.getContextPath()));
			return;
		}
		if(!service.checkSyllabusType(baseSyllabusTime.getProCode(),baseSyllabusTime.getSyllabusCode())){
			this.PostJs(String.format(
			"alert('增加失败：论文及实践课程不能导入！');location.href='%1$s/manager/plan/examarrange/change.jsp?examTimeCode="+baseSyllabusTime.getExaminationTimeCode()+"&proCode="+baseSyllabusTime.getProCode()+"&proName="+baseSyllabusTime.getProName()+"';",
			request.getContextPath()));
			return;
		}
		if(!service.checkSyllabuscode(baseSyllabusTime.getSyllabusCode(),baseSyllabusTime.getExaminationTimeCode())){
			this.PostJs(String.format(
			"alert('增加失败：同一课程不同时间段只能出现一次！');location.href='%1$s/manager/plan/examarrange/change.jsp?examTimeCode="+baseSyllabusTime.getExaminationTimeCode()+"&proCode="+baseSyllabusTime.getProCode()+"&proName="+baseSyllabusTime.getProName()+"';",
			request.getContextPath()));
			return;
		}
		boolean bool=service.save(baseSyllabusTime);
		if(bool){
			this.PostJs(String.format(
					"alert('增加成功！');location.href='%1$s/manager/plan/examarrange/change.jsp?examTimeCode="+baseSyllabusTime.getExaminationTimeCode()+"&proCode="+baseSyllabusTime.getProCode()+"&proName="+baseSyllabusTime.getProName()+"';",
					request.getContextPath()));
			return;
		}
		else{
			this.PostJs(String.format(
					"alert('增加失败！');location.href='%1$s/manager/plan/examarrange/change.jsp?examTimeCode="+baseSyllabusTime.getExaminationTimeCode()+"&proCode="+baseSyllabusTime.getProCode()+"&proName="+baseSyllabusTime.getProName()+"';",
					request.getContextPath()));
			return;
		}
	}
	
	public void delete(){
		baseSyllabusTime.setProName(service.getProName(baseSyllabusTime.getProCode()));
		boolean bool=service.delete(baseSyllabusTime);
		if(bool){
			this.PostJs(String.format(
					"alert('删除成功！');location.href='%1$s/manager/plan/examarrange/change.jsp?examTimeCode="+baseSyllabusTime.getExaminationTimeCode()+"&proCode="+baseSyllabusTime.getProCode()+"&proName="+baseSyllabusTime.getProName()+"';",
					request.getContextPath()));
			return;
		}
		else{
			this.PostJs(String.format(
					"alert('删除失败！');location.href='%1$s/manager/plan/examarrange/change.jsp?examTimeCode="+baseSyllabusTime.getExaminationTimeCode()+"&proCode="+baseSyllabusTime.getProCode()+"&proName="+baseSyllabusTime.getProName()+"';",
					request.getContextPath()));
			return;
		}
	}
	
	
	public void update(){
		baseSyllabusTime.setProName(service.getProName(baseSyllabusTime.getProCode()));
		baseSyllabusTime.setSyllabusName(service.getSyllabusName(baseSyllabusTime.getSyllabusCode()));
		String menutype=request.getParameter("menutype");
		boolean bool=false;
		if(!menutype.equals(baseSyllabusTime.getCountryProvince())){
			int i=service.count(baseSyllabusTime);
			if(i>0){
				this.PostJs(String.format(
						"alert('更新失败:这条记录已经存在！');location.href='%1$s/manager/plan/examarrange/change.jsp?examTimeCode="+baseSyllabusTime.getExaminationTimeCode()+"&proCode="+baseSyllabusTime.getProCode()+"&proName="+baseSyllabusTime.getProName()+"';",
						request.getContextPath()));
				return;
			}
			else{
				bool=service.save(baseSyllabusTime);
				baseSyllabusTime.setCountryProvince(menutype);
				i=service.count(baseSyllabusTime);
				if(i>0){
					bool=service.delete(baseSyllabusTime);
				}
			}
		}
		if(bool){
			this.PostJs(String.format(
					"alert('更新成功！');location.href='%1$s/manager/plan/examarrange/change.jsp?examTimeCode="+baseSyllabusTime.getExaminationTimeCode()+"&proCode="+baseSyllabusTime.getProCode()+"&proName="+baseSyllabusTime.getProName()+"';",
					request.getContextPath()));
			return;
		}
		else{
			this.PostJs(String.format(
					"alert('更新失败！');location.href='%1$s/manager/plan/examarrange/change.jsp?examTimeCode="+baseSyllabusTime.getExaminationTimeCode()+"&proCode="+baseSyllabusTime.getProCode()+"&proName="+baseSyllabusTime.getProName()+"';",
					request.getContextPath()));
			return;
		}
	}
	
	public String history(){
		return "history";
	}
	
	public String hisSave(){
		boolean booltime=false,bool=false;
		String examYear1=request.getParameter("examYear1");
		String examYear2=request.getParameter("examYear2");
		List<String> lMonth1=service.distinctMonthAll(examYear1);
		List<String> lMonth2=service.distinctMonthAll(examYear2);
		if(lMonth1.size()==2 && lMonth2.size()==2 && lMonth1.get(0).equals(lMonth2.get(0)) && lMonth1.get(1).equals(lMonth2.get(1))){
			List<String> lSyllabusArrangeIdA=service.lSyllabusArrangeId(examYear1, lMonth1.get(0));
			List<String> lSyllabusArrangeId1=service.lSyllabusArrangeId(examYear1, lMonth1.get(1));
			if(lSyllabusArrangeIdA.size()==4 || lSyllabusArrangeId1.size()==4){
				lSyllabusArrangeIdA.addAll(lSyllabusArrangeId1);
			}
			List<String> lSyllabusArrangeIdB=service.lSyllabusArrangeId(examYear2, lMonth2.get(0));
			List<String> lSyllabusArrangeId2=service.lSyllabusArrangeId(examYear2, lMonth2.get(1));
			if(lSyllabusArrangeIdB.size()==4 || lSyllabusArrangeId2.size()==4){
				lSyllabusArrangeIdB.addAll(lSyllabusArrangeId2);
			}
			if(lSyllabusArrangeIdA.size()==8 && lSyllabusArrangeIdB.size()==8){
				booltime=true;
				bool=service.CopyBaseSyllabusTime(lSyllabusArrangeIdA, lSyllabusArrangeIdB);
			}
		}
		if(bool){
			this.PostJs(String.format(
					"alert('复制成功！');location.href='%1$s/manager/plan/examarrange/exam_show.do?baseSyllabusTime.applyYear="+examYear2+"&menutype=2';",
					request.getContextPath()));
			return null;
		}
		else{
			if(booltime){
				this.PostJs(String.format(
						"alert('复制失败！');location.href='%1$s/manager/plan/examarrange/exam_show.do?baseSyllabusTime.applyYear="+examYear1+"&menutype=2';",
						request.getContextPath()));
				return null;
			}
			else{
				this.PostJs(String.format(
						"alert('系统时间有误，无法复制！\\n（请确认是否导入了"+examYear2+"年的全国统考课程考试安排）');location.href='%1$s/manager/plan/examarrange/exam_show.do?baseSyllabusTime.applyYear="+examYear1+"&menutype=2';",
						request.getContextPath()));
				return null;
			}
		}
		
	}
	
	/**
	 * 发布考试安排
	 * @return
	 */
	public String publicArrange() {
	    String params = "d=" + new Date().getTime();
	    params += "&baseSyllabusTime.applyYear=" + StringTool.trim(baseSyllabusTime.getApplyYear());
	    params += "&baseSyllabusTime.examinationDate=" + StringTool.trim(baseSyllabusTime.getExaminationDate());
	    params += "&baseSyllabusTime.examinationTime=" + StringTool.trim(baseSyllabusTime.getExaminationTime());
	    params += "&baseSyllabusTime.proCode=" + StringTool.trim(baseSyllabusTime.getProCode());
	    params += "&baseSyllabusTime.proName=" + StringTool.trim(baseSyllabusTime.getProName());
	    params += "&baseSyllabusTime.syllabusCode=" + StringTool.trim(baseSyllabusTime.getSyllabusCode());
	    params += "&baseSyllabusTime.syllabusName=" + StringTool.trim(baseSyllabusTime.getSyllabusName());
	    params += "&menutype=3";
	    
	    if (StringTool.isEmpty(baseSyllabusTime.getApplyYear())) {
	        this.Alert("alert('缺少参数！');");
            this.PostJs(String.format("location.href='%1$s/manager/plan/examarrange/exam_show.do?%2$s';", request
                    .getContextPath(),params));
            return null;
	    }
	    
	    if (StringTool.isEmpty(menutype)) {
            this.Alert("alert('缺少参数！');");
            this.PostJs(String.format("location.href='%1$s/manager/plan/examarrange/exam_show.do?%2$s';", request
                    .getContextPath(),params));
            return null;
        }
        if (!menutype.equals("1") && !menutype.equals("2") && !menutype.equals("3") && !menutype.equals("4")) {
            this.Alert("alert('参数错误！');");
            this.PostJs(String.format("location.href='%1$s/manager/plan/examarrange/exam_show.do?%2$s';", request
                    .getContextPath(),params));
            return null;
        }
        if (menutype.equals("4")) { // 陕西省全国统考课程考试安排
            baseSyllabusTime.setCountryProvince("1");
            baseSyllabusTime.setCommView("1");
        } else if (menutype.equals("3")) { // 
            baseSyllabusTime.setCountryProvince("3");
            baseSyllabusTime.setCommView("1");// CommView("1")就会遍历公共课
        } else {
            // 2-陕西省非全国统考课程考试安排
            // 1-全国统考课程考试安排
            baseSyllabusTime.setCountryProvince(menutype);
            baseSyllabusTime.setCommView("0");
        }
        
        int i = -1;
        if (baseSyllabusTime.getCommView().equals("1")) {
             i = service.publicArrange(baseSyllabusTime);
        } else {
            // 
        }
        
        if (i > 0) {
            this.Alert("发布成功！");
            this.PostJs(String.format("location.href='%1$s/manager/plan/examarrange/exam_show.do?%2$s';", request
                    .getContextPath(),params));
            return null;
        } else {
            this.Alert("发布失败！");
            this.PostJs(String.format("location.href='%1$s/manager/plan/examarrange/exam_show.do?%2$s';", request
                    .getContextPath(),params));
            return null;
        }
	}
	
	public List<String> getlYear(){
		return this.lYear;
	}

	public void setlYear(List<String> lyear) {
		this.lYear = lyear;
	}

	public BaseSyllabusTime getBaseSyllabusTime() {
		return baseSyllabusTime;
	}

	public void setBaseSyllabusTime(BaseSyllabusTime baseSyllabusTime) {
		this.baseSyllabusTime = baseSyllabusTime;
	}

	public String getMenutype() {
		return menutype;
	}

	public void setMenutype(String menutype) {
		this.menutype = menutype;
	}

	public List<Object[]> getLo() {
		return lo;
	}

	public void setLo(List<Object[]> lo) {
		this.lo = lo;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public String getDifftype() {
		return difftype;
	}

	public void setDifftype(String difftype) {
		this.difftype = difftype;
	}

	public Object[] getShowlist() {
		return showlist;
	}

	public void setShowlist(Object[] showlist) {
		this.showlist = showlist;
	}

	public List<Object[]> getLosec() {
		return losec;
	}

	public void setLosec(List<Object[]> losec) {
		this.losec = losec;
	}

	public int getTableLong() {
		return tableLong;
	}

	public void setTableLong(int tableLong) {
		this.tableLong = tableLong;
	}

	public int getTableCol() {
		return tableCol;
	}

	public void setTableCol(int tableCol) {
		this.tableCol = tableCol;
	}

	public File getExcelFile() {
		return excelFile;
	}

	public void setExcelFile(File excelFile) {
		this.excelFile = excelFile;
	}

	public String getExcelFileFileName() {
		return excelFileFileName;
	}

	public void setExcelFileFileName(String excelFileFileName) {
		this.excelFileFileName = excelFileFileName;
	}

	public String getErrortext() {
		return errortext;
	}

	public void setErrortext(String errortext) {
		this.errortext = errortext;
	}

	public String[] getLie() {
		return lie;
	}

	public void setLie(String[] lie) {
		this.lie = lie;
	}
}