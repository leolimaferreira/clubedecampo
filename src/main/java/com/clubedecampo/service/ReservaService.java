package com.clubedecampo.service;

import com.clubedecampo.entity.Reserva;
import com.clubedecampo.repository.ReservaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservaService {

    private final ReservaRepository reservaRepository;

    public void salvar(Reserva reserva) {
        reservaRepository.save(reserva);
    }
}
