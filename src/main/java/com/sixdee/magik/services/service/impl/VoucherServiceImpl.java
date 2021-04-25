package com.sixdee.magik.services.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sixdee.magik.services.dao.VoucherDAO;
import com.sixdee.magik.services.model.ConfigureCategoryTO;
import com.sixdee.magik.services.model.UploadVoucherTO;
import com.sixdee.magik.services.model.VoucherAssigningTO;
import com.sixdee.magik.services.model.VoucherGenerationTO;
import com.sixdee.magik.services.service.VoucherService;

/**
 * @author ABHIRAM MACHIRAJU
 * @Date : January, 2020
 */


@Service
@Transactional
public class VoucherServiceImpl implements VoucherService {

	@Autowired VoucherDAO voucherDAO;
	
	@Override
	public List<VoucherGenerationTO> getVoucherGenerationList() {
		return voucherDAO.getVoucherGenerationList();
	}

	@Override
	public void generateVoucher(VoucherGenerationTO voucherGenerationTO) {
		voucherDAO.generateVoucher(voucherGenerationTO);		
	}

	@Override
	public List<VoucherAssigningTO> getVoucherAssigningList() {
		return voucherDAO.getVoucherAssigningList();
	}

	@Override
	public void assignVoucher(VoucherAssigningTO voucherAssigningTO) {
		voucherDAO.assignVoucher(voucherAssigningTO);	
		
	}

	@Override
	public List<UploadVoucherTO> getUploadVoucherList() {
		return voucherDAO.getUploadVoucherList();
	}

	@Override
	public void saveUploadVoucher(UploadVoucherTO uploadvoucherto) {
		voucherDAO.saveUploadVoucher(uploadvoucherto);	
		
	}

	@Override
	public List<ConfigureCategoryTO> getLoyalityCategoryList() {
		return voucherDAO.getLoyalityCategoryList();
	}

	@Override
	public void saveLoyalityCategoryList(ConfigureCategoryTO configurecategoryto) {
		voucherDAO.saveLoyalityCategoryList(configurecategoryto);
		
	}

	@Override
	public void deleteCategory(ConfigureCategoryTO configurecategoryto) {
		 voucherDAO.deleteCategory(configurecategoryto);
	}

}
