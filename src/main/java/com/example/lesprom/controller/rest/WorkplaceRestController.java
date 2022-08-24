package com.example.lesprom.controller.rest;

import com.example.lesprom.dto.workplace.Workplace;
import com.example.lesprom.mapper.WorkplaceMapper;
import com.example.lesprom.service.rest.WorkplaceRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workplace")
public class WorkplaceRestController {

    private final WorkplaceRestService service;

    @Autowired
    public WorkplaceRestController(WorkplaceRestService service) {
        this.service = service;
    }

    @GetMapping
    public List<Workplace> list() {
        return WorkplaceMapper.INSTANCE.mapList(service.list());
    }

    @GetMapping("{id}")
    public Workplace getOne(@PathVariable Long id) {
        return WorkplaceMapper.INSTANCE.mapSingle(service.getById(id));
    }

    @PostMapping
    public Workplace create(@RequestBody Workplace modelDto) {
        com.example.lesprom.entity.Workplace model = WorkplaceMapper.INSTANCE.mapSingle(modelDto);
        return WorkplaceMapper.INSTANCE.mapSingle(service.create(model));
    }

    @PutMapping("{id}")
    public Workplace update(@PathVariable Long id, @RequestBody Workplace modelDto) {
        com.example.lesprom.entity.Workplace model = WorkplaceMapper.INSTANCE.mapSingle(modelDto);
        return WorkplaceMapper.INSTANCE.mapSingle(service.update(id, model));
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }


}
