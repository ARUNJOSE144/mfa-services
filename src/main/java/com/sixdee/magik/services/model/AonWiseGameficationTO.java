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
@Table(name = "DB_GAM_AON_UESRS")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class AonWiseGameficationTO {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "AonWiseGameficationTO")
	@TableGenerator(name = "AonWiseGameficationTO", allocationSize = 1)

	@Column(name = "ID")
	private int id;

	@Column(name = "CATEGORY")
	private String category;

	@Column(name = "COUNT")
	private int count;

	@Column(name = "REVENUE_DIFFERNCE")
	private int revDif;

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

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getRevDif() {
		return revDif;
	}

	public void setRevDif(int revDif) {
		this.revDif = revDif;
	}

	@Override
	public String toString() {
		return "AonWiseGameficationTO [id=" + id + ", category=" + category + ", count=" + count + ", revDif=" + revDif
				+ "]";
	}

}
