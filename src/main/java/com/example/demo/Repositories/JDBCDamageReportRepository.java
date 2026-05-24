package com.example.demo.Repositories;

import com.example.demo.Models.DamageLine;
import com.example.demo.Models.DamageReport;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JDBCDamageReportRepository implements DamageReportRepository {

    String url = System.getenv("DB_URL");
    String user = System.getenv("DB_USER");
    String password = System.getenv("DB_PASSWORD");

    @Override
    public List<DamageReport> findAll() {
        Map<Integer, DamageReport> reports = new LinkedHashMap<>();

        String sql = """
                SELECT 
                    dr.report_id,
                    dr.car_id,
                    dr.report_date,
                    dr.description AS report_description,
                    dl.damage_line_id,
                    dl.description AS line_description,
                    dl.price,
                    COALESCE(SUM(dl.price) OVER(PARTITION BY dr.report_id), 0) AS total_price
                FROM damage_report dr
                LEFT JOIN damage_line dl 
                    ON dr.report_id = dl.report_id 
                    ORDER BY dr.report_id DESC
                """;

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int reportId = resultSet.getInt("report_id");

                DamageReport report = reports.get(reportId);

                if (report == null) {
                    report = new DamageReport(
                            resultSet.getInt("report_id"),
                            resultSet.getInt("car_id"),
                            resultSet.getDate("report_date").toLocalDate(),
                            resultSet.getString("report_description")
                    );

                    report.setTotalPrice(resultSet.getBigDecimal("total_price"));

                    reports.put(reportId, report);
                }

                int damageLineId = resultSet.getInt("damage_line_id");

                if (damageLineId != 0) {
                    DamageLine damageLine = new DamageLine(
                            damageLineId,
                            reportId,
                            resultSet.getString("line_description"),
                            resultSet.getBigDecimal("price")
                    );

                    report.addDamageLine(damageLine);
                }
            }

        } catch (SQLException e) {
            System.out.println("Fejl: kunne ikke hente skader " + e.getMessage());
        }

        return new ArrayList<>(reports.values());
    }

    @Override
    public DamageReport findById(int id) {
        String sql = "SELECT dr.report_id, dr.car_id, dr.report_date, dr.description AS report_description, dl.damage_line_id, dl.description AS line_description, dl.price FROM damage_report dr LEFT JOIN damage_line dl ON dr.report_id = dl.report_id WHERE dr.report_id = ?";

        DamageReport report = null;

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                if (report == null) {
                    report = new DamageReport(
                            resultSet.getInt("report_id"),
                            resultSet.getInt("car_id"),
                            resultSet.getDate("report_date").toLocalDate(),
                            resultSet.getString("report_description")
                    );
                }

                int damageLineId = resultSet.getInt("damage_line_id");

                if (damageLineId != 0) {
                    DamageLine damageLine = new DamageLine(
                            damageLineId,
                            resultSet.getInt("report_id"),
                            resultSet.getString("line_description"),
                            resultSet.getBigDecimal("price")
                    );

                    report.addDamageLine(damageLine);
                }
            }

        } catch (SQLException e) {
            System.out.println("Fejl: kunne ikke finde skade " + e.getMessage());
        }

        return report;
    }

    @Override
    public void save(DamageReport damageReport) {
        String sql = "INSERT INTO damage_report (car_id, report_date, description) VALUES (?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, damageReport.getCarId());
            statement.setDate(2, Date.valueOf(damageReport.getReportDate()));
            statement.setString(3, damageReport.getDescription());

            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Fejl: kunne ikke gemme skade " + e.getMessage());
        }
    }

    @Override
    public void update(DamageReport damageReport) {
        String sql = """
                UPDATE damage_report 
                SET car_id = ?, report_date = ?, description = ?
                WHERE report_id = ?
                """;

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, damageReport.getCarId());
            statement.setDate(2, Date.valueOf(damageReport.getReportDate()));
            statement.setString(3, damageReport.getDescription());
            statement.setInt(4, damageReport.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Fejl: kunne ikke opdatere skade " + e.getMessage());
        }
    }

    @Override
    public void deleteById(int id) {
        String deleteLinesSql = "DELETE FROM damage_line WHERE report_id = ?";
        String deleteReportSql = "DELETE FROM damage_report WHERE report_id = ?";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {

            try (PreparedStatement statement = connection.prepareStatement(deleteLinesSql)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }

            try (PreparedStatement statement = connection.prepareStatement(deleteReportSql)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }

        } catch (SQLException e) {
            System.out.println("Fejl: kunne ikke slette skade " + e.getMessage());
        }
    }


    @Override
    public int saveAndReturnId(DamageReport damageReport) {
        String sql = "INSERT INTO damage_report (car_id, report_date, description) VALUES (?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setInt(1, damageReport.getCarId());
            statement.setDate(2, Date.valueOf(damageReport.getReportDate()));
            statement.setString(3, damageReport.getDescription());

            statement.executeUpdate();

            ResultSet keys = statement.getGeneratedKeys();

            if (keys.next()) {
                return keys.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println("Fejl i saveAndReturnId: " + e.getMessage());
        }

        return 0;
    }
}