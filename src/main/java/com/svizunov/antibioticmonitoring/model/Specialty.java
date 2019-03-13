package com.svizunov.antibioticmonitoring.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Specialty {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String name;
    @OneToMany(mappedBy = "specialty", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Doctor> doctors = new HashSet<Doctor>();

    public Specialty(String name, Set<Doctor> doctors) {
        this.name = name;
        this.doctors = doctors;
    }

    public Specialty() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(Set<Doctor> doctors) {
        this.doctors = doctors;
    }

    @Override
    public String toString() {
        return "Specialty{" + "id=" + id + ", name='" + name + '\'' + ", doctors=" + doctors + '}';
    }
}
