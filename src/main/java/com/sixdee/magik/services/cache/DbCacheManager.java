package com.sixdee.magik.services.cache;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.sixdee.magik.services.dao.impl.UserAuthDAOImpl;
import com.sixdee.magik.services.dao.impl.UserStatusDAOImpl;
import com.sixdee.magik.services.model.UserAuth;
import com.sixdee.magik.services.model.UserStatus;

/**
 * @author Basil Jose
 * @version 1.0.0.0
 * @since Dec 13, 2017 : 3:55:53 PM
 */

@Component
public class DbCacheManager {

    private static Logger LOGGER = LoggerFactory.getLogger(DbCacheManager.class);
    
    @Autowired
    UserStatusDAOImpl userStatusDAOImpl;
    
    @Autowired
    UserAuthDAOImpl authRepo;

  /*  @Autowired
    ErrorCodeRepository errorCodeRepo;

    @Autowired
    UserStatusRepository userStatusRepo;

    

    @Autowired
    ModuleRepository moduleRepo;

  

    @Autowired
    ConfigParamRepository configRepo;

 

    @Cacheable(value = "errorcodes")
    public Map<String, ErrorCode> getErrorCodes() {
        Map<String, ErrorCode> map = new HashMap<String, ErrorCode>();

        try {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("Caching error codes...");
            }

            Iterable<ErrorCode> results = errorCodeRepo.findAll();
            if (results != null) {
                for (ErrorCode obj : results) {
                    map.put(obj.getErrorCode(), obj);
                }
            }

        } catch (Exception e) {
            LOGGER.error("Error while caching error code..!!");
        }
        return map;
    }

   

    @Cacheable(value = "ModuleMaster")
    public List<ModuleMaster> getModules() {
        List<ModuleMaster> list = new ArrayList<>();

        try {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("Caching LocationTypes...");
            }

            Iterable<ModuleMaster> results = moduleRepo.findAll();
            if (results != null) {
                for (ModuleMaster obj : results) {
                    list.add(obj);
                }
            }

        } catch (Exception e) {
            LOGGER.error("Error while caching ModuleMaster..!!" + e, e);
        }
        return list;
    }

   

    @Cacheable(value = "ConfigParams")
    public Map<String, ConfigParam> getConfigParams() {
        Map<String, ConfigParam> map = new HashMap<String, ConfigParam>();

        try {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("Caching config params...");
            }

            Iterable<ConfigParam> results = configRepo.findAll();
            if (results != null) {
                for (ConfigParam obj : results) {
                    map.put(obj.getParamName(), obj);
                }
            }

        } catch (Exception e) {
            LOGGER.error("Error while caching config params..!!");
        }
        return map;
    }
*/
   

    @Cacheable(value = "UserStatus")
    public Map<Integer, String> getUserStatus() {
        Map<Integer, String> map = new HashMap<Integer, String>();

        try {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("Caching UserStatus...");
            }

            Iterable<UserStatus> results = userStatusDAOImpl.getUserStatusList();
            if (results != null) {
                for (UserStatus obj : results) {
                    map.put(obj.getPrimId(), obj.getStatus());
                }
            }
            
        } catch (Exception e) {
            LOGGER.error("Error while caching UserStatus..!!", e);
        }
        return map;
    }
    
    @Cacheable(value = "UserAuth")
    public Map<String, UserAuth> getAuths() {
        Map<String, UserAuth> map = new HashMap<>();

        try {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("Caching Auths...");
            }

          Iterable<UserAuth> results = authRepo.findAll();
            if (results != null) {
                for (UserAuth obj : results) {
                    map.put(obj.getUserName(), obj);
                }
            }

        } catch (Exception e) {
            LOGGER.error("Error while caching UserAuth..!!" + e, e);
        }
        return map;
    }

    /*@CacheEvict(value = "errorcodes", allEntries = true)
    public void errorCodeEvict() {
    }


    @CacheEvict(value = "ModuleMaster", allEntries = true)
    public void moduleMasterEvict() {
    }

    @CacheEvict(value = "UserAuth", allEntries = true)
    public void userAuthEvict() {
    }

    @CacheEvict(value = "ConfigParams", allEntries = true)
    public void configParamsEvict() {
    }

    @CacheEvict(value = "UserStatus", allEntries = true)
    public void userStatusEvict () {
    }*/
 
}
