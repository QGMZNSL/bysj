/**----------------------------------------------------------------
/*Copyright (C) 2009 西安云海信息技术有限公司
/*版权所有
/*
/*文件名：MyPDF.java
/*文件功能描述：PDF报表
/*
/*
/*创建人：JIANGHE
/*创建时间：2010-08-11
/* 
/*
/**----------------------------------------------------------------*/
package com.sincinfo.zxks.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfWriter;
import com.sincinfo.zxks.common.db.DbUtil;
/**
 * @author Administrator
 * 
 */
/**
 * @author Administrator
 * 
 */
public class PdfTool {
	public static String FONTTTF = null;
	
	static {
	    try {
	        DbUtil dbutil = new DbUtil();
	        FONTTTF = dbutil.getPaths()[0] + dbutil.getConfig("30");
	    } catch ( Exception e) {
            //e.printStackTrace();
        }
	}

	private File file = null; // 生成的pdf文件对象（对应物理文件）
	private Rectangle rectangle = null; // 长方形页面
	private Document document = null; // PDF文档对象（虚拟文件）
	private FileOutputStream fos = null; // 文件输出流对象，用于写物理文件
	private PdfWriter pdfWriter = null; // 用于将Document对象写入输出流
	private FileInputStream fis = null;
	private PdfReader pdfReader = null;

	/**
	 * 生成BaseFont对象
	 * 
	 * @param fontttf
	 *            字体文件存放的位置
	 * @param identity
	 *            文字方向 BaseFont.IDENTITY_V,BaseFont.IDENTITY_V
	 * @param embedded
	 *            嵌入 BaseFont.NOT_EMBEDDED,BaseFont.EMBEDDED 含义有待研究
	 * 
	 * @return BaseFont对象
	 * 
	 * @deprecated
	 */
	public static BaseFont createBaseFont(String fontttf, String identity,
			boolean embedded) {
		BaseFont baseFont = null;
		try {
			baseFont = BaseFont.createFont(fontttf, identity, embedded);
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return baseFont;
	}

	/**
	 * 生成字体样式
	 * 
	 * @param fontttf
	 *            字体文件存放的位置
	 * @param identity
	 *            文字方向 BaseFont.IDENTITY_V,BaseFont.IDENTITY_V
	 * @param embedded
	 *            嵌入 BaseFont.NOT_EMBEDDED,BaseFont.EMBEDDED 含义有待研究
	 * @param baseFont
	 *            基本字体样式
	 * @param fontSize
	 *            字体大小
	 * @param style
	 *            字体样式
	 *            FONT.BOLD,FONT.BOLDITALIC,FONT.ITALIC,FONT.NORMAL,FONT.STRIKETHRU,FONT.UNDERLINE
	 * 
	 * @return 字体
	 */
	public static Font createFontStyle(String fontttf, String identity,
			boolean embedded, float fontSize, int style) {
		Font font = null;
		BaseFont baseFont = null;
		try {
			baseFont = BaseFont.createFont(fontttf, identity, embedded);
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		font = new Font(baseFont, fontSize, style);
		return font;
	}

	/**
	 * 创建PDF文件和各种IO流
	 * 
	 * @param filepath
	 *            文件存放地址
	 * @param rectangle
	 *            页面对象
	 * @param marginLeft
	 *            左页边距
	 * @param marginRight
	 *            右
	 * @param marginTop
	 *            上
	 * @param marginBottom
	 *            下
	 * @return
	 */
	public boolean createPdfFile(String filepath, Rectangle rectangle,
			float marginLeft, float marginRight, float marginTop,
			float marginBottom) {
		boolean flag = true;
		try {
			file = new File(filepath); // PDF文件对象
			file.getParentFile().mkdirs(); // 在硬盘上创建文件路径
			// Rectangle rectangle1 = new Rectangle(PageSize.A4); // 设置纸张大小
			// rectangle.setBackgroundColor(Color.lightGray); // 背景色
			document = new Document(rectangle, marginLeft, marginRight,
					marginTop, marginBottom); // 设置页边距,左右上下
			fos = new FileOutputStream(file); // 文件输出流
			pdfWriter = PdfWriter.getInstance(document, fos); // 写对象

			flag = true;
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
			// this.closePdfFile();
		}
		return flag;
	}

	/**
	 * 关闭PDF对象和IO流
	 */
	public void closePdfFile() {
		try {
			if (document != null && document.isOpen()) {
				document.close();
			}
			if (fos != null) {
				fos.close();
			}
			if (pdfWriter != null) {
				pdfWriter.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 生成PdfPTable对象
	 * 
	 * @param f
	 *            表格的列宽比例数组
	 * @return PdfPTable对象
	 */
	public static PdfPTable createPdfPTable(float[] f) {
		PdfPTable pTable = new PdfPTable(f);
		return pTable;
	}

	/**
	 * 生成PdfPCell对象
	 * 
	 * @param phrase
	 *            要加入cell的Phrase对象
	 * @param height
	 *            行高度
	 * @param colspan
	 *            合并列
	 * @param hAlign
	 *            水平对齐
	 *            Element.ALIGN_LEFT,Element.ALIGN_CENTER,ELEMENT.ALIGN_RIGHT
	 * @param vAlign
	 *            垂直对齐
	 *            Element.ALIGN_TOP,Element.ALIGN_MIDDLE,ELEMENT.ALIGN_BOTTOM
	 * @return PdfPCell对象
	 */
	public static PdfPCell createPdfPCell(Phrase phrase, int hAlign, int vAlign) {
		PdfPCell pCell = new PdfPCell(phrase);
		// pCell.addElement(phrase); 使用这种方法貌似不能控制对齐方式
		// pCell.setColspan(1); // 合并行
		// pCell.setBackgroundColor(Color.red); 背景色
		// pCell.setPadding(5f);
		pCell.setBorder(15); // 四周边框线
		pCell.setHorizontalAlignment(hAlign); // 设置水平对齐
		pCell.setVerticalAlignment(vAlign); // 设置垂直对齐

		return pCell;
	}

	/**
	 * 生成PdfPCell对象
	 * 
	 * @param image
	 *            要加入cell的Image对象
	 * @param height
	 *            行高度
	 * @param colspan
	 *            合并列
	 * @param hAlign
	 *            水平对齐
	 *            Element.ALIGN_LEFT,Element.ALIGN_CENTER,ELEMENT.ALIGN_RIGHT
	 * @param vAlign
	 *            垂直对齐
	 *            Element.ALIGN_TOP,Element.ALIGN_MIDDLE,ELEMENT.ALIGN_BOTTOM
	 * @return PdfPCell对象
	 */
	public static PdfPCell createPdfPCell(Image image, int hAlign, int vAlign) {
		PdfPCell pCell = new PdfPCell(image);
		// pCell.addElement(phrase); 使用这种方法貌似不能控制对齐方式
		// pCell.setColspan(1); // 合并行
		// pCell.setBackgroundColor(Color.red); 背景色
		// pCell.setPadding(5f);
		pCell.setBorder(15); // 四周边框线
		pCell.setHorizontalAlignment(hAlign); // 设置水平对齐
		pCell.setVerticalAlignment(vAlign); // 设置垂直对齐

		return pCell;
	}

	/**
	 * 生成PdfPCell对象
	 * 
	 * @param pTable
	 *            要加入cell的PdfPTable对象
	 * @param height
	 *            行高度
	 * @param colspan
	 *            合并列
	 * @param hAlign
	 *            水平对齐
	 *            Element.ALIGN_LEFT,Element.ALIGN_CENTER,ELEMENT.ALIGN_RIGHT
	 * @param vAlign
	 *            垂直对齐
	 *            Element.ALIGN_TOP,Element.ALIGN_MIDDLE,ELEMENT.ALIGN_BOTTOM
	 * @return PdfPCell对象
	 */
	public static PdfPCell createPdfPCell(PdfPTable pTable, int hAlign,
			int vAlign) {
		PdfPCell pCell = new PdfPCell(pTable);
		// pCell.addElement(phrase); 使用这种方法貌似不能控制对齐方式
		// pCell.setColspan(1); // 合并行
		// pCell.setBackgroundColor(Color.red); 背景色
		// pCell.setPadding(5f);
		pCell.setBorder(15); // 四周边框线
		pCell.setHorizontalAlignment(hAlign); // 设置水平对齐
		pCell.setVerticalAlignment(vAlign); // 设置垂直对齐

		return pCell;
	}

	/**
	 * 生成一张绝对定位的图片
	 * 
	 * @param imagepath
	 *            图片物理地址
	 * @param absoluteX
	 *            图片定位X轴 最左边为0
	 * @param absoluteY
	 *            图片定位Y轴 最下边为0
	 * @return Image图片对象
	 */
	public static Image createImage(String imagepath, float absoluteX,
			float absoluteY) {
		Image image = null;
		try {
			image = Image.getInstance(imagepath);
			image.setAbsolutePosition(absoluteX, absoluteY);
		} catch (BadElementException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}

	/**
	 * 测试用
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		PdfTool pdf = new PdfTool();
		/* 写文件 */
		try {
			pdf.createPdfFile(PdfTool.FONTTTF, PageSize.A4, 10, 10, 10, 10);

			HeaderFooter footer = new HeaderFooter(
					new Phrase("This is page: "), new Phrase("."));
			footer.setBorder(Rectangle.BOTTOM);
			pdf.document.setFooter(footer);

			HeaderFooter header = new HeaderFooter(new Phrase(
					"This is a header without a page number"), false);
			pdf.document.setHeader(header);

			pdf.document.open();

			PdfPTable pTable = null;
			PdfPCell pCell = null;

			Font font = pdf.createFontStyle(PdfTool.FONTTTF,
					BaseFont.IDENTITY_V, BaseFont.NOT_EMBEDDED, 8,
					Font.BOLDITALIC);
			pTable = pdf.createPdfPTable(new float[] { 1.0f, 2.0f, 3.0f });
			for (int i = 0; i < 21; i++) {
				Paragraph phrase = new Paragraph(i, "测试" + i, font);
				pCell = pdf.createPdfPCell(phrase, Element.ALIGN_CENTER,
						Element.ALIGN_TOP);
				pCell.setPaddingBottom(0f);
				pTable.addCell(pCell);
			}
			pdf.document.add(pTable);

			pdf.closePdfFile();
		} catch (DocumentException e1) {
			e1.printStackTrace();
		}

		// /* 读文件 */
		// try {
		// pdf.file = new File(PDF.FILEPATH);
		// pdf.fis = new FileInputStream(pdf.file);
		// pdf.pdfReader = new PdfReader(pdf.fis);
		// byte[] bytes = pdf.pdfReader.getPageContent(1);
		// for (int i = 0; i<bytes.length; i++) {
		// System.out.print((char)bytes[i]);
		// }
		//                
		// } catch (FileNotFoundException e) {
		// e.printStackTrace();
		// } catch (IOException e) {
		// e.printStackTrace();
		// } finally {
		// pdf.closePdfFile(); // 关闭文件，各种IO流
		// }

	}

	/* set和get方法 */
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public Rectangle getRectangle() {
		return rectangle;
	}

	public void setRectangle(Rectangle rectangle) {
		this.rectangle = rectangle;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public FileOutputStream getFos() {
		return fos;
	}

	public void setFos(FileOutputStream fos) {
		this.fos = fos;
	}

	public PdfWriter getPdfWriter() {
		return pdfWriter;
	}

	public void setPdfWriter(PdfWriter pdfWriter) {
		this.pdfWriter = pdfWriter;
	}

}
