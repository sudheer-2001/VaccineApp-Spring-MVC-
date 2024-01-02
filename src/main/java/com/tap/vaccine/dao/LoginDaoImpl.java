package com.tap.vaccine.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tap.vaccine.entity.RegisterEntity;
import com.tap.vaccine.exception.EntityNotFoundException;

@Component
public class LoginDaoImpl implements LoginDAO {

	private SessionFactory sessionfact;

	@Autowired
	public LoginDaoImpl(SessionFactory sessionfact) {
		this.sessionfact = sessionfact;
	}

	public LoginDaoImpl() {
		System.out.println("Created Object ->Login DAO");
	}

	@Override
	public RegisterEntity getRegisterEntityByEmail(String email) throws EntityNotFoundException {
		Session session = null;
		RegisterEntity entity = null;
		String hql = "from RegisterEntity where email = '" + email + "'";
		try {
			session = sessionfact.openSession();
			Query query = session.createQuery(hql);
			entity = (RegisterEntity) query.uniqueResult();
			System.out.println(entity);
			if (entity != null) {
				return entity;
			} else {
				throw new EntityNotFoundException("RegisterEntity with email " + email + " not found");
			}
		} catch (HibernateException e) {
			System.out.println("Exception in getRegisterEntityByEmail: " + e.getMessage());
			throw new EntityNotFoundException("Exception in getRegisterEntityByEmail: " + e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	@Override
	public boolean updateRegisterEntity(String email) {
		System.out.println("Invoked updateRegisterEntity()");
		Session session = null;
		String hql = "Update RegisterEntity set loginAttempts = loginAttempts + 1 where EMAIL='" + email + "'";
		try {
			session = sessionfact.openSession();
			Transaction transaction = session.beginTransaction();
			Query query = session.createQuery(hql);
			int i = query.executeUpdate();

			transaction.commit();
			return (i > 0) ? true : false;
		} finally {
			if (session != null) {
				session.close();
				System.out.println("session is closed ");
			}
		}
	}
}
