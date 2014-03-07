package org.vmgs.com.ManagedBeans;
 

import java.io.Serializable;
import javax.faces.application.FacesMessage;
//import javax.faces.bean.ManagedBean;// no funciona la inyeccion si uso estos
//import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.vmgs.com.clases.Usuario;
import org.vmgs.com.clases.utilidades.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.vmgs.com.servicios.ServicioCredencial;
import org.springframework.context.annotation.Scope;//y este
import org.springframework.stereotype.Component;//cuando esta integrado con sprint hay que usar este


//@ManagedBean(name = "loginBean")
@Scope("request")
@Component("loginBean")
public class LoginBean implements Serializable{
	private Usuario account;
	private boolean usuarioisAdmin;
	
	@Autowired
	private ServicioCredencial servicioCredencial;
	
	 public Usuario getAccount() {
        return account;
    }
 
    public void setAccount(Usuario value) {
        this.account = value;
    }
	public LoginBean(){
		this.account= new Usuario();
	}
	
	public String getDisplayUserName(){
		return Util.getCurrentUserName();
	}
	
	public boolean getUsuarioisAdmin(){
		return this.currentUserisAdmin();
	}
	
	public String loginProject() {
	try{
		boolean result = servicioCredencial.Autenticacion(account);//(this.account.getNombre().equals("admin") && this.account.getPassword().equals("admin"));//UserDAO.login(uname, password);
			if (result) {
				// get Http Session and store username
				HttpSession session = Util.getSession();
				session.setAttribute("currentUser", account);
	 
				return "primeForm?faces-redirect=true";//"formularios?faces-redirect=true";
			} 
			else {
	 
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_WARN,
						"Invalid Login!",
						"Please Try Again!"));
	 
				// invalidate session, and redirect to other pages
	 
				//message = "Invalid Login. Please Try Again!";
				return "login";
			}
		}
		catch(Exception e){
		 System.out.println(e.getMessage());
		 return "";
		}
    }
	
    public String logout() {
	  //System.out.print("\n " + this.account.getNombre());
      HttpSession session = Util.getSession();
      session.invalidate();
	  return "login?faces-redirect=true";
   }
   
   public void test(){
   //Probando si el jrebel hace en caliente la actualizacion
	System.out.print(" \n Prueba, Victor Guerrero,---> :)");
   }
   
   public boolean currentUserisAdmin(){
		String sessionUsername = Util.getCurrentUserName();
		if(!sessionUsername.isEmpty()){
			return servicioCredencial.UsuarioesAdminRol(sessionUsername);
		}
		return false;
   }
  
}