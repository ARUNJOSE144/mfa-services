package com.sixdee.magik.services.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Nakhil Kurian
 * @Date : August, 2020
 *
 */

@Entity
@Table(name = "DB_LOY_MERCHNT_WISE_RDMD")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class MercantWiseRedeemptionTO {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "MercantWiseRedeemptionTO")
	@TableGenerator(name = "MercantWiseRedeemptionTO", allocationSize = 1)

	@Column(name = "ID")
	private int id;

	@Column(name = "CATEGORY")
	private String category;

	@Column(name = "ORDERED")
	private String ordered;

	@Column(name = "DELIVERED")
	private String delivered;

	@Column(name = "PENDING")
	private String pending;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getOrdered() {
		return ordered;
	}

	public void setOrdered(String ordered) {
		this.ordered = ordered;
	}

	public String getDelivered() {
		return delivered;
	}

	public void setDelivered(String delivered) {
		this.delivered = delivered;
	}

	public String getPending() {
		return pending;
	}

	public void setPending(String pending) {
		this.pending = pending;
	}

	@Override
	public String toString() {
		return "MercantWiseRedeemptionTO [id=" + id + ", category=" + category + ", ordered=" + ordered + ", delivered="
				+ delivered + ", pending=" + pending + "]";
	}

}
