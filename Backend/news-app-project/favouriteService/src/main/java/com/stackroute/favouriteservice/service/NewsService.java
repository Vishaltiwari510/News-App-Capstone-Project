package com.stackroute.favouriteservice.service;

import java.util.List;

import com.stackroute.favouriteservice.domain.News;
import com.stackroute.favouriteservice.exception.NewsAlreadyExistsException;
import com.stackroute.favouriteservice.exception.NewsNotFoundException;



public interface NewsService 
{
	boolean saveNews(News news) throws NewsAlreadyExistsException;
	
	boolean deleteNewsById(int id) throws NewsNotFoundException;
	
	List<News> getNews(String userId) throws NewsNotFoundException;
}
