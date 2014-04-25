package org.vmgs.com.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.vmgs.com.servicios.ServicioCredencial;
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
		//el numero de roles debe ser 4
		Assert.assertEquals(4, RolList.size());
		
		
	}
	
	/*Que el servicio sea capaz de traer una lista todos  los usuarios del sistema*/
	@Ignore
	@Test	
	public void buscarTodosUsuariosTest(){
		Assert.assertEquals(4, service.buscarTodosUsuarios().size());
	}
	
	/*Que pueda traer un Usuario por medio del Nombre */
	@Test
	public void getUsuarioxNombreTest(){
		user = service.getUsuarioxNombre("admin");
		Assert.assertNotNull(user);
		Assert.assertEquals("admin", user.getNombre());
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
	
	//Que pueda obtener el Rol por medio del Id
	@Test
	public void obtenerRolxIdTest(){
	   Rol rol = service.obtenerRolxId(3L);
	   Assert.assertNotNull(rol);
	   //el rol es de nombre 
	   Assert.assertEquals("secretary", rol.getNombre());
	   
	}
	
	//Que pueda obtener el Usuario con sus roles
	@Test
	public void getUsuariowRolesTest(){
		user = service.getUsuariowRoles(4L);
		//el usuario con id = 4 es secretataria
		Assert.assertEquals("secretaria", user.getNombre());
		//ahora comprobamos que traiga los roles correspondientes
		List<Rol> RolList = new ArrayList(user.getRoles());
		Assert.assertNotNull(RolList);
		//ahora comprobamos que sean los roles user y secretary
		for(Rol r : RolList ){
			boolean resp = ("user".equals(r.getNombre()) || "secretary".equals(r.getNombre()));
			 Assert.assertTrue(resp);
		}
	}
	
	//se puede probar con este test leer la imagen y guardarla atravez de jpa
	@Test
	public void saveUsuarioImagenTest(){
		String fileLocation= "C:\\Users\\vguerrero\\Desktop\\hermosura.jpg";
		byte[] bites = Util.convertFileToByteArray(fileLocation);
		System.out.println("Array de bytes: " + bites);
		user = new Usuario();
		user.setNombre("capristino");
		user.setPassword("test");
		user.setImage(bites);
		Respuesta resultado = service.GuardarUsuario(user);
		Assert.assertTrue(resultado.getRespuesta());//si guardo correctamente
		System.out.println("Se guardo el usuario con el id: " + user.getId());
		Assert.assertTrue(user.getId() > 0);
		Usuario testU = service.getUsuarioxId(user.getId());
		Assert.assertEquals(user.getNombre(), testU.getNombre());
		Assert.assertNotNull(testU.getImage());
		//escribimos la imagen en disco para saber si sirve
		 try{
			FileOutputStream fos = new FileOutputStream("E:\\testImage.jpg"); 
			fos.write(testU.getImage());
			fos.close();
		 }catch(Exception e){
            e.printStackTrace();
		 }
	}
	
	@Ignore //esta anotacion hace que se ignore y no se testee este metodo
	@Test
	public void ignorarTest(){
		LOGGER.info("Este test sera ignorado");
	}
	
	

}