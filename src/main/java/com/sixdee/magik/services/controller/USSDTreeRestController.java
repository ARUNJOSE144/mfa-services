/*package com.sixdee.magik.services.controller;


*//**
 * @author davis.nayak
 * @Date : June, 2020
 *
 *//*

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;



import com.sixdee.magik.services.model.USSDRequest;
import com.sixdee.magik.services.model.USSDResponse;
import com.sixdee.magik.services.service.USSDTreeService;

@RestController
public class USSDTreeRestController {

	static final Logger logger = Logger.getLogger(USSDTreeRestController.class);

	@Autowired
	USSDTreeService ussdTreeService;

	
	  @RequestMapping(value="/USSDProcessing", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	  public @ResponseBody USSDResponse processUSSD(@RequestBody USSDRequest
	  request) {
	  
		  logger.info("******UserID inside the controller class******   ::"+request.getUserId());
		  
	   return (USSDResponse) ussdTreeService.processRequest(request);
	  
	  }
	 

}
*/