package com.sixdee.magik.services.dao.impl;



import java.nio.channels.SeekableByteChannel;
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

import com.sixdee.magik.services.dao.SmsTreeDAO;
import com.sixdee.magik.services.model.SMSLangMessagesTO;
import com.sixdee.magik.services.model.SMSLanguagesTO;
import com.sixdee.magik.services.model.SMSMenusTO;
import com.sixdee.magik.services.model.SMSMessagesTO;
import com.sixdee.magik.services.model.SMSRequest;
import com.sixdee.magik.services.model.SMSResponse;
import com.sixdee.magik.services.model.SmsCategoryTO;
import com.sixdee.magik.services.model.WhatsAppDetailsTO;

@Repository
@Transactional
public class SmsTreeDAOImpl implements SmsTreeDAO {
	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;
	static final Logger logger = Logger.getLogger(SmsTreeDAOImpl.class);
	
	@Override
	public List<SmsCategoryTO> getCategory(int userId) {
		
		Session session=sessionFactory.getCurrentSession();
		@SuppressWarnings({ "unchecked", "deprecation" })
		//List<SmsCategoryTO> list = (List<SmsCategoryTO>) session.createCriteria(SmsCategoryTO.class).list();
		Criteria cr = session.createCriteria(SmsCategoryTO.class);
		if(userId!=1)
		{
			cr.add(Restrictions.eq("createdUserId",userId));
		}
		List<SmsCategoryTO> list = cr.list();

		
		return list;
	}

	@Override
	public SMSResponse createCategory(SMSRequest req) {
		SMSResponse resp = new SMSResponse();
		boolean flag=false;
		
		try{
			int category_order=getMaxCategoryOrder();
			flag=createSMSCategory(req,category_order);
			
			if(flag==true)
			{
				resp.setStatusCode("200");
				resp.setStatus("success");
				
			}
			else
			{
				resp.setStatusCode("500");
				resp.setStatus("failure");
			}
			
			
				
		}
		catch (Exception e) {
			logger.error("Exception Raised in the try Block of CreateMessage:" + e);
			e.printStackTrace();
			
		}
		
		return resp;
	}

	private int getMaxCategoryOrder() {
		// TODO Auto-generated method stub
		int categoryOrder=0;
		String sql="";
		Transaction txn;
		Session session;
		try {
			session=sessionFactory.getCurrentSession();
			
			System.out.println("Inside getMaxCategoryOrder::");
			
			sql="SELECT MAX(CATEGORY_ORDER),CATEGORY_NAME FROM CATEGORY_MASTER";
			
			System.out.println("getMaxCategoryOrder sql:: "+sql);
			
			Query qry=session.createSQLQuery(sql);
			
			@SuppressWarnings("unchecked")
			List<Object[]> results = qry.list();
			if(results!= null && results.size()>0) {
				for (Object[] aRow : results) {
					categoryOrder=(int) aRow[0];
				}
			}
			
			categoryOrder=categoryOrder+1;
			
			logger.info(" value of catagort_order Id::"+categoryOrder);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return categoryOrder;
	}

	private boolean createSMSCategory(SMSRequest req, int category_order) {
		boolean flag=false;
		String sql="";
		Transaction txn;
		Session session;
		try {
			
			System.out.println("category name:: "+req.getCampName());
			session = sessionFactory.getCurrentSession();
			txn=session.beginTransaction();
			sql="INSERT INTO CATEGORY_MASTER(CATEGORY_NAME,STATUS,CATEGORY_ORDER,CREATED_BY) VALUES ('"+req.getCampName()+"','T','"+category_order+"','"+req.getUserId()+"')";
			
			logger.info("createCategorySMS-->"+sql);
			
			
			Query qry=session.createSQLQuery(sql);
			int rs=qry.executeUpdate();
			if(rs>0) {
				flag=true;
			}
			System.out.println("flag in createSMSCategory:: "+ flag);
			//session.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return flag;
	}

	@Override
	public SMSResponse editCategory(SMSRequest req) {
		boolean flag=false;
		SMSResponse resp=new SMSResponse();
		
		try {
			flag=editSMSCategory(req);
			if(flag==true)
			{
				resp.setStatusCode("200");
				resp.setStatus("success");
				
			}
			else
			{
				resp.setStatusCode("500");
				resp.setStatus("failure");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return resp;
	}

	
	public boolean editSMSCategory(SMSRequest req) {
		boolean flag=false;
		String sql="";
		Transaction txn;
		Session session;
		try {
			
			System.out.println("category name:: "+req.getCampName());
			System.out.println("category name:: "+req.getCampId());
			session = sessionFactory.getCurrentSession();
			txn=session.beginTransaction();
			
//			sql="DELETE FROM CATEGORY_MASTER WHERE CATEGORY_ID='"+req.getCampId() +"'";
//			
//			logger.info("deleteCategorySMS-->"+sql);
//			
//			Query qry=session.createSQLQuery(sql);
//			int rs=qry.executeUpdate();
		
			
			
			
			 sql="UPDATE CATEGORY_MASTER SET CATEGORY_NAME = '"+req.getCampName()+"',CREATED_BY='"+req.getUserId()+"' WHERE CATEGORY_ID='"+req.getCampId() +"'";
			  
			  logger.info("editCategorySMS-->"+sql);
			  
			  Query qry=session.createSQLQuery(sql); 
			  int rs=qry.executeUpdate();
			 
			if(rs>0) {
				flag=true;
//				int category_order=getMaxCategoryOrder();
//				flag=createSMSCategory(req,category_order);
			}
			System.out.println("flag in editCategorySMS:: "+ flag);
			//session.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return flag;
	}
	
	@Override
	public SMSResponse delCategory(SMSRequest req) {
		boolean flag=false;
		SMSResponse resp=new SMSResponse();
		
		try {
			flag=delSMSCategory(req);
			if(flag==true)
			{
				resp.setStatusCode("200");
				resp.setStatus("success");
				
			}
			else
			{
				resp.setStatusCode("500");
				resp.setStatus("failure");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return resp;
	}

	private boolean delSMSCategory(SMSRequest req) {boolean flag=false;
	String sql="";
	Transaction txn;
	Session session;
	try {
		
		System.out.println("category name:: "+req.getCampName());
		System.out.println("category name:: "+req.getCampId());
		session = sessionFactory.getCurrentSession();
		txn=session.beginTransaction();

		sql="DELETE FROM CATEGORY_MASTER WHERE CATEGORY_ID='"+req.getCampId() +"'";
		
		logger.info("deleteCategorySMS-->"+sql);
		
		Query qry=session.createSQLQuery(sql);
		int rs=qry.executeUpdate();
		//txn.commit();
		
		if(rs > 0) {
			flag=true;
		}
		
		System.out.println("flag in deleteCategorySMS:: "+ flag);

	}catch(Exception e) {
		e.printStackTrace();
	}
	
	
	return flag;
	
	}

	@Override
	public SMSResponse getLanguage(SMSRequest req) {
		SMSResponse resp=new SMSResponse();
		try {
		Session session=sessionFactory.getCurrentSession();
		@SuppressWarnings({ "unchecked", "deprecation" })
		List<SMSLanguagesTO> list = (List<SMSLanguagesTO>) session.createCriteria(SMSLanguagesTO.class).list();
		resp.setLanguages(list);
		System.out.println("language List::"+resp.getLanguages().toString());
		}catch (Exception e) {
			e.printStackTrace();
		}
		return resp;
	}

	@Override
	public SMSResponse getMenuName(SMSRequest req) {
		SMSResponse resp=new SMSResponse();
		String sql="";
		Query qry=null;
		SMSMenusTO dto;
		List menuList=new ArrayList();
		try {
		Session session=sessionFactory.getCurrentSession();

		if(Integer.parseInt(req.getUserId())!=1)
		{
			
			sql = "SELECT MENU_ID,MENU_NAME FROM SMS_MENU_DETAILS WHERE CAMP_ID ='"+req.getCampId()+"'AND CREATE_USER='"+req.getUserId()+"'";
		}
		else
		{
			sql = "SELECT MENU_ID,MENU_NAME FROM SMS_MENU_DETAILS WHERE CAMP_ID ="+req.getCampId();
		}
		 logger.info("[sql] : "+sql);
		 qry= session.createSQLQuery(sql);
		 List<Object[]> results=qry.list();
		
			if(results!= null && results.size()>0) {
				for (Object[] aRow : results) {
						dto = new SMSMenusTO();
						dto.setMenuId(Integer.parseInt(aRow[0].toString()));
						dto.setMenuName(aRow[1].toString());
						menuList.add(dto);
				}
			}
			 resp.setMenus(menuList);
			 System.out.println("Menu LIST>>> "+resp.getMenus().toString());
		 
		}catch (Exception e) {
			e.printStackTrace();
		}
		return resp;
	}

	@Override
	public SMSResponse getMessage(SMSRequest req) {
		SMSResponse resp = null; 
		boolean flag = false;
		
		
		try{
			
			List msglist = GetSMSMessage(req);
			resp = new SMSResponse();
			resp.setMessages(msglist);
			
				
		}
		catch (Exception e) {
			// TODO: handle exception
			logger.error("Exception Raised in the try Block of GetMessage:" + e);
			e.printStackTrace();
			
		}
		
		return resp;
	}

	private List GetSMSMessage(SMSRequest request) {

		
		logger.info("[GetCampaignDao : GetSMSMessage() ]");
		

		Session session=null;
		//List<String> offerList=null;
		List<SMSLangMessagesTO>menuList = new ArrayList<SMSLangMessagesTO>();
		String sql = null;
		Transaction txn=null;
		SMSLangMessagesTO dto = null;
		HashMap<Integer, String> map = null;
		try {
			
			 map = GetLanguage();
			
			 System.out.println("============ Menu Id ============="+request.getMenuId());
			
			 session=sessionFactory.getCurrentSession();
			 txn=session.beginTransaction();
			
			 sql = "SELECT LANGUAGE_ID,MESSAGE,SM.MENU_ID,MENU_NAME FROM SMS_MENU_MSG_DETAILS SMM,SMS_MENU_DETAILS SM WHERE SM.MENU_ID=SMM.MENU_ID AND SMM.MENU_ID = "+request.getMenuId();
			 logger.info("[sql] : "+sql);
			 
			 Query qry=session.createSQLQuery(sql);
			 List<Object[]> result=qry.list();
			 if(result!=null && result.size() > 0) {
				 for(Object[] aRow:result) {
				 dto = new SMSLangMessagesTO();
				 dto.setLangId(aRow[0].toString());
				 dto.setMessage(getUtf8(aRow[1].toString()));
				 dto.setLangName(map.get(Integer.parseInt(aRow[0].toString())));
				 dto.setMenuId(aRow[2].toString());
				 dto.setMenuName(aRow[3].toString());
				 
				 menuList.add(dto);
				 }
			 }
			// txn.commit();
			 
			// logger.info("[offer_Details table list size]"+cpsOffersList.size());
			 
		     
		} catch (Exception e) {
			//genericDTO.setStatus("1");
			logger.error("Exception Raised in the try Block of GetSMSMessage:" + e);
			e.printStackTrace();

		} 
		return menuList;

	}

	private HashMap<Integer, String> GetLanguage() {
		logger.info("[GetCampaignDao : GetLanguage() ]");
		

		Session session=null;
		String sql = null;
		Transaction txn=null;
		HashMap<Integer, String> LangMap = new HashMap<Integer, String>();
		try {
			
			 //System.out.println("============ Menu Id ============="+request.getMenuId());
			
			 session=sessionFactory.getCurrentSession();
			// txn=session.beginTransaction();
			 
			
			 sql = "SELECT ID,LANGUAGE FROM LANGUAGE";
			 logger.info("[sql] : "+sql);
			 Query qry=session.createSQLQuery(sql);
			 List<Object[]> result=qry.list();
			 if(result!= null && result.size()>0) {
					for (Object[] aRow : result) {
				 LangMap.put(Integer.parseInt(aRow[0].toString()), aRow[1].toString());
			 }
			 }
			 //txn.commit();
		} catch (Exception e) {
			//genericDTO.setStatus("1");
			logger.error("Exception Raised in the try Block of GetLanguage:" + e);
			e.printStackTrace();

		}
		return LangMap;
	}

	@Override
	public SMSResponse createMessage(SMSRequest req) {
		SMSResponse resp = null; 
		boolean flag = false,flag1=false;
		
		
		try{
			flag1=CreateSMSMenuName(req);
			if(flag1) {
			String menuId = getMenuId(req);
			flag = CreateSMSMessage(req,menuId);
			resp = new SMSResponse();
			}
			if(flag==true)
			{
				resp.setStatusCode("200");
				resp.setStatus("success");
				
			}
			else
			{
				resp.setStatusCode("500");
				resp.setStatus("failure");
			}
			
			
				
		}
		catch (Exception e) {
			// TODO: handle exception
			logger.error("Exception Raised in the try Block of CreateMessage:" + e);
			e.printStackTrace();
			
		}
		
		return resp;
	}

	private String getMenuId(SMSRequest req) {
		logger.info("[getMenu Id ]");
		

		Session session=null;
		String menuId="";
		String sql = null;
		
		try {
			
			 System.out.println("============ Menu Id ============="+req.getMenuName());
			
			 session=sessionFactory.getCurrentSession();
			
			 sql = "SELECT MENU_ID,MENU_NAME FROM SMS_MENU_DETAILS WHERE MENU_NAME = '"+req.getMenuName()+"'";
			 logger.info("[sql] : "+sql);
			 Query qry=session.createSQLQuery(sql);
			
			 List<Object[]> results = qry.list();
				if(results!= null && results.size()>0) {
					for (Object[] aRow : results) {
						 menuId=aRow[0].toString();
					}
				
				}
				
				System.out.println("menuId*****"+menuId);
			 
			 
		     
		} catch (Exception e) {
			//genericDTO.setStatus("1");
			logger.error("Exception Raised in the try Block of GetSMSMessage:" + e);
			e.printStackTrace();

		}
		
		return menuId;
	}

	private boolean CreateSMSMessage(SMSRequest req, String menuId) {
		// TODO Auto-generated method stub
		
		Transaction txn=null;
		boolean flag = false;
		Session session=null;
		String sql="";
	
		try {

			
			logger.info("[GetCampaignDao : CreateSMSMessage() ]");
			
			
				
				 session=sessionFactory.getCurrentSession();
				
				 
				List list = req.getMessages();
				System.out.println("list Size"+list.size());
				for(int j=0;j<list.size();j++)
				{
					SMSMessagesTO dto = (SMSMessagesTO)list.get(j);
					 txn=session.beginTransaction();
					System.out.println("get j"+ j);
				
				 sql = "INSERT INTO SMS_MENU_MSG_DETAILS (MENU_ID,LANGUAGE_ID,MESSAGE,USER_ID) VALUES"
				 		+ " ('"+menuId+"','"+dto.getLangId()+"','"+getHex(dto.getMessage())+"','"+req.getUserId()+"')";
				 logger.info("[sql] : "+sql);
				 Query qry=session.createSQLQuery(sql);
				 int i = qry.executeUpdate();
				 System.out.println("============ i ============="+i);
				 if(i>0)
				 {
					flag = true;
				 }
				 //txn.commit();
				 
				logger.info("[flag]"+flag);
				 
				}
			     
			} catch (Exception e) {
				//genericDTO.setStatus("1");
				logger.error("Exception Raised in the try Block of CreateSMSMenuName:" + e);
				e.printStackTrace();

			}
			

		
		return flag;
	}

	private boolean CreateSMSMenuName(SMSRequest req) {
		// TODO Auto-generated method stub
		logger.info("[GetCampaignDao : CreateSMSMenuName() ]");
		

		Session session=null;
		Transaction txn=null;
		boolean flag = false;
		String menuId = null;

		try {
			
			 System.out.println("============ User Id ============="+req.getUserId());
			 System.out.println("============ Camp Id ============="+req.getCampId());
			 System.out.println("-------menu name----"+req.getMenuName());
			
			 session=sessionFactory.getCurrentSession();
			 txn=session.beginTransaction();
			
			  String sql = "INSERT INTO SMS_MENU_DETAILS (MENU_NAME,CAMP_ID,CREATE_USER) VALUES ('"+req.getMenuName()+"','"+req.getCampId()+"','"+req.getUserId()+"')";
			 logger.info("[sql] : "+sql);
			 Query qry=session.createSQLQuery(sql);
			 int i=qry.executeUpdate();
			 if(i>0)
				 flag=true;
			 
			 //txn.commit();
			 
			logger.info("[flag]"+flag);
			 
		     
		} catch (Exception e) {
			//genericDTO.setStatus("1");
			logger.error("Exception Raised in the try Block of CreateSMSMenuName:" + e);
			e.printStackTrace();

		} 
		return flag;
	}

	@Override
	public SMSResponse editMessage(SMSRequest req) {

		SMSResponse resp = null; 
		boolean flag = false;
		
		
		try{
			
			flag = EditSMSMenuName(req);
			flag = EditSMSMessage(req);
			resp = new SMSResponse();
			if(flag==true)
			{
				resp.setStatusCode("200");
				resp.setStatus("success");
				
			}
			else
			{
				resp.setStatusCode("500");
				resp.setStatus("failure");
			}
			
			
				
		}
		catch (Exception e) {
			// TODO: handle exception
			logger.error("Exception Raised in the try Block of EditMessage:" + e);
			e.printStackTrace();
			
		}
		
		return resp;
		
}

	private boolean EditSMSMessage(SMSRequest request) {
		logger.info("[GetCampaignDao : EditSMSMessage() ]");
		

		Session session=null;
		String sql = null;
		Transaction txn=null;
		boolean flag = false;

		try {
			
			 session=sessionFactory.getCurrentSession();
			txn=session.beginTransaction();
			
			 sql = "DELETE FROM SMS_MENU_MSG_DETAILS WHERE MENU_ID ="+request.getMenuId();
			 logger.info("[sql] : "+sql);
			Query qry=session.createSQLQuery(sql);
			qry.executeUpdate();
			 
			List list = request.getMessages();
			for(int j=0;j<list.size();j++)
			{
				 txn=session.beginTransaction();
				
			SMSMessagesTO dto = (SMSMessagesTO)list.get(j);
			 
			
			 sql = "INSERT INTO SMS_MENU_MSG_DETAILS (MENU_ID,LANGUAGE_ID,MESSAGE,USER_ID) VALUES"
			 		+ " ('"+request.getMenuId()+"','"+dto.getLangId()+"','"+getHex(dto.getMessage())+"','"+request.getUserId()+"')";
			 logger.info("[sql] : "+sql);
			 Query qry1=session.createSQLQuery(sql);
			 int i = qry1.executeUpdate();
			 System.out.println("============ i ============="+i);
			 if(i>0)
			 {
				flag = true;
			 }
			// txn.commit();
			 
			logger.info("[flag]"+flag);
			 
			}
		     
		} catch (Exception e) {
			//genericDTO.setStatus("1");
			logger.error("Exception Raised in the try Block of EditSMSMessage:" + e);
			e.printStackTrace();

		}
		return flag;
	}

	private boolean EditSMSMenuName(SMSRequest request) {
		logger.info("[GetCampaignDao : EditSMSMenuName() ]");
		

		Session session=null;
		
		String sql = null;
		
		Transaction txn=null;
		boolean flag = false;
		//String menuId = null;

		try {
			
			 System.out.println("============ User Id ============="+request.getUserId());
			 System.out.println("============ Camp Id ============="+request.getCampId());
			
			 session=sessionFactory.getCurrentSession();
			 txn=session.beginTransaction();
			
			
			 sql = "UPDATE SMS_MENU_DETAILS SET MENU_NAME = '"+request.getMenuName()+"' WHERE MENU_ID ='"+request.getMenuId()+"' AND CAMP_ID = '"+request.getCampId()+"' AND CREATE_USER ='"+request.getUserId()+"' ";
			 logger.info("[sql] : "+sql);
			 Query qry=session.createSQLQuery(sql);
			 int i = qry.executeUpdate();
			 System.out.println("============ i ============="+i);
			 if(i>0)
				 flag = true;
			 
			// txn.commit();
			 
			logger.info("[flag]"+flag);
			 
		     
		} catch (Exception e) {
			//genericDTO.setStatus("1");
			logger.error("Exception Raised in the try Block of EditSMSMenuName:" + e);
			e.printStackTrace();

		}
		return flag;
	}

	@Override
	public SMSResponse delMenu(SMSRequest req) {
		boolean flag = false;
		SMSResponse resp=new SMSResponse();;
		
		try{
			
			flag = DelMenuName(req);
			
			if(flag==true)
			{
				resp.setStatusCode("200");
				resp.setStatus("success");
				
			}
			else
			{
				resp.setStatusCode("500");
				resp.setStatus("failure");
			}
			
				
		}
		catch (Exception e) {
			// TODO: handle exception
			logger.error("Exception Raised in the try Block of DelMenu:" + e);
			e.printStackTrace();
			
		}
		
		return resp;
	}

	private boolean DelMenuName(SMSRequest req) {

		logger.info("[GetCampaignDao : DelMenuName() ]");
		

		Session session=null;
		//List<String> offerList=null;
		String sql = null;
		
		Transaction txn=null;
		boolean flag = false;
		
		try {
			
			 System.out.println("============ Menu Id ============="+req.getMenuId());
			
			 session=sessionFactory.getCurrentSession();
			 txn=session.beginTransaction();
			 
			
			 sql = "DELETE FROM SMS_MENU_DETAILS WHERE MENU_ID ="+req.getMenuId();
			 logger.info("[sql] : "+sql);
			 Query qry=session.createSQLQuery(sql);
			 qry.executeUpdate();
			
			 sql = "DELETE FROM SMS_MENU_MSG_DETAILS WHERE MENU_ID ="+req.getMenuId();
			 logger.info("[sql] : "+sql);
			 
			 Query qry1=session.createSQLQuery(sql);
			 qry1.executeUpdate();
			 
			 flag = true;
			// txn.commit();
			 
			 
		     
		} catch (Exception e) {
			//genericDTO.setStatus("1");
			logger.error("Exception Raised in the try Block of DelMenuName:" + e);
			e.printStackTrace();

		}
		return flag;
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
		System.out.println("============ decoded text==============="+decoded);
		return decoded;
	}

	private static String getHex(String parameter) {
		 // TODO Auto-generated method stub
		 
		 String value = "";
		 
		 try {
		  for(int i=0;i<parameter.length();i++){
		   char a = parameter.charAt(i);
		   
		   if(Character.isDigit(a) || ((int) a >=65 && (int)a <=90) || ((int) a >=97 && (int)a <=122) ||a==' '){
		    value += a;
		   }else{
		    value += "&#"+(int)a+";";
		   }
		  }
		 } catch (Exception e) {
		  // TODO: handle exception
		  e.printStackTrace();
		 }
		 
		 System.out.println("============= value =============="+value);
		 
		 return value;
		}
	
	
	//==============================previous======================================
	
//	@Override
//	public SmsTreeDTO smsTree(SmsTreeDTO smsTreeDTO) {
//		// TODO Auto-generated method stub
//		return smsTreeDTO;
//	}
	
//	@Override
//	public List<CategoryTO> getCategory() {
//		// TODO Auto-generated method stub
//		
//			Session session=sessionFactory.getCurrentSession();
//			@SuppressWarnings({ "unchecked", "deprecation" })
//			List<CategoryTO> list = (List<CategoryTO>) session.createCriteria(CategoryTO.class).list();
//
//			return list;
//	}
//
//	
//	@Override
//	public List<SmsTreeDTO> getMenuName(SmsTreeDTO smsMenuTO) {
//		Session session = null;
//		List list=new ArrayList();
//		List<GetSMSMenus> menuList = new ArrayList<GetSMSMenus>();;
//		GetSMSMenus getsmsmenu1=new GetSMSMenus();
//		try {
//			session = sessionFactory.getCurrentSession();
//			System.out.println("camp Id"+smsMenuTO.getCampId());
//			if(smsMenuTO.getCampId()!=null) {
//			String sql = "SELECT MENU_ID,MENU_NAME FROM SMS_MENU_DETAILS WHERE CAMP_ID =:campId ";
//			Query query = session.createSQLQuery(sql);
//			query.setParameter("campId", smsMenuTO.getCampId());
//			@SuppressWarnings("unchecked")
//			List<Object[]> results = query.list();
//			if(results!= null && results.size()>0) {
//				//menuList = new ArrayList<GetSMSMenus>();
//				for (Object[] aRow : results) {
//					GetSMSMenus dto=new GetSMSMenus();
//					dto.setMenuId(aRow[0].toString());
//					dto.setMenuName(aRow[1].toString());
//					
//					menuList.add(dto);
//				}
//			}
//			list=menuList;
//			smsMenuTO.setMenus(menuList);
//			}
//			System.out.println("List--->"+list.size());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return list;
//	}
//
//
//	@Override
//	public SmsTreeDTO createMessage(SmsTreeDTO smsMenuDTO) {
//		// TODO Auto-generated method stub
//		boolean flag=false;
//		boolean flag1=false;
//		try{
//			
//			flag1 = CreateSMSMenuName(smsMenuDTO);
//			if(flag1) {
//				String menuId=getMenuId(smsMenuDTO);
//			flag = CreateSMSMessage(smsMenuDTO,menuId);
//			smsMenuDTO = new SmsTreeDTO();
//			}
//			if(flag==true)
//			{
//				smsMenuDTO.setStatusCode("200");
//				smsMenuDTO.setStatus("success");
//				
//			}
//			else
//			{
//				smsMenuDTO.setStatusCode("500");
//				smsMenuDTO.setStatus("failure");
//			}
//			
//			
//				
//		}
//		catch (Exception e) {
//			// TODO: handle exception
//			logger.error("Exception Raised in the try Block of CreateMessage:" + e);
//			e.printStackTrace();
//			
//		}
//		return smsMenuDTO;
//	}
//
//	private String getMenuId(SmsTreeDTO smsMenuDTO) {
//logger.info("[getMenu Id ]");
//		
//
//		Session session=null;
//		String menuId="";
//		String sql = null;
//		
//		try {
//			
//			 System.out.println("============ Menu Id ============="+smsMenuDTO.getMenuName());
//			
//			 session=sessionFactory.getCurrentSession();
//			// txn=session.beginTransaction();
//			 
//			
//			 //sql = "SELECT LANGUAGE_ID,MESSAGE FROM SMS_MENU_MSG_DETAILS WHERE MENU_ID ="+request.getMenuId();
//			 sql = "SELECT MENU_ID,MENU_NAME FROM SMS_MENU_DETAILS WHERE MENU_NAME = '"+smsMenuDTO.getMenuName()+"'";
//			 logger.info("[sql] : "+sql);
//			 Query qry=session.createSQLQuery(sql);
//			
//			 List<Object[]> results = qry.list();
//				if(results!= null && results.size()>0) {
//					//menuList = new ArrayList<GetSMSMenus>();
//					for (Object[] aRow : results) {
//						 menuId=aRow[0].toString();
//					}
//				
//				}
//				
//				System.out.println("menuId*****"+menuId);
//			 
//			 
//		     
//		} catch (Exception e) {
//			//genericDTO.setStatus("1");
//			logger.error("Exception Raised in the try Block of GetSMSMessage:" + e);
//			e.printStackTrace();
//
//		}
//		
//		return menuId;
//	}
//
//
//	private boolean CreateSMSMenuName(SmsTreeDTO smsMenuDTO) {
//		// TODO Auto-generated method stub
//		logger.info("[GetCampaignDao : CreateSMSMenuName() ]");
//		
//
//		Session session=null;
//		Transaction txn=null;
//		boolean flag = false;
//		String menuId = null;
//
//		try {
//			
//			 System.out.println("============ User Id ============="+smsMenuDTO.getUserId());
//			 System.out.println("============ Camp Id ============="+smsMenuDTO.getCampId());
//			 System.out.println("-------menu name----"+smsMenuDTO.getMenuName());
//			
//			 session=sessionFactory.getCurrentSession();
//			 txn=session.beginTransaction();
//			
//			  String sql = "INSERT INTO SMS_MENU_DETAILS (MENU_NAME,CAMP_ID,CREATE_USER) VALUES ('"+smsMenuDTO.getMenuName()+"','"+smsMenuDTO.getCampId()+"','"+smsMenuDTO.getUserId()+"')";
//			 logger.info("[sql] : "+sql);
//			 Query qry=session.createSQLQuery(sql);
//			 int i=qry.executeUpdate();
//			 if(i>0)
//				 flag=true;
//			
//			
//				//System.out.println("menuId--------->"+menuId);
//			 txn.commit();
//			 
//			logger.info("[flag]"+flag);
//			 
//		     
//		} catch (Exception e) {
//			//genericDTO.setStatus("1");
//			logger.error("Exception Raised in the try Block of CreateSMSMenuName:" + e);
//			e.printStackTrace();
//
//		} 
//		return flag;
//	}
//
//
//	private boolean CreateSMSMessage(SmsTreeDTO smsMenuDTO, String menuId) {
//		// TODO Auto-generated method stub
//		
//		Transaction txn=null;
//		boolean flag = false;
//		Session session=null;
//		String sql="";
//	
//		try {
//			session =sessionFactory.getCurrentSession();
//			//txn=session.beginTransaction();
//			//session.getTransaction().begin();
//			List list=smsMenuDTO.getGetmessages();
//			int i=0;
//			System.out.println("list"+list.size());
//			//System.err.println("i<list.size()"+(i<list.size()));
//			for(i=0; i<list.size(); i++) {
//				
//				txn=session.beginTransaction();
//				//System.err.println("i<list.size()"+(GetSMSMessages)list.get(i));
//				System.out.println("in for loop get lang id"
//				+smsMenuDTO.getGetmessages().get(i).getLangId());
//				
//				System.out.println("in for loop get get Message"+
//				smsMenuDTO.getGetmessages().get(i).getMessage());
//				String langId=smsMenuDTO.getGetmessages().get(i).getLangId();
//				String message=getHex(smsMenuDTO.getGetmessages().get(i).getMessage());
//				
//				//GetSMSMessages dto = (GetSMSMessages)list.get(i);
//				System.out.println("in for loop --["+i+"] "+message+"  "+langId);
//				sql = "INSERT INTO SMS_MENU_MSG_DETAILS (MENU_ID,LANGUAGE_ID,MESSAGE,USER_ID) VALUES"
//				 		+ " ('"+menuId+"','"+langId+"','"+message+"','"+smsMenuDTO.getUserId()+"')";
//				System.out.println("sql: "+sql);
//				Query qry=session.createSQLQuery(sql);
//				
//				/*
//				 * qry.setParameter(1, menuId); qry.setParameter(2,
//				 * smsMenuDTO.getGetmessages().get(i).getLangId()); qry.setParameter(3,
//				 * getHex(smsMenuDTO.getGetmessages().get(i).getMessage())); qry.setParameter(4,
//				 * smsMenuDTO.getUserId());
//				 */
//				
//				int j=qry.executeUpdate();
//				System.out.println("jth value "+j);
//				if(j>0) {
//					flag=true;
//				}
//				txn.commit();
//				
//			}
//			System.out.println("flag value "+flag);
//			//txn.commit();
//		}catch(Exception e) {
//			
//		}
//		
//		return flag;
//	}
//
//
//	private static String getHex(String parameter) {
//		 // TODO Auto-generated method stub
//		 
//		 String value = "";
//		 
//		 try {
//		  for(int i=0;i<parameter.length();i++){
//		   char a = parameter.charAt(i);
//		   
//		   if(Character.isDigit(a) || ((int) a >=65 && (int)a <=90) || ((int) a >=97 && (int)a <=122) ||a==' '){
//		    value += a;
//		   }else{
//		    value += "&#"+(int)a+";";
//		   }
//		  }
//		 } catch (Exception e) {
//		  // TODO: handle exception
//		  e.printStackTrace();
//		 }
//		 
//		 System.out.println("============= value =============="+value);
//		 
//		 return value;
//		}
//
//
//	@Override
//	public List<SmsTreeDTO> getMessege(SmsTreeDTO smsMenuTO) {
//
//		
//		logger.info("[GetCampaignDao : GetSMSMessage() ]");
//		
//
//		Session session=null;
//		
//		List<GetSMSLangMessages>menuList = new ArrayList<GetSMSLangMessages>();
//		List msgList=new ArrayList();
//		String sql = null;
//		
//		Transaction txn=null;
//		GetSMSLangMessages dto = null;
//		HashMap<Integer, String> map = null;
//		try {
//			
//			 map = GetLanguage();
//			
//			 System.out.println("============ Menu Id ============="+smsMenuTO.getMenuId());
//			
//			 session=sessionFactory.getCurrentSession();
//			// txn=session.beginTransaction();
//			 
//			
//			 //sql = "SELECT LANGUAGE_ID,MESSAGE FROM SMS_MENU_MSG_DETAILS WHERE MENU_ID ="+request.getMenuId();
//			 sql = "SELECT LANGUAGE_ID,MESSAGE,SM.MENU_ID,MENU_NAME FROM SMS_MENU_MSG_DETAILS SMM,SMS_MENU_DETAILS SM WHERE SM.MENU_ID=SMM.MENU_ID AND SMM.MENU_ID = "+smsMenuTO.getMenuId();
//			 logger.info("[sql] : "+sql);
//			 Query qry=session.createSQLQuery(sql);
//			
//			 List<Object[]> results = qry.list();
//				if(results!= null && results.size()>0) {
//					//menuList = new ArrayList<GetSMSMenus>();
//					for (Object[] aRow : results) {
//						 dto = new GetSMSLangMessages();
//						dto.setMenuId(aRow[0].toString());
//						dto.setMenuName(aRow[1].toString());
//						
//						dto.setLangId(aRow[0].toString());
//						 dto.setMessage(getUtf8(aRow[1].toString()));
//						 dto.setLangName(map.get(Integer.parseInt(aRow[0].toString())));
//						 dto.setMenuId(aRow[2].toString());
//						 dto.setMenuName(aRow[3].toString());
//						
//						menuList.add(dto);
//					}
//					msgList=menuList;
//				}
//				
//				smsMenuTO.setGetmessages(menuList);
//				System.out.println("messageList*****"+msgList.size());
//			 //txn.commit();
//			 
//			 
//		     
//		} catch (Exception e) {
//			//genericDTO.setStatus("1");
//			logger.error("Exception Raised in the try Block of GetSMSMessage:" + e);
//			e.printStackTrace();
//
//		}
//		return msgList;
//
//	}
//
//
//	private String getUtf8(String word) {
//		String decoded = "";
//		for (Integer index = 0; index < word.length(); index++) {
//			String charAt = "" + word.charAt(index);
//			if (charAt.equals("&") && index < word.length() && ("" + word.charAt(index + 1)).equals("#")) {
//				try {
//					Integer length = word.indexOf(";", index);
//					String sub = word.substring(index + 2, length);
//					decoded += Character.toString((char) Integer.parseInt(sub));
//					index = length;
//				} catch (Exception ex) {
//					decoded += charAt;
//				}
//			} else {
//				decoded += charAt;
//			}
//		}
//		System.out.println("============ decoded text==============="+decoded);
//		return decoded;
//	}
//
//
//	private HashMap<Integer, String> GetLanguage() {
//
//		logger.info("[GetCampaignDao : GetLanguage() ]");
//		
//
//		Session session=null;
//		String sql = null;
//		Transaction txn=null;
//		HashMap<Integer, String> LangMap = new HashMap<Integer, String>();
//		try {
//			
//			 //System.out.println("============ Menu Id ============="+request.getMenuId());
//			
//			 session=sessionFactory.getCurrentSession();
//			// txn=session.beginTransaction();
//			 
//			
//			 sql = "SELECT ID,LANGUAGE FROM LANGUAGE";
//			 logger.info("[sql] : "+sql);
//			 Query qry=session.createSQLQuery(sql);
//			 List<Object[]> result=qry.list();
//			 if(result!= null && result.size()>0) {
//					for (Object[] aRow : result) {
//				 LangMap.put(Integer.parseInt(aRow[0].toString()), aRow[1].toString());
//			 }
//			 }
//			 //txn.commit();
//		} catch (Exception e) {
//			//genericDTO.setStatus("1");
//			logger.error("Exception Raised in the try Block of GetLanguage:" + e);
//			e.printStackTrace();
//
//		}
//		return LangMap;
//	}
//
//
//	@Override
//	public List<SmsTreeDTO> getLang() {
//		logger.info("[GetCampaignDao : GetSMSMessage() ]");
//		
//
//		Session session=null;
//		//List<String> offerList=null;
//		List<GetLanguages>langList = new ArrayList<GetLanguages>();
//		List lang=new ArrayList();
//		String sql = null;
//		
//		Transaction txn=null;
//		GetLanguages dto = null;
//		try {
//			
//			 //System.out.println("============ Menu Id ============="+request.getMenuId());
//			
//			 session=sessionFactory.getCurrentSession();
//			// txn=session.beginTransaction();
//			 
//			
//			 sql = "SELECT ID,LANGUAGE FROM LANGUAGE";
//			 logger.info("[sql] : "+sql);
//			
//			 Query qry=session.createSQLQuery(sql);
//			 List<Object[]> result=qry.list();
//			 if(result!=null && result.size()>0) {
//				 for(Object []aRow:result) {
//				 dto = new GetLanguages();
//					dto.setLangId(aRow[0].toString());
//					dto.setLangName(aRow[1].toString());
//					langList.add(dto); 
//				 }
//				 	lang=langList;
//			 }
//				//.setLanguages(lang);
//				System.out.println("LanguageList Size*"+lang.size());
//			// txn.commit();
//			 
//		     
//		} catch (Exception e) {
//			//genericDTO.setStatus("1");
//			logger.error("Exception Raised in the try Block of GetSMSMessage:" + e);
//			e.printStackTrace();
//
//		}
//		return lang;
//	}
//
//
//	@Override
//	public SmsTreeDTO delMenu(SmsTreeDTO smsMenuDTO) {
//		boolean flag = false;
//		
//		
//		try{
//			
//			flag = DelMenuName(smsMenuDTO);
//			
//			if(flag==true)
//			{
//				smsMenuDTO.setStatusCode("200");
//				smsMenuDTO.setStatus("success");
//				
//			}
//			else
//			{
//				smsMenuDTO.setStatusCode("500");
//				smsMenuDTO.setStatus("failure");
//			}
//			
//				
//		}
//		catch (Exception e) {
//			// TODO: handle exception
//			logger.error("Exception Raised in the try Block of DelMenu:" + e);
//			e.printStackTrace();
//			
//		}
//		
//		return smsMenuDTO;
//	}
//
//
//	private boolean DelMenuName(SmsTreeDTO smsMenuDTO) {
//		logger.info("[GetCampaignDao : DelMenuName() ]");
//		
//
//		Session session=null;
//		//List<String> offerList=null;
//		PreparedStatement pstmt = null;
//		String sql = null;
//		Connection conn = null;
//		ResultSet rs = null;
//		Transaction txn=null;
//		boolean flag = false;
//		
//		try {
//			
//			 //System.out.println("============ Menu Id ============="+request.getMenuId());
//			
//			 session=sessionFactory.getCurrentSession();
//			// txn=session.beginTransaction();
//			 
//			
//			 sql = "DELETE FROM SMS_MENU_DETAILS WHERE MENU_ID ="+smsMenuDTO.getMenuId();
//			 logger.info("[sql] : "+sql);
//			 Query qry=session.createSQLQuery(sql);
//			 qry.executeUpdate();
//			
//			 sql = "DELETE FROM SMS_MENU_MSG_DETAILS WHERE MENU_ID ="+smsMenuDTO.getMenuId();
//			 logger.info("[sql] : "+sql);
//			 
//			 Query qry1=session.createSQLQuery(sql);
//			 qry1.executeUpdate();
//			 
//			 flag = true;
//			// txn.commit();
//			 
//			// logger.info("[offer_Details table list size]"+cpsOffersList.size());
//			 
//		     
//		} catch (Exception e) {
//			//genericDTO.setStatus("1");
//			logger.error("Exception Raised in the try Block of DelMenuName:" + e);
//			e.printStackTrace();
//
//		}
//		return flag;
//	}
//
//
//	@Override
//	public SmsTreeDTO editMessage(SmsTreeDTO smsMenuDTO) {
//		boolean flag = false;
//		
//		
//		try{
//			
//			flag = EditSMSMenuName(smsMenuDTO);
//			flag = EditSMSMessage(smsMenuDTO);
//			if(flag==true)
//			{
//				smsMenuDTO.setStatusCode("200");
//				smsMenuDTO.setStatus("success");
//				
//			}
//			else
//			{
//				smsMenuDTO.setStatusCode("500");
//				smsMenuDTO.setStatus("failure");
//			}
//			
//			
//				
//		}
//		catch (Exception e) {
//			// TODO: handle exception
//			logger.error("Exception Raised in the try Block of EditMessage:" + e);
//			e.printStackTrace();
//			
//		}
//		
//		return smsMenuDTO;
//	}
//
//
//	private boolean EditSMSMessage(SmsTreeDTO smsMenuDTO) {
//		
//		logger.info("[GetCampaignDao : EditSMSMenuName() ]");
//		
//
//		Session session=null;
//		String sql = null;
//		Transaction txn=null;
//		boolean flag = false;
//		//String menuId = null;
//
//		try {
//			
//			 System.out.println("============ User Id ============="+smsMenuDTO.getUserId());
//			 System.out.println("============ Camp Id ============="+smsMenuDTO.getCampId());
//			
//			 session=sessionFactory.getCurrentSession();
//			// txn=session.beginTransaction();
//			
//			
//			 sql = "UPDATE SMS_MENU_DETAILS SET MENU_NAME = '"+smsMenuDTO.getMenuName()+"' WHERE MENU_ID ='"+smsMenuDTO.getMenuId()+"' AND CAMP_ID = '"+smsMenuDTO.getCampId()+"' AND CREATE_USER ='"+smsMenuDTO.getUserId()+"' ";
//			 logger.info("[sql] : "+sql);
//			 Query qry=session.createSQLQuery(sql);
//			 int i = qry.executeUpdate();
//			 System.out.println("============ i ============="+i);
//			 if(i>0)
//				 flag = true;
//			 
//			 //txn.commit();
//			 
//			logger.info("[flag]"+flag);
//			 
//		     
//		} catch (Exception e) {
//			//genericDTO.setStatus("1");
//			logger.error("Exception Raised in the try Block of EditSMSMenuName:" + e);
//			e.printStackTrace();
//
//		}
//			
//		return flag;
//}
//
//
//	private boolean EditSMSMenuName(SmsTreeDTO smsMenuDTO) {
//		
//		logger.info("[GetCampaignDao : EditSMSMessage() ]");
//		
//
//		Session session=null;
//		PreparedStatement pstmt = null;
//		String sql = null;
//		Connection conn = null;
//		ResultSet rs = null;
//		Transaction txn=null;
//		boolean flag = false;
//
//		try {
//			
//			 session=sessionFactory.getCurrentSession();
//			
//			 sql = "DELETE FROM SMS_MENU_MSG_DETAILS WHERE MENU_ID ="+smsMenuDTO.getMenuId();
//			 logger.info("[sql] : "+sql);
//			 Query qry=session.createSQLQuery(sql);
//			 qry.executeUpdate();
//			 
//			List list = smsMenuDTO.getMessages();
//			for(int j=0;j<list.size();j++)
//			{
//				 //txn=session.beginTransaction();
//				
//				GetSMSMessages dto = (GetSMSMessages)list.get(j);
//			 
//			
//			 sql = "INSERT INTO SMS_MENU_MSG_DETAILS (MENU_ID,LANGUAGE_ID,MESSAGE,USER_ID) VALUES"
//			 		+ " ('"+smsMenuDTO.getMenuId()+"','"+dto.getLangId()+"','"+getHex(dto.getMessage())+"','"+smsMenuDTO.getUserId()+"')";
//			 logger.info("[sql] : "+sql);
//			 Query qry1=session.createSQLQuery(sql);
//			 int i=qry1.executeUpdate();
//			 
//			 System.out.println("============ i ============="+i);
//			 if(i>0)
//			 {
//				flag = true;
//			 }
//			 //txn.commit();
//			 
//			logger.info("[flag]"+flag);
//			 
//			}
//		     
//		} catch (Exception e) {
//			//genericDTO.setStatus("1");
//			logger.error("Exception Raised in the try Block of EditSMSMessage:" + e);
//			e.printStackTrace();
//
//		}
//		return flag;
//}
//

	
}
