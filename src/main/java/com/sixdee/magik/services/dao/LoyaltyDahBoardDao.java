package com.sixdee.magik.services.dao;

import java.util.List;

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

/**
 * @author Nakhil Kurian
 * @Date : August, 2020
 *
 */

public interface LoyaltyDahBoardDao {

	List<NewEnrollmentsTO> getNewEnrollments();

	List<LoyaltyPointsTrendTO> getLoyaltyPointTrend();

	List<MAKASIB_AcccountTO> getMakasibAccounts();

	List<LoyaltyPointsRedeemedTO> getLoyaltyPointsRedeemed();

	List<CustomerMigrationTO> getCustomerMigration();

	List<MercantWiseRedeemptionTO> getMercantWise();

	List<EarnedRedeemptionTO> getEarnedRedeemption();

	List<TotalRewardPointsTO> getTotalReward();

	List<ChannelWiseRedeemptionTO> getChannelRedeemption();

	List<EarnedRedeemptionTO_1> getEarnedRedeemption_1();

}
