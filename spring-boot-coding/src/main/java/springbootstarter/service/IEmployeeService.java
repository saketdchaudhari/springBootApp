package springbootstarter.service;

import java.util.List;

import springbootstarter.jpa.entity.Employee;

public interface IEmployeeService {
	
	Employee getEmployee(Long id);
	
	List<Employee> getAllEmployees();
	
	Employee addEmployee(Employee emp);
	
	void deleteEmployee(Long id);

}
