package com.clubedecampo.repository;

import com.clubedecampo.entity.Associado;
import com.clubedecampo.entity.Dependente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface DependenteRepository extends JpaRepository<Dependente, UUID> {
    Optional<Dependente> findByRg(String rg);

    boolean existsByAssociado(Associado associado);
}
