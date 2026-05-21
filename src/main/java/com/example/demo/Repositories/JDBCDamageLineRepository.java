package com.example.demo.Repositories;

import com.example.demo.Models.DamageLine;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JDBCDamageLineRepository implements DamageLineRepository {

    String url = System.getenv("DB_URL");
    String user = System.getenv("DB_USER");
    String password = System.getenv("DB_PASSWORD");

    @Override
    public List<DamageLine> findAll() {
        List<DamageLine> damageLines = new ArrayList<>();
        String sql = "SELECT * FROM damage_line";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                DamageLine damageLine = new DamageLine(
                        resultSet.getInt("damage_line_id"),
                        resultSet.getInt("report_id"),
                        resultSet.getString("description"),
                        resultSet.getBigDecimal("price")
                );

                damageLines.add(damageLine);
            }

        } catch (SQLException e) {
            System.out.println("Fejl i findAll: " + e.getMessage());
        }

        return damageLines;
    }

    @Override
    public DamageLine findById(int id) {
        String sql = "SELECT * FROM damage_line WHERE damage_line_id = ?";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new DamageLine(
                        resultSet.getInt("damage_line_id"),
                        resultSet.getInt("report_id"),
                        resultSet.getString("description"),
                        resultSet.getBigDecimal("price")
                );
            }

        } catch (SQLException e) {
            System.out.println("Fejl i findById: " + e.getMessage());
        }

        return null;
    }

    @Override
    public List<DamageLine> findByReportId(int reportId) {
        List<DamageLine> damageLines = new ArrayList<>();
        String sql = "SELECT * FROM damage_line WHERE report_id = ?";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, reportId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                DamageLine damageLine = new DamageLine(
                        resultSet.getInt("damage_line_id"),
                        resultSet.getInt("report_id"),
                        resultSet.getString("description"),
                        resultSet.getBigDecimal("price")
                );

                damageLines.add(damageLine);
            }

        } catch (SQLException e) {
            System.out.println("Fejl i findByReportId: " + e.getMessage());
        }

        return damageLines;
    }

    @Override
    public void save(DamageLine damageLine) {
        String sql = "INSERT INTO damage_line (report_id, description, price) VALUES (?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, damageLine.getReportId());
            statement.setString(2, damageLine.getDescription());
            statement.setBigDecimal(3, damageLine.getPrice());

            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Fejl i save: " + e.getMessage());
        }
    }

    @Override
    public void update(DamageLine damageLine) {
        String sql = "UPDATE damage_line SET report_id = ?, description = ?, price = ? WHERE damage_line_id = ?";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, damageLine.getReportId());
            statement.setString(2, damageLine.getDescription());
            statement.setBigDecimal(3, damageLine.getPrice());
            statement.setInt(4, damageLine.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Fejl i update: " + e.getMessage());
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM damage_line WHERE damage_line_id = ?";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Fejl i deleteById: " + e.getMessage());
        }
    }
}