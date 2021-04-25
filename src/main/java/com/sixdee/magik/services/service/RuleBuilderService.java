package com.sixdee.magik.services.service;

import java.util.List;

import com.sixdee.magik.services.model.ActionFileTO;
import com.sixdee.magik.services.model.PaginationDTO;
import com.sixdee.magik.services.model.RemoteCopyUserTO;
import com.sixdee.magik.services.model.RuleBuilderTO;
import com.sixdee.magik.services.model.RuleTO;
import com.sixdee.magik.services.model.ScheduleTO;
import com.sixdee.magik.services.model.TokenMaster;

/**
 * @author ramesh.cheerla
 * @Date : October, 2018
 *
 */

public interface RuleBuilderService {

	RuleBuilderTO saveRule(RuleBuilderTO ruleTo);

	public ScheduleTO loadScheduleData(int userId);

	public List<RuleTO> getAllRules(int type, int campaignId, int userId);

	public String scheduleRule(RuleTO ruleTO);

	public String getRuleJson(RuleTO ruleTO);

	public String deleteRule(RuleTO ruleTO);

	RuleBuilderTO saveAttachedRule(RuleBuilderTO ruleTo);

	public ScheduleTO loadScheduleDataOfRule(int ruleId);

	public Boolean updateRuleStatus(int ruleId, Integer status);

	public RuleBuilderTO fetchRuleStatus(int ruleId);

	public List<RuleTO> getRuleAuditInfo(RuleTO ruleTO);

	public String approveOrRejectRule(RuleTO ruleTO);

	public List<RuleTO> getRulesForApproval(RuleTO ruleTO, String userId);

	List<ActionFileTO> getFileDetails(String actionid, String ruleId);

	List<TokenMaster> getSessionName();

	String remoteCopy(RemoteCopyUserTO copyTO);

	List<RemoteCopyUserTO> remotePaste(String token);

	RemoteCopyUserTO getPasteName(String token);
	
	PaginationDTO<RuleTO> getRulesWithPagination (PaginationDTO<RuleTO> to);



}
