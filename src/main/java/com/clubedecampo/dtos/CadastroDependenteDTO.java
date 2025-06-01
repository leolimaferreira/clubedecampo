package com.clubedecampo.dtos;

import java.util.UUID;

public record CadastroDependenteDTO(
        String nome,
        String rg,
        UUID associadoId
) {
}
