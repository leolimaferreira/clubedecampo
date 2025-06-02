package com.clubedecampo.service;

import com.clubedecampo.entity.Dependente;
import com.clubedecampo.repository.DependenteRepository;
import com.clubedecampo.validator.DependenteValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DependenteService {

    private final DependenteRepository dependenteRepository;
    private final DependenteValidator dependenteValidator;

    public Dependente salvar(Dependente dependente) {
        dependenteValidator.validar(dependente);
        return dependenteRepository.save(dependente);
    }
}
