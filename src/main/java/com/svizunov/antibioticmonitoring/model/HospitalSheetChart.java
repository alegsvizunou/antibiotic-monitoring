package com.svizunov.antibioticmonitoring.model;

import org.springframework.hateoas.Identifiable;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class HospitalSheetChart implements Identifiable<Long> {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String number;
    @Column
    private String startdate;
    @Column
    private String enddate;
    @Column
    private boolean b;
    @OneToMany(mappedBy = "hospitalSheetChart", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Prescribtion> prescribtions = new HashSet<Prescribtion>();
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_patient")
    private Patient patient;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_clinic")
    private Clinic clinic;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_doctor")
    private Doctor doctor;
    @Override
    public Long getId() {
        return id;
    }

    public HospitalSheetChart(String number, String startdate, String enddate, boolean b, Set<Prescribtion> prescribtions, Patient patient, Clinic clinic, Doctor doctor) {
        this.number = number;
        this.startdate = startdate;
        this.enddate = enddate;
        this.b = b;
        this.prescribtions = prescribtions;
        this.patient = patient;
        this.clinic = clinic;
        this.doctor = doctor;
    }

    public HospitalSheetChart() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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

    public Set<Prescribtion> getPrescribtions() {
        return prescribtions;
    }

    public void setPrescribtions(Set<Prescribtion> prescribtions) {
        this.prescribtions = prescribtions;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public boolean isB() {
        return b;
    }

    public void setB(boolean b) {
        this.b = b;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    @Override
    public String toString() {
        return "HospitalSheetChart{" + "id=" + id + ", number='" + number + '\'' + ", startdate='" + startdate + '\'' + ", enddate='" + enddate + '\'' + ", b=" + b + ", prescribtions=" + prescribtions + ", patient=" + patient + ", clinic=" + clinic + ", doctor=" + doctor + '}';
    }
}
