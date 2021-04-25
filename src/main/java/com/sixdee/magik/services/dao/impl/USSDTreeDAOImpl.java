package com.sixdee.magik.services.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sixdee.magik.services.dao.USSDTreeDAO;
import com.sixdee.magik.services.model.GetCampaigns;
import com.sixdee.magik.services.model.GetCampainsTO;
import com.sixdee.magik.services.model.GetLanguages;
import com.sixdee.magik.services.model.GetSMSLangMessages;
import com.sixdee.magik.services.model.GetSMSMenusTO;
import com.sixdee.magik.services.model.GetSMSMessages;
import com.sixdee.magik.services.model.TwitterDetailsTO;
import com.sixdee.magik.services.model.USSDRequest;
import com.sixdee.magik.services.model.USSDResponse;
import com.sixdee.magik.services.model.UssdMsgMenuDetailsTO;


/**
 * @author davis.nayak
 * @Date : June, 2020
 *
 */



@Repository
@Transactional
public class USSDTreeDAOImpl implements USSDTreeDAO {

	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;

	protected final Logger logger = Logger.getLogger(USSDTreeDAOImpl.class);

	
	  @Override public List<GetCampaigns> GetCategories(USSDRequest request) {
	  
	  
	  Session session = sessionFactory.getCurrentSession();
	  
	  List<Object[]> rs = null;
	  String hql ="";
	  
	  if(Integer.parseInt(request.getUserId())!=1) {   hql = "SELECT campId, campName FROM GetCampainsTO Where createdUserId = '"+request.getUserId()+"'  ORDER BY campId";      }
	  else
	  {   hql = "SELECT campId, campName FROM GetCampainsTO ORDER BY campId"; }
	  
	  rs = (List<Object[]>) session.createQuery(hql).list();
	  
	  List<GetCampaigns> response = new ArrayList<>();
	  
	  for (Object[] obj : rs) { GetCampaigns getCamaigns = new GetCampaigns();
	  
	  getCamaigns.setCampId(obj[0] + ""); getCamaigns.setCampName(obj[1] + "");
	  response.add(getCamaigns);
	  
	  }
	  
	  return response;
	  
	  }
	  
		@Override
		public USSDResponse CreateCamp(USSDRequest request) {

			USSDResponse response = null;
			boolean flag = false;
			int c_id=0;

			try {
				c_id=getCategoryId();
				flag = CreateCategory(request,c_id);
				response = new USSDResponse();
				if (flag == true) {
					response.setStatusCode("200");
					response.setStatus("success");

				} else {
					response.setStatusCode("500");
					response.setStatus("failure");
				}
			} catch (Exception e) {
				// TODO: handle exception
				logger.error("Exception Raised in the try Block of CreateMessage:" + e);
				e.printStackTrace();

			}

			return response;
		}
	 

		private int getCategoryId() {		
		
			Session session = null;
			Transaction txn = null;
			boolean flag = false;
			Integer  c_id=0; 
			try {
				session = sessionFactory.getCurrentSession();
				txn = session.beginTransaction();
				List<Object[]> rs = null;
				
				String sql="SELECT MAX(CATEGORY_ORDER) FROM CATEGORY_MASTER";
				logger.info("[sql] : " + sql);
				rs = (List<Object[]>) session.createSQLQuery(sql).list();
				
				for (Object[] obj : rs) {
					c_id=(Integer) obj[0];
				}
				logger.info(" value of catagort Id::"+c_id);
				c_id=c_id+1;
				/*
				 * if(result!=null &&result.size()>0) { for(Integer r:result) { c_id=r; } }
				 */
				//txn.commit();
		}catch (Exception e) {
			logger.error("Exception Raised in the try Block of getCategoryId:" + e);
			e.printStackTrace();
		}
			return c_id;
		}
		private boolean CreateCategory(USSDRequest request,int c_id) {
			
			Session session = null;
			Transaction txn = null;
			boolean flag = false;
			try {
				logger.info("Name ::" + request.getName());
				logger.info("Catagory Id::"+c_id);

				session = sessionFactory.getCurrentSession();
				txn = session.beginTransaction();
//				String sql = "INSERT INTO CATEGORY_MASTER (CATEGORY_NAME,STATUS,CATEGORY_ORDER) VALUES ('"
//						+ request.getName() + "','" + "T" + "','" + c_id + "')";
				String sql="INSERT INTO CATEGORY_MASTER(CATEGORY_NAME,STATUS,CATEGORY_ORDER,CREATED_BY) VALUES ('"+request.getName()+"','T','"+c_id+"','"+request.getUserId()+"')";
				Query qry = session.createSQLQuery(sql);
				int i = qry.executeUpdate();
				if (i > 0)
					flag = true;

				/*
				 * if (txn.getStatus().equals(TransactionStatus.ACTIVE)) { txn.commit(); }
				 */

				logger.info("[flag]" + flag);
			} catch (Exception e) {
				logger.error("Exception Raised in the try Block of CreateCategory:" + e);
				e.printStackTrace();
				//txn.rollback();

			}
			return flag;
		
		
	}
		
		@Override
		public USSDResponse EditCamp(USSDRequest request) {
			
			USSDResponse response = null;
			boolean flag = false;

			try {

				flag = EditCategory(request);
				response = new USSDResponse();
				if (flag == true) {
					response.setStatusCode("200");
					response.setStatus("success");

				} else {
					response.setStatusCode("500");
					response.setStatus("failure");
				}

			} catch (Exception e) {
				// TODO: handle exception
				logger.error("Exception Raised in the try Block of EditMessage:" + e);
				e.printStackTrace();

			}

			return response;
		}

		private boolean EditCategory(USSDRequest request) {
			boolean flag = false;
			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();

			//String sql = "UPDATE CATEGORY_MASTER SET CATEGORY_NAME='" + request.getName()+"' WHERE CATEGORY_ID="+request.getId();
			String sql ="UPDATE CATEGORY_MASTER SET CATEGORY_NAME = '"+request.getName()+"',CREATED_BY='"+request.getUserId()+"' WHERE CATEGORY_ID='"+request.getCampId() +"'";
			Query query = session.createSQLQuery(sql);
			//int row1 = query.executeUpdate();
			int i = query.executeUpdate();
			if (i > 0) {
				flag = true;
				}
			  
			 logger.info("[flag]" + flag);

			/*
			 * List list = request.getMessages(); for (int j = 0; j < list.size(); j++) {
			 * 
			 * GetSMSMessages getSMSMessages = (GetSMSMessages) list.get(j); String sql
			 * ="INSERT INTO USSD_MENU_MSG_DETAILS (MENU_ID,LANGUAGE_ID,MESSAGE,USER_ID) VALUES"
			 * + " ('"+request.getMenuId()+"','"+getSMSMessages.getLangId()+"','"+
			 * getSMSMessages.getMessage()+"','"+request.getUserId()+"')";
			 * 
			 * logger.info("sql] : " + sql); Query query1 = session.createSQLQuery(sql); int
			 * i = query1.executeUpdate(); System.out.println("============ i ============="
			 * + i); if (i > 0) { flag = true; }
			 * 
			 * logger.info("[flag]" + flag);
			 * 
			 * }
			 */

			return flag;
		}

		@Override
		public USSDResponse DeleteCamp(USSDRequest request) {
			USSDResponse response = null;
			boolean flag = false;

			try {

				flag = DelCatagoryName(request);
				response = new USSDResponse();
				if (flag == true) {
					response.setStatusCode("200");
					response.setStatus("success");

				} else {
					response.setStatusCode("500");
					response.setStatus("failure");
				}

			} catch (Exception e) {
				logger.error("Exception Raised in the try Block of DelMenu:" + e);
				e.printStackTrace();

			}

			return response;
		}

		private boolean DelCatagoryName(USSDRequest request) {
			boolean flag = false;

			try {

				Session session = sessionFactory.getCurrentSession();
				session.beginTransaction();

				String sql = "DELETE FROM CATEGORY_MASTER WHERE CATEGORY_NAME='" + request.getName()+"'";
				Query query = session.createSQLQuery(sql);
				int row1 = query.executeUpdate();

				logger.info("*** Value of row1 **** ::"+row1);

				if (row1 > 0 ) {
					flag = true;
				}
				
				logger.info("Value of logger inside del method ::"+flag);

			} catch (Exception e) {
				logger.error("Exception Raised in the try Block of DelCatagoryName:" + e);
				e.printStackTrace();
			}

			return flag;
		}

		@Override
		public List<GetSMSMenusTO> GetSMSMenu(USSDRequest request) {

			Session session = sessionFactory.getCurrentSession();

			List<Object[]> rs = null;

			String hql = "";
          
			
			 if(Integer.parseInt(request.getUserId())!=1) 
			 {  hql = "SELECT menuId, menuName FROM GetSMSMenusTO WHERE campId = " + request.getCampId() + " AND createUser="+request.getUserId()+")";}
			 else
			  {  hql = "SELECT menuId, menuName FROM GetSMSMenusTO WHERE campId = " + request.getCampId() + ")"; }
			
			rs = (List<Object[]>) session.createQuery(hql).list();

			List<GetSMSMenusTO> response = new ArrayList<>();

			for (Object[] obj : rs) {
				GetSMSMenusTO getSMSMenusTO = new GetSMSMenusTO();
				System.out.println("loping");
				getSMSMenusTO.setMenuId(obj[0] + "");
				getSMSMenusTO.setMenuName(obj[1] + "");
				response.add(getSMSMenusTO);

			}

			return response;

		}

	@Override
	public USSDResponse CreateMessage(USSDRequest request) {
		USSDResponse response = null;
		boolean flag = false;
		boolean flag1 = false;

		logger.info("***User Id inside DAO class******** ::" + request.getUserId());

		try {

			flag1 = CreateSMSMenuName(request);
			if (flag1) {
				String menuId = getMenuId(request);
				flag = CreateSMSMessage(request, menuId);
			}

			response = new USSDResponse();
			if (flag == true) {
				response.setStatusCode("200");
				response.setStatus("success");

			} else {
				response.setStatusCode("500");
				response.setStatus("failure");
			}

		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Exception Raised in the try Block of CreateMessage:" + e);
			e.printStackTrace();

		}

		return response;
	}

	public boolean CreateSMSMenuName(USSDRequest request) {

		Session session = null;
		Transaction txn = null;
		boolean flag = false;

		try {
			logger.info("Menu_Name ::" + request.getMenuName());
			logger.info("Camp_Id ::" + request.getCampId());
			logger.info("User_Id ::" + request.getUserId());

			session = sessionFactory.getCurrentSession();
			txn = session.beginTransaction();
			String sql = "INSERT INTO USSD_MENU_DETAILS (MENU_NAME,CAMP_ID,CREATE_USER) VALUES ('"
					+ request.getMenuName() + "','" + request.getCampId() + "','" + request.getUserId() + "')";
			logger.info("[sql] : " + sql);
			Query qry = session.createSQLQuery(sql);
			int i = qry.executeUpdate();
			if (i > 0)
				flag = true;

			if (txn.getStatus().equals(TransactionStatus.ACTIVE)) {
				txn.commit();
			}

			logger.info("[flag]" + flag);
		} catch (Exception e) {
			logger.error("Exception Raised in the try Block of CreateSMSMenuName:" + e);
			e.printStackTrace();
			txn.rollback();

		}
		return flag;

	}
	
	private String getMenuId(USSDRequest request) {
		logger.info("[getMenu Id ]");

		Session session = null;
		String menuId = "";
		String sql = null;
		Transaction txn = null;
		try {

			System.out.println("============ Menu Id =============" + request.getMenuName());

			session = sessionFactory.getCurrentSession();
			txn = session.beginTransaction();

			 
			   sql = "SELECT MENU_ID,MENU_NAME FROM USSD_MENU_DETAILS WHERE MENU_NAME = '" + request.getMenuName() + "'"; 
			
			logger.info("[sql] : " + sql);
			Query qry = session.createSQLQuery(sql);

			List<Object[]> results = qry.list();
			if (results != null && results.size() > 0) {

				for (Object[] aRow : results) {
					menuId = aRow[0].toString();
				}

			}

			System.out.println("menuId*****" + menuId);
			if (txn.getStatus().equals(TransactionStatus.ACTIVE)) {
				txn.commit();
			}

		} catch (Exception e) {
			// genericDTO.setStatus("1");
			logger.error("Exception Raised in the try Block of getMenuId:" + e);
			e.printStackTrace();
			txn.rollback();

		}

		return menuId;
	}

	
	private boolean CreateSMSMessage(USSDRequest request, String menuId) {

		Transaction txn = null;
		boolean flag = false;
		Session session = null;
		String sql = "";

		try {
			session = sessionFactory.getCurrentSession();

			List<GetSMSMessages> list = request.getMessages();
			int i = 0;
			System.out.println("list" + list.size());

			for (i = 0; i < list.size(); i++) {

				txn = session.beginTransaction();

				System.out.println("in for loop get lang id" + request.getMessages().get(i).getLangId());

				System.out.println("in for loop get get Message" + request.getMessages().get(i).getMessage());
				String langId = request.getMessages().get(i).getLangId();
				String message = request.getMessages().get(i).getMessage();

				System.out.println("in for loop --[" + i + "] " + message + "  " + langId);
				sql = "INSERT INTO USSD_MENU_MSG_DETAILS (MENU_ID,LANGUAGE_ID,MESSAGE,USER_ID) VALUES" + " ('" + menuId
						+ "','" + langId + "','" + message + "','" + request.getUserId() + "')";
				System.out.println("sql: " + sql);
				Query qry = session.createSQLQuery(sql);

				int j = qry.executeUpdate();
				System.out.println("jth value " + j);
				if (j > 0) {
					flag = true;
				}

				
				/*
				 * if (txn.getStatus().equals(TransactionStatus.ACTIVE)) { txn.commit(); }
				 */
				 
				/*
				 * if (!txn.wasCommitted()) txn.commit();
				 */
			}
			System.out.println("flag value " + flag);

		} catch (Exception e) {
			logger.error("Exception Raised in the try Block of CreateSMSMessage:" + e);
			e.printStackTrace();
			//txn.rollback();

		}

		return flag;
	}
	
	@Override
	public USSDResponse GetMessage(USSDRequest request) {
		USSDResponse response = null;

		try {

			List<GetSMSLangMessages> msglist = GetSMSMessage(request);
			response = new USSDResponse();
			response.setMessages(msglist);

		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Exception Raised in the try Block of GetMessage:" + e);
			e.printStackTrace();

		}

		return response;
	}

	private List<GetSMSLangMessages> GetSMSMessage(USSDRequest request) {

		Session session = sessionFactory.getCurrentSession();

		List<Object[]> rs = null;
		Map<Integer, String> map = null;
		map=GetLanguage();

		String hql = "SELECT SMM.language_id,SMM.message,SM.menuId, SM.menuName FROM UssdMsgMenuDetailsTO SMM,GetSMSMenusTO SM WHERE SM.menuId=SMM.menu_id AND SMM.menu_id =" + request.getMenuId() + ")";

		rs = (List<Object[]>) session.createQuery(hql).list();

		List<GetSMSLangMessages> response = new ArrayList<>();

		for (Object[] obj : rs) {
			GetSMSLangMessages getSMSLangMessages = new GetSMSLangMessages();
            
			
			getSMSLangMessages.setLangId(obj[0]+ "");
			getSMSLangMessages.setMessage(obj[1] + "");
			getSMSLangMessages.setLangName(map.get(obj[0]));
			getSMSLangMessages.setMenuId(obj[2] + "");
			getSMSLangMessages.setMenuName(obj[3] + "");
			response.add(getSMSLangMessages);

		}

		return response;
	}
	
	public Map<Integer, String> GetLanguage() {

		Map<Integer, String> LangMap = new HashMap<Integer, String>();
		Session session = sessionFactory.getCurrentSession();

		List<Object[]> rs = null;

		String hql = "SELECT langId, langName FROM Language";

		rs = (List<Object[]>) session.createQuery(hql).list();

		for (Object[] obj : rs) {

			Integer l_Id = (Integer) obj[0];
			String l_Name = (String) obj[1];
			LangMap.put(l_Id, l_Name);

		}

		return LangMap;
	}
	

	@Override
	public USSDResponse GetLang(USSDRequest request) {

		USSDResponse response = null;

		try {

			List<GetLanguages> langList = GetLanguages();
			response = new USSDResponse();
			response.setLanguages(langList);

		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Exception Raised in the try Block of GetLang:" + e);
			e.printStackTrace();

		}

		return response;

	}

	private List<GetLanguages> GetLanguages() {
		Session session = sessionFactory.getCurrentSession();

		List<Object[]> rs = null;

		String hql = "SELECT langId, langName FROM GetLanguages";

		rs = (List<Object[]>) session.createQuery(hql).list();

		List<GetLanguages> response = new ArrayList<>();

		for (Object[] obj : rs) {
			GetLanguages getLanguages = new GetLanguages();

			getLanguages.setLangId(obj[0] + "");
			getLanguages.setLangName(obj[1] + "");
			response.add(getLanguages);

		}

		return response;
	}

	@Override
	public USSDResponse DelMenu(USSDRequest request) {

		USSDResponse response = null;
		boolean flag = false;

		try {

			flag = DelMenuName(request);
			response = new USSDResponse();
			if (flag == true) {
				response.setStatusCode("200");
				response.setStatus("success");

			} else {
				response.setStatusCode("500");
				response.setStatus("failure");
			}

		} catch (Exception e) {
			logger.error("Exception Raised in the try Block of DelMenu:" + e);
			e.printStackTrace();

		}

		return response;

	}

	private boolean DelMenuName(USSDRequest request) {

		boolean flag = false;

		try {

			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();

			String hql = "DELETE FROM GetSMSMenusTO WHERE menuId=" + request.getMenuId();
			Query query = session.createQuery(hql);
			int row1 = query.executeUpdate();

			String hql1 = "DELETE FROM UssdMsgMenuDetailsTO WHERE menu_id=" + request.getMenuId();
			Query query1 = session.createQuery(hql1);
			int row2 = query1.executeUpdate();
			
			logger.info("*** Value of row1 **** ::"+row1);
			logger.info("*** Value of row2 ****  ::"+row2);

			if (row1 > 0 & row2 > 0) {
				flag = true;
			}
			
			logger.info("Value of logger inside del method ::"+flag);

		} catch (Exception e) {
			logger.error("Exception Raised in the try Block of DelMenuName:" + e);
			e.printStackTrace();
		}

		return flag;
	}

	@Override
	public USSDResponse EditMessage(USSDRequest request) {

		USSDResponse response = null;
		boolean flag = false;

		try {

			flag = EditSMSMenuName(request);
			flag = EditSMSMessage(request);
			response = new USSDResponse();
			if (flag == true) {
				response.setStatusCode("200");
				response.setStatus("success");

			} else {
				response.setStatusCode("500");
				response.setStatus("failure");
			}

		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Exception Raised in the try Block of EditMessage:" + e);
			e.printStackTrace();

		}

		return response;

	}

	private boolean EditSMSMenuName(USSDRequest request) {

		boolean flag = false;

		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();

		String hql = "UPDATE GetSMSMenusTO SET menuName='" + request.getMenuName() + "'WHERE menuId='"
				+ request.getMenuId() + "' AND campId='" + request.getCampId() + "'AND createUser='"
				+ request.getUserId() + "'";

		Query query = session.createQuery(hql);
		int res = query.executeUpdate();
		if (res > 0) {
			flag = true;
		}

		return flag;
	}

	private boolean EditSMSMessage(USSDRequest request) {

		boolean flag = false;
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();

		String hql = "DELETE FROM UssdMsgMenuDetailsTO WHERE menu_id=" + request.getMenuId();
		Query query = session.createQuery(hql);
		int row1 = query.executeUpdate();

		List list = request.getMessages();
		for (int j = 0; j < list.size(); j++) {

			GetSMSMessages getSMSMessages = (GetSMSMessages) list.get(j);
			String sql ="INSERT INTO USSD_MENU_MSG_DETAILS (MENU_ID,LANGUAGE_ID,MESSAGE,USER_ID) VALUES"
			 		+ " ('"+request.getMenuId()+"','"+getSMSMessages.getLangId()+"','"+getSMSMessages.getMessage()+"','"+request.getUserId()+"')";
			
			logger.info("sql] : " + sql);
			Query query1 = session.createSQLQuery(sql);
			int i = query1.executeUpdate();
			System.out.println("============ i =============" + i);
			if (i > 0) {
				flag = true;
			}

			logger.info("[flag]" + flag);

		}

		return flag;
	}


}
