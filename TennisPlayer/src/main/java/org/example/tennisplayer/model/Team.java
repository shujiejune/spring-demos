package org.example.tennisplayer.model;

import com.fasterxml.jackson.annotation.JsonView;
import org.example.tennisplayer.view.Views;

public class Team {
    @JsonView(Views.Public.class)
    private int teamId;

    @JsonView(Views.Public.class)
    private String teamName;

    @JsonView(Views.Public.class)
    private String country;

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}