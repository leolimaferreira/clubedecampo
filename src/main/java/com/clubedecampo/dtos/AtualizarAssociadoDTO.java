package com.clubedecampo.dtos;

import com.clubedecampo.entity.TipoAssociado;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record AtualizarAssociadoDTO(
        @Size(max = 100, message = "campo fora do tamanho padrão")
        String nome,
        @Size(max = 20, message = "campo fora do tamanho padrão")
        String rg,
        @Size(max = 14, message = "campo fora do tamanho padrão")
        String cpf,
        @Size(max = 255, message = "campo fora do tamanho padrão")
        String endereco,
        @Size(max = 10, message = "campo fora do tamanho padrão")
        String cep,
        @Size(max = 50, message = "campo fora do tamanho padrão")
        String bairro,
        @Size(max = 50, message = "campo fora do tamanho padrão")
        String cidade,
        @Size(max = 2, message = "campo fora do tamanho padrão")
        String estado,
        @Size(max = 15, message = "campo fora do tamanho padrão")
        String telefoneResidencial,
        @Size(max = 15, message = "campo fora do tamanho padrão")
        String telefoneComercial,
        @Size(max = 15, message = "campo fora do tamanho padrão")
        String celular,
        TipoAssociado tipoAssociado,
        @Size(max = 20, message = "campo fora do tamanho padrão")
        String status,
        boolean carteirinhaAtiva
) {
}
