package com.clubedecampo.service;

import com.clubedecampo.entity.Associado;
import com.clubedecampo.repository.*;
import com.clubedecampo.validator.AssociadoValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AssociadoService {

    private final AssociadoRepository associadoRepository;
    private final AssociadoValidator associadoValidator;
    private final DependenteRepository dependenteRepository;
    private final ReservaRepository reservaRepository;
    private final MensalidadeRepository mensalidadeRepository;
    private final PagamentoRepository pagamentoRepository;

    public Associado salvar(Associado associado) {
        associadoValidator.validarCadastroEAtualizacao(associado);
        return associadoRepository.save(associado);
    }

    public Optional<Associado> buscarPorId(UUID id) {
        return associadoRepository.findById(id);
    }

    public void deletar(Associado associado) {
        associadoValidator.validarRemocao(associado);
        associadoRepository.delete(associado);
    }

    public void atualizar(Associado associado) {
        associadoValidator.validarCadastroEAtualizacao(associado);
        associadoRepository.save(associado);
    }

    public List<Associado> listar() {
        return associadoRepository.findAll();
    }

}
