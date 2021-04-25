package com.sixdee.magik.services.model;
/**
 * @author amal.a.s
 * @Date : April, 2019
 *
 */

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

@Entity
@Table(name = "LMS_PACKAGE_CATEGORY_CHILD")
public class LmsPackageCategoryChildDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "LmsPackageCategoryChildDTO")
	@TableGenerator(name = "LmsPackageCategoryChildDTO", allocationSize = 1)
	@Column(name = "ID")
	private int id;

	@ManyToOne
	@JoinColumn(name = "CAT_ID", nullable = false)
	private LmsPackageCategoryMasterDTO categoryMasterDTO;

	@Column(name = "LANG_ID")
	private int langId;

	@Column(name = "CAT_NAME")
	private String categoryName;

	@Column(name = "CAT_DESCRIPTION")
	private String categoryDescription;

	@Column(name = "CAT_SYNONYM")
	private String categorySynonym;
	
	@Transient
	private int categoryId;

	public LmsPackageCategoryChildDTO() {
	}

	public LmsPackageCategoryChildDTO(LmsPackageCategoryMasterDTO categoryMasterDTO, int langId, String categoryName,
			String categoryDescription, String categorySynonym, int id) {
		
		this.id = id;
		this.categoryMasterDTO = categoryMasterDTO;
		this.langId = langId;
		this.categoryName = categoryName;
		this.categoryDescription = categoryDescription;
		this.categorySynonym = categorySynonym;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LmsPackageCategoryMasterDTO getCategoryMasterDTO() {
		return categoryMasterDTO;
	}

	public void setCategoryMasterDTO(LmsPackageCategoryMasterDTO categoryMasterDTO) {
		this.categoryMasterDTO = categoryMasterDTO;
	}

	public int getLangId() {
		return langId;
	}

	public void setLangId(int langId) {
		this.langId = langId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryDescription() {
		return categoryDescription;
	}

	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}

	public String getCategorySynonym() {
		return categorySynonym;
	}

	public void setCategorySynonym(String categorySynonym) {
		this.categorySynonym = categorySynonym;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	
	

}
