package com.sixdee.magik.services.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sixdee.magik.services.dao.SubscriberBaseReportDAO;
import com.sixdee.magik.services.model.SubscriberBaseReportTO;
import com.sixdee.magik.services.service.SubscriberBaseReportService;


/**
 * @author JANARDHAN REDDY
 * @Date : February, 2021
 */


@Service
@Transactional
public class SubscriberBaseReportServiceImpl implements SubscriberBaseReportService {

	@Autowired SubscriberBaseReportDAO daoLayer;
	
	@Override
	public List<SubscriberBaseReportTO> getData() {
		// TODO Auto-generated method stub
		return daoLayer.getData();
	}

}
