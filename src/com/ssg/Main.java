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
				System.out.println("article list");
			} else if(command.equals("article detail")) {
				System.out.println("article detail");
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
	
	Article(String title, String body) {
		this.index++;
		this.articleId = index;
		this.title = title;
		this.body = body;
	}
}