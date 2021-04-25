package com.sixdee.magik.services.dao.impl;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sixdee.magik.services.dao.CampaignDAO;
import com.sixdee.magik.services.model.CampaignMasterTO;
import com.sixdee.magik.services.model.CampaignSegmentTypeTO;
import com.sixdee.magik.services.model.CampaignTypeTO;
import com.sixdee.magik.services.model.FacebookCampaignMasterDTO;
import com.sixdee.magik.services.model.GoogleCampaignMasterDTO;
import com.sixdee.magik.services.model.RuleBuilderTO;
import com.sixdee.magik.services.model.RuleTO;
import com.sixdee.magik.services.model.ScheduleTO;
import com.sixdee.magik.services.model.TaskProfileTO;
import com.sixdee.magik.services.util.DateFormat;

/**
 * @author Rajesh
 * @Date : September, 2018
 *
 */

@Repository
public class CampaignDAOImpl implements CampaignDAO {

	@Autowired
	private Environment env;

	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;

	static final Logger logger = Logger.getLogger(CampaignDAOImpl.class);

	public LinkedHashMap<Integer, String> campaignStatusMap = new LinkedHashMap<Integer, String>();

	DateFormat dateFormat = new DateFormat();

	@Override
	public List<CampaignTypeTO> getCampaignTypes() {
		Session session = sessionFactory.getCurrentSession();

		@SuppressWarnings({ "unchecked", "deprecation" })
		List<CampaignTypeTO> list = (List<CampaignTypeTO>) session.createCriteria(CampaignTypeTO.class).list();

		return list;
	}

	@Override
	public CampaignMasterTO createCampaign(CampaignMasterTO masterTO) {
		// TODO Auto-generated method stub

		Session session = sessionFactory.getCurrentSession();

		System.out.println("Campaign To while Creating /Updating------------>" + masterTO.toString());

		if (masterTO.getId() == 0)
			masterTO.setCreateDate(new Date());
		if (masterTO.getIsTemplate() == 1)
			masterTO.setDefaultTemplate(0);

		session.saveOrUpdate(masterTO);

	//	 CampaignMasterTO status = createTaskProfile(masterTO);

		if (masterTO.getCampaignType() == 9 || masterTO.getCampaignType() == 11) { // Facebook

			FacebookCampaignMasterDTO fbMaster = new FacebookCampaignMasterDTO();

			if (masterTO.getFbId() != 0) {
				fbMaster.setId(masterTO.getFbId());
			}

			fbMaster.setBid(masterTO.getBid());
			fbMaster.setCampaignMediaFormat(masterTO.getCampaignMediaFormat());
			fbMaster.setDailyBudget(masterTO.getDailyBudget());
			fbMaster.setFacebookPage(masterTO.getFacebookPage());
			fbMaster.setFacebookCampaignMaster(masterTO);
			fbMaster.setMediaName(masterTO.getMediaName());
			fbMaster.setMediaPath(masterTO.getMediaPath());
			fbMaster.setTargetingId(masterTO.getCampaignSegmentType());
			fbMaster.setTitleDescription(masterTO.getTitleDescription());
			fbMaster.setTitleName(masterTO.getTitleName());
			fbMaster.setTotalBudget(masterTO.getTotalBudget());
			fbMaster.setIsCustomAudience(masterTO.getIsCustomAudience());
			fbMaster.setCustomAudienceFile(masterTO.getCustomAudienceFile());
			fbMaster.setFbCategroy(masterTO.getFbCategroy());
			fbMaster.setAudienceAttachType(masterTO.getAudienceAttachType());
			fbMaster.setCampObjective(masterTO.getCampObjective());
			fbMaster.setPostId(masterTO.getPostId());
			fbMaster.setLifeTimeBudget(masterTO.getLifeTimeBudget());
			fbMaster.setUrl(masterTO.getUrl());

			session.saveOrUpdate(fbMaster);

		}

		if (masterTO.getCampaignType() == 10) { // Google

			GoogleCampaignMasterDTO googleMaster = new GoogleCampaignMasterDTO();

			if (masterTO.getGoogleId() != 0) {
				googleMaster.setId(masterTO.getGoogleId());
			}

			googleMaster.setAdId(masterTO.getAdId());
			googleMaster.setBudgetAmount(masterTO.getTotalBudget());
			googleMaster.setBudgetName(masterTO.getBudgetName());
			googleMaster.setChannelType(masterTO.getGoogleChannelType());
			googleMaster.setGoogleCampaignMaster(masterTO);
			googleMaster.setTargetingId(masterTO.getCampaignSegmentType());

			session.saveOrUpdate(googleMaster);

		}

		return masterTO;
	}

	@Override
	public List<CampaignMasterTO> getCampaignsList(int userId) {
		// TODO Auto-generated method stub
		System.out.println("getCampaignsList  " + userId);
		String pattern = "dd MMM yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

		String datePattern = "dd/MM/yyyy";
		SimpleDateFormat uidateformat = new SimpleDateFormat(datePattern);

		String hql = "";
		int ruleCount = 0;
		Long count;

		LinkedHashMap<String, String> campaignTypeMap = getCampaignTypesMap();
		LinkedHashMap<String, String> campaignSegmentTypeMap = getCampaignSegmentTypesMap();
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(CampaignMasterTO.class);
		if (userId != 1) {
			criteria.add(Restrictions.eq("userId", userId));
		}
		// criteria.add(Restrictions.eq("status", "1"));
		criteria.addOrder(Order.desc("id"));
		CampaignMasterTO mainTO = null;
		List<CampaignMasterTO> mainList = new ArrayList();
		List<CampaignMasterTO> list = criteria.list();
		for (CampaignMasterTO to : list) {
			mainTO = new CampaignMasterTO();
			mainTO.setId(to.getId());
			mainTO.setCampaignName(to.getCampaignName());
			mainTO.setCampaignDesc(to.getCampaignDesc());
			mainTO.setStatus(to.getStatus());
			mainTO.setCreateDate(to.getCreateDate());
			mainTO.setStartDate(to.getStartDate());
			mainTO.setEndDate(to.getEndDate());
			mainTO.setCampaignSegmentType(to.getCampaignSegmentType());
			mainTO.setCampaignSegmentTypeUI(campaignSegmentTypeMap.get(to.getCampaignSegmentType() + ""));
			mainTO.setTargetCount(to.getTargetCount());
			mainTO.setPlayPauseStatus(to.getPlayPauseStatus());
			mainTO.setIsTemplate(to.getIsTemplate());
			mainTO.setGeoFencingId(to.getGeoFencingId());
			mainTO.setUserId(to.getUserId());

			// mainTO.setExpiryDate(to.getExpiryDate());
			/*
			 * if (to.getStatus().equalsIgnoreCase("1")) mainTO.setStatusDesc("Active"); if
			 * (to.getStatus().equalsIgnoreCase("0")) mainTO.setStatusDesc("InActive");
			 */
			mainTO.setStatusDesc(getCampaignStatusMap().get(to.getStatus()));
			hql = "select count(*) from RuleBuilderTO where campaignId=" + to.getId();
			count = (Long) session.createQuery(hql).uniqueResult();

			if (count != null)
				ruleCount = count.intValue();
			// to.setNoOfActiveRules(100);
			mainTO.setNoOfInActiveRules(ruleCount);

			hql = "select count(*) from ScheduleTO where campaignId=" + to.getId();
			count = (Long) session.createQuery(hql).uniqueResult();

			if (count != null)
				ruleCount = count.intValue();
			// to.setNoOfActiveRules(100);
			mainTO.setNoOfActiveRules(ruleCount);

			mainTO.setCreateDateUI(simpleDateFormat.format(to.getCreateDate()));
			mainTO.setStartDateUI(uidateformat.format(to.getStartDate()));
			mainTO.setEndDateUI(uidateformat.format(to.getEndDate()));
			// mainTO.setExpiryDateUI(uidateformat.format(to.getExpiryDate()));
			mainTO.setCampaignType(to.getCampaignType());
			mainTO.setCampaignTypeUI(campaignTypeMap.get(to.getCampaignType() + ""));

			mainTO.setCreateDateForOverview(uidateformat.format(to.getCreateDate()));
			mainTO.setEndDateForOverview(uidateformat.format(to.getEndDate()));
			mainTO.setStartDateForOverview(uidateformat.format(to.getStartDate()));

			mainTO.setTotalNoOfSegments((mainTO.getNoOfActiveRules() + mainTO.getNoOfInActiveRules()));
			// mainTO.setExpiryDateForOverview(uidateformat.format(to.getExpiryDate()));

			List<FacebookCampaignMasterDTO> fbList = to.getFacebookCampaignDetails();
			if (fbList != null && fbList.size() > 0) {
				for (FacebookCampaignMasterDTO fbDto : fbList) {
					mainTO.setBid(fbDto.getBid());
					mainTO.setFbId(fbDto.getId());
					mainTO.setFacebookPage(fbDto.getFacebookPage());
					mainTO.setCampaignMediaFormat(fbDto.getCampaignMediaFormat());
					mainTO.setTitleName(fbDto.getTitleName());
					mainTO.setTitleDescription(fbDto.getTitleDescription());
					mainTO.setMediaName(fbDto.getMediaName());
					mainTO.setMediaPath(fbDto.getMediaPath());
					mainTO.setDailyBudget(fbDto.getDailyBudget());
					mainTO.setTotalBudget(fbDto.getTotalBudget());
					mainTO.setFbCategroy(fbDto.getFbCategroy());
					mainTO.setIsCustomAudience(fbDto.getIsCustomAudience());
					mainTO.setCustomAudienceFile(fbDto.getCustomAudienceFile());
					mainTO.setAudienceAttachType(fbDto.getAudienceAttachType());
					mainTO.setCampObjective(fbDto.getCampObjective());
					mainTO.setPostId(fbDto.getPostId());
					mainTO.setLifeTimeBudget(fbDto.getLifeTimeBudget());
					mainTO.setUrl(fbDto.getUrl());

				}
			}

			List<GoogleCampaignMasterDTO> googleList = to.getGoogleCampaignDetails();

			if (googleList != null && googleList.size() > 0) {
				for (GoogleCampaignMasterDTO googleMaster : googleList) {
					mainTO.setTotalBudget(googleMaster.getBudgetAmount());
					mainTO.setBudgetName(googleMaster.getBudgetName());
					mainTO.setAdId(googleMaster.getAdId());
					mainTO.setGoogleChannelType(googleMaster.getChannelType());
					mainTO.setGoogleId(googleMaster.getId());

				}
			}

			mainList.add(mainTO);

		}

		return mainList;
	}

	@Override
	public String deleteCampaign(int campaignId) {
		// TODO Auto-generated method stub
		String status = "1";
		Session session = sessionFactory.getCurrentSession();
		String hql = "delete from CampaignMasterTO where id=" + campaignId;
		Query query = session.createQuery(hql);
		int result = query.executeUpdate();
		if (result > 0) {
			System.out.println("Campaign with Id " + campaignId + " deleted successfully");
			status = "0";
		}
		return status;

	}

	@Override
	public String copyCampaign(int campaignId) {
		// TODO Auto-generated method stub

		String newCampaignId = "0";
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(CampaignMasterTO.class);
		criteria.add(Restrictions.eq("id", campaignId));
		CampaignMasterTO masterTO = (CampaignMasterTO) criteria.uniqueResult();

		CampaignMasterTO copyTO;
		/* try { */
		copyTO = (CampaignMasterTO) masterTO.cloneObject(masterTO);
		copyTO.setCampaignName(masterTO.getCampaignName() + "_Copy");
		copyTO.setCreateDate(new Date());
		session.save(copyTO);
		newCampaignId = copyTO.getId() + "";
		/*
		 * } catch (CloneNotSupportedException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
		return newCampaignId;

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

	@Override
	public CampaignMasterTO getCampaignDetails(int campaignId, int userId) {

		// TODO Auto-generated method stub

		String pattern = "dd MMM yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

		String datePattern = "dd/MM/yyyy";
		SimpleDateFormat uidateformat = new SimpleDateFormat(datePattern);

		String hql = "";
		int ruleCount = 0;
		Long count;

		LinkedHashMap<String, String> campaignTypeMap = getCampaignTypesMap();
		LinkedHashMap<String, String> campaignSegmentTypeMap = getCampaignSegmentTypesMap();
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(CampaignMasterTO.class);
		criteria.add(Restrictions.eq("id", campaignId));

		CampaignMasterTO mainTO = null;
		CampaignMasterTO to = (CampaignMasterTO) criteria.uniqueResult();

		mainTO = new CampaignMasterTO();
		mainTO.setId(to.getId());
		mainTO.setCampaignName(to.getCampaignName());
		mainTO.setCampaignDesc(to.getCampaignDesc());
		mainTO.setCampaignType(to.getCampaignType());
		mainTO.setCampaignTypeUI(campaignTypeMap.get(to.getCampaignType()));
		mainTO.setStatus(to.getStatus());
		mainTO.setCreateDate(to.getCreateDate());
		mainTO.setStartDate(to.getStartDate());
		mainTO.setEndDate(to.getEndDate());
		mainTO.setCampaignSegmentType(to.getCampaignSegmentType());
		mainTO.setCampaignSegmentTypeUI(campaignSegmentTypeMap.get(to.getCampaignSegmentType() + ""));
		mainTO.setIsTemplate(to.getIsTemplate());
		mainTO.setGeoFencingId(to.getGeoFencingId());
		// mainTO.setExpiryDate(to.getExpiryDate());
		/*
		 * if (to.getStatus().equalsIgnoreCase("1")) mainTO.setStatusDesc("Active"); if
		 * (to.getStatus().equalsIgnoreCase("0")) mainTO.setStatusDesc("InActive");
		 */
		mainTO.setStatusDesc(getCampaignStatusMap().get(to.getStatus()));
		hql = "select count(*) from RuleBuilderTO where campaignId=" + to.getId();
		count = (Long) session.createQuery(hql).uniqueResult();

		if (count != null)
			ruleCount = count.intValue();
		// to.setNoOfActiveRules(100);
		mainTO.setNoOfInActiveRules(ruleCount);

		hql = "select count(*) from ScheduleTO where campaignId=" + to.getId();
		count = (Long) session.createQuery(hql).uniqueResult();

		if (count != null)
			ruleCount = count.intValue();
		// to.setNoOfActiveRules(100);
		mainTO.setNoOfActiveRules(ruleCount);

		mainTO.setCreateDateUI(simpleDateFormat.format(to.getCreateDate()));
		mainTO.setStartDateUI(uidateformat.format(to.getStartDate()));
		mainTO.setEndDateUI(uidateformat.format(to.getEndDate()));
		// mainTO.setExpiryDateUI(uidateformat.format(to.getExpiryDate()));
		// mainTO.setCampaignType(campaignTypeMap.get(to.getCampaignType()));

		mainTO.setCreateDateForOverview(uidateformat.format(to.getCreateDate()));
		mainTO.setEndDateForOverview(uidateformat.format(to.getEndDate()));
		mainTO.setStartDateForOverview(uidateformat.format(to.getStartDate()));
		// mainTO.setExpiryDateForOverview(uidateformat.format(to.getExpiryDate()));

		return mainTO;

	}

	@Override
	public List<CampaignSegmentTypeTO> getCampaignSegmentTypes() {
		// TODO Auto-generated method stub

		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings({ "unchecked", "deprecation" })
		List<CampaignSegmentTypeTO> list = (List<CampaignSegmentTypeTO>) session
				.createCriteria(CampaignSegmentTypeTO.class).list();

		return list;
	}

	public LinkedHashMap<String, String> getCampaignSegmentTypesMap() {
		LinkedHashMap<String, String> campaignSegmentTypesMap = new LinkedHashMap<String, String>();
		Session session = sessionFactory.getCurrentSession();

		@SuppressWarnings({ "unchecked", "deprecation" })
		List<CampaignSegmentTypeTO> list = (List<CampaignSegmentTypeTO>) session
				.createCriteria(CampaignSegmentTypeTO.class).list();
		for (CampaignSegmentTypeTO to : list) {
			campaignSegmentTypesMap.put(to.getId() + "", to.getName());
		}

		return campaignSegmentTypesMap;
	}

	@Override
	public List<RuleTO> getRulesOfACampaign(int campaignId, int userId) {
		// TODO Auto-generated method stub
		RuleTO ruleTO = null;
		Criteria criteria = null;
		Session session = sessionFactory.getCurrentSession();

		CampaignMasterTO masterTO = getCampaignDetails(campaignId,userId);

		List<RuleTO> mainList = new ArrayList<RuleTO>();
		criteria = session.createCriteria(RuleBuilderTO.class);

		// criteria.add(Restrictions.eq("userId", 1));
		if (campaignId != 0) {
			criteria.add(Restrictions.eq("campaignId", campaignId));
		}
		if (userId != 1) {
			criteria.add(Restrictions.eq("userId", userId));
		}
		criteria.addOrder(Order.desc("id"));

		List<RuleBuilderTO> savedRules = criteria.list();
		if (!savedRules.isEmpty()) {
			for (RuleBuilderTO to : savedRules) {
				ruleTO = new RuleTO();
				ruleTO.setRuleId(to.getId());
				ruleTO.setRuleName(to.getName());
				ruleTO.setIsSavedORScheduledRule(-1);
				ruleTO.setCampaignId(campaignId);
				ruleTO.setCampaignName(masterTO.getCampaignName());
				mainList.add(ruleTO);
			}
		}

		criteria = session.createCriteria(ScheduleTO.class);
		if (campaignId != 0) {
			criteria.add(Restrictions.eq("campaignId", campaignId));
		}
		if (userId != 1) {
			criteria.add(Restrictions.eq("userId", userId));
		}
		criteria.addOrder(Order.desc("id"));
		List<ScheduleTO> scheduledRules = criteria.list();
		if (!scheduledRules.isEmpty()) {
			for (ScheduleTO to : scheduledRules) {
				ruleTO = new RuleTO();
				ruleTO.setRuleId(to.getId());
				ruleTO.setRuleName(to.getRuleName());
				ruleTO.setIsSavedORScheduledRule(1);
				ruleTO.setCampaignId(campaignId);
				ruleTO.setCampaignName(masterTO.getCampaignName());
				ruleTO.setStatusID(to.getRuleStatus());
				mainList.add(ruleTO);
			}
		}
		return mainList;
	}

	@Override
	public void copyCampaignWithSpecificRules(List<RuleTO> rulesList) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = null;
		int campaignId = rulesList.get(0).getCampaignId();

		ArrayList<Integer> savedRuleIds = new ArrayList<Integer>();
		ArrayList<Integer> scheduledRuleIds = new ArrayList<Integer>();
		LinkedHashMap<Integer, String> savedRulesMapForJson = new LinkedHashMap<Integer, String>();
		LinkedHashMap<Integer, String> scheduledRulesMapForJson = new LinkedHashMap<Integer, String>();
		for (RuleTO to : rulesList) {
			if (to.getIsSavedORScheduledRule() == -1) {
				savedRuleIds.add(to.getRuleId());
			} else if (to.getIsSavedORScheduledRule() == 1) {
				scheduledRuleIds.add(to.getRuleId());
			}

		}
		if (!savedRuleIds.isEmpty()) {
			criteria = session.createCriteria(RuleBuilderTO.class);
			criteria.add(Restrictions.in("Id", savedRuleIds));
			List<RuleBuilderTO> savedRulesList = criteria.list();
			if (!savedRulesList.isEmpty()) {
				for (RuleBuilderTO savedTO : savedRulesList) {
					savedRulesMapForJson.put(savedTO.getId(), savedTO.getRuleJson());
				}

			}
		}

		if (!scheduledRuleIds.isEmpty()) {
			criteria = session.createCriteria(ScheduleTO.class);
			criteria.add(Restrictions.in("Id", scheduledRuleIds));
			List<ScheduleTO> scheduledRulesList = criteria.list();
			if (!scheduledRulesList.isEmpty()) {
				for (ScheduleTO scheduledTO : scheduledRulesList) {
					scheduledRulesMapForJson.put(scheduledTO.getId(), scheduledTO.getUiJson());
				}

			}
		}

		if (!rulesList.isEmpty()) {
			RuleBuilderTO ruleBuilderTO = null;
			for (RuleTO to : rulesList) {
				ruleBuilderTO = new RuleBuilderTO();
				ruleBuilderTO.setName(to.getRuleName());
				ruleBuilderTO.setCampaignId(to.getCampaignId());
				if (to.getIsSavedORScheduledRule() == -1)
					ruleBuilderTO.setRuleJson(savedRulesMapForJson.get(to.getRuleId()));
				else if (to.getIsSavedORScheduledRule() == 1)
					ruleBuilderTO.setRuleJson(scheduledRulesMapForJson.get(to.getRuleId()));
				ruleBuilderTO.setCreateDate(new Date());
				ruleBuilderTO.setUserId(1);
				session.save(ruleBuilderTO);

			}
		}

	}

	public LinkedHashMap<String, String> getCampaignStatusMap() {
		LinkedHashMap<String, String> statusMap = new LinkedHashMap<String, String>();
		statusMap.put("0", "InActive");
		statusMap.put("1", "Active");
		statusMap.put("2", "Suspended");
		statusMap.put("3", "Expired");
		return statusMap;
	}

	@Override
	public String updatePlayPauseStatus(int campaignId, int status) {
		// TODO Auto-generated method stub
		String mainStatus = "1";
		Session session = sessionFactory.getCurrentSession();
		Query query = null;
		String hql = "update CampaignMasterTO set playPauseStatus=" + status + "  where id=" + campaignId;
		query = session.createQuery(hql);
		int result = query.executeUpdate();
		if (result > 0)
			mainStatus = "0";

		return mainStatus;
	}

	@Override
	public List<CampaignMasterTO> getCampaignsWrtType(int typeId, String campaignName) {
		// TODO Auto-generated method stub

		Session session = sessionFactory.getCurrentSession();
		LinkedHashMap<String, String> campaignTypeMap = getCampaignTypesMap();
		LinkedHashMap<String, String> campaignTypeImageMap = getCampaignTypeImageMap();
		LinkedHashMap<String, String> campaignSegmentTypeMap = getCampaignSegmentTypesMap();
		String pattern = "dd MMM yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

		String datePattern = "dd/MM/yyyy";
		SimpleDateFormat uidateformat = new SimpleDateFormat(datePattern);

		Criteria criteria = session.createCriteria(CampaignMasterTO.class);
		// criteria.add(Restrictions.eq("status", "1"));
		if (typeId != -1)
			criteria.add(Restrictions.eq("campaignType", typeId));
		if (campaignName != null && !campaignName.trim().equalsIgnoreCase(""))
			criteria.add(Restrictions.ilike("campaignName", campaignName, MatchMode.ANYWHERE));
		criteria.add(Restrictions.eq("isTemplate", 1));
		criteria.addOrder(Order.desc("id"));
		CampaignMasterTO mainTO = null;
		List<CampaignMasterTO> mainList = new ArrayList();
		List<CampaignMasterTO> list = criteria.list();
		for (CampaignMasterTO to : list) {
			mainTO = new CampaignMasterTO();
			mainTO.setId(to.getId());
			mainTO.setCampaignName(to.getCampaignName());
			mainTO.setCampaignDesc(to.getCampaignDesc());
			mainTO.setCampaignTypeUI(campaignTypeMap.get(to.getCampaignType() + ""));
			mainTO.setCampaignTypeIcon(campaignTypeImageMap.get(to.getCampaignType() + ""));
			mainTO.setCampaignType(to.getCampaignType());
			mainTO.setStatus(to.getStatus());
			mainTO.setCreateDate(to.getCreateDate());
			mainTO.setStartDate(to.getStartDate());
			mainTO.setEndDate(to.getEndDate());
			mainTO.setCampaignSegmentType(to.getCampaignSegmentType());
			mainTO.setCampaignSegmentTypeUI(campaignSegmentTypeMap.get(to.getCampaignSegmentType() + ""));
			mainTO.setIsTemplate(to.getIsTemplate());
			mainTO.setDefaultTemplate(to.getDefaultTemplate());
			// mainTO.setExpiryDate(to.getExpiryDate());
			/*
			 * if (to.getStatus().equalsIgnoreCase("1")) mainTO.setStatusDesc("Active"); if
			 * (to.getStatus().equalsIgnoreCase("0")) mainTO.setStatusDesc("InActive");
			 */
			mainTO.setStatusDesc(getCampaignStatusMap().get(to.getStatus()));

			mainTO.setCreateDateUI(simpleDateFormat.format(to.getCreateDate()));
			mainTO.setStartDateUI(uidateformat.format(to.getStartDate()));
			mainTO.setEndDateUI(uidateformat.format(to.getEndDate()));
			// mainTO.setExpiryDateUI(uidateformat.format(to.getExpiryDate()));
			// mainTO.setCampaignType(campaignTypeMap.get(to.getCampaignType()));

			mainTO.setCreateDateForOverview(uidateformat.format(to.getCreateDate()));
			mainTO.setEndDateForOverview(uidateformat.format(to.getEndDate()));
			mainTO.setStartDateForOverview(uidateformat.format(to.getStartDate()));
			// mainTO.setExpiryDateForOverview(uidateformat.format(to.getExpiryDate()));

			mainList.add(mainTO);

		}

		return mainList;
	}

	public LinkedHashMap<String, String> getCampaignTypeImageMap() {
		LinkedHashMap<String, String> campaignTypeImageMap = new LinkedHashMap<String, String>();
		Session session = sessionFactory.getCurrentSession();

		@SuppressWarnings({ "unchecked", "deprecation" })
		List<CampaignTypeTO> list = (List<CampaignTypeTO>) session.createCriteria(CampaignTypeTO.class).list();
		for (CampaignTypeTO to : list) {
			campaignTypeImageMap.put(to.getId() + "", to.getIcon());
		}

		return campaignTypeImageMap;
	}

	/*
	 * Task profile
	 */
	public CampaignMasterTO createTaskProfile(CampaignMasterTO campTO) {

		String respCode = null;
		String taskProfileUrl = env.getProperty("taskprofile.url");
		System.out.println("Task profile url >>>>>>>>>>>>>> : " + taskProfileUrl);

		TaskProfileTO to = new TaskProfileTO();
		to.setCampaignId(campTO.getId());
		to.setStartDate(dateFormat.taskProfileDate(campTO.getStartDate()));
		to.setEndDate(dateFormat.taskProfileDate(campTO.getEndDate()));
		to.setStatus(campTO.getStatus().equals("1") ? "Active" : "In Active");
		to.setUserId(1);

		ObjectMapper Obj = new ObjectMapper();
		String jsonStr = null;

		try {
			jsonStr = Obj.writeValueAsString(to);
			System.out.println("Task profile request json >>>>>>>>>>>>>> : " + jsonStr);
		} catch (IOException e) {
			e.printStackTrace();
		}

		InputStream inputStream = null;
		DataOutputStream out = null;
		HttpURLConnection connection = null;
		StringBuffer sb = new StringBuffer();

		try {
			URL url = new URL(taskProfileUrl);
			connection = (HttpURLConnection) url.openConnection();
			connection.setConnectTimeout(15000);
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setInstanceFollowRedirects(false);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("charset", "utf-8");
			connection.setRequestProperty("Content-Length", "" + Integer.toString(to.toString().getBytes().length));

			out = new DataOutputStream(connection.getOutputStream());
			out.writeBytes(jsonStr);
			out.flush();
			out.close();

			inputStream = new BufferedInputStream(connection.getInputStream(), 32 * 1024);

			int character = inputStream.read();

			while (character != -1) {
				sb.append((char) character);
				character = inputStream.read();
			}

			System.out.println("Response from Task profile API =============================>  " + sb);

			if (sb != null && !sb.toString().trim().equalsIgnoreCase("")) {
				System.out.println("=================== 1 =============================>  ");
				JSONObject jsonObj = new JSONObject(sb.toString());
				System.out.println("=================== 2 =============================>  " + jsonObj);
				// System.out.println("Status =============================>
				// "+jsonObj.getString("status"));
			} else {
				System.out.println("Empty Resonse from Task Profile API !");
			}

			/*
			 * if (sb.toString() != null && !sb.toString().trim().equalsIgnoreCase("")) {
			 * 
			 * JSONObject jsonObj = new JSONObject(sb.toString());
			 * 
			 * if (sb.toString() == null || sb.toString().equals("") ||
			 * !jsonObj.getString("status").equals("201")) { respCode = "1"; System.out.
			 * println("Exception on Task Profile creation process from Bulk side !");
			 * TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); } else
			 * { respCode = "0"; } } else {
			 * System.out.println("Empty Resonse from Task Profile API !");
			 * TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); }
			 */

		} catch (ProtocolException e) {
			System.out.println("ProtocolException Exception ================================> ");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IOException Exception ======================================> ");
			e.printStackTrace();
		}

		return campTO;
	}
}
