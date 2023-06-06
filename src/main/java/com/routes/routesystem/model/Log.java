package com.routes.routesystem.model;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class Log {

    private Long id;
    private LocalDateTime dataEvento;
    private String statusRota;

}
