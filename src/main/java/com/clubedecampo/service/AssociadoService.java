package com.clubedecampo.service;

import com.clubedecampo.entity.Associado;
import com.clubedecampo.repository.AssociadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AssociadoService {

    private final AssociadoRepository associadoRepository;

    public void salvar(Associado associado) {
        associadoRepository.save(associado);
    }

    public Optional<Associado> buscarPorId(UUID id) {
        return associadoRepository.findById(id);
    }
}
