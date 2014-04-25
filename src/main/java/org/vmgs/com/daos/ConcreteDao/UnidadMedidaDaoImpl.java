package org.vmgs.com.daos.ConcreteDao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import org.vmgs.com.daos.ConcreteDao.UnidadMedidaDao;
import org.vmgs.com.clases.UnidadMedida;
import org.vmgs.com.daos.GenericDao.GenericDaoImpl;

@Component("UnidadMedidaDao")
public class UnidadMedidaDaoImpl extends GenericDaoImpl<UnidadMedida> implements UnidadMedidaDao {
//no need additional methods for now
    
}