package com.tap.vaccine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tap.vaccine.exception.InvalidException;
import com.tap.vaccine.service.RegisterService;

@Controller
public class RegisterController {

	private RegisterService registerService;

	@Autowired
	public RegisterController(RegisterService registerService) {
		this.registerService = registerService;
	}

	public RegisterController() {
		System.out.println("Object created..->Register Controller");
	}

	@RequestMapping(value = "/getRegisterPage")
	public String getRegisterPagee() {
		return "/WEB-INF/pages/Register.jsp";
	}

	@RequestMapping(value = "/saveUserData")
	public String saveUserDataObject(@RequestParam String userName, @RequestParam String email,
			@RequestParam String password, @RequestParam String confirmPassword, @RequestParam String mobileNumber,
			@RequestParam String gender, @RequestParam String dob, Model model) {
		try {
			boolean validateData = registerService.validateData(userName, email, password, confirmPassword,
					mobileNumber, gender, dob);
			long mobileNumber1 = Long.parseLong(mobileNumber);
			try {
				boolean result = registerService.saveUserData(userName, email, password, mobileNumber1, gender, dob);
				if (result) {
					model.addAttribute("result", "Successfully Registered...");
				}
			} catch (InvalidException e) {
				model.addAttribute("result", e.getMessage());
			}
		} catch (InvalidException e) {
			model.addAttribute("result", e.getMessage());
		}
		return "/WEB-INF/pages/Register.jsp";
	}

	@RequestMapping(value = "/getIndexPage")
	public String getHomePagee() {
		return "/index.jsp";
	}
}
