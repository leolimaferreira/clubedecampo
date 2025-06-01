package com.clubedecampo.controller;

import com.clubedecampo.dtos.CadastroAreaDTO;
import com.clubedecampo.entity.Area;
import com.clubedecampo.mappers.AreaMapper;
import com.clubedecampo.service.AreaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/areas")
@RequiredArgsConstructor
public class AreaController {

    private final AreaService areaService;
    private final AreaMapper areaMapper;

    @PostMapping
    public void salvar(@RequestBody CadastroAreaDTO dto) {
        Area area = areaMapper.toEntity(dto);
        areaService.salvar(area);
    }
}
