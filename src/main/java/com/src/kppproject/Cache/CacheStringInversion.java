package com.src.kppproject.Cache;

import com.src.kppproject.StringInversion.StringInversionService;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.src.kppproject.appLogger;
import java.util.HashMap;
import java.util.Map;

@Component
public class CacheStringInversion {
    private Map stringMap;

    public CacheStringInversion() {
        stringMap = new HashMap<String, String>();
    }

    public Boolean isCached(String key){
        return stringMap.containsKey(key);
    }

    public void addValueToCache(String key, String value){
        if(!stringMap.containsKey(key)){
            appLogger.setLog(Level.INFO, key + " was added to cache");
            stringMap.put(key, value);
        }
    }

    public String getCachedValue(String key){
        if(stringMap.containsKey(key)){
            appLogger.setLog(Level.INFO, key + " was found in cache");
            return stringMap.get(key).toString();
        }
        appLogger.setLog(Level.INFO, key + " was not found in cache");
        return null;
    }

}
