package com.sincinfo.zxks.core.plan.examarrange;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sincinfo.zxks.bean.BaseSyllabusArrange;
import com.sincinfo.zxks.zxksdbs.ExamArrangeService;

public class ExcelAjax extends HttpServlet{
	private static final long serialVersionUID = -6810066914443304146L;
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
			StringBuffer sb=new StringBuffer();
			ExamArrangeService service=new ExamArrangeService();
			int i,j,l,ll,k,lll;
			String month;//EXAMINATION_CODE
			String examYear=request.getParameter("examYear");
			List<String> ssss=new ArrayList<String>();
	        List<String> ss1=service.distinctMonthAll(examYear);
			ll=ss1.size();
			if(ll>0){
				ssss.addAll(ss1);
				boolean bool=true;
				for(j=0;j<ll;j++){
					month=ssss.get(j);
					if(month.equals("4")){
						bool=false;
					}
				}
				if(bool){
					ssss.add("4");
				}
				bool=true;
				for(j=0;j<ll;j++){
					month=ssss.get(j);
					if(month.equals("10")){
						bool=false;
					}
				}
				if(bool){
					ssss.add("10");
				}
			}
			else{
				ssss.add("4");
				ssss.add("10");
			}
			List<BaseSyllabusArrange> lsec;
			List<BaseSyllabusArrange> ls1;
			String[] lie={"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
			BaseSyllabusArrange baseSyllabusArrange;
			ll=ssss.size();
			int lienum=1;
			int shangwu=0,xiawu=0;
			String startTime="00:00",endTime="00:00";
			for(j=0;j<ll;j++){
				lsec=new ArrayList<BaseSyllabusArrange>();
				month=ssss.get(j);
				ls1=service.lBaseSyllabusArrangeAll(examYear,month);
				if(ls1.size()>0){
					lsec.addAll(ls1);
				}
				l=lsec.size();
				if(l<4){
					l=4-l;
					for(i=0;i<l;i++){
						baseSyllabusArrange=new BaseSyllabusArrange();
						baseSyllabusArrange.setDay("");
						baseSyllabusArrange.setExaminationEndTime("");
						baseSyllabusArrange.setExaminationStartTime("");
						baseSyllabusArrange.setExaminationTime("0");
						baseSyllabusArrange.setMonth(month);
						baseSyllabusArrange.setSyllabusArrangeId("");
						baseSyllabusArrange.setWeek("");
						baseSyllabusArrange.setYear(examYear);
						lsec.add(baseSyllabusArrange);
					}
				}
				l=lsec.size();
				sb.append("<table border='1' cellpadding='0' cellspacing='0' width='100%'>\n"+
				"<tr style='height:25px;'>\n<td align='center' width='5%'");
				if(l>1){
					sb.append(" rowspan='"+l+"'");
				}
				sb.append(">"+month+"月</td>\n");
				for(i=0;i<l;i++){
					shangwu=0;xiawu=0;
					startTime="00:00";endTime="00:00";
					baseSyllabusArrange=lsec.get(i);
					if(i>0){
						sb.append("<tr>\n");
					}
					sb.append("<td align='right' width='40%'>\n" +
							"<input type='hidden' value='"+baseSyllabusArrange.getSyllabusArrangeId()+"'" +
							" name='syllabusArrangeId"+lienum+"' id='syllabusArrangeId"+lienum+"'/>\n"+
							"<input type='hidden' id='year"+lienum+"' name='year"+lienum+"' value='"+baseSyllabusArrange.getYear()+"'/>"+
							baseSyllabusArrange.getYear()+"年\n" +
							"<input type='hidden' id='month"+lienum+"' name='month"+lienum+"' value='"+baseSyllabusArrange.getMonth()+"'/>"+
							baseSyllabusArrange.getMonth()+"月\n" +
							"<input type='text' id='day"+lienum+"' name='day"+lienum+"' style='width:20px' value='"+baseSyllabusArrange.getDay()+"'/>日\n");
					sb.append("<select id='examinationTime"+lienum+"' name='examinationTime"+lienum+"'>\n" +
							"<option value=''>请选择…</option>\n" +
							"<option value='1'");
					if(baseSyllabusArrange.getExaminationTime().equals("1")){
						sb.append(" selected='selected'");
						shangwu=1;
					}
					else if(baseSyllabusArrange.getExaminationTime().equals("0")){
						if(i%2==0){
							sb.append(" selected='selected'");
							shangwu=1;
						}
					}
					sb.append(">上午</option>\n" +
					"<option value='2'");
					if(baseSyllabusArrange.getExaminationTime().equals("2")){
						sb.append(" selected='selected'");
						xiawu=1;
					}
					else if(baseSyllabusArrange.getExaminationTime().equals("0")){
						if(i%2==1){
							sb.append(" selected='selected'");
							xiawu=1;
						}
					}
					sb.append(">下午</option>\n</select>\n");
					if(shangwu==1 && xiawu==0){
						if(baseSyllabusArrange.getSyllabusArrangeId().equals("")){
							startTime="09:00";
							endTime="11:30";
						}
						else{
							startTime=baseSyllabusArrange.getExaminationStartTime();
							endTime=baseSyllabusArrange.getExaminationEndTime();
						}
						
					}
					else if(shangwu==0 && xiawu==1){
						if(baseSyllabusArrange.getSyllabusArrangeId().equals("")){
							startTime="02:30";
							endTime="05:00";
						}
						else{
							startTime=baseSyllabusArrange.getExaminationStartTime();
							endTime=baseSyllabusArrange.getExaminationEndTime();
						}
					}
					sb.append("从<input type='text' name='beginTime"+lienum+"' value='"+startTime+"' style='width:40px'/>" +
							"到<input type='text' name='endTime"+lienum+"' value='"+endTime+"' style='width:40px'/>");
					sb.append("—所在列：</td><td width='55%'>\n");
					sb.append("<select name='lie"+lienum+"' id='lie"+lienum+"'>\n"+
						"<option value=''>请选择…</option>\n");
					lll=lie.length;
					for(k=0;k<lll;k++){
						sb.append("<option value='"+k+"'>"+lie[k]+"</option>\n");
					}
					sb.append("</select></td></tr>\n");
					lienum++;
				}
				sb.append("</table>\n");
			}
			sb.append("<input type='hidden' name='lienum' id='lienum' value='"+lienum+"'/>\n");
			sb.append("<font style='color:red;'>* 注：以上信息，“所在列”左边的信息请填写完整，“所在列”右边的信息可以部分不用选择。</font>\n");
			out.write(sb.toString());
			out.close();
		}
		catch (Exception e){
			 System.out.println("ExamAjax:"+e.toString());
		 }
	}
	public void doGet(HttpServletRequest request,
	            HttpServletResponse response)
	throws IOException{
		doPost(request, response);
	}
}