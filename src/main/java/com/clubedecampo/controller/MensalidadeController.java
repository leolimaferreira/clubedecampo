package com.clubedecampo.controller;

import com.clubedecampo.dtos.CadastroMensalidadeDTO;
import com.clubedecampo.entity.Mensalidade;
import com.clubedecampo.mappers.MensalidadeMapper;
import com.clubedecampo.service.AssociadoService;
import com.clubedecampo.service.MensalidadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mensalidades")
@RequiredArgsConstructor
public class MensalidadeController {

    private final MensalidadeService mensalidadeService;
    private final AssociadoService associadoService;
    private final MensalidadeMapper mensalidadeMapper;

    @PostMapping
    public void salvar(@RequestBody CadastroMensalidadeDTO dto) {
        Mensalidade mensalidade = mensalidadeMapper.toEntity(dto);
        mensalidade.setAssociado(associadoService.buscarPorId(dto.associadoId()).get());
        mensalidadeService.salvar(mensalidade);
    }
}
