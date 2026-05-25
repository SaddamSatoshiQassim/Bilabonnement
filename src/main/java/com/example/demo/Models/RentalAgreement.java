package com.example.demo.Models;

import java.math.BigDecimal;
import java.time.LocalDate;

public class RentalAgreement {

    private int id;
    private int customerId;
    private int carId;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal rentalPrice;
    private Location pickupLocation;
    private Location returnLocation;

    private String customerName;
    private String carModel;

    public RentalAgreement() {
        this.id = 0;
        this.customerId = 0;
        this.carId = 0;
        this.startDate = null;
        this.endDate = null;
        this.rentalPrice = BigDecimal.ZERO;
        this.pickupLocation = null;
        this.returnLocation = null;
        this.customerName = null;
        this.carModel = null;
    }

    public RentalAgreement(LocalDate startDate, LocalDate endDate) {
        this.id = 0;
        this.customerId = 0;
        this.carId = 0;
        this.startDate = startDate;
        this.endDate = endDate;
        this.rentalPrice = BigDecimal.ZERO;
        this.pickupLocation = null;
        this.returnLocation = null;
        this.customerName = null;
        this.carModel = null;
    }

    public RentalAgreement(
            int id,
            int customerId,
            int carId,
            LocalDate startDate,
            LocalDate endDate,
            BigDecimal rentalPrice,
            Location pickupLocation,
            Location returnLocation) {

        this.id = id;
        this.customerId = customerId;
        this.carId = carId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.rentalPrice = rentalPrice;
        this.pickupLocation = pickupLocation;
        this.returnLocation = returnLocation;
        this.customerName = null;
        this.carModel = null;
    }

    public int getId() {
        return id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getCarId() {
        return carId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public BigDecimal getRentalPrice() {
        return rentalPrice;
    }

    public Location getPickupLocation() {
        return pickupLocation;
    }

    public Location getReturnLocation() {
        return returnLocation;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setRentalPrice(BigDecimal rentalPrice) {
        this.rentalPrice = rentalPrice;
    }

    public void setPickupLocation(Location pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public void setReturnLocation(Location returnLocation) {
        this.returnLocation = returnLocation;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public boolean hasValidDates() {
        return startDate != null
                && endDate != null
                && !endDate.isBefore(startDate);
    }
}