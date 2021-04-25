package com.sixdee.magik.services.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sixdee.magik.services.dao.MTDLMTDReportDAO;
import com.sixdee.magik.services.model.MTDLMTDTO;
import com.sixdee.magik.services.service.MTDLMTDReportService;


@Service
@Transactional
public class MTDLMTDReportServiceImpl implements MTDLMTDReportService {

	@Autowired MTDLMTDReportDAO daoLayer;
	 
	@Override
	public List<MTDLMTDTO> getData() {
		// TODO Auto-generated method stub
		return daoLayer.getData();
	}

}
