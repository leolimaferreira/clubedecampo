package com.clubedecampo.dtos;

import com.clubedecampo.entity.TipoAssociado;

import java.time.LocalDate;

public record ResultadoPesquisaAssociadoDTO(
        String nome,
        String rg,
        String cpf,
        String endereco,
        String cep,
        String bairro,
        String cidade,
        String estado,
        String telefoneResidencial,
        String telefoneComercial,
        String celular,
        LocalDate dataCadastro,
        TipoAssociado tipoAssociado,
        String status,
        boolean carteirinhaAtiva
) {
}
