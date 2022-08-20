package com.stackroute.favouriteservice.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.favouriteservice.domain.News;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class NewsRepositoryTest 
{
	private static Logger logger = LoggerFactory.getLogger(NewsRepositoryTest.class);
	
	@Autowired
	private NewsRepository newsRepository;
	
	News news = new News();
	@BeforeEach
	public void setup() {
		news.setUserId("12345");
		news.setTitle("hot donao");
		news.setContent("that 70 show");;
		news.setDescription("love they had");
		news.setPublishedAt("09/09/2019");
		news.setSourceWebsiteName("ccn");
		news.setUrl("lki");
		news.setUrlToImage("pois");
		
	}
	@AfterEach
	public void tearDown()
	{
		newsRepository.deleteAllInBatch();
	}

	@Test
	public void testSave() throws Exception
	{	
		newsRepository.save(news);
		boolean b1=newsRepository.existsById(news.getId());
		int val1 = (true) ? 1 : 0;
		int val2 = (b1) ? 1 : 0;
		assertEquals(val1,val2);
	}
	
	@Test
	public void testDelete()
	{
		newsRepository.save(news);
		assertEquals(true,newsRepository.existsById(news.getId()));
		newsRepository.delete(news);
		assertEquals(false,newsRepository.existsById(news.getId()));
	}
}
