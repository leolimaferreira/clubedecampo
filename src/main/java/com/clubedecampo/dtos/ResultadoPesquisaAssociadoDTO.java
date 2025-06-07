package com.clubedecampo.dtos;

import java.time.LocalDate;
import java.util.UUID;

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
        UUID tipo_associado_id,
        String status,
        boolean carteirinhaAtiva
) {
}
