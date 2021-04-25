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

import com.sixdee.magik.services.dao.SMSCampaignScreenDAO;
import com.sixdee.magik.services.model.Language;
import com.sixdee.magik.services.model.LanguageTransientTO;
import com.sixdee.magik.services.model.MessageLanguagesTO;
import com.sixdee.magik.services.model.RequestBodyTO;
import com.sixdee.magik.services.model.SMSDetailsCategoryTO;
import com.sixdee.magik.services.model.SMSDetailsTemplateDtlsTO;
import com.sixdee.magik.services.model.SMSDetailsTemplateTO;
import com.sixdee.magik.services.model.SMSLanguagesTO;
import com.sixdee.magik.services.model.WhatsAppDetailsCategoryTO;
import com.sixdee.magik.services.model.WhatsAppDetailsTO;
import com.sixdee.magik.services.model.WhatsAppMessageDetailsTO;

/**
 * @author ABHIRAM MACHIRAJU
 * @Date : January, 2021
 */

@SuppressWarnings({ "unchecked", "deprecation" })
@Repository
public class SMSCampaignScreenDAOImpl implements SMSCampaignScreenDAO {
	
	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;
	
	
	protected Session currentSession()
	{
		return sessionFactory.getCurrentSession();
	}


	@Override
	public List<SMSLanguagesTO> getLanguages() {
		List<SMSLanguagesTO> list = currentSession().createCriteria(SMSLanguagesTO.class).list();
		return list;
	}


	@Override
	public String saveorUpdateCategoryData(SMSDetailsCategoryTO categoryTO) {
		
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
	public List<SMSDetailsCategoryTO> getCategoriesList(int userId) {
		Criteria cr = currentSession().createCriteria(SMSDetailsCategoryTO.class);
		cr.addOrder(Order.desc("autoId"));
		if(userId!=1)
		{
			cr.add(Restrictions.eq("createdUserId",userId));
		}
		List<SMSDetailsCategoryTO> list = cr.list();

		try 
		{
			int iterate=0;
			for(SMSDetailsCategoryTO parentObj :list)
			{
				if(parentObj.getSmsTemplateTO().size()>0)
				{
					
					Hibernate.initialize(parentObj.getSmsTemplateTO());
					if(parentObj.getSmsTemplateTO().get(iterate).getSmsDetailsTemplateDtlsTO().size()>0)
						Hibernate.initialize(parentObj.getSmsTemplateTO().get(iterate).getSmsDetailsTemplateDtlsTO());
					
					iterate++;
				}
			}
			
		}
		catch (Exception e) {e.printStackTrace();}
		
		return list;
	}


	@Override
	public List<SMSDetailsTemplateTO> getTemplateRecord(int userId, int categoryId) {
		Criteria cr = currentSession().createCriteria(SMSDetailsTemplateTO.class);
		cr.addOrder(Order.desc("autoId"));
		cr.add(Restrictions.eq("smsDetailsCategoryTO.autoId",categoryId));
		if(userId!=1)
		{
			cr.add(Restrictions.eq("createdUserId",userId));
		}
		List<SMSDetailsTemplateTO> list = cr.list();

		try 
		{
			for(SMSDetailsTemplateTO parentObj :list)
			{
				 
				Hibernate.initialize(parentObj.getSmsDetailsTemplateDtlsTO());
				 
				
			}
			
		}catch (Exception e) {e.printStackTrace();}
		
		return list;
	}


	@Override
	public List<SMSDetailsTemplateTO> getSelectedTemplate_Detials(int menuId) {
		
		Criteria cr = currentSession().createCriteria(SMSDetailsTemplateTO.class);
		cr.addOrder(Order.desc("autoId"));
		cr.add(Restrictions.eq("autoId",menuId));
		List<SMSDetailsTemplateTO> list = cr.list();
		return list;
	}


	@Override
	public String saveorUpdateSMSData(RequestBodyTO requestBodyTO) {
		 String  statusCode = "SC00001";
			try 
			{
				//Create Time...................................
				if(requestBodyTO.getMessageAutoId()== null)
				{
					//Parent Table
					    SMSDetailsTemplateTO parentObject = new SMSDetailsTemplateTO();
					    parentObject.setMessageName(requestBodyTO.getMessage());
					    parentObject.setCreatedUserId(Integer.parseInt(requestBodyTO.getUserId()));
					    
					    SMSDetailsCategoryTO categoryTO = new SMSDetailsCategoryTO();
					    categoryTO.setAutoId(requestBodyTO.getCategoryId());
					    parentObject.setSmsDetailsCategoryTO(categoryTO);
					    
					    currentSession().saveOrUpdate(parentObject);
					    if(requestBodyTO.getLanguageList().size()>0)
					    {
					    	for(LanguageTransientTO langObj:requestBodyTO.getLanguageList())
					    	{
					    		
					    		//Child Table  
					    		 SMSDetailsTemplateDtlsTO childObject = new SMSDetailsTemplateDtlsTO();
					    		  Language messageLanguagesTO = new Language();
								  messageLanguagesTO.setLangId(Integer.parseInt(langObj.getLangValue()));
								  
								  childObject.setLangagueTO(messageLanguagesTO);
								  childObject.setMessageContent(langObj.getLangDescprition());
								  childObject.setSmsDetailsTemplateTO(parentObject);
								  currentSession().saveOrUpdate(childObject);
					    	}
									
					    }
				}
				else
				{
				 //EditTime...............................................
					String query = " from SMSDetailsTemplateTO where autoId = '" +requestBodyTO.getMessageAutoId()+ "'";
					SMSDetailsTemplateTO templateDetailsTO_DB =null;
					List<SMSDetailsTemplateDtlsTO>  messageDetailsList =new ArrayList<>();
					
					   templateDetailsTO_DB = (SMSDetailsTemplateTO)currentSession().createQuery(query).uniqueResult();
						if(templateDetailsTO_DB!=null)
						{
							//whatsAppDetailsTO_DB.setAutoId(messageAutoId);
							templateDetailsTO_DB.setMessageName(requestBodyTO.getMessage());
							templateDetailsTO_DB.setCreatedUserId(Integer.parseInt(requestBodyTO.getUserId()));
						    currentSession().saveOrUpdate(templateDetailsTO_DB);
						    
							Hibernate.initialize(templateDetailsTO_DB.getSmsDetailsTemplateDtlsTO());
							
							
						if(requestBodyTO.getLanguageList().size()>0)
						 {
							
							//Remove first -- de-selected Option..............
								for(int i=0;i<templateDetailsTO_DB.getSmsDetailsTemplateDtlsTO().size();i++)
								{
									for(int j=0;j<requestBodyTO.getLanguageList().size();j++)
									{
									   if(    templateDetailsTO_DB.getSmsDetailsTemplateDtlsTO().get(i).getLangagueTO().getLangId()!= 
											    Integer.parseInt(requestBodyTO.getLanguageList().get(j).getLangValue()))
									   {
										   currentSession().delete(templateDetailsTO_DB.getSmsDetailsTemplateDtlsTO().get(i));
									   }
										
									}
								}
							
							
						   for(LanguageTransientTO langObj :requestBodyTO.getLanguageList())  
						   {
							   String queryStr = "from SMSDetailsTemplateDtlsTO where smsDetailsTemplateTO = '" +requestBodyTO.getMessageAutoId()
									              +"' AND langagueTO = '"+langObj.getLangValue()+"'";
							  
							   SMSDetailsTemplateDtlsTO templateChild_MessageDetailsTO_DB =(SMSDetailsTemplateDtlsTO)currentSession().createQuery(queryStr).uniqueResult();
							   
							   
							   if(templateChild_MessageDetailsTO_DB!=null)
								   {
								              Language messageLanguagesTO = new Language();
											  messageLanguagesTO.setLangId(Integer.parseInt(langObj.getLangValue()));
											  
											        templateChild_MessageDetailsTO_DB.setLangagueTO(messageLanguagesTO);
											        templateChild_MessageDetailsTO_DB.setMessageContent(langObj.getLangDescprition());
											        templateChild_MessageDetailsTO_DB.setSmsDetailsTemplateTO(templateDetailsTO_DB);
												    currentSession().saveOrUpdate(templateChild_MessageDetailsTO_DB);
												  
								   }
								   else
								   {
									            SMSDetailsTemplateDtlsTO templateChild_MessageDetailsTO_UI = new SMSDetailsTemplateDtlsTO();
									            Language messageLanguagesTO = new Language();
										        messageLanguagesTO.setLangId(Integer.parseInt(langObj.getLangValue()));
										        
										        templateChild_MessageDetailsTO_UI.setLangagueTO(messageLanguagesTO);
										        templateChild_MessageDetailsTO_UI.setMessageContent(langObj.getLangDescprition());
										        templateChild_MessageDetailsTO_UI.setSmsDetailsTemplateTO(templateDetailsTO_DB);
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


	@Override
	public String deleteCategory(int campId) {
		
		 String  statusCode = "SC00001";
			try 
			{
				Criteria cr = currentSession().createCriteria(SMSDetailsTemplateTO.class);
				cr.add(Restrictions.eq("smsDetailsCategoryTO.autoId",campId));
				List<SMSDetailsTemplateTO> list = cr.list();
				for(SMSDetailsTemplateTO parentObject : list)
				{
					if(parentObject.getSmsDetailsTemplateDtlsTO().size()>0)
					{
						for(SMSDetailsTemplateDtlsTO childObject:parentObject.getSmsDetailsTemplateDtlsTO())
						{
							currentSession().delete(childObject);
						}
					}
					currentSession().delete(parentObject);
					
				}
				String query = " from SMSDetailsCategoryTO where autoId = '" +campId+ "'";
				SMSDetailsCategoryTO smsDetailsCategoryTO = (SMSDetailsCategoryTO)currentSession().createQuery(query).uniqueResult();
				if(smsDetailsCategoryTO!=null)
				{
					currentSession().delete(smsDetailsCategoryTO);
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
				Criteria cr = currentSession().createCriteria(SMSDetailsTemplateTO.class);
				cr.add(Restrictions.eq("autoId",menuId));
				List<SMSDetailsTemplateTO> list = cr.list();
				for(SMSDetailsTemplateTO parentObject : list)
				{
					if(parentObject.getSmsDetailsTemplateDtlsTO().size()>0)
					{
						for(SMSDetailsTemplateDtlsTO childObject:parentObject.getSmsDetailsTemplateDtlsTO())
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


}
