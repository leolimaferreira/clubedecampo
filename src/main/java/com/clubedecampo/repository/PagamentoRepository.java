package com.clubedecampo.repository;

import com.clubedecampo.entity.Associado;
import com.clubedecampo.entity.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PagamentoRepository extends JpaRepository<Pagamento, UUID> {
    boolean existsByAssociado(Associado associado);
}
