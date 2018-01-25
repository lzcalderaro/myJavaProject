package br.com.luizcalderaro.web;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.model.StreamedContent;

import br.com.luizcalderaro.beans.FileBean;
import br.com.luizcalderaro.beans.UserBean;
import br.com.luizcalderaro.model.FileDAO;
import br.com.luizcalderaro.model.UserDAO;


public class DownloadController extends AbstractController
{
	private String url = null;
	private UserBean user = new UserBean();
	private FileBean fileBean = new FileBean();
	
	private FileDAO daoFile = new FileDAO();
	private UserDAO daoUser = new UserDAO();
	
	private StreamedContent file;
	
	private String way;
	private HttpSession session;
	
	private File filedownload;

	public DownloadController() 
	{	
		System.out.println("Classe Construtora");
	}

	public File download()
	{
		
		System.out.println("Depois entra aonde deveria");
		
		FacesContext context = FacesContext.getCurrentInstance();
	    Map<String, String> requestMap = context.getExternalContext().getRequestParameterMap();
	    url = (String) requestMap.get("code");
	    
	    
	    int url2 = Integer.parseInt(url);
	    
	    this.fileBean = daoFile.consultaPorCodigo(url2);
	    
	    user = daoUser.consultaPorCodigo(this.fileBean.getIdUser());
	    
	    session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		way = session.getServletContext().getRealPath("/users/");
		
		 this.setMessage("formDados", way + "\\" + user.getUser() + "\\" + fileBean.getName());
		 
		 try {
			 
			 String way2 = way + "\\" + user.getUser() + "\\" + fileBean.getName();
			 
			URL url3 = new URL(way2);
			
			InputStream is = url3.openStream();
			
			FileOutputStream fos = new FileOutputStream(way2);
			
			int umByte = 0;  
	        while ((umByte = is.read()) != -1){  
	            fos.write(umByte);  
	        }  
	  
	        //Nao se esqueca de sempre fechar as streams apos seu uso!  
	        is.close();  
	        fos.close();  
	  
	        //apos criar o arquivo fisico, retorna referencia para o mesmo  
	        return filedownload =  new File(way2);
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		 return null;
	}

	public StreamedContent getFile() 
	{
		return file;
	}

	public void setFile(StreamedContent file)
	{
		this.file = file;
	}
}
