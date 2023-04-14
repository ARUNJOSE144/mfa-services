package com.inno.mfa.services.dao;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.inno.mfa.services.model.PaginationTo;
import com.inno.mfa.services.model.trade.TradeMasterTo;

/**
 * @author Arun Jose
 * @Date : March, 2021
 */

@Repository
@Service
@Transactional
public class TradeDAO {

	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;

	static final Logger logger = Logger.getLogger(TradeDAO.class);

	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-SSS");
	Format f = new SimpleDateFormat("EEEE");
	Format f_date = new SimpleDateFormat("dd");

	public void create(TradeMasterTo to) {
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			to.setStatus(1);
			session.saveOrUpdate(to);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings("unchecked")
	public PaginationTo<TradeMasterTo> getDaysList(PaginationTo<TradeMasterTo> paginationTo) {
		List<TradeMasterTo> list = new ArrayList<TradeMasterTo>();
		try {
			Session session = sessionFactory.getCurrentSession();
			if (paginationTo.getDataTotalSize() == 0) {
				paginationTo.setDataTotalSize(getRowCount(paginationTo, session));
			}

			Criteria criteria = session.createCriteria(TradeMasterTo.class);

			criteria.addOrder(Order.desc("id"));
			criteria.setFirstResult(paginationTo.getFirstRecord());
			criteria.setMaxResults(paginationTo.getRecordCount());
			list = criteria.list();

			paginationTo.setList(list);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return paginationTo;
	}

	public Integer getRowCount(PaginationTo<TradeMasterTo> paginationTo, Session session) {
		Long count = 0l;
		try {
			Criteria criteria = session.createCriteria(TradeMasterTo.class);
			criteria.setProjection(Projections.rowCount());
			count = (Long) criteria.uniqueResult();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return count.intValue();
	}

	@SuppressWarnings("unchecked")
	public TradeMasterTo view(TradeMasterTo dto) {
		Criteria criteria = null;
		Session session = null;
		TradeMasterTo result = null;

		try {
			session = sessionFactory.getCurrentSession();
			criteria = session.createCriteria(TradeMasterTo.class);
			criteria.add(Restrictions.eq("id", dto.getId()));
			result = (TradeMasterTo) criteria.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void delete(TradeMasterTo to) {
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			session.delete(to);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
