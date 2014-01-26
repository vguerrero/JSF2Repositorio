package org.vmgs.com.ManagedBeans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.vmgs.com.clases.Persona;

@ManagedBean
@SessionScoped
public class FormularioBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private Persona persona;
	
	public FormularioBean(){
		persona= new Persona();
		persona.setRespuesta("Mi esposa");
	}

	/**
	 * @return the persona
	 */
	public Persona getPersona() {
		return persona;
	}

	/**
	 * @param persona the persona to set
	 */
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	
	
	public String GuardarPersona(){
		System.out.print(persona.toString());
		
		persona= new Persona();//limpia el formulario
		return "";
	}
}
