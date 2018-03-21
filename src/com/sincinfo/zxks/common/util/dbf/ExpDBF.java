package com.sincinfo.zxks.common.util.dbf;

import java.io.File;
import java.util.List;
import com.linuxense.javadbf.DBFException;
import com.linuxense.javadbf.DBFField;
import com.linuxense.javadbf.DBFWriter;

public class ExpDBF {
	public String exception = "";
	int fieldsNum;

	/**
	 * 对外公开类
	 * 
	 * @param datastr
	 *            实际数据
	 * @param colstr
	 *            列标题
	 * @param file
	 *            文件名
	 * @param lang
	 *            每列的数据长度
	 * @return
	 */
	public boolean export(List<String[]> datastr, String colstr, String file,
			int[] lang) {
		appendException(String.format("[%1$s,%2$s,%3$s,%4$s]\\r\\n",
				datastr.size(), colstr, file, lang.length));
		return write(write(datastr, colstr, lang, file));
	}

	/**
	 * 将数据写到DBFWriter中
	 * 
	 * @param datastr
	 *            实际数据
	 * @param colstr
	 *            列标题
	 * @param lang
	 *            每列的数据长度
	 * @param filename
	 *            文件名
	 * @return
	 */
	private DBFWriter write(List<String[]> datastr, String colstr, int[] lang,
			String filename) {
		// 定义一个写DBF的实例
		// 定义一个文件并创建该文件的父路径
		File f = new File(filename);
		if (f.exists()) {
			f.delete();
		}
		f.getParentFile().mkdirs();

		// 定义一个写DBF的实例
		DBFWriter writer = null;
		try {
			writer = new DBFWriter(f);// 同步模式
			writer.setFields(writecol(colstr, lang));// 将列字段加到DBFWriter中
			writer = writedata(datastr, writer);// 将行数据加到DBFWriter中
		} catch (DBFException e) {
			appendException(e);
			e.printStackTrace();
			return null;
		}
		return writer;
	}

	/**
	 * 封装列字段数据
	 * 
	 * @param cstr
	 * @param lang
	 * @return
	 */
	private DBFField[] writecol(String cstr, int[] lang) {
		// 将列名分开放到一个数组中
		String[] col = cstr.split("_");
		// 定义一个DBFField数组长度为列的个数
		DBFField fields[] = new DBFField[col.length];
		// 为每个列定义一个实例
		for (int i = 0; i < fields.length; i++) {
			fields[i] = new DBFField();// 定义实例
			fields[i].setName(col[i]);// 设置列名
			fields[i].setDataType(DBFField.FIELD_TYPE_C);// 列的数据类型C为String型
			fields[i].setFieldLength(lang[i]);// 设置每列内容数据的长度
		}

		fieldsNum = fields.length;
		return fields;
	}

	/**
	 * 封装行数据
	 * 
	 * @param datastr
	 * @param writer
	 * @return
	 */
	private DBFWriter writedata(List<String[]> datastr, DBFWriter writer) {
		String[] data = null;// 将每一行的数据放到一个数组中
		Object rowData[] = null;// 定义一行数据的实例
		for (int i = 0; i < datastr.size(); i++) {
			data = datastr.get(i);// 将定好的数据格式分开
			if (data.length < fieldsNum) {
				appendException(data[0]);
				continue;
			}
			rowData = new Object[data.length];// 实例化行一共有data.length个列
			// 循环行为每个列加数据
			for (int j = 0; j < rowData.length; j++) {
				rowData[j] = data[j];
			}
			try {
				writer.setCharactersetName("GBK");
				writer.addRecord(rowData);
			} catch (DBFException e) {
				appendException(e);
				e.printStackTrace();
			}
		}
		return writer;
	}

	/**
	 * 将内容写到流中
	 * 
	 * @param writer
	 * @return
	 */
	private boolean write(DBFWriter writer) {
		try {
			writer.write();
		} catch (Exception e) {
			appendException(e);
			return false;
		}
		return true;
	}

	public String getException() {
		return exception;
	}

	void appendException(Exception e) {
		appendException(e.getMessage());
	}

	void appendException(String e) {
		exception += e.replace('\'', ':') + "\\r\\n";
	}
}
