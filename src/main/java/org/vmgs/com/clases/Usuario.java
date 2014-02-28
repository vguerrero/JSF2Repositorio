package org.vmgs.com.clases;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.vmgs.com.clases.Rol;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.persistence.OneToMany;
import javax.persistence.FetchType;
import javax.persistence.NamedQuery;

@Entity
@Table(name = "Usuario")
@NamedQuery(name=Usuario.buscarTodos, query="SELECT p FROM Usuario p ORDER BY p.nombre asc") //estas consultas estaticas son eficientes
public class Usuario implements Serializable{
	
	public static final String buscarTodos = "Usuario.buscarTodos"; 
	
	/* Miembros */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column (nullable=false, length=20)
	private String nombre;
	
	@Column (nullable=false)
	private String password;
	
	@Column(nullable=false)
	private Date fechacrea;
	
	@Column
	private Date fechaedita;
	
	@Column(nullable=false)
	private String usuariocrea;
	
	@Column
	private String usuarioedita;
	
	@OneToMany(fetch = FetchType.EAGER)
	private List<Rol> roles;
	
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
	
	public String getPassword(){
		return this.password;
	}
	
	public void setPassword(String value){
		this.password = value;
	}
	
	public Date getFechacrea(){
	 return fechacrea;
	}
	
	public void setFechacrea(Date value){
		this.fechacrea=value;
	}
	
	public Date getFechaedita(){
	 return fechaedita;
	}
	
	public void setFechaedita(Date value){
		this.fechaedita=value;
	}
	
	public String getUsuariocrea(){
		return this.usuariocrea;
	}
	
	public void setUsuariocrea(String value){
		this.usuariocrea= value;
	}
	
	public String getUsuarioedita(){
		return this.usuarioedita;
	}
	
	public void setUsuarioedita(String value){
		this.usuarioedita = value;
	}
}