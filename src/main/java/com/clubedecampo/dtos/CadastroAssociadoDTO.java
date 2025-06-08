package com.clubedecampo.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record CadastroAssociadoDTO(
        @NotBlank(message = "campo obrigatorio")
        @Size(max = 100, message = "campo fora do tamanho padrão")
        String nome,
        @NotBlank(message = "campo obrigatorio")
        @Size(max = 20, message = "campo fora do tamanho padrão")
        String rg,
        @NotBlank(message = "campo obrigatorio")
        @Size(max = 14, message = "campo fora do tamanho padrão")
        String cpf,
        @NotBlank(message = "campo obrigatorio")
        @Size(max = 255, message = "campo fora do tamanho padrão")
        String endereco,
        @NotBlank(message = "campo obrigatorio")
        @Size(max = 10, message = "campo fora do tamanho padrão")
        String cep,
        @NotBlank(message = "campo obrigatorio")
        @Size(max = 50, message = "campo fora do tamanho padrão")
        String bairro,
        @NotBlank(message = "campo obrigatorio")
        @Size(max = 50, message = "campo fora do tamanho padrão")
        String cidade,
        @NotBlank(message = "campo obrigatorio")
        @Size(max = 2, message = "campo fora do tamanho padrão")
        String estado,
        @Size(max = 15, message = "campo fora do tamanho padrão")
        String telefoneResidencial,
        @Size(max = 15, message = "campo fora do tamanho padrão")
        String telefoneComercial,
        @Size(max = 15, message = "campo fora do tamanho padrão")
        String celular,
        UUID tipoAssociadoId) {
}
