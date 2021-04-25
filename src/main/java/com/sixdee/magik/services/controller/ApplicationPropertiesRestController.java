package com.sixdee.magik.services.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sixdee.magik.services.model.ApplicationPropertyTO;
import com.sixdee.magik.services.service.ApplicationPropertiesService;
import com.sixdee.magik.services.util.ExceptionUtil;
import com.sixdee.magik.services.util.MagikServicePath;
import com.sixdee.magik.services.util.RestInfo;
import com.sixdee.magik.services.util.RestListInfo;

/**
 * @author Rajesh
 * @Date : January, 2019
 *
 */

@RestController
public class ApplicationPropertiesRestController {

	static final Logger logger = Logger.getLogger(ApplicationPropertiesRestController.class);

	@Autowired
	ApplicationPropertiesService applicationPropertiesService;
	
	
	@GetMapping(MagikServicePath.GET_APPLICATION_PROPERTIES)
	public @ResponseBody RestListInfo<ApplicationPropertyTO> getApplicationProperties(HttpServletRequest httpServletRequest) {

	
		logger.info("==================  ApplicationPropertiesRest Controllar API Start =====================");
		logger.info("Class : ApplicationPropertiesRestControllar | Method : getApplicationProperties");
		RestListInfo<ApplicationPropertyTO> info = new RestListInfo<ApplicationPropertyTO>();
		try {
			info.setData(applicationPropertiesService.getApplicationProperties());
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : ApplicationPropertiesRestControllar  |  Method : getApplicationProperties " + e);
			e.printStackTrace();
		}
		logger.info("================== ApplicationProperties Rest Controllar API End =====================");
		return info;
	}
	
	@GetMapping(MagikServicePath.CHANGE_PROPERTY_STATUS)
	public @ResponseBody RestInfo<String> changePropertyStatus(HttpServletRequest httpServletRequest) {

	
		logger.info("==================  ApplicationPropertiesRest Controllar API Start =====================");
		logger.info("Class : ApplicationPropertiesRestControllar | Method : changePropertyStatus");
		ApplicationPropertyTO propertyTO=new ApplicationPropertyTO();
		if(httpServletRequest.getParameter("propertyId")!=null && !httpServletRequest.getParameter("propertyId").trim().equalsIgnoreCase("") && httpServletRequest.getParameter("propertyId")!="undefined")
			propertyTO.setId(Integer.parseInt(httpServletRequest.getParameter("propertyId")));
		
		if(httpServletRequest.getParameter("status")!=null && !httpServletRequest.getParameter("status").trim().equalsIgnoreCase("") && httpServletRequest.getParameter("status")!="undefined")
			propertyTO.setStatus(Integer.parseInt(httpServletRequest.getParameter("status")));
		RestInfo<String> info = new RestInfo<String>();
		try {
			info.setData(applicationPropertiesService.changePropertyStatus(propertyTO));
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : ApplicationPropertiesRestControllar  |  Method : changePropertyStatus " + e);
			e.printStackTrace();
		}
		logger.info("================== ApplicationProperties Rest Controllar API End =====================");
		return info;
	}
//	@PostMapping(MagikServicePath.UPLOAD_BLACKLIST_FILE)
//	public @ResponseBody RestInfo<String> uploadBlackList(HttpServletRequest httpServletRequest ,@RequestParam("file") MultipartFile file) {
//
//		System.out.println("Came 111111111111111111111111111111");
//	
//		logger.info("==================  ApplicationPropertiesRest Controllar API Start =====================");
//		logger.info("Class : ApplicationPropertiesRestControllar | Method : changePropertyStatus");
//		ApplicationPropertyTO propertyTO=new ApplicationPropertyTO();
//		
//		System.out.println("Came to upload blacklist file...................");
////		if(httpServletRequest.getParameter("propertyId")!=null && !httpServletRequest.getParameter("propertyId").trim().equalsIgnoreCase("") && httpServletRequest.getParameter("propertyId")!="undefined")
////			propertyTO.setId(Integer.parseInt(httpServletRequest.getParameter("propertyId")));
////		
////		if(httpServletRequest.getParameter("status")!=null && !httpServletRequest.getParameter("status").trim().equalsIgnoreCase("") && httpServletRequest.getParameter("status")!="undefined")
////			propertyTO.setStatus(Integer.parseInt(httpServletRequest.getParameter("status")));
//		RestInfo<String> info = new RestInfo<String>();
////		try {
////			info.setData(applicationPropertiesService.changePropertyStatus(propertyTO));
////		} catch (Exception e) {
////			ExceptionUtil.handle(e, info);
////			logger.error("Class : ApplicationPropertiesRestControllar  |  Method : changePropertyStatus " + e);
////			e.printStackTrace();
////		}
//		logger.info("================== ApplicationProperties Rest Controllar API End =====================");
//		return info;
//	}
	@RequestMapping(value = "/uploadBlackListFile", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> uploadFile(
	    @RequestParam("file") MultipartFile file) {

	  try {
	    System.out.println("Came to file upload....................");
	    // ...
	  }
	  catch (Exception e) {
	    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	  }

	  return new ResponseEntity<>(HttpStatus.OK);
	}
}
