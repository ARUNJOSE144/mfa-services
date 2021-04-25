package com.sixdee.magik.services.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;


@SuppressWarnings("serial")
@Entity
@Table(name = "RPT_REGION_LEVEL_OFFER_UPTAKE")
public class RegionOfferUptakeTO {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "RegionOfferUptakeTO")
	@TableGenerator(name = "RegionOfferUptakeTO", allocationSize = 1)
	@Column(name = "ID")
	private Integer autoId;
	
	@Column(name = "USD")
	private Integer usd;

	@Column(name = "DAY1")
	private Integer day1;
	
	@Column(name = "DAY2")
	private Integer day2;
	
	@Column(name = "DAY3")
	private Integer day3;
	
	@Column(name = "DAY4")
	private Integer day4;
	
	@Column(name = "DAY5")
	private Integer day5;
	
	@Column(name = "DAY6")
	private Integer day6;
	
	@Column(name = "DAY7")
	private Integer day7;
	
	@Column(name = "DAY8")
	private Integer day8;
	
	@Column(name = "DAY9")
	private Integer day9;
	
	@Column(name = "DAY10")
	private Integer day10;
	
	@Column(name = "DAY11")
	private Integer day11;
	
	@Column(name = "DAY12")
	private Integer day12;
	
	@Column(name = "DAY13")
	private Integer day13;
	
	@Column(name = "DAY14")
	private Integer day14;
	
	@Column(name = "DAY15")
	private Integer day15;
	
	@Column(name = "DAY16")
	private Integer day16;
	
	@Column(name = "DAY17")
	private Integer day17;
	
	@Column(name = "DAY18")
	private Integer day18;
	
	@Column(name = "DAY19")
	private Integer day19;
	
	@Column(name = "DAY20")
	private Integer day20;
	
	@Column(name = "DAY21")
	private Integer day21;
	
	@Column(name = "DAY22")
	private Integer day22;
	
	@Column(name = "DAY23")
	private Integer day23;
	
	@Column(name = "DAY24")
	private Integer day24;
	
	@Column(name = "DAY25")
	private Integer day25;
	
	@Column(name = "DAY26")
	private Integer day26;
	
	@Column(name = "DAY27")
	private Integer day27;
	
	@Column(name = "DAY28")
	private Integer day28;
	
	@Column(name = "DAY29")
	private Integer day29;
	
	@Column(name = "DAY30")
	private Integer day30;
	
	@Column(name = "DAY31")
	private Integer day31;
	
	@Column(name = "TOTAL")
	private Integer total;
	
	@Column(name = "CIRCLE")
	private String circle;	
	
	public String getCircle() {
		return circle;
	}

	public void setCircle(String circle) {
		this.circle = circle;
	}


	public Integer getAutoId() {
		return autoId;
	}

	public void setAutoId(Integer autoId) {
		this.autoId = autoId;
	}

	public Integer getDay1() {
		return day1;
	}

	public void setDay1(Integer day1) {
		this.day1 = day1;
	}

	public Integer getDay2() {
		return day2;
	}

	public void setDay2(Integer day2) {
		this.day2 = day2;
	}

	public Integer getDay3() {
		return day3;
	}

	public void setDay3(Integer day3) {
		this.day3 = day3;
	}

	public Integer getDay4() {
		return day4;
	}

	public void setDay4(Integer day4) {
		this.day4 = day4;
	}

	public Integer getDay5() {
		return day5;
	}

	public void setDay5(Integer day5) {
		this.day5 = day5;
	}

	public Integer getDay6() {
		return day6;
	}

	public void setDay6(Integer day6) {
		this.day6 = day6;
	}

	public Integer getDay7() {
		return day7;
	}

	public void setDay7(Integer day7) {
		this.day7 = day7;
	}

	public Integer getDay8() {
		return day8;
	}

	public void setDay8(Integer day8) {
		this.day8 = day8;
	}

	public Integer getDay9() {
		return day9;
	}

	public void setDay9(Integer day9) {
		this.day9 = day9;
	}

	public Integer getDay10() {
		return day10;
	}

	public void setDay10(Integer day10) {
		this.day10 = day10;
	}

	public Integer getDay11() {
		return day11;
	}

	public void setDay11(Integer day11) {
		this.day11 = day11;
	}

	public Integer getDay12() {
		return day12;
	}

	public void setDay12(Integer day12) {
		this.day12 = day12;
	}

	public Integer getDay13() {
		return day13;
	}

	public void setDay13(Integer day13) {
		this.day13 = day13;
	}

	public Integer getDay14() {
		return day14;
	}

	public void setDay14(Integer day14) {
		this.day14 = day14;
	}

	public Integer getDay15() {
		return day15;
	}

	public void setDay15(Integer day15) {
		this.day15 = day15;
	}

	public Integer getDay16() {
		return day16;
	}

	public void setDay16(Integer day16) {
		this.day16 = day16;
	}

	public Integer getDay17() {
		return day17;
	}

	public void setDay17(Integer day17) {
		this.day17 = day17;
	}

	public Integer getDay18() {
		return day18;
	}

	public void setDay18(Integer day18) {
		this.day18 = day18;
	}

	public Integer getDay19() {
		return day19;
	}

	public void setDay19(Integer day19) {
		this.day19 = day19;
	}

	public Integer getDay20() {
		return day20;
	}

	public void setDay20(Integer day20) {
		this.day20 = day20;
	}

	public Integer getDay21() {
		return day21;
	}

	public void setDay21(Integer day21) {
		this.day21 = day21;
	}

	public Integer getDay22() {
		return day22;
	}

	public void setDay22(Integer day22) {
		this.day22 = day22;
	}

	public Integer getDay23() {
		return day23;
	}

	public void setDay23(Integer day23) {
		this.day23 = day23;
	}

	public Integer getDay24() {
		return day24;
	}

	public void setDay24(Integer day24) {
		this.day24 = day24;
	}

	public Integer getDay25() {
		return day25;
	}

	public void setDay25(Integer day25) {
		this.day25 = day25;
	}

	public Integer getDay26() {
		return day26;
	}

	public void setDay26(Integer day26) {
		this.day26 = day26;
	}

	public Integer getDay27() {
		return day27;
	}

	public void setDay27(Integer day27) {
		this.day27 = day27;
	}

	public Integer getDay28() {
		return day28;
	}

	public void setDay28(Integer day28) {
		this.day28 = day28;
	}

	public Integer getDay29() {
		return day29;
	}

	public void setDay29(Integer day29) {
		this.day29 = day29;
	}

	public Integer getDay30() {
		return day30;
	}

	public void setDay30(Integer day30) {
		this.day30 = day30;
	}

	public Integer getDay31() {
		return day31;
	}

	public void setDay31(Integer day31) {
		this.day31 = day31;
	}
	
	public Integer getUsd() {
		return usd;
	}

	public void setUsd(Integer usd) {
		this.usd = usd;
	}

	
	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

		
	
}
