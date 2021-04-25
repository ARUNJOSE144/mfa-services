package com.sixdee.magik.services.dao.impl;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.sixdee.magik.services.exception.DAOException;
import com.sixdee.magik.services.model.OverAllCampaignPushTO;
import com.sixdee.magik.services.model.RemoteCopyUserTO;
import com.sixdee.magik.services.model.TokenMaster;

/**
 * @author Basil Jose
 * @version 1.0.0.0
 * @since Jul 4, 2018 : 6:29:28 PM
 */

@Repository
@Transactional
public class TokenMasterDAOImpl {

	private static Logger LOGGER = LoggerFactory.getLogger(TokenMasterDAOImpl.class);

	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;
	private Query query = null;
	private String hql;
	private Session session;

	@Autowired
	TokenDAOImpl tokenRepository;

	@SuppressWarnings("unchecked")
	public TokenMaster generateToken(String token, String userId, String sessionId, String username) {
		TokenMaster tokenMaster = new TokenMaster();
		String finalToken = null;
		String sessionName = null;
		try {
			session = sessionFactory.getCurrentSession();
			tokenMaster.setToken(token);
			tokenMaster.setUserId(userId);

			String tokenName = tokenMaster.getToken();
			if (tokenName.length() > 2) {
				// finalToken = tokenName.substring(0, 2);
				finalToken = tokenName.substring(0, 3);
				// sessionName = "MagikUser_" + finalToken + tokenMaster.getUserId();
				sessionName = "MagikUser" + "(" + username + "_" + tokenMaster.getUserId() + "_" + finalToken + ")";
				System.out.println("finalToken  ::  " + finalToken);
				System.out.println("sessionName  ::  " + sessionName);
				System.out.println("sessionId  ::  " + sessionId);
				// saveRemoteCopyDetails(sessionName, sessionId, userId, token);
			}
			tokenMaster.setSessionId(sessionId);
			tokenMaster.setSessionName(sessionName);

			session.save(tokenMaster);

		} catch (Exception e) {
			LOGGER.error("Failed to store token: " + e.getMessage());
		}
		return tokenMaster;
	}

	@SuppressWarnings("unused")
	private RemoteCopyUserTO saveRemoteCopyDetails(String sessionName, String sessionId, String userId, String token) {
		RemoteCopyUserTO copyTO = new RemoteCopyUserTO();
		session = sessionFactory.getCurrentSession();
		copyTO.setCopyTokenId(sessionId);
		copyTO.setCopyTokenName(sessionName);
		copyTO.setToken(token);
		copyTO.setUserID(userId);
		session.save(copyTO);
		return copyTO;
		// TODO Auto-generated method stub

	}

	public boolean findByUserIdAndToken(String userId, String token) throws DAOException {
		try {
			TokenMaster tokenMaster = tokenRepository.findByUserIdAndToken(userId, token);
			if (tokenMaster != null) {
				return true;
			}
		} catch (Exception e) {
			LOGGER.error("Failed to fetch token: " + e.getMessage());
		}
		return false;
	}

	public boolean deleteLastToken(String userId) throws DAOException {
		try {
			return tokenRepository.deleteByUserId(userId) > 0;
		} catch (Exception e) {
			LOGGER.error("Failed to delete last token: " + e.getMessage());
		}
		return false;
	}

	public List<TokenMaster> getSessionname(String uid, String userId) {
		System.out.println("uid  +   " + uid.toString());

		Session session = sessionFactory.getCurrentSession();
		List<TokenMaster> list = new ArrayList<TokenMaster>();
		TokenMaster to = null;
		List<Object[]> res = null;
		hql = "select tokenId, token, userId, sessionId,sessionName from TokenMaster order by id asc";
		res = session.createQuery(hql).list();
		for (Object[] obj : res) {
			to = new TokenMaster();
			to.setTokenId(Integer.parseInt(obj[0] + ""));
			to.setToken((obj[1].toString()));
			to.setUserId(obj[2].toString() + "");
			to.setSessionId(obj[3].toString() + "");
			to.setSessionName(obj[4].toString() + "");
			list.add(to);
		}

		return list;
	}

}
