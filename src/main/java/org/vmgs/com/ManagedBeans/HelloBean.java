package org.vmgs.com.ManagedBeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@SessionScoped
@ManagedBean
public class HelloBean {

	private String name;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/*Jsf accesa por el metodo setter por eso getSayWelcome y alla en
	 * la vista sayWelcome
	 */
	public String getSayWelcome() {
		if (!(name == null) || ("".equals(name))) {
			return "Mensaje Ajax: " + name + " Bienvenido";
		}
		return "";
	}
}
