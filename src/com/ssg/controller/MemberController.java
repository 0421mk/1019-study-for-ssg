package com.ssg.controller;

import java.util.List;
import java.util.Scanner;

import com.ssg.container.Container;
import com.ssg.dto.Member;
import com.ssg.service.MemberService;

public class MemberController extends Controller {

	private Scanner scanner;
	private MemberService memberService;

	public MemberController(Scanner scanner) {
		this.scanner = scanner;
		this.memberService = new MemberService();
	}

	public void doAction(String command, String actionMethodName) {
		switch (actionMethodName) {
		case "join":
			doJoin();
			break;
		case "login":
			doLogin();
			break;
		case "logout":
			doLogout();
			break;
		default:
			System.out.println("잘못된 명령어입니다.");
			break;
		}
	}

	public void doJoin() {
		
		if (loginedMember != null) {
			System.out.println("로그아웃 후 이용해주세요.");
			return;
		}
		
		String loginId = null;

		while (true) {
			System.out.printf("회원가입 ID : ");
			loginId = scanner.nextLine();
			
			Member foundMember = memberService.getMemberByLoginId(loginId);
			
			if (foundMember != null) {
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
		memberService.add(member);

		System.out.println(member.memberId + "번 회원이 생성되었습니다.");

	}

	public void doLogin() {

		if (loginedMember != null) {
			System.out.println("로그아웃 후 이용해주세요.");
			return;
		}

		System.out.printf("로그인 ID : ");
		String loginId = scanner.nextLine();

		System.out.printf("로그인 PW : ");
		String loginPw = scanner.nextLine();

		Member foundMember = memberService.getMemberByLoginId(loginId);

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

	public void doLogout() {

		if (loginedMember == null) {
			System.out.println("로그인 후 이용해주세요.");
			return;
		}

		loginedMember = null;

		System.out.println("로그아웃 하였습니다");

	}
}
