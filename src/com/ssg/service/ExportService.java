package com.ssg.service;

import java.util.List;

import com.ssg.container.Container;
import com.ssg.dto.Article;
import com.ssg.util.Util;

public class ExportService {

	public void makeHtml() {
		
		List<Article> articles = Container.articleDao.articles;
		
		for (Article article : articles) {
			String fileName = article.articleId + ".html";
			String html = "<meta charset=\"UTF-8\">";
			html += "<div>번호 : " + article.articleId + "</div>";
			html += "<div>날짜 : " + article.regDate + "</div>";
			html += "<div>작성자 : " + article.memberName + "</div>";
			html += "<div>제목 : " + article.title + "</div>";
			html += "<div>내용 : " + article.body + "</div>";
			
			// 페이징
			if (article.articleId > 1) {
				html += "<div><a href=\"" + (article.articleId - 1) + ".html\">이전글</a></div>";
			}
			if (article.articleId < articles.size()) {
				html += "<div><a href=\"" + (article.articleId + 1) + ".html\">다음글</a></div>";
			}
			
			// 경로는 메인폴더
			Util.writeFileContents(fileName, html);
		}
		
	}

}
