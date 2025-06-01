package com.clubedecampo.repository;

import com.clubedecampo.entity.Mensalidade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MensalidadeRepository extends JpaRepository<Mensalidade, UUID> {
}
