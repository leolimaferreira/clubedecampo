package com.clubedecampo.mappers;

import com.clubedecampo.dtos.CadastroAreaDTO;
import com.clubedecampo.entity.Area;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AreaMapper {

    Area toEntity(CadastroAreaDTO dto);

    CadastroAreaDTO toDto(Area area);

}
