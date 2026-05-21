package com.example.demo.Models;

import java.time.LocalDate;

public class DamageReport {

    private int id;
    private int carId;
    private LocalDate reportDate;
    private String description;

    public DamageReport(int id, int carId, LocalDate reportDate, String description) {
        this.id = id;
        this.carId = carId;
        this.reportDate = reportDate;
        this.description = description;
    }

    public DamageReport(int carId, LocalDate reportDate, String description) {
        this.carId = carId;
        this.reportDate = reportDate;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public int getCarId() {
        return carId;
    }

    public LocalDate getReportDate() {
        return reportDate;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public void setReportDate(LocalDate reportDate) {
        this.reportDate = reportDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}