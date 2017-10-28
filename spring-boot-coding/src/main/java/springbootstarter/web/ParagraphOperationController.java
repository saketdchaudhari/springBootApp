package springbootstarter.web;

import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import springbootstarter.dto.WordCount;
import springbootstarter.service.IParagraphService;

@RestController
@RequestMapping("/paragraph")
public class ParagraphOperationController {

	private static final Logger logger = LogManager.getLogger(ParagraphOperationController.class);

	@Autowired
	private IParagraphService service;

	@RequestMapping(value = "/wordCount", method = RequestMethod.GET)
	public ResponseEntity<Set<WordCount>> findWordCount(@RequestParam("paragraph") String paragraph) {
		logger.info("Received gretting request.");
		Set<WordCount> wordCount = service.findWordCount(paragraph);
		return new ResponseEntity<Set<WordCount>>(wordCount, HttpStatus.OK);
	}
}
