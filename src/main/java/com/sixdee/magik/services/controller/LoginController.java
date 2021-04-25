package com.sixdee.magik.services.controller;

import static com.sixdee.magik.services.util.Globals.asJsonString;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sixdee.magik.services.cache.DbCacheManager;
import com.sixdee.magik.services.controller.bean.LoginRequest;
import com.sixdee.magik.services.controller.bean.LoginResponse;
import com.sixdee.magik.services.dao.impl.CacheDaoImpl;
import com.sixdee.magik.services.dao.impl.TokenMasterDAOImpl;
import com.sixdee.magik.services.dao.impl.UserDAOImpl;
import com.sixdee.magik.services.exception.CommonException;
import com.sixdee.magik.services.exception.NoObjectMatchingException;
import com.sixdee.magik.services.exception.TokenExpiryException;
import com.sixdee.magik.services.exception.TokenInvalidException;
import com.sixdee.magik.services.model.TokenMaster;
import com.sixdee.magik.services.model.UserMaster;
import com.sixdee.magik.services.redis.TokenMasterRedis;
import com.sixdee.magik.services.redis.TokenRepositoryRedisDAO;
import com.sixdee.magik.services.service.AuthenticationService;
import com.sixdee.magik.services.util.ExceptionUtil;
import com.sixdee.magik.services.util.MDCConstants;
import com.sixdee.magik.services.util.MySessionUtil;
import com.sixdee.magik.services.util.RestInfo;
import com.sixdee.magik.services.util.StatusCode;
import com.sixdee.magik.services.util.SystemConstants;
import com.sixdee.magik.services.util.SystemProperties;

@RestController
public class LoginController {

	static final Logger LOGGER = Logger.getLogger(UserController.class);

	@Autowired
	SystemProperties properties;

	@Autowired
	UserDAOImpl userDAOImpl;

	@Autowired
	DbCacheManager cache;

	@Autowired
	TokenRepositoryRedisDAO tokenRepositoryRedis;

	@Autowired
	TokenMasterDAOImpl tokenMasterDAOimpl;

	@Autowired
	private RedisTemplate<String, TokenMasterRedis> redisTemplate;

	@Autowired
	AuthenticationService authenticationService;

//	@GetMapping(value="/")
//	public String welcome()
//	{
//		return "REST-service is running";
//	}
//	
	String sessionVal = null;
	String userNameVal = null;

	@RequestMapping(value = "/v1/login", method = { RequestMethod.POST })
	public ResponseEntity<LoginResponse> login(@Valid @RequestBody final LoginRequest request,
			@RequestAttribute(value = "Token-Auth", required = false) String tokenAuth,
			HttpServletRequest httpRequest) {

		String channel = MDC.get(MDCConstants.CHANNEL_ID);
		Date startTime = new Date();

		/* String tokenAuth="Basic aW50ZXJmYWNlX3dlYl91c2VyOjk4OHNkc2RAdHU="; */
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("LoginController login Request: {} " + asJsonString(request) + " from Channel : " + channel);
			LOGGER.info("LoginController HTTP SERVLET Request: IP ADDRESS : " + httpRequest.getRemoteAddr());
		}
		sessionVal = httpRequest.getSession().getId();
		MySessionUtil.sessionMap.put(sessionVal, httpRequest.getSession());
		System.out.println("Session ID::::::: " + httpRequest.getSession().getId());
		System.out.println("sessionMap size::::::: " + MySessionUtil.sessionMap.size());

		String passsword = request.getPassword();
		String encryptedPassword = null;
		UserMaster user = null;

		try {
			// Create MessageDigest instance for MD5
			MessageDigest md = MessageDigest.getInstance("MD5");
			// Add password bytes to digest
			md.update(passsword.getBytes());
			// Get the hash's bytes
			byte[] bytes = md.digest();
			// This bytes[] has bytes in decimal format;
			// Convert it to hexadecimal format
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			// Get complete hashed password in hex format
			encryptedPassword = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		LoginResponse response = null;
		try {
			response = new LoginResponse();

			String username = "";
			String userId = "";

			/*
			 * Set<String> keys = redisTemplate.keys("*"); Iterator<String> it =
			 * keys.iterator();
			 */

			/*
			 * while(it.hasNext()){
			 * System.out.println("=========Came to remove token=============>");
			 * redisTemplate.opsForValue().getOperations().delete(it.next()); }
			 */

			System.out.println("User name==================>" + request.getUsername());
			user = userDAOImpl.findByUserName(request.getUsername());
			System.out.println("User Object===============>" + user);

			LOGGER.info("the current status of the user is :: " + user.getStatus());
			if (user != null && user.getStatus() != SystemConstants.ACTIVE) {
				String status = "";
				Map<Integer, String> userStatus = cache.getUserStatus();
				if (userStatus != null && !userStatus.isEmpty() && userStatus.get((int) user.getStatus()) != null) {
					status = userStatus.get((int) user.getStatus());
				}
				LOGGER.info("the current status of the user is :: " + user.getStatus() + " : " + status);
				throw new CommonException(
						properties.getUserInactiveMessage() != null && !properties.getUserInactiveMessage().equals("")
								? properties.getUserInactiveMessage()
								: "User Inactive!");
			}

			if (!user.getPassword().equals(encryptedPassword)) {
				if (user.getPswdAttempts() + 1 >= properties.getLoginAttempts()) {
					LOGGER.info("User has attempted more than " + properties.getLoginAttempts()
							+ " times with the wrong password. So blocking user with userId : " + user.getUserId());
					userDAOImpl.changeStatus(user.getUserId(), SystemConstants.USER_BLOCK);
					LOGGER.info("Updating User's wrong password attempts :: from=" + user.getPswdAttempts() + " - to="
							+ (user.getPswdAttempts() + 1) + " of userId:" + user.getUserId());
					userDAOImpl.updateWrongPasswordAttempts(user.getUserId(), user.getPswdAttempts() + 1);
					throw new CommonException(properties.getBlockUserForWrongPasswordAttempts());
				} else {
					LOGGER.info("Updating User's wrong password attempts :: from=" + user.getPswdAttempts() + " - to="
							+ (user.getPswdAttempts() + 1) + " of userId:" + user.getUserId());
					userDAOImpl.updateWrongPasswordAttempts(user.getUserId(), user.getPswdAttempts() + 1);
					throw new Exception("Invalid Credentials!");
				}
			}

			response.setUserId(user.getUserId() + "");
			response.setMsisdn(user.getMsisdn());
			response.setFullName(user.getFirstName() + " "
					+ (user.getLastName() == null || user.getLastName().equals("") ? "" : user.getLastName()));
			response.setForceChangePassword(user.isForcepswd());
			// response.setUserName(Base64.getEncoder().encodeToString(user.getUsername().getBytes()));
			response.setUserName(user.getUsername());
			response.setChannelType(user.getChannelType());
			response.setEntityId(user.getEntityId());
			response.setDesignationId(user.getDesignationId());

			// Role id and role name for audit
			String roleDetails = userDAOImpl.getRoleDetails(user.getDesignationId());
			System.out.println("roleDetails ===============>" + roleDetails);
			if (roleDetails != null) {
				response.setRoleName(roleDetails);
			}
			// Approval Deatils

			response.setIsApprovalRequired(user.getIsApprovalRequired());
			response.setApprovalTypeFlag(user.getApprovalTypeFlag());
			response.setParentId(user.getParentId());
			response.setChannelTypeIdName(user.getChannelTypeIdName());
			response.setDesignationName(user.getDesignationName());
			/*
			 * response.setPrivilages(new HashSet<Integer>(
			 * user.getRoles().stream().flatMap(list -> list.getFeatureList().stream())
			 * .map(FeatureMaster::getFeatureId).collect(Collectors.toList())));
			 */

			response.setPrivilages(userDAOImpl.getPrivilegesByDesignation(user.getDesignationId()));
			response.setResultCode(StatusCode.SUCCESS);
			response.setResponseMsg("Success");
			response.setResultCode(StatusCode.SUCCESS);

			username = user.getUsername();
			userId = user.getUserId() + "";

			if (user.getPswdExpirydate() != null && user.getPswdExpirydate().before(new Date())) {
				if (LOGGER.isInfoEnabled()) {
					LOGGER.info("Password Expired....");
				}
				userDAOImpl.changeForcePswd(user.getUserId());
				response.setForceChangePassword(true);
			}

			if (user.getPswdAttempts() != 0) {
				LOGGER.info("Updating User's wrong password attempts :: from=" + user.getPswdAttempts() + " -to=0");
				userDAOImpl.updateWrongPasswordAttempts(user.getUserId(), 0);
			}

//			if (StringUtils.isNotBlank(tokenAuth) && tokenAuth.equals("true")) {
//				boolean isMultiSession = (request.getIsMultiSession() != null && request.getIsMultiSession().equalsIgnoreCase("true")) ? true : false;
//				LOGGER.info("MULTI-SESSION ENABLED - "+properties.getEnableMultipleSessions());
//				if(properties.getEnableMultipleSessions()) {
//					String uid = generateToken();
//					String uid2 = generateRefreshToken();
//					TokenMasterRedis tokenMaster = new TokenMasterRedis(uid, username, userId, uid2);
//					tokenRepositoryRedis.addToken(tokenMaster.getToken(), tokenMaster.getUserId(), tokenMaster);
//					response.setToken(uid.toString());
//					response.setRefreshToken(uid2.toString());
//					LOGGER.info("The uid value is :" + uid.toString());
//					LOGGER.info("The uid2 value is :" + uid2.toString());
//					tokenRepositoryRedis.deleteUserId(userId);
//					tokenMasterDAOimpl.generateToken(uid, userId);
//					response.setResultCode(StatusCode.SUCCESS);
//					response.setResponseMsg("Success");
//				}else {
//					LOGGER.info("USER AND TOKEN EXISTS ? " + tokenRepositoryRedis.hasKey(userId));
//					if(tokenRepositoryRedis.hasKey(userId) && !isMultiSession) {
//						LOGGER.info("MULTISESSION FOUND");
//						response.setResultCode(StatusCode.MULTI_SESSION_FOUND);
//						response.setResponseMsg(properties.getMultiSessionFoundMessage() != null && !properties.getMultiSessionFoundMessage().equals("") ? properties.getMultiSessionFoundMessage(): "Multi Sesion Found!");
//					}else {
//						String uid = generateToken();
//						String uid2 = generateRefreshToken();
//						TokenMasterRedis tokenMaster = new TokenMasterRedis(uid, username, userId, uid2);
//						if(tokenRepositoryRedis.hasKey(userId)) {
//							LOGGER.info("DELETING TOKEN - " + userId );
//							tokenRepositoryRedis.deleteUserId(userId);
//							tokenMasterDAOimpl.deleteLastToken(userId);
//						}
//						tokenRepositoryRedis.addToken(tokenMaster.getUserId(), tokenMaster.getToken(), tokenMaster);
//						response.setToken(uid.toString());
//						response.setRefreshToken(uid2.toString());
//						LOGGER.info("The uid value is :" + uid.toString());
//						LOGGER.info("The uid2 value is :" + uid2.toString());
//						tokenMasterDAOimpl.generateToken(uid, userId);
//						response.setResultCode(StatusCode.SUCCESS);
//						response.setResponseMsg("Success");
//					}
//				}
//			}

			if (StringUtils.isNotBlank(tokenAuth) && tokenAuth.equals("true")) {
				String uid = generateToken();
				String uid2 = generateRefreshToken();
				TokenMasterRedis tokenMaster = new TokenMasterRedis(uid, username, userId, uid2);
				tokenRepositoryRedis.addToken(tokenMaster.getToken(), tokenMaster.getUserId(), tokenMaster);
				response.setToken(uid.toString());
				response.setRefreshToken(uid2.toString());
				// tokenMasterDAOimpl.deleteLastToken(userId);
				userNameVal = username;
				tokenMasterDAOimpl.generateToken(uid, userId, sessionVal, userNameVal);

				// Session Name
				if (user.getUserId() != null && uid != null) {
					List<TokenMaster> sessName = tokenMasterDAOimpl.getSessionname(uid, userId);
					System.out.println("sessName  " + sessName.toString());
					response.setSessionName(sessName);
				}

			}
			if (LOGGER.isInfoEnabled()) {
				LOGGER.info("LoginController login Response: {} " + asJsonString(response));
			}
		} catch (CommonException e) {
			// TODO: handle exception
			LOGGER.error("LoginController :-> CommonException : " + e.getMessage(), e);
			response.setResultCode(StatusCode.COMMON_FAIL);
			response.setResponseMsg(e.getMessage());
		} catch (NoObjectMatchingException e) {
			// TODO: handle exception
			LOGGER.error("LoginController :-> NoObjectMatchingException : " + e.getMessage(), e);
			response.setResultCode(StatusCode.COMMON_FAIL);
			response.setResponseMsg(e.getMessage());
		} catch (Exception e) {
			response.setResultCode(StatusCode.COMMON_FAIL);
			response.setResponseMsg("Invalid Credentials.. Please Try with valid Credentials ");
			LOGGER.error("LoginController :-> Exception : " + e.getMessage(), e);
		} finally {
			/*
			 * audit.save(request.getUsername(), "LOGIN", httpRequest, (response != null &&
			 * response.getResultCode() != null &&
			 * (response.getResultCode().equals(StatusCode.SUCCESS) ||
			 * response.getResultCode().equals(StatusCode.MULTI_SESSION_FOUND))) ? "0" :
			 * "1", false, startTime);
			 */
		}
		return new ResponseEntity<LoginResponse>(response, HttpStatus.OK);
	}

	private LoginResponse prepareLoginResp(LoginResponse response) {
		response.setUserId("");
		response.setMsisdn("");
		response.setFullName("");
		response.setUserName("");
		response.setEntityId("");
		response.setPrivilages(new HashSet<>());
		response.setToken("");
		response.setTokenExpiry(new Date());
		response.setRefreshToken("");
		response.setResultCode("");
		response.setResponseMsg("");
		return response;
	}

	private String generateToken() {
		return UUID.randomUUID().toString();
	}

	private String generateRefreshToken() {
		return UUID.randomUUID().toString();
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/v1/logout", method = RequestMethod.POST)
	public ResponseEntity<LoginResponse> logout(@RequestHeader(value = "X-UserId") String userId,
			HttpServletRequest httpRequest) {
		LoginResponse response = new LoginResponse();
		Date startTime = new Date();
		System.err.println(userId);
		try {
			/*
			 * tokenRepositoryRedis.deleteUserId(userId);
			 * tokenMasterDAOimpl.deleteLastToken(userId);
			 */

			// tokenService.delete(userMaster.getUsername());
			if (tokenRepositoryRedis.hasKey(userId)) {
				LOGGER.info("DELETING TOKEN - " + userId);
				tokenRepositoryRedis.deleteUserId(userId);
				tokenMasterDAOimpl.deleteLastToken(userId);
			}
			response.setResultCode(StatusCode.SUCCESS);
			response.setResponseMsg("Success");

		} catch (Exception e) {
			response.setResultCode(StatusCode.COMMON_FAIL);
			response.setResponseMsg("Failure");
			LOGGER.error("LoginController :-> Exception : " + e.getMessage(), e);
		} finally {
			/*
			 * audit.save(userId, "LOGOUT", httpRequest, (response != null &&
			 * response.getResultCode() != null &&
			 * response.getResultCode().equals(StatusCode.SUCCESS)) ? "0" : "1", true,
			 * startTime);
			 */
		}
		return new ResponseEntity<LoginResponse>(response, HttpStatus.OK);

	}

	@RequestMapping(value = "/v1/relogin", method = { RequestMethod.GET })
	public ResponseEntity<LoginResponse> relogin(@RequestHeader(value = "refreshtoken") String refreshtoken,
			@RequestHeader(value = "accesstoken") String token, @RequestHeader(value = "userId") String userId,
			HttpServletRequest httpRequest) {
		LoginResponse response = new LoginResponse();
		Date startTime = new Date();

		try {
			if (LOGGER.isInfoEnabled()) {
				LOGGER.info("LoginController relogin Request: Token:{}, RefreshToken:{} " + token + " " + refreshtoken);
			}

			prepareLoginResp(response);

			UsernamePasswordAuthenticationToken requestAuthentication = new UsernamePasswordAuthenticationToken(userId,
					token);

			TokenMasterRedis redisObj = tokenRepositoryRedis.getToken((String) requestAuthentication.getCredentials(),
					(String) requestAuthentication.getPrincipal());

			boolean isTokenGenerated = tokenMasterDAOimpl.findByUserIdAndToken(userId, token);

			if (isTokenGenerated) {
				if (redisObj == null) {
					LOGGER.error("Token Expired. TokenMasterRedis is null.");
					throw new TokenExpiryException("Token Expired.");
				}
			} else {
				LOGGER.error("Invalid Token. TokenMasterRedis is null.");
				throw new TokenInvalidException("Invalid Token.");
			}

			if (!redisObj.getRefreshToken().equals(refreshtoken)) {
				LOGGER.error("Invalid Refresh Token : " + refreshtoken);
				throw new TokenInvalidException("Invalid Refresh Token.");
			}

			String uid = generateToken();
			String uid2 = generateRefreshToken();

			TokenMasterRedis tokenMaster = new TokenMasterRedis(uid, userId, userId, uid2);
			LOGGER.info("USER AND TOKEN EXISTS ? " + tokenRepositoryRedis.hasKey(tokenMaster.getUserId()));
			if (tokenRepositoryRedis.hasKey(tokenMaster.getUserId())) {
				LOGGER.info("DELETING TOKEN - " + tokenMaster.getUserId());
				tokenMaster.getToken();
				tokenRepositoryRedis.deleteUserId(tokenMaster.getUserId());
			}
			tokenRepositoryRedis.addToken(tokenMaster.getUserId(), tokenMaster.getToken(), tokenMaster);
			tokenMasterDAOimpl.deleteLastToken(userId);
			// tokenMasterDAOimpl.generateToken(uid, userId, sessionVal);
			tokenMasterDAOimpl.generateToken(uid, userId, sessionVal, userNameVal);

			response.setToken(uid.toString());
			response.setRefreshToken(uid2.toString());
			response.setResultCode(StatusCode.SUCCESS);
			response.setResponseMsg("Success");

			if (LOGGER.isInfoEnabled()) {
				LOGGER.info("LoginController login Response: {} " + asJsonString(response));
			}

		} catch (TokenExpiryException te) {
			LOGGER.error("TokenExpiryException :-> " + te.getMessage());
			throw te;

		} catch (TokenInvalidException tie) {
			LOGGER.error("TokenInvalidException :-> " + tie.getMessage());
			throw tie;

		} catch (Exception ex) {
			LOGGER.error("Exception :-> " + ex.getMessage());
			throw new TokenInvalidException("Insufficient Privilege.");
		} finally {
			/*
			 * audit.save(userId, "RE_LOGIN", httpRequest, (response != null &&
			 * response.getResultCode() != null &&
			 * response.getResultCode().equals(StatusCode.SUCCESS)) ? "0" : "1", true,
			 * startTime);
			 */
		}
		return new ResponseEntity<LoginResponse>(response, HttpStatus.OK);
	}

	@PostMapping("/changeUserPassword")
	public @ResponseBody RestInfo<String> changePassword(HttpServletRequest httpServletRequest,
			@RequestBody UserMaster userMasterTO) {

		LOGGER.info("================== Logincontroller Reset Password API Start =====================");
		LOGGER.info("Class : Logincontroller | Method : changePassword");

		RestInfo<String> info = new RestInfo<String>();
		int statusCode = 0;

		try {
			statusCode = authenticationService.ChangeUserPassword(userMasterTO);
			info.setOperationCode(statusCode);
			info.setData(CacheDaoImpl.messages.get(statusCode));
			info.setMessage("Success");
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			LOGGER.error("Class : Logincontroller  |  Method : changePassword " + e);
			e.printStackTrace();
			info.setMessage(CacheDaoImpl.messages.get(7));
		}

		LOGGER.info("==================Logincontroller changePassword API End =====================");

		return info;

	}

}
