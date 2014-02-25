package org.vmgs.com.daos;

import java.util.List;

import org.vmgs.com.clases.Persona;

public interface PersonaDao {

	public Persona save(Persona persona);
	
	public void update (Persona persona);
	
	public void remove (Persona persona);
	
	public Persona getPersonaById(Long id);
	
	public List<Persona> buscarPersonas();
	
}
