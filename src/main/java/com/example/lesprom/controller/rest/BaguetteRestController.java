package com.example.lesprom.controller.rest;

import com.example.lesprom.dto.baguette.Baguette;
import com.example.lesprom.mapper.BaguetteMapper;
import com.example.lesprom.service.rest.BaguetteRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/baguette")
public class BaguetteRestController {

    private final BaguetteRestService service;

    @Autowired
    public BaguetteRestController(BaguetteRestService service) {
        this.service = service;
    }

    @GetMapping
    public List<Baguette> list() {
        return BaguetteMapper.INSTANCE.mapList(service.list());
    }

    @GetMapping("{id}")
    public Baguette getOne(@PathVariable Long id) {
        return BaguetteMapper.INSTANCE.mapSingle(service.getById(id));
    }

    @PostMapping
    public Baguette create(@RequestBody Baguette modelDto) {
        com.example.lesprom.entity.Baguette model = BaguetteMapper.INSTANCE.mapSingle(modelDto);
        return BaguetteMapper.INSTANCE.mapSingle(service.create(model));
    }

    @PutMapping("{id}")
    public Baguette update(@PathVariable Long id, @RequestBody Baguette modelDto) {
        com.example.lesprom.entity.Baguette model = BaguetteMapper.INSTANCE.mapSingle(modelDto);
        return BaguetteMapper.INSTANCE.mapSingle(service.update(id, model));
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
