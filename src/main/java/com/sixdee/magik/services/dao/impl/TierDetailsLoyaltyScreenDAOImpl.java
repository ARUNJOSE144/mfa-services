package com.sixdee.magik.services.dao.impl;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.sixdee.magik.services.dao.TierDetailsLoyaltyScreenDAO;
import com.sixdee.magik.services.model.CategoryLoyaltyLMSTO;
import com.sixdee.magik.services.model.TierConfigurationLMSTO;
import com.sixdee.magik.services.model.TierDetailsLMSTO;

/**
 * @author ABHIRAM MACHIRAJU
 * @Date : March, 2021
 */
@SuppressWarnings("unchecked")
@Repository
public class TierDetailsLoyaltyScreenDAOImpl implements TierDetailsLoyaltyScreenDAO {

	@Autowired
	@Qualifier("lmsConfigSessionFactory")
	private SessionFactory sessionFactory;
	private static AtomicInteger ID_GENERATOR = new AtomicInteger(25);

	protected Session currentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public List<TierDetailsLMSTO> getAllRecords() {
		List<TierDetailsLMSTO> list = currentSession().createCriteria(TierDetailsLMSTO.class).addOrder(Order.desc("autoId")).list();
		return list;
	}

	@Override
	public String deleteRecord(int autoId) {
		String statusCode = "SC00001";
		try {
			TierDetailsLMSTO obj = (TierDetailsLMSTO) currentSession().get(TierDetailsLMSTO.class, autoId);
			currentSession().delete(obj);
			statusCode = "SC0000";
		} catch (Exception e) {
			e.printStackTrace();
		}

		return statusCode;
	}

	@Override
	public String saveorUpdateRecord(TierDetailsLMSTO tierDetailsTO) {
		String statusCode = "SC00001";
		try {
			// CreateTime.....
			if (tierDetailsTO.getAutoId() == 0) {
				//tierDetailsTO.setAutoId(ID_GENERATOR.getAndIncrement());
				System.out.println("ID_GENERATOR  :  " + tierDetailsTO.getAutoId());
				currentSession().save(tierDetailsTO);
			} else {
				// EditTime...
				currentSession().saveOrUpdate(tierDetailsTO);
			}
			statusCode = "SC0000";
		} catch (Exception e) {
			e.printStackTrace();
		}

		return statusCode;
	}

	
	@Override
	public List<CategoryLoyaltyLMSTO> saveTierCategory(CategoryLoyaltyLMSTO categoryLoyaltyTO) {
		List<CategoryLoyaltyLMSTO> list = null;
		try {
			categoryLoyaltyTO.setUnitCalc("N");
			currentSession().save(categoryLoyaltyTO);
			list = currentSession().createCriteria(CategoryLoyaltyLMSTO.class).list();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public List<TierConfigurationLMSTO> getAllConfigRecords() {
		List<TierConfigurationLMSTO> list = currentSession().createCriteria(TierConfigurationLMSTO.class).addOrder(Order.desc("autoId")).list();
		return list;
	}

	@Override
	public List<CategoryLoyaltyLMSTO> getAllCategory() {
		List<CategoryLoyaltyLMSTO> list = currentSession().createCriteria(CategoryLoyaltyLMSTO.class).list();
		return list;
	}

	@Override
	public String deleteConfigRecord(int autoId) {
		String statusCode = "SC00001";
		Boolean childRef = false;
		TierConfigurationLMSTO obj = null;
		try {

			List<TierDetailsLMSTO> tierDtlsList = currentSession().createCriteria(TierDetailsLMSTO.class).list();
			/*
			 * tierDtls.forEach((item)->{if(item!=null && item.getAutoId() ==
			 * autoId){childRef=true; }});
			 */

			for (TierDetailsLMSTO dtlsObj : tierDtlsList) {
				if (dtlsObj.getTierConfigurationTO().getAutoId() == autoId) {
					childRef = true;
					break;
				}
			}

			if (childRef) {

				statusCode = "SC0005";
			} else {
				String query = " from TierConfigurationLMSTO where autoId = '" + autoId + "'";
				obj = (TierConfigurationLMSTO) currentSession().createQuery(query).uniqueResult();
				// TierConfigurationTO obj
				// =(TierConfigurationTO)currentSession().get(TierConfigurationTO.class,autoId);
				currentSession().delete(obj);
				statusCode = "SC0000";

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return statusCode;
	}

	@Override
	public String saveorUpdateConfigRecord(TierConfigurationLMSTO tierConfigTO) {
		String statusCode = "SC00001";
		try {
			// CreateTime.....
			if (tierConfigTO.getAutoId() == null || tierConfigTO.getAutoId() == 0) {

				currentSession().save(tierConfigTO);
			} else {
				// EditTime...
				currentSession().saveOrUpdate(tierConfigTO);
			}
			statusCode = "SC0000";
		} catch (Exception e) {
			e.printStackTrace();
		}

		return statusCode;
	}

}
