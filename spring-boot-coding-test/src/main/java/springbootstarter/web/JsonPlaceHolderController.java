package springbootstarter.web;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import springbootstarter.dto.Post;
import springbootstarter.service.IJsonPlaceHolderService;

@RestController
@RequestMapping("/jsonPlaceHolder")
public class JsonPlaceHolderController {
private static final Logger logger = LogManager.getLogger(JsonPlaceHolderController.class);
	
	@Autowired
    private IJsonPlaceHolderService service;
	
	@RequestMapping(value = "/posts", method = RequestMethod.GET)
    public ResponseEntity<List<Post>> getPosts() {
    	logger.info("Received gretting request.");
    	List<Post> posts = service.getAllPosts();
    	return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
    }
}
