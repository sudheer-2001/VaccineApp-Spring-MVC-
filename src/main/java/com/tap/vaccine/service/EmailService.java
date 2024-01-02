package com.tap.vaccine.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.tap.vaccine.entity.MemberEntity;
import com.tap.vaccine.entity.RegisterEntity;
import com.tap.vaccine.exception.InvalidException;

@Component
public class EmailService {

	public EmailService() {
		System.out.println("Object created in Email Service");
	}

	private JavaMailSender mailSender;

	@Autowired
	public EmailService(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public boolean sendEmail(RegisterEntity entity) throws InvalidException {
		boolean flag = true;
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setTo(entity.getEmail());
		simpleMailMessage.setSubject("🎉 Registration successful for vaccineApp 🌟");
		simpleMailMessage.setText(
				"Dear " + entity.getUserName() + "\n\nThanks for registering with us.. " + "\n\nLogin Creditionals\n\n"
						+ "Your Email: " + entity.getEmail() + "\n" + "Your Password:" + entity.getPassword() + "\n\n"
						+ "Note: Keep your login credentials secure and do not share them with anyone.\n\n"
						+ "Use these credentials to log in to the VaccineApp and stay updated on vaccineApp.\n\n"
						+ "Stay healthy! \n\n" + "Best Regards,\n" + "VaccineApp");
		try {
			mailSender.send(simpleMailMessage);
			System.out.println("Registration Mail sended successfully....");
			return flag;
		} catch (MailException e) {
			System.out.println(e.getMessage());
			throw new InvalidException("Something went wrong..");
		}
	}

	public void sendAccountBlockedEmail(String to, String userName) throws InvalidException {
		String resetToken = UUID.randomUUID().toString();

		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setTo(to);
		simpleMailMessage.setSubject("Account Blocked on vaccineApp");
		simpleMailMessage.setText("Dear " + userName + ",\n\n"
				+ "We regret to inform you that VaccineApp account has been blocked due to multiple invalid login attempts.\n"
				+ "Please reset your password to unblock your account.\n\n"
				+ "Use the following link to reset your password: 'http://localhost:9090/vaccineApp/resetPassword?token="
				+ resetToken + "'\n\n" + "If you did not attempt to log in, please contact support immediately.\n\n"
				+ "Stay secure!\n\n" + "Best regards,\n" + "VaccineApp");
		try {
			mailSender.send(simpleMailMessage);
			System.out.println("Blocked Mail sent successfully!...");
		} catch (MailException e) {
			System.out.println(e.getMessage());
			throw new InvalidException("Failed to send email. Something went wrong");
		}
	}

	public boolean resetPassword(String userName, String email, String newPassword) throws InvalidException {
		boolean flag = true;
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setTo(email);
		simpleMailMessage.setSubject("Password resseted successfully");
		simpleMailMessage.setText("Dear " + userName + ",\n\n"
				+ "successfully reset the password done!.. below are the credintials..for next login\n\n"
				+ "Your Email: " + email + "\n" + "Your Password: " + newPassword + "\n\n"
				+ "If you did not attempt to log in, please contact support immediately.\n\n" + "Stay secure!\n\n"
				+ "Best regards,\n" + "VaccineApp");
		try {
			mailSender.send(simpleMailMessage);
			System.out.println("Reset Mail sended successfully....");
			return flag;
		} catch (MailException e) {
			System.out.println(e.getMessage());
			throw new InvalidException("Something went wrong");
		}
	}

	public boolean sendEmailToMember(MemberEntity entity) throws InvalidException {
		boolean flag = true;
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setTo(entity.getMemberEmail());
		simpleMailMessage.setSubject("🎉 You Are Successfully added to VaccineApp! 🌟");

		// Format the email message based on the dose
		String message = "Dear " + entity.getMemberName() + ",\n\n" + "Your family member with Email "
				+ entity.getUserEmail() + " has added your data to VaccineApp.\n\n";

		if (entity.getDose() == 1) {
			message += "You have completed 1 dose of" + entity.getVaccineType() + " Vaccination.\n"
					+ "Now you are eligible for dose 2.\n";
		} else if (entity.getDose() == 2) {
			message += "You have completed 2 doses of" + entity.getVaccineType() + " Vaccination.\n"
					+ "You are eligible for dose 3.\n";
		} else if (entity.getDose() == 3) {
			message += "You have successfully completed all 3 doses of.\n" + entity.getVaccineType() + " Vaccination";
		}

		message += "\nStay healthy!\n\nBest regards,\nVaccineApp";

		simpleMailMessage.setText(message);

		try {
			mailSender.send(simpleMailMessage);
			System.out.println("Mail sent successfully");
			return flag;
		} catch (MailException e) {
			System.out.println(e.getMessage());
			throw new InvalidException("Something went wrong");
		}
	}
}
