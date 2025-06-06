package com.clubedecampo.validator;

import com.clubedecampo.entity.Associado;
import com.clubedecampo.exception.OperacaoNaoPermitidaException;
import com.clubedecampo.exception.RegistroDuplicadoException;
import com.clubedecampo.repository.*;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AssociadoValidator {

    private AssociadoRepository associadoRepository;
    private ReservaRepository reservaRepository;
    private PagamentoRepository pagamentoRepository;
    private MensalidadeRepository mensalidadeRepository;
    private DependenteRepository dependenteRepository;

    public AssociadoValidator(AssociadoRepository associadoRepository) {
        this.associadoRepository = associadoRepository;
    }

    public void validarCadastroEAtualizacao(Associado associado) {
        if(existeAssociadoCadastrado(associado)) {
            throw new RegistroDuplicadoException("Associado já cadastrado");
        }
    }

    private boolean existeAssociadoCadastrado(Associado associado) {
        Optional<Associado> associadoEncontrado = associadoRepository.findByRgOrCpf(associado.getRg(), associado.getCpf());
        if(associado.getId() == null) {
            return associadoEncontrado.isPresent();
        }

        return !associado.getId().equals(associadoEncontrado.get().getId()) && associadoEncontrado.isPresent();
    }

    public void validarRemocao(Associado associado) {
        if (possuiDependentes(associado)) {
            throw new OperacaoNaoPermitidaException("Associado possui dependentes");
        }

        if (possuiReservas(associado)) {
            throw new OperacaoNaoPermitidaException("Associado possui reservas");
        }

        if (possuiPagamentos(associado)) {
            throw new OperacaoNaoPermitidaException("Associado possui pagamentos");
        }

        if (possuiMensalidades(associado)) {
            throw new OperacaoNaoPermitidaException("Associado possui mensalidades");
        }
    }

    private boolean possuiDependentes(Associado associado) {
        return dependenteRepository.existsByAssociado(associado);
    }

    private boolean possuiReservas(Associado associado) {
        return reservaRepository.existsByAssociado(associado);
    }

    private boolean possuiPagamentos(Associado associado) {
        return pagamentoRepository.existsByAssociado(associado);
    }

    private boolean possuiMensalidades(Associado associado) {
        return mensalidadeRepository.existsByAssociado(associado);
    }

}
