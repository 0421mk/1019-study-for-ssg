package com.ssg.dao;

import java.util.ArrayList;
import java.util.List;

import com.ssg.dto.Article;

public class ArticleDao {
	public List<Article> articles;
	
	public ArticleDao() {
		articles = new ArrayList<>();
	}
}
