package com.sixdee.magik.services.service.impl;

import java.util.List;

import com.sixdee.magik.services.dao.RewardExpiryReportDAO;
import com.sixdee.magik.services.model.RewardExpiryReportTO;
import com.sixdee.magik.services.service.RewardExpiryReportService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author JANARDHAN REDDY
 * @Date : February, 2021
 */


@Service
@Transactional
public class RewardExpiryReportServiceImpl implements RewardExpiryReportService{

	@Autowired RewardExpiryReportDAO daoLayer;
	
	@Override
	public List<RewardExpiryReportTO> getData() {
		// TODO Auto-generated method stub
		return daoLayer.getData();
	}

}
