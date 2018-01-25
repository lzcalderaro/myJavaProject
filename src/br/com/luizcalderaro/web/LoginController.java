package br.com.luizcalderaro.web;

import br.com.luizcalderaro.beans.UserBean;
import br.com.luizcalderaro.model.UserDAO;
import br.com.luizcalderaro.utils.EcriptedMD5;

public class LoginController extends AbstractController 
{
	private UserBean user = new UserBean();
	private UsuarioController userControl = new UsuarioController();
	private EcriptedMD5 md5 = new EcriptedMD5();
	private String saveSenha = null;
	
	public String login()
	{
		
		UserDAO dao = new UserDAO();
		
		user.setSenha(md5.transform(user.getSenha()));
		
		user = dao.login(user);
		
		if (user != null)
		{
			this.setSessionAttribute("usuarioLogado", user);
			return "upload";
		}
		else 
		{
			this.setMessage("formDados", "Login e/ou Senha Inválidos!");
			return "login";
		}
	}
	
	public String cadastrar()
	{
		this.saveSenha = user.getSenha();
		
		user.setSenha(md5.transform(user.getSenha()));
		userControl.executaTransacao(user, 1);
		
		user.setSenha(this.saveSenha);
		
		return login();
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
