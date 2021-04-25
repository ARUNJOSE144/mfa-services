package com.sixdee.magik.services.dao;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sixdee.magik.services.model.RewardReportTO;

/**
 * @author JANARDHAN REDDY
 * @Date : February, 2021
 */


@Service
@Transactional
public interface RewardReportDAO {

	List<RewardReportTO> getData();

}
