package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Cenovnik;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface PricelistRepository extends JpaRepository<Cenovnik, Integer> {

    Cenovnik findTopByOrderByIdCenovnikDesc();
}
