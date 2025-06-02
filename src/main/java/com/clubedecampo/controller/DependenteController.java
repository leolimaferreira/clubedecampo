package com.clubedecampo.controller;

import com.clubedecampo.dtos.CadastroDependenteDTO;
import com.clubedecampo.entity.Dependente;
import com.clubedecampo.mappers.DependenteMapper;
import com.clubedecampo.service.AssociadoService;
import com.clubedecampo.service.DependenteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/dependentes")
@RequiredArgsConstructor
public class DependenteController implements GenericController {

    private final AssociadoService associadoService;
    private final DependenteService dependenteService;
    private final DependenteMapper dependenteMapper;

    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody @Valid CadastroDependenteDTO dto) {

        Dependente dependente = dependenteMapper.toEntity(dto);
        dependente.setAssociado(associadoService.buscarPorId(dto.associadoId()).get());
        dependenteService.salvar(dependente);
        URI location = gerarHeaderLocation(dependente.getId());
        return ResponseEntity.created(location).build();
    }
}
