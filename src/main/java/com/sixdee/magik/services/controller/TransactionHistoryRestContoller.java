package com.sixdee.magik.services.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sixdee.magik.services.model.ApprovalAuditTO;
import com.sixdee.magik.services.model.CreateUSerProfileTO;
import com.sixdee.magik.services.model.TransactionHistoryTO;
import com.sixdee.magik.services.service.TransactionHistoryService;
import com.sixdee.magik.services.util.ExceptionUtil;
import com.sixdee.magik.services.util.MagikServicePath;
import com.sixdee.magik.services.util.RestInfo;
import com.sixdee.magik.services.util.RestListInfo;
import com.sixdee.magik.services.util.SystemProperties;

/**
 * @author Nakhil Kurian
 * @Date : February, 2021
 */

@RestController
public class TransactionHistoryRestContoller {

	@Autowired
	private Environment env;

	static final Logger logger = Logger.getLogger(TransactionHistoryRestContoller.class);

	@Autowired
	SystemProperties properties;

	@Autowired
	TransactionHistoryService tranactionHistoryService;

	@PostMapping(MagikServicePath.GET_SEARCH_TRANSACTION_HISTORY)
	public @ResponseBody RestInfo<TransactionHistoryTO> getTransactionHistory(HttpServletRequest httpServletRequest,
			@RequestBody TransactionHistoryTO transTO) {
		RestInfo<TransactionHistoryTO> info = new RestInfo<TransactionHistoryTO>();
		TransactionHistoryTO status = null;
		try {
			info.setOperationCode(0);
			info.setMessage("Success");
			info.setData(tranactionHistoryService.getTransactionHistory(transTO));

		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			e.printStackTrace();
			info.setOperationCode(1);
			info.setMessage("Failure");
		}
		return info;
	}
}
