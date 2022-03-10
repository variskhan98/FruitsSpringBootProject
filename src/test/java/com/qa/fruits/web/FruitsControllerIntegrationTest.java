package com.qa.fruits.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.fruits.domain.Fruits;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts= {"classpath:fruits-schema.sql", "classpath:fruits-data.sql"}, executionPhase=ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class FruitsControllerIntegrationTest {

	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Test
	void testCreate() throws Exception {
		Fruits testFruits = new Fruits(null, "banana", 2, false);
		String testFruitsAsJSON= this.mapper.writeValueAsString(testFruits);
		RequestBuilder req = post("/create").contentType(MediaType.APPLICATION_JSON).content(testFruitsAsJSON);
		
		Fruits testCreatedFruits = new Fruits(3, "banana", 2, false);
		String testCreatedFruitsAsJSON = this.mapper.writeValueAsString(testCreatedFruits);
		ResultMatcher checkStatus = status().isCreated();
		ResultMatcher checkBody = content().json(testCreatedFruitsAsJSON);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
		
	}
	
	@Test
	void getAllTest() throws Exception {
		RequestBuilder req = get("/getAll");
		List<Fruits> testFru = List.of(new Fruits(1, "apple", 2, true),new Fruits(2, "watermelon", 2, true));
		String json = this.mapper.writeValueAsString(testFru);
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(json);
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
		
	}
	
	@Test
	void getTest() throws Exception {
		RequestBuilder req = get("/get/1");
		String fruitsAsJson = this.mapper.writeValueAsString(new Fruits(1, "apple", 2, true));
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(fruitsAsJson);
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void getByNameTest() throws Exception {
		RequestBuilder req = get("/getByName/apple");
		List<Fruits> testFru = List.of(new Fruits(1, "apple", 2, true));
		String json = this.mapper.writeValueAsString(testFru);
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(json);
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
//	@Test
//	void getByQuantityTest() throws Exception {
//		RequestBuilder req = get("/getByQuantity/2");
//		List<Fruits> testFru = List.of(new Fruits(1, "apple", 2, true));
//		String json = this.mapper.writeValueAsString(testFru);
//		ResultMatcher checkStatus = status().isOk();
//		ResultMatcher checkBody = content().json(json);
//		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);	
		
//	}
	
	@Test
	void testReplace() throws Exception {
		Fruits testFruits = new Fruits(null, "banana", 4, false);
		String testFruitsAsJson = this.mapper.writeValueAsString(testFruits);
		RequestBuilder req = put("/replace/1").contentType(MediaType.APPLICATION_JSON).content(testFruitsAsJson);
		Fruits testCreatedFruits = new Fruits(1, "banana", 4, false);
		String testCreatedFruitsAsJSON = this.mapper.writeValueAsString(testCreatedFruits);
		ResultMatcher checkStatus = status().isAccepted();
		ResultMatcher checkBody = content().json(testCreatedFruitsAsJSON);
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
			
	}
	
	@Test
	void testRemove() throws Exception {
		this.mvc.perform(delete("/remove/1")).andExpect(status().isNoContent());
	}
}
