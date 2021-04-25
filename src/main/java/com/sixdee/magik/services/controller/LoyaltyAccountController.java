package com.sixdee.magik.services.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sixdee.magik.services.model.LoyaltyRequestDTO;
import com.sixdee.magik.services.model.RateCardDTO;
import com.sixdee.magik.services.model.TierConfigDTO;
import com.sixdee.magik.services.model.TierInfoDTO;
import com.sixdee.magik.services.service.LoyaltyAccountService;
import com.sixdee.magik.services.util.ExceptionUtil;
import com.sixdee.magik.services.util.RestInfo;
import com.sixdee.magik.services.util.RestListInfo;

@RestController
public class LoyaltyAccountController {

	static final Logger logger = Logger.getLogger(LoyaltyAccountController.class);

	@Autowired
	private LoyaltyAccountService loyaltyAccountService;

	@PostMapping("/{talent}/accountMerging")
	public @ResponseBody RestInfo<LoyaltyRequestDTO> accountMerging(HttpServletRequest httpServletRequest, @PathVariable String talent, @RequestBody LoyaltyRequestDTO loyaltyRequestDTO) {
		
		logger.info("LoyaltyAccountController>>>>>>>accountMerging>>>>>");
		RestInfo<LoyaltyRequestDTO> info = new RestInfo<LoyaltyRequestDTO>();
		try {
			info.setOperationCode(0);
			if(talent!=null && talent.equalsIgnoreCase("1.0")) {
				info.setData(loyaltyAccountService.accountMerging(loyaltyRequestDTO));
			}
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			e.printStackTrace();
			logger.error("Exception :: " + e.getMessage());
		}
		return info;
	}
	
	@PostMapping("/{talent}/accountTransfer")
	public @ResponseBody RestInfo<LoyaltyRequestDTO> accountTransfer(HttpServletRequest httpServletRequest, @PathVariable String talent, @RequestBody LoyaltyRequestDTO loyaltyRequestDTO) {
		logger.info("LoyaltyAccountController>>>>>>>accountTransfer>>>>>>>");
		RestInfo<LoyaltyRequestDTO> info = new RestInfo<LoyaltyRequestDTO>();
		try {
			info.setOperationCode(0);
			if(talent!=null && talent.equalsIgnoreCase("1.0")) {
				info.setData(loyaltyAccountService.accountTransfer(loyaltyRequestDTO));
			}
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			e.printStackTrace();
			logger.error("Exception :: " + e.getMessage());
		}
		return info;
	}
	
	@GetMapping("/{talent}/getTierAndCategory")
	public @ResponseBody RestInfo<TierInfoDTO> getTierAndCategory(HttpServletRequest httpServletRequest, @PathVariable String talent) {
		logger.info("LoyaltyAccountController>>>>>>>accountTransfer>>>>>>>");
		RestInfo<TierInfoDTO> info = new RestInfo<TierInfoDTO>();
		TierInfoDTO tierInfoDTO = null;
		try {
			tierInfoDTO = new TierInfoDTO();
			info.setOperationCode(0);
			if(talent!=null && talent.equalsIgnoreCase("1.0")) {
				info.setData(loyaltyAccountService.getTierAndCategory(tierInfoDTO));
			}
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			e.printStackTrace();
			logger.error("Exception :: " + e.getMessage());
		}
		return info;
	}
	
	@PostMapping("/{talent}/createTierDetails")
	public @ResponseBody RestInfo<TierInfoDTO> createTierDetails(HttpServletRequest httpServletRequest, @PathVariable String talent, @RequestBody TierInfoDTO tierInfoDTO) {
		logger.info("LoyaltyAccountController>>>>>>>createTierDetails>>>>>>>");
		RestInfo<TierInfoDTO> info = new RestInfo<TierInfoDTO>();
		try {
			info.setOperationCode(0);
			if(talent!=null && talent.equalsIgnoreCase("1.0")) {
				info.setData(loyaltyAccountService.createTierDetails(tierInfoDTO));
			}
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			e.printStackTrace();
			logger.error("Exception :: " + e.getMessage());
		}
		return info;
	}
	
	@GetMapping("/{talent}/getAllTierDetails")
	public @ResponseBody RestListInfo<TierInfoDTO> getAllTierDetails(HttpServletRequest httpServletRequest, @PathVariable String talent) {
		logger.info("LoyaltyAccountController>>>>>>>getAllTierDetails>>>>>>>");
		RestListInfo<TierInfoDTO> info = new RestListInfo<TierInfoDTO>();
		try {
			TierInfoDTO tierInfoDTO = new TierInfoDTO();
			info.setOperationCode(0);
			if(talent!=null && talent.equalsIgnoreCase("1.0")) {
				info.setData(loyaltyAccountService.getAllTierDetails(tierInfoDTO));
			}
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			e.printStackTrace();
			logger.error("Exception :: " + e.getMessage());
		}
		return info;
	}
	
	@PostMapping("/{talent}/createCategoryDetails")
	public @ResponseBody RestInfo<TierInfoDTO> createCategoryDetails(HttpServletRequest httpServletRequest, @PathVariable String talent, @RequestBody TierInfoDTO tierInfoDTO) {
		logger.info("LoyaltyAccountController>>>>>>>createCategoryDetails>>>>>>>");
		RestInfo<TierInfoDTO> info = new RestInfo<TierInfoDTO>();
		try {
			info.setOperationCode(0);
			if(talent!=null && talent.equalsIgnoreCase("1.0")) {
				info.setData(loyaltyAccountService.createCategoryDetails(tierInfoDTO));
			}
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			e.printStackTrace();
			logger.error("Exception :: " + e.getMessage());
		}
		return info;
	}
	
	@PostMapping("/{talent}/updateTierDetails")
	public @ResponseBody RestInfo<TierInfoDTO> updateTierDetails(HttpServletRequest httpServletRequest, @PathVariable String talent, @RequestBody TierInfoDTO tierInfoDTO) {
		logger.info("LoyaltyAccountController>>>>>>>updateTierDetails>>>>>>>");
		RestInfo<TierInfoDTO> info = new RestInfo<TierInfoDTO>();
		try {
			info.setOperationCode(0);
			if(talent!=null && talent.equalsIgnoreCase("1.0")) {
				info.setData(loyaltyAccountService.updateTierDetails(tierInfoDTO));
			}
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			e.printStackTrace();
			logger.error("Exception :: " + e.getMessage());
		}
		return info;
	}
	
	@PostMapping("/{talent}/deleteTierDetails")
	public @ResponseBody RestInfo<TierInfoDTO> deleteTierDetails(HttpServletRequest httpServletRequest, @PathVariable String talent, @RequestBody TierInfoDTO tierInfoDTO) {
		logger.info("LoyaltyAccountController>>>>>>>deleteTierDetails>>>>>>>");
		RestInfo<TierInfoDTO> info = new RestInfo<TierInfoDTO>();
		try {
			info.setOperationCode(0);
			if(talent!=null && talent.equalsIgnoreCase("1.0")) {
				info.setData(loyaltyAccountService.deleteTierDetails(tierInfoDTO));
			}
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			e.printStackTrace();
			logger.error("Exception :: " + e.getMessage());
		}
		return info;
	}
	
	@GetMapping("/{talent}/getAllTierConfigDetails")
	public @ResponseBody RestListInfo<TierConfigDTO> getAllTierConfigDetails(HttpServletRequest httpServletRequest, @PathVariable String talent) {
		logger.info("LoyaltyAccountController>>>>>>>getAllTierConfigDetails>>>>>>>");
		RestListInfo<TierConfigDTO> info = new RestListInfo<TierConfigDTO>();
		try {
			info.setOperationCode(0);
			TierConfigDTO tierInfoDTO = new TierConfigDTO();
			info.setOperationCode(0);
			if(talent!=null && talent.equalsIgnoreCase("1.0")) {
				info.setData(loyaltyAccountService.getAllTierConfigDetails(tierInfoDTO));
			}
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			e.printStackTrace();
			logger.error("Exception :: " + e.getMessage());
		}
		return info;
	}
	
	@PostMapping("/{talent}/createTierConfigDetails")
	public @ResponseBody RestInfo<TierConfigDTO> createTierConfigDetails(HttpServletRequest httpServletRequest, @PathVariable String talent, @RequestBody TierConfigDTO tierConfigDTO) {
		logger.info("LoyaltyAccountController>>>>>>>createTierConfigDetails>>>>>>>");
		RestInfo<TierConfigDTO> info = new RestInfo<TierConfigDTO>();
		try {
			info.setOperationCode(0);
			if(talent!=null && talent.equalsIgnoreCase("1.0")) {
				info.setData(loyaltyAccountService.createTierConfigDetails(tierConfigDTO));
			}
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			e.printStackTrace();
			logger.error("Exception :: " + e.getMessage());
		}
		return info;
	}
	
	@PostMapping("/{talent}/updateTierConfigDetails")
	public @ResponseBody RestInfo<TierConfigDTO> updateTierConfigDetails(HttpServletRequest httpServletRequest, @PathVariable String talent, @RequestBody TierConfigDTO tierConfigDTO) {
		logger.info("LoyaltyAccountController>>>>>>>updateTierConfigDetails>>>>>>>");
		RestInfo<TierConfigDTO> info = new RestInfo<TierConfigDTO>();
		try {
			info.setOperationCode(0);
			if(talent!=null && talent.equalsIgnoreCase("1.0")) {
				info.setData(loyaltyAccountService.updateTierConfigDetails(tierConfigDTO));
			}
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			e.printStackTrace();
			logger.error("Exception :: " + e.getMessage());
		}
		return info;
	}
	
	@PostMapping("/{talent}/deleteTierConfigDetails")
	public @ResponseBody RestInfo<TierConfigDTO> deleteTierConfigDetails(HttpServletRequest httpServletRequest, @PathVariable String talent, @RequestBody TierConfigDTO tierConfigDTO) {
		logger.info("LoyaltyAccountController>>>>>>>deleteTierConfigDetails>>>>>>>");
		RestInfo<TierConfigDTO> info = new RestInfo<TierConfigDTO>();
		try {
			info.setOperationCode(0);
			if(talent!=null && talent.equalsIgnoreCase("1.0")) {
				info.setData(loyaltyAccountService.deleteTierConfigDetails(tierConfigDTO));
			}
		} catch (Exception e) {
			info.setOperationCode(1);
			e.printStackTrace();
			logger.error("Exception :: " + e.getMessage());
		}
		return info;
	}
	
	@PostMapping("/{talent}/createRateCardDetails")
	public @ResponseBody RestInfo<RateCardDTO> createRateCardDetails(HttpServletRequest httpServletRequest, @PathVariable String talent, @RequestBody RateCardDTO rateCardDTO) {
		logger.info("LoyaltyAccountController>>>>>>>createRateCardDetails>>>>>>>");
		RestInfo<RateCardDTO> info = new RestInfo<RateCardDTO>();
		try {
			info.setOperationCode(0);
			if(talent!=null && talent.equalsIgnoreCase("1.0")) {
				info.setData(loyaltyAccountService.createRateCardDetails(rateCardDTO));
			}
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			e.printStackTrace();
			logger.error("Exception :: " + e.getMessage());
		}
		return info;
	}
	
	@GetMapping("/{talent}/getAllRateCardDetails")
	public @ResponseBody RestListInfo<RateCardDTO> getAllRateCardDetails(HttpServletRequest httpServletRequest, @PathVariable String talent) {
		logger.info("LoyaltyAccountController>>>>>>>getAllRateCardDetails>>>>>>>");
		RestListInfo<RateCardDTO> info = new RestListInfo<RateCardDTO>();
		try {
			RateCardDTO rateCardDTO = new RateCardDTO();
			info.setOperationCode(0);
			if(talent!=null && talent.equalsIgnoreCase("1.0")) {
				info.setData(loyaltyAccountService.getAllRateCardDetails(rateCardDTO));
			}
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			e.printStackTrace();
			logger.error("Exception :: " + e.getMessage());
		}
		return info;
	}
	
	@PostMapping("/{talent}/updateRateCardDetails")
	public @ResponseBody RestInfo<RateCardDTO> updateRateCardDetails(HttpServletRequest httpServletRequest, @PathVariable String talent, @RequestBody RateCardDTO rateCardDTO) {
		logger.info("LoyaltyAccountController>>>>>>>updateRateCardDetails>>>>>>>");
		RestInfo<RateCardDTO> info = new RestInfo<RateCardDTO>();
		try {
			info.setOperationCode(0);
			if(talent!=null && talent.equalsIgnoreCase("1.0")) {
				info.setData(loyaltyAccountService.updateRateCardDetails(rateCardDTO));
			}
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			e.printStackTrace();
			logger.error("Exception :: " + e.getMessage());
		}
		return info;
	}
	
	@PostMapping("/{talent}/deleteRateCardDetails")
	public @ResponseBody RestInfo<RateCardDTO> deleteRateCardDetails(HttpServletRequest httpServletRequest, @PathVariable String talent, @RequestBody RateCardDTO rateCardDTO) {
		logger.info("LoyaltyAccountController>>>>>>>deleteRateCardDetails>>>>>>>");
		RestInfo<RateCardDTO> info = new RestInfo<RateCardDTO>();
		try {
			info.setOperationCode(0);
			if(talent!=null && talent.equalsIgnoreCase("1.0")) {
				info.setData(loyaltyAccountService.deleteRateCardDetails(rateCardDTO));
			}
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			e.printStackTrace();
			logger.error("Exception :: " + e.getMessage());
		}
		return info;
	}
	
}
