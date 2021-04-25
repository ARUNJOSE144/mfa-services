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

import com.sixdee.magik.services.dao.WhatsAppCampaignScreenDAO;
import com.sixdee.magik.services.model.AttachedFileTransientTO;
import com.sixdee.magik.services.model.LanguageTransientTO;
import com.sixdee.magik.services.model.MessageLanguagesTO;
import com.sixdee.magik.services.model.OptionTransientTO;
import com.sixdee.magik.services.model.WhatsAppDetailsTO;
import com.sixdee.magik.services.model.WhatsAppMessageDetailsTO;
import com.sixdee.magik.services.model.RequestBodyTO;
import com.sixdee.magik.services.model.WhatsAppDetailsCategoryTO;
import com.sixdee.magik.services.util.FileUploadUtil;
import com.sixdee.magik.services.util.SystemProperties;

/**
 * @author ABHIRAM MACHIRAJU
 * @Date : December, 2020
 */

@SuppressWarnings({ "unchecked", "deprecation" })
@Repository
public class WhatsAppCampaignScreenDAOImpl implements WhatsAppCampaignScreenDAO {
	
	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;
	
	//private static final String FILE_PATH = "C:\\Magik_3.0\\DMS\\WhatsAppData\\";
	

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
	public String saveorUpdateWhatsAppData(RequestBodyTO whatsAppRequestBodyTO) {
		
    String  statusCode = "SC00001";
	try 
	{
		//Create Time...................................
		if(whatsAppRequestBodyTO.getMessageAutoId()=="")
		{
			//Parent Table
				WhatsAppDetailsTO whatsAppDetailsTO = new WhatsAppDetailsTO();
			    whatsAppDetailsTO.setMessageName(whatsAppRequestBodyTO.getMessage());
			    whatsAppDetailsTO.setCreatedUserId(Integer.parseInt(whatsAppRequestBodyTO.getUserId()));
			    
			    WhatsAppDetailsCategoryTO whatsAppDetailsCategoryTO = new WhatsAppDetailsCategoryTO();
			    whatsAppDetailsCategoryTO.setAutoId(whatsAppRequestBodyTO.getCategoryId());
			    whatsAppDetailsTO.setWhatsAppDetailsCategoryTO(whatsAppDetailsCategoryTO);
			    
			    currentSession().saveOrUpdate(whatsAppDetailsTO);
			    if(whatsAppRequestBodyTO.getLanguageList().size()>0)
			    {
			    	for(LanguageTransientTO langObj:whatsAppRequestBodyTO.getLanguageList())
			    	{
			    		
			    		//Child Table  
						 WhatsAppMessageDetailsTO whatsApp_MsgDetailsTO = new WhatsAppMessageDetailsTO();
						  MessageLanguagesTO messageLanguagesTO = new MessageLanguagesTO();
						  messageLanguagesTO.setAutoId(Integer.parseInt(langObj.getLangValue()));
						  
						  whatsApp_MsgDetailsTO.setLangagueTO(messageLanguagesTO);
						  whatsApp_MsgDetailsTO.setMessageContent(langObj.getLangDescprition());
						  whatsApp_MsgDetailsTO.setWhatsAppDetailsTO(whatsAppDetailsTO);
						  whatsApp_MsgDetailsTO.setStatus("CREATED");
						  currentSession().saveOrUpdate(whatsApp_MsgDetailsTO);
			    	}
							
			    }
		}
		else
		{
		 //EditTime...............................................
			String query = " from WhatsAppDetailsTO where autoId = '" +whatsAppRequestBodyTO.getMessageAutoId()+ "'";
			WhatsAppDetailsTO whatsAppDetailsTO_DB =null;
			List<WhatsAppMessageDetailsTO>  messageDetailsList =new ArrayList<>();
			
			whatsAppDetailsTO_DB = (WhatsAppDetailsTO)currentSession().createQuery(query).uniqueResult();
				if(whatsAppDetailsTO_DB!=null)
				{
					//whatsAppDetailsTO_DB.setAutoId(messageAutoId);
					whatsAppDetailsTO_DB.setMessageName(whatsAppRequestBodyTO.getMessage());
					whatsAppDetailsTO_DB.setCreatedUserId(Integer.parseInt(whatsAppRequestBodyTO.getUserId()));
				    currentSession().saveOrUpdate(whatsAppDetailsTO_DB);
				    
					Hibernate.initialize(whatsAppDetailsTO_DB.getWhatsAppMessageDetailsTO());
					
					
				if(whatsAppRequestBodyTO.getLanguageList().size()>0)
				 {
					
					//Remove first -- de-selected Option..............
						for(int i=0;i<whatsAppDetailsTO_DB.getWhatsAppMessageDetailsTO().size();i++)
						{
							for(int j=0;j<whatsAppRequestBodyTO.getLanguageList().size();j++)
							{
							   if(    whatsAppDetailsTO_DB.getWhatsAppMessageDetailsTO().get(i).getLangagueTO().getAutoId()!= 
									    Integer.parseInt(whatsAppRequestBodyTO.getLanguageList().get(j).getLangValue()))
							   {
								   //System.err.println("Remaining value :"+whatsAppDetailsTO_DB.getWhatsAppMessageDetailsTO().get(i).getLangagueTO().getLanguages());
								   currentSession().delete(whatsAppDetailsTO_DB.getWhatsAppMessageDetailsTO().get(i));
							   }
								
							}
						}
					
					
				   for(LanguageTransientTO langObj :whatsAppRequestBodyTO.getLanguageList())  
				   {
					   String queryStr = "from WhatsAppMessageDetailsTO where whatsAppDetailsTO = '" +whatsAppRequestBodyTO.getMessageAutoId()
							              +"' AND langagueTO = '"+langObj.getLangValue()+"'";
					  
					   WhatsAppMessageDetailsTO whatsAppMessageDetailsTO_DB =(WhatsAppMessageDetailsTO)currentSession().createQuery(queryStr).uniqueResult();
					   
					   
					   if(whatsAppMessageDetailsTO_DB!=null)
						   {
								   MessageLanguagesTO messageLanguagesTO = new MessageLanguagesTO();
									  messageLanguagesTO.setAutoId(Integer.parseInt(langObj.getLangValue()));
									  
									      whatsAppMessageDetailsTO_DB.setLangagueTO(messageLanguagesTO);
									      whatsAppMessageDetailsTO_DB.setMessageContent(langObj.getLangDescprition());
									      whatsAppMessageDetailsTO_DB.setWhatsAppDetailsTO(whatsAppDetailsTO_DB);
									       whatsAppMessageDetailsTO_DB.setStatus("MODIFIED");
										  currentSession().saveOrUpdate(whatsAppMessageDetailsTO_DB);
										  
						   }
						   else
						   {
							      WhatsAppMessageDetailsTO whatsAppMessageDetailsTO_UI = new WhatsAppMessageDetailsTO();
							            MessageLanguagesTO messageLanguagesTO = new MessageLanguagesTO();
								        messageLanguagesTO.setAutoId(Integer.parseInt(langObj.getLangValue()));
								        
								        whatsAppMessageDetailsTO_UI.setLangagueTO(messageLanguagesTO);
								        whatsAppMessageDetailsTO_UI.setMessageContent(langObj.getLangDescprition());
								        whatsAppMessageDetailsTO_UI.setWhatsAppDetailsTO(whatsAppDetailsTO_DB);
								        whatsAppMessageDetailsTO_UI.setStatus("CREATED");
								        currentSession().saveOrUpdate(whatsAppMessageDetailsTO_UI);
							   
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
	public List<WhatsAppDetailsTO> getWhatsAppData(int userId,int categoryId) {
		
		//List<WhatsAppDetailsTO> list = currentSession().createCriteria(WhatsAppDetailsTO.class).list();
		Criteria cr = currentSession().createCriteria(WhatsAppDetailsTO.class);
		cr.addOrder(Order.desc("autoId"));
		cr.add(Restrictions.eq("whatsAppDetailsCategoryTO.autoId",categoryId));
		if(userId!=1)
		{
			cr.add(Restrictions.eq("createdUserId",userId));
		}
		List<WhatsAppDetailsTO> list = cr.list();

		try 
		{
			for(WhatsAppDetailsTO parentObj :list)
			{
				 
				Hibernate.initialize(parentObj.getWhatsAppMessageDetailsTO());
				/*for(WhatsAppMessageDetailsTO  childObj:parentObj.getWhatsAppMessageDetailsTO())
					{
						List<AttachedFileTransientTO> attachedDOc = FileUploadUtil.retrieveFilesFromServer(childObj.getUploadedFilePath());
						if (attachedDOc.size() > 0)
							childObj.setAttachedDocs(attachedDOc);
					}*/
				
				 
				
			}
			
		}catch (Exception e) {e.printStackTrace();}
		
		return list;
	}

	@Override
	public WhatsAppDetailsTO getSelectedRecord(int msgAutoId) {
		
		String query = " from WhatsAppDetailsTO where autoId = '" +msgAutoId+ "'";
		WhatsAppDetailsTO whatsAppDetailsTO_DB =null;
		try 
		{
				whatsAppDetailsTO_DB = (WhatsAppDetailsTO)currentSession().createQuery(query).uniqueResult();
				if(whatsAppDetailsTO_DB!=null)
				{
					Hibernate.initialize(whatsAppDetailsTO_DB.getWhatsAppMessageDetailsTO());
					/*for(WhatsAppMessageDetailsTO  childObj:whatsAppDetailsTO_DB.getWhatsAppMessageDetailsTO())
						{
							List<AttachedFileTransientTO> attachedDOc = FileUploadUtil.retrieveFilesFromServer(childObj.getUploadedFilePath());
							if (attachedDOc.size() > 0)
								childObj.setAttachedDocs(attachedDOc);
						}*/
					
				}
		}catch (Exception e) {e.printStackTrace();}
		return whatsAppDetailsTO_DB;
	}


	@Override
	public String deleteWhatsAppMsg(int msgAutoId) {
		String queryStr =null;
		String statusCode = "SC0001";
		Boolean childDeleted =false;
	
		try 
		{
		 queryStr = "from WhatsAppMessageDetailsTO where whatsAppDetailsTO = '" + msgAutoId + "'";
		 List<WhatsAppMessageDetailsTO> detailsList =(List<WhatsAppMessageDetailsTO>)currentSession().createQuery(queryStr).list();
		 if(detailsList.size()>0)
		 {
			 for(WhatsAppMessageDetailsTO childObj : detailsList)
			 {
				 currentSession().delete(childObj);
				 childDeleted=true;
			 }
		 }
		 
		 if(childDeleted)
		 {
			 queryStr = "from WhatsAppDetailsTO where autoId = '" + msgAutoId + "'";
			 WhatsAppDetailsTO parentObj = (WhatsAppDetailsTO)currentSession().createQuery(queryStr).uniqueResult();
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
	public List<WhatsAppDetailsCategoryTO> getWhatsAppCategories(int userId) {
		Criteria cr = currentSession().createCriteria(WhatsAppDetailsCategoryTO.class);
		cr.addOrder(Order.desc("autoId"));
		if(userId!=1)
		{
			cr.add(Restrictions.eq("createdUserId",userId));
		}
		List<WhatsAppDetailsCategoryTO> list = cr.list();

		try 
		{
			int iterate=0;
			for(WhatsAppDetailsCategoryTO parentObj :list)
			{
				if(parentObj.getWhatsAppDetailsTO().size()>0)
				{
					
					Hibernate.initialize(parentObj.getWhatsAppDetailsTO());
					if(parentObj.getWhatsAppDetailsTO().get(iterate).getWhatsAppMessageDetailsTO().size()>0)
						Hibernate.initialize(parentObj.getWhatsAppDetailsTO().get(iterate).getWhatsAppMessageDetailsTO());
					
					iterate++;
				}
			}
			
		}
		catch (Exception e) {e.printStackTrace();}
		
		return list;
	}
	

//================================================Old methods====================================================================
		@Override
		public List<WhatsAppMessageDetailsTO> saveWhatsAppMsg(String messageName, List<OptionTransientTO> languages, String messageDesc, String userId) {
			
			//int messageAutoId=0; 
			String queryStr = null;
			List<WhatsAppMessageDetailsTO>  messageDetailsList =new ArrayList<>();
			
			try 
			{       //Parent Table
					WhatsAppDetailsTO whatsAppDetailsTO = new WhatsAppDetailsTO();
					    whatsAppDetailsTO.setMessageName(messageName);
					    whatsAppDetailsTO.setCreatedUserId(Integer.parseInt(userId));
					    currentSession().saveOrUpdate(whatsAppDetailsTO);
					
					
					 if(languages.size()>0)
					 {
						 for(OptionTransientTO jsonObj :languages) 
						 {
							//Child Table  
							 WhatsAppMessageDetailsTO whatsApp_MsgDetailsTO = new WhatsAppMessageDetailsTO();
							     MessageLanguagesTO messageLanguagesTO = new MessageLanguagesTO();
								    messageLanguagesTO.setAutoId(Integer.parseInt(jsonObj.getValue()));
									whatsApp_MsgDetailsTO.setLangagueTO(messageLanguagesTO);
									whatsApp_MsgDetailsTO.setMessageContent(messageDesc);
									whatsApp_MsgDetailsTO.setWhatsAppDetailsTO(whatsAppDetailsTO);
									currentSession().saveOrUpdate(whatsApp_MsgDetailsTO);
						 }
							
						 
					 }
					
					
			
			   if(whatsAppDetailsTO.getAutoId()!=0)
			   { 
				   
				   //From Child Table......
				   queryStr = "from WhatsAppMessageDetailsTO where whatsAppDetailsTO = '" + whatsAppDetailsTO.getAutoId() + "'";
				   messageDetailsList =(List<WhatsAppMessageDetailsTO>)currentSession().createQuery(queryStr).list();
				   if(messageDetailsList.size()>0)
				   {
					 for(WhatsAppMessageDetailsTO childObj:messageDetailsList)
					   {
					       childObj.setUploadedFilePath(properties.getFilePath().trim()+"WhatsAppData\\"+whatsAppDetailsTO.getAutoId()+"\\"+childObj.getLangagueTO().getAutoId() +"\\");
					       currentSession().saveOrUpdate(childObj);
						   
					   }
				   }
				   
				   //messageAutoId=whatsAppDetailsTO.getAutoId();
			   }
			   
			   
			   
			}
			catch (Exception e) {
				e.printStackTrace();
				//messageAutoId=0;
			}
			
			
			return messageDetailsList;
		}
		
	@Override
	public List<WhatsAppMessageDetailsTO> updateWhatsAppMsg(int messageAutoId, String messageName,
			List<OptionTransientTO> languages, String messageDesc, String userId) {

			String query = " from WhatsAppDetailsTO where autoId = '" +messageAutoId+ "'";
			WhatsAppDetailsTO whatsAppDetailsTO_DB =null;
			List<WhatsAppMessageDetailsTO>  messageDetailsList =new ArrayList<>();
			try 
			{
				whatsAppDetailsTO_DB = (WhatsAppDetailsTO)currentSession().createQuery(query).uniqueResult();
				if(whatsAppDetailsTO_DB!=null)
				{
					whatsAppDetailsTO_DB.setAutoId(messageAutoId);
					whatsAppDetailsTO_DB.setMessageName(messageName);
					whatsAppDetailsTO_DB.setCreatedUserId(Integer.parseInt(userId));
				    currentSession().saveOrUpdate(whatsAppDetailsTO_DB);
				    
					Hibernate.initialize(whatsAppDetailsTO_DB.getWhatsAppMessageDetailsTO());
					
					//From Child Table......
					   if(whatsAppDetailsTO_DB.getWhatsAppMessageDetailsTO().size()>0)
					   {
						   for(WhatsAppMessageDetailsTO  childObj:whatsAppDetailsTO_DB.getWhatsAppMessageDetailsTO())
							{
						   
								   if(languages.size()>0)
									 {
										 for(OptionTransientTO jsonObj :languages) 
										 {
											//Child Table  
											     MessageLanguagesTO messageLanguagesTO = new MessageLanguagesTO();
												    messageLanguagesTO.setAutoId(Integer.parseInt(jsonObj.getValue()));
												    childObj.setLangagueTO(messageLanguagesTO);
												    childObj.setMessageContent(messageDesc);
												    childObj.setWhatsAppDetailsTO(whatsAppDetailsTO_DB);
													currentSession().saveOrUpdate(childObj);
										 }
									 }
							}
					   }
					   
					   if(whatsAppDetailsTO_DB.getAutoId()!=0)
					   { 
						   
						   //From Child Table......
						   String queryStr = "from WhatsAppMessageDetailsTO where whatsAppDetailsTO = '" + whatsAppDetailsTO_DB.getAutoId() + "'";
						   messageDetailsList =(List<WhatsAppMessageDetailsTO>)currentSession().createQuery(queryStr).list();
						   if(messageDetailsList.size()>0)
						   {
							 for(WhatsAppMessageDetailsTO childObj:messageDetailsList)
							   {
							       childObj.setUploadedFilePath(properties.getFilePath().trim()+"WhatsAppData\\"+whatsAppDetailsTO_DB.getAutoId()+"\\"+childObj.getLangagueTO().getAutoId() +"\\");
							       currentSession().saveOrUpdate(childObj);
								   
							   }
						   }
						   
					   }
				}		
			
			}catch (Exception e) {e.printStackTrace();}
			return messageDetailsList;
		}
//---------------------------------------------------------------------------------------------------------------------------------------------

	@Override
	public String saveorUpdateWhatsAppCategoryData(WhatsAppDetailsCategoryTO whatsAppRequestBodyTO) {
		 String  statusCode = "SC00001";
			try 
			{
					
					currentSession().saveOrUpdate(whatsAppRequestBodyTO);
				    statusCode = "SC0000";
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		return statusCode;
	}

	@Override
	public String getSelectedCategoryRecord(int msgAutoId) {
		String query = " from WhatsAppDetailsCategoryTO where autoId = '" +msgAutoId+ "'";
		String response = "";
		WhatsAppDetailsCategoryTO whatsAppDetailsCategoryTO_DB =null;
		try 
		{
			   whatsAppDetailsCategoryTO_DB = (WhatsAppDetailsCategoryTO)currentSession().createQuery(query).uniqueResult();
			   if(whatsAppDetailsCategoryTO_DB!=null)
			   {
				   response =whatsAppDetailsCategoryTO_DB.getAutoId()+"$"+whatsAppDetailsCategoryTO_DB.getCampName();
			   }
		}catch (Exception e) {e.printStackTrace();}
		return response;
	}

}
