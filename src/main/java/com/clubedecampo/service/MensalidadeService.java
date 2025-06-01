package com.clubedecampo.service;

import com.clubedecampo.entity.Mensalidade;
import com.clubedecampo.repository.MensalidadeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MensalidadeService {

    private final MensalidadeRepository mensalidadeRepository;

    public void salvar(Mensalidade mensalidade) {
        mensalidadeRepository.save(mensalidade);
    }
}
