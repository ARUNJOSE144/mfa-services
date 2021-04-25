package com.sixdee.magik.services.dao.impl;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.beans.factory.annotation.Value;

import java.text.ParseException;

import com.sixdee.magik.services.dao.AuditInfoDAO;
import com.sixdee.magik.services.dao.QuarantineDAO;
import com.sixdee.magik.services.dao.QuarantineRevampDAO;
import com.sixdee.magik.services.model.ARPUSegmentsTO;
import com.sixdee.magik.services.model.AuditInfoTO;
import com.sixdee.magik.services.model.BlackListDetailsTO;
import com.sixdee.magik.services.model.BlackListTO;
import com.sixdee.magik.services.model.CampaignMasterTO;
import com.sixdee.magik.services.model.DNDDetailsTO;
import com.sixdee.magik.services.model.DNDTO;
import com.sixdee.magik.services.model.GenericTO;
import com.sixdee.magik.services.model.GlobalFilterDetailsTO;
import com.sixdee.magik.services.model.GlobalFilterTO;
import com.sixdee.magik.services.model.QuarantineBLCommunicationTO;
import com.sixdee.magik.services.model.QuarantineBlackListTO;
import com.sixdee.magik.services.model.QuarantineDetailsTO;
import com.sixdee.magik.services.model.QuarantineSpecialDaysTO;
import com.sixdee.magik.services.model.QuarantineTO;
import com.sixdee.magik.services.model.QuarantineWeekDaysTO;
import com.sixdee.magik.services.model.SpecialDateDetailsTO;
import com.sixdee.magik.services.model.SpecialDateTO;
import com.sixdee.magik.services.model.WeekDayContentTO;
import com.sixdee.magik.services.model.WeekDayDetailsTO;
import com.sixdee.magik.services.model.WeekDayTO;
import com.sixdee.magik.services.model.WhiteListDetailsTO;
import com.sixdee.magik.services.model.WhiteListTO;
import com.sixdee.magik.services.util.DateFormat;
import com.sixdee.magik.services.util.QuarantineBLCommunication;
import com.sixdee.magik.services.util.QuarantineBlCommunicationJSON;

/**
 * @author Nakhil Kurian
 * @Date : August 2020
 *
 */

@Repository
public class QuarantineRevampDaoImpl implements QuarantineRevampDAO {
	Logger logger = Logger.getLogger(QuarantineRevampDaoImpl.class);
	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;
	@SuppressWarnings("unused")
	private Query query = null;
	@Autowired
	AuditInfoDAO auditInfo;
	AuditInfoTO audtoTO = null;

	private Session session = null;
	private String hql = null;

	private String dateValue = null;
	private String fileValue = null;
	private String dateconvert = null;
	private String specMarketingID = null;
	private String blackMarketingID = null;
	private String weekMarketingID = null;
	private String dndarketingID = null;
	@Value("${BL_XML_URL}")
	String BL_XML_URL;
	@Value("${BL_JSON_URL}")
	String BL_JSON_URL;
	@Value("${quarantine.policy}")
	String policyUrl;

	QuarantineBLCommunication quarBLComm = new QuarantineBLCommunication();
	QuarantineBlCommunicationJSON quarantineBlJSON = new QuarantineBlCommunicationJSON();

	@Override
	public GenericTO saveSpecialDates(SpecialDateTO specialDateTO) {

		logger.info("Class : QuarantineRevamp IMPL  | Method : SaveSpecialDate");

		GenericTO genericTO = new GenericTO();

		System.out.println("saveSpecialDates DaoImpl   ::");
		session = sessionFactory.getCurrentSession();
		specMarketingID = specialDateTO.getMarketingPlanId();
		System.out.println("MarketingPlanId :: " + specMarketingID);
		session.saveOrUpdate(specialDateTO);
		System.out.println("specialDateTO id :::: " + specialDateTO.getId());

		if (specialDateTO.getId() != 0) {
			String hql = "delete from SpecialDateDetailsTO where Detailsid = " + specialDateTO.getDetailsId();
			session.createQuery(hql).executeUpdate();
		}

		for (SpecialDateDetailsTO daysDetails : specialDateTO.getSpecialDaysDetails()) {
			daysDetails.setSpecialPolicyId(specialDateTO.getId());
			session.saveOrUpdate(daysDetails);
		}

		genericTO.setRespMessage("Special Date Saved  Successfully.");

		dateValue = specialDateTO.getSpecialDate();
		SimpleDateFormat formatter;
		if (specialDateTO.getIsFromFile().equals("true")) {
			formatter = new SimpleDateFormat("yyyy-MM-dd");

		} else {
			formatter = new SimpleDateFormat("dd-MM-yyyy");
		}
		SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
		dateValue = specialDateTO.getSpecialDate();
		String specialDates = "";
		if (specialDateTO.getFileName() != null && !specialDateTO.getFileName().equals("")) {
			if (specialDateTO.getDesclist() != null && specialDateTO.getDesclist().size() > 0) {
				for (SpecialDateTO innerDto : specialDateTO.getDesclist()) {
					Date date = null;
					try {
						date = formatter.parse(innerDto.getSpecialDate());
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					dateconvert = innerDto.getSpecialDate();
					String strDate = formatter2.format(date);
					if (specialDates == "") {
						specialDates = strDate;
					} else {
						specialDates += "," + strDate;
						fileValue = specialDates;
					}
				}
			}
		}

		QuarantineBLCommunicationTO blComnTo = new QuarantineBLCommunicationTO();
		blComnTo.setPolicyId(specialDateTO.getId());
		blComnTo.setPolicyName(specialDateTO.getPolicyName());
		blComnTo.setPolicyType("GLOBAL_SPECIAL_DATE_CONTROL");
		// blComnTo.setUrlValue(BL_XML_URL);
		blComnTo.setUrlValue(BL_JSON_URL);
		blComnTo.setUrlAppender(policyUrl);
		blComnTo.setTypeChecker("SPECIALDATE");
		// System.out.println("BL_XML_URL :: " + BL_XML_URL);
		if (specialDateTO.getFileName() != null && !specialDateTO.getFileName().equals("")) {
			blComnTo.setStatusValueDate("1");
			blComnTo.setSpecialDates(fileValue);
			blComnTo.setMarketingPlanIds(specMarketingID);
			// System.out.println("fileValue :: " + fileValue);

		} else {
			blComnTo.setStatusValueDate("0");
			dateValue = specialDateTO.getSpecialDate();
			blComnTo.setSpecialDates(dateValue);
			blComnTo.setMarketingPlanIds(specMarketingID);
		}
		// String status = quarBLComm.reqXMLToBL(blComnTo);
		String status = quarantineBlJSON.blJson(blComnTo);
		System.out.println("Status from RE_BL :::   " + status);
		if (status != null && status.equalsIgnoreCase("SC0000")) {
			genericTO.setRespCode("0");
		} else {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			genericTO.setRespCode("1");
		}
		return genericTO;
	}

	@SuppressWarnings({ "unchecked", "unused" })
	@Override
	public List<SpecialDateTO> getSpecialDatesList() {
		System.out.println("getSpecialDatesList :: ");
		logger.info("Class : QuarantineRevamp | Method : getSpecialDatesList");
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		List<SpecialDateTO> specialDaysTOs = new ArrayList<SpecialDateTO>();
		SpecialDateTO daysTO = null;
		session = sessionFactory.getCurrentSession();

		hql = "SELECT ST.id,ST.policyName,SDD.specialDate,SDD.description,SDD.specialDateStartTime,SDD.specialDateEndTime,ST.createDate,ST.ModifiedDate ,SDD.marketingPlanId FROM SpecialDateTO ST,SpecialDateDetailsTO SDD"
				+ " where ST.id = SDD.specialPolicyId  order by ST.id desc";
		List<Object[]> objects = session.createQuery(hql).list();
		for (Object[] obj : objects) {
			daysTO = new SpecialDateTO();
			daysTO.setId(Integer.parseInt(obj[0] + ""));
			daysTO.setPolicyName(obj[1] + "");
			daysTO.setSpecialDate(obj[2] + "");
			daysTO.setDescription(obj[3] + "");
			daysTO.setSpecialDateStartTime(obj[4] + "");
			daysTO.setSpecialDateEndTime(obj[5] + "");
			daysTO.setCreateDate(obj[6] + "");
			daysTO.setModifiedDate(obj[7] + "");
			String mkId = obj[8].toString();
			System.out.println("id value" + mkId);
			if (mkId != null && !mkId.trim().equalsIgnoreCase("")) {
				daysTO.setMarketingPlanName(getCampaignNamesFromId(mkId));
			} else {
				daysTO.setMarketingPlanName("");
			}

			daysTO.setMarketingPlanId(obj[8] + "");
			specialDaysTOs.add(daysTO);
		}
		System.out.println("specialDaysTO ::: " + specialDaysTOs);
		return specialDaysTOs;
	}

	@Override
	public GenericTO deleteSpecialDate(SpecialDateTO specialDaysTO) {
		logger.info("Class : QuarantineRevampDaoImpl | Method : deleteSpecialDate");
		System.out.println("deleteSpecialDate :::");
		session = sessionFactory.getCurrentSession();
		GenericTO genericTO = new GenericTO();
		String hql = "delete from SpecialDateDetailsTO where specialPolicyId = " + specialDaysTO.getId();
		session.createQuery(hql).executeUpdate();
		String hql1 = "delete from SpecialDateTO where id = " + specialDaysTO.getId();
		session.createQuery(hql1).executeUpdate();
		genericTO.setRespMessage("Special Date Deleted Successfully.");
		return genericTO;
	}

	@Override
	public GenericTO saveWeekDay(WeekDayTO weekDayTO) {
		System.out.println("saveWeekDay :;  " + weekDayTO);
		session = sessionFactory.getCurrentSession();
		weekDayTO.setPolicyName(weekDayTO.getPolicyName());
		session.saveOrUpdate(weekDayTO);
		WeekDayDetailsTO detailsTO = null;
		GenericTO genericTO = new GenericTO();
		hql = "delete from WeekDayDetailsTO where weekPolicyId=" + weekDayTO.getId();
		session.createQuery(hql).executeUpdate();

		if (weekDayTO.getMarketingPlanId() != null) {
			String marketingPlans[] = weekDayTO.getMarketingPlanId().split(",");
			for (int i = 0; i < marketingPlans.length; i++) {
				detailsTO = new WeekDayDetailsTO();
				detailsTO.setWeekPolicyId(weekDayTO.getId());
				detailsTO.setDay(weekDayTO.getDay());
				detailsTO.setWeekDayStartTime(weekDayTO.getWeekDayStartTime());
				detailsTO.setWeekDayEndTime(weekDayTO.getWeekDayEndTime());
				detailsTO.setWeekDaysIds(weekDayTO.getWeekDaysIds());
				detailsTO.setDescription(weekDayTO.getDescription());
				detailsTO.setMarketingPlanId(marketingPlans[i]);
				session.save(detailsTO);
			}
		}
		weekMarketingID = weekDayTO.getMarketingPlanId();
		System.out.println("weekMarketingID  :: " + weekMarketingID);
		QuarantineBLCommunicationTO blComnTo = new QuarantineBLCommunicationTO();
		blComnTo.setPolicyId(weekDayTO.getId());
		blComnTo.setPolicyName(weekDayTO.getPolicyName());
		blComnTo.setPolicyType("GLOBAL_DAY_CONTROL");
		blComnTo.setMarketingPlanIds(weekMarketingID);
		// blComnTo.setUrlValue(BL_XML_URL);
		blComnTo.setUrlValue(BL_JSON_URL);
		blComnTo.setUrlAppender(policyUrl);
		blComnTo.setTypeChecker("WEEKDAY");
		// String status = quarBLComm.reqXMLToBL(configurationTO);
		// System.out.println("day id " + weekDayTO.getDay());
		String weekDayNames = "";
		if (weekDayTO.getDay() != null && !weekDayTO.getDay().trim().equalsIgnoreCase("")) {

			String weekDayArray[] = weekDayTO.getDay().split(",");
			for (String weekDay : weekDayArray) {
				if (weekDayNames == "")
					weekDayNames = getWeekDaysMap().get(Integer.parseInt(weekDay));
				else
					weekDayNames += "," + getWeekDaysMap().get(Integer.parseInt(weekDay));
			}
		}
		System.out.println("weekDayNames " + weekDayNames);
		blComnTo.setWeekDays(weekDayNames);
		// String status = quarBLComm.reqXMLToBL(blComnTo);
		String status = quarantineBlJSON.blJson(blComnTo);
		System.out.println("Status from RE_BL :::   " + status);

		if (status != null && status.equalsIgnoreCase("SC0000")) {
			genericTO.setRespCode("0");
		} else {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			genericTO.setRespCode("1");
		}

		return genericTO;
	}

	@SuppressWarnings("unchecked")
	public LinkedHashMap<Integer, String> getWeekDaysMap() {
		LinkedHashMap<Integer, String> weekDayMap = new LinkedHashMap<Integer, String>();
		session = sessionFactory.getCurrentSession();
		String hql = "from WeekDayContentTO";
		List<WeekDayContentTO> list = session.createQuery(hql).list();
		for (WeekDayContentTO to : list)
			weekDayMap.put(to.getId(), to.getName());
		return weekDayMap;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WeekDayTO> getWeekDayList() {
		System.out.println("getWeekDayList :: ");
		logger.info("Class : QuarantineRevamp | Method : getWeekDayList");
		List<WeekDayTO> weekDayTo = new ArrayList<WeekDayTO>();
		WeekDayTO daysTO = null;
		session = sessionFactory.getCurrentSession();

		hql = "SELECT ST.id,ST.policyName,SDD.description,SDD.day,SDD.weekDaysIds,SDD.weekDayStartTime,SDD.weekDayEndTime,ST.createDate,ST.modifiedDate  ,SDD.marketingPlanId FROM WeekDayTO ST,WeekDayDetailsTO SDD"
				+ " where ST.id = SDD.weekPolicyId  order by ST.id desc";
		List<Object[]> objects = session.createQuery(hql).list();
		for (Object[] obj : objects) {
			daysTO = new WeekDayTO();
			daysTO.setId(Integer.parseInt(obj[0] + ""));
			daysTO.setPolicyName(obj[1] + "");
			daysTO.setDescription(obj[2] + "");
			daysTO.setDay(obj[3] + "");
			daysTO.setWeekDaysIds(obj[4] + "");
			daysTO.setWeekDayStartTime(obj[5] + "");
			daysTO.setWeekDayEndTime(obj[6] + "");
			// daysTO.setCreateDate(obj[7] + "");
			// daysTO.setCreateDate(obj[8] + "");

			String mkId = obj[9].toString();
			System.out.println("id value" + mkId);
			if (mkId != null && !mkId.trim().equalsIgnoreCase("")) {
				daysTO.setMarketingPlanName(getCampaignNamesFromId(mkId));
			} else {
				daysTO.setMarketingPlanName("");
			}

			daysTO.setMarketingPlanId(obj[9] + "");

			weekDayTo.add(daysTO);
		}
		System.out.println("weekDayTo ::: " + weekDayTo);
		return weekDayTo;
	}

	@Override
	public GenericTO deleteWeekDay(WeekDayTO weekDayTo) {
		logger.info("Class : QuarantineRevampDaoImpl | Method : deleteWeekDay");
		System.out.println("deleteWeekDay :::");
		session = sessionFactory.getCurrentSession();
		GenericTO genericTO = new GenericTO();
		String hql = "delete from WeekDayDetailsTO where weekPolicyId = " + weekDayTo.getId();
		session.createQuery(hql).executeUpdate();
		String hql1 = "delete from WeekDayTO where id = " + weekDayTo.getId();
		session.createQuery(hql1).executeUpdate();
		genericTO.setRespMessage("WeekDay Deleted Successfully.");
		return genericTO;
	}

	@Override
	public GenericTO saveBlackList(BlackListTO blackListTO) {
		logger.info("Class : QuarantineDaoImpl | Method : saveBlackList");
		System.out.println("saveBlackList Dao Impl :: " + blackListTO);
		blackMarketingID = blackListTO.getMarketingPlanId();
		System.out.println("blackMarketingID :: " + blackMarketingID);
		session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(blackListTO);
		System.out.println("getId ::" + blackListTO.getId());
		System.out.println("inside save---------------------");
		if (blackListTO.getId() != 0) {
			String hql = "delete from BlackListDetailsTO where blackPolicyId = " + blackListTO.getId();
			session.createQuery(hql).executeUpdate();
		}
		for (BlackListDetailsTO blackListDetailsTO : blackListTO.getDetailsTo()) {
			blackListDetailsTO.setBlackPolicyId(blackListTO.getId());
			session.saveOrUpdate(blackListDetailsTO);
		}
		GenericTO genericTO = new GenericTO();
		genericTO.setRespMessage("Black List saved  successfully.");

		QuarantineBLCommunicationTO configurationTO = new QuarantineBLCommunicationTO();
		configurationTO.setPolicyId(blackListTO.getId());
		configurationTO.setPolicyName(blackListTO.getPolicyName());
		configurationTO.setPolicyType("GLOBAL_BLACKLIST");
		configurationTO.setMarketingPlanIds(blackMarketingID);
		configurationTO.setUrlValue(BL_JSON_URL);
		configurationTO.setUrlAppender(policyUrl);
		configurationTO.setTypeChecker("BLACKLIST");
		// String status = quarBLComm.reqXMLToBL(configurationTO);

		String status = quarantineBlJSON.blJson(configurationTO);
		if (status != null && status.equalsIgnoreCase("SC0000")) {
			genericTO.setRespCode("0");
		} else {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			genericTO.setRespCode("1");
		}

		return genericTO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BlackListTO> getBlackList() {
		System.out.println("getBlackList :: ");
		logger.info("Class : QuarantineRevamp | Method : getBlackList");
		List<BlackListTO> blackLsitTo = new ArrayList<BlackListTO>();
		BlackListTO daysTO = null;
		session = sessionFactory.getCurrentSession();

		hql = "SELECT ST.id,ST.policyName,SDD.description,SDD.msisdn,SDD.blackPolicyId,ST.createDate,ST.modifiedDate, SDD.marketingPlanId FROM BlackListTO ST,BlackListDetailsTO SDD"
				+ " where ST.id = SDD.blackPolicyId and SDD.blackPolicyId !=1   order by ST.id desc";
		List<Object[]> objects = session.createQuery(hql).list();
		for (Object[] obj : objects) {
			daysTO = new BlackListTO();
			daysTO.setId(Integer.parseInt(obj[0] + ""));
			daysTO.setPolicyName(obj[1] + "");
			daysTO.setDescription(obj[2] + "");
			daysTO.setMsisdn(obj[3] + "");
			// daysTO.setCreateDate(obj[7] + "");
			// daysTO.setCreateDate(obj[8] + "");
			String mkId = obj[7].toString();
			System.out.println("id value" + mkId);
			if (mkId != null && !mkId.trim().equalsIgnoreCase("")) {
				daysTO.setMarketingPlanName(getCampaignNamesFromId(mkId));
			} else {
				daysTO.setMarketingPlanName("");
			}

			daysTO.setMarketingPlanId(obj[7] + "");

			blackLsitTo.add(daysTO);
		}
		System.out.println("blackLsitTo ::: " + blackLsitTo);
		return blackLsitTo;
	}

	@Override
	public GenericTO deleteBlackLsit(BlackListTO blackListTo) {
		logger.info("Class : QuarantineRevampDaoImpl | Method : deleteBlackLsit");
		System.out.println("deleteBlackLsit :::");
		session = sessionFactory.getCurrentSession();
		GenericTO genericTO = new GenericTO();
		String hql = "delete from BlackListDetailsTO where blackPolicyId = " + blackListTo.getId();
		session.createQuery(hql).executeUpdate();
		String hql1 = "delete from BlackListTO where id = " + blackListTo.getId();
		session.createQuery(hql1).executeUpdate();
		genericTO.setRespMessage("BlackLsit Deleted Successfully.");
		return genericTO;
	}

	@Override
	public GenericTO saveDND(DNDTO dndTo) {
		System.out.println("saveDND :;  " + dndTo);
		session = sessionFactory.getCurrentSession();
		dndTo.setPolicyName(dndTo.getPolicyName());
		session.saveOrUpdate(dndTo);
		DNDDetailsTO detailsTO = null;
		GenericTO genericTO = new GenericTO();
		hql = "delete from DNDDetailsTO where dndPolicyId=" + dndTo.getId();
		session.createQuery(hql).executeUpdate();
		System.out.println("dndTo.getMarketingPlan()  :: " + dndTo.getMarketingPlanId());

		if (dndTo.getMarketingPlanId() != null) {
			String marketingPlans[] = dndTo.getMarketingPlanId().split(",");
			for (int i = 0; i < marketingPlans.length; i++) {
				detailsTO = new DNDDetailsTO();
				detailsTO.setDndPolicyId(dndTo.getId());
				detailsTO.setDescription(dndTo.getDescription());
				detailsTO.setStartTime(dndTo.getStartTime());
				detailsTO.setEndTime(dndTo.getEndTime());
				System.out.println("dnd id  " + detailsTO.getMarketingPlanId());
				detailsTO.setMarketingPlanId((Integer.parseInt(marketingPlans[i])));

				session.save(detailsTO);
			}
		}
		dndarketingID = dndTo.getMarketingPlanId();
		System.out.println("dndarketingID  :: " + dndarketingID);
		QuarantineBLCommunicationTO configurationTO = new QuarantineBLCommunicationTO();
		configurationTO.setPolicyId(dndTo.getId());
		configurationTO.setPolicyName(dndTo.getPolicyName());
		configurationTO.setPolicyType("GLOBAL_DND");
		configurationTO.setDndStartTime(dndTo.getStartTime());
		configurationTO.setDndEndTime(dndTo.getEndTime());
		configurationTO.setMarketingPlanIds(dndarketingID);
		// configurationTO.setUrlValue(BL_XML_URL);
		configurationTO.setUrlValue(BL_JSON_URL);
		configurationTO.setUrlAppender(policyUrl);
		configurationTO.setTypeChecker("DND");
		// String status = quarBLComm.reqXMLToBL(configurationTO);
		String status = quarantineBlJSON.blJson(configurationTO);
		System.out.println("Status from RE_BL :::   " + status);

		if (status != null && status.equalsIgnoreCase("SC0000")) {
			genericTO.setRespCode("0");
		} else {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			genericTO.setRespCode("1");
		}

		return genericTO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DNDTO> getDND() {
		System.out.println("getDND :: ");
		logger.info("Class : QuarantineRevamp | Method : getDND");
		List<DNDTO> dndLsitTo = new ArrayList<DNDTO>();
		DNDTO daysTO = null;
		session = sessionFactory.getCurrentSession();

		hql = "SELECT ST.id,ST.policyName,SDD.description,SDD.startTime,SDD.endTime,ST.createDate,ST.modifiedDate ,SDD.marketingPlanId FROM DNDTO ST,DNDDetailsTO SDD"
				+ " where ST.id = SDD.dndPolicyId order by ST.id desc ";
		List<Object[]> objects = session.createQuery(hql).list();
		for (Object[] obj : objects) {
			daysTO = new DNDTO();
			daysTO.setId(Integer.parseInt(obj[0] + ""));
			daysTO.setPolicyName(obj[1] + "");
			daysTO.setDescription(obj[2] + "");
			daysTO.setStartTime(obj[3] + "");
			daysTO.setEndTime(obj[4] + "");
			// daysTO.setCreateDate(obj[7] + "");
			// daysTO.setCreateDate(obj[8] + "");

			String mkId = obj[7].toString();
			System.out.println("id value" + mkId);
			if (mkId != null && !mkId.trim().equalsIgnoreCase("")) {
				daysTO.setMarketingPlanName(getCampaignNamesFromId(mkId));
			} else {
				daysTO.setMarketingPlanName("");
			}

			daysTO.setMarketingPlanId(obj[7] + "");

			dndLsitTo.add(daysTO);
		}
		System.out.println("dndLsitTo ::: " + dndLsitTo);
		return dndLsitTo;
	}

	@Override
	public GenericTO deleteDND(DNDTO dndTO) {
		logger.info("Class : QuarantineRevampDaoImpl | Method : deleteDND");
		System.out.println("deleteDND :::");
		session = sessionFactory.getCurrentSession();
		GenericTO genericTO = new GenericTO();
		String hql = "delete from DNDDetailsTO where dndPolicyId = " + dndTO.getId();
		session.createQuery(hql).executeUpdate();
		String hql1 = "delete from DNDTO where id = " + dndTO.getId();
		session.createQuery(hql1).executeUpdate();
		genericTO.setRespMessage("DND Deleted Successfully.");
		return genericTO;
	}

	@Override
	public GenericTO savewhiteList(WhiteListTO whitelistTO) {
		logger.info("Class : QuarantineDaoImpl | Method : savewhiteList");
		System.out.println("savewhiteList Dao Impl :: " + whitelistTO);
		session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(whitelistTO);
		System.out.println("getId ::" + whitelistTO.getId());
		System.out.println("getId ::" + whitelistTO.getPolicyId());
		System.out.println("inside save---------------------");
		if (whitelistTO.getId() != 0) {
			String hql = "delete from WhiteListDetailsTO where detailsId = " + whitelistTO.getPolicyId();
			session.createQuery(hql).executeUpdate();
		}
		for (WhiteListDetailsTO blackListDetailsTO : whitelistTO.getDetailsTo()) {
			blackListDetailsTO.setWhitePolicyId(whitelistTO.getId());
			session.saveOrUpdate(blackListDetailsTO);
		}
		GenericTO genericTO = new GenericTO();
		genericTO.setRespMessage("WhiteList saved  successfully.");

		return genericTO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WhiteListTO> getWhiteList() {
		System.out.println("getWhiteList :: ");
		logger.info("Class : QuarantineRevamp | Method : getWhiteList");
		List<WhiteListTO> dndLsitTo = new ArrayList<WhiteListTO>();
		WhiteListTO daysTO = null;
		session = sessionFactory.getCurrentSession();

		hql = "SELECT ST.id,ST.policyName,SDD.detailsId,SDD.description,SDD.msisdn,SDD.expiryDate,ST.createDate,ST.modifiedDate FROM WhiteListTO ST,WhiteListDetailsTO SDD"
				+ " where ST.id = SDD.whitePolicyId order by ST.id desc ";
		List<Object[]> objects = session.createQuery(hql).list();
		for (Object[] obj : objects) {
			daysTO = new WhiteListTO();
			daysTO.setId(Integer.parseInt(obj[0] + ""));
			daysTO.setPolicyName(obj[1] + "");
			daysTO.setPolicyId(obj[2] + "");
			daysTO.setDescription(obj[3] + "");
			daysTO.setMsisdn(obj[4] + "");
			daysTO.setExpiryDate(obj[5] + "");
			// daysTO.setCreateDate(obj[7] + "");
			// daysTO.setCreateDate(obj[8] + "");
			dndLsitTo.add(daysTO);
		}
		System.out.println("dndLsitTo ::: " + dndLsitTo);
		return dndLsitTo;
	}

	@Override
	public GenericTO deleteWhiteList(WhiteListTO whitelistTO) {
		logger.info("Class : QuarantineRevampDaoImpl | Method : deleteWhiteList");
		System.out.println("deleteWhiteList :::");
		session = sessionFactory.getCurrentSession();
		GenericTO genericTO = new GenericTO();
		String hql = "delete from WhiteListDetailsTO where whitePolicyId = " + whitelistTO.getId();
		session.createQuery(hql).executeUpdate();
		String hql1 = "delete from WhiteListTO where id = " + whitelistTO.getId();
		session.createQuery(hql1).executeUpdate();
		genericTO.setRespMessage("WhiteList Deleted Successfully.");
		return genericTO;
	}

	@Override
	public GenericTO saveGlobalFilter(GlobalFilterTO globalFilterTo) {
		System.out.println("saveGlobalFilter :;  " + globalFilterTo);
		session = sessionFactory.getCurrentSession();
		globalFilterTo.setPolicyName(globalFilterTo.getPolicyName());
		session.saveOrUpdate(globalFilterTo);
		GlobalFilterDetailsTO detailsTO = null;
		GenericTO genericTO = new GenericTO();
		hql = "delete from GlobalFilterDetailsTO where filterPolicyId=" + globalFilterTo.getId();
		session.createQuery(hql).executeUpdate();
		detailsTO = new GlobalFilterDetailsTO();
		detailsTO.setFilterPolicyId(globalFilterTo.getId());
		detailsTO.setDescription(globalFilterTo.getDescription());
		detailsTO.setAccountType(globalFilterTo.getAccountType());
		detailsTO.setTariffPlan(globalFilterTo.getTariffPlan());
		detailsTO.setExclusionTyp(globalFilterTo.getExclusionTyp());
		session.save(detailsTO);
		return genericTO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GlobalFilterTO> getGlobalFilter() {
		System.out.println("getGlobalFilter :: ");
		logger.info("Class : QuarantineRevamp | Method : getDND");
		List<GlobalFilterTO> dndLsitTo = new ArrayList<GlobalFilterTO>();
		GlobalFilterTO daysTO = null;
		session = sessionFactory.getCurrentSession();

		hql = "SELECT ST.id,ST.policyName,SDD.description,SDD.accountType,SDD.tariffPlan,SDD.exclusionTyp,ST.createDate,ST.modifiedDate FROM GlobalFilterTO ST,GlobalFilterDetailsTO SDD"
				+ " where ST.id = SDD.filterPolicyId order by ST.id desc ";
		List<Object[]> objects = session.createQuery(hql).list();
		for (Object[] obj : objects) {
			daysTO = new GlobalFilterTO();
			daysTO.setId(Integer.parseInt(obj[0] + ""));
			daysTO.setPolicyName(obj[1] + "");
			daysTO.setDescription(obj[2] + "");
			daysTO.setAccountType(obj[3] + "");
			daysTO.setTariffPlan(obj[4] + "");
			daysTO.setExclusionTyp(obj[5] + "");
			// daysTO.setCreateDate(obj[7] + "");
			// daysTO.setCreateDate(obj[8] + "");
			dndLsitTo.add(daysTO);
		}
		System.out.println("dndLsitTo ::: " + dndLsitTo);
		return dndLsitTo;
	}

	@Override
	public GenericTO deleteGlobalFilter(GlobalFilterTO globalFilterTO) {
		logger.info("Class : QuarantineRevampDaoImpl | Method : deleteGlobalFilter");
		System.out.println("deleteGlobalFilter :::");
		session = sessionFactory.getCurrentSession();
		GenericTO genericTO = new GenericTO();
		String hql = "delete from GlobalFilterDetailsTO where filterPolicyId = " + globalFilterTO.getId();
		session.createQuery(hql).executeUpdate();
		String hql1 = "delete from GlobalFilterTO where id = " + globalFilterTO.getId();
		session.createQuery(hql1).executeUpdate();
		genericTO.setRespMessage("globalFilterTO Deleted Successfully.");
		return genericTO;
	}

	public LinkedHashMap<String, String> getCampaignsMap() {
		LinkedHashMap<String, String> campaignTypeMap = new LinkedHashMap<String, String>();
		Session session = sessionFactory.getCurrentSession();

		@SuppressWarnings({ "unchecked", "deprecation" })
		List<CampaignMasterTO> list = (List<CampaignMasterTO>) session.createCriteria(CampaignMasterTO.class).list();
		campaignTypeMap.put("-1", "All");
		for (CampaignMasterTO to : list) {
			campaignTypeMap.put(to.getId() + "", to.getCampaignName());
		}

		return campaignTypeMap;
	}

	public String getCampaignNamesFromId(String campaingIds) {
		String campaignNames = "";
		if (campaingIds != null) {
			String[] campIdsArray = campaingIds.split(",");
			if (campIdsArray != null) {
				for (String campId : campIdsArray) {
					if (getCampaignsMap().keySet().contains(campId)) {
						if (campaignNames == "") {
							campaignNames = getCampaignsMap().get(campId);
						} else {
							campaignNames += "," + getCampaignsMap().get(campId);
						}
					}

				}
			}
		}
		return campaignNames;

	}

}
