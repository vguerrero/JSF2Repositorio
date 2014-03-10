package org.vmgs.com.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.vmgs.com.servicios.ServicioCredencial;
import org.vmgs.com.clases.*;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring-beans-test.xml", "classpath:/applicationContext-servlet-test.xml" })
public class TestServicioCredencial {

	@Autowired
	ServicioCredencial service;
	
	private static final Logger LOGGER = Logger.getLogger(TestServicioCredencial.class);
	private Usuario user;
	private List<Usuario> listaUsuarios;
	
	
	@Before
    public void setUp() {
        //PropertyConfigurator.configure("classpath:/log4j.properties");
		System.out.println("@Before - setUp");
		 user = new Usuario();
		 listaUsuarios = new ArrayList<Usuario>();
    }
 
	/* Que el servicio traiga todos los roles */
	@Test
	public void getAllRolesTest() {
 				
		List<Rol> RolList = service.buscarTodosRoles();
		//System.out.println("Numero de Roles: " + RolList.size());
		LOGGER.debug("Numero de Roles: " + RolList.size());
		Assert.assertNotNull(RolList);
		
	}
	
	/*Que el servicio sea capaz de traer una lista todos  los usuarios del sistema*/
	@Test	
	public void buscarTodosUsuariosTest(){
		Assert.assertEquals(4, service.buscarTodosUsuarios().size());
	}
	
	/*Que pueda traer un Usuario por medio del Nombre */
	@Test
	public void getUsuarioxNombreTest(){
		user = service.getUsuarioxNombre("admin");
		Assert.assertNotNull(user);
	}
	
	@Test
	public void autenticacionTest(){
		//this.buscarTodosUsuariosTest();
		 //creamos un usuario invalido para que no se pueda logear
		 user.setNombre("admin");
		 user.setPassword("okmaSDKFKMASDF");//este pass es invalido
		 boolean resp = service.Autenticacion(user);
		 Assert.assertFalse(resp);
		 
		 //Ahora creamos un usuario que se pueda autenticar correctamente
		 user= new Usuario();
		 user.setNombre("admin");
		 user.setPassword("admin");
		 resp = service.Autenticacion(user);
		 Assert.assertTrue(resp);
		 
	}

	//Que pueda obtener el Usuario por medio del Id
	@Test
	public void getUsuarioxIdTest(){
		user = service.getUsuarioxId(1L);
		Assert.assertNotNull(user);
		Assert.assertEquals("admin", user.getNombre());//admin debe ser el primer Usuario
	}

}