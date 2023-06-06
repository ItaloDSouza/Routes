package com.routes.routesystem.test;

import com.routes.routesystem.controller.RotaController;
import com.routes.routesystem.model.Parada;
import com.routes.routesystem.model.Rota;
import com.routes.routesystem.service.RotaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


class RotaControllerTest {

    @Mock
    private RotaService rotaService;

    private RotaController rotaController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        rotaController = new RotaController(rotaService);
    }

    @Test
    void testCriarRota() {
        Rota rota = new Rota();
        when(rotaService.save(rota)).thenReturn(rota);

        Rota result = rotaController.criarRota(rota);

        assertEquals(rota, result);
        verify(rotaService, times(1)).save(rota);
    }

    @Test
    void testObterRota() {
        Rota rota = new Rota();
        Long id = 1L;
        when(rotaService.findById(id)).thenReturn(rota);

        Rota result = rotaController.obterRota(id);

        assertEquals(rota, result);
        verify(rotaService, times(1)).findById(id);
    }

    @Test
    void testAtualizarRota() {
        Rota rotaAtualizada = new Rota();
        Rota rotaExistente = new Rota();
        Long id = 1L;
        when(rotaService.updateRota(id, rotaAtualizada)).thenReturn(rotaExistente);

        Rota result = rotaController.atualizarRota(id, rotaAtualizada);

        assertEquals(rotaExistente, result);
        verify(rotaService, times(1)).updateRota(id, rotaAtualizada);
    }

    @Test
    void testDeletarRota() {
        Long id = 1L;
        rotaController.deletarRota(id);

        verify(rotaService, times(1)).delete(id);
    }

    @Test
    void testGetParadasAtendidas() {
        Long rotaId = 1L;
        List<Parada> paradasAtendidas = Arrays.asList(new Parada(), new Parada());
        when(rotaService.getParadasAtendidas(rotaId)).thenReturn(paradasAtendidas);

        List<Parada> result = rotaController.getParadasAtendidas(rotaId);

        assertEquals(paradasAtendidas, result);
        verify(rotaService, times(1)).getParadasAtendidas(rotaId);
    }

    @Test
    void testGetStatusRota() {
        LocalDateTime inicio = LocalDateTime.now();
        LocalDateTime fim = LocalDateTime.now();
        Map<Long, String> statusRotas = new HashMap<>();
        when(rotaService.getStatusRotas(inicio, fim)).thenReturn(statusRotas);

        ResponseEntity<Map<Long, String>> result = rotaController.getStatusRota(inicio, fim);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(statusRotas, result.getBody());
        verify(rotaService, times(1)).getStatusRotas(inicio, fim);
    }
}