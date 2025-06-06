package com.clubedecampo.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record AtualizarDependenteDTO(
        @Size(max = 100, message = "campo fora do tamanho padrão")
        String nome,
        @Size(max = 20, message = "campo fora do tamanho padrão")
        String rg,
        UUID associadoId
) {
}
