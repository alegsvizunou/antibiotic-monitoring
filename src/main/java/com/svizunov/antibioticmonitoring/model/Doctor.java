package com.svizunov.antibioticmonitoring.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Doctor {
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
    private String login;
    @Column
    private String password;
    @Column
    private boolean isEnabled;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_specialty")
    private Specialty specialty;
    @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Prescribtion> prescribtions = new HashSet<Prescribtion>();
    @OneToMany(mappedBy = "doctor2", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Prescribtion> prescribtions2 = new HashSet<Prescribtion>();
    @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<DoctorClinic> doctorClinics = new HashSet<DoctorClinic>();
    @OneToMany(mappedBy = "doctor", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<DoctorRole> doctorRoles = new HashSet<DoctorRole>();
    @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Patient> patients = new HashSet<Patient>();
    @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<HospitalSheetChart> hospitalSheetCharts = new HashSet<HospitalSheetChart>();

    public Doctor(String firstname, String lastname, String surname, String login, String password, boolean isEnabled, Specialty specialty, Set<Prescribtion> prescribtions, Set<Prescribtion> prescribtions2, Set<DoctorClinic> doctorClinics, Set<DoctorRole> doctorRoles, Set<Patient> patients, Set<HospitalSheetChart> hospitalSheetCharts) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.isEnabled = isEnabled;
        this.specialty = specialty;
        this.prescribtions = prescribtions;
        this.prescribtions2 = prescribtions2;
        this.doctorClinics = doctorClinics;
        this.doctorRoles = doctorRoles;
        this.patients = patients;
        this.hospitalSheetCharts = hospitalSheetCharts;
    }

    public Doctor() {
    }

    public Doctor(Doctor doctor) {
        this.id = doctor.getId();
        this.firstname = doctor.getFirstname();
        this.lastname = doctor.getLastname();
        this.surname = doctor.getSurname();
        this.login = doctor.getLogin();
        this.password = doctor.getPassword();
        this.isEnabled = doctor.isEnabled();
        this.doctorRoles = doctor.getDoctorRoles();
        this.specialty = doctor.getSpecialty();
        this.prescribtions = doctor.getPrescribtions();
        this.prescribtions2 = doctor.getPrescribtions2();
        this.doctorClinics = doctor.getDoctorClinics();
        this.doctorRoles = doctor.getDoctorRoles();
        this.hospitalSheetCharts = doctor.getHospitalSheetCharts();
        this.patients = doctor.getPatients();
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }

    public Set<Prescribtion> getPrescribtions() {
        return prescribtions;
    }

    public void setPrescribtions(Set<Prescribtion> prescribtions) {
        this.prescribtions = prescribtions;
    }

    public Set<Prescribtion> getPrescribtions2() {
        return prescribtions2;
    }

    public void setPrescribtions2(Set<Prescribtion> prescribtions2) {
        this.prescribtions2 = prescribtions2;
    }

    public Set<DoctorClinic> getDoctorClinics() {
        return doctorClinics;
    }

    public void setDoctorClinics(Set<DoctorClinic> doctorClinics) {
        this.doctorClinics = doctorClinics;
    }

    public Set<DoctorRole> getDoctorRoles() {
        return doctorRoles;
    }

    public void setDoctorRoles(Set<DoctorRole> doctorRoles) {
        this.doctorRoles = doctorRoles;
    }

    public Set<Patient> getPatients() {
        return patients;
    }

    public void setPatients(Set<Patient> patients) {
        this.patients = patients;
    }

    public Set<HospitalSheetChart> getHospitalSheetCharts() {
        return hospitalSheetCharts;
    }

    public void setHospitalSheetCharts(Set<HospitalSheetChart> hospitalSheetCharts) {
        this.hospitalSheetCharts = hospitalSheetCharts;
    }

    @Override
    public String toString() {
        return "Doctor{" + "id=" + id + ", firstname='" + firstname + '\'' + ", lastname='" + lastname + '\'' + ", surname='" + surname + '\'' + ", login='" + login + '\'' + ", password='" + password + '\'' + ", isEnabled=" + isEnabled + ", specialty=" + specialty + ", prescribtions=" + prescribtions + ", prescribtions2=" + prescribtions2 + ", doctorClinics=" + doctorClinics + ", doctorRoles=" + doctorRoles + ", patients=" + patients + ", hospitalSheetCharts=" + hospitalSheetCharts + '}';
    }
}
