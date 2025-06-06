package com.clubedecampo.dtos;

import jakarta.validation.constraints.Size;

public record AtualizarPagamentoDTO(
        @Size(max = 20, message = "campo fora do tamanho padrão")
        String status
) {
}
