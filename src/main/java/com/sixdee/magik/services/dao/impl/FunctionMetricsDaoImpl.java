package com.sixdee.magik.services.dao.impl;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.sixdee.magik.services.dao.FunctionMetricsDao;
import com.sixdee.magik.services.model.FunctionMetricsTO;
import com.sixdee.magik.services.util.DateFormat;

/**
 * @author arun.jose
 * @Date : September, 2018
 *
 */

@Repository
public class FunctionMetricsDaoImpl implements FunctionMetricsDao {

	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;
	private String query = null;

	DateFormat dateFormat = new DateFormat();

	String pattern = "dd MMM yyyy";
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
	/*
	 * User and Language validation
	 */

	@Override
	public String saveFunctionMetrics(FunctionMetricsTO funTo) {
		Session session = sessionFactory.getCurrentSession();
		String statusCode = "SC0001";
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yy");
		Date dateobj = new Date();
		System.out.println(df.format(dateobj));
		funTo.setCreateDate(dateobj);
		funTo.setUpdatedDate(dateobj.toString());
		session.saveOrUpdate(funTo);
		statusCode = "SC0000";

		return statusCode;
	}

	@Override
	public List<FunctionMetricsTO> getFunctionMetricsList(FunctionMetricsTO funTo) {
		Session session = sessionFactory.getCurrentSession();
		List<FunctionMetricsTO> rs = null;
		List<FunctionMetricsTO> list = new ArrayList<FunctionMetricsTO>();
		FunctionMetricsTO to = null;

		query = "FROM FunctionMetricsTO order by id desc";
		if (funTo.getUserId() != 0)
			query += " where userId = " + funTo.getUserId();
		if (funTo.getName() != null)
			query += " and  name like '" + funTo.getName() + "%'";

		rs = (List<FunctionMetricsTO>) session.createQuery(query).list();

		for (FunctionMetricsTO obj : rs) {
			to = new FunctionMetricsTO();
			to.setId(obj.getId());
			to.setName(obj.getName());

			to.setCreateDateUI(simpleDateFormat.format(obj.getCreateDate()));
			// to.setUpdatedDate(new Date(obj[3] + ""));
			to.setUserId(obj.getUserId());

			list.add(to);
		}
		return list;
	}

	@Override
	public FunctionMetricsTO getFunctionMetrics(int funId) {
		Session session = sessionFactory.getCurrentSession();
		String statusCode = "SC0001";

		query = "from FunctionMetricsTO where id=" + funId;
		FunctionMetricsTO to = (FunctionMetricsTO) session.createQuery(query).uniqueResult();

		return to;
	}

	@Override
	public String deleteFunctionMetrics(FunctionMetricsTO funTo) {
		String statusCode = "1";
		Session session = sessionFactory.getCurrentSession();

		session.delete(funTo);
		statusCode = "0";

		return statusCode;
	}

}
