package informatica;

// import static com.jayway.restassured.matcher.RestAssuredMatchers.*;
import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
// import static com.jayway.restassured.http.ContentType.
import static org.hamcrest.Matchers.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import informatica.domain.Event;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = EventApp.class)
// @ContextConfiguration({ "file:src/test/resources/applicationContext.xml" })
@WebIntegrationTest
public class IntegrationTest {
	
	private static final String URL = "http://localhost:8080";
	
	private String json;

	@Before
	public void setUp() throws Exception {
		Event event = new Event(-999L, new Date(System.currentTimeMillis() + 14 * 24 * 3600 * 1000L), 
				   					  new Date(System.currentTimeMillis() + 15 * 24 * 3600 * 1000L), "test");
		this.json = null;
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			this.json = objectMapper.writeValueAsString(event);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		System.out.println("json : " + json);
	}

	@Test
	public void testResponseHeader() {
		// ResponseEntity<String> response = this.restTemplate.getForEntity(URL + "/events", String.class);
		when().get(URL + "/events").then().statusCode(200).contentType("application/json");
		// when().log().all().then().log().all();
	}

	@Test
	public void testAddAndGet() {
		
		given().when().contentType("application/json").body(this.json).post(URL + "/add").then().statusCode(200).log().all();

		given().when().get(URL + "/event?name=test").then().statusCode(200).body("name", is("test"));
		
	}

	@Test
	public void testDeleteAndGet() {
		
		given().when().contentType("application/json").body(this.json).post(URL + "/add").then().statusCode(200);
		
		given().when().delete(URL + "/delete?id=-999").then().statusCode(200);
		
		given().when().get(URL + "/events").then().statusCode(200).body(not(contains(-999)));
		
		// given().when().get(URL + "/event?name=test").then().body(empty()).log().all();
		
	}
}