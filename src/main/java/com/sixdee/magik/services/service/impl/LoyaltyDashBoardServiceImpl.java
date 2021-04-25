package com.sixdee.magik.services.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sixdee.magik.services.dao.CampaignDashBoardDao;
import com.sixdee.magik.services.dao.LoyaltyDahBoardDao;
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

/**
 * @author Nakhil Kurian
 * @Date : August, 2020
 *
 */

@Service
@Transactional

public class LoyaltyDashBoardServiceImpl implements LoyaltyDashBoardService {

	@Autowired
	LoyaltyDahBoardDao loyaltyDahBoardDao;

	@Override
	public List<NewEnrollmentsTO> getNewEnrollments() {
		return loyaltyDahBoardDao.getNewEnrollments();
	}

	@Override
	public List<LoyaltyPointsTrendTO> getLoyaltyPointTrend() {
		return loyaltyDahBoardDao.getLoyaltyPointTrend();
	}

	@Override
	public List<MAKASIB_AcccountTO> getMakasibAccounts() {
		return loyaltyDahBoardDao.getMakasibAccounts();
	}

	@Override
	public List<LoyaltyPointsRedeemedTO> getLoyaltyPointsRedeemed() {
		return loyaltyDahBoardDao.getLoyaltyPointsRedeemed();
	}

	@Override
	public List<CustomerMigrationTO> getCustomerMigration() {
		return loyaltyDahBoardDao.getCustomerMigration();
	}

	@Override
	public List<MercantWiseRedeemptionTO> getMercantWise() {
		return loyaltyDahBoardDao.getMercantWise();
	}

	@Override
	public List<EarnedRedeemptionTO> getEarnedRedeemption() {
		return loyaltyDahBoardDao.getEarnedRedeemption();
	}

	@Override
	public List<TotalRewardPointsTO> getTotalReward() {
		return loyaltyDahBoardDao.getTotalReward();
	}

	@Override
	public List<ChannelWiseRedeemptionTO> getChannelRedeemption() {
		return loyaltyDahBoardDao.getChannelRedeemption();
	}

	@Override
	public List<EarnedRedeemptionTO_1> getEarnedRedeemption_1() {
		return loyaltyDahBoardDao.getEarnedRedeemption_1();
	}

}
