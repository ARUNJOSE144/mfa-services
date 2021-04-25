package com.sixdee.magik.services.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sixdee.magik.services.dao.OfflineUploadSegmentDAO;
import com.sixdee.magik.services.model.OfflineUploadSegmentTO;
import com.sixdee.magik.services.service.OfflineUploadSegmentService;

/**
 * @author Nakhil Kurian
 * @Date : January, 2021
 */

@Service
@Transactional
public class OfflineUploadSegmentServiceImpl implements OfflineUploadSegmentService {
	@Autowired
	OfflineUploadSegmentDAO offlineUploadSegmentDao;

	@Override
	public String saveOfflineSegment(String campaignOperationType, String userId) {
		// TODO Auto-generated method stub
		return offlineUploadSegmentDao.saveOfflineSegment(campaignOperationType, userId);
	}

	@Override
	public OfflineUploadSegmentTO insertOrDeleteSingle(String campaignOperationType, String userId, String mobileNumber,
			String microSegment) {
		// TODO Auto-generated method stub
		return offlineUploadSegmentDao.insertOrDeleteSingle(campaignOperationType, userId, mobileNumber, microSegment);
	}

}
