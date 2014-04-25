package org.vmgs.com.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import org.vmgs.com.clases.UnidadMedida;
import org.vmgs.com.clases.Producto;
import org.vmgs.com.daos.ConcreteDao.ProductoDao;
import org.vmgs.com.daos.ConcreteDao.UnidadMedidaDao;
import java.util.List;

@Service("ServicioProducto")
public class ServicioProductoImpl implements ServicioProducto {

    @Autowired
    private ProductoDao pdao;
	
	@Autowired
	private UnidadMedidaDao uMedidaDao;

    @Override
    public Producto CrearProducto(String nombre, UnidadMedida umedida, String usuarioCrea) {
       	Producto p = new Producto();
		p.setNombre(nombre);
		p.setUmedida(umedida);
		p.setUsuariocrea(usuarioCrea);
		p.setFechacrea(new Date());
		pdao.create(p);
		return p;
    }
	
	public List<Producto> ObtenerTodosProductos(){
		return pdao.getAll();
	}
	
	public List<UnidadMedida> ObtenerTodosUnidadMedida(){
		return uMedidaDao.getAll();
	}
}