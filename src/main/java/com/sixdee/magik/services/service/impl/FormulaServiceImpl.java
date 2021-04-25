
package com.sixdee.magik.services.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sixdee.magik.services.dao.FormulaDao;
import com.sixdee.magik.services.model.FormulaTO;
import com.sixdee.magik.services.model.FunctionMetricsTO;
import com.sixdee.magik.services.service.FormulaService;

/**
 * @author arun.jose
 * @Date : September, 2018
 *
 */

@Service
@Transactional
public class FormulaServiceImpl implements FormulaService {

	@Autowired
	FormulaDao formulaDao;

	@Override
	public String saveFormula(FormulaTO formulaTO) {
		// TODO Auto-generated method stub
		return formulaDao.saveFormula(formulaTO);

	}

	@Override
	public List<FormulaTO> getFormulaList(FormulaTO formulaTO) {
		// TODO Auto-generated method stub
		return formulaDao.getFormulaList(formulaTO);
	}

	@Override
	public FormulaTO getFormula(FormulaTO formulaTO) {
		// TODO Auto-generated method stub
		return formulaDao.getFormula(formulaTO);
	}

	@Override
	public String deleteFormula(FormulaTO formulaTO) {
		// TODO Auto-generated method stub
		return formulaDao.deleteFormula(formulaTO);
		
	}

}
