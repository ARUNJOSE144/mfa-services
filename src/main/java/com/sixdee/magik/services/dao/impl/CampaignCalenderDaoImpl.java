package com.sixdee.magik.services.dao.impl;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.sixdee.magik.services.dao.CampaignCalenderDao;
import com.sixdee.magik.services.model.CalenderCampaignTypesDataTO;
import com.sixdee.magik.services.model.CalenderDataTO;
import com.sixdee.magik.services.model.CampaignMasterTO;
import com.sixdee.magik.services.model.CampaignTypeTO;

/**
 * @author Ramesh Babu Cheerla
 * @Date : April, 2019
 *
 */

@Repository
public class CampaignCalenderDaoImpl implements CampaignCalenderDao {

	static final Logger logger = Logger.getLogger(CampaignCalenderDaoImpl.class);
	
	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;
	private String query = null;
	Map<String, Boolean> checkingMap = new HashMap<String, Boolean>();// format => key : "campaignId_day" ,value:true

	@Override
	public List<CalenderDataTO> getCalenderData(String startDate, String endDate, String userId) {
		
		System.out.println("User Id ---> : "+userId+" | Month : Start Date ---> : "+startDate+" | End Date ---> : "+endDate);
		
		Session session = sessionFactory.getCurrentSession();
		List<CalenderDataTO> list = new ArrayList<CalenderDataTO>();
		CalenderDataTO to = null;		
			
		query = "SELECT CDM.ID, CDM.CAMPAIGN_NAME, CDM.CAMPAIGN_DESCRIPTION, CDM.START_DATE, CDM.END_DATE, CT.TEXT_COLOR, CT.BACKGROUND_COLOR, CT.BORDER_COLOR " + 
				"FROM CAMPAIGN_DEFINTION_MASTER CDM, CAMPAIGN_TYPE CT WHERE CDM.CAMPAIGN_TYPE = CT.ID AND CDM.START_DATE <= '"+endDate+"' AND CDM.END_DATE >= '"+startDate+"' ORDER BY CDM.START_DATE ASC";
		
		List<Object[]> rs = (List<Object[]>) session.createSQLQuery(query).list();
			
		for (Object[] obj : rs) {
			to = new CalenderDataTO();
			to.setId("0");
			to.setCampId(obj[0] + "");
			to.setTitle(obj[1] + "");
			to.setDescription(obj[2] + "");
			to.setStart(obj[3] + "");
			to.setEnd(obj[4] + "");
			to.setTextColor(obj[5] + "");
			to.setBackgroundColor(obj[6] + "");
			to.setBorderColor(obj[7] + "");

			list.add(to); 
		}

		return getDetailJson(list, startDate, endDate);
	}

	
	/*
	 * Campaign Type Info
	 */
	public List<CampaignTypeTO> getCampaignTypeInfo() {
			
		Session session = sessionFactory.getCurrentSession();

		@SuppressWarnings({ "unchecked", "deprecation" })
		List<CampaignTypeTO> list = (List<CampaignTypeTO>) session.createCriteria(CampaignTypeTO.class).list();
	
		return list;
	}
	
	/*
	 * Calendar data json format
	 */
	private List<CalenderDataTO> getDetailJson(List<CalenderDataTO> list, String startDate, String endDate) {
		
		List<CalenderDataTO> jsonList = new ArrayList<CalenderDataTO>();
		CalenderDataTO to = null;
		int i = 0;
		String monthStartDate = startDate;
		String monthEndDate = endDate;
		String cmpStartDate = "";
		String cmpEndDate = "";
		String date = "";
		
		for (CalenderDataTO resTO : list) {
						
			cmpStartDate = resTO.getStart();
			cmpEndDate = resTO.getEnd();
			
			/*System.out.println("----------------------------------------------------------------------------");
			System.out.println("Campaign : Start Date ---> : "+cmpStartDate+" | End Date ---> : "+cmpEndDate);
			System.out.println("Current Month : Start Date ---> : "+monthStartDate+" | End Date ---> : "+monthEndDate);*/
			
			if(getDate(cmpStartDate).after(getDate(monthStartDate)) && getDate(cmpStartDate).before(getDate(monthEndDate))) {
				date = cmpStartDate;
				//System.out.println(" 1  >>>>>>>>>>>>> : "+date);
			} else {
				date = monthStartDate;
				//System.out.println(" 2  >>>>>>>>>>>>> : "+date);
			}
			
			while (!(getDate(date).after(getDate(cmpEndDate)) || getDate(date).after(getDate(monthEndDate)))) {
				
				i++;
				
				/*System.out.println("Index --------------------------------------->  : "+i);
				System.out.println("Start and End Date ==================================>  : "+date);*/
				
				to = new CalenderDataTO();
				to.setId(i+"");
				to.setCampId(resTO.getCampId());
				to.setTitle(resTO.getTitle());
				to.setDescription(resTO.getDescription());
				to.setStart(date);
				to.setEnd(date);
				to.setCampSatrtDate(resTO.getStart());
				to.setCampEndDate(resTO.getEnd());
				to.setTextColor(resTO.getTextColor());
				to.setBackgroundColor(resTO.getBackgroundColor());
				to.setBorderColor(resTO.getBorderColor());
				
				jsonList.add(to);
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Calendar c = Calendar.getInstance();
				
				try {
					c.setTime(sdf.parse(date));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
				c.add(Calendar.DATE, 1); 
				
				date = sdf.format(c.getTime());
			}			
		}
		
		return jsonList;
		
	}
	
	/*
	 * Date conversion
	 * 
	 */
	
	private Date getDate(String date) {
		
		Date resDate = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			resDate = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return resDate;
	}
	
	@Override
	public List<CampaignMasterTO> getCampaignInfo(String date) {
		List<CampaignMasterTO> finalList = new ArrayList<CampaignMasterTO>();
		LinkedHashMap<String, String> campaignTypeMap = getCampaignTypesMap();

		String pattern = "dd MMM yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

		String datePattern = "dd/MM/yyyy";
		SimpleDateFormat uidateformat = new SimpleDateFormat(datePattern);

		String hql = "";
		int ruleCount = 0;
		Long count;

		Session session = sessionFactory.getCurrentSession();
		query = "FROM CampaignMasterTO  where startDate >= '" + date + " 00:00:00'";

		List<CampaignMasterTO> list = (List<CampaignMasterTO>) session.createQuery(query).list();

		for (CampaignMasterTO to : list) {
			if (to.getStatus().equalsIgnoreCase("1"))
				to.setStatusDesc("Active");
			if (to.getStatus().equalsIgnoreCase("0"))
				to.setStatusDesc("InActive");

			hql = "select count(*) from ScheduleTO where campaignId=" + to.getId();
			count = (Long) session.createQuery(hql).uniqueResult();

			if (count != null)
				ruleCount = count.intValue();
			to.setNoOfActiveRules(ruleCount);

			to.setCreateDateUI(simpleDateFormat.format(to.getCreateDate()));
			to.setStartDateUI(uidateformat.format(to.getStartDate()));
			to.setEndDateUI(uidateformat.format(to.getEndDate()));
			/* to.setExpiryDateUI(uidateformat.format(to.getExpiryDate())); */
			to.setCampaignType(to.getCampaignType());
			to.setCampaignTypeUI(campaignTypeMap.get(to.getCampaignType()));

			to.setCreateDateForOverview(uidateformat.format(to.getCreateDate()));
			to.setEndDateForOverview(uidateformat.format(to.getEndDate()));
			to.setStartDateForOverview(uidateformat.format(to.getStartDate()));
			/* to.setExpiryDateForOverview(uidateformat.format(to.getExpiryDate())); */

			finalList.add(to);
		}

		return list;
	}

	@Override
	public List<CalenderCampaignTypesDataTO> getCampaignTypesData(String date) {
		List<CalenderCampaignTypesDataTO> dto = new ArrayList<CalenderCampaignTypesDataTO>();
		Session session = sessionFactory.getCurrentSession();
		/*Long count;*/
		int typeCount = 0;
	
			String sql = "SELECT b.ID,b.NAME,COUNT(*) FROM CAMPAIGN_DEFINTION_MASTER a,CAMPAIGN_TYPE b "
				+ " WHERE a.CAMPAIGN_TYPE =b.ID and"
				+" a.START_DATE <= '" + date + " 12:59:59'" 
				/*+ " and a.EXPIRY_DATE >='" +  date + " 00:00:00'"*/
				+ " GROUP BY a.CAMPAIGN_TYPE";
				

		List<Object[]> rs = (List<Object[]>) session.createSQLQuery(sql).list();

		for (Object[] obj : rs) {

			CalenderCampaignTypesDataTO to = new CalenderCampaignTypesDataTO();
			to.setId(Integer.parseInt(obj[0] + ""));
			to.setCamaignType(obj[1] + "");
			to.setAll(Integer.parseInt(obj[2] + ""));

			// prepaid Count
			sql = "SELECT COUNT(*) FROM CAMPAIGN_DEFINTION_MASTER a" + " WHERE a.CAMPAIGN_TYPE= " + to.getId()
					+ " AND SEGMENT_TYPE=1";
			BigInteger count = (BigInteger) session.createSQLQuery(sql).uniqueResult();
			if (count != null)
				to.setPrepaid(count.intValue());

			// postpaid Count
			sql = "SELECT COUNT(*) FROM CAMPAIGN_DEFINTION_MASTER a" + " WHERE a.CAMPAIGN_TYPE= " + to.getId()
					+ " AND SEGMENT_TYPE=2";
			count = (BigInteger ) session.createSQLQuery(sql).uniqueResult();
			if (count != null)
				to.setPostPaid(count.intValue());    

			dto.add(to);

		}

		return dto;
	}

	@Override
	public List<CalenderCampaignTypesDataTO> getAllCampaign() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String viewPromotion() {
		// TODO Auto-generated method stub
		return null;
	}

	public LinkedHashMap<String, String> getCampaignTypesMap() {
		LinkedHashMap<String, String> campaignTypeMap = new LinkedHashMap<String, String>();
		Session session = sessionFactory.getCurrentSession();

		@SuppressWarnings({ "unchecked", "deprecation" })
		List<CampaignTypeTO> list = (List<CampaignTypeTO>) session.createCriteria(CampaignTypeTO.class).list();
		for (CampaignTypeTO to : list) {
			campaignTypeMap.put(to.getId() + "", to.getName());
		}

		return campaignTypeMap;
	}

	public CalenderDataTO getCalenderTo(String date, int day, int campaignId) {
		String dayStr = day + "";
		if (day < 10) // checking because of the calender did't render 9.so should give 09 instead
			dayStr = "0" + day;
		CalenderDataTO to = new CalenderDataTO();
		to.setStart(date + "-" + dayStr);
		checkingMap.put(campaignId + "_" + day, true);
		return to;
	}
}
