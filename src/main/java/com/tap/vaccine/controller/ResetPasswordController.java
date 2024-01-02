package com.tap.vaccine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tap.vaccine.exception.InvalidException;
import com.tap.vaccine.service.ResetPasswordService;

@Controller
public class ResetPasswordController {

	private ResetPasswordService resetPasswordService;

	public ResetPasswordController() {
		System.out.println("ResetPasswordController Object Created..");
	}

	@Autowired
	public ResetPasswordController(ResetPasswordService resetPasswordService) {
		this.resetPasswordService = resetPasswordService;
	}

	@RequestMapping(value = "/resetPasswordPage")
	public String resetPassword(@RequestParam String email, @RequestParam String password,
			@RequestParam String confirmPassword, Model model) {

		System.out.println("Invoked resetPassword in controller");
		try {
			boolean isValid = resetPasswordService.validateUserData(email, password, confirmPassword);
			if (isValid) {
				model.addAttribute("resetPasswordResponse", "Password resetted successfully...");
				return "/index.jsp";
			}
		} catch (InvalidException e) {
			model.addAttribute("response", e.getMessage());
		}
		return "/WEB-INF/pages/resetPassword.jsp";
	}
}
