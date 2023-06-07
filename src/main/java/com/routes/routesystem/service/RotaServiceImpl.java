package com.routes.routesystem.service;

import com.routes.routesystem.enumerator.StatusParada;
import com.routes.routesystem.model.Coordenada;
import com.routes.routesystem.model.Log;
import com.routes.routesystem.model.Parada;
import com.routes.routesystem.model.Rota;
import com.routes.routesystem.repository.RotaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Service
public class RotaServiceImpl implements RotaService{

    RotaRepository rotaRepository;
    ModelMapper modelMapper;

    RotaStatusPublisher rotaStatusPublisher;
    public RotaServiceImpl(RotaRepository rotaRepository,ModelMapper modelMapper,RotaStatusPublisher rotaStatusPublisher) {
        this.rotaRepository = rotaRepository;
        this.modelMapper = modelMapper;
        this.rotaStatusPublisher = rotaStatusPublisher;
    }

    @Override
    public Rota findById(Long id) {
        return rotaRepository.findById(id).orElseThrow(() -> new RuntimeException("Rota não encontrada com ID: " + id));
    }

    @Override
    public void delete(Long rotaId) {
        Rota rota = findById(rotaId);
        rotaRepository.delete(rota);

    }
    @Override
    public Rota save(Rota rota) {
        validateRotas(rota);
        return rotaRepository.save(rota);
    }

    @Override
    public Rota updateRota(Long idRota, Rota rotaAtualizada) {
        validateRotas(rotaAtualizada);
        Rota rotaFromDatabase = findById(idRota);
        boolean rotaChanged = !rotaAtualizada.getStatus().equals(rotaFromDatabase.getStatus());
        modelMapper.map(rotaAtualizada,rotaFromDatabase);
        rotaFromDatabase = save(rotaFromDatabase);
        if(rotaChanged) {
            updateRotaLog(rotaFromDatabase);
        }
        return rotaFromDatabase;
    }

    @Override
    public void atualizarStatusParadas(Coordenada coordenada) {

    }

    @Override
    public Map<Long, String> getStatusRotas(LocalDateTime inicio, LocalDateTime fim) {
        Map<Long, String> rotasStatus = new HashMap<>();

        List<Rota> todasRotas = rotaRepository.findAllBetweenDates(inicio,fim);
        for (Rota rota : todasRotas) {
            LocalDateTime dataRota = LocalDateTime.from(rota.getData());

            if (dataRota.isAfter(inicio) && dataRota.isBefore(fim)) {
                rotasStatus.put(rota.getId(), rota.getStatus().name());
            }
        }

        return rotasStatus;
    }

    @Override
    public List<Parada> getParadasAtendidas(Long rotaId) {
        Rota rota = findById(rotaId);
        List<Parada> paradasAtendidas = new ArrayList<>();

        for (Parada parada : rota.getParadas()) {
            if (parada.getStatus() == StatusParada.ANSWER) {
                paradasAtendidas.add(parada);
            }

        }
        return paradasAtendidas;
    }

    private void updateRotaLog(Rota rota) {

            Log log = new Log();
            log.setStatusRota(rota.getStatus().name());
            log.setId(rota.getId());
            LocalDate data = rota.getData();
            Instant instant = data.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
            LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
            log.setDataEvento(localDateTime);

            rotaStatusPublisher.publishStatusUpdate(log);

        }

    private static void validateRotas(Rota rota) {
        if(rota.getParadas().isEmpty()){
            throw new RuntimeException("Uma rota deve possuir ao menos uma parada!");
        }else if(rota.getParadas().stream()
                .anyMatch(parada -> parada.getLatitude() == null || parada.getLongitude() == null)){
            throw new RuntimeException("Alguma das rotas possui paradas inválidas!");
        }
    }

}
