package com.ssg;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.ssg.container.Container;
import com.ssg.controller.ArticleController;
import com.ssg.controller.Controller;
import com.ssg.controller.ExportController;
import com.ssg.controller.MemberController;
import com.ssg.dto.Article;
import com.ssg.dto.Member;

public class App {

	void start() {
		System.out.println("==== 프로그램 시작 ====");
		Scanner scanner = new Scanner(System.in);

		MemberController memberController = new MemberController(scanner);
		ArticleController articleController = new ArticleController(scanner);
		ExportController exportController = new ExportController(scanner);
		Controller controller = null;
		
		Container.articleDao.makeTestData();
		Container.memberDao.makeTestData();

		while (true) {
			System.out.printf("명령어를 입력해주세요 : ");
			String command = scanner.nextLine().trim();

			if (command.equals("system exit")) {
				break;
			}

			String[] commandBits = command.split(" ");
			String controllerName = commandBits[0];
			String actionMethodName = commandBits[1];

			if(!command.startsWith("article list")) {
				if (commandBits.length > 3) {
					System.out.println("잘못된 명령어입니다.");
					continue;
				}
			}

			if (controllerName.equals("article")) {
				controller = articleController;
			} else if (controllerName.equals("member")) {
				controller = memberController;
			} else if (controllerName.equals("export")) {
				controller = exportController;
			} else {
				System.out.println("잘못된 명령어입니다.");
				continue;
			}

			controller.doAction(command, actionMethodName);
		}

		System.out.println("==== 프로그램 끝 ====");
	}

}
