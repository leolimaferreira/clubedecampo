package com.clubedecampo.service;

import com.clubedecampo.entity.Mensalidade;
import com.clubedecampo.exception.OperacaoNaoPermitidaException;
import com.clubedecampo.repository.MensalidadeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MensalidadeService {

    private final MensalidadeRepository mensalidadeRepository;

    public Mensalidade salvar(Mensalidade mensalidade) {
        return mensalidadeRepository.save(mensalidade);
    }

    public void deletar(Mensalidade mensalidade) {
        if(mensalidade.getStatus().equalsIgnoreCase("paga")) {
            throw new OperacaoNaoPermitidaException("Só é possível deletar mensalidades pendentes");
        }

        if(!mensalidade.getDataVencimento().isAfter(LocalDate.now())) {
            throw new OperacaoNaoPermitidaException("Só é possível deletar mensalidades ativas");
        }

        mensalidadeRepository.delete(mensalidade);
    }

    public Optional<Mensalidade> buscarPorId(UUID id) {
        return mensalidadeRepository.findById(id);
    }

    public void atualizar(Mensalidade mensalidade) {
        mensalidadeRepository.save(mensalidade);
    }

    public List<Mensalidade> listar() {
        return mensalidadeRepository.findAll();
    }
}
