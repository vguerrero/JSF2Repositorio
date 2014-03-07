package org.vmgs.com.daos;
import java.util.List;
import java.util.Set;

import org.vmgs.com.clases.Rol;

public interface RolDao {

	public Rol save(Rol r);
	
	public void update (Rol r);
	
	public void remove (Rol r);
	
	public Rol getRolById(Long id);
	
	public Rol obtenerRolPorNombre(String value);
	
	public List<Rol> buscarTodosRoles();
	
	
}
