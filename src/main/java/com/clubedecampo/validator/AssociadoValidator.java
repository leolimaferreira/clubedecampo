package com.clubedecampo.validator;

import com.clubedecampo.entity.Associado;
import com.clubedecampo.exception.RegistroDuplicadoException;
import com.clubedecampo.repository.AssociadoRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AssociadoValidator {

    private AssociadoRepository associadoRepository;

    public AssociadoValidator(AssociadoRepository associadoRepository) {
        this.associadoRepository = associadoRepository;
    }

    public void validar(Associado associado) {
        if(existeAssociadoCadastrado(associado)) {
            throw new RegistroDuplicadoException("Associado já cadastrado");
        }
    }

    private boolean existeAssociadoCadastrado(Associado associado) {
        Optional<Associado> associadoEncontrado = associadoRepository.findByRgOrCpf(associado.getRg(), associado.getCpf());
        if(associado.getId() == null) {
            return associadoEncontrado.isPresent();
        }

        return !associado.getId().equals(associadoEncontrado.get().getId()) && associadoEncontrado.isPresent();
    }


}
