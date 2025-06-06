package com.clubedecampo.dtos;

public record ResultadoPesquisaAreaDTO(
        String nome,
        int quantidadeDisponivel,
        Boolean reservavel,
        String tipoReserva
){
}
