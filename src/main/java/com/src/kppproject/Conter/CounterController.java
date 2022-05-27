package com.src.kppproject.Conter;

import org.apache.logging.log4j.Level;
import com.src.kppproject.appLogger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CounterController {

    static int service_calls = 0;
    synchronized public void incrementCalls(){
        service_calls++;
        appLogger.setLog(Level.INFO, "Counter increment");
    }

    @GetMapping("/calls")
    synchronized public int displayCalls(){
        appLogger.setLog(Level.INFO, "Successful ");
        return service_calls;
    }
}