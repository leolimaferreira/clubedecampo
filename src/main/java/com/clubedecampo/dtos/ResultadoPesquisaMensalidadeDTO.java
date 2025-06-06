package com.clubedecampo.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.UUID;

public record ResultadoPesquisaMensalidadeDTO(
        UUID associadoId,
        YearMonth mesReferencia,
        BigDecimal valorBase,
        BigDecimal valorCorrigido,
        String status,
        LocalDate dataVencimento,
        LocalDate dataPagamento
) {
}
