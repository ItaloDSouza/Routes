package com.routes.routesystem.model;

import com.routes.routesystem.enumerator.StatusParada;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "parada")
@Data
public class Parada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "endereco")
    private String endereco;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusParada status;

    @Column(name = "raio_entrega")
    private Double raioEntrega;



}