package com.tap.vaccine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tap.vaccine.dao.ResetPasswordDAO;
import com.tap.vaccine.dao.LoginDAO;
import com.tap.vaccine.exception.InvalidException;

@Component
public class ResetPasswordService {

	private LoginDAO loginDao;
	private ResetPasswordDAO resetPasswordDAO;

	public ResetPasswordService() {
		System.out.println("ResetPasswordService Object created..");
	}

	@Autowired
	public ResetPasswordService(LoginDAO loginDao, ResetPasswordDAO resetPasswordDAO) {
		super();
		this.loginDao = loginDao;
		this.resetPasswordDAO = resetPasswordDAO;
	}

	public boolean validateUserData(String email, String password, String confirmPassword) throws InvalidException {
		System.out.println("Invoked validateUserData() in service");

		if (email == null || email.isEmpty() || email.isBlank()) {
			throw new InvalidException("Please Enter valid mail..");
		}

		if (password == null || password.isEmpty() || password.isBlank()) {
			throw new InvalidException("Please Enter valid Password..");
		}

		if (confirmPassword == null || confirmPassword.isEmpty() || confirmPassword.isBlank()) {
			throw new InvalidException("Confirm Password cannot be empty, password matches required to set!..");
		}

		if (!password.equals(confirmPassword)) {
			throw new InvalidException("Password mismatches.. Try again..");
		}
		boolean resetted = resetPasswordDAO.resetPasswordByEmail(email, confirmPassword);
		return resetted;
	}
}
