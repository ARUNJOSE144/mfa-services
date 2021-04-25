/**
 * 
 */
package com.sixdee.magik.services.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sixdee.magik.services.dao.DashboardCardDAO;
import com.sixdee.magik.services.model.DashboardCardDTO;

/**
 * @author Vinay Kariyappa
 *
 * Oct 3, 2018
 */
@Repository
@Transactional
public class DashboardCardDAOImpl implements DashboardCardDAO {

	@Autowired
	//@Qualifier("reportingSessionFactory")
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;
	
	
	@Override
	public List<DashboardCardDTO> getOnlineDashboardCardDetails(DashboardCardDTO dashboardCardDTO) {
		List<DashboardCardDTO> lis = null;
		Session session = null;
		String query = null;
		try {
			 session = sessionFactory.getCurrentSession();
			if(dashboardCardDTO.getFeature()!=null && dashboardCardDTO.getFeature().equalsIgnoreCase("offline")) {
				if(dashboardCardDTO.isCustomDate() && !dashboardCardDTO.isDefaultDash()) {
					query = "SELECT sum(TARGET_COUNT) as targetCount, sum(BOUNS_CREDITED) as bounsCredited,sum(DELIVERY_SUCCESS_COUNT) as deliverySuccessCount, count(CAMPAIGN_ID) as campId FROM RPT_SUBMIT_DELIVERY_FACT WHERE CDR_DATE >=:startDate AND CDR_DATE <= :endDate AND ACCOUNT_TYPE IN(:accountType) AND CAMPAIGN_TYPE IN(:campaignType) GROUP BY CDR_DATE";
				} else {
					query = "SELECT sum(TARGET_COUNT) as targetCount, sum(BOUNS_CREDITED) as bounsCredited,sum(DELIVERY_SUCCESS_COUNT) as deliverySuccessCount, count(CAMPAIGN_ID) as campId FROM RPT_SUBMIT_DELIVERY_FACT WHERE CDR_DATE >DATE_FORMAT(:startDate,'%Y-%m-%d')-INTERVAL "+dashboardCardDTO.getDays()+" DAY AND CDR_DATE <=  DATE_FORMAT(:endDate,'%Y-%m-%d')-INTERVAL 0 DAY AND ACCOUNT_TYPE IN(:accountType) AND CAMPAIGN_TYPE IN(:campaignType) GROUP BY CDR_DATE";
				}
				SQLQuery sqlQuery = session.createSQLQuery(query);
				sqlQuery.setParameter("startDate", dashboardCardDTO.getStartDate());
				sqlQuery.setParameter("endDate", dashboardCardDTO.getEndDate());
				sqlQuery.setParameterList("accountType", dashboardCardDTO.getAccountTypeList());
				sqlQuery.setParameterList("campaignType", dashboardCardDTO.getCampaignTypeList());
				sqlQuery.addScalar("targetCount", IntegerType.INSTANCE); 
				sqlQuery.addScalar("bounsCredited", IntegerType.INSTANCE); 
				sqlQuery.addScalar("deliverySuccessCount", IntegerType.INSTANCE); 
				sqlQuery.addScalar("campId", IntegerType.INSTANCE); 
				List<Object[]> results = (List<Object[]>)sqlQuery.list();
				if(results!= null && results.size()>0) {
					for (Object[] aRow : results) {
						if(aRow != null && aRow[0]!=null && aRow[1]!=null && aRow[2]!=null && aRow[3]!=null) {
							dashboardCardDTO.setTargetCount((Integer)aRow[0]);
							dashboardCardDTO.setBounsCredited((Integer)aRow[1]);
							dashboardCardDTO.setDeliverySuccessCount((Integer)aRow[2]);
							dashboardCardDTO.setCampId((Integer)aRow[3]+"");
						}
					}
				}
				lis = new ArrayList<DashboardCardDTO>();
				lis.add(dashboardCardDTO);
				
			} else {
				query ="SELECT sum(TARGET_COUNT) as targetCount, sum(BOUNS_CREDITED) as bounsCredited,sum(DELIVERY_SUCCESS_COUNT) as deliverySuccessCount, count(CAMPAIGN_ID) as campId FROM RPT_SUBMIT_DELIVERY_FACT WHERE CDR_DATE =:currentDate AND H_HOUR <=:currentHour AND ACCOUNT_TYPE IN(:accountType) AND CAMPAIGN_TYPE IN(:campaignType) GROUP BY H_HOUR" ;
				SQLQuery sqlQuery = session.createSQLQuery(query);
				sqlQuery.setParameter("currentDate", dashboardCardDTO.getCdrDate());
				sqlQuery.setParameter("currentHour", dashboardCardDTO.getHHour());
				sqlQuery.setParameterList("accountType", dashboardCardDTO.getAccountTypeList());
				sqlQuery.setParameterList("campaignType", dashboardCardDTO.getCampaignTypeList());
				sqlQuery.addScalar("targetCount", IntegerType.INSTANCE); 
				sqlQuery.addScalar("bounsCredited", IntegerType.INSTANCE); 
				sqlQuery.addScalar("deliverySuccessCount", IntegerType.INSTANCE); 
				sqlQuery.addScalar("campId", IntegerType.INSTANCE); 
				List<Object[]> results = (List<Object[]>)sqlQuery.list();
				if(results!= null && results.size()>0) {
					for (Object[] aRow : results) {
						if(aRow != null && aRow[0]!=null && aRow[1]!=null && aRow[2]!=null && aRow[3]!=null) {
							dashboardCardDTO.setTargetCount((Integer)aRow[0]);
							dashboardCardDTO.setBounsCredited((Integer)aRow[1]);
							dashboardCardDTO.setDeliverySuccessCount((Integer)aRow[2]);
							dashboardCardDTO.setCampId((Integer)aRow[3]+"");
						}
					}
				}
				lis = new ArrayList<DashboardCardDTO>();
				lis.add(dashboardCardDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lis;
	}


	/* (non-Javadoc)
	 * @see com.sixdee.magik.services.dao.DashboardCardDAO#getDashboardGraphDetails(com.sixdee.magik.services.model.DashboardCardDTO)
	 */
	@Override
	public List<DashboardCardDTO> getDashboardGraphDetails(DashboardCardDTO dashboardCardDTO) {
		Session session = null;
		List<DashboardCardDTO> lis = null;
		String sql = null;
		try {
			session = sessionFactory.getCurrentSession();
			if(dashboardCardDTO.getFeature()!=null && dashboardCardDTO.getFeature().equalsIgnoreCase("offline")) {
				if(dashboardCardDTO.isCustomDate() && !dashboardCardDTO.isDefaultDash()) {
					sql = "SELECT CAMPAIGN_NAME as campName, sum(DELIVERY_SUCCESS_COUNT) as deliverySuccessCount FROM RPT_SUBMIT_DELIVERY_FACT WHERE CDR_DATE >=:startDate AND CDR_DATE <=:endDate AND ACCOUNT_TYPE IN(:accountType) AND CAMPAIGN_TYPE IN(:campaignType) GROUP BY CAMPAIGN_NAME ";
				} else {
					if(dashboardCardDTO.getLimit() == 0) {
					  sql = "SELECT CAMPAIGN_NAME as campName, sum(DELIVERY_SUCCESS_COUNT) as deliverySuccessCount FROM RPT_SUBMIT_DELIVERY_FACT WHERE CDR_DATE >DATE_FORMAT(:startDate,'%Y-%m-%d')-INTERVAL "+dashboardCardDTO.getDays()+" DAY AND CDR_DATE <=  DATE_FORMAT(:endDate,'%Y-%m-%d')-INTERVAL 0 DAY AND ACCOUNT_TYPE IN(:accountType) AND CAMPAIGN_TYPE IN(:campaignType) GROUP BY CAMPAIGN_NAME ";
					} else {
						sql = "SELECT CAMPAIGN_NAME as campName, sum(DELIVERY_SUCCESS_COUNT) as deliverySuccessCount FROM RPT_SUBMIT_DELIVERY_FACT WHERE CDR_DATE >DATE_FORMAT(:startDate,'%Y-%m-%d')-INTERVAL "+dashboardCardDTO.getDays()+" DAY AND CDR_DATE <=  DATE_FORMAT(:endDate,'%Y-%m-%d')-INTERVAL 0 DAY AND ACCOUNT_TYPE IN(:accountType) AND CAMPAIGN_TYPE IN(:campaignType) GROUP BY CAMPAIGN_NAME LIMIT "+dashboardCardDTO.getLimit()+"";
					}
				}
				SQLQuery sqlQuery = session.createSQLQuery(sql);
				sqlQuery.addScalar("campName", StringType.INSTANCE); 
				sqlQuery.addScalar("deliverySuccessCount", IntegerType.INSTANCE);
				sqlQuery.setParameter("startDate", dashboardCardDTO.getStartDate());
				sqlQuery.setParameter("endDate", dashboardCardDTO.getEndDate());
				sqlQuery.setParameterList("accountType", dashboardCardDTO.getAccountTypeList());
				sqlQuery.setParameterList("campaignType", dashboardCardDTO.getCampaignTypeList());
				List<Object[]> results = (List<Object[]>)sqlQuery.list();
				if(results!= null && results.size()>0) {
					DashboardCardDTO dsh = null;
					lis = new ArrayList<DashboardCardDTO>();
					for (Object[] aRow : results) {
						if(aRow != null && aRow[0]!=null && aRow[1]!=null ) {
							dsh = new DashboardCardDTO();
							dsh.setCampName((String)aRow[0]);
							dsh.setDeliverySuccessCount((Integer)aRow[1]);
							lis.add(dsh);
						}
					}
				}
			} else {
				String query =" from DashboardCardDTO d where cdrDate = '"+dashboardCardDTO.getCdrDate() +"' and hHour = "+dashboardCardDTO.getHHour()+"" ;
				lis = (List<DashboardCardDTO>) session.createQuery(query).list();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lis;
	}


	/* (non-Javadoc)
	 * @see com.sixdee.magik.services.dao.DashboardCardDAO#getDashboardGraphDetails1(com.sixdee.magik.services.model.DashboardCardDTO)
	 */
	@Override
	public List<DashboardCardDTO> getDashboardGraphDetails1(DashboardCardDTO dashboardCardDTO) {
		
		Session session = null;
		String query = null;
		List<DashboardCardDTO> lis = null;
		try {
			session = sessionFactory.getCurrentSession();
			if(dashboardCardDTO.getFeature()!=null && dashboardCardDTO.getFeature().equalsIgnoreCase("offline")) {
				if(dashboardCardDTO.isCustomDate() && !dashboardCardDTO.isDefaultDash()) {
				query = "SELECT CDR_DATE as cdrDate, sum(DELIVERY_SUCCESS_COUNT) as submitSuccessCount, sum(RESPONSE_COUNT) as responseCount FROM RPT_SUBMIT_DELIVERY_FACT WHERE CDR_DATE >=:startDate AND CDR_DATE <=:endDate AND ACCOUNT_TYPE IN(:accountType) AND CAMPAIGN_TYPE IN(:campaignType) GROUP BY CDR_DATE ORDER BY CDR_DATE DESC ";
			} else {
				if(dashboardCardDTO.getLimit() == 0) {
					query = "SELECT CDR_DATE as cdrDate, sum(DELIVERY_SUCCESS_COUNT) as deliverySuccessCount, sum(RESPONSE_COUNT) as responseCount FROM RPT_SUBMIT_DELIVERY_FACT WHERE CDR_DATE >DATE_FORMAT(:startDate,'%Y-%m-%d')-INTERVAL "+dashboardCardDTO.getDays()+" DAY AND CDR_DATE <=  DATE_FORMAT(:endDate,'%Y-%m-%d')-INTERVAL 0 DAY AND ACCOUNT_TYPE IN(:accountType) AND CAMPAIGN_TYPE IN(:campaignType) GROUP BY CDR_DATE ORDER BY CDR_DATE DESC ";
				} else {
					query = "SELECT CDR_DATE as cdrDate, sum(DELIVERY_SUCCESS_COUNT) as deliverySuccessCount, sum(RESPONSE_COUNT) as responseCount FROM RPT_SUBMIT_DELIVERY_FACT WHERE CDR_DATE >DATE_FORMAT(:startDate,'%Y-%m-%d')-INTERVAL "+dashboardCardDTO.getDays()+" DAY AND CDR_DATE <=  DATE_FORMAT(:endDate,'%Y-%m-%d')-INTERVAL 0 DAY AND ACCOUNT_TYPE IN(:accountType) AND CAMPAIGN_TYPE IN(:campaignType) GROUP BY CDR_DATE ORDER BY CDR_DATE DESC LIMIT "+dashboardCardDTO.getLimit()+"";
				}
			}
			SQLQuery sqlQuery = session.createSQLQuery(query);
			sqlQuery.addScalar("cdrDate", StringType.INSTANCE); 
			sqlQuery.addScalar("deliverySuccessCount", IntegerType.INSTANCE);
			sqlQuery.addScalar("responseCount", IntegerType.INSTANCE);
			sqlQuery.setParameter("startDate", dashboardCardDTO.getStartDate());
			sqlQuery.setParameter("endDate", dashboardCardDTO.getEndDate());
			sqlQuery.setParameterList("accountType", dashboardCardDTO.getAccountTypeList());
			sqlQuery.setParameterList("campaignType", dashboardCardDTO.getCampaignTypeList());
			List<Object[]> results = (List<Object[]>)sqlQuery.list();
			if(results!= null && results.size()>0) {
				DashboardCardDTO dsh = null;
				lis = new ArrayList<DashboardCardDTO>();
				for (Object[] aRow : results) {
					if(aRow != null && aRow[0]!=null && aRow[1]!=null && aRow[2]!=null ) {
						dsh = new DashboardCardDTO();
						dsh.setCdrDate((String)aRow[0]);
						dsh.setDeliverySuccessCount((Integer)aRow[1]);
						dsh.setResponseCount((Integer)aRow[2]);
						lis.add(dsh);
					}
				}
			}
			} else {
				query =" SELECT CAMPAIGN_TYPE as campaignType, sum(SUBMIT_SUCCESS_COUNT) as submitSuccessCount, sum(DELIVERY_SUCCESS_COUNT) as deliverySuccessCount, H_HOUR as hHour FROM RPT_SUBMIT_DELIVERY_FACT WHERE ACCOUNT_TYPE IN(:accountType) AND CAMPAIGN_TYPE IN(:campaignType) GROUP BY CAMPAIGN_TYPE" ;
				SQLQuery sqlQuery = session.createSQLQuery(query);
				sqlQuery.addScalar("campaignType", StringType.INSTANCE); 
				sqlQuery.addScalar("submitSuccessCount", IntegerType.INSTANCE);
				sqlQuery.addScalar("deliverySuccessCount", IntegerType.INSTANCE);
				sqlQuery.addScalar("hHour", IntegerType.INSTANCE);
/*				sqlQuery.setParameter("currentDate", dashboardCardDTO.getCdrDate());
				sqlQuery.setParameter("currentHour", dashboardCardDTO.getHHour());
	*/			sqlQuery.setParameterList("accountType", dashboardCardDTO.getAccountTypeList());
				sqlQuery.setParameterList("campaignType", dashboardCardDTO.getCampaignTypeList());
				List<Object[]> results = (List<Object[]>)sqlQuery.list();
				if(results!= null && results.size()>0) {
					DashboardCardDTO dashboardCard = null;
					lis = new ArrayList<DashboardCardDTO>();
					for (Object[] aRow : results) {
						if(aRow != null && aRow[0]!=null && aRow[1]!=null && aRow[2]!=null && aRow[3]!=null) {
							dashboardCard = new DashboardCardDTO();
							dashboardCard.setCampaignType((String)aRow[0]);
							dashboardCard.setSubmitSuccessCount((Integer)aRow[1]);
							dashboardCard.setDeliverySuccessCount((Integer)aRow[2]);
							dashboardCard.setHHour((Integer)aRow[3]);
							lis.add(dashboardCard);
						}
					}
				}
			}
			
			/*lis = new ArrayList<DashboardCardDTO>();
			
			DashboardCardDTO rech1 = new DashboardCardDTO();
			rech1.setHHour(1);
			rech1.setCampaignType("Recharge");
			rech1.setSubmitSuccessCount(1000);
			rech1.setDeliverySuccessCount(923);
			lis.add(rech1);
			
			DashboardCardDTO rech2 = new DashboardCardDTO();
			rech2.setHHour(2);
			rech2.setCampaignType("Recharge");
			rech2.setSubmitSuccessCount(1234);
			rech2.setDeliverySuccessCount(910);
			lis.add(rech2);
			
			DashboardCardDTO rech3 = new DashboardCardDTO();
			rech3.setHHour(3);
			rech3.setCampaignType("Recharge");
			rech3.setSubmitSuccessCount(1463);
			rech3.setDeliverySuccessCount(897);
			lis.add(rech3);*/
			
		}  catch (Exception e) {
			e.printStackTrace();
		} 
		return lis;
	}


	/* (non-Javadoc)
	 * @see com.sixdee.magik.services.dao.DashboardCardDAO#getDashboardGraphDetails2(com.sixdee.magik.services.model.DashboardCardDTO)
	 */
	@Override
	public List<DashboardCardDTO> getDashboardGraphDetails2(DashboardCardDTO dashboardCardDTO) {
		
		Session session = null;
		String query = null;
		List<DashboardCardDTO> lis = null;
		
		try {
			session = sessionFactory.getCurrentSession();
			if(dashboardCardDTO.getFeature()!=null && dashboardCardDTO.getFeature().equalsIgnoreCase("offline")) {
				if(dashboardCardDTO.isCustomDate() && !dashboardCardDTO.isDefaultDash()) {
				query = "SELECT CDR_DATE as cdrDate, sum(TARGET_GROUP_REVENUE) as targetGroupRevenue, sum(CONTROL_GROUP_REVENUE) as controlGroupRevenue FROM RPT_SUBMIT_DELIVERY_FACT WHERE CDR_DATE >=:startDate AND CDR_DATE <=:endDate AND ACCOUNT_TYPE IN(:accountType) AND CAMPAIGN_TYPE IN(:campaignType) GROUP BY CDR_DATE ORDER BY CDR_DATE DESC";
			} else {
				if(dashboardCardDTO.getLimit() == 0) {
				query = "SELECT CDR_DATE as cdrDate, sum(TARGET_GROUP_REVENUE) as targetGroupRevenue, sum(CONTROL_GROUP_REVENUE) as controlGroupRevenue FROM RPT_SUBMIT_DELIVERY_FACT WHERE CDR_DATE >DATE_FORMAT(:startDate,'%Y-%m-%d')-INTERVAL "+dashboardCardDTO.getDays()+" DAY AND CDR_DATE <=  DATE_FORMAT(:endDate,'%Y-%m-%d')-INTERVAL 0 DAY AND ACCOUNT_TYPE IN(:accountType) AND CAMPAIGN_TYPE IN(:campaignType) GROUP BY CDR_DATE ORDER BY CDR_DATE DESC";
				} else {
					query = "SELECT CDR_DATE as cdrDate, sum(TARGET_GROUP_REVENUE) as targetGroupRevenue, sum(CONTROL_GROUP_REVENUE) as controlGroupRevenue FROM RPT_SUBMIT_DELIVERY_FACT WHERE CDR_DATE >DATE_FORMAT(:startDate,'%Y-%m-%d')-INTERVAL "+dashboardCardDTO.getDays()+" DAY AND CDR_DATE <=  DATE_FORMAT(:endDate,'%Y-%m-%d')-INTERVAL 0 DAY AND ACCOUNT_TYPE IN(:accountType) AND CAMPAIGN_TYPE IN(:campaignType) GROUP BY CDR_DATE ORDER BY CDR_DATE DESC LIMIT "+dashboardCardDTO.getLimit()+"";
				}
			}
			SQLQuery sqlQuery = session.createSQLQuery(query);
			sqlQuery.addScalar("cdrDate", StringType.INSTANCE); 
			sqlQuery.addScalar("targetGroupRevenue", IntegerType.INSTANCE);
			sqlQuery.addScalar("controlGroupRevenue", IntegerType.INSTANCE);
			sqlQuery.setParameter("startDate", dashboardCardDTO.getStartDate());
			sqlQuery.setParameter("endDate", dashboardCardDTO.getEndDate());
			sqlQuery.setParameterList("accountType", dashboardCardDTO.getAccountTypeList());
			sqlQuery.setParameterList("campaignType", dashboardCardDTO.getCampaignTypeList());
			List<Object[]> results = (List<Object[]>)sqlQuery.list();
			if(results!= null && results.size()>0) {
				DashboardCardDTO dsh = null;
				lis = new ArrayList<DashboardCardDTO>();
				for (Object[] aRow : results) {
					if(aRow != null && aRow[0]!=null && aRow[1]!=null && aRow[2]!=null ) {
						dsh = new DashboardCardDTO();
						dsh.setCdrDate((String)aRow[0]);
						dsh.setTargetGroupRevenue((Integer)aRow[1]);
						dsh.setControlGroupRevenue((Integer)aRow[2]);
						lis.add(dsh);
					}
				}
			 }
			 
			} else {
			query =" SELECT sum(TARGET_COUNT) as targetCount, sum(RESPONSE_COUNT) as responseCount, H_HOUR as hHour FROM RPT_SUBMIT_DELIVERY_FACT WHERE ACCOUNT_TYPE IN(:accountType) AND CAMPAIGN_TYPE IN(:campaignType) GROUP BY H_HOUR" ;
			SQLQuery sqlQuery = session.createSQLQuery(query);
			sqlQuery.addScalar("targetCount", IntegerType.INSTANCE);
			sqlQuery.addScalar("responseCount", IntegerType.INSTANCE);
			sqlQuery.addScalar("hHour", IntegerType.INSTANCE); 
/*			sqlQuery.setParameter("currentDate", dashboardCardDTO.getCdrDate());
			sqlQuery.setParameter("currentHour", dashboardCardDTO.getHHour());
*/			sqlQuery.setParameterList("accountType", dashboardCardDTO.getAccountTypeList());
			sqlQuery.setParameterList("campaignType", dashboardCardDTO.getCampaignTypeList());
			List<Object[]> results = (List<Object[]>)sqlQuery.list();
			DashboardCardDTO dashboardCard = null;
			if(results!= null && results.size()>0) {
				lis = new ArrayList<DashboardCardDTO>();
				for (Object[] aRow : results) {
					if(aRow != null && aRow[0]!=null && aRow[1]!=null  && aRow[2]!=null) {
						dashboardCard = new DashboardCardDTO();
						dashboardCard.setTargetCount((Integer) aRow[0]);
						dashboardCard.setResponseCount((Integer) aRow[1]);
						dashboardCard.setHHour((Integer)aRow[2]);
						lis.add(dashboardCard);
					}
				}
			}
		}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return lis;
	}
	
	@Override
	public List<DashboardCardDTO> getDashboardGraphDetails3(DashboardCardDTO dashboardCardDTO) {
		
		Session session = null;
		String query = null;
		List<DashboardCardDTO> lis = null;
		
		try {
			session = sessionFactory.getCurrentSession();
			if(dashboardCardDTO.isCustomDate() && !dashboardCardDTO.isDefaultDash()) {
				query = "SELECT CDR_DATE as cdrDate, sum(TG_RESPOND) as tgRespond, sum(CG_RESPOND) as cgRespond FROM RPT_SUBMIT_DELIVERY_FACT WHERE CDR_DATE >=:startDate AND CDR_DATE <=:endDate AND ACCOUNT_TYPE IN(:accountType) AND CAMPAIGN_TYPE IN(:campaignType) GROUP BY CDR_DATE ORDER BY CDR_DATE DESC";
			} else {
				if(dashboardCardDTO.getLimit() == 0) {
				query = "SELECT CDR_DATE as cdrDate, sum(TG_RESPOND) as tgRespond, sum(CG_RESPOND) as cgRespond FROM RPT_SUBMIT_DELIVERY_FACT WHERE CDR_DATE >DATE_FORMAT(:startDate,'%Y-%m-%d')-INTERVAL "+dashboardCardDTO.getDays()+" DAY AND CDR_DATE <=  DATE_FORMAT(:endDate,'%Y-%m-%d')-INTERVAL 0 DAY AND ACCOUNT_TYPE IN(:accountType) AND CAMPAIGN_TYPE IN(:campaignType) GROUP BY CDR_DATE ORDER BY CDR_DATE DESC";
				} else {
					query = "SELECT CDR_DATE as cdrDate, sum(TG_RESPOND) as tgRespond, sum(CG_RESPOND) as cgRespond FROM RPT_SUBMIT_DELIVERY_FACT WHERE CDR_DATE >DATE_FORMAT(:startDate,'%Y-%m-%d')-INTERVAL "+dashboardCardDTO.getDays()+" DAY AND CDR_DATE <=  DATE_FORMAT(:endDate,'%Y-%m-%d')-INTERVAL 0 DAY AND ACCOUNT_TYPE IN(:accountType) AND CAMPAIGN_TYPE IN(:campaignType) GROUP BY CDR_DATE ORDER BY CDR_DATE DESC LIMIT "+dashboardCardDTO.getLimit()+"";
				}
			}
			SQLQuery sqlQuery = session.createSQLQuery(query);
			sqlQuery.addScalar("cdrDate", StringType.INSTANCE); 
			sqlQuery.addScalar("tgRespond", IntegerType.INSTANCE);
			sqlQuery.addScalar("cgRespond", IntegerType.INSTANCE);
			sqlQuery.setParameter("startDate", dashboardCardDTO.getStartDate());
			sqlQuery.setParameter("endDate", dashboardCardDTO.getEndDate());
			sqlQuery.setParameterList("accountType", dashboardCardDTO.getAccountTypeList());
			sqlQuery.setParameterList("campaignType", dashboardCardDTO.getCampaignTypeList());
			List<Object[]> results = (List<Object[]>)sqlQuery.list();
			if(results!= null && results.size()>0) {
				DashboardCardDTO dsh = null;
				lis = new ArrayList<DashboardCardDTO>();
				for (Object[] aRow : results) {
					if(aRow != null && aRow[0]!=null && aRow[1]!=null && aRow[2]!=null ) {
						dsh = new DashboardCardDTO();
						dsh.setCdrDate((String)aRow[0]);
						dsh.setTgRespond((Integer)aRow[1]);
						dsh.setCgRespond((Integer)aRow[2]);
						lis.add(dsh);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lis;
	}
	
	@Override
	public List<DashboardCardDTO> getDashboardGraphDetails4(DashboardCardDTO dashboardCardDTO) {
		
		Session session = null;
		String query = null;
		List<DashboardCardDTO> lis = null;
		
		try {
			session = sessionFactory.getCurrentSession();
			if(dashboardCardDTO.isCustomDate() && !dashboardCardDTO.isDefaultDash()) {
				query = "SELECT CDR_DATE as cdrDate, sum(TOTAL_COUNT) as totalCount, CHANNEL_ID as channelId FROM RPT_SUBMIT_DELIVERY_FACT WHERE CDR_DATE >=:startDate AND CDR_DATE <=:endDate AND ACCOUNT_TYPE IN(:accountType) AND CAMPAIGN_TYPE IN(:campaignType) GROUP BY CHANNEL_ID";
			} else {
				query = "SELECT CDR_DATE as cdrDate, sum(TOTAL_COUNT) as totalCount, CHANNEL_ID as channelId FROM RPT_SUBMIT_DELIVERY_FACT WHERE CDR_DATE >DATE_FORMAT(:startDate,'%Y-%m-%d')-INTERVAL "+dashboardCardDTO.getDays()+" DAY AND CDR_DATE <=  DATE_FORMAT(:endDate,'%Y-%m-%d')-INTERVAL 0 DAY AND ACCOUNT_TYPE IN(:accountType) AND CAMPAIGN_TYPE IN(:campaignType) GROUP BY CHANNEL_ID";
			}
			SQLQuery sqlQuery = session.createSQLQuery(query);
			sqlQuery.addScalar("cdrDate", StringType.INSTANCE); 
			sqlQuery.addScalar("totalCount", IntegerType.INSTANCE);
			sqlQuery.addScalar("channelId", StringType.INSTANCE);
			sqlQuery.setParameter("startDate", dashboardCardDTO.getStartDate());
			sqlQuery.setParameter("endDate", dashboardCardDTO.getEndDate());
			sqlQuery.setParameterList("accountType", dashboardCardDTO.getAccountTypeList());
			sqlQuery.setParameterList("campaignType", dashboardCardDTO.getCampaignTypeList());
			List<Object[]> results = (List<Object[]>)sqlQuery.list();
			if(results!= null && results.size()>0) {
				DashboardCardDTO dsh = null;
				lis = new ArrayList<DashboardCardDTO>();
				for (Object[] aRow : results) {
					if(aRow != null && aRow[0]!=null && aRow[1]!=null && aRow[2]!=null ) {
						dsh = new DashboardCardDTO();
						dsh.setCdrDate((String)aRow[0]);
						dsh.setTotalCount((Integer)aRow[1]);
						dsh.setChannelId((String)aRow[2]);
						lis.add(dsh);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return lis;
	}
	
	@Override
	public List<DashboardCardDTO> getDashboardGraphDetails5(DashboardCardDTO dashboardCardDTO) {
		Session session = null;
		String query = null;
		List<DashboardCardDTO> lis = null;
		try {
			session = sessionFactory.getCurrentSession();
			if(dashboardCardDTO.isCustomDate() && !dashboardCardDTO.isDefaultDash()) {
				query = "SELECT CDR_DATE as cdrDate, PRODUCT_NAME as productName, SUM(DELIVERY_SUCCESS_COUNT) as productCount FROM RPT_SUBMIT_DELIVERY_FACT WHERE CDR_DATE >=:startDate AND CDR_DATE <=:endDate GROUP BY PRODUCT_NAME AND ACCOUNT_TYPE IN(:accountType) AND CAMPAIGN_TYPE IN(:campaignType) GROUP BY PRODUCT_NAME ORDER BY DELIVERY_SUCCESS_COUNT DESC LIMIT 5";
			} else {
				query = "SELECT CDR_DATE as cdrDate, PRODUCT_NAME as productName, SUM(DELIVERY_SUCCESS_COUNT) as productCount FROM RPT_SUBMIT_DELIVERY_FACT WHERE CDR_DATE >DATE_FORMAT(:startDate,'%Y-%m-%d')-INTERVAL "+dashboardCardDTO.getDays()+" DAY AND CDR_DATE <=  DATE_FORMAT(:endDate,'%Y-%m-%d')-INTERVAL 0 DAY AND ACCOUNT_TYPE IN(:accountType) AND CAMPAIGN_TYPE IN(:campaignType) GROUP BY PRODUCT_NAME ORDER BY DELIVERY_SUCCESS_COUNT DESC LIMIT 5";
			}
			SQLQuery sqlQuery = session.createSQLQuery(query);
			sqlQuery.addScalar("cdrDate", StringType.INSTANCE); 
			sqlQuery.addScalar("productName", StringType.INSTANCE);
			sqlQuery.addScalar("productCount", IntegerType.INSTANCE);
			sqlQuery.setParameter("startDate", dashboardCardDTO.getStartDate());
			sqlQuery.setParameter("endDate", dashboardCardDTO.getEndDate());
			sqlQuery.setParameterList("accountType", dashboardCardDTO.getAccountTypeList());
			sqlQuery.setParameterList("campaignType", dashboardCardDTO.getCampaignTypeList());
			List<Object[]> results = (List<Object[]>)sqlQuery.list();
			if(results!= null && results.size()>0) {
				DashboardCardDTO dsh = null;
				lis = new ArrayList<DashboardCardDTO>();
				for (Object[] aRow : results) {
					if(aRow != null && aRow[0]!=null && aRow[1]!=null && aRow[2]!=null ) {
						dsh = new DashboardCardDTO();
						dsh.setCdrDate((String)aRow[0]);
						dsh.setProductName((String)aRow[1]);
						dsh.setTotalCount((Integer)aRow[2]);
						lis.add(dsh);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return lis;
	}


	/* (non-Javadoc)
	 * @see com.sixdee.magik.services.dao.DashboardCardDAO#getDashboardGraphDetails6(com.sixdee.magik.services.model.DashboardCardDTO)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DashboardCardDTO> getDashboardGraphDetails6(DashboardCardDTO dashboardCardDTO) {
		Session session = null;
		String query = null;
		List<DashboardCardDTO> lis = null;
		try {
			session = sessionFactory.getCurrentSession();
			if(dashboardCardDTO.isCustomDate() && !dashboardCardDTO.isDefaultDash()) {
				query = "SELECT CAMPAIGN_NAME as campName, SUM(RESPONSE_COUNT) as responseCount FROM RPT_SUBMIT_DELIVERY_FACT WHERE CDR_DATE >=:startDate AND CDR_DATE <=:endDate GROUP BY PRODUCT_NAME AND ACCOUNT_TYPE IN(:accountType) AND CAMPAIGN_TYPE IN(:campaignType) GROUP BY CAMPAIGN_NAME ORDER BY RESPONSE_COUNT ASC LIMIT 5";
			} else {
				query = "SELECT CAMPAIGN_NAME as campName, SUM(RESPONSE_COUNT) as responseCount FROM RPT_SUBMIT_DELIVERY_FACT WHERE CDR_DATE >DATE_FORMAT(:startDate,'%Y-%m-%d')-INTERVAL "+dashboardCardDTO.getDays()+" DAY AND CDR_DATE <=  DATE_FORMAT(:endDate,'%Y-%m-%d')-INTERVAL 0 DAY AND ACCOUNT_TYPE IN(:accountType) AND CAMPAIGN_TYPE IN(:campaignType) GROUP BY CAMPAIGN_NAME ORDER BY RESPONSE_COUNT ASC LIMIT 5";
			}
			SQLQuery sqlQuery = session.createSQLQuery(query);
			sqlQuery.addScalar("campName", StringType.INSTANCE); 
			sqlQuery.addScalar("responseCount", IntegerType.INSTANCE);
			sqlQuery.setParameter("startDate", dashboardCardDTO.getStartDate());
			sqlQuery.setParameter("endDate", dashboardCardDTO.getEndDate());
			sqlQuery.setParameterList("accountType", dashboardCardDTO.getAccountTypeList());
			sqlQuery.setParameterList("campaignType", dashboardCardDTO.getCampaignTypeList());
			List<Object[]> results = (List<Object[]>)sqlQuery.list();
			if(results!= null && results.size()>0) {
				DashboardCardDTO dsh = null;
				lis = new ArrayList<DashboardCardDTO>();
				for (Object[] aRow : results) {
					if(aRow != null && aRow[0]!=null && aRow[1]!=null) {
						dsh = new DashboardCardDTO();
						dsh.setCampName((String)aRow[0]);
						dsh.setResponseCount((Integer)aRow[1]);
						lis.add(dsh);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return lis;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<DashboardCardDTO> getDashboardGraphDetails7(DashboardCardDTO dashboardCardDTO) {
		Session session = null;
		String query = null;
		List<DashboardCardDTO> lis = null;
		try {
			session = sessionFactory.getCurrentSession();
			if(dashboardCardDTO.isCustomDate() && !dashboardCardDTO.isDefaultDash()) {
				query = "SELECT CIRCLE_NAME as circleName, SUM(RESPONSE_COUNT) as responseCount,SUM(DELIVERY_SUCCESS_COUNT) as deliverySuccessCount FROM RPT_SUBMIT_DELIVERY_FACT WHERE CDR_DATE >=:startDate AND CDR_DATE <=:endDate GROUP BY PRODUCT_NAME AND ACCOUNT_TYPE IN(:accountType) AND CAMPAIGN_TYPE IN(:campaignType) GROUP BY CIRCLE_NAME";
			} else {
				if(dashboardCardDTO.getLimit() == 0) {
				query = "SELECT CIRCLE_NAME as circleName, SUM(RESPONSE_COUNT) as responseCount, SUM(DELIVERY_SUCCESS_COUNT) as deliverySuccessCount FROM RPT_SUBMIT_DELIVERY_FACT WHERE CDR_DATE >DATE_FORMAT(:startDate,'%Y-%m-%d')-INTERVAL "+dashboardCardDTO.getDays()+" DAY AND CDR_DATE <=  DATE_FORMAT(:endDate,'%Y-%m-%d')-INTERVAL 0 DAY AND ACCOUNT_TYPE IN(:accountType) AND CAMPAIGN_TYPE IN(:campaignType) GROUP BY CIRCLE_NAME";
				} else {
					query = "SELECT CIRCLE_NAME as circleName, SUM(RESPONSE_COUNT) as responseCount, SUM(DELIVERY_SUCCESS_COUNT) as deliverySuccessCount FROM RPT_SUBMIT_DELIVERY_FACT WHERE CDR_DATE >DATE_FORMAT(:startDate,'%Y-%m-%d')-INTERVAL "+dashboardCardDTO.getDays()+" DAY AND CDR_DATE <=  DATE_FORMAT(:endDate,'%Y-%m-%d')-INTERVAL 0 DAY AND ACCOUNT_TYPE IN(:accountType) AND CAMPAIGN_TYPE IN(:campaignType) GROUP BY CIRCLE_NAME LIMIT "+dashboardCardDTO.getLimit()+"";
				}
			}
			SQLQuery sqlQuery = session.createSQLQuery(query);
			sqlQuery.addScalar("circleName", StringType.INSTANCE); 
			sqlQuery.addScalar("responseCount", IntegerType.INSTANCE);
			sqlQuery.addScalar("deliverySuccessCount", IntegerType.INSTANCE);
			sqlQuery.setParameter("startDate", dashboardCardDTO.getStartDate());
			sqlQuery.setParameter("endDate", dashboardCardDTO.getEndDate());
			sqlQuery.setParameterList("accountType", dashboardCardDTO.getAccountTypeList());
			sqlQuery.setParameterList("campaignType", dashboardCardDTO.getCampaignTypeList());
			List<Object[]> results = (List<Object[]>)sqlQuery.list();
			if(results!= null && results.size()>0) {
				DashboardCardDTO dsh = null;
				lis = new ArrayList<DashboardCardDTO>();
				for (Object[] aRow : results) {
					if(aRow != null && aRow[0]!=null && aRow[1]!=null && aRow[2]!=null) {
						dsh = new DashboardCardDTO();
						dsh.setCircleName((String)aRow[0]);
						dsh.setResponseCount((Integer)aRow[1]);
						dsh.setDeliverySuccessCount((Integer)aRow[2]);
						lis.add(dsh);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return lis;
	}
}
