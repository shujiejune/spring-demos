package org.example.tennisplayer.controller;

import org.example.tennisplayer.model.Player;
import org.example.tennisplayer.model.Team;
import org.example.tennisplayer.repository.TeamRepository;
import org.example.tennisplayer.view.Views;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/teams")
public class TeamController {

    @Autowired
    private TeamRepository teamRepository;

    @GetMapping
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    @GetMapping("/{id}/players")
    public MappingJacksonValue getPlayersByTeam(@PathVariable int id, @RequestParam(defaultValue = "false") boolean isAdmin) {
        List<Player> players = teamRepository.findPlayersByTeamId(id);
        MappingJacksonValue wrapper = new MappingJacksonValue(players);
        wrapper.setSerializationView(isAdmin ? Views.Internal.class : Views.Public.class);
        return wrapper;
    }
}