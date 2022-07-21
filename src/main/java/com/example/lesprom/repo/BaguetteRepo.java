package com.example.lesprom.repo;

import com.example.lesprom.entity.Baguette;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaguetteRepo extends JpaRepository<Baguette, Long> {
}
