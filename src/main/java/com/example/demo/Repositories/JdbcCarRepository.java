package com.example.demo.Repositories;

import com.example.demo.Models.Car;
import com.example.demo.Models.CarStatus;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JdbcCarRepository implements CarRepository {

    String url = System.getenv("DB_URL");
    String user = System.getenv("DB_USER");
    String password = System.getenv("DB_PASSWORD");


    @Override
    public List<Car> findAll() {
        List<Car> cars = new ArrayList<>();
        String sql = "SELECT * FROM car";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Car car = new Car(resultSet.getInt("car_id"),
                        resultSet.getString("vin"),
                        resultSet.getString("brand"),
                        resultSet.getString("model"),
                        CarStatus.valueOf(resultSet.getString("status").toUpperCase()),
                        resultSet.getBigDecimal("purchase_price")
                );

                cars.add(car);
            }
        } catch (SQLException e) {
            System.out.println("fejl: " + e.getMessage());
        }
        return cars;
    }

    @Override
    public Car findById(int id) {
        String sql = "SELECT * FROM car WHERE car_id = ?";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new Car(
                        resultSet.getInt("car_id"),
                        resultSet.getString("vin"),
                        resultSet.getString("brand"),
                        resultSet.getString("model"),
                        CarStatus.valueOf(resultSet.getString("status").toUpperCase()),
                        resultSet.getBigDecimal("purchase_price")
                );
            }


        } catch (SQLException e) {
            System.out.println("fejl: " + e.getMessage());
        }
        return null;
    }
    @Override
    public void save(Car car){
        String sql = "INSERT INTO car (vin, brand, model, status, purchase_price) VALUES (?,?,?,?,?)";

        try(Connection connection = DriverManager.getConnection(url,user,password)){
            PreparedStatement statement = connection.prepareStatement(sql);{

            statement.setString(1, car.getVin());
            statement.setString(2, car.getBrand());
            statement.setString(3, car.getModel());
            statement.setString(4,car.getStatus().name());
            statement.setBigDecimal(5,car.getBasePrice());

            statement.executeUpdate();

        }
        }catch (SQLException e){
            System.out.println("fejl: "+e.getMessage());
        }
    }
    @Override
    public void updateStatus(int carId, CarStatus carStatus) {
        String sql = "UPDATE car SET status = ? WHERE car_id = ?";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, carStatus.name());
            statement.setInt(2, carId);

            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("fejl: " + e.getMessage());
        }
    }

    }
