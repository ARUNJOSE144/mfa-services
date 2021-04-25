package com.sixdee.magik.services.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.sixdee.magik.services.dao.ConfigureOfferLoyaltyDAO;
import com.sixdee.magik.services.model.AccountTypeLMSTO;
import com.sixdee.magik.services.model.ChannelDetailsLMSTO;
import com.sixdee.magik.services.model.ConfigureInterfaceMasterLMSTO;
import com.sixdee.magik.services.model.ConfigureOfferAccountMappingLMSTO;
import com.sixdee.magik.services.model.ConfigureOfferAccountSubMappingLMSTO;
import com.sixdee.magik.services.model.ConfigureOfferAttributesLMSTO;
import com.sixdee.magik.services.model.ConfigureOfferChannelLMSTO;
import com.sixdee.magik.services.model.ConfigureOfferLMSTransientTO;
import com.sixdee.magik.services.model.ConfigureOfferLocMappingLMSTO;
import com.sixdee.magik.services.model.ConfigureOfferMasterLMSTO;
import com.sixdee.magik.services.model.ConfigureOfferMerchantTypeTO;
import com.sixdee.magik.services.model.ConfigureOfferNomenclatureLMSTO;
import com.sixdee.magik.services.model.ConfigureOfferTierMappingLMSTO;
import com.sixdee.magik.services.model.ConfigureOfferTypeLMSTO;
import com.sixdee.magik.services.model.ConfigureOfferUcategoryMappingLMSTO;
import com.sixdee.magik.services.model.LanguageMasterLMSTO;
import com.sixdee.magik.services.model.SegmentDtlsLMSTO;
import com.sixdee.magik.services.model.TierConfigurationLMSTO;
import com.sixdee.magik.services.model.UserCategoryLMSTO;

/**
 * @author ABHIRAM MACHIRAJU
 * @Date : March, 2021
 * @Table to Verify : 
 *         SELECT * FROM LMS_CNFG_OFFER_MASTER 
 *         SELECT * FROM LMS_CNFG_OFFER_NOMENCLATURE
 *         SELECT * FROM LMS_CSEG_OFFER_CHANNEL
 *         SELECT * FROM LMS_CSEG_OFFER_TIER_MAPPING 
 *         SELECT * FROM LMS_CSEG_OFFER_ACCOUNT_MAPPING
 *         SELECT * FROM LMS_CNFG_OFFER_SUB_MAPPING 
 *         SELECT * FROM LMS_CSEG_OFFER_ATTRIBUTES
 *         SELECT * FROM LMS_CNFG_OFFER_LOC_MAPPING
           SELECT * FROM LMS_CSEG_OFFER_UCATEG_MAPPING
 * 
 *        //DropDowns....... 
 *        LMS_CNFG_CHANNEL_DETAILS
 *        LMS_CNFG_TIER_INFO
 *        LMS_CNFG_SUBSCRIPTION_TYPE 
 *        LMS_CNFG_ACCOUNT_TYPE
 *        LMS_CNFG_USER_CATEGORY_MASTER
 *        LMS_CNFG_MERCHANT_NOMENCLATURE
 *        LMS_CNFG_INTERFACE_MASTER
 * 
 *        //Segment Details:CheckBoxes....
 *         LMS_CNFG_CHANNEL_DETAILS
 *         LMS_CNFG_TIER_INFO 
 *         LMS_CNFG_ACCOUNT_TYPE
 *         LMS_CNFG_USER_CATEGORY_MASTER
 *         
 *         
 *         //Languages....
 *         LMS_CNFG_LANGUAGE_MASTER
 * 
 */
@SuppressWarnings("unchecked")
@Repository
public class ConfigureOfferLoyaltyDAOImpl implements ConfigureOfferLoyaltyDAO {

	@Autowired
	@Qualifier("lmsConfigSessionFactory")
	private SessionFactory sessionFactory;

	protected Session currentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public SegmentDtlsLMSTO segmentDtls() {
		SegmentDtlsLMSTO segmentDtls = new SegmentDtlsLMSTO();

		List<ChannelDetailsLMSTO> channelDtlsList = null;
		List<AccountTypeLMSTO> subscriberDtlsList = null;
		List<TierConfigurationLMSTO> loyalityDtlsList = null;
		List<UserCategoryLMSTO> usrCategoryDtlsList = null;
		List<ConfigureOfferTypeLMSTO> offerTypeList = null;
		List<ConfigureOfferMerchantTypeTO> partnerTypeList = null;
		List<ConfigureInterfaceMasterLMSTO> interfaceTypeList = null;

		try {
			channelDtlsList = currentSession().createCriteria(ChannelDetailsLMSTO.class).list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			subscriberDtlsList = currentSession().createCriteria(AccountTypeLMSTO.class).list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			loyalityDtlsList = currentSession().createCriteria(TierConfigurationLMSTO.class).list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			usrCategoryDtlsList = currentSession().createCriteria(UserCategoryLMSTO.class).list();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// DropDownValues
		try {
			offerTypeList = currentSession().createCriteria(ConfigureOfferTypeLMSTO.class).list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			partnerTypeList = currentSession().createCriteria(ConfigureOfferMerchantTypeTO.class).list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			interfaceTypeList = currentSession().createCriteria(ConfigureInterfaceMasterLMSTO.class).list();
		} catch (Exception e) {
			e.printStackTrace();
		}

		segmentDtls.setChannelDtlsList(channelDtlsList);
		segmentDtls.setLoyalityDtlsList(loyalityDtlsList);
		segmentDtls.setSubscriberDtlsList(subscriberDtlsList);
		segmentDtls.setUsrCategoryDtlsList(usrCategoryDtlsList);

		segmentDtls.setOfferTypeList(offerTypeList);
		segmentDtls.setPartnerTypeList(partnerTypeList);
		segmentDtls.setInterfaceTypeList(interfaceTypeList);

		return segmentDtls;
	}

	/*
	 * @Override public <OBJECT> String saveOrUpdateConfigureObjects(OBJECT obj) {
	 * 
	 * // System.out.println(obj.getClass().getSimpleName());
	 * 
	 * return null;
	 * 
	 * }
	 */

	@Override
	public String saveOrUpdateConfigureObjects(ConfigureOfferLMSTransientTO obj) {
		String statusCode = "SC00001";
		try {
			ConfigureOfferMasterLMSTO parentObject = new ConfigureOfferMasterLMSTO();

			ConfigureOfferTypeLMSTO childObj_one = new ConfigureOfferTypeLMSTO();
			childObj_one.setOfferTypeId(obj.getOfferTypeId());
			parentObject.setConfigureOfferTypeLMSTO(childObj_one);

			ConfigureInterfaceMasterLMSTO childObj_two = new ConfigureInterfaceMasterLMSTO();
			childObj_two.setInterfaceID(obj.getInterfaceTypeId());
			parentObject.setConfigureInterfaceMasterLMSTO(childObj_two);

			ConfigureOfferMerchantTypeTO childObj_three = new ConfigureOfferMerchantTypeTO();
			LanguageMasterLMSTO langObj = new LanguageMasterLMSTO();
			langObj.setLanguageId(1);
			childObj_three.setLangId(langObj);
			childObj_three.setMerchantid(obj.getPartner());
			parentObject.setConfigureOfferMerchantTypeTO(childObj_three);

			parentObject.setPriority(obj.getPriority());
			parentObject.setTopoffer(obj.getTopOffer());
			parentObject.setStatus(obj.getStatus());
			parentObject.setPoints(obj.getOfferPts());
			parentObject.setShortcode(obj.getShortCode());
			parentObject.setStartDate(obj.getOfferStartDate());
			parentObject.setEndDate(obj.getOfferEndDate());
			parentObject.setExpiryDay(obj.getOfferExpDay());
			parentObject.setExpiryHour(obj.getOfferExpHrs());
			parentObject.setExpiryMinutes(obj.getOfferExpMin());
			parentObject.setCost(obj.getCost());
			parentObject.setProviderName(obj.getVendorName());
			parentObject.setFetch_type("INSERTED");
			parentObject.setIs_physical_gift(obj.getPhysicalGift());

			currentSession().save(parentObject);

			ConfigureOfferNomenclatureLMSTO parentObject_two = new ConfigureOfferNomenclatureLMSTO();
			parentObject_two.setOfferId(parentObject);
			parentObject_two.setLangId(langObj);
			parentObject_two.setOfferName(obj.getOfferName());
			parentObject_two.setDescription(obj.getOfferDesc());
			parentObject_two.setTermsCondtions(obj.getOfferTc());
			parentObject_two.setIconName_base64(obj.getIconImage());
			parentObject_two.setImageFile_base64(obj.getRectangluarImg());
			parentObject_two.setIconName_base64(obj.getIconImage());
			currentSession().save(parentObject_two);

			if (obj.getChannelList().size() > 0) {
				for (ChannelDetailsLMSTO channelObj : obj.getChannelList()) {
					ConfigureOfferChannelLMSTO parentObject_three = new ConfigureOfferChannelLMSTO();
					parentObject_three.setConfigureOfferMasterLMSTO(parentObject);
					parentObject_three.setChannelDetailsLMSTO(channelObj);
					currentSession().save(parentObject_three);

				}
			}

			if (obj.getLoyaltyList().size() > 0) {
				for (TierConfigurationLMSTO tierObj : obj.getLoyaltyList()) {
					ConfigureOfferTierMappingLMSTO parentObject_four = new ConfigureOfferTierMappingLMSTO();
					parentObject_four.setConfigureOfferMasterLMSTO(parentObject);
					parentObject_four.setTierConfigurationLMSTO(tierObj);
					currentSession().save(parentObject_four);

				}
			}

			if (obj.getSubscrptionList().size() > 0) {
				for (AccountTypeLMSTO subscObj : obj.getSubscrptionList()) {
					ConfigureOfferAccountMappingLMSTO parentObject_five = new ConfigureOfferAccountMappingLMSTO();
					parentObject_five.setConfigureOfferMasterLMSTO(parentObject);
					parentObject_five.setAccounttypeid(subscObj);
					currentSession().save(parentObject_five);
					ConfigureOfferAccountSubMappingLMSTO parentObj_subMap = new ConfigureOfferAccountSubMappingLMSTO();
					parentObj_subMap.setConfigureOfferMasterLMSTO(parentObject);
					parentObj_subMap.setAccounttypeid(subscObj);
					currentSession().save(parentObj_subMap);

				}
			}

			if (obj.getUserCatList().size() > 0) {
				for (UserCategoryLMSTO userCatobj : obj.getUserCatList()) {
					ConfigureOfferUcategoryMappingLMSTO parentObject_six = new ConfigureOfferUcategoryMappingLMSTO();
					parentObject_six.setConfigureOfferMasterLMSTO(parentObject);
					parentObject_six.setUserCategoryLMSTO(userCatobj);
					currentSession().save(parentObject_six);

				}
			}

			ConfigureOfferAttributesLMSTO parentObject_seven = new ConfigureOfferAttributesLMSTO();
			parentObject_seven.setConfigureOfferMasterLMSTO(parentObject);
			currentSession().save(parentObject_seven);

			ConfigureOfferLocMappingLMSTO parentObject_eigth = new ConfigureOfferLocMappingLMSTO();
			parentObject_eigth.setConfigureOfferMasterLMSTO(parentObject);
			currentSession().save(parentObject_eigth);

			statusCode = "SC0000";
		} catch (Exception e) {
			e.printStackTrace();
		}

		return statusCode;
	}

	@Override
	public List<ConfigureOfferNomenclatureLMSTO> getAllOffers() {
		System.out.println("getAllOffers  ::    >>>>>>>>>>>>>>>>>>>>>>");
		List<ConfigureOfferNomenclatureLMSTO> list = currentSession()
				.createCriteria(ConfigureOfferNomenclatureLMSTO.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		System.out.println("getAllOffers  33333333333333333333333333  ::    >>>>>>>>>>>>>>>>>>>>>>");
		return list;
	}

}
