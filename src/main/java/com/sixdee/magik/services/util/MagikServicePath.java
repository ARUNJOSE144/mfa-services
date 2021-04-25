
package com.sixdee.magik.services.util;

/**
 * @author : ramesh.cheerla
 * @Date : September, 2018
 *
 */
public class MagikServicePath {

	// ------- Authentication ---------//
	public static final String AUTH_LOGIN_CREDENTIALS = "/authlogincrd";
	public static final String RESET_PASSWORD = "/resetpassword";
	public static final String CHANGE_PASSWORD = "/changepassword";

	// ------- Cache ---------//
	public static final String CACHE_MESSAGES = "/cachemessages";
	public static final String USER_SESSION = "/usersession";

//**************************   Start of CAMPIAGIN MESSAGE    **************************
	// ------- Language ---------//
	public static final String GET_LANGUAGES = "/getlanguages";

	public static final String GET_CAMP_LANGUAGEs_DATA = "/getCampaignLanguages";

	// -----SMS----------------//
	public static final String GET_SMS_CATEGORGYs_DATA = "/getSMSCategoryList";
	public static final String DELETE_SMS_CATEGORGY_DATA = "/DeleteSMSCategory";
	public static final String GET_SAVEUPDATE_CATEGORGYs_DATA = "/saveUpdateSMSCategory";
	public static final String GET_SMS_TEMPLATE_MSG_DATA = "/getSMSTemplateData";
	public static final String GET_SMS_SELECTED_DATA = "/getSMSSelected_dtlsData";
	public static final String SAVEUPDATE_SMS_DATA = "/saveorUpdateSMSDtls";
	public static final String DELETE_SMS_MENU_DATA = "/DeleteSMSMenu";

	// -----USSD----------------//
	public static final String GET_USSD_CATEGORGYs_DATA = "/getUSSDCategoryList";
	public static final String DELETE_USSD_CATEGORGY_DATA = "/DeleteUSSDCategory";
	public static final String GET_SAVEUPDATE_USSD_CATEGORGYs_DATA = "/saveUpdateUSSDCategory";
	public static final String GET_USSD_TEMPLATE_MSG_DATA = "/getUSSDTemplateData";
	public static final String GET_USSD_SELECTED_DATA = "/getUSSDSelected_dtlsData";
	public static final String SAVEUPDATE_USSD_DATA = "/saveorUpdateUSSDDtls";
	public static final String DELETE_USSD_MENU_DATA = "/DeleteUSSDMenu";

	// -----WAP----------------//
	public static final String GET_WAP_CATEGORGYs_DATA = "/getWAPCategoryList";
	public static final String DELETE_WAP_CATEGORGY_DATA = "/DeleteWAPCategory";
	public static final String GET_SAVEUPDATE_WAP_CATEGORGYs_DATA = "/saveUpdateWAPCategory";
	public static final String GET_WAP_TEMPLATE_MSG_DATA = "/getWAPTemplateData";
	public static final String GET_WAP_SELECTED_DATA = "/getWAPSelected_dtlsData";
	public static final String SAVEUPDATE_WAP_DATA = "/saveorUpdateWAPDtls";
	public static final String DELETE_WAP_MENU_DATA = "/DeleteWAPMenu";

	// -----MOBILE APP----------------//
	public static final String GET_MOBILE_APP_CATEGORGYs_DATA = "/getMobileAppCategoryList";
	public static final String DELETE_MOBILE_APP_CATEGORGY_DATA = "/DeleteMobileAppCategory";
	public static final String GET_SAVEUPDATE_MOBILE_APP_CATEGORGYs_DATA = "/saveUpdateMOBILEAPPCategory";
	public static final String GET_MOBILE_APP_TEMPLATE_MSG_DATA = "/getMobileAppTemplateData";
	public static final String GET_MOBILE_APP_SELECTED_DATA = "/getMobileAppSelected_dtlsData";
	public static final String SAVEUPDATE_MOBILE_APP_DATA = "/saveorUpdateMobileAppDtls";
	public static final String DELETE_MOBILE_APP_MENU_DATA = "/DeleteMobileAppMenu";

	// ------- Questionform ---------//
	public static final String GET_QUESTIONTYPES = "/getQuestiontypes";
	public static final String SAVE_QUESTIONFORM = "/saveQuestionform";
	public static final String GET_QUESTION_FORMDATA = "/getQuestionformdata";
	public static final String GET_QUESTION_INFODATA = "/getQuestionInfo";
	public static final String DELETE_QUESTION_INFODATA = "/deleteQuestionFormData";

	public static final String GETLANGUAGEs_DATA = "/getLanguagesList";
	// -----WhatsApp----------------//
	public static final String SAVE_UPDATE_WHATSAPP_CATEGORY = "/saveorUpdatewhatsappCategory";
	public static final String GETWHATSAPP_CATEGORGYs_DATA = "/getWhatsAppCategoryList";
	public static final String GET_EDIT_CATEGORY_DATA = "/getWhatsAppCategoryData";
	public static final String SAVE_UPDATE_WHATSAPP_MSG = "/saveorUpdatewhatsappMsg";
	public static final String GET_WHATSAPP_MSG_DATA = "/getWhatsAppMsgData";
	public static final String GET_EDIT_RECORD_DATA = "/getRecordWhatsAppMsgData";
	public static final String UPDATE_WHATSAPP_MSG = "/updatewhatsappMsg";
	public static final String DELETE_WHATSAPP_MSG = "/deleteWhatsAppMsg";

	// ----------Twitter----------//
	public static final String SAVE_UPDATE_TWITTER_MSG = "/saveorUpdateTwitterMsg";
	public static final String GET_TWITTER_MSG_DATA = "/getTwitterMsgData";
	public static final String GET_EDIT_TWITTER_RECORD_DATA = "/getRecordTwitterData";
	public static final String DELETE_TWITTER_MSG = "/deleteTwitterMsg";

	// ----------Telegram----------//
	public static final String SAVE_UPDATE_TELEGRAM_MSG = "/saveorUpdateTelegramMsg";
	public static final String GET_TELEGRAM_MSG_DATA = "/getTelegramMsgData";
	public static final String GET_EDIT_TELEGRAM_RECORD_DATA = "/getRecordTelegramData";
	public static final String DELETE_TELEGRAM_MSG = "/deleteTelegramMsg";

	// ----------FaceBook----------//
	public static final String SAVE_UPDATE_FACEBOOK_MSG = "/saveorUpdateFacebookMsg";
	public static final String GET_FACEBOOK_MSG_DATA = "/getFacebookMsgData";
	public static final String GET_EDIT_FACEBOOK_RECORD_DATA = "/getRecordFacebookData";
	public static final String DELETE_FACEBOOK_MSG = "/deleteFacebookMsg";

	// ----------Skype----------//
	public static final String SAVE_UPDATE_SKYPE_MSG = "/saveorUpdateSkypeMsg";
	public static final String GET_SKYPE_MSG_DATA = "/getSkypeMsgData";
	public static final String GET_EDIT_SKYPE_RECORD_DATA = "/getRecordSkypeData";
	public static final String DELETE_SKYPE_MSG = "/deleteSkypeMsg";

//**************************   End of CAMPIAGIN MESSAGE    **************************	

	// ************************** Start of LOYALTY **************************

	// Tier Details

	// ************************** Start of LOYALTY **************************

	public static final String GET_TIERDTLS = "/getAllTierDetails";
	public static final String SAVEORUPDATE_TIERDTLS = "/saveOrUpdateTierDetails";
	public static final String DELETE_TIERDTLS = "/deleteTierDtls";
	public static final String SAVE_CATEGORY_TIER = "/saveTierCategory";

	// Voucher Details
	public static final String GET_VOUCHERDTLS = "/getAllVoucherDetails";
	public static final String SAVEORUPDATE_VOUCHERDTLS = "/saveOrUpdateVoucherDetails";
	public static final String EDIT_VOUCHERDTLS = "/editVoucherDtls";
	public static final String DELETE_VOUCHERDTLS = "/deleteVoucherDtls";

	// Loyalty Status
	public static final String GET_LOYALTYSTATUS = "/getAllLoyaltyStatus";
	public static final String SAVEORUPDATE_LOYALTYSTATUS = "/saveOrUpdateLoyaltyStatus";
	public static final String EDIT_LOYALTYSTATUS = "/editLoyaltyStatus";
	public static final String DELETE_LOYALTYSTATUS = "/deleteLoyaltyStatus";

	// Account Merging
	public static final String SAVEORUPDATE_ACCOUNTMERGING = "/saveOrUpdateAccountMerging";

	// Account Transfer
	public static final String SAVEORUPDATE_ACCOUNTTRANSFER = "/saveOrUpdateAccountTransfer";

	// ************************** End of LOYALTY *************************

	public static final String SAVE_CONVERSION = "/saveConversion";

	public static final String SAVE_PACKAGE_CATEGORY = "/savePackageCategory";

	public static final String GET_ALL_MERCHANTS = "/getMerchants";
	public static final String GET_MERCHANT_DETAILS = "/getMerchantDetails";
	public static final String EDIT_MERCHANT_DETAILS = "/editMerchantDetails";
	public static final String DELETE_MERCHANT = "/deleteMerchant";
	public static final String SAVE_MERCHANT_DETAILS = "/saveMerchantDetails";

	public static final String GET_ALL_COUNTRIES = "/getCountries";
	public static final String GET_COUNTRY_DETAILS = "/getCountryDetails";
	public static final String EDIT_COUNTRY_DETAILS = "/editCountryDetails";
	public static final String DELETE_COUNTRY = "/deleteCountry";
	public static final String SAVE_COUNTRY_DETAILS = "/saveCountryDetails";

	public static final String GET_ALL_CUSTOMER_CLASS = "/getCustomers";
	public static final String GET_CUSTOMER_DETAILS = "/getCustomerDetails";
	public static final String EDIT_CUSTOMER_DETAILS = "/editCustomerDetails";
	public static final String DELETE_CUSTOMER = "/deleteCustomer";
	public static final String SAVE_CUSTOMER_DETAILS = "/saveCustomerDetails";

	public static final String GET_ALL_CARD_CLASS = "/getCards";
	public static final String GET_CARD_DETAILS = "/getCardDetails";
	public static final String EDIT_CARD_DETAILS = "/editCardDetails";
	public static final String DELETE_CARD = "/deleteCard";
	public static final String SAVE_CARD_DETAILS = "/saveCardDetails";

	public static final String GET_ALL_CYCLE_CODE = "/getCycleCode";
	public static final String GET_CYCLE_DETAILS = "/getCycleDetails";
	public static final String EDIT_CYCLE_DETAILS = "/editCycleDetails";
	public static final String DELETE_CYCLE = "/deleteCycle";
	public static final String SAVE_CYCLE_DETAILS = "/saveCycleDetails";

	public static final String GET_ALL_TENANTS = "/getTenants";
	public static final String GET_TENANT_DETAILS = "/getTenantDetails";
	public static final String EDIT_TENANT_DETAILS = "/editTenantDetails";
	public static final String DELETE_TENANT = "/deleteTenant";
	public static final String SAVE_TENANT_DETAILS = "/saveTenantDetails";

	// ************************** End of LOYALTY **************************
	public static final String GET_TIERCONFIGDTLS = "/getAllTierConfigDetails";
	public static final String GET_CATEGORYGDTLS = "/getAllCategoryDetails";
	public static final String SAVEORUPDATE_TIERCONFIG = "/saveOrUpdateTierConfig";
	public static final String DELETE_TIERCONFIG = "/deleteTierConfig";

	public static final String GET_SEGMENT_and_DRPDWN_DTLS = "/getSegmentandDrpDwnDetails";
	public static final String SAVEORUPDATE_OFFER = "/saveOrUpdateOffer";
	public static final String GET_ALL_CONFIGUREOFFERDTLS = "/getAllConfigureOfferDtls";

	// ************************** End of LOYALTY **************************

//**************************   Start of REPORTS    **************************

	public static final String GET_CAMPIAGN_DATA = "/getCampData";

	// DaywiseCampaignConversion_RPT
	public static final String GET_DAYWISE_CAMPCONV_RPT_DATA = "/getDayWiseRptData";
	public static final String GET_DAYWISE_CAMPCONV_RPT_DATA_WITH_PAGINATION = "/getDayWiseRptDataWithPagination";

	// Impact Performance Report
	public static final String GET_IMPACT_PERFORMANCE_RPT_DATA = "/getImpactPerformanceRptData";

	// ARPU Bands
	public static final String GET_ARPU_BANDS_DATA = "/getARPUBandsData";

	// Incremental Revenue Report
	public static final String GET_INCREMENTAL_REVENUE_RPT_DATA = "/getIncrementalRevenueRptData";

	// MTDvsLMTD Report
	public static final String GET_MTD_LMTD_RPT_DATA = "/getMTDLMTDRptData";

	// Regionwise Offer Impact Report
	public static final String GET_REGIONWISE_OFFER_IMPACT_RPT_DATA = "/getRegionwiseOfferImpactRptData";

	// Circle
	public static final String GET_CIRCLE_DATA = "/getCircleData";

	// RegionWise ROI Report
	public static final String GET_REGIONWISE_ROI_RPT_DATA = "/getRegionwiseROIRptData";

	// RegionWise ROI Report
	public static final String GET_REGION_OFFER_UPTAKE_RPT_DATA = "/getRegionOfferUptakeRptData";
	// Enrolment_RPT
	public static final String GET_ENROLMENT_RPT_DATA = "/getEnrolmentRptData";
	// Programwise_RPT
	public static final String GET_PROGRAMWISE_RPT_DATA = "/getProgramwiseRptData";
	// Reward_RPT
	public static final String GET_REWARD_RPT_DATA = "/getRewardRptData";
	// Leader_RPT
	public static final String GET_LEADER_RPT_DATA = "/getLeaderRptData";
	// RewardExpiry_RPT
	public static final String GET_REWARDEXPIRY_RPT_DATA = "/getRewardExpiryRptData";
	// SubscriberBase_RPT
	public static final String GET_SUBSCRIBERBASE_RPT_DATA = "/getSubscriberBaseRptData";

	// CamPaignBasePerformanceRPT
	public static final String GET_CMP_PER_RPT_RECORDS = "/getAllWeekonWeekRecords";

	// Campaign Information
	// public static final String GET_CAMPIAGNNAMES_DATA = "/getCampNamesData";
	public static final String GET_CAMPAIGNINFORMATION_RPT_DATA = "/getCampaignInformationRptData";

//**************************  End of REPORTS    **************************	

	// -------------Voucher Generation ------------------------//
	public static final String GET_VOUCHER_GEN_DTLS = "/getVoucherList";
	public static final String GENERATE_VOUCHER = "/generateVoucher";

	// -------------Voucher Assigning ------------------------//
	public static final String GET_VOUCHER_ASSIGN_DTLS = "/getVoucherAssignList";
	public static final String ASSIGN_VOUCHER = "/CreateAssignedVoucher";
	public static final String SAVE_VOUCHER_ASSIGN = "/saveVoucherAssign";

	// -------------Upload Voucher ------------------------//
	public static final String GET_VOUCHER_UPLOAD_DTLS = "/getVoucherUploadList";
	public static final String SAVE_VOUCHERUPLOAD = "/saveUploadVoucher";

	// -------------Confgiure category ------------------------//
	public static final String GET_CATEGORY_DTLS = "/getLoyalityCategoryList";
	public static final String SAVE_CATEGORY_DTLS = "/saveCategoryDtls";
	public static final String DELETE_CATEGORY_DTLS = "/deleteCategoryDtls";

	// ------- Campaign ---------//
	public static final String CREATE_CAMAPAIGN = "/createCampaign";
	public static final String CREATE_TASK_PROFILE = "/createTaskProfile";
	public static final String COPY_CAMAPAIGN = "/copyCampaign";
	public static final String DELETE_CAMAPAIGN = "/deleteCampaign";
	public static final String GET_CAMPAIGN_TYPES = "/getCampaignTypes";
	public static final String GET_CAMPAIGNS_LIST = "/getCampaignsList";
	public static final String GET_CAMPAIGN_DETAILS = "/getCampaignDetails";
	public static final String GET_CAMPAIGN_SEGMENT_TYPES = "/getCampaignSegmentTypes";
	public static final String GET_RULES_OF_A_CAMPAIGN = "/getRulesOfACampaign";
	public static final String ATTACH_RULES_TO_A_CAMPAIGN_WHILE_COPY = "/attachRulesToACampaign";
	public static final String UPDATE_PLAY_PAUSE_STATUS = "/updatePlayPauseStatus";
	public static final String GET_CAMPAIGNS_WRT_TYPE = "/getCampaignsWrtType";

	// ------- Menu Details--------//
	public static final String GET_MENU_DETAILS = "/getMenuDetails";

	// ------- Dashboard--------//
	public static final String GET_DASHBOARD_CARD = "/getDasboardCardDetails";

	// Campaign Calender Services
	public static final String GET_CALENDER_DATA = "/getCalenderData";
	public static final String GET_CAMPAIGN_INFO = "/getCampaignInfo";
	public static final String GET_CAMPAIGN_CALENDER_TYPES = "/getCampaignCalenderTypes";
	public static final String GET_ALL_CAMPAIGNS = "/getAllCampaigns";
	public static final String VIEW_PROMOTION = "/viewPromotion";
	public static final String GET_CAMPAIGN_TYPE_INFO = "/getCampaignTypeInfo";

	// ------- Notifications--------//
	public static final String GET_NOTIFICATIONS = "/getnotifications";
	public static final String VIEW_ALL_NOTIFICATIONS = "/viewallnotifications";
	public static final String ADD_NOTIFICATION = "/addnotification";
	public static final String DELETE_NOTIFICATION = "/deleteNotification";
	public static final String SEARCH_NOTIFICATION = "/searchNotification";

	// ------- Groups --------//
	public static final String ADD_GROUP = "/addgroup";
	public static final String GET_GROUPS = "/getgroups";
	public static final String ADD_SUBGROUP = "/addsubgroup";
	public static final String GET_SUBGROUPS = "/getsubgroups";
	public static final String GET_ALL_SUBGROUPS = "/getallsubgroups";

	// ------- Rule Editor--------//
	public static final String SEARCH_PROFILES = "/searchprofiles";

	// ------- KPI --------//
	public static final String ADD_KPI = "/addkpi";
	public static final String GET_KPIS = "/getkpis";
	public static final String GRT_DATA_TYPES = "/getdatatypes";

	// ------- Function Metrics --------//
	public static final String SAVE_FUNCTION_METRIC = "/savefunctionmetrics";
	public static final String GET_FUNCTION_METRICS_LIST = "/getfunctionmetricslist";
	public static final String GET_FUNCTION_METRICS = "/getfunctionmetrics";
	public static final String DELETE_FUNCTION_METRICS = "deletefunctionmetrics";

	// ------- Trigger --------//
	public static final String LOAD_TRIGGER_DATA = "/loadTriggerData";
	public static final String SAVE_TRIGGER = "/saveTrigger";
	public static final String GET_TRIGGER_LIST = "/getTriggersList";
	public static final String GET_TRIGGER = "/getTrigger";
	public static final String DELETE_TRIGGER = "deleteTrigger";

	// ---------- Event ----------//
	public static final String LOAD_EVENT_DATA = "/loadEventData";
	public static final String SAVE_EVENT = "/saveEvent";
	public static final String GET_EVENTS = "/getEvents";
	public static final String GET_EVENTS_WITH_PAGINATION = "/getEventsWithPagination";
	public static final String DELETE_EVENT = "/deleteEvent";

	// -------------Rule -----------//
	public static final String SAVE_RULE = "/saveRule";
	public static final String SAVE_RULE_FROM_CAMPAIGN = "/saveRuleCampaign";
	public static final String SAVE_ATTACHED_RULE = "/saveAttachedRule";
	public static final String GET_RULES = "/getRules";// (including saved and scheduled rules)
	public static final String GET_RULES_WITH_PAGINATION = "/getRulesWithPagination";// (including saved and scheduled
																						// rules)
	public static final String DELETE_RULE = "/deleteRule";// (including saved and scheduled rules)

	// ------- Analytics --------//
	public static final String GET_PRE_POST_ANALYTICS_DATA = "/getPrePostAnalyticsData";
	public static final String GET_PRE_POST_ANALYTICS_DATA_OF_LATEST_CAMPAIGN = "/getPrePostAnalyticsDataOfLatestCampaign";
	public static final String GET_PRE_POST_ANALYTICS_KPIS = "/getPrePostAnalyticsKpis";

	// ------- KPI Metrics Defintion--------//
	public static final String SAVE_KPI_METRICS = "/saveKPIMetrics";
	public static final String GET_KPI_METRICS = "/getKPIMetrics";
	public static final String GET_ALL_KPI_METRICS = "/getAllKPIMetrics";
	public static final String DELETE_KPI_DEFINITION = "/deleteKPIDefintion";

	// ------- Action --------//

	public static final String GET_ACTION_LIST = "/getActionList";
	public static final String GET_ACTION_PARAMETER_LIST = "/getActionParameterList";
	public static final String GET_ACTION_HEADER_PARAMETER_LIST = "/getActionHeaderParameterList";
	public static final String GET_ACTION_DEFAULT_PARAMETER_LIST = "/getActionDefaultParameterList";

	// ------- Action Key --------//

	public static final String LOAD_BUNDLE_TARIFF_DETAILS = "/loadBundleTariffDetails";
	public static final String IS_PROMOTION_DETAILS = "/CreateIspromotionDetails";
	public static final String GET_IS_PROMOTION_DETAILS = "/GetIspromotionDetails";
	public static final String GET_CAMPAIGN_CHANNEL = "/getCampaignChannel";
	public static final String GET_ACTION_KEY_CAMPAIGN_TYPE = "/getActionKeyCampaignType";
	public static final String GET_UPSELL_BUNDLE = "/getUpsellBundle";
	public static final String GET_UPSELL_BUNDLE_PRODUCTS = "/getUpsellBundleProducts";
	public static final String GET_UPSELL_BUNDLE_TARGET_LIST = "/getUpsellBundleProductsTargetList";
	public static final String GET_CREDIT_TYPE = "/getCreditType";

	// ....................... Scheduling ...................//
	public static final String LOAD_SCDHEDULE_DATA = "/loadScheduleData";
	public static final String SCDHEDULE_RULE = "/scheduleRule";
	public static final String GET_RULE_JSON = "/getRuleJson";
	public static final String GET_SCHEDULE_DETAILS_OF_RULE = "/scheduleDetailsOfRule";
	public static final String UPDATE_RULE_STATUS = "/updateRuleStatus";
	public static final String FETCH_RULE_STATUS = "/fetchRuleStatus";
	public static final String GET_RULE_AUDIT_INFO = "/getRuleAuditInfo";
	public static final String APPROVE_OR_REJECT_RULE = "/approveOrRejectRule";
	public static final String GET_RULES_FOR_APPROVAL = "/getRulesForApproval";

	// ------------------------Formula----------------------//
	public static final String SAVE_FORMULA = "/saveFormula";
	public static final String GET_FORMULA_LIST = "/getFormulaList";
	public static final String GET_FORMULA = "/getFormula";
	public static final String DELETE_FORMULA = "/deleteFormula";

	// ---------------Application Properties ------------------//
	public static final String GET_APPLICATION_PROPERTIES = "/getApplicationProperties";
	public static final String CHANGE_PROPERTY_STATUS = "/changePropertyStatus";

	// -----------------URL Configuration-----------------------//
	public static final String GET_URL_LIST = "/getUrlList";
	public static final String ADD_URL = "/addUrl";
	public static final String DELETE_URL = "/deleteUrl";

	// -----------------LEAD POLICY-----------------------//
	public static final String CREATE_LEAD_POLICY = "/createLeadPolicy";
	public static final String GET_LEAD_POLICY_LIST = "/getLeadPolicyList";
	public static final String DELETE_LEAD_POLICY = "/deleteLeadPolicy";

	// -----------------Quarantine-----------------------//
	public static final String CREATE_QUARANTINE_POLICY = "/createQuarantinePolicy";
	public static final String GET_QUARANTINE_POLICY_LIST = "/getQuarantinePolicyList";
	public static final String DELETE_QUARANTINE_POLICY = "/deleteQuarantinePolicy";
	public static final String UPLOAD_BLACKLIST_FILE_DETAILS = "/uploadBlackListFile1";
	public static final String UPLOAD_FILE_CONTENT = "/editQuarantineDetails";
	public static final String EDIT_QUARANTINE_DETAILS = "/editQuarantineDetails";

	// ----------------Contact Policy--------------------//
	public static final String CONTACT_POLICY_GET_SEGMENT_TYPES = "/contactPolicyGetSegmentTypes";
	public static final String CONTACT_POLICY_GET_SEGMENT_CATEGORY_TYPES = "/contactPolicyGetSegmentCategoryTypes";
	public static final String CREATE_CONTACT_POLICY = "/createContactPolicy";
	public static final String GET_CONTACT_POLICY_DETAILS = "/getContactPolicyDetails";
	public static final String DELETE_CONTACT_POLICY = "/deleteContactPolicy";

	// ----------------Menu Shortcuts--------------------//
	public static final String GET_SHORTCUTS = "/getShortcuts";
	public static final String CREATE_SHORTCUTS = "/createShortcuts";
	public static final String DELETE_SHORTCUTS = "/deleteShortcuts";
	public static final String GET_MENU_OPTIONS_FOR_SHORTCUTS = "/getMenuOptionsForShortcuts";

	// -----------------Channel Messages Loading---------------//
	public static final String GET_CHANNEL_MESSAGES = "/getChannelMessages";

	// -------------------What-If-------------------------------//
	public static final String GET_WHAT_IF_ANALYSIS_BASIC_DETAILS = "/getWhatIfAnalysisBasicDetails";

	// ----------------Audit Info--------------------//
	public static final String GET_AUDIT_INFO = "/getAuditInfo";
	public static final String GET_AUDIT_DEFAULT_INFO = "/getAuditDefaultInfo";
	public static final String SAVE_RULE_AUDIT = "/saveRuleAudit";

	// ----------------Sampling--------------------//
	public static final String GET_SAMPLING_LIST = "/getSamplingList";

	// --------------------Geo Fencing Maps ------------------------//
	public static final String GET_GEO_LOCATIONS_TREE = "getGeoLocationsTree";
	public static final String SAVE_GEO_LOCATION = "saveGeoLocation";
	public static final String UPDATE_GEO_LOCATION = "updateGeoLocation";
	public static final String GET_GEO_LOCATION = "getGeoLocation";
	public static final String DELETE_GEO_LOCATION = "deleteGeoLocation";

	// --------------Audience Targeting---------------//
	public static final String SHOW_AUDIENCE_GRAPH = "showAudienceGraph";
	public static final String MANAGE_AUDIENCE = "manageAudience";
	public static final String SHOW_AUDIENCE_COUNTRIES = "audienceCountries";

	// ---------- Email ----------//
	public static final String SAVE_EMAIL = "/saveEmail";
	public static final String GET_MENUS = "/getEmailMenus";
	public static final String DELETE_EMAIL = "/deleteEmail";
	public static final String GET_EMAIL = "/getEmail";
	public static final String EDIT_EMAIL = "/editEmail";

	// --------------Social Media Scheuling---------------//
	public static final String SOCIAL_MEDIA_SCHEDULING = "manageSocialMediaSchedule";

	// --------------Google Ads Management---------------//
	public static final String GOOGLE_ADS_MANAGEMENT = "manageGoogleAds";
	// --------------Telegram Management---------------//
	public static final String TELEGRAM_CHAT_BOT = "manageTelegram";
	public static final String TELEGRAM_BOT_COMMANDS = "ManageBotCommands";
	public static final String TELEGRAM_BOT_COMMANDS_UN = "ManageBotCommands/Command";
	public static final String TELEGRAM_MANAGEMENT = "manageTelegramMessage";
	public static final String TELEGRAM_BOT_MANAGEMENT = "manageTelegramBot";

	// ---------- Personalization ----------//
	public static final String TEMPLATE_SERVLET = "/MessageTemplateServlet";

	// --------------Quarantine Revamp---------------//
	public static final String SPECIAL_DATE_CREATE = "/createSpecialDate";
	public static final String GET_SPECIAL_DATE_LIST = "/getSpecialDateList";
	public static final String DELETE_SPECIAL_DATE = "/deleteSpecialDate";
	public static final String SAVE_FILe_SPECIAL_DATE = "/fileUploadSpecialDate";
	public static final String WEEK_DAY_CREATE = "/createWeekDay";
	public static final String GET_WEEK_DAY_LIST = "/getWeekDay";
	public static final String DELETE_WEEK_DAY = "/deleteWeekDay";
	public static final String CREATE_BLACK_LIST = "/createBlackList";
	public static final String GET_BLACK_LIST = "/getBlackList";
	public static final String DELETE_BLACK_LIST = "/deleteBlackList";
	public static final String SAVE_FILE_BLACK_LIST = "/fileUploadBlackList";
	public static final String CREATE_DND = "/createDND";
	public static final String GET_DND = "/getDND";
	public static final String DELETE_DND = "/deleteDND";
	public static final String CREATE_WHITELIST = "/createWhiteList";
	public static final String GET_WHITELIST = "/getWhiteList";
	public static final String DELETE_WHITELIST = "/deleteWhiteList";
	public static final String SAVE_FILE_WHITELIST = "/fileUploadWhiteList";
	public static final String GET_ACCOUNT_TYPE = "/getAccounType";
	public static final String GET_TARIFF_PLAN = "/getTariffPlan";
	public static final String GET_EXCLUSION = "/getExclusion";
	public static final String SAVE_GLOBAL_FILTER = "/saveGlobalFilter";
	public static final String GET_GLOBAL_FILTER = "/getGlobalFilter";
	public static final String DELETE_GLOBAL_FILTER = "/deleteGlobalFilter";

	// --------------Campaign DashBoard---------------//
	public static final String OVERALL_CAMPAIGN_PUSH = "/overAllCampaignPush";
	public static final String TG_CG_RESPONSE = "/tgCgResponse";
	public static final String ROI_TOTAL_REVENUE = "/roiTotalRevenu";
	public static final String CAMPAIGN_PERFORMANCE = "/campaignPerformance";
	public static final String TG_COUNT = "/tgCount";
	public static final String HOURLY_RESPONSE = "/hourlyResponse";
	public static final String ARPU_SEGMENTS = "/arpuSegments";
	public static final String TOP_5_CAMPAIGN = "/top5Campaign";
	public static final String CAMPAIGN_RESPONSE = "/campaignResponse";
	public static final String TG_COUNT_RESPONSE = "/getTgCountResponse";

	// --------------Loyalty DashBoard---------------//
	public static final String NEW_ENROLLMENTS = "/getNewEnrollments";
	public static final String LOYALTY_POINTS_TREND = "/getLoyaltyPointTrend";
	public static final String MAKASIB_ACCOUNTS = "/getMakasibAccounts";
	public static final String LOYALTY_POINTS_REDEEMED = "/getLoyaltyPointsRedeemed";
	public static final String CUSTOMER_MIGRATION = "/getCustomerMigration";
	public static final String MERCHANTWISE_REDEMPTION = "/getgetMercantiseRedeemption";
	public static final String EARNED_VS_REDEMPTION = "/getEarnedRedeemption";
	public static final String TOTAL_REWARD_POINTS_STATUS = "/getToatlReward";
	public static final String CHANNEL_WISE_REDEMPTION = "/getChannelWiseRedeemption";
	public static final String EARNED_VS_REDEMPTION_1 = "/getEarnedRedeemption_1";

	// --------------Gamification Dashboard---------------//
	public static final String USERS_VS_NONUSERS = "/usersVsNonUsers";
	public static final String USERS_WEB_VS_APP = "/usersWebVsApp";
	public static final String LOYALTY_CAT_WISE_USER_INFO = "/loyaltyCatWiseUserInfo";
	public static final String TOP_FIVE_PLAYED_GAMES = "/topFivePlayedGames";
	public static final String AON_WISE_USERS = "/aonWiseUsers";
	public static final String SLAB_WISE_USER_COUNT = "/slabWiseUserCount";
	public static final String REWARDS_REDEEMED_COUNT = "/rewardsRedeemedCount";
	public static final String SUMMARY_COUNT = "/summaryCount";

	// -------------Role Management------------------------//
	public static final String GET_ROLE_PERMISSIONS = "/getRolePermissions";
	public static final String CREATE_ROLE = "/createRole";
	public static final String VIEW_ROLE = "/viewRole";
	public static final String GET_ROLE_LIST = "/getRoleList";
	public static final String DELETE_ROLE = "/deleteRole";
	public static final String GET_ROLE_LIST_PAGINATION = "/getRoleListWithPagination";

	// -------------Hierarchy Management------------------------//
	public static final String VIEW_HIERARCHY = "/viewHierarchy";
	public static final String CREATE_HIERARCHY_NODE = "/createHierarchyNode";
	public static final String DELETE_HIERARCHY_NODE = "/deleteHierarchyNode";

	public static final String VIEW_DESTINATION_HIERARCHY = "/viewDestinationHierarchy";
	public static final String CREATE_DESIGNATION_HIERARCHY_NODE = "/createDesignationHierarchyNode";
	public static final String DELETE_DESIGNATION_HIERARCHY_NODE = "/deleteDesignationHierarchyNode";

	// ---------- SMS ----------//
	public static final String GET_SMS_CATEGORY = "getSmsCategory";
	// -------------Campaign Message Languages------------------------//
	public static final String GET_CAMPAIGN_MESSAGE_LANGUAGES = "/getCampaignMessageLanguages";

	// -------------Twitter------------------------//
	public static final String SAVE_TWITTER = "/saveTwitter";
	public static final String GET_TWIITER_DETAILS = "/getTwitterDetails";
	public static final String DELETE_TWIITER_DETAILS = "/deleteTwitter";

	// -------------File Download------------------------//
	public static final String GET_FILE_DETAILS = "/getFileDetails";
	public static final String DOWNLOAD_FILE = "/fileDownload";
	public static final String INSERT_FILE = "/ActionConfig";

	// -------------Offline Upload Segment------------------------//
	public static final String UPLOAD_OFFLINE_UPLOAD_SEGMENT = "/uploadOfflineSegment";
	public static final String INSERT_OR_DELETE_SINGLE = "/insertOrDeleteSingle";

	// -------------Remote Copy For Rule------------------------//
	public static final String GET_SESSISON_NAME = "/getSessionName";
	public static final String REMOTE_COPY = "/remoteCopy";
	public static final String GET_PASTE_NAME = "/getPasteName";
	public static final String REMOTE_PASTE = "/remotePaste";

	// -------------Rule Approval ------------------------//
	public static final String Get_DESIGNATION_DETAILS = "/getDesignationDetails";
	public static final String Get_USER_DETAILS = "/getUserDetails";
	public static final String VIEW_USER_DETAILS = "/viewUserDetails";
	public static final String UPDATE_USER_DETAILS = "/updateUserDetails";
	public static final String GET_APPROVAL_AUDIT_INFO = "/getApprovalAuditInfo";
	public static final String SEARCH_APPROVAL_AUDIT_INFO = "/searchApprovalAuditInfo";
	public static final String GET_SEARCH_APPROVAL_AUDIT_INFO = "/getSearchAuditInfo";

	// -------------AppX Notifier ------------------------//

	public static final String XNOTFIY_LANGUAGE = "/AppXLanguage";
	public static final String XNOTFIY_TONE_DETAILS = "/AppXTones";
	public static final String XNOTFIY_TYPES = "/AppXTypes";
	public static final String XNOTFIY_TOPICS = "/AppXTopics";
	public static final String XNOTFIY_SEND_NOTI = "/AppXSchedule";
	public static final String XNOTFIY_MANAGE_SUBSCRIPTIONS = "/AppXManageSubscription";

	// -------------CustomerCare Loyalty ------------------------//
	public static final String CREATE_ACCOUNT = "/createAccountDetails";
	public static final String DELETE_ACCOUNT = "/deleteAccountDetails";
	public static final String GET_USER_PROFILE = "/getUserProfile";
	public static final String GET_SEARCH_TRANSACTION_HISTORY = "/getSearchTranaction";
	public static final String GET_PACKAGES = "/getPackages";
	public static final String GET_REDEEM_POINTS = "/getRedeemPoints";

	// -------------AppX Notifier ------------------------//
	public static final String BLACKLIST_API = "/updateBlacklist";
	public static final String BLACKLIST_API_STATUS = "/blackListNumberStatus";
}
