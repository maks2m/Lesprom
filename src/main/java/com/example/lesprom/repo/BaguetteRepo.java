package com.example.lesprom.repo;

import com.example.lesprom.entity.Baguette;
import com.example.lesprom.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BaguetteRepo extends JpaRepository<Baguette, Long> {
    List<Baguette> findAllByOrderById();
}
