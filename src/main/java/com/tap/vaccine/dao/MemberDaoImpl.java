package com.tap.vaccine.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tap.vaccine.entity.MemberEntity;
import com.tap.vaccine.entity.RegisterEntity;

@Component
public class MemberDaoImpl implements MemberDAO {

	private SessionFactory sessionfact;

	@Autowired
	public MemberDaoImpl(SessionFactory sessionfact) {
		this.sessionfact = sessionfact;
	}

	public MemberDaoImpl() {
		System.out.println("Object created in MemberDaoImpl");
	}

	@Override
	public boolean saveData(MemberEntity entity) {
		System.out.println("Invoked saveData()..");
		Session session = null;
		Transaction transaction = null;
		boolean flag = false;
		try {
			session = sessionfact.openSession();
			transaction = session.beginTransaction();
			session.save(entity);
			transaction.commit();
			flag = true;
		} catch (HibernateException e) {
			transaction.rollback();
			System.out.println("transaction is rollbacked " + e.getMessage());
		} finally {
			if (session != null) {
				session.close();
				System.out.println("session closed...");
			}
		}
		return flag;
	}

	@Override
	public boolean isEmailExists(String email) {
		Session session = null;
		String hql = "SELECT COUNT(*) FROM MemberEntity WHERE memberEmail = :email";
		try {
			session = sessionfact.openSession();
			Query<Long> query = session.createQuery(hql, Long.class);
			query.setParameter("email", email);
			Long count = query.uniqueResult();
			return count != null && count > 0;
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	@Override
	public boolean isMemberNameExists(String memberName) {
		Session session = null;
		String hql = "SELECT COUNT(*) FROM MemberEntity WHERE memberName = :memberName";
		try {
			session = sessionfact.openSession();
			Query<Long> query = session.createQuery(hql, Long.class);
			query.setParameter("memberName", memberName);
			Long count = query.uniqueResult();
			return count != null && count > 0;
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	@Override
	public List<MemberEntity> getAllMembers(String userEmail) {
		System.out.println("Invoked getAllMembers() in DAO");
		Session session = null;
		String hql = "from MemberEntity where userEmail =:userMail";
		try {
			session = sessionfact.openSession();
			Query query = session.createQuery(hql);
			query.setParameter("userMail", userEmail);
			List resultList = query.getResultList();
			resultList.forEach(obj -> System.out.println(obj));
			return resultList;
		} finally {
			if (session != null) {
				session.close();
				System.out.println("session is closed");
			}
		}
	}

	@Override
	public int getMemberCount(String userEmail) {
		System.out.println("Invoked getMemberCount() in DAO");
		Session session = null;
		String getCount = "from RegisterEntity re where re.email='" + userEmail + "'";
		RegisterEntity entity = null;
		int count = 0;
		try {
			session = sessionfact.openSession();
			Query query = session.createQuery(getCount);
			entity = (RegisterEntity) query.uniqueResult();
			if (entity != null) {
				count = entity.getMember_count();
				System.out.println(count);
				return count;
			} else {
				System.out.println("No entity found for email: " + userEmail);
				return count;
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	@Override
	public boolean deleteMemberByID(int id) {
		boolean flag = false;
		Session session = null;
		try {
			session = sessionfact.openSession();
			Transaction transaction = session.beginTransaction();
			MemberEntity entity = session.get(MemberEntity.class, id);
			session.delete(entity);
			transaction.commit();
			flag = true;
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return flag;
	}

	@Override
	public int updateMemberCount(String userEmail, int memberCount) {
		Session session = null;
		Transaction transaction = null;
		int increaseCount = ++memberCount;
		String UPDATE_COUNT = "update RegisterEntity r set r.member_count=" + increaseCount + " where r.email='"
				+ userEmail + "'";
		int count = 0;
		try {
			session = sessionfact.openSession();
			transaction = session.beginTransaction();
			Query query = session.createQuery(UPDATE_COUNT);
			count = query.executeUpdate();
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
			System.out.println("transaction rolled back");
		} finally {
			if (session != null) {
				session.close();
				System.out.println("session is closed");
			}
		}
		return increaseCount;
	}

	@Override
	public MemberEntity getEntityById(int memberId) {
		Session session = null;
		MemberEntity entity = null;
		try {
			session = sessionfact.openSession();
			entity = session.get(MemberEntity.class, memberId);
			if (entity != null) {
				return entity;
			} else {
				System.out.println("entity is null");
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return entity;
	}

	@Override
	public boolean updateMemberEntity(MemberEntity entity) {
		boolean flag = false;
		Session session = null;
		try {
			session = sessionfact.openSession();
			Transaction transaction = session.beginTransaction();
			session.update(entity);
			transaction.commit();
			flag = true;
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return flag;
	}

	@Override
	public int decreaseMemberCount(String userEmail, int memberCount) {
		Session session = null;
		Query query = null;
		Transaction transaction = null;
		int decreaseCount = --memberCount;
		int count = 0;
		String hql = "UPDATE RegisterEntity set member_count=" + decreaseCount + " WHERE email='" + userEmail + "'";
		try {
			session = sessionfact.openSession();
			query = session.createQuery(hql);
			transaction = session.beginTransaction();
			count = query.executeUpdate();
			transaction.commit();
		} catch (HibernateException hibernateException) {
			transaction.rollback();
			System.out.println("Transaction has been rolled back " + hibernateException.getMessage());
		} finally {
			if (session != null) {
				session.close();
				System.out.println("session is closed");
			}
		}
		return count;
	}

	@Override
	public RegisterEntity getEntityByEmail(String email) {
		System.out.println("Invoked getAllMembers()");
		Session session = null;
		try {
			session = sessionfact.openSession();
			session.beginTransaction();

			// Logic with parameter binding
			Query query = session.createQuery("from RegisterEntity where email = :userEmail ");
			query.setParameter("userEmail", email); // Binding parameter
			RegisterEntity entity = (RegisterEntity) query.uniqueResult();

			// Committing the transaction
			session.getTransaction().commit();
			System.out.println(entity);

			return entity;

		} catch (Exception e) {
			e.printStackTrace();
			if (session != null && session.getTransaction().isActive()) {
				session.getTransaction().rollback();
				System.out.println("View member Query Rollbacked.");
			}

		} finally {
			if (session != null) {
				session.close();
				System.out.println("Session is closed");
			}
		}
		return null;
	}

}
