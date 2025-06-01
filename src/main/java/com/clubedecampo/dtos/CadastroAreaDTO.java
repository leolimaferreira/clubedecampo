package com.clubedecampo.dtos;

public record CadastroAreaDTO(
        String nome,
        int quantidadeDisponivel,
        boolean reservavel,
        String tipoReserva
) {
}
