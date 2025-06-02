package com.clubedecampo.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record CadastroDependenteDTO(
        @NotBlank(message = "campo obrigatorio")
        @Size(max = 100, message = "campo fora do tamanho padrão")
        String nome,
        @NotBlank(message = "campo obrigatorio")
        @Size(max = 20, message = "campo fora do tamanho padrão")
        String rg,
        @NotNull(message = "campo obrigatorio")
        UUID associadoId
) {
}
