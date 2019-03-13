package com.svizunov.antibioticmonitoring.model;

import org.springframework.hateoas.Identifiable;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class City implements Identifiable<Long>{
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String name;
    @OneToMany(mappedBy = "city", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Clinic> clinics = new HashSet<Clinic>();
    public City(String name, Set<Clinic> clinics) {
        this.name = name;
        this.clinics = clinics;
    }
    public City() {
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
    public Set<Clinic> getClinics() {
        return clinics;
    }
    public void setClinics(Set<Clinic> clinics) {
        this.clinics = clinics;
    }

    @Override
    public String toString() {
        return "City{" + "id=" + id + ", name='" + name + '\'' + ", clinics=" + clinics + '}';
    }
}
