package com.example.lesprom.controller.rest.impl;

import com.example.lesprom.controller.rest.AbstractRestController;
import com.example.lesprom.dto.baguette.Baguette;
import com.example.lesprom.mapper.BaguetteMapper;
import com.example.lesprom.service.rest.impl.BaguetteRestService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/baguette")
public class BaguetteRestController extends AbstractRestController<Baguette, BaguetteRestService> {

    public BaguetteRestController(BaguetteRestService service) {
        super(service);
    }

    @Override
    public List<Baguette> list() {
        return BaguetteMapper.INSTANCE.mapList(service.list());
    }

    @Override
    public Baguette getOne(Long id) {
        return BaguetteMapper.INSTANCE.mapSingle(service.getById(id));
    }

    @Override
    public Baguette create(Baguette modelDto) {
        com.example.lesprom.entity.Baguette model = BaguetteMapper.INSTANCE.mapSingle(modelDto);
        return BaguetteMapper.INSTANCE.mapSingle(service.create(model));
    }

    @Override
    public Baguette update(Long id, Baguette modelDto) {
        com.example.lesprom.entity.Baguette model = BaguetteMapper.INSTANCE.mapSingle(modelDto);
        return BaguetteMapper.INSTANCE.mapSingle(service.update(id, model));
    }

    @Override
    public void delete(Long id) {
        service.delete(id);
    }

}
