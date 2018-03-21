/**
 * Copyright(c) 2012 西安云海信息技术有限责任公司 All Rights Reserved<br>
 * @Title: com.sincinfo.zxks.examrelated.examaffair.busi.ExamTicketsTool.java<br>
 * @Description: 专业信息汇编pdf生成工具类 <br>
 * <br>
 * @author  yuansh<br>
 * @date    2012-02-24
 * @version V1.0   
 */
package com.sincinfo.zxks.core.plan;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import com.lowagie.text.pdf.PdfWriter;
import com.sincinfo.zxks.bean.BasePro;
import com.sincinfo.zxks.bean.BaseStudentInfo;
import com.sincinfo.zxks.bean.GraduateCondition;
import com.sincinfo.zxks.bean.PlanInfos;
import com.sincinfo.zxks.common.db.DbUtil;
import com.sincinfo.zxks.common.util.Log;

/**
 * @ClassName: PlanInfosTool
 * @Description: 专业信息汇编pdf生成工具类 <br>
 * @author yuansh
 * @date   2012-02-24
 */
public class PlanInfosTool {
	private DbUtil dbUtil = null;
	private Log log;
	private String[] paths;
	private String pdfSubPath;
	private BaseFont kai;
	private BaseFont hei;
	private String phyPdf;
	private String waterMarkImg;
	private String[] pdfPathsWithWaterMark;
	private String[] pdfPathsPlanInfos;

	/**
	 * 构造函数
	 */
	public PlanInfosTool() {
		this.log = new Log();
		this.dbUtil = new DbUtil();
		this.paths = this.dbUtil.getPaths();
		this.pdfSubPath = this.dbUtil.getConfig("38");
		this.waterMarkImg = paths[0] + this.dbUtil.getConfig("29");
		String keiFile = this.paths[0] + this.dbUtil.getConfig("27");
		String heiFile = this.paths[0] + this.dbUtil.getConfig("28");

		try {
			this.kai = BaseFont.createFont(keiFile, BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
			this.hei = BaseFont.createFont(heiFile, BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
		} catch (Exception e) {
			log.error(this.getClass(), String.format(
							"初始化失败！（字体文件无法找到！%1$s , %2$s）", keiFile,heiFile), e);
		}
		File dir = new File(this.paths[0] + this.pdfSubPath);
		if (!dir.exists()) {
			dir.mkdirs();
		}
	}

	/**
	 * 返回一个PdfPCell对象
	 * @param msg    String 文本内容
	 * @param font   Font 字体
	 * @param halign int 水平对齐
	 * @param valign int 竖直对齐
	 * @param fixHeight float 固定高度
	 * @param border    int 边框
	 * @return PdfPCell
	 */
	public PdfPCell getPCell(String msg, Font font, int halign, int valign,float fixHeight, int border) {
		PdfPCell cell = new PdfPCell();
		cell = new PdfPCell(new Phrase(msg, font));
		cell.setHorizontalAlignment(halign); // 设置水平对齐
		cell.setVerticalAlignment(valign);   // 设置垂直对齐
		cell.setFixedHeight(fixHeight);
		cell.setBorder(border);
		return cell;
	}
	
	/**
	 * 生成考生pdf准考证
	 * @param studList  考生信息列表
	 * @return String[] [0]-物理地址 [1]-网络地址
	 */
	public String[] makeUpPlanInfos(List<PlanInfos> planInfosList,BasePro basePro,GraduateCondition graduateCondition,String ckType) {
		// 没有符合条件的考生，则返回空
		if ( planInfosList == null || planInfosList.size() == 0) {
			return null;
		}		
		// 当前时间作为文件名
		String fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		// 无水印文件地址
		phyPdf = paths[0] + pdfSubPath + System.getProperty("file.separator")+ fileName + ".pdf";//"_pi.pdf"
		pdfPathsPlanInfos = new String[2];
		// 物理地址
		pdfPathsPlanInfos[0] = paths[0] + pdfSubPath + System.getProperty("file.separator") + fileName + ".pdf";
		// 网络地址
		//pdfPathsPlanInfos[1] = paths[1] + pdfSubPath + "/" + fileName + ".pdf";
		pdfPathsPlanInfos[1] = paths[1] + pdfSubPath + "/" + fileName + ".pdf";

		// 开始生成文件
		if("0".equals(ckType))
		  createPlanInfosPdfAll(phyPdf,planInfosList,basePro,graduateCondition);
		else
		  createPlanInfosPdf(phyPdf,planInfosList,basePro,graduateCondition);
//		boolean waterMarkFlag = makeWaterMark(phyPdf, pdfPathsWithWaterMark[0], waterMarkImg);
//		if (!waterMarkFlag)
//			pdfPathsWithWaterMark = null;

		return pdfPathsPlanInfos;
	}	
	
	/**
	 * 创建专业信息汇编pdf文件
	 * @param pdfPhyPath  pdf文件物理地址
	 * @param studList    要生成准考证的考生列表
	 * @param underImgUrl 水印图片
	 * @return String     物理地址
	 */
	private void createPlanInfosPdf(String pdfPhyPath,
			List<PlanInfos> planInfosList,BasePro basePro,GraduateCondition graduateCondition) {

		// 打开文档，以A4
		Rectangle rectPageSize = new Rectangle(PageSize.A4);// 定义A4页面大小
		rectPageSize = rectPageSize.rotate();// 横向
		Document document = new Document(rectPageSize, 20, 20, 20, 20);

		PdfWriter writer = null;
		FileOutputStream pdfOut = null;
		try {
			// 打开流，关联到document并获取输出对象
			pdfOut = new FileOutputStream(pdfPhyPath);
			writer = PdfWriter.getInstance(document, pdfOut);

			// 创建字体
			Font fontKai = new Font(kai, 20);
			Font fontHeiTitle = new Font(hei, 12);
			Font fontHeiContent = new Font(hei, 12);

			// 打开文档连接
			document.open();

			// 写入主题内容
			float[] oneInfoCols = { 5f,15f,35f,10f,10f,5f,8f,8f,30f,20f,20f,15f };
			float height  = 26f;
			float height1 = 40f;
			PdfPTable fullPage = null;
			PdfPTable oneTitle = null;
			PdfPTable onePro = null;
			PdfPTable oneInfo = null;
			PdfPCell  pCell = null;
			PlanInfos pis = null;
			PdfPTable oneNotes = null;

			// 创建一个准考证
			oneTitle = new PdfPTable(1);
			onePro = new PdfPTable(1);
			oneInfo = new PdfPTable(oneInfoCols);
			
			// 准考证头部
			pCell = getPCell(basePro.getProName()+"("+basePro.getProTypeName()+")"+"课程设置与指定教材", fontKai, Element.ALIGN_CENTER,Element.ALIGN_MIDDLE, height, 0);
			oneTitle.addCell(pCell);

			// 添加下部信息
			pCell = getPCell("专业代码:"+basePro.getProCode()+"   "+"主考学校:"+basePro.getAcademyName(), fontHeiTitle, Element.ALIGN_LEFT,Element.ALIGN_MIDDLE, height, 0);
			onePro.addCell(pCell);	
//			pCell = getPCell("主考学校:"+basePro.getAcademyName(), fontHeiTitle, Element.ALIGN_RIGHT,Element.ALIGN_MIDDLE, height, 0);
//			onePro.addCell(pCell);
			
			
			pCell = getPCell("序号", fontHeiContent, Element.ALIGN_CENTER,Element.ALIGN_MIDDLE,height1, 15);
			oneInfo.addCell(pCell);			
			pCell = getPCell("课程\n代码", fontHeiContent, Element.ALIGN_CENTER,Element.ALIGN_MIDDLE,height1, 15);
			oneInfo.addCell(pCell);
			pCell = getPCell("课程名称", fontHeiContent, Element.ALIGN_CENTER,Element.ALIGN_MIDDLE,height1, 15);
			oneInfo.addCell(pCell);
			pCell = getPCell("是否必考", fontHeiContent, Element.ALIGN_CENTER,Element.ALIGN_MIDDLE,height1, 15);
			oneInfo.addCell(pCell);		
			pCell = getPCell("课程选修\n说明", fontHeiContent, Element.ALIGN_CENTER,Element.ALIGN_MIDDLE,height1, 15);
			oneInfo.addCell(pCell);			
			pCell = getPCell("学分", fontHeiContent, Element.ALIGN_CENTER,Element.ALIGN_MIDDLE,height1, 15);
			oneInfo.addCell(pCell);
			pCell = getPCell("是否\n全国\n统考", fontHeiContent, Element.ALIGN_CENTER,Element.ALIGN_MIDDLE,height1, 15);
			oneInfo.addCell(pCell);
			pCell = getPCell("是否\n全国\n统编\n教材", fontHeiContent, Element.ALIGN_CENTER,Element.ALIGN_MIDDLE,height1, 15);
			oneInfo.addCell(pCell);	
			pCell = getPCell("教材名称", fontHeiContent, Element.ALIGN_CENTER,Element.ALIGN_MIDDLE,height1, 15);
			oneInfo.addCell(pCell);	
			pCell = getPCell("教材主编", fontHeiContent, Element.ALIGN_CENTER,Element.ALIGN_MIDDLE,height1, 15);
			oneInfo.addCell(pCell);		
			pCell = getPCell("出版社", fontHeiContent, Element.ALIGN_CENTER,Element.ALIGN_MIDDLE,height1, 15);
			oneInfo.addCell(pCell);	
			pCell = getPCell("出版时间", fontHeiContent, Element.ALIGN_CENTER,Element.ALIGN_MIDDLE,height1, 15);
			oneInfo.addCell(pCell);
			
			oneNotes = new PdfPTable(2);
			pCell = getPCell("说明", fontHeiContent, Element.ALIGN_CENTER,Element.ALIGN_MIDDLE,height1, 15);
			oneNotes.addCell(pCell);	
			pCell = getPCell(graduateCondition.getGraduateConditionDescribe(), fontHeiContent, Element.ALIGN_CENTER,Element.ALIGN_MIDDLE,height1, 15);
			oneNotes.addCell(pCell);			
			
			//回车换行			
			for (int i = 0, fitForNull = 0; i < planInfosList.size(); i++, fitForNull++) {
				pis = planInfosList.get(i);

				// 每20行一页
				if (i % 20 == 0) {
					document.newPage();
				}
				if (i % 20 == 0)
					fullPage = new PdfPTable(1);
				// 序号
				pCell = getPCell(i+1+"", fontHeiContent,Element.ALIGN_CENTER, Element.ALIGN_MIDDLE, height, 15);
				oneInfo.addCell(pCell);				
				// 课程代码
				pCell = getPCell(pis.getSyllabusCode(), fontHeiContent,Element.ALIGN_CENTER, Element.ALIGN_MIDDLE, height, 15);
				oneInfo.addCell(pCell);
				// 课程名称
				pCell = getPCell(pis.getSyllabusName(), fontHeiContent,	Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, height, 15);
				oneInfo.addCell(pCell);
				// 课程是否必考
				pCell = getPCell("--", fontHeiContent,	Element.ALIGN_CENTER, Element.ALIGN_MIDDLE, height, 15);
				oneInfo.addCell(pCell);
				// 课程选修说明
				pCell = getPCell("--", fontHeiContent,	Element.ALIGN_CENTER, Element.ALIGN_MIDDLE, height, 15);
				oneInfo.addCell(pCell);				
				// 学分
				pCell = getPCell(pis.getSyllabusCredit(), fontHeiContent,Element.ALIGN_CENTER, Element.ALIGN_MIDDLE, height, 15);
				oneInfo.addCell(pCell);
				//是否全国统考
				String tk=pis.getExamUnitary();
				if("1".equals(tk)) tk="√";
				pCell = getPCell(tk, fontHeiContent,Element.ALIGN_CENTER, Element.ALIGN_MIDDLE, height, 15);
				oneInfo.addCell(pCell);	
				//是否全国统编教材
				String tb=pis.getExamUnitary();
				if("1".equals(tb)) tb="√";
				pCell = getPCell(tb, fontHeiContent,Element.ALIGN_CENTER, Element.ALIGN_MIDDLE, height, 15);
				oneInfo.addCell(pCell);		
				//教材名称
				pCell = getPCell(pis.getTextbookName(), fontHeiContent,Element.ALIGN_CENTER, Element.ALIGN_MIDDLE, height, 15);
				oneInfo.addCell(pCell);	
				//教材主编
				pCell = getPCell(pis.getTextbookEditor(), fontHeiContent,Element.ALIGN_CENTER, Element.ALIGN_MIDDLE, height, 15);
				oneInfo.addCell(pCell);	
				//出版社
				pCell = getPCell(pis.getTextbookPublisher(), fontHeiContent,Element.ALIGN_CENTER, Element.ALIGN_MIDDLE, height, 15);
				oneInfo.addCell(pCell);		
				//出版时间
				pCell = getPCell(pis.getPublishTime(), fontHeiContent,Element.ALIGN_CENTER, Element.ALIGN_MIDDLE, height, 15);
				oneInfo.addCell(pCell);					

				// 添加到页面表格
				if (i % 20 == 19 || i == planInfosList.size() - 1) {
					// 添加到下部表格			
					pCell = new PdfPCell(oneNotes);
					pCell.setBorder(1);
					pCell.setHorizontalAlignment(Element.ALIGN_CENTER); // 设置水平对齐
					pCell.setVerticalAlignment(Element.ALIGN_MIDDLE);   // 设置垂直对齐
					oneInfo.addCell(pCell);
					
					pCell = new PdfPCell(oneInfo);
					pCell.setBorder(1);
					pCell.setHorizontalAlignment(Element.ALIGN_CENTER); // 设置水平对齐
					pCell.setVerticalAlignment(Element.ALIGN_MIDDLE);   // 设置垂直对齐
					onePro.addCell(pCell);

					pCell = new PdfPCell();
					pCell.setBorder(1);
					onePro.addCell(pCell);
					
					pCell = new PdfPCell(onePro);
					pCell.setBorder(1);
					pCell.setHorizontalAlignment(Element.ALIGN_CENTER); // 设置水平对齐
					pCell.setVerticalAlignment(Element.ALIGN_MIDDLE);   // 设置垂直对齐
					oneTitle.addCell(pCell);

					pCell = new PdfPCell(oneTitle);
					pCell.setBorder(0);
					pCell.setHorizontalAlignment(Element.ALIGN_CENTER); // 设置水平对齐
					pCell.setVerticalAlignment(Element.ALIGN_MIDDLE);   // 设置垂直对齐
					fullPage.addCell(pCell);
					
					document.add(fullPage);
				}
			}

			// 关闭连接
			document.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		} finally {
			if (writer != null)
				writer.close();
			try {
				if (pdfOut != null)
					pdfOut.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}	
	
	/**
	 * 创建专业信息汇编pdf文件
	 * @param pdfPhyPath  pdf文件物理地址
	 * @param studList    要生成准考证的考生列表
	 * @param underImgUrl 水印图片
	 * @return String     物理地址
	 */
	private void createPlanInfosPdfAll(String pdfPhyPath,
			List<PlanInfos> planInfosList,BasePro basePro,GraduateCondition graduateCondition) {

		// 打开文档，以A4
		Rectangle rectPageSize = new Rectangle(PageSize.A4);// 定义A4页面大小
		rectPageSize = rectPageSize.rotate();// 横向
		Document document = new Document(rectPageSize, 10, 10, 20, 20);

		PdfWriter writer = null;
		FileOutputStream pdfOut = null;
		try {
			// 打开流，关联到document并获取输出对象
			pdfOut = new FileOutputStream(pdfPhyPath);
			writer = PdfWriter.getInstance(document, pdfOut);

			// 创建字体
			Font fontKai = new Font(kai, 20);
			Font fontHeiTitle = new Font(hei, 12);
			Font fontHeiContent = new Font(hei, 12);

			// 打开文档连接
			document.open();

			// 写入主题内容
			float[] oneInfoCols = { 5f,15f,25f,15f,35f,10f,10f,5f,8f,8f,30f,20f,20f,15f };
			float height  = 26f;
			float height1 = 40f;
			PdfPTable fullPage = null;
			PdfPTable oneTitle = null;
			PdfPTable onePro = null;
			PdfPTable oneInfo = null;
			PdfPCell  pCell = null;
			PlanInfos pis = null;

			// 创建一个准考证
			oneTitle= new PdfPTable(1);
			onePro  = new PdfPTable(1);
			oneInfo = new PdfPTable(oneInfoCols);
			
			// 准考证头部
			pCell = getPCell("专业信息课程设置与指定教材", fontKai, Element.ALIGN_CENTER,Element.ALIGN_MIDDLE, height, 0);
			oneTitle.addCell(pCell);

			pCell = getPCell("序号", fontHeiContent, Element.ALIGN_CENTER,Element.ALIGN_MIDDLE,height1, 15);
			oneInfo.addCell(pCell);			
			pCell = getPCell("专业\n代码", fontHeiContent, Element.ALIGN_CENTER,Element.ALIGN_MIDDLE,height1, 15);
			oneInfo.addCell(pCell);
			pCell = getPCell("专业名称", fontHeiContent, Element.ALIGN_CENTER,Element.ALIGN_MIDDLE,height1, 15);		
			oneInfo.addCell(pCell);			
			pCell = getPCell("课程\n代码", fontHeiContent, Element.ALIGN_CENTER,Element.ALIGN_MIDDLE,height1, 15);
			oneInfo.addCell(pCell);
			pCell = getPCell("课程名称", fontHeiContent, Element.ALIGN_CENTER,Element.ALIGN_MIDDLE,height1, 15);
			oneInfo.addCell(pCell);
			pCell = getPCell("是否必考", fontHeiContent, Element.ALIGN_CENTER,Element.ALIGN_MIDDLE,height1, 15);
			oneInfo.addCell(pCell);		
			pCell = getPCell("课程选修\n说明", fontHeiContent, Element.ALIGN_CENTER,Element.ALIGN_MIDDLE,height1, 15);
			oneInfo.addCell(pCell);			
			pCell = getPCell("学分", fontHeiContent, Element.ALIGN_CENTER,Element.ALIGN_MIDDLE,height1, 15);
			oneInfo.addCell(pCell);
			pCell = getPCell("是否\n全国\n统考", fontHeiContent, Element.ALIGN_CENTER,Element.ALIGN_MIDDLE,height1, 15);
			oneInfo.addCell(pCell);
			pCell = getPCell("是否\n全国\n统编\n教材", fontHeiContent, Element.ALIGN_CENTER,Element.ALIGN_MIDDLE,height1, 15);
			oneInfo.addCell(pCell);	
			pCell = getPCell("教材名称", fontHeiContent, Element.ALIGN_CENTER,Element.ALIGN_MIDDLE,height1, 15);
			oneInfo.addCell(pCell);	
			pCell = getPCell("教材主编", fontHeiContent, Element.ALIGN_CENTER,Element.ALIGN_MIDDLE,height1, 15);
			oneInfo.addCell(pCell);		
			pCell = getPCell("出版社", fontHeiContent, Element.ALIGN_CENTER,Element.ALIGN_MIDDLE,height1, 15);
			oneInfo.addCell(pCell);	
			pCell = getPCell("出版时间", fontHeiContent, Element.ALIGN_CENTER,Element.ALIGN_MIDDLE,height1, 15);
			oneInfo.addCell(pCell);				
			
			//回车换行			
			for (int i = 0, fitForNull = 0; i < planInfosList.size(); i++, fitForNull++) {
				pis = planInfosList.get(i);

				// 每20行一页
				if (i % 20 == 0) {
					document.newPage();
				}
				if (i % 20 == 0)
					fullPage = new PdfPTable(1);
				// 序号
				pCell = getPCell(i+1+"", fontHeiContent,Element.ALIGN_CENTER, Element.ALIGN_MIDDLE, height, 15);
				oneInfo.addCell(pCell);		
				// 专业代码
				pCell = getPCell(pis.getProCode(), fontHeiContent,Element.ALIGN_CENTER, Element.ALIGN_MIDDLE, height, 15);
				oneInfo.addCell(pCell);
				// 专业名称
				pCell = getPCell(pis.getProName(), fontHeiContent,	Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, height, 15);
				oneInfo.addCell(pCell);				
				// 课程代码
				pCell = getPCell(pis.getSyllabusCode(), fontHeiContent,Element.ALIGN_CENTER, Element.ALIGN_MIDDLE, height, 15);
				oneInfo.addCell(pCell);
				// 课程名称
				pCell = getPCell(pis.getSyllabusName(), fontHeiContent,	Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, height, 15);
				oneInfo.addCell(pCell);
				// 课程是否必考
				pCell = getPCell("--", fontHeiContent,	Element.ALIGN_CENTER, Element.ALIGN_MIDDLE, height, 15);
				oneInfo.addCell(pCell);
				// 课程选修说明
				pCell = getPCell("--", fontHeiContent,	Element.ALIGN_CENTER, Element.ALIGN_MIDDLE, height, 15);
				oneInfo.addCell(pCell);				
				// 学分
				pCell = getPCell(pis.getSyllabusCredit(), fontHeiContent,Element.ALIGN_CENTER, Element.ALIGN_MIDDLE, height, 15);
				oneInfo.addCell(pCell);
				//是否全国统考
				String tk=pis.getExamUnitary();
				if("1".equals(tk)) tk="√";
				pCell = getPCell(tk, fontHeiContent,Element.ALIGN_CENTER, Element.ALIGN_MIDDLE, height, 15);
				oneInfo.addCell(pCell);	
				//是否全国统编教材
				String tb=pis.getExamUnitary();
				if("1".equals(tb)) tb="√";
				pCell = getPCell(tb, fontHeiContent,Element.ALIGN_CENTER, Element.ALIGN_MIDDLE, height, 15);
				oneInfo.addCell(pCell);		
				//教材名称
				pCell = getPCell(pis.getTextbookName(), fontHeiContent,Element.ALIGN_CENTER, Element.ALIGN_MIDDLE, height, 15);
				oneInfo.addCell(pCell);	
				//教材主编
				pCell = getPCell(pis.getTextbookEditor(), fontHeiContent,Element.ALIGN_CENTER, Element.ALIGN_MIDDLE, height, 15);
				oneInfo.addCell(pCell);	
				//出版社
				pCell = getPCell(pis.getTextbookPublisher(), fontHeiContent,Element.ALIGN_CENTER, Element.ALIGN_MIDDLE, height, 15);
				oneInfo.addCell(pCell);		
				//出版时间
				pCell = getPCell(pis.getPublishTime(), fontHeiContent,Element.ALIGN_CENTER, Element.ALIGN_MIDDLE, height, 15);
				oneInfo.addCell(pCell);					

				// 添加到页面表格
				if (i % 20 == 19 || i == planInfosList.size() - 1) {
					// 添加到下部表格
					pCell = new PdfPCell(oneInfo);
					pCell.setBorder(1);
					pCell.setHorizontalAlignment(Element.ALIGN_CENTER); // 设置水平对齐
					pCell.setVerticalAlignment(Element.ALIGN_MIDDLE);   // 设置垂直对齐
					onePro.addCell(pCell);

					pCell = new PdfPCell();
					pCell.setBorder(1);
					onePro.addCell(pCell);

					pCell = new PdfPCell(onePro);
					pCell.setBorder(1);
					pCell.setHorizontalAlignment(Element.ALIGN_CENTER); // 设置水平对齐
					pCell.setVerticalAlignment(Element.ALIGN_MIDDLE);   // 设置垂直对齐
					oneTitle.addCell(pCell);

					pCell = new PdfPCell(oneTitle);
					pCell.setBorder(0);
					pCell.setHorizontalAlignment(Element.ALIGN_CENTER); // 设置水平对齐
					pCell.setVerticalAlignment(Element.ALIGN_MIDDLE);   // 设置垂直对齐
					fullPage.addCell(pCell);
					
					document.add(fullPage);
				}
			}

			// 关闭连接
			document.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		} finally {
			if (writer != null)
				writer.close();
			try {
				if (pdfOut != null)
					pdfOut.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}	

	/**
	 * 创建专业信息汇编pdf文件
	 * @param pdfPhyPath  pdf文件物理地址
	 * @param studList    要生成准考证的考生列表
	 * @param underImgUrl 水印图片
	 * @return String     物理地址
	 */
	private void createExamTicket(String pdfPhyPath,List<BaseStudentInfo> studList) {

		// 打开文档，以A4
		Rectangle rectPageSize = new Rectangle(PageSize.A4);// 定义A4页面大小
		rectPageSize = rectPageSize.rotate();// 横向
		Document document = new Document(rectPageSize, 20, 20, 20, 20);

		PdfWriter writer = null;
		FileOutputStream pdfOut = null;
		try {
			// 打开流，关联到document并获取输出对象
			pdfOut = new FileOutputStream(pdfPhyPath);
			writer = PdfWriter.getInstance(document, pdfOut);

			// 创建字体
			Font fontKai = new Font(kai, 16);
			Font fontHeiTitle = new Font(hei, 20);
			Font fontHeiContent = new Font(hei, 12);

			// 打开文档连接
			document.open();

			// 写入主题内容
			float[] tabCols = { 20f, 1f, 20f };
			float[] mainCols = { 30f, 1f, 15f };
			float[] oneInfoCols = { 3f, 5f };
			float height = 26f;
			PdfPTable fullPage = null;
			PdfPTable oneTicket = null;
			PdfPTable oneMain = null;
			PdfPTable oneInfo = null;
			PdfPTable onePic = null;
			PdfPCell pCell = null;
			BaseStudentInfo stud = null;
			for (int i = 0, fitForNull = 0; i < studList.size(); i++, fitForNull++) {
				stud = studList.get(i);

				// 每6个准考证换一页
				if (i % 20 == 0) {
					document.newPage();
				}
				if (i % 20 == 0)
					fullPage = new PdfPTable(tabCols);

				// 占据中部空格
				if (fitForNull % 2 == 1) {
					pCell = new PdfPCell();
					pCell.setBorder(0);
					fullPage.addCell(pCell);
				}

				// 创建一个准考证
				oneTicket = new PdfPTable(1);
				oneMain = new PdfPTable(mainCols);
				oneInfo = new PdfPTable(oneInfoCols);
				onePic = new PdfPTable(1);

				// 准考证头部
				pCell = getPCell("陕西省高等教育自学考试", fontKai, Element.ALIGN_CENTER,
						Element.ALIGN_MIDDLE, height, 0);
				oneTicket.addCell(pCell);

				// 添加下部信息
				pCell = getPCell("准　考　证", fontHeiTitle, Element.ALIGN_CENTER,
						Element.ALIGN_MIDDLE, height, 0);
				pCell.setColspan(3);
				oneMain.addCell(pCell);

				// 准考证号
				pCell = getPCell("准考证号：", fontHeiContent, Element.ALIGN_RIGHT,
						Element.ALIGN_MIDDLE, height, 0);
				oneInfo.addCell(pCell);

				pCell = getPCell(stud.getStudExamCode(), fontHeiContent,
						Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, height, 0);
				oneInfo.addCell(pCell);

				// 姓名
				pCell = getPCell("姓　　名：", fontHeiContent, Element.ALIGN_RIGHT,
						Element.ALIGN_MIDDLE, height, 0);
				oneInfo.addCell(pCell);

				pCell = getPCell(stud.getStudName(), fontHeiContent,
						Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, height, 0);
				oneInfo.addCell(pCell);

				// 身份证号
				pCell = getPCell("身份证号：", fontHeiContent, Element.ALIGN_RIGHT,
						Element.ALIGN_MIDDLE, height, 0);
				oneInfo.addCell(pCell);

				pCell = getPCell(stud.getStudIdnum(), fontHeiContent,
						Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, height, 0);
				oneInfo.addCell(pCell);

				// 性别
				pCell = getPCell("性　　别：", fontHeiContent, Element.ALIGN_RIGHT,
						Element.ALIGN_MIDDLE, height, 0);
				oneInfo.addCell(pCell);

				pCell = getPCell(stud.getGenderName(), fontHeiContent,
						Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, height, 0);
				oneInfo.addCell(pCell);

				// 空两行
				pCell = new PdfPCell();
				pCell.setBorder(0);
				pCell.setFixedHeight(height);
				oneInfo.addCell(pCell);
				oneInfo.addCell(pCell);

				// 添加照片
				try {
					File imgFile = new File(stud.getStudPhotoFile1());
					if (!imgFile.exists()) {
						stud.setStudPhotoFile1(stud.getStudPhotoFile2());
					}
					Image image = Image.getInstance(stud.getStudPhotoFile1());
					image.scaleAbsolute(90, 120);
					pCell = new PdfPCell(image);
					pCell.setBorder(0);
					pCell.setHorizontalAlignment(Element.ALIGN_CENTER); // 设置水平对齐
					pCell.setVerticalAlignment(Element.ALIGN_MIDDLE);   // 设置垂直对齐
				} catch (Exception e) {
					// do nothing
				}
				onePic.addCell(pCell);

				// 添加到下部表格
				pCell = new PdfPCell(oneInfo);
				pCell.setBorder(0);
				pCell.setHorizontalAlignment(Element.ALIGN_CENTER); // 设置水平对齐
				pCell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置垂直对齐
				oneMain.addCell(pCell);

				pCell = new PdfPCell();
				pCell.setBorder(0);
				oneMain.addCell(pCell);

				pCell = new PdfPCell(onePic);
				pCell.setBorder(0);
				pCell.setHorizontalAlignment(Element.ALIGN_CENTER); // 设置水平对齐
				pCell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置垂直对齐
				oneMain.addCell(pCell);

				pCell = new PdfPCell(oneMain);
				pCell.setBorder(0);
				pCell.setHorizontalAlignment(Element.ALIGN_CENTER); // 设置水平对齐
				pCell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置垂直对齐
				oneTicket.addCell(pCell);

				pCell = new PdfPCell(oneTicket);
				pCell.setBorder(15);
				pCell.setHorizontalAlignment(Element.ALIGN_CENTER); // 设置水平对齐
				pCell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置垂直对齐
				fullPage.addCell(pCell);

				// 添加到页面表格
				if (i % 6 == 5 || i == studList.size() - 1) {
					document.add(fullPage);
				}
			}

			// 关闭连接
			document.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		} finally {
			if (writer != null)
				writer.close();
			try {
				if (pdfOut != null)
					pdfOut.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * @param inputFile      已存在的pdf文件物理地址
	 * @param outputFile     已加水印的pdf文件输出地址
	 * @param imageFilePath  水印文件的物理地址
	 */
	private boolean makeWaterMark(String inputFile, String outputFile,
			String imageFilePath) {
		PdfReader reader = null;
		PdfStamper stamp = null;
		try {
			reader = new PdfReader(inputFile);
			stamp = new PdfStamper(reader, new FileOutputStream(outputFile));
			int total = reader.getNumberOfPages() + 1;
			Image image = Image.getInstance(imageFilePath);
			image.setAbsolutePosition(0, 0);
			PdfContentByte under;
			for (int i = 1; i < total; i++) {
				under = stamp.getUnderContent(i);
				// 添加图片
				under.addImage(image);
				under.beginText();
				under.setColorFill(Color.CYAN);
				// 字体设置结束
				under.endText();
			}
			stamp.close();
			return true;
		} catch (Exception e) {
			log.error(this.getClass(), "添加水印错误！", e);
			return false;
		}
	}
}
