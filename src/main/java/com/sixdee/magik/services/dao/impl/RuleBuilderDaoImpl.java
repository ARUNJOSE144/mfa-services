
package com.sixdee.magik.services.dao.impl;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sixdee.magik.services.dao.AuditInfoDAO;
import com.sixdee.magik.services.dao.RuleBuilderDao;
import com.sixdee.magik.services.model.ActionFileTO;
import com.sixdee.magik.services.model.ApprovalAuditTO;
import com.sixdee.magik.services.model.AuditInfoTO;
import com.sixdee.magik.services.model.CampaignMasterTO;
import com.sixdee.magik.services.model.ContactPolicyTO;
import com.sixdee.magik.services.model.GroupComparisonTo;
import com.sixdee.magik.services.model.LeadPolicyTO;
import com.sixdee.magik.services.model.OnlineTriggerUrlTO;
import com.sixdee.magik.services.model.PaginationDTO;
import com.sixdee.magik.services.model.RemoteCopyUserTO;
import com.sixdee.magik.services.model.RuleBuilderTO;
import com.sixdee.magik.services.model.RuleTO;
import com.sixdee.magik.services.model.SamplingTO;
import com.sixdee.magik.services.model.ScheduleTO;
import com.sixdee.magik.services.model.ScheduleTypeTO;
import com.sixdee.magik.services.model.TokenMaster;
import com.sixdee.magik.services.model.WeekdaysTO;

/**
 * @author ramesh.cheerla
 * @Date : October, 2018
 *
 */

@Repository
public class RuleBuilderDaoImpl implements RuleBuilderDao {

	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;
	private String hql = null;
	private Query query = null;

	@Autowired
	private Environment env;

	@Value("${BL_JSON_URL}")
	String BL_JSON_URL;

	String pattern = "dd MMM yyyy";
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

	static Logger logger = Logger.getLogger(RuleBuilderDaoImpl.class);

	private String telgMessage = "";
	String idValue = null;

	@Autowired
	AuditInfoDAO auditInfo;
	AuditInfoTO audtoTO = null;

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public RuleBuilderTO saveRule(RuleBuilderTO ruleTo) {
		System.out.println("SaveRule  " + ruleTo.getUserId());
		try {
			Session session = sessionFactory.getCurrentSession();
//			ruleTo.setCreateDate(new Date());
//			ruleTo.setCreateBy("sud");
//			ruleTo.setLastUpdatedDate(new Date());
//			ruleTo.setCampaignId(1);
			/*
			 * RuleBuilderTO builderTO = new RuleBuilderTO(); // builderTO.setCreateDate(new
			 * Date()); builderTO.setRuleJson(ruleTo.getRuleJson());
			 * builderTO.setName(ruleTo);
			 */

			System.out.println("save rule idd    " + ruleTo.getId());
			if (ruleTo.getId() == 0) {
				audtoTO = new AuditInfoTO();
				audtoTO.setDesc(ruleTo.getName() + "Created");
				audtoTO.setUserId(Integer.parseInt(ruleTo.getUserIdAuit()));
				audtoTO.setFeatureName("Saved Rule");
				audtoTO.setAddedName(ruleTo.getName());
				audtoTO.setActionType("Saved Rule Created");
				audtoTO.setAttribute("N/A");
				audtoTO.setPreviousValue("N/A");
				audtoTO.setNewValue("N/A");
				audtoTO.setRoleName(ruleTo.getRoleName());
				audtoTO.setCreatedBy(ruleTo.getCreatedBy());
				auditInfo.addAuditLog(audtoTO);
			}

			ruleTo.setCreateDate(new Date());
			// ruleTo.setUserId(1);
			ruleTo.setUserId(ruleTo.getUserId());
			session.saveOrUpdate(ruleTo);

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("in catch");
			e.printStackTrace();
		}
		return ruleTo;

	}

	@Override
	public ScheduleTO loadScheduleData(int userId) {
		// TODO Auto-generated method stub
		ScheduleTO scheduleTO = new ScheduleTO();

		LinkedHashMap<Integer, String> samplingMap = new LinkedHashMap<Integer, String>();
		LinkedHashMap<Integer, String> campaignsMap = new LinkedHashMap<Integer, String>();
		LinkedHashMap<Integer, String> scheduleTypeMap = new LinkedHashMap<Integer, String>();
		LinkedHashMap<Integer, String> leadPolicyMap = new LinkedHashMap<Integer, String>();
		LinkedHashMap<Integer, String> contactPolicyMap = new LinkedHashMap<Integer, String>();
		LinkedHashMap<Integer, String> weekdaysMap = new LinkedHashMap<Integer, String>();
		List<WeekdaysTO> weekdaysList = new ArrayList<WeekdaysTO>();

		Session session = sessionFactory.getCurrentSession();

		Criteria criteria = session.createCriteria(SamplingTO.class);
		// criteria.add(Restrictions.eq("userId", 1));
		criteria.addOrder(Order.desc("id"));
		List<SamplingTO> samplingList = criteria.list();
		if (!samplingList.isEmpty()) {
			for (SamplingTO samplingTO : samplingList) {
				samplingMap.put(samplingTO.getId(), samplingTO.getSamplingName());
			}

		}

		criteria = session.createCriteria(WeekdaysTO.class);
		// criteria.add(Restrictions.eq("userId", 1));
		criteria.addOrder(Order.asc("id"));
		weekdaysList = criteria.list();

		criteria = session.createCriteria(CampaignMasterTO.class);
		// criteria.add(Restrictions.eq("userId", 1));
		if (userId != 1) {
			criteria.add(Restrictions.eq("userId", userId));
		}
		criteria.addOrder(Order.desc("id"));
		List<CampaignMasterTO> campaignsList = criteria.list();
		if (!campaignsList.isEmpty()) {
			for (CampaignMasterTO campaignMasterTO : campaignsList) {
				if (campaignMasterTO.getIsTemplate() != 1)
					campaignsMap.put(campaignMasterTO.getId(), campaignMasterTO.getCampaignName());
			}

		}

		criteria = session.createCriteria(ScheduleTypeTO.class);
		// criteria.add(Restrictions.eq("userId", 1));
		criteria.addOrder(Order.asc("id"));
		List<ScheduleTypeTO> scheduleTypeList = criteria.list();
		if (!scheduleTypeList.isEmpty()) {
			for (ScheduleTypeTO typeTO : scheduleTypeList) {
				scheduleTypeMap.put(typeTO.getId(), typeTO.getScheduleType());
			}

		}

		criteria = session.createCriteria(LeadPolicyTO.class);
		// criteria.add(Restrictions.eq("userId", 1));
		criteria.addOrder(Order.desc("id"));
		List<LeadPolicyTO> leadPolicyList = criteria.list();
		if (!leadPolicyList.isEmpty()) {
			for (LeadPolicyTO to : leadPolicyList) {
				leadPolicyMap.put(to.getId(), to.getPolicyName());
			}

		}

		criteria = session.createCriteria(ContactPolicyTO.class);
		// criteria.add(Restrictions.eq("userId", 1));
		criteria.addOrder(Order.desc("policyId"));
		List<ContactPolicyTO> contactPolicyList = criteria.list();
		if (!contactPolicyList.isEmpty()) {
			for (ContactPolicyTO to : contactPolicyList) {
				contactPolicyMap.put(to.getPolicyId(), to.getPolicyName());
			}

		}

		scheduleTO.setCampaignMap(campaignsMap);
		scheduleTO.setSamplingMap(samplingMap);
		scheduleTO.setScheduleTypeMap(scheduleTypeMap);
		scheduleTO.setLeadPolicyMap(leadPolicyMap);
		scheduleTO.setContactPolicyMap(contactPolicyMap);
		scheduleTO.setWeekdaysList(weekdaysList);
		return scheduleTO;
	}

	@Override
	public List<RuleTO> getAllRules(int type, int campaignId, int userId) {
		// TODO Auto-generated method stub
		ScheduleTO scheduleTO = loadScheduleData(userId);
		System.out.println("Campaings Map---------->" + scheduleTO.getCampaignMap());
		List<RuleTO> mainList = new ArrayList<RuleTO>();
		RuleTO ruleTO = null;
		Criteria criteria = null;
		Session session = sessionFactory.getCurrentSession();

		System.out.println("Campaing id------------>" + campaignId);

		LinkedHashMap<Integer, String> scheduleTypeMap = getScheduleTypesMap();

		if (type == 1) {
			// -------------- Scheduled Rules-----------------//
			criteria = session.createCriteria(ScheduleTO.class);
			// criteria.add(Restrictions.eq("userId", 1));
			if (userId != 1) {
				criteria.add(Restrictions.eq("userId", userId));
			}
			criteria.addOrder(Order.desc("id"));
			List<ScheduleTO> scheduledRules = criteria.list();
			if (!scheduledRules.isEmpty()) {
				for (ScheduleTO to : scheduledRules) {
					ruleTO = new RuleTO();

					// System.out.println("AB Testing before " + to.getIsABTesting());
					int abTest = Integer.parseInt(to.getIsABTesting());
					// if (abTest == 1) {
					System.out.println("AB Testing " + abTest);
					ruleTO.setRuleId(to.getId());
					ruleTO.setRuleName(to.getRuleName());
					ruleTO.setSavedORScheduledRule(1);
					ruleTO.setStatusID(to.getRuleStatus());
					ruleTO.setStatus(ruleStatusMap().get(to.getRuleStatus()));
					ruleTO.setCampaignId(to.getCampaignId());
					ruleTO.setScheduleType(scheduleTypeMap.get(to.getTypeId()));
					ruleTO.setScheduleTypeId(to.getTypeId());
					ruleTO.setUserId(to.getUserId());
					ruleTO.setUrlDetails("RE_BL");
					ruleTO.setCampaignName(
							to.getCampaignId() != 0 ? scheduleTO.getCampaignMap().get(to.getCampaignId()) : "");
					ruleTO.setCreateDateUI(simpleDateFormat.format(to.getCreateDate()));
					ruleTO.setIsABTesting(to.getIsABTesting());
					mainList.add(ruleTO);
					// }
				}
			}

		} else if (type == -1) {
			// -------------- SAved Rules-----------------//
			System.out.println("userId  " + userId);
			criteria = session.createCriteria(RuleBuilderTO.class);
			if (userId != 1) {
				criteria.add(Restrictions.eq("userId", userId));
			}
			if (campaignId != 0)
				criteria.add(Restrictions.eq("campaignId", campaignId));
			criteria.addOrder(Order.desc("id"));

			List<RuleBuilderTO> savedRules = criteria.list();
			if (!savedRules.isEmpty()) {
				for (RuleBuilderTO to : savedRules) {
					ruleTO = new RuleTO();
					ruleTO.setRuleId(to.getId());
					ruleTO.setRuleName(to.getName());
					ruleTO.setSavedORScheduledRule(-1);
					ruleTO.setStatusID(-1);
					ruleTO.setUserId(to.getUserId());
					ruleTO.setStatus(ruleStatusMap().get(-1));
					ruleTO.setCampaignId(to.getCampaignId());
					ruleTO.setCampaignName(
							to.getCampaignId() != 0 ? scheduleTO.getCampaignMap().get(to.getCampaignId()) : "");
					ruleTO.setCreateDateUI(simpleDateFormat.format(to.getCreateDate()));
					mainList.add(ruleTO);
				}
			}
		} else if (type == 2) {
			criteria = session.createCriteria(RuleBuilderTO.class);
			criteria.add(Restrictions.eq("campaignId", 0));
			// criteria.add(Restrictions.eq("userId", 1));
			if (userId != 1) {
				criteria.add(Restrictions.eq("userId", userId));
			}
			criteria.addOrder(Order.desc("id"));
			List<RuleBuilderTO> savedRules = criteria.list();
			System.out.println("saved rules size>>>>>" + savedRules.size());

			if (!savedRules.isEmpty()) {
				for (RuleBuilderTO to : savedRules) {
					ruleTO = new RuleTO();
					ruleTO.setRuleId(to.getId());
					ruleTO.setRuleName(to.getName());
					ruleTO.setSavedORScheduledRule(-1);
					ruleTO.setStatusID(-1);
					ruleTO.setStatus(ruleStatusMap().get(-1));
					ruleTO.setCampaignId(to.getCampaignId());
					ruleTO.setUserId(to.getUserId());
					ruleTO.setCampaignName(
							to.getCampaignId() != 0 ? scheduleTO.getCampaignMap().get(to.getCampaignId()) : "");
					ruleTO.setCreateDateUI(simpleDateFormat.format(to.getCreateDate()));
					mainList.add(ruleTO);
				}
			}

		} else if (type == 0) {

			// -------------- Scheduled Rules-----------------//
			criteria = session.createCriteria(ScheduleTO.class);
			if (campaignId != 0)
				criteria.add(Restrictions.eq("campaignId", campaignId));
			// criteria.add(Restrictions.eq("isABTesting","0"));
			// criteria.add(Restrictions.eq("userId", 1));
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
					ruleTO.setSavedORScheduledRule(1);
					ruleTO.setStatus(ruleStatusMap().get(to.getRuleStatus()));
					ruleTO.setStatusID(to.getRuleStatus());
					ruleTO.setScheduleType(scheduleTypeMap.get(to.getTypeId()));
					ruleTO.setScheduleTypeId(to.getTypeId());
					ruleTO.setCampaignId(to.getCampaignId());
					ruleTO.setUserId(to.getUserId());
					ruleTO.setCampaignName(
							to.getCampaignId() != 0 ? scheduleTO.getCampaignMap().get(to.getCampaignId()) : "");
					ruleTO.setCreateDateUI(simpleDateFormat.format(to.getCreateDate()));
					// ruleTO.setCreateDate(to.getCreateDate());
					ruleTO.setUrlDetails("RE_BL");
					mainList.add(ruleTO);
				}
			}

			// -------------- Saved Rules-----------------//
			criteria = session.createCriteria(RuleBuilderTO.class);
			if (campaignId != 0)
				criteria.add(Restrictions.eq("campaignId", campaignId));
			if (userId != 1) {
				criteria.add(Restrictions.eq("userId", userId));
			}
			// criteria.add(Restrictions.eq("userId", 1));
			criteria.addOrder(Order.desc("id"));
			List<RuleBuilderTO> savedRules = criteria.list();
			if (!savedRules.isEmpty()) {
				for (RuleBuilderTO to : savedRules) {
					ruleTO = new RuleTO();
					ruleTO.setRuleId(to.getId());
					ruleTO.setRuleName(to.getName());
					ruleTO.setSavedORScheduledRule(-1);
					ruleTO.setStatusID(-1);
					ruleTO.setUserId(to.getUserId());
					ruleTO.setStatus(ruleStatusMap().get(-1));
					ruleTO.setCampaignId(to.getCampaignId());
					ruleTO.setCampaignName(
							to.getCampaignId() != 0 ? scheduleTO.getCampaignMap().get(to.getCampaignId()) : "");
					// ruleTO.setCreateDate(to.getCreateDate());
					ruleTO.setCreateDateUI(simpleDateFormat.format(to.getCreateDate()));
					mainList.add(ruleTO);
				}
			}

			/*
			 * Collections.sort(mainList,new Comparator<RuleTO>() {
			 * 
			 * @Override public int compare(RuleTO o1, RuleTO o2) { // TODO Auto-generated
			 * method stub return o2.getCreateDate().compareTo(o1.getCreateDate()); } });
			 */

		}

		System.out.println("Rules list--------->" + mainList);

		return mainList;

	}

	@Override
	public String scheduleRule(RuleTO ruleTO) {
		System.out.println("Rule TO----while scheduling------->" + ruleTO.toString());
		String status = "1";
		Session session = sessionFactory.getCurrentSession();
		System.out.println("BL_JSON_URL:::" + BL_JSON_URL);
		String blAdapterUrl = "";
		String urlAppender = "";
		System.out.println("Rule Engine Url BL  :::" + ruleTO.getRuleEngineUrl());
		System.out.println("Rule Engine Url id  :::" + ruleTO.getRuleEngineUrlId());
		System.out.println("Rule Engine Url Name  :::" + ruleTO.getRuleEngineUrlName());

		// Online Url
		System.out.println("Online Url BL  :::" + ruleTO.getOnlineTriggerUrl());
		System.out.println("Online Url id  :::" + ruleTO.getOnlineTriggerUrlId());
		System.out.println("Online Url Name  :::" + ruleTO.getOnlineTriggerUrlName());

		// rule Url appending
		System.out.println("url appending Create " + env.getProperty("create.rule"));
		System.out.println("url appending update " + env.getProperty("update.rule"));

		System.out.println("save rule idd    " + ruleTO.getRuleId());
		if (ruleTO.getRuleId() == 0) {
			audtoTO = new AuditInfoTO();
			audtoTO.setDesc(ruleTO.getRuleName() + "Created");
			audtoTO.setUserId(Integer.parseInt(ruleTO.getUserIdAudit()));
			audtoTO.setFeatureName("Schedule Rule ");
			audtoTO.setAddedName(ruleTO.getRuleName());
			audtoTO.setActionType("Schedule Rule Created");
			audtoTO.setAttribute("N/A");
			audtoTO.setPreviousValue("N/A");
			audtoTO.setNewValue("N/A");
			audtoTO.setRoleName(ruleTO.getRoleNameAudit());
			audtoTO.setCreatedBy(ruleTO.getUserNameAudit());
			auditInfo.addAuditLog(audtoTO);
		}

		System.out.println("UserId :::" + ruleTO.getUserId());

		if (ruleTO.getRuleEngineUrlId() != "" && ruleTO.getRuleEngineUrlId() != null) {
			System.out.println("Rule Engine Url : :");
			if (ruleTO.getRuleId() != 0) {
				urlAppender = ruleTO.getRuleEngineUrl() + env.getProperty("update.rule");
				System.out.println("urlAppender Modify  :::  " + urlAppender);
				blAdapterUrl = urlAppender;
			} else {
				urlAppender = ruleTO.getRuleEngineUrl() + env.getProperty("create.rule");
				System.out.println("urlAppender Create  :::  " + urlAppender);
				blAdapterUrl = urlAppender;
			}
		}
		if (ruleTO.getOnlineTriggerUrlId() != "" && ruleTO.getOnlineTriggerUrlId() != null) {
			System.out.println("Online Url  ::  ");
			if (ruleTO.getRuleId() != 0) {
				urlAppender = ruleTO.getOnlineTriggerUrl() + env.getProperty("update.rule");
				System.out.println("urlAppender Modify  :::  " + urlAppender);
				blAdapterUrl = urlAppender;
			} else {
				urlAppender = ruleTO.getOnlineTriggerUrl() + env.getProperty("create.rule");
				System.out.println("urlAppender Create  :::  " + urlAppender);
				blAdapterUrl = urlAppender;
			}
		}

		System.out.println("UserId :::" + ruleTO.getUserId());
		// String blAdapterUrl = BL_JSON_URL;

		String schedRuleJson = ruleTO.getRuleJson();
		int schedId;
		InputStream inputStream = null;
		DataOutputStream out = null;
		HttpURLConnection connection = null;
		StringBuffer sb = new StringBuffer();
		try {
			ScheduleTO scheduleTO = new ScheduleTO();
			if (ruleTO.getRuleId() != 0)
				scheduleTO.setId(ruleTO.getRuleId());
			scheduleTO.setRuleName(ruleTO.getRuleName());
			scheduleTO.setRuleJson(ruleTO.getRuleJson());
			scheduleTO.setUiJson(ruleTO.getUiJson());
			scheduleTO.setCampaignId(ruleTO.getCampaignId());
			// scheduleTO.setUserId(1);
			scheduleTO.setUserId(ruleTO.getUserId());
			scheduleTO.setCreateDate(new Date());
			scheduleTO.setPriority(ruleTO.getPriority());
			scheduleTO.setApprovalLevel(ruleTO.getApprovalLevel());
			scheduleTO.setExpiryDate(ruleTO.getExpiryDate());
			scheduleTO.setStartDate(ruleTO.getStartDate());
			scheduleTO.setTypeId(ruleTO.getScheduleTypeId());
			scheduleTO.setSamplingId(ruleTO.getSamplingId());
			scheduleTO.setContactPolicy(ruleTO.getContactPolicyId());
			scheduleTO.setLeadPolicy(ruleTO.getLeadPolicy());
			scheduleTO.setRuleStatus(1);
			if (ruleTO.getRuleEngineUrlId() != null) {
				scheduleTO.setRuleEngineURLId(Integer.parseInt(ruleTO.getRuleEngineUrlId()));
				scheduleTO.setRuleEngineUrl(ruleTO.getRuleEngineUrl());
			}
			if (ruleTO.getOnlineTriggerUrlId() != null) {
				scheduleTO.setRuleEngineURLId(Integer.parseInt(ruleTO.getOnlineTriggerUrlId()));
				scheduleTO.setRuleEngineUrl(ruleTO.getOnlineTriggerUrl());
			}
			System.out.println("before  If  :: " + ruleTO.getIsABTesting());
			if (ruleTO.getIsABTesting() == "0") {
				System.out.println("Inside If  :: ");
				scheduleTO.setIsABTesting("0"); // AB Testing Is Not Passing From UI
			} else {
				System.out.println("Inside Else  :: ");
				scheduleTO.setIsABTesting("1"); // AB Testing Is Passing From UI
			}
			if (ruleTO.getParentId() != null) {
				System.out.println("getParentId  :: " + ruleTO.getParentId().toString());
				scheduleTO.setApproverType(ruleTO.getParentId());
			}
			System.out.println("getApprovalStatus  :: " + ruleTO.getApprovalStatus());
			scheduleTO.setApprovalStatus(ruleTO.getApprovalStatus());

			/*
			 * if(ruleTO.getScheduleTypeId()==1) { scheduleTO.setDay(ruleTO.getDay());
			 * scheduleTO.setHour(ruleTO.getHour());
			 * scheduleTO.setMinute(ruleTO.getMinute()); }
			 */

			session.saveOrUpdate(scheduleTO);

			schedId = scheduleTO.getId();
			System.out.println("Generated Scheduled ID >>>>>>>>>>> " + schedId);

			String finalSchedJson = schedRuleJson.replace("ReplaceScheduledId", schedId + "");

			System.out.println("BL Adapter url >>>> " + blAdapterUrl);
			System.out.println("Schedule Rule JSON  >>>> " + finalSchedJson);

			// JSONObject jsonObj = new JSONObject(finalSchedJson);
//			JSONObject rulesObj = jsonObj.getJSONObject("data").getJSONObject("detail").getJSONObject("rules");
//			JSONArray childArr = (JSONArray) rulesObj.get("childrens");
//			
//			jsonArray(childArr);

			// System.out.println("Calling Telegram Notifer for notification
			// .................." + telgMessage);

//			TelegramNotifier notify = new TelegramNotifier();
//			notify.Notification(env.getProperty("channel.telegram.apiToken"),
//					env.getProperty("channel.telegram.chatId"), env.getProperty("channel.telegram.api"), telgMessage);

			URL url = new URL(blAdapterUrl.trim());
			connection = (HttpURLConnection) url.openConnection();
			connection.setConnectTimeout(15000);
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setInstanceFollowRedirects(false);
			connection.setRequestMethod("POST");
			// connection.setRequestProperty("Content-Type",
			// "application/x-www-form-urlencoded");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("charset", "utf-8");
			connection.setRequestProperty("Content-Length", "" + Integer.toString(finalSchedJson.getBytes().length));

			out = new DataOutputStream(connection.getOutputStream());
			out.writeBytes(finalSchedJson);
			out.flush();
			out.close();

			inputStream = new BufferedInputStream(connection.getInputStream(), 32 * 1024);

			int character = inputStream.read();
			System.out.println("v::::" + character);
			while (character != -1) {
				sb.append((char) character);
				character = inputStream.read();
			}
			logger.info("Response from BL =================  " + sb.toString());
			System.out.println("Response from BL =================  " + sb.toString());

			if (sb.toString() != null && !sb.toString().trim().equalsIgnoreCase("")) {

				JSONObject jsonObj = new JSONObject(sb.toString());

				if (sb.toString() == null || sb.toString().equals("")
						|| jsonObj.getString("respCode").equals("SC0001")) {
					status = "1";
					System.out.println("Exception on Schedule Rule processing from BL side !");
					// respTO.setCode(1);
					// respTO.setMessage("Exception on Schedule Rule processing from BL side !");
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				} else if (jsonObj.getString("respCode").equals("SC0000")) {
					status = "0";

					// Updating campaign Status after scheduling if campaign status is inactive.
					if (getCampaignStatus(ruleTO.getCampaignId()).trim().equalsIgnoreCase("0"))
						updateCampaignStatus(ruleTO.getCampaignId(), 1);

					if (ruleTO.getSavedRuleId() != 0)
						deleteSavedRule(ruleTO);
					status = "0";

					if (ruleTO.getOnlineTriggerUrlId() != null) {
						onlineTriggerSave(ruleTO, session, schedId, status);
					}

					insertIntoGroupComaprison(ruleTO, session, schedId);
				}

				if (getCampaignStatus(ruleTO.getCampaignId()).trim().equalsIgnoreCase("0"))
					updateCampaignStatus(ruleTO.getCampaignId(), 1);
				System.out.println("Save Rule ID:::::::" + ruleTO.getSavedRuleId());

			} else {
				System.out.println("Empty Resonse from BL !");
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			}

			// Updating campaign Status after scheduling if campaign status is inactive.
//			if(getCampaignStatus(ruleTO.getCampaignId()).trim().equalsIgnoreCase("0"))
//				updateCampaignStatus(ruleTO.getCampaignId(),1);

//			if(ruleTO.getSavedRuleId()!=0)
//				deleteSavedRule(ruleTO);
			// status="0";

		} catch (Exception e) {
			// TODO: handle exception
			// respTO.setCode(1);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			// System.out.println("1111111---------->Exception on Scheduled Json Processing
			// from BL side....");
			status = "1";
			try {
				throw new Exception();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			logger.info(":::::>Exception on Scheduled Json Processing to BL side....");
			e.printStackTrace();
		} finally {

			try {
				if (inputStream != null) {
					inputStream.close();
					inputStream = null;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				if (out != null) {
					out.close();
					out = null;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				if (connection != null)
					connection.disconnect();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		return status;

	}

	private void onlineTriggerSave(RuleTO ruleTO, Session session, int schedId, String status) {
		// TODO Auto-generated method stub
		System.out.println("getScheduleType  :  " + ruleTO.getScheduleTypeId());
		System.out.println("status  :  " + status);
		System.out.println("URl  :  " + ruleTO.getOnlineTriggerUrl());
		if (ruleTO.getScheduleTypeId() == 6 && (status == "0") && ruleTO.getOnlineTriggerUrl() != null
				&& ruleTO.getOnlineTriggerUrlId() != "0" && ruleTO.getOnlineTriggerId() != "0") {
			OnlineTriggerUrlTO onlineRuleTrigger = new OnlineTriggerUrlTO();
			onlineRuleTrigger.setRuleId(schedId);
			onlineRuleTrigger.setRuleURL(ruleTO.getOnlineTriggerUrl());
			onlineRuleTrigger.setRuleURLId(ruleTO.getOnlineTriggerUrlId());
			onlineRuleTrigger.setTriggerId(ruleTO.getOnlineTriggerId());
			onlineRuleTrigger.setUserId(ruleTO.getUserId());
			onlineRuleTrigger.setCampId(ruleTO.getCampaignId());
			System.out.println("onlineRuleTrigger ::::::::::::  " + onlineRuleTrigger);
			session.saveOrUpdate(onlineRuleTrigger);

//			if (genericTO2.getCode() == 0) {
//				System.out.println("in reco urls");
//				ruleBuilderDAO.reloadRECOCache(ruleSchedulerTO.getRecoUrls());
		}

	}

	private boolean insertIntoGroupComaprison(RuleTO ruleTO, Session session, int schedId) {
		boolean status = false;
		ObjectMapper mapper = new ObjectMapper();
		List<GroupComparisonTo> list;
		System.out.println("Rule Id ::  " + schedId);
		try {
			// Delete part come Here
			String hql = "delete from GroupComparisonTo where scheduleid=" + schedId;
			session.createQuery(hql).executeUpdate();

			Map<String, Integer> analysisMap = new HashMap<String, Integer>();
			System.out.println("abtest :: " + ruleTO.getIsABTestingReport());
			System.out.println("TrackerBoosterReport :: " + ruleTO.getIsTrackerBoosterReport());
			int abTest = Integer.parseInt(ruleTO.getIsABTestingReport());
			int trackerBooster = Integer.parseInt(ruleTO.getIsTrackerBoosterReport());
			analysisMap.put("AB", abTest);
			analysisMap.put("TB", trackerBooster);
			for (Map.Entry<String, Integer> entry : analysisMap.entrySet()) {
				System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
				if (ruleTO.getGroupComparisonJson() != null && !ruleTO.getGroupComparisonJson().equalsIgnoreCase("")) {
					list = Arrays.asList(mapper.readValue(ruleTO.getGroupComparisonJson(), GroupComparisonTo[].class));
					System.out.println("GroupComparisonJson ::  " + ruleTO.getGroupComparisonJson().toString());
					for (GroupComparisonTo to : list) {
						to.setScheduleid(schedId);
						to.setScheduleName(ruleTO.getRuleName());
						to.setCampaignId(ruleTO.getCampaignId());
						to.setCampaignName(ruleTO.getCampaignNameGroup());

//						DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
//						String strDate = dateFormat.format(ruleTO.getStartDate());
//						to.setStartDate(strDate);
//
//						DateFormat dateFormat1 = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
//						String endDate = dateFormat1.format(ruleTO.getExpiryDate());
//						to.setEndDate(endDate);
//						System.out.println("endDate " + endDate);

						// to.setUserid(ruleTO.getUserId());
						if (entry.getKey().equals("AB")) {
							to.setAnalysysType(1);
							to.setUniqueid(schedId + "_" + to.getActionId() + "_" + 1);

						} else if (entry.getKey().equals("TB")) {
							to.setAnalysysType(2);
							to.setUniqueid(schedId + "_" + to.getActionId() + "_" + 2);
							System.out.println("getIsWeeklyReport" + ruleTO.getIsWeeklyReport());
							if (ruleTO.getIsWeeklyReport() != null && ruleTO.getIsWeeklyReport() != ""
									&& ruleTO.getIsWeeklyReport() == "1") {
								to.setIsweekly(Integer.parseInt(ruleTO.getIsWeeklyReport()));
								to.setNoofweeks(Integer.parseInt(ruleTO.getWeekTrakerReport()));
							}
						}

						if (entry.getKey().equals("AB") && entry.getValue() == 1) {
							System.out.println("IN AB SAVE");
							session.save(to);
							System.out.println("FINAL to AB " + to.toString());
						} else if (entry.getKey().equals("TB") && entry.getValue() == 1) {
							System.out.println("IN TB SAVE");
							to.setTbBefore(Integer.parseInt(ruleTO.getBeforeTrakerReport()));
							to.setTbAfter(Integer.parseInt(ruleTO.getAfterTrakerReport()));
							session.save(to);
							System.out.println("FINAL to  TB " + to.toString());
						}

						// to.setUniqueid(schedId + "_" + to.getActionId() + "_" + 1);
						// session.save(to);
					}

				}
			}

			status = true;
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return status;

	}

	private void jsonArray(JSONArray arr) {

		for (int i = 0; i < arr.length(); i++) {
			JSONObject objects = arr.getJSONObject(i);
			System.out.println("Json Object >>>>>>>>>>>>>>> : " + objects.toString());
			if (objects.get("type").equals("action")) {
				System.out.println("Inside action 1 >>>>>>>>>>>>>>> : " + objects.toString());
				JSONArray fieldArr = objects.getJSONObject("request").getJSONArray("field");
				for (int j = 0; j < fieldArr.length(); j++) {
					System.out.println("Inside action 2 >>>>>>>>>>>>>>> : " + objects.toString());
					JSONObject obj = fieldArr.getJSONObject(j);
					System.out.println(
							obj.get("name") + " ==========--------  " + env.getProperty("channel.telegram.text"));
					if (obj.get("name").equals(env.getProperty("channel.telegram.text"))) {
						System.out.println("Inside action 3 >>>>>>>>>>>>>>> : " + obj.toString());
						telgMessage = (String) obj.get("value");
						break;
					}
				}
			} else {
				if (objects.has("childrens")) {
					jsonArray((JSONArray) objects.get("childrens"));
				}
			}
		}
	}

	private void deleteSavedRule(RuleTO ruleTO) {
		Session session = null;
		Criteria criteria = null;

		try {
			session = sessionFactory.getCurrentSession();
			criteria = session.createCriteria(RuleBuilderTO.class);
			criteria.add(Restrictions.eq("Id", ruleTO.getSavedRuleId()));
			RuleBuilderTO ruleBuilderTO = (RuleBuilderTO) criteria.uniqueResult();
			session.delete(ruleBuilderTO);

		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}

	}

	@Override
	public String getRuleJson(RuleTO ruleTO) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = null;
		String ruleJson = "";
		// System.out.println("Rule TO---------in Get
		// json---------->"+ruleTO.toString());
		if (ruleTO.getIsSavedORScheduledRule() == -1) {
			System.out.println("Came to check saved rules with id--------->" + ruleTO.getRuleId());
			criteria = session.createCriteria(RuleBuilderTO.class);
			criteria.add(Restrictions.eq("Id", ruleTO.getRuleId()));
			RuleBuilderTO savedRuleTO = (RuleBuilderTO) criteria.uniqueResult();
			if (savedRuleTO != null)
				ruleJson = savedRuleTO.getRuleJson();

		} else if (ruleTO.getIsSavedORScheduledRule() == 1) {
			System.out.println("Came to check scheduled rules with id--------->" + ruleTO.getRuleId());
			criteria = session.createCriteria(ScheduleTO.class);
			criteria.add(Restrictions.eq("Id", ruleTO.getRuleId()));
			ScheduleTO scheduleTO = (ScheduleTO) criteria.uniqueResult();
			if (scheduleTO != null)
				ruleJson = scheduleTO.getUiJson();

		}

		// System.out.println("Rule Json------------->"+ruleJson);
		return ruleJson;
	}

	@Override
	public String deleteRule(RuleTO ruleTO) {
		// TODO Auto-generated method stub
		String status = "1";
		Criteria criteria = null;
		Session session = sessionFactory.getCurrentSession();
		if (ruleTO.getIsSavedORScheduledRule() == -1) {
			criteria = session.createCriteria(RuleBuilderTO.class);
			criteria.add(Restrictions.eq("id", ruleTO.getRuleId()));
			RuleBuilderTO ruleBuilderTO = (RuleBuilderTO) criteria.uniqueResult();
			session.delete(ruleBuilderTO);
			status = "0";

			audtoTO = new AuditInfoTO();
			audtoTO.setDesc(ruleTO.getRuleName() + "Deleted");
			audtoTO.setUserId(Integer.parseInt(ruleTO.getUserIdAudit()));
			audtoTO.setFeatureName("Saved Rule");
			audtoTO.setAddedName(ruleTO.getRuleName());
			audtoTO.setActionType("Saved Rule Deleted");
			audtoTO.setAttribute("N/A");
			audtoTO.setPreviousValue("N/A");
			audtoTO.setNewValue("N/A");
			audtoTO.setCreatedBy(ruleTO.getUserNameAudit());
			audtoTO.setRoleName(ruleTO.getRoleNameAudit());
			auditInfo.addAuditLog(audtoTO);

		} else if (ruleTO.getIsSavedORScheduledRule() == 1) {
			criteria = session.createCriteria(ScheduleTO.class);
			criteria.add(Restrictions.eq("id", ruleTO.getRuleId()));
			ScheduleTO scheduleTO = (ScheduleTO) criteria.uniqueResult();
			session.delete(scheduleTO);
			status = "0";

			audtoTO = new AuditInfoTO();
			audtoTO.setDesc(ruleTO.getRuleName() + "Deleted");
			audtoTO.setUserId(ruleTO.getUserId());
			audtoTO.setFeatureName("Schedule Rule");
			audtoTO.setAddedName(ruleTO.getRuleName());
			audtoTO.setActionType("Deleted");
			audtoTO.setAttribute("N/A");
			audtoTO.setPreviousValue("N/A");
			audtoTO.setNewValue("N/A");
			audtoTO.setCreatedBy(ruleTO.getUserNameAudit());
			audtoTO.setRoleName(ruleTO.getRoleNameAudit());
			auditInfo.addAuditLog(audtoTO);
		}
		return status;
	}

	@Override
	public RuleBuilderTO saveAttachedRule(RuleBuilderTO ruleTo) {
		// TODO Auto-generated method stub
		RuleBuilderTO returnTo = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			System.out.println("ruleTo::::::::::" + ruleTo);

			RuleBuilderTO existingRule = session.load(RuleBuilderTO.class, ruleTo.getRuleId());
			System.out.println("existingRule::::::::::" + existingRule);

			RuleBuilderTO attachRule = new RuleBuilderTO();
			System.out.println("existingRule::::::::::" + existingRule.getCampaignId());
			int type = 1;
			if (existingRule.getCampaignId() == 0) {
				System.out.println("Test:::::::::::::::::::::::::::111111");
				existingRule.setCreateDate(new Date());
				existingRule.setCampaignId(ruleTo.getCampaignId());
				type = 2;

			} else {

				System.out.println("Test:::::::::::::::::::::::::::2222");
				attachRule.setName(ruleTo.getName());
				attachRule.setCreateDate(new Date());
				attachRule.setCampaignId(ruleTo.getCampaignId());
				attachRule.setRuleJson(existingRule.getRuleJson());
				attachRule.setUserId(existingRule.getUserId());
				type = 1;

			}

			// attachRule.setLastUpdatedDate(existingRule.getLastUpdatedDate());

			System.out.println("attachRule::::::::::" + attachRule);
			if (type == 2) {
				System.out.println("in exusting" + existingRule);
				session.saveOrUpdate(existingRule);
				returnTo = existingRule;

			} else {
				System.out.println("in attached");
				session.saveOrUpdate(attachRule);
				returnTo = attachRule;
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("in catch");
			e.printStackTrace();

		}
		return returnTo;

	}

	public void updateCampaignStatus(int campaignId, int status) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "update CampaignMasterTO set status=" + status + " where id=" + campaignId;
		Query query = session.createQuery(hql);
		int result = query.executeUpdate();
		if (result > 0) {
			System.out.println("Campaign with id " + campaignId + " is Active now");
			logger.info("Campaign with id " + campaignId + " is Active now");
		}

	}

	public String getCampaignStatus(int campaignId) {
		String status = "";
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = null;

		criteria = session.createCriteria(CampaignMasterTO.class);
		criteria.add(Restrictions.eq("id", campaignId));

		System.out.println("campaignId::::::::::::::::::::" + campaignId);
		CampaignMasterTO masterTO = (CampaignMasterTO) criteria.uniqueResult();
		if (masterTO != null && masterTO.getStatus() != null) {
			status = masterTO.getStatus();
		}

		return status;
	}

	@Override
	public ScheduleTO loadScheduleDataOfRule(int ruleId) {
		// TODO Auto-generated method stub

		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = null;
		System.out.println("Rule id in daoimpl--------->" + ruleId);
		criteria = session.createCriteria(ScheduleTO.class);
		criteria.add(Restrictions.eq("id", ruleId));
		ScheduleTO masterTO = (ScheduleTO) criteria.uniqueResult();

		return masterTO;
	}

	public LinkedHashMap<Integer, String> ruleStatusMap() {
		LinkedHashMap<Integer, String> statusMap = new LinkedHashMap<>();
		statusMap.put(-1, "Inactive");
		statusMap.put(1, "Active");
		statusMap.put(2, "Paused");
		statusMap.put(3, "Stopped");

		return statusMap;
	}

	public LinkedHashMap<Integer, String> getScheduleTypesMap() {

		LinkedHashMap<Integer, String> scheduleTypesMap = new LinkedHashMap<>();

		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = null;
		criteria = session.createCriteria(ScheduleTypeTO.class);
		List<ScheduleTypeTO> list = criteria.list();
		if (!list.isEmpty()) {
			for (ScheduleTypeTO scheduleTypeTO : list) {
				scheduleTypesMap.put(scheduleTypeTO.getId(), scheduleTypeTO.getScheduleType());
			}
		}

		return scheduleTypesMap;

	}

	@Override
	public Boolean updateRuleStatus(int ruleId, Integer ruleStatus) {
		System.out.println("ruleId::::" + ruleId);
		System.out.println("status:::" + ruleStatus);
		Boolean Status = false;
		String urlAppender = "";

		JSONObject jsonReqObj = new JSONObject();

		InputStream inputStream = null;
		DataOutputStream out = null;
		HttpURLConnection connection = null;

		try {

			jsonReqObj.put("featureId", "SCHEDULE_STATUS_UPDATE");
			jsonReqObj.put("appName", "CMS");
			jsonReqObj.put("username", "6D");
			jsonReqObj.put("password", "6D");
			jsonReqObj.put("reqTxnId", "100");
			jsonReqObj.put("msgOrigin", "ORI");
			jsonReqObj.put("msgDest", "DEST");
			jsonReqObj.put("timestamp", System.currentTimeMillis());
			jsonReqObj.put("id", "");
			jsonReqObj.put("ruletype", "");

			JSONArray array = new JSONArray();

			JSONObject paramObj = new JSONObject();
			paramObj.put("name", "id");
			paramObj.put("value", String.valueOf(ruleId));
			array.put(paramObj);

			JSONObject paramObj2 = new JSONObject();
			paramObj2.put("name", "lOCK_UNLOCK");
			paramObj2.put("value", ruleStatus);
			array.put(paramObj2);

			JSONObject paramNameObj = new JSONObject();
			paramNameObj.put("param", array);

			JSONObject dataObj = new JSONObject();
			dataObj.put("detail", paramNameObj);
			dataObj.put("rule_name", "");

			jsonReqObj.put("data", dataObj);

			System.out.println("JSON:::::" + jsonReqObj.toString());

			System.out.println("Post URL::" + BL_JSON_URL);
			System.out.println("url appending updaterulestatus " + env.getProperty("updaterulestatus.rule"));
			urlAppender = BL_JSON_URL + env.getProperty("updaterulestatus.rule");
			System.out.println("urlAppender fetch ::   " + urlAppender);

			// URL url = new URL(BL_JSON_URL);
			URL url = new URL(urlAppender);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");

			connection.setConnectTimeout(15000);
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setInstanceFollowRedirects(false);

			connection.setRequestProperty("Content-Type", "application/json; utf-8");
			connection.setRequestProperty("charset", "utf-8");
			connection.setRequestProperty("Accept", "application/json");
			// connection.setRequestProperty("Content-Length", "" +
			// Integer.toString(playPauseJson.getBytes().length));

			out = new DataOutputStream(connection.getOutputStream());
			out.writeBytes(jsonReqObj.toString());
			out.flush();
			out.close();

			inputStream = new BufferedInputStream(connection.getInputStream(), 32 * 1024);
			StringBuffer sb = new StringBuffer();
			int character = inputStream.read();

			while (character != -1) {
				sb.append((char) character);
				character = inputStream.read();
			}
			System.out.println("sb.toString() ::::" + sb.toString());
			if (sb.toString() != null && !sb.toString().equals("")) {
				JSONObject jsonRespObj = new JSONObject(sb.toString());
				String value = (String) jsonRespObj.get("respCode");
				System.out.println("RespCOde::::" + value);
				System.out.println("RespCOde::::" + jsonRespObj.toString());
				if (value.equals("SC0000")) {
					Status = true;
				}

//				 JSONObject dataRespObj = jsonRespObj.getJSONObject("data");
//				 
//				 JSONArray fieldDetailRespArray =  dataRespObj.getJSONArray("detail");
//
//				 JSONObject paramRespObj = fieldDetailRespArray.getJSONObject(0);
//				 
//				JSONArray paramRespArray  =  paramRespObj.getJSONArray("param");
//				 
//				 System.out.println("Multi::::::"+paramRespArray.toString());
//				 JSONObject innerObj  = paramRespArray.getJSONObject(0);
//				
//				JSONArray multiparamArray   =innerObj.getJSONArray("multiParam");
//					
//				System.out.println("multiparamArray:::"+multiparamArray.toString());
				//

				// System.out.println("final out put::::" + sb.toString());

			}

		} catch (Exception e) {
			System.out.println("Exception on Scheduled Rule Status Processing from BL side....");
			e.printStackTrace();
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
					inputStream = null;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				if (out != null) {
					out.close();
					out = null;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				if (connection != null)
					connection.disconnect();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return Status;

	}

	private void fetchRuleStatus(int ruleId, int Status) {
	}

	@Override
	public List<RuleTO> getRuleAuditInfo(RuleTO ruleTO) {
		// TODO Auto-generated method stub
		List<RuleTO> rules = new ArrayList<RuleTO>();
		RuleTO mainRuleTO = null;
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = null;
		criteria = session.createCriteria(ScheduleTO.class);

		criteria.add(Restrictions.ge("startDate", ruleTO.getStartDate()));
		criteria.add(Restrictions.le("expiryDate", ruleTO.getExpiryDate()));
		List<ScheduleTO> scheduleRulesList = criteria.list();
		if (!scheduleRulesList.isEmpty()) {

			LinkedHashMap<Integer, String> campaignMap = getCampaignsMap();
			LinkedHashMap<Integer, String> scheduleTypeMap = getScheduleTypesMap();

			for (ScheduleTO schTO : scheduleRulesList) {
				mainRuleTO = new RuleTO();
				mainRuleTO.setRuleId(schTO.getId());
				mainRuleTO.setRuleName(schTO.getRuleName());
				mainRuleTO.setCampaignName(campaignMap.get(schTO.getCampaignId()));
				mainRuleTO.setScheduleType(scheduleTypeMap.get(schTO.getTypeId()));
				mainRuleTO.setStatus(ruleStatusMap().get(schTO.getRuleStatus()));
				mainRuleTO.setStatusID(schTO.getRuleStatus());
				mainRuleTO.setCreateDateUI(simpleDateFormat.format(schTO.getCreateDate()));
				rules.add(mainRuleTO);
			}

		}
		return rules;
	}

	public LinkedHashMap<Integer, String> getCampaignsMap() {

		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = null;
		LinkedHashMap<Integer, String> campaignMap = new LinkedHashMap<>();
		criteria = session.createCriteria(CampaignMasterTO.class);
		// criteria.add(Restrictions.eq("userId", 1));
		criteria.addOrder(Order.desc("id"));
		List<CampaignMasterTO> campaignsList = criteria.list();
		if (!campaignsList.isEmpty()) {
			for (CampaignMasterTO campaignMasterTO : campaignsList) {
				campaignMap.put(campaignMasterTO.getId(), campaignMasterTO.getCampaignName());
			}

		}
		return campaignMap;
	}

	@Override
	public String approveOrRejectRule(RuleTO ruleTO) {
		// TODO Auto-generated method stub
		System.out.println("parentId  " + ruleTO.getParentId());
		System.out.println("approvalStatus  " + ruleTO.getApprovalStatus());
		String status = "-1";
		Session session = sessionFactory.getCurrentSession();

		// Approval Pending From Top Level
		if (ruleTO.getApprovalStatus() == 1) {
			String hql = "update ScheduleTO set approvalStatus = '" + ruleTO.getApprovalStatus() + "', approverType = '"
					+ ruleTO.getParentId() + "' where id = '" + ruleTO.getRuleId() + "'";
			Query query = session.createQuery(hql);
			int result = query.executeUpdate();
			System.out.println("result  Value :: " + result);
			if (result > 0) {
				status = "0";
				String approveORReject = "Approved";
				saveApprovalAudit(ruleTO, approveORReject);
			}
		}

		// Rule Approved
		if (ruleTO.getApprovalStatus() == 2) {
			System.out.println("Bl calling Starting  :::  ");
			JSONObject jsonReqObj = new JSONObject();
			InputStream inputStream = null;
			DataOutputStream out = null;
			HttpURLConnection connection = null;
			RuleTO finalTO = new RuleTO();

			String timestamp = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new java.util.Date());
			String valueTag = "4";

			try {
				jsonReqObj.put("featureId", "SCHEDULE_STATUS_UPDATE");
				jsonReqObj.put("appName", "CMS");
				jsonReqObj.put("username", "6D");
				jsonReqObj.put("password", "6D");
				jsonReqObj.put("reqTxnId", "100");
				jsonReqObj.put("msgOrigin", "ORI");
				jsonReqObj.put("msgDest", "DEST");
				jsonReqObj.put("timestamp", timestamp);

				JSONArray array = new JSONArray();

				JSONObject paramObj = new JSONObject();
				paramObj.put("name", "id");
				paramObj.put("value", ruleTO.getRuleId());
				array.put(paramObj);

				JSONObject paramObj2 = new JSONObject();
				paramObj2.put("name", "LOCK_UNLOCK");
				paramObj2.put("value", valueTag);
				array.put(paramObj2);

				jsonReqObj.put("dataSet", array);

				System.out.println("Approval BL_JSON_URL   ::" + BL_JSON_URL);
				URL url = new URL(BL_JSON_URL);
				connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("POST");

				connection.setConnectTimeout(15000);
				connection.setDoOutput(true);
				connection.setDoInput(true);
				connection.setInstanceFollowRedirects(false);

				connection.setRequestProperty("Content-Type", "application/json; utf-8");
				connection.setRequestProperty("charset", "utf-8");
				connection.setRequestProperty("Accept", "application/json");

				out = new DataOutputStream(connection.getOutputStream());
				out.writeBytes(jsonReqObj.toString());
				out.flush();
				out.close();

				inputStream = new BufferedInputStream(connection.getInputStream(), 32 * 1024);
				StringBuffer sb = new StringBuffer();
				int character = inputStream.read();

				while (character != -1) {
					sb.append((char) character);
					character = inputStream.read();
				}
				System.out.println("Request JSON :: " + jsonReqObj.toString());
				System.out.println("Response JSON" + sb.toString());
				if (sb.toString() != null && !sb.toString().equals("")) {
					JSONObject jsonRespObj = new JSONObject(sb.toString());
					String responseCode = (String) jsonRespObj.get("respCode");
					System.out.println("responseCode ::::" + responseCode);
					if (responseCode.equals("SC0000")) {
						String hql = "update ScheduleTO set approvalStatus = '" + ruleTO.getApprovalStatus()
								+ "', approverType = '" + ruleTO.getParentId() + "' where id = '" + ruleTO.getRuleId()
								+ "'";
						Query query = session.createQuery(hql);
						int result = query.executeUpdate();
						System.out.println("result  Value :: " + result);
						if (result > 0) {
							status = "0";
							String approveORReject = "Approved";
							saveApprovalAudit(ruleTO, approveORReject);
						}
					} else {
						status = "-1";
						System.out.println("Bl Exception  " + status);
					}

				}

			} catch (Exception e) {
				status = "1";
				finalTO.setStatus(status);
				System.out.println("Exception On BL  Side :::::");
				e.printStackTrace();
			} finally {
				try {
					if (inputStream != null) {
						inputStream.close();
						inputStream = null;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				try {
					if (out != null) {
						out.close();
						out = null;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				try {
					if (connection != null)
						connection.disconnect();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		// Rule Rejected
		if (ruleTO.getApprovalStatus() == 3) {
			String hql = "update ScheduleTO set approvalStatus = '" + ruleTO.getApprovalStatus() + "', approverType = '"
					+ ruleTO.getParentId() + "' where id = '" + ruleTO.getRuleId() + "'";
			Query query = session.createQuery(hql);
			int result = query.executeUpdate();
			System.out.println("result  Value :: " + result);
			if (result > 0) {
				status = "0";
				String approveORReject = "Rejected";
				saveApprovalAudit(ruleTO, approveORReject);
			}
		}
		return status;

	}

	private void saveApprovalAudit(RuleTO ruleTO, String approveORReject) {
		System.out.println("ruleTO  " + ruleTO.toString());
		System.out.println("approveORReject  " + approveORReject.toString());
		Session session = sessionFactory.getCurrentSession();
		if (ruleTO.getRuleId() != 0) {
			ApprovalAuditTO approvalTO = new ApprovalAuditTO();
			approvalTO.setRuleId(ruleTO.getRuleId());
			approvalTO.setOperator(ruleTO.getChannelTypeName());
			approvalTO.setDesignation(ruleTO.getDesignationName());
			approvalTO.setApprovalType(ruleTO.getApprovalType());
			approvalTO.setApprover(ruleTO.getUserId());
			approvalTO.setStatus(approveORReject);
			approvalTO.setRuleName(ruleTO.getRuleName());
			approvalTO.setCampaignName(ruleTO.getCampaignName());
			approvalTO.setUser(ruleTO.getUserName());
			System.out.println("approvalTO ::::::::::::  " + approvalTO);
			session.saveOrUpdate(approvalTO);

		}

	}

	@Override
	public List<RuleTO> getRulesForApproval(RuleTO ruleTO, String userId) {
		// TODO Auto-generated method stub
		List<RuleTO> rules = new ArrayList<RuleTO>();
		RuleTO mainRuleTO = null;
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = null;
		criteria = session.createCriteria(ScheduleTO.class);

		Disjunction or = Restrictions.disjunction();
		or.add(Restrictions.eq("approvalStatus", 1));
		or.add(Restrictions.eq("approvalStatus", 2));
		or.add(Restrictions.eq("approvalStatus", 3));
		criteria.add(Restrictions.eq("approverType", userId));
		criteria.addOrder(Order.desc("Id"));

		criteria.add(or);

		List<ScheduleTO> scheduleRulesList = criteria.list();
		if (!scheduleRulesList.isEmpty()) {

			LinkedHashMap<Integer, String> campaignMap = getCampaignsMap();
			LinkedHashMap<Integer, String> scheduleTypeMap = getScheduleTypesMap();

			for (ScheduleTO schTO : scheduleRulesList) {
				mainRuleTO = new RuleTO();
				mainRuleTO.setRuleId(schTO.getId());
				mainRuleTO.setRuleName(schTO.getRuleName());
				mainRuleTO.setCampaignName(campaignMap.get(schTO.getCampaignId()));
				mainRuleTO.setScheduleType(scheduleTypeMap.get(schTO.getTypeId()));
				mainRuleTO.setApprovalStatus(schTO.getApprovalStatus());
				mainRuleTO.setCreateDateUI(simpleDateFormat.format(schTO.getCreateDate()));
				rules.add(mainRuleTO);
			}

		}
		return rules;
	}

	@Override
	public RuleBuilderTO fetchRuleStatus(int ruleId) {
		System.out.println("ruleId dao::::" + ruleId);

		Boolean Status = false;
		String urlAppender = "";

		JSONObject jsonReqObj = new JSONObject();

		InputStream inputStream = null;
		DataOutputStream out = null;
		HttpURLConnection connection = null;
		RuleBuilderTO builderTO = new RuleBuilderTO();

		try {

			jsonReqObj.put("featureId", "GET_SCHEDULE_DETAILS");
			jsonReqObj.put("appName", "CMS");
			jsonReqObj.put("username", "6D");
			jsonReqObj.put("password", "6D");
			jsonReqObj.put("reqTxnId", "100");
			jsonReqObj.put("msgOrigin", "ORI");
			jsonReqObj.put("msgDest", "DEST");
			jsonReqObj.put("timestamp", System.currentTimeMillis());
			jsonReqObj.put("id", "");
			jsonReqObj.put("ruletype", "");

			JSONArray array = new JSONArray();

			JSONObject paramObj = new JSONObject();
			paramObj.put("name", "id");
			paramObj.put("value", String.valueOf(ruleId));
			array.put(paramObj);

			JSONObject paramNameObj = new JSONObject();
			paramNameObj.put("param", array);

			JSONObject dataObj = new JSONObject();
			dataObj.put("detail", paramNameObj);
			dataObj.put("rule_name", "");

			jsonReqObj.put("data", dataObj);

			System.out.println("JSON:::::" + jsonReqObj.toString());

			System.out.println("Post URL::" + BL_JSON_URL);
			System.out.println("Post URL::" + BL_JSON_URL);

			System.out.println("url appending fetch " + env.getProperty("getrulestatus.rule"));

			urlAppender = BL_JSON_URL + env.getProperty("getrulestatus.rule");
			System.out.println("urlAppender fetch ::   " + urlAppender);

			// URL url = new URL(BL_JSON_URL);
			URL url = new URL(urlAppender);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");

			connection.setConnectTimeout(15000);
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setInstanceFollowRedirects(false);

			connection.setRequestProperty("Content-Type", "application/json; utf-8");
			connection.setRequestProperty("charset", "utf-8");
			connection.setRequestProperty("Accept", "application/json");
			// connection.setRequestProperty("Content-Length", "" +
			// Integer.toString(playPauseJson.getBytes().length));

			out = new DataOutputStream(connection.getOutputStream());
			out.writeBytes(jsonReqObj.toString());
			out.flush();
			out.close();

			inputStream = new BufferedInputStream(connection.getInputStream(), 32 * 1024);
			StringBuffer sb = new StringBuffer();
			int character = inputStream.read();

			while (character != -1) {
				sb.append((char) character);
				character = inputStream.read();
			}
			System.out.println("sb.toString() ::::" + sb.toString());
			if (sb.toString() != null && !sb.toString().equals("")) {
				JSONObject jsonRespObj = new JSONObject(sb.toString());
				builderTO.setRuleJson(jsonRespObj.toString());
				String value = (String) jsonRespObj.get("respCode");
				System.out.println("RespCOde::::" + value);
				System.out.println("RespCOde::::" + jsonRespObj.toString());

				if (value.equalsIgnoreCase("SC0000")) {
					JSONObject dataRespObj = jsonRespObj.getJSONObject("data");

					JSONArray fieldDetailRespArray = dataRespObj.getJSONArray("detail");

					JSONObject paramRespObj = fieldDetailRespArray.getJSONObject(0);

					JSONArray paramRespArray = paramRespObj.getJSONArray("param");

					System.out.println("Multi::::::" + paramRespArray.toString());
					JSONObject innerObj = paramRespArray.getJSONObject(0);

					JSONArray multiparamArray = innerObj.getJSONArray("multiParam");

					System.out.println("multiparamArray:::" + multiparamArray.toString());

				}

				// System.out.println("final out put::::" + sb.toString());

			}

		} catch (Exception e) {
			System.out.println("Exception on Scheduled Rule Status Processing from BL side....");
			e.printStackTrace();
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
					inputStream = null;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				if (out != null) {
					out.close();
					out = null;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				if (connection != null)
					connection.disconnect();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return builderTO;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ActionFileTO> getFileDetails(String actionid, String ruleId) {
		System.out.println("actionid   :: " + actionid.toString());
		System.out.println("ruleId   :: " + ruleId.toString());
		List<ActionFileTO> fileTO = new ArrayList<ActionFileTO>();
		ActionFileTO detailsTO = null;
		Session session = sessionFactory.getCurrentSession();

		hql = "SELECT ST.id,ST.scheduledId,ST.actionFileName,SDD.ip,SDD.userName,SDD.password,RT.Id FROM ActionFileTO ST,UrlConfigTO SDD ,ScheduleTO RT"
				+ " where ST.scheduledId = RT.Id and ST.nodeName=SDD.nodeId  and ST.actionId = '" + actionid
				+ "' and ST.scheduledId = '" + ruleId + "'";
		List<Object[]> objects = session.createQuery(hql).list();
		for (Object[] obj : objects) {
			detailsTO = new ActionFileTO();
			detailsTO.setId(Integer.parseInt(obj[0] + ""));
			detailsTO.setScheduledId(Integer.parseInt(obj[1] + ""));
			detailsTO.setActionFileName(obj[2] + "");
			detailsTO.setIp(obj[3] + "");
			detailsTO.setUserName(obj[4] + "");
			detailsTO.setPassword(obj[5] + "");
			fileTO.add(detailsTO);
		}
		System.out.println("fileTO ::: " + fileTO);
		return fileTO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TokenMaster> getSessionName() {
		Session session = sessionFactory.getCurrentSession();
		List<TokenMaster> list = new ArrayList<TokenMaster>();
		TokenMaster to = null;
		List<Object[]> res = null;
		hql = " select tokenId, token, userId, sessionId,sessionName from TokenMaster order by id desc";
		res = session.createQuery(hql).list();
		for (Object[] obj : res) {
			to = new TokenMaster();
			to.setTokenId(Integer.parseInt(obj[0] + ""));
			to.setToken((obj[1] + ""));
			to.setUserId((obj[2] + ""));
			to.setSessionId((obj[3] + ""));
			to.setSessionName((obj[4] + ""));
			list.add(to);
		}

		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String remoteCopy(RemoteCopyUserTO copyTO) {
		String status = "1";
		System.out.println("copyTO dao   " + copyTO.toString());
		Session session = sessionFactory.getCurrentSession();
		SQLQuery sqlQuery = null;
		System.out.println("copyTO Id   " + copyTO.getId());
		if (copyTO.getCopyTokenId() != null && copyTO.getPasteTokenId() != null) {
//			String hql = "delete from RemoteCopyUserTO where copyTokenId =  " + copyTO.getCopyTokenId()
//					+ "and pasteTokenId=" + copyTO.getPasteTokenId();
//			session.createQuery(hql).executeUpdate();

			String query = "DELETE FROM REMOTE_COPY WHERE COPY_TOKEN_ID=:copyTokenId AND PASTE_TOKEN_ID=:pasteTokenId";
			sqlQuery = session.createSQLQuery(query);
			sqlQuery.setParameter("copyTokenId", copyTO.getCopyTokenId());
			sqlQuery.setParameter("pasteTokenId", copyTO.getPasteTokenId());
			int affectedRows = sqlQuery.executeUpdate();
//			if (affectedRows == 0) {
//				try {
//					throw new Exception("Creating failed, no rows affected.");
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}

		}
		session.saveOrUpdate(copyTO);
		status = "0";

		return status;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RemoteCopyUserTO> remotePaste(String token) {
		Session session = sessionFactory.getCurrentSession();
		List<RemoteCopyUserTO> list = new ArrayList<RemoteCopyUserTO>();
		RemoteCopyUserTO to = null;
		List<Object[]> res = null;
		hql = " select id, copyTokenId, pasteTokenId, copyTokenName,pasteTokenName,comment from RemoteCopyUserTO order by id asc";
		res = session.createQuery(hql).list();
		for (Object[] obj : res) {
			to = new RemoteCopyUserTO();
			to.setId(Integer.parseInt(obj[0] + ""));
			to.setCopyTokenId(obj[1] + "");
			to.setPasteTokenId(obj[2] + "");
			to.setCopyTokenName(obj[3] + "");
			to.setPasteTokenName(obj[4] + "");
			to.setComment(obj[5] + "");
			list.add(to);
		}

		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public RemoteCopyUserTO getPasteName(String token) {
		Criteria criteria = null;
		Session session = sessionFactory.getCurrentSession();
		System.out.println("token--------->" + token);
		criteria = session.createCriteria(RemoteCopyUserTO.class);
		// criteria.add(Restrictions.eq("token", token));
		criteria.add(Restrictions.eq("pasteTokenId", token));
		criteria.addOrder(Order.desc("id"));
		RemoteCopyUserTO masterTO = (RemoteCopyUserTO) criteria.uniqueResult();
		// System.out.println("masterTO " + masterTO.toString());
		return masterTO;
	}

	@Override
	public PaginationDTO<RuleTO> getRulesWithPagination(PaginationDTO<RuleTO> paginationTo) {
		// TODO Auto-generated method stub
		System.out.println("before Campaings Map---------->");
		// ScheduleTO scheduleTO = loadScheduleData(1);
		// System.out.println("Campaings Map---------->" + scheduleTO.getCampaignMap());
		List<RuleTO> mainList = new ArrayList<RuleTO>();
		RuleTO ruleTO = null;
		Criteria criteria = null;

		LinkedHashMap<Integer, String> campaignsMap = new LinkedHashMap<Integer, String>();
		try {
			Session session = sessionFactory.getCurrentSession();
			criteria = session.createCriteria(CampaignMasterTO.class);
			// criteria.add(Restrictions.eq("userId", 1));
			criteria.addOrder(Order.desc("id"));
			List<CampaignMasterTO> campaignsList = criteria.list();
			if (!campaignsList.isEmpty()) {
				for (CampaignMasterTO campaignMasterTO : campaignsList) {
					if (campaignMasterTO.getIsTemplate() != 1)
						campaignsMap.put(campaignMasterTO.getId(), campaignMasterTO.getCampaignName());
				}

			}

			if (paginationTo.getDataTotalSize() == 0) {
				paginationTo.setDataTotalSize(getRowCount(session, paginationTo));
			}

			LinkedHashMap<Integer, String> scheduleTypeMap = getScheduleTypesMap();
			if (paginationTo.getObj().getIsSavedORScheduledRule() == 1) {
				// -------------- Scheduled Rules-----------------//
				criteria = session.createCriteria(ScheduleTO.class);
				if (paginationTo.getObj().getUserId() != 1) {
					criteria.add(Restrictions.eq("userId", paginationTo.getObj().getUserId()));
				}
				if (validate(paginationTo.getSearchKey1())) {
					criteria.add(Restrictions.ilike("ruleName", "%" + paginationTo.getSearchKey1() + "%"));
				}
				// Sorting
				if (validate(paginationTo.getSortKey1())) {
					if (paginationTo.getSortKey1().split(" ")[1].equalsIgnoreCase("desc"))
						criteria.addOrder(Order.desc("ruleName"));
					else if (paginationTo.getSortKey1().split(" ")[1].equalsIgnoreCase("asc"))
						criteria.addOrder(Order.asc("ruleName"));
				} else {
					criteria.addOrder(Order.desc("id"));
				}
				criteria.setFirstResult(paginationTo.getFirstRecord());
				criteria.setMaxResults(paginationTo.getRecordCount());
				List<ScheduleTO> scheduledRules = criteria.list();
				for (ScheduleTO to : scheduledRules) {
					ruleTO = new RuleTO();
					int abTest = Integer.parseInt(to.getIsABTesting());
					System.out.println("AB Testing " + abTest);
					ruleTO.setRuleId(to.getId());
					ruleTO.setRuleName(to.getRuleName());
					ruleTO.setSavedORScheduledRule(1);
					ruleTO.setStatusID(to.getRuleStatus());
					ruleTO.setStatus(ruleStatusMap().get(to.getRuleStatus()));
					ruleTO.setCampaignId(to.getCampaignId());
					ruleTO.setScheduleType(scheduleTypeMap.get(to.getTypeId()));
					ruleTO.setScheduleTypeId(to.getTypeId());
					ruleTO.setUserId(to.getUserId());
					ruleTO.setUrlDetails("RE_BL");
					ruleTO.setCampaignName(to.getCampaignId() != 0 ? campaignsMap.get(to.getCampaignId()) : "");

					ruleTO.setCreateDateUI(simpleDateFormat.format(to.getCreateDate()));
					ruleTO.setIsABTesting(to.getIsABTesting());
					ruleTO.setRuleEngineUrl(to.getRuleEngineUrl());
					mainList.add(ruleTO);
				}
			} else if (paginationTo.getObj().getIsSavedORScheduledRule() == -1) {
				// -------------- Saved Rules-----------------//
				criteria = session.createCriteria(RuleBuilderTO.class);
				if (paginationTo.getObj().getUserId() != 1) {
					criteria.add(Restrictions.eq("userId", paginationTo.getObj().getUserId()));
				}
				if (validate(paginationTo.getSearchKey1())) {
					criteria.add(Restrictions.ilike("name", "%" + paginationTo.getSearchKey1() + "%"));
				}

				// Sorting
				if (validate(paginationTo.getSortKey1())) {
					if (paginationTo.getSortKey1().split(" ")[1].equalsIgnoreCase("desc"))
						criteria.addOrder(Order.desc("ruleName"));
					else if (paginationTo.getSortKey1().split(" ")[1].equalsIgnoreCase("asc"))
						criteria.addOrder(Order.asc("ruleName"));
				} else {
					criteria.addOrder(Order.desc("id"));
				}
				criteria.setFirstResult(paginationTo.getFirstRecord());
				criteria.setMaxResults(paginationTo.getRecordCount());

				List<RuleBuilderTO> savedRules = criteria.list();
				if (!savedRules.isEmpty()) {
					for (RuleBuilderTO to : savedRules) {
						ruleTO = new RuleTO();
						ruleTO.setRuleId(to.getId());
						ruleTO.setRuleName(to.getName());
						ruleTO.setSavedORScheduledRule(-1);
						ruleTO.setStatusID(-1);
						ruleTO.setUserId(to.getUserId());
						ruleTO.setStatus(ruleStatusMap().get(-1));
						ruleTO.setCampaignId(to.getCampaignId());
						ruleTO.setCampaignName(to.getCampaignId() != 0 ? campaignsMap.get(to.getCampaignId()) : "");
						ruleTO.setCreateDateUI(simpleDateFormat.format(to.getCreateDate()));
						mainList.add(ruleTO);
					}
				}
			}

			paginationTo.setData(mainList);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return paginationTo;
	}

	public int getRowCount(Session session, PaginationDTO<RuleTO> paginationTO) {
		System.out.println("Class : ConfigureUrlDAOImpl | Method : getUrlInfo");
		String sql = null;
		int rowCount = 0;
		// Criteria criteria = null;
		try {

			if (paginationTO.getObj().getIsSavedORScheduledRule() == 1) {
				sql = "SELECT COUNT(*)FROM ScheduleTO ";

				sql += " where 1=1 ";
				if (validate(paginationTO.getSearchKey1()))
					sql += " and  rulename LIKE '%" + paginationTO.getSearchKey1() + "%'";
				if (paginationTO.getObj().getUserId() != 1)
					sql += " and  userId =" + paginationTO.getObj().getUserId();

			} else if (paginationTO.getObj().getIsSavedORScheduledRule() == -1) {
				sql = "SELECT COUNT(*)FROM RuleBuilderTO ";

				sql += " where 1=1 ";
				if (validate(paginationTO.getSearchKey1()))
					sql += " and  name LIKE '%" + paginationTO.getSearchKey1() + "%'";
				if (paginationTO.getObj().getUserId() != 1)
					sql += " and  userId =" + paginationTO.getObj().getUserId();
			}
			System.out.println("hqllllllllll : " + sql);

			query = session.createQuery(sql);
			List<Long> count = query.list();
			rowCount = Integer.parseInt(count.get(0).toString());
		} catch (

		Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return rowCount;
	}

	boolean validate(String val) {
		if (val != null && !val.equalsIgnoreCase("Undefined") && !val.equalsIgnoreCase(""))
			return true;
		else
			return false;
	}
}
