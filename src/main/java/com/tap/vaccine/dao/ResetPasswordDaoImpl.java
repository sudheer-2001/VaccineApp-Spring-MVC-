package com.tap.vaccine.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tap.vaccine.entity.RegisterEntity;
import com.tap.vaccine.exception.InvalidException;
import com.tap.vaccine.service.EmailService;

@Component
public class ResetPasswordDaoImpl implements ResetPasswordDAO {

	private SessionFactory sessionfact;
	private EmailService emailService;

	public ResetPasswordDaoImpl() {
		System.out.println("ForgetPasswordDaoImpl Object Created..");
	}

	@Autowired
	public ResetPasswordDaoImpl(SessionFactory sessionfact, EmailService emailService) {
		this.sessionfact = sessionfact;
		this.emailService = emailService;
	}

	@Override
	public boolean resetPasswordByEmail(String email, String newPassword) throws InvalidException {
		System.out.println("Invoked resetPasswordByEmail()");
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionfact.openSession();
			transaction = session.beginTransaction();
			RegisterEntity entity = session.createQuery("from RegisterEntity where email=:email", RegisterEntity.class)
					.setParameter("email", email).uniqueResult();
			if (entity != null) {
				entity.setPassword(newPassword);
				entity.setLoginAttempts(0);
				boolean result = emailService.resetPassword(entity.getUserName(), email, newPassword);
				transaction.commit();
				return true;
			} else {
				throw new InvalidException("Email not found, Try again..");
			}
		} catch (HibernateException e) {
			transaction.rollback();
			System.out.println("transaction is rollbacked " + e.getMessage());
		} catch (InvalidException e) {
			throw new InvalidException(e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return false;
	}
}
