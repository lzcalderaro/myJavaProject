package br.com.luizcalderaro.utils;

import java.io.*;

public class CreateFolder
{
	public void create(String name)
	{
		File theDir = new File(name);
		  if (!theDir.exists())
		  {
		    System.out.println("creating directory: " + name);
		    boolean result = theDir.mkdir();
		    if(result) System.out.println("DIR created");
		}
	}
}
