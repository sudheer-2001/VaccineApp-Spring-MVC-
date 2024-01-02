package com.tap.vaccine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tap.vaccine.dao.LoginDAO;
import com.tap.vaccine.entity.RegisterEntity;
import com.tap.vaccine.exception.EntityNotFoundException;
import com.tap.vaccine.exception.InvalidException;

@Component
public class LoginService {

	private LoginDAO loginDao;
	private EmailService emailService;
	private static final int maxLoginAttempts = 4;

	public LoginService() {
		System.out.println("Object created..->Login Service");
	}

	@Autowired
	public LoginService(LoginDAO loginDao, EmailService emailService) {
		this.loginDao = loginDao;
		this.emailService = emailService;
	}

	public boolean loginCredentials(String mail, String password) throws InvalidException, EntityNotFoundException {
		System.out.println("Invoked loginCredentials() in service");
		if (mail == null || mail.isEmpty() || mail.isBlank()) {
			throw new InvalidException("Please Enter valid mail..");
		}

		if (password == null || password.isEmpty() || password.isBlank()) {
			throw new InvalidException("Please Enter valid Password..");
		}

		RegisterEntity entity = loginDao.getRegisterEntityByEmail(mail);
		if (entity != null) {
			if (entity.getLoginAttempts() >= maxLoginAttempts - 1) {
				emailService.sendAccountBlockedEmail(entity.getEmail(), entity.getUserName());
				throw new InvalidException("Your account is blocked. Please reset to unblock..");
			}
			if (entity.getPassword().equals(password)) {
				return true;
			} else {
				loginDao.updateRegisterEntity(mail);
				int loginAttempt = entity.getLoginAttempts();
				int attempt = maxLoginAttempts - loginAttempt - 1;
				throw new InvalidException(attempt + " attempts left");
			}
		} else {
			throw new InvalidException("mail doesn't exist, Please Register!...");
		}
	}

	public RegisterEntity getRegisterEntityByEmail(String email) {
		return loginDao.getRegisterEntityByEmail(email);
	}
}
