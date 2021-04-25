package com.sixdee.magik.services.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sixdee.magik.services.dao.EnrolmentReportDAO;
import com.sixdee.magik.services.model.EnrolmentTO;
import com.sixdee.magik.services.service.EnrolmentReportService;


/**
 * @author JANARDHAN REDDY
 * @Date : February, 2021
 */


@Service
@Transactional
public class EnrolmentReportServiceImpl implements EnrolmentReportService  {

	@Autowired EnrolmentReportDAO daoLayer;

	@Override
	public List<EnrolmentTO> getData() {
		// TODO Auto-generated method stub
		return daoLayer.getData();
	}

	/*@Override
	public List<EnrolmentTO> getEnrolmentSearchData(EnrolmentTO enrolmentTO) {
		// TODO Auto-generated method stub
		return daoLayer.getEnrolmentSearchData(enrolmentTO);
	}
*/
	
	
}
