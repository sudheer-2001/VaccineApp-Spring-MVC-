package com.tap.vaccine.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tap.vaccine.entity.RegisterEntity;
import com.tap.vaccine.exception.InvalidException;
import com.tap.vaccine.service.EmailService;

@Component
public class RegisterDaoImpl implements RegisterDAO {

	private SessionFactory sessionfact;
	private EmailService emailService;

	@Autowired
	public RegisterDaoImpl(SessionFactory sessionfact, EmailService emailService) {
		this.sessionfact = sessionfact;
		this.emailService = emailService;
	}

	public RegisterDaoImpl() {
		System.out.println("Created Object -> Register DAO");
	}

	@Override
	public boolean saveData(RegisterEntity entity) throws InvalidException {
		System.out.println("Invoked saveData()..");
		Session session = null;
		Transaction transaction = null;
		boolean flag = false;
		try {
			session = sessionfact.openSession();
			transaction = session.beginTransaction();
			session.save(entity);
			boolean result = emailService.sendEmail(entity);
			transaction.commit();
			flag = true;
		} catch (HibernateException e) {
			transaction.rollback();
			System.out.println("transaction is rollbacked " + e.getMessage());
		} catch (InvalidException e) {
			throw new InvalidException(e.getMessage());
		} finally {
			if (session != null) {
				session.close();
				System.out.println("session closed...");
			}
		}
		return flag;
	}

	public boolean isEmailExists(String email) {
		Session session = null;
		String hql = "SELECT COUNT(*) FROM RegisterEntity WHERE email = :email";
		try {
			session = sessionfact.openSession();
			Query<Long> query = session.createQuery(hql, Long.class);
			query.setParameter("email", email);
			Long count = query.uniqueResult();
			return count != null && count > 0;
		} finally {
			session.close();
		}
	}

	public boolean isUserNameExists(String userName) {
		Session session = null;
		String hql = "SELECT COUNT(*) FROM RegisterEntity WHERE userName = :userName";
		try {
			session = sessionfact.openSession();
			Query<Long> query = session.createQuery(hql, Long.class);
			query.setParameter("userName", userName);
			Long count = query.uniqueResult();
			return count != null && count > 0;
		} finally {
			session.close();
		}
	}
}
