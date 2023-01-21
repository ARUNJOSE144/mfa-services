package com.inno.mfa.services.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
@Table(name = "TRADE_LOG_MASTER")
public class TradeLogMasterTo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;

	@Column(name = "TRADE_DATE", nullable = false)
	private String tradeDate;

	@Column(name = "DAY", nullable = true)
	private String day;

	@Column(name = "DATE_", nullable = true)
	private int date;

	@Column(name = "COMMENTS", nullable = true)
	private String comments;

	@Column(name = "INDIA_VIX", nullable = true)
	private String indiaVix;

	@Column(name = "EVENTS", nullable = true)
	private String events;

	@Column(name = "CREATE_DATE", nullable = true, updatable = false)
	private Date createDate;

	@Column(name = "MODIFIED_DATE", nullable = true, insertable = false)
	private Date modifiedDate;

	@Column(name = "SOFT_DELETE", nullable = true)
	private int softDelete;

	@Transient
	List<MultipartFile> images;

	@Transient
	private int havingAnswer;

	@Transient
	private int rowCount;

	@Transient
	private int page;

	@Transient
	private List<TradeLogDetailsTo> tradeLogDetailsTos;

	@Transient
	private String tradeLogDetailsTosString;

	@Transient
	private MultipartFile niftyImage;

	@Transient
	private MultipartFile bankNiftyImage;

	@Transient
	private MultipartFile finNiftyImage;

	@Transient
	private MultipartFile dowJohnsImage;

	@Transient
	private MultipartFile nasdaqImage;

	@Transient
	private MultipartFile sp500Image;

	@Transient
	private MultipartFile stock1Image;

	@Transient
	private MultipartFile stock2Image;

	@Transient
	private List<String> dayList;

	@Transient
	private List<Integer> dateList;

	@Transient
	private List<TradeLogImageTo> imageList;

	@Transient
	private List<Integer> showResultOf;

}