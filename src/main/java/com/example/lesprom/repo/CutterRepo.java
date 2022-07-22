package com.example.lesprom.repo;

import com.example.lesprom.entity.Cutter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CutterRepo extends JpaRepository<Cutter, Long> {
    List<Cutter> findAllByOrderById();
}
