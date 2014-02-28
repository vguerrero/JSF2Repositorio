package org.vmgs.com.clases.utilidades;
 
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.vmgs.com.clases.Usuario; 
 
public class Util {
 
      public static HttpSession getSession() {
        return (HttpSession)
          FacesContext.
          getCurrentInstance().
          getExternalContext().
          getSession(false);
      }
       
      public static HttpServletRequest getRequest() {
       return (HttpServletRequest) FacesContext.
          getCurrentInstance().
          getExternalContext().getRequest();
      }
 
      public static Usuario getUser()
      {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        return  (Usuario)session.getAttribute("currentUser");
      }
	  
	  public static String getCurrentUserName(){
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		if(session != null && (Usuario)session.getAttribute("currentUser") != null){
			Usuario c =  (Usuario)session.getAttribute("currentUser");
			return c.getNombre();
		}
        return "";
	  }
       
     /* public static String getUserId()
      {
        HttpSession session = getSession();
        if ( session != null )
            return (String) session.getAttribute("userid");
        else
            return null;
      }*/
}