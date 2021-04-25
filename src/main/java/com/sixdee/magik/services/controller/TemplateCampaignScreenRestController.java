package com.sixdee.magik.services.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sixdee.magik.services.model.TemplateResponseTO;
import com.sixdee.magik.services.model.TemplateTO;
import com.sixdee.magik.services.service.TemplateService;
import com.sixdee.magik.services.util.MagikServicePath;

/**
 * 
 * @author Anees CK
 * @Date : June, 2020
 *
 */
@RestController
public class TemplateCampaignScreenRestController {

	
	static final Logger logger = Logger.getLogger(TemplateCampaignScreenRestController.class);
	

	@Autowired
	TemplateService  templateService;
		
	@PostMapping(MagikServicePath.TEMPLATE_SERVLET)
	public @ResponseBody TemplateResponseTO templateServlet(@RequestBody TemplateTO templateTO) {
		logger.info("================== TemplateCampaignScreenRestController:"+templateTO.getKeyword());
		TemplateResponseTO response = null;
		if(templateTO.getKeyword().equalsIgnoreCase("CreateTemplate")) {
			 response = templateService.saveTemplate(templateTO);
		} else if(templateTO.getKeyword().equalsIgnoreCase("DeleteTemplate")) {
			response = templateService.deleteTemplate(templateTO);
		} else if(templateTO.getKeyword().equalsIgnoreCase("GetTemplate")) {
			response = templateService.getTemplate(templateTO);
		} else if(templateTO.getKeyword().equalsIgnoreCase("EditTemplate")) {
			response = templateService.saveTemplate(templateTO);
		} else if(templateTO.getKeyword().equalsIgnoreCase("ViewTemplate")) {
			response = templateService.viewTemplate(templateTO);
		}
		return response;
	}
}
