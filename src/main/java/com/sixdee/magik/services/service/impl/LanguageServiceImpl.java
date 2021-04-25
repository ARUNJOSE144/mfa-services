
package com.sixdee.magik.services.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sixdee.magik.services.dao.LanguageDao;
import com.sixdee.magik.services.model.LanguageTO;
import com.sixdee.magik.services.service.LanguageService;

/**
 * @author ramesh.cheerla
 * @Date : September, 2018
 *
 */

@Service
@Transactional
public class LanguageServiceImpl implements LanguageService {

	@Autowired
	LanguageDao languageDao;

	@Override
	public List<LanguageTO> getLanguages() {
		return languageDao.getLanguages();
	}

}
