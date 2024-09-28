package com.inno.mfa.services.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.inno.mfa.services.dao.OIDAO;
import com.inno.mfa.services.dao.TradeDAO;
import com.inno.mfa.services.model.CommonRespTo;
import com.inno.mfa.services.model.OIDataTo;
import com.inno.mfa.services.model.PaginationTo;
import com.inno.mfa.services.model.trade.TradeMasterTo;

/**
 * @author Arun Jose
 * @Date : March, 2021
 */

@RestController
public class OIController {

	static final Logger logger = Logger.getLogger(OIController.class);
	@Autowired
	OIDAO oidao;

	@PostMapping(value = "/trade/v1/oiData")
	public @ResponseBody OIDataTo getOIData(HttpServletRequest httpServletRequest, @RequestBody OIDataTo oiDataTo)
			throws IOException {
		CommonRespTo<TradeMasterTo> to = new CommonRespTo<TradeMasterTo>();
		try {
			oidao.getOIData(oiDataTo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return oiDataTo;
	}
}
