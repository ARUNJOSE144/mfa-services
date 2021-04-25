package com.sixdee.magik.services.model;

import java.util.List;
import java.util.Map;


/**
 * @author ABHIRAM MACHIRAJU
 * @Date : March, 2021
 */

public class SegmentDtlsLMSTO {
	
	
	private List<ChannelDetailsLMSTO> channelDtlsList;
	private List<TierConfigurationLMSTO> loyalityDtlsList;
	private List<AccountTypeLMSTO> subscriberDtlsList;
	private List<UserCategoryLMSTO> usrCategoryDtlsList;
	
	
	//DropDownValues
	private List<ConfigureOfferTypeLMSTO> offerTypeList;
	private List<ConfigureOfferMerchantTypeTO> partnerTypeList;
	private List<ConfigureInterfaceMasterLMSTO> interfaceTypeList;
	 
	
	
	
	public List<ChannelDetailsLMSTO> getChannelDtlsList() {
		return channelDtlsList;
	}
	public void setChannelDtlsList(List<ChannelDetailsLMSTO> channelDtlsList) {
		this.channelDtlsList = channelDtlsList;
	}
	public List<TierConfigurationLMSTO> getLoyalityDtlsList() {
		return loyalityDtlsList;
	}
	public void setLoyalityDtlsList(List<TierConfigurationLMSTO> loyalityDtlsList) {
		this.loyalityDtlsList = loyalityDtlsList;
	}
	public List<AccountTypeLMSTO> getSubscriberDtlsList() {
		return subscriberDtlsList;
	}
	public void setSubscriberDtlsList(List<AccountTypeLMSTO> subscriberDtlsList) {
		this.subscriberDtlsList = subscriberDtlsList;
	}
	public List<UserCategoryLMSTO> getUsrCategoryDtlsList() {
		return usrCategoryDtlsList;
	}
	public void setUsrCategoryDtlsList(List<UserCategoryLMSTO> usrCategoryDtlsList) {
		this.usrCategoryDtlsList = usrCategoryDtlsList;
	}
	public List<ConfigureOfferTypeLMSTO> getOfferTypeList() {
		return offerTypeList;
	}
	public void setOfferTypeList(List<ConfigureOfferTypeLMSTO> offerTypeList) {
		this.offerTypeList = offerTypeList;
	}
	public List<ConfigureOfferMerchantTypeTO> getPartnerTypeList() {
		return partnerTypeList;
	}
	public void setPartnerTypeList(List<ConfigureOfferMerchantTypeTO> partnerTypeList) {
		this.partnerTypeList = partnerTypeList;
	}
	public List<ConfigureInterfaceMasterLMSTO> getInterfaceTypeList() {
		return interfaceTypeList;
	}
	public void setInterfaceTypeList(List<ConfigureInterfaceMasterLMSTO> interfaceTypeList) {
		this.interfaceTypeList = interfaceTypeList;
	}
	
}
