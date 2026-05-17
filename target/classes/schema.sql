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
                               car_id INT NOT NULL,
                               report_date DATE NOT NULL,
                               description VARCHAR(255),

                               FOREIGN KEY (car_id) REFERENCES car(car_id)
);

CREATE TABLE damage_line (
                             damage_line_id INT AUTO_INCREMENT PRIMARY KEY,
                             report_id INT NOT NULL,
                             description VARCHAR(300) NOT NULL,
                             price DECIMAL(15,2) NOT NULL,

                             FOREIGN KEY (report_id) REFERENCES damage_report(report_id)
);
CREATE TABLE users (

                       user_id INT PRIMARY KEY AUTO_INCREMENT,

                       username VARCHAR(50),

                       password VARCHAR(50),

                       role VARCHAR(50)

);