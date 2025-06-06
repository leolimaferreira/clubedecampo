package com.clubedecampo.dtos;

import com.clubedecampo.entity.Associado;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record ResultadoPesquisaDependenteDTO (
        String nome,
        String rg,
        UUID associadoId
){
}
