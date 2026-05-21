package com.example.demo.Models;

import java.math.BigDecimal;
import java.time.LocalDate;

public class RentalAgreement {

    private int id;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal rentalPrice;

    private Customer customer;
    private Car car;
    private Location pickupLocation;
    private Location returnLocation;

    public RentalAgreement() {
    }

    public RentalAgreement(int id,
                           LocalDate startDate,
                           LocalDate endDate,
                           BigDecimal rentalPrice,
                           Customer customer,
                           Car car,
                           Location pickupLocation,
                           Location returnLocation) {

        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.rentalPrice = rentalPrice;
        this.customer = customer;
        this.car = car;
        this.pickupLocation = pickupLocation;
        this.returnLocation = returnLocation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id=id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate=startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate=endDate;
    }

    public BigDecimal getRentalPrice() {
        return rentalPrice;
    }

    public void setRentalPrice(BigDecimal rentalPrice) {
        this.rentalPrice=rentalPrice;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer=customer;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car=car;
    }

    public Location getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(Location pickupLocation) {
        this.pickupLocation=pickupLocation;
    }

    public Location getReturnLocation() {
        return returnLocation;
    }

    public void setReturnLocation(Location returnLocation) {
        this.returnLocation=returnLocation;
    }
}