package com.clubedecampo.controller;

import com.clubedecampo.dtos.AtualizarReservaDTO;
import com.clubedecampo.dtos.CadastroReservaDTO;
import com.clubedecampo.dtos.ErroRespostaDTO;
import com.clubedecampo.dtos.ResultadoPesquisaReservaDTO;
import com.clubedecampo.entity.Reserva;
import com.clubedecampo.mappers.ReservaMapper;
import com.clubedecampo.service.AreaService;
import com.clubedecampo.service.AssociadoService;
import com.clubedecampo.service.ReservaService;
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

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        Optional<Reserva> reservaOptional = reservaService.buscarPorId(id);

        if(reservaOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        reservaService.deletar(reservaOptional.get());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> atualizar(@PathVariable UUID id, @RequestBody @Valid AtualizarReservaDTO dto) {
        Optional<Reserva> reservaOptional = reservaService.buscarPorId(id);

        if(reservaOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErroRespostaDTO(HttpStatus.NOT_FOUND.value(), "Reserva não cadastrada!", List.of()));
        }

        var reserva = reservaOptional.get();
        if(dto.dataReserva() != null) reserva.setDataReserva(dto.dataReserva());
        if(dto.horaInicio() != null) reserva.setHoraInicio(dto.horaInicio());
        if(dto.horaFim() != null) reserva.setHoraFim(dto.horaFim());
        if(dto.numeroPessoas() > 0) reserva.setNumeroPessoas(dto.numeroPessoas());
        reservaService.atualizar(reserva);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<ResultadoPesquisaReservaDTO> buscarPorId(@PathVariable UUID id) {

        return reservaService.buscarPorId(id)
                .map(reserva -> {
                    var dto = reservaMapper.toDto(reserva);
                    return ResponseEntity.ok(dto);
                }).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Object> listar() {
        List<Reserva> reservas = reservaService.listar();

        if (reservas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErroRespostaDTO(HttpStatus.NOT_FOUND.value(), "Nenhuma reserva cadastrada!", List.of()));
        }

        return ResponseEntity.ok(reservas);
    }
}
