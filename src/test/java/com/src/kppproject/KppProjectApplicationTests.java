package com.src.kppproject;

import com.src.kppproject.Cache.CacheStringInversion;
import com.src.kppproject.StringInversion.StringInversionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class KppProjectApplicationTests {

    CacheStringInversion stringCache = new CacheStringInversion();

    StringInversionService stringInversionService = new StringInversionService();

    @Test
    void contextLoads() {
    }

    @Test
    void CacheTest(){
        String testStr = "MyString";
        String first_test_str = stringInversionService.reverse(testStr);
        stringCache.addValueToCache(testStr, first_test_str);
        String second_test_str = stringCache.getCachedValue(testStr);

        assertEquals(first_test_str, second_test_str);
    }
}
