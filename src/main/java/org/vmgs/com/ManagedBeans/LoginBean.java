package org.vmgs.com.ManagedBeans;
 

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.vmgs.com.clases.Usuario;
import org.vmgs.com.clases.utilidades.*;

@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean implements Serializable{
	private Usuario account;
	
	 public Usuario getAccount() {
        return account;
    }
 
    public void setAccount(Usuario value) {
        this.account = value;
    }
	public LoginBean(){
		this.account= new Usuario();
	}
	  public String loginProject() {
		
        boolean result = (this.account.getNombre().equals("admin") && this.account.getPassword().equals("admin"));//UserDAO.login(uname, password);
        if (result) {
            // get Http Session and store username
            HttpSession session = Util.getSession();
            session.setAttribute("currentUser", account);
 
            return "primeForm?faces-redirect=true";//"formularios?faces-redirect=true";
        } else {
 
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
	public String getCurrentUserName(){
		HttpSession session = Util.getSession();
		Usuario theAccount = (Usuario)session.getAttribute("currentUser"); 
		if(theAccount != null){
			return theAccount.getNombre();
		}
		return "empty";
	}
    public String logout() {
	  System.out.print("\n " + this.account.getNombre());
      HttpSession session = Util.getSession();
      session.invalidate();
      return "login?faces-redirect=true";
   }
}