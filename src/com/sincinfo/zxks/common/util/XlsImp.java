/**
 * @Title: com.lt.test.XLSExp.java<br>
 * @Description: XLS文件的导入操作 <br>
 * <br>
 * @author litian<br>
 * @date Feb 21, 2012 2:39:30 PM 
 * @version V1.0   
 */
package com.sincinfo.zxks.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

/**
 * @ClassName: XLSImp
 * @Description: 用于导入EXCEL文件，读取其中内容 <br>
 *               <br>
 * @author litian
 * @date Feb 21, 2012 2:17:46 PM<br>
 * 
 */
public class XlsImp {
	/**
	 * 读取excel文件的sheetName对应的工作表中，startRow至endRow中，A-Z列的内容
	 * 
	 * @param xlsFile
	 *            excel的File对象
	 * @param sheetName
	 *            工作表sheet的名字
	 * @param startRow
	 *            开始行数
	 * @param endRow
	 *            结束行数 如果为负值、或者大于正文行数、或者小于开始行数，则取总行数
	 * @return List<XlsRow> 结果列表，行数在前的记录在列表前端，这里只支持A-Z的列
	 * @throws IOException
	 *             流异常
	 * @throws Exception
	 *             其他异常
	 */
	public static List<XlsRow> impExcel(File xlsFile, String sheetName,
			int startRow, int endRow) throws IOException, Exception {
		// 结果集列表
		List<XlsRow> xlsRows = new ArrayList<XlsRow>();

		// 初始化
		Workbook dataFile = null;
		InputStream is = null;
		try {
			// 读取excel文件指定的sheet
			is = new FileInputStream(xlsFile);

			// 获取文档对象
			dataFile = Workbook.getWorkbook(is);

			// 获得指定工作表
			Sheet sheet = dataFile.getSheet(sheetName);

			// 读取sheet的数据
			if (sheet != null) {
				int sheetRows = sheet.getRows();

				// 如果为负值、或者大于正文行数、或者小于开始行数，则取总行数
				if (endRow < 0 || endRow >= sheetRows || endRow < startRow) {
					endRow = sheetRows;
				}

				// 遍历中间行数
				XlsRow row = null;
				boolean addSign = false;
				String A = null;
				for (int i = startRow; i < endRow; i++) {
					row = new XlsRow();
					addSign = false;
					try {
						// A列
						A = getContent(sheet.getCell(0, i));
						if ("".equals(StringTool.trim(A)))
							continue;
						row.setA(A);
						addSign = true;
						// B列
						row.setB(getContent(sheet.getCell(1, i)));
						// C列
						row.setC(getContent(sheet.getCell(2, i)));
						// D列
						row.setD(getContent(sheet.getCell(3, i)));
						// E列
						row.setE(getContent(sheet.getCell(4, i)));
						// F列
						row.setF(getContent(sheet.getCell(5, i)));
						// G列
						row.setG(getContent(sheet.getCell(6, i)));
						// H列
						row.setH(getContent(sheet.getCell(7, i)));
						// I列
						row.setI(getContent(sheet.getCell(8, i)));
						// J列
						row.setJ(getContent(sheet.getCell(9, i)));
						// K列
						row.setK(getContent(sheet.getCell(10, i)));
						// L列
						row.setL(getContent(sheet.getCell(11, i)));
						// M列
						row.setM(getContent(sheet.getCell(12, i)));
						// N列
						row.setN(getContent(sheet.getCell(13, i)));
						// O列
						row.setO(getContent(sheet.getCell(14, i)));
						// P列
						row.setP(getContent(sheet.getCell(15, i)));
						// Q列
						row.setQ(getContent(sheet.getCell(16, i)));
						// R列
						row.setR(getContent(sheet.getCell(17, i)));
						// S列
						row.setS(getContent(sheet.getCell(18, i)));
						// T列
						row.setT(getContent(sheet.getCell(19, i)));
						// U列
						row.setU(getContent(sheet.getCell(20, i)));
						// V列
						row.setV(getContent(sheet.getCell(21, i)));
						// W列
						row.setW(getContent(sheet.getCell(22, i)));
						// X列
						row.setX(getContent(sheet.getCell(23, i)));
						// Y列
						row.setY(getContent(sheet.getCell(24, i)));
						// Z列
						row.setZ(getContent(sheet.getCell(25, i)));
					} catch (Exception e) {
					}

					// 加入结果列表
					if (addSign)
						xlsRows.add(row);
				}
			}
		} catch (Exception e) {
			throw new Exception("读取excel失败！" + e.getMessage(), e.getCause());
		} finally {
			is.close();
		}

		return xlsRows;
	}

	/**
	 * 读取excel文件的sheetName对应的工作表中，startRow至endRow中，A-Z列的内容
	 * 
	 * @param xlsFile
	 *            excel的File对象
	 * @param sheetIndex
	 *            工作表sheet的下标
	 * @param startRow
	 *            开始行数
	 * @param endRow
	 *            结束行数 如果为负值、或者大于正文行数、或者小于开始行数，则取总行数
	 * @return List<XlsRow> 结果列表，行数在前的记录在列表前端，这里只支持A-Z的列
	 * @throws IOException
	 *             流异常
	 * @throws Exception
	 *             其他异常
	 */
	public static List<XlsRow> impExcel(File xlsFile, int sheetIndex,
			int startRow, int endRow) throws IOException, Exception {
		// 结果集列表
		List<XlsRow> xlsRows = new ArrayList<XlsRow>();

		// 初始化
		Workbook dataFile = null;
		InputStream is = null;
		try {
			// 读取excel文件指定的sheet
			is = new FileInputStream(xlsFile);

			// 获取文档对象
			dataFile = Workbook.getWorkbook(is);

			// 获得指定工作表
			Sheet sheet = dataFile.getSheet(sheetIndex);

			// 读取sheet的数据
			if (sheet != null) {
				int sheetRows = sheet.getRows();

				// 如果为负值、或者大于正文行数、或者小于开始行数，则取总行数
				if (endRow < 0 || endRow >= sheetRows || endRow < startRow) {
					endRow = sheetRows;
				}

				// 遍历中间行数
				XlsRow row = null;
				boolean addSign = false;
				String A = null;
				for (int i = startRow; i < endRow; i++) {
					row = new XlsRow();
					addSign = false;
					try {

						// A列
						A = getContent(sheet.getCell(0, i));
						if ("".equals(StringTool.trim(A)))
							continue;
						row.setA(A);
						addSign = true;
						// B列
						row.setB(getContent(sheet.getCell(1, i)));
						// C列
						row.setC(getContent(sheet.getCell(2, i)));
						// D列
						row.setD(getContent(sheet.getCell(3, i)));
						// E列
						row.setE(getContent(sheet.getCell(4, i)));
						// F列
						row.setF(getContent(sheet.getCell(5, i)));
						// G列
						row.setG(getContent(sheet.getCell(6, i)));
						// H列
						row.setH(getContent(sheet.getCell(7, i)));
						// I列
						row.setI(getContent(sheet.getCell(8, i)));
						// J列
						row.setJ(getContent(sheet.getCell(9, i)));
						// K列
						row.setK(getContent(sheet.getCell(10, i)));
						// L列
						row.setL(getContent(sheet.getCell(11, i)));
						// M列
						row.setM(getContent(sheet.getCell(12, i)));
						// N列
						row.setN(getContent(sheet.getCell(13, i)));
						// O列
						row.setO(getContent(sheet.getCell(14, i)));
						// P列
						row.setP(getContent(sheet.getCell(15, i)));
						// Q列
						row.setQ(getContent(sheet.getCell(16, i)));
						// R列
						row.setR(getContent(sheet.getCell(17, i)));
						// S列
						row.setS(getContent(sheet.getCell(18, i)));
						// T列
						row.setT(getContent(sheet.getCell(19, i)));
						// U列
						row.setU(getContent(sheet.getCell(20, i)));
						// V列
						row.setV(getContent(sheet.getCell(21, i)));
						// W列
						row.setW(getContent(sheet.getCell(22, i)));
						// X列
						row.setX(getContent(sheet.getCell(23, i)));
						// Y列
						row.setY(getContent(sheet.getCell(24, i)));
						// Z列
						row.setZ(getContent(sheet.getCell(25, i)));
					} catch (Exception e) {
					}

					// 加入结果列表
					if (addSign)
						xlsRows.add(row);
				}
			}
		} catch (Exception e) {
			throw new Exception("读取excel失败！" + e.getMessage(), e.getCause());
		} finally {
			is.close();
		}

		return xlsRows;
	}

	/**
	 * 获取excel单元格内容
	 * 
	 * @param cell
	 * @return
	 */
	private static String getContent(Cell cell) {
		return nullToEmpty(cell.getContents()).trim();
	}

	/**
	 * 将null转换成空字符串""
	 * 
	 * @param src
	 *            原字符串
	 * @return 处理结果
	 */
	public static String nullToEmpty(String src) {
		return src == null ? "" : src.trim();
	}
}
