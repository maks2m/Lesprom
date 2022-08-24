package com.example.lesprom.service.rest;

import com.example.lesprom.entity.Order;
import com.example.lesprom.exception.NotFoundException;
import com.example.lesprom.repo.OrderRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderRestService extends AbstractRestService<Order, OrderRepo>{

    public OrderRestService(OrderRepo repository) {
        super(repository);
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
        return repository.save(item);
    }

    @Override
    public Order update(Long id, Order item) {
        Order itemFromDB = repository.findById(id).orElseThrow(NotFoundException::new);
        BeanUtils.copyProperties(item, itemFromDB, "id");
        return repository.save(itemFromDB);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

}
