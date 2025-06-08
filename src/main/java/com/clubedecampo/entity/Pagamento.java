package com.clubedecampo.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "pagamento")
@ToString
@Getter
@Setter
@EqualsAndHashCode
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "associado_id")
    private Associado associado;

    @Column(name = "valor", nullable = false, precision = 10, scale = 2)
    private BigDecimal valor;

    @Column(name = "data_pagamento", nullable = false)
    private LocalDate dataPagamento;

    @Column(name = "forma_pagamento", length = 20)
    private String formaPagamento;

    @OneToOne
    @JoinColumn(name = "mensalidade_id")
    private Mensalidade mensalidade;

}
