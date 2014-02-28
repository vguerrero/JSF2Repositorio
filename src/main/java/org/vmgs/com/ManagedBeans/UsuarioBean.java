package org.vmgs.com.ManagedBeans;

import org.vmgs.com.servicios.ServicioCredencial;
import org.vmgs.com.clases.Usuario;
import org.vmgs.com.clases.utilidades.Respuesta;
import javax.faces.bean.ManagedBean;
//import javax.faces.bean.RequestScoped;
import org.springframework.context.annotation.Scope;
//import javax.faces.bean.ViewScoped;
import org.springframework.stereotype.Component;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;  
import java.util.ArrayList;
import javax.faces.event.ActionEvent;

@ManagedBean
@Component("usuarioBean")
@Scope("request") //because is a pring integrate
//@RequestScoped
public class UsuarioBean {
	@Autowired
	private ServicioCredencial servicio;
	
	private Usuario usuario;
	private List<Usuario> usuariosSistema;
	
	//Init Method
	@PostConstruct  
	public void init() {  
		System.out.println("Init Method \n");
		usuario = new Usuario();
		this.usuariosSistema = new ArrayList<Usuario>();
		//this.getUsuariosSistema();
	}  
	
	
	//getters and setters
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario value) {
		this.usuario = value;
	}
	
	public List<Usuario> getUsuariosSistema() {
		System.out.println("getUsuariosSistema");
		if(servicio != null){
			List<Usuario> ul= servicio.buscarTodosUsuarios();
			this.usuariosSistema =servicio.buscarTodosUsuarios();
			if(ul.size() > 0)
				this.setUsuariosSistema(ul);
		}
		else{System.out.println("el servicio es null");}
		return usuariosSistema;
	}
	public void setUsuariosSistema(List<Usuario> value) {
		this.usuariosSistema = value;
	}
	//----
	
	
	
	//Actions
	public void GuardarUsuario(ActionEvent event){ //action listener cuando no vas a devolver ninguna pagina o outcome, action cuando si lo vas a devolver
		if(usuario != null){
			Respuesta r = servicio.GuardarUsuario(usuario);
			if(r.getRespuesta()){
				FacesMessage msg = new FacesMessage( FacesMessage.SEVERITY_INFO,"Se guardo correctamente el Usuario: "+ usuario.getNombre(),"Usuario Manager" );
				FacesContext.getCurrentInstance().addMessage(null, msg);  	
				this.getUsuariosSistema();
				
			}
			else{
		       	 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"No se guardo el Usuario: "+ r.getMensaje(), "Usuario Manager"));  
			}
			
		}
			
	}

}