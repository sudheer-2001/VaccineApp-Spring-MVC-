package com.tap.vaccine.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tap.vaccine.entity.RegisterEntity;
import com.tap.vaccine.exception.EntityNotFoundException;
import com.tap.vaccine.exception.InvalidException;
import com.tap.vaccine.service.LoginService;

@Controller
public class LoginController {

	private LoginService loginService;

	@Autowired
	public LoginController(LoginService loginService) {
		this.loginService = loginService;
	}

	public LoginController() {
		System.out.println("Object created..->Login Controller");
	}

	@RequestMapping(value = "/userLogin")
	public String getLoginPage() {
		return "/WEB-INF/pages/LoginPage.jsp";
	}

	@RequestMapping(value = "/login")
	public String UserLogin(@RequestParam String mail, @RequestParam String password, Model model,
			HttpServletRequest request) {
		System.out.println("Invoked UserLogin in controller...");
		try {
			boolean isValidUser = loginService.loginCredentials(mail, password);
			model.addAttribute("userEmail", mail);
			HttpSession session = request.getSession(true);
			session.setAttribute("userEmail", mail);
			RegisterEntity entity = loginService.getRegisterEntityByEmail(mail);
			session.setAttribute("memCount", entity.getMember_count());
			session.setAttribute("userName", entity.getUserName().toUpperCase());
			return "/WEB-INF/pages/WelcomeUser.jsp";
		} catch (InvalidException | EntityNotFoundException e) {
			model.addAttribute("result", "Login failed: " + e.getMessage());
		}
		return "/WEB-INF/pages/LoginPage.jsp";
	}

	@RequestMapping(value = "/resetPassword")
	public String getResetPage() {
		return "/WEB-INF/pages/resetPassword.jsp";
	}
}
