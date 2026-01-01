package org.example.tennisplayer.repository.mapper;

import org.example.tennisplayer.model.Team;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TeamRowMapper implements RowMapper<Team> {
    @Override
    public Team mapRow(ResultSet rs, int rowNum) throws SQLException {
        Team team = new Team();
        team.setTeamId(rs.getInt("team_id"));
        team.setTeamName(rs.getString("team_name"));
        team.setCountry(rs.getString("country"));
        return team;
    }
}