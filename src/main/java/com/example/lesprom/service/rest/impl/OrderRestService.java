package com.example.lesprom.service.rest.impl;

import com.example.lesprom.entity.Baguette;
import com.example.lesprom.entity.Cutter;
import com.example.lesprom.entity.Order;
import com.example.lesprom.entity.TechnologicalProcess;
import com.example.lesprom.exception.NotFoundException;
import com.example.lesprom.repo.BaguetteRepo;
import com.example.lesprom.repo.CutterRepo;
import com.example.lesprom.repo.OrderRepo;
import com.example.lesprom.repo.TechnologicalProcessRepo;
import com.example.lesprom.service.rest.AbstractRestService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.HashSet;
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

    // метод отдает список заказов, отфильтрованных по конкретному участку
    public Page<Order> listOrdersOfWorkplace(Long idWorkplace) {
        return new PageImpl<>(repository.findAllOnWorkplace(idWorkplace));
    }

    @Override
    public Page<Order> list(Integer pageNo, Integer pageSize, String sortBy) {
        if (pageNo <= -1) {
            Sort sortItem = Sort.by(sortBy);
            return new PageImpl<>(repository.findAll(sortItem));
        } else {
            Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
            return repository.findAll(paging);
        }
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
