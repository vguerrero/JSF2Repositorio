package org.vmgs.com.ManagedBeans;
 

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.vmgs.com.clases.Account;
import org.vmgs.com.clases.Util;

@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean implements Serializable{
	private Account account;
	
	 public Account getAccount() {
        return account;
    }
 
    public void setAccount(Account value) {
        this.account = value;
    }
	public LoginBean(){
		this.account= new Account();
	}
	  public String loginProject() {
		
        boolean result = (this.account.getUsername().equals("admin") && this.account.getPassword().equals("admin"));//UserDAO.login(uname, password);
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
		Account theAccount = (Account)session.getAttribute("currentUser"); 
		if(theAccount != null){
			return theAccount.getUsername();
		}
		return "empty";
	}
    public String logout() {
	  System.out.print("\n " + this.account.getUsername());
      HttpSession session = Util.getSession();
      session.invalidate();
      return "login?faces-redirect=true";
   }
}