package springbootstarter.service;

import java.util.List;

import springbootstarter.dto.Post;

public interface IExternalRestService {
	List<Post> getAllPosts();
}
