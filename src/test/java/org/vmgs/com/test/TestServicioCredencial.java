package org.vmgs.com.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.vmgs.com.servicios.ServicioCredencial;
import org.vmgs.com.clases.Rol;
import java.util.List;

@Test
@ContextConfiguration(locations = { "classpath:spring-test-config.xml" })
public class TestServicioCredencial extends AbstractTestNGSpringContextTests {

	@Autowired
	ServicioCredencial service;
 
	@Test()
	void testEmailGenerator() {
 
		/*String email = emailGenerator.generate();
		System.out.println(email);
 
		Assert.assertNotNull(email);
		Assert.assertEquals(email, "feedback@yoursite.com");*/
		List<Rol> RolList = service.buscarTodosRoles();
		
		Assert.assertNotNull(RolList);
		
	}


}