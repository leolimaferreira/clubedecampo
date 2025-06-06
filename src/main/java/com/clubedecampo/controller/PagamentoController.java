package com.clubedecampo.controller;

import com.clubedecampo.dtos.AtualizarPagamentoDTO;
import com.clubedecampo.dtos.CadastroPagamentoDTO;
import com.clubedecampo.dtos.ErroRespostaDTO;
import com.clubedecampo.dtos.ResultadoPesquisaPagamentoDTO;
import com.clubedecampo.entity.Pagamento;
import com.clubedecampo.mappers.PagamentoMapper;
import com.clubedecampo.service.AssociadoService;
import com.clubedecampo.service.PagamentoService;
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
@RequestMapping("/pagamentos")
@RequiredArgsConstructor
public class PagamentoController implements GenericController{

    private final PagamentoService pagamentoService;
    private final AssociadoService associadoService;
    private final PagamentoMapper pagamentoMapper;

    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody CadastroPagamentoDTO dto) {
        Pagamento pagamento = pagamentoMapper.toEntity(dto);
        pagamento.setAssociado(associadoService.buscarPorId(dto.associadoId()).get());
        pagamentoService.salvar(pagamento);

        URI location = gerarHeaderLocation(pagamento.getId());
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        Optional<Pagamento> pagamentoOptional = pagamentoService.buscarPorId(id);

        if(pagamentoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        pagamentoService.deletar(pagamentoOptional.get());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> atualizar(@PathVariable UUID id, @RequestBody @Valid AtualizarPagamentoDTO dto) {
        Optional<Pagamento> pagamentoOptional = pagamentoService.buscarPorId(id);

        if(pagamentoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErroRespostaDTO(HttpStatus.NOT_FOUND.value(), "Pagamento não cadastrado!", List.of()));
        }

        var pagamento = pagamentoOptional.get();
        if (dto.status() != null) pagamento.setStatus(dto.status());
        pagamentoService.atualizar(pagamento);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<ResultadoPesquisaPagamentoDTO> buscarPorId(@PathVariable UUID id) {

        return pagamentoService.buscarPorId(id)
                .map(pagamento -> {
                    var dto = pagamentoMapper.toDto(pagamento);
                    return ResponseEntity.ok(dto);
                }).orElseGet( () -> ResponseEntity.notFound().build());
    }
}
