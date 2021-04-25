package com.sixdee.magik.services.model;
/**
 * @author amal.a.s
 * @Date : April, 2019
 *
 */

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

@Entity
@Table(name="LMS_PACKAGE_CATEGORY_MASTER")
public class LmsPackageCategoryMasterDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "LmsPackageCategoryMasterDTO")
	@TableGenerator(name = "LmsPackageCategoryMasterDTO", allocationSize = 1)
	@Column(name = "CAT_ID")
	private int categoryId;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "categoryMasterDTO")
	private List<LmsPackageCategoryChildDTO> packageCategories;
	
	@Transient
	private String statusCode;
	@Transient
	private String statusDescription;
	@Transient
	private int limit;
	@Transient
	private int offset;
	@Transient
	private int totalCount;
	
	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public List<LmsPackageCategoryChildDTO> getPackageCategories() {
		return packageCategories;
	}

	public void setPackageCategories(List<LmsPackageCategoryChildDTO> packageCategories) {
		this.packageCategories = packageCategories;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusDescription() {
		return statusDescription;
	}

	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
	
	
}
