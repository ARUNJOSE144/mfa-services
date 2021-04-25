package com.sixdee.magik.services.dao;

import java.util.List;

import com.sixdee.magik.services.model.ConfigureCategoryTO;
import com.sixdee.magik.services.model.UploadVoucherTO;
import com.sixdee.magik.services.model.VoucherAssigningTO;
import com.sixdee.magik.services.model.VoucherGenerationTO;

/**
 * @author ABHIRAM MACHIRAJU
 * @Date : January, 2020
 */


public interface VoucherDAO {
	
		List<VoucherGenerationTO> getVoucherGenerationList();
		void generateVoucher(VoucherGenerationTO voucherGenerationTO);
		List<VoucherAssigningTO> getVoucherAssigningList();
		void assignVoucher(VoucherAssigningTO voucherAssigningTO);
		List<UploadVoucherTO> getUploadVoucherList();
		void saveUploadVoucher(UploadVoucherTO uploadvoucherto);
		List<ConfigureCategoryTO> getLoyalityCategoryList();
		void saveLoyalityCategoryList(ConfigureCategoryTO configurecategoryto);
		void deleteCategory(ConfigureCategoryTO configurecategoryto);
}
