package br.com.luizcalderaro.utils;

import javax.faces.event.ActionEvent; 

public class FacesUtils 
{
	 public static String getActionAttribute(ActionEvent event, String name)
	 {  
	        return (String) event.getComponent().getAttributes().get(name);  
	 }  
}
