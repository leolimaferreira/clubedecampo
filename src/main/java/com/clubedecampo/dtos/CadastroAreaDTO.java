package com.clubedecampo.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CadastroAreaDTO(
        @NotBlank(message = "campo obrigatorio")
        @Size(max = 50, message = "campo fora do tamanho padrão")
        String nome,
        @NotNull(message = "campo obrigatorio")
        int quantidadeDisponivel,
        boolean reservavel,
        @Size(max = 20, message = "campo fora do tamanho padrão")
        String tipoReserva
) {
}
