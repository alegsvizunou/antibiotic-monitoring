package com.svizunov.antibioticmonitoring.model;

import javax.persistence.*;

@Entity
public class DoctorRole {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_doctor")
    private Doctor doctor;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_role")
    private Role role;

    public DoctorRole(Doctor doctor, Role role) {
        this.doctor = doctor;
        this.role = role;
    }

    public DoctorRole() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "DoctorRole{" + "id=" + id + ", doctor=" + doctor + ", role=" + role + '}';
    }
}
