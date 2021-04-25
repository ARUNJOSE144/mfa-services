/**
 * 
 */
package com.sixdee.magik.services.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;


/**
 * @author arun.jose
 *
 */

@Entity
@Table(name = "MFS_DESIGNATION_HIERARCHY")
@SuppressWarnings("serial")
public class DesignationHierarchy implements Serializable {

	@GeneratedValue(strategy = GenerationType.TABLE, generator = "DesignationHierarchy")
	@TableGenerator(name = "DesignationHierarchy", allocationSize = 1)
	@Column(name = "ID")
	@Id
	private long designationId;

	@Column(name = "NAME", unique = true, nullable = false)
	private String name;

	@Column(name = "PARENT_ID")
	private int parentId;

	@Column(name = "CHANNEL_TYPE")
	private long channelType;

	@Transient
	private List<DesignationHierarchy> children;
	
/*	@OneToMany(mappedBy = "designationHierarchy", cascade = CascadeType.ALL, fetch = FetchType.LAZY,orphanRemoval = true)
	private Set<DesignationRoles> designationRoles = new HashSet<DesignationRoles>();
	
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "MFS_DESIGNATION_ROLES", joinColumns = @JoinColumn(name = "DESIGNATION_ID", referencedColumnName = "ID"), 
    inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    private List<RoleMaster> roles = new ArrayList<>();	*/


	@Transient
	private int roleId;

	public long getDesignationId() {
		return designationId;
	}

	public void setDesignationId(long designationId) {
		this.designationId = designationId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public long getChannelType() {
		return channelType;
	}

	public void setChannelType(long channelType) {
		this.channelType = channelType;
	}

	public List<DesignationHierarchy> getChildren() {
		return children;
	}

	public void setChildren(List<DesignationHierarchy> children) {
		this.children = children;
	}

/*	public Set<DesignationRoles> getDesignationRoles() {
		return designationRoles;
	}

	public void setDesignationRoles(Set<DesignationRoles> designationRoles) {
		this.designationRoles = designationRoles;
	}

	public List<RoleMaster> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleMaster> roles) {
		this.roles = roles;
	}*/

	
	
	
	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	@Override
	public String toString() {
		return "DesignationHierarchy [designationId=" + designationId + ", name=" + name + ", parentId=" + parentId
				+ ", channelType=" + channelType + ", children=" + children + ", roleId=" + roleId + "]";
	}

}
