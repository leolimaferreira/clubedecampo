package com.clubedecampo.repository;

import com.clubedecampo.entity.Dependente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DependenteRepository extends JpaRepository<Dependente, UUID> {
}
