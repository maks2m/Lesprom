package com.example.lesprom.repo;

import com.example.lesprom.entity.Workplace;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkplaceRepo extends JpaRepository<Workplace, Long> {
    List<Workplace> findAllByOrderById();
}
