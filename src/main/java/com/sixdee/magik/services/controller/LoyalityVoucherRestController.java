package com.sixdee.magik.services.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sixdee.magik.services.model.ConfigureCategoryTO;
import com.sixdee.magik.services.model.RoleMaster;
import com.sixdee.magik.services.model.UploadVoucherTO;
import com.sixdee.magik.services.model.VoucherAssigningTO;
import com.sixdee.magik.services.model.VoucherGenerationTO;
import com.sixdee.magik.services.service.VoucherService;
import com.sixdee.magik.services.util.ExceptionUtil;
import com.sixdee.magik.services.util.MagikServicePath;
import com.sixdee.magik.services.util.RestInfo;
import com.sixdee.magik.services.util.RestListInfo;

/**
 * @author ABHIRAM MACHIRAJU
 * @Date : January, 2021
 */

@RestController
public class LoyalityVoucherRestController {
	
	@Autowired
	VoucherService voucherService;
	
	static final Logger logger = Logger.getLogger(LoyalityVoucherRestController.class);

	
/*******************************Start of VoucherGenerationMethods***************************/
	@GetMapping(MagikServicePath.GET_VOUCHER_GEN_DTLS)
	public @ResponseBody RestListInfo<VoucherGenerationTO> getVoucherList(HttpServletRequest httpServletRequest) {

		logger.info("Class : LoyalityVoucherRestController | Method : getVoucherList");

		RestListInfo<VoucherGenerationTO> resp = new RestListInfo<VoucherGenerationTO>();
		try {

			resp.setData(voucherService.getVoucherGenerationList());
			resp.setOperationCode(0);
			resp.setMessage("Success");
		} catch (Exception e) {
			resp.setOperationCode(1);
			ExceptionUtil.handle(e, resp);
			logger.error("Class : LoyalityVoucherRestController  |  Method : getVoucherList " + e);
			e.printStackTrace();
		}

		return resp;
	}
	
	@PostMapping(MagikServicePath.GENERATE_VOUCHER)
	public  @ResponseBody RestInfo generateVoucher(HttpServletRequest httpServletRequest, @RequestBody VoucherGenerationTO voucherGenerationto) {
		
		logger.info("Class : LoyalityVoucherRestController | Method : generateVoucher");

		RestInfo resp = new RestInfo<>();
		try {
			voucherService.generateVoucher(voucherGenerationto);
			resp.setOperationCode(0);
			resp.setMessage("Success");
		} 
		catch (Exception e) {
					
				resp.setOperationCode(1);
				ExceptionUtil.handle(e, resp);
			logger.error("Class : LoyalityVoucherRestController  |  Method : generateVoucher " + e);
			e.printStackTrace();
		}
		return resp;
	}
	/*******************************End of VoucherGenerationMethods***************************/	
	
	
	
	
	/*******************************Start of VoucherAssignMethods***************************/		
	
	
	
	@GetMapping(MagikServicePath.GET_VOUCHER_ASSIGN_DTLS)
	public @ResponseBody RestListInfo<VoucherAssigningTO> getVoucherAssignList(HttpServletRequest httpServletRequest) {

		logger.info("Class : LoyalityVoucherRestController | Method : getVoucherAssignList");

		RestListInfo<VoucherAssigningTO> resp = new RestListInfo<VoucherAssigningTO>();
		try {

			resp.setData(voucherService.getVoucherAssigningList());
			resp.setOperationCode(0);
			resp.setMessage("Success");
		} catch (Exception e) {
			resp.setOperationCode(1);
			ExceptionUtil.handle(e, resp);
			logger.error("Class : LoyalityVoucherRestController  |  Method : getVoucherAssignList " + e);
			e.printStackTrace();
		}

		return resp;
	}
	
	
	@PostMapping(MagikServicePath.SAVE_VOUCHER_ASSIGN)
	public  @ResponseBody RestInfo saveVoucherAssign(HttpServletRequest httpServletRequest, @RequestBody VoucherAssigningTO voucherAssigningTO) {
		
		logger.info("Class : LoyalityVoucherRestController | Method : saveVoucherAssign");

		RestInfo resp = new RestInfo<>();
		try {
			voucherService.assignVoucher(voucherAssigningTO);
			resp.setOperationCode(0);
			resp.setMessage("Success");
		} 
		catch (Exception e) {
					
				resp.setOperationCode(1);
				ExceptionUtil.handle(e, resp);
			logger.error("Class : LoyalityVoucherRestController  |  Method : saveVoucherAssign " + e);
			e.printStackTrace();
		}
		return resp;
	}
	
	/*******************************End of VoucherAssignMethods***************************/		
	
	
	
	/*******************************Start of VoucherUploadMethods***************************/		
	
	@GetMapping(MagikServicePath.GET_VOUCHER_UPLOAD_DTLS)
	public @ResponseBody RestListInfo<UploadVoucherTO> getVoucherUploadList(HttpServletRequest httpServletRequest) {

		logger.info("Class : LoyalityVoucherRestController | Method : getVoucherUploadList");

		RestListInfo<UploadVoucherTO> resp = new RestListInfo<UploadVoucherTO>();
		try {

			resp.setData(voucherService.getUploadVoucherList());
			resp.setOperationCode(0);
			resp.setMessage("Success");
		} catch (Exception e) {
			resp.setOperationCode(1);
			ExceptionUtil.handle(e, resp);
			logger.error("Class : LoyalityVoucherRestController  |  Method : getVoucherUploadList " + e);
			e.printStackTrace();
		}

		return resp;
	}
	
	
	@PostMapping(MagikServicePath.SAVE_VOUCHERUPLOAD)
	public  @ResponseBody RestInfo saveUploadVoucher(HttpServletRequest httpServletRequest, @RequestBody UploadVoucherTO uploadvoucherto) {
		
		logger.info("Class : LoyalityVoucherRestController | Method : saveUploadVoucher");

		RestInfo resp = new RestInfo<>();
		try {
			voucherService.saveUploadVoucher(uploadvoucherto);
			resp.setOperationCode(0);
			resp.setMessage("Success");
		} 
		catch (Exception e) {
					
				resp.setOperationCode(1);
				ExceptionUtil.handle(e, resp);
			logger.error("Class : LoyalityVoucherRestController  |  Method : saveUploadVoucher " + e);
			e.printStackTrace();
		}
		return resp;
	}
	
	
	
	/*******************************End of VoucherUploadMethods***************************/	
	
	
	/*******************************Start of ConfigureCategory Loyality***************************/
	
	@GetMapping(MagikServicePath.GET_CATEGORY_DTLS)
	public @ResponseBody RestListInfo<ConfigureCategoryTO> getLoyalityCategoryList(HttpServletRequest httpServletRequest) {

		logger.info("Class : LoyalityVoucherRestController | Method : getLoyalityCategoryList");

		RestListInfo<ConfigureCategoryTO> resp = new RestListInfo<ConfigureCategoryTO>();
		try {

			resp.setData(voucherService.getLoyalityCategoryList());
			resp.setOperationCode(0);
			resp.setMessage("Success");
		} catch (Exception e) {
			resp.setOperationCode(1);
			ExceptionUtil.handle(e, resp);
			logger.error("Class : LoyalityVoucherRestController  |  Method : getLoyalityCategoryList " + e);
			e.printStackTrace();
		}

		return resp;
	}
	
	
	@PostMapping(MagikServicePath.SAVE_CATEGORY_DTLS)
	public  @ResponseBody RestInfo saveLoyalityCategory(HttpServletRequest httpServletRequest, @RequestBody ConfigureCategoryTO configurecategoryto) {
		
		logger.info("Class : LoyalityVoucherRestController | Method : saveLoyalityCategory");

		RestInfo resp = new RestInfo<>();
		try {
			voucherService.saveLoyalityCategoryList(configurecategoryto);
			resp.setOperationCode(0);
			resp.setMessage("Success");
		} 
		catch (Exception e) {
					
				resp.setOperationCode(1);
				ExceptionUtil.handle(e, resp);
			logger.error("Class : LoyalityVoucherRestController  |  Method : saveLoyalityCategory " + e);
			e.printStackTrace();
		}
		return resp;
	}
	
	@PostMapping(MagikServicePath.DELETE_CATEGORY_DTLS)
	public @ResponseBody RestInfo<ConfigureCategoryTO> deleteCategory(HttpServletRequest httpServletRequest,
			@RequestBody ConfigureCategoryTO configurecategoryto) {

		logger.info("Class : LoyalityVoucherRestController | Method : deleteCategory");

		RestInfo<ConfigureCategoryTO> resp = new RestInfo<ConfigureCategoryTO>();
		try {

			voucherService.deleteCategory(configurecategoryto);
			resp.setOperationCode(0);
			resp.setMessage("Success");
		} catch (Exception e) {
			resp.setOperationCode(1);
			ExceptionUtil.handle(e, resp);
			logger.error("Class : LoyalityVoucherRestController  |  Method : deleteCategory " + e);
			e.printStackTrace();
		}

		return resp;
	}
	
	
	
	/*******************************End of ConfigureCategory Loyality***************************/
	
	
	

}
