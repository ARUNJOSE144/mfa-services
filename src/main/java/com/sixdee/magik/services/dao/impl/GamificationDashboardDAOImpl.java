package com.sixdee.magik.services.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.sixdee.magik.services.dao.GamificationDashboardDao;
import com.sixdee.magik.services.model.AonWiseGameficationTO;
import com.sixdee.magik.services.model.GamifDBUsersVsNonusersTO;
import com.sixdee.magik.services.model.LoyaltyCaategoryWiseGamificationTO;
import com.sixdee.magik.services.model.RewardsRedeemedTO;
import com.sixdee.magik.services.model.SlabWiseGamificationTO;
import com.sixdee.magik.services.model.SummaryCountTO;
import com.sixdee.magik.services.model.Top5PlayedGamesTO;
import com.sixdee.magik.services.model.UserWebAppTO;

/**
 * @author ramesh.cheerla
 * @Date : July, 2020
 *
 */
@Repository
public class GamificationDashboardDAOImpl implements GamificationDashboardDao {

	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;
	private Session session = null;
	private String hql;

	@SuppressWarnings("unchecked")
	@Override
	public List<GamifDBUsersVsNonusersTO> getUsersVsNonUsers() {

		session = sessionFactory.getCurrentSession();
		List<GamifDBUsersVsNonusersTO> list = new ArrayList<GamifDBUsersVsNonusersTO>();
		hql = "from GamifDBUsersVsNonusersTO";
		list = (List<GamifDBUsersVsNonusersTO>) session.createQuery(hql).list();

		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserWebAppTO> getUserWebApp() {
		session = sessionFactory.getCurrentSession();
		List<UserWebAppTO> list = new ArrayList<UserWebAppTO>();
		hql = "from UserWebAppTO";
		list = (List<UserWebAppTO>) session.createQuery(hql).list();

		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LoyaltyCaategoryWiseGamificationTO> getLoyaltyCategoryWise() {
		session = sessionFactory.getCurrentSession();
		List<LoyaltyCaategoryWiseGamificationTO> list = new ArrayList<LoyaltyCaategoryWiseGamificationTO>();
		hql = "from LoyaltyCaategoryWiseGamificationTO";
		list = (List<LoyaltyCaategoryWiseGamificationTO>) session.createQuery(hql).list();

		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Top5PlayedGamesTO> getTop5Played() {
		session = sessionFactory.getCurrentSession();
		List<Top5PlayedGamesTO> list = new ArrayList<Top5PlayedGamesTO>();
		hql = "from Top5PlayedGamesTO";
		list = (List<Top5PlayedGamesTO>) session.createQuery(hql).list();

		return list;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AonWiseGameficationTO> getAonWiseUsers() {
		session = sessionFactory.getCurrentSession();
		List<AonWiseGameficationTO> list = new ArrayList<AonWiseGameficationTO>();
		hql = "from AonWiseGameficationTO";
		list = (List<AonWiseGameficationTO>) session.createQuery(hql).list();

		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SlabWiseGamificationTO> getSlabWiseUSers() {
		session = sessionFactory.getCurrentSession();
		List<SlabWiseGamificationTO> list = new ArrayList<SlabWiseGamificationTO>();
		hql = "from SlabWiseGamificationTO";
		list = (List<SlabWiseGamificationTO>) session.createQuery(hql).list();

		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RewardsRedeemedTO> getRewardsRedeemed() {
		session = sessionFactory.getCurrentSession();
		List<RewardsRedeemedTO> list = new ArrayList<RewardsRedeemedTO>();
		hql = "from RewardsRedeemedTO";
		list = (List<RewardsRedeemedTO>) session.createQuery(hql).list();

		return list;
	}

	@Override
	public List<SummaryCountTO> getSummaryCount() {
		session = sessionFactory.getCurrentSession();
		List<SummaryCountTO> list = new ArrayList<SummaryCountTO>();
		hql = "from SummaryCountTO";
		list = (List<SummaryCountTO>) session.createQuery(hql).list();

		return list;
	}
}