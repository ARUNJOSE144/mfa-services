package com.sixdee.magik.services.controller;


import static com.sixdee.magik.services.util.Globals.asJsonString;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sixdee.magik.services.model.UserDetails;
import com.sixdee.magik.services.service.impl.UserServiceImpl;
import com.sixdee.magik.services.util.CommonStringUtils;
import com.sixdee.magik.services.util.ExceptionUtil;
import com.sixdee.magik.services.util.RestInfo;
import com.sixdee.magik.services.util.RestListInfo;

/**
 * @author Rajesh
 * @Date : Sep, 2020
 *
 */
@RequestMapping(value = "/user")
@RestController
public class UserController {

	static final Logger logger = Logger.getLogger(UserController.class);

	@Autowired
	UserServiceImpl userServiceImpl;
	
	
	
	@PostMapping("/v1/create")
	public @ResponseBody RestInfo<String> createUser(@RequestBody UserDetails request) {

	
		logger.info("==================  UserController Controllar API Start =====================");
		logger.info("Class : UserController | Method : createUser");
		RestInfo<String> info = new RestInfo<String>();
		try {
			userServiceImpl.create(request, 0, "1");
			info.setOperationCode(0);
		}
//		catch(MySQLIntegrityConstraintViolationException de)
//		{
//			ExceptionUtil.handle(de, info);
//			logger.error("Class : UserController  |  Method : createUser " + de);
//			de.printStackTrace();
//		}
		catch (Exception e) 
		{
			System.err.println(e.toString());
			System.err.println(e.getMessage());
			if(e.getMessage().contains("Duplicate entry"))
			{
				String msg =CommonStringUtils.afterStringUtils(e.getMessage(),"jdbc4.MySQLIntegrityConstraintViolationException:");
				String errorCause =CommonStringUtils.beforeStringUtils(msg,"for key");
				
				System.err.println("------------Duplicate entry-----------------------------"+errorCause);
				info.setOperationCode(999);
				info.setMessage(errorCause);
			}
			else
			{	
				//info.setOperationCode(1);
				ExceptionUtil.handle(e, info);
			}
			
			logger.error("Class : UserController  |  Method : createUser " + e);
			e.printStackTrace();
		}
		logger.info("================== UserController Rest Controllar API End =====================");
		return info;
	}
	 
	
	@PostMapping("/v1/getUsers")
	public @ResponseBody RestListInfo<UserDetails> getUsers(@RequestParam(value = "userId") Integer loginUserId) {

	
		logger.info("==================  UserController Controllar API Start =====================");
		logger.info("Class : UserController | Method : getUsers");
		RestListInfo<UserDetails> info = new RestListInfo<UserDetails> ();
		try {
			info.setData(userServiceImpl.getUserDetails(loginUserId));
			info.setOperationCode(0);
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : UserController  |  Method : getUsers " + e);
			e.printStackTrace();
		}
		logger.info("================== UserController Rest Controllar API End =====================");
		return info;
	}
	
	@RequestMapping(value = "/v1/update", method = RequestMethod.POST)
	public  RestInfo<String>  update(@RequestBody final UserDetails request,
			@RequestAttribute(value = "UserId",required=false) String origUserId, HttpServletRequest httpRequest) {

		Date startTime = new Date();
		if (logger.isInfoEnabled()) {
			logger.info("UserController : update : Request : " + asJsonString(request));
		}
		RestInfo<String> info = null;
		try {
			info=new RestInfo<String>();
			userServiceImpl.update(request, origUserId);

			if (logger.isInfoEnabled()) {
				logger.info("UserController : update : Response: " + asJsonString(info));
			}
		} catch (Exception e) {
			info.setOperationCode(1);
			logger.error("Some error :" + e.getMessage());
			throw e;
		} finally {
		/*	audit.save(origUserId, "USER_UPDATE", httpRequest,
					(response != null && response.getResultCode() != null
							&& response.getResultCode().equals(StatusCode.SUCCESS)) ? "0" : "1",
					true, startTime);*/
		}
		return info;
	}
	
	
	
	@RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
	public RestInfo<String> deleteUser(HttpServletRequest httpRequest) {

		Date startTime = new Date();
		 RestInfo<String> info=new  RestInfo<String>();
		 String userId=httpRequest.getParameter("userID");
		if (logger.isInfoEnabled()) {
			logger.info("UserController : delete : Request : userId: " + userId);
		}
		try {
			userServiceImpl.delete(userId,"");
			info.setOperationCode(0);


			if (logger.isInfoEnabled()) {
				logger.info("UserController : delete : Response: " + asJsonString(info));
			}
		} catch (Exception e) {
			info.setOperationCode(1);
			logger.error("Some error :" + e.getMessage());
			throw e;
		} finally {
		}
		return info;
	}
	
	
	@RequestMapping(value = "/v1/delete", method = RequestMethod.DELETE)
	public RestInfo<String> delete(@RequestParam(value = "userId") String userId,
			@RequestAttribute(value = "UserId") String origUserId, HttpServletRequest httpRequest) {

		Date startTime = new Date();
		 RestInfo<String> info=new  RestInfo<String>();
		if (logger.isInfoEnabled()) {
			logger.info("UserController : delete : Request : userId: " + userId);
		}
		try {
			userServiceImpl.delete(userId, origUserId);


			if (logger.isInfoEnabled()) {
				logger.info("UserController : delete : Response: " + asJsonString(info));
			}
		} catch (Exception e) {
			logger.error("Some error :" + e.getMessage());
			throw e;
		} finally {
			/*audit.save(origUserId, "USER_DELETE", httpRequest,
					(response != null && response.getResultCode() != null
							&& response.getResultCode().equals(StatusCode.SUCCESS)) ? "0" : "1",
					true, startTime);*/
		}
		return info;
	}
	
	@RequestMapping(value = "/v1/view", method = RequestMethod.GET)
	public RestInfo<UserDetails> view(@RequestParam(value = "userId") String userId,
			@RequestAttribute(value = "UserId",required=false) String origUserId, HttpServletRequest httpRequest) {

		Date startTime = new Date();
		if (logger.isInfoEnabled()) {
			logger.info("UserController : view : Request : userId: " + userId);
		}
		 RestInfo<UserDetails> info =new  RestInfo<UserDetails>();
		UserDetails userDetails = null;
		try {
			userDetails = userServiceImpl.view(origUserId, userId);
			info.setData(userDetails);

			if (logger.isInfoEnabled()) {
				logger.info("UserController : view : Response: " + asJsonString(userDetails));
			}
		} catch (Exception e) {
			logger.error("Some error :" + e.getMessage());
			throw e;
		} finally {

			/*audit.save(origUserId, "USER_VIEW", httpRequest,
					(response != null && response.getResultCode() != null
							&& response.getResultCode().equals(StatusCode.SUCCESS)) ? "0" : "1",
					true, startTime);*/
		}
		return info;
	}
	
}
