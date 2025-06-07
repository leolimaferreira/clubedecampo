package com.clubedecampo.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record CadastroPagamentoDTO(
        @NotNull(message = "campo obrigatorio")
        UUID associadoId,
        @NotBlank(message = "campo obrigatorio")
        @Size(max = 20, message = "campo fora do tamanho padrão")
        String formaPagamento,
        UUID mensalidadeId
) {
}
