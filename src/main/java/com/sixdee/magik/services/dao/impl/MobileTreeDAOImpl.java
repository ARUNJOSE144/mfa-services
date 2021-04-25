package com.sixdee.magik.services.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sixdee.magik.services.dao.MobileAppTreeDAO;
import com.sixdee.magik.services.model.EmailFileTO;
import com.sixdee.magik.services.model.EmailMasterTO;
import com.sixdee.magik.services.model.MobileAppCategoryTO;
import com.sixdee.magik.services.model.MobileAppLangMessagesTO;
import com.sixdee.magik.services.model.MobileAppLanguagesTO;
import com.sixdee.magik.services.model.MobileAppMenuMsgDetailsTO;
import com.sixdee.magik.services.model.MobileAppMenusTO;
import com.sixdee.magik.services.model.MobileAppTreeDTO;
import com.sixdee.magik.services.model.SkypeDetailsTO;

@Repository
@Transactional
public class MobileTreeDAOImpl implements MobileAppTreeDAO {
	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;
	static final Logger logger = Logger.getLogger(MobileTreeDAOImpl.class);

	@Override
	public List<MobileAppCategoryTO> getCategory(MobileAppTreeDTO mobileAppTreeDTO) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings({ "unchecked", "deprecation" })
//		List<MobileAppCategoryTO> list = (List<MobileAppCategoryTO>) session.createCriteria(MobileAppCategoryTO.class)
//				.list();
		
		Criteria cr = session.createCriteria(MobileAppCategoryTO.class);
		if(Integer.parseInt(mobileAppTreeDTO.getUserId())!=1)
		{
			cr.add(Restrictions.eq("createdUserId",Integer.parseInt(mobileAppTreeDTO.getUserId())));
		}

		List<MobileAppCategoryTO> list = cr.list();

		return list;
	}

	@Override
	public MobileAppCategoryTO createCategory(MobileAppCategoryTO mobileAppTreeDTO) {
		try {
			int category_order = getMaxCategoryOrder();
			boolean flag = createMobileAppCategory(mobileAppTreeDTO, category_order);

			if (flag) {
				mobileAppTreeDTO.setStatusCode("200");
				// mobileAppTreeDTO.setStatus("success");
			} else {
				mobileAppTreeDTO.setStatusCode("500");
				// mobileAppTreeDTO.setStatus("failure");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.err.println(e);
		}
		// TODO Auto-generated method stub
		return mobileAppTreeDTO;
	}

	private int getMaxCategoryOrder() {
		// TODO Auto-generated method stub
		int categoryOrder = 0;
		String sql = "";
		Transaction txn;
		Session session;
		try {
			session = sessionFactory.getCurrentSession();

			System.out.println("Inside getMaxCategoryOrder::");

			sql = "SELECT MAX(CATEGORY_ORDER),CATEGORY_NAME FROM MOBILE_APP_CATEGORY_MASTER";

			System.out.println("getMaxCategoryOrder sql:: " + sql);

			Query qry = session.createSQLQuery(sql);

			@SuppressWarnings("unchecked")
			List<Object[]> results = qry.list();
			if (results != null && results.size() > 0) {
				for (Object[] aRow : results) {
					categoryOrder = (int) aRow[0];
				}
			}

			categoryOrder = categoryOrder + 1;

			logger.info(" value of catagort_order Id::" + categoryOrder);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return categoryOrder;
	}

	private boolean createMobileAppCategory(MobileAppCategoryTO mobileAppCategoryTO, int category_order) {
		
		boolean flag = false;
		String sql = "";
		Transaction txn;
		Session session;
		try {

			session = sessionFactory.getCurrentSession();
			mobileAppCategoryTO.setStatus("T");
			mobileAppCategoryTO.setCategoryOrder("" + category_order);
			mobileAppCategoryTO.setCreatedUserId(mobileAppCategoryTO.getCreatedUserId());
			session.saveOrUpdate(mobileAppCategoryTO);
			flag = true;
			System.err.println(category_order);

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e);
		}

		return flag;
	}

	@Override
	public MobileAppCategoryTO editCategory(MobileAppCategoryTO mobileAppTreeDTO) {
		// TODO Auto-generated method stub
		try {
			boolean flag = editMobCategory(mobileAppTreeDTO);
			if (flag) {
				mobileAppTreeDTO.setStatusCode("200");
				// mobileAppTreeDTO.setStatus("success");

			} else {
				mobileAppTreeDTO.setStatusCode("500");
				// mobileAppTreeDTO.setStatus("failure");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return mobileAppTreeDTO;
	}

	public boolean editMobCategory(MobileAppCategoryTO mobileAppCategoryTO) {
		boolean flag = false;
		String sql = "";
		Transaction txn;
		Session session;
		try {
			session = sessionFactory.getCurrentSession();
			MobileAppCategoryTO to = (MobileAppCategoryTO) session.get(MobileAppCategoryTO.class,
					mobileAppCategoryTO.getCampId());
			to.setCampName(mobileAppCategoryTO.getCampName());
			to.setCreatedUserId(mobileAppCategoryTO.getCreatedUserId());
			session.saveOrUpdate(to);
			flag = true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}

	@Override
	public MobileAppTreeDTO delCategory(MobileAppTreeDTO mobileAppTreeDTO) {
		// TODO Auto-generated method stub
		try {
			boolean flag = delMobCategory(mobileAppTreeDTO);
			if (flag) {
				mobileAppTreeDTO.setStatusCode("200");
				mobileAppTreeDTO.setStatus("success");

			} else {
				mobileAppTreeDTO.setStatusCode("500");
				mobileAppTreeDTO.setStatus("failure");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return mobileAppTreeDTO;
	}

	private boolean delMobCategory(MobileAppTreeDTO dto) {
		boolean flag = false;
		String sql = "";
		Transaction txn;
		Session session;
		try {

			session = sessionFactory.getCurrentSession();
			txn = session.beginTransaction();

			sql = "DELETE FROM MOBILE_APP_CATEGORY_MASTER WHERE CATEGORY_ID='" + dto.getCampId() + "'";

			logger.info("deleteCategoryMobileApp-->" + sql);

			Query qry = session.createSQLQuery(sql);
			int rs = qry.executeUpdate();

			if (rs > 0) {
				flag = true;
			}

			System.out.println("flag in deleteCategoryMobileApp:: " + flag);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;

	}

	@Override
	public MobileAppTreeDTO getLanguage(MobileAppTreeDTO mobileAppTreeDTO) {
		// TODO Auto-generated method stub
		try {

			Session session = sessionFactory.getCurrentSession();
			@SuppressWarnings({ "unchecked", "deprecation" })
			List<MobileAppLanguagesTO> list = (List<MobileAppLanguagesTO>) session
					.createCriteria(MobileAppLanguagesTO.class).list();
			mobileAppTreeDTO.setLanguages(list);
			System.out.println("language List::" + mobileAppTreeDTO.getLanguages().toString());

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return mobileAppTreeDTO;
	}

	@Override
	public MobileAppTreeDTO getMenuName(MobileAppTreeDTO mobileAppTreeDTO) {
		String sql = "";
		Query qry = null;
		MobileAppMenusTO dto;
		List menuList = new ArrayList();
		try {
			Session session = sessionFactory.getCurrentSession();

			sql = "SELECT MENU_ID,MENU_NAME FROM MOBILE_APP_MENU_DETAILS WHERE CAMP_ID ="
					+ mobileAppTreeDTO.getCampId();
			logger.info("[sql] : " + sql);
			qry = session.createSQLQuery(sql);
			List<Object[]> results = qry.list();

			if (results != null && results.size() > 0) {
				for (Object[] aRow : results) {
					dto = new MobileAppMenusTO();
					dto.setMenuId(Integer.parseInt(aRow[0].toString()));
					dto.setMenuName(aRow[1].toString());
					menuList.add(dto);
				}
			}
			mobileAppTreeDTO.setMenus(menuList);
			System.out.println("Menu LIST>>> " + mobileAppTreeDTO.getMenus().toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return mobileAppTreeDTO;
	}

	@Override
	public MobileAppTreeDTO getMessage(MobileAppTreeDTO mobileAppTreeDTO) {
		// TODO Auto-generated method stub
		MobileAppTreeDTO mobileAppTreeDTO1 = null;
		try {

			mobileAppTreeDTO1 = GetMobileAppMessage(mobileAppTreeDTO);
			// mobileAppTreeDTO.setMessages(msglist);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return mobileAppTreeDTO1;
	}

	private MobileAppTreeDTO GetMobileAppMessage(MobileAppTreeDTO mobileAppTreeDTO) {

		logger.info("[GetCampaignDao : GetMobileAppMessage() ]");

		Session session = null;
		List<MobileAppLangMessagesTO> menuList = new ArrayList<MobileAppLangMessagesTO>();
		String sql = null;
		Transaction txn = null;
		MobileAppLangMessagesTO dto = null;
		HashMap<Integer, String> map = null;
		String sql1 = null;
		List<MobileAppLangMessagesTO> inboundMenuList = new ArrayList<MobileAppLangMessagesTO>();
		try {

			map = GetLanguage();

			System.out.println("============ Menu Id =============" + mobileAppTreeDTO.getMenuId());

			session = sessionFactory.getCurrentSession();
			txn = session.beginTransaction();

			sql = "SELECT LANGUAGE_ID,MESSAGE,SM.MENU_ID,MENU_NAME,TITLE,USSD_SENDER_ID,APP_API_LINK,WEB_API_LINK FROM MOBILE_APP_MENU_MSG_DETAILS SMM,MOBILE_APP_MENU_DETAILS SM WHERE SM.MENU_ID=SMM.MENU_ID AND SMM.MENU_ID = "
					+ mobileAppTreeDTO.getMenuId() + " AND MSG_TYPE = 1";
			logger.info("[sql] : " + sql);

			Query qry = session.createSQLQuery(sql);
			List<Object[]> result = qry.list();
			if (result != null && result.size() > 0) {
				for (Object[] aRow : result) {
					dto = new MobileAppLangMessagesTO();
					dto.setLangId(aRow[0].toString());
					dto.setMessage(getUtf8(aRow[1].toString()));
					dto.setLangName(map.get(Integer.parseInt(aRow[0].toString())));
					dto.setMenuId(aRow[2].toString());
					dto.setMenuName(aRow[3].toString());
					dto.setTitle(aRow[4].toString());
					dto.setUssdSenderId(aRow[5] != null ? aRow[5].toString() : "");
					dto.setAppApiLink(aRow[6] != null ? aRow[6].toString() : "");
					dto.setWebApiLink(aRow[7] != null ? aRow[7].toString() : "");
					menuList.add(dto);
				}
			}

			mobileAppTreeDTO.setMessages(menuList);

			sql1 = "SELECT LANGUAGE_ID,MESSAGE,SM.MENU_ID,MENU_NAME,TITLE,DEEP_LINK,CAMPAIGN_TYPE,PRIORITY,START_DATE,END_DATE FROM MOBILE_APP_MENU_MSG_DETAILS SMM,MOBILE_APP_MENU_DETAILS SM WHERE SM.MENU_ID=SMM.MENU_ID AND SMM.MENU_ID = "
					+ mobileAppTreeDTO.getMenuId() + " AND MSG_TYPE = 2";
			logger.info("[sql1] : " + sql1);

			Query qry1 = session.createSQLQuery(sql1);
			List<Object[]> result1 = qry1.list();
			if (result1 != null && result1.size() > 0) {
				for (Object[] aRow : result1) {
					dto = new MobileAppLangMessagesTO();
					dto.setLangId(aRow[0].toString());
					dto.setMessage(getUtf8(aRow[1].toString()));
					dto.setLangName(map.get(Integer.parseInt(aRow[0].toString())));
					dto.setMenuId(aRow[2].toString());
					dto.setMenuName(aRow[3].toString());
					dto.setTitle(aRow[4].toString());
					dto.setDeepLink(aRow[5] != null ? aRow[5].toString() : "");
					dto.setCampaignType(aRow[6] != null ? aRow[6].toString() : "");
					dto.setPriority(aRow[7] != null ? aRow[7].toString() : "");
					dto.setStartDate(aRow[8] != null ? aRow[8].toString() : "");
					dto.setEndDate(aRow[9] != null ? aRow[9].toString() : "");
					inboundMenuList.add(dto);
				}
			}

			mobileAppTreeDTO.setInboundMessages(inboundMenuList);

		} catch (Exception e) {
			logger.error("Exception Raised in the try Block of GetMobileAppMessage:" + e);
			e.printStackTrace();

		}
		return mobileAppTreeDTO;

	}

	public static String getUtf8(String word) {
		String decoded = "";
		for (Integer index = 0; index < word.length(); index++) {
			String charAt = "" + word.charAt(index);
			if (charAt.equals("&") && index < word.length() && ("" + word.charAt(index + 1)).equals("#")) {
				try {
					Integer length = word.indexOf(";", index);
					String sub = word.substring(index + 2, length);
					decoded += Character.toString((char) Integer.parseInt(sub));
					index = length;
				} catch (Exception ex) {
					decoded += charAt;
				}
			} else {
				decoded += charAt;
			}
		}
		System.out.println("============ decoded text===============" + decoded);
		return decoded;
	}

	private HashMap<Integer, String> GetLanguage() {
		logger.info("[GetCampaignDao : GetLanguage() ]");

		Session session = null;
		String sql = null;
		Transaction txn = null;
		HashMap<Integer, String> LangMap = new HashMap<Integer, String>();
		try {
			session = sessionFactory.getCurrentSession();
			sql = "SELECT ID,LANGUAGE FROM LANGUAGE";
			logger.info("[sql] : " + sql);
			Query qry = session.createSQLQuery(sql);
			List<Object[]> result = qry.list();
			if (result != null && result.size() > 0) {
				for (Object[] aRow : result) {
					LangMap.put(Integer.parseInt(aRow[0].toString()), aRow[1].toString());
				}
			}
		} catch (Exception e) {
			logger.error("Exception Raised in the try Block of GetLanguage:" + e);
			e.printStackTrace();

		}
		return LangMap;
	}

	@Override
	public MobileAppMenusTO createMessage(MobileAppMenusTO mobileAppTreeDTO) {
		// TODO Auto-generated method stub
		try {

			boolean flag = CreateMobileAppMenuName(mobileAppTreeDTO);

			if (flag) {

				int menuId = mobileAppTreeDTO.getMenuId();
				flag = CreateMobileAppMessage(mobileAppTreeDTO, menuId);
			}

			if (flag) {
				mobileAppTreeDTO.setStatusCode("200");
				// mobileAppTreeDTO.setStatus("success");

			} else {
				mobileAppTreeDTO.setStatusCode("500");
				// mobileAppTreeDTO.setStatus("failure");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return mobileAppTreeDTO;
	}

	private boolean CreateMobileAppMenuName(MobileAppMenusTO mobileAppMenusTO) {
		// TODO Auto-generated method stub
		logger.info("[CreateMobileAppMenuName() ]");
		Session session = null;
		Transaction txn = null;
		boolean flag = false;
		String menuId = null;

		try {

			session = sessionFactory.getCurrentSession();
			txn = session.beginTransaction();
			session.save(mobileAppMenusTO);
			flag = true;

			/*
			 * String sql =
			 * "INSERT INTO MOBILE_APP_MENU_DETAILS (MENU_NAME,CAMP_ID,CREATE_USER) VALUES ('"
			 * + mobileAppTreeDTO.getMenuName() + "','" + mobileAppTreeDTO.getCampId() +
			 * "','" + mobileAppTreeDTO.getUserId() + "')"; logger.info("[sql] : " + sql);
			 * Query qry = session.createSQLQuery(sql); int i = qry.executeUpdate(); if (i >
			 * 0) flag = true;
			 * 
			 * logger.info("[flag]" + flag);
			 */

		} catch (Exception e) {
			// genericDTO.setStatus("1");
			logger.error("Exception Raised in the try Block of CreateMobileAppMenuName:" + e);
			e.printStackTrace();

		}
		return flag;
	}

	private boolean CreateMobileAppMessage(MobileAppMenusTO mobileAppMenusTO, int menuId) {
		// TODO Auto-generated method stub
		Transaction txn = null;
		boolean flag = false;
		Session session = null;
		String sql = "";
		try {

			logger.info("[ CreateMobileAppMessage() ]");
			session = sessionFactory.getCurrentSession();

			List list = mobileAppMenusTO.getMessages();
			System.out.println("list Size" + list.size());
			for (int j = 0; j < list.size(); j++) {
				MobileAppMenuMsgDetailsTO dto = (MobileAppMenuMsgDetailsTO) list.get(j);
				dto.setMenuId(menuId);
				dto.setMessage(getHex(dto.getMessage()));
				session.save(dto);
				flag = true;

			}

			List inboundList = mobileAppMenusTO.getInboundMessages();
			System.out.println("inboundList Size" + inboundList.size());
			for (int j = 0; j < inboundList.size(); j++) {
				MobileAppMenuMsgDetailsTO dto = (MobileAppMenuMsgDetailsTO) inboundList.get(j);
				dto.setMenuId(menuId);
				dto.setMessage(getHex(dto.getMessage()));
				session.save(dto);
				flag = true;
			}

			/*
			 * List list = mobileAppMenusTO.getMessages(); System.out.println("list Size" +
			 * list.size()); for (int j = 0; j < list.size(); j++) {
			 * MobileAppMenuMsgDetailsTO dto = (MobileAppMenuMsgDetailsTO) list.get(j); txn
			 * = session.beginTransaction(); System.out.println("get j" + j);
			 * 
			 * sql =
			 * "INSERT INTO MOBILE_APP_MENU_MSG_DETAILS (MENU_ID,LANGUAGE_ID,MESSAGE,USER_ID,TITLE,USSD_SENDER_ID,APP_API_LINK,WEB_API_LINK,MSG_TYPE) VALUES"
			 * + " ('" + menuId + "','" + dto.getLangId() + "','" + getHex(dto.getMessage())
			 * + "','" + mobileAppMenusTO.getUserId() + "','" + dto.getTitle() + "','" +
			 * dto.getUssdSenderId() + "','" + dto.getAppApiLink() + "','" +
			 * dto.getWebApiLink() + "','" + dto.getMessageType() + "')";
			 * logger.info("[sql] : " + sql); Query qry = session.createSQLQuery(sql); int i
			 * = qry.executeUpdate(); System.out.println("============ i =============" +
			 * i); if (i > 0) { flag = true; } // txn.commit();
			 * 
			 * 
			 * 
			 * }
			 * 
			 * List inboundList = mobileAppMenusTO.getInboundMessages();
			 * System.out.println("inboundList Size" + inboundList.size()); for (int j = 0;
			 * j < inboundList.size(); j++) { MobileAppMenuMsgDetailsTO dto =
			 * (MobileAppMenuMsgDetailsTO) inboundList.get(j); txn =
			 * session.beginTransaction();
			 * 
			 * System.out.println("get j" + j);
			 * 
			 * sql =
			 * "INSERT INTO MOBILE_APP_MENU_MSG_DETAILS (MENU_ID,LANGUAGE_ID,MESSAGE,USER_ID,TITLE,MSG_TYPE,DEEP_LINK,CAMPAIGN_TYPE,PRIORITY,START_DATE,END_DATE) VALUES"
			 * + " ('" + menuId + "','" + dto.getLangId() + "','" + getHex(dto.getMessage())
			 * + "','" + mobileAppMenusTO.getUserId() + "','" + dto.getTitle() + "','" +
			 * dto.getMessageType() + "','" + dto.getDeepLink() + "','" +
			 * dto.getCampaignType() + "','" + dto.getPriority() + "','" +
			 * dto.getStartDate() + "','" + dto.getEndDate() + "')"; logger.info("[sql] : "
			 * + sql); Query qry = session.createSQLQuery(sql); int i = qry.executeUpdate();
			 * System.out.println("============ i =============" + i); if (i > 0) { flag =
			 * true; } // txn.commit();
			 * 
			 * 
			 * }
			 */

		} catch (Exception e) {
			// genericDTO.setStatus("1");
			logger.error("Exception Raised in the try Block of CreateMobileAppMenuName:" + e);
			e.printStackTrace();

		}

		return flag;
	}

	private static String getHex(String parameter) {
		// TODO Auto-generated method stub

		String value = "";

		try {
			for (int i = 0; i < parameter.length(); i++) {
				char a = parameter.charAt(i);

				if (Character.isDigit(a) || ((int) a >= 65 && (int) a <= 90) || ((int) a >= 97 && (int) a <= 122)
						|| a == ' ') {
					value += a;
				} else {
					value += "&#" + (int) a + ";";
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		System.out.println("============= value ==============" + value);

		return value;
	}

	/*
	 * private String getMenuId(MobileAppMenusTO mobileAppTreeDTO) {
	 * logger.info("[getMenu Id ]"); Session session = null; String menuId = "";
	 * String sql = null; try { session = sessionFactory.getCurrentSession(); sql =
	 * "SELECT MENU_ID,MENU_NAME FROM MOBILE_APP_MENU_DETAILS WHERE MENU_NAME = '" +
	 * mobileAppTreeDTO.getMenuName() + "'"; logger.info("[sql] : " + sql); Query
	 * qry = session.createSQLQuery(sql);
	 * 
	 * List<Object[]> results = qry.list(); if (results != null && results.size() >
	 * 0) { for (Object[] aRow : results) { menuId = aRow[0].toString(); }
	 * 
	 * } System.out.println("menuId*****" + menuId);
	 * 
	 * } catch (Exception e) { // genericDTO.setStatus("1");
	 * logger.error("Exception Raised in the try Block of GetMobileAppMessage:" +
	 * e); e.printStackTrace();
	 * 
	 * }
	 * 
	 * return menuId; }
	 */

	@Override
	public MobileAppMenusTO editMessage(MobileAppMenusTO mobileAppTreeDTO) {
		// TODO Auto-generated method stub
		try {

			boolean flag = EditMobileAppMenuName(mobileAppTreeDTO);
			flag = EditMobileAppMessage(mobileAppTreeDTO);
			if (flag) {
				mobileAppTreeDTO.setStatusCode("200");
				// mobileAppTreeDTO.setStatus("success");

			} else {
				mobileAppTreeDTO.setStatusCode("500");
				// mobileAppTreeDTO.setStatus("failure");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return mobileAppTreeDTO;
	}

	private boolean EditMobileAppMenuName(MobileAppMenusTO mobileAppTreeDTO) {
		logger.info("[ EditMobileAppMenuName() ]");
		Session session = null;
		String sql = null;
		Transaction txn = null;
		boolean flag = false;

		try {

			session = sessionFactory.getCurrentSession();
			txn = session.beginTransaction();

			MobileAppMenusTO mobileAppMenusTO = session.get(MobileAppMenusTO.class, mobileAppTreeDTO.getMenuId());
			session.saveOrUpdate(mobileAppMenusTO);

			/*
			 * sql = "UPDATE MOBILE_APP_MENU_DETAILS SET MENU_NAME = '" +
			 * mobileAppTreeDTO.getMenuName() + "' WHERE MENU_ID ='" +
			 * mobileAppTreeDTO.getMenuId() + "' AND CAMP_ID = '" +
			 * mobileAppTreeDTO.getCampId() + "' AND CREATE_USER ='" +
			 * mobileAppTreeDTO.getUserId() + "' "; logger.info("[sql] : " + sql); Query qry
			 * = session.createSQLQuery(sql); int i = qry.executeUpdate();
			 * System.out.println("============ i =============" + i); if (i > 0) flag =
			 * true;
			 * 
			 * logger.info("[flag]" + flag);
			 */

		} catch (Exception e) {
			// genericDTO.setStatus("1");
			logger.error("Exception Raised in the try Block of EditMobileAppMenuName:" + e);
			e.printStackTrace();

		}
		return flag;
	}

	private boolean EditMobileAppMessage(MobileAppMenusTO mobileAppMenusTO) {
		logger.info("[EditMobileAppMessage() ]");

		Session session = null;
		String sql = null;
		Transaction txn = null;
		boolean flag = false;

		try {

			session = sessionFactory.getCurrentSession();
			/*
			 * System.out.println("========== menu id  ============"+
			 * mobileAppMenusTO.getMenuId()); MobileAppMenuMsgDetailsTO mobileAppMenusTO1 =
			 * session.get(MobileAppMenuMsgDetailsTO.class, mobileAppMenusTO.getMenuId());
			 * System.out.println("========== menu id  1============"+
			 * mobileAppMenusTO1.getMenuId()); session.delete(mobileAppMenusTO1);
			 */

			sql = "DELETE FROM MOBILE_APP_MENU_MSG_DETAILS WHERE MENU_ID =" + mobileAppMenusTO.getMenuId();
			logger.info("[sql] : " + sql);
			Query qry = session.createSQLQuery(sql);
			qry.executeUpdate();

			List list = mobileAppMenusTO.getMessages();
			System.out.println("list Size" + list.size());
			for (int j = 0; j < list.size(); j++) {
				MobileAppMenuMsgDetailsTO dto = (MobileAppMenuMsgDetailsTO) list.get(j);
				dto.setMenuId(mobileAppMenusTO.getMenuId());
				dto.setMessage(getHex(dto.getMessage()));
				session.save(dto);
				flag = true;

			}

			List inboundList = mobileAppMenusTO.getInboundMessages();
			System.out.println("inboundList Size" + inboundList.size());
			for (int j = 0; j < inboundList.size(); j++) {
				MobileAppMenuMsgDetailsTO dto = (MobileAppMenuMsgDetailsTO) inboundList.get(j);
				dto.setMenuId(mobileAppMenusTO.getMenuId());
				dto.setMessage(getHex(dto.getMessage()));
				session.save(dto);
				flag = true;
			}

			/*
			 * sql = "DELETE FROM MOBILE_APP_MENU_MSG_DETAILS WHERE MENU_ID =" +
			 * mobileAppTreeDTO.getMenuId(); logger.info("[sql] : " + sql); Query qry =
			 * session.createSQLQuery(sql); qry.executeUpdate();
			 * 
			 * List list = mobileAppTreeDTO.getMessages(); for (int j = 0; j < list.size();
			 * j++) { txn = session.beginTransaction(); MobileAppLangMessagesTO dto =
			 * (MobileAppLangMessagesTO) list.get(j); sql =
			 * "INSERT INTO MOBILE_APP_MENU_MSG_DETAILS (MENU_ID,LANGUAGE_ID,MESSAGE,USER_ID,TITLE,USSD_SENDER_ID,APP_API_LINK,WEB_API_LINK,MSG_TYPE) VALUES"
			 * + " ('" + mobileAppTreeDTO.getMenuId() + "','" + dto.getLangId() + "','" +
			 * getHex(dto.getMessage()) + "','" + mobileAppTreeDTO.getUserId() + "','" +
			 * dto.getTitle() + "','" + dto.getUssdSenderId() + "','" + dto.getAppApiLink()
			 * + "','" + dto.getWebApiLink() + "','" + dto.getMessageType() + "')";
			 * logger.info("[sql] : " + sql); Query qry1 = session.createSQLQuery(sql); int
			 * i = qry1.executeUpdate(); System.out.println("============ i =============" +
			 * i); if (i > 0) { flag = true; } logger.info("[flag]" + flag); }
			 * 
			 * List list1 = mobileAppTreeDTO.getInboundMessages(); for (int j = 0; j <
			 * list1.size(); j++) { txn = session.beginTransaction();
			 * MobileAppLangMessagesTO dto = (MobileAppLangMessagesTO) list1.get(j); sql =
			 * "INSERT INTO MOBILE_APP_MENU_MSG_DETAILS (MENU_ID,LANGUAGE_ID,MESSAGE,USER_ID,TITLE,MSG_TYPE,DEEP_LINK,CAMPAIGN_TYPE,PRIORITY,START_DATE,END_DATE) VALUES"
			 * + " ('" + mobileAppTreeDTO.getMenuId() + "','" + dto.getLangId() + "','" +
			 * getHex(dto.getMessage()) + "','" + mobileAppTreeDTO.getUserId() + "','" +
			 * dto.getTitle() + "','" + dto.getMessageType() +
			 * "','"+dto.getDeepLink()+"','"+dto.getCampaignType()+"','"+dto.getPriority()+
			 * "','"+dto.getStartDate()+"','"+dto.getEndDate()+"')"; logger.info("[sql] : "
			 * + sql); Query qry1 = session.createSQLQuery(sql); int i =
			 * qry1.executeUpdate(); System.out.println("============ i =============" + i);
			 * if (i > 0) { flag = true; } logger.info("[flag]" + flag); }
			 */

		} catch (Exception e) {
			// genericDTO.setStatus("1");
			logger.error("Exception Raised in the try Block of EditMobileAppMessage:" + e);
			e.printStackTrace();

		}
		return flag;
	}

	@Override
	public MobileAppTreeDTO delMenu(MobileAppTreeDTO mobileAppTreeDTO) {
		// TODO Auto-generated method stub
		try {

			boolean flag = DelMenuName(mobileAppTreeDTO);

			if (flag) {
				mobileAppTreeDTO.setStatusCode("200");
				mobileAppTreeDTO.setStatus("success");
			} else {
				mobileAppTreeDTO.setStatusCode("500");
				mobileAppTreeDTO.setStatus("failure");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return mobileAppTreeDTO;
	}

	private boolean DelMenuName(MobileAppTreeDTO mobileAppTreeDTO) {

		logger.info("[GetCampaignDao : DelMenuName() ]");
		Session session = null;
		String sql = null;

		Transaction txn = null;
		boolean flag = false;

		try {

			session = sessionFactory.getCurrentSession();
			txn = session.beginTransaction();
			sql = "DELETE FROM MOBILE_APP_MENU_DETAILS WHERE MENU_ID =" + mobileAppTreeDTO.getMenuId();
			logger.info("[sql] : " + sql);
			Query qry = session.createSQLQuery(sql);
			qry.executeUpdate();
			sql = "DELETE FROM MOBILE_APP_MENU_MSG_DETAILS WHERE MENU_ID =" + mobileAppTreeDTO.getMenuId();
			logger.info("[sql] : " + sql);

			Query qry1 = session.createSQLQuery(sql);
			qry1.executeUpdate();

			flag = true;

		} catch (Exception e) {
			// genericDTO.setStatus("1");
			logger.error("Exception Raised in the try Block of DelMenuName:" + e);
			e.printStackTrace();

		}
		return flag;
	}

}
