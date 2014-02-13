package org.vmgs.com.clases;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
public class Persona implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String primerNombre;
	private String apellido;
	private String password;
	private String comentario;//textarea
	private String respuesta;//hidden
	private boolean tieneTrabajo;
	private String[] favoriteTecnologies;
	private String favoriteFoot;
	private double sueldo;
	private Date fechaContrato;
	
	
	private boolean editable;
	/**
	 * @return the primerNombre
	 */
	public String getPrimerNombre() {
		return primerNombre;
	}
	/**
	 * @param primerNombre the primerNombre to set
	 */
	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}
	/**
	 * @return the apellido
	 */
	public String getApellido() {
		return apellido;
	}
	/**
	 * @param apellido the apellido to set
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
		
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	/**
	 * @return the comentario
	 */
	public String getComentario() {
		return comentario;
	}
	/**
	 * @param comentario the comentario to set
	 */
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	
	
	/**
	 * @return the respuesta
	 */
	public String getRespuesta() {
		return respuesta;
	}
	/**
	 * @param respuesta the respuesta to set
	 */
	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
	
	
	/**
	 * @return the tieneTrabajo
	 */
	public boolean isTieneTrabajo() {
		return tieneTrabajo;
	}
	/**
	 * @param tieneTrabajo the tieneTrabajo to set
	 */
	public void setTieneTrabajo(boolean tieneTrabajo) {
		this.tieneTrabajo = tieneTrabajo;
	}
	
	
	/**
	 * @return the favoriteTecnologies
	 */
	public String[] getFavoriteTecnologies() {
		return favoriteTecnologies;
	}
	/**
	 * @param favoriteTecnologies the favoriteTecnologies to set
	 */
	public void setFavoriteTecnologies(String[] favoriteTecnologies) {
		this.favoriteTecnologies = favoriteTecnologies;
	}
	
	public String getFavoriteFoot(){
		return this.favoriteFoot;
	}
	public void setFavoriteFoot(String favoriteFoot){
		this.favoriteFoot=favoriteFoot;
	}
	
	public boolean getEditable(){
		return this.editable;
	}
	
	public void setEditable(boolean value){
		this.editable=value;
	}
	
	public double getSueldo(){
		return this.sueldo;
	}
	
	public void setSueldo(double value){
		this.sueldo= value;
	}
	
	public Date getFechaContrato(){
		return this.fechaContrato; 
	}
	
	public void setFechaContrato(Date value){
		this.fechaContrato= value;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Persona [primerNombre=" + primerNombre + ", apellido="
				+ apellido + ", password=" + password + ", comentario="
				+ comentario + ", respuesta=" + respuesta + ", tieneTrabajo="
				+ tieneTrabajo + ", favoriteTecnologies="+" ["
				+ Arrays.toString(favoriteTecnologies) + "] "
				+", Comida Favorita= " + favoriteFoot 
				+", Sueldo= "+ sueldo
				+", FechaContrato= " + fechaContrato.toString()
				+ "]";
	}
	
	
	
	
	
}
