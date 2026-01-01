package org.example.tennisplayer.repository.mapper;

import org.example.tennisplayer.model.Player;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerRowMapper implements RowMapper<Player> {
    @Override
    public Player mapRow(ResultSet rs, int rowNum) throws SQLException {
        Player player = new Player();
        player.setPlayerId(rs.getInt("player_id"));
        player.setFirstName(rs.getString("first_name"));
        player.setLastName(rs.getString("last_name"));
        player.setGender(rs.getString("gender"));
        player.setBirthDate(rs.getDate("birth_date").toLocalDate());
        player.setHeightCm(rs.getDouble("height_cm"));
        player.setWeightKg(rs.getDouble("weight_kg"));
        player.setCareerTitle(rs.getInt("career_title"));
        player.setCareerWins(rs.getInt("career_wins"));
        player.setCountry(rs.getString("country"));
        player.setRanking(rs.getInt("ranking"));
        player.setTeamId(rs.getInt("team_id"));
        return player;
    }
}
