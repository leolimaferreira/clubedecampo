package com.clubedecampo.dtos;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public record ResultadoPesquisaReservaDTO(
        UUID associadoId,
        UUID areaId,
        LocalDate dataReserva,
        LocalTime horaInicio,
        LocalTime horaFim,
        int numeroPessoas
) {
}
