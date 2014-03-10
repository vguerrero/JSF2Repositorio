package org.vmgs.com.servicios;

import org.vmgs.com.clases.utilidades.Respuesta;
import org.springframework.stereotype.Service;
import org.vmgs.com.clases.Usuario;
import org.vmgs.com.clases.Rol;
import org.vmgs.com.daos.UsuarioDao;
import org.vmgs.com.daos.RolDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.vmgs.com.clases.utilidades.*;
import java.util.Date;
import java.lang.UnsupportedOperationException;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Set;
import java.util.LinkedHashSet;

@Service
@Transactional
public class ServicioCredencialImpl implements ServicioCredencial {
	
	@Autowired
	private UsuarioDao usuarioDao;
	@Autowired
	private RolDao rolDao;
	
	public Respuesta GuardarUsuario(Usuario user) throws UnsupportedOperationException {
		try{
			//validaciones
			if(usuarioDao.obtenerUsuarioPorNombre(user.getNombre()) != null)
				return new Respuesta(false, "el nombre de usuario ya existe");
			
			user.setPassword(Cifrado.Encriptar(user.getPassword()));//Cifrado.Encriptar(user.getPassword()));
			user.setFechacrea(new Date());
			user.setUsuariocrea(Util.getCurrentUserName());
			usuarioDao.save(user);
			return new Respuesta(true,"ok");
		}
		catch(UnsupportedOperationException e){
			System.out.println("el servicio de guardar usuarios todavia no esta soportado");
			return new Respuesta(false,e.getMessage());
		}
		catch(Exception ex){
			System.out.println("el servicio de guardar usuarios presento un error"+ ex.getMessage());
			return new Respuesta(false,ex.getMessage());
		}
		
	}
	
	public List<Usuario> buscarTodosUsuarios() throws UnsupportedOperationException {
		try{
			return usuarioDao.buscarTodosUsuarios();//LinkedHashSet para que esten ordenados
		}
		catch(UnsupportedOperationException e){
			System.out.println("el servicio de buscarTodosUsuarios todavia no esta soportado");
			return null;
		}
		catch(Exception ex){
			System.out.println("el servicio de buscarTodosUsuarios presento un error");
			return null;
		}
	}
	
	public boolean Autenticacion(Usuario account){
		return usuarioDao.Autenticacion(account);
	}
	
	public List<Rol> buscarTodosRoles() throws UnsupportedOperationException {
		try{
			return rolDao.buscarTodosRoles();
		}
		catch(UnsupportedOperationException e){
			System.out.println("el servicio de buscarTodosRoles todavia no esta soportado");
			return null;
		}
		catch(Exception ex){
			System.out.println("el servicio de buscarTodosRoles presento un error");
			return null;
		}
	}
	
	public Rol obtenerRolxId(Long id){
	    return rolDao.getRolById(id);
	}
	
	public Usuario getUsuarioxId(Long id ){
		return usuarioDao.getUsusarioById(id);
	}
	
	/*Este metodo trae el usuario con su correspondiente Set de Roles
	*/
	public Usuario getUsuariowRoles(Long UsuarioId){
		return usuarioDao.getUsuariowRoles(UsuarioId);
	}
	
	public Usuario getUsuarioxNombre(String nombre){
		return usuarioDao.obtenerUsuarioPorNombre(nombre);
	}
	
	public boolean UsuarioesAdminRol(String nombre){
		return this.usuarioDao.UsuarioesAdminRol(nombre);
	}
}