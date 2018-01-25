package br.com.luizcalderaro.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class EncriptedFile
{
	public String encrypts(String args)
	{
		try{
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            byte[] mensagem = args.getBytes();
 
           byte[] chave = "chave de 16bytes".getBytes();
            System.out.println("Tamanho da chave: " + chave.length);
 
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(chave, "AES"));
            byte[] encrypted = cipher.doFinal(mensagem);
            
            return new String (encrypted);
 
		}catch (Exception e) {
	           e.printStackTrace();
	           return null;
	    }
	}
	
	public String decrypts(String args)
	{
		try{
			
			byte[] b = args.getBytes();
			
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
 
           byte[] chave = "chave de 16bytes".getBytes();
           System.out.println("Tamanho da chave: " + chave.length);
			
			cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(chave, "AES"));
            byte[] decrypted = cipher.doFinal(b);
 
            System.out.println(new String(decrypted));
            
            return new String(decrypted);
 
		}catch (Exception e) {
	           e.printStackTrace();
	           return null;
	    }
	}
}