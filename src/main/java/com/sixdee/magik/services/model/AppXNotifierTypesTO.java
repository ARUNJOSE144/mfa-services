package com.sixdee.magik.services.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "X_NOTIFIER_NOTIFICATION_TYPES")
public class AppXNotifierTypesTO {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "AppXNotifierTypesTO")
	@TableGenerator(name = "AppXNotifierTypesTO", allocationSize = 1)
	@Column(name = "NOTIFICATION_ID")
	private int notificationId;

	@Column(name = "LANGUAGE_ID")
	private int languageId;

	@Column(name = "NOTIFICATION_NAME")
	private String notificationName;

	@Column(name = "LANGUAGE_ABB")
	private String languageAbb;

	public int getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(int notificationId) {
		this.notificationId = notificationId;
	}

	public int getLanguageId() {
		return languageId;
	}

	public void setLanguageId(int languageId) {
		this.languageId = languageId;
	}

	public String getNotificationName() {
		return notificationName;
	}

	public void setNotificationName(String notificationName) {
		this.notificationName = notificationName;
	}

	public String getLanguageAbb() {
		return languageAbb;
	}

	public void setLanguageAbb(String languageAbb) {
		this.languageAbb = languageAbb;
	}

	@Override
	public String toString() {
		return "AppXNotifierTypesTO [notificationId=" + notificationId + ", languageId=" + languageId
				+ ", notificationName=" + notificationName + ", languageAbb=" + languageAbb + "]";
	}

}
