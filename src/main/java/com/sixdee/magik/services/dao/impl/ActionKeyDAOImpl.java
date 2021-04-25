package com.sixdee.magik.services.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sixdee.magik.services.dao.ActionKeyDAO;
import com.sixdee.magik.services.dao.AuditInfoDAO;
import com.sixdee.magik.services.model.ActionControlGroupTO;
import com.sixdee.magik.services.model.ActionPromotionDetailsTO;
import com.sixdee.magik.services.model.AuditInfoTO;
import com.sixdee.magik.services.model.ActionKeyBundleTariffRateTO;
import com.sixdee.magik.services.model.ActionKeyBundleTypeTO;
import com.sixdee.magik.services.model.ActionKeyCampaignChannelTO;
import com.sixdee.magik.services.model.ActionKeyCampaignTypeTO;
import com.sixdee.magik.services.model.ActionKeyCreditTypeTO;
import com.sixdee.magik.services.model.CampaignChannelTO;
import com.sixdee.magik.services.model.CampaignTypeTO;
import com.sixdee.magik.services.model.DNDTO;
import com.sixdee.magik.services.model.IsPromotionDetailsTO;
import com.sixdee.magik.services.model.TC_CG_ResponseTO;
import com.sixdee.magik.services.model.UserSessionTO;

/**
 * @author Nakhil Kurian
 * @category January 2021
 * 
 */

@Repository
@Transactional
public class ActionKeyDAOImpl implements ActionKeyDAO {

	static Logger logger = Logger.getLogger(ActionKeyDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;
	private Session session = null;
	private String query = null;
	@Autowired
	AuditInfoDAO auditInfo;
	AuditInfoTO audtoTO = null;
	int idValue;
	String nameValue;
	String startdateValue;
	String enddateValue;
	String typeValue;
	String noooftimes;
	String nameValues;
	String pattern = "yyyy-MM-dd";
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

	@SuppressWarnings("unchecked")
	@Override
	public List<ActionControlGroupTO> getActionKeyDetails(Integer parentId, UserSessionTO user) {
		logger.info("Class : ActionKeyDAOImpl | Method : getActionKeyDetails");
		List<ActionControlGroupTO> actionKeyList = new ArrayList<ActionControlGroupTO>();

		session = sessionFactory.getCurrentSession();
		query = "FROM ActionControlGroupTO WHERE parentId=" + parentId + " order by ID desc";
		if (user.getUserId() != 1)
			query += " and userId=" + user.getUserId();
		System.out.println("SQL Query : " + query);
		actionKeyList = (List<ActionControlGroupTO>) session.createQuery(query).list();
		SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd");

		for (ActionControlGroupTO actionControlGroupTO : actionKeyList) {
			if (actionControlGroupTO.getStartDate() != null) {
				actionControlGroupTO.setStartDateUi(sf1.format(actionControlGroupTO.getStartDate()));
			}
			if (actionControlGroupTO.getEndDate() != null) {
				actionControlGroupTO.setEndDateUI(sf1.format(actionControlGroupTO.getEndDate()));
			}

		}

		return actionKeyList;
	}

	@Override
	public void updateActionKeyDetails(ActionControlGroupTO actionControlGroupTO) {
		logger.info("Class : ActionKeyDAOImpl | Method : deleteActionKey");

		session = sessionFactory.getCurrentSession();
		System.out.println("id : " + actionControlGroupTO.getId());
		session.saveOrUpdate(actionControlGroupTO);

	}

	@Override
	public void deleteActionKey(ActionControlGroupTO actionControlGroupTO) {
		logger.info("Class : ActionKeyDAOImpl | Method : deleteActionKey");

		System.out.println("====================== " + actionControlGroupTO.getId());
		session = sessionFactory.getCurrentSession();

		String hql = "select ID,NAME from RE_ACTION_CONFIG_GROUP_NEW where ID= '" + actionControlGroupTO.getId()
				+ "'  ";

		List<Object[]> rs = (List<Object[]>) session.createSQLQuery(hql).list();
		System.out.println("size::::::" + rs.size());

		for (Object[] obj : rs) {
			//int idValues = (Integer) obj[0];
			String id = obj[0].toString();
			idValue = Integer.parseInt(id);
			nameValues = obj[1].toString();

		}

		// System.out.println("delete " + nameValues);
		audtoTO = new AuditInfoTO();
		audtoTO.setFeatureName("ActionKey");
		audtoTO.setAddedName(nameValues);
		audtoTO.setActionType("DELETE");
		audtoTO.setAttribute("N/A");
		audtoTO.setPreviousValue("N/A");
		audtoTO.setNewValue("N/A");
		String a = "Deleted";
		String b = nameValues.concat(a);
		audtoTO.setDesc(b);

		audtoTO.setCreatedBy(actionControlGroupTO.getAuditUserName());
		audtoTO.setRoleName(actionControlGroupTO.getAuditRoleName());

		auditInfo.addAuditLog(audtoTO);

		hql = "delete from ActionPromotionDetailsTO where actionKeyDetailsId = " + actionControlGroupTO.getId();
		session.createQuery(hql).executeUpdate();
		String hql1 = "delete from ActionControlGroupTO where id = " + actionControlGroupTO.getId();
		session.createQuery(hql1).executeUpdate();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ActionControlGroupTO> getActionConfigList(Integer actionId, UserSessionTO user) {
		System.out.println("getActionConfigList :: ");
		List<ActionControlGroupTO> actionKeyControlTOList = new ArrayList<ActionControlGroupTO>();
		ActionControlGroupTO controlTO = null;
		session = sessionFactory.getCurrentSession();
		Session session1 = sessionFactory.getCurrentSession();
		query = "FROM ActionControlGroupTO WHERE parentId=" + actionId + " order by id desc ";
		actionKeyControlTOList = (List<ActionControlGroupTO>) session.createQuery(query).list();
		ActionPromotionDetailsTO actionPromotionDetailsTO = null;
		for (ActionControlGroupTO actionKeyControlTO : actionKeyControlTOList) {
			actionPromotionDetailsTO = (ActionPromotionDetailsTO) session1.get(ActionPromotionDetailsTO.class,
					actionKeyControlTO.getId());

			if (actionPromotionDetailsTO != null) {
				System.out.println("actionPromotionDetailsTO ::: " + actionPromotionDetailsTO);
				actionKeyControlTO
						.setActionKeyIdDetails(String.valueOf(actionPromotionDetailsTO.getActionKeyDetailsId()));
				actionKeyControlTO.setActionKeyNameDetails(actionPromotionDetailsTO.getActionKeyName());
				actionKeyControlTO.setEnglishNameDetails(actionPromotionDetailsTO.getEnglishName());
				actionKeyControlTO.setCampaignChannelDetails(actionPromotionDetailsTO.getCampaignChannel());
				actionKeyControlTO.setSourceDateDetails(actionPromotionDetailsTO.getSourceType());
				actionKeyControlTO.setUpsellTargetListDetails(actionPromotionDetailsTO.getUpsellTargetLst());
				actionKeyControlTO.setUpsellTargetListIDDetails(actionPromotionDetailsTO.getUpsellTargetListid());
				actionKeyControlTO.setCampaignTypeIdDetails(actionPromotionDetailsTO.getCampaignTypeId());
				actionKeyControlTO.setCampaignTypeNameDetails(actionPromotionDetailsTO.getCampaignTypeName());
				actionKeyControlTO.setPromotedTargetListDetails(actionPromotionDetailsTO.getPromotedTargetList());
				actionKeyControlTO.setPromotedTargetIDDetails(actionPromotionDetailsTO.getPromotedTargetId());
				actionKeyControlTO.setTrgetBandDetails(actionPromotionDetailsTO.getTargetBand());
				actionKeyControlTO.setPackDescriptionDetails(actionPromotionDetailsTO.getPackDescription());
				actionKeyControlTO.setMinTargetValueDetails(actionPromotionDetailsTO.getMinTargetValue());
				actionKeyControlTO.setValidDaysDetails(actionPromotionDetailsTO.getValidDays());
				actionKeyControlTO.setRoiTypeDetails(actionPromotionDetailsTO.getRoiType());
				actionKeyControlTO.setMarketingPlanNameDetails(actionPromotionDetailsTO.getMarKetingPlanNameDetails());
				actionKeyControlTO.setCreditAmount(actionPromotionDetailsTO.getCreditAmount());
				actionKeyControlTO.setCreditType(actionPromotionDetailsTO.getCreditType());

			}
			if (actionKeyControlTO.getUpsellBundleType() != null && actionKeyControlTO.getUpsellBundleType() != ""
					&& actionKeyControlTO.getUpsellBundleType().trim() != "") {
				System.out.println("getUpsellBundleType ::: " + actionKeyControlTO.getUpsellBundleType());
				actionKeyControlTO.setUpsellBundleType((actionKeyControlTO.getUpsellBundleType()));
			}
			if (actionKeyControlTO.getUpsellBundleProducts() != null
					&& actionKeyControlTO.getUpsellBundleProducts().trim() != ""
					&& actionKeyControlTO.getUpsellBundleProducts() != "") {
				System.out.println("getUpsellBundleProducts ::: " + actionKeyControlTO.getUpsellBundleProducts());
				actionKeyControlTO.setUpsellBundleProducts((actionKeyControlTO.getUpsellBundleProducts()));
			}
			if (actionKeyControlTO.getPromotedBundleType() != null
					&& actionKeyControlTO.getPromotedBundleType().trim() != ""
					&& actionKeyControlTO.getPromotedBundleType() != "") {
				System.out.println("getPromotedBundleType ::: " + actionKeyControlTO.getPromotedBundleType());
				actionKeyControlTO.setPromotedBundleType((actionKeyControlTO.getPromotedBundleType()));
			}
			if (actionKeyControlTO.getPromotedBundleProducts() != null
					&& actionKeyControlTO.getPromotedBundleProducts().trim() != ""
					&& actionKeyControlTO.getPromotedBundleProducts() != "") {
				System.out.println("getPromotedBundleProducts ::: " + actionKeyControlTO.getPromotedBundleProducts());
				actionKeyControlTO.setPromotedBundleProducts((actionKeyControlTO.getPromotedBundleProducts()));
			}
			if (actionKeyControlTO.getStartDate() != null) {
				actionKeyControlTO.setStartDateUi(simpleDateFormat.format(actionKeyControlTO.getStartDate()));
			}
			if (actionKeyControlTO.getEndDate() != null) {
				actionKeyControlTO.setEndDateUI(simpleDateFormat.format(actionKeyControlTO.getEndDate()));
			}
		}
		System.out.println("actionKeyControlTO Final " + actionKeyControlTOList);
		return actionKeyControlTOList;

	}

	/*
	 * Date convertion
	 */
	public String dateConvertion(String strDate) {
		String[] str = strDate.split(" ");
		return str[0];
	}

	@Override
	public void saveActionConfigDetails(ActionControlGroupTO actionControlGroupTO) throws Exception {

		System.out.println(" saveActionConfigDetails ");
		session = sessionFactory.getCurrentSession();

		if (actionControlGroupTO.getId() == 0) {
			System.out.println(" inside if");
			audtoTO = new AuditInfoTO();
			audtoTO.setFeatureName("Action Key");
			audtoTO.setAddedName(actionControlGroupTO.getActionKeyName());
			audtoTO.setActionType("CREATE");
			audtoTO.setAttribute("N/A");
			audtoTO.setPreviousValue("N/A");
			audtoTO.setNewValue("N/A");

			String a1 = "Created";
			String b1 = actionControlGroupTO.getActionKeyName().concat(a1);
			audtoTO.setDesc(b1);

			audtoTO.setCreatedBy(actionControlGroupTO.getAuditUserName());
			audtoTO.setRoleName(actionControlGroupTO.getAuditRoleName());

			auditInfo.addAuditLog(audtoTO);

			System.out.println("-----------------------Lead  Policy Successfully-----------------");
		}
		if (actionControlGroupTO.getId() != 0) {

			String hql = "select ID ,NAME ,START_DATE,END_DATE,Type,No_OF_TIMES from  RE_ACTION_CONFIG_GROUP_NEW where ID= '"
					+ actionControlGroupTO.getId() + "'  ";
			List<Object[]> rs = (List<Object[]>) session.createSQLQuery(hql).list();
			System.out.println("size::::::" + rs.size());

			for (Object[] obj : rs) {
				//idValue = (Integer) obj[0];
				String id = obj[0].toString();
				idValue = Integer.parseInt(id);
				nameValue = obj[1].toString();
				startdateValue = obj[2].toString();
				enddateValue = obj[3].toString();
				typeValue = obj[4].toString();
				noooftimes = obj[5].toString();
			}

			audtoTO = new AuditInfoTO();
			audtoTO.setFeatureName("ActionKey");
			audtoTO.setAddedName(actionControlGroupTO.getActionKeyName());
			audtoTO.setActionType("MODIFY");

			String a1 = "Modified";
			String b1 = actionControlGroupTO.getActionKeyName().concat(a1);
			audtoTO.setDesc(b1);

			if (nameValue != null && !nameValue.equalsIgnoreCase(actionControlGroupTO.getActionKeyName())) {
				System.out.println(" name inside ");
				audtoTO.setAttribute("ActionKey Name");
				audtoTO.setPreviousValue(nameValue);
				audtoTO.setNewValue(actionControlGroupTO.getActionKeyName());
			}

			if (typeValue != null && !typeValue.equalsIgnoreCase(actionControlGroupTO.getType())) {
				System.out.println(" Desc	ription inside ");
				audtoTO.setAttribute("Type");
				audtoTO.setPreviousValue(typeValue);
				audtoTO.setNewValue(actionControlGroupTO.getType());

			}

			if (noooftimes != null && !noooftimes.equalsIgnoreCase(actionControlGroupTO.getNoOfTimes())) {
				System.out.println(" Description inside ");
				audtoTO.setAttribute("No Of Times");
				audtoTO.setPreviousValue(noooftimes);
				audtoTO.setNewValue(actionControlGroupTO.getNoOfTimes());

			}

			audtoTO.setCreatedBy(actionControlGroupTO.getAuditUserName());
			audtoTO.setRoleName(actionControlGroupTO.getAuditRoleName());

			auditInfo.addAuditLog(audtoTO);

		}
		System.out.println("actionControlGroupTO  Before Save :::  " + actionControlGroupTO.toString());
		System.out.println("upsell " + actionControlGroupTO.getUpsellBundleType());
		if (actionControlGroupTO.getUpsellBundleType() != "" && actionControlGroupTO.getUpsellBundleType() != null) {
			actionControlGroupTO.setUpsellBundleType(actionControlGroupTO.getUpsellBundleType());
		}
		if (actionControlGroupTO.getUpsellBundleProducts() != ""
				&& actionControlGroupTO.getUpsellBundleProducts().trim() != ""
				&& actionControlGroupTO.getUpsellBundleProducts() != null) {
			actionControlGroupTO.setUpsellBundleProducts(actionControlGroupTO.getUpsellBundleProducts());
		}
		if (actionControlGroupTO.getPromotedBundleType() != ""
				&& actionControlGroupTO.getPromotedBundleType().trim() != ""
				&& actionControlGroupTO.getPromotedBundleType() != null) {
			actionControlGroupTO.setPromotedBundleType(actionControlGroupTO.getPromotedBundleType());
		}
		if (actionControlGroupTO.getPromotedBundleProducts() != ""
				&& actionControlGroupTO.getPromotedBundleProducts().trim() != ""
				&& actionControlGroupTO.getPromotedBundleProducts() != null) {
			actionControlGroupTO.setPromotedBundleProducts(actionControlGroupTO.getPromotedBundleProducts());
		}
		if (actionControlGroupTO.getFromDate() != "" && actionControlGroupTO.getFromDate() != null) {
			actionControlGroupTO.setFromDate(actionControlGroupTO.getFromDate());
		}
		if (actionControlGroupTO.getToDate() != "" && actionControlGroupTO.getToDate() != null) {
			actionControlGroupTO.setToDate(actionControlGroupTO.getToDate());
		}
		session.saveOrUpdate(actionControlGroupTO);

		ActionPromotionDetailsTO detailsTO = null;

		// Save Action Promotion Details

		if (actionControlGroupTO.getIspromotion().equalsIgnoreCase("true")) {

			String hql1 = "delete from ActionPromotionDetailsTO where actionKeyDetailsId="
					+ actionControlGroupTO.getId();
			session.createQuery(hql1).executeUpdate();

			detailsTO = new ActionPromotionDetailsTO();
			detailsTO.setActionKeyDetailsId(actionControlGroupTO.getId());
			detailsTO.setActionKeyName(actionControlGroupTO.getActionKeyName());
			detailsTO.setEnglishName(actionControlGroupTO.getEnglishNameDetails());
			detailsTO.setCampaignChannel(actionControlGroupTO.getCampaignChannelDetails());
			detailsTO.setSourceType(actionControlGroupTO.getSourceDateDetails());
			detailsTO.setUpsellTargetListid(actionControlGroupTO.getUpsellTargetListIDDetails());
			detailsTO.setUpsellTargetLst(actionControlGroupTO.getUpsellTargetListDetails());
			detailsTO.setCampaignTypeId(actionControlGroupTO.getCampaignTypeIdDetails());
			detailsTO.setCampaignTypeName(actionControlGroupTO.getCampaignTypeNameDetails());
			detailsTO.setPromotedTargetId(actionControlGroupTO.getPromotedTargetIDDetails());
			detailsTO.setPromotedTargetList(actionControlGroupTO.getPromotedTargetListDetails());
			detailsTO.setTargetBand(actionControlGroupTO.getTrgetBandDetails());
			detailsTO.setPackDescription(actionControlGroupTO.getPackDescriptionDetails());
			detailsTO.setMinTargetValue(actionControlGroupTO.getMinTargetValueDetails());
			detailsTO.setValidDays(actionControlGroupTO.getValidDaysDetails());
			detailsTO.setRoiType(actionControlGroupTO.getRoiTypeDetails());
			detailsTO.setMarKetingPlanNameDetails(actionControlGroupTO.getMarketingPlan());
			detailsTO.setCreditAmount(actionControlGroupTO.getCreditAmount());
			detailsTO.setCreditType(actionControlGroupTO.getCreditType());

			session.save(detailsTO);
			System.out.println("detailsTO  Before Save :::  " + detailsTO.toString());
		}

	}

	@Override
	public List<ActionControlGroupTO> getAllActionKeys(int userId) {
		// TODO Auto-generated method stub
		logger.info("Class : ActionKeyDAOImpl | Method : getAllActionKeys");
		session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(ActionControlGroupTO.class);
		/*
		 * if(userId!=0) { criteria.add(Restrictions.eq("userId", userId)); }
		 */

		criteria.addOrder(Order.desc("id"));

		List<ActionControlGroupTO> mainList = (List<ActionControlGroupTO>) criteria.list();

		return mainList;
	}

	@Override
	public ActionControlGroupTO loadPromotionBundleDetails() {
		// TODO Auto-generated method stub
		logger.info("Class : ActionKeyDAOImpl | Method : loadPromotionBundleDetails()");
		session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(ActionKeyBundleTariffRateTO.class);
		List<ActionKeyBundleTariffRateTO> bundleTariffList = criteria.list();

		criteria = session.createCriteria(CampaignTypeTO.class);
		List<CampaignTypeTO> campTypeList = criteria.list();

		criteria = session.createCriteria(CampaignChannelTO.class);
		List<CampaignChannelTO> campChannelList = criteria.list();

		ActionControlGroupTO controlTO = new ActionControlGroupTO();
		controlTO.setBundleTariffList(bundleTariffList);
		controlTO.setCampaignChannelList(campChannelList);
		controlTO.setCampaignTypeList(campTypeList);

		return controlTO;
	}

	@Override
	public void saveIspromotionDetails(IsPromotionDetailsTO isPromotionTO) {
		// TODO Auto-generated method stub
		session = sessionFactory.getCurrentSession();
		System.out.println("id : " + isPromotionTO.getId());
		session.save(isPromotionTO);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<IsPromotionDetailsTO> getIsPromotionDetails(Integer actionId, UserSessionTO user) {
		// TODO Auto-generated method stub
		logger.info("Class : ActionKeyDAOImpl | Method : getActionConfigList");
		List<IsPromotionDetailsTO> getIsPromotion = new ArrayList<IsPromotionDetailsTO>();

		session = sessionFactory.getCurrentSession();
		query = "FROM IsPromotionDetailsTO WHERE id=" + actionId;
		if (user.getUserId() != 1)
			query += " and userId=" + user.getUserId();
		System.out.println("SQL Query : " + query);
		getIsPromotion = (List<IsPromotionDetailsTO>) session.createQuery(query).list();
		System.out.println(" IsPromotionDetailsTO " + getIsPromotion.toString());

		return getIsPromotion;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ActionKeyCampaignChannelTO> getCampaignChannel() {
		session = sessionFactory.getCurrentSession();
		List<ActionKeyCampaignChannelTO> list = new ArrayList<ActionKeyCampaignChannelTO>();
		ActionKeyCampaignChannelTO to = null;
		List<Object[]> res = null;
		String hql = " select id, name from ActionKeyCampaignChannelTO order by id desc";
		res = session.createQuery(hql).list();
		for (Object[] obj : res) {
			to = new ActionKeyCampaignChannelTO();
			to.setId((obj[0].toString() + ""));
			to.setName(obj[1].toString() + "");
			list.add(to);
		}

		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ActionKeyCampaignTypeTO> getActionKeyCampaignType() {
		session = sessionFactory.getCurrentSession();
		List<ActionKeyCampaignTypeTO> list = new ArrayList<ActionKeyCampaignTypeTO>();
		ActionKeyCampaignTypeTO to = null;
		List<Object[]> res = null;
		String hql = " select id, name from ActionKeyCampaignTypeTO order by id desc";
		res = session.createQuery(hql).list();
		for (Object[] obj : res) {
			to = new ActionKeyCampaignTypeTO();
			to.setId((obj[0].toString() + ""));
			to.setName(obj[1].toString() + "");
			list.add(to);
		}

		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ActionKeyBundleTypeTO> getUpsellBundle() {
		List<ActionKeyBundleTypeTO> bundletype = new ArrayList<ActionKeyBundleTypeTO>();

		session = sessionFactory.getCurrentSession();
		List<ActionKeyBundleTypeTO> list = new ArrayList<ActionKeyBundleTypeTO>();
		ActionKeyBundleTypeTO to = null;
		List<Object[]> res = null;
		String hql = " select id, type from ActionKeyBundleTypeTO order by id desc";
		res = session.createQuery(hql).list();
		for (Object[] obj : res) {
			to = new ActionKeyBundleTypeTO();
			to.setId((obj[0].toString() + ""));
			to.setType(obj[1].toString() + "");
			list.add(to);
		}

		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getUpsellBundleProducts(String bundleType) {
		List<String> bundleType1 = new ArrayList<String>();
		String[] bundlArr = bundleType.split(",");
		Query query = null;
		System.out.println("===============>" + bundleType);

		List<Integer> argList = new ArrayList<>();
		for (String arg : bundlArr) {
			if (arg != null && !arg.trim().equalsIgnoreCase("")) {
				System.out.println("---------------->" + arg);
				argList.add(Integer.parseInt(arg));
			}
		}
		System.out.println("List=========>" + argList.toString());
		session = sessionFactory.getCurrentSession();
		// String hql = "select distinct(productname) FROM ActionKeyBundleTariffRateTO
		// where bundletype in (" + bundleType + ")";
		String hql = "select distinct(PRODUCT_NAME) FROM AK_BUNDLE_TARIFF_RATE where BUNDLE_TYPE in  (" + bundleType
				+ ")";
		query = session.createSQLQuery(hql);
		// query.setParameterList("argsList", argList);
		System.out.println("SQL Query : " + query);

		bundleType1 = (List<String>) query.list();

		System.out.println("Data is geting successfully");

		return bundleType1;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ActionKeyBundleTariffRateTO> getUpsellBundleTargetList(String productname) {
		List<ActionKeyBundleTariffRateTO> name1 = new ArrayList<ActionKeyBundleTariffRateTO>();
		System.out.println("ActionKeyBundleTariffRateTO  ===============>" + productname);
		String[] bundlArr = productname.split(",");
		Query query = null;
		List<String> argList = new ArrayList<>();
		for (String arg : bundlArr) {
			if (arg != null && !arg.trim().equalsIgnoreCase("")) {
				System.out.println("---------------->" + arg);
				argList.add(arg);
			}
		}
		System.out.println("List=========>" + argList.toString());
		// session = sessionFactory.getCurrentSession();
		session = sessionFactory.getCurrentSession();
		String hql = "FROM ActionKeyBundleTariffRateTO where productName in  (:argsList)";
		// query = session.createQuery(hql);
		query = session.createQuery(hql);
		query.setParameterList("argsList", argList);
		System.out.println("SQL Query : " + query);
		name1 = (List<ActionKeyBundleTariffRateTO>) query.list();
		System.out.println("Data is geting successfully target");
		return name1;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ActionKeyCreditTypeTO> getCreditType() {
		Session session = sessionFactory.getCurrentSession();
		List<ActionKeyCreditTypeTO> list = new ArrayList<ActionKeyCreditTypeTO>();
		ActionKeyCreditTypeTO to = null;
		List<Object[]> res = null;
		String hql = " select id, name from ActionKeyCreditTypeTO order by id desc";
		res = session.createQuery(hql).list();
		for (Object[] obj : res) {
			to = new ActionKeyCreditTypeTO();
			to.setId(Integer.parseInt(obj[0] + ""));
			to.setName(obj[1] + "");
			list.add(to);
		}

		System.out.println("ActionKeyCreditTypeTO  :  " + list.toString());
		return list;
	}

}