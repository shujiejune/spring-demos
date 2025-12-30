package org.example.coachdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Player {
    private Coach tennisCoach;
    private Coach golfCoach;

    @Autowired
    @Qualifier("footballCoach")
    private Coach footballCoach;

    @Autowired
    public Player(@Qualifier("tennisCoach") Coach tennisCoach) {
        this.tennisCoach = tennisCoach;
    }

    @Autowired
    public void setGolfCoach(@Qualifier("golfCoach") Coach golfCoach) {
        this.golfCoach = golfCoach;
    }

    public void showWorkOut() {
        System.out.println(tennisCoach.getDailyWorkOutSchedule());
        System.out.println(golfCoach.getDailyWorkOutSchedule());
        System.out.println(footballCoach.getDailyWorkOutSchedule());
    }
}
