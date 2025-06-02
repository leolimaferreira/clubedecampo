package com.clubedecampo.controller;

import com.clubedecampo.dtos.CadastroReservaDTO;
import com.clubedecampo.entity.Reserva;
import com.clubedecampo.mappers.AreaMapper;
import com.clubedecampo.mappers.ReservaMapper;
import com.clubedecampo.service.AreaService;
import com.clubedecampo.service.AssociadoService;
import com.clubedecampo.service.ReservaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/reservas")
@RequiredArgsConstructor
public class ReservaController implements GenericController {

    private final ReservaService reservaService;
    private final AssociadoService associadoService;
    private final AreaService areaService;
    private final ReservaMapper reservaMapper;

    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody CadastroReservaDTO dto) {

        Reserva reserva = reservaMapper.toEntity(dto);
        reserva.setAssociado(associadoService.buscarPorId(dto.associadoId()).get());
        reserva.setArea(areaService.buscarPorId(dto.areaId()).get());
        reservaService.salvar(reserva);
        URI location = gerarHeaderLocation(reserva.getId());
        return ResponseEntity.created(location).build();
    }
}
