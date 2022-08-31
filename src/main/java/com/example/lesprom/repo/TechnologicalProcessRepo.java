package com.example.lesprom.repo;

import com.example.lesprom.entity.TechnologicalProcess;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TechnologicalProcessRepo extends JpaRepository<TechnologicalProcess, Long> {
    List<TechnologicalProcess> findAllByOrderById();
}
