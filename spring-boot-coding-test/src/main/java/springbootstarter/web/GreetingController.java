package springbootstarter.web;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import springbootstarter.service.IGreetingService;

@RestController
@RequestMapping("/greeting")
public class GreetingController {
	
	private static final Logger logger = LogManager.getLogger(GreetingController.class);
	
	@Autowired
    private IGreetingService service;
	
	@RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<String> greetUser() {
    	logger.info("Received gretting request.");
    	return new ResponseEntity<String>(service.greetUser(), HttpStatus.OK);
    }
}
