package com.svizunov.antibioticmonitoring.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Antibiotic {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String name;
    @OneToMany(mappedBy = "antibiotic", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Prescribtion> prescribtions = new HashSet<Prescribtion>();

    public Antibiotic(String name, Set<Prescribtion> prescribtions) {
        this.name = name;
        this.prescribtions = prescribtions;
    }

    public Antibiotic() {
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

    public Set<Prescribtion> getPrescribtions() {
        return prescribtions;
    }

    public void setPrescribtions(Set<Prescribtion> prescribtions) {
        this.prescribtions = prescribtions;
    }

    @Override
    public String toString() {
        return "Antibiotic{" + "id=" + id + ", name='" + name + '\'' + ", prescribtions=" + prescribtions + '}';
    }
}
