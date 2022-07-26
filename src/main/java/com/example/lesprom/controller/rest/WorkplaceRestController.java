package com.example.lesprom.controller.rest;

import com.example.lesprom.dto.WorkplaceDto;
import com.example.lesprom.entity.Workplace;
import com.example.lesprom.mapper.WorkplaceMapper;
import com.example.lesprom.service.WorkplaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workplace")
public class WorkplaceRestController {

    private final WorkplaceService workplaceService;

    @Autowired
    public WorkplaceRestController(WorkplaceService workplaceService) {
        this.workplaceService = workplaceService;
    }

    @GetMapping
    public List<WorkplaceDto> list() {
        return WorkplaceMapper.INSTANCE.mapList(workplaceService.list());
    }

    @GetMapping("{id}")
    public WorkplaceDto getOne(@PathVariable Long id) {
        return WorkplaceMapper.INSTANCE.mapSingle(workplaceService.getById(id));
    }

    @PostMapping
    public WorkplaceDto create(@RequestBody WorkplaceDto modelDto) {
        Workplace model = WorkplaceMapper.INSTANCE.mapSingle(modelDto);
        return WorkplaceMapper.INSTANCE.mapSingle(workplaceService.save(null ,model));
    }

    @PutMapping("{id}")
    public WorkplaceDto update(@PathVariable Long id, @RequestBody WorkplaceDto modelDto) {
        Workplace model = WorkplaceMapper.INSTANCE.mapSingle(modelDto);
        return WorkplaceMapper.INSTANCE.mapSingle(workplaceService.save(id, model));
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        workplaceService.delete(id);
    }


}
