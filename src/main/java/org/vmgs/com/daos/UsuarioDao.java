package org.vmgs.com.daos;
import java.util.List;
import javax.persistence.Query;
import org.vmgs.com.clases.Usuario;

public interface UsuarioDao {

	public Usuario save(Usuario u);
	
	public void update (Usuario u);
	
	public void remove (Usuario u);
	
	public Usuario getUsusarioById(Long id);
	
	public Usuario obtenerUsuarioPorNombre(String value);
	
	public List<Usuario> buscarTodosUsuarios();
	
	public boolean Autenticacion(Usuario account);
	
	//trae el usuario con los roles que tiene
	public Usuario getUsuariowRoles(Long UsuarioId);
	
	public boolean UsuarioesAdminRol(String nombre);
	
}

