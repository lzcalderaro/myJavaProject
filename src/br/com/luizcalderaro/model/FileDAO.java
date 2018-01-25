package br.com.luizcalderaro.model;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import br.com.luizcalderaro.beans.FileBean;

public class FileDAO extends HibernateDAO
{
	private Query consulta = null;

	public FileBean consultaPorCodigo(Integer codigo)
	{
		try
		{
			sessao = HibernateUtil.buildSessionFactory().openSession();
			String query = "FROM FileBean F WHERE F.id = :f";
			consulta = sessao.createQuery(query);
			consulta.setInteger("f", codigo);
			return (FileBean) consulta.uniqueResult();

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