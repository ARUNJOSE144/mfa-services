package com.sixdee.magik.services.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.sixdee.magik.services.dao.LoyaltyDahBoardDao;
import com.sixdee.magik.services.model.ChannelWiseRedeemptionTO;
import com.sixdee.magik.services.model.CustomerMigrationTO;
import com.sixdee.magik.services.model.EarnedRedeemptionTO;
import com.sixdee.magik.services.model.EarnedRedeemptionTO_1;
import com.sixdee.magik.services.model.LoyaltyPointsRedeemedTO;
import com.sixdee.magik.services.model.LoyaltyPointsTrendTO;
import com.sixdee.magik.services.model.MAKASIB_AcccountTO;
import com.sixdee.magik.services.model.MercantWiseRedeemptionTO;
import com.sixdee.magik.services.model.NewEnrollmentsTO;
import com.sixdee.magik.services.model.OverAllCampaignPushTO;
import com.sixdee.magik.services.model.TotalRewardPointsTO;

/**
 * @author Nakhil Kurian
 * @Date : August, 2020
 *
 */

@Repository
public class LoyaltyDashBoardDaoImpl implements LoyaltyDahBoardDao {

	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;
	private String hql = null;
	Criteria criteria = null;
	String pattern = "MMM-dd";
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

	@SuppressWarnings("unchecked")
	@Override
	public List<NewEnrollmentsTO> getNewEnrollments() {
		Session session = sessionFactory.getCurrentSession();
		List<NewEnrollmentsTO> list = new ArrayList<NewEnrollmentsTO>();
		NewEnrollmentsTO to = null;
		List<Object[]> res = null;
		hql = " select id, date, s2g, g2p,mig_trend from NewEnrollmentsTO order by id asc";
		res = session.createQuery(hql).list();
		for (Object[] obj : res) {
			to = new NewEnrollmentsTO();
			to.setId(Integer.parseInt(obj[0] + ""));
			//to.setDate(obj[1] + "");
			to.setCreateDateUI(simpleDateFormat.format(obj[1]));
			to.setS2g(Integer.parseInt(obj[2] + ""));
			to.setG2p(Integer.parseInt(obj[3] + ""));
			to.setMig_trend(Integer.parseInt(obj[4] + ""));
			list.add(to);
		}

		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LoyaltyPointsTrendTO> getLoyaltyPointTrend() {
		Session session = sessionFactory.getCurrentSession();
		List<LoyaltyPointsTrendTO> list = new ArrayList<LoyaltyPointsTrendTO>();
		LoyaltyPointsTrendTO to = null;
		List<Object[]> res = null;
		hql = " select id, date, gold, silver,platinum from LoyaltyPointsTrendTO order by id asc";
		res = session.createQuery(hql).list();
		for (Object[] obj : res) {
			to = new LoyaltyPointsTrendTO();
			to.setId(Integer.parseInt(obj[0] + ""));
			//to.setDate(obj[1] + "");
			to.setCreateDateUI(simpleDateFormat.format(obj[1]));
			to.setGold(Integer.parseInt(obj[2] + ""));
			to.setSilver(Integer.parseInt(obj[3] + ""));
			to.setPlatinum(Integer.parseInt(obj[4] + ""));
			list.add(to);
		}

		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MAKASIB_AcccountTO> getMakasibAccounts() {
		Session session = sessionFactory.getCurrentSession();
		List<MAKASIB_AcccountTO> list = new ArrayList<MAKASIB_AcccountTO>();
		MAKASIB_AcccountTO to = null;
		List<Object[]> res = null;
		hql = " select id, date, gold, silver,platinum from MAKASIB_AcccountTO order by id asc";
		res = session.createQuery(hql).list();
		for (Object[] obj : res) {
			to = new MAKASIB_AcccountTO();
			to.setId(Integer.parseInt(obj[0] + ""));
			///to.setDate(obj[1] + "");
			to.setCreateDateUI(simpleDateFormat.format(obj[1]));
			to.setGold(Integer.parseInt(obj[2] + ""));
			to.setSilver(Integer.parseInt(obj[3] + ""));
			to.setPlatinum(Integer.parseInt(obj[4] + ""));
			list.add(to);
		}

		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LoyaltyPointsRedeemedTO> getLoyaltyPointsRedeemed() {
		Session session = sessionFactory.getCurrentSession();
		List<LoyaltyPointsRedeemedTO> list = new ArrayList<LoyaltyPointsRedeemedTO>();
		LoyaltyPointsRedeemedTO to = null;
		List<Object[]> res = null;
		hql = " select id, date, points, subCount from LoyaltyPointsRedeemedTO order by id asc";
		res = session.createQuery(hql).list();
		for (Object[] obj : res) {
			to = new LoyaltyPointsRedeemedTO();
			to.setId(Integer.parseInt(obj[0] + ""));
			//to.setDate(obj[1] + "");
			to.setCreateDateUI(simpleDateFormat.format(obj[1]));
			to.setPoints(Integer.parseInt(obj[2] + ""));
			to.setSubCount(Integer.parseInt(obj[3] + ""));
			list.add(to);
		}

		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CustomerMigrationTO> getCustomerMigration() {
		Session session = sessionFactory.getCurrentSession();
		List<CustomerMigrationTO> list = new ArrayList<CustomerMigrationTO>();
		CustomerMigrationTO to = null;
		List<Object[]> res = null;
		hql = " select id, date, s2g, g2p from CustomerMigrationTO order by id asc";
		res = session.createQuery(hql).list();
		for (Object[] obj : res) {
			to = new CustomerMigrationTO();
			to.setId(Integer.parseInt(obj[0] + ""));
			//to.setDate(obj[1] + "");
			to.setCreateDateUI(simpleDateFormat.format(obj[1]));
			to.setS2g(Integer.parseInt(obj[2] + ""));
			to.setG2p(Integer.parseInt(obj[3] + ""));
			list.add(to);
		}

		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MercantWiseRedeemptionTO> getMercantWise() {
		Session session = sessionFactory.getCurrentSession();
		List<MercantWiseRedeemptionTO> list = new ArrayList<MercantWiseRedeemptionTO>();
		MercantWiseRedeemptionTO to = null;
		List<Object[]> res = null;
		hql = " select id, category, ordered, delivered,pending from MercantWiseRedeemptionTO order by id asc";
		res = session.createQuery(hql).list();
		for (Object[] obj : res) {
			to = new MercantWiseRedeemptionTO();
			to.setId(Integer.parseInt(obj[0] + ""));
			to.setCategory(obj[1] + "");
			to.setOrdered(obj[2] + "");
			to.setDelivered(obj[3] + "");
			to.setPending(obj[4] + "");
			list.add(to);
		}

		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EarnedRedeemptionTO> getEarnedRedeemption() {
		Session session = sessionFactory.getCurrentSession();
		List<EarnedRedeemptionTO> list = new ArrayList<EarnedRedeemptionTO>();
		EarnedRedeemptionTO to = null;
		List<Object[]> res = null;
		hql = " select id, date, points, subCount from EarnedRedeemptionTO order by id asc";
		res = session.createQuery(hql).list();
		for (Object[] obj : res) {
			to = new EarnedRedeemptionTO();
			to.setId(Integer.parseInt(obj[0] + ""));
			//to.setDate(obj[1] + "");
			to.setCreateDateUI(simpleDateFormat.format(obj[1]));
			to.setPoints(obj[2] + "");
			to.setSubCount(obj[3] + "");
			list.add(to);
		}

		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TotalRewardPointsTO> getTotalReward() {
		Session session = sessionFactory.getCurrentSession();
		List<TotalRewardPointsTO> list = new ArrayList<TotalRewardPointsTO>();
		TotalRewardPointsTO to = null;
		List<Object[]> res = null;
		hql = " select id, date, totPoints from TotalRewardPointsTO order by id asc";
		res = session.createQuery(hql).list();
		for (Object[] obj : res) {
			to = new TotalRewardPointsTO();
			to.setId(Integer.parseInt(obj[0] + ""));
			//to.setDate(obj[1] + "");
			to.setCreateDateUI(simpleDateFormat.format(obj[1]));
			to.setTotPoints(obj[2] + "");
			list.add(to);
		}

		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ChannelWiseRedeemptionTO> getChannelRedeemption() {
		Session session = sessionFactory.getCurrentSession();
		List<ChannelWiseRedeemptionTO> list = new ArrayList<ChannelWiseRedeemptionTO>();
		ChannelWiseRedeemptionTO to = null;
		List<Object[]> res = null;
		hql = " select id, channel, gold,silver,platinum from ChannelWiseRedeemptionTO order by id asc";
		res = session.createQuery(hql).list();
		for (Object[] obj : res) {
			to = new ChannelWiseRedeemptionTO();
			to.setId(Integer.parseInt(obj[0] + ""));
			to.setChannel(obj[1] + "");
			to.setGold(obj[2] + "");
			to.setSilver(obj[3] + "");
			to.setPlatinum(obj[4] + "");
			list.add(to);
		}

		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EarnedRedeemptionTO_1> getEarnedRedeemption_1() {
		Session session = sessionFactory.getCurrentSession();
		List<EarnedRedeemptionTO_1> list = new ArrayList<EarnedRedeemptionTO_1>();
		EarnedRedeemptionTO_1 to = null;
		List<Object[]> res = null;
		hql = " select id, date, points, subCount from EarnedRedeemptionTO_1 order by id asc";
		res = session.createQuery(hql).list();
		for (Object[] obj : res) {
			to = new EarnedRedeemptionTO_1();
			to.setId(Integer.parseInt(obj[0] + ""));
			//to.setDate(obj[1] + "");
			to.setCreateDateUI(simpleDateFormat.format(obj[1]));
			to.setPoints(obj[2] + "");
			to.setSubCount(obj[3] + "");
			list.add(to);
		}

		return list;
	}
}
