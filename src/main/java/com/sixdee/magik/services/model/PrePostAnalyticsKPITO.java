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
 * @author Nakhil Kurian
 * @Date : August, 2020
 *
 */

@Entity
@Table(name = "PRE_POST_ANALYTICS_KPIS")
@SuppressWarnings("serial")
public class PrePostAnalyticsKPITO implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "PrePostAnalyticsKPITO")
	@TableGenerator(name = "PrePostAnalyticsKPITO", allocationSize = 1)
	@Column(name = "ID")
	private int id;

	@Column(name = "KPI")
	private String kpi;

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

}
