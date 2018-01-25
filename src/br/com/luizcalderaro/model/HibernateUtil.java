package br.com.luizcalderaro.model;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateUtil
{
	public static final SessionFactory sessionFactory = buildSessionFactory();

	@SuppressWarnings("deprecation")
	public static SessionFactory buildSessionFactory()
	{
		try
		{
			@SuppressWarnings("deprecation")
			AnnotationConfiguration cfg = new AnnotationConfiguration();                   
			cfg.configure("hibernate.cfg.xml");
			return cfg.buildSessionFactory();
		}
		catch(Throwable e)
		{
			System.out.println("Erro ao abrir conexão! Erro: " + e);
			throw new ExceptionInInitializerError();
		}
	}
	
	public static SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}
}