package com.example.lesprom.repo;

import com.example.lesprom.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepo extends JpaRepository<Order, Long> {
    List<Order> findAllByOrderById();


    @Query("select distinct o from Order o " +
            "left join fetch o.baguettes " +
            "left join fetch o.cutters " +
            "left join fetch o.technologicalProcesses t " +
            "left join fetch t.employee " +
            "left join fetch t.workplace w " +
            "where w.id =:idWorkplace")
    List<Order> findAllOnWorkplace(Long idWorkplace);
}


// select distinct o from Order o left join fetch o.baguettes left join fetch o.cutters left join fetch o.technologicalProcesses t left join fetch t.employee left join fetch t.workplace w where w.id =:idWorkplace order by t.operationCode
