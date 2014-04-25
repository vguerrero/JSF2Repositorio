package org.vmgs.com.servicios;
import org.springframework.transaction.annotation.Transactional;
import org.vmgs.com.clases.UnidadMedida;
import org.vmgs.com.clases.Producto;
import java.util.List;

public interface ServicioProducto {

    @Transactional
    Producto CrearProducto(String nombre, UnidadMedida umedida, String UsuarioCrea);
	
	List<Producto> ObtenerTodosProductos();
	
	List<UnidadMedida> ObtenerTodosUnidadMedida();
}