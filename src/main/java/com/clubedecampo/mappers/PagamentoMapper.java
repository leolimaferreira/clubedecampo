package com.clubedecampo.mappers;

import com.clubedecampo.dtos.CadastroPagamentoDTO;
import com.clubedecampo.dtos.ResultadoPesquisaPagamentoDTO;
import com.clubedecampo.entity.Pagamento;
import com.clubedecampo.repository.AssociadoRepository;
import com.clubedecampo.service.AssociadoService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class PagamentoMapper {

    @Autowired
    AssociadoRepository associadoRepository;

    @Mapping(target = "associado", expression = "java(associadoRepository.findById(dto.associadoId()).orElse(null))")
    public abstract Pagamento toEntity(CadastroPagamentoDTO dto);

    @Mapping(target = "associadoId", source = "associado.id")
    public abstract ResultadoPesquisaPagamentoDTO toDto(Pagamento dependente);
}
