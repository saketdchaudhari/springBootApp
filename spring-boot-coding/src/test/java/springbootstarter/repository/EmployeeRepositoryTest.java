package springbootstarter.repository;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import springbootstarter.SpringBootCodingApplication;
import springbootstarter.jpa.entity.Employee;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SpringBootCodingApplication.class)
public class EmployeeRepositoryTest {
	
	@Autowired
	IEmployeeRepository repository;
	
	@Test
	@Transactional
	@Rollback(true)
	public void createEmployeeTest() {
		Employee emp = new Employee(null, "Test name 1", 2000);
		Employee persistedEmp = repository.save(emp);
		assertEquals(Integer.valueOf(1), persistedEmp.getEmpId());
		assertEquals(emp.getName(), persistedEmp.getName());
		assertEquals(emp.getSalary(), persistedEmp.getSalary());
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void createEmployeeTest1() {
		Employee emp = new Employee(null, "Test name 1", 2000);
		Employee persistedEmp = repository.save(emp);
		Employee persistedEmp1 = repository.findOne(1);
		assertEquals(Integer.valueOf(1), persistedEmp1.getEmpId());
	}
}
