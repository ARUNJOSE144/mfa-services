package com.sixdee.magik.services.util;

/**
 * @author Basil Jose
 * @version 1.0.0.0
 * @since Dec 12, 2017 : 11:57:08 AM
 */

public class StatusCode {

    /** Success codes 1 - 100. **/
    public final static String SUCCESS              = "0";
    public final static String SUCCESS_MSG          = "Success";
    
    public final static String USER_CREATE_SUCCESS  = "10";
    public final static String RESET_PSWD_SUCCESS   = "11";

    
    /** Failure codes. **/
    public final static String COMMON_FAIL          = "100";
    public final static String UN_AUTHORIZED        = "101";
    public final static String TOKEN_EXPIRED        = "102";
    public final static String AUTHENTICATION_ERROR = "103";
    public final static String RECORD_NOT_FOUND     = "104";
    public final static String USERNAME_EXISTS      = "105";
    public final static String MSISDN_EXISTS        = "106";
    public final static String DESIGNATION_EMPTY    = "107";
    public final static String USER_INACTIVE        = "108";
    public final static String INVALID_OLD_PASSWORD = "109";
    public final static String INVALID_OLD_NEW_PWD  = "110";
    public final static String INVALID_VALUE        = "111";
    public final static String PSWD_RULE_NOT_MET    = "112";
    public final static String LOGIN_ATTEMPTS_EXCEEDS  = "113";
    public final static String SAME_HISTORY_PSWD_REPEAT = "114";
    public final static String EMPLOYEE_ID_EXISTS = "115";
    public final static String ROLE_EXIXTS = "116";
    public final static String INVALID_MAIL = "117";
    public final static String MAILID_EXISTS = "118";
    
    public final static String MULTI_SESSION_FOUND	= "120";

}
