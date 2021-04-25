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

import com.sixdee.magik.services.dao.SkypeCampaignScreenDAO;
import com.sixdee.magik.services.model.LanguageTransientTO;
import com.sixdee.magik.services.model.MessageLanguagesTO;
import com.sixdee.magik.services.model.RequestBodyTO;
import com.sixdee.magik.services.model.SkypeDetailsTO;
import com.sixdee.magik.services.model.SkypeMessageDetailsTO;
import com.sixdee.magik.services.util.SystemProperties;

/**
 * @author ABHIRAM MACHIRAJU
 * @Date : December, 2020
 */

@SuppressWarnings({ "unchecked", "deprecation" })
@Repository
public class SkypeCampaignScreenDAOImpl implements SkypeCampaignScreenDAO {
	

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
	public List<SkypeDetailsTO> getDetailsData(int userId) {
		Criteria cr = currentSession().createCriteria(SkypeDetailsTO.class);
		cr.addOrder(Order.desc("id"));
		if(userId!=1)
		{
			cr.add(Restrictions.eq("createdUserId",userId));
		}

		List<SkypeDetailsTO> list = cr.list();

		try 
		{
			for(SkypeDetailsTO parentObj :list)
			{
				 
				Hibernate.initialize(parentObj.getSkypeMessageDetailsTO());
			}
			
		}catch (Exception e) {e.printStackTrace();}
		
		return list;
	}

	@Override
	public SkypeDetailsTO getSelectedRecord(int msgAutoId) {
		
		String query = " from SkypeDetailsTO where autoId = '" +msgAutoId+ "'";
		SkypeDetailsTO skypeDetailsTO_DB =null;
		try 
		{
			   skypeDetailsTO_DB = (SkypeDetailsTO)currentSession().createQuery(query).uniqueResult();
				if(skypeDetailsTO_DB!=null)
				{
					Hibernate.initialize(skypeDetailsTO_DB.getSkypeMessageDetailsTO());
					
				}
		}catch (Exception e) {e.printStackTrace();}
		return skypeDetailsTO_DB;
	}

	@Override
	public String deleteRecord(int msgAutoId) {
		String queryStr =null;
		String statusCode = "SC0001";
		Boolean childDeleted =false;
	
		try 
		{
		 queryStr = "from SkypeMessageDetailsTO where skypeDetailsTO = '" + msgAutoId + "'";
		 List<SkypeMessageDetailsTO> detailsList =(List<SkypeMessageDetailsTO>)currentSession().createQuery(queryStr).list();
		 if(detailsList.size()>0)
		 {
			 for(SkypeMessageDetailsTO childObj : detailsList)
			 {
				 currentSession().delete(childObj);
				 childDeleted=true;
			 }
		 }
		 
		 if(childDeleted)
		 {
			 queryStr = "from SkypeDetailsTO where autoId = '" + msgAutoId + "'";
			  SkypeDetailsTO parentObj = (SkypeDetailsTO)currentSession().createQuery(queryStr).uniqueResult();
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
				    SkypeDetailsTO parentDetailsObj = new SkypeDetailsTO();
				    parentDetailsObj.setMessageName(requestBodyTO.getMessage());
				    parentDetailsObj.setCreatedUserId(Integer.parseInt(requestBodyTO.getUserId()));
				    currentSession().saveOrUpdate(parentDetailsObj);
				    if(requestBodyTO.getLanguageList().size()>0)
				    {
				    	for(LanguageTransientTO langObj:requestBodyTO.getLanguageList())
				    	{
				    		
				    		//Child Table  
				    		SkypeMessageDetailsTO childDetailsObj = new SkypeMessageDetailsTO();
							  MessageLanguagesTO messageLanguagesTO = new MessageLanguagesTO();
							  messageLanguagesTO.setAutoId(Integer.parseInt(langObj.getLangValue()));
							  
							  childDetailsObj.setLangagueTO(messageLanguagesTO);
							  childDetailsObj.setMessageContent(langObj.getLangDescprition());
							  childDetailsObj.setSkypeDetailsTO(parentDetailsObj);
							  childDetailsObj.setStatus("CREATED");
							  currentSession().saveOrUpdate(childDetailsObj);
				    	}
								
				    }
			}
			else
			{
			 //EditTime...............................................
				String query = " from SkypeDetailsTO where autoId = '" +requestBodyTO.getMessageAutoId()+ "'";
				SkypeDetailsTO parentObj_TO_DB =null;
				List<SkypeDetailsTO>  messageDetailsList =new ArrayList<>();
				
				    parentObj_TO_DB = (SkypeDetailsTO)currentSession().createQuery(query).uniqueResult();
					if(parentObj_TO_DB!=null)
					{
						parentObj_TO_DB.setMessageName(requestBodyTO.getMessage());
						parentObj_TO_DB.setCreatedUserId(Integer.parseInt(requestBodyTO.getUserId()));
					    currentSession().saveOrUpdate(parentObj_TO_DB);
					    
						Hibernate.initialize(parentObj_TO_DB.getSkypeMessageDetailsTO());
						
						
					if(requestBodyTO.getLanguageList().size()>0)
					 {
						
						//Remove first -- de-selected Option..............
							for(int i=0;i<parentObj_TO_DB.getSkypeMessageDetailsTO().size();i++)
							{
								for(int j=0;j<requestBodyTO.getLanguageList().size();j++)
								{
								   if(    parentObj_TO_DB.getSkypeMessageDetailsTO().get(i).getLangagueTO().getAutoId()!= 
										    Integer.parseInt(requestBodyTO.getLanguageList().get(j).getLangValue()))
								   {
									   currentSession().delete(parentObj_TO_DB.getSkypeMessageDetailsTO().get(i));
								   }
									
								}
							}
						
						
					   for(LanguageTransientTO langObj :requestBodyTO.getLanguageList())  
					   {
						   String queryStr = "from SkypeMessageDetailsTO where skypeDetailsTO = '" +requestBodyTO.getMessageAutoId()
								              +"' AND langagueTO = '"+langObj.getLangValue()+"'";
						  
						   SkypeMessageDetailsTO childObj_TO_DB =(SkypeMessageDetailsTO)currentSession().createQuery(queryStr).uniqueResult();
						   
						   
						   if(childObj_TO_DB!=null)
							   {
									   MessageLanguagesTO messageLanguagesTO = new MessageLanguagesTO();
										  messageLanguagesTO.setAutoId(Integer.parseInt(langObj.getLangValue()));
										  
											  childObj_TO_DB.setLangagueTO(messageLanguagesTO);
											  childObj_TO_DB.setMessageContent(langObj.getLangDescprition());
											  childObj_TO_DB.setSkypeDetailsTO(parentObj_TO_DB);
											  childObj_TO_DB.setStatus("MODIFIED");
											  currentSession().saveOrUpdate(childObj_TO_DB);
											  
							   }
							   else
							   {
								           SkypeMessageDetailsTO childObj_TO_UI = new SkypeMessageDetailsTO();
								            MessageLanguagesTO messageLanguagesTO = new MessageLanguagesTO();
									        messageLanguagesTO.setAutoId(Integer.parseInt(langObj.getLangValue()));
									        
									        childObj_TO_UI.setLangagueTO(messageLanguagesTO);
									        childObj_TO_UI.setMessageContent(langObj.getLangDescprition());
									        childObj_TO_UI.setSkypeDetailsTO(parentObj_TO_DB);
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
