package org.example.bakery;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PastryRowMapper implements RowMapper<Pastry> {
    @Override
    public Pastry mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Pastry(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getFloat("price")
        );
    }
}
