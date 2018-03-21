/**
 * Copyright(c) 2012 西安云海信息技术有限责任公司 All Rights Reserved<br>
 * @Title: com.sincinfo.zxks.common.util.FileManager.java<br>
 * @Description: 写入读取文件操作 <br>
 * <br>
 * @author litian<br>
 * @date Feb 2, 2012 3:37:12 PM 
 * @version V1.0   
 */
package com.sincinfo.zxks.common.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: FileManager
 * @Description: 写入读取文件操作 <br>
 *               <br>
 * @author litian
 * @date Feb 2, 2012 3:37:12 PM<br>
 * 
 */
public class FileManager { 

	/**
	 * 将字节数组写到指定文件中
	 * 
	 * @param dirPath
	 *            文件夹 String
	 * @param fileName
	 *            文件名 String
	 * @param content
	 *            文件内容 byte[]
	 * @return boolean 写出状态
	 */
	public static boolean writeToFile(String dirPath, String fileName,
			byte[] content) {
		boolean flag = false;

		// 判断文件夹
		File dir = new File(dirPath);
		if (!dir.exists()) {
			dir.mkdirs();
		}

		// 判断文件夹路径是否以分隔符结束
		if (dirPath.endsWith("/") || dirPath.endsWith("\\")) {
		} else {
			dirPath = dirPath + System.getProperty("file.separator");
		}

		// 判断文件名是否以分隔符开头
		fileName = fileName.replace("\\", "").replace("/", "");
		String fullPath = dirPath + fileName;
		File outFile = new File(fullPath);
		OutputStream output = null;
		try {
			if (outFile.exists()) {
				outFile.createNewFile();
			} else {
				outFile.delete();
				outFile.createNewFile();
			}

			// 写入文件
			output = new FileOutputStream(fullPath);
			output.write(content);
			output.flush();
			output.close();
			flag = true;
		} catch (Exception e) {
			new Log().error(FileManager.class, "写入失败！", e);
			flag = false;
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					new Log().error(FileManager.class, "关闭文件流失败！", e);
				}
			}
		}

		return flag;
	}

	/**
	 * 删除文件
	 * 
	 * @param files
	 * @return true:删除成功 false:删除失败
	 */
	public static boolean delFiles(String[] files) {
		// 将数组中的文件提取到list中
		List<String> fileList = new ArrayList<String>();
		if (files != null) {
			for (String file : files) {
				fileList.add(file);
			}
		}

		// 批量删除文件
		return delFiles(fileList);
	}

	/**
	 * 删除文件
	 * 
	 * @param files
	 * @return true:删除成功 false:删除失败
	 */
	public static boolean delFiles(List<String> files) {
		boolean result = true;

		try {
			File file = null;

			if (files != null) {
				// 逐个删除文件
				for (String fileName : files) {
					file = new File(fileName);
					if (file != null && file.exists()) {
						file.delete();
					}
				}
			}
		} catch (Exception e) {
			new Log().error(FileManager.class, "删除失败！", e);
			result = false;
		}
		return result;
	}

	/**
	 * 清除目录下的所有文件
	 * 
	 * @return
	 * 
	 * add by lt 2010-1-5
	 */
	public static boolean clearFile(String src) {
		boolean result = true;

		try {
			File file = new File(src);
			File[] files = file.listFiles();
			if (files != null) {
				for (int i = 0; i < files.length; i++) {
					if (files[i].isFile()) {
						files[i].delete();// 如果是文件，则先删除，
					} else {
						clearFile(files[i].getAbsolutePath());// 递归删除
					}
				}
			}
		} catch (Exception e) {
			new Log().error(FileManager.class, "删除失败！", e);
			result = false;
		}
		return result;
	}

	/**
	 * 清除文件夹
	 * 
	 * @param src
	 * 
	 * 
	 * add by lt 2010-1-5
	 */
	public static boolean clearDir(String src) {
		boolean result = true;

		try {
			File file = new File(src);
			File[] files = file.listFiles();
			for (int i = 0; i < files.length; i++) {
				if (files[i].isDirectory()) {
					files[i].delete();//
					if (files[i].exists()) {
						clearDir(files[i].getAbsolutePath());// 递归删除
					}
					files[i].delete();// 删除子目录后，再删除改目录
				}
			}
		} catch (Exception e) {
			new Log().error(FileManager.class, "删除失败！", e);
			result = false;
		}
		return result;
	}

	/**
	 * 写入文件
	 * 
	 * @param targetPath
	 *            写入路径
	 * @param fileName
	 *            写入文件名
	 * @param srcFile
	 *            源文件
	 * @return true：写入成功 false：写入失败
	 */
	public static boolean write(String targetPath, String fileName, File srcFile) {
		BufferedInputStream bis = null;
		FileOutputStream bos = null;
		boolean result = true;

		try {
			// 如果路径不存在，则创建
			File dir = new File(targetPath);
			if (!dir.exists()) {
				dir.mkdirs();
			}

			// 写入文件
			byte[] by = null;

			bis = new BufferedInputStream(new FileInputStream(srcFile));
			bos = new FileOutputStream(targetPath + fileName);
			by = new byte[4096];
			int n = 0;
			while (true) {
				n = bis.read(by);
				if (n == -1) {
					break;
				}
				bos.write(by, 0, n);
			}
		} catch (Exception e) {
			// 写入失败
			new Log().error(FileManager.class, "写入失败！", e);
			result = false;
		} finally {
			// 关闭流
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (bos != null) {
				try {
					bos.flush();
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return result;
	}

	/**
	 * 写入文件
	 * 
	 * @param targetPath
	 *            写入路径
	 * @param fileName
	 *            写入文件名
	 * @param srcFile
	 *            源文件
	 * @return true：写入成功 false：写入失败
	 */
	public static boolean write(String targetPath, String fileName,
			String srcFile) {

		File file = new File(srcFile);

		return write(targetPath, fileName, file);
	}

	/**
	 * 重命名文件 将文件名中的小写字母变成大写字母
	 * 
	 * @param path
	 * @return
	 * 
	 * 修改 by lt 2010-1-5
	 */
	public static boolean upcase(File path) {
		boolean result = true;

		try {
			// 文件路径是否为空
			if (path != null) {
				// 是一个目录,循环目录下的文件
				if (path.isDirectory()) {
					File[] files = path.listFiles();// 文件列表
					// 重命名每个文件
					for (File aFile : files) {
						// 此文件是否是一个标准文件
						if (aFile.isFile()) {
							toUpperCase(aFile);
						} else {
							upcase(aFile);
						}
					}
				} else {
					toUpperCase(path);
				}
			}
		} catch (Exception e) {
			// 重命名失败
			new Log().error(FileManager.class, "重命名失败！", e);
			result = false;
		}
		return result;
	}

	/**
	 * 重命名文件 将文件名中的小写字母变成大写字母
	 * 
	 * @param path
	 * 
	 * add by lt 2010-1-5
	 */
	private static void toUpperCase(File path) {
		String tempName = "";// 原文件名
		String destName = "";// 不包含后辍的原文件名
		String postfixName = "";// 文件的后缀
		String destPath = "";// 绝对路径
		String toFileName = "";// 改变之后的文件名
		File toFile = null;// 改名后的文件

		tempName = path.getName();
		destName = tempName.substring(0, tempName.lastIndexOf(".")) + "";
		postfixName = tempName.substring(tempName.lastIndexOf("."));
		destPath = path.getParent();

		toFileName = destPath + System.getProperty("file.separator")
				+ destName.toUpperCase() + postfixName;
		toFile = new File(toFileName);
		// 重命名文件
		path.renameTo(toFile);

	}

	/**
	 * 重命名文件
	 * 
	 * @param targetPath
	 *            目标文件
	 * @param srcFileName
	 *            文件重命名的名称
	 * 
	 * add by lt 2010-1-5
	 */
	public static boolean rename(String targetPath, String srcFileName) {
		boolean result = true;
		String destPath = "";// 绝对路径
		String toFileName = "";// 改变之后的文件名
		File toFile = null;// 改名后的文件
		try {
			File path = new File(targetPath);
			// 标准文件
			if (path.isFile()) {
				destPath = path.getParent();
				toFileName = destPath + System.getProperty("file.separator")
						+ srcFileName;
				toFile = new File(toFileName);
				// 重命名文件
				path.renameTo(toFile);
			}
		} catch (Exception e) {
			// 重命名失败
			new Log().error(FileManager.class, "重命名失败！", e);
			result = false;
		}
		return result;
	}

}
