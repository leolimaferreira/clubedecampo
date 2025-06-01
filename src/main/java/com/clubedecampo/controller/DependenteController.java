package com.clubedecampo.controller;

import com.clubedecampo.dtos.CadastroDependenteDTO;
import com.clubedecampo.entity.Dependente;
import com.clubedecampo.mappers.DependenteMapper;
import com.clubedecampo.service.AssociadoService;
import com.clubedecampo.service.DependenteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dependentes")
@RequiredArgsConstructor
public class DependenteController {

    private final AssociadoService associadoService;
    private final DependenteService dependenteService;
    private final DependenteMapper dependenteMapper;

    @PostMapping
    public void salvar(@RequestBody CadastroDependenteDTO dto) {
        Dependente dependente = dependenteMapper.toEntity(dto);
        dependente.setAssociado(associadoService.buscarPorId(dto.associadoId()).get());
        dependenteService.salvar(dependente);
    }
}
