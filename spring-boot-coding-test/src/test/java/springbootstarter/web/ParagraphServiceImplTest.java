package springbootstarter.web;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.http.entity.ContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import springbootstarter.dto.WordCount;
import springbootstarter.service.IParagraphService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ParagraphServiceImplTest {
	
	@Autowired
    private MockMvc mvc;
 
    @MockBean
    private IParagraphService service;
    
    @Test
    public void greetUserTest()
      throws Exception {
    	final String input = "A1 a1 b2 b2 b3 c1";
    	final ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
    	
    	when(service.findWordCount(input)).thenReturn(prepareExpectedResult());
    	
        mvc.perform(get("/paragraph/wordCount").accept(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk())
          .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
          .andExpect(MockMvcResultMatchers.content().json(jsonContent));
        
        verify(service, times(1)).findWordCount(captor.capture());
        
    }

	private Set<WordCount> prepareExpectedResult() {
		Set<WordCount> result = new TreeSet<WordCount>();
		result.add(new WordCount("A1", 1));
		result.add(new WordCount("a1", 1));
		result.add(new WordCount("b2", 2));
		result.add(new WordCount("c1", 1));
		return result;
	}
	
	
	/* @Test
	    public void testSerialize() throws Exception {
	        VehicleDetails details = new VehicleDetails("Honda", "Civic");
	        // Assert against a `.json` file in the same package as the test
	        assertThat(this.json.write(details)).isEqualToJson("expected.json");
	        // Or use JSON path based assertions
	        assertThat(this.json.write(details)).hasJsonPathStringValue("@.make");
	        assertThat(this.json.write(details)).extractingJsonPathStringValue("@.make")
	                .isEqualTo("Honda");
	    }

	    @Test
	    public void testDeserialize() throws Exception {
	        String content = "{\"make\":\"Ford\",\"model\":\"Focus\"}";
	        assertThat(this.json.parse(content))
	                .isEqualTo(new VehicleDetails("Ford", "Focus"));
	        assertThat(this.json.parseObject(content).getMake()).isEqualTo("Ford");
	    }*/
}
