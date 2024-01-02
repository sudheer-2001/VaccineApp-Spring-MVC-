package com.tap.vaccine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.tap.vaccine.dao.RegisterDAO;
import com.tap.vaccine.entity.RegisterEntity;
import com.tap.vaccine.exception.InvalidException;

@Component
public class RegisterService {

	private RegisterDAO registerDao;

	@Autowired
	public RegisterService(RegisterDAO registerDao) {
		this.registerDao = registerDao;
	}

	public RegisterService() {
		System.out.println("Object created..->Register Service");
	}

	public boolean validateData(String userName, String email, String pwd, String cfPwd, String number, String gender,
			String dob) throws InvalidException {

		boolean isEmailExists = registerDao.isEmailExists(email);
		boolean isUserNameExists = registerDao.isUserNameExists(userName);

		boolean flag = false;
		if (userName == null || userName.isEmpty() || userName.isBlank()) {
			throw new InvalidException("User Name is Invalid..");
		}
		if (isUserNameExists) {
			throw new InvalidException("User Name is already exists.. Try with different username");
		}

		if (email == null || email.isEmpty() || email.isBlank()) {
			throw new InvalidException("Email cannot be empty...");
		} else {
			if (isEmailExists) {
				throw new InvalidException("Email already exists.. Try with different mail");
			} else {
				System.out.println("New Email registering...");
			}
		}

		if (pwd == null || pwd.isEmpty() || pwd.isBlank()) {
			throw new InvalidException("Password is mandatory for future logins...");
		}

		if (!pwd.equals(cfPwd) || cfPwd == null || cfPwd.isEmpty() || cfPwd.isBlank()) {
			throw new InvalidException("password does not matches..");
		}

		if (number.isEmpty() || number.isBlank() || number == null) {
			throw new InvalidException("number required...");
		}

		if (number.length() != 10) {
			throw new InvalidException("Invalid Number!..Please enter 10 digit mobile number");
		}
		if (gender == null || gender.isEmpty() || gender.isBlank()) {
			throw new InvalidException("Please select gender...");
		}
		if (dob == null || dob.isEmpty() || dob.isBlank()) {
			throw new InvalidException("Please select Date of Birth...");
		}
		flag = true;
		return flag;
	}

	public boolean saveUserData(String userName, String email, String pwd, long number, String gender, String dob)
			throws InvalidException {
		RegisterEntity entity = new RegisterEntity(userName, email, pwd, number, gender, dob, 0);
		boolean result = registerDao.saveData(entity);
		return result;
	}

}
