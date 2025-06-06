package com.clubedecampo.dtos;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public record AtualizarReservaDTO(
        LocalDate dataReserva,
        LocalTime horaInicio,
        LocalTime horaFim,
        int numeroPessoas
) {
}
