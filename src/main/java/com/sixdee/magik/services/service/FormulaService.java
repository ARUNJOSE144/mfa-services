package com.sixdee.magik.services.service;

import java.util.List;

import com.sixdee.magik.services.model.FormulaTO;

/**
 * @author arun.jose
 * @Date : November, 2018
 *
 */

public interface FormulaService {

	public String saveFormula(FormulaTO formulaTO);

	public List<FormulaTO> getFormulaList(FormulaTO formulaTO);

	public FormulaTO getFormula(FormulaTO formulaTO);

	public String deleteFormula(FormulaTO formulaTO);

}
