package br.com.luizcalderaro.web;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.mail.EmailException;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import br.com.luizcalderaro.beans.FileBean;
import br.com.luizcalderaro.beans.UserBean;
import br.com.luizcalderaro.model.HibernateDAO;
import br.com.luizcalderaro.utils.EmailSender;

public class UploadController extends AbstractController
{
	private File file;
	private UserBean user = new UserBean();
	private String way;
	private HttpSession session;
	
	private String emailDest = null;
	private EmailSender email = new EmailSender();
	
	private String fileNameUploaded;
	private boolean isFile = false;
	
	private FileBean fileBean = new FileBean();
	private HibernateDAO dao = new HibernateDAO();
	
	public UploadController()
	{	
		user = (UserBean) this.getSessionAttribute("usuarioLogado");
	}
	
	public void doUpload(FileUploadEvent fileUploadEvent) 
	{		
        UploadedFile uploadedFile = fileUploadEvent.getFile(); 
        this.fileNameUploaded = uploadedFile.getFileName();
        long fileSizeUploaded = uploadedFile.getSize();
        String infoAboutFile = "Arquivo recebido: " + this.fileNameUploaded + " " + "Tamanho do Arquivo: "+ fileSizeUploaded;
        this.setMessage("formDados", infoAboutFile);
        
        session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		way = session.getServletContext().getRealPath("/users/");
		
		System.out.println(way);
        
        byte[] b = uploadedFile.getContents();
        file = new File(uploadedFile.getFileName());
        
        try {
			OutputStream out = new FileOutputStream(way + "\\" + user.getUser() + "\\" + file);
			try {
				out.write(b);
				out.close();
				
				fileBean.setIdUser(user.getId());
				fileBean.setName(fileNameUploaded);
				
				try {
					dao.executaTransacao(fileBean, 4);
				} catch (EmailException e) {
					e.printStackTrace();
				}
				
				this.isFile = true;
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void senderEmail() throws EmailException
	{
		if(this.isFile)
		{
		
			if(this.emailDest != null)
			{
				String container = "localhost:8080/ProjectManhattan/public/download.xhtml?url=" + fileBean.getId();
				email.sender(user.getNome(), this.getEmailDest(), container);
				
				this.setMessage("formDados", container);
				
			}else {
				this.setMessage("formDados", "Por favor coloque o email do Destinatario...");
			}
		}
	}
	
	public String getEmailDest() 
	{
		return emailDest;
	}

	public void setEmailDest(String emailDest) 
	{
		this.emailDest = emailDest;
	}

	public File getFile()
	{
		return file;
	}

	public void setFile(File file)
	{
		this.file = file;
	}
	
	public UserBean getUser() {
		return user;
	}

	public void setUser(UserBean user) {
		this.user = user;
	}
}