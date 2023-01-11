package com.example.demo.repositories;

import com.example.demo.models.Measuring;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasuringRepository
        extends JpaRepository<Measuring,Integer> {
}
