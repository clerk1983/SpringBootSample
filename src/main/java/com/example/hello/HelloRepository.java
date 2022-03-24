package com.example.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class HelloRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Map<String, Object> findById(final String id) {
        final String query = """
                SELECT * FROM employee
                WHERE id = ?
                """;
        return jdbcTemplate.queryForMap(query, id);
    }

}
