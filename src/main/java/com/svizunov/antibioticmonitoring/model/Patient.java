package com.svizunov.antibioticmonitoring.model;

import org.springframework.hateoas.Identifiable;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Patient implements Identifiable<Long>{
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String firstname;
    @Column
    private String lastname;
    @Column
    private String surname;
    @Column
    private int birthyear;
    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<HospitalSheetChart> hospitalSheetCharts = new HashSet<HospitalSheetChart>();
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_doctor")
    private Doctor doctor;

    public Patient(String firstname, String lastname, String surname, int birthyear, Set<HospitalSheetChart> hospitalSheetCharts, Doctor doctor) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.surname = surname;
        this.birthyear = birthyear;
        this.hospitalSheetCharts = hospitalSheetCharts;
        this.doctor = doctor;
    }

    public Patient() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getBirthyear() {
        return birthyear;
    }

    public void setBirthyear(int birthyear) {
        this.birthyear = birthyear;
    }

    public Set<HospitalSheetChart> getHospitalSheetCharts() {
        return hospitalSheetCharts;
    }

    public void setHospitalSheetCharts(Set<HospitalSheetChart> hospitalSheetCharts) {
        this.hospitalSheetCharts = hospitalSheetCharts;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    @Override
    public String toString() {
        return "Patient{" + "id=" + id + ", firstname='" + firstname + '\'' + ", lastname='" + lastname + '\'' + ", surname='" + surname + '\'' + ", birthyear=" + birthyear + ", hospitalSheetCharts=" + hospitalSheetCharts + ", doctor=" + doctor + '}';
    }
}
