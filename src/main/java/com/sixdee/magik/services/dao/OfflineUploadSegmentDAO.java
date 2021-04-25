package com.sixdee.magik.services.dao;

import com.sixdee.magik.services.model.OfflineUploadSegmentTO;

/**
 * @author Nakhil Kurian
 * @Date : January, 2021
 */

public interface OfflineUploadSegmentDAO {

	String saveOfflineSegment(String campaignOperationType, String userId);

	OfflineUploadSegmentTO insertOrDeleteSingle(String campaignOperationType, String userId, String mobileNumber,
			String microSegment);

}
