package com.sixdee.magik.services.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sixdee.magik.services.dao.ActionKeyDAO;
import com.sixdee.magik.services.model.ActionControlGroupTO;
import com.sixdee.magik.services.model.ActionKeyBundleTariffRateTO;
import com.sixdee.magik.services.model.ActionKeyBundleTypeTO;
import com.sixdee.magik.services.model.ActionKeyCampaignChannelTO;
import com.sixdee.magik.services.model.ActionKeyCampaignTypeTO;
import com.sixdee.magik.services.model.ActionKeyCreditTypeTO;
import com.sixdee.magik.services.model.IsPromotionDetailsTO;
import com.sixdee.magik.services.model.UserSessionTO;
import com.sixdee.magik.services.service.ActionKeyService;

/**
 * @author Nakhil Kurian
 * @category January 2021
 * 
 */
@Service
public class ActionKeyServiceImpl implements ActionKeyService {

	static final Logger logger = Logger.getLogger(ActionKeyServiceImpl.class);
	@Autowired
	private ActionKeyDAO actionKeyDAO;

	@Override
	public List<ActionControlGroupTO> getActionKeyDetails(Integer parentId, UserSessionTO user) {
		// TODO Auto-generated method stub
		return actionKeyDAO.getActionKeyDetails(parentId, user);
	}

	@Override
	public void deleteActionKey(ActionControlGroupTO actionControlGroupTO) {
		// TODO Auto-generated method stub
		actionKeyDAO.deleteActionKey(actionControlGroupTO);
	}

	@Override
	public void updateActionKeyDetails(ActionControlGroupTO actionControlGroupTO) {
		actionControlGroupTO.setFromDate(actionControlGroupTO.getFromDate().replace("%3A", ":"));
		actionControlGroupTO.setToDate(actionControlGroupTO.getToDate().replace("%3A", ":"));
		if (actionControlGroupTO.getRepeatStatus() == null)
			actionControlGroupTO.setRepeatStatus("false");
		if (actionControlGroupTO.getEveryDay() == null)
			actionControlGroupTO.setEveryDay("false");
		if (actionControlGroupTO.getIspromotion() == null)
			actionControlGroupTO.setIspromotion("false");
		actionKeyDAO.updateActionKeyDetails(actionControlGroupTO);
	}

	@Override
	public List<ActionControlGroupTO> getActionConfigList(Integer actionId, UserSessionTO user) {
		return actionKeyDAO.getActionConfigList(actionId, user);
	}

	@Override
	public void saveActionConfigDetails(ActionControlGroupTO actionControlGroupTO) throws Exception {
		actionKeyDAO.saveActionConfigDetails(actionControlGroupTO);
	}

	@Override
	public List<ActionControlGroupTO> getAllActionKeys(int userId) {
		// TODO Auto-generated method stub
		return actionKeyDAO.getAllActionKeys(userId);
	}

	@Override
	public ActionControlGroupTO loadPromotionBundleDetails() {
		// TODO Auto-generated method stub
		return actionKeyDAO.loadPromotionBundleDetails();
	}

	@Override
	public void saveIspromotionDetails(IsPromotionDetailsTO isPromotionTO) {
		// TODO Auto-generated method stub
		actionKeyDAO.saveIspromotionDetails(isPromotionTO);

	}

	@Override
	public List<IsPromotionDetailsTO> getIsPromotionDetails(Integer actionId, UserSessionTO user) {
		// TODO Auto-generated method stub
		return actionKeyDAO.getIsPromotionDetails(actionId, user);
	}

	@Override
	public List<ActionKeyCampaignChannelTO> getCampaignChannel() {
		// TODO Auto-generated method stub
		return actionKeyDAO.getCampaignChannel();
	}

	@Override
	public List<ActionKeyCampaignTypeTO> getActionKeyCampaignType() {
		// TODO Auto-generated method stub
		return actionKeyDAO.getActionKeyCampaignType();
	}

	@Override
	public List<ActionKeyBundleTypeTO> getUpsellBundle() {
		// TODO Auto-generated method stub
		return actionKeyDAO.getUpsellBundle();
	}

	@Override
	public List<String> getUpsellBundleProducts(String bundletype) {
		// TODO Auto-generated method stub
		return actionKeyDAO.getUpsellBundleProducts(bundletype);
	}

	@Override
	public List<ActionKeyBundleTariffRateTO> getUpsellBundleTargetList(String productname) {
		// TODO Auto-generated method stub
		return actionKeyDAO.getUpsellBundleTargetList(productname);
	}

	@Override
	public List<ActionKeyCreditTypeTO> getCreditType() {
		// TODO Auto-generated method stub
		return actionKeyDAO.getCreditType();
	}

}
