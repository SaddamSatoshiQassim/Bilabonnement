package com.example.demo.Repositories;

import com.example.demo.Models.Location;
import com.example.demo.Models.RentalAgreement;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JdbcRentalAgreementRepository implements RentalAgreementRepository {

    String url = System.getenv("DB_URL");
    String user = System.getenv("DB_USER");
    String password = System.getenv("DB_PASSWORD");

    @Override
    public List<RentalAgreement> findAll() {

        List<RentalAgreement> rentalAgreements = new ArrayList<>();

        String sql = "SELECT * FROM rental_agreement";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                Location pickupLocation =
                        new Location(resultSet.getString("pickup_location"));

                Location returnLocation =
                        new Location(resultSet.getString("return_location"));

                RentalAgreement rentalAgreement =
                        new RentalAgreement(
                                resultSet.getInt("rental_id"),
                                resultSet.getInt("customer_id"),
                                resultSet.getInt("car_id"),
                                resultSet.getDate("start_date").toLocalDate(),
                                resultSet.getDate("end_date") != null
                                        ? resultSet.getDate("end_date").toLocalDate()
                                        : null,
                                resultSet.getBigDecimal("rental_price"),
                                pickupLocation,
                                returnLocation
                        );

                rentalAgreements.add(rentalAgreement);
            }

        } catch (SQLException e) {
            System.out.println("Fejl: kunne ikke hente aftaler " + e.getMessage());
        }

        return rentalAgreements;
    }

    @Override
    public RentalAgreement findById(int id) {

        String sql = "SELECT * FROM rental_agreement WHERE rental_id = ?";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                Location pickupLocation =
                        new Location(resultSet.getString("pickup_location"));

                Location returnLocation =
                        new Location(resultSet.getString("return_location"));

                return new RentalAgreement(
                        resultSet.getInt("rental_id"),
                        resultSet.getInt("customer_id"),
                        resultSet.getInt("car_id"),
                        resultSet.getDate("start_date").toLocalDate(),
                        resultSet.getDate("end_date") != null
                                ? resultSet.getDate("end_date").toLocalDate()
                                : null,
                        resultSet.getBigDecimal("rental_price"),
                        pickupLocation,
                        returnLocation
                );
            }

        } catch (SQLException e) {
            System.out.println("Fejl: kunne ikke finde aftale " + e.getMessage());
        }

        return null;
    }

    @Override
    public void save(RentalAgreement rentalAgreement) {

        String sql = "INSERT INTO rental_agreement(customer_id, car_id, start_date, end_date, rental_price, pickup_location, return_location) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, rentalAgreement.getCustomerId());
            statement.setInt(2, rentalAgreement.getCarId());
            statement.setDate(3, Date.valueOf(rentalAgreement.getStartDate()));

            if (rentalAgreement.getEndDate() != null) {
                statement.setDate(4, Date.valueOf(rentalAgreement.getEndDate()));
            } else {
                statement.setNull(4, Types.DATE);
            }

            statement.setBigDecimal(5, rentalAgreement.getRentalPrice());
            statement.setString(6, rentalAgreement.getPickupLocation().getName());
            statement.setString(7, rentalAgreement.getReturnLocation().getName());

            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Fejl: kunne ikke gemme aftale " + e.getMessage());
        }
    }

    @Override
    public void update(RentalAgreement rentalAgreement) {

        String sql = "UPDATE rental_agreement SET customer_id=?, car_id=?, start_date=?, end_date=?, rental_price=?, pickup_location=?, return_location=? WHERE rental_id=?";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, rentalAgreement.getCustomerId());
            statement.setInt(2, rentalAgreement.getCarId());
            statement.setDate(3, Date.valueOf(rentalAgreement.getStartDate()));

            if (rentalAgreement.getEndDate() != null) {
                statement.setDate(4, Date.valueOf(rentalAgreement.getEndDate()));
            } else {
                statement.setNull(4, Types.DATE);
            }

            statement.setBigDecimal(5, rentalAgreement.getRentalPrice());
            statement.setString(6, rentalAgreement.getPickupLocation().getName());
            statement.setString(7, rentalAgreement.getReturnLocation().getName());

            statement.setInt(8, rentalAgreement.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Fejl: kunne ikke opdatere aftale " + e.getMessage());
        }
    }

    @Override
    public void deleteById(int id) {

        String sql = "DELETE FROM rental_agreement WHERE rental_id=?";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Fejl: kunne ikke slette aftale " + e.getMessage());
        }
    }
}