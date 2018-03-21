package com.sincinfo.zxks.common.util.dbf;

import com.linuxense.javadbf.*;

import java.util.*;
import java.io.*;
import java.text.*;

public class DbfUtil {
	/**
	 * 导出DBF文件
	 * 
	 * @param records
	 * @param names
	 * @param lengths
	 * @param cols
	 * @param filename
	 */
	public static void expertToDbfFile(List<String[]> records, String[] names,
			int[] lengths, int cols, String filename) {
		OutputStream fos = null;
		DBFWriter writer = null;
		try {
			fos = new FileOutputStream(filename);
			writer = new DBFWriter();
			// writer.setCharactersetName("GBK");
			DBFField[] dd = new DBFField[cols];
			for (int i = 0; i < cols; i++) {
				dd[i] = new DBFField();
				dd[i].setFieldLength(lengths[i]);
				dd[i].setName(names[i]);
				dd[i].setDataType(DBFField.FIELD_TYPE_C);
			}
			writer.setFields(dd);
			Object[] o = null;
			String fieldstr = "";
			for (int i = 0; i < records.size(); i++) {
				o = new Object[dd.length];
				for (int j = 0; j < dd.length; j++) {
					fieldstr = records.get(i)[j];
					o[j] = new String(
							(fieldstr == null ? " " : fieldstr).getBytes(),
							"ISO-8859-1");
				}
				writer.addRecord(o);
			}
			writer.write(fos);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 导入dbf文件中的数据到List中
	 * 
	 * @param filename
	 * @return
	 */
	public static List<String[]> importFromDbf(String filename) {
		List<String[]> r = new ArrayList<String[]>();
		InputStream inputStream = null;
		// 记录数
		int record_count = 0;
		// 字段数
		int field_count = 0;
		// 记录所有字段字节长度的和
		int record_len = 0;
		// 字段数组
		String[] rowObjects = null;
		// 是否有_NullFlags字段
		boolean isHaveNullFlages = false;
		try {

			return importFromDbf(new File(filename));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}

		return r;
	}

	/**
	 * 导入dbf文件中的数据到List中
	 * 
	 * @param filename
	 * @return
	 */
	public static List<String[]> importFromDbf(File file) {
		List<String[]> r = new ArrayList<String[]>();
		InputStream inputStream = null;
		DBFReader reader = null;
		// 记录数
		int record_count = 0;
		// 字段数
		int field_count = 0;
		// 记录所有字段字节长度的和
		int record_len = 0;
		// 字段数组
		String[] rowObjects = null;
		// 是否有_NullFlags字段
		boolean isHaveNullFlages = false;
		try {
			inputStream = new FileInputStream(file);
			reader = new DBFReader(inputStream);
			reader.setCharactersetName("GBK");
			// 记录数
			record_count = reader.getRecordCount();
			// 字段数
			field_count = reader.getFieldCount();
			rowObjects = null;
			Object[] os;
			while ((os = reader.nextRecord()) != null) {
				r.add(toStrings(os));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
				inputStream.close();
			} catch (IOException e) {
			}
		}

		return r;
	}

	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	static String[] toStrings(Object[] os) {
		String[] r = new String[os.length];
		for (int i = 0; i < os.length; i++) {
			if (os[i] == null) {
				r[i] = "";
			} else if (os[i].getClass() == Date.class) {
				r[i] = sdf.format((Date) os[i]);
			} else {
				r[i] = os[i].toString().trim();
			}
		}

		return r;
	}
}
