package br.com.luizcalderaro.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "TBUpload")


public class UploadBean
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private String fileName;
	private byte[] file;
	
	public String getFileName()
	{
		return fileName;
	}
	
	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}
	
	public byte[] getFile()
	{
		return file;
	}
	
	public void setFile(byte[] file)
	{
		this.file = file;
	}
}
