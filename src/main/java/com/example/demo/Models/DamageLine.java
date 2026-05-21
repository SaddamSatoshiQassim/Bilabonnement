package com.example.demo.Models;

import java.math.BigDecimal;

public class DamageLine {

    private int id;
    private int reportId;
    private String description;
    private BigDecimal price;

    public DamageLine(int id, int reportId, String description, BigDecimal price) {
        this.id = id;
        this.reportId = reportId;
        this.description = description;
        this.price = price;
    }

    public DamageLine(int reportId, String description, BigDecimal price) {
        this.reportId = reportId;
        this.description = description;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public int getReportId() {
        return reportId;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}