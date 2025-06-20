package com.clubedecampo.controller;

import com.clubedecampo.dtos.AtualizarPagamentoDTO;
import com.clubedecampo.dtos.CadastroPagamentoDTO;
import com.clubedecampo.dtos.ErroRespostaDTO;
import com.clubedecampo.dtos.ResultadoPesquisaPagamentoDTO;
import com.clubedecampo.entity.Mensalidade;
import com.clubedecampo.entity.Pagamento;
import com.clubedecampo.mappers.PagamentoMapper;
import com.clubedecampo.service.AssociadoService;
import com.clubedecampo.service.MensalidadeService;
import com.clubedecampo.service.PagamentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
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
    private final MensalidadeService mensalidadeService;

    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody CadastroPagamentoDTO dto) {
        Pagamento pagamento = pagamentoMapper.toEntity(dto);
        Mensalidade mensalidade = mensalidadeService.buscarPorId(dto.mensalidadeId()).get();

        pagamento.setAssociado(associadoService.buscarPorId(dto.associadoId()).get());
        pagamento.setMensalidade(mensalidade);
        pagamento.setDataPagamento(LocalDate.now());
        mensalidade.setDataPagamento(pagamento.getDataPagamento());
        pagamentoService.calcularValorCorrigido(pagamento);
        pagamento.setValor(mensalidade.getValorCorrigido());

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

    @GetMapping
    public ResponseEntity<Object> listar() {
        List<Pagamento> pagamentos = pagamentoService.listar();

        if (pagamentos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErroRespostaDTO(HttpStatus.NOT_FOUND.value(), "Nenhum pagamento cadastrado!", List.of()));
        }

        return ResponseEntity.ok(pagamentos);
    }
}
