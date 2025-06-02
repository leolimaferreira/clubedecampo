package com.clubedecampo.repository;

import com.clubedecampo.entity.Associado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AssociadoRepository extends JpaRepository<Associado, UUID> {
    Optional<Associado> findByRgOrCpf(String rg, String cpf);
}
