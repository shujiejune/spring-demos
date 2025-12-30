package org.example.coachdemo;

import org.springframework.stereotype.Component;

@Component
public class GolfCoach implements Coach {
    @Override
    public String getDailyWorkOutSchedule() {
        return "Golf: Practice putting for 2 hours.";
    }
}
