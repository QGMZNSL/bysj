package com.sincinfo.zxks.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * 读写ZIP文档
 * 
 * @author litian
 * 
 */
public class ReduceToZIP {
    /**
     * 
     * 根据传入的文件路径，压缩成zip文档，返回为文件的字节数组
     * 
     * @param filePaths
     * @return
     */
    public static byte[] zip(List<String> filePaths) {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        ZipOutputStream z = new ZipOutputStream(bo);
        File tempFile = null;
        FileInputStream fin = null;
        byte[] b = null;
        for (int i = 0; i < filePaths.size(); i++) {
            try {
                tempFile = new File(filePaths.get(i));
                fin = new FileInputStream(tempFile);
                b = new byte[fin.available()];
                fin.read(b);
                z.putNextEntry(new ZipEntry(tempFile.getName()));
                z.write(b, 0, b.length);
            } catch (Exception e) {
            } finally {
            	if ( fin != null) {
            		try {
						fin.close();
					} catch (IOException e) {
					}
            	}
            }
        }
        try {
            z.closeEntry();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                z.close();
            } catch (IOException e) {
                // TODO 异常处理
                e.printStackTrace();
            }
        }
        return bo.toByteArray();
    }

    /**
     * 读取zip中的某个文件，返回该文件内容的字符流
     * 
     * @param zipFile
     * @param entryName
     * @return
     */
    public static ByteArrayOutputStream readZipEntry(ZipFile zipFile,
            String entryName) {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        try {
            // 读取到输入流
            ZipEntry dataXML = zipFile.getEntry(entryName);
            InputStream input = zipFile.getInputStream(dataXML);

            // 处理字符流
            int i = 0;
            while ((i = input.read()) != -1) {
                byteOut.write(i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteOut;
    }

    /**
     * 读取zip中的某个文件，返回该文件内容的字符流
     * 
     * @param filePath
     * @param entryName
     * @return
     */
    public static ByteArrayOutputStream readZipEntry(String filePath,
            String entryName) {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        try {
            // 读取到输入流
            ZipFile zipFile = new ZipFile(filePath);
            ZipEntry dataXML = zipFile.getEntry(entryName);
            InputStream input = zipFile.getInputStream(dataXML);

            // 处理字符流
            int i = 0;
            while ((i = input.read()) != -1) {
                byteOut.write(i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteOut;
    }

    /**
     * 读取zip中的某个文件，返回该文件内容的字符串
     * 
     * @param zipFile
     * @param entryName
     * @return
     */
    public static String readZipEntryContent(ZipFile zipFile, String entryName) {
        return readZipEntry(zipFile, entryName).toString();
    }

    /**
     * 读取zip中的某个文件，返回该文件内容的字符串
     * 
     * @param filePath
     * @param entryName
     * @return
     */
    public static String readZipEntryContent(String filePath, String entryName) {
        return readZipEntry(filePath, entryName).toString();
    }
    
    /**
     * 将zip文件解压到指定的目录下
     * 
     * @param zipFilePath 压缩包文件的全路径
     * @param destDir 解压目的目录的路径名
     * @return
     */
    public static boolean unZipToDestDir( String zipFilePath, String destDir ) {
        int BUFFER = 2048;
        boolean flag = false;
        BufferedOutputStream dest = null;
        FileInputStream fis = null;
        ZipInputStream zis = null;

        try {
            File zipFile = new File( zipFilePath);
            if ( !zipFile.exists()) {
                return flag;
            }
            
            File destDirFile = new File( destDir);
            if ( !destDirFile.exists()) {
                destDirFile.mkdirs();
            }
            
            fis = new FileInputStream(zipFilePath);
            zis = new ZipInputStream(
                    new BufferedInputStream(fis));
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
//                System.out.println("Extracting: " + entry);
                int count;
                byte data[] = new byte[BUFFER];
                // write the files to the disk
                FileOutputStream fos = new FileOutputStream(destDir + entry.getName());
                dest = new BufferedOutputStream(fos, BUFFER);
                while ((count = zis.read(data, 0, BUFFER)) != -1) {
                    dest.write(data, 0, count);
                }
                dest.flush();
            }
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                dest.close();
                zis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        return flag;
    }

    // public static void main(String[] args) {
    // String path = "C:/paper.zip";
    // ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
    // try {
    // // 读取到输入流
    // ZipFile zipFile = new ZipFile(path);
    // ZipEntry dataXML = zipFile.getEntry("extract.txt");
    // InputStream input = zipFile.getInputStream(dataXML);
    //
    // // 处理字符流
    // int i = 0;
    // while ((i = input.read()) != -1) {
    // byteOut.write(i );
    // }
    // } catch (IOException e) {
    // e.printStackTrace();
    // }
    // System.out.println(byteOut.toString());
    // }
}
