package org.example.tennisplayer.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import org.example.tennisplayer.view.Views;

import java.time.LocalDate;
import java.time.Period;

public class Player {
    @JsonView(Views.Public.class)
    private int playerId;

    @JsonView(Views.Public.class)
    private String firstName;

    @JsonView(Views.Internal.class) // Only visible to Admins
    private String lastName;

    @JsonView(Views.Internal.class)
    private String gender;

    @JsonView(Views.Public.class)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @JsonView(Views.Internal.class)
    private double heightCm;

    @JsonView(Views.Internal.class)
    private double weightKg;

    @JsonView(Views.Public.class)
    private int careerTitle;

    @JsonView(Views.Public.class)
    private int careerWins;

    @JsonView(Views.Public.class)
    private String country;

    @JsonView(Views.Public.class)
    private int ranking;

    private int teamId;

    @JsonView(Views.Public.class)
    public int getAge() {
        if (this.birthDate == null) return 0;
        return Period.between(this.birthDate, LocalDate.now()).getYears();
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public double getHeightCm() {
        return heightCm;
    }

    public void setHeightCm(double heightCm) {
        this.heightCm = heightCm;
    }

    public double getWeightKg() {
        return weightKg;
    }

    public void setWeightKg(double weightKg) {
        this.weightKg = weightKg;
    }

    public int getCareerTitle() {
        return careerTitle;
    }

    public void setCareerTitle(int careerTitle) {
        this.careerTitle = careerTitle;
    }

    public int getCareerWins() {
        return careerWins;
    }

    public void setCareerWins(int careerWins) {
        this.careerWins = careerWins;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }
}
