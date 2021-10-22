package com.ssg.controller;

import java.util.List;
import java.util.Scanner;

import com.ssg.dto.Member;

public class MemberController {
	
	private Scanner scanner;
	private List<Member> members;
	
	public MemberController(Scanner scanner, List<Member> members) {
		this.scanner = scanner;
		this.members = members;
	}
	
	public void doJoin() {
		String loginId = null;
		
		while (true) {
			boolean isJoinable = false;
			
			System.out.printf("회원가입 ID : ");
			loginId = scanner.nextLine();
			
			for (Member member : members) {
				if(member.loginId.equals(loginId)) {
					isJoinable = confirmLoginId(loginId);;
					break; // 다시 while 로 돌아감
				}
			}
			
			if(isJoinable) {
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
			
			if(!loginPw.equals(loginPwConfirm)) {
				System.out.println("비밀번호 확인을 확인해주세요.");
				continue;
			}
			
			break;
		}
		
		// 비밀번호 확인이 다릅니다.
		
		System.out.printf("회원가입 이름 : ");
		String name = scanner.nextLine();
		
		Member member = new Member(loginId, loginPw, name);
		members.add(member);
		
		System.out.println(member.memberId + "번 회원이 생성되었습니다.");

	}
	
	boolean confirmLoginId(String loginId) {
		boolean isJoinable = false;
		
		for (Member member : members) {
			if(member.loginId.equals(loginId)) {
				isJoinable = true;
				break; // 다시 while 로 돌아감
			}
		}
		
		return isJoinable;
	}
}
