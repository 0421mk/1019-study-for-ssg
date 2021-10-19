package com.ssg;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		System.out.println("==== 프로그램 시작 ====");
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
			System.out.printf("명령어를 입력해주세요 : ");
			String command = scanner.nextLine();
			
			if(command.equals("article write")) {
				System.out.println("article write");
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
