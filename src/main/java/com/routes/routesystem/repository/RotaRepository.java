package com.routes.routesystem.repository;

import com.routes.routesystem.model.Rota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface RotaRepository extends JpaRepository<Rota, Long> {

    @Query("SELECT r FROM Rota r WHERE r.data BETWEEN :inicio AND :fim")
    List<Rota> findAllBetweenDates(LocalDateTime inicio, LocalDateTime fim);
}