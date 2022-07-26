package com.example.lesprom.controller.rest;

import com.example.lesprom.dto.BaguetteDto;
import com.example.lesprom.entity.Baguette;
import com.example.lesprom.mapper.BaguetteMapper;
import com.example.lesprom.service.BaguetteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/baguette")
public class BaguetteRestController {

    private final BaguetteService baguetteService;

    @Autowired
    public BaguetteRestController(BaguetteService baguetteService) {
        this.baguetteService = baguetteService;
    }

    @GetMapping
    public List<BaguetteDto> list() {
        return BaguetteMapper.INSTANCE.mapList(baguetteService.list());
    }

    @GetMapping("{id}")
    public BaguetteDto getOne(@PathVariable Long id) {
        return BaguetteMapper.INSTANCE.mapSingle(baguetteService.getById(id));
    }

    @PostMapping
    public BaguetteDto create(@RequestBody BaguetteDto modelDto) {
        Baguette model = BaguetteMapper.INSTANCE.mapSingle(modelDto);
        return BaguetteMapper.INSTANCE.mapSingle(baguetteService.save(null ,model));
    }

    @PutMapping("{id}")
    public BaguetteDto update(@PathVariable Long id, @RequestBody BaguetteDto modelDto) {
        Baguette model = BaguetteMapper.INSTANCE.mapSingle(modelDto);
        return BaguetteMapper.INSTANCE.mapSingle(baguetteService.save(id, model));
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        baguetteService.delete(id);
    }

}
