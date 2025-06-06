package com.clubedecampo.controller;

import com.clubedecampo.dtos.AtualizarAreaDTO;
import com.clubedecampo.dtos.CadastroAreaDTO;
import com.clubedecampo.dtos.ErroRespostaDTO;
import com.clubedecampo.dtos.ResultadoPesquisaAreaDTO;
import com.clubedecampo.entity.Area;
import com.clubedecampo.mappers.AreaMapper;
import com.clubedecampo.service.AreaService;
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
@RequestMapping("/areas")
@RequiredArgsConstructor
public class AreaController implements GenericController {

    private final AreaService areaService;
    private final AreaMapper areaMapper;

    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody CadastroAreaDTO dto) {
        Area area = areaMapper.toEntity(dto);
        areaService.salvar(area);

        URI location = gerarHeaderLocation(area.getId());
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        Optional<Area> areaOptional = areaService.buscarPorId(id);

        if (areaOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        areaService.deletar(areaOptional.get());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> atualizar(@PathVariable UUID id, @RequestBody @Valid AtualizarAreaDTO dto) {
        Optional<Area> areaOptional = areaService.buscarPorId(id);

        if (areaOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErroRespostaDTO(HttpStatus.NOT_FOUND.value(), "Área não cadastrada!", List.of()));
        }

        var area = areaOptional.get();
        if(dto.nome() != null) area.setNome(dto.nome());
        if(dto.quantidadeDisponivel() > 0) area.setQuantidadeDisponivel(dto.quantidadeDisponivel());
        if (dto.reservavel() != null && dto.reservavel() != area.isReservavel()) {
            area.setReservavel(dto.reservavel());
        }
        System.out.println(dto.reservavel());
        if(dto.tipoReserva() != null) area.setTipoReserva(dto.tipoReserva());
        areaService.atualizar(area);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<ResultadoPesquisaAreaDTO> buscarPorId(@PathVariable UUID id) {

        return areaService.buscarPorId(id)
                .map(area -> {
                    var dto = areaMapper.toDto(area);
                    return ResponseEntity.ok(dto);
                }).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Object> listar() {
        List<Area> areas = areaService.listar();

        if (areas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErroRespostaDTO(HttpStatus.NOT_FOUND.value(), "Nenhuma área cadastrada!", List.of()));
        }

        return ResponseEntity.ok(areas);
    }
}
