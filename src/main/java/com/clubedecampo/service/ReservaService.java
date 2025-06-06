package com.clubedecampo.service;

import com.clubedecampo.entity.Reserva;
import com.clubedecampo.exception.OperacaoNaoPermitidaException;
import com.clubedecampo.repository.ReservaRepository;
import com.clubedecampo.validator.ReservaValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final ReservaValidator reservaValidator;

    public Reserva salvar(Reserva reserva) {
        reservaValidator.validar(reserva);
        return reservaRepository.save(reserva);
    }

    public void deletar(Reserva reserva) {
        if (!reserva.getDataReserva().isAfter(LocalDate.now())) {
            throw new OperacaoNaoPermitidaException("Só é possível deletar reservas futuras");
        }

        reservaRepository.delete(reserva);
    }

    public Optional<Reserva> buscarPorId(UUID id) {
        return reservaRepository.findById(id);
    }

    public void atualizar(Reserva reserva) {
        reservaValidator.validar(reserva);
        reservaRepository.save(reserva);
    }
}
