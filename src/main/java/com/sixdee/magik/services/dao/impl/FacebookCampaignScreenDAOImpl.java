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

import com.sixdee.magik.services.dao.FacebookCampaignScreenDAO;
import com.sixdee.magik.services.model.FacebookDetailsTO;
import com.sixdee.magik.services.model.FacebookMessageDetailsTO;
import com.sixdee.magik.services.model.LanguageTransientTO;
import com.sixdee.magik.services.model.MessageLanguagesTO;
import com.sixdee.magik.services.model.RequestBodyTO;
import com.sixdee.magik.services.util.SystemProperties;

/**
 * @author ABHIRAM MACHIRAJU
 * @Date : December, 2020
 */

@SuppressWarnings({ "unchecked", "deprecation" })
@Repository
public class FacebookCampaignScreenDAOImpl implements FacebookCampaignScreenDAO {
	
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
	public List<FacebookDetailsTO> getDetailsData(int userId) {
		Criteria cr = currentSession().createCriteria(FacebookDetailsTO.class);
		cr.addOrder(Order.desc("id"));
		if(userId!=1)
		{
			cr.add(Restrictions.eq("createdUserId",userId));
		}

		List<FacebookDetailsTO> list = cr.list();

		try 
		{
			for(FacebookDetailsTO parentObj :list)
			{
				 
				Hibernate.initialize(parentObj.getFacebookMessageDetailsTO());
			}
			
		}catch (Exception e) {e.printStackTrace();}
		
		return list;
	}

	@Override
	public FacebookDetailsTO getSelectedRecord(int msgAutoId) {
		
		String query = " from FacebookDetailsTO where autoId = '" +msgAutoId+ "'";
		FacebookDetailsTO facebookDetailsTO_DB =null;
		try 
		{
			    facebookDetailsTO_DB = (FacebookDetailsTO)currentSession().createQuery(query).uniqueResult();
				if(facebookDetailsTO_DB!=null)
				{
					Hibernate.initialize(facebookDetailsTO_DB.getFacebookMessageDetailsTO());
					
				}
		}catch (Exception e) {e.printStackTrace();}
		return facebookDetailsTO_DB;
	}

	@Override
	public String deleteRecord(int msgAutoId) {
		String queryStr =null;
		String statusCode = "SC0001";
		Boolean childDeleted =false;
	
		try 
		{
		 queryStr = "from FacebookMessageDetailsTO where facebookDetailsTO = '" + msgAutoId + "'";
		 List<FacebookMessageDetailsTO> detailsList =(List<FacebookMessageDetailsTO>)currentSession().createQuery(queryStr).list();
		 if(detailsList.size()>0)
		 {
			 for(FacebookMessageDetailsTO childObj : detailsList)
			 {
				 currentSession().delete(childObj);
				 childDeleted=true;
			 }
		 }
		 
		 if(childDeleted)
		 {
			 queryStr = "from FacebookDetailsTO where autoId = '" + msgAutoId + "'";
			 FacebookDetailsTO parentObj = (FacebookDetailsTO)currentSession().createQuery(queryStr).uniqueResult();
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
				    FacebookDetailsTO parentDetailsObj = new FacebookDetailsTO();
				    parentDetailsObj.setMessageName(requestBodyTO.getMessage());
				    parentDetailsObj.setCreatedUserId(Integer.parseInt(requestBodyTO.getUserId()));
				    currentSession().saveOrUpdate(parentDetailsObj);
				    if(requestBodyTO.getLanguageList().size()>0)
				    {
				    	for(LanguageTransientTO langObj:requestBodyTO.getLanguageList())
				    	{
				    		
				    		//Child Table  
				    		FacebookMessageDetailsTO childDetailsObj = new FacebookMessageDetailsTO();
							  MessageLanguagesTO messageLanguagesTO = new MessageLanguagesTO();
							  messageLanguagesTO.setAutoId(Integer.parseInt(langObj.getLangValue()));
							  
							  childDetailsObj.setLangagueTO(messageLanguagesTO);
							  childDetailsObj.setMessageContent(langObj.getLangDescprition());
							  childDetailsObj.setFacebookDetailsTO(parentDetailsObj);
							  childDetailsObj.setStatus("CREATED");
							  currentSession().saveOrUpdate(childDetailsObj);
				    	}
								
				    }
			}
			else
			{
			 //EditTime...............................................
				String query = " from FacebookDetailsTO where autoId = '" +requestBodyTO.getMessageAutoId()+ "'";
				FacebookDetailsTO parentObj_TO_DB =null;
				List<FacebookDetailsTO>  messageDetailsList =new ArrayList<>();
				
				    parentObj_TO_DB = (FacebookDetailsTO)currentSession().createQuery(query).uniqueResult();
					if(parentObj_TO_DB!=null)
					{
						parentObj_TO_DB.setMessageName(requestBodyTO.getMessage());
						parentObj_TO_DB.setCreatedUserId(Integer.parseInt(requestBodyTO.getUserId()));
					    currentSession().saveOrUpdate(parentObj_TO_DB);
					    
						Hibernate.initialize(parentObj_TO_DB.getFacebookMessageDetailsTO());
						
						
					if(requestBodyTO.getLanguageList().size()>0)
					 {
						
						//Remove first -- de-selected Option..............
							for(int i=0;i<parentObj_TO_DB.getFacebookMessageDetailsTO().size();i++)
							{
								for(int j=0;j<requestBodyTO.getLanguageList().size();j++)
								{
								   if(    parentObj_TO_DB.getFacebookMessageDetailsTO().get(i).getLangagueTO().getAutoId()!= 
										    Integer.parseInt(requestBodyTO.getLanguageList().get(j).getLangValue()))
								   {
									   currentSession().delete(parentObj_TO_DB.getFacebookMessageDetailsTO().get(i));
								   }
									
								}
							}
						
						
					   for(LanguageTransientTO langObj :requestBodyTO.getLanguageList())  
					   {
						   String queryStr = "from FacebookMessageDetailsTO where facebookDetailsTO = '" +requestBodyTO.getMessageAutoId()
								              +"' AND langagueTO = '"+langObj.getLangValue()+"'";
						  
						   FacebookMessageDetailsTO childObj_TO_DB =(FacebookMessageDetailsTO)currentSession().createQuery(queryStr).uniqueResult();
						   
						   
						   if(childObj_TO_DB!=null)
							   {
									   MessageLanguagesTO messageLanguagesTO = new MessageLanguagesTO();
										  messageLanguagesTO.setAutoId(Integer.parseInt(langObj.getLangValue()));
										  
											  childObj_TO_DB.setLangagueTO(messageLanguagesTO);
											  childObj_TO_DB.setMessageContent(langObj.getLangDescprition());
											  childObj_TO_DB.setFacebookDetailsTO(parentObj_TO_DB);
											  childObj_TO_DB.setStatus("MODIFIED");
											  currentSession().saveOrUpdate(childObj_TO_DB);
											  
							   }
							   else
							   {
								           FacebookMessageDetailsTO childObj_TO_UI = new FacebookMessageDetailsTO();
								            MessageLanguagesTO messageLanguagesTO = new MessageLanguagesTO();
									        messageLanguagesTO.setAutoId(Integer.parseInt(langObj.getLangValue()));
									        
									        childObj_TO_UI.setLangagueTO(messageLanguagesTO);
									        childObj_TO_UI.setMessageContent(langObj.getLangDescprition());
									        childObj_TO_UI.setFacebookDetailsTO(parentObj_TO_DB);
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
