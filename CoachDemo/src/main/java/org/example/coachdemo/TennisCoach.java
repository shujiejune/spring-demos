package org.example.coachdemo;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class TennisCoach implements Coach {
    private List<String> schedules;

    public TennisCoach() {} {
        schedules = new ArrayList<>();
    }

    @PostConstruct
    public void loadSchedules() {
        System.out.println(">> TennisCoach: inside loadSchedules() - reading file");

        try (
                InputStream inputStream = getClass().getClassLoader().getResourceAsStream("schedules.txt");
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                schedules.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getDailyWorkOutSchedule() {
        Random random = new Random();
        int day = random.nextInt(schedules.size());
        return "Tennis: " + schedules.get(day);
    }
}
