package br.com.luizcalderaro.model;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import br.com.luizcalderaro.beans.*;

public class UserDAO extends HibernateDAO
{
	
	private Query consulta = null;
	
	public UserBean login(UserBean dados)
	{
		
		
		try
		{
			sessao = HibernateUtil.getSessionFactory().openSession();
			consulta = sessao.createQuery("from UserBean U where U.user = :l AND U.senha = :s");
			consulta.setString("l", dados.getUser());
			consulta.setString("s", dados.getSenha());
			return (UserBean) consulta.uniqueResult();
			
		}catch(HibernateException erro)
		{
			System.out.println("Erro ao executar transação: " + erro.getMessage());
			throw new HibernateException(erro);
			
		}finally
		{
			try{sessao.close();}
			catch(Throwable erro){System.out.println("Erro Ao Fechar a Sessao: " + erro);}
		}
	}
	
	public UserBean consultaPorCodigo(Integer codigo)
	{
		try
		{
			sessao = HibernateUtil.buildSessionFactory().openSession();
			String query = "from UserBean B where B.id = :b";
			consulta = sessao.createQuery(query);
			consulta.setInteger("b", codigo);
			return (UserBean) consulta.uniqueResult();
		}catch(HibernateException erro)
		{
			System.out.println("Erro ao executar transação: " + erro.getMessage());
			throw new HibernateException(erro);
		}finally
		{
			try{sessao.close();}
			catch(Throwable erro){System.out.println("Erro Ao Fechar a Sessao: " + erro);}
		}
	}
}