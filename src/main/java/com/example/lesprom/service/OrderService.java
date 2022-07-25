package com.example.lesprom.service;

import com.example.lesprom.entity.Order;
import com.example.lesprom.exception.NotFoundException;
import com.example.lesprom.repo.OrderRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepo orderRepo;

    @Autowired
    public OrderService(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    public List<Order> list() {
        return orderRepo.findAllByOrderById();
    }

    public Order create() {
        return new Order();
    }

    public Order getById(Long id) {
        return orderRepo.findById(id).orElseThrow(NotFoundException::new);
    }

    public void save(Long id, Order item) {
        Order itemFromDB;
        if (id == null) {
            itemFromDB = new Order();
        } else {
            itemFromDB = getById(id);
        }
        BeanUtils.copyProperties(item, itemFromDB, "id");
        orderRepo.save(itemFromDB);
    }

    public void delete(Long id) {
        orderRepo.deleteById(id);
    }

}
