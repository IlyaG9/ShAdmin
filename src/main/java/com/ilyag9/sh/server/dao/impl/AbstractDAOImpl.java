package com.ilyag9.sh.server.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ilyag9.sh.server.dao.DAO;

@Transactional
public abstract class AbstractDAOImpl<T> implements DAO<T> {

	@Autowired
	private SessionFactory sessionFactory;

	private final Class<T> clazz;

	public AbstractDAOImpl(Class<T> clazz) {
		this.clazz = clazz;
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void create(T object) {
		if (object != null) {
			 getSession().save(object);
		}
	}

	public T get(Long id) {
		return (T) getSession().get(clazz, id);
	}

}
