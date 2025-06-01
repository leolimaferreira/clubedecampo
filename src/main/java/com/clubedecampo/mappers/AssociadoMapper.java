package com.clubedecampo.mappers;

import com.clubedecampo.dtos.CadastroAssociadoDTO;
import com.clubedecampo.entity.Associado;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AssociadoMapper {

    Associado toEntity(CadastroAssociadoDTO dto);

    CadastroAssociadoDTO toDto(Associado associado);
}
