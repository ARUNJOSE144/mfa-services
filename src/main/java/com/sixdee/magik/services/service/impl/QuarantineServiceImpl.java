package com.sixdee.magik.services.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sixdee.magik.services.dao.QuarantineDAO;
import com.sixdee.magik.services.model.QuarantineBlackListTO;
import com.sixdee.magik.services.model.QuarantineDetailsTO;
import com.sixdee.magik.services.model.QuarantineTO;
import com.sixdee.magik.services.service.QuarantineService;

/**
 * @author arun.jose
 *
 * January, 2019
 */
@Service
@Transactional
public class QuarantineServiceImpl implements QuarantineService{
	
	@Autowired
	QuarantineDAO quarantineDAO;

	@Override
	public String createQuarantinePolicy(QuarantineTO quarantineTO) {
		// TODO Auto-generated method stub
		return quarantineDAO.createQuarantinePolicy(quarantineTO);
	}

	@Override
	public List<QuarantineTO> getQuarantinePolicyList() {
		// TODO Auto-generated method stub
		return quarantineDAO.getQuarantinePolicyList();
	}

	@Override
	public String deleteQuarantinePolicy(QuarantineTO quarantineTO) {
		// TODO Auto-generated method stub
		return quarantineDAO.deleteQuarantinePolicy(quarantineTO);
	}

	@Override
	public String uploadBlacklist(QuarantineBlackListTO testto) {
		return quarantineDAO.uploadBlacklist(testto);

	}

	@Override
	public List<QuarantineDetailsTO> editQuarantineDetails(int id) {
		return quarantineDAO.editQuarantineDetails(id);
	}

}
