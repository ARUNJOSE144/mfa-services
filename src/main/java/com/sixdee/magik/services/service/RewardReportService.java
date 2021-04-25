package com.sixdee.magik.services.service;

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
public interface RewardReportService {

	List<RewardReportTO> getData();

}
