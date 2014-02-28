package org.vmgs.com.daos;
import java.util.List;

import org.vmgs.com.clases.Usuario;

public interface UsuarioDao {

	public Usuario save(Usuario u);
	
	public void update (Usuario u);
	
	public void remove (Usuario u);
	
	public Usuario getUsusarioById(Long id);
	
	public Usuario obtenerUsuarioPorNombre(String value);
	
	public List<Usuario> buscarTodosUsuarios();
	
}

