package com.clubedecampo.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record CadastroPagamentoDTO(
        UUID associadoId,
        BigDecimal valor,
        LocalDate dataPagamento,
        String formaPagamento
) {
}
