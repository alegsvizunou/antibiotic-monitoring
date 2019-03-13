package com.svizunov.antibioticmonitoring.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(types = HospitalSheetChart.class, name = "minimal")
public interface HospitalSheetChartProjection extends HospitalSheetChartLinkable{

    String getNumber();

    String getStartdate();

    String getEnddate();

    @Value("#{target.clinic.number}")
    int getClinicNumber();

    @Value("#{target.clinic.city.name}")
    String getCity();

    boolean getB();

}


