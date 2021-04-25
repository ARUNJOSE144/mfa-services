package com.sixdee.magik.services.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sixdee.magik.services.dao.LeaderReportDAO;
import com.sixdee.magik.services.model.LeaderReportTO;
import com.sixdee.magik.services.service.LeaderReportService;

/**
 * @author JANARDHAN REDDY
 * @Date : February, 2021
 */


@Service
@Transactional
public class LeaderReportServiceImpl implements LeaderReportService{

	@Autowired LeaderReportDAO daoLayer;
	
	@Override
	public List<LeaderReportTO> getData() {
		// TODO Auto-generated method stub
		return daoLayer.getData();
	}

}
