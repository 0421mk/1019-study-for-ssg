package com.ssg.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.ssg.dto.Article;

public class ArticleController extends Controller {

	private Scanner scanner;
	private List<Article> articles;

	public ArticleController(Scanner scanner, List<Article> articles) {
		this.scanner = scanner;
		this.articles = articles;
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
		articles.add(article);

		System.out.println(article.articleId + "번 게시물이 생성되었습니다.");
	}

	private void showList(String command) {

		if (articles.size() == 0) {
			System.out.println("게시물이 없습니다.");
			return;
		}

		String searchKeyword = command.substring("article list".length()).trim();

		List<Article> searchedArticles = new ArrayList<>();

		if (searchKeyword.length() > 0) {
			for (Article article : articles) {
				if (article.title.contains(searchKeyword)) {
					searchedArticles.add(article);
				}
			}

			if (searchedArticles.size() == 0) {
				System.out.println("검색된 게시물이 없습니다.");
				return;
			}
		} else {
			searchedArticles = articles;
		}

		// Article article => 껍데기
		// articels => 실제 데이터

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
		int foundId = getFoundIdByCheckStr(checkStr);

		if (foundId == 0) {
			System.out.println("숫자만 입력해주세요.");
			return;
		}

		Article foundArticle = getFoundArticleById(foundId);

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
		
		command = command.trim();
		String[] commandBits = command.split(" ");

		if (commandBits.length > 3) {
			System.out.println("명령어를 잘못입력하셨습니다.");
			return;
		}

		String checkStr = commandBits[2];
		int foundId = getFoundIdByCheckStr(checkStr);

		if (foundId == 0) {
			System.out.println("숫자만 입력해주세요.");
			return;
		}

		Article foundArticle = getFoundArticleById(foundId);

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

		foundArticle.title = title;
		foundArticle.body = body;

		System.out.println(foundArticle.articleId + "번 게시물이 수정되었습니다.");

	}

	private void doDelete(String command) {
		command = command.trim();
		String[] commandBits = command.split(" ");

		if (commandBits.length > 3) {
			System.out.println("명령어를 잘못입력하셨습니다.");
			return;
		}

		String checkStr = commandBits[2];
		int foundId = getFoundIdByCheckStr(checkStr);

		if (foundId == 0) {
			System.out.println("숫자만 입력해주세요.");
			return;
		}

		Article foundArticle = getFoundArticleById(foundId);

		if (foundArticle == null) {
			System.out.println("게시물이 존재하지 않습니다.");
			return;
		}
		
		if (loginedMember.memberId != foundArticle.memberId) {
			System.out.println("권한이 없습니다.");
			return;
		}

		articles.remove(foundArticle);

		System.out.println(foundArticle.articleId + "번 게시물이 삭제되었습니다.");

	}

	private int getFoundIdByCheckStr(String checkStr) {
		boolean checkInt = checkStr.matches("-?\\d+");
		int foundId = 0;

		if (checkInt) {
			foundId = Integer.parseInt(checkStr);
		}

		return foundId;
	}

	private Article getFoundArticleById(int foundId) {
		Article foundArticle = null;

		for (Article article : articles) {
			if (article.articleId == foundId) {
				foundArticle = article;
			}
		}

		return foundArticle;
	}
	
	public void makeTestData() {
		articles.add(new Article("제목1", "내용1", 1, "admin"));
		articles.add(new Article("제목2", "내용2", 1, "admin"));
		articles.add(new Article("제목3", "내용3", 1, "admin"));

		System.out.println("게시물의 테스트 데이터를 생성했습니다.");
	}
}
