
package com.example.demo.Repositories;

import com.example.demo.Models.DamageReport;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Repository
public class JDBCDamageReportRepository implements DamageReportRepository {

    String url = System.getenv("DB_URL");
    String user = System.getenv("DB_USER");
    String password = System.getenv("DB_PASSWORD");

    @Override
    public List<DamageReport> findAll() {
        List<DamageReport> damageReports = new ArrayList<>();
        String sql = "SELECT * FROM damage_report";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                DamageReport damageReport = new DamageReport(
                        resultSet.getInt("report_id"),
                        resultSet.getDate("report_date").toLocalDate(),
                        resultSet.getString("description")
                );
                damageReports.add(damageReport);
            }
        } catch (SQLException e) {
            System.out.println("fejl: kunne ikke hente skader" + e.getMessage());
        }
        return null;
    }
    @Override
    public DamageReport findById(int id) {
        String sql = "SELECT * FROM damage_report WHERE report_id = ?";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new DamageReport(
                        resultSet.getInt("report_id"),
                        resultSet.getDate("report_date").toLocalDate(),
                        resultSet.getString("description")
                );
            }

        } catch (SQLException e) {
            System.out.println("fejl: kunne ikke finde skade " + e.getMessage());
        }

        return null;
    }

    @Override
    public void save(DamageReport damageReport) {
        String sql = "INSERT INTO damage_report (report_date, description) VALUES (?, ?)";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDate(1, Date.valueOf(damageReport.getReportDate()));
            statement.setString(2, damageReport.getDescription());

            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("fejl: kunne ikke gemme skade" + e.getMessage());
        }
    }
    @Override
    public void update(DamageReport damageReport) {
        String sql = "UPDATE damage_report SET report_date = ?, description = ? WHERE report_id = ?";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDate(1, Date.valueOf(damageReport.getReportDate()));
            statement.setString(2, damageReport.getDescription());
            statement.setInt(3, damageReport.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("fejl: kunne ikke opdatere skade" + e.getMessage());
        }

    }
    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM damage_report WHERE report_id = ?";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("fejl: kunne ikke slette skade " + e.getMessage());
        }
    }
}
