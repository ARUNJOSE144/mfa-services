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

import com.sixdee.magik.services.dao.TelegramCampaignScreenDAO;
import com.sixdee.magik.services.model.LanguageTransientTO;
import com.sixdee.magik.services.model.MessageLanguagesTO;
import com.sixdee.magik.services.model.RequestBodyTO;
import com.sixdee.magik.services.model.TelegramDetailsTO;
import com.sixdee.magik.services.model.TelegramMessageDetailsTO;
import com.sixdee.magik.services.util.SystemProperties;

/**
 * @author ABHIRAM MACHIRAJU
 * @Date : December, 2020
 */

@SuppressWarnings({ "unchecked", "deprecation" })
@Repository
public class TelegramCampaignScreenDAOImpl implements TelegramCampaignScreenDAO {
	
	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;
	

	@Autowired
	SystemProperties properties;
	
	protected Session currentSession()
	{
		return sessionFactory.getCurrentSession();
	}

	@Override
	public List<MessageLanguagesTO> getLanguages() {
		
		List<MessageLanguagesTO> list = currentSession().createCriteria(MessageLanguagesTO.class).list();
		return list;
		
	}
	
	@Override
	public String saveorUpdateTelegramData(RequestBodyTO telegramRequestBodyTO) {
		
    String  statusCode = "SC00001";
	try 
	{
		//Create Time...................................
		if(telegramRequestBodyTO.getMessageAutoId()=="")
		{
			//Parent Table
				TelegramDetailsTO telegramDetailsTO = new TelegramDetailsTO();
				telegramDetailsTO.setMessageName(telegramRequestBodyTO.getMessage());
				telegramDetailsTO.setCreatedUserId(Integer.parseInt(telegramRequestBodyTO.getUserId()));
			    currentSession().saveOrUpdate(telegramDetailsTO);
			    if(telegramRequestBodyTO.getLanguageList().size()>0)
			    {
			    	for(LanguageTransientTO langObj:telegramRequestBodyTO.getLanguageList())
			    	{
			    		
			    		//Child Table  
						 TelegramMessageDetailsTO telegramMessageDetailsTO = new TelegramMessageDetailsTO();
						  MessageLanguagesTO messageLanguagesTO = new MessageLanguagesTO();
						  messageLanguagesTO.setAutoId(Integer.parseInt(langObj.getLangValue()));
						  
						  telegramMessageDetailsTO.setLangagueTO(messageLanguagesTO);
						  telegramMessageDetailsTO.setMessageContent(langObj.getLangDescprition());
						  telegramMessageDetailsTO.setTelegramDetailsTO(telegramDetailsTO);
						  telegramMessageDetailsTO.setStatus("CREATED");
						  currentSession().saveOrUpdate(telegramMessageDetailsTO);
			    	}
							
			    }
		}
		else
		{
		 //EditTime...............................................
			String query = " from TelegramDetailsTO where autoId = '" +telegramRequestBodyTO.getMessageAutoId()+ "'";
			TelegramDetailsTO telegramDetailsTO_DB =null;
			List<TelegramMessageDetailsTO>  messageDetailsList =new ArrayList<>();
			
			  telegramDetailsTO_DB = (TelegramDetailsTO)currentSession().createQuery(query).uniqueResult();
				if(telegramDetailsTO_DB!=null)
				{
					//whatsAppDetailsTO_DB.setAutoId(messageAutoId);
					telegramDetailsTO_DB.setMessageName(telegramRequestBodyTO.getMessage());
					telegramDetailsTO_DB.setCreatedUserId(Integer.parseInt(telegramRequestBodyTO.getUserId()));
				    currentSession().saveOrUpdate(telegramDetailsTO_DB);
				    
					Hibernate.initialize(telegramDetailsTO_DB.getTelegramMessageDetailsTO());
					
					
				if(telegramRequestBodyTO.getLanguageList().size()>0)
				 {
					
					//Remove first -- de-selected Option..............
						for(int i=0;i<telegramDetailsTO_DB.getTelegramMessageDetailsTO().size();i++)
						{
							for(int j=0;j<telegramRequestBodyTO.getLanguageList().size();j++)
							{
							   if(    telegramDetailsTO_DB.getTelegramMessageDetailsTO().get(i).getLangagueTO().getAutoId()!= 
									    Integer.parseInt(telegramRequestBodyTO.getLanguageList().get(j).getLangValue()))
							   {
								   currentSession().delete(telegramDetailsTO_DB.getTelegramMessageDetailsTO().get(i));
							   }
								
							}
						}
					
					
				   for(LanguageTransientTO langObj :telegramRequestBodyTO.getLanguageList())  
				   {
					   String queryStr = "from TelegramMessageDetailsTO where telegramDetailsTO = '" +telegramRequestBodyTO.getMessageAutoId()
							              +"' AND langagueTO = '"+langObj.getLangValue()+"'";
					  
					   TelegramMessageDetailsTO telegramMessageDetailsTO_DB =(TelegramMessageDetailsTO)currentSession().createQuery(queryStr).uniqueResult();
					   
					   
					   if(telegramMessageDetailsTO_DB!=null)
						   {
								   MessageLanguagesTO messageLanguagesTO = new MessageLanguagesTO();
									  messageLanguagesTO.setAutoId(Integer.parseInt(langObj.getLangValue()));
									  
									       telegramMessageDetailsTO_DB.setLangagueTO(messageLanguagesTO);
									       telegramMessageDetailsTO_DB.setMessageContent(langObj.getLangDescprition());
									       telegramMessageDetailsTO_DB.setTelegramDetailsTO(telegramDetailsTO_DB);
									       telegramMessageDetailsTO_DB.setStatus("MODIFIED");
										  currentSession().saveOrUpdate(telegramMessageDetailsTO_DB);
										  
						   }
						   else
						   {
							          TelegramMessageDetailsTO TelegramMessageDetailsTO_UI = new TelegramMessageDetailsTO();
							            MessageLanguagesTO messageLanguagesTO = new MessageLanguagesTO();
								        messageLanguagesTO.setAutoId(Integer.parseInt(langObj.getLangValue()));
								        
								        TelegramMessageDetailsTO_UI.setLangagueTO(messageLanguagesTO);
								        TelegramMessageDetailsTO_UI.setMessageContent(langObj.getLangDescprition());
								        TelegramMessageDetailsTO_UI.setTelegramDetailsTO(telegramDetailsTO_DB);
								        TelegramMessageDetailsTO_UI.setStatus("CREATED");
								        currentSession().saveOrUpdate(TelegramMessageDetailsTO_UI);
							   
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
	public List<TelegramDetailsTO> getTelegramData(int userId) {
		
		Criteria cr = currentSession().createCriteria(TelegramDetailsTO.class);
		cr.addOrder(Order.desc("id"));
		if(userId!=1)
		{
			cr.add(Restrictions.eq("createdUserId",userId));
		}

		List<TelegramDetailsTO> list = cr.list();

		try 
		{
			for(TelegramDetailsTO parentObj :list)
			{
				 
				Hibernate.initialize(parentObj.getTelegramMessageDetailsTO());
			}
			
		}catch (Exception e) {e.printStackTrace();}
		
		return list;
	}

	@Override
	public TelegramDetailsTO getSelectedRecord(int msgAutoId) {
		
		String query = " from TelegramDetailsTO where autoId = '" +msgAutoId+ "'";
		TelegramDetailsTO telegramDetailsTO_DB =null;
		try 
		{
			    telegramDetailsTO_DB = (TelegramDetailsTO)currentSession().createQuery(query).uniqueResult();
				if(telegramDetailsTO_DB!=null)
				{
					Hibernate.initialize(telegramDetailsTO_DB.getTelegramMessageDetailsTO());
					
				}
		}catch (Exception e) {e.printStackTrace();}
		return telegramDetailsTO_DB;
	}


	@Override
	public String deleteTelegramMsg(int msgAutoId) {
		String queryStr =null;
		String statusCode = "SC0001";
		Boolean childDeleted =false;
	
		try 
		{
		 queryStr = "from TelegramMessageDetailsTO where telegramDetailsTO = '" + msgAutoId + "'";
		 List<TelegramMessageDetailsTO> detailsList =(List<TelegramMessageDetailsTO>)currentSession().createQuery(queryStr).list();
		 if(detailsList.size()>0)
		 {
			 for(TelegramMessageDetailsTO childObj : detailsList)
			 {
				 currentSession().delete(childObj);
				 childDeleted=true;
			 }
		 }
		 
		 if(childDeleted)
		 {
			 queryStr = "from TelegramDetailsTO where autoId = '" + msgAutoId + "'";
			 TelegramDetailsTO parentObj = (TelegramDetailsTO)currentSession().createQuery(queryStr).uniqueResult();
			  if(parentObj!=null)
			  {
				  currentSession().delete(parentObj);
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
