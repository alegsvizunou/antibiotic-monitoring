package com.svizunov.antibioticmonitoring.model;


import org.springframework.data.rest.core.config.Projection;

@Projection(types = City.class, name = "minimal")
public interface CityProjection extends CityLinkable{

    String getName();

}



