package com.clubedecampo.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "tipo_associado")
@ToString
@Getter
@Setter
@EqualsAndHashCode
public class TipoAssociado {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "tipo", nullable = false, unique = true, length = 20)
    private String tipo;

    @Column(name = "valor_base",nullable = false, precision = 10, scale = 2)
    private BigDecimal valorBase;
}
