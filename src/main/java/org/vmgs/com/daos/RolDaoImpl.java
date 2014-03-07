package org.vmgs.com.daos;

import java.util.List;
import java.util.HashSet;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.vmgs.com.clases.Rol;
import java.lang.UnsupportedOperationException;
import javax.persistence.Query;
import org.vmgs.com.clases.utilidades.*;

@Repository
public class RolDaoImpl implements RolDao{

	@PersistenceContext
	private EntityManager em;
	
	
	public Rol save(Rol r) throws UnsupportedOperationException{
		em.persist(r);
		return r;
	}
	
	public void update (Rol r)throws UnsupportedOperationException{
	}
	
	public void remove (Rol r)throws UnsupportedOperationException{
	
	}
	
	public Rol getRolById(Long id){
		String jpql="SELECT r FROM Rol r WHERE r.id = :id";
		Query q = em.createQuery(jpql);
		q.setParameter("id", id);
		List results = q.getResultList();
		if(results.size() > 0){
			return (Rol)q.getSingleResult();//results.get(0)
		}
		return null;
	}
	
	public Rol obtenerRolPorNombre(String value)throws UnsupportedOperationException{
	
		String jpql="SELECT r FROM Rol r WHERE r.nombre = :value";
		Query q = em.createQuery(jpql);
		q.setParameter("value", value);
		List results = q.getResultList();
		if(results.size() > 0){
			return (Rol)q.getSingleResult();//results.get(0)
		}
		return null;
	
	}
	
	//convertir list to set
	//http://www.mkyong.com/java/how-to-convert-list-to-set-arraylist-to-hastset/
	public List<Rol> buscarTodosRoles()throws UnsupportedOperationException{
		Query query = em.createNamedQuery(Rol.buscarTodos);
		List<Rol> resultados = query.getResultList();
		return resultados;
	}
	
	
	
	
}