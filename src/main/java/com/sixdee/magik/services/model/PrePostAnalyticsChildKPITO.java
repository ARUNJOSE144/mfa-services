package com.sixdee.magik.services.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * @author Rajesh
 * @Date : August, 2019
 *
 */

@Entity
@Table(name = "PRE_POST_CHILD_KPIS")
@SuppressWarnings("serial")
public class PrePostAnalyticsChildKPITO implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "PrePostAnalyticsChildKPITO")
	@TableGenerator(name = "PrePostAnalyticsChildKPITO", allocationSize = 1)
	@Column(name = "ID")
	private int id;

	@Column(name = "KPI")
	private String kpi;
	
	@Column(name = "PARENT_ID")
	private int parentId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getKpi() {
		return kpi;
	}

	public void setKpi(String kpi) {
		this.kpi = kpi;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	
	
	

	
}
