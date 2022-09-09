package com.example.lesprom.controller.rest.impl;

import com.example.lesprom.controller.rest.AbstractRestController;
import com.example.lesprom.dto.cutter.Cutter;
import com.example.lesprom.mapper.CutterMapper;
import com.example.lesprom.service.rest.impl.CutterRestService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cutter")
public class CutterRestController extends AbstractRestController<Cutter, CutterRestService> {

    public CutterRestController(CutterRestService service) {
        super(service);
    }

    @Override
    public Object list(Integer pageNo, Integer pageSize, String sortBy) {
        Page<com.example.lesprom.entity.Cutter> page = service.list(pageNo, pageSize, sortBy);
        List<Cutter> listDto = CutterMapper.INSTANCE.mapList(page.getContent());
        return new PageImpl<>(listDto, page.getPageable(), page.getTotalElements());
    }

    @Override
    public Cutter getOne(Long id) {
        return CutterMapper.INSTANCE.mapSingle(service.getById(id));
    }

    @Override
    public Cutter create(Cutter modelDto) {
        com.example.lesprom.entity.Cutter model = CutterMapper.INSTANCE.mapSingle(modelDto);
        return CutterMapper.INSTANCE.mapSingle(service.create(model));
    }

    @Override
    public Cutter update(Long id, Cutter modelDto) {
        com.example.lesprom.entity.Cutter model = CutterMapper.INSTANCE.mapSingle(modelDto);
        return CutterMapper.INSTANCE.mapSingle(service.update(id, model));
    }

    @Override
    public void delete(Long id) {
        service.delete(id);
    }

}
