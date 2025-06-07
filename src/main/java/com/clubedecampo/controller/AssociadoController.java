package com.clubedecampo.controller;

import com.clubedecampo.dtos.AtualizarAssociadoDTO;
import com.clubedecampo.dtos.CadastroAssociadoDTO;
import com.clubedecampo.dtos.ErroRespostaDTO;
import com.clubedecampo.dtos.ResultadoPesquisaAssociadoDTO;
import com.clubedecampo.entity.Associado;
import com.clubedecampo.mappers.AssociadoMapper;
import com.clubedecampo.service.AssociadoService;
import com.clubedecampo.service.TipoAssociadoService;
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
@RequestMapping("/associados")
@RequiredArgsConstructor
public class AssociadoController implements GenericController {

    private final AssociadoService associadoService;
    private final AssociadoMapper associadoMapper;
    private final TipoAssociadoService tipoAssociadoService;

    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody @Valid CadastroAssociadoDTO dto) {

        Associado associado = associadoMapper.toEntity(dto);
        associado.setTipoAssociado(tipoAssociadoService.buscarPorId(dto.tipo_associado_id()).get());
        associadoService.salvar(associado);
        URI location = gerarHeaderLocation(associado.getId());
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        Optional<Associado> associadoOptional = associadoService.buscarPorId(id);

        if (associadoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        associadoService.deletar(associadoOptional.get());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> atualizar(@PathVariable UUID id, @RequestBody @Valid AtualizarAssociadoDTO dto) {
        Optional<Associado> associadoOptional = associadoService.buscarPorId(id);

        if (associadoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErroRespostaDTO(HttpStatus.NOT_FOUND.value(), "Associado não cadastrado!", List.of()));
        }

        var associado = associadoOptional.get();
        associadoMapper.atualizarPorDTO(dto, associado);

        associadoService.atualizar(associado);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<ResultadoPesquisaAssociadoDTO> buscarPorId(@PathVariable UUID id) {

        return associadoService.buscarPorId(id)
                .map(associado -> {
                    var dto = associadoMapper.toDto(associado);
                    return ResponseEntity.ok(dto);
                }).orElseGet( () -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Object> listar() {
        List<Associado> associados = associadoService.listar();

        if (associados.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErroRespostaDTO(HttpStatus.NOT_FOUND.value(), "Nenhum associado cadastrado!", List.of()));
        }

        return ResponseEntity.ok(associados);
    }
}
