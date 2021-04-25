package com.sixdee.magik.services.model;

import javax.persistence.OneToOne;

 
public class PackageCategoryTO {
	
	
	
	private int autoId;
	private String categoryName;
	private String subCategoryName;
	private String categoryDescription;
	private String categorySynonym;
	private String isPackage;
	
	
	
	
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getSubCategoryName() {
		return subCategoryName;
	}
	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
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
	public String getIsPackage() {
		return isPackage;
	}
	public void setIsPackage(String isPackage) {
		this.isPackage = isPackage;
	}
	
	public int getAutoId() {
		return autoId;
	}
	public void setAutoId(int autoId) {
		this.autoId = autoId;
	}
	
	
	
}
