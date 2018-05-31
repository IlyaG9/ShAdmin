package com.ilyag9.sh.server.dao;

public interface DAO<T> {

	void create(T object);
	
	 T get(Long id);
	
}
