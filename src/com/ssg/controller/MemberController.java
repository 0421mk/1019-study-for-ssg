package com.ssg.controller;

import java.util.List;
import java.util.Scanner;

import com.ssg.dto.Article;
import com.ssg.dto.Member;

public class MemberController extends Controller {

	private Scanner scanner;
	private List<Member> members;

	public MemberController(Scanner scanner, List<Member> members) {
		this.scanner = scanner;
		this.members = members;
	}

	public void doAction(String command, String actionMethodName) {
		switch (actionMethodName) {
		case "join":
			doJoin();
			break;
		case "login":
			doLogin();
			break;
		default:
			System.out.println("잘못된 명령어입니다.");
			break;
		}
	}

	public void doJoin() {
		String loginId = null;

		while (true) {
			boolean isJoinable = false;

			System.out.printf("회원가입 ID : ");
			loginId = scanner.nextLine();

			for (Member member : members) {
				if (member.loginId.equals(loginId)) {
					isJoinable = confirmLoginId(loginId);
					;
					break; // 다시 while 로 돌아감
				}
			}

			if (isJoinable) {
				System.out.println("이미 존재하는 아이디입니다.");
				continue;
			}

			break;
		}

		// 이미 존재하는 아이디입니다.
		String loginPw = null;
		String loginPwConfirm = null;
		while (true) {
			System.out.printf("회원가입 PW : ");
			loginPw = scanner.nextLine();

			System.out.printf("회원가입 PW 확인 : ");
			loginPwConfirm = scanner.nextLine();

			if (!loginPw.equals(loginPwConfirm)) {
				System.out.println("비밀번호 확인을 확인해주세요.");
				continue;
			}

			break;
		}

		System.out.printf("회원가입 이름 : ");
		String name = scanner.nextLine();

		Member member = new Member(loginId, loginPw, name);
		members.add(member);

		System.out.println(member.memberId + "번 회원이 생성되었습니다.");

	}

	public void doLogin() {
		
		if (loginedMember != null) {
			System.out.println("이미 로그인되어 있습니다.");
			return;
		}
		
		System.out.printf("로그인 ID : ");
		String loginId = scanner.nextLine();
		
		System.out.printf("로그인 PW : ");
		String loginPw = scanner.nextLine();
		
		Member foundMember = getMemberByLoginId(loginId);
		
		if (foundMember == null) {
			System.out.println("존재하지 않는 아이디입니다.");
			return;
		}
		
		if (!foundMember.loginPw.equals(loginPw)) {
			System.out.println("비밀번호를 확인해주세요.");
			return;
		}
		
		loginedMember = foundMember;
		
		System.out.println(loginedMember.name + "님이 로그인하셨습니다.");

	}

	boolean confirmLoginId(String loginId) {
		boolean isJoinable = false;

		for (Member member : members) {
			if (member.loginId.equals(loginId)) {
				isJoinable = true;
				break; // 다시 while 로 돌아감
			}
		}

		return isJoinable;
	}
	
	Member getMemberByLoginId(String loginId) {
		Member foundMember = null;
		
		for (Member member : members) {
			if(member.loginId.equals(loginId)) {
				foundMember = member;
			}
		}
		
		return foundMember;
	}
	
	public void makeTestData() {
		members.add(new Member("admin", "admin", "admin"));
		members.add(new Member("user1", "user1", "user1"));
		members.add(new Member("user2", "user2", "user2"));

		System.out.println("유저의 테스트 데이터를 생성했습니다.");
	}
}
