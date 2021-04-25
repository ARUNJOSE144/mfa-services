package com.sixdee.magik.services.dao;

import java.util.List;

import com.sixdee.magik.services.model.AonWiseGameficationTO;
import com.sixdee.magik.services.model.GamifDBUsersVsNonusersTO;
import com.sixdee.magik.services.model.LoyaltyCaategoryWiseGamificationTO;
import com.sixdee.magik.services.model.RewardsRedeemedTO;
import com.sixdee.magik.services.model.SlabWiseGamificationTO;
import com.sixdee.magik.services.model.SummaryCountTO;
import com.sixdee.magik.services.model.Top5PlayedGamesTO;
import com.sixdee.magik.services.model.UserWebAppTO;

/**
 * @author ramesh.cheerla
 * @Date : July, 2020
 *
 */

public interface GamificationDashboardDao {

	// Users vs Non Users
	public List<GamifDBUsersVsNonusersTO> getUsersVsNonUsers();

	public List<UserWebAppTO> getUserWebApp();

	public List<LoyaltyCaategoryWiseGamificationTO> getLoyaltyCategoryWise();

	public List<Top5PlayedGamesTO> getTop5Played();

	public List<AonWiseGameficationTO> getAonWiseUsers();

	public List<SlabWiseGamificationTO> getSlabWiseUSers();

	public List<RewardsRedeemedTO> getRewardsRedeemed();

	public List<SummaryCountTO> getSummaryCount();

}
