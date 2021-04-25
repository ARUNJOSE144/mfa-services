package com.sixdee.magik.services.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.sixdee.magik.services.adaptor.BlackListApiTO;
import com.sixdee.magik.services.dao.BlackListApiDao;
import com.sixdee.magik.services.model.ActionControlGroupTO;
import com.sixdee.magik.services.model.BlackListDetailsTO;
import com.sixdee.magik.services.model.CampaignMasterTO;
import com.sixdee.magik.services.model.OverAllCampaignPushTO;
import com.sixdee.magik.services.model.TokenMaster;

import org.springframework.core.env.Environment;
import org.springframework.dao.DataIntegrityViolationException;

/**
 * @author Nakhil Kurian
 * @Date : April 2020
 *
 */

@Repository
public class BlackListApiDaoImpl implements BlackListApiDao {

	Logger logger = Logger.getLogger(BlackListApiDaoImpl.class);
	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;
	@SuppressWarnings("unused")
	private Query query = null;
	private Session session = null;
	private String hql = null;

	@Autowired
	private Environment env;
	String marketingPlan = null;
	String policyId = null;
	String optIn = null;
	String optOut = null;
	String description = null;
	String dateValue = null;
	BlackListDetailsTO detailsTO = new BlackListDetailsTO();
	Date date = null;

	@Override
	public BlackListApiTO updateBlackListApi(BlackListApiTO blackListTO) {
		marketingPlan = env.getProperty("marketingPlan");
		policyId = env.getProperty("PolicyId");
		optIn = env.getProperty("optIn");
		optOut = env.getProperty("optOut");
		description = env.getProperty("description");
		String status = "SC0001";
		System.out.println("blackListTO  :  " + blackListTO.toString());
		detailsTO.setMsisdn(blackListTO.getMsisdn());
		detailsTO.setBlackPolicyId(Integer.parseInt(policyId));
		detailsTO.setMarketingPlanId(Integer.parseInt(marketingPlan));
		detailsTO.setDescription(description);
		session = sessionFactory.getCurrentSession();
		if (blackListTO.getExpiryDate() != null && blackListTO.getExpiryDate() != "") {
			dateValue = blackListTO.getExpiryDate();
			System.out.println("dateValue  :  " + dateValue.toString());
			try {
				date = new SimpleDateFormat("yyyy-MM-dd").parse(dateValue);
				System.out.println("final date  ::" + date);
				detailsTO.setExpiryDate(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("else ::  ");
			detailsTO.setExpiryDate(null);
		}
		if (blackListTO.getChannelType() != null && blackListTO.getChannelType() != "") {
			System.out.println("Channel Type :: " + blackListTO.getChannelType().toString());
			detailsTO.setChannelType(blackListTO.getChannelType());
		} else {
			detailsTO.setChannelType(null);
		}
		if (Integer.parseInt(blackListTO.getAction()) == Integer.parseInt(optIn)) {
			System.out.println("detailsTO ::  " + detailsTO.toString());
			try {
				System.out.println("date " + date);
				session.save(detailsTO);
				status = "SC0000";
				blackListTO.setStatus(status);
				blackListTO.setAction(blackListTO.getAction());
			} catch (DataIntegrityViolationException e) {
				System.out.println("Catch :");
				status = "SC0001";
				blackListTO.setStatus(status);
				e.printStackTrace();
			}
		}
		if (Integer.parseInt(blackListTO.getAction()) == Integer.parseInt(optOut)) {
			System.out.println("delete detailsTO  ::  " + detailsTO.toString());
			String hql = "delete from BlackListDetailsTO where msisdn = " + blackListTO.getMsisdn();
			session.createQuery(hql).executeUpdate();
			status = "SC0000";
			blackListTO.setAction(blackListTO.getAction());
			blackListTO.setStatus(status);
		}
		return blackListTO;
	}

	@Override
	public BlackListApiTO getStatus(BlackListApiTO blackListTO) {
		session = sessionFactory.getCurrentSession();
		String status = null;
		Criteria criteria = session.createCriteria(BlackListDetailsTO.class);
		criteria.add(Restrictions.eq("msisdn", blackListTO.getMsisdn()));
		if (blackListTO.getChannelType() != null) {
			criteria.add(Restrictions.eq("channelType", blackListTO.getChannelType()));
		}
		BlackListDetailsTO to = (BlackListDetailsTO) criteria.uniqueResult();
		if (to == null) {
			System.out.println("if :::  ");
			status = "SC0001";
			blackListTO.setStatus(status);
			blackListTO.setMsisdn(blackListTO.getMsisdn());
		} else {
			System.out.println("else :::  ");
			status = "SC0000";
			blackListTO.setStatus(status);
			blackListTO.setMsisdn(blackListTO.getMsisdn());
		}
		return blackListTO;
	}

}
