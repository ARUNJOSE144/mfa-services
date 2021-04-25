package com.sixdee.magik.services.dao.impl;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.sixdee.magik.services.dao.CampaignDashBoardDao;
import com.sixdee.magik.services.model.ARPUSegmentsTO;
import com.sixdee.magik.services.model.CampaignPerformanceTO;
import com.sixdee.magik.services.model.CampaignResponseTO;
import com.sixdee.magik.services.model.HourlyResponseTO;
import com.sixdee.magik.services.model.NotificationsTO;
import com.sixdee.magik.services.model.OverAllCampaignPushTO;
import com.sixdee.magik.services.model.ROI_Total_RevenueTO;
import com.sixdee.magik.services.model.TC_CG_ResponseTO;
import com.sixdee.magik.services.model.TGCountResponseTO;
import com.sixdee.magik.services.model.TG_CountTO;
import com.sixdee.magik.services.model.Top5CampaignTO;

/**
 * @author Nakhil Kurian
 * @Date : August, 2020
 *
 */
@Repository
public class CampaignDashBoardDaoImpl implements CampaignDashBoardDao {

	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;
	private String hql = null;
	Criteria criteria = null;
	String pattern = "MMM-dd";
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

	@SuppressWarnings("unchecked")
	@Override
	public List<OverAllCampaignPushTO> getOverallCampaignPush() {
		Session session = sessionFactory.getCurrentSession();
		List<OverAllCampaignPushTO> list = new ArrayList<OverAllCampaignPushTO>();
		OverAllCampaignPushTO to = null;
		List<Object[]> res = null;
		hql = " select id, date, pushedCount, deliveredCount,percentage from OverAllCampaignPushTO order by id asc";
		res = session.createQuery(hql).list();
		for (Object[] obj : res) {
			to = new OverAllCampaignPushTO();
			to.setId(Integer.parseInt(obj[0] + ""));
			to.setCreateDateUI(simpleDateFormat.format(obj[1]));
			to.setPushedCount(obj[2] + "");
			to.setDeliveredCount(obj[3] + "");
			to.setPercentage(obj[4] + "");
			list.add(to);
		}

		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TC_CG_ResponseTO> getTG_CG_Response() {
		Session session = sessionFactory.getCurrentSession();
		List<TC_CG_ResponseTO> list = new ArrayList<TC_CG_ResponseTO>();
		TC_CG_ResponseTO to = null;
		List<Object[]> res = null;
		hql = " select id, date, tgResponse, cgResponse,nrrValue from TC_CG_ResponseTO order by id asc";
		res = session.createQuery(hql).list();
		for (Object[] obj : res) {
			to = new TC_CG_ResponseTO();
			to.setId(Integer.parseInt(obj[0] + ""));
			to.setCreateDateUI(simpleDateFormat.format(obj[1]));
			to.setTgResponse((obj[2] + ""));
			to.setCgResponse((obj[3] + ""));
			to.setNrrValue(obj[4] + "");
			list.add(to);
		}

		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ROI_Total_RevenueTO> getROI_Total_Revenue() {
		Session session = sessionFactory.getCurrentSession();
		List<ROI_Total_RevenueTO> list = new ArrayList<ROI_Total_RevenueTO>();
		ROI_Total_RevenueTO to = null;
		List<Object[]> res = null;
		hql = " select id, date, incrimentalRevenue, totalCost ,netRevenue from ROI_Total_RevenueTO order by id asc";
		res = session.createQuery(hql).list();
		for (Object[] obj : res) {
			to = new ROI_Total_RevenueTO();
			to.setId(Integer.parseInt(obj[0] + ""));
			to.setCreateDateUI(simpleDateFormat.format(obj[1]));
			to.setIncrimentalRevenue((obj[2] + ""));
			to.setTotalCost((obj[3] + ""));
			to.setNetRevenue((obj[4] + ""));
			list.add(to);
		}

		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CampaignPerformanceTO> getCampaignPerformance() {
		Session session = sessionFactory.getCurrentSession();
		List<CampaignPerformanceTO> list = new ArrayList<CampaignPerformanceTO>();
		CampaignPerformanceTO to = null;
		List<Object[]> res = null;
		hql = " select id, date, uniqueTargeted, uniqueDelivered,uniqueReponded from CampaignPerformanceTO order by id asc";
		res = session.createQuery(hql).list();
		for (Object[] obj : res) {
			to = new CampaignPerformanceTO();
			to.setId(Integer.parseInt(obj[0] + ""));
			to.setCreateDateUI(simpleDateFormat.format(obj[1]));
			to.setUniqueTargeted(obj[2] + "");
			to.setUniqueDelivered(obj[3] + "");
			to.setUniqueReponded(obj[4] + "");
			list.add(to);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TG_CountTO> getTG_Count() {
		Session session = sessionFactory.getCurrentSession();
		List<TG_CountTO> list = new ArrayList<TG_CountTO>();
		TG_CountTO to = null;
		List<Object[]> res = null;
		hql = " select id, country, year2004, year2005 from TG_CountTO order by id asc";
		res = session.createQuery(hql).list();
		for (Object[] obj : res) {
			to = new TG_CountTO();
			to.setId(Integer.parseInt(obj[0] + ""));
			to.setCountry(obj[1] + "");
			to.setYear2004(obj[2] + "");
			to.setYear2005(obj[3] + "");
			list.add(to);
		}
		return list;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HourlyResponseTO> getHourlyResponse() {
		Session session = sessionFactory.getCurrentSession();
		List<HourlyResponseTO> list = new ArrayList<HourlyResponseTO>();
		HourlyResponseTO to = null;
		List<Object[]> res = null;
		hql = " select id, time, delievryCount, response from HourlyResponseTO order by id asc";
		res = session.createQuery(hql).list();
		for (Object[] obj : res) {
			to = new HourlyResponseTO();
			to.setId(Integer.parseInt(obj[0] + ""));
			to.setTime(obj[1] + "");
			to.setDelievryCount(obj[2] + "");
			to.setResponse(obj[3] + "");
			list.add(to);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ARPUSegmentsTO> getARPUSegments() {
		Session session = sessionFactory.getCurrentSession();
		List<ARPUSegmentsTO> list = new ArrayList<ARPUSegmentsTO>();
		ARPUSegmentsTO to = null;
		List<Object[]> res = null;
		hql = " select id, name, value  from ARPUSegmentsTO order by id asc";
		res = session.createQuery(hql).list();
		for (Object[] obj : res) {
			to = new ARPUSegmentsTO();
			to.setId(Integer.parseInt(obj[0] + ""));
			to.setName(obj[1] + "");
			to.setValue(obj[2] + "");
			list.add(to);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Top5CampaignTO> getTop5Campaign() {
		Session session = sessionFactory.getCurrentSession();
		List<Top5CampaignTO> list = new ArrayList<Top5CampaignTO>();
		Top5CampaignTO to = null;
		List<Object[]> res = null;
		hql = " select id, campaignName,nrrVlaue ,deliveredCount from Top5CampaignTO order by id asc";
		res = session.createQuery(hql).list();
		for (Object[] obj : res) {
			to = new Top5CampaignTO();
			to.setId(Integer.parseInt(obj[0] + ""));
			to.setCampaignName(obj[1] + "");
			to.setNrrVlaue(obj[2] + "");
			to.setDeliveredCount(obj[3] + "");
			list.add(to);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CampaignResponseTO> getCampaignResponse() {
		Session session = sessionFactory.getCurrentSession();
		List<CampaignResponseTO> list = new ArrayList<CampaignResponseTO>();
		CampaignResponseTO to = null;
		List<Object[]> res = null;
		System.out.println("CampaignResponseTO  :  ");
		hql = " select ID, NAME,COUNT  from DB_CMP_HEADER_INFO order by id asc";
		System.out.println("hql  :  " + hql.toString());
		res = session.createSQLQuery(hql).list();
		for (Object[] obj : res) {
			to = new CampaignResponseTO();
			to.setId(Integer.parseInt(obj[0] + ""));
			to.setName(obj[1] + "");
			to.setCount(obj[2] + "");
			list.add(to);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TGCountResponseTO> getTGCountResponse() {
		Session session = sessionFactory.getCurrentSession();
		List<TGCountResponseTO> list = new ArrayList<TGCountResponseTO>();
		TGCountResponseTO to = null;
		List<Object[]> res = null;
		hql = " select id, date, pushedCount, tgrCount,response from TGCountResponseTO order by id asc";
		res = session.createQuery(hql).list();
		for (Object[] obj : res) {
			to = new TGCountResponseTO();
			to.setId(Integer.parseInt(obj[0] + ""));
			// to.setDate(obj[1] + "");
			to.setCreateDateUI(simpleDateFormat.format(obj[1]));
			to.setPushedCount(obj[2] + "");
			to.setTgrCount(obj[3] + "");
			to.setResponse(obj[4] + "");
			list.add(to);
		}

		return list;
	}

}
