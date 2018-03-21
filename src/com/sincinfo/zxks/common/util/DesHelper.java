package com.sincinfo.zxks.common.util;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * DES加密解密助手
 * @author sc
 *
 */
public class DesHelper {
	public String key;
	public String iv;
	Cipher encrypter;
	Cipher decrypter;
	
	public DesHelper(String key, String iv) {
		super();
		this.key = key;
		this.iv = iv;
		try{
			encrypter = Cipher.getInstance("DES/CBC/PKCS5Padding");
			decrypter = Cipher.getInstance("DES/CBC/PKCS5Padding");
			DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			javax.crypto.SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
			IvParameterSpec _iv = new IvParameterSpec(iv.getBytes("UTF-8"));
			encrypter.init(Cipher.ENCRYPT_MODE, secretKey, _iv);
			decrypter.init(Cipher.DECRYPT_MODE, secretKey, _iv);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public synchronized byte[] encrypt(byte[] input){
		byte[] r=null;
		
		try {
			r = encrypter.doFinal(input);
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		
		return r;
	}
	
	public synchronized byte[] decrypt(byte[] input){
		byte[] r=null;
		
		try {
			r = decrypter.doFinal(input);
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		
		return r;
	}
}

