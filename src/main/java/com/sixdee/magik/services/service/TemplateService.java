package com.sixdee.magik.services.service;

import com.sixdee.magik.services.model.TemplateResponseTO;
import com.sixdee.magik.services.model.TemplateTO;

public interface TemplateService {

	
	public TemplateResponseTO  saveTemplate(TemplateTO  templateTO);

	public TemplateResponseTO deleteTemplate(TemplateTO templateData);

	public TemplateResponseTO getTemplate(TemplateTO templateTO);

	public TemplateResponseTO viewTemplate(TemplateTO templateTO);
	
}
