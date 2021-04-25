package com.sixdee.magik.services.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sixdee.magik.services.dao.AuditInfoDAO;
import com.sixdee.magik.services.model.AuditInfoTO;
import com.sixdee.magik.services.service.AuditInfoService;

/**
 * @author Nakhil Kurian
 * @Date : March, 2020
 *
 */
@Service
@Transactional
public class AuditInfoServiceImpl implements AuditInfoService {

	@Autowired
	AuditInfoDAO auditInfoDAO;

	@Override
	public List<AuditInfoTO> getAuditInfo(AuditInfoTO auditInfoTO) {
		// TODO Auto-generated method stub
		return auditInfoDAO.getAuditInfo(auditInfoTO);
	}

	@Override
	public List<AuditInfoTO> viewDefaultAudit() {
		return auditInfoDAO.viewDefaultAudit();
	}

	@Override
	public String saveRuleAudit(AuditInfoTO auditTO) {
		return auditInfoDAO.saveRuleAudit(auditTO);
	}

}
