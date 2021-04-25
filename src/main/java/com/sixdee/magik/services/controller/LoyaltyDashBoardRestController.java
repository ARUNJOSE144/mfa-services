package com.sixdee.magik.services.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sixdee.magik.services.model.ChannelWiseRedeemptionTO;
import com.sixdee.magik.services.model.CustomerMigrationTO;
import com.sixdee.magik.services.model.EarnedRedeemptionTO;
import com.sixdee.magik.services.model.EarnedRedeemptionTO_1;
import com.sixdee.magik.services.model.LoyaltyPointsRedeemedTO;
import com.sixdee.magik.services.model.LoyaltyPointsTrendTO;
import com.sixdee.magik.services.model.MAKASIB_AcccountTO;
import com.sixdee.magik.services.model.MercantWiseRedeemptionTO;
import com.sixdee.magik.services.model.NewEnrollmentsTO;
import com.sixdee.magik.services.model.TotalRewardPointsTO;
import com.sixdee.magik.services.service.LoyaltyDashBoardService;
import com.sixdee.magik.services.util.ExceptionUtil;
import com.sixdee.magik.services.util.MagikServicePath;
import com.sixdee.magik.services.util.RestListInfo;

/**
 * @author Nakhil Kurian
 * @Date : August, 2020
 *
 */

@RestController
public class LoyaltyDashBoardRestController {

	static final Logger logger = Logger.getLogger(LoyaltyDashBoardRestController.class);

	@Autowired
	LoyaltyDashBoardService loyaltydashBoardService;

	@GetMapping(MagikServicePath.NEW_ENROLLMENTS)
	public @ResponseBody RestListInfo<NewEnrollmentsTO> getNewEnrollments(HttpServletRequest httpServletRequest) {

		logger.info(
				"================== LoyaltyDashBoardRestController getNewEnrollments API Start =====================");
		RestListInfo<NewEnrollmentsTO> listInfo = new RestListInfo<NewEnrollmentsTO>();
		try {
			listInfo.setData(loyaltydashBoardService.getNewEnrollments());
		} catch (Exception e) {
			ExceptionUtil.handle(e, listInfo);
			logger.error("Class : LoyaltyDashBoardRestController  |  Method : getNewEnrollments \n" + e);
			e.printStackTrace();
		}

		return listInfo;
	}

	@GetMapping(MagikServicePath.LOYALTY_POINTS_TREND)
	public @ResponseBody RestListInfo<LoyaltyPointsTrendTO> getLoyaltyPointTrend(
			HttpServletRequest httpServletRequest) {

		logger.info(
				"================== LoyaltyDashBoardRestController getLoyaltyPointTrend API Start =====================");
		RestListInfo<LoyaltyPointsTrendTO> listInfo = new RestListInfo<LoyaltyPointsTrendTO>();
		try {
			listInfo.setData(loyaltydashBoardService.getLoyaltyPointTrend());
		} catch (Exception e) {
			ExceptionUtil.handle(e, listInfo);
			logger.error("Class : LoyaltyDashBoardRestController  |  Method : getLoyaltyPointTrend \n" + e);
			e.printStackTrace();
		}

		return listInfo;
	}

	@GetMapping(MagikServicePath.MAKASIB_ACCOUNTS)
	public @ResponseBody RestListInfo<MAKASIB_AcccountTO> getMakasibAccounts(HttpServletRequest httpServletRequest) {

		logger.info(
				"================== LoyaltyDashBoardRestController getMakasibAccounts API Start =====================");
		RestListInfo<MAKASIB_AcccountTO> listInfo = new RestListInfo<MAKASIB_AcccountTO>();
		try {
			listInfo.setData(loyaltydashBoardService.getMakasibAccounts());
		} catch (Exception e) {
			ExceptionUtil.handle(e, listInfo);
			logger.error("Class : LoyaltyDashBoardRestController  |  Method : getMakasibAccounts \n" + e);
			e.printStackTrace();
		}

		return listInfo;
	}

	@GetMapping(MagikServicePath.LOYALTY_POINTS_REDEEMED)
	public @ResponseBody RestListInfo<LoyaltyPointsRedeemedTO> getLoyaltyPointsRedeemed(
			HttpServletRequest httpServletRequest) {

		logger.info(
				"================== LoyaltyDashBoardRestController getLoyaltyPointsRedeemed API Start =====================");
		RestListInfo<LoyaltyPointsRedeemedTO> listInfo = new RestListInfo<LoyaltyPointsRedeemedTO>();
		try {
			listInfo.setData(loyaltydashBoardService.getLoyaltyPointsRedeemed());
		} catch (Exception e) {
			ExceptionUtil.handle(e, listInfo);
			logger.error("Class : LoyaltyDashBoardRestController  |  Method : getLoyaltyPointsRedeemed \n" + e);
			e.printStackTrace();
		}

		return listInfo;
	}

	@GetMapping(MagikServicePath.CUSTOMER_MIGRATION)
	public @ResponseBody RestListInfo<CustomerMigrationTO> getCustomerMigration(HttpServletRequest httpServletRequest) {

		logger.info(
				"================== LoyaltyDashBoardRestController getCustomerMigration API Start =====================");
		RestListInfo<CustomerMigrationTO> listInfo = new RestListInfo<CustomerMigrationTO>();
		try {
			listInfo.setData(loyaltydashBoardService.getCustomerMigration());
		} catch (Exception e) {
			ExceptionUtil.handle(e, listInfo);
			logger.error("Class : LoyaltyDashBoardRestController  |  Method : getCustomerMigration \n" + e);
			e.printStackTrace();
		}

		return listInfo;
	}

	@GetMapping(MagikServicePath.MERCHANTWISE_REDEMPTION)
	public @ResponseBody RestListInfo<MercantWiseRedeemptionTO> getMercantWise(HttpServletRequest httpServletRequest) {

		logger.info("================== LoyaltyDashBoardRestController getMercantWise API Start =====================");
		RestListInfo<MercantWiseRedeemptionTO> listInfo = new RestListInfo<MercantWiseRedeemptionTO>();
		try {
			listInfo.setData(loyaltydashBoardService.getMercantWise());
		} catch (Exception e) {
			ExceptionUtil.handle(e, listInfo);
			logger.error("Class : LoyaltyDashBoardRestController  |  Method : getMercantWise \n" + e);
			e.printStackTrace();
		}

		return listInfo;
	}

	@GetMapping(MagikServicePath.EARNED_VS_REDEMPTION)
	public @ResponseBody RestListInfo<EarnedRedeemptionTO> getEarnedRedeemption(HttpServletRequest httpServletRequest) {

		logger.info(
				"================== LoyaltyDashBoardRestController getEarnedRedeemption API Start =====================");
		RestListInfo<EarnedRedeemptionTO> listInfo = new RestListInfo<EarnedRedeemptionTO>();
		try {
			listInfo.setData(loyaltydashBoardService.getEarnedRedeemption());
		} catch (Exception e) {
			ExceptionUtil.handle(e, listInfo);
			logger.error("Class : LoyaltyDashBoardRestController  |  Method : getEarnedRedeemption \n" + e);
			e.printStackTrace();
		}

		return listInfo;
	}

	@GetMapping(MagikServicePath.TOTAL_REWARD_POINTS_STATUS)
	public @ResponseBody RestListInfo<TotalRewardPointsTO> getTotalReward(HttpServletRequest httpServletRequest) {

		logger.info("================== LoyaltyDashBoardRestController getTotalReward API Start =====================");
		RestListInfo<TotalRewardPointsTO> listInfo = new RestListInfo<TotalRewardPointsTO>();
		try {
			listInfo.setData(loyaltydashBoardService.getTotalReward());
		} catch (Exception e) {
			ExceptionUtil.handle(e, listInfo);
			logger.error("Class : LoyaltyDashBoardRestController  |  Method : getTotalReward \n" + e);
			e.printStackTrace();
		}

		return listInfo;
	}

	@GetMapping(MagikServicePath.CHANNEL_WISE_REDEMPTION)
	public @ResponseBody RestListInfo<ChannelWiseRedeemptionTO> getChannelRedeemption(
			HttpServletRequest httpServletRequest) {

		logger.info(
				"================== LoyaltyDashBoardRestController getChannelRedeemption API Start =====================");
		RestListInfo<ChannelWiseRedeemptionTO> listInfo = new RestListInfo<ChannelWiseRedeemptionTO>();
		try {
			listInfo.setData(loyaltydashBoardService.getChannelRedeemption());
		} catch (Exception e) {
			ExceptionUtil.handle(e, listInfo);
			logger.error("Class : LoyaltyDashBoardRestController  |  Method : getChannelRedeemption \n" + e);
			e.printStackTrace();
		}

		return listInfo;
	}

	@GetMapping(MagikServicePath.EARNED_VS_REDEMPTION_1)
	public @ResponseBody RestListInfo<EarnedRedeemptionTO_1> getEarnedRedeemption_1(
			HttpServletRequest httpServletRequest) {

		logger.info(
				"================== LoyaltyDashBoardRestController getEarnedRedeemption_1 API Start =====================");
		RestListInfo<EarnedRedeemptionTO_1> listInfo = new RestListInfo<EarnedRedeemptionTO_1>();
		try {
			listInfo.setData(loyaltydashBoardService.getEarnedRedeemption_1());
		} catch (Exception e) {
			ExceptionUtil.handle(e, listInfo);
			logger.error("Class : LoyaltyDashBoardRestController  |  Method : getEarnedRedeemption_1 \n" + e);
			e.printStackTrace();
		}

		return listInfo;
	}

}
