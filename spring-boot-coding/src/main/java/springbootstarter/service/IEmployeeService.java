package springbootstarter.service;

import java.util.List;

import springbootstarter.jpa.entity.Employee;

public interface IEmployeeService {
	
	List<Employee> getAllEmployees();
	
	Employee getEmployee(Integer id);
	
	Employee addEmployee(Employee emp);
	
	void deleteEmployee(Integer id);

}
