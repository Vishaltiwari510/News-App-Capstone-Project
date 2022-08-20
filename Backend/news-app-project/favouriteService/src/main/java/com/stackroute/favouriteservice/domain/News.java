package com.stackroute.favouriteservice.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table
public class News 
{
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String userId;
	private String title;
	@Size(max = 500)
	private String description;
	@Size(max = 1000)
	private String content;
	private String publishedAt;
	private String sourceWebsiteName;
	private String url;
private String urlToImage;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public News() {
		super();
		// TODO Auto-generated constructor stub
	}

	public News(int id, String userId, String title, @Size(max = 500) String description,
			@Size(max = 1000) String content, String publishedAt, String sourceWebsiteName, String url,
			String urlToImage) {
		super();
		this.id = id;
		this.userId = userId;
		this.title = title;
		this.description = description;
		this.content = content;
		this.publishedAt = publishedAt;
		this.sourceWebsiteName = sourceWebsiteName;
		this.url = url;
		this.urlToImage = urlToImage;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPublishedAt() {
		return publishedAt;
	}

	public void setPublishedAt(String publishedAt) {
		this.publishedAt = publishedAt;
	}

	public String getSourceWebsiteName() {
		return sourceWebsiteName;
	}

	public void setSourceWebsiteName(String sourceWebsiteName) {
		this.sourceWebsiteName = sourceWebsiteName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrlToImage() {
		return urlToImage;
	}

	public void setUrlToImage(String urlToImage) {
		this.urlToImage = urlToImage;
	}
}
