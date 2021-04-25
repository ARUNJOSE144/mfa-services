package com.sixdee.magik.services.dao;

import java.util.List;

import com.sixdee.magik.services.model.FormulaTO;

/**
 * @author arun.jose
 * @Date : September, 2018
 *
 */

public interface FormulaDao {

	public String saveFormula(FormulaTO formulaTO);

	public List<FormulaTO> getFormulaList(FormulaTO formulaTO);

	public FormulaTO getFormula(FormulaTO formulaTO);

	public String deleteFormula(FormulaTO formulaTO);

}
