package com.svizunov.antibioticmonitoring.model;

import javax.persistence.*;

@Entity
public class DoctorClinic {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_clinic")
    private Clinic clinic;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_doctor")
    private Doctor doctor;

    public DoctorClinic(Clinic clinic, Doctor doctor) {
        this.clinic = clinic;
        this.doctor = doctor;
    }

    public DoctorClinic() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    @Override
    public String toString() {
        return "DoctorClinic{" + "id=" + id + ", clinic=" + clinic + ", doctor=" + doctor + '}';
    }
}
