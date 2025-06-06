package com.clubedecampo.mappers;

import com.clubedecampo.dtos.AtualizarDependenteDTO;
import com.clubedecampo.dtos.CadastroDependenteDTO;
import com.clubedecampo.entity.Dependente;
import com.clubedecampo.repository.AssociadoRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class DependenteMapper {

    @Autowired
    AssociadoRepository associadoRepository;

    @Mapping(target = "associado", expression = "java(associadoRepository.findById(dto.associadoId()).orElse(null))")
    public abstract Dependente toEntity(CadastroDependenteDTO dto);

    @Mapping(target = "associadoId", source = "associado.id")
    public abstract CadastroDependenteDTO toDto(Dependente dependente);
}
