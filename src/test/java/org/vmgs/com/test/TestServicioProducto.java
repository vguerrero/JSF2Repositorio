package org.vmgs.com.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.vmgs.com.servicios.ServicioProducto;
import org.vmgs.com.daos.ConcreteDao.UnidadMedidaDao;
import org.vmgs.com.clases.*;
import org.vmgs.com.clases.utilidades.*;
import java.util.List;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.*;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.io.FileOutputStream;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring-beans-test.xml", "classpath:/applicationContext-servlet-test.xml" })
public class TestServicioProducto {

	@Autowired
	private ServicioProducto pServicio;
	
	@Autowired
	private UnidadMedidaDao uMedidaDao;
	
	//private static final Logger LOGGER = Logger.getLogger(TestServicioProducto.class);
	private Producto prod;
	private List<Producto> prodList;
	private UnidadMedida unidadm;
	private List<UnidadMedida> uMedList;
	
	@Before
    public void setUp() {
        //PropertyConfigurator.configure("classpath:/log4j.properties");
		System.out.println("@Before - setUp");
		 prod = new Producto();
		 prodList = new ArrayList<Producto>();
		 unidadm= new UnidadMedida();
		 uMedList = new ArrayList<UnidadMedida>();
    }
	
	@Test
	public void AgregarProductoTest(){
		/*boolean e = (uMedidaDao ==null);
		System.out.println(e);*/
		String usuario = "admin";
		unidadm.setNombre("libras");
		unidadm.setNombreCorto("lbs");
		unidadm.setFechacrea(new Date());
		unidadm.setUsuariocrea(usuario);
		UnidadMedida t = uMedidaDao.create(unidadm);
		Assert.assertEquals(unidadm.getNombre(), t.getNombre());
		//ahora agregamos varios productos
		prod = pServicio.CrearProducto("Producto 1", unidadm, usuario );
		Assert.assertEquals("Producto 1", prod.getNombre());
		prod = pServicio.CrearProducto("Producto 2", unidadm, usuario );
		Assert.assertEquals("Producto 2", prod.getNombre());
		prod = pServicio.CrearProducto("Producto 3", unidadm, usuario );
		Assert.assertEquals("Producto 3", prod.getNombre());
		
		//ahora probamos que ObtenerTodosProductos funcione
		prodList = pServicio.ObtenerTodosProductos();
		Assert.assertEquals(3, prodList.size());
		
		System.out.println("---Productos agregados----");
		for(Producto p : prodList){
			System.out.println(p.getId()+" '" + p.getNombre()+ "' " + p.getFechacrea());
		}
		
		
	}
 
	
	
	

}