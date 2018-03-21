/**
 * Copyright(c) 2012 西安云海信息技术有限责任公司 All Rights Reserved<br>
 * @Title: com.sincinfo.zxks.common.util.ImageCheckCodeCreator.java<br>
 * @Description: 用于生成图形验证码 <br>
 * <br>
 * @author litian<br>
 * @date Jan 17, 2012 10:34:53 AM 
 * @version V1.0   
 */
package com.sincinfo.zxks.common.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @ClassName: ImageCheckCodeCreator
 * @Description: 用于生成图形验证码 <br>
 *               <br>
 * @author litian
 * @date Jan 17, 2012 10:34:53 AM<br>
 * 
 */
public class ImageCheckCodeCreator {

	/**
	 * @Fields CCTYPE_NUMBER_ONLY : 仅数字
	 */
	public final static int CCTYPE_NUMBER_ONLY = 0;

	/**
	 * @Fields CCTYPE_LOWER_CHAR_ONLY : 仅小写字母
	 */
	public final static int CCTYPE_LOWER_CHAR_ONLY = 1;

	/**
	 * @Fields CCTYPE_UPPER_CHAR_ONLY : 仅大写字母
	 */
	public final static int CCTYPE_UPPER_CHAR_ONLY = 2;

	/**
	 * @Fields CCTYPE_NUMBER_UPPER_MIX : 数字与大写字母混合
	 */
	public final static int CCTYPE_NUMBER_UPPER_MIX = 3;

	/**
	 * @Fields IMAGE_TYPE : 图片文件格式
	 */
	private final static String IMAGE_TYPE = "JPEG";

	// 数字ASCII取值范围
	private int[] numberScope = { 48, 57 };
	// 小写字母ASCII取值范围
	private int[] lowerCharScope = { 97, 122 };
	// 大写字母ASCII取值范围
	private int[] upperCharScope = { 65, 90 };

	// 长度默认4
	private int codeLength = 4;
	// 类型，默认仅数字
	private int codeType = 0;
	// session中保存的key值，默认login_check_rand
	private String sessAttribName = "login_check_rand";

	// 边框宽度，默认60
	private int width = 60;
	// 边框高度，默认20
	private int height = 20;
	
	// 字体
	private String fontFamily = "Times New Roman";
	// 字体样式
	private int fontType = Font.PLAIN;
	// 字体大小
	private int fontSize = 18;

	// 用来与servlet交互
	private HttpServletRequest request = null;
	// 用来与servlet交互
	private HttpServletResponse response = null;

	/**
	 * 默认初始化
	 */
	public ImageCheckCodeCreator(HttpServletRequest request,
			HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.codeLength = 4;
		this.codeType = 0;
		this.sessAttribName = "login_check_rand";
	}

	/**
	 * 定制对象
	 * 
	 * @param checkCodeLength
	 *            长度
	 * @param checkCodeType
	 *            类型（包括 数字、小写字母、大写字母、数字与大写字母混合）
	 */
	public ImageCheckCodeCreator(HttpServletRequest request,
			HttpServletResponse response, int checkCodeLength, int checkCodeType) {
		this.request = request;
		this.response = response;
		this.codeLength = checkCodeLength;
		this.codeType = checkCodeType;
		this.sessAttribName = "login_check_rand";
		this.width = this.width / 4 * checkCodeLength;
	}

	/**
	 * 定制对象
	 * 
	 * @param checkCodeLength
	 *            长度
	 * @param checkCodeType
	 *            类型（包括 数字、小写字母、大写字母、数字与大写字母混合）
	 * @param sessAttribName
	 *            保存在session中，属性的key名
	 */
	public ImageCheckCodeCreator(HttpServletRequest request,
			HttpServletResponse response, int checkCodeLength,
			int checkCodeType, String sessAttribName) {
		this.request = request;
		this.response = response;
		this.codeLength = checkCodeLength;
		this.codeType = checkCodeType;
		this.sessAttribName = sessAttribName;
		this.width = this.width / 4 * checkCodeLength;
	}

	/**
	 * 给定范围获得随机颜色
	 */
	private Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	/**
	 * @return 根据对象属性，返回一个符合范围的随机值
	 */
	private String getRandStr() {
		String randChar = "0";
		Random rand = new Random();

		int ascii = 0;
		switch (this.codeType) {
		case CCTYPE_NUMBER_ONLY:
			// 仅数字
			ascii = rand.nextInt(255);
			ascii = ascii % 10 + numberScope[0];
			randChar = String.valueOf((char) ascii);
			break;
		case CCTYPE_LOWER_CHAR_ONLY:
			// 仅小写字母
			ascii = rand.nextInt(255);
			ascii = ascii % 26 + lowerCharScope[0];
			randChar = String.valueOf((char) ascii);
			break;
		case CCTYPE_UPPER_CHAR_ONLY:
			// 仅大写字母
			ascii = rand.nextInt(255);
			ascii = ascii % 26 + upperCharScope[0];
			randChar = String.valueOf((char) ascii);
			break;
		case CCTYPE_NUMBER_UPPER_MIX:
			// 大写字母与数字混合
			ascii = rand.nextInt(255);
			if (ascii >= 100) {
				// 随机数大于100，获取大写字母
				ascii = ascii % 26 + upperCharScope[0];
			} else {
				// 随机数小于100，获取数字
				ascii = ascii % 10 + numberScope[0];
			}
			randChar = String.valueOf((char) ascii);
			break;
		default:
			break;
		}

		return randChar;
	}
	
	/**
	 * 设置字体<br>
	 * 默认为"Times New Roman"
	 * @param fontFamily 字体名称
	 */
	public void setFontFamily( String fontFamily) {
		this.fontFamily = fontFamily;
	}
	
	/**
	 * 设置字体大小<br>
	 * 默认为18，如果设置字体大小，同时会改变外框的高度及宽度
	 * @param fontSize
	 */
	public void setFontSize( int fontSize) {
		this.fontSize = fontSize;
	}
	
	/**
	 * 设置字体样式<br>
	 * 默认Font.PLAIN，请用Font的常量来赋值
	 * @param fontType
	 */
	public void setFontType( int fontType) {
		this.fontType = fontType;
	}

	/**
	 * 生成图形验证码，注意，所有调整的字体等设置的代码必须在此方法执行之前完成。
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public ServletOutputStream createImageCheckCode() throws ServletException,
			IOException {
		response.setContentType("image/jpeg");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		HttpSession session = request.getSession();
		// 在内存中创建图象
		BufferedImage image = new BufferedImage(this.width, this.height,
				BufferedImage.TYPE_INT_RGB);
		// 获取图形上下文
		Graphics g = image.getGraphics();
		// 生成随机类
		Random random = new Random();
		// 设定背景色
		g.setColor(getRandColor(200, 250));
		g.fillRect(0, 0, this.width, this.height);
		// 设定字体
		g.setFont(new Font(fontFamily, fontType, fontSize));

		// 画边框
		g.setColor(Color.BLUE);
		g.drawRect(0, 0, this.width - 1, this.height - 1);

		// 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
		g.setColor(getRandColor(160, 200));
		for (int i = 0; i < 155; i++) {
			int x = random.nextInt(this.width);
			int y = random.nextInt(this.height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			g.drawLine(x, y, x + xl, y + yl);
		}
		// 取随机产生的认证码(4位数字)
		String sRand = "";
		for (int i = 0; i < this.codeLength; i++) {
			String rand = getRandStr();
			sRand += rand;

			// 将认证码显示到图象中
			g.setColor(new Color(20 + random.nextInt(110), 20 + random
					.nextInt(110), 20 + random.nextInt(110)));

			// 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
			g.drawString(rand, 13 * i + 6, 16);
		}
		// 将认证码存入SESSION
		session.setAttribute(sessAttribName, sRand);
		// 图象生效
		g.dispose();
		ServletOutputStream responseOutputStream = response.getOutputStream();
		// 输出图象到页面
		ImageIO.write(image, IMAGE_TYPE, responseOutputStream);

		return responseOutputStream;
	}

	 /**
	 * 设置边框大小 宽度默认60，高度默认20，正好容纳4个字符
	 * @param width 宽度 默认60
	 * @param height 高度 默认20
	 */
	public void setSize(int width, int height) {
		this.width = width;
		this.height = height;
	}

	/**
	 * 设置高度
	 * @param height 高度 默认20
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * 设置宽度
	 * @param width 宽度 默认60
	 */
	public void setWidth(int width) {
		this.width = width;
	}
}
