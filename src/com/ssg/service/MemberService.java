package com.ssg.service;

import com.ssg.container.Container;
import com.ssg.dto.Member;

public class MemberService {

	public Member getMemberByLoginId(String loginId) {
		return Container.memberDao.getMemberByLoginId(loginId);
	}

	public void add(Member member) {
		
		Container.memberDao.add(member);
		
	}
	
}
