package com.clubedecampo.dtos;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.UUID;

public record CadastroMensalidadeDTO(
        @NotNull(message = "campo obrigatorio")
        UUID associadoId,
        @NotNull(message = "campo obrigatorio")
        YearMonth mesReferencia,
        @NotNull(message = "campo obrigatorio")
        @Future(message = "A data de vencimento deve ser no futuro")
        LocalDate dataVencimento
) {
}
