package com.clubedecampo.service;

import com.clubedecampo.entity.Dependente;
import com.clubedecampo.repository.DependenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DependenteService {

    private final DependenteRepository dependenteRepository;

    public void salvar(Dependente dependente) {
        dependenteRepository.save(dependente);
    }
}
