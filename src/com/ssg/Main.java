package com.ssg;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		System.out.println("==== 프로그램 시작 ====");
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.printf("명령어를 입력해주세요 : ");
		String command = scanner.nextLine();
		
		System.out.println(command);
		System.out.println("==== 프로그램 끝 ====");
	}

}
