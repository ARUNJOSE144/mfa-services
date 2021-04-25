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

import com.sixdee.magik.services.dao.USSDCampaignScreenDAO;
import com.sixdee.magik.services.model.Language;
import com.sixdee.magik.services.model.LanguageTransientTO;
import com.sixdee.magik.services.model.RequestBodyTO;
import com.sixdee.magik.services.model.USSDDetailsCategoryTO;
import com.sixdee.magik.services.model.USSDDetailsTemplateDtlsTO;
import com.sixdee.magik.services.model.USSDDetailsTemplateTO;


/**
 * @author ABHIRAM MACHIRAJU
 * @Date : January, 2021
 */

@SuppressWarnings({ "unchecked", "deprecation" })
@Repository
public class USSDCampaignScreenDAOImpl implements USSDCampaignScreenDAO {
	
	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;
	
	
	protected Session currentSession()
	{
		return sessionFactory.getCurrentSession();
	}


	@Override
	public List<USSDDetailsCategoryTO> getCategoriesList(int userId) {
		Criteria cr = currentSession().createCriteria(USSDDetailsCategoryTO.class);
		cr.addOrder(Order.desc("autoId"));
		if(userId!=1)
		{
			cr.add(Restrictions.eq("createdUserId",userId));
		}
		List<USSDDetailsCategoryTO> list = cr.list();

		try 
		{
			int iterate=0;
			for(USSDDetailsCategoryTO parentObj :list)
			{
				if(parentObj.getUssdTemplateTO().size()>0)
				{
					
					Hibernate.initialize(parentObj.getUssdTemplateTO());
					if(parentObj.getUssdTemplateTO().get(iterate).getUssdDetailsTemplatedtlsTO().size()>0)
						Hibernate.initialize(parentObj.getUssdTemplateTO().get(iterate).getUssdDetailsTemplatedtlsTO());
					
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
				Criteria cr = currentSession().createCriteria(USSDDetailsTemplateTO.class);
				cr.add(Restrictions.eq("ussdDetailsCategoryTO.autoId",campId));
				List<USSDDetailsTemplateTO> list = cr.list();
				for(USSDDetailsTemplateTO parentObject : list)
				{
					if(parentObject.getUssdDetailsTemplatedtlsTO().size()>0)
					{
						for(USSDDetailsTemplateDtlsTO childObject:parentObject.getUssdDetailsTemplatedtlsTO())
						{
							currentSession().delete(childObject);
						}
					}
					currentSession().delete(parentObject);
					
				}
				String query = " from USSDDetailsCategoryTO where autoId = '" +campId+ "'";
				USSDDetailsCategoryTO ussdDetailsCategoryTO = (USSDDetailsCategoryTO)currentSession().createQuery(query).uniqueResult();
				if(ussdDetailsCategoryTO!=null)
				{
					currentSession().delete(ussdDetailsCategoryTO);
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
						Criteria cr = currentSession().createCriteria(USSDDetailsTemplateTO.class);
						cr.add(Restrictions.eq("autoId",menuId));
						List<USSDDetailsTemplateTO> list = cr.list();
						for(USSDDetailsTemplateTO parentObject : list)
						{
							if(parentObject.getUssdDetailsTemplatedtlsTO().size()>0)
							{
								for(USSDDetailsTemplateDtlsTO childObject:parentObject.getUssdDetailsTemplatedtlsTO())
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
	public List<USSDDetailsTemplateTO> getTemplateRecord(int userId, int categoryId) {
		
				Criteria cr = currentSession().createCriteria(USSDDetailsTemplateTO.class);
				cr.addOrder(Order.desc("autoId"));
				cr.add(Restrictions.eq("ussdDetailsCategoryTO.autoId",categoryId));
				if(userId!=1)
				{
					cr.add(Restrictions.eq("createdUserId",userId));
				}
				List<USSDDetailsTemplateTO> list = cr.list();
		
				try 
				{
					for(USSDDetailsTemplateTO parentObj :list)
					{
						 
						Hibernate.initialize(parentObj.getUssdDetailsTemplatedtlsTO());
						 
						
					}
					
				}catch (Exception e) {e.printStackTrace();}
				
				return list;
	}

	@Override
	public List<USSDDetailsTemplateTO> getSelectedTemplate_Detials(int menuId) {
		Criteria cr = currentSession().createCriteria(USSDDetailsTemplateTO.class);
		cr.addOrder(Order.desc("autoId"));
		cr.add(Restrictions.eq("autoId",menuId));
		List<USSDDetailsTemplateTO> list = cr.list();
		return list;
	}

	@Override
	public String saveorUpdateCategoryData(USSDDetailsCategoryTO categoryTO) {
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
					    USSDDetailsTemplateTO parentObject = new USSDDetailsTemplateTO();
					    parentObject.setMessageName(requestBodyTO.getMessage());
					    parentObject.setCreatedUserId(Integer.parseInt(requestBodyTO.getUserId()));
					    
					    USSDDetailsCategoryTO categoryTO = new USSDDetailsCategoryTO();
					    categoryTO.setAutoId(requestBodyTO.getCategoryId());
					    parentObject.setUssdDetailsCategoryTO(categoryTO);
					    
					    currentSession().saveOrUpdate(parentObject);
					    
					    if(requestBodyTO.getLanguageList().size()>0)
					    {
					    	for(LanguageTransientTO langObj:requestBodyTO.getLanguageList())
					    	{
					    		
					    		//Child Table  
					    		  USSDDetailsTemplateDtlsTO childObject = new USSDDetailsTemplateDtlsTO();
					    		  Language messageLanguagesTO = new Language();
								  messageLanguagesTO.setLangId(Integer.parseInt(langObj.getLangValue()));
								  
								  childObject.setLangagueTO(messageLanguagesTO);
								  childObject.setMessageContent(langObj.getLangDescprition());
								  childObject.setUssdDetailsTemplateTO(parentObject);
								  currentSession().saveOrUpdate(childObject);
					    	}
									
					    }
				}
				else
				{
				 //EditTime...............................................
					String query = " from USSDDetailsTemplateTO where autoId = '" +requestBodyTO.getMessageAutoId()+ "'";
					USSDDetailsTemplateTO templateDetailsTO_DB =null;
					List<USSDDetailsTemplateDtlsTO>  messageDetailsList =new ArrayList<>();
					
					   templateDetailsTO_DB = (USSDDetailsTemplateTO)currentSession().createQuery(query).uniqueResult();
						if(templateDetailsTO_DB!=null)
						{
							//whatsAppDetailsTO_DB.setAutoId(messageAutoId);
							templateDetailsTO_DB.setMessageName(requestBodyTO.getMessage());
							templateDetailsTO_DB.setCreatedUserId(Integer.parseInt(requestBodyTO.getUserId()));
						    currentSession().saveOrUpdate(templateDetailsTO_DB);
						    
							Hibernate.initialize(templateDetailsTO_DB.getUssdDetailsTemplatedtlsTO());
							
							
						if(requestBodyTO.getLanguageList().size()>0)
						 {
							
							//Remove first -- de-selected Option..............
								for(int i=0;i<templateDetailsTO_DB.getUssdDetailsTemplatedtlsTO().size();i++)
								{
									for(int j=0;j<requestBodyTO.getLanguageList().size();j++)
									{
									   if(    templateDetailsTO_DB.getUssdDetailsTemplatedtlsTO().get(i).getLangagueTO().getLangId()!= 
											    Integer.parseInt(requestBodyTO.getLanguageList().get(j).getLangValue()))
									   {
										   currentSession().delete(templateDetailsTO_DB.getUssdDetailsTemplatedtlsTO().get(i));
									   }
										
									}
								}
							
							
						   for(LanguageTransientTO langObj :requestBodyTO.getLanguageList())  
						   {
							   String queryStr = "from USSDDetailsTemplateDtlsTO where ussdDetailsTemplateTO = '" +requestBodyTO.getMessageAutoId()
									              +"' AND langagueTO = '"+langObj.getLangValue()+"'";
							  
							   USSDDetailsTemplateDtlsTO templateChild_MessageDetailsTO_DB =(USSDDetailsTemplateDtlsTO)currentSession().createQuery(queryStr).uniqueResult();
							   
							   
							   if(templateChild_MessageDetailsTO_DB!=null)
								   {
								              Language messageLanguagesTO = new Language();
											  messageLanguagesTO.setLangId(Integer.parseInt(langObj.getLangValue()));
											  
											        templateChild_MessageDetailsTO_DB.setLangagueTO(messageLanguagesTO);
											        templateChild_MessageDetailsTO_DB.setMessageContent(langObj.getLangDescprition());
											        templateChild_MessageDetailsTO_DB.setUssdDetailsTemplateTO(templateDetailsTO_DB);
												    currentSession().saveOrUpdate(templateChild_MessageDetailsTO_DB);
												  
								   }
								   else
								   {
									            USSDDetailsTemplateDtlsTO templateChild_MessageDetailsTO_UI = new USSDDetailsTemplateDtlsTO();
									            Language messageLanguagesTO = new Language();
										        messageLanguagesTO.setLangId(Integer.parseInt(langObj.getLangValue()));
										        
										        templateChild_MessageDetailsTO_UI.setLangagueTO(messageLanguagesTO);
										        templateChild_MessageDetailsTO_UI.setMessageContent(langObj.getLangDescprition());
										        templateChild_MessageDetailsTO_UI.setUssdDetailsTemplateTO(templateDetailsTO_DB);
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
