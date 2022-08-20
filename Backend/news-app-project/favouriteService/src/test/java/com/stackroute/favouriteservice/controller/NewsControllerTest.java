package com.stackroute.favouriteservice.controller;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.favouriteservice.domain.News;
import com.stackroute.favouriteservice.service.NewsService;


@WebMvcTest(NewsController.class)
public class NewsControllerTest 
{
	@Autowired
	private MockMvc mockMvc;
	
	@InjectMocks
	private NewsController newsController;

	@MockBean
	private NewsService newsService;

	private News news;

	private ObjectMapper objectMapper;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
		
		objectMapper = new ObjectMapper();
		mockMvc = MockMvcBuilders.standaloneSetup(newsController).build();
		news = new News();
		news.setUserId("12345");
		news.setTitle("hot donao");
		news.setContent("that 70 show");;
		news.setDescription("love they had");
		news.setPublishedAt("09/09/2019");
		news.setSourceWebsiteName("ccn");
		news.setUrl("lki");
		news.setUrlToImage("pois");
		
	}

	@Test
	public void testSave() throws Exception {
		String token = "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiIxMjM0NSIsImlhdCI6MTU1NjM1MDQzOH0.FGiXKNL-M_RMYN5Ph6kG-I9Zcrb-_QvirTzLGijcmOoD8C3CH3Y5fspECd4MROCn";
		when(newsService.saveNews(news)).thenReturn(true);
		mockMvc.perform(post("/api/news").header("authorization","Bearer "+token).content(objectMapper.writeValueAsString(news))
				.contentType("application/json;charset=UTF-8")).andExpect(status().isCreated());

	}


	@Test
	public void testDelete() throws Exception {
		when(newsService.deleteNewsById(news.getId())).thenReturn(true);
		mockMvc.perform(delete("/api/news/{id}", news.getId()).contentType("application/json;charset=UTF-8")).andExpect(status().isOk());

	}
	

	
	@Test
	public void testList() throws Exception {
		String token = "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiIxMjM0NSIsImlhdCI6MTU1NjM1MDQzOH0.FGiXKNL-M_RMYN5Ph6kG-I9Zcrb-_QvirTzLGijcmOoD8C3CH3Y5fspECd4MROCn";
		List<News> news = new ArrayList<>();
		when(newsService.getNews("12345")).thenReturn(news);
		mockMvc.perform(get("/api/news").header("authorization","Bearer "+token).contentType("application/json;charset=UTF-8")).andExpect(status().isOk());

	}
}
