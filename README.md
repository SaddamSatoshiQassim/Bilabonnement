# Bilabonnement
Et webbaseret system til håndtering af biludlejning, lejeaftaler, skader og kunder. Bygget med Spring Boot og MySQL.

Softwaremæssige forudsætninger
Følgende software skal være installeret på din computer før applikationen kan køres:

Java JDK: Kører Java applikationen
Maven: Bygger og håndterer projektets afhængigheder
MySQL: Database til at gemme data
Git: Hente koden fra GitHub

# 1. Installation af Java
Gå til https://www.oracle.com/java/technologies/downloads og download JDK 17 eller nyere
Kør installationsfilen og følg vejledningen
Tjek at Java er installeret korrekt ved at åbne en terminal og skrive:

java -version
Du skulle gerne se noget i stil med:
openjdk version "17.0.x"

# 2. Installation af Maven
Gå til https://maven.apache.org/download.cgi og download den nyeste version
Udpak filen og tilføj Maven til din PATH variabel
Tjek at Maven er installeret korrekt ved at skrive i terminalen:

mvn -version
Du skulle gerne se noget i stil med:
Apache Maven 3.x.x
Man kan også bruge Intellij IDEA som har Maven indbygget

# 3. Installation og opsætning af MySQL
Gå til https://dev.mysql.com/downloads/mysql og download MySQL Community Server 8.0
Kør installationsfilen og følg vejledningen
Notér det root-password du vælger under installationen
Åbn MySQL Workbench eller en terminal og opret databasen ved at køre følgende SQL:

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
    password VARCHAR(50)

);

# 4. Hent koden fra github
Åbn en terminal og kør:
git clone https://github.com/Yuk1210/Bilabonnement.git
cd bilabonnement

# 5. Konfigurer miljøvariabler
Applikationen bruger miljøvariabler til at forbinde til databasen. Du skal sætte følgende tre variabler:

DB_URL: jdbc:mysql://localhost:3306/bilabonnement
DB_USERD: root
DB_PASSWORD: ditpassword

I IntelliJ IDEA:
Gå til Run → Edit Configurations
Klik på din Spring Boot konfiguration
Find feltet Environment Variables
Tilføj variablerne adskilt med semikolon:

DB_URL=jdbc:mysql://localhost:3306/bilabonnement;
DB_USER=root;
DB_PASSWORD=ditpassword

I terminal (Windows):
set DB_URL=jdbc:mysql://localhost:3306/bilabonnement
set DB_USER=root
set DB_PASSWORD=ditpassword

# 6. Byg og kør applikationen
Via IntelliJ IDEA:

Åbn projektet i IntelliJ IDEA
Vent på at Maven downloader alle afhængigheder
Find filen DemoApplication.java i src/main/java/com/example/demo/
Klik på den grønne pil og vælg Run

Via terminal:
Navigate to the project folder and run:
mvn spring-boot:run
Alternatively, build the project as a JAR file and run it directly:
mvn clean package
java -jar target/demo-0.0.1-SNAPSHOT.jar

Once the application is running, open a browser and go to:
http://localhost:8080
You will be prompted to log in. If you do not have a user yet, click Opret bruger to create one.

# 7. Troubleshooting
The application does not start:
Make sure MySQL is running and the bilabonnement database has been created
Check that all three environment variables are set correctly
Make sure port 8080 is not already in use by another application

Cannot connect to the database:
Double-check that DB_URL, DB_USER and DB_PASSWORD are correct
Verify that MySQL is running by opening MySQL Workbench

Whitelabel Error Page:
Make sure all HTML files are located in src/main/resources/templates/
Restart the application
