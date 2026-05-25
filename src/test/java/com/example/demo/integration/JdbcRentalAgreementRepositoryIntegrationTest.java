package com.example.demo.integration;

import com.example.demo.Models.Location;
import com.example.demo.Models.RentalAgreement;
import com.example.demo.Repositories.JdbcRentalAgreementRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JdbcRentalAgreementRepositoryIntegrationTest {

    private static final String URL = "jdbc:h2:mem:rental_agreement_test;MODE=MySQL;DB_CLOSE_DELAY=-1";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    private JdbcRentalAgreementRepository repository;

    @BeforeEach
    void setUp() throws Exception {
        Class.forName("org.h2.Driver");

        repository = new JdbcRentalAgreementRepository();
        setField(repository, "url", URL);
        setField(repository, "user", USER);
        setField(repository, "password", PASSWORD);

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {
            statement.execute("DROP TABLE IF EXISTS rental_agreement");
            statement.execute("DROP TABLE IF EXISTS customer");
            statement.execute("DROP TABLE IF EXISTS car");

            statement.execute("""
                    CREATE TABLE customer (
                        customer_id INT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(100) NOT NULL,
                        email VARCHAR(100),
                        phone VARCHAR(30)
                    )
                    """);

            statement.execute("""
                    CREATE TABLE car (
                        car_id INT AUTO_INCREMENT PRIMARY KEY,
                        vin VARCHAR(50) NOT NULL UNIQUE,
                        brand VARCHAR(50) NOT NULL,
                        model VARCHAR(50) NOT NULL,
                        status VARCHAR(30) NOT NULL,
                        purchase_price DECIMAL(15,2)
                    )
                    """);

            statement.execute("""
                    CREATE TABLE rental_agreement (
                        rental_id INT AUTO_INCREMENT PRIMARY KEY,
                        customer_id INT NOT NULL,
                        car_id INT NOT NULL,
                        start_date DATE NOT NULL,
                        end_date DATE,
                        rental_price DECIMAL(15,2),
                        pickup_location VARCHAR(100),
                        return_location VARCHAR(100),
                        FOREIGN KEY (customer_id) REFERENCES customer(customer_id),
                        FOREIGN KEY (car_id) REFERENCES car(car_id)
                    )
                    """);

            statement.execute("""
                    INSERT INTO customer (name, email, phone)
                    VALUES ('Test Kunde', 'kunde@test.dk', '12345678')
                    """);

            statement.execute("""
                    INSERT INTO car (vin, brand, model, status, purchase_price)
                    VALUES ('VIN-TEST-1', 'Toyota', 'Yaris', 'AVAILABLE', 500.00)
                    """);
        }
    }

    @AfterEach
    void tearDown() throws SQLException {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {
            statement.execute("DROP TABLE IF EXISTS rental_agreement");
            statement.execute("DROP TABLE IF EXISTS customer");
            statement.execute("DROP TABLE IF EXISTS car");
        }
    }

    @Test
    void shouldSaveRentalAgreementCorrectlyInDatabase() throws SQLException {
        RentalAgreement agreement = new RentalAgreement(
                0,
                1,
                1,
                LocalDate.of(2026, 5, 21),
                LocalDate.of(2026, 5, 28),
                new BigDecimal("3499.95"),
                new Location("Kobenhavn"),
                new Location("Odense")
        );

        repository.save(agreement);

        assertEquals(1, countRentalAgreements());
        int savedId = findLatestRentalAgreementId();
        RentalAgreement savedAgreement = repository.findById(savedId);

        assertNotNull(savedAgreement);
        assertEquals(1, savedAgreement.getCustomerId());
        assertEquals(1, savedAgreement.getCarId());
        assertEquals(LocalDate.of(2026, 5, 21), savedAgreement.getStartDate());
        assertEquals(LocalDate.of(2026, 5, 28), savedAgreement.getEndDate());
        assertEquals(new BigDecimal("3499.95"), savedAgreement.getRentalPrice());
        assertEquals("Kobenhavn", savedAgreement.getPickupLocation().getName());
        assertEquals("Odense", savedAgreement.getReturnLocation().getName());
    }

    private int findLatestRentalAgreementId() throws SQLException {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT rental_id FROM rental_agreement ORDER BY rental_id DESC LIMIT 1");
             ResultSet resultSet = statement.executeQuery()) {
            assertTrue(resultSet.next());
            return resultSet.getInt("rental_id");
        }
    }

    private int countRentalAgreements() throws SQLException {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT COUNT(*) AS total FROM rental_agreement");
             ResultSet resultSet = statement.executeQuery()) {
            resultSet.next();
            return resultSet.getInt("total");
        }
    }

    private void setField(Object target, String fieldName, String value) throws Exception {
        Field field = JdbcRentalAgreementRepository.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(target, value);
    }
}
