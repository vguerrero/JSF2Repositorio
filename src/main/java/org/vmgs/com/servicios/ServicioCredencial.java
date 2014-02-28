package org.vmgs.com.servicios;

import org.springframework.transaction.annotation.Transactional;
import org.vmgs.com.clases.Usuario;
import org.vmgs.com.clases.utilidades.Respuesta;
import java.util.List;

public interface  ServicioCredencial{
	public Respuesta GuardarUsuario(Usuario user) throws UnsupportedOperationException ;
	
	public List<Usuario> buscarTodosUsuarios() throws UnsupportedOperationException ;
}