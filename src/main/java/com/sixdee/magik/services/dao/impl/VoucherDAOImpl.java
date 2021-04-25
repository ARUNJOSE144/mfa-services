package com.sixdee.magik.services.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.sixdee.magik.services.dao.VoucherDAO;
import com.sixdee.magik.services.model.ConfigureCategoryTO;
import com.sixdee.magik.services.model.UploadVoucherTO;
import com.sixdee.magik.services.model.VoucherAssigningTO;
import com.sixdee.magik.services.model.VoucherGenerationTO;
import com.sixdee.magik.services.model.WhatsAppDetailsTO;

/**
 * @author ABHIRAM MACHIRAJU
 * @Date : January, 2021
 */


@Repository
public class VoucherDAOImpl implements VoucherDAO {
	
	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;
	
	
	protected Session currentSession()
	{
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public List<VoucherGenerationTO> getVoucherGenerationList() {
		
		List<VoucherGenerationTO> list = currentSession().createCriteria(VoucherGenerationTO.class).list();
		return list;
		
	}

	@Override
	public void generateVoucher(VoucherGenerationTO voucherGenerationTO) {
		
		voucherGenerationTO.setStatus("Processing");
		 currentSession().saveOrUpdate(voucherGenerationTO);
		
		
	}

	@Override
	public List<VoucherAssigningTO> getVoucherAssigningList() {
		List<VoucherAssigningTO> list = currentSession().createCriteria(VoucherAssigningTO.class).list();
		return list;
	}

	@Override
	public void assignVoucher(VoucherAssigningTO voucherAssigningTO) {
		
		try {
		VoucherGenerationTO vouchergenerationto = new VoucherGenerationTO();
		vouchergenerationto.setAutoId(Integer.parseInt(voucherAssigningTO.getBatchNametransient()));
		
		voucherAssigningTO.setVoucherGenerationTO(vouchergenerationto);
		 currentSession().saveOrUpdate(voucherAssigningTO);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<UploadVoucherTO> getUploadVoucherList() {
		List<UploadVoucherTO> list = currentSession().createCriteria(UploadVoucherTO.class).list();
		return list;
	}

	@Override
	public void saveUploadVoucher(UploadVoucherTO uploadvoucherto) {
		 currentSession().saveOrUpdate(uploadvoucherto);
		
	}

	@Override
	public List<ConfigureCategoryTO> getLoyalityCategoryList() {
		List<ConfigureCategoryTO> list = currentSession().createCriteria(ConfigureCategoryTO.class).list();
		return list;
	}

	@Override
	public void saveLoyalityCategoryList(ConfigureCategoryTO configurecategoryto) {
		 currentSession().saveOrUpdate(configurecategoryto);
		
	}

	@Override
	public void deleteCategory(ConfigureCategoryTO configurecategoryto) {
		
		 String queryStr = "from ConfigureCategoryTO where autoId = '" + configurecategoryto.getAutoId() + "'";
		 ConfigureCategoryTO parentObj = (ConfigureCategoryTO)currentSession().createQuery(queryStr).uniqueResult();
		 currentSession().delete(parentObj);
	}

}
