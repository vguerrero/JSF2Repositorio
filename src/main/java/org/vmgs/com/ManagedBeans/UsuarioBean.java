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
import javax.faces.context.ExternalContext;
import java.util.ArrayList;
import javax.faces.event.ActionEvent;
import java.util.Arrays;
import org.primefaces.event.SelectEvent;  
import org.apache.log4j.Logger;
import java.util.Date;
import org.primefaces.event.FileUploadEvent;  
import org.primefaces.model.UploadedFile;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.DefaultStreamedContent;
import java.io.ByteArrayInputStream;
import java.net.URLConnection;


@ManagedBean
@Component("usuarioBean")
@Scope("request") //because is a spring integrate -->
//@RequestScoped
public class UsuarioBean {
	@Autowired
	private ServicioCredencial servicio;
	
	private Usuario usuario;
	private List<Usuario> usuariosSistema;
	private List<Rol> rolesLista;
	private String[] rolesSeleccionados;
	private Usuario usuarioSeleccionado;
	private UploadedFile file;  
	private StreamedContent dbImage;
   
  
  
	
	private static final Logger logger = Logger.getLogger(UsuarioBean.class);
	
	//Init Method
	@PostConstruct  
	public void init() {  
		System.out.println("Init Method \n");
		//logger.debug("\n Inicializando el Bean de Usuario: " + new Date() + " \n");
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
	
	public UploadedFile getFile() {  
        return file;  
    }  
	
	  public void setFile(UploadedFile file) {  
        this.file = file;  
    }  
	
	public StreamedContent getDbImage(){
		  /*logger.debug("Entered method getFileContent.");
		  ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		  String photoId = externalContext.getRequestParameterMap().get("photo_id");
		  logger.debug("Entered method getFileContent. " + photoId);*/
		  return dbImage;
	}
	
	public void setDbImage(StreamedContent value){
		this.dbImage =value;
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
			//logger.debug("antes de guardar: " + file);
			//String contentType = file.getContentType();
			byte[] bytes = file.getContents() ;
			logger.debug("antes de guardar: " + bytes);
			if (bytes != null){
				this.usuario.setImage(bytes);
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
	
	
	
	public void onRowSelect(SelectEvent event)  {  
	try{
		if(usuarioSeleccionado != null && usuarioSeleccionado.getId() > 0){
			dbImage=null;
			logger.debug("\n Usuario seleccionado "+ usuarioSeleccionado.getNombre()+ "\n");		
			Long id = usuarioSeleccionado.getId();
			this.usuario = servicio.getUsuariowRoles(id);
			if(usuario != null){
				/*byte[] imageBytes= usuario.getImage();
				logger.debug("select : " + imageBytes);
				if( imageBytes != null){
					//primefaces tiene un bug con el control graphicsimage y la session , solo funciona bien con el session scope 
					http://stackoverflow.com/questions/13765258/why-i-cant-view-the-pgraphicimage-while-use-view-as-scope, http://stackoverflow.com/questions/13401569/how-to-make-primefaces-graphicimage-work-with-spring-web-flow 
					ByteArrayInputStream ba = new ByteArrayInputStream(imageBytes);
					String mimeType = URLConnection.guessContentTypeFromStream(ba);
					dbImage = new DefaultStreamedContent(new ByteArrayInputStream(imageBytes), mimeType );  
				}*/
				Set<Rol> roles = usuario.getRoles();//para poder obtenerlo debajo con el get(i) el set no tiene eso
				if(roles != null){
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
			else{ 
				FacesMessage msg = new FacesMessage( FacesMessage.SEVERITY_INFO,"Este usuario no tiene roles activos","Usuario Manager" );
					FacesContext.getCurrentInstance().addMessage(null, msg);  
			}
			
		}
		
		}
		catch(Exception e){
			logger.error("Error : " + e.getMessage());//e.printStackTrace();
		}
    }
	
	/* public void handleFileUpload(FileUploadEvent event) {  
		this.setImageName("El archivo: " + event.getFile().getFileName() + " se subio correctamente " + event.getFile().getContents());
		imageBytes = event.getFile().getContents();
		logger.debug("Bytes imagen.....> "+ imageBytes);
		//FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");  
        //FacesContext.getCurrentInstance().addMessage(null, msg);  
    } */ 
	
	

}