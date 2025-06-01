package com.clubedecampo.dtos;

import com.clubedecampo.entity.TipoAssociado;

public record CadastroAssociadoDTO(
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
        TipoAssociado tipoAssociado) {
}
