package com.example.lesprom.repo;

import com.example.lesprom.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {

    @Query("select distinct e from Employee e left join fetch e.workplaces order by e.id")
    List<Employee> findAllByOrderById();

}
