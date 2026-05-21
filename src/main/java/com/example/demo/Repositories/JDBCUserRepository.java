package com.example.demo.Repositories;

import com.example.demo.Models.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JDBCUserRepository {

    private final JdbcTemplate jdbcTemplate;

    public JDBCUserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User findByUsername(String username) {

        String sql = "SELECT * FROM users WHERE username = ?";

        try {
            return jdbcTemplate.queryForObject(
                    sql,
                    (resultSet, rowNum) ->
                            new User(
                                    resultSet.getInt("user_id"),
                                    resultSet.getString("username"),
                                    resultSet.getString("password")
                            ),
                    username
            );

        } catch (Exception e) {
            return null;
        }
    }

    public void save(User user) {

        String sql = "INSERT INTO users (username, password) VALUES (?, ?)";

        jdbcTemplate.update(
                sql,
                user.getUsername(),
                user.getPassword()
        );
    }
}
