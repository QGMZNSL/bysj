package com.sincinfo.zxks.common.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.PageOrientation;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class XLS {
	private static String border;
	private static float colWidth;
	private static float rowHeight;
	private static int startRow;
	private static String tableTitle;
	private static int sheetIndex;
	private static String sheetName;
	private jxl.write.WritableWorkbook wwb = null; 
	private BufferedOutputStream bos = null;
	private jxl.write.WritableSheet ws = null;
	private int row = 0;
	public static final int MAX_SHEET_LINES = 10000;

	private List<String> sheetList = new ArrayList<String>();

	/***************************************************************************
	 * 创建并打庿文件
	 * 
	 * @param path
	 *            文件路径
	 * @param filename
	 *            文件县
	 * @param aTableTitle
	 *            表头名称
	 * @param aSheetName
	 *            sheet名称
	 * @return 返回打开的文乿
	 */
	public boolean creatOpenFile(String path, String filename) {
		boolean result = false;
		if (!StringTool.isEmpty(path) && !StringTool.isEmpty(filename)) {
			// 生成文件
			try {
				// 文件路径判断
				File dir = new File(path);
				if (dir != null && !dir.exists()) {
					dir.mkdirs();
				}

				// 合成真实的文件名
				String file = "";
				if (path.endsWith(System.getProperty("file.separator"))) {
					file = path + filename;
				} else {
					file = path + System.getProperty("file.separator")
							+ filename;
				}

				// 生成文件
				bos = new BufferedOutputStream(new FileOutputStream(file));
				wwb = Workbook.createWorkbook(bos);
				sheetIndex = 0;
				result = true;
				// 生成表头和sheet县
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 生成表头和sheet
	 * 
	 * @param aTableTitle
	 *            表头
	 * @param aSheetName
	 *            sheet名称
	 * @param colNames
	 *            列名
	 * @return
	 */
	private Label creatNewSheet1(String aTableTitle, String aSheetName,
			String[] colNames) {
		Label aCell = null;
		try {
			WritableFont fontStyle = new WritableFont(WritableFont.ARIAL, 10,
					WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
					jxl.format.Colour.BLACK); // 定义格式 字体 下划縿 斜体 粗体 颜色
			WritableCellFormat format = new WritableCellFormat(fontStyle);
			format.setAlignment(jxl.format.Alignment.CENTRE); // 设置对齐方式
			format.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			format.setBorder(Border.ALL, BorderLineStyle.THIN);
			ws = wwb.createSheet(aSheetName, sheetIndex);
			ws.getSettings().setOrientation(PageOrientation.LANDSCAPE);
			// 生成表头
			if (!StringTool.isEmpty(aTableTitle)) {
				Label titleCell = new Label(0, startRow, aTableTitle, this
						.getWritableCellFormat());
				ws.addCell(titleCell);
				ws.mergeCells(0, 0, colNames.length - 1, 0);
				startRow++;
			}
			// 设置标题
			for (int i = 0; i < colNames.length; i++) {
				aCell = new Label(i, startRow, colNames[i], format);
				ws.addCell(aCell);
			}

		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
		return aCell;
	}

	/**
	 * 生成表头和sheet Add By Wucf 20100105
	 * 
	 * @param aTableTitle
	 *            表头
	 * @param aSheetName
	 *            sheet名称
	 * @param colNames
	 * 列名 @
	 * @param colWidths
	 *            列宽数组
	 * @return
	 */
	private Label creatNewSheet2(String aTableTitle, String aSheetName,
			String[] colNames, Integer[] colWidths) {
		Label aCell = null;
		try {
			WritableFont fontStyle = new WritableFont(WritableFont.ARIAL, 10,
					WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
					jxl.format.Colour.BLACK); // 定义格式 字体 下划縿 斜体 粗体 颜色
			WritableCellFormat format = new WritableCellFormat(fontStyle);
			format.setAlignment(jxl.format.Alignment.CENTRE); // 设置对齐方式
			format.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			format.setBorder(Border.ALL, BorderLineStyle.THIN);
			ws = wwb.createSheet(aSheetName, sheetIndex);
			ws.getSettings().setOrientation(PageOrientation.LANDSCAPE);
			ws.getSettings().setPassword("wcf");
			// 生成表头
			if (!StringTool.isEmpty(aTableTitle)) {
				Label titleCell = new Label(0, startRow, aTableTitle, this
						.getWritableCellFormat());
				ws.addCell(titleCell);
				ws.mergeCells(0, 0, colNames.length - 1, 0);
				startRow++;
			}
			// 设置标题
			for (int i = 0; i < colNames.length; i++) {
				aCell = new Label(i, startRow, colNames[i], format);
				ws.setColumnView(i, colWidths[i]); // 设置列的宽度
				ws.addCell(aCell);
			}

		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
		return aCell;
	}

	/**
	 * 定义头部格式样式专用方法
	 * 
	 * @return
	 */
	public WritableCellFormat getWritableCellFormat() {
		WritableCellFormat format = new WritableCellFormat(this
				.getWritableFont()); // 单元格定䷿
		try {
			format = new WritableCellFormat(this.getWritableFont()); // 单元格定䷿
			format.setAlignment(jxl.format.Alignment.CENTRE); // 设置对齐方式
			format.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			// format.setBorder(Border.ALL, BorderLineStyle.THIN);
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 设置对齐方式
		return format;
	}

	/**
	 * 定义头部字体样式专用方法
	 * 
	 * @return
	 */
	public WritableFont getWritableFont() {
		WritableFont fontStyle = new WritableFont(WritableFont.ARIAL, 15,
				WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
				jxl.format.Colour.BLACK); // 定义格式 字体 下划縿 斜体 粗体 颜色
		return fontStyle;
	}

	/**
	 * 生成表头和sheet
	 * 
	 * @param aTableTitle
	 *            表头
	 * @param aSheetName
	 *            sheet名称
	 * @param colNames
	 *            列名
	 * @return
	 */
	private Label creatNewSheet(String aTableTitle, String aSheetName,
			String[] colNames) {
		Label aCell = null;
		try {
			ws = wwb.createSheet(aSheetName, sheetIndex);
			// 生成表头
			if (!StringTool.isEmpty(aTableTitle)) {
				Label titleCell = new Label(0, startRow, aTableTitle);
				ws.addCell(titleCell);
				startRow++;
			}
			// 设置标题
			for (int i = 0; i < colNames.length; i++) {
				aCell = new Label(i, startRow, colNames[i]);
				ws.addCell(aCell);
			}

		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
		return aCell;
	}

	/**
	 * 关闭已经写完的xls文件
	 * 
	 * @param wwb
	 *            传入打开的文乿
	 * @param bos
	 *            输出泿
	 * @return 是否成功关闭
	 * 
	 * 
	 */
	public void closeFile() {
		try {
			// 将文件写入硬癿
			if (wwb != null) {
				wwb.write();
				wwb.close();
			}
			if (bos != null) {
				bos.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 写入xls文件
	 * 
	 * @param wwb
	 *            xls文件生成写入对象
	 * @param aSheetName
	 *            sheet县
	 * @param aTableTitle
	 *            表名
	 * @param colNames
	 *            标题集合
	 * @param rowValues
	 *            内容集合
	 * @param colCount
	 *            多少条记录分䶿个sheet
	 * @return true:生成成功 false:生成失败
	 */
	public boolean write1(String aTableTitle, String aSheetName,
			String[] colNames, List<String[]> rowValues, int colCount) {
		boolean result = false;
		Label aCell = null;
		try {
			/*
			 * 将数据写入文乿
			 */
			WritableFont fontStyle = new WritableFont(WritableFont.ARIAL, 10,
					WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,
					jxl.format.Colour.BLACK); // 定义格式 字体 下划縿 斜体 粗体 颜色
			WritableCellFormat format = new WritableCellFormat(fontStyle);
			format.setAlignment(jxl.format.Alignment.CENTRE); // 设置对齐方式
			format.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			format.setBorder(Border.ALL, BorderLineStyle.THIN);
			if (row == 0) {
				// modify by litian 2011-08-23 begin
				if (sheetIndex == 0) {
				} else {
					for (int i = 0; i < sheetList.size(); i++) {
						if (aSheetName.equals(sheetList.get(i))) {
							aTableTitle = aSheetName + sheetIndex;
							break;
						}
					}
				}
				aCell = creatNewSheet1(aTableTitle, aSheetName, colNames);
				sheetList.add(aSheetName);
				// modify by litian 2011-08-23 end
			}
			if (rowValues != null && rowValues.size() > 0) {
				// 插入内容
				if (row == 0 || row % colCount == 0) {
					row = startRow + 1 + row;
				}
				for (String[] aRow : rowValues) {
					if (row > colCount + 1) {
						sheetIndex++;
						startRow = 0;
						if (sheetIndex == 0) {
							aCell = creatNewSheet1(aTableTitle, aSheetName,
									colNames);
						} else {

							aCell = creatNewSheet1(aTableTitle, aSheetName
									+ sheetIndex, colNames);
						}
						row = startRow + 1;
					}
					for (int i = 0; i < aRow.length; i++) {
						aCell = new Label(i, row, aRow[i], format);
						ws.addCell(aCell);
					}
					row++;
				}
			}
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		startRow = 0;
		return result;
	}

	/**
	 * 写入xls文件 Add By Wucf 20100105
	 * 
	 * @param wwb
	 *            xls文件生成写入对象
	 * @param aSheetName
	 *            sheet县
	 * @param aTableTitle
	 *            表名
	 * @param colNames
	 *            标题集合
	 * @param rowValues
	 *            内容集合
	 * @param colCount
	 *            多少条记录分䶿个sheet
	 * @param colWidths
	 *            列宽数组
	 * @return true:生成成功 false:生成失败
	 */
	public boolean write2(String aTableTitle, String aSheetName,
			String[] colNames, List<String[]> rowValues, int colCount,
			Integer[] colWidths) {
		boolean result = false;
		Label aCell = null;
		try {
			/*
			 * 将数据写入文乿
			 */
			WritableFont fontStyle = new WritableFont(WritableFont.ARIAL, 10,
					WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,
					jxl.format.Colour.BLACK); // 定义格式 字体 下划縿 斜体 粗体 颜色
			WritableCellFormat format = new WritableCellFormat(fontStyle);
			format.setAlignment(jxl.format.Alignment.CENTRE); // 设置对齐方式
			format.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			format.setBorder(Border.ALL, BorderLineStyle.THIN);
			if (row == 0) {
				if (sheetIndex == 0) {
					aCell = creatNewSheet2(aTableTitle, aSheetName, colNames,
							colWidths);
				} else {
					aCell = creatNewSheet2(aTableTitle,
							aSheetName + sheetIndex, colNames, colWidths);
				}
			}
			if (rowValues != null && rowValues.size() > 0) {
				// 插入内容
				if (row == 0 || row % colCount == 0) {
					row = startRow + 1 + row;
				}
				for (String[] aRow : rowValues) {
					if (row > colCount + 1) {
						sheetIndex++;
						startRow = 0;
						if (sheetIndex == 0) {
							aCell = creatNewSheet2(aTableTitle, aSheetName,
									colNames, colWidths);
						} else {
							aCell = creatNewSheet2(aTableTitle, aSheetName
									+ sheetIndex, colNames, colWidths);
						}
						row = startRow + 1;
					}
					for (int i = 0; i < aRow.length; i++) {
						aCell = new Label(i, row, aRow[i], format);
						ws.addCell(aCell);
					}
					row++;
				}
			}
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		startRow = 0;
		return result;
	}

	/**
	 * 写入xls文件
	 * 
	 * @param wwb
	 *            xls文件生成写入对象
	 * @param aSheetName
	 *            sheet县
	 * @param aTableTitle
	 *            表名
	 * @param colNames
	 *            标题集合
	 * @param rowValues
	 *            内容集合
	 * @param colCount
	 *            多少条记录分䶿个sheet
	 * @return true:生成成功 false:生成失败
	 */
	public boolean write(String aTableTitle, String aSheetName,
			String[] colNames, List<String[]> rowValues, int colCount) {
		boolean result = false;
		Label aCell = null;
		try {
			/*
			 * 将数据写入文乿
			 */

			if (row == 0) {
				aCell = creatNewSheet(aTableTitle, aSheetName + sheetIndex,
						colNames);
			}
			if (rowValues != null && rowValues.size() > 0) {
				// 插入内容
				if (row == 0 || row % colCount == 0) {
					row = startRow + 1 + row;
				}
				for (String[] aRow : rowValues) {
					if (row > colCount) {
						sheetIndex++;
						startRow = 0;
						aCell = creatNewSheet(aTableTitle, aSheetName
								+ sheetIndex, colNames);
						row = startRow + 1;
					}
					for (int i = 0; i < aRow.length; i++) {
						aCell = new Label(i, row, aRow[i]);
						ws.addCell(aCell);
					}
					row++;
				}
			}
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		startRow = 0;
		return result;
	}

	/**
	 * 生成指定内容的excel文件<br>
	 * 可以指定庿始行擿<br>
	 * TODO : 目前还不支持单元格格式的编辑
	 * 
	 * @param path
	 *            文件夹路弿
	 * @param filename
	 *            文件县
	 * @param aSheetName
	 *            sheet县
	 * @param aTableTitle
	 *            表名
	 * @param colNames
	 *            标题集合
	 * @param rowValues
	 *            内容集合
	 * @return true:生成成功 false:生成失败
	 */
	public static boolean write(String path, String filename,
			String aSheetName, String aTableTitle, String[] colNames,
			List<String[]> rowValues) {

		// 初始势
		jxl.write.WritableWorkbook wwb = null;
		jxl.write.WritableSheet ws = null;
		BufferedOutputStream bos = null;
		boolean result = false;

		if (!StringTool.isEmpty(path) && !StringTool.isEmpty(filename)) {
			// 生成文件
			try {
				// 文件路径判断
				File dir = new File(path);
				if (dir != null && !dir.exists()) {
					dir.mkdirs();
				}

				// 合成真实的文件名
				String file = "";
				if (path.endsWith(System.getProperty("file.separator"))) {
					file = path + filename;
				} else {
					file = path + System.getProperty("file.separator")
							+ filename;
				}

				// 生成文件
				bos = new BufferedOutputStream(new FileOutputStream(file));
				wwb = Workbook.createWorkbook(bos);

				// sheet县
				ws = wwb.createSheet(aSheetName, sheetIndex);

				// 报表表名
				if (!StringTool.isEmpty(aTableTitle)) {
					Label titleCell = new Label(0, startRow, aTableTitle);
					ws.addCell(titleCell);
					startRow++;
				}

				/*
				 * 将数据写入文乿
				 */
				if (colNames != null && colNames.length > 0) {
					Label aCell = null;
					// 设置标题
					for (int i = 0; i < colNames.length; i++) {
						aCell = new Label(i, startRow, colNames[i]);
						ws.addCell(aCell);
					}

					if (rowValues != null && rowValues.size() > 0) {
						// 插入内容
						int row = startRow + 1;
						for (String[] aRow : rowValues) {
							for (int i = 0; i < aRow.length; i++) {
								aCell = new Label(i, row, aRow[i]);
								ws.addCell(aCell);
							}
							row++;
						}
					}

					// 将文件写入硬癿
					wwb.write();
					result = true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (wwb != null) {
						wwb.close();
					}
					if (bos != null) {
						bos.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		// 还原startrow
		startRow = 0;
		return result;

	}

	/**
	 * TODO 读取xls文档
	 */
	public static void read() {

	}

	/**
	 * 读取excel文件中某䶿行的数据<br>
	 * 参数中的整数，下标从1庿奿
	 * 
	 * @param srcFile
	 *            源文乿
	 * @param row
	 *            懿要读取的行数
	 * @param fromIndex
	 *            庿始列擿
	 * @param toIndex
	 *            结束列数
	 * @param separator
	 *            单元格㿼的分隔窿
	 * @return 第row行中，从fromIndex到toIndex列单元格的㿼的合并
	 */
	public static String readRow(File srcFile, int row, int fromIndex,
			int toIndex, String separator) {
		// 初始势
		InputStream is = null;
		Workbook wb = null;
		Sheet st = null;
		StringBuilder sb = new StringBuilder();

		// 读取文件
		try {
			is = new FileInputStream(srcFile);
			wb = Workbook.getWorkbook(is);
			// 如果不指定sheet名，则读取第䶿个sheet
			if (StringTool.isEmpty(sheetName)) {
				st = wb.getSheet(sheetIndex);
			} else {
				st = wb.getSheet(sheetName);
			}

			// 读取数据
			for (int i = fromIndex - 1; i < toIndex; i++) {
				try {
					sb.append(StringTool.trim(st.getCell(i, row - 1)
							.getContents()));
				} catch (Exception e) {
					// do nothing
				}
				sb.append(separator);
			}
		} catch (Exception e) {
			// 读取出错
			e.printStackTrace();
			sb = new StringBuilder();
		} finally {
			try {
				if (wb != null) {
					wb.close();
				}
				if (is != null) {
					is.close();
				}
			} catch (Exception e) {
				// do nothing
			}
		}
		return sb.toString();
	}

	/**
	 * 读取excel文件中某䶿行的数据<br>
	 * 参数中的整数，下标从1庿奿
	 * 
	 * @param fileName
	 *            源文件名，含路径
	 * @param row
	 *            懿要读取的行数
	 * @param fromIndex
	 *            庿始列擿
	 * @param toIndex
	 *            结束列数
	 * @param separator
	 *            单元格㿼的分隔窿
	 * @return 第row行中，从fromIndex到toIndex列单元格的㿼的合并
	 */
	public static String readRow(String fileName, int row, int fromIndex,
			int toIndex, String separator) {
		File srcFile = new File(fileName);
		return readRow(srcFile, row, fromIndex, toIndex, separator);
	}

	/**
	 * 读取excel文件中某䶿列的数据<br>
	 * 参数中的整数，下标从1庿奿
	 * 
	 * @param srcFile
	 *            源文乿
	 * @param colnum
	 *            懿要读取的列数
	 * @param fromIndex
	 *            庿始行擿
	 * @param toIndex
	 *            结束行数
	 * @param separator
	 *            单元格㿼的分隔窿
	 * @return 第column列中，从fromIndex到toIndex行单元格的㿼的合并
	 */
	public static String readColumn(File srcFile, int colnum, int fromIndex,
			int toIndex, String separator) {
		// 初始势
		InputStream is = null;
		Workbook wb = null;
		Sheet st = null;
		StringBuilder sb = new StringBuilder();

		// 读取文件
		try {
			is = new FileInputStream(srcFile);
			wb = Workbook.getWorkbook(is);
			// 如果不指定sheet名，则读取第䶿个sheet
			if (StringTool.isEmpty(sheetName)) {
				st = wb.getSheet(sheetIndex);
			} else {
				st = wb.getSheet(sheetName);
			}

			// 读取数据
			for (int i = fromIndex - 1; i < toIndex; i++) {
				try {
					sb.append(StringTool.trim(st.getCell(colnum - 1, i)
							.getContents()));
				} catch (Exception e) {
					// do nothing
				}
				sb.append(separator);
			}
		} catch (Exception e) {
			// 读取出错
			e.printStackTrace();
			sb = new StringBuilder();
		} finally {
			try {
				if (wb != null) {
					wb.close();
				}
				if (is != null) {
					is.close();
				}
			} catch (Exception e) {
				// do nothing
			}
		}
		return sb.toString();
	}

	/**
	 * 读取excel文件中某䶿列的数据<br>
	 * 参数中的整数，下标从1庿奿
	 * 
	 * @param fileName
	 *            源文件名，含路径
	 * @param column
	 *            懿要读取的列数
	 * @param fromIndex
	 *            庿始行擿
	 * @param toIndex
	 *            结束行数
	 * @param separator
	 *            单元格㿼的分隔窿
	 * @return 第column列中，从fromIndex到toIndex行单元格的㿼的合并
	 */
	public static String readColumn(String fileName, int column, int fromIndex,
			int toIndex, String separator) {
		File srcFile = new File(fileName);
		return readRow(srcFile, column, fromIndex, toIndex, separator);
	}

	/**
	 * 读取指定列的指定行内嬿
	 * 
	 * @param srcFile
	 * @param column
	 * @param fromIndex
	 * @param toIndex
	 * @return
	 */
	public static String[] readColumn(File srcFile, int column, int fromIndex,
			int toIndex) {
		// 初始势
		InputStream is = null;
		Workbook wb = null;
		Sheet st = null;
		String[] result = new String[toIndex - fromIndex + 1];

		// 读取文件
		try {
			is = new FileInputStream(srcFile);
			wb = Workbook.getWorkbook(is);
			// 如果不指定sheet名，则读取第䶿个sheet
			if (StringTool.isEmpty(sheetName)) {
				st = wb.getSheet(sheetIndex);
			} else {
				st = wb.getSheet(sheetName);
			}

			// 读取数据
			int index = 0;
			for (int i = fromIndex - 1; i < toIndex; i++) {
				String cellContent = "";
				try {
					cellContent = StringTool.trim(st.getCell(column - 1, i)
							.getContents());
				} catch (Exception e) {
					// do nothing
				}

				result[index++] = cellContent;
			}
		} catch (Exception e) {
			// 读取出错
			e.printStackTrace();
		} finally {
			try {
				if (wb != null) {
					wb.close();
				}
				if (is != null) {
					is.close();
				}
			} catch (Exception e) {
				// do nothing
			}
		}

		return result;
	}

	public static void setBorder(String border) {
		XLS.border = border;
	}

	public static void setColWidth(float colWidth) {
		colWidth = colWidth < 0 ? 0 : colWidth;
		XLS.colWidth = colWidth;
	}

	public static void setRowHeight(float rowHeight) {
		rowHeight = rowHeight < 0 ? 0 : rowHeight;
		XLS.rowHeight = rowHeight;
	}

	public static void setStartRow(int startRow) {
		startRow = startRow < 0 ? 0 : startRow;
		XLS.startRow = startRow;
	}

	public static void setTableTitle(String tableTitle) {
		XLS.tableTitle = tableTitle;
	}

	public static int getSheetIndex() {
		return sheetIndex;
	}

	public static void setSheetIndex(int sheetIndex) {
		XLS.sheetIndex = sheetIndex;
	}

	public static String getBorder() {
		return border;
	}

	public static float getColWidth() {
		return colWidth;
	}

	public static float getRowHeight() {
		return rowHeight;
	}

	public static int getStartRow() {
		return startRow;
	}

	public static String getTableTitle() {
		return tableTitle;
	}

	public static String getSheetName() {
		return sheetName;
	}

	public static void setSheetName(String sheetName) {
		XLS.sheetName = sheetName;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

}
