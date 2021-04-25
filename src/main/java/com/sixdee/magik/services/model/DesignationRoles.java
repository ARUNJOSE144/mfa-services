/**
 * 
 */
package com.sixdee.magik.services.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author arun.jose
 *
 */

@Entity
@Table(name = "MFS_DESIGNATION_ROLES")
public class DesignationRoles implements Serializable {

	private static final long serialVersionUID = -4081804245662368901L;

	@Id
	@Column(name = "ID")
	private long id;

	@Column(name = "DESIGNATION_ID", nullable = false)
	private long designationId;

	@Column(name = "ROLE_ID", nullable = false)
	private Integer roleId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getDesignationId() {
		return designationId;
	}

	public void setDesignationId(long designationId) {
		this.designationId = designationId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	@Override
	public String toString() {
		return "DesignationRoles [id=" + id + ", designationId=" + designationId + ", roleId=" + roleId + "]";
	}	
	
	
	

}
