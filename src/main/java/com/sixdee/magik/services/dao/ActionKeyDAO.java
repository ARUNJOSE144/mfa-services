package com.sixdee.magik.services.dao;

import java.util.List;

import com.sixdee.magik.services.model.ActionControlGroupTO;
import com.sixdee.magik.services.model.ActionKeyBundleTariffRateTO;
import com.sixdee.magik.services.model.ActionKeyBundleTypeTO;
import com.sixdee.magik.services.model.ActionKeyCampaignChannelTO;
import com.sixdee.magik.services.model.ActionKeyCampaignTypeTO;
import com.sixdee.magik.services.model.ActionKeyCreditTypeTO;
import com.sixdee.magik.services.model.IsPromotionDetailsTO;
import com.sixdee.magik.services.model.UserSessionTO;

/**
 * @author Nakhil Kurian
 * @category January 2021
 * 
 */
public interface ActionKeyDAO {

	public List<ActionControlGroupTO> getActionKeyDetails(Integer parentId, UserSessionTO user);

	public void deleteActionKey(ActionControlGroupTO actionControlGroupTO);

	public void updateActionKeyDetails(ActionControlGroupTO actionControlGroupTO);

	public List<ActionControlGroupTO> getActionConfigList(Integer actionId, UserSessionTO user);

	public void saveActionConfigDetails(ActionControlGroupTO actionControlGroupTO) throws Exception;

	public List<ActionControlGroupTO> getAllActionKeys(int userId);

	public ActionControlGroupTO loadPromotionBundleDetails();

	public void saveIspromotionDetails(IsPromotionDetailsTO isPromotionTO);

	public List<IsPromotionDetailsTO> getIsPromotionDetails(Integer actionId, UserSessionTO user);

	public List<ActionKeyCampaignChannelTO> getCampaignChannel();

	public List<ActionKeyCampaignTypeTO> getActionKeyCampaignType();

	public List<ActionKeyBundleTypeTO> getUpsellBundle();

	public List<String> getUpsellBundleProducts(String bundletype);

	public List<ActionKeyBundleTariffRateTO> getUpsellBundleTargetList(String productname);

	public List<ActionKeyCreditTypeTO> getCreditType();

}
