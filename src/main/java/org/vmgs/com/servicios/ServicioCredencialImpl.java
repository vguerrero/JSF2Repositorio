package org.vmgs.com.servicios;

import org.vmgs.com.clases.utilidades.Respuesta;
import org.springframework.stereotype.Service;
import org.vmgs.com.clases.Usuario;
import org.vmgs.com.daos.UsuarioDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.vmgs.com.clases.utilidades.*;
import java.util.Date;
import java.lang.UnsupportedOperationException;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class ServicioCredencialImpl implements ServicioCredencial {
	
	@Autowired
	private UsuarioDao usuarioDao;
	
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
			System.out.println("el servicio de guardar usuarios presento un error");
			return new Respuesta(false,ex.getMessage());
		}
		
	}
	
	public List<Usuario> buscarTodosUsuarios() throws UnsupportedOperationException {
		try{
			return usuarioDao.buscarTodosUsuarios();
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

}