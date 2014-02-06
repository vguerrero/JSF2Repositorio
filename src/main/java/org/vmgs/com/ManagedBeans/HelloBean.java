package org.vmgs.com.ManagedBeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.io.Serializable;

@SessionScoped
@ManagedBean
public class HelloBean implements Serializable {

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
	
	public String movetoPage2Implicit(){
		return "page2";
	}
	
	public String redirectPage2(){
		this.setName("redireccionando en JSF 2");
		return "page2?faces-redirect=true";
	}
	
	public void recibirParametro(){
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
		String parametro = params.get("poblacion");
		System.out.print(parametro);
	}
	
	public void attrListener(ActionEvent event){
 		String country = (String)event.getComponent().getAttributes().get("poblacion");
		System.out.print(country);
 	}
}
