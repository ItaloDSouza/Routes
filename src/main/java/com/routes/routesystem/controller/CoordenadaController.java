package com.routes.routesystem.controller;

import com.routes.routesystem.model.Coordenada;
import com.routes.routesystem.service.RotaService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/coordenadas")
public class CoordenadaController {
//    private final RotaService rotaService;
//
//    public CoordenadaController(RotaService rotaService) {
//        this.rotaService = rotaService;
//    }
//
//    @PostMapping
//    public void atualizarStatusParadas(@RequestBody Coordenada coordenada) {
//        rotaService.atualizarStatusParadas(coordenada);
//    }
}