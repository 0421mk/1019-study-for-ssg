// git add . => 모든 파일을 깃으로 추가
// git commit -m "{msg}" => 커밋할 내용을 메시지로 추가
// git push origin main => 깃 서버로 파일 업로드

package com.ssg;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		System.out.println("==== 프로그램 시작 ====");
		Scanner scanner = new Scanner(System.in);
		List<Article> articles = new ArrayList<>();
		
		while(true) {
			System.out.printf("명령어를 입력해주세요 : ");
			String command = scanner.nextLine();
			
			if(command.equals("article write")) {

				System.out.printf("제목 : ");
				String title = scanner.nextLine();
				
				System.out.printf("내용 : ");
				String body = scanner.nextLine();
				
				Article article = new Article(title, body);
				articles.add(article);
				
				System.out.println(article.articleId + "번 게시물이 생성되었습니다.");
				
			} else if(command.equals("article list")) {
				
				// Article article => 껍데기
				// articels => 실제 데이터
				
				System.out.printf(" 번호 |   제목\n");
				for(Article article : articles) {
					System.out.printf("%4d | %12s\n", article.articleId, article.title);
				}
				
			} else if(command.startsWith("article detail ")) {
				command = command.trim();
				String[] commandBits = command.split(" ");
				String checkStr = commandBits[2];
				
				boolean checkInt = checkStr.matches("-?\\d+");
				int foundId;
				
				if(checkInt) {
					foundId = Integer.parseInt(checkStr);
				} else {
					System.out.println("숫자만 입력해주세요.");
					continue;
				}
				
				Article foundArticle = null;
				
				for(Article article : articles) {
					if(article.articleId == foundId) {
						foundArticle = article;
					}
				}
				
				if(foundArticle == null) {
					System.out.println("게시물이 존재하지 않습니다.");
					continue;
				}
				
				System.out.println("번호 : " + foundArticle.articleId);
				System.out.println("제목 : " + foundArticle.title);
				System.out.println("내용 : " + foundArticle.body);
				System.out.println("=============");
			} else if(command.equals("article modify")) {
				System.out.println("article modify");
			} else if(command.equals("system exit")) {
				break;
			} else {
				System.out.println("존재하지 않는 명령어입니다.");
			}
		}
		
		System.out.println("==== 프로그램 끝 ====");
	}
}

class Article {
	// index란 static 변수가 0으로 1회(만!) 초기화됨. 그 뒤로 값이 누적
	static int index = 0;
	int articleId;
	String title;
	String body;
	
	// Article class가 생길때마다 아래 생성자 실행
	// index 값이 1 오름(누적 데이터)
	Article(String title, String body) {
		this.index++;
		this.articleId = index;
		this.title = title;
		this.body = body;
	}
}