
package com.sixdee.magik.services.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sixdee.magik.services.dao.GamificationDashboardDao;
import com.sixdee.magik.services.model.AonWiseGameficationTO;
import com.sixdee.magik.services.model.GamifDBUsersVsNonusersTO;
import com.sixdee.magik.services.model.LoyaltyCaategoryWiseGamificationTO;
import com.sixdee.magik.services.model.RewardsRedeemedTO;
import com.sixdee.magik.services.model.SlabWiseGamificationTO;
import com.sixdee.magik.services.model.SummaryCountTO;
import com.sixdee.magik.services.model.Top5PlayedGamesTO;
import com.sixdee.magik.services.model.UserWebAppTO;
import com.sixdee.magik.services.service.GamificationDashboardService;

/**
 * @author ramesh.cheerla
 * @Date : July, 2020
 *
 */

@Service
@Transactional
public class GamificationDashboardServiceImpl implements GamificationDashboardService {

	@Autowired
	GamificationDashboardDao gamifDashDao;

	@Override
	public List<GamifDBUsersVsNonusersTO> getUsersVsNonUsers() {
		return gamifDashDao.getUsersVsNonUsers();
	}

	@Override
	public List<UserWebAppTO> getUserWebApp() {
		return gamifDashDao.getUserWebApp();
	}

	@Override
	public List<LoyaltyCaategoryWiseGamificationTO> getLoyaltyCategoryWise() {
		return gamifDashDao.getLoyaltyCategoryWise();
	}

	@Override
	public List<Top5PlayedGamesTO> getTop5Played() {
		return gamifDashDao.getTop5Played();
	}

	@Override
	public List<AonWiseGameficationTO> getAonWiseUsers() {
		return gamifDashDao.getAonWiseUsers();
	}

	@Override
	public List<SlabWiseGamificationTO> getSlabWiseUSers() {
		return gamifDashDao.getSlabWiseUSers();
	}

	@Override
	public List<RewardsRedeemedTO> getRewardsRedeemed() {
		return gamifDashDao.getRewardsRedeemed();
	}

	@Override
	public List<SummaryCountTO> getSummaryCount() {
		return gamifDashDao.getSummaryCount();
	}

}
