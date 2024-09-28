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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getIndiaVix() {
		return indiaVix;
	}

	public void setIndiaVix(String indiaVix) {
		this.indiaVix = indiaVix;
	}

	public String getEvents() {
		return events;
	}

	public void setEvents(String events) {
		this.events = events;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public int getSoftDelete() {
		return softDelete;
	}

	public void setSoftDelete(int softDelete) {
		this.softDelete = softDelete;
	}

	public List<MultipartFile> getImages() {
		return images;
	}

	public void setImages(List<MultipartFile> images) {
		this.images = images;
	}

	public int getHavingAnswer() {
		return havingAnswer;
	}

	public void setHavingAnswer(int havingAnswer) {
		this.havingAnswer = havingAnswer;
	}

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public List<TradeLogDetailsTo> getTradeLogDetailsTos() {
		return tradeLogDetailsTos;
	}

	public void setTradeLogDetailsTos(List<TradeLogDetailsTo> tradeLogDetailsTos) {
		this.tradeLogDetailsTos = tradeLogDetailsTos;
	}

	public String getTradeLogDetailsTosString() {
		return tradeLogDetailsTosString;
	}

	public void setTradeLogDetailsTosString(String tradeLogDetailsTosString) {
		this.tradeLogDetailsTosString = tradeLogDetailsTosString;
	}

	public MultipartFile getNiftyImage() {
		return niftyImage;
	}

	public void setNiftyImage(MultipartFile niftyImage) {
		this.niftyImage = niftyImage;
	}

	public MultipartFile getBankNiftyImage() {
		return bankNiftyImage;
	}

	public void setBankNiftyImage(MultipartFile bankNiftyImage) {
		this.bankNiftyImage = bankNiftyImage;
	}

	public MultipartFile getFinNiftyImage() {
		return finNiftyImage;
	}

	public void setFinNiftyImage(MultipartFile finNiftyImage) {
		this.finNiftyImage = finNiftyImage;
	}

	public MultipartFile getDowJohnsImage() {
		return dowJohnsImage;
	}

	public void setDowJohnsImage(MultipartFile dowJohnsImage) {
		this.dowJohnsImage = dowJohnsImage;
	}

	public MultipartFile getNasdaqImage() {
		return nasdaqImage;
	}

	public void setNasdaqImage(MultipartFile nasdaqImage) {
		this.nasdaqImage = nasdaqImage;
	}

	public MultipartFile getSp500Image() {
		return sp500Image;
	}

	public void setSp500Image(MultipartFile sp500Image) {
		this.sp500Image = sp500Image;
	}

	public MultipartFile getStock1Image() {
		return stock1Image;
	}

	public void setStock1Image(MultipartFile stock1Image) {
		this.stock1Image = stock1Image;
	}

	public MultipartFile getStock2Image() {
		return stock2Image;
	}

	public void setStock2Image(MultipartFile stock2Image) {
		this.stock2Image = stock2Image;
	}

	public List<String> getDayList() {
		return dayList;
	}

	public void setDayList(List<String> dayList) {
		this.dayList = dayList;
	}

	public List<Integer> getDateList() {
		return dateList;
	}

	public void setDateList(List<Integer> dateList) {
		this.dateList = dateList;
	}

	public List<TradeLogImageTo> getImageList() {
		return imageList;
	}

	public void setImageList(List<TradeLogImageTo> imageList) {
		this.imageList = imageList;
	}

	public List<Integer> getShowResultOf() {
		return showResultOf;
	}

	public void setShowResultOf(List<Integer> showResultOf) {
		this.showResultOf = showResultOf;
	}

}