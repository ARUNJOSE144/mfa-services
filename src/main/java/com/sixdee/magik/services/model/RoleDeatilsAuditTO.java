package com.sixdee.magik.services.model;

import java.util.Date;

public class RoleDeatilsAuditTO {

	private int roleId;
	private long designationId;
	private String roleName;

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public long getDesignationId() {
		return designationId;
	}

	public void setDesignationId(long designationId) {
		this.designationId = designationId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public String toString() {
		return "RoleDeatilsAuditTO [roleId=" + roleId + ", designationId=" + designationId + ", roleName=" + roleName
				+ "]";
	}

}
