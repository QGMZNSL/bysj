package com.sincinfo.zxks.common.util;

import java.io.FileInputStream;
import java.security.MessageDigest;

/**
 * 将一个字符串转换成MD5码
 * 
 */
public class MD5 {
	private static String hex_chr = "0123456789abcdef";

	private static String wordToHex(int num) {
		String str = "";
		for (int j = 0; j <= 3; j++)
			str = str + hex_chr.charAt((num >> (j * 8 + 4)) & 0x0F) + hex_chr.charAt((num >> (j * 8)) & 0x0F);
		return str;
	}

	/*
	 * 将一个字符串转换成符合MD5标准的数组
	 */
	private static int[] convertToArray(String str) {
		int nblk = ((str.length() + 8) >> 6) + 1;
		int[] blks = new int[nblk * 16];
		int i = 0;
		for (i = 0; i < nblk * 16; i++) {
			blks[i] = 0;
		}
		for (i = 0; i < str.length(); i++) {
			blks[i >> 2] |= str.charAt(i) << ((i % 4) * 8);
		}
		blks[i >> 2] |= 0x80 << ((i % 4) * 8);
		blks[nblk * 16 - 2] = str.length() * 8;
		return blks;
	}

	private static int addUnsigned(int x, int y) {
		return ((x & 0x7FFFFFFF) + (y & 0x7FFFFFFF)) ^ (x & 0x80000000) ^ (y & 0x80000000);
	}

	/*
	 * 将一个32位数字num左移cnt位，移出部分填充到num右边
	 */
	private static int rol(int num, int cnt) {
		return (num << cnt) | (num >>> (32 - cnt));
	}

	/*
	 * 基本转换
	 */
	private static int cmn(int q, int a, int b, int x, int s, int t) {
		return addUnsigned(rol(addUnsigned(addUnsigned(a, q), addUnsigned(x, t)), s), b);
	}

	/*
	 * f转换
	 */
	private static int md5Ff(int a, int b, int c, int d, int x, int s, int t) {
		return cmn((b & c) | ((~b) & d), a, b, x, s, t);
	}

	/*
	 * g转换
	 */
	private static int md5Gg(int a, int b, int c, int d, int x, int s, int t) {
		return cmn((b & d) | (c & (~d)), a, b, x, s, t);
	}

	/*
	 * h转换
	 */
	private static int md5Hh(int a, int b, int c, int d, int x, int s, int t) {
		return cmn(b ^ c ^ d, a, b, x, s, t);
	}

	/*
	 * i转换
	 */
	private static int md5Ii(int a, int b, int c, int d, int x, int s, int t) {
		return cmn(c ^ (b | (~d)), a, b, x, s, t);
	}

	/**
	 * 
	 * 将输入字符串转换成MD5码<br>
	 * 应用场合：webservice调用，用于身份校验，与sqlserver交互
	 * 
	 * @param str
	 *            源字符串
	 * @return MD5码
	 */
	public static String getMD5String(String str) {
		// 初始化数据
		final int s11 = 7;
		final int s12 = 12;
		final int s13 = 17;
		final int s14 = 22;

		final int s21 = 5;
		final int s22 = 9;
		final int s23 = 14;
		final int s24 = 20;

		final int s31 = 4;
		final int s32 = 11;
		final int s33 = 16;
		final int s34 = 23;

		final int s41 = 6;
		final int s42 = 10;
		final int s43 = 15;
		final int s44 = 21;

		int a = 0x67452301;
		int b = 0xEFCDAB89;
		int c = 0x98BADCFE;
		int d = 0x10325476;

		// 转换成数组
		int[] x = convertToArray(str);

		for (int i = 0; i < x.length; i += 16) {
			int olda = a;
			int oldb = b;
			int oldc = c;
			int oldd = d;

			// f转换
			a = md5Ff(a, b, c, d, x[i + 0], s11, 0xD76AA478);
			d = md5Ff(d, a, b, c, x[i + 1], s12, 0xE8C7B756);
			c = md5Ff(c, d, a, b, x[i + 2], s13, 0x242070DB);
			b = md5Ff(b, c, d, a, x[i + 3], s14, 0xC1BDCEEE);
			a = md5Ff(a, b, c, d, x[i + 4], s11, 0xF57C0FAF);
			d = md5Ff(d, a, b, c, x[i + 5], s12, 0x4787C62A);
			c = md5Ff(c, d, a, b, x[i + 6], s13, 0xA8304613);
			b = md5Ff(b, c, d, a, x[i + 7], s14, 0xFD469501);
			a = md5Ff(a, b, c, d, x[i + 8], s11, 0x698098D8);
			d = md5Ff(d, a, b, c, x[i + 9], s12, 0x8B44F7AF);
			c = md5Ff(c, d, a, b, x[i + 10], s13, 0xFFFF5BB1);
			b = md5Ff(b, c, d, a, x[i + 11], s14, 0x895CD7BE);
			a = md5Ff(a, b, c, d, x[i + 12], s11, 0x6B901122);
			d = md5Ff(d, a, b, c, x[i + 13], s12, 0xFD987193);
			c = md5Ff(c, d, a, b, x[i + 14], s13, 0xA679438E);
			b = md5Ff(b, c, d, a, x[i + 15], s14, 0x49B40821);

			// g转换
			a = md5Gg(a, b, c, d, x[i + 1], s21, 0xF61E2562);
			d = md5Gg(d, a, b, c, x[i + 6], s22, 0xC040B340);
			c = md5Gg(c, d, a, b, x[i + 11], s23, 0x265E5A51);
			b = md5Gg(b, c, d, a, x[i + 0], s24, 0xE9B6C7AA);
			a = md5Gg(a, b, c, d, x[i + 5], s21, 0xD62F105D);
			d = md5Gg(d, a, b, c, x[i + 10], s22, 0x02441453);
			c = md5Gg(c, d, a, b, x[i + 15], s23, 0xD8A1E681);
			b = md5Gg(b, c, d, a, x[i + 4], s24, 0xE7D3FBC8);
			a = md5Gg(a, b, c, d, x[i + 9], s21, 0x21E1CDE6);
			d = md5Gg(d, a, b, c, x[i + 14], s22, 0xC33707D6);
			c = md5Gg(c, d, a, b, x[i + 3], s23, 0xF4D50D87);
			b = md5Gg(b, c, d, a, x[i + 8], s24, 0x455A14ED);
			a = md5Gg(a, b, c, d, x[i + 13], s21, 0xA9E3E905);
			d = md5Gg(d, a, b, c, x[i + 2], s22, 0xFCEFA3F8);
			c = md5Gg(c, d, a, b, x[i + 7], s23, 0x676F02D9);
			b = md5Gg(b, c, d, a, x[i + 12], s24, 0x8D2A4C8A);

			// h转换
			a = md5Hh(a, b, c, d, x[i + 5], s31, 0xFFFA3942);
			d = md5Hh(d, a, b, c, x[i + 8], s32, 0x8771F681);
			c = md5Hh(c, d, a, b, x[i + 11], s33, 0x6D9D6122);
			b = md5Hh(b, c, d, a, x[i + 14], s34, 0xFDE5380C);
			a = md5Hh(a, b, c, d, x[i + 1], s31, 0xA4BEEA44);
			d = md5Hh(d, a, b, c, x[i + 4], s32, 0x4BDECFA9);
			c = md5Hh(c, d, a, b, x[i + 7], s33, 0xF6BB4B60);
			b = md5Hh(b, c, d, a, x[i + 10], s34, 0xBEBFBC70);
			a = md5Hh(a, b, c, d, x[i + 13], s31, 0x289B7EC6);
			d = md5Hh(d, a, b, c, x[i + 0], s32, 0xEAA127FA);
			c = md5Hh(c, d, a, b, x[i + 3], s33, 0xD4EF3085);
			b = md5Hh(b, c, d, a, x[i + 6], s34, 0x04881D05);
			a = md5Hh(a, b, c, d, x[i + 9], s31, 0xD9D4D039);
			d = md5Hh(d, a, b, c, x[i + 12], s32, 0xE6DB99E5);
			c = md5Hh(c, d, a, b, x[i + 15], s33, 0x1FA27CF8);
			b = md5Hh(b, c, d, a, x[i + 2], s34, 0xC4AC5665);

			// i转换
			a = md5Ii(a, b, c, d, x[i + 0], s41, 0xF4292244);
			d = md5Ii(d, a, b, c, x[i + 7], s42, 0x432AFF97);
			c = md5Ii(c, d, a, b, x[i + 14], s43, 0xAB9423A7);
			b = md5Ii(b, c, d, a, x[i + 5], s44, 0xFC93A039);
			a = md5Ii(a, b, c, d, x[i + 12], s41, 0x655B59C3);
			d = md5Ii(d, a, b, c, x[i + 3], s42, 0x8F0CCC92);
			c = md5Ii(c, d, a, b, x[i + 10], s43, 0xFFEFF47D);
			b = md5Ii(b, c, d, a, x[i + 1], s44, 0x85845DD1);
			a = md5Ii(a, b, c, d, x[i + 8], s41, 0x6FA87E4F);
			d = md5Ii(d, a, b, c, x[i + 15], s42, 0xFE2CE6E0);
			c = md5Ii(c, d, a, b, x[i + 6], s43, 0xA3014314);
			b = md5Ii(b, c, d, a, x[i + 13], s44, 0x4E0811A1);
			a = md5Ii(a, b, c, d, x[i + 4], s41, 0xF7537E82);
			d = md5Ii(d, a, b, c, x[i + 11], s42, 0xBD3AF235);
			c = md5Ii(c, d, a, b, x[i + 2], s43, 0x2AD7D2BB);
			b = md5Ii(b, c, d, a, x[i + 9], s44, 0xEB86D391);

			// add转换
			a = addUnsigned(a, olda);
			b = addUnsigned(b, oldb);
			c = addUnsigned(c, oldc);
			d = addUnsigned(d, oldd);
		}

		// 返回一个16位的字符串
		return wordToHex(c) + wordToHex(a);
	}

	/************** 文件md5码生成 ***************/
	static char hexDigits[] = {	// 用来将字节转化成16进制表示的字符 add by litian 2010-12-16 转换为md5码用
		'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'
	};

	/**
	 * 获取指定的绝对路径中的纯文件名
	 * @param originFileName
	 * @return
	 */
	public static String getFileName(String originFileName){
		int p=originFileName.lastIndexOf("\\");
		if(p<0)
			p=originFileName.lastIndexOf("/");
		if(p<0)
			p=-1;
		
		return originFileName.substring(p+1);
	}
	
	/**
	 * 生成文件的md5
	 */
	public static String makeMd5File( String filePath) throws Exception{
		MessageDigest md5 = MessageDigest.getInstance("MD5"); 
		FileInputStream fis=new FileInputStream(filePath);
		byte[] data=new byte[fis.available()];
		fis.read(data);
		fis.close();

		md5.update(data);
		data = md5.digest();
			
		char str[] = new char[16 * 2];	// 每个字节用16进制表示，用2个字符
		int k = 0;
		for ( int i = 0; i < 16; i++) { // 从第一个字节开始对md5的每个字节转换成16进制字符串
			byte btemp = data[i];	// 取第i个字节
			str[k++] = hexDigits[btemp >>> 4 & 0xf];	// 取字节中高4位的数字转换
			str[k++] = hexDigits[btemp & 0xf];	// 取字节中低的数字转换
		}
		return new String(str);
	}
	
	public static void main(String[] args) {
	    System.out.println(MD5.getMD5String("123456"));
	    try {
			System.out.println(makeMd5File("g:\\AcctCtl.zip"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
