package com.clubedecampo.controller;

import com.clubedecampo.dtos.AtualizarMensalidadeDTO;
import com.clubedecampo.dtos.CadastroMensalidadeDTO;
import com.clubedecampo.dtos.ErroRespostaDTO;
import com.clubedecampo.dtos.ResultadoPesquisaMensalidadeDTO;
import com.clubedecampo.entity.Associado;
import com.clubedecampo.entity.Mensalidade;
import com.clubedecampo.mappers.MensalidadeMapper;
import com.clubedecampo.service.AssociadoService;
import com.clubedecampo.service.MensalidadeService;
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
@RequestMapping("/mensalidades")
@RequiredArgsConstructor
public class MensalidadeController implements GenericController {

    private final MensalidadeService mensalidadeService;
    private final AssociadoService associadoService;
    private final MensalidadeMapper mensalidadeMapper;

    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody CadastroMensalidadeDTO dto) {
        Mensalidade mensalidade = mensalidadeMapper.toEntity(dto);
        Associado associado = associadoService.buscarPorId(dto.associadoId()).get();
        mensalidade.setAssociado(associado);
        mensalidade.setValorBase(associado.getTipoAssociado().getValorBase());
        mensalidadeService.salvar(mensalidade);

        URI location = gerarHeaderLocation(mensalidade.getId());
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        Optional<Mensalidade> mensalidadeOptional = mensalidadeService.buscarPorId(id);

        if(mensalidadeOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        mensalidadeService.deletar(mensalidadeOptional.get());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> atualizar(@PathVariable UUID id, @RequestBody @Valid AtualizarMensalidadeDTO dto) {
        Optional<Mensalidade> mensalidadeOptional = mensalidadeService.buscarPorId(id);

        if(mensalidadeOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErroRespostaDTO(HttpStatus.NOT_FOUND.value(), "Mensalidade não cadastrada!", List.of()));
        }

        var mensalidade = mensalidadeOptional.get();
        if(dto.valorCorrigido() != null) mensalidade.setValorCorrigido(dto.valorCorrigido());
        if(dto.dataPagamento() != null) mensalidade.setDataPagamento(dto.dataPagamento());
        mensalidadeService.atualizar(mensalidade);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<ResultadoPesquisaMensalidadeDTO> buscarPorId(@PathVariable UUID id) {

        return mensalidadeService.buscarPorId(id)
                .map(mensalidade -> {
                    var dto = mensalidadeMapper.toDto(mensalidade);
                    return ResponseEntity.ok(dto);
                }).orElseGet( () -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Object> listar() {
        List<Mensalidade> mensalidades = mensalidadeService.listar();

        if (mensalidades.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErroRespostaDTO(HttpStatus.NOT_FOUND.value(), "Nenhuma mensalidade cadastrada!", List.of()));
        }

        return ResponseEntity.ok(mensalidades);
    }
}
