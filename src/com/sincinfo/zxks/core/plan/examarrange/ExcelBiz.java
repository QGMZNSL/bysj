package com.sincinfo.zxks.core.plan.examarrange;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sincinfo.zxks.bean.BaseSyllabusArrange;
import com.sincinfo.zxks.bean.BaseSyllabusTime;
import com.sincinfo.zxks.zxksdbs.ExamArrangeService;

import jxl.SheetSettings;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ExcelBiz extends HttpServlet{
	/**
	 * powered by zhangjb
	 * 2012-8-22
	 **/
	private static final long serialVersionUID = 381916901022501499L;
	PrintWriter out;
	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request,
            HttpServletResponse response)
	throws IOException{
		try{
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=UTF-8");
	        response.setHeader("Cache-Control","no-cache");
			out=response.getWriter();
			ExamArrangeService service =new ExamArrangeService();
			String excelFileName ="bST"+service.newFilename()+".xls";
			String[] paths = service.getPaths();
			String subPath = service.getConfig("65");
			String excelPhyPath = paths[0] + subPath
					+ System.getProperty("file.separator");
			String excelNetPath = paths[1] + subPath + "/" + excelFileName;
			String menutype=request.getParameter("menutype");
			String examYear=request.getParameter("examYear");
			BaseSyllabusTime baseSyllabusTime=new BaseSyllabusTime();
			baseSyllabusTime.setApplyYear(examYear);
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
			// 文件路径判断
			File dir = new File(excelPhyPath);
			if (dir != null && !dir.exists()) {
				dir.mkdirs();
			}
			// 合成真实的文件名
			String file = "";
			if (excelPhyPath.endsWith(System.getProperty("file.separator"))) {
				file = excelPhyPath + excelFileName;
			} else {
				file = excelPhyPath + System.getProperty("file.separator")
						+ excelFileName;
			}
			int i,j,l,ll;
			List<String> ssss=service.distinctMonthAll(examYear);
			ll=ssss.size();
			String month;//EXAMINATION_CODE
			List<Object[]> lo0,lo1;
			List<Object[]> losec;
			losec=new ArrayList<Object[]>();//标题
			List<String> lexamTimeCode=new ArrayList<String>();
			List<String> lsyllabus;
			List<BaseSyllabusArrange> lsec;
			String s_s;
			for(j=0;j<ll;j++){
				month=ssss.get(j);
				lsec=service.lBaseSyllabusArrangeAll(examYear,month);
				l=lsec.size();
				Object[] oo={lsec.size(),month,lsec};//
				losec.add(oo);
				for(i=0;i<l;i++){
					lexamTimeCode.add(lsec.get(i).getSyllabusArrangeId());
				}
			}
			ll=lexamTimeCode.size();
			String sql="";
			List<String[]> lss0=new ArrayList<String[]>();
			List<String[]> lss1=new ArrayList<String[]>();
	        if(baseSyllabusTime.getCommView().equals("1")){
	        	sql=service.shanxiBaseSyllabusTime1(baseSyllabusTime,"1");
	        	List<String[]> lssllss1=service.getRsArrayList(sql, 2);//取唯一的专业号
	        	lss0.addAll(lssllss1);
	        	sql=service.shanxiBaseSyllabusTime1(baseSyllabusTime,"2");
	        	List<String[]> lss=service.getRsArrayList(sql, 2);
	        	lss0.addAll(lss);
	        	sql=service.shanxiBaseSyllabusTime1(baseSyllabusTime,"4");
	        	List<String[]> lss4=service.getRsArrayList(sql, 2);
	        	lss1.addAll(lss4);
	        	sql=service.shanxiBaseSyllabusTime1(baseSyllabusTime,"3");
	        	List<String[]> lss3=service.getRsArrayList(sql, 2);
	        	lss1.addAll(lss3);
	        	sql=service.shanxiBaseSyllabusTime1(baseSyllabusTime,"5");
	        	List<String[]> lss5=service.getRsArrayList(sql, 2);
	        	lss1.addAll(lss5);
	        }
	        else{
	        	sql=service.getStrBaseSyllabusTime1(baseSyllabusTime,"1");
	        	List<String[]> lsslss1=service.getRsArrayList(sql, 4);//取唯一的专业号
	        	lss0.addAll(lsslss1);
	        	sql=service.getStrBaseSyllabusTime1(baseSyllabusTime,"2");
	        	List<String[]> lss=service.getRsArrayList(sql, 4);//取唯一的专业号
	        	lss0.addAll(lss);
	        	sql=service.getStrBaseSyllabusTime1(baseSyllabusTime,"4");
	        	List<String[]> lss4=service.getRsArrayList(sql, 4);
	        	lss1.addAll(lss4);
	        	sql=service.getStrBaseSyllabusTime1(baseSyllabusTime,"3");
	        	List<String[]> lss3=service.getRsArrayList(sql, 4);
	        	lss1.addAll(lss3);
	        	sql=service.getStrBaseSyllabusTime1(baseSyllabusTime,"5");
	        	List<String[]> lss5=service.getRsArrayList(sql, 4);
	        	lss1.addAll(lss5);
	        }
			l=lss0.size();
			lo0=new ArrayList<Object[]>();
			for(i=0;i<l;i++){
				String[] ss=lss0.get(i);
				lsyllabus=new ArrayList<String>();
		        for(j=0;j<ll;j++){
		        	if(menutype.equals("3")){
		        		lsyllabus.add(service.getSyllabus4(lexamTimeCode.get(j),ss[0],baseSyllabusTime.getSyllabusCode(),baseSyllabusTime.getSyllabusName(),baseSyllabusTime.getExamUnitary()," \n "));
		        	}
		        	else if(menutype.equals("4")){
		        		lsyllabus.add(service.getSyllabus2(lexamTimeCode.get(j),ss[0],baseSyllabusTime.getSyllabusCode(),baseSyllabusTime.getSyllabusName()," \n "));
		        	}
		        	else{
		        		lsyllabus.add(service.getSyllabus13(lexamTimeCode.get(j),ss[0],baseSyllabusTime.getCountryProvince(),baseSyllabusTime.getSyllabusCode(),baseSyllabusTime.getSyllabusName()," \n "));
		        	}
		        }
		        if(baseSyllabusTime.getCommView().equals("1")){
		        	Object[] ool={ss[1]+"\n（"+ss[0]+"）",lsyllabus};
		        	lo0.add(ool);
		        }
		        else{
		        	s_s=ss[3];
			        if(s_s.equals("1")){
			        	Object[] ool={ss[1],lsyllabus};
			        	lo0.add(ool);
			        }
			        else{
			        	Object[] ool={ss[1]+"\n（"+ss[0]+"）",lsyllabus};
			        	lo0.add(ool);
			        }
		        }
			}
			l=lss1.size();
			lo1=new ArrayList<Object[]>();
			for(i=0;i<l;i++){
				String[] ss=lss1.get(i);
				lsyllabus=new ArrayList<String>();
		        for(j=0;j<ll;j++){
		        	if(menutype.equals("3")){
		        		lsyllabus.add(service.getSyllabus4(lexamTimeCode.get(j),ss[0],baseSyllabusTime.getSyllabusCode(),baseSyllabusTime.getSyllabusName(),baseSyllabusTime.getExamUnitary()," \n "));
		        	}
		        	else if(menutype.equals("4")){
		        		lsyllabus.add(service.getSyllabus2(lexamTimeCode.get(j),ss[0],baseSyllabusTime.getSyllabusCode(),baseSyllabusTime.getSyllabusName()," \n "));
		        	}
		        	else{
		        		lsyllabus.add(service.getSyllabus13(lexamTimeCode.get(j),ss[0],baseSyllabusTime.getCountryProvince(),baseSyllabusTime.getSyllabusCode(),baseSyllabusTime.getSyllabusName()," \n "));
		        	}
		        }
		        if(baseSyllabusTime.getCommView().equals("1")){
		        	Object[] ool={ss[1]+"\n（"+ss[0]+"）",lsyllabus};
		        	lo1.add(ool);
		        }
		        else{
		        	s_s=ss[3];
			        if(s_s.equals("1")){
			        	Object[] ool={ss[1],lsyllabus};
			        	lo1.add(ool);
			        }
			        else{
			        	Object[] ool={ss[1]+"\n（"+ss[0]+"）",lsyllabus};
			        	lo1.add(ool);
			        }
		        }
			}
			// 生成文件
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
//			String excelName=str+"信息";
//			excelName=response.encodeURL(new String(excelName.getBytes(),"ISO8859_1"));
//			response.setContentType("application/x-msdownload");
//			response.setHeader("Content-Disposition", "attachment; filename="+excelName+".xls");
//			OutputStream os = response.getOutputStream();
			WritableWorkbook book = Workbook.createWorkbook(bos);
			WritableSheet sheet0 = book.createSheet("专科",0);
			WritableSheet sheet1 = book.createSheet("本科",1);
			ExcelUtil excelUtil=new ExcelUtil();
			int sheet_i,rowpin=0;
			SheetSettings sheetSettings;
			Label excelTitle = null;
    		WritableCellFormat haligncenter =excelUtil.getTitleformat();
    		WritableCellFormat alignleft =excelUtil.getBodyformatAlignLeft();
    		WritableCellFormat alignleftCenter =excelUtil.getBodyformatAlignLeftCenter();
    		WritableCellFormat aligncenter =excelUtil.getBodyformatAlignCenter();
    		WritableCellFormat demoleft =excelUtil.getDemoformat();
    		String excelTile="";
			if(menutype.equals("1")){
				excelTile=examYear+"年全国高等教育自学考试4月、10月课程安排表";
			}
			else if(menutype.equals("2")){
				excelTile=examYear+"年陕西省非全国统考自学考试4月、10月课程安排表";
			}
			else if(menutype.equals("3")){
				excelTile=examYear+"年陕西省高等教育自学考试4月、10月课程安排表";
			}
			else if(menutype.equals("4")){
				excelTile=examYear+"年陕西省全国统考自学考试4月、10月课程安排表";
			}
			WritableSheet sheet;
			List<Object[]> lo;
			int ki=1,ke=0,z=0,zl=0;
			for(sheet_i=0;sheet_i<2;sheet_i++){
				rowpin=0;
				if(sheet_i==0){
					sheet=sheet0;
					sheetSettings=excelUtil.getSheetSettings(sheet);
				}
				else{
					sheet=sheet1;
					sheetSettings=excelUtil.getSheetSettings(sheet);
				}
				sheetSettings.setScaleFactor(80);
				sheet.setRowView(rowpin,600);
	    		excelTitle=new Label(0,rowpin,excelTile,haligncenter);
	    		sheet.addCell(excelTitle);
				sheet.mergeCells(0, 0, ll, 0);
				rowpin++;
				sheet.setRowView(rowpin,400);
				if(sheet_i==0){
					excelTitle=new Label(0,rowpin,"一、专科/基础科段",demoleft);
				}
				else{
					excelTitle=new Label(0,rowpin,"二、本科/独立本科段/本科段",demoleft);
				}
	    		
				sheet.addCell(excelTitle);
				rowpin++;
				sheet.setRowView(rowpin,400);
				excelTitle=new Label(0,rowpin,"",alignleft);
				sheet.addCell(excelTitle);
				sheet.mergeCells(0, rowpin, 0, (rowpin+1));
				sheet.setColumnView(0,15);
				l=losec.size();
				ki=1;
				for(i=0;i<l;i++){
					Object[] oo=losec.get(i);
					ke=ki+(Integer)oo[0]-1;
					excelTitle=new Label(ki,rowpin,oo[1]+"月",aligncenter);
					sheet.addCell(excelTitle);
					sheet.mergeCells(ki,rowpin,ke,rowpin);
					ki=ke+1;
				}
				rowpin++;
				ki=1;
				String str;
				for(i=0;i<l;i++){
					Object[] oo=losec.get(i);
					lsec=(List<BaseSyllabusArrange>)oo[2];
					zl=lsec.size();
					str="";
					for(z=0;z<zl;z++){
						BaseSyllabusArrange baseSyllabusArrange=lsec.get(z);
						if(baseSyllabusArrange.getExaminationTime().equals("1")){
							str=baseSyllabusArrange.getYear()+"-"+baseSyllabusArrange.getMonth()+"-"+baseSyllabusArrange.getDay()+"上午";
						}
						else if(baseSyllabusArrange.getExaminationTime().equals("2")){
							str=baseSyllabusArrange.getYear()+"-"+baseSyllabusArrange.getMonth()+"-"+baseSyllabusArrange.getDay()+"下午";
						}
						excelTitle=new Label(ki,rowpin,str,alignleftCenter);
						sheet.addCell(excelTitle);
						sheet.setColumnView(ki,15);
						ki++;
					}
				}
				rowpin++;
				
				if(sheet_i==0){
					lo=lo0;
				}
				else{
					lo=lo1;
				}
				l=lo.size();
				for(i=0;i<l;i++){
					Object[] ool=lo.get(i);
					lsyllabus=(List<String>)ool[1];
					sheet.setRowView(rowpin,1600);
					excelTitle=new Label(0,rowpin,ool[0]+"",alignleftCenter);
					sheet.addCell(excelTitle);
					ll=lsyllabus.size();
					for(j=0;j<ll;j++){
						str=lsyllabus.get(j);
						excelTitle=new Label((j+1),rowpin,str,alignleft);
						sheet.addCell(excelTitle);
					}
					rowpin++;
				}
			}
    		book.write();
			book.close();
			bos.close();
			out.write("<a href='"+excelNetPath+"' target='_blank'>下载</a>");
			out.close();
		}
		catch (Exception e){
			 System.out.println("ExcelBiz:"+e.toString());
		 }
	}
	public void doGet(HttpServletRequest request,
                HttpServletResponse response)
	throws IOException{
		doPost(request, response);
	}
}