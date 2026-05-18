package com.example.demo.Models;

import java.math.BigDecimal;
import java.time.LocalDate;

public class RentalAgreement {
   private int id;
   private LocalDate startDate;
   private LocalDate endDate;
   private BigDecimal rentalPrice;
   Location pickupLocation;
   Location returnLocation;

    public RentalAgreement() {
    }

    public RentalAgreement(LocalDate startDate, LocalDate endDate) {
        this(0, startDate, endDate, BigDecimal.ZERO, null, null);
    }

    public RentalAgreement(int id, LocalDate startDate, LocalDate endDate, BigDecimal rentalPrice, Location pickupLocation, Location returnLocation) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.rentalPrice = rentalPrice;
        this.pickupLocation = pickupLocation;
        this.returnLocation = returnLocation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getRentalPrice() {
        return rentalPrice;
    }

    public void setRentalPrice(BigDecimal rentalPrice) {
        this.rentalPrice = rentalPrice;
    }

    public Location getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(Location pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public Location getReturnLocation() {
        return returnLocation;
    }

    public void setReturnLocation(Location returnLocation) {
        this.returnLocation = returnLocation;
    }

    public boolean hasValidDates() {
        return startDate != null
                && endDate != null
                && !endDate.isBefore(startDate);
    }
}
