package com.stackroute.favouriteservice.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.favouriteservice.domain.News;
import com.stackroute.favouriteservice.exception.NewsAlreadyExistsException;
import com.stackroute.favouriteservice.exception.NewsNotFoundException;
import com.stackroute.favouriteservice.repository.NewsRepository;


@Service
public class NewsServiceImpl implements NewsService
{
	@Autowired
	private NewsRepository newsRepository;

	@Override
	public boolean saveNews(News news) throws NewsAlreadyExistsException
	{

		Optional<News> checkNews = newsRepository.findByUserIdAndTitle(news.getUserId(), news.getTitle());
		
		if(checkNews.isPresent())
			throw new NewsAlreadyExistsException("News already exists");
		
		newsRepository.save(news);
		return true;
	}
	
	@Override
	public boolean deleteNewsById(int id) throws NewsNotFoundException
	{
		News news = newsRepository.findById(id).orElse(null);
		if(news == null) {
			throw new NewsNotFoundException("News not found!");
		}
		
		newsRepository.delete(news);
		return true;
	}
	

	
	@Override
	public List<News> getNews(String userId) throws NewsNotFoundException
	{
		List<News> newsList = (List<News>) newsRepository.findByUserId(userId);
		if(newsList.size()==0)
			throw new NewsNotFoundException("News not found!");
		return newsList;
	}
}
