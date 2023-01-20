package com.inno.mfa.services.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inno.mfa.services.dao.TradeLogDAO;
import com.inno.mfa.services.model.CommonRespTo;
import com.inno.mfa.services.model.PaginationTo;
import com.inno.mfa.services.model.TradeEventsTo;
import com.inno.mfa.services.model.TradeLogDetailsTo;
import com.inno.mfa.services.model.TradeLogMasterTo;
import com.inno.mfa.services.model.TradeSymbolTo;

/**
 * @author Arun Jose
 * @Date : March, 2021
 */

@RestController
public class TradeLogRestController {

	static final Logger logger = Logger.getLogger(TradeLogRestController.class);
	@Autowired
	TradeLogDAO tradeLogDAO;

	@PostMapping(value = "/tradeLog/v1/create")
	public @ResponseBody CommonRespTo<String> create(@ModelAttribute final TradeLogMasterTo tradeLogMasterTo,
			@RequestParam(value = "niftyImage", required = false) MultipartFile niftyImage,
			@RequestParam(value = "bankNiftyImage", required = false) MultipartFile bankNiftyImage,
			@RequestParam(value = "file1", required = false) MultipartFile file1,
			@RequestParam(value = "file2", required = false) MultipartFile file2,
			@RequestParam(value = "file3", required = false) MultipartFile file3,
			@RequestParam(value = "file4", required = false) MultipartFile file4) throws IOException {
		logger.info("Filesssss=====>" + file1);

		List<MultipartFile> files = new ArrayList<MultipartFile>();
		if (file1 != null)
			files.add(file1);
		if (file2 != null)
			files.add(file2);
		if (file3 != null)
			files.add(file3);
		if (file4 != null)
			files.add(file4);

		tradeLogMasterTo.setNiftyImage(niftyImage);
		tradeLogMasterTo.setBankNiftyImage(bankNiftyImage);

		tradeLogMasterTo.setImages(files);

		ObjectMapper objectMapper = new ObjectMapper();

		tradeLogMasterTo.setTradeLogDetailsTos(Arrays.asList(
				objectMapper.readValue(tradeLogMasterTo.getTradeLogDetailsTosString(), TradeLogDetailsTo[].class)));

		System.out.println("TradeLogMasterTo : " + tradeLogMasterTo);

		CommonRespTo<String> to = new CommonRespTo<String>();
		try {
			tradeLogDAO.create(tradeLogMasterTo, to);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return to;
	}

	@PostMapping(value = "/tradeLog/v1/search")
	public @ResponseBody PaginationTo<List<TradeLogMasterTo>> searchQuestion(HttpServletRequest httpServletRequest,
			@RequestBody TradeLogMasterTo searchTO) throws IOException {

		PaginationTo<List<TradeLogMasterTo>> paginationTo = new PaginationTo<List<TradeLogMasterTo>>();
		System.out.println("================Search Request : " + searchTO.toString());
		List<TradeLogMasterTo> list = null;
		try {
			paginationTo = tradeLogDAO.search(searchTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return paginationTo;
	}

	@PostMapping(value = "/tradeLog/v1/list")
	public @ResponseBody PaginationTo<TradeLogMasterTo> getDaysList(HttpServletRequest httpServletRequest,
			@RequestBody PaginationTo<TradeLogMasterTo> paginationTo) throws IOException {
		System.out.println("================Search Request : " + paginationTo.toString());
		try {
			tradeLogDAO.getDaysList(paginationTo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return paginationTo;
	}

	@PostMapping(value = "/tradeLog/v1/view")
	public @ResponseBody CommonRespTo<TradeLogMasterTo> view(HttpServletRequest httpServletRequest,
			@RequestBody TradeLogMasterTo dto) throws IOException {
		CommonRespTo<TradeLogMasterTo> to = new CommonRespTo<TradeLogMasterTo>();
		try {
			to.setData(tradeLogDAO.view(dto));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return to;
	}

	@GetMapping(value = "/tradeLog/v1/getEvents")
	public @ResponseBody CommonRespTo<List<TradeEventsTo>> getEvents(HttpServletRequest httpServletRequest)
			throws IOException {
		CommonRespTo<List<TradeEventsTo>> list = new CommonRespTo<List<TradeEventsTo>>();
		try {
			list.setData(tradeLogDAO.getEvents());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@GetMapping(value = "/tradeLog/v1/getSymbols")
	public @ResponseBody CommonRespTo<List<TradeSymbolTo>> getSymbols(HttpServletRequest httpServletRequest)
			throws IOException {
		CommonRespTo<List<TradeSymbolTo>> list = new CommonRespTo<List<TradeSymbolTo>>();
		try {
			list.setData(tradeLogDAO.getSymbols());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
