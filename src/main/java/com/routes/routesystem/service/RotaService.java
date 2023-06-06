package com.routes.routesystem.service;

import com.routes.routesystem.model.Coordenada;
import com.routes.routesystem.model.Parada;
import com.routes.routesystem.model.Rota;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public interface RotaService {
    Rota findById(Long id);
    void delete(Long rotaId);
    Rota save(Rota rota);
    Rota updateRota(Long idRota,Rota rotaAtualizada);
    void atualizarStatusParadas(Coordenada coordenada);
    List<Parada> getParadasAtendidas(Long rotaI4d);
    Map<Long, String> getStatusRotas(LocalDateTime inicio, LocalDateTime fim);
}
