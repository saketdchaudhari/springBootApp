package springbootstarter.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import springbootstarter.SpringBootCodingApplication;
import springbootstarter.dto.Post;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SpringBootCodingApplication.class)
public class ExternalRestServiceImplTest {

	@Mock
	private RestTemplate restTemplate;
	
	@InjectMocks
	ExternalRestServiceImpl service;

	@SuppressWarnings("unchecked")
	@Test
	public void getAllPostsTest() {
		final ArgumentCaptor<String> uriCaptor = ArgumentCaptor.forClass(String.class);
		Class<HttpEntity<String>> httpEntityClass =
	              (Class<HttpEntity<String>>)(Class)HttpEntity.class;
		Class<ParameterizedTypeReference<List<Post>>> parameterizedTypeRef = 
				(Class<ParameterizedTypeReference<List<Post>>>)(Class)ParameterizedTypeReference.class;
		
		final ArgumentCaptor<HttpEntity<String>> httpEntityCaptor = ArgumentCaptor.forClass(httpEntityClass);
		final ArgumentCaptor<HttpMethod> httpMethodCaptor = ArgumentCaptor.forClass(HttpMethod.class);
		final ArgumentCaptor<ParameterizedTypeReference<List<Post>>> parameterizedTypeReferenceCaptor = ArgumentCaptor.forClass(parameterizedTypeRef);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		
		when(restTemplate.exchange(Mockito.anyString(),
                Mockito.<HttpMethod> any(),
                Mockito.<HttpEntity<String>> any(),
                Mockito.<ParameterizedTypeReference<List<Post>>> any())).thenReturn(prepareResult());
		
		service.getAllPosts();
		
		verify(restTemplate, times(1)).exchange(uriCaptor.capture(), httpMethodCaptor.capture(), 
				httpEntityCaptor.capture(), parameterizedTypeReferenceCaptor.capture());
		verifyNoMoreInteractions(restTemplate);
		
		String URI = uriCaptor.getValue();
		HttpMethod httpMethod = httpMethodCaptor.getValue();
		
		assertEquals(URI, "http://jsonplaceholder.typicode.com/posts");
		assertEquals(httpMethod.name(), HttpMethod.GET.name());
		assertEquals(httpEntityCaptor.getValue().getHeaders().size(), 1);
		assertEquals(httpEntityCaptor.getValue().getHeaders().get("parameters"), 1);
		assertEquals(URI, "http://jsonplaceholder.typicode.com/posts");
	}

	private ResponseEntity<List<Post>> prepareResult() {
		List<Post> posts = new ArrayList<>();
		Post post = new Post(1, 1, "First post title", "First post body");
        posts.add(post);
        post = new Post(2, 2, "Second post title", "Second post body");
        posts.add(post);
		ResponseEntity<List<Post>> postsResult = new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
		return postsResult;
	}

}
