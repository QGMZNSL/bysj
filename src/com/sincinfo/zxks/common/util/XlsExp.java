/**
 * @Title: com.lt.test.XLSExp.java<br>
 * @Description: XLS文件的导出操作 <br>
 * <br>
 * @author litian<br>
 * @date Feb 21, 2012 2:39:30 PM 
 * @version V1.0   
 */
package com.sincinfo.zxks.common.util;

import java.util.List;

/**
 * @ClassName: XLSImp
 * @Description: 用于导出EXCEL文件，读取其中内容 <br>
 *               <br>
 * @author litian
 * @date Feb 21, 2012 2:17:46 PM<br>
 * 
 */
public class XlsExp {

	/**
	 * 导出excel表格至文件（只支持简单的单标题+表头+列表内容的单sheet表）
	 * 
	 * @param dirPath
	 *            文件夹路径
	 * @param fileName
	 *            文件名
	 * @param title
	 *            标题（caption部分）
	 * @param sheetName
	 *            sheet名
	 * @param rowNum
	 *            最大行数
	 * @param colFields
	 *            字段（th部分）
	 * @param infoList
	 *            内容（td部分）
	 * @return boolean 写入成功或失败
	 */
	public boolean expExcel(String dirPath, String fileName, String title,
			String sheetName, int rowNum, String[] colFields,
			List<String[]> infoList) throws Exception {
		// 写入到xls文件中
		XLS xls = new XLS();
		xls.creatOpenFile(dirPath, fileName);

		// 执行，并判断执行结果
		boolean expFlag = false;
		expFlag = xls.write1(title, sheetName, colFields, infoList, rowNum);
		xls.closeFile();

		return expFlag;
	}
}
