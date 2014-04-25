package org.vmgs.com.daos.GenericDao;

import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;

//TOMADO DE http://www.codeproject.com/Articles/251166/The-Generic-DAO-pattern-in-Java-with-Spring-and
public interface GenericDao<T> {
    /**
     * Method that returns the number of entries from a table that meet some
     * criteria (where clause params)
     *
     * @param params
     *            sql parameters
     * @return the number of records meeting the criteria
     */
    long countAll(Map<String, Object> params);
	
	@Transactional //la idea es que estos transactional esten en los servicios cuando se guarden con mas datos y cosas por pruebas los coloque aqui para que funcionara 
	//UnidadMedida t = uMedidaDao.create(unidadm);
	T create(T t);

	@Transactional
    void delete(Object id);

    T find(Object id);

	@Transactional
    T update(T t);   
	
	List<T> getAll();
}