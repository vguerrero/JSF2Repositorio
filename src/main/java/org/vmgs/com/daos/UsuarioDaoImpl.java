package org.vmgs.com.daos;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.vmgs.com.clases.Usuario;
import java.lang.UnsupportedOperationException;
import javax.persistence.Query;

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
	
	public Usuario getUsusarioById(Long id)throws UnsupportedOperationException{
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
	
}