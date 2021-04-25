package com.sixdee.magik.services.dao;

import java.util.List;

import com.sixdee.magik.services.model.LanguageTO;

/**
 * @author ramesh.cheerla
 * @Date : September, 2018
 *
 */

public interface LanguageDao {

	// User validation
	public List<LanguageTO> getLanguages();

}
