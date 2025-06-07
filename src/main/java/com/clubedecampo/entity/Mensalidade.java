package com.clubedecampo.entity;

import com.clubedecampo.converter.YearMonthToLocalDateConverter;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.UUID;

@Entity
@Table(name = "mensalidade")
@ToString
@Getter
@Setter
@EqualsAndHashCode
public class Mensalidade {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "associado_id")
    private Associado associado;

    @Convert(converter = YearMonthToLocalDateConverter.class)
    @Column(name = "mes_referencia", nullable = false)
    private YearMonth mesReferencia;

    @Column(name = "valor_base", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorBase;

    @Column(name = "valor_corrigido", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorCorrigido;

    @Column(name = "data_vencimento", nullable = false)
    private LocalDate dataVencimento;

    @Column(name = "data_pagamento")
    private LocalDate dataPagamento;
}
