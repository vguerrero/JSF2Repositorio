package org.vmgs.com.clases.utilidades;
 
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.vmgs.com.clases.Usuario; 
import javax.faces.context.ExternalContext;
import org.apache.log4j.Logger;
import java.io.File;
import java.io.FileInputStream;

 
public class Util {
 
	private static final Logger logger = Logger.getLogger(Util.class);
	
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
	  
	  public static ExternalContext getExternalContext (){
		  return  FacesContext.
			  getCurrentInstance().
			  getExternalContext();
	  }
 
      public static Usuario getUser()
      {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        return  (Usuario)session.getAttribute("currentUser");
      }
	  
	  public static String getCurrentUserName(){
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		if(session != null){
			if((Usuario)session.getAttribute("currentUser") != null){
				Usuario c =  (Usuario)session.getAttribute("currentUser");
				return c.getNombre();
			}
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
	  
	  //Private method to create byte[] from image file  
	 public static byte[] convertFileToByteArray(String fileLocation) {
			try {
			   File file = new File(fileLocation);
				byte[] bFile = new byte[(int) file.length()];
				
				 try {
					 FileInputStream fileInputStream = new FileInputStream(file);
					 //convert file into array of bytes
					 fileInputStream.read(bFile);
					 fileInputStream.close();
				} catch (Exception e) {
					 e.printStackTrace();
				}
				
				return bFile;
				
			} catch (Exception e) {
				logger.error("convertFileToByteArray error: " + e.getMessage() );
			}
			return null;
    }
}