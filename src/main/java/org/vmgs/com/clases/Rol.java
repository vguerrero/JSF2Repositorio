package org.vmgs.com.clases;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.NamedQuery;
import java.io.Serializable;
import javax.persistence.ManyToMany;
import javax.persistence.FetchType;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Rol")
@NamedQuery(name=Rol.buscarTodos, query="SELECT r FROM Rol r ORDER BY r.nombre asc") //estas consultas estaticas son eficientes
public class Rol implements Serializable{

	public static final String buscarTodos = "Rol.buscarTodos"; 
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column (nullable=false, length=20)
	private String nombre;
	
	@ManyToMany(fetch = FetchType.LAZY , mappedBy = "roles")
	private Set<Usuario> usuarios;
	
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
	
	public Set<Usuario> getUsuarios(){
		return this.usuarios;
	}
	
	public void setUsuarios(Set<Usuario> value){
		this.usuarios= value;
	}
}