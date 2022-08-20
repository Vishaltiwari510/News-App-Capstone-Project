package com.stackroute.favouriteservice.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.favouriteservice.domain.News;
import com.stackroute.favouriteservice.exception.NewsAlreadyExistsException;
import com.stackroute.favouriteservice.exception.NewsNotFoundException;

import com.stackroute.favouriteservice.service.NewsService;

import io.jsonwebtoken.Jwts;


@RestController
@RequestMapping(path = "/api/news")
public class NewsController 
{
	
	
	@Autowired
	private NewsService newsService;
	
	public String getToken(final HttpServletRequest request) {
		  final String authHeader = request.getHeader("authorization"); 
		  final String token = authHeader.substring(7); 
		  String userId =Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody().getSubject();
		  return userId;
		
	}
	
	@PostMapping
	public ResponseEntity<?> saveNews(@RequestBody News news, HttpServletRequest request, HttpServletResponse response)
	{
	
		ResponseEntity<?> responseEntity;
		try
		{
		
			news.setUserId(this.getToken(request));
			newsService.saveNews(news);
			responseEntity = new ResponseEntity<News>(news,HttpStatus.CREATED);
		}
		catch(NewsAlreadyExistsException e)
		{
			responseEntity = new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
		}
		return responseEntity;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteNews(@PathVariable int id)
	{
		ResponseEntity<?> responseEntity;
		try
		{
			newsService.deleteNewsById(id);
			responseEntity = new ResponseEntity<String>("News deleted successfully!",HttpStatus.OK);
		}
		catch(NewsNotFoundException e)
		{
			responseEntity = new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
		
		return responseEntity;
	}
	

	
	@GetMapping
	public ResponseEntity<?> getMyFavouriteNews(HttpServletRequest request, HttpServletResponse response)
	{
		ResponseEntity<?> responseEntity;
		try
		{
			List<News> newsList = newsService.getNews(this.getToken(request));
			responseEntity = new ResponseEntity<List<News>>(newsList,HttpStatus.OK);
		}
		catch(NewsNotFoundException e)
		{
			responseEntity = new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}
}
