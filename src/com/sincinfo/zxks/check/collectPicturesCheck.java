package com.sincinfo.zxks.check;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import com.sincinfo.zxks.bean.imageInfo;
import com.sincinfo.zxks.common.db.DbUtil;
import com.sincinfo.zxks.common.util.StringTool;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class collectPicturesCheck {
	/**
     * 本地获取
     * */
    public imageInfo Imgchange(String filePath) throws IOException{
           File picture = new File(filePath);
           BufferedImage sourceImg = null;
           BufferedImage pre_image =ImageIO.read(new FileInputStream(picture)); 
           sourceImg = new BufferedImage(480,640, BufferedImage.TYPE_INT_RGB);
           sourceImg.getGraphics().drawImage(pre_image.getScaledInstance(480, 640, Image.SCALE_SMOOTH), 0, 0, null); 
           ImageIO.write(sourceImg, "JPG", new File(filePath));
           imageInfo ii= new imageInfo();
           ii.setHight(sourceImg.getWidth());
           ii.setHight(sourceImg.getHeight());
           return ii;

    }
    public void LoadImageToServer(String fileFullPath,int i) throws Exception {
	    FileOutputStream out = null;     //文件输出流
	    try {  //验证图片上传的格式是否正确
	     File f = new File(fileFullPath);
	        if (!f.isFile()) {
	        throw new Exception(" 不是图片文件!");
	    }
	     if (f != null && f.exists()) {          //这里的ImageIO属于java工厂类，在工厂类class里面，调用的System.gc()，频繁调用会造成dump，需要考虑优化
	        BufferedImage image = ImageIO.read(f); // 读入文件
	        if (image != null) {
	        BufferedImage tag = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);  //构造一个类型为预定义图像类型之一的 BufferedImage
	           tag.getGraphics().drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);                     //绘制所需要尺寸大小的图片
	        /*
	         * 进行图片的绘制
	         */
	        out = new FileOutputStream(fileFullPath);
	        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
	        JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(tag);
//	        System.out.println("i="+i);
//	        if(i==1){
//	        	param.setQuality(0.55f, true);
//	        }
//	        if(i==2){
//	        	param.setQuality(2.5f, true); 
//	        }
//	        if(i==3){
//	        	param.setQuality(0.4f, true); 
//	        }
//	        if(i==4){
//	        	param.setQuality(0.2f, true); 
//	        }
//	        param.setQuality(0.90f, true);       
	        param.setDensityUnit(1);                //像素尺寸单位.像素/英寸    
	        param.setXDensity(300);                  //水平分辨率      
	        param.setYDensity(300);                 //垂直分辨率
	        encoder.setJPEGEncodeParam(param);
	        encoder.encode(tag);
	      }
	     }
	     f = null;

	    } catch (Exception ex) {
	     ex.printStackTrace();
	    } finally {
	     out.close();
	     out = null;
	    }
}
    public int LoadImageToServer1(String fileFullPath,int i) throws Exception {
	    FileOutputStream out = null;     //文件输出流
	    try {  //验证图片上传的格式是否正确
	     File f = new File(fileFullPath);
	        if (!f.isFile()) {
	        throw new Exception(" 不是图片文件!");
	    }
	     if (f != null && f.exists()) {          //这里的ImageIO属于java工厂类，在工厂类class里面，调用的System.gc()，频繁调用会造成dump，需要考虑优化
	        BufferedImage image = ImageIO.read(f); // 读入文件
	        if (image != null) {
	        BufferedImage tag = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);  //构造一个类型为预定义图像类型之一的 BufferedImage
	           tag.getGraphics().drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);                     //绘制所需要尺寸大小的图片
	        /*
	         * 进行图片的绘制
	         */
	        out = new FileOutputStream(fileFullPath);
	        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
	        JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(tag);
	        System.out.println("i="+i);
	        if(i==1){
	        	param.setQuality(0.55f, true);
	        	i=0;
	        }
	        if(i==2){
	        	param.setQuality(0.95f, true); 
	        	i=0;
	        }
	        if(i==3){
	        	param.setQuality(0.25f, true);
	        	i=0;
	        }
//	        if(i==4){
//	        	param.setQuality(0.2f, true); 
//	        	i=0;
//	        }
//	        param.setQuality(0.90f, true);       
	        param.setDensityUnit(1);                //像素尺寸单位.像素/英寸    
	        param.setXDensity(300);                  //水平分辨率      
	        param.setYDensity(300);                 //垂直分辨率
	        encoder.setJPEGEncodeParam(param);
	        encoder.encode(tag);
	      }
	     }
	     f = null;

	    } catch (Exception ex) {
	     ex.printStackTrace();
	    } finally {
	     out.close();
	     out = null;
	    }
	    System.out.println("return i="+i);
	    return i;
}
}