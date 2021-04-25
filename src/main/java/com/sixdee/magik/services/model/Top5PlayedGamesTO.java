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
@Table(name = "DB_GAM_TOP_PLAYED_GAMES")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Top5PlayedGamesTO {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "Top5PlayedGamesTO")
	@TableGenerator(name = "Top5PlayedGamesTO", allocationSize = 1)

	@Column(name = "ID")
	private int id;

	@Column(name = "TITLE")
	private String title;

	@Column(name = "VALUE")
	private int value;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Top5PlayedGamesTO [id=" + id + ", title=" + title + ", value=" + value + "]";
	}

}
