package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Voznja;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface VoznjaRepository extends JpaRepository<Voznja, Integer> {


    List<Voznja> findAllByStatusVoznje(String slobodan);
}
