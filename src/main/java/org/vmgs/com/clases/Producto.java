package org.vmgs.com.clases;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Date;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import org.vmgs.com.clases.UnidadMedida;


@Entity
public class Producto implements Serializable{
	/* Miembros */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column (nullable=false, length=40)
	private String nombre;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="uMedidaId")
	private UnidadMedida umedida;
	
	@Column(nullable=false)
	private Date fechacrea;
	
	@Column
	private Date fechaedita;
	
	@Column(nullable=false)
	private String usuariocrea;
	
	@Column
	private String usuarioedita;
	
	
	//getters and setters
	public Long getId(){
		return this.id;
	}
			
	public String getNombre(){
		return this.nombre;
	}
	public void setNombre(String value){
		this.nombre = value;
	}
	
	public UnidadMedida getUmedida(){
		return this.umedida;
	}
	public void setUmedida(UnidadMedida value){
		this.umedida = value;
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