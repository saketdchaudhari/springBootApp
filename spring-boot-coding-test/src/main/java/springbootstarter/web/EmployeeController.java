/*package springbootstarter.web;

import java.util.List;

import javax.websocket.server.PathParam;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import springbootstarter.jpa.entity.Employee;
import springbootstarter.service.IEmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
private static final Logger logger = LogManager.getLogger(GreetingController.class);
	
	@Autowired
    private IEmployeeService service;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Employee findEmployee(@PathParam("id") String employeeId) {
    	logger.info("Received gretting request.");
    	return service.getEmployee(employeeId);
    }
	
	@RequestMapping(method = RequestMethod.GET)
    public List<Employee> findAllEmployees() {
    	logger.info("Received gretting request.");
    	return service.getAllEmployees();
    }
	
	@RequestMapping(method = RequestMethod.POST)
    public void createEmployee(@RequestBody Employee employee) {
    	logger.info("Received gretting request.");
    	service.addEmployee( employee);
    }
	
	@RequestMapping(method = RequestMethod.DELETE)
    public void removeEmployee(@PathParam("id") String employeeId) {
    	logger.info("Received gretting request.");
    	service.deleteEmployee(employeeId);
    }
}
*/