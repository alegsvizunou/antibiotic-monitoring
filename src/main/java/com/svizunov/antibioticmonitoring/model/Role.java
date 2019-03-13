package com.svizunov.antibioticmonitoring.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Role {
    @Id
    @GeneratedValue
    private int id;
    @Column
    private String name;
    @OneToMany(mappedBy = "role", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<DoctorRole> doctorRoles = new HashSet<DoctorRole>();

    public Role(String name, Set<DoctorRole> doctorRoles) {
        this.name = name;
        this.doctorRoles = doctorRoles;
    }

    public Role() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<DoctorRole> getDoctorRoles() {
        return doctorRoles;
    }

    public void setDoctorRoles(Set<DoctorRole> doctorRoles) {
        this.doctorRoles = doctorRoles;
    }

    @Override
    public String toString() {
        return "Role{" + "id=" + id + ", name='" + name + '\'' + ", doctorRoles=" + doctorRoles + '}';
    }
}
