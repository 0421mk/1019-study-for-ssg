package com.ssg.dao;

import java.util.ArrayList;
import java.util.List;

import com.ssg.dto.Article;

public class ArticleDao {
	public List<Article> articles;
	
	public ArticleDao() {
		articles = new ArrayList<>();
	}

	public void add(Article article) {
		articles.add(article);
	}

	public List<Article> getSearchedArticlesByKeyword(String searchKeyword) {
		
		List<Article> searchedArticles = new ArrayList<>();
	
		if (searchKeyword.length() > 0) {
			for (Article article : articles) {
				if (article.title.contains(searchKeyword)) {
					searchedArticles.add(article);
				}
			}
		} else {
			searchedArticles = articles;
		}
		
		return searchedArticles;
	}

	public Article getFoundArticleById(int foundId) {
		
		Article foundArticle = null;

		for (Article article : articles) {
			if (article.articleId == foundId) {
				foundArticle = article;
			}
		}

		return foundArticle;
		
	}

	public void modify(Article foundArticle, String title, String body) {
		
		foundArticle.title = title;
		foundArticle.body = body;
		
	}

	public void remove(Article foundArticle) {
		
		articles.remove(foundArticle);
		
	}
	
	public void makeTestData() {
		articles.add(new Article("제목1", "내용1", 1, "admin"));
		articles.add(new Article("제목2", "내용2", 1, "admin"));
		articles.add(new Article("제목3", "내용3", 1, "admin"));

		System.out.println("게시물의 테스트 데이터를 생성했습니다.");
	}
}
