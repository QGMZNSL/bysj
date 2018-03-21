package com.sincinfo.zxks.test;

import java.io.IOException;

import org.bouncycastle.util.encoders.Base64Encoder;
import org.river.nbf.encrypt.CryptHelper;
import org.river.nbf.encrypt.DesHelper;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.sincinfo.zxks.common.util.MD5;

/**
 * @ClassName: DesMd5Test
 * @Description: 测试des与md5生成 <br>
 *               <br>
 * @author litian
 * @date Jan 17, 2012 10:01:32 AM<br>
 * 
 */
public class DesMd5Test {
	public static void testMd5() {
		String password = "myPassword";
		String md5Password = MD5.getMD5String(password);
		System.out.println("md5原始：" + password);
		System.out.println("md5摘要：" + md5Password);
	}

	public static void testDes() {
		String codePwd = "12345678";
		String password = "myDesTestString";
		DesHelper des = CryptHelper.getDesHelper(codePwd, codePwd);
		byte[] desPassword = des.encrypt(password.getBytes());
		byte[] unDesPassword = des.decrypt(desPassword);
		
		System.out.println("des原始：" + password);
		System.out.println("des加密：" + new String(desPassword));
		System.out.println("des解密：" + new String(unDesPassword));
	}

	public static void main(String[] args) {
		testMd5();
		System.out.println("------------------");
		testDes();
	}

}
