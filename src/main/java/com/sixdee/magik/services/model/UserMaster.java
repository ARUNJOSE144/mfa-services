package com.sixdee.magik.services.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "MFS_USER_MASTER")
public class UserMaster implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID")
	private Long userId;

	@Column(name = "USER_NAME", nullable = false)
	private String username;

	@Column(name = "PASSWORD", nullable = false)
	private String password;

	@Column(name = "WRONG_PWD_ATTEMPT")
	private int pswdAttempts;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "PWD_EXPIRY_DATE")
	private Date pswdExpirydate;

	@Column(name = "IS_FORCE_PASSWORD")
	private boolean forcepswd;

	@Column(name = "STATUS", nullable = false)
	private short status;

	@Column(name = "CHANNEL_TYPE", nullable = false)
	private int channelType;

	@Column(name = "ENTITY_ID")
	private String entityId;

	@Column(name = "DESIGNATION_ID")
	private long designationId;

	@Column(name = "EMPLOYEE_ID")
	private String employeeId;

	@Column(name = "FIRST_NAME", nullable = false)
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Column(name = "MSISDN", nullable = false)
	private String msisdn;

	@Column(name = "EMAIL_ID")
	private String emailId;

	@Column(name = "ERP_CODE")
	private String erpCode;

	@Column(name = "GENDER")
	private String gender;

	@Column(name = "DOB")
	private Date dob;

	@Column(name = "PARENT_ID")
	private String parentId;

	@Column(name = "CONTACT_NUMBER")
	private String contactnumber;

	@Column(name = "ID_TYPE")
	private Integer idType;

	@Column(name = "ID_NUMBER")
	private String identificationNumber;

	@Column(name = "USER_TYPE")
	private int userType;

	/*
	 * @Column(name = "IS_LDAP_USER") private boolean ldapUser;
	 */

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CONTRACT_START_DATE")
	private Date contractStartdate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CONTRACT_END_DATE")
	private Date contractEnddate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ASSIGNMENT_START_DATE")
	private Date assignmentStartdate;

	@Column(name = "IMAGE")
	@Lob
	private byte[] image;

	/*
	 * @Column(name = "LOCATION_ID") public int locationId;
	 */

	@Embedded
	private Address addresss;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "REGISTERED_DATE", updatable = false)
	private Date registeredDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ACTIVATED_DATE")
	private Date activatedDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "BLOCKED_DATE")
	private Date blockedDate;

	@Column(name = "CREATED_BY", updatable = false)
	private String created_by;

	@Column(name = "SOFT_DELETE")
	private boolean softDelete;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "DESIGNATION_ID", insertable = false, updatable = false)
	private DesignationHierarchy designation;

//    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinTable(name = "MFS_DESIGNATION_ROLES", joinColumns = @JoinColumn(name = "DESIGNATION_ID", referencedColumnName = "DESIGNATION_ID"), inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
//    private List<RoleMaster> roles = new ArrayList<>();

	/*
	 * @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	 * 
	 * @JoinColumn(name = "LOCATION_ID", insertable = false, updatable = false)
	 * private LocationMaster locMaster;
	 */

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "PARENT_ID", insertable = false, updatable = false)
	private UserMaster parent;

	@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<UserMaster> children = new HashSet<UserMaster>();

	@Transient
	private String currentPassword;

	@Transient
	private String userIDUI;

	@Transient
	private String parentUserName;

	@Column(name = "IS_APPROVAL_REQUIRED")
	private String isApprovalRequired;

	@Column(name = "APPROVAL_TYPE")
	private String approvalTypeFlag;

	@Column(name = "CHANNEL_TYPE_NAME")
	private String channelTypeIdName;

	@Column(name = "DESIGNATION_NAME")
	private String designationName;

	/** --------------------------------------------------------. **/

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public short getStatus() {
		return status;
	}

	public void setStatus(short status) {
		this.status = status;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getErpCode() {
		return erpCode;
	}

	public void setErpCode(String erp_code) {
		this.erpCode = erp_code;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getContactnumber() {
		return contactnumber;
	}

	public void setContactnumber(String contactnumber) {
		this.contactnumber = contactnumber;
	}

	public Integer getIdType() {
		return idType;
	}

	public void setIdType(Integer idType) {
		this.idType = idType;
	}

	public String getIdentificationNumber() {
		return identificationNumber;
	}

	public void setIdentificationNumber(String identificationNumber) {
		this.identificationNumber = identificationNumber;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public Date getContractStartdate() {
		return contractStartdate;
	}

	public void setContractStartdate(Date contractStartdate) {
		this.contractStartdate = contractStartdate;
	}

	public Date getContractEnddate() {
		return contractEnddate;
	}

	public void setContractEnddate(Date contractEnddate) {
		this.contractEnddate = contractEnddate;
	}

	public Date getAssignmentStartdate() {
		return assignmentStartdate;
	}

	public void setAssignmentStartdate(Date assignmentStartdate) {
		this.assignmentStartdate = assignmentStartdate;
	}

	public Address getAddresss() {
		return addresss;
	}

	public void setAddresss(Address addresss) {
		this.addresss = addresss;
	}

	public Date getRegisteredDate() {
		return registeredDate;
	}

	public void setRegisteredDate(Date registeredDate) {
		this.registeredDate = registeredDate;
	}

	public Date getActivatedDate() {
		return activatedDate;
	}

	public void setActivatedDate(Date activatedDate) {
		this.activatedDate = activatedDate;
	}

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getPswdAttempts() {
		return pswdAttempts;
	}

	public void setPswdAttempts(int pswdAttempts) {
		this.pswdAttempts = pswdAttempts;
	}

	public Date getPswdExpirydate() {
		return pswdExpirydate;
	}

	public void setPswdExpirydate(Date pswdExpirydate) {
		this.pswdExpirydate = pswdExpirydate;
	}

	public boolean isForcepswd() {
		return forcepswd;
	}

	public void setForcepswd(boolean forcepswd) {
		this.forcepswd = forcepswd;
	}

	public boolean isSoftDelete() {
		return softDelete;
	}

	public void setSoftDelete(boolean soft_delete) {
		this.softDelete = soft_delete;
	}

	/*
	 * public int getLocationId() { return locationId; }
	 * 
	 * public void setLocationId(int locationId) { this.locationId = locationId; }
	 */

	public long getDesignationId() {
		return designationId;
	}

	public void setDesignationId(long designationId) {
		this.designationId = designationId;
	}

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}
//
//    public List<RoleMaster> getRoles() {
//        return roles;
//    }
//
//    public void setRoles(List<RoleMaster> roles) {
//        this.roles = roles;
//    }

	/*
	 * public LocationMaster getLocMaster() { return locMaster; }
	 * 
	 * public void setLocMaster(LocationMaster locMaster) { this.locMaster =
	 * locMaster; }
	 */

	public UserMaster getParent() {
		return parent;
	}

	public void setParent(UserMaster parent) {
		this.parent = parent;
	}

	public Set<UserMaster> getChildren() {
		return children;
	}

	public void setChildren(Set<UserMaster> children) {
		this.children = children;
	}

	public DesignationHierarchy getDesignation() {
		return designation;
	}

	public void setDesignation(DesignationHierarchy designation) {
		this.designation = designation;
	}

	public int getChannelType() {
		return channelType;
	}

	public void setChannelType(int channelType) {
		this.channelType = channelType;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	/*
	 * public boolean isLdapUser() { return ldapUser; }
	 * 
	 * public void setLdapUser(boolean ldapUser) { this.ldapUser = ldapUser; }
	 */

	public Date getBlockedDate() {
		return blockedDate;
	}

	public void setBlockedDate(Date blockedDate) {
		this.blockedDate = blockedDate;
	}

	public String getCurrentPassword() {
		return currentPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}

	public String getUserIDUI() {
		return userIDUI;
	}

	public void setUserIDUI(String userIDUI) {
		this.userIDUI = userIDUI;
	}

	public String getParentUserName() {
		return parentUserName;
	}

	public void setParentUserName(String parentUserName) {
		this.parentUserName = parentUserName;
	}

	public String getIsApprovalRequired() {
		return isApprovalRequired;
	}

	public void setIsApprovalRequired(String isApprovalRequired) {
		this.isApprovalRequired = isApprovalRequired;
	}

	public String getApprovalTypeFlag() {
		return approvalTypeFlag;
	}

	public void setApprovalTypeFlag(String approvalTypeFlag) {
		this.approvalTypeFlag = approvalTypeFlag;
	}

	public String getChannelTypeIdName() {
		return channelTypeIdName;
	}

	public void setChannelTypeIdName(String channelTypeIdName) {
		this.channelTypeIdName = channelTypeIdName;
	}

	public String getDesignationName() {
		return designationName;
	}

	public void setDesignationName(String designationName) {
		this.designationName = designationName;
	}

}
