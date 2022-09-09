package com.example.lesprom.controller.rest.impl;

import com.example.lesprom.controller.rest.AbstractRestController;
import com.example.lesprom.dto.workplace.Workplace;
import com.example.lesprom.mapper.WorkplaceMapper;
import com.example.lesprom.service.rest.impl.WorkplaceRestService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/workplace")
public class WorkplaceRestController extends AbstractRestController<Workplace, WorkplaceRestService> {

    public WorkplaceRestController(WorkplaceRestService service) {
        super(service);
    }

    @Override
    public Object list(Integer pageNo, Integer pageSize, String sortBy) {
        Page<com.example.lesprom.entity.Workplace> page = service.list(pageNo, pageSize, sortBy);
        List<Workplace> listDto = WorkplaceMapper.INSTANCE.mapList(page.getContent());
        return new PageImpl<>(listDto, page.getPageable(), page.getTotalElements());
    }

    @Override
    public Workplace getOne(Long id) {
        return WorkplaceMapper.INSTANCE.mapSingle(service.getById(id));
    }

    @Override
    public Workplace create(Workplace modelDto) {
        com.example.lesprom.entity.Workplace model = WorkplaceMapper.INSTANCE.mapSingle(modelDto);
        return WorkplaceMapper.INSTANCE.mapSingle(service.create(model));
    }

    @Override
    public Workplace update(Long id, Workplace modelDto) {
        com.example.lesprom.entity.Workplace model = WorkplaceMapper.INSTANCE.mapSingle(modelDto);
        return WorkplaceMapper.INSTANCE.mapSingle(service.update(id, model));
    }

    @Override
    public void delete(Long id) {
        service.delete(id);
    }

}
