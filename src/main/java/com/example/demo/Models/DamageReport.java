package com.example.demo.Models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

public class DamageReport {

    private int id;
    private int rentalId;
    private LocalDate reportDate;
    private String description;
    private List<DamageLine> damageLines = new ArrayList<>();
    private BigDecimal totalPrice;

    public DamageReport(
            int id,
            int rentalId,
            LocalDate reportDate,
            String description) {

        this.id = id;
        this.rentalId = rentalId;
        this.reportDate = reportDate;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public int getRentalId() {
        return rentalId;
    }

    public LocalDate getReportDate() {
        return reportDate;
    }

    public String getDescription() {
        return description;
    }

    public List<DamageLine> getDamageLines() {
        return damageLines;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRentalId(int rentalId) {
        this.rentalId = rentalId;
    }

    public void setReportDate(LocalDate reportDate) {
        this.reportDate = reportDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDamageLines(List<DamageLine> damageLines) {
        this.damageLines = damageLines;
    }

    public void addDamageLine(DamageLine damageLine) {
        this.damageLines.add(damageLine);
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}