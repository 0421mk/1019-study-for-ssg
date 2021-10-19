package com.ssg;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.ssg.dto.Article;

public class App {
	
	static List<Article> articles = new ArrayList<>();
	
	void start() {
		System.out.println("==== 프로그램 시작 ====");
		Scanner scanner = new Scanner(System.in);

		makeTestData();

		while (true) {
			System.out.printf("명령어를 입력해주세요 : ");
			String command = scanner.nextLine();

			if (command.equals("article write")) {

				System.out.printf("제목 : ");
				String title = scanner.nextLine();

				System.out.printf("내용 : ");
				String body = scanner.nextLine();

				Article article = new Article(title, body);
				articles.add(article);

				System.out.println(article.articleId + "번 게시물이 생성되었습니다.");

			} else if (command.equals("article list")) {

				// Article article => 껍데기
				// articels => 실제 데이터

				System.out.printf(" 번호  / 조회수 /   제목\n");
				for (Article article : articles) {
					System.out.printf("%4d / %4d / %12s\n", article.articleId, article.hit, article.title);
				}

			} else if (command.startsWith("article detail ")) {
				command = command.trim();
				String[] commandBits = command.split(" ");

				if (commandBits.length > 3) {
					System.out.println("명령어를 잘못입력하셨습니다.");
					continue;
				}

				String checkStr = commandBits[2];

				boolean checkInt = checkStr.matches("-?\\d+");
				int foundId;

				if (checkInt) {
					foundId = Integer.parseInt(checkStr);
				} else {
					System.out.println("숫자만 입력해주세요.");
					continue;
				}

				Article foundArticle = null;

				for (Article article : articles) {
					if (article.articleId == foundId) {
						foundArticle = article;
					}
				}

				if (foundArticle == null) {
					System.out.println("게시물이 존재하지 않습니다.");
					continue;
				}

				foundArticle.increaseHit();

				System.out.println("번호 : " + foundArticle.articleId);
				System.out.println("작성날짜 : " + foundArticle.regDate);
				System.out.println("조회수 : " + foundArticle.hit);
				System.out.println("제목 : " + foundArticle.title);
				System.out.println("내용 : " + foundArticle.body);
				System.out.println("=============");
			} else if (command.startsWith("article modify ")) {

				command = command.trim();
				String[] commandBits = command.split(" ");

				if (commandBits.length > 3) {
					System.out.println("명령어를 잘못입력하셨습니다.");
					continue;
				}

				String checkStr = commandBits[2];

				boolean checkInt = checkStr.matches("-?\\d+");
				int foundId;

				if (checkInt) {
					foundId = Integer.parseInt(checkStr);
				} else {
					System.out.println("숫자만 입력해주세요.");
					continue;
				}

				Article foundArticle = null;

				for (Article article : articles) {
					if (article.articleId == foundId) {
						foundArticle = article;
					}
				}

				if (foundArticle == null) {
					System.out.println("게시물이 존재하지 않습니다.");
					continue;
				}

				System.out.printf("제목 : ");
				String title = scanner.nextLine();

				System.out.printf("내용 : ");
				String body = scanner.nextLine();

				foundArticle.title = title;
				foundArticle.body = body;

				System.out.println(foundArticle.articleId + "번 게시물이 수정되었습니다.");

			} else if (command.startsWith("article delete ")) {

				command = command.trim();
				String[] commandBits = command.split(" ");

				if (commandBits.length > 3) {
					System.out.println("명령어를 잘못입력하셨습니다.");
					continue;
				}

				String checkStr = commandBits[2];

				boolean checkInt = checkStr.matches("-?\\d+");
				int foundId;

				if (checkInt) {
					foundId = Integer.parseInt(checkStr);
				} else {
					System.out.println("숫자만 입력해주세요.");
					continue;
				}

				Article foundArticle = null;

				for (Article article : articles) {
					if (article.articleId == foundId) {
						foundArticle = article;
					}
				}

				if (foundArticle == null) {
					System.out.println("게시물이 존재하지 않습니다.");
					continue;
				}

				articles.remove(foundArticle);

				System.out.println(foundArticle.articleId + "번 게시물이 삭제되었습니다.");

			} else if (command.equals("system exit")) {
				break;
			} else {
				System.out.println("존재하지 않는 명령어입니다.");
			}
		}

		System.out.println("==== 프로그램 끝 ====");
	}

	static void makeTestData() {
		articles.add(new Article("제목1", "내용1"));
		articles.add(new Article("제목2", "내용2"));
		articles.add(new Article("제목3", "내용3"));
		
		System.out.println("테스트 데이터를 생성했습니다.");
	}
}
