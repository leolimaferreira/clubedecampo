package com.clubedecampo.service;

import com.clubedecampo.entity.Dependente;
import com.clubedecampo.repository.DependenteRepository;
import com.clubedecampo.validator.DependenteValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DependenteService {

    private final DependenteRepository dependenteRepository;
    private final DependenteValidator dependenteValidator;

    public Dependente salvar(Dependente dependente) {
        dependenteValidator.validar(dependente);
        return dependenteRepository.save(dependente);
    }

    public void deletar(Dependente dependente) {
        dependenteRepository.delete(dependente);
    }

    public Optional<Dependente> buscarPorId(UUID id) {
        return dependenteRepository.findById(id);
    }

    public void atualizar(Dependente dependente) {
        dependenteValidator.validar(dependente);
        dependenteRepository.save(dependente);
    }
}
