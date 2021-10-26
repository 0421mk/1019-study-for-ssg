package com.ssg.controller;

import com.ssg.dto.Member;

public abstract class Controller {
	Member loginedMember = null;
	
	public abstract void doAction(String command, String actionMethodName);

}
