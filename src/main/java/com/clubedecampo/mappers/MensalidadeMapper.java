package com.clubedecampo.mappers;

import com.clubedecampo.dtos.CadastroMensalidadeDTO;
import com.clubedecampo.dtos.ResultadoPesquisaMensalidadeDTO;
import com.clubedecampo.entity.Mensalidade;
import com.clubedecampo.repository.AssociadoRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class MensalidadeMapper {

    @Autowired
    AssociadoRepository associadoRepository;

    @Mapping(target = "associado", expression = "java(associadoRepository.findById(dto.associadoId()).orElse(null))")
    public abstract Mensalidade toEntity(CadastroMensalidadeDTO dto);

    @Mapping(target = "associadoId", source = "associado.id")
    public abstract ResultadoPesquisaMensalidadeDTO toDto(Mensalidade dependente);
}
