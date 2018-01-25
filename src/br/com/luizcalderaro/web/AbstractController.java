package br.com.luizcalderaro.web;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

public abstract class AbstractController
{
	public void setMessage(String target, String msn)
	{
		FacesContext fc = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage(msn);
		fc.addMessage(target, message);
	}

	public Object getSessionAttribute(String name)
	{
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession sessao = (HttpSession) fc.getExternalContext().getSession(false);
		return sessao.getAttribute(name);
	}

	public void setSessionAttribute(String name, Object value)
	{
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession sessao = (HttpSession) fc.getExternalContext().getSession(false);

		sessao.setAttribute(name, value);
	}

	public String getParameter(String param)
	{
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		return ec.getRequestParameterMap().get(param);
	}
}

