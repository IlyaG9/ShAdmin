package com.ilyag9.sh.server.dao.impl;

import org.springframework.stereotype.Repository;

import com.ilyag9.sh.server.dao.RemarkDAO;
import com.ilyag9.sh.server.dao.entity.RemarkEntity;

@Repository("remarkDao")
public class RemarkDAOImpl extends AbstractDAOImpl<RemarkEntity> implements RemarkDAO{

	public RemarkDAOImpl() {
		super(RemarkEntity.class);
	}


	
}
