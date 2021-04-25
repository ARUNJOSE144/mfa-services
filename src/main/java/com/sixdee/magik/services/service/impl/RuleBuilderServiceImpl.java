
package com.sixdee.magik.services.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sixdee.magik.services.dao.RuleBuilderDao;
import com.sixdee.magik.services.model.ActionFileTO;
import com.sixdee.magik.services.model.PaginationDTO;
import com.sixdee.magik.services.model.RemoteCopyUserTO;
import com.sixdee.magik.services.model.RuleBuilderTO;
import com.sixdee.magik.services.model.RuleTO;
import com.sixdee.magik.services.model.ScheduleTO;
import com.sixdee.magik.services.model.TokenMaster;
import com.sixdee.magik.services.service.RuleBuilderService;

/**
 * @author ramesh.cheerla
 * @Date : October, 2018
 *
 */

@Service
@Transactional
public class RuleBuilderServiceImpl implements RuleBuilderService {

	@Autowired
	RuleBuilderDao ruleDao;

	@Override
	public RuleBuilderTO saveRule(RuleBuilderTO ruleTo) {
		return ruleDao.saveRule(ruleTo);
	}

	@Override
	public ScheduleTO loadScheduleData(int userId) {
		// TODO Auto-generated method stub
		return ruleDao.loadScheduleData(userId);
	}

	@Override
	public List<RuleTO> getAllRules(int type, int campaignId, int userId) {
		// TODO Auto-generated method stub
		return ruleDao.getAllRules(type, campaignId, userId);
	}

	@Override
	public String scheduleRule(RuleTO ruleTO) {
		return ruleDao.scheduleRule(ruleTO);

	}

	@Override
	public String getRuleJson(RuleTO ruleTO) {
		return ruleDao.getRuleJson(ruleTO);

	}

	@Override
	public String deleteRule(RuleTO ruleTO) {
		// TODO Auto-generated method stub
		return ruleDao.deleteRule(ruleTO);
	}

	@Override
	public RuleBuilderTO saveAttachedRule(RuleBuilderTO ruleTo) {
		System.out.println("in  service");
		return ruleDao.saveAttachedRule(ruleTo);

	}

	@Override
	public ScheduleTO loadScheduleDataOfRule(int ruleId) {
		// TODO Auto-generated method stub
		return ruleDao.loadScheduleDataOfRule(ruleId);
	}

	@Override
	public Boolean updateRuleStatus(int ruleId, Integer status) {
		// TODO Auto-generated method stub
		return ruleDao.updateRuleStatus(ruleId, status);

	}

	@Override
	public List<RuleTO> getRuleAuditInfo(RuleTO ruleTO) {
		// TODO Auto-generated method stub
		return ruleDao.getRuleAuditInfo(ruleTO);
	}

	@Override
	public String approveOrRejectRule(RuleTO ruleTO) {
		// TODO Auto-generated method stub
		return ruleDao.approveOrRejectRule(ruleTO);
	}

	@Override
	public List<RuleTO> getRulesForApproval(RuleTO ruleTO,String userId) {
		// TODO Auto-generated method stub
		return ruleDao.getRulesForApproval(ruleTO,userId);
	}

	@Override
	public RuleBuilderTO fetchRuleStatus(int ruleId) {
		// TODO Auto-generated method stub

		System.out.println("in service111" + ruleId);
		return ruleDao.fetchRuleStatus(ruleId);
	}

	@Override
	public List<ActionFileTO> getFileDetails(String actionid, String ruleId) {
		// TODO Auto-generated method stub
		return ruleDao.getFileDetails(actionid, ruleId);
	}

	@Override
	public List<TokenMaster> getSessionName() {
		// TODO Auto-generated method stub
		return ruleDao.getSessionName();
	}

	@Override
	public String remoteCopy(RemoteCopyUserTO copyTO) {
		// TODO Auto-generated method stub
		return ruleDao.remoteCopy(copyTO);
	}

	@Override
	public List<RemoteCopyUserTO> remotePaste(String token) {
		// TODO Auto-generated method stub
		return ruleDao.remotePaste(token);
	}

	@Override
	public RemoteCopyUserTO getPasteName(String token) {
		return ruleDao.getPasteName( token);
	}

	@Override
	public PaginationDTO<RuleTO> getRulesWithPagination(PaginationDTO<RuleTO> to) {
		// TODO Auto-generated method stub
		return ruleDao.getRulesWithPagination( to);
	}

	
	

}
