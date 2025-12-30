package org.example.coachdemo;

import org.springframework.stereotype.Component;

@Component
public class FootballCoach implements Coach {
    @Override
    public String getDailyWorkOutSchedule() {
        return "Football: Run 100 yards with high knees.";
    }
}
