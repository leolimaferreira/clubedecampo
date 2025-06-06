package com.clubedecampo.mappers;

import com.clubedecampo.dtos.CadastroReservaDTO;
import com.clubedecampo.dtos.ResultadoPesquisaReservaDTO;
import com.clubedecampo.entity.Reserva;
import com.clubedecampo.repository.AreaRepository;
import com.clubedecampo.repository.AssociadoRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class ReservaMapper {

    @Autowired
    AssociadoRepository associadoRepository;

    @Autowired
    AreaRepository areaRepository;


    @Mapping(target = "associado", expression = "java(associadoRepository.findById(dto.associadoId()).orElse(null))")
    @Mapping(target = "area", expression = "java(areaRepository.findById(dto.areaId()).orElse(null))")
    public abstract Reserva toEntity(CadastroReservaDTO dto);

    @Mapping(target = "associadoId", source = "associado.id")
    @Mapping(target = "areaId", source = "area.id")
    public abstract ResultadoPesquisaReservaDTO toDto(Reserva reserva);
}
