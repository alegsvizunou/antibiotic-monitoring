package com.svizunov.antibioticmonitoring.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Clinic {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private int number;


    @OneToMany(mappedBy = "clinic", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<DoctorClinic> doctorClinics = new HashSet<DoctorClinic>();

    @OneToMany(mappedBy = "clinic", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<HospitalSheetChart> hospitalSheetCharts = new HashSet<HospitalSheetChart>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_city")
    private City city;

    public Clinic() {
    }

    public Clinic(int number, Set<DoctorClinic> doctorClinics, Set<HospitalSheetChart> hospitalSheetCharts, City city) {
        this.number = number;
        this.doctorClinics = doctorClinics;
        this.hospitalSheetCharts = hospitalSheetCharts;
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Set<DoctorClinic> getDoctorClinics() {
        return doctorClinics;
    }

    public void setDoctorClinics(Set<DoctorClinic> doctorClinics) {
        this.doctorClinics = doctorClinics;
    }

    public Set<HospitalSheetChart> getHospitalSheetCharts() {
        return hospitalSheetCharts;
    }

    public void setHospitalSheetCharts(Set<HospitalSheetChart> hospitalSheetCharts) {
        this.hospitalSheetCharts = hospitalSheetCharts;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Clinic{" + "id=" + id + ", number=" + number + ", doctorClinics=" + doctorClinics + ", hospitalSheetCharts=" + hospitalSheetCharts + ", city=" + city + '}';
    }
}
