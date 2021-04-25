package com.sixdee.magik.services.service;

import com.sixdee.magik.services.model.OfflineUploadSegmentTO;

/**
 * @author Nakhil Kurian
 * @Date : January, 2021
 */
public interface OfflineUploadSegmentService {

	String saveOfflineSegment(String campaignOperationType, String userId);

	OfflineUploadSegmentTO insertOrDeleteSingle(String campaignOperationType, String userId, String mobileNumber,
			String microSegment);

}
