package br.com.luizcalderaro.utils;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;

public class EcriptedMD5
{
	public String transform(String senha)
	{
		PasswordEncoder encoder = new Md5PasswordEncoder();
		senha = encoder.encodePassword(senha, null);
		return senha;
	}
}