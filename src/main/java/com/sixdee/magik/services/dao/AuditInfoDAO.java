/**
 * 
 */
package com.sixdee.magik.services.dao;

import java.util.List;

import com.sixdee.magik.services.model.AuditInfoTO;

/**
 * @author Nakhil Kurian
 * @Date : March, 2020
 *
 */
public interface AuditInfoDAO {
	public List<AuditInfoTO> getAuditInfo(AuditInfoTO auditInfoTO);

	public void addAuditLog(AuditInfoTO auditInfoTO);

	List<AuditInfoTO> viewDefaultAudit();

	public String saveRuleAudit(AuditInfoTO auditTO);
}
