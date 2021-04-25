package com.sixdee.magik.services.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Arun Jose
 * @Date : April, 2019
 *
 */

@Entity
@Table(name = "MENU_SHORTCUTS")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class ShortcutsTO implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ShortcutsTO")
	@TableGenerator(name = "ShortcutsTO", allocationSize = 1)
	@Column(name = "ID")
	int id;

	@Column(name = "NAME")
	String name;

	@Column(name = "URL")
	String url;

	@Column(name = "IMAGE_NAME")
	String imageName;

	@Column(name = "MENU_ID")
	int menuId;

	@Column(name = "USER_ID")
	int userId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	@Override
	public String toString() {
		return "ShortcutsTO [id=" + id + ", name=" + name + ", url=" + url + ", imageName=" + imageName + ", menuId="
				+ menuId + ", userId=" + userId + "]";
	}

}
