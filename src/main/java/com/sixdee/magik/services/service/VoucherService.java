package com.sixdee.magik.services.service;

import java.util.List;

import com.sixdee.magik.services.model.ConfigureCategoryTO;
import com.sixdee.magik.services.model.RoleMaster;
import com.sixdee.magik.services.model.UploadVoucherTO;
import com.sixdee.magik.services.model.VoucherAssigningTO;
import com.sixdee.magik.services.model.VoucherGenerationTO;

/**
 * @author ABHIRAM MACHIRAJU
 * @Date : January, 2020
 */


public interface VoucherService {
	
	//VoucherGeneration
	List<VoucherGenerationTO> getVoucherGenerationList();
	void generateVoucher(VoucherGenerationTO voucherGenerationTO);
	
	//VoucherAssigning
	List<VoucherAssigningTO> getVoucherAssigningList();
	void assignVoucher(VoucherAssigningTO voucherAssigningTO);
	
	
	//VoucherUpload
	List<UploadVoucherTO> getUploadVoucherList();
	void saveUploadVoucher(UploadVoucherTO uploadvoucherto);
	
	
	//Configure Category
		List<ConfigureCategoryTO> getLoyalityCategoryList();
		void saveLoyalityCategoryList(ConfigureCategoryTO configurecategoryto);
		void deleteCategory(ConfigureCategoryTO configurecategoryto);

}
