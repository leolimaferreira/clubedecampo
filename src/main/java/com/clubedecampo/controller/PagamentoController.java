package com.clubedecampo.controller;

import com.clubedecampo.dtos.CadastroPagamentoDTO;
import com.clubedecampo.entity.Pagamento;
import com.clubedecampo.mappers.PagamentoMapper;
import com.clubedecampo.service.AssociadoService;
import com.clubedecampo.service.PagamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pagamentos")
@RequiredArgsConstructor
public class PagamentoController {

    private final PagamentoService pagamentoService;
    private final AssociadoService associadoService;
    private final PagamentoMapper pagamentoMapper;

    @PostMapping
    public void salvar(@RequestBody CadastroPagamentoDTO dto) {
        Pagamento pagamento = pagamentoMapper.toEntity(dto);
        pagamento.setAssociado(associadoService.buscarPorId(dto.associadoId()).get());
        pagamentoService.salvar(pagamento);
    }
}
