package com.clubedecampo.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record ResultadoPesquisaPagamentoDTO(
        UUID associadoId,
        BigDecimal valor,
        LocalDate dataPagamento,
        String formaPagamento,
        String status
) {
}
