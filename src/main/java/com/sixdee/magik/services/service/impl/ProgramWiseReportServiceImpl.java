package com.sixdee.magik.services.service.impl;

import java.util.List;

import com.sixdee.magik.services.dao.ProgramWiseReportDAO;
import com.sixdee.magik.services.model.ProgramWiseReportTO;
import com.sixdee.magik.services.service.ProgramWiseReportService;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



/**
 * @author JANARDHAN REDDY
 * @Date : February, 2021
 */


@Service
@Transactional
public class ProgramWiseReportServiceImpl implements ProgramWiseReportService{

	
	@Autowired ProgramWiseReportDAO daoLayer;
	
	@Override
	public List<ProgramWiseReportTO> getData() {
		// TODO Auto-generated method stub
		return daoLayer.getData();
	}

}
