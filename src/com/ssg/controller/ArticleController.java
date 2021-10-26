package com.ssg.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.ssg.dto.Article;
import com.ssg.service.ArticleService;

public class ArticleController extends Controller {

	private Scanner scanner;
	private ArticleService articleService;

	public ArticleController(Scanner scanner) {
		this.scanner = scanner;
		this.articleService = new ArticleService();
	}

	public void doAction(String command, String actionMethodName) {
		switch (actionMethodName) {
		case "write":
			doWrite();
			break;
		case "modify":
			doModify(command);
			break;
		case "delete":
			doDelete(command);
			break;
		case "list":
			showList(command);
			break;
		case "detail":
			showDetail(command);
			break;
		default :
			System.out.println("잘못된 명령어입니다.");
			break;
		}
	}

	private void doWrite() {
		
		if (loginedMember == null) {
			System.out.println("로그인 후 이용해주세요.");
			return;
		}
		
		System.out.printf("제목 : ");
		String title = scanner.nextLine();

		System.out.printf("내용 : ");
		String body = scanner.nextLine();

		Article article = new Article(title, body, loginedMember.memberId, loginedMember.name);
		articleService.add(article);

		System.out.println(article.articleId + "번 게시물이 생성되었습니다.");
	}

	private void showList(String command) {

		String searchKeyword = command.substring("article list".length()).trim();

		List<Article> searchedArticles = articleService.getSearchedArticlesByKeyword(searchKeyword);
		
		if (searchedArticles.size() == 0) {
			System.out.println("검색된 게시물이 없습니다.");
			return;
		}

		System.out.printf(" 번호  / 조회수 /   제목\n");
		for (Article article : searchedArticles) {
			System.out.printf("%4d / %4d / %12s\n", article.articleId, article.hit, article.title);
		}

	}

	private void showDetail(String command) {
		command = command.trim();
		String[] commandBits = command.split(" ");

		if (commandBits.length > 3) {
			System.out.println("명령어를 잘못입력하셨습니다.");
			return;
		}

		String checkStr = commandBits[2];
		int foundId = articleService.getFoundIdByCheckStr(checkStr);

		if (foundId == 0) {
			System.out.println("숫자만 입력해주세요.");
			return;
		}

		Article foundArticle = articleService.getFoundArticleById(foundId);

		if (foundArticle == null) {
			System.out.println("게시물이 존재하지 않습니다.");
			return;
		}

		foundArticle.increaseHit();

		System.out.println("번호 : " + foundArticle.articleId);
		System.out.println("작성날짜 : " + foundArticle.regDate);
		System.out.println("작성자 : " + foundArticle.memberName);
		System.out.println("조회수 : " + foundArticle.hit);
		System.out.println("제목 : " + foundArticle.title);
		System.out.println("내용 : " + foundArticle.body);
		System.out.println("=============");

	}

	private void doModify(String command) {
		
		if (loginedMember == null) {
			System.out.println("로그인 후 이용해주세요.");
			return;
		}
		
		command = command.trim();
		String[] commandBits = command.split(" ");

		if (commandBits.length > 3) {
			System.out.println("명령어를 잘못입력하셨습니다.");
			return;
		}

		String checkStr = commandBits[2];
		int foundId = articleService.getFoundIdByCheckStr(checkStr);

		if (foundId == 0) {
			System.out.println("숫자만 입력해주세요.");
			return;
		}

		Article foundArticle = articleService.getFoundArticleById(foundId);

		if (foundArticle == null) {
			System.out.println("게시물이 존재하지 않습니다.");
			return;
		}
		
		if (loginedMember.memberId != foundArticle.memberId) {
			System.out.println("권한이 없습니다.");
			return;
		}

		System.out.printf("제목 : ");
		String title = scanner.nextLine();

		System.out.printf("내용 : ");
		String body = scanner.nextLine();

		articleService.modify(foundArticle, title, body);
	
		System.out.println(foundArticle.articleId + "번 게시물이 수정되었습니다.");

	}

	private void doDelete(String command) {
		
		if (loginedMember == null) {
			System.out.println("로그인 후 이용해주세요.");
			return;
		}
		
		command = command.trim();
		String[] commandBits = command.split(" ");

		if (commandBits.length > 3) {
			System.out.println("명령어를 잘못입력하셨습니다.");
			return;
		}

		String checkStr = commandBits[2];
		int foundId = articleService.getFoundIdByCheckStr(checkStr);

		if (foundId == 0) {
			System.out.println("숫자만 입력해주세요.");
			return;
		}

		Article foundArticle = articleService.getFoundArticleById(foundId);

		if (foundArticle == null) {
			System.out.println("게시물이 존재하지 않습니다.");
			return;
		}
		
		if (loginedMember.memberId != foundArticle.memberId) {
			System.out.println("권한이 없습니다.");
			return;
		}

		articleService.remove(foundArticle);

		System.out.println(foundArticle.articleId + "번 게시물이 삭제되었습니다.");

	}
}
