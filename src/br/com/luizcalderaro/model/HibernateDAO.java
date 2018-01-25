package br.com.luizcalderaro.model;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.mail.EmailException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.luizcalderaro.beans.UserBean;
import br.com.luizcalderaro.utils.CreateFolder;
import br.com.luizcalderaro.utils.EmailSender;

public class HibernateDAO 
{
	protected Session sessao = null;
	protected Transaction transacao = null;
	private EmailSender email = new EmailSender();
	private UserBean user = new UserBean();
	private CreateFolder create = new CreateFolder();
	private String way;
	private HttpSession session;
	
	public void executaTransacao(Object dados, int operacao) throws EmailException
	{
		try
		{
			sessao = HibernateUtil.getSessionFactory().openSession();
			transacao = sessao.beginTransaction();
			if (operacao == 1){
				sessao.save(dados);
				user = (UserBean) dados;
				String container = "Bem vindo(a) ao UpToShare, Temos toda a segurança necessaria. <br/> Att. <br/> Equipe UpToShare.";
				email.sender(user.getNome(), user.getEmail(), container);
				
				session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
				way = session.getServletContext().getRealPath("/users/");
				
				create.create(way + "\\" + user.getUser());
			}
			else if (operacao == 2) sessao.update(dados);
			else if (operacao == 3) sessao.delete(dados);
			else if(operacao == 4) sessao.save(dados);
			transacao.commit();
		}
		catch(HibernateException erro)
		{
			System.out.println("Erro ao executar transação: " + erro.getMessage());
			throw new HibernateException(erro);
		}
		finally
		{
			try{ sessao.close(); }
			catch(Throwable erro){ System.out.println("Erro ao fechar a sessao");}
		}
	}
}