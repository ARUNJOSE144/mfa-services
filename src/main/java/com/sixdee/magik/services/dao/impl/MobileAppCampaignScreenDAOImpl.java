package com.sixdee.magik.services.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.sixdee.magik.services.dao.MobileAppCampaignScreenDAO;
import com.sixdee.magik.services.model.Language;
import com.sixdee.magik.services.model.MOBILE_APP_CategoryTO;
import com.sixdee.magik.services.model.MOBILE_APP_MGS_DTLS_RequestBody;
import com.sixdee.magik.services.model.MOBILE_APP_RequestBody;
import com.sixdee.magik.services.model.MOBILE_APP_TemplateDtlsTO;
import com.sixdee.magik.services.model.MOBILE_APP_TemplateTO;

/**
 * @author ABHIRAM MACHIRAJU
 * @Date : January, 2021
 */

@SuppressWarnings({ "unchecked", "deprecation" })
@Repository
public class MobileAppCampaignScreenDAOImpl implements MobileAppCampaignScreenDAO {
	
	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;
	
	
	protected Session currentSession()
	{
		return sessionFactory.getCurrentSession();
	}


	@Override
	public List<MOBILE_APP_CategoryTO> getCategoriesList(int userId) {
		Criteria cr = currentSession().createCriteria(MOBILE_APP_CategoryTO.class);
		cr.addOrder(Order.desc("autoId"));
		if(userId!=1)
		{
			cr.add(Restrictions.eq("createdUserId",userId));
		}
		List<MOBILE_APP_CategoryTO> list = cr.list();

		try 
		{
			int iterate=0;
			for(MOBILE_APP_CategoryTO parentObj :list)
			{
				if(parentObj.getMobileAppTemplateTO().size()>0)
				{
					
					Hibernate.initialize(parentObj.getMobileAppTemplateTO());
					if(parentObj.getMobileAppTemplateTO().get(iterate).getMobileApp_TemplateDtlsTO().size()>0)
						Hibernate.initialize(parentObj.getMobileAppTemplateTO().get(iterate).getMobileApp_TemplateDtlsTO());
					
					iterate++;
				}
			}
			
		}
		catch (Exception e) {e.printStackTrace();}
		
		return list;
	}


	@Override
	public String deleteCategory(int campId) {
		 String  statusCode = "SC00001";
			try 
			{
				Criteria cr = currentSession().createCriteria(MOBILE_APP_TemplateTO.class);
				cr.add(Restrictions.eq("mobileAppCategoryTO.autoId",campId));
				List<MOBILE_APP_TemplateTO> list = cr.list();
				for(MOBILE_APP_TemplateTO parentObject : list)
				{
					if(parentObject.getMobileApp_TemplateDtlsTO().size()>0)
					{
						for(MOBILE_APP_TemplateDtlsTO childObject:parentObject.getMobileApp_TemplateDtlsTO())
						{
							currentSession().delete(childObject);
						}
					}
					currentSession().delete(parentObject);
					
				}
				String query = " from MOBILE_APP_CategoryTO where autoId = '" +campId+ "'";
				MOBILE_APP_CategoryTO mobileAppCategoryTO = (MOBILE_APP_CategoryTO)currentSession().createQuery(query).uniqueResult();
				if(mobileAppCategoryTO!=null)
				{
					currentSession().delete(mobileAppCategoryTO);
				}
				
				statusCode = "SC0000";	
			 }
			  catch (Exception e) {
					e.printStackTrace();
				}
				
				return statusCode;
	}


	@Override
	public String deleteMenu(int menuId) {
		 String  statusCode = "SC00001";
		  try 
			{
				Criteria cr = currentSession().createCriteria(MOBILE_APP_TemplateTO.class);
				cr.add(Restrictions.eq("autoId",menuId));
				List<MOBILE_APP_TemplateTO> list = cr.list();
				for(MOBILE_APP_TemplateTO parentObject : list)
				{
					if(parentObject.getMobileApp_TemplateDtlsTO().size()>0)
					{
						for(MOBILE_APP_TemplateDtlsTO childObject:parentObject.getMobileApp_TemplateDtlsTO())
						{
							currentSession().delete(childObject);
						}
					}
					currentSession().delete(parentObject);
					
				}
				
				statusCode = "SC0000";	
			 }
			  catch (Exception e) {
					e.printStackTrace();
				}
		  return statusCode;
	}


	@Override
	public List<MOBILE_APP_TemplateTO> getTemplateRecord(int userId, int categoryId) {
		Criteria cr = currentSession().createCriteria(MOBILE_APP_TemplateTO.class);
		cr.addOrder(Order.desc("autoId"));
		cr.add(Restrictions.eq("mobileAppCategoryTO.autoId",categoryId));
		if(userId!=1)
		{
			cr.add(Restrictions.eq("createdUserId",userId));
		}
		List<MOBILE_APP_TemplateTO> list = cr.list();

		try 
		{
			for(MOBILE_APP_TemplateTO parentObj :list)
			{
				 
				Hibernate.initialize(parentObj.getMobileApp_TemplateDtlsTO());
				 
				
			}
			
		}catch (Exception e) {e.printStackTrace();}
		
		return list;
	}


	@Override
	public List<MOBILE_APP_TemplateTO> getSelectedTemplate_Detials(int menuId) {
		Criteria cr = currentSession().createCriteria(MOBILE_APP_TemplateTO.class);
		cr.addOrder(Order.desc("autoId"));
		cr.add(Restrictions.eq("autoId",menuId));
		List<MOBILE_APP_TemplateTO> list = cr.list();
		
		 List<MOBILE_APP_TemplateDtlsTO> inboundMessages = new ArrayList<>();
		 List<MOBILE_APP_TemplateDtlsTO> outBoundMessages = new ArrayList<>();
		if(list.size()>0)
		{
			for(MOBILE_APP_TemplateTO parentobj : list)
			{
				if(parentobj.getMobileApp_TemplateDtlsTO().size()>0)
				{
					for(MOBILE_APP_TemplateDtlsTO childObj:parentobj.getMobileApp_TemplateDtlsTO())
						{
						     
						      if(childObj.getMessageType().equals("IN_BOUND"))
						      {
						    	  inboundMessages.add(childObj);
						      }
						      else
						      {
						    	  outBoundMessages.add(childObj);
						      }
						      
							
						}
					
				}
				parentobj.setInboundMessages(inboundMessages);
				parentobj.setOutBoundMessages(outBoundMessages);
				
			}
			
			
		}
		return list;
	}


	@Override
	public String saveorUpdateCategoryData(MOBILE_APP_CategoryTO categoryTO) {
		String  statusCode = "SC00001";
		try 
		{
				
				currentSession().saveOrUpdate(categoryTO);
			    statusCode = "SC0000";
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	return statusCode;
	}


	@Override
	public String saveorUpdateMsgDetailsData(MOBILE_APP_RequestBody requestBody) {
		String  statusCode = "SC00001";
		try 
		{
			//ParentObject
			MOBILE_APP_TemplateTO parentObj = new MOBILE_APP_TemplateTO();
			
			if(requestBody.getMessageId() != null) { parentObj.setAutoId(Integer.parseInt(requestBody.getMessageId())); }
			
			parentObj.setMessageName(requestBody.getMessageName());
			parentObj.setCreatedUserId(Integer.parseInt(requestBody.getUserId()));
			
			//GrandParentObject
			MOBILE_APP_CategoryTO grandParentObj = new MOBILE_APP_CategoryTO();
			grandParentObj.setAutoId(Integer.parseInt(requestBody.getCategoryAutoId()));
			
			parentObj.setMobileAppCategoryTO(grandParentObj);
			
			currentSession().saveOrUpdate(parentObj);
			
			
			//ChildObject's
			if(requestBody.getInboundMessages().size()>0)
				{
					for(MOBILE_APP_MGS_DTLS_RequestBody  requestObj_1: requestBody.getInboundMessages())
							{
								MOBILE_APP_TemplateDtlsTO childobj_1 = new MOBILE_APP_TemplateDtlsTO();
								
								if(requestObj_1.getMsgDtlsId() != null) { childobj_1.setAutoId(Integer.parseInt(requestObj_1.getMsgDtlsId())); }
								
								Language langTO = new Language();
								langTO.setLangId(Integer.parseInt(requestObj_1.getLanguageAutoId()));
								childobj_1.setLangagueTO(langTO);
								
								childobj_1.setMobileAppTemplateTO(parentObj);
								
								childobj_1.setMessageContent(requestObj_1.getMessage());
								childobj_1.setTitle(requestObj_1.getTitle());
								childobj_1.setUssdSenderId(requestObj_1.getUssdSenderId());
								childobj_1.setAppApiLink(requestObj_1.getAppApiLink());
								childobj_1.setMessageType(requestObj_1.getMessageType());
								childobj_1.setDeepLink(requestObj_1.getDeepLink());
								childobj_1.setCampaignType(requestObj_1.getCampaignType());
								childobj_1.setPriority(requestObj_1.getPriority());
								childobj_1.setStartDate(requestObj_1.getStartDate());
								childobj_1.setEndDate(requestObj_1.getEndDate());
								currentSession().saveOrUpdate(childobj_1);
							 }
						
					}
				if(requestBody.getOutBoundmessages().size()>0)
					{
						for(MOBILE_APP_MGS_DTLS_RequestBody  requestObj_2: requestBody.getOutBoundmessages())
							{
								MOBILE_APP_TemplateDtlsTO childobj_2 = new MOBILE_APP_TemplateDtlsTO();
								
								if(requestObj_2.getMsgDtlsId() != null) { childobj_2.setAutoId(Integer.parseInt(requestObj_2.getMsgDtlsId())); }
								
								Language langTO = new Language();
								langTO.setLangId(Integer.parseInt(requestObj_2.getLanguageAutoId()));
								childobj_2.setLangagueTO(langTO);
								
								childobj_2.setMobileAppTemplateTO(parentObj);
								
								childobj_2.setMessageContent(requestObj_2.getMessage());
								childobj_2.setTitle(requestObj_2.getTitle());
								childobj_2.setUssdSenderId(requestObj_2.getUssdSenderId());
								childobj_2.setAppApiLink(requestObj_2.getAppApiLink());
								childobj_2.setMessageType(requestObj_2.getMessageType());
								childobj_2.setDeepLink(requestObj_2.getDeepLink());
								childobj_2.setCampaignType(requestObj_2.getCampaignType());
								childobj_2.setPriority(requestObj_2.getPriority());
								childobj_2.setStartDate(requestObj_2.getStartDate());
								childobj_2.setEndDate(requestObj_2.getEndDate());
								childobj_2.setWebApiLink(requestObj_2.getWebApiLink());
								currentSession().saveOrUpdate(childobj_2);
								
							}
					}
				statusCode = "SC0000";	
			  }catch (Exception e) {e.printStackTrace();}
		     return statusCode;
				
	}
	
}
			
