package org.vmgs.com.clases;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import java.io.Serializable;

@Entity
@Table(name = "Rol")
public class Rol implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column (nullable=false, length=20)
	private String nombre;
	
	/* Propiedades */
	public Long getId(){
		return this.id;
	}
	
	public String getNombre(){
		return this.nombre;
	}
	
	public void setNombre(String value){
		this.nombre = value;
	}
}