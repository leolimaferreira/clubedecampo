package com.clubedecampo.validator;

import com.clubedecampo.entity.Reserva;
import com.clubedecampo.exception.AreaNaoReservavelException;
import com.clubedecampo.exception.AreaNaoSuportaNumPessoasException;
import com.clubedecampo.exception.RegistroDuplicadoException;
import com.clubedecampo.repository.ReservaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReservaValidator {

    private final ReservaRepository reservaRepository;

    public ReservaValidator(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    public void validar(Reserva reserva) {
        if(existeReservaConflitante(reserva)) {
            throw new RegistroDuplicadoException(reserva.getArea().getNome() + " já reservado(a) nessa data e horário");
        }

        if(reserva.getArea().getQuantidadeDisponivel() < reserva.getNumeroPessoas()) {
            throw new AreaNaoSuportaNumPessoasException(reserva.getArea().getNome() + " suporta no máximo " + reserva.getArea().getQuantidadeDisponivel() + " pessoas");
        }

        if(!reserva.getArea().isReservavel()) {
            throw new AreaNaoReservavelException(reserva.getArea().getNome() + " não é reservável");
        }
    }

    private boolean existeReservaConflitante(Reserva reserva) {
        List<Reserva> conflitos = reservaRepository.findReservasConflitantes(
                reserva.getArea(),
                reserva.getDataReserva(),
                reserva.getHoraInicio(),
                reserva.getHoraFim()
        );

        return conflitos.stream()
                .anyMatch(r -> !r.getId().equals(reserva.getId()));
    }
}
