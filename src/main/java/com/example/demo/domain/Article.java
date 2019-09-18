package com.example.demo.domain;

import java.util.List;

public class Article {
	
	
	private Integer id;
	private String name;
	private String content;
	
	
	public int getIntId() {
		return id.intValue();
	}
	
	private List<Comments> commentlist;
	
	@Override
	public String toString() {
		return "Article [id=" + id + ", name=" + name + ", content=" + content + ", commentlist=" + commentlist + "]";
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public List<Comments> getCommentlist() {
		return commentlist;
	}
	public void setCommentlist(List<Comments> commentlist) {
		this.commentlist = commentlist;
	}
	
	
	
}