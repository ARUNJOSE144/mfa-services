package com.sixdee.magik.services.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.sixdee.magik.services.dao.CampaignMessagesChannelDAO;
import com.sixdee.magik.services.model.ChannelMessageTO;


/**
 * @author ABHIRAM MACHIRAJU
 * @Date : January, 2020
 * @see  : AS View has SQL SECURITY DEFINER : change DEFINER user according to userConnected to DB { ex:`cmsuser`@`%`  [' user @ host'] } for Views in DB
 *                        OR
 *   			REPLACE : DEFINER=CURRENT_USER()  
 *    
 * 
 */
 
@Repository
public class CampaignMessagesChannelDAOImpl implements CampaignMessagesChannelDAO {
	
	
	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;
	private String hql = null;
	Criteria criteria = null;

	
	
	@Override
	public List<ChannelMessageTO> getChannelSearch(String channel, int userId) {
		Session session = null;
		String query = null,socaiMediaQuery=null;
		List<ChannelMessageTO> list = null;
		Criteria cr=null;
		try {
			session = sessionFactory.getCurrentSession();
			String channelName = channel.split("_")[0];
			String key = "";
			if (channel.split("_").length > 1)
				key = channel.split("_")[1];

			if (channelName.equalsIgnoreCase("SMS")) {
				if(key!=null && key!="")
					query = "SELECT * FROM RULE_CMP_SMS_VIEW WHERE MSG_ID = '" + Integer.parseInt(key)
						+ "' ORDER BY ID DESC";
				else
					query = "SELECT * FROM RULE_CMP_SMS_VIEW";
				
			} 
			
			
			else if (channelName.equalsIgnoreCase("USSD")) {
				
				if(key!=null && key!="")
					query = "SELECT * FROM RULE_CMP_USSD_VIEW WHERE MSG_ID = '" + Integer.parseInt(key)
						+ "' ORDER BY ID DESC";
				else
					query = "SELECT * FROM RULE_CMP_USSD_VIEW";
				
			} 
			
			
			else if (channelName.equalsIgnoreCase("WAP")) {
				if(key!=null && key!="")
					query = "SELECT * FROM RULE_CMP_WAP_VIEW WHERE MSG_ID = '" + Integer.parseInt(key)
						+ "' ORDER BY ID DESC";
				else
					query = "SELECT * FROM RULE_CMP_WAP_VIEW";
			}
			
			else if (channelName.equalsIgnoreCase("Email")) {
				
				/*query = "SELECT MENU_ID,LANGUAGE_ID,MESSAGE FROM EMAIL_MESSAGE_DETAILS WHERE MENU_ID LIKE '" + key
						+ "%' ORDER BY MENU_ID DESC";*/
			}
			
			else if (channelName.equalsIgnoreCase("Mobile")) {
				
				if(key!=null && key!="")
					query = "SELECT * FROM RULE_CMP_MOBILE_APP_VIEW WHERE MSG_ID = '" + Integer.parseInt(key)
						+ "' ORDER BY ID DESC";
				else
					query = "SELECT * FROM RULE_CMP_MOBILE_APP_VIEW";
				
			}
			else if (channelName.equalsIgnoreCase("WhatsApp")) {
				if(key!=null && key!="")
					query = "SELECT * FROM RULE_CMP_WHATSAPP_VIEW WHERE MSG_ID = '" + Integer.parseInt(key)
						+ "' ORDER BY ID DESC";
				else
					query = "SELECT * FROM RULE_CMP_WHATSAPP_VIEW";
				
			}
			else if (channelName.equalsIgnoreCase("Telegram")) {
				if(key!=null && key!="")
					query = "SELECT * FROM RULE_CMP_TELEGRAM_VIEW WHERE MSG_ID = '" + Integer.parseInt(key)
						+ "' ORDER BY ID DESC";
				else
					query = "SELECT * FROM RULE_CMP_TELEGRAM_VIEW";
				
			}
			else if (channelName.equalsIgnoreCase("Facebook")) {
				if(key!=null && key!="")
					query = "SELECT * FROM RULE_CMP_FACEBOOK_VIEW WHERE MSG_ID = '" + Integer.parseInt(key)
						+ "' ORDER BY ID DESC";
				else
					query = "SELECT * FROM RULE_CMP_FACEBOOK_VIEW";
				
			}
			else if (channelName.equalsIgnoreCase("Twitter")) {
				if(key!=null && key!="")
					query = "SELECT * FROM RULE_CMP_TWITTER_VIEW WHERE MSG_ID = '" + Integer.parseInt(key)
						+ "' ORDER BY ID DESC";
				else
					query = "SELECT * FROM RULE_CMP_TWITTER_VIEW";
				
			}
			else if (channelName.equalsIgnoreCase("Skype")) {
				if(key!=null && key!="")
					query = "SELECT * FROM RULE_CMP_SKYPE_VIEW WHERE MSG_ID = '" + Integer.parseInt(key)
						+ "' ORDER BY ID DESC";
				else
					query = "SELECT * FROM RULE_CMP_SKYPE_VIEW";
				
			}
			
			/* 
			 * AS View has SQL SECURITY DEFINER : 
			 * change DEFINER user according to userConnected to DB  { ex:`cmsuser`@`%`  [' user @ host'] }
			 * for Views in DB 
			 *     */
			
			if(query!=null)
			{
				
				SQLQuery sqlQuery = session.createSQLQuery(query);
				List<Object[]> results = (List<Object[]>) sqlQuery.list();
				list = processMessage(results, channelName);
			}

		} catch (Exception e) {
			throw e;
		}

		return list;
	}
	
	

	public List<ChannelMessageTO> processMessage(List<Object[]> results, String channelName) {
		List<ChannelMessageTO> list = null;
		ChannelMessageTO msgTo = null;
		int currentMsgId = 0;
		int count = 0;
		if (results != null && results.size() > 0) {
			list = new ArrayList<ChannelMessageTO>();
			for (Object[] aRow : results) {
				count++;
				if (currentMsgId != Integer.parseInt(aRow[0] + "")) {
					if (msgTo != null) {
						list.add(msgTo);
					}
					msgTo = new ChannelMessageTO();
					currentMsgId = Integer.parseInt(aRow[0] + "");
					msgTo.setMessageId(channelName + "_" + aRow[0] + "");
					if(aRow[3]!=null) {msgTo.setMessageName(aRow[3].toString());}
				}
				
				

				if(channelName!=null &&( channelName.equals("WhatsApp") || channelName.equals("Skype") || channelName.equals("Facebook")|| channelName.equals("Telegram") || channelName.equals("Twitter")))
				{
					
						if (Integer.parseInt(aRow[1] + "") == 1) {
							msgTo.setEnglish(aRow[2].toString());
						}
						if (Integer.parseInt(aRow[1] + "") == 2) {
							msgTo.setSpanish(aRow[2].toString());
						}
						if (Integer.parseInt(aRow[1] + "") == 3) {
							msgTo.setArabic(aRow[2].toString());
						}
				}
				else
				{	
					if (Integer.parseInt(aRow[1] + "") == 1) {
						msgTo.setEnglish(getUtf8((aRow[2] + "")));
				     }
					if (Integer.parseInt(aRow[1] + "") == 2) {
						msgTo.setSpanish(getUtf8(aRow[2] + ""));
					}
					if (Integer.parseInt(aRow[1] + "") == 3) {
						msgTo.setArabic(getUtf8(aRow[2] + ""));
					}
				}
				msgTo.setChannelType(channelName);
		 		
				
				if (results.size() == count) {
					if (msgTo != null) {
						list.add(msgTo);
					}
				}
			}
		}
		return list;
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
		System.out.println("============ decoded text===============" + decoded);
		return decoded;
	}




	

}
