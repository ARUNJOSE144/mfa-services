package com.sixdee.magik.services.service;

import java.util.List;

import com.sixdee.magik.services.model.AuditInfoTO;

/**
 * @author Nakhil Kurian
 * @Date : March, 2020
 *
 */
public interface AuditInfoService {
	
	public List<AuditInfoTO> getAuditInfo(AuditInfoTO auditInfoTO);

	public List<AuditInfoTO> viewDefaultAudit();

	public String saveRuleAudit(AuditInfoTO auditTO);

}
