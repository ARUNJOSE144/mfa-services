package com.sixdee.magik.services.dao.impl;

import static org.springframework.util.StringUtils.isEmpty;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.apache.catalina.realm.RealmBase;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.sixdee.magik.services.cache.DbCacheManager;
import com.sixdee.magik.services.dao.UserDAO;
import com.sixdee.magik.services.exception.CommonException;
import com.sixdee.magik.services.exception.DAOException;
import com.sixdee.magik.services.exception.NoObjectMatchingException;
import com.sixdee.magik.services.exception.UnAuthorizedException;
import com.sixdee.magik.services.model.Address;
import com.sixdee.magik.services.model.ChannelTypeHeirarchy;
import com.sixdee.magik.services.model.PasswordHistory;
import com.sixdee.magik.services.model.PasswordMgmnt;
import com.sixdee.magik.services.model.RoleDeatilsAuditTO;
import com.sixdee.magik.services.model.UserDetails;
import com.sixdee.magik.services.model.UserMaster;
import com.sixdee.magik.services.util.PasswordCore;
import com.sixdee.magik.services.util.StatusCode;
import com.sixdee.magik.services.util.SystemConstants;
import com.sixdee.magik.services.util.SystemProperties;

/**
 * @author Rajesh January 2019
 */
@Repository
@Transactional
public class UserDAOImpl implements UserDAO{
	
	private static Logger LOGGER = LoggerFactory.getLogger(UserDAOImpl.class);
	
	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;
	private Query query = null;
	private String hql;
	Session session=null;
	
	
	 	@Autowired
	    SystemProperties property;
	    
	    @Autowired
	    PasswordMgmntDAOImpl passwordMgmntDAOImpl;
	    
	    @Autowired
	    PasswordCore pswdCore;
	    @Autowired
		DbCacheManager dbCache;
	
	@Override
    public void create(UserDetails request, int userType, String origUserId) throws DAOException, CommonException {
        String password = "admin123";
        boolean flag = false;
        session=sessionFactory.getCurrentSession();
        
        try {
            UserMaster userMaster = new UserMaster();
            userMaster.setUsername(request.getUserName());

            if (property.isRegisterUserDefaultPwdFlag()) {
                userMaster.setPassword(property.getRegisterUserDefaultPwd());
            } else {
                //password = PasswordManagement.generateNewPin(Integer.parseInt(property.getPasswordMaxLength()));
                PasswordMgmnt rule = passwordMgmntDAOImpl.findById(SystemConstants.USER_PSWD);
                PasswordMgmnt pas = rule!=null?rule:null;
                password = pswdCore.generatePassword(pas);
                userMaster.setPassword(RealmBase.Digest(password, "MD5", "UTF-8"));
            }

            userMaster.setFirstName(request.getFirstName());
            userMaster.setLastName(request.getLastName());
            userMaster.setEmployeeId(request.getEmpCode());
            userMaster.setMsisdn(request.getMsisdn());
            userMaster.setStatus(SystemConstants.ACTIVE);
            userMaster.setEmailId(request.getEmail());
            userMaster.setChannelType(request.getChannelType());
            userMaster.setDesignationId(request.getDesignationId());
            userMaster.setParentId("1");
            //userMaster.setParentId(request.getParentId());
            userMaster.setEntityId(request.getEntityId());
            // userMaster.setLocationId(request.getTeritoryTypeId());
            userMaster.setRegisteredDate(new Date());
            userMaster.setUserType(userType);
            
          /*  if(request.isLdapUser() == true) {
            	userMaster.setLdapUser(true);
            }else {
            	userMaster.setLdapUser(false);
            }*/
            
            userMaster.setForcepswd(true);
            
            //int expiryDays =0;
            Integer expiryDays = passwordMgmntDAOImpl.findById(SystemConstants.USER_PSWD).getExpiryDays();
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DAY_OF_MONTH, expiryDays);
            userMaster.setPswdExpirydate(cal.getTime());
            
            userMaster.setCreated_by(origUserId);

            if (request.getAddress() != null) {
                Address addresss = new Address();
                addresss.setAddressLine1(request.getAddress().getAddressLine1());
                addresss.setAddressLine2(request.getAddress().getAddressLine2());
                addresss.setCity(request.getAddress().getCity());
                addresss.setZip(request.getAddress().getZip());
                addresss.setRegion(request.getAddress().getRegion());
                userMaster.setAddresss(addresss);
            }

            session.save(userMaster);

            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("UserDAOImpl.create :: Successfully created new user : " + userMaster.getUserId() + ":"
                        + password);
            }

            flag = true;
            
            PasswordHistory pswd = new PasswordHistory();
            pswd.setUserId(userMaster.getUserId());
            pswd.setPassword1(userMaster.getPassword());
            session.save(pswd);

        } catch (Exception e) {
            LOGGER.error("Exception (UserDAOImpl.create) : " + e.getMessage(), e);

            if (e.getCause() != null && e.getCause().getCause() != null
                    && e.getCause().getCause().getMessage() != null) {

                if (e.getCause().getCause().getMessage().contains("MFS_USER_MASTER_U01")) {
                    throw new CommonException("Username already exists.", StatusCode.USERNAME_EXISTS);

                } else if (e.getCause().getCause().getMessage().contains("MFS_USER_MASTER_U02")) {
                    throw new CommonException("MSISDN already exists.", StatusCode.MSISDN_EXISTS);
                }
            }
            throw new DAOException("Exception in DAO Layer (UserDAOImpl.create) :Message:" + e.getMessage()
                    + ":Cause:" + e.getCause());
            
        } /*finally {
            String channel = MDC.get(MDCConstants.CHANNEL_ID);
            if (Integer.valueOf(channel) == SystemConstants.CHANNEL_WEB) {
                if (flag && property.getSendSMS() && request.getMsisdn() != null) {
                    SmsBean smsBean = new SmsBean();
                    smsBean.setStatusCode(StatusCode.USER_CREATE_SUCCESS);
                    smsBean.setOrigMsisdn(request.getMsisdn());
                    smsBean.setPassword(password);
                    smsBean.setUsername(request.getUserName());
                    asych.executeAsych(smsBean);
                }
            	if(flag && property.getSendEmail() && request.getEmail() != null 
            			&& !request.getEmail().equalsIgnoreCase("")) {
            		EmailBean emailBean = new EmailBean();
            		emailBean.setStatusCode(StatusCode.USER_CREATE_SUCCESS);
            		emailBean.setOrigMsisdn(request.getMsisdn());
            		emailBean.setPassword(password);
            		emailBean.setUsername(request.getUserName());
            		emailBean.setTo(request.getEmail());
            		emailBean.setSubject("You're Registered");
            		asych.executeAsych(emailBean);
            	}
            }
            
        }*/
    }
	
	/*public String findUserNameByUserId(String userId) {
		String userName = "";
		session = sessionFactory.getCurrentSession();
		hql="select userName from UserMaster where userId=:userId";
		query=session.createQuery(hql);
		query.setParameter("userId", userId);
		userName=(String)query.uniqueResult();

		return userName;
	}*/
	
	@Override
	public List<UserDetails> getUserDetails(int loginUserId){
		List<UserDetails> usersList=new ArrayList();
		UserDetails userDetails=null;
		session=sessionFactory.getCurrentSession();
		LinkedHashMap<Integer,String> channelTypeMap=new LinkedHashMap<>();
		
		hql="from ChannelTypeHeirarchy";
		query=session.createQuery(hql);
		List<ChannelTypeHeirarchy> list=(List<ChannelTypeHeirarchy>)query.list();
		if(list!=null && !list.isEmpty()) {
			for(ChannelTypeHeirarchy type:list) {
				channelTypeMap.put(type.getChannelType(), type.getName());
			}
		}
		
		
		hql = "SELECT um.USER_ID,um.FIRST_NAME,um.LAST_NAME,um.MSISDN,um.STATUS,um.CHANNEL_TYPE,um.DESIGNATION_ID,oh.NAME from MFS_USER_MASTER um "
				+ "INNER JOIN  MFS_DESIGNATION_HIERARCHY  oh ON um.DESIGNATION_ID = oh.ID "
				+ "INNER JOIN (SELECT USER_ID from MFS_USER_MASTER ,"
				+ "(SELECT @pv\\:= ?) initialisation where find_in_set(parent_id, @pv) > 0 "
				+ "and @pv\\:= concat(@pv, ',', user_id) union select USER_ID FROM MFS_USER_MASTER "
				+ ") jt ON jt.USER_ID = um.USER_ID "
				+ " AND SOFT_DELETE=0 ";
		
	/*	hql = "SELECT um.userId,um.firstName,um.lastName,um.msisdn,um.status,um.channelType from UserMaster um "
				+ "INNER JOIN  MFS_DESIGNATION_HIERARCHY  oh ON um.DESIGNATION_ID = oh.ID "
				+ "INNER JOIN (SELECT userId from UserMaster ,"
				+ "(SELECT @pv\\:= ?) initialisation where find_in_set(um.parentId, @pv) > 0 "
				+ "and @pv\\:= concat(@pv, ',', user_id) union select userId FROM UserMaster "
				+ ") jt ON jt.userId = um.userId "
				+ " AND softDelete=0 ";
				*/

		query = session.createSQLQuery(hql);
		//query = session.createQuery(hql);
		query.setParameter(0, loginUserId);
		List<Object[]> listFromDB=query.list();
		if(!listFromDB.isEmpty()) {
			for(Object[] objects:listFromDB) {
				userDetails=new UserDetails();
				userDetails.setUserId(Long.parseLong(objects[0]+""));
				userDetails.setFirstName((String)objects[1]);
				userDetails.setLastName((String)objects[2]);
				userDetails.setMsisdn((String)objects[3]);
				userDetails.setStatusId(Integer.parseInt(objects[4]+""));
				System.out.println("User status db cache===============>"+dbCache.getUserStatus());
				userDetails.setStatusName(dbCache.getUserStatus().get(userDetails.getStatusId()));
				userDetails.setChannelType((Integer)objects[5]);
				if(channelTypeMap!=null && channelTypeMap.get(userDetails.getChannelType())!=null){
					userDetails.setChannelTypeName(channelTypeMap.get(userDetails.getChannelType()));
				}else {
					userDetails.setChannelTypeName("");

				}
				userDetails.setDesignationId(Long.parseLong(objects[6]+""));
				userDetails.setDesignation((String)objects[7]);
				if(userDetails.getUserId()!=1)
					usersList.add(userDetails);
			}
			
			/*for(Object[] objects:listFromDB) {
				userDetails=new UserDetails();
				userDetails.setUserId((Long)objects[0]);
				userDetails.setFirstName((String)objects[1]);
				userDetails.setLastName((String)objects[2]);
				userDetails.setMsisdn((String)objects[3]);
				userDetails.setStatusId((Integer)objects[4]);
				userDetails.setStatusName(dbCache.getUserStatus().get((Integer)objects[4]));
				userDetails.setChannelType((Integer)objects[5]);
				usersList.add(userDetails);
			}*/
			
		}
		
		return usersList;
		
	}
	
	public UserMaster findByUserName(String userName) {
		session=sessionFactory.getCurrentSession();
		hql="from UserMaster where username=:userName";
		query=session.createQuery(hql);
		query.setParameter("userName", userName);
		return (UserMaster)query.uniqueResult();
	}
	
	 public void changeStatus(Long userId, int status)
	            throws DAOException, NoObjectMatchingException {
	        try {
	        	session=sessionFactory.getCurrentSession();
	            UserMaster user = findByUserId(userId);
	            if (user!=null) {
	                UserMaster thisUser = user;

	                if (thisUser.isSoftDelete()) {
	                    throw new NoObjectMatchingException("No such user found : " + userId);
	                }
	                thisUser.setStatus((short) status);

	                session.saveOrUpdate(thisUser);

	                if (LOGGER.isInfoEnabled()) {
	                    LOGGER.info("UserDAOImpl.changeStatus :: Successfully changed status of userId : "
	                            + thisUser.getUserId() + " to : " + status);
	                }

	            } else {
	                throw new NoObjectMatchingException("No such user found : " + userId);
	            }

	        } catch (NoObjectMatchingException ne) {
	            LOGGER.error("NoObjectMatchingException (UserDAOImpl.changeStatus) : " + ne.getMessage(), ne);
	            throw ne;
	        } catch (Exception e) {
	            LOGGER.error("Exception (UserDAOImpl.changeStatus) : " + e.getMessage(), e);
	            throw new DAOException("Exception in DAO Layer (UserDAOImpl.changeStatus) :Message:" + e.getMessage()
	                    + ":Cause:" + e.getCause());
	        }
	    }
	    
	    
	    public void changeForcePswd(Long userId) throws DAOException, NoObjectMatchingException {
	        try {
	        	session=sessionFactory.getCurrentSession();
	            UserMaster user = findByUserId(userId);

	            if (user!=null) {
	                UserMaster thisUser = user;

	                if (thisUser.isSoftDelete()) {
	                    throw new NoObjectMatchingException("No such user found : " + userId);
	                }
	                thisUser.setForcepswd(true);
	                session.saveOrUpdate(thisUser);
	            } else {
	                throw new NoObjectMatchingException("No such user found : " + userId);
	            }

	        } catch (NoObjectMatchingException ne) {
	            LOGGER.error("NoObjectMatchingException (UserDAOImpl.changeForcePswd) : " + ne.getMessage(), ne);
	            throw ne;
	        } catch (Exception e) {
	            LOGGER.error("Exception (UserDAOImpl.changeForcePswd) : " + e.getMessage(), e);
	            throw new DAOException("Exception in DAO Layer (UserDAOImpl.changeForcePswd) :Message:" + e.getMessage()
	                    + ":Cause:" + e.getCause());
	        }
	    }
	
	    public UserMaster findByUserId(Long userId) {
			session=sessionFactory.getCurrentSession();
			hql="from UserMaster where userId=:userId";
			query=session.createQuery(hql);
			query.setParameter("userId", userId);
			return (UserMaster)query.uniqueResult();
		}
	    
	    public void updateWrongPasswordAttempts(Long userId, int attempt) throws DAOException, NoObjectMatchingException {
	        try {
	            UserMaster user = findByUserId(userId);
	            session=sessionFactory.getCurrentSession();

	            if (user!=null) {
	                UserMaster thisUser = user;

	                if (thisUser.isSoftDelete()) {
	                    throw new NoObjectMatchingException("No such user found : " + userId);
	                }
	                thisUser.setPswdAttempts(attempt);

	                session.saveOrUpdate(thisUser);

	                if (LOGGER.isInfoEnabled()) {
	                    LOGGER.info("UserDAOImpl.updateWrongPasswordAttempts :: Successfully changed password attempts of the userId : "
	                            + thisUser.getUserId() + " to : " + attempt);
	                }

	            } else {
	                throw new NoObjectMatchingException("No such user found : " + userId);
	            }

	        } catch (NoObjectMatchingException ne) {
	            LOGGER.error("NoObjectMatchingException (UserDAOImpl.changeStatus) : " + ne.getMessage(), ne);
	            throw ne;
	        } catch (Exception e) {
	            LOGGER.error("Exception (UserDAOImpl.changeStatus) : " + e.getMessage(), e);
	            throw new DAOException("Exception in DAO Layer (UserDAOImpl.changeStatus) :Message:" + e.getMessage()
	                    + ":Cause:" + e.getCause());
	        }
	    	
	    }

		@Override
		public void update(UserDetails request, String origUserId) throws DAOException, NoObjectMatchingException {
			// TODO Auto-generated method stub

	        try {
	        	session=sessionFactory.getCurrentSession();
	            UserMaster user = findByUserId(Long.parseLong(request.getUserId()+""));

	            if (user!=null) {

	                UserMaster thisUser = user;

	                if (thisUser.isSoftDelete()) {
	                    throw new NoObjectMatchingException("No such user found : " + request.getUserId());
	                }

	                // if (!isEmpty(request.getFirstName())) {
	                thisUser.setFirstName(request.getFirstName());
	                // }
	                // if (!isEmpty(request.getLastName())) {
	                thisUser.setLastName(request.getLastName());
	                // }
	                // if (!isEmpty(request.getEmpCode())) {
	                thisUser.setEmployeeId(request.getEmpCode());
	                // }
	                // if (!isEmpty(request.getMsisdn())) {
	                thisUser.setMsisdn(request.getMsisdn());
	                // }
	                /*
	                 * if (!isEmpty(request.getEmail())) {
	                 * thisUser.setEmailId(request.getEmail()); }
	                 */
	                
	                if (request.getDesignationId() > 0) {
	                	thisUser.setDesignationId(request.getDesignationId()); }
	                
	                // if (!isEmpty(request.getParentId())) {
	                thisUser.setParentId(request.getParentId());
	                // }

	                thisUser.setEntityId(request.getEntityId());
	               // thisUser.setLdapUser(request.isLdapUser());

	                /*
	                 * if (request.getTeritoryId() > 0) {
	                 * thisUser.setLocationId(request.getTeritoryId()); }
	                 */

	                if (request.getAddress() != null) {
	                    Address addresss = thisUser.getAddresss();

	                    // if (!isEmpty(request.getAddress().getAddressLine1())) {
	                    addresss.setAddressLine1(request.getAddress().getAddressLine1());
	                    // }
	                    // if (!isEmpty(request.getAddress().getAddressLine2())) {
	                    addresss.setAddressLine2(request.getAddress().getAddressLine2());
	                    // }
	                    // if (!isEmpty(request.getAddress().getCity())) {
	                    addresss.setCity(request.getAddress().getCity());
	                    // }
	                    // if (!isEmpty(request.getAddress().getZip())) {
	                    addresss.setZip(request.getAddress().getZip());
	                    // }
	                    // if (!isEmpty(request.getAddress().getRegion())) {
	                    addresss.setRegion(request.getAddress().getRegion());
	                    // }
	                    thisUser.setAddresss(addresss);
	                }

	                session.update(thisUser);

	                if (LOGGER.isInfoEnabled()) {
	                    LOGGER.info("UserDAOImpl.update :: Successfully updated user : " + thisUser.getUserId());
	                }

	                /** Update location assosiation. **/
	                /*List<UserAssoLocation> existing = userAssoLocationRepo.findByUserId(thisUser.getUserId());
	                boolean isInsert = false;
	                if (existing != null && !existing.isEmpty()) {
	                    if (userAssoLocationRepo.deleteByUserId(thisUser.getUserId()) > 0) {
	                        isInsert = true;
	                    }
	                } else {
	                    isInsert = true;
	                }

	                if (isInsert && request.getLocations() != null && !request.getLocations().isEmpty()) {
	                    for (int locs : request.getLocations()) {
	                        UserAssoLocation newLocs = new UserAssoLocation();
	                        newLocs.setUserId(thisUser.getUserId());
	                        newLocs.setPrimId(UUID.randomUUID().toString());
	                        newLocs.setLocationId(locs);
	                        userAssoLocationRepo.save(newLocs);
	                    }
	                }*/

	                /** Update business assosiation. **/
	                /*List<UserAssoBusiness> existingB = userAssoBusinessRepo.findByUserId(thisUser.getUserId());

	                isInsert = false;
	                if (existingB != null && !existingB.isEmpty()) {
	                    if (userAssoBusinessRepo.deleteByUserId(thisUser.getUserId()) > 0) {
	                        isInsert = true;
	                    }
	                } else {
	                    isInsert = true;
	                }

	                if (isInsert && request.getBusinessUnits() != null && !request.getBusinessUnits().isEmpty()) {
	                    for (int bunits : request.getBusinessUnits()) {
	                        UserAssoBusiness newBunit = new UserAssoBusiness();
	                        newBunit.setUserId(thisUser.getUserId());
	                        newBunit.setPrimId(UUID.randomUUID().toString());
	                        newBunit.setBusinessUnit(bunits);
	                        userAssoBusinessRepo.save(newBunit);
	                    }
	                }*/

	                /** Update entity assosiation. **/
	                /*List<UserAssoEntity> existingE = userAssoEntityRepo.findByUserId(thisUser.getUserId());

	                isInsert = false;
	                if (existingE != null && !existingE.isEmpty()) {
	                    if (userAssoEntityRepo.deleteByUserId(thisUser.getUserId()) > 0) {
	                        isInsert = true;
	                    }
	                } else {
	                    isInsert = true;
	                }

	                if (isInsert && request.getEntities() != null && !request.getEntities().isEmpty()) {
	                    for (UserEnitity entity : request.getEntities()) {
	                        UserAssoEntity newEntity = new UserAssoEntity();
	                        newEntity.setUserId(thisUser.getUserId());
	                        newEntity.setPrimId(UUID.randomUUID().toString());
	                        newEntity.setEntityId(entity.getEntityId());
	                        userAssoEntityRepo.save(newEntity);
	                    }
	                }*/

	                if (LOGGER.isInfoEnabled()) {
	                    LOGGER.info("UserDAOImpl.update :: Successfully updated assosiation.");
	                }

	            } else {
	                throw new NoObjectMatchingException("No such user found : " + request.getUserId());
	            }

	        } catch (NoObjectMatchingException ne) {
	            LOGGER.error("NoObjectMatchingException (UserDAOImpl.update) : " + ne.getMessage(), ne);
	            throw ne;
	        } catch (Exception e) {
	            LOGGER.error("Exception (UserDAOImpl.update) : " + e.getMessage(), e);
	            e.printStackTrace();
	            throw new DAOException("Exception in DAO Layer (UserDAOImpl.update) :Message:" + e.getMessage()
	                    + ":Cause:" + e.getCause());
	        }
	    
			
		}
		
		public void delete(String userId, String origUserId) throws DAOException, NoObjectMatchingException {
	        try {
	            UserMaster user = findByUserId(Long.parseLong(userId));
	            session=sessionFactory.getCurrentSession();

	            if (user!=null) {
	                UserMaster thisUser = user;

	                if (thisUser.isSoftDelete()) {
	                    throw new NoObjectMatchingException("No such user found : " + userId);
	                }

	                thisUser.setSoftDelete(true);
	                // thisUser.setCreated_by(origUserId);
	                if(property.getEnableSoftDelete())
	                	session.save(thisUser);
	                else 
	                	deleteByUserId(thisUser.getUserId());

	                if (LOGGER.isInfoEnabled()) {
	                    LOGGER.info("UserDAOImpl.delete :: Successfully deleted user : " + thisUser.getUserId());
	                }

	            } else {
	                throw new NoObjectMatchingException("No such user found : " + userId);
	            }

	        } catch (NoObjectMatchingException ne) {
	            LOGGER.error("NoObjectMatchingException (UserDAOImpl.delete) : " + ne.getMessage(), ne);
	            throw ne;
	        } catch (Exception e) {
	            LOGGER.error("Exception (UserDAOImpl.delete) : " + e.getMessage(), e);
	            throw new DAOException("Exception in DAO Layer (UserDAOImpl.delete) :Message:" + e.getMessage()
	                    + ":Cause:" + e.getCause());
	        }
	    }
		
		public void deleteByUserId(Long userId) {
			session=sessionFactory.getCurrentSession();
			hql="delete from UserMaster where userId=:userId";
			query=session.createQuery(hql);
			query.setParameter("userId", userId);
			query.executeUpdate();
		}
		
		@Override
		public UserDetails view(String origUserId, String userId) throws DAOException, NoObjectMatchingException {
	        UserDetails response = new UserDetails();

	        try {
	        	session=sessionFactory.getCurrentSession();
	        	LinkedHashMap<Integer,String> channelTypeMap=new LinkedHashMap<>();
	    		
	    		hql="from ChannelTypeHeirarchy";
	    		query=session.createQuery(hql);
	    		List<ChannelTypeHeirarchy> list=(List<ChannelTypeHeirarchy>)query.list();
	    		if(list!=null && !list.isEmpty()) {
	    			for(ChannelTypeHeirarchy type:list) {
	    				channelTypeMap.put(type.getChannelType(), type.getName());
	    			}
	    		}

	            //privilageRepo.hasView(origUserId, userId);

	            UserMaster user = findByUserId(Long.parseLong(userId));

	            if (user!=null) {
	                UserMaster thisUser = user;

	                response = fillUserDetails(thisUser,channelTypeMap);
	                

	            } else {
	                throw new NoObjectMatchingException("No such user found : " + userId);
	            }

	        } catch (UnAuthorizedException ne) {
	            LOGGER.error("UnAuthorizedException (UserDAOImpl.view) : " + ne.getMessage(), ne);
	            throw ne;

	        } catch (NoObjectMatchingException ne) {
	            LOGGER.error("NoObjectMatchingException (UserDAOImpl.view) : " + ne.getMessage(), ne);
	            throw ne;
	        } catch (Exception e) {
	            LOGGER.error("Exception (UserDAOImpl.view) : " + e.getMessage(), e);
	            throw new DAOException("Exception in DAO Layer (UserDAOImpl.view) :Message:" + e.getMessage()
	                    + ":Cause:" + e.getCause());
	        }
	        return response;
	    }
		
		  private UserDetails fillUserDetails(UserMaster thisUser,LinkedHashMap<Integer, String> channelTypeMap) {
		        UserDetails response = new UserDetails();
		        response.setUserId(thisUser.getUserId());
		        response.setUserName(thisUser.getUsername());
		        response.setFirstName(thisUser.getFirstName());
		        response.setLastName(thisUser.getLastName());
		        response.setMsisdn(thisUser.getMsisdn());
		        response.setEmail(thisUser.getEmailId());
		        response.setEmpCode(thisUser.getEmployeeId());
		        response.setDesignationId(thisUser.getDesignationId());
		        response.setStatusId(thisUser.getStatus());
		        response.setChannelType(thisUser.getChannelType());

		        response.setEntityId(thisUser.getEntityId());
		       /* try {
		            response.setEntity(getEntityBasicInfo(thisUser.getEntityId()).getEntityName());
		        } catch (Exception e) {
		        }*/

		     /*   Optional<ChannelTypeHeirarchy> op = salesStructureRepository.findById(thisUser.getChannelType());

		        if (op.isPresent()) {
		            response.setChannelTypeName(op.get().getName());
		            response.setCpType(op.get().getType());
		        }
*/
		        response.setStatusName(dbCache.getUserStatus().get(Integer.valueOf(thisUser.getStatus())));

		        if (thisUser.getDesignation() != null) {
		            response.setDesignation(thisUser.getDesignation().getName());
		            response.setDesignationId(thisUser.getDesignationId());
		        }

		        response.setParentId(thisUser.getParentId());

		        if (thisUser.getParent() != null) {
		            String lastname = thisUser.getParent().getLastName();
		            lastname = (isEmpty(lastname)) ? "" : lastname;
		            response.setParent(thisUser.getParent().getFirstName() + " " + lastname);
		        }
		        
		        if(channelTypeMap!=null && channelTypeMap.get(thisUser.getChannelType())!=null) {
		        	response.setChannelTypeName(channelTypeMap.get(thisUser.getChannelType()));
		        }else {
		        	response.setChannelTypeName("");
		        }

		        if (thisUser.getAddresss() != null) {
		            Address address = new Address();
		            address.setAddressLine1(thisUser.getAddresss().getAddressLine1());
		            address.setAddressLine2(thisUser.getAddresss().getAddressLine2());
		            address.setCity(thisUser.getAddresss().getCity());
		            address.setZip(thisUser.getAddresss().getZip());
		            address.setRegion(thisUser.getAddresss().getRegion());
		            response.setAddress(address);
		        }

		       

		        return response;
		    }
		  
		  public Set<Integer> getPrivilegesByDesignation(long designationId) {
				Set<Integer> privileges = new HashSet<>();
				String role=null;
				session = sessionFactory.getCurrentSession();
				//hql = "SELECT ROLE_ID,DESIGNATION_ID FROM MFS_DESIGNATION_ROLES where DESIGNATION_ID=:designationId";
				 hql = "SELECT  roleId ,designationId  from DesignationRoles where designationId = " + designationId;
				query = session.createQuery(hql);
			//	query.setParameter("designationId", designationId);
			///	query.setMaxResults(1);
				List<Object[]> roleId =  query.list();
				System.out.println("roleId  :  " +roleId.toString());
				if(roleId != null &&  roleId.size()>0) {
					for(Object[] obj:roleId ) {
					   role = obj[0].toString();
					}
				}
				
				if (role != null) {
					hql = "select rp.featureId from RolePermission rp,FeatureMaster f where f.featureId=rp.featureId and rp.roleId=:roleId";
					query = session.createQuery(hql);
					query.setParameter("roleId", Integer.parseInt(role));
					List<Integer> result = (List<Integer>) query.list();
					if (result != null && !result.isEmpty()) {
						privileges.addAll(result);
					}

				}

				return privileges;
			}
		@Override
		public List<UserDetails> getAllUserDetails() {
			
			List<UserDetails> usersList= new ArrayList();
			session=sessionFactory.getCurrentSession();
			
			org.hibernate.Query query = session.createQuery("SELECT * FROM MFS_USER_MASTER");
	        query.setReadOnly(true);
	        usersList=query.list();
	        return usersList;
		}
		
		@SuppressWarnings("unchecked")
		public String getRoleDetails(long designationId) {

			System.out.println("designationId   :: " + designationId);
			List<RoleDeatilsAuditTO> fileTO = new ArrayList<RoleDeatilsAuditTO>();
			RoleDeatilsAuditTO detailsTO = null;
			Session session = sessionFactory.getCurrentSession();
			String userName = null;

			hql = "SELECT ST.designationId,SDD.roleId,SDD.roleName FROM DesignationHierarchy ST,RoleMaster SDD "
					+ " where  ST.designationId = '" + designationId + "' and SDD.roleId = '" + designationId + "'";

			List<Object[]> objects = session.createQuery(hql).list();
			for (Object[] obj : objects) {
				detailsTO = new RoleDeatilsAuditTO();
				detailsTO.setDesignationId(Integer.parseInt(obj[0] + ""));
				detailsTO.setRoleId(Integer.parseInt(obj[1] + ""));
				detailsTO.setRoleName((obj[2] + ""));
				userName = detailsTO.getRoleName().toString();
				System.out.println("userName  1  " + userName);
				fileTO.add(detailsTO);
			}
			return userName;
		}

}
