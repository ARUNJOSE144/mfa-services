package com.sixdee.magik.services.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.sixdee.magik.services.dao.ChannelDao;

/**
 * @author ramesh.cheerla
 * @Date : January, 2019
 *
 */

@Repository
public class ChannelDaoImpl implements ChannelDao {

	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;
	private String query = null;

	
}
