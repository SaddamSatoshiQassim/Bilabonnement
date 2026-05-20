package com.example.demo.Models;
import java.time.LocalDate;
import java.util.List;

public class DamageReport {
    private int id;
    private int carId;
    private LocalDate reportDate;
    private String description;
    private List<DamageLine> damageLines;


    public DamageReport(int id,int carId ,LocalDate reportDate, String description) {
        this.id = id;
        this.carId = carId;
        this.reportDate = reportDate;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public LocalDate getReportDate() {
        return reportDate;
    }

    public void setReportDate(LocalDate reportDate) {
        this.reportDate = reportDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<DamageLine> getDamageLines() {
        return damageLines;
    }

    public void setDamageLines(List<DamageLine> damageLines) {
        this.damageLines = damageLines;
    }
    public void addDamageLine(DamageLine damageLine){
        this.damageLines.add(damageLine);
    }
}
