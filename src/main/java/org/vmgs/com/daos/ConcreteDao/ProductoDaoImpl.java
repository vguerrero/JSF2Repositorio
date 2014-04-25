package org.vmgs.com.daos.ConcreteDao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import org.vmgs.com.daos.ConcreteDao.ProductoDao;
import org.vmgs.com.clases.Producto;
import org.vmgs.com.daos.GenericDao.GenericDaoImpl;

@Component("ProductoDao")
public class ProductoDaoImpl extends GenericDaoImpl<Producto> implements ProductoDao {

    public Producto ObtenerProductoxNombre(String nombre) {
        Query query = this.em
                .createQuery("select p FROM Producto p where p.nombre= :nombre");
        query.setParameter("nombre", nombre);
        List<Producto> productos = query.getResultList();
        if (productos != null && productos.size() == 1) {
            return productos.get(0);
        }
        return null;
    }
}