package springbootstarter.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import springbootstarter.SpringBootCodingApplication;
import springbootstarter.jpa.entity.Employee;
import springbootstarter.repository.IEmployeeRepository;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SpringBootCodingApplication.class)
public class EmployeeServiceImplTest {
	
	@InjectMocks
	private EmployeeServiceImpl service;
	
	@Mock
	private IEmployeeRepository repository;
	
	@Test
	public void getEmployeeTest1() {
		final ArgumentCaptor<Long> idCaptor = ArgumentCaptor.forClass(Long.class);
		when(repository.findOne(anyInt())).thenReturn(new Employee(1, "Test Name", 8000));
		Employee emp = service.getEmployee(Long.valueOf(1));
		verify(repository, times(1)).findOne(idCaptor.capture());
		verifyNoMoreInteractions(repository);
		assertEquals(Integer.valueOf(1), idCaptor.getValue());
		assertEquals(Integer.valueOf(1), emp.getEmpId());
		assertEquals("Test Name", emp.getName());
		assertEquals(Integer.valueOf(8000), emp.getSalary());
	}
	
	@Test
	public void getEmployeeTest2() {
		final ArgumentCaptor<Long> idCaptor = ArgumentCaptor.forClass(Long.class);
		when(repository.findOne(anyInt())).thenReturn(null);
		Employee emp = service.getEmployee(Long.valueOf(1));
		verify(repository, times(1)).findOne(idCaptor.capture());
		verifyNoMoreInteractions(repository);
		assertEquals(null, emp);
	}
	
	@Test
	public void getAllEmployeesTest1() {
		when(repository.findOne(anyInt())).thenReturn(null);
		List<Employee> emps = service.getAllEmployees();
		verify(repository, times(1)).findAll();
		verifyNoMoreInteractions(repository);
		assertEquals(null, emps);
	}

	@Test
	public void deleteEmployeeTest1() {
		final ArgumentCaptor<Long> idCaptor = ArgumentCaptor.forClass(Long.class);
		when(repository.findOne(anyInt())).thenReturn(null);
		service.deleteEmployee(Long.valueOf(1));
		verify(repository, times(1)).delete(idCaptor.capture());
		verifyNoMoreInteractions(repository);
		assertEquals(Integer.valueOf(1), idCaptor.getValue());
	}
}
