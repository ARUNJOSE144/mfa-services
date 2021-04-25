package com.sixdee.magik.services.util;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Basil Jose
 * @version 1.0.0.0
 * @since Dec 12, 2017 : 11:57:08 AM
 */

public class Globals {
	
	@SuppressWarnings("unchecked")
	public static Map<String, String>  jsonToMap(final String json) {
		try {
			
			Map<String,String> map = new HashMap<String,String>();

			ObjectMapper mapper = new ObjectMapper();

			map = mapper.readValue(json, HashMap.class);
		
			return map;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> Object  jsonToObject(final String json, Object t) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.readValue(json, (Class<T>) t);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static <T> List<T> jsonStringToList(final String json) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			return  mapper.readValue(json, new TypeReference<List<T>>(){});
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static <T> String listTojson(List<T> list) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString( list);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	

	public static <T> boolean isNull(T reference) {
		if (reference == null || reference.equals("")) {
			return true;
		}
		return false;
	}
	
	public static boolean isInteger(String input) {
        try {
            Integer.valueOf(input);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
	
	public static boolean isLong(String input) {
	    try {
	        Long.valueOf(input);
	    } catch (Exception e) {
	        return false;
	    }
	    return true;
	}
	

    public static int getDayOfWeek() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DAY_OF_WEEK);
    }
    
    public static int getWeekOfMonth() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.WEEK_OF_MONTH);
    }
}
