package com.ssg.dao;

import java.util.ArrayList;
import java.util.List;

import com.ssg.dto.Member;

public class MemberDao {
	public List<Member> members;

	public MemberDao() {
		members = new ArrayList<>();
	}

	public Member getMemberByLoginId(String loginId) {
		Member foundMember = null;

		for (Member member : members) {
			if (member.loginId.equals(loginId)) {
				foundMember = member;
			}
		}

		return foundMember;
	}

	public void add(Member member) {
		members.add(member);
	}
	
	public void makeTestData() {
		members.add(new Member("admin", "admin", "admin"));
		members.add(new Member("user1", "user1", "user1"));
		members.add(new Member("user2", "user2", "user2"));

		System.out.println("유저의 테스트 데이터를 생성했습니다.");
	}
}
