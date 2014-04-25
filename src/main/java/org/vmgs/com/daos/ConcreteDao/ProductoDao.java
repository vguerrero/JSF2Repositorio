package org.vmgs.com.daos.ConcreteDao;

import org.vmgs.com.clases.Producto;
import org.vmgs.com.daos.GenericDao.GenericDao;

public interface ProductoDao extends GenericDao<Producto> {
   
    public Producto ObtenerProductoxNombre(String nombre);
}