package com.svizunov.antibioticmonitoring.model;


import org.springframework.data.rest.core.config.Projection;

@Projection(types = Patient.class, name = "minimal")
public interface PatientProjection extends PatientLinkable{


    String getFirstname();

    String getLastname();

    String getSurname();

    int getBirthyear();
}

