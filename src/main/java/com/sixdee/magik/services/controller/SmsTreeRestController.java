/*package com.sixdee.magik.services.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sixdee.magik.services.model.SMSRequest;
import com.sixdee.magik.services.model.SMSResponse;
import com.sixdee.magik.services.service.SmsTreeService;
import com.sixdee.magik.services.util.MagikServicePath;


@RestController
public class SmsTreeRestController {
	
	static final Logger logger = Logger.getLogger(SmsTreeRestController.class);
	@Autowired
	private SmsTreeService smsTreeService;
	
	
	@PostMapping(value=MagikServicePath.GET_SMS_CATEGORY)
	public @ResponseBody SMSResponse getCategory(HttpServletRequest httpServletRequest,@RequestParam(value = "userId") int userId) {
		
		logger.info("SMS GET CATEGORY:: >> ");
		SMSResponse smsResp=new SMSResponse();
		try {
			smsResp.setCategory(smsTreeService.getCategory(userId));
			System.out.println("smsResp get Campaigns"+smsResp.getCategory().toString());
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		return smsResp;
	}
	
	@RequestMapping(value="/createCategory",method= RequestMethod.POST,consumes ={MediaType.APPLICATION_JSON_UTF8_VALUE},produces = "application/json; charset=utf-8")
	public @ResponseBody SMSResponse createCategory(HttpServletRequest httpServletRequest,@RequestBody SMSRequest Req){
		logger.info("Inside createCategory::");
		SMSResponse resp = new SMSResponse();
		try {
			resp = smsTreeService.createCategory(Req);
			System.out.println("SMS CREATE CATEGORY:: "+resp.toString());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return resp;
	}
	
	@RequestMapping(value="/editCategory",method= RequestMethod.POST,consumes ={MediaType.APPLICATION_JSON_UTF8_VALUE},produces = "application/json; charset=utf-8")
	public @ResponseBody SMSResponse editCategory(HttpServletRequest httpServletRequest,@RequestBody SMSRequest Req){
		logger.info("Inside editCategory::");
		SMSResponse resp = new SMSResponse();
		try {
			resp = smsTreeService.editCategory(Req);
			
			System.out.println("SMS MODIFY CATEGORY:: "+resp.toString());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return resp;
	}
	
	@RequestMapping(value="/delCategory",method= RequestMethod.POST,consumes ={MediaType.APPLICATION_JSON_UTF8_VALUE},produces = "application/json; charset=utf-8")
	public @ResponseBody SMSResponse delCategory(HttpServletRequest httpServletRequest,@RequestBody SMSRequest Req){
		logger.info("Inside delCategory::");
		SMSResponse resp = new SMSResponse();
		try {
			resp = smsTreeService.delCategory(Req);
			
			System.out.println("SMS DEL CATEGORY:: "+resp.toString());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return resp;
	}
	
	//MENU-->
	@RequestMapping(value="/getLanguage",method= RequestMethod.POST,consumes ={MediaType.APPLICATION_JSON_UTF8_VALUE},produces = "application/json; charset=utf-8")
	public @ResponseBody SMSResponse getLanguage(HttpServletRequest httpServletRequest,@RequestBody SMSRequest Req){
		logger.info("Inside getLanguage::");
		SMSResponse resp = new SMSResponse();
		try {
			resp=smsTreeService.getLanguage(Req);
			
			System.out.println("SMS getLanguage:: "+resp.toString());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return resp;
	}
	
	//=================menu
	
	@RequestMapping(value="/getMenuName",method= RequestMethod.POST,consumes ={MediaType.APPLICATION_JSON_UTF8_VALUE},produces = "application/json; charset=utf-8")
	public @ResponseBody SMSResponse getMenuName(HttpServletRequest httpServletRequest,@RequestBody SMSRequest Req){
		logger.info("Inside getMenuName::");
		SMSResponse resp = new SMSResponse();
		try {
			resp=smsTreeService.getMenuName(Req);
			
			System.out.println("SMS getMenuName:: "+resp.toString());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return resp;
	}
	
	@RequestMapping(value="/getMessage",method= RequestMethod.POST,consumes ={MediaType.APPLICATION_JSON_UTF8_VALUE},produces = "application/json; charset=utf-8")
	public @ResponseBody SMSResponse getMessage(HttpServletRequest httpServletRequest,@RequestBody SMSRequest Req){
		logger.info("Inside getMessage::");
		SMSResponse resp = new SMSResponse();
		try {
			resp=smsTreeService.getMessage(Req);
			
			System.out.println("SMS getMessage:: "+resp.toString());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return resp;
	}
	
	//-----------menu
	
	@RequestMapping(value="/createMessage",method= RequestMethod.POST,consumes ={MediaType.APPLICATION_JSON_UTF8_VALUE},produces = "application/json; charset=utf-8")
	public @ResponseBody SMSResponse createMessage(HttpServletRequest httpServletRequest,@RequestBody SMSRequest Req){
		logger.info("Inside createMenu::");
		SMSResponse resp = new SMSResponse();
		try {
			resp=smsTreeService.createMessage(Req);
			
			System.out.println("SMS createMessage:: "+resp.toString());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return resp;
	}
	
	
	@RequestMapping(value="/editMessage",method= RequestMethod.POST,consumes ={MediaType.APPLICATION_JSON_UTF8_VALUE},produces = "application/json; charset=utf-8")
	public @ResponseBody SMSResponse editMessage(HttpServletRequest httpServletRequest,@RequestBody SMSRequest Req){
		logger.info("Inside editMessage::");
		SMSResponse resp = new SMSResponse();
		try {
			resp=smsTreeService.editMessage(Req);
			
			System.out.println("SMS editMessage:: "+resp.toString());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return resp;
	}
	
	@RequestMapping(value="/delMenu",method= RequestMethod.POST,consumes ={MediaType.APPLICATION_JSON_UTF8_VALUE},produces = "application/json; charset=utf-8")
	public @ResponseBody SMSResponse delMenu(HttpServletRequest httpServletRequest,@RequestBody SMSRequest Req){
		logger.info("Inside delMenu::");
		SMSResponse resp = new SMSResponse();
		try {
			resp=smsTreeService.delMenu(Req);
			
			System.out.println("SMS delMenu:: "+resp.toString());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return resp;
	}
	
	//===============================previous=============================
	

	//@RequestMapping(value = "/getMenuName",method = RequestMethod.POST,consumes ={MediaType.APPLICATION_JSON_UTF8_VALUE},produces = "application/json; charset=utf-8")
	
	 * public @ResponseBody RestListInfo<SmsTreeDTO> getMenuName(HttpServletRequest
	 * httpServletRequest,@RequestBody SmsTreeDTO smsMenuTO) {
	 * logger.info("smsTree>>>>>>>>>>>>>>");
	 * 
	 * System.out.println("smsMenuTO campId"+smsMenuTO.campId);
	 * RestListInfo<SmsTreeDTO> info = new RestListInfo<SmsTreeDTO>(); try {
	 * 
	 * info.setData(smsTreeService.getMenuName(smsMenuTO));
	 * 
	 * } catch (Exception e) { ExceptionUtil.handle(e, info); e.printStackTrace();
	 * logger.error("Exception :: " + e.getMessage()); } return info; }
	 
	
	
	//@RequestMapping(value = "/createMessage",method = RequestMethod.POST,consumes ={MediaType.APPLICATION_JSON_UTF8_VALUE},produces = "application/json; charset=utf-8")
	
	 * public @ResponseBody RestInfo<SmsTreeDTO> createMessage(HttpServletRequest
	 * httpServletRequest,@RequestBody SmsTreeDTO smsMenuDTO){
	 * logger.info("careate massage"); RestInfo<SmsTreeDTO> info=new
	 * RestInfo<SmsTreeDTO>(); try {
	 * info.setData(smsTreeService.createMessage(smsMenuDTO)); }catch(Exception e){
	 * ExceptionUtil.handle(e, info); e.printStackTrace();
	 * logger.error("Exception :: " + e.getMessage()); } return info; }
	 
	

	//@RequestMapping(value = "/getMessage",method = RequestMethod.POST,consumes ={MediaType.APPLICATION_JSON_UTF8_VALUE},produces = "application/json; charset=utf-8")
	
	 * public @ResponseBody RestListInfo<SmsTreeDTO> getMessage(HttpServletRequest
	 * httpServletRequest,@RequestBody SmsTreeDTO smsMenuTO) {
	 * logger.info("getMessege>>>>>>>>>>>>>>");
	 * 
	 * System.out.println("getMessege"+smsMenuTO.campId); RestListInfo<SmsTreeDTO>
	 * info = new RestListInfo<SmsTreeDTO>(); try {
	 * 
	 * info.setData(smsTreeService.getMessege(smsMenuTO));
	 * 
	 * } catch (Exception e) { ExceptionUtil.handle(e, info); e.printStackTrace();
	 * logger.error("Exception :: " + e.getMessage()); } return info; }
	 
	
	//@RequestMapping(value = "/getLang",method = RequestMethod.POST,consumes ={MediaType.APPLICATION_JSON_UTF8_VALUE},produces = "application/json; charset=utf-8")
	
	 * public @ResponseBody RestListInfo<SmsTreeDTO> getLang(HttpServletRequest
	 * httpServletRequest) { logger.info("getLang>>>>>>>>>>>>>>");
	 * 
	 * RestListInfo<SmsTreeDTO> info = new RestListInfo<SmsTreeDTO>(); try {
	 * 
	 * info.setData(smsTreeService.getLang());
	 * 
	 * } catch (Exception e) { ExceptionUtil.handle(e, info); e.printStackTrace();
	 * logger.error("Exception :: " + e.getMessage()); } return info; }
	 
	
	
	//@RequestMapping(value = "/delMenu",method = RequestMethod.POST,consumes ={MediaType.APPLICATION_JSON_UTF8_VALUE})
	
	 * public @ResponseBody RestInfo<SmsTreeDTO> delMenu(HttpServletRequest
	 * httpServletRequest,@RequestBody SmsTreeDTO smsMenuDTO){
	 * logger.info("delMenu massage"); RestInfo<SmsTreeDTO> info=new
	 * RestInfo<SmsTreeDTO>(); try {
	 * info.setData(smsTreeService.delMenu(smsMenuDTO)); }catch(Exception e){
	 * ExceptionUtil.handle(e, info); e.printStackTrace();
	 * logger.error("Exception :: " + e.getMessage()); } return info; }
	 
	
	//@RequestMapping(value = "/editMessage",method = RequestMethod.POST,consumes ={MediaType.APPLICATION_JSON_UTF8_VALUE},produces = "application/json; charset=utf-8")
	public @ResponseBody RestInfo<SmsTreeDTO> editMessage(HttpServletRequest httpServletRequest,@RequestBody SmsTreeDTO smsMenuDTO){
		logger.info("editMessage massage");
		RestInfo<SmsTreeDTO> info=new RestInfo<SmsTreeDTO>();
		try {
			info.setData(smsTreeService.editMessage(smsMenuDTO));
		}catch(Exception e){
			ExceptionUtil.handle(e, info);
			e.printStackTrace();
			logger.error("Exception :: " + e.getMessage());
		}
		return info;
	}

}
*/