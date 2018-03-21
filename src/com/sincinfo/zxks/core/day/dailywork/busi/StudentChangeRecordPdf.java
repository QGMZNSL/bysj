package com.sincinfo.zxks.core.day.dailywork.busi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.sincinfo.zxks.bean.BaseCity;
import com.sincinfo.zxks.bean.BaseStudinfoChange;
import com.sincinfo.zxks.bean.BaseUser;
import com.sincinfo.zxks.common.util.Log;
import com.sincinfo.zxks.zxksdbs.StudentChangeRecordDbService;

/**
 * @see 生成考生基本信息变更记录 pdf
 * @author guanm
 */
public class StudentChangeRecordPdf extends StudentChangeRecordDbService {
	public static final Long LOCK = System.currentTimeMillis();

	private Log log;
	private String[] paths;
	private String subPath;
	private BaseFont kai;
	private BaseFont hei;

	public StudentChangeRecordPdf() {
		this.log = new Log();
		this.paths = super.getPaths();
		this.subPath = super.getConfig("39");// 所有文件上传使用的临时文件路径
		String keiFile = this.paths[0] + super.getConfig("27");
		String heiFile = this.paths[0] + super.getConfig("28");

		try {
			this.kai = BaseFont.createFont(keiFile, BaseFont.IDENTITY_H,
					BaseFont.NOT_EMBEDDED);
			this.hei = BaseFont.createFont(heiFile, BaseFont.IDENTITY_H,
					BaseFont.NOT_EMBEDDED);
		} catch (Exception e) {
			log
					.error(this.getClass(), String.format(
							"字体文件无法找到！%1$s , %2$s", keiFile,
							heiFile), e);
		}

		File dir = new File(this.paths[0] + this.subPath);
		if (!dir.exists()) {
			dir.mkdirs();
		}
	}

	/**
	 * 返回一个PdfPCell对象
	 * 
	 * @param msg
	 *            String 文本内容
	 * @param font
	 *            Font 字体
	 * @param halign
	 *            int 水平对齐
	 * @param valign
	 *            int 竖直对齐
	 * @param fixHeight
	 *            float 固定高度
	 * @param border
	 *            int 边框 15是全边框
	 * @return PdfPCell
	 */
	public PdfPCell getPCell(String msg, Font font, int halign, int valign,
			float fixHeight, int border) {
		PdfPCell cell = new PdfPCell();
		cell = new PdfPCell(new Phrase(msg, font));
		cell.setHorizontalAlignment(halign); // 设置水平对齐
		cell.setVerticalAlignment(valign); // 设置垂直对齐
		cell.setFixedHeight(fixHeight);
		cell.setBorder(border);
		return cell;
	}

	/**
	 * 生成考生基本信息变更记录pdf
	 * 
	 * @param
	 * @return String[] [0]-物理地址 [1]-网络地址
	 * @throws Exception
	 */
	public String[] makeStudentChangeRecordPdf(List<BaseStudinfoChange> list,
			BaseUser user, String studExamCode, String cityCode,
			String startDate, String endDate,BaseCity city) {
		if (list == null || list.size() == 0) {
			return null;
		}
		String fileName = this.getLock() + "_" + user.getUserName() + ".pdf";
		String[] url = new String[2];
		url[0] = paths[0] + subPath + System.getProperty("file.separator")
				+ fileName;
		url[1] = paths[1] + subPath + System.getProperty("file.separator")
				+ fileName;

		Font fontTitle = new Font(kai, 6);
		Font fontContent = new Font(hei, 6);

		// 打开文档，以A4
		Rectangle rectPageSize = new Rectangle(PageSize.A4);// 定义A4页面大小
		Document document = new Document(rectPageSize, -35, -35, 20, 20);

		PdfWriter writer = null;
		FileOutputStream pdfOut = null;
		try {
			pdfOut = new FileOutputStream(url[0]);
			writer = PdfWriter.getInstance(document, pdfOut);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			new Log().error(this.getClass(), "考生基本信息变更记录生成pdf有误", e);
		}

		document.open();

//		float[] tabCols = { 0.5f, 1.5f, 1f, 1.5f, 1.6f, 1.5f, 1f, 1f, 1f, 1f,
//				1f };
		float[] tabCols = { 0.5f, 1.5f, 1f, 1.5f, 1.6f, 2.5f, 2f, 2f};
		float[] tabTop = { 24f };
		float fixHeight = 30f;
		int border = 15;
		PdfPTable fullPage = new PdfPTable(tabCols);
		PdfPTable topTable = new PdfPTable(tabTop);
		document.newPage();
		PdfPCell pCell = null;
		
		
		StringBuilder str=new StringBuilder();
//		str.append("考生基本信息变更记录；");
//		if(studExamCode!=null&&!studExamCode.equals("")){
//			str.append("准考证号:"+studExamCode);
//		}
//		if(cityCode==null||cityCode.equals("")){
//			str.append("  各地市");
//		}else{
//			str.append("  地市："+city.getCityName());
//		}
//		if(startDate!=null&&!startDate.equals("")){
//			str.append(" ,起始时间:"+startDate);
//		}
//		if(endDate!=null&&!endDate.equals("")){
//			str.append(" ,截止时间:"+endDate);
//		}
		str.append("考生基本信息变更记录表");
		pCell=getPCell(str.toString(), fontTitle, Element.ALIGN_CENTER,
				Element.ALIGN_MIDDLE, 20f, 15);
		pCell.setBorder(0);
		topTable.addCell(pCell);
		
		
		pCell = getPCell("序号", fontTitle, Element.ALIGN_CENTER,
				Element.ALIGN_MIDDLE, fixHeight, border);
		fullPage.addCell(pCell);

		pCell = getPCell("准考证号", fontTitle, Element.ALIGN_CENTER,
				Element.ALIGN_MIDDLE, fixHeight, border);
		fullPage.addCell(pCell);

		pCell = getPCell("姓名", fontTitle, Element.ALIGN_CENTER,
				Element.ALIGN_MIDDLE, fixHeight, border);
		fullPage.addCell(pCell);

		pCell = getPCell("证件号码", fontTitle, Element.ALIGN_CENTER,
				Element.ALIGN_MIDDLE, fixHeight, border);
		fullPage.addCell(pCell);

		pCell = getPCell("报考专业", fontTitle, Element.ALIGN_CENTER,
				Element.ALIGN_MIDDLE, fixHeight, border);
		fullPage.addCell(pCell);

		pCell = getPCell("信息变更内容", fontTitle, Element.ALIGN_CENTER,
				Element.ALIGN_MIDDLE, fixHeight, border);
		fullPage.addCell(pCell);

		pCell = getPCell("信息变更时间", fontTitle, Element.ALIGN_CENTER,
				Element.ALIGN_MIDDLE, fixHeight, border);
		fullPage.addCell(pCell);

		pCell = getPCell("信息变更原因", fontTitle, Element.ALIGN_CENTER,
				Element.ALIGN_MIDDLE, fixHeight, border);
		fullPage.addCell(pCell);

//		pCell = getPCell("证明材料", fontTitle, Element.ALIGN_CENTER,
//				Element.ALIGN_MIDDLE, fixHeight, border);
//		fullPage.addCell(pCell);
//
//		pCell = getPCell("登记人", fontTitle, Element.ALIGN_CENTER,
//				Element.ALIGN_MIDDLE, fixHeight, border);
//		fullPage.addCell(pCell);
//
//		pCell = getPCell("审批人", fontTitle, Element.ALIGN_CENTER,
//				Element.ALIGN_MIDDLE, fixHeight, border);
//		fullPage.addCell(pCell);

		for (int i = 0; i < list.size(); i++) {
			BaseStudinfoChange bean = list.get(i);
			pCell = getPCell(String.valueOf(i + 1), fontTitle,
					Element.ALIGN_CENTER, Element.ALIGN_MIDDLE, fixHeight,
					border);
			fullPage.addCell(pCell);

			pCell = getPCell(bean.getStudExamCode(), fontTitle,
					Element.ALIGN_CENTER, Element.ALIGN_MIDDLE, fixHeight,
					border);
			fullPage.addCell(pCell);

			pCell = getPCell(bean.getStudName(), fontTitle,
					Element.ALIGN_CENTER, Element.ALIGN_MIDDLE, fixHeight,
					border);
			fullPage.addCell(pCell);

			pCell = getPCell(bean.getStudIdnum(), fontTitle,
					Element.ALIGN_CENTER, Element.ALIGN_MIDDLE, fixHeight,
					border);
			fullPage.addCell(pCell);

			pCell = getPCell(bean.getProName(), fontTitle,
					Element.ALIGN_CENTER, Element.ALIGN_MIDDLE, fixHeight,
					border);
			fullPage.addCell(pCell);

			pCell = getPCell(bean.getChangeTemplate(), fontTitle,
					Element.ALIGN_CENTER, Element.ALIGN_MIDDLE, fixHeight,
					border);
			fullPage.addCell(pCell);

			pCell = getPCell(bean.getChangeApplyTime(), fontTitle,
					Element.ALIGN_CENTER, Element.ALIGN_MIDDLE, fixHeight,
					border);
			fullPage.addCell(pCell);

			pCell = getPCell(bean.getChangeReason(), fontTitle,
					Element.ALIGN_CENTER, Element.ALIGN_MIDDLE, fixHeight,
					border);
			fullPage.addCell(pCell);

//			pCell = getPCell(bean.getChangeProve(), fontTitle,
//					Element.ALIGN_CENTER, Element.ALIGN_MIDDLE, fixHeight,
//					border);
//			fullPage.addCell(pCell);
//
//			pCell = getPCell(bean.getChangeApplyUserRealName(), fontTitle,
//					Element.ALIGN_CENTER, Element.ALIGN_MIDDLE, fixHeight,
//					border);
//			fullPage.addCell(pCell);
//
//			pCell = getPCell(bean.getChangeAuditUserRealName(), fontTitle,
//					Element.ALIGN_CENTER, Element.ALIGN_MIDDLE, fixHeight,
//					border);
//			fullPage.addCell(pCell);
		}
		try {
			document.add(topTable);
			document.add(fullPage);
		} catch (DocumentException e) {
			new Log().error(this.getClass(), "页面docunmet添加page有误", e);
		}
		document.close();
		if (writer != null)
			writer.close();
		if (pdfOut != null)
			try {
				pdfOut.close();
			} catch (IOException e) {
				new Log().error(this.getClass(), "关闭pdf输出流有误", e);
			}
		return url;
	}

	/**
	 * @see 获得唯一标识
	 */
	public String getLock() {
		long l;
		synchronized (LOCK) {
			l = System.currentTimeMillis();
		}
		return String.valueOf(l);
	}

}
