package com.sincinfo.zxks.common.util;

import java.io.IOException;
import java.net.MalformedURLException;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Image;

public class PDFUtil {
	/**
	 * 获得一张PDF图片，需根据要求缩放实际图片
	 * 
	 * @param imagePath
	 *            图片物理地址
	 * @param x
	 *            x轴坐标，左起为0
	 * @param y
	 *            y轴坐标，下起为0
	 * @param width
	 *            图片要求宽度
	 * @return 返回Image对象或空
	 */
	public static Image getPDFImage(String imagePath, float x, float y,
			float width) {
		Image image = null;
		System.out.println("imagePath===========" + imagePath);
		try {
			image = Image.getInstance(imagePath);
			float w = image.getScaledWidth();
			float percent = 100 * width / w; // 缩放比例
			image.scalePercent(percent);			
			image.setAbsolutePosition(x, y);
		} catch (BadElementException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}
}
