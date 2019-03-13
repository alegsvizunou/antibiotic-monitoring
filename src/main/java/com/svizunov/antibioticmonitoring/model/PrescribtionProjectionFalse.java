package com.svizunov.antibioticmonitoring.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;


@Projection(types = Prescribtion.class, name = "minimalFalse")
public interface PrescribtionProjectionFalse extends PrescribtionLinkable{
    double getMultiplicity();
    int getDosage();
    String getStartdate();
    String getStartcomment();
    String getEnddate();
    String getEndcomment();
    @Value("#{target.antibiotic.name}")
    String getName();
    @Value("#{target.doctor.surname}")
    String getDoctorSurname();
    @Value("#{target.doctor.specialty.name}")
    String getDoctorSpecialtyName();
    boolean isB();
    @Value(" #{target.doctor2 != null ? target.doctor2.surname : ''} ")
    String getDoctor2Surname();
    @Value(" #{target.doctor2 != null ? target.doctor2.specialty.name : ''} ")
    String getDoctor2SpecialtyName();
}



