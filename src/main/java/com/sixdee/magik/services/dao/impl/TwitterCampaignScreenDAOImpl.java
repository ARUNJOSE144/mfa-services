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

import com.sixdee.magik.services.dao.TwitterCampaignScreenDAO;
import com.sixdee.magik.services.model.LanguageTransientTO;
import com.sixdee.magik.services.model.MessageLanguagesTO;
import com.sixdee.magik.services.model.RequestBodyTO;
import com.sixdee.magik.services.model.TwitterDetailsTO;
import com.sixdee.magik.services.model.TwitterMessageDetailsTO;
import com.sixdee.magik.services.util.SystemProperties;

/**
 * @author ABHIRAM MACHIRAJU
 * @Date : December, 2020
 */

@SuppressWarnings({ "unchecked", "deprecation" })
@Repository
public class TwitterCampaignScreenDAOImpl implements TwitterCampaignScreenDAO {
	
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
	public List<TwitterDetailsTO> getDetailsData(int userId) {
		Criteria cr = currentSession().createCriteria(TwitterDetailsTO.class);
		cr.addOrder(Order.desc("id"));
		if(userId!=1)
		{
			cr.add(Restrictions.eq("createdUserId",userId));
		}
		List<TwitterDetailsTO> list = cr.list();

		try 
		{
			for(TwitterDetailsTO parentObj :list)
			{
				 
				Hibernate.initialize(parentObj.getTwitterMessageDetailsTO());
			}
			
		}catch (Exception e) {e.printStackTrace();}
		
		return list;
	}

	@Override
	public TwitterDetailsTO getSelectedRecord(int msgAutoId) {
		
		String query = " from TwitterDetailsTO where autoId = '" +msgAutoId+ "'";
		TwitterDetailsTO twitterDetailsTO_DB =null;
		try 
		{
			     twitterDetailsTO_DB = (TwitterDetailsTO)currentSession().createQuery(query).uniqueResult();
				if(twitterDetailsTO_DB!=null)
				{
					Hibernate.initialize(twitterDetailsTO_DB.getTwitterMessageDetailsTO());
					
				}
		}catch (Exception e) {e.printStackTrace();}
		return twitterDetailsTO_DB;
	}

	@Override
	public String deleteRecord(int msgAutoId) {
		String queryStr =null;
		String statusCode = "SC0001";
		Boolean childDeleted =false;
	
		try 
		{
		 queryStr = "from TwitterMessageDetailsTO where twitterDetailsTO = '" + msgAutoId + "'";
		 List<TwitterMessageDetailsTO> detailsList =(List<TwitterMessageDetailsTO>)currentSession().createQuery(queryStr).list();
		 if(detailsList.size()>0)
		 {
			 for(TwitterMessageDetailsTO childObj : detailsList)
			 {
				 currentSession().delete(childObj);
				 childDeleted=true;
			 }
		 }
		 
		 if(childDeleted)
		 {
			 queryStr = "from TwitterDetailsTO where autoId = '" + msgAutoId + "'";
			 TwitterDetailsTO parentObj = (TwitterDetailsTO)currentSession().createQuery(queryStr).uniqueResult();
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

	
	@Override
	public String saveorUpdateRecord(RequestBodyTO requestBodyTO) {
		
	    String  statusCode = "SC00001";
		try 
		{
			//Create Time...................................
			if(requestBodyTO.getMessageAutoId()=="")
			{
				//Parent Table
				    TwitterDetailsTO parentDetailsObj = new TwitterDetailsTO();
				    parentDetailsObj.setMessageName(requestBodyTO.getMessage());
				    parentDetailsObj.setCreatedUserId(Integer.parseInt(requestBodyTO.getUserId()));
				    currentSession().saveOrUpdate(parentDetailsObj);
				    if(requestBodyTO.getLanguageList().size()>0)
				    {
				    	for(LanguageTransientTO langObj:requestBodyTO.getLanguageList())
				    	{
				    		
				    		//Child Table  
				    		TwitterMessageDetailsTO childDetailsObj = new TwitterMessageDetailsTO();
							  MessageLanguagesTO messageLanguagesTO = new MessageLanguagesTO();
							  messageLanguagesTO.setAutoId(Integer.parseInt(langObj.getLangValue()));
							  
							  childDetailsObj.setLangagueTO(messageLanguagesTO);
							  childDetailsObj.setMessageContent(langObj.getLangDescprition());
							  childDetailsObj.setTwitterDetailsTO(parentDetailsObj);
							  childDetailsObj.setStatus("CREATED");
							  currentSession().saveOrUpdate(childDetailsObj);
				    	}
								
				    }
			}
			else
			{
			 //EditTime...............................................
				String query = " from TwitterDetailsTO where autoId = '" +requestBodyTO.getMessageAutoId()+ "'";
				TwitterDetailsTO parentObj_TO_DB =null;
				List<TwitterDetailsTO>  messageDetailsList =new ArrayList<>();
				
				    parentObj_TO_DB = (TwitterDetailsTO)currentSession().createQuery(query).uniqueResult();
					if(parentObj_TO_DB!=null)
					{
						parentObj_TO_DB.setMessageName(requestBodyTO.getMessage());
						parentObj_TO_DB.setCreatedUserId(Integer.parseInt(requestBodyTO.getUserId()));
					    currentSession().saveOrUpdate(parentObj_TO_DB);
					    
						Hibernate.initialize(parentObj_TO_DB.getTwitterMessageDetailsTO());
						
						
					if(requestBodyTO.getLanguageList().size()>0)
					 {
						
						//Remove first -- de-selected Option..............
							for(int i=0;i<parentObj_TO_DB.getTwitterMessageDetailsTO().size();i++)
							{
								for(int j=0;j<requestBodyTO.getLanguageList().size();j++)
								{
								   if(    parentObj_TO_DB.getTwitterMessageDetailsTO().get(i).getLangagueTO().getAutoId()!= 
										    Integer.parseInt(requestBodyTO.getLanguageList().get(j).getLangValue()))
								   {
									   currentSession().delete(parentObj_TO_DB.getTwitterMessageDetailsTO().get(i));
								   }
									
								}
							}
						
						
					   for(LanguageTransientTO langObj :requestBodyTO.getLanguageList())  
					   {
						   String queryStr = "from TwitterMessageDetailsTO where twitterDetailsTO = '" +requestBodyTO.getMessageAutoId()
								              +"' AND langagueTO = '"+langObj.getLangValue()+"'";
						  
						   TwitterMessageDetailsTO childObj_TO_DB =(TwitterMessageDetailsTO)currentSession().createQuery(queryStr).uniqueResult();
						   
						   
						   if(childObj_TO_DB!=null)
							   {
									   MessageLanguagesTO messageLanguagesTO = new MessageLanguagesTO();
										  messageLanguagesTO.setAutoId(Integer.parseInt(langObj.getLangValue()));
										  
											  childObj_TO_DB.setLangagueTO(messageLanguagesTO);
											  childObj_TO_DB.setMessageContent(langObj.getLangDescprition());
											  childObj_TO_DB.setTwitterDetailsTO(parentObj_TO_DB);
											  childObj_TO_DB.setStatus("MODIFIED");
											  currentSession().saveOrUpdate(childObj_TO_DB);
											  
							   }
							   else
							   {
								           TwitterMessageDetailsTO childObj_TO_UI = new TwitterMessageDetailsTO();
								            MessageLanguagesTO messageLanguagesTO = new MessageLanguagesTO();
									        messageLanguagesTO.setAutoId(Integer.parseInt(langObj.getLangValue()));
									        
									        childObj_TO_UI.setLangagueTO(messageLanguagesTO);
									        childObj_TO_UI.setMessageContent(langObj.getLangDescprition());
									        childObj_TO_UI.setTwitterDetailsTO(parentObj_TO_DB);
									        childObj_TO_UI.setStatus("CREATED");
									        currentSession().saveOrUpdate(childObj_TO_UI);
								   
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
