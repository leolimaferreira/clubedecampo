package com.clubedecampo.validator;

import com.clubedecampo.entity.Dependente;
import com.clubedecampo.exception.RegistroDuplicadoException;
import com.clubedecampo.repository.DependenteRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DependenteValidator {

    private DependenteRepository dependenteRepository;

    public DependenteValidator(DependenteRepository dependenteRepository) {
        this.dependenteRepository = dependenteRepository;
    }

    public void validar(Dependente dependente) {
        if(existeDependenteCadastrado(dependente)) {
            throw new RegistroDuplicadoException("Dependente já cadastrado");
        }
    }

    private boolean existeDependenteCadastrado(Dependente dependente) {
        Optional<Dependente> dependenteEncontrado = dependenteRepository.findByRg(dependente.getRg());
        if(dependente.getId() == null) {
            return dependenteEncontrado.isPresent();
        }

        return !dependente.getId().equals(dependenteEncontrado.get().getId()) && dependenteEncontrado.isPresent();
    }
}
