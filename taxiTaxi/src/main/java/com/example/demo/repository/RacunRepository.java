package com.example.demo.repository;

import com.example.demo.model.Racun;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface RacunRepository extends JpaRepository<Racun, Integer> {
    @Query(
            value =
                    "select sum(ukupan_racun) as ukupan_racun from racun r " +
                    "inner join voznja v on r.voznja_id_voznja = v.id_voznja " +
                    "where v.vozac_id_korisnik = ?1 and status_voznje = ?2 ",
            nativeQuery = true)
    double sumRacunByIdVozac(int idKorisnik, String statusVoznjeZavrseno);
}
