package com.clubedecampo.repository;

import com.clubedecampo.entity.TipoAssociado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TipoAssociadoRepository extends JpaRepository<TipoAssociado, UUID> {
}
