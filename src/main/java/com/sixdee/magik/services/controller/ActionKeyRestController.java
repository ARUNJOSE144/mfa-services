package com.sixdee.magik.services.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sixdee.magik.services.model.ActionControlGroupTO;
import com.sixdee.magik.services.model.ActionKeyBundleTariffRateTO;
import com.sixdee.magik.services.model.ActionKeyBundleTypeTO;
import com.sixdee.magik.services.model.ActionKeyCampaignChannelTO;
import com.sixdee.magik.services.model.ActionKeyCampaignTypeTO;
import com.sixdee.magik.services.model.ActionKeyCreditTypeTO;
import com.sixdee.magik.services.model.DNDTO;
import com.sixdee.magik.services.model.GenericTO;
import com.sixdee.magik.services.model.IsPromotionDetailsTO;
import com.sixdee.magik.services.model.OverAllCampaignPushTO;
import com.sixdee.magik.services.model.UserSessionTO;
import com.sixdee.magik.services.service.ActionKeyService;
import com.sixdee.magik.services.util.ExceptionUtil;
import com.sixdee.magik.services.util.RestInfo;
import com.sixdee.magik.services.util.RestListInfo;
import com.sixdee.magik.services.util.MagikServicePath;

/**
 * @author Nakhil Kurian
 * @category January 2021
 * 
 */
@RestController
public class ActionKeyRestController {

	static final Logger logger = Logger.getLogger(ActionKeyRestController.class);

	@Autowired
	ActionKeyService actionKeyService;

	@GetMapping("GetActionKeysDetails")
	public @ResponseBody RestListInfo<ActionControlGroupTO> getActionKeyDetails(HttpServletRequest httpServletRequest) {
		logger.info(
				"================== ActionKeyRestController  : getActionKeyDetails  API Start =====================");
		Integer parentId = Integer.parseInt(httpServletRequest.getParameter("parentId"));
		logger.info("Param Id : " + parentId);

		UserSessionTO user = new UserSessionTO();
		int userId = Integer.parseInt(httpServletRequest.getParameter("userId"));
		user.setUserId(userId);
		logger.info("================== ActionKeyRestController  : getActionKeyDetails  API End =====================");
		RestListInfo<ActionControlGroupTO> listInfo = new RestListInfo<ActionControlGroupTO>();
		try {
			listInfo.setData(actionKeyService.getActionKeyDetails(parentId, user));
		} catch (Exception e) {
			ExceptionUtil.handle(e, listInfo);
			logger.error("Class : ActionsRestController  |  Method : getActionGroupList \n" + e);
			e.printStackTrace();
		}

		return listInfo;
	}

	@PostMapping("DeleteActionKey")
	public @ResponseBody RestInfo<String> deleteActionKey(HttpServletRequest httpServletRequest,
			@RequestBody ActionControlGroupTO actionControlGroupTO) {
		logger.info("================== ActionKeyRestController  : deleteActionKey  API Start =====================");
		logger.info("================== ActionKeyRestController  : deleteActionKey  API End =====================");

		System.out.println("actionControlGroupTO   " + actionControlGroupTO.toString());
		RestInfo<String> listInfo = new RestInfo<String>();
		try {
			actionKeyService.deleteActionKey(actionControlGroupTO);
			// listInfo.setMessage(CacheDAOImpl.messages.get(9));
		} catch (Exception e) {
			ExceptionUtil.handle(e, listInfo);
			logger.error("Class : ActionKeyRestController  |  Method : deleteActionKey \n" + e);
			e.printStackTrace();
		}

		return listInfo;
	}

	@PostMapping("UpdateActionKeyDetails")
	public @ResponseBody RestInfo<String> updateActionKeyDetails(HttpServletRequest httpServletRequest,
			@RequestBody ActionControlGroupTO actionControlGroupTO) {
		logger.info("================== ActionKeyRestController  : deleteActionKey  API Start =====================");
		System.out.println("ID: " + actionControlGroupTO.getId());
		logger.info("================== ActionKeyRestController  : deleteActionKey  API End =====================");
		RestInfo<String> listInfo = new RestInfo<String>();
		try {
			actionKeyService.updateActionKeyDetails(actionControlGroupTO);
			// listInfo.setMessage(CacheDAOImpl.messages.get(10));
		} catch (Exception e) {
			ExceptionUtil.handle(e, listInfo);
			logger.error("Class : ActionKeyRestController  |  Method : deleteActionKey \n" + e);
			e.printStackTrace();
		}

		return listInfo;
	}

	@GetMapping("GetActionConfgList")
	public @ResponseBody RestListInfo<ActionControlGroupTO> getActionconfigList(HttpServletRequest httpServletRequest) {
		logger.info(
				"================== ActionKeyRestController  : getActionconfigList  API Start =====================");
		Integer actionId = Integer.parseInt(httpServletRequest.getParameter("actionId"));
		logger.info("Action Id : " + actionId);
		UserSessionTO user = new UserSessionTO();
		int userId = Integer.parseInt(httpServletRequest.getParameter("userId"));
		user.setUserId(userId);
		logger.info("================== ActionKeyRestController  : getActionconfigList  API End =====================");
		RestListInfo<ActionControlGroupTO> listInfo = new RestListInfo<ActionControlGroupTO>();
		try {
			listInfo.setData(actionKeyService.getActionConfigList(actionId, user));
		} catch (Exception e) {
			ExceptionUtil.handle(e, listInfo);
			logger.error("Class : ActionsRestController  |  Method : getActionGroupList \n" + e);
			e.printStackTrace();
		}

		return listInfo;
	}

	@PostMapping("CreateActionConfigDetails")
	public @ResponseBody RestInfo<String> saveActionConfigdetails(HttpServletRequest httpServletRequest,
			@RequestBody ActionControlGroupTO actionControlGroupTO) {

		logger.info("================== ActionsRestController saveActionConfigdetails API Start =====================");
		System.out.println("Parent_ID: " + actionControlGroupTO.getParentId());
		// int userId = Integer.parseInt(httpServletRequest.getParameter("userId"));
		// actionControlGroupTO.setUserId(userId);
		logger.info("================== ActionsRestController saveActionConfigdetails API End =====================");
		RestInfo<String> info = new RestInfo<String>();
		try {
			actionKeyService.saveActionConfigDetails(actionControlGroupTO);
		} catch (Exception e) {
			info.setOperationCode(1);
			logger.error("Class : ActionsRestController  |  Method : saveHeaderParam \n" + e);
			e.printStackTrace();
		}
		return info;
	}

	@GetMapping("getAllActionKeys")
	public @ResponseBody RestListInfo<ActionControlGroupTO> getAllActionKeys(HttpServletRequest httpServletRequest) {
		logger.info("================== ActionKeyRestController  : getAllActionKeys  API Start =====================");
		/*
		 * Integer parentId =
		 * Integer.parseInt(httpServletRequest.getParameter("parentId"));
		 * logger.info("Param Id : "+parentId);
		 * 
		 * UserSessionTO user = new UserSessionTO(); int userId =
		 * Integer.parseInt(httpServletRequest.getParameter("userId"));
		 * user.setUserId(userId);
		 */
		int userId = 0;
		logger.info("================== ActionKeyRestController  : getActionKeyDetails  API End =====================");
		RestListInfo<ActionControlGroupTO> listInfo = new RestListInfo<ActionControlGroupTO>();
		try {
			listInfo.setData(actionKeyService.getAllActionKeys(userId));
		} catch (Exception e) {
			ExceptionUtil.handle(e, listInfo);
			logger.error("Class : ActionsRestController  |  Method : getAllActionKeys \n" + e);
			e.printStackTrace();
		}

		return listInfo;
	}

	@GetMapping(MagikServicePath.LOAD_BUNDLE_TARIFF_DETAILS)
	public @ResponseBody RestInfo<ActionControlGroupTO> loadBundleTariffDetails(HttpServletRequest httpServletRequest) {
		logger.info(
				"================== ActionKeyRestController  : loadBundleTariffDetails  API Start =====================");
		RestInfo<ActionControlGroupTO> info = new RestInfo<ActionControlGroupTO>();
		try {
			info.setData(actionKeyService.loadPromotionBundleDetails());
			info.setOperationCode(0);
		} catch (Exception e) {
			info.setOperationCode(1);
			ExceptionUtil.handle(e, info);

			logger.error("Class : ActionsRestController  |  Method : loadBundleTariffDetails \n" + e);
			e.printStackTrace();
		}

		return info;
	}

	@PostMapping(MagikServicePath.IS_PROMOTION_DETAILS)
	public @ResponseBody RestInfo<String> saveIspromotionDetails(HttpServletRequest httpServletRequest,
			@RequestBody IsPromotionDetailsTO isPromotionTO) {

		logger.info("================== ActionsRestController saveActionConfigdetails API Start =====================");
		int userId = Integer.parseInt(httpServletRequest.getParameter("userId"));
		isPromotionTO.setId(userId);
		logger.info("================== ActionsRestController saveActionConfigdetails API End =====================");
		RestInfo<String> info = new RestInfo<String>();
		try {
			actionKeyService.saveIspromotionDetails(isPromotionTO);
		} catch (Exception e) {
			info.setOperationCode(1);
			logger.error("Class : ActionsRestController  |  Method : saveHeaderParam \n" + e);
			e.printStackTrace();
		}
		return info;
	}

	@GetMapping(MagikServicePath.GET_IS_PROMOTION_DETAILS)
	public @ResponseBody RestListInfo<IsPromotionDetailsTO> getIsPromotionDetails(
			HttpServletRequest httpServletRequest) {
		logger.info(
				"================== ActionKeyRestController  : getActionconfigList  API Start =====================");
		Integer actionId = Integer.parseInt(httpServletRequest.getParameter("actionId"));
		logger.info("Action Id : " + actionId);
		UserSessionTO user = new UserSessionTO();
		int userId = Integer.parseInt(httpServletRequest.getParameter("userId"));
		user.setUserId(userId);
		logger.info("================== ActionKeyRestController  : getActionconfigList  API End =====================");
		RestListInfo<IsPromotionDetailsTO> listInfo = new RestListInfo<IsPromotionDetailsTO>();
		try {
			listInfo.setData(actionKeyService.getIsPromotionDetails(actionId, user));
		} catch (Exception e) {
			ExceptionUtil.handle(e, listInfo);
			logger.error("Class : ActionsRestController  |  Method : getActionGroupList \n" + e);
			e.printStackTrace();
		}

		return listInfo;
	}

	@GetMapping(MagikServicePath.GET_CAMPAIGN_CHANNEL)
	public @ResponseBody RestListInfo<ActionKeyCampaignChannelTO> getCampaignChannel(
			HttpServletRequest httpServletRequest) {
		RestListInfo<ActionKeyCampaignChannelTO> listInfo = new RestListInfo<ActionKeyCampaignChannelTO>();
		try {
			listInfo.setData(actionKeyService.getCampaignChannel());
		} catch (Exception e) {
			ExceptionUtil.handle(e, listInfo);
			logger.error("Class : ActionsRestController  |  Method : getCampaignChannel \n" + e);
			e.printStackTrace();
		}
		return listInfo;
	}

	@GetMapping(MagikServicePath.GET_ACTION_KEY_CAMPAIGN_TYPE)
	public @ResponseBody RestListInfo<ActionKeyCampaignTypeTO> getActionKeyCampaignTyp(
			HttpServletRequest httpServletRequest) {
		RestListInfo<ActionKeyCampaignTypeTO> listInfo = new RestListInfo<ActionKeyCampaignTypeTO>();
		try {
			listInfo.setData(actionKeyService.getActionKeyCampaignType());
		} catch (Exception e) {
			ExceptionUtil.handle(e, listInfo);
			logger.error("Class : ActionsRestController  |  Method : getActionKeyCampaignType \n" + e);
			e.printStackTrace();
		}
		return listInfo;
	}

	@GetMapping(MagikServicePath.GET_UPSELL_BUNDLE)
	public @ResponseBody RestListInfo<ActionKeyBundleTypeTO> getUpsellBundle(HttpServletRequest httpServletRequest) {
		RestListInfo<ActionKeyBundleTypeTO> listInfo = new RestListInfo<ActionKeyBundleTypeTO>();
		try {
			listInfo.setData(actionKeyService.getUpsellBundle());
		} catch (Exception e) {
			ExceptionUtil.handle(e, listInfo);
			logger.error("Class : ActionsRestController  |  Method : getActionKeyCampaignType \n" + e);
			e.printStackTrace();
		}
		return listInfo;
	}

	@GetMapping(MagikServicePath.GET_UPSELL_BUNDLE_PRODUCTS)
	public @ResponseBody RestListInfo<String> getUpsellBundleProducts(HttpServletRequest httpServletRequest) {
		String bundletype = httpServletRequest.getParameter("bundletype");
		logger.info("Bundle Type : " + bundletype);
		System.out.println("Bundle Type : " + bundletype);
		logger.info("================== ActionKeyRestController  : getBundleTariffRate  API End =====================");
		RestListInfo<String> listInfo = new RestListInfo<String>();
		try {
			listInfo.setData(actionKeyService.getUpsellBundleProducts(bundletype));
		} catch (Exception e) {
			ExceptionUtil.handle(e, listInfo);
			logger.error("Class : ActionsRestController  |  Method : getBundleTariffRate \n" + e);
			e.printStackTrace();
		}

		return listInfo;
	}

	@GetMapping(MagikServicePath.GET_UPSELL_BUNDLE_TARGET_LIST)
	public @ResponseBody RestListInfo<ActionKeyBundleTariffRateTO> getUpsellBundleTargetList(
			HttpServletRequest httpServletRequest) {
		System.out.println("target id ");
		logger.info("================== ActionKeyRestController  : getTargetId  API Start =====================");
		String productname = httpServletRequest.getParameter("productName");
		logger.info("name : " + productname);
		logger.info(
				"================== ActionKeyRestController  : getUpsellBundleTargetList API End =====================");
		RestListInfo<ActionKeyBundleTariffRateTO> listInfo = new RestListInfo<ActionKeyBundleTariffRateTO>();
		try {
			listInfo.setData(actionKeyService.getUpsellBundleTargetList(productname));
		} catch (Exception e) {
			ExceptionUtil.handle(e, listInfo);
			logger.error("Class : ActionsRestController  |  Method : getUpsellBundleTargetList \n" + e);
			e.printStackTrace();
		}

		return listInfo;
	}

	@GetMapping(MagikServicePath.GET_CREDIT_TYPE)
	public @ResponseBody RestListInfo<ActionKeyCreditTypeTO> getCreditType(HttpServletRequest ActionKeyCreditTypeTO) {

		RestListInfo<ActionKeyCreditTypeTO> listInfo = new RestListInfo<ActionKeyCreditTypeTO>();
		try {
			listInfo.setData(actionKeyService.getCreditType());
		} catch (Exception e) {
			ExceptionUtil.handle(e, listInfo);
			e.printStackTrace();
		}

		return listInfo;
	}

}
