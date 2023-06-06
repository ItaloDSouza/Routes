package com.routes.routesystem.model;

import lombok.Data;

import java.time.Instant;
@Data
public class Coordenada {

    private double lat;
    private double lng;
    private Instant instant;
    private long routeId;


}