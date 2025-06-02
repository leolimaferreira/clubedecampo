package com.clubedecampo.dtos;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public record CadastroReservaDTO(
        @NotNull(message = "campo obrigatorio")
        UUID associadoId,
        @NotNull(message = "campo obrigatorio")
        UUID areaId,
        @NotNull(message = "campo obrigatorio")
        LocalDate dataReserva,
        @NotNull(message = "campo obrigatorio")
        LocalTime horaInicio,
        @NotNull(message = "campo obrigatorio")
        LocalTime horaFim,
        int numeroPessoas
) {
}
