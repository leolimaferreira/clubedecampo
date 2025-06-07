package com.clubedecampo.service;

import com.clubedecampo.entity.TipoAssociado;
import com.clubedecampo.repository.TipoAssociadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TipoAssociadoService {

    private final TipoAssociadoRepository tipoAssociadoRepository;

    public Optional<TipoAssociado> buscarPorId(UUID id) {
        return tipoAssociadoRepository.findById(id);
    }
}
