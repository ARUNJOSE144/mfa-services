package com.sixdee.magik.services.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sixdee.magik.services.dao.GeoFencingDao;
import com.sixdee.magik.services.model.GeoLocationsTO;

/**
 * 
 * @author ramesh.cheerla
 *
 */

@Repository
@Transactional
public class GeoFencingDaoImpl implements GeoFencingDao {
	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;

	@Override
	public int saveGeoLocation(GeoLocationsTO to) {

		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(to);

		return to.getId();
	}

	@Override
	public List<GeoLocationsTO> getGeoLocationsTree() {

		Session session = sessionFactory.getCurrentSession();

		List<GeoLocationsTO> list = new ArrayList<GeoLocationsTO>();
		List<Object[]> rs = null;

		String hql = "SELECT id, locationName, parentId, category FROM GeoLocationsTO";

		rs = (List<Object[]>) session.createQuery(hql).list();

		GeoLocationsTO to = null;

		for (Object[] obj : rs) {

			to = new GeoLocationsTO();
			to.setId(Integer.parseInt(obj[0] + ""));
			to.setLocationName(obj[1] + "");
			to.setParentId(Integer.parseInt(obj[2] + ""));
			to.setCategory(Integer.parseInt(obj[3] + ""));

			list.add(to);

		}

		return list;
	}

	@Override
	public String deleteGeoLocation(int locId) {

		Criteria criteria = null;
		Session session = null;
		String message = null;

		try {
			session = sessionFactory.getCurrentSession();
			criteria = session.createCriteria(GeoLocationsTO.class);
			criteria.add(Restrictions.eq("id", locId));
			GeoLocationsTO geoLocationsTO = (GeoLocationsTO) criteria.uniqueResult();
			session.delete(geoLocationsTO);
			message = "Sucess";
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
		return message;
	}

	@Override
	public GeoLocationsTO getGeoLocation(int locId) {

		Session session = sessionFactory.getCurrentSession();

		GeoLocationsTO info = (GeoLocationsTO) session.get(GeoLocationsTO.class, locId);

		return info;
	}

	@Override
	public int updateGeoLocation(GeoLocationsTO to) {

		Session session = sessionFactory.getCurrentSession();

		GeoLocationsTO info = (GeoLocationsTO) session.get(GeoLocationsTO.class, to.getId());
		info.setMapJson(to.getMapJson());

		session.update(info);

		return to.getId();
	}

}
