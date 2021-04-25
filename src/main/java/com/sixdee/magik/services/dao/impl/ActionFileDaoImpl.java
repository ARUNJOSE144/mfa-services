package com.sixdee.magik.services.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sixdee.magik.services.dao.ActionFileDao;
import com.sixdee.magik.services.model.ActionFileTO;



@Repository
@Transactional
public class ActionFileDaoImpl implements ActionFileDao{
	static Logger logger = Logger.getLogger(ActionFileDaoImpl.class);
	@Autowired
    private SessionFactory sessionFactory;
	
	private Session session = null;
	@Override
	public void saveFileDetails(List<ActionFileTO> actionFileTOs) {
		// TODO Auto-generated method stub
		System.out.println("in do file service>>");
		try {
			session = sessionFactory.getCurrentSession();
			
			for (ActionFileTO actionFileTO2 : actionFileTOs) {
				session.saveOrUpdate(actionFileTO2);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
