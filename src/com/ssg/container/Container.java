package com.ssg.container;

import com.ssg.dao.ArticleDao;
import com.ssg.dao.MemberDao;

public class Container {
	// 데이터베이스를 한군데로 모으자!
	public static ArticleDao articleDao;
	public static MemberDao memberDao;
	
	// static : 실행되자마자 1회만 초기화하고 그 뒤로 누적한다.
	static {
		articleDao = new ArticleDao();
		memberDao = new MemberDao();
	}
}
