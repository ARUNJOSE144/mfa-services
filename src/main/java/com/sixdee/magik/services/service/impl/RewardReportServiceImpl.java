package com.sixdee.magik.services.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sixdee.magik.services.dao.RewardReportDAO;
import com.sixdee.magik.services.model.RewardReportTO;
import com.sixdee.magik.services.service.RewardReportService;

/**
 * @author JANARDHAN REDDY
 * @Date : February, 2021
 */


@Service
@Transactional
public class RewardReportServiceImpl implements RewardReportService{

	@Autowired  RewardReportDAO daoLayer;
	
	@Override
	public List<RewardReportTO> getData() {
		// TODO Auto-generated method stub
		return  daoLayer.getData();
	}

}
