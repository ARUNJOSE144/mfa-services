package com.sixdee.magik.services.util;

import org.apache.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;

import com.sixdee.magik.services.dao.impl.CacheDaoImpl;



/**
 * @author ramesh.cheerla
 * @Date : September, 2018
 *
 */

public class ExceptionUtil {

	static Logger logger = Logger.getLogger(ExceptionUtil.class);

	public static void handle(Exception e, RestInfo<?> restInfo) {
		if (e instanceof DataIntegrityViolationException) {
			DataIntegrityViolationException dae = (DataIntegrityViolationException) e;
			restInfo.setOperationCode(Integer.parseInt("155"));
			restInfo.setMessage(CacheDaoImpl.messages.get(1));
		} 
		else if (e instanceof ConstraintViolationException) {
			ConstraintViolationException cve = (ConstraintViolationException) e;
			restInfo.setOperationCode(cve.getErrorCode());
			restInfo.setMessage(CacheDaoImpl.messages.get(1));
		} 
		else {
			logger.fatal("Application process error. Please try agin");
			restInfo.setOperationCode(666);
			restInfo.setMessage(CacheDaoImpl.messages.get(1));
		}
	}

	public static void handle(Exception e, RestListInfo<?> restInfo) {
		if (e instanceof ValidationException) {
			ValidationException ve = (ValidationException) e;
			restInfo.setMessage(ve.getMessage());
			restInfo.setOperationCode(ve.getErrorCode());
		}
	}
}
