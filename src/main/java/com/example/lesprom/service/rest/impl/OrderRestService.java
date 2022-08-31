package com.example.lesprom.service.rest.impl;

import com.example.lesprom.entity.*;
import com.example.lesprom.exception.NotFoundException;
import com.example.lesprom.repo.*;
import com.example.lesprom.service.rest.AbstractRestService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderRestService extends AbstractRestService<Order, OrderRepo> {

    private final BaguetteRepo baguetteRepo;
    private final CutterRepo cutterRepo;
    private final TechnologicalProcessRepo technologicalProcessRepo;

    public OrderRestService(OrderRepo repository, BaguetteRepo baguetteRepo, CutterRepo cutterRepo, TechnologicalProcessRepo technologicalProcessRepo) {
        super(repository);
        this.baguetteRepo = baguetteRepo;
        this.cutterRepo = cutterRepo;
        this.technologicalProcessRepo = technologicalProcessRepo;
    }

    @Override
    public List<Order> list() {
        return super.repository.findAllByOrderById();
    }

    @Override
    public Order getById(Long id) {
        return repository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public Order create(Order item) {
        item.setId(null);
        setChildren(item);
        return repository.save(item);
    }

    @Override
    public Order update(Long id, Order item) {
        Order itemFromDB = repository.findById(id).orElseThrow(NotFoundException::new);
        setChildren(item);
        BeanUtils.copyProperties(item, itemFromDB, "id");
        return repository.save(itemFromDB);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    /**
     * функция поиска в БД и восстановления дочерних сущностей в родительской по ID
     * @param item - сущность, требующая восстановления дочерних сущностей
     */
    private void setChildren(Order item) {
        item.setBaguettes(new HashSet<>(baguetteRepo
                .findAllById(item.getBaguettes().stream().map(Baguette::getId).collect(Collectors.toSet()))));
        item.setCutters(new HashSet<>(cutterRepo
                .findAllById(item.getCutters().stream().map(Cutter::getId).collect(Collectors.toSet()))));
        item.setTechnologicalProcesses(new HashSet<>(technologicalProcessRepo
                .findAllById(item.getTechnologicalProcesses().stream().map(TechnologicalProcess::getId).collect(Collectors.toSet()))));
    }

}
