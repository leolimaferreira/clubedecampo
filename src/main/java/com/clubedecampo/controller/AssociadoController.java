package com.clubedecampo.controller;

import com.clubedecampo.dtos.CadastroAssociadoDTO;
import com.clubedecampo.entity.Associado;
import com.clubedecampo.mappers.AssociadoMapper;
import com.clubedecampo.service.AssociadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/associados")
@RequiredArgsConstructor
public class AssociadoController {

    private final AssociadoService associadoService;
    private final AssociadoMapper associadoMapper;

    @PostMapping
    public void salvar(@RequestBody CadastroAssociadoDTO dto) {
        Associado associado = associadoMapper.toEntity(dto);
        associadoService.salvar(associado);
    }
}
