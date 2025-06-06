package com.clubedecampo.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AtualizarAreaDTO(
        @Size(max = 50, message = "campo fora do tamanho padrão")
        String nome,
        int quantidadeDisponivel,
        Boolean reservavel,
        @Size(max = 20, message = "campo fora do tamanho padrão")
        String tipoReserva
) {
}
