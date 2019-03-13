package com.svizunov.antibioticmonitoring.model;

import org.springframework.hateoas.Identifiable;

import javax.persistence.*;

@Entity
public class Prescribtion implements Identifiable<Long> {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private int multiplicity;
    @Column
    private int dosage;
    @Column
    private String startdate;
    @Column
    private String enddate;
    @Column
    private String startcomment;
    @Column
    private String endcomment;
    @Column
    private boolean b;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_antibiotic")
    private Antibiotic antibiotic;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_hospitalSheetChart")
    private HospitalSheetChart hospitalSheetChart;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_doctorOpened")
    private Doctor doctor;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_doctorClosed")
    private Doctor doctor2;


    @Override
    public Long getId() {
        return id;
    }

    public Prescribtion(int multiplicity, int dosage, String startdate, String enddate, String startcomment, String endcomment, boolean b, Antibiotic antibiotic, HospitalSheetChart hospitalSheetChart, Doctor doctor, Doctor doctor2) {
        this.multiplicity = multiplicity;
        this.dosage = dosage;
        this.startdate = startdate;
        this.enddate = enddate;
        this.startcomment = startcomment;
        this.endcomment = endcomment;
        this.b = b;
        this.antibiotic = antibiotic;
        this.hospitalSheetChart = hospitalSheetChart;
        this.doctor = doctor;
        this.doctor2 = doctor2;
    }

    public Prescribtion() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getMultiplicity() {
        return multiplicity;
    }

    public void setMultiplicity(int multiplicity) {
        this.multiplicity = multiplicity;
    }

    public int getDosage() {
        return dosage;
    }

    public void setDosage(int dosage) {
        this.dosage = dosage;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getStartcomment() {
        return startcomment;
    }

    public void setStartcomment(String startcomment) {
        this.startcomment = startcomment;
    }

    public String getEndcomment() {
        return endcomment;
    }

    public void setEndcomment(String endcomment) {
        this.endcomment = endcomment;
    }

    public boolean isB() {
        return b;
    }

    public void setB(boolean b) {
        this.b = b;
    }

    public Antibiotic getAntibiotic() {
        return antibiotic;
    }

    public void setAntibiotic(Antibiotic antibiotic) {
        this.antibiotic = antibiotic;
    }

    public HospitalSheetChart getHospitalSheetChart() {
        return hospitalSheetChart;
    }

    public void setHospitalSheetChart(HospitalSheetChart hospitalSheetChart) {
        this.hospitalSheetChart = hospitalSheetChart;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Doctor getDoctor2() {
        return doctor2;
    }

    public void setDoctor2(Doctor doctor2) {
        this.doctor2 = doctor2;
    }

    @Override
    public String toString() {
        return "Prescribtion{" + "id=" + id + ", multiplicity=" + multiplicity + ", dosage=" + dosage + ", startdate='" + startdate + '\'' + ", enddate='" + enddate + '\'' + ", startcomment='" + startcomment + '\'' + ", endcomment='" + endcomment + '\'' + ", b=" + b + ", antibiotic=" + antibiotic + ", hospitalSheetChart=" + hospitalSheetChart + ", doctor=" + doctor + ", doctor2=" + doctor2 + '}';
    }
}
