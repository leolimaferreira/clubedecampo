package com.clubedecampo.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.UUID;

public record CadastroMensalidadeDTO(
        UUID associadoId,
        YearMonth mesReferencia,
        BigDecimal valorBase,
        BigDecimal valorCorrigido,
        LocalDate dataVencimento
) {
}
