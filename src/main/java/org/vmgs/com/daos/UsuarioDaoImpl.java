package org.vmgs.com.daos;

import java.util.List;
import java.util.HashSet;
import java.util.Set;
import java.util.LinkedHashSet;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.vmgs.com.clases.Usuario;
import java.lang.UnsupportedOperationException;
import javax.persistence.Query;
import org.vmgs.com.clases.utilidades.*;

@Repository
public class UsuarioDaoImpl implements UsuarioDao{

	@PersistenceContext
	private EntityManager em;
	
	
	public Usuario save(Usuario u) throws UnsupportedOperationException{
		em.persist(u);
		return u;
		
	}
	
	public void update (Usuario u)throws UnsupportedOperationException{
	}
	
	public void remove (Usuario u)throws UnsupportedOperationException{
	
	}
	
	public Usuario getUsusarioById(Long id){
		String jpql="SELECT p FROM Usuario p WHERE p.id = :id";
		Query q = em.createQuery(jpql);
		q.setParameter("id", id);
		List results = q.getResultList();
		if(results.size() > 0){
			return (Usuario)q.getSingleResult();//results.get(0)
		}
		return null;
	}
	
	public Usuario obtenerUsuarioPorNombre(String value)throws UnsupportedOperationException{
	
		String jpql="SELECT p FROM Usuario p WHERE p.nombre = :value";
		Query q = em.createQuery(jpql);
		q.setParameter("value", value);
		List results = q.getResultList();
		if(results.size() > 0){
			return (Usuario)q.getSingleResult();//results.get(0)
		}
		return null;
		
		 /*List results = query.getResultList();
        if (results.isEmpty()) return null;
        else if (results.size() == 1) return results.get(0);
        throw new NonUniqueResultException();*/
	}
	
	public List<Usuario> buscarTodosUsuarios()throws UnsupportedOperationException{
		Query query = em.createNamedQuery(Usuario.buscarTodos);
		List<Usuario> resultados = query.getResultList();
		return resultados;
	}
	
	public boolean Autenticacion(Usuario account){
		Usuario un = this.obtenerUsuarioPorNombre(account.getNombre());
		if(un != null){
			try{
			  if(account.getPassword().equals(Cifrado.desEncriptar(un.getPassword())))
				return true;
			  else
				return false;
			}
			catch(Exception ex){
				System.out.println("Hubo Error en Autenticacion");	
			}
		}
		return false;
	}
	
	
	public Usuario getUsuariowRoles(Long usuarioId){
		//The result type of a select query cannot be a collection; it must be a single valued object such as an entity instance or persistent field type
		String jpql="SELECT u FROM Usuario u JOIN FETCH u.roles WHERE u.id = (:UsuarioId)";
		Query q = em.createQuery(jpql);
		q.setParameter("UsuarioId", usuarioId);
		List results = q.getResultList();
		if(results.size() > 0){
			return (Usuario)q.getSingleResult();//results.get(0)
		}
		return null;
	}
	
	public boolean UsuarioesAdminRol(String nombre){
		//en JPQL Jpa no puedo seleccionar una columna especifica toca trabajar con toda la entidad, tocaria hacerlo con QueryDSL
		String jpql = "SELECT u from Usuario u JOIN  u.roles r WHERE u.nombre = :nombre AND r.nombre = 'admin'";
			Query q = em.createQuery(jpql);
			q.setParameter("nombre", nombre);
			List results = q.getResultList();
			int count = results.size(); 
			if(count > 0){
				Usuario user = (Usuario)q.getSingleResult();
				return true;
			}
			return false;
	}
	
}