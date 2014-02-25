package org.vmgs.com.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import org.vmgs.com.clases.Persona;

@Repository
public class PersonaDaoImpl implements PersonaDao {

	@PersistenceContext
	private EntityManager em;
	
	public Persona save(Persona persona) {
		em.persist(persona);
		return persona;
	}
	
	public void update(Persona p){
		em.merge(p);
	}
	
	@Override
	public void remove(Persona p) {
		//Contact toremove = em.find(Contact.class, id);
		em.remove(p);
	}
	
	public Persona getPersonaById(Long id){
		Persona p = em.find(Persona.class, id);
		return p;
	}

	public List<Persona> buscarPersonas() {
		return em.createQuery("SELECT p FROM Persona p").getResultList();
	}
	
}
