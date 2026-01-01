package org.example.tennisplayer.repository;

import org.example.tennisplayer.model.Player;
import org.example.tennisplayer.repository.mapper.PlayerRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PlayerRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // retrieve all players
    public List<Player> findAll() {
        String sql = "SELECT * FROM Players";
        return jdbcTemplate.query(sql, new PlayerRowMapper());
    }

    // retrieve a player by id
    public Player findById(int id) {
        String sql = "SELECT * FROM Players WHERE player_id = ?";
        return jdbcTemplate.queryForObject(sql, new PlayerRowMapper(), id);
    }

    // create a new player
    public int save(Player player) {
        String sql = "INSERT INTO Players (first_name, last_name, gender, birth_date, " +
                "height_cm, weight_kg, career_title, career_wins, country, ranking, team_id) VALUES " +
                "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                player.getFirstName(),
                player.getLastName(),
                player.getGender(),
                player.getBirthDate(),
                player.getHeightCm(),
                player.getWeightKg(),
                player.getCareerTitle(),
                player.getCareerWins(),
                player.getCountry(),
                player.getRanking(),
                player.getTeamId());
    }

    // update a player
    public int update(int id, Player player) {
        String sql = "UPDATE Players SET first_name = ?, last_name = ?, gender = ?, birth_date = ?, " +
                "height_cm = ?, weight_kg = ?, career_title = ?, career_wins = ?, country = ?, " +
                "ranking = ?, team_id = ? WHERE player_id = ?";
        return jdbcTemplate.update(sql,
                player.getFirstName(),
                player.getLastName(),
                player.getGender(),
                player.getBirthDate(),
                player.getHeightCm(),
                player.getWeightKg(),
                player.getCareerTitle(),
                player.getCareerWins(),
                player.getCountry(),
                player.getRanking(),
                player.getTeamId(),
                id);
    }

    // delete a player by id
    public int deleteById(int id) {
        String sql = "DELETE FROM Players WHERE player_id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
