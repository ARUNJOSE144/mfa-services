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
@Table(name = "DB_GAM_WEB_VS_APP")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class UserWebAppTO {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "UserWebAppTO")
	@TableGenerator(name = "UserWebAppTO", allocationSize = 1)
	@Column(name = "ID")
	private int id;

	@Column(name = "CATEGORY")
	private String category;

	@Column(name = "WEB")
	private int web;

	@Column(name = "APP")
	private int app;

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

	public int getWeb() {
		return web;
	}

	public void setWeb(int web) {
		this.web = web;
	}

	public int getApp() {
		return app;
	}

	public void setApp(int app) {
		this.app = app;
	}

	@Override
	public String toString() {
		return "UserWebAppTO [id=" + id + ", category=" + category + ", web=" + web + ", app=" + app + "]";
	}

}
