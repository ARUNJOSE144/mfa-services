package com.sixdee.magik.services.dao;

import java.util.List;

import com.sixdee.magik.services.model.VoucherDetailsTO;

public interface VoucherDetailsLoyaltyScreenDAO {

	List<VoucherDetailsTO> getAllRecords();

	VoucherDetailsTO getSelectedRecord(int autoId);

	String saveorUpdateRecord(VoucherDetailsTO voucherDetailsTO);

	String deleteRecord(int autoId);

}
