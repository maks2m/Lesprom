package com.example.lesprom.controller.rest;

import com.example.lesprom.dto.CutterDto;
import com.example.lesprom.entity.Cutter;
import com.example.lesprom.mapper.CutterMapper;
import com.example.lesprom.service.CutterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cutter")
public class CutterRestController {

    private final CutterService cutterService;

    @Autowired
    public CutterRestController(CutterService cutterService) {
        this.cutterService = cutterService;
    }

    @GetMapping
    public List<CutterDto> list() {
        return CutterMapper.INSTANCE.mapList(cutterService.list());
    }

    @GetMapping("{id}")
    public CutterDto getOne(@PathVariable Long id) {
        return CutterMapper.INSTANCE.mapSingle(cutterService.getById(id));
    }

    @PostMapping
    public CutterDto create(@RequestBody CutterDto modelDto) {
        Cutter model = CutterMapper.INSTANCE.mapSingle(modelDto);
        return CutterMapper.INSTANCE.mapSingle(cutterService.save(null ,model));
    }

    @PutMapping("{id}")
    public CutterDto update(@PathVariable Long id, @RequestBody CutterDto modelDto) {
        Cutter model = CutterMapper.INSTANCE.mapSingle(modelDto);
        return CutterMapper.INSTANCE.mapSingle(cutterService.save(id, model));
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        cutterService.delete(id);
    }

}
