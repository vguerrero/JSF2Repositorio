package org.vmgs.com.servicios;

import org.springframework.transaction.annotation.Transactional;
import org.vmgs.com.clases.Usuario;
import org.vmgs.com.clases.Rol;
import org.vmgs.com.clases.utilidades.Respuesta;
import java.util.List;
import java.util.Set;
import java.util.LinkedHashSet;

public interface  ServicioCredencial{
	public Respuesta GuardarUsuario(Usuario user) throws UnsupportedOperationException ;
	
	public List <Usuario> buscarTodosUsuarios() throws UnsupportedOperationException ;//debe ser una lista ordenada
	
	public boolean Autenticacion(Usuario account);
	
	public List<Rol> buscarTodosRoles() throws UnsupportedOperationException ;
	
	public Rol obtenerRolxId(Long id);
	
	public Usuario getUsuarioxId(Long id );
	
	public Usuario getUsuariowRoles(Long UsuarioId);
	
	public Usuario getUsuarioxNombre(String nombre);
	
	public boolean UsuarioesAdminRol(String nombre);


}