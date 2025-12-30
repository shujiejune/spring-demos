package org.example.bakery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PastryRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void create(String name, float price) {
        jdbcTemplate.update("INSERT INTO Pizza (name, price) VALUES (?, ?)", name, price);
    }

    public List<Pastry> findAll() {
        return jdbcTemplate.query("SELECT * FROM Pizza", new PastryRowMapper());
    }

    public Pastry findById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM Pizza WHERE id = ?", new PastryRowMapper(), id);
    }

    public void update(int id, String name, float price) {
        jdbcTemplate.update("UPDATE Pizza SET name = ?, price = ? WHERE id = ?", name, price, id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Pizza WHERE id = ?", id);
    }
}
