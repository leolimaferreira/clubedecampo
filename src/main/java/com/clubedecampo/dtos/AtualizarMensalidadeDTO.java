package com.clubedecampo.dtos;

import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

public record AtualizarMensalidadeDTO(
        BigDecimal valorCorrigido,
        LocalDate dataPagamento
) {
}
