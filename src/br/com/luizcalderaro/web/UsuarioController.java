package br.com.luizcalderaro.web;

import br.com.luizcalderaro.beans.UserBean;
import br.com.luizcalderaro.model.UserDAO;

public class UsuarioController extends AbstractController
{
	private UserBean user = new UserBean();
	
	public void executaTransacao(UserBean info, int operacao) 
	{
		try
		{
			UserDAO dao = new UserDAO();
			dao.executaTransacao(info, operacao);
			
			user = new UserBean();
			
		}catch(Exception erro)
		{
			setMessage("formDados", "Erro na Operação");
		}
	}

	public String cadastrar()
	{
		this.executaTransacao(user, 1);
		return null;
	}

	public String alterar()
	{
		this.executaTransacao(user, 2);
		return null;
	}
	
	public String excluir()
	{
		this.executaTransacao(user, 3);
		return null;
	}

	public UserBean getUser() 
	{
		return user;
	}

	public void setUser(UserBean user) 
	{
		this.user = user;
	}
}