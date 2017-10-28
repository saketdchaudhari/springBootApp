package springbootstarter.web;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import springbootstarter.jpa.entity.Employee;
import springbootstarter.service.IEmployeeService;

@RestController
@RequestMapping("employees")
public class EmployeeController {
	private static final Logger logger = LogManager.getLogger(GreetingController.class);

	@Autowired
	private IEmployeeService service;

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public Employee findEmployee(@PathVariable("id") Integer employeeId) {
		logger.info("Received gretting request.");
		return service.getEmployee(employeeId);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Employee>> findAllEmployees() {
		logger.info("Received gretting request.");
		List<Employee> employees = service.getAllEmployees();
		for (Employee employee : employees) {
			Link employeeLink = ControllerLinkBuilder
					.linkTo(ControllerLinkBuilder.methodOn(EmployeeController.class).findEmployee(employee.getEmpId()))
					.withRel("employee");
			employee.add(employeeLink);
		}
		return ResponseEntity.ok(employees);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
		logger.info("Received gretting request.");
		Employee persistedEmployee = service.addEmployee(employee);
		Link employeeLink = ControllerLinkBuilder
				.linkTo(ControllerLinkBuilder.methodOn(EmployeeController.class).findEmployee(persistedEmployee.getEmpId()))
				.withRel("employee");
		Link allEmployeeLink = ControllerLinkBuilder
				.linkTo(ControllerLinkBuilder.methodOn(EmployeeController.class).findAllEmployees())
				.withRel("all-employees");
		persistedEmployee.add(employeeLink);
		persistedEmployee.add(allEmployeeLink);
		return new ResponseEntity<Employee>(persistedEmployee, HttpStatus.OK);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> removeEmployee(@PathVariable("id") int employeeId) {
		logger.info("Received gretting request.");
		service.deleteEmployee(employeeId);
		return new ResponseEntity<String>("Deleted", HttpStatus.OK);
	}
}
