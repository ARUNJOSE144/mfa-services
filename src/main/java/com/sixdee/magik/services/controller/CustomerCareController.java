package com.sixdee.magik.services.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.api.client.json.Json;
import com.sixdee.magik.services.model.CustomerCareDTO;
import com.sixdee.magik.services.model.CustomerCareHeaders;

import com.sixdee.magik.services.model.CustomerProfileHeaders;
import com.sixdee.magik.services.service.CustomerCareService;
import com.sixdee.magik.services.util.ExceptionUtil;
import com.sixdee.magik.services.util.RestListInfo;

@RestController
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class CustomerCareController {

	static final Logger logger = Logger.getLogger(CustomerCareController.class);

	@Autowired
	private CustomerCareService customerCareService;

	@PostMapping(value = "/getCustomerCareDetails")

	public @ResponseBody RestListInfo<String> getDashboardDetails(
			@RequestBody CustomerCareHeaders customerCareHeaders) {
		RestListInfo<String> info = new RestListInfo<String>();
		List<String> list = null;

		try {

			String resp = customerCareService.getDashboardDetails(customerCareHeaders);

			System.out.println(resp);
			info.setOperationCode(0);

			list = new ArrayList<String>();
			list.add(resp);
			info.setData(list);
			System.err.println("Response:"+list);

		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			e.printStackTrace();
			logger.error("Exception :: " + e.getMessage());
		}
		return info;
	}

	@PostMapping(value = "/getCustomerCareDetailsOld")

	public @ResponseBody RestListInfo<CustomerCareDTO> getDashboardDetailsOld(HttpServletRequest httpServletRequest,
			@RequestBody CustomerCareDTO customerCareDTO) {
		RestListInfo<CustomerCareDTO> info = new RestListInfo<CustomerCareDTO>();
		List<CustomerCareDTO> list=null;
		try {
			info.setOperationCode(0);
			list=customerCareService.getDashboardDetailsOld(customerCareDTO);
			info.setData(list);
			System.err.println("Response_Insigth:"+list);
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			e.printStackTrace();
			logger.error("Exception :: " + e.getMessage());
		}
		return info;
	}

	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper().disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
	}
}
