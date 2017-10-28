package springbootstarter.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import springbootstarter.SpringBootCodingApplication;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SpringBootCodingApplication.class)
public class ThreadingServiceImplTest {

	@Autowired
	private IThreadingService service;

	@Test
	public void testGreeting_TestCase1() {
		boolean result = service.detectDeadlock("thread1", "thread2");
		assertEquals(result, false);

	}

	@Test
	public void testGreeting_TestCase2() throws InterruptedException {
		List<String> result = service.createDeadlock();
		assertEquals(result.size(), 2);
		Thread.sleep(5000);
		boolean deadlockResult = service.detectDeadlock(result.get(0), result.get(1));
		assertEquals(deadlockResult, true);
	}
	
	@Test
	public void testGreeting_TestCase3() throws InterruptedException {
		List<String> result = service.createDeadlock();
		assertEquals(result.size(), 2);
		Thread.sleep(5000);
		boolean deadlockResult = service.detectDeadlock(result.get(0), "wrongThreadName");
		assertEquals(deadlockResult, false);
	}
	
	@Test
	public void testGreeting_TestCase4() throws InterruptedException {
		List<String> result = service.createDeadlock();
		assertEquals(result.size(), 2);
		Thread.sleep(5000);
		boolean deadlockResult = service.detectDeadlock("wrongThreadName", result.get(0));
		assertEquals(deadlockResult, false);
	}
}
