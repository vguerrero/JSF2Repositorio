package org.vmgs.com.clases;

import java.io.Serializable;
import java.util.Date;

public class Account implements Serializable{
	private String username;
	private String password;
	
	public String getUsername(){
	 return this.username;
	}
	
	public void setUsername(String value){
		this.username=value;
	}
	
	public String getPassword(){
	 return this.password;
	}
	
	public void setPassword(String value){
		this.password=value;
	}
}