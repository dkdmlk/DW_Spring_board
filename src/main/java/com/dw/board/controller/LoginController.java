package com.dw.board.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	@GetMapping("/login")
	public String callLoginPage() {
		return "login";
	}
	
	@GetMapping("/join")
	public String callJoinPage() {
		return "join";
	}
	
	@GetMapping("/logout")
	public String callLougout(HttpSession httpsession) {
		//세션 삭제(로그아웃시 저장된 세션 삭제)
		httpsession.removeAttribute("studentsId");
		httpsession.removeAttribute("studentsName");
		return "login";
	}
}
