package br.com.luizcalderaro.utils;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class EmailSender
{
	public void sender(String name,String email_dest, String container) throws EmailException
	{
		 SimpleEmail email = new SimpleEmail();

		 System.out.println("alterando hostname...");

		 email.setHostName("smtp.gmail.com");

		 email.setSmtpPort(465);

		 email.addTo(email_dest, name);

		 email.setFrom("programadorallstar@gmail.com", "Luiz Calderaro");

		 email.setSubject("Bem vindo(a) ao UpToShare");

		 email.setMsg(container);

		 System.out.println("autenticando...");
		 email.setSSL(true);
		 email.setAuthentication("programadorallstar", "ViolaoManeiro01");
		 System.out.println("enviando...");
		 email.send();
		 System.out.println("Email enviado!");
	}
}
