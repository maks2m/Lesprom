package com.example.lesprom.controller.rest;

import com.example.lesprom.dto.cutter.Cutter;
import com.example.lesprom.mapper.CutterMapper;
import com.example.lesprom.service.rest.CutterRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cutter")
public class CutterRestController {

    private final CutterRestService service;

    @Autowired
    public CutterRestController(CutterRestService service) {
        this.service = service;
    }

    @GetMapping
    public List<Cutter> list() {
        return CutterMapper.INSTANCE.mapList(service.list());
    }

    @GetMapping("{id}")
    public Cutter getOne(@PathVariable Long id) {
        return CutterMapper.INSTANCE.mapSingle(service.getById(id));
    }

    @PostMapping
    public Cutter create(@RequestBody Cutter modelDto) {
        com.example.lesprom.entity.Cutter model = CutterMapper.INSTANCE.mapSingle(modelDto);
        return CutterMapper.INSTANCE.mapSingle(service.create(model));
    }

    @PutMapping("{id}")
    public Cutter update(@PathVariable Long id, @RequestBody Cutter modelDto) {
        com.example.lesprom.entity.Cutter model = CutterMapper.INSTANCE.mapSingle(modelDto);
        return CutterMapper.INSTANCE.mapSingle(service.update(id, model));
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
