package com.sixdee.magik.services.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;


@Entity
@Table(name = "ARPU_BANDS")
@SuppressWarnings("serial")
public class ARPUBandsTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ARPUBandsTO")
	@TableGenerator(name = "ARPUBandsTO", allocationSize = 1)
	@Column(name = "ID")
	private int id;

	@Column(name = "ARPU_BAND")
	private String arpuBands;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getArpuBands() {
		return arpuBands;
	}

	public void setArpuBands(String arpuBands) {
		this.arpuBands = arpuBands;
	}

	
	
	


}
