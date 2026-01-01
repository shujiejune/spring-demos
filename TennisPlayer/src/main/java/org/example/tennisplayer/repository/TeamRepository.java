package org.example.tennisplayer.repository;

import org.example.tennisplayer.model.Player;
import org.example.tennisplayer.model.Team;
import org.example.tennisplayer.repository.mapper.PlayerRowMapper;
import org.example.tennisplayer.repository.mapper.TeamRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TeamRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Team> findAll() {
        String sql = "SELECT * FROM Teams";
        return jdbcTemplate.query(sql, new TeamRowMapper());
    }

    public List<Player> findPlayersByTeamId(int teamId) {
        String sql = "SELECT * FROM Players WHERE team_id = ?";
        return jdbcTemplate.query(sql, new PlayerRowMapper(), teamId);
    }
}