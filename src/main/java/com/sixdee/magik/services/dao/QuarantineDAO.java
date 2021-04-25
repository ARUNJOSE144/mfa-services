/**
 * 
 */
package com.sixdee.magik.services.dao;

import java.util.List;

import com.sixdee.magik.services.model.QuarantineBlackListTO;
import com.sixdee.magik.services.model.QuarantineDetailsTO;
import com.sixdee.magik.services.model.QuarantineTO;

/**
 * @author arun.jose
 * January 2019
 */
public interface QuarantineDAO {
	
	public String createQuarantinePolicy(QuarantineTO quarantineTO);

	public List<QuarantineTO> getQuarantinePolicyList();

	public String deleteQuarantinePolicy(QuarantineTO quarantineTO);

	public String uploadBlacklist(QuarantineBlackListTO testto);

	public List<QuarantineDetailsTO> editQuarantineDetails(int id);

}
