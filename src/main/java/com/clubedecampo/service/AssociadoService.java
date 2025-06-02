package com.clubedecampo.service;

import com.clubedecampo.entity.Associado;
import com.clubedecampo.repository.AssociadoRepository;
import com.clubedecampo.validator.AssociadoValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AssociadoService {

    private final AssociadoRepository associadoRepository;
    private final AssociadoValidator associadoValidator;

    public Associado salvar(Associado associado) {
        associadoValidator.validar(associado);
        associadoRepository.save(associado);
        return associadoRepository.save(associado);
    }

    public Optional<Associado> buscarPorId(UUID id) {
        return associadoRepository.findById(id);
    }
}
