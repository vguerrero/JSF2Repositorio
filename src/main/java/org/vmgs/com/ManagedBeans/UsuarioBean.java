package org.vmgs.com.ManagedBeans;

import org.vmgs.com.servicios.ServicioCredencial;
import org.vmgs.com.clases.Usuario;
import org.vmgs.com.clases.Rol;
import org.vmgs.com.clases.utilidades.Respuesta;
import javax.faces.bean.ManagedBean;
//import javax.faces.bean.RequestScoped;
import org.springframework.context.annotation.Scope;
//import javax.faces.bean.ViewScoped;
import org.springframework.stereotype.Component;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import javax.annotation.PostConstruct;
import java.util.LinkedHashSet;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;  
import java.util.ArrayList;
import javax.faces.event.ActionEvent;
import java.util.Arrays;
import org.primefaces.event.SelectEvent;  

@ManagedBean
@Component("usuarioBean")
@Scope("request") //because is a spring integrate
//@RequestScoped
public class UsuarioBean {
	@Autowired
	private ServicioCredencial servicio;
	
	private Usuario usuario;
	private List<Usuario> usuariosSistema;
	private List<Rol> rolesLista;
	private String[] rolesSeleccionados;
	private Usuario usuarioSeleccionado;
	
	//Init Method
	@PostConstruct  
	public void init() {  
		System.out.println("Init Method \n");
		usuario = new Usuario();
		this.usuariosSistema = new ArrayList<Usuario>();
		rolesLista = servicio.buscarTodosRoles();
		
	}  
	
	
	//getters and setters
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario value) {
		this.usuario = value;
	}
	
	public List<Usuario> getUsuariosSistema() {
		if(servicio != null)		
			this.usuariosSistema = new ArrayList(servicio.buscarTodosUsuarios());		
		else
			System.out.println("el servicio es null");
		return usuariosSistema;
	}
	public void setUsuariosSistema(List<Usuario> value) {
		this.usuariosSistema = value;
	}
	
	public List<Rol> getRolesLista(){
		return this.rolesLista;
	}
	
	public void setRolesLista(List<Rol> value){
		this.rolesLista=value;
	}
	
	public  String[] getRolesSeleccionados(){
		return this.rolesSeleccionados;
	}
	
	public void setRolesSeleccionados( String[] value){
		this.rolesSeleccionados=value;
	}
	
	public Usuario getUsuarioSeleccionado(){
		return usuarioSeleccionado;
	}
	
	public void setUsuarioSeleccionado(Usuario value){
		 this.usuarioSeleccionado= value;
	}
	//--------------------------------------------------
	

	
	//Actions and Methods	
	public LinkedHashSet<Rol> ObtenerRolesSeleccionados(){
		LinkedHashSet<Rol> selectedRoles= new LinkedHashSet<Rol>();
		for(String e : this.rolesSeleccionados){
			for(Rol r : this.rolesLista){
				if(Long.parseLong(e) == r.getId()){
					selectedRoles.add(r);
					
				}
			}
		}
		return selectedRoles;
	}
	
	public String GuardarUsuario(){//(ActionEvent event) //action listener cuando no vas a devolver ninguna pagina o outcome, action cuando si lo vas a devolver
		if(usuario != null){
			Set<Rol> roles = this.ObtenerRolesSeleccionados();
			if(roles.size()>0){
				usuario.setRoles(roles);
			}
			Respuesta r = servicio.GuardarUsuario(usuario);
			if(r.getRespuesta()){
				FacesMessage msg = new FacesMessage( FacesMessage.SEVERITY_INFO,"Se guardo correctamente el Usuario: "+ usuario.getNombre(),"Usuario Manager" );
				FacesContext.getCurrentInstance().addMessage(null, msg);  
				//prepare the form for the new adding data
				setUsuario(new Usuario());
				setRolesSeleccionados(new String[0]);
				//----
				return "UsuarioManager";
			}
			else{
		       	 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"No se guardo el Usuario: "+ r.getMensaje(), "Usuario Manager"));  
			}				
		}
		return "";		
	}
	
	public void onRowSelect(SelectEvent event) {  
		//System.out.println("Usuario:  " + usuarioSeleccionado.getId());
		if(usuarioSeleccionado != null && usuarioSeleccionado.getId() > 0){
			Long id = usuarioSeleccionado.getId();
			this.usuario = servicio.getUsuariowRoles(id);
			Set<Rol> roles = usuario.getRoles();//para poder obtenerlo debajo con el get(i) el set no tiene eso
			
			List<Rol> rolesl = new ArrayList(roles);
			Integer rsize = rolesl.size();
			if(rsize > 0){
				rolesSeleccionados = new String[rolesl.size()];
				for(int i = 0 ; i <= rsize-1 ;i++ ){
					rolesSeleccionados[i] = rolesl.get(i).getId().toString();
				}
			}
		}
		
    }
	
	

}