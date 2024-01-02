package com.tap.vaccine.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {

	public LogoutController() {
		System.out.println("Logout Controller Object Created...");
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session1 = request.getSession(false); // false means do not create a new session if not exists
		if (session1 != null) {
			session1.invalidate();
		}

		return "/WEB-INF/pages/LogoutPage.jsp";
	}
}