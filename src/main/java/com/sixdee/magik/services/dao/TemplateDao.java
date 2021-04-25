package com.sixdee.magik.services.dao;

import com.sixdee.magik.services.model.TemplateResponseTO;
import com.sixdee.magik.services.model.TemplateTO;

public interface TemplateDao {

	TemplateResponseTO saveTemplate(TemplateTO templateTO);

	TemplateResponseTO deleteTemplate(TemplateTO templateTO);

	TemplateResponseTO getTemplate(TemplateTO templateTO);

	TemplateResponseTO viewTemplate(TemplateTO templateTO);

}