package com.clubedecampo.controller;

import com.clubedecampo.dtos.CadastroAssociadoDTO;
import com.clubedecampo.entity.Associado;
import com.clubedecampo.mappers.AssociadoMapper;
import com.clubedecampo.service.AssociadoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/associados")
@RequiredArgsConstructor
public class AssociadoController implements GenericController {

    private final AssociadoService associadoService;
    private final AssociadoMapper associadoMapper;

    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody @Valid CadastroAssociadoDTO dto) {

        Associado associado = associadoMapper.toEntity(dto);
        associadoService.salvar(associado);
        URI location = gerarHeaderLocation(associado.getId());
        return ResponseEntity.created(location).build();
    }
}
