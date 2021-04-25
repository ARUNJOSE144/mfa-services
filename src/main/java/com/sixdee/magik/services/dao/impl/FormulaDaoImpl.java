package com.sixdee.magik.services.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.sixdee.magik.services.dao.FormulaDao;
import com.sixdee.magik.services.model.FormulaTO;
import com.sixdee.magik.services.model.KPITO;
import com.sixdee.magik.services.util.DateFormat;

/**
 * @author arun.jose
 * @Date : September, 2018
 *
 */

@Repository
public class FormulaDaoImpl implements FormulaDao {

	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;
	private String query = null;

	DateFormat dateFormat = new DateFormat();

	String pattern = "dd MMM yyyy";
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

	@Override
	public String saveFormula(FormulaTO formulaTO) {
		Session session = sessionFactory.getCurrentSession();
		String statusCode = "SC0001";
		String flag = "SAVE";
		String query = "";

		if (formulaTO.getId() != 0) {
			flag = "EDIT";
		} else {

			formulaTO.setUserId(0);
		}
		formulaTO.setCreateDate(new Date());

		System.out.println("----------id : " + formulaTO.getId());
		session.saveOrUpdate(formulaTO);

		// Adding Entry in Business KPI Table
		if (flag.equalsIgnoreCase("SAVE")) {
			KPITO kpito = new KPITO();
			kpito.setName(formulaTO.getName());
			kpito.setProfileName(formulaTO.getDescription());
			kpito.setGroupId(-1);
			kpito.setSubGroupId(-1);
			kpito.setDataTypeId(-1);
			kpito.setCretedBy(formulaTO.getUserId() + "");
			kpito.setFormulaId(formulaTO.getId());
			kpito.setProfileName(formulaTO.getProfileName());
			kpito.setCretedDate(new Date());
			kpito.setLastUpdatedDate(new Date());

			session.save(kpito);

		} else {

			query = "from KPITO where formulaId=" + formulaTO.getId();
			KPITO kpito = (KPITO) session.createQuery(query).uniqueResult();
			if (kpito != null) {
				kpito.setName(formulaTO.getName());
				kpito.setProfileName(formulaTO.getDescription());
				kpito.setProfileName(formulaTO.getProfileName());
				session.save(kpito);
			}
		}

		statusCode = "SC0000";

		return statusCode;
	}

	@Override
	public List<FormulaTO> getFormulaList(FormulaTO formulaTO) {
		Session session = sessionFactory.getCurrentSession();
		List<FormulaTO> rs = null;
		List<FormulaTO> list = new ArrayList<FormulaTO>();
		FormulaTO to = null;

		query = "FROM FormulaTO ";
		if (formulaTO.getUserId() != 0)
			query += " where userId = " + formulaTO.getUserId();

		query += " order by id DESC";

		rs = (List<FormulaTO>) session.createQuery(query).list();

		for (FormulaTO obj : rs) {
			to = new FormulaTO();
			to.setId(obj.getId());
			to.setName(obj.getName());

			// to.setCreateDate(obj.getCreateDate() + "");
			to.setCreateDateUI(simpleDateFormat.format(obj.getCreateDate()));
			// to.setUpdatedDate(new Date(obj[3] + ""));
			to.setUserId(obj.getUserId());

			list.add(to);
		}
		return list;
	}

	@Override
	public FormulaTO getFormula(FormulaTO formulaTO) {
		Session session = sessionFactory.getCurrentSession();
		String statusCode = "SC0001";

		query = "from FormulaTO where id=" + formulaTO.getId();
		FormulaTO to = (FormulaTO) session.createQuery(query).uniqueResult();

		return to;
	}

	@Override
	public String deleteFormula(FormulaTO formulaTO) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		String statusCode = "SC0001";
		session.delete(formulaTO);
		query = "from KPITO where formulaId=" + formulaTO.getId();
		KPITO to = (KPITO) session.createQuery(query).uniqueResult();
		session.delete(to);
		statusCode = "SC0000";

		return statusCode;
	}

}
