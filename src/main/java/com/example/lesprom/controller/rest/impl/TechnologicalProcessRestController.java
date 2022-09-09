package com.example.lesprom.controller.rest.impl;

import com.example.lesprom.controller.rest.AbstractRestController;
import com.example.lesprom.dto.technologicalprocess.TechnologicalProcess;
import com.example.lesprom.mapper.TechnologicalProcessMapper;
import com.example.lesprom.service.rest.impl.TechnologicalProcessService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/technological-process")
public class TechnologicalProcessRestController extends AbstractRestController<TechnologicalProcess, TechnologicalProcessService> {

    protected TechnologicalProcessRestController(TechnologicalProcessService service) {
        super(service);
    }

    @Override
    public Object list(Integer pageNo, Integer pageSize, String sortBy) {
        Page<com.example.lesprom.entity.TechnologicalProcess> page = service.list(pageNo, pageSize, sortBy);
        List<TechnologicalProcess> listDto = TechnologicalProcessMapper.INSTANCE.mapList(page.getContent());
        return new PageImpl<>(listDto, page.getPageable(), page.getTotalElements());
    }

    @Override
    public TechnologicalProcess getOne(Long id) {
        return TechnologicalProcessMapper.INSTANCE.mapSingle(service.getById(id));
    }

    @Override
    public TechnologicalProcess create(TechnologicalProcess modelDto) {
        com.example.lesprom.entity.TechnologicalProcess model = TechnologicalProcessMapper.INSTANCE.mapSingle(modelDto);
        return TechnologicalProcessMapper.INSTANCE.mapSingle(service.create(model));
    }

    @Override
    public TechnologicalProcess update(Long id, TechnologicalProcess modelDto) {
        com.example.lesprom.entity.TechnologicalProcess model = TechnologicalProcessMapper.INSTANCE.mapSingle(modelDto);
        return TechnologicalProcessMapper.INSTANCE.mapSingle(service.update(id, model));
    }

    @Override
    public void delete(Long id) {
        service.delete(id);
    }
}
