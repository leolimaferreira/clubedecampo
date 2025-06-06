package com.clubedecampo.dtos;

import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

public record AtualizarMensalidadeDTO(
        BigDecimal valorCorrigido,
        @Size(max = 20, message = "campo fora do tamanho padrão")
        String status,
        LocalDate dataPagamento
) {
}
