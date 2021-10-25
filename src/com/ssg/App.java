package com.ssg;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.ssg.controller.ArticleController;
import com.ssg.controller.Controller;
import com.ssg.controller.MemberController;
import com.ssg.dto.Article;
import com.ssg.dto.Member;

public class App {
	
	List<Article> articles;
	List<Member> members;
	
	App() {
		articles = new ArrayList<>();
		members = new ArrayList<>();
	}
	
	void start() {
		System.out.println("==== 프로그램 시작 ====");
		Scanner scanner = new Scanner(System.in);

		makeTestData();
		
		MemberController memberController = new MemberController(scanner, members);
		ArticleController articleController = new ArticleController(scanner, articles);

		while (true) {
			System.out.printf("명령어를 입력해주세요 : ");
			String command = scanner.nextLine();
			
			String[] commandBits = command.split(" ");
			
			if(commandBits.length < 2) {
				System.out.println("존재하지 않는 명령어입니다.");
				continue;
			}
			
			String controllerName = commandBits[0];
			String actionMethodName = commandBits[1];
			
			Controller controller = null;
			
			if(command.length() == 0) {
				continue;
			}
			
			if(command.equals("system exit")) {
				break;
			}
			
			if(controllerName.equals("article")) {
				controller = articleController;
			} else if(controllerName.equals("member")) {
				controller = memberController;
			} else {
				System.out.println("존재하지 않는 명령어입니다.");
				continue;
			}
			
			controller.doAction(command, actionMethodName);
		}

		System.out.println("==== 프로그램 끝 ====");
	}

	void makeTestData() {
		articles.add(new Article("제목1", "내용1"));
		articles.add(new Article("제목2", "내용2"));
		articles.add(new Article("제목3", "내용3"));
		
		System.out.println("테스트 데이터를 생성했습니다.");
	}

}
