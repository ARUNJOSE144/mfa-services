package com.sixdee.magik.services.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Nakhil Kurian
 * @Date : August, 2020
 *
 */

@Entity
@Table(name = "DB_LOY_TIRE_WISE_POINTS_TRND")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class LoyaltyPointsTrendTO {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "LoyaltyPointsTrendTO")
	@TableGenerator(name = "LoyaltyPointsTrendTO", allocationSize = 1)

	@Column(name = "ID")
	private int id;

	@Column(name = "DATE_TAG")
	private Date date;

	@Column(name = "GOLD")
	private int gold;

	@Column(name = "SILVER")
	private int silver;

	@Column(name = "PLATINUM")
	private int platinum;

	@Transient
	private String createDateUI;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public int getSilver() {
		return silver;
	}

	public void setSilver(int silver) {
		this.silver = silver;
	}

	public int getPlatinum() {
		return platinum;
	}

	public void setPlatinum(int platinum) {
		this.platinum = platinum;
	}

	public String getCreateDateUI() {
		return createDateUI;
	}

	public void setCreateDateUI(String createDateUI) {
		this.createDateUI = createDateUI;
	}

	@Override
	public String toString() {
		return "LoyaltyPointsTrendTO [id=" + id + ", date=" + date + ", gold=" + gold + ", silver=" + silver
				+ ", platinum=" + platinum + ", createDateUI=" + createDateUI + "]";
	}

}
