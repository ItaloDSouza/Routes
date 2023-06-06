package com.routes.routesystem.controller;

import com.routes.routesystem.model.Parada;
import com.routes.routesystem.model.Rota;
import com.routes.routesystem.service.RotaService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("v1/routes/")
public class RotaController {

    private final RotaService rotaService;

    public RotaController(RotaService rotaService) {
        this.rotaService = rotaService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Rota criarRota(@Valid @RequestBody Rota rota) {
        // Realizar validações adicionais, se necessário
        return rotaService.save(rota);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public Rota obterRota(@PathVariable Long id) {
        return rotaService.findById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Rota atualizarRota(@PathVariable Long id, @Valid @RequestBody Rota rotaAtualizada) {
        // Atualizar os campos necessários da rota com base na rotaAtualizada
        return rotaService.updateRota(id,rotaAtualizada);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarRota(@PathVariable Long id) {
        rotaService.delete(id);
    }


    @GetMapping("/{id}/paradas/atendidas")
    public List<Parada> getParadasAtendidas(@PathVariable("id") Long rotaId) {
        List<Parada> paradasAtendidas = rotaService.getParadasAtendidas(rotaId);
        return paradasAtendidas;
    }

    @GetMapping("/status")
    public ResponseEntity<Map<Long, String>> getStatusRota(@RequestParam("inicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
                                                           @RequestParam("fim") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fim) {
        Map<Long, String> statusRotas = rotaService.getStatusRotas(inicio,fim);
        return ResponseEntity.ok(statusRotas);
    }

//    @GetMapping("/{id}/paradas/maisdemoradas")
//    public List<Parada> getParadasMaisDemoradas(@PathVariable("id") Long rotaId) {
//        // Obtenha as paradas mais demoradas para a rota (baseado no seu modelo de dados)
//        List<Parada> paradasMaisDemoradas = rotaService.getParadasMaisDemoradas(rotaId);
//        return paradasMaisDemoradas;
//    }
}
