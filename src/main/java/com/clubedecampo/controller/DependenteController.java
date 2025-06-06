package com.clubedecampo.controller;

import com.clubedecampo.dtos.AtualizarDependenteDTO;
import com.clubedecampo.dtos.CadastroDependenteDTO;
import com.clubedecampo.dtos.ErroRespostaDTO;
import com.clubedecampo.entity.Dependente;
import com.clubedecampo.mappers.DependenteMapper;
import com.clubedecampo.service.AssociadoService;
import com.clubedecampo.service.DependenteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        Optional<Dependente> dependenteOptional = dependenteService.obterPorId(id);

        if (dependenteOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        dependenteService.deletar(dependenteOptional.get());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> atualizar(@PathVariable UUID id, @RequestBody @Valid AtualizarDependenteDTO dto) {
        Optional<Dependente> dependenteOptional = dependenteService.obterPorId(id);

        if (dependenteOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErroRespostaDTO(HttpStatus.NOT_FOUND.value(), "Dependente não cadastrado!", List.of()));
        }

        var dependente = dependenteOptional.get();
        if(dto.nome() != null) dependente.setNome(dto.nome());
        if(dto.rg() != null) dependente.setRg(dto.rg());
        if(dto.associadoId() != null) dependente.setAssociado(associadoService.buscarPorId(dto.associadoId()).get());
        dependenteService.salvar(dependente);
        return ResponseEntity.noContent().build();
    }
}
