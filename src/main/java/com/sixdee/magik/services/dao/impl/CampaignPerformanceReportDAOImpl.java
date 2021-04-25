package com.sixdee.magik.services.dao.impl;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;

import javax.persistence.Column;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.sixdee.magik.services.dao.CampaignPerformanceReportDAO;
import com.sixdee.magik.services.model.CGBaseReportTO;
import com.sixdee.magik.services.model.CampaignPerformanceReportTransientTO;
import com.sixdee.magik.services.model.ShortSummaryReportTO;
import com.sixdee.magik.services.model.TGbaseReportTO;
import com.sixdee.magik.services.model.WeekNetConvReportTO;
import com.sixdee.magik.services.model.WeekRevenueBaseReportTO;

/**
 * @author ABHIRAM MACHIRAJU
 * @Date : March, 2021				
 */
@SuppressWarnings("unchecked")
@Repository
public class CampaignPerformanceReportDAOImpl implements CampaignPerformanceReportDAO {
	
	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;
	
	protected Session currentSession()
	{
		return sessionFactory.getCurrentSession();
	}

	@Override
	public CampaignPerformanceReportTransientTO getAllRcrds() {
		
		DecimalFormat df = new DecimalFormat("#.##");
		df.setRoundingMode(RoundingMode.CEILING);
		
		CampaignPerformanceReportTransientTO obj =new CampaignPerformanceReportTransientTO();
		List<TGbaseReportTO> tgBaselist=null;
		List<CGBaseReportTO> cgBaselist=null;
		List<WeekNetConvReportTO> weekNetlist=null;
		List<WeekRevenueBaseReportTO> weekRevenuelist=null;
		List<ShortSummaryReportTO> shortSummarylist=null;
		
		try{ 
			
			  tgBaselist = currentSession().createCriteria(TGbaseReportTO.class).list();
			  TGbaseReportTO  tgGrandObject = new TGbaseReportTO();
			  tgGrandObject.setTg_base("Grand Total");
			  
				Double delivered_w1=0.0;Double takers_w1=0.0;Double takers_per_w1=0.0;
				Double delivered_w2=0.0;Double takers_w2=0.0;Double takers_per_w2=0.0;
				Double delivered_w3=0.0;Double takers_w3=0.0;Double takers_per_w3=0.0;
				Double delivered_w4=0.0;Double takers_w4=0.0;Double takers_per_w4=0.0;
				Double delivered_w2w1=0.0;Double takers_w2w1=0.0;Double takers_per_w2w1=0.0;
				Double delivered_w3w2=0.0;Double takers_w3w2=0.0;Double takers_per_w3w2=0.0;
				Double delivered_w4w3=0.0;Double takers_w4w3=0.0;Double takers_per_w4w3=0.0;
				
				for(TGbaseReportTO tgBaseObj : tgBaselist)
				{
					if(tgBaseObj.getDelivered_w1()!=null) {delivered_w1 = delivered_w1+tgBaseObj.getDelivered_w1();};
					if(tgBaseObj.getTakers_w1()!=null) {takers_w1 = takers_w1+tgBaseObj.getTakers_w1();};
					if(tgBaseObj.getTakers_per_w1()!=null) {takers_per_w1 = takers_per_w1+tgBaseObj.getTakers_per_w1();};
					
					if(tgBaseObj.getDelivered_w2()!=null) {delivered_w2 = delivered_w2+tgBaseObj.getDelivered_w2();};
					if(tgBaseObj.getTakers_w2()!=null) {takers_w2 = takers_w2+tgBaseObj.getTakers_w2();};
					if(tgBaseObj.getTakers_per_w2()!=null) {takers_per_w2 = takers_per_w2+tgBaseObj.getTakers_per_w2();};
					
					if(tgBaseObj.getDelivered_w3()!=null) {delivered_w3 = delivered_w3+tgBaseObj.getDelivered_w3();};
					if(tgBaseObj.getTakers_w3()!=null) {takers_w3 = takers_w3+tgBaseObj.getTakers_w3();};
					if(tgBaseObj.getTakers_per_w3()!=null) {takers_per_w3 = takers_per_w3+tgBaseObj.getTakers_per_w3();};
					
					if(tgBaseObj.getDelivered_w4()!=null) {delivered_w4 = delivered_w4+tgBaseObj.getDelivered_w4();};
					if(tgBaseObj.getTakers_w4()!=null) {takers_w4 = takers_w4+tgBaseObj.getTakers_w4();};
					if(tgBaseObj.getTakers_per_w4()!=null) {takers_per_w4 = takers_per_w4+tgBaseObj.getTakers_per_w4();};
					
					if(tgBaseObj.getDelivered_w2w1()!=null) {delivered_w2w1 = delivered_w2w1+tgBaseObj.getDelivered_w2w1();};
					if(tgBaseObj.getTakers_w2w1()!=null) {takers_w2w1 = takers_w2w1+tgBaseObj.getTakers_w2w1();};
					if(tgBaseObj.getTakers_per_w2w1()!=null) {takers_per_w2w1 = takers_per_w2w1+tgBaseObj.getTakers_per_w2w1();};
					
					if(tgBaseObj.getDelivered_w3w2()!=null) {delivered_w3w2 = delivered_w3w2+tgBaseObj.getDelivered_w3w2();};
					if(tgBaseObj.getTakers_w3w2()!=null) {takers_w3w2 = takers_w3w2+tgBaseObj.getTakers_w3w2();};
					if(tgBaseObj.getTakers_per_w3w2()!=null) {takers_per_w3w2 = takers_per_w3w2+tgBaseObj.getTakers_per_w3w2();};
					
					
					if(tgBaseObj.getDelivered_w4w3()!=null) {delivered_w4w3 = delivered_w4w3+tgBaseObj.getDelivered_w4w3();};
					if(tgBaseObj.getTakers_w4w3()!=null) {takers_w4w3 = takers_w4w3+tgBaseObj.getTakers_w4w3();};
					if(tgBaseObj.getTakers_per_w4w3()!=null) {takers_per_w4w3 = takers_per_w4w3+tgBaseObj.getTakers_per_w4w3();};
				}
				
				
				 tgGrandObject.setDelivered_w1(Double.parseDouble(df.format(delivered_w1)));tgGrandObject.setTakers_w1(Double.parseDouble(df.format(takers_w1)));tgGrandObject.setTakers_per_w1(Double.parseDouble(df.format(takers_per_w1)));
				 tgGrandObject.setDelivered_w2(Double.parseDouble(df.format(delivered_w2)));tgGrandObject.setTakers_w2(Double.parseDouble(df.format(takers_w2)));tgGrandObject.setTakers_per_w2(Double.parseDouble(df.format(takers_per_w2)));
				 tgGrandObject.setDelivered_w3(Double.parseDouble(df.format(delivered_w3)));tgGrandObject.setTakers_w3(Double.parseDouble(df.format(takers_w3)));tgGrandObject.setTakers_per_w3(Double.parseDouble(df.format(takers_per_w3)));
				 tgGrandObject.setDelivered_w4(Double.parseDouble(df.format(delivered_w4)));tgGrandObject.setTakers_w4(Double.parseDouble(df.format(takers_w4)));tgGrandObject.setTakers_per_w4(Double.parseDouble(df.format(Double.parseDouble(df.format(takers_per_w4)))));
				 tgGrandObject.setDelivered_w2w1(Double.parseDouble(df.format(delivered_w2w1)));tgGrandObject.setTakers_w2w1(Double.parseDouble(df.format(takers_w2w1)));tgGrandObject.setTakers_per_w2w1(Double.parseDouble(df.format(takers_per_w2w1)));
				 tgGrandObject.setDelivered_w3w2(Double.parseDouble(df.format(delivered_w3w2)));tgGrandObject.setTakers_w3w2(Double.parseDouble(df.format(takers_w3w2)));tgGrandObject.setTakers_per_w3w2(Double.parseDouble(df.format(takers_per_w3w2)));
				 tgGrandObject.setDelivered_w4w3(Double.parseDouble(df.format(delivered_w4w3)));tgGrandObject.setTakers_w4w3(Double.parseDouble(df.format(takers_w4w3)));tgGrandObject.setTakers_per_w4w3(Double.parseDouble(df.format(takers_per_w4w3)));
					
				tgBaselist.add(tgGrandObject);
				
		   }catch (Exception e) {e.printStackTrace(); }
		   
		
		  try
		  {
			  cgBaselist = currentSession().createCriteria(CGBaseReportTO.class).list();
			  CGBaseReportTO  cgGrandObject = new CGBaseReportTO();
			  cgGrandObject.setTg_base("Grand Total");
			  
				Double delivered_w1=0.0;Double takers_w1=0.0;Double takers_per_w1=0.0;
				Double delivered_w2=0.0;Double takers_w2=0.0;Double takers_per_w2=0.0;
				Double delivered_w3=0.0;Double takers_w3=0.0;Double takers_per_w3=0.0;
				Double delivered_w4=0.0;Double takers_w4=0.0;Double takers_per_w4=0.0;
				Double delivered_w2w1=0.0;Double takers_w2w1=0.0;Double takers_per_w2w1=0.0;
				Double delivered_w3w2=0.0;Double takers_w3w2=0.0;Double takers_per_w3w2=0.0;
				Double delivered_w4w3=0.0;Double takers_w4w3=0.0;Double takers_per_w4w3=0.0;
				
				for(CGBaseReportTO  baseObj : cgBaselist)
				{
					if(baseObj.getDelivered_w1()!=null) {delivered_w1 = delivered_w1+baseObj.getDelivered_w1();};
					if(baseObj.getTakers_w1()!=null) {takers_w1 = takers_w1+baseObj.getTakers_w1();};
					if(baseObj.getTakers_per_w1()!=null) {takers_per_w1 = takers_per_w1+baseObj.getTakers_per_w1();};
					
					if(baseObj.getDelivered_w2()!=null) {delivered_w2 = delivered_w2+baseObj.getDelivered_w2();};
					if(baseObj.getTakers_w2()!=null) {takers_w2 = takers_w2+baseObj.getTakers_w2();};
					if(baseObj.getTakers_per_w2()!=null) {takers_per_w2 = takers_per_w2+baseObj.getTakers_per_w2();};
					
					if(baseObj.getDelivered_w3()!=null) {delivered_w3 = delivered_w3+baseObj.getDelivered_w3();};
					if(baseObj.getTakers_w3()!=null) {takers_w3 = takers_w3+baseObj.getTakers_w3();};
					if(baseObj.getTakers_per_w3()!=null) {takers_per_w3 = takers_per_w3+baseObj.getTakers_per_w3();};
					
					if(baseObj.getDelivered_w4()!=null) {delivered_w4 = delivered_w4+baseObj.getDelivered_w4();};
					if(baseObj.getTakers_w4()!=null) {takers_w4 = takers_w4+baseObj.getTakers_w4();};
					if(baseObj.getTakers_per_w4()!=null) {takers_per_w4 = takers_per_w4+baseObj.getTakers_per_w4();};
					
					if(baseObj.getDelivered_w2w1()!=null) {delivered_w2w1 = delivered_w2w1+baseObj.getDelivered_w2w1();};
					if(baseObj.getTakers_w2w1()!=null) {takers_w2w1 = takers_w2w1+baseObj.getTakers_w2w1();};
					if(baseObj.getTakers_per_w2w1()!=null) {takers_per_w2w1 = takers_per_w2w1+baseObj.getTakers_per_w2w1();};
					
					if(baseObj.getDelivered_w3w2()!=null) {delivered_w3w2 = delivered_w3w2+baseObj.getDelivered_w3w2();};
					if(baseObj.getTakers_w3w2()!=null) {takers_w3w2 = takers_w3w2+baseObj.getTakers_w3w2();};
					if(baseObj.getTakers_per_w3w2()!=null) {takers_per_w3w2 = takers_per_w3w2+baseObj.getTakers_per_w3w2();};
					
					
					if(baseObj.getDelivered_w4w3()!=null) {delivered_w4w3 = delivered_w4w3+baseObj.getDelivered_w4w3();};
					if(baseObj.getTakers_w4w3()!=null) {takers_w4w3 = takers_w4w3+baseObj.getTakers_w4w3();};
					if(baseObj.getTakers_per_w4w3()!=null) {takers_per_w4w3 = takers_per_w4w3+baseObj.getTakers_per_w4w3();};
				}
				
				
				cgGrandObject.setDelivered_w1(Double.parseDouble(df.format(delivered_w1)));cgGrandObject.setTakers_w1(Double.parseDouble(df.format(takers_w1)));cgGrandObject.setTakers_per_w1(Double.parseDouble(df.format(takers_per_w1)));
				cgGrandObject.setDelivered_w2(Double.parseDouble(df.format(delivered_w2)));cgGrandObject.setTakers_w2(Double.parseDouble(df.format(takers_w2)));cgGrandObject.setTakers_per_w2(Double.parseDouble(df.format(takers_per_w2)));
				cgGrandObject.setDelivered_w3(Double.parseDouble(df.format(delivered_w3)));cgGrandObject.setTakers_w3(Double.parseDouble(df.format(takers_w3)));cgGrandObject.setTakers_per_w3(Double.parseDouble(df.format(takers_per_w3)));
				cgGrandObject.setDelivered_w4(Double.parseDouble(df.format(delivered_w4)));cgGrandObject.setTakers_w4(Double.parseDouble(df.format(takers_w4)));cgGrandObject.setTakers_per_w4(Double.parseDouble(df.format(Double.parseDouble(df.format(takers_per_w4)))));
				cgGrandObject.setDelivered_w2w1(Double.parseDouble(df.format(delivered_w2w1)));cgGrandObject.setTakers_w2w1(Double.parseDouble(df.format(takers_w2w1)));cgGrandObject.setTakers_per_w2w1(Double.parseDouble(df.format(takers_per_w2w1)));
				cgGrandObject.setDelivered_w3w2(Double.parseDouble(df.format(delivered_w3w2)));cgGrandObject.setTakers_w3w2(Double.parseDouble(df.format(takers_w3w2)));cgGrandObject.setTakers_per_w3w2(Double.parseDouble(df.format(takers_per_w3w2)));
				cgGrandObject.setDelivered_w4w3(Double.parseDouble(df.format(delivered_w4w3)));cgGrandObject.setTakers_w4w3(Double.parseDouble(df.format(takers_w4w3)));cgGrandObject.setTakers_per_w4w3(Double.parseDouble(df.format(takers_per_w4w3)));
					
					
				cgBaselist.add(cgGrandObject);
		
		}catch (Exception e) {e.printStackTrace();}
		  
		  
		try
		   { 
			       weekNetlist = currentSession().createCriteria(WeekNetConvReportTO.class).list();
			       WeekNetConvReportTO weekNetConvObj = new WeekNetConvReportTO(); 
			       weekNetConvObj.setNetconversion("Grand Total");
			       
				   Double week1=0.0;Double week2=0.0;Double week3=0.0;Double week4=0.0;Double w2w1=0.0;Double w3w2=0.0;Double w4w3=0.0;
		            for(WeekNetConvReportTO baseObj : weekNetlist)
		            {
		            	
		            	if(baseObj.getWeek1()!=null) {week1 = week1+baseObj.getWeek1();};
		            	if(baseObj.getWeek2()!=null) {week2 = week2+baseObj.getWeek2();};
		            	if(baseObj.getWeek3()!=null) {week3 = week3+baseObj.getWeek3();};
		            	if(baseObj.getWeek4()!=null) {week4 = week4+baseObj.getWeek4();};
		            	if(baseObj.getW2w1()!=null) {w2w1 = w2w1+baseObj.getW2w1();};
		            	if(baseObj.getW3w2()!=null) {w3w2 = w3w2+baseObj.getW3w2();};
		            	if(baseObj.getW4w3()!=null) {w4w3 = w4w3+baseObj.getW4w3();};
		            	
		            }
		   
		            weekNetConvObj.setWeek1(Double.parseDouble(df.format(week1)));weekNetConvObj.setWeek2(Double.parseDouble(df.format(week2)));weekNetConvObj.setWeek3(Double.parseDouble(df.format(week3)));
		            weekNetConvObj.setWeek4(Double.parseDouble(df.format(week4)));weekNetConvObj.setW2w1(Double.parseDouble(df.format(w2w1)));weekNetConvObj.setW3w2(Double.parseDouble(df.format(w3w2)));
		            weekNetConvObj.setW4w3(Double.parseDouble(df.format(w4w3)));
		            weekNetlist.add(weekNetConvObj);
		   
		   }catch (Exception e) {e.printStackTrace();}
		
		
		try
		{
			weekRevenuelist = currentSession().createCriteria(WeekRevenueBaseReportTO.class).list();
			   WeekRevenueBaseReportTO weekRevvObj = new WeekRevenueBaseReportTO(); 
			   weekRevvObj.setIncrementalrevenue("Grand Total");
		       
			   Double week1=0.0;Double week2=0.0;Double week3=0.0;Double week4=0.0;Double w2w1=0.0;Double w3w2=0.0;Double w4w3=0.0;
	            for(WeekRevenueBaseReportTO baseObj : weekRevenuelist)
	            {
	            	
	            	if(baseObj.getWeek1()!=null) {week1 = week1+baseObj.getWeek1();};
	            	if(baseObj.getWeek2()!=null) {week2 = week2+baseObj.getWeek2();};
	            	if(baseObj.getWeek3()!=null) {week3 = week3+baseObj.getWeek3();};
	            	if(baseObj.getWeek4()!=null) {week4 = week4+baseObj.getWeek4();};
	            	if(baseObj.getW2w1()!=null) {w2w1 = w2w1+baseObj.getW2w1();};
	            	if(baseObj.getW3w2()!=null) {w3w2 = w3w2+baseObj.getW3w2();};
	            	if(baseObj.getW4w3()!=null) {w4w3 = w4w3+baseObj.getW4w3();};
	            	
	            }
	   
	            weekRevvObj.setWeek1(Double.parseDouble(df.format(week1)));weekRevvObj.setWeek2(Double.parseDouble(df.format(week2)));weekRevvObj.setWeek3(Double.parseDouble(df.format(week3)));
	            weekRevvObj.setWeek4(Double.parseDouble(df.format(week4)));weekRevvObj.setW2w1(Double.parseDouble(df.format(w2w1)));weekRevvObj.setW3w2(Double.parseDouble(df.format(w3w2)));
	            weekRevvObj.setW4w3(Double.parseDouble(df.format(w4w3)));
	            weekRevenuelist.add(weekRevvObj);
		
		}catch (Exception e) {e.printStackTrace();}
		
		
		
		
		try{
			
			  shortSummarylist = currentSession().createCriteria(ShortSummaryReportTO.class).list();
			   ShortSummaryReportTO ssObj = new ShortSummaryReportTO(); 
			    ssObj.setCampaign("Grand Total");
				Double week1=0.0;Double week2=0.0;Double week3=0.0;Double week4=0.0;
				Double conv_w1=0.0;Double conv_per_w1=0.0;
				Double conv_w2=0.0;Double conv_per_w2=0.0;
				Double conv_w3=0.0;Double conv_per_w3=0.0;
				Double conv_w4=0.0;Double conv_per_w4=0.0;
				Double week1_nc=0.0; Double week2_nc=0.0;Double week3_nc=0.0;Double week4_nc=0.0;
                for(ShortSummaryReportTO baseObj : shortSummarylist)
                {
                	if(baseObj.getWeek1()!=null) {week1 = week1+Double.parseDouble(df.format(baseObj.getWeek1()));};
	            	if(baseObj.getWeek2()!=null) {week2 = week2+Double.parseDouble(df.format(baseObj.getWeek2()));};
	            	if(baseObj.getWeek3()!=null) {week3 = week3+Double.parseDouble(df.format(baseObj.getWeek3()));};
	            	if(baseObj.getWeek4()!=null) {week4 = week4+Double.parseDouble(df.format(baseObj.getWeek4()));};
                	
	            	if(baseObj.getConv_w1()!=null) {conv_w1 = conv_w1+Double.parseDouble(df.format(baseObj.getConv_w1()));};
	            	if(baseObj.getConv_per_w1()!=null) {conv_per_w1 = conv_per_w1+Double.parseDouble(df.format(baseObj.getConv_per_w1()));};
	            	
	            	if(baseObj.getConv_w2()!=null) {conv_w2 = conv_w2+Double.parseDouble(df.format(baseObj.getConv_w2()));};
	            	if(baseObj.getConv_per_w2()!=null) {conv_per_w2 = conv_per_w2+Double.parseDouble(df.format(baseObj.getConv_per_w2()));};
                	
	            	if(baseObj.getConv_w3()!=null) {conv_w3 = conv_w3+Double.parseDouble(df.format(baseObj.getConv_w3()));};
	            	if(baseObj.getConv_per_w3()!=null) {conv_per_w3 = conv_per_w3+Double.parseDouble(df.format(baseObj.getConv_per_w3()));};
	            	
	            	if(baseObj.getConv_w4()!=null) {conv_w4 = conv_w4+Double.parseDouble(df.format(baseObj.getConv_w4()));};
	            	if(baseObj.getConv_per_w4()!=null) {conv_per_w4 = conv_per_w4+Double.parseDouble(df.format(baseObj.getConv_per_w4()));};
	            	
	            	
	            	
	            	if(baseObj.getWeek1_nc()!=null) {week1_nc = week1_nc+Double.parseDouble(df.format(baseObj.getWeek1_nc()));};
	            	if(baseObj.getWeek2_nc()!=null) {week2_nc = week1_nc+Double.parseDouble(df.format(baseObj.getWeek2_nc()));};
	            	if(baseObj.getWeek3_nc()!=null) {week3_nc = week1_nc+Double.parseDouble(df.format(baseObj.getWeek3_nc()));};
	            	if(baseObj.getWeek4_nc()!=null) {week4_nc = week1_nc+Double.parseDouble(df.format(baseObj.getWeek4_nc()));};
                }
                 
                   ssObj.setWeek1(week1);ssObj.setWeek2(week2);ssObj.setWeek3(week3); ssObj.setWeek4(week4);
                   ssObj.setConv_w1(conv_w1);ssObj.setConv_per_w1(conv_per_w1);
                   ssObj.setConv_w2(conv_w2);ssObj.setConv_per_w2(conv_per_w2);
                   ssObj.setConv_w3(conv_w3);ssObj.setConv_per_w3(conv_per_w3);
                   ssObj.setConv_w4(conv_w4);ssObj.setConv_per_w4(conv_per_w4);
                   ssObj.setWeek1_nc(week1_nc);ssObj.setWeek2_nc(week2_nc);ssObj.setWeek3_nc(week3_nc); ssObj.setWeek4_nc(week4_nc);
                   shortSummarylist.add(ssObj);
		}catch (Exception e) {e.printStackTrace();}
		
		obj.setTgBaselist(tgBaselist);
		obj.setCgBaselist(cgBaselist);
		obj.setWeekNetlist(weekNetlist);
		obj.setWeekRevenuelist(weekRevenuelist);
		obj.setShortSummarylist(shortSummarylist);
		
		return obj;
	}

}
