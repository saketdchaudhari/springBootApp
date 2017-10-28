package springbootstarter.web;

import java.net.URI;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
		URI location = ServletUriComponentsBuilder
				.fromCurrentServletMapping()
				.pathSegment("threads", "deadlockStatus")
				.queryParam("firstThreadName", threadNames.get(0))
				.queryParam("secondThreadName", threadNames.get(1))
				.build().toUri();
		Link link = new Link(location.toString(), "deadlockStatus");
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