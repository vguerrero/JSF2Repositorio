package org.vmgs.com.clases.utilidades;


public class Respuesta{
	private boolean respuesta;
	private String mensaje;
	
	public Respuesta(boolean v, String me){
		this.respuesta = v;
		this.mensaje= me;
	}
	
	public boolean  getRespuesta(){
		return this.respuesta;
	}
	
	public String getMensaje(){
	return this.mensaje;
	}
}