package springbootstarter.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import springbootstarter.SpringBootCodingApplication;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SpringBootCodingApplication.class)
public class GreetingServiceImplTest {
	
	@Autowired
	private IGreetingService service;
	
	@Test
	public void testGreeting() {
		String result = service.greetUser();
		assertEquals("Hello World!", result);
	}
}
