package com.inno.mfa.services.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.inno.mfa.services.dao.TradeDAO;
import com.inno.mfa.services.model.CommonRespTo;
import com.inno.mfa.services.model.PaginationTo;
import com.inno.mfa.services.model.trade.TradeMasterTo;

/**
 * @author Arun Jose
 * @Date : March, 2021
 */

@RestController
public class TradeRestController {

	static final Logger logger = Logger.getLogger(TradeRestController.class);
	@Autowired
	TradeDAO tradeDAO;

	@PostMapping(value = "/trade/v1/create")
	public @ResponseBody CommonRespTo<TradeMasterTo> create(HttpServletRequest httpServletRequest,
			@RequestBody TradeMasterTo tradeMasterTo) throws IOException {
		CommonRespTo<TradeMasterTo> to = new CommonRespTo<TradeMasterTo>();
		try {
			tradeDAO.create(tradeMasterTo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return to;
	}

	@PostMapping(value = "/trade/v1/list")
	public @ResponseBody PaginationTo<TradeMasterTo> getDaysList(HttpServletRequest httpServletRequest,
			@RequestBody PaginationTo<TradeMasterTo> paginationTo) throws IOException {
		System.out.println("================Search Request : " + paginationTo.toString());
		try {
			tradeDAO.getDaysList(paginationTo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return paginationTo;
	}

	@PostMapping(value = "/trade/v1/view")
	public @ResponseBody CommonRespTo<TradeMasterTo> view(HttpServletRequest httpServletRequest,
			@RequestBody TradeMasterTo dto) throws IOException {
		CommonRespTo<TradeMasterTo> to = new CommonRespTo<TradeMasterTo>();
		try {
			to.setData(tradeDAO.view(dto));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return to;
	}

	@PostMapping(value = "/trade/v1/delete")
	public @ResponseBody CommonRespTo<TradeMasterTo> delete(HttpServletRequest httpServletRequest,
			@RequestBody TradeMasterTo tradeMasterTo) throws IOException {
		CommonRespTo<TradeMasterTo> to = new CommonRespTo<TradeMasterTo>();
		try {
			tradeDAO.delete(tradeMasterTo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return to;
	}

}
