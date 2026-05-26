CREATE DATABASE IF NOT EXISTS bilabonnement;

USE bilabonnement;



CREATE TABLE customer (
                          customer_id INT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(100) NOT NULL,
                          email VARCHAR(100),
                          phone VARCHAR(30)
);

CREATE TABLE car (
                     car_id INT AUTO_INCREMENT PRIMARY KEY,
                     vin VARCHAR(50) NOT NULL UNIQUE,
                     brand VARCHAR(50) NOT NULL,
                     model VARCHAR(50) NOT NULL,
                     status VARCHAR(30) NOT NULL,
                     purchase_price DECIMAL(15,2)
);

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
);

CREATE TABLE damage_report (
                               report_id INT AUTO_INCREMENT PRIMARY KEY,
                               rental_id INT NOT NULL,
                               report_date DATE NOT NULL,
                               description VARCHAR(255),

                               FOREIGN KEY (rental_id) REFERENCES rental_agreement(rental_id)
);

CREATE TABLE damage_line (
                             damage_line_id INT AUTO_INCREMENT PRIMARY KEY,
                             report_id INT NOT NULL,
                             description VARCHAR(300) NOT NULL,
                             price DECIMAL(15,2) NOT NULL,

                             FOREIGN KEY (report_id) REFERENCES damage_report(report_id)
);

CREATE TABLE users (
                       user_id INT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(50),
                       password VARCHAR(50)
);

INSERT INTO car (vin, brand, model, status, purchase_price) VALUES
                                                                ('VIN0001', 'BMW', '320i', 'AVAILABLE', 699),
                                                                ('VIN0002', 'BMW', '520d', 'AVAILABLE', 849),
                                                                ('VIN0003', 'BMW', 'X5', 'AVAILABLE', 1299),
                                                                ('VIN0004', 'Mercedes', 'E200', 'AVAILABLE', 899),
                                                                ('VIN0005', 'Mercedes', 'E220', 'AVAILABLE', 949),
                                                                ('VIN0006', 'Mercedes', 'GLC', 'AVAILABLE', 1199),
                                                                ('VIN0007', 'Toyota', 'Auris', 'AVAILABLE', 449),
                                                                ('VIN0008', 'Toyota', 'Corolla', 'AVAILABLE', 499),
                                                                ('VIN0009', 'Toyota', 'Yaris', 'AVAILABLE', 399),
                                                                ('VIN0010', 'Volkswagen', 'Golf', 'AVAILABLE', 549),
                                                                ('VIN0011', 'Volkswagen', 'Passat', 'AVAILABLE', 649),
                                                                ('VIN0012', 'Volkswagen', 'Polo', 'AVAILABLE', 399);