package com.example.demo.Models;

import java.math.BigDecimal;

public class Car {

    private int id;
    private String vin;
    private String brand;
    private String model;
    private CarStatus status;

    // Pris pr dag
    private BigDecimal basePrice;

    public Car(int id,
               String vin,
               String brand,
               String model,
               CarStatus status,
               BigDecimal basePrice) {

        this.id = id;
        this.vin = vin;
        this.brand = brand;
        this.model = model;
        this.status = status;
        this.basePrice = basePrice;
    }

    public int getId() {
        return id;
    }

    public String getVin() {
        return vin;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public CarStatus getStatus() {
        return status;
    }

    // VIGTIG FIX
    public BigDecimal getPricePerDay() {
        return basePrice;
    }

    // Du kan også beholde denne
    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void markAsAvailable() {
        this.status = CarStatus.AVAILABLE;
    }

    public void markAsUnavailable() {
        this.status = CarStatus.UNAVAILABLE;
    }

    public void markAsReturned() {
        this.status = CarStatus.RETURNED;
    }

    public void markAsDamaged() {
        this.status = CarStatus.DAMAGED;
    }

    public void markAsUnderRepair() {
        this.status = CarStatus.UNDER_REPAIR;
    }
}