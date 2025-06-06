package com.clubedecampo.mappers;

import com.clubedecampo.dtos.AtualizarAssociadoDTO;
import com.clubedecampo.dtos.CadastroAssociadoDTO;
import com.clubedecampo.entity.Associado;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface AssociadoMapper {

    Associado toEntity(CadastroAssociadoDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void atualizarPorDTO(AtualizarAssociadoDTO dto, @MappingTarget Associado associado);

    CadastroAssociadoDTO toDto(Associado associado);
}
