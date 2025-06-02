package com.clubedecampo.service;

import com.clubedecampo.entity.Reserva;
import com.clubedecampo.repository.ReservaRepository;
import com.clubedecampo.validator.ReservaValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final ReservaValidator reservaValidator;

    public Reserva salvar(Reserva reserva) {
        reservaValidator.validar(reserva);
        return reservaRepository.save(reserva);
    }
}
