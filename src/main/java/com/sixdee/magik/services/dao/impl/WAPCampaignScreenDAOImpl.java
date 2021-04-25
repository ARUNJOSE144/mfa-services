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

import com.sixdee.magik.services.dao.WAPCampaignScreenDAO;
import com.sixdee.magik.services.model.Language;
import com.sixdee.magik.services.model.LanguageTransientTO;
import com.sixdee.magik.services.model.RequestBodyTO;
import com.sixdee.magik.services.model.WAPDetailsCategoryTO;
import com.sixdee.magik.services.model.WAPDetailsTemplateDtlsTO;
import com.sixdee.magik.services.model.WAPDetailsTemplateTO;

/**
 * @author ABHIRAM MACHIRAJU
 * @Date : January, 2021
 */

@SuppressWarnings({ "unchecked", "deprecation" })
@Repository
public class WAPCampaignScreenDAOImpl implements WAPCampaignScreenDAO {
	
	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;
	
	
	protected Session currentSession()
	{
		return sessionFactory.getCurrentSession();
	}


	@Override
	public List<WAPDetailsCategoryTO> getCategoriesList(int userId) {
		
		Criteria cr = currentSession().createCriteria(WAPDetailsCategoryTO.class);
		cr.addOrder(Order.desc("autoId"));
		if(userId!=1)
		{
			cr.add(Restrictions.eq("createdUserId",userId));
		}
		List<WAPDetailsCategoryTO> list = cr.list();

		try 
		{
			int iterate=0;
			for(WAPDetailsCategoryTO parentObj :list)
			{
				if(parentObj.getWapTemplateTO().size()>0)
				{
					
					Hibernate.initialize(parentObj.getWapTemplateTO());
					if(parentObj.getWapTemplateTO().get(iterate).getWapDetailsTemplateDtlsTO().size()>0)
						Hibernate.initialize(parentObj.getWapTemplateTO().get(iterate).getWapDetailsTemplateDtlsTO());
					
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
				Criteria cr = currentSession().createCriteria(WAPDetailsTemplateTO.class);
				cr.add(Restrictions.eq("wapDetailsCategoryTO.autoId",campId));
				List<WAPDetailsTemplateTO> list = cr.list();
				for(WAPDetailsTemplateTO parentObject : list)
				{
					if(parentObject.getWapDetailsTemplateDtlsTO().size()>0)
					{
						for(WAPDetailsTemplateDtlsTO childObject:parentObject.getWapDetailsTemplateDtlsTO())
						{
							currentSession().delete(childObject);
						}
					}
					currentSession().delete(parentObject);
					
				}
				String query = " from WAPDetailsCategoryTO where autoId = '" +campId+ "'";
				WAPDetailsCategoryTO wapDetailsCategoryTO = (WAPDetailsCategoryTO)currentSession().createQuery(query).uniqueResult();
				if(wapDetailsCategoryTO!=null)
				{
					currentSession().delete(wapDetailsCategoryTO);
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
				Criteria cr = currentSession().createCriteria(WAPDetailsTemplateTO.class);
				cr.add(Restrictions.eq("autoId",menuId));
				List<WAPDetailsTemplateTO> list = cr.list();
				for(WAPDetailsTemplateTO parentObject : list)
				{
					if(parentObject.getWapDetailsTemplateDtlsTO().size()>0)
					{
						for(WAPDetailsTemplateDtlsTO childObject:parentObject.getWapDetailsTemplateDtlsTO())
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
	public List<WAPDetailsTemplateTO> getTemplateRecord(int userId, int categoryId) {
		
		Criteria cr = currentSession().createCriteria(WAPDetailsTemplateTO.class);
		cr.addOrder(Order.desc("autoId"));
		cr.add(Restrictions.eq("wapDetailsCategoryTO.autoId",categoryId));
		if(userId!=1)
		{
			cr.add(Restrictions.eq("createdUserId",userId));
		}
		List<WAPDetailsTemplateTO> list = cr.list();

		try 
		{
			for(WAPDetailsTemplateTO parentObj :list)
			{
				 
				Hibernate.initialize(parentObj.getWapDetailsTemplateDtlsTO());
				 
				
			}
			
		}catch (Exception e) {e.printStackTrace();}
		
		return list;
	}


	@Override
	public List<WAPDetailsTemplateTO> getSelectedTemplate_Detials(int menuId) {
		Criteria cr = currentSession().createCriteria(WAPDetailsTemplateTO.class);
		cr.addOrder(Order.desc("autoId"));
		cr.add(Restrictions.eq("autoId",menuId));
		List<WAPDetailsTemplateTO> list = cr.list();
		return list;
	}


	@Override
	public String saveorUpdateCategoryData(WAPDetailsCategoryTO categoryTO) {
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
	public String saveorUpdateTemplateData(RequestBodyTO requestBodyTO) {
		 String  statusCode = "SC00001";
			try 
			{
				//Create Time...................................
				if(requestBodyTO.getMessageAutoId()== null)
				{
					//Parent Table
					    WAPDetailsTemplateTO parentObject = new WAPDetailsTemplateTO();
					    parentObject.setMessageName(requestBodyTO.getMessage());
					    parentObject.setCreatedUserId(Integer.parseInt(requestBodyTO.getUserId()));
					    
					    WAPDetailsCategoryTO categoryTO = new WAPDetailsCategoryTO();
					    categoryTO.setAutoId(requestBodyTO.getCategoryId());
					    parentObject.setWapDetailsCategoryTO(categoryTO);
					    
					    currentSession().saveOrUpdate(parentObject);
					    
					    if(requestBodyTO.getLanguageList().size()>0)
					    {
					    	for(LanguageTransientTO langObj:requestBodyTO.getLanguageList())
					    	{
					    		
					    		//Child Table  
					    		  WAPDetailsTemplateDtlsTO childObject = new WAPDetailsTemplateDtlsTO();
					    		  Language messageLanguagesTO = new Language();
								  messageLanguagesTO.setLangId(Integer.parseInt(langObj.getLangValue()));
								  
								  childObject.setLangagueTO(messageLanguagesTO);
								  childObject.setMessageContent(langObj.getLangDescprition());
								  childObject.setWapDetailsTemplateTO(parentObject);
								  currentSession().saveOrUpdate(childObject);
					    	}
									
					    }
				}
				else
				{
				 //EditTime...............................................
					String query = " from WAPDetailsTemplateTO where autoId = '" +requestBodyTO.getMessageAutoId()+ "'";
					WAPDetailsTemplateTO templateDetailsTO_DB =null;
					List<WAPDetailsTemplateDtlsTO>  messageDetailsList =new ArrayList<>();
					
					   templateDetailsTO_DB = (WAPDetailsTemplateTO)currentSession().createQuery(query).uniqueResult();
						if(templateDetailsTO_DB!=null)
						{
							//whatsAppDetailsTO_DB.setAutoId(messageAutoId);
							templateDetailsTO_DB.setMessageName(requestBodyTO.getMessage());
							templateDetailsTO_DB.setCreatedUserId(Integer.parseInt(requestBodyTO.getUserId()));
						    currentSession().saveOrUpdate(templateDetailsTO_DB);
						    
							Hibernate.initialize(templateDetailsTO_DB.getWapDetailsTemplateDtlsTO());
							
							
						if(requestBodyTO.getLanguageList().size()>0)
						 {
							
							//Remove first -- de-selected Option..............
								for(int i=0;i<templateDetailsTO_DB.getWapDetailsTemplateDtlsTO().size();i++)
								{
									for(int j=0;j<requestBodyTO.getLanguageList().size();j++)
									{
									   if(    templateDetailsTO_DB.getWapDetailsTemplateDtlsTO().get(i).getLangagueTO().getLangId()!= 
											    Integer.parseInt(requestBodyTO.getLanguageList().get(j).getLangValue()))
									   {
										   currentSession().delete(templateDetailsTO_DB.getWapDetailsTemplateDtlsTO().get(i));
									   }
										
									}
								}
							
							
						   for(LanguageTransientTO langObj :requestBodyTO.getLanguageList())  
						   {
							   String queryStr = "from WAPDetailsTemplateDtlsTO where wapDetailsTemplateTO = '" +requestBodyTO.getMessageAutoId()
									              +"' AND langagueTO = '"+langObj.getLangValue()+"'";
							  
							   WAPDetailsTemplateDtlsTO templateChild_MessageDetailsTO_DB =(WAPDetailsTemplateDtlsTO)currentSession().createQuery(queryStr).uniqueResult();
							   
							   
							   if(templateChild_MessageDetailsTO_DB!=null)
								   {
								              Language messageLanguagesTO = new Language();
											  messageLanguagesTO.setLangId(Integer.parseInt(langObj.getLangValue()));
											  
											        templateChild_MessageDetailsTO_DB.setLangagueTO(messageLanguagesTO);
											        templateChild_MessageDetailsTO_DB.setMessageContent(langObj.getLangDescprition());
											        templateChild_MessageDetailsTO_DB.setWapDetailsTemplateTO(templateDetailsTO_DB);
												    currentSession().saveOrUpdate(templateChild_MessageDetailsTO_DB);
												  
								   }
								   else
								   {
									            WAPDetailsTemplateDtlsTO templateChild_MessageDetailsTO_UI = new WAPDetailsTemplateDtlsTO();
									            Language messageLanguagesTO = new Language();
										        messageLanguagesTO.setLangId(Integer.parseInt(langObj.getLangValue()));
										        
										        templateChild_MessageDetailsTO_UI.setLangagueTO(messageLanguagesTO);
										        templateChild_MessageDetailsTO_UI.setMessageContent(langObj.getLangDescprition());
										        templateChild_MessageDetailsTO_UI.setWapDetailsTemplateTO(templateDetailsTO_DB);
										        currentSession().saveOrUpdate(templateChild_MessageDetailsTO_UI);
									   
								   }
									   
						   }
								   
							   }
						}	   
					
				}
				statusCode = "SC0000";	
			  }
				catch (Exception e) {
					e.printStackTrace();
				}
				
				return statusCode;
	}

}
