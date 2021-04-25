package com.sixdee.magik.services.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sixdee.magik.services.model.AonWiseGameficationTO;
import com.sixdee.magik.services.model.GamifDBUsersVsNonusersTO;
import com.sixdee.magik.services.model.LoyaltyCaategoryWiseGamificationTO;
import com.sixdee.magik.services.model.RewardsRedeemedTO;
import com.sixdee.magik.services.model.SlabWiseGamificationTO;
import com.sixdee.magik.services.model.SummaryCountTO;
import com.sixdee.magik.services.model.Top5PlayedGamesTO;
import com.sixdee.magik.services.model.UserWebAppTO;
import com.sixdee.magik.services.service.GamificationDashboardService;
import com.sixdee.magik.services.util.ExceptionUtil;
import com.sixdee.magik.services.util.MagikServicePath;
import com.sixdee.magik.services.util.RestListInfo;

/**
 * @author ramesh.cheerla
 * @Date : July, 2020
 *
 */

@RestController
public class GamificationDashboardRestControllar {

	static final Logger logger = Logger.getLogger(GamificationDashboardRestControllar.class);

	@Autowired
	GamificationDashboardService gamifDashService;

	/*
	 * Authenticate Credentials
	 */
	@GetMapping(MagikServicePath.USERS_VS_NONUSERS)
	public @ResponseBody RestListInfo<GamifDBUsersVsNonusersTO> getUsersVsNonUsers(
			HttpServletRequest httpServletRequest) {

		logger.info("================== Gamification Dashboard Rest Controllar API Start =====================");
		logger.info("Class : GamificationDashboardRestControllar | Method : getUsersVsNonusers");

		RestListInfo<GamifDBUsersVsNonusersTO> info = new RestListInfo<GamifDBUsersVsNonusersTO>();

		try {
			info.setData(gamifDashService.getUsersVsNonUsers());
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : GamificationDashboardRestControllar  |  Method : getUsersVsNonusers " + e);
			e.printStackTrace();
		}

		logger.info("================== Gamification Dashboard Rest Controllar API End =====================");

		return info;
	}

	@GetMapping(MagikServicePath.USERS_WEB_VS_APP)
	public @ResponseBody RestListInfo<UserWebAppTO> getUserWebApp(HttpServletRequest httpServletRequest) {

		logger.info("================== Gamification Dashboard Rest Controllar API Start =====================");
		logger.info("Class : GamificationDashboardRestControllar | Method : getUserWebApp");

		RestListInfo<UserWebAppTO> info = new RestListInfo<UserWebAppTO>();

		try {
			info.setData(gamifDashService.getUserWebApp());
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : GamificationDashboardRestControllar  |  Method : getUserWebApp " + e);
			e.printStackTrace();
		}

		logger.info("================== Gamification Dashboard Rest Controllar API End =====================");

		return info;
	}
	
	@GetMapping(MagikServicePath.LOYALTY_CAT_WISE_USER_INFO)
	public @ResponseBody RestListInfo<LoyaltyCaategoryWiseGamificationTO> getLoyaltyCategoryWise(HttpServletRequest httpServletRequest) {

		logger.info("================== Gamification Dashboard Rest Controllar API Start =====================");
		logger.info("Class : GamificationDashboardRestControllar | Method : getLoyaltyCategoryWise");

		RestListInfo<LoyaltyCaategoryWiseGamificationTO> info = new RestListInfo<LoyaltyCaategoryWiseGamificationTO>();

		try {
			info.setData(gamifDashService.getLoyaltyCategoryWise());
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : GamificationDashboardRestControllar  |  Method : getLoyaltyCategoryWise " + e);
			e.printStackTrace();
		}

		logger.info("================== Gamification Dashboard Rest Controllar API End =====================");

		return info;
	}
	
	@GetMapping(MagikServicePath.TOP_FIVE_PLAYED_GAMES)
	public @ResponseBody RestListInfo<Top5PlayedGamesTO> getTop5Played(HttpServletRequest httpServletRequest) {

		logger.info("================== Gamification Dashboard Rest Controllar API Start =====================");
		logger.info("Class : GamificationDashboardRestControllar | Method : getTop5Played");

		RestListInfo<Top5PlayedGamesTO> info = new RestListInfo<Top5PlayedGamesTO>();

		try {
			info.setData(gamifDashService.getTop5Played());
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : GamificationDashboardRestControllar  |  Method : getTop5Played " + e);
			e.printStackTrace();
		}

		logger.info("================== Gamification Dashboard Rest Controllar API End =====================");

		return info;
	}

	
	@GetMapping(MagikServicePath.AON_WISE_USERS)
	public @ResponseBody RestListInfo<AonWiseGameficationTO> getAonWiseUsers(HttpServletRequest httpServletRequest) {

		logger.info("================== Gamification Dashboard Rest Controllar API Start =====================");
		logger.info("Class : GamificationDashboardRestControllar | Method : getAonWiseUsers");

		RestListInfo<AonWiseGameficationTO> info = new RestListInfo<AonWiseGameficationTO>();

		try {
			info.setData(gamifDashService.getAonWiseUsers());
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : GamificationDashboardRestControllar  |  Method : getAonWiseUsers " + e);
			e.printStackTrace();
		}

		logger.info("================== Gamification Dashboard Rest Controllar API End =====================");

		return info;
	}

	@GetMapping(MagikServicePath.SLAB_WISE_USER_COUNT)
	public @ResponseBody RestListInfo<SlabWiseGamificationTO> getSlabWiseUSers(HttpServletRequest httpServletRequest) {

		logger.info("================== Gamification Dashboard Rest Controllar API Start =====================");
		logger.info("Class : GamificationDashboardRestControllar | Method : getSlabWiseUSers");

		RestListInfo<SlabWiseGamificationTO> info = new RestListInfo<SlabWiseGamificationTO>();

		try {
			info.setData(gamifDashService.getSlabWiseUSers());
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : GamificationDashboardRestControllar  |  Method : getSlabWiseUSers " + e);
			e.printStackTrace();
		}

		logger.info("================== Gamification Dashboard Rest Controllar API End =====================");

		return info;
	}

	@GetMapping(MagikServicePath.REWARDS_REDEEMED_COUNT)
	public @ResponseBody RestListInfo<RewardsRedeemedTO> getRewardsRedeemed(HttpServletRequest httpServletRequest) {

		logger.info("================== Gamification Dashboard Rest Controllar API Start =====================");
		logger.info("Class : GamificationDashboardRestControllar | Method : getRewardsRedeemed");

		RestListInfo<RewardsRedeemedTO> info = new RestListInfo<RewardsRedeemedTO>();

		try {
			info.setData(gamifDashService.getRewardsRedeemed());
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : GamificationDashboardRestControllar  |  Method : getRewardsRedeemed " + e);
			e.printStackTrace();
		}

		logger.info("================== Gamification Dashboard Rest Controllar API End =====================");

		return info;
	}
	@GetMapping(MagikServicePath.SUMMARY_COUNT)
	public @ResponseBody RestListInfo<SummaryCountTO> getSummaryCount(HttpServletRequest httpServletRequest) {

		logger.info("================== Gamification Dashboard Rest Controllar API Start =====================");
		logger.info("Class : GamificationDashboardRestControllar | Method : getSummaryCount");

		RestListInfo<SummaryCountTO> info = new RestListInfo<SummaryCountTO>();

		try {
			info.setData(gamifDashService.getSummaryCount());
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : GamificationDashboardRestControllar  |  Method : getSummaryCount " + e);
			e.printStackTrace();
		}

		logger.info("================== Gamification Dashboard Rest Controllar API End =====================");

		return info;
	}

}
