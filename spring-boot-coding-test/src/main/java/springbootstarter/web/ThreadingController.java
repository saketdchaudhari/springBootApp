package springbootstarter.web;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import springbootstarter.dto.Link;
import springbootstarter.service.IThreadingService;

@RestController
@RequestMapping("/threads")
public class ThreadingController {

	private static final Logger logger = LogManager.getLogger(JsonPlaceHolderController.class);

	@Autowired
	private IThreadingService service;

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<Link> createThreads() {
		logger.info("Received gretting request.");
		List<String> threadNames = service.createDeadlock();
		String linkVal = "http://localhost:9090/threads/deadlockStatus?firstThreadName=" + threadNames.get(0)
				+ "&secondThreadName=" + threadNames.get(1);
		Link link = new Link(linkVal, "deadlockStatus");
		return new ResponseEntity<Link>(link, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/deadlockStatus", method = RequestMethod.GET)
	public ResponseEntity<Boolean> deadlockStatus(@RequestParam("firstThreadName") String firstThreadName,
			@RequestParam("secondThreadName") String secondThreadName) {
		logger.info("Received gretting request.");
		boolean result = service.detectDeadlock(firstThreadName, secondThreadName);
		return new ResponseEntity<Boolean>(result, HttpStatus.OK);
	}
}