package com.example.lesprom.repo;

import com.example.lesprom.entity.TimeOfEmployeeOnOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TimeOfEmployeeOnOrderRepo extends JpaRepository<TimeOfEmployeeOnOrder, Long> {

    @Query("select t from TimeOfEmployeeOnOrder t join fetch t.employee e join fetch e.workplaces order by t.id")
    List<TimeOfEmployeeOnOrder> findAllByOrderById();

}
