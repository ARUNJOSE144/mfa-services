package com.sixdee.magik.services.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.sixdee.magik.services.dao.PrePostAnalyticsDAO;
import com.sixdee.magik.services.model.PrePostAnalyticsChildKPITO;
import com.sixdee.magik.services.model.PrePostAnalyticsDataTO;
import com.sixdee.magik.services.model.PrePostAnalyticsKPITO;
import com.sixdee.magik.services.model.PrePostAnalyticsReportsTO;

/**
 * @author Nakhil Kurian
 * @Date : August, 2020
 *
 */

@Repository
public class PrePostAnalyticsDAOImpl implements PrePostAnalyticsDAO {

	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;

	String pattern = "yyyy-MM-dd";
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
	Query query;
	String hql;

	@SuppressWarnings({ "unused", "unchecked" })
	@Override
	public PrePostAnalyticsReportsTO getPrePostAnalyticsData(PrePostAnalyticsReportsTO prePostTO) {

		LinkedHashMap<String, String> preDataMap = new LinkedHashMap<>();
		LinkedHashMap<String, String> postDataMap = new LinkedHashMap<>();
		LinkedHashMap<String, String> totalDataMap = new LinkedHashMap<>();
		PrePostAnalyticsReportsTO prePostAnalyticsReportsTO = new PrePostAnalyticsReportsTO();

		try {
			LinkedHashMap<Integer, ArrayList<String>> prepostKPIMap = getPrePostAnalyticsChildKpis();

			Session session = sessionFactory.getCurrentSession();
			PrePostAnalyticsDataTO dataTO1 = null;

			ArrayList<PrePostAnalyticsDataTO> dataList = new ArrayList<PrePostAnalyticsDataTO>();
			double totalCGCountBefore = 0;
			double totalCGCountAfter = 0;

			double totalTGCountBefore = 0;
			double totalTGCountAfter = 0;

			double totalTGConversionBefore = 0;
			double totalTGConversionAfter = 0;

			double totalCGConversionBefore = 0;
			double totalCGConversionAfter = 0;

			String hql = "";
			Query query = null;

			double totalCGBefore = 0;
			double totalCGAfter = 0;

			double totalTGBefore = 0;
			double totalTGAfter = 0;

			double totalTGBefore1 = 0;
			double totalTGAfter2 = 0;

			double totalCGBefore1 = 0;
			double totalCGAfter2 = 0;

			// prepost

			hql = "select kpi,sum(tgCount),sum(postTG), sum(cgCount),sum(postCG), sum(tgConversionCount),sum(postTGConversion),"
					+ " sum(cgConversionCount) ,sum(postCGConversion)from  PrePostAnalyticsReportsTO"
					+ " where CAMPAIGN_ID='" + prePostTO.getCampaignId() + "'   and  DATE_FIELD='"
					+ prePostTO.getDateField() + "'  and kpi in (:kpis) group by kpi   ";

			System.out.println("query------------>" + hql);

			query = session.createQuery(hql);
			query.setParameterList("kpis", prepostKPIMap.get(1));

			List<Object[]> objects = query.list();

			for (Object[] objArray : objects) {
				totalTGBefore += Double.parseDouble(objArray[1] + "");
				totalTGAfter += Double.parseDouble(objArray[2] + "");

				totalCGBefore += Double.parseDouble(objArray[3] + "");
				totalCGAfter += Double.parseDouble(objArray[4] + "");

				totalTGBefore1 += Double.parseDouble(objArray[5] + "");
				totalTGAfter2 += Double.parseDouble(objArray[6] + "");

				totalCGBefore1 += Double.parseDouble(objArray[7] + "");
				totalCGAfter2 += Double.parseDouble(objArray[8] + "");

				preDataMap.put(objArray[0] + "", objArray[1] + ":" + objArray[2] + ":" + objArray[3] + ":" + objArray[4]
						+ ":" + objArray[5] + ":" + objArray[6] + ":" + objArray[7] + ":" + objArray[8]);

			}

			hql = "select kpi,sum(tgCount),sum(postTG), sum(cgCount),sum(postCG), sum(tgConversionCount),sum(postTGConversion),"
					+ " sum(cgConversionCount) ,sum(postCGConversion)from  PrePostAnalyticsReportsTO"
					+ " where CAMPAIGN_ID='" + prePostTO.getCampaignId() + "'   and  DATE_FIELD='"
					+ prePostTO.getDateField() + "'  and kpi in (:kpis) group by kpi   ";

			query = session.createQuery(hql);
			query.setParameterList("kpis", prepostKPIMap.get(1));

			objects = query.list();

			for (Object[] objArray : objects) {
				totalTGBefore += Double.parseDouble(objArray[1] + "");
				totalTGAfter += Double.parseDouble(objArray[2] + "");

				totalCGBefore += Double.parseDouble(objArray[3] + "");
				totalCGAfter += Double.parseDouble(objArray[4] + "");

				totalTGBefore1 += Double.parseDouble(objArray[5] + "");
				totalTGAfter2 += Double.parseDouble(objArray[6] + "");

				totalCGBefore1 += Double.parseDouble(objArray[7] + "");
				totalCGAfter2 += Double.parseDouble(objArray[8] + "");

				postDataMap.put(objArray[0] + "", objArray[1] + ":" + objArray[2] + ":" + objArray[3] + ":"
						+ objArray[4] + ":" + objArray[5] + ":" + objArray[6] + ":" + objArray[7] + ":" + objArray[8]);

			}

			dataTO1 = new PrePostAnalyticsDataTO();
			dataTO1.setType("Before");
			dataTO1.setColor("#FAD8A1");

			dataTO1.setCgCount(totalCGCountBefore + "");
			dataTO1.setTgCount(totalTGCountBefore + "");

			dataTO1.setTgConversionCount(totalTGConversionBefore + "");
			dataTO1.setCgConversionCount(totalCGConversionBefore + "");

			dataTO1.setPostcgCount(totalCGCountAfter + "");
			dataTO1.setPosttgCount(totalTGCountAfter + "");

			dataTO1.setPostcgConversionCount(totalCGConversionAfter + "");
			dataTO1.setPosttgConversionCount(totalTGConversionAfter + "");

			dataList.add(dataTO1);

			dataTO1 = new PrePostAnalyticsDataTO();
			dataTO1.setType("After");
			dataTO1.setColor("#F6B244");

			dataTO1.setPostcgCount(totalCGCountAfter + "");
			dataTO1.setPosttgCount(totalTGCountAfter + "");

			dataTO1.setPostcgConversionCount(totalCGConversionAfter + "");
			dataTO1.setPosttgConversionCount(totalTGConversionAfter + "");
			dataList.add(dataTO1);

			prePostAnalyticsReportsTO.setAnalyticsDataList(dataList);

			System.out.println(totalCGCountBefore + ":::" + totalTGCountBefore + "::" + totalCGCountAfter + "::::"
					+ totalTGCountAfter);

			Map<String, String> copy = new LinkedHashMap<String, String>(preDataMap);
			String firstElementKeyinMap = "";
			for (String key : preDataMap.keySet()) {
				if (key.contains("Total"))
					firstElementKeyinMap = key;

			}
			preDataMap.keySet().retainAll(Collections.singleton(firstElementKeyinMap));
			preDataMap.putAll(copy);

			copy = new LinkedHashMap<String, String>(postDataMap);
			for (String key : postDataMap.keySet()) {
				if (key.contains("Total"))
					firstElementKeyinMap = key;

			}
			postDataMap.keySet().retainAll(Collections.singleton(firstElementKeyinMap));
			postDataMap.putAll(copy);

			prePostAnalyticsReportsTO.setPreDataMap(preDataMap);
			prePostAnalyticsReportsTO.setPostDataMap(postDataMap);
			prePostAnalyticsReportsTO.setTotalDataMap(totalDataMap);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return prePostAnalyticsReportsTO;

	}

	@SuppressWarnings({ "unused", "unchecked" })
	@Override
	public PrePostAnalyticsReportsTO getPrePostAnalyticsDataOfLatestCampaign() {
		LinkedHashMap<String, String> preDataMap = new LinkedHashMap<>();
		LinkedHashMap<String, String> postDataMap = new LinkedHashMap<>();
		LinkedHashMap<String, String> totalDataMap = new LinkedHashMap<>();
		PrePostAnalyticsReportsTO prePostAnalyticsReportsTO = new PrePostAnalyticsReportsTO();

		try {
			LinkedHashMap<Integer, ArrayList<String>> prepostKPIMap = getPrePostAnalyticsChildKpis();

			Session session = sessionFactory.getCurrentSession();
			PrePostAnalyticsDataTO dataTO1 = null;

			ArrayList<PrePostAnalyticsDataTO> dataList = new ArrayList<PrePostAnalyticsDataTO>();
			double totalCGCountBefore = 0;
			double totalCGCountAfter = 0;

			double totalTGCountBefore = 0;
			double totalTGCountAfter = 0;

			double totalTGConversionBefore = 0;
			double totalTGConversionAfter = 0;

			double totalCGConversionBefore = 0;
			double totalCGConversionAfter = 0;

			String hql = "";
			Query query = null;

			double totalCGBefore = 0;
			double totalCGAfter = 0;

			double totalTGBefore = 0;
			double totalTGAfter = 0;

			double totalTGBefore1 = 0;
			double totalTGAfter2 = 0;

			double totalCGBefore1 = 0;
			double totalCGAfter2 = 0;

			// pre
			hql = "select kpi,sum(tgCount),sum(postTG), sum(cgCount),sum(postCG), sum(tgConversionCount),sum(postTGConversion),"
					+ " sum(cgConversionCount),sum(postCGConversion) from  PrePostAnalyticsReportsTO where kpi in (:kpis) group by kpi";
			System.out.println("query------------>" + hql);
			query = session.createQuery(hql);
			query.setParameterList("kpis", prepostKPIMap.get(1));

			List<Object[]> objects = query.list();

			for (Object[] objArray : objects) {
				totalTGBefore += Double.parseDouble(objArray[1] + "");
				totalCGBefore += Double.parseDouble(objArray[2] + "");

				totalTGBefore1 += Double.parseDouble(objArray[3] + "");
				totalCGBefore1 += Double.parseDouble(objArray[4] + "");

				totalTGAfter += Double.parseDouble(objArray[5] + "");
				totalCGAfter += Double.parseDouble(objArray[6] + "");

				totalTGAfter2 += Double.parseDouble(objArray[7] + "");
				totalCGAfter2 += Double.parseDouble(objArray[8] + "");

				preDataMap.put(objArray[0] + "", objArray[1] + ":" + objArray[2] + ":" + objArray[3] + ":" + objArray[4]
						+ ":" + objArray[5] + ":" + objArray[6] + ":" + objArray[7] + ":" + objArray[8]);
			}

			hql = "select kpi,sum(tgCount),sum(postTG), sum(cgCount),sum(postCG), sum(tgConversionCount),sum(postTGConversion),"
					+ " sum(cgConversionCount),sum(postCGConversion) from  PrePostAnalyticsReportsTO where kpi in (:kpis) group by kpi";
			query = session.createQuery(hql);
			query.setParameterList("kpis", prepostKPIMap.get(1));

			objects = query.list();

			for (Object[] objArray : objects) {
				totalTGAfter += Double.parseDouble(objArray[1] + "");
				totalCGAfter += Double.parseDouble(objArray[2] + "");

				totalTGAfter2 += Double.parseDouble(objArray[3] + "");
				totalCGAfter2 += Double.parseDouble(objArray[4] + "");

				totalTGBefore += Double.parseDouble(objArray[5] + "");
				totalCGBefore += Double.parseDouble(objArray[6] + "");

				totalTGBefore1 += Double.parseDouble(objArray[7] + "");
				totalCGBefore1 += Double.parseDouble(objArray[8] + "");

				postDataMap.put(objArray[0] + "", objArray[1] + ":" + objArray[2] + ":" + objArray[3] + ":"
						+ objArray[4] + ":" + objArray[5] + ":" + objArray[6] + ":" + objArray[7] + ":" + objArray[8]);
			}

			dataTO1 = new PrePostAnalyticsDataTO();
			dataTO1.setType("Before");
			dataTO1.setColor("#FAD8A1");

			dataTO1.setCgCount(totalCGCountBefore + "");
			dataTO1.setTgCount(totalTGCountBefore + "");

			dataTO1.setTgConversionCount(totalTGConversionBefore + "");
			dataTO1.setCgConversionCount(totalCGConversionBefore + "");

			dataTO1.setPostcgCount(totalCGCountAfter + "");
			dataTO1.setPosttgCount(totalTGCountAfter + "");

			dataTO1.setPostcgConversionCount(totalCGConversionAfter + "");
			dataTO1.setPosttgConversionCount(totalTGConversionAfter + "");

			dataList.add(dataTO1);

			dataTO1 = new PrePostAnalyticsDataTO();
			dataTO1.setType("After");
			dataTO1.setColor("#F6B244");

			dataTO1.setPostcgCount(totalCGCountAfter + "");
			dataTO1.setPosttgCount(totalTGCountAfter + "");

			dataTO1.setPostcgConversionCount(totalCGConversionAfter + "");
			dataTO1.setPosttgConversionCount(totalTGConversionAfter + "");
			dataList.add(dataTO1);

			prePostAnalyticsReportsTO.setAnalyticsDataList(dataList);

			System.out.println(totalCGCountBefore + ":::" + totalTGCountBefore + "::" + totalCGCountAfter + "::::"
					+ totalTGCountAfter);

			Map<String, String> copy = new LinkedHashMap<String, String>(preDataMap);
			String firstElementKeyinMap = "";
			for (String key : preDataMap.keySet()) {
				if (key.contains("Total"))
					firstElementKeyinMap = key;

			}
			preDataMap.keySet().retainAll(Collections.singleton(firstElementKeyinMap));
			preDataMap.putAll(copy);

			copy = new LinkedHashMap<String, String>(postDataMap);
			for (String key : postDataMap.keySet()) {
				if (key.contains("Total"))
					firstElementKeyinMap = key;

			}
			postDataMap.keySet().retainAll(Collections.singleton(firstElementKeyinMap));
			postDataMap.putAll(copy);

			prePostAnalyticsReportsTO.setPreDataMap(preDataMap);
			prePostAnalyticsReportsTO.setPostDataMap(postDataMap);
			prePostAnalyticsReportsTO.setTotalDataMap(totalDataMap);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return prePostAnalyticsReportsTO;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PrePostAnalyticsKPITO> getPrePostAnalyticsKpis() {
		List<PrePostAnalyticsKPITO> kpisList = new ArrayList<>();
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(PrePostAnalyticsKPITO.class);
		criteria.addOrder(Order.asc("id"));
		kpisList = criteria.list();
		return kpisList;
	}

	@SuppressWarnings("unchecked")
	public LinkedHashMap<Integer, ArrayList<String>> getPrePostAnalyticsChildKpis() {
		LinkedHashMap<Integer, ArrayList<String>> kpisMap = new LinkedHashMap<>();
		ArrayList<String> childKpisList;
		List<PrePostAnalyticsKPITO> kpisList = new ArrayList<>();
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(PrePostAnalyticsKPITO.class);
		criteria.addOrder(Order.asc("id"));
		kpisList = criteria.list();
		if (!kpisList.isEmpty()) {
			for (PrePostAnalyticsKPITO to : kpisList) {
				childKpisList = new ArrayList<>();
				criteria = session.createCriteria(PrePostAnalyticsChildKPITO.class);
				criteria.add(Restrictions.eq("parentId", to.getId()));
				List<PrePostAnalyticsChildKPITO> childList = criteria.list();
				if (!childList.isEmpty()) {
					for (PrePostAnalyticsChildKPITO childTO : childList) {
						childKpisList.add(childTO.getKpi());
					}

				}
				kpisMap.put(to.getId(), childKpisList);
			}
		}

		return kpisMap;
	}

}
