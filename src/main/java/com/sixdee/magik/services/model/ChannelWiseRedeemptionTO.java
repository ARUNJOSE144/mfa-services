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
@Table(name = "DB_LOY_CHAN_WISE_RDMT_COUNT")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class ChannelWiseRedeemptionTO {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ChannelWiseRedeemptionTO")
	@TableGenerator(name = "ChannelWiseRedeemptionTO", allocationSize = 1)

	@Column(name = "ID")
	private int id;

	@Column(name = "CHANNEL")
	private String channel;

	@Column(name = "GOLD")
	private String gold;

	@Column(name = "SILVER")
	private String silver;

	@Column(name = "PLATINUM")
	private String platinum;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getGold() {
		return gold;
	}

	public void setGold(String gold) {
		this.gold = gold;
	}

	public String getSilver() {
		return silver;
	}

	public void setSilver(String silver) {
		this.silver = silver;
	}

	public String getPlatinum() {
		return platinum;
	}

	public void setPlatinum(String platinum) {
		this.platinum = platinum;
	}

	@Override
	public String toString() {
		return "ChannelWiseRedeemptionTO [id=" + id + ", channel=" + channel + ", gold=" + gold + ", silver=" + silver
				+ ", platinum=" + platinum + "]";
	}

}
