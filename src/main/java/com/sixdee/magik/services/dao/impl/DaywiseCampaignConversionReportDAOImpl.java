package com.sixdee.magik.services.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.sixdee.magik.services.dao.DaywiseCampaignConversionReportDAO;
import com.sixdee.magik.services.model.CampaignDefMasterTO;
import com.sixdee.magik.services.model.CampaignMasterTO;
import com.sixdee.magik.services.model.DaywiseCampaignConversionTO;
import com.sixdee.magik.services.model.PaginationDTO;

/**
 * @author ABHIRAM MACHIRAJU
 * @Date : February, 2021
 */

//@SuppressWarnings({ "unchecked", "deprecation" })
@Repository
public class DaywiseCampaignConversionReportDAOImpl implements DaywiseCampaignConversionReportDAO {

	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;
	private Query query = null;
	private String hql;

	protected Session currentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public List<DaywiseCampaignConversionTO> getData() {

		List<DaywiseCampaignConversionTO> list = currentSession().createCriteria(DaywiseCampaignConversionTO.class)
				.list();

		// SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		for (DaywiseCampaignConversionTO parentObj : list) {
			// Hibernate.initialize(parentObj.getCampMasterTO()); //Optional to check
			// String transientDate_STR= formatter.format(parentObj.getPromotionDate());
			// parentObj.setPromotionDates(transientDate_STR);

		}
		return list;

	}

	@Override
	public PaginationDTO<DaywiseCampaignConversionTO> getAllRecordsWithPagination(
			PaginationDTO<DaywiseCampaignConversionTO> paginationDTO, boolean isDowdload) {
		// getting RowCount
		String sql = "";
		List<DaywiseCampaignConversionTO> list = null;

		try {
			Session session = sessionFactory.getCurrentSession();
			if (paginationDTO.getDataTotalSize() == 0)
				paginationDTO.setDataTotalSize(getRowCount(session, paginationDTO));

			sql = "FROM DaywiseCampaignConversionTO ";
			System.out.println("pagination To  : " + paginationDTO);
			sql += " where 1=1 ";

			if (validate(paginationDTO.getSearchKey1()))
				sql += " and  campaignName= '" + paginationDTO.getSearchKey1() + "'";

			if (validate(paginationDTO.getSearchKey2()))
				sql += "and promotionDate= '" + paginationDTO.getSearchKey2() + "'";

			/*
			 * if (validate(paginationDTO.getSortKey1())) { sql += " order by " +
			 * paginationDTO.getSortKey1(); } else { sql += " order by roleId DESC"; }
			 */

			sql += " order by autoId DESC";
			System.out.println("SQL Query : " + sql);

			query = session.createQuery(sql);
			if (!isDowdload) {
				query.setMaxResults(paginationDTO.getRecordCount());
				query.setFirstResult(paginationDTO.getFirstRecord());
			}
			list = (List<DaywiseCampaignConversionTO>) query.list();
			paginationDTO.setData(list);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return paginationDTO;

	}

	public int getRowCount(Session session, PaginationDTO<DaywiseCampaignConversionTO> paginationDTO) {
		String sql = null;
		int rowCount = 0;
		try {
			sql = "SELECT COUNT(*)FROM DaywiseCampaignConversionTO ";
			sql += " where 1=1 ";

			if (validate(paginationDTO.getSearchKey1()))
				sql += " and  campaignName= '" + paginationDTO.getSearchKey1() + "'";

			if (validate(paginationDTO.getSearchKey2()))
				sql += "and promotionDate= '" + paginationDTO.getSearchKey2() + "'";

			query = session.createQuery(sql);
			List<Long> count = query.list();
			rowCount = Integer.parseInt(count.get(0).toString());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return rowCount;
	}

	boolean validate(String val) {
		if (val != null && !val.equalsIgnoreCase("Undefined") && !val.equalsIgnoreCase(""))
			return true;
		else
			return false;
	}

	@Override
	public List<CampaignDefMasterTO> getCampaignData() {
		List<CampaignDefMasterTO> list = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(CampaignDefMasterTO.class);
			list = criteria.list();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;

	}
}
