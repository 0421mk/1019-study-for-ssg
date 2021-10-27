package com.ssg.controller;

import java.util.Scanner;

import com.ssg.service.ExportService;

public class ExportController extends Controller {
	private ExportService exportService;
	
	public ExportController(Scanner scanner) {
		this.exportService = new ExportService();
	}
	
	public void doAction(String command, String actionMethodName) {
		switch (actionMethodName) {
		case "html":
			doHtml();
			break;
		default :
			System.out.println("잘못된 명령어입니다.");
			break;
		}
	}

	private void doHtml() {
		System.out.println("== HTML 생성을 시작합니다. ==");
		exportService.makeHtml();
	}

}
