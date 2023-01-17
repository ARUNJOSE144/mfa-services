package com.inno.mfa.services.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

/**
 * @author Arun Jose
 * @Date : March, 2021
 */
@Data
@Entity
@Table(name = "TRADE_LOG_IMAGE_DETAILS")
public class TradeLogImageTo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;

	@Column(name = "TRADE_LOG_ID", nullable = true)
	private int tradeLogId;

	@Column(name = "TRADE_LOG_DETAILS_ID", nullable = true)
	private int tradeLogDetailsId;

	@Column(name = "IMAGE_PATH", nullable = false)
	private String imagePath;

	@Transient
	private MultipartFile image;

}