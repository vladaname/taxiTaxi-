package com.example.demo.service;

import com.example.demo.dto.DrivingDto;
import com.example.demo.dto.ZauzmiVoznjuDto;
import com.example.demo.model.Korisnik;
import com.example.demo.model.Voznja;

import java.util.List;

public interface VoznjaService {

    boolean createVoznja(DrivingDto drivingDto, Korisnik k);

//    Voznja zavrsiVoznju(ZavrsiVoznjuDto zavrsiVoznjuDto, Korisnik k);

    List<Voznja> listaKreiranihVoznji();


    Voznja zauzmiVoznju(int idVoznja, Korisnik k);

    boolean zavrsiVoznju(ZauzmiVoznjuDto zauzmiVoznjuDto);

    List<Voznja> krerianaVoznja();

    Voznja getVoznjaById(int idVoznja);

    List<Voznja> findAllByIdVozac(Korisnik k);

    double sumRacunByIdVozac(int idKorisnik, String statusVoznjeZavrseno);
}
