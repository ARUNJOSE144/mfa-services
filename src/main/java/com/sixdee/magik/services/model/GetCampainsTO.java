package com.sixdee.magik.services.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "CATEGORY_MASTER")
public class GetCampainsTO {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.TABLE, generator = "GetCampainsTO")
	    @TableGenerator(name = "GetCampainsTO", allocationSize = 1)
	    @Column(name = "CATEGORY_ID")
		public String campId;
	    
	    
	    @Column(name = "CATEGORY_NAME")
		public String campName;
	    
	    @Column(name = "CREATED_BY")
		private int createdUserId;
	    
	    
		
		
		public int getCreatedUserId() {
			return createdUserId;
		}
		public void setCreatedUserId(int createdUserId) {
			this.createdUserId = createdUserId;
		}
		public String getCampId() {
			return campId;
		}
		public void setCampId(String campId) {
			this.campId = campId;
		}
		public String getCampName() {
			return campName;
		}
		public void setCampName(String campName) {
			this.campName = campName;
		}
		@Override
		public String toString() {
			return "GetCampainsTO [campId=" + campId + ", campName=" + campName + "]";
		}
		
		
		
	

}
