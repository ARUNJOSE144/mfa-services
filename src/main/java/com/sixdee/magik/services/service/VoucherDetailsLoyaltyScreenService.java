package com.sixdee.magik.services.service;

import java.util.List;

import com.sixdee.magik.services.model.VoucherDetailsTO;

public interface VoucherDetailsLoyaltyScreenService {

	List<VoucherDetailsTO> getAllRecords();

	VoucherDetailsTO getSelectedRecord(int autoId);

	String saveorUpdateRecord(VoucherDetailsTO voucherDetailsTO);

	String deleteRecord(int autoId);

}
