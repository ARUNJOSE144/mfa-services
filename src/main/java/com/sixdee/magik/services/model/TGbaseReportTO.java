package com.sixdee.magik.services.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * @author ABHIRAM MACHIRAJU
 * @Date : March, 2021
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "RPT_TG_BASE")
public class TGbaseReportTO {
	
		@Id
		@GeneratedValue(strategy = GenerationType.TABLE, generator = "TGbaseReportTO")
		@TableGenerator(name = "TGbaseReportTO", allocationSize = 1)
		@Column(name = "ID")
		private int autoId;
		
		@Column(name = "TG_BASE")
		private String tg_base;

		@Column(name = "DELIVERED_W1")
		private Double delivered_w1;
		
		@Column(name = "TAKERS_W1")
		private Double takers_w1;
		
		@Column(name = "TAKERS_PER_W1")
		private Double takers_per_w1;
		
		
		@Column(name = "DELIVERED_W2")
		private Double delivered_w2;
		
		@Column(name = "TAKERS_W2")
		private Double takers_w2;
		
		@Column(name = "TAKERS_PER_W2")
		private Double takers_per_w2;
		
		@Column(name = "DELIVERED_W3")
		private Double delivered_w3;
		
		@Column(name = "TAKERS_W3")
		private Double takers_w3;
		
		@Column(name = "TAKERS_PER_W3")
		private Double takers_per_w3;
		
		
		@Column(name = "DELIVERED_W4")
		private Double delivered_w4;
		
		@Column(name = "TAKERS_W4")
		private Double takers_w4;
		
		@Column(name = "TAKERS_PER_W4")
		private Double takers_per_w4;
		
		@Column(name = "DELIVERED_W2W1")
		private Double delivered_w2w1;
		
		@Column(name = "TAKERS_W2W1")
		private Double takers_w2w1;
		
		@Column(name = "TAKERS_PER_W2W1")
		private Double takers_per_w2w1;
		
		@Column(name = "DELIVERED_W3W2")
		private Double delivered_w3w2;
		
		@Column(name = "TAKERS_W3W2")
		private Double takers_w3w2;
		
		@Column(name = "TAKERS_PER_W3W2")
		private Double takers_per_w3w2;
		
		@Column(name = "DELIVERED_W4W3")
		private Double delivered_w4w3;
		
		@Column(name = "TAKERS_W4W3")
		private Double takers_w4w3;
		
		@Column(name = "TAKERS_PER_W4W3")
		private Double takers_per_w4w3;

		public int getAutoId() {
			return autoId;
		}

		public void setAutoId(int autoId) {
			this.autoId = autoId;
		}

		public Double getDelivered_w1() {
			return delivered_w1;
		}

		public void setDelivered_w1(Double delivered_w1) {
			this.delivered_w1 = delivered_w1;
		}

		public Double getTakers_w1() {
			return takers_w1;
		}

		public void setTakers_w1(Double takers_w1) {
			this.takers_w1 = takers_w1;
		}

		public Double getTakers_per_w1() {
			return takers_per_w1;
		}

		public void setTakers_per_w1(Double takers_per_w1) {
			this.takers_per_w1 = takers_per_w1;
		}

		public Double getDelivered_w2() {
			return delivered_w2;
		}

		public void setDelivered_w2(Double delivered_w2) {
			this.delivered_w2 = delivered_w2;
		}

		public Double getTakers_w2() {
			return takers_w2;
		}

		public void setTakers_w2(Double takers_w2) {
			this.takers_w2 = takers_w2;
		}

		public Double getTakers_per_w2() {
			return takers_per_w2;
		}

		public void setTakers_per_w2(Double takers_per_w2) {
			this.takers_per_w2 = takers_per_w2;
		}

		public Double getDelivered_w3() {
			return delivered_w3;
		}
		

		public String getTg_base() {
			return tg_base;
		}

		public void setTg_base(String tg_base) {
			this.tg_base = tg_base;
		}

		public void setDelivered_w3(Double delivered_w3) {
			this.delivered_w3 = delivered_w3;
		}

		public Double getTakers_w3() {
			return takers_w3;
		}

		public void setTakers_w3(Double takers_w3) {
			this.takers_w3 = takers_w3;
		}

		public Double getTakers_per_w3() {
			return takers_per_w3;
		}

		public void setTakers_per_w3(Double takers_per_w3) {
			this.takers_per_w3 = takers_per_w3;
		}

		public Double getDelivered_w4() {
			return delivered_w4;
		}

		public void setDelivered_w4(Double delivered_w4) {
			this.delivered_w4 = delivered_w4;
		}

		public Double getTakers_w4() {
			return takers_w4;
		}

		public void setTakers_w4(Double takers_w4) {
			this.takers_w4 = takers_w4;
		}

		public Double getTakers_per_w4() {
			return takers_per_w4;
		}

		public void setTakers_per_w4(Double takers_per_w4) {
			this.takers_per_w4 = takers_per_w4;
		}

		public Double getDelivered_w2w1() {
			return delivered_w2w1;
		}

		public void setDelivered_w2w1(Double delivered_w2w1) {
			this.delivered_w2w1 = delivered_w2w1;
		}

		public Double getTakers_w2w1() {
			return takers_w2w1;
		}

		public void setTakers_w2w1(Double takers_w2w1) {
			this.takers_w2w1 = takers_w2w1;
		}

		public Double getTakers_per_w2w1() {
			return takers_per_w2w1;
		}

		public void setTakers_per_w2w1(Double takers_per_w2w1) {
			this.takers_per_w2w1 = takers_per_w2w1;
		}

		public Double getDelivered_w3w2() {
			return delivered_w3w2;
		}

		public void setDelivered_w3w2(Double delivered_w3w2) {
			this.delivered_w3w2 = delivered_w3w2;
		}

		public Double getTakers_w3w2() {
			return takers_w3w2;
		}

		public void setTakers_w3w2(Double takers_w3w2) {
			this.takers_w3w2 = takers_w3w2;
		}

		public Double getTakers_per_w3w2() {
			return takers_per_w3w2;
		}

		public void setTakers_per_w3w2(Double takers_per_w3w2) {
			this.takers_per_w3w2 = takers_per_w3w2;
		}

		public Double getDelivered_w4w3() {
			return delivered_w4w3;
		}

		public void setDelivered_w4w3(Double delivered_w4w3) {
			this.delivered_w4w3 = delivered_w4w3;
		}

		public Double getTakers_w4w3() {
			return takers_w4w3;
		}

		public void setTakers_w4w3(Double takers_w4w3) {
			this.takers_w4w3 = takers_w4w3;
		}

		public Double getTakers_per_w4w3() {
			return takers_per_w4w3;
		}

		public void setTakers_per_w4w3(Double takers_per_w4w3) {
			this.takers_per_w4w3 = takers_per_w4w3;
		}
		
	
}
