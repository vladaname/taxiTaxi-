package com.example.demo.impl;

import com.example.demo.dto.DrivingDto;
import com.example.demo.dto.ZauzmiVoznjuDto;
import com.example.demo.model.Cenovnik;
import com.example.demo.model.Korisnik;
import com.example.demo.model.Racun;
import com.example.demo.model.Voznja;
import com.example.demo.repository.*;
import com.example.demo.service.VoznjaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class VoznjaServiceImpl implements VoznjaService {

    @Autowired
    VoznjaRepository voznjaRepository;
    @Autowired
    PricelistRepository pricelistRepository;
    @Autowired
    RacunRepository racunRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;

    public static final String STATUS_VOZNJE_SLOBODAN = "slobodan";
    public static final String STATUS_VOZNJE_ZAUZETO = "zauzet";
    public static final String STATUS_VOZNJE_ZAVRSENO = "zavrsio";

    public static final String DISPECER = "dispecer";
    public static final String VOZAC = "vozac";

    @Override
    public boolean createVoznja(DrivingDto drivingDto, Korisnik k) {

        if(k != null){
            Voznja v = new Voznja();
            v.setAdresaCilj(drivingDto.getAdresaCilj());
            v.setAdresaPolazak(drivingDto.getAdresaPolazak());
            v.setTelefonMob(drivingDto.getTelefonMob());
            v.setUkupnoKm(0);
            v.setStatusVoznje(STATUS_VOZNJE_SLOBODAN);
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            v.setVremePolazak(timestamp);
            v.setKorisnik(k);
            v.setVozac(null);
            voznjaRepository.saveAndFlush(v);

            Cenovnik getCenovnik = pricelistRepository.findTopByOrderByIdCenovnikDesc();

            Racun r = new Racun();
            r.setVoznja(v);
            r.setCenovnik(getCenovnik);
            r.setUkupanRacun(0);
            racunRepository.saveAndFlush(r);
            return true;
        }
        return false;
    }

    @Override
    public List<Voznja> listaKreiranihVoznji() {

           List<Voznja> getSlobodneVoznje = voznjaRepository.findAllByStatusVoznje(STATUS_VOZNJE_SLOBODAN);
           System.out.println("lista kreiranih voznji" + getSlobodneVoznje);
           return getSlobodneVoznje;

    }

    @Override
    public Voznja zauzmiVoznju(int idVoznja, Korisnik k) {

        System.out.println(k.getIme());
            List<Voznja> getSlobodneVoznje = voznjaRepository.findAllByStatusVoznje(STATUS_VOZNJE_SLOBODAN);

        System.out.println(getSlobodneVoznje);

        if(getSlobodneVoznje.size() == 0){
                return null;
            } else {
                Optional<Voznja> getIdVoznja = voznjaRepository.findById(idVoznja);
                if (getIdVoznja.isPresent()) {
                    Voznja v = getIdVoznja.get();
                    v.setStatusVoznje(STATUS_VOZNJE_ZAUZETO);
                    v.setVozac(k);
                    voznjaRepository.saveAndFlush(v);
                    return v;
                }
            }
        return null;
    }

    @Override
    public boolean zavrsiVoznju(ZauzmiVoznjuDto zauzmiVoznjuDto) {
        Optional<Voznja> getIdVoznja = voznjaRepository.findById(zauzmiVoznjuDto.getIdVoznja());
        if(getIdVoznja.isPresent()){
            Voznja v = getIdVoznja.get();
            v.setStatusVoznje(STATUS_VOZNJE_ZAVRSENO);
            v.setUkupnoKm( zauzmiVoznjuDto.getUkupnoKm());
            if(zauzmiVoznjuDto.getUkupnoKm() != 0) {
                voznjaRepository.saveAndFlush(v);
                Racun r = new Racun();
                Cenovnik getCenovnik = pricelistRepository.findTopByOrderByIdCenovnikDesc();
                double sumRacun = getCenovnik.getCenaStart() + (getCenovnik.getCenaDan() * zauzmiVoznjuDto.getUkupnoKm());
                r.setUkupanRacun(sumRacun);
                r.setCenovnik(getCenovnik);
                r.setVoznja(v);
                racunRepository.saveAndFlush(r);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Voznja> krerianaVoznja() {
        return null;
    }

    @Override
    public Voznja getVoznjaById(int idVoznja) {
        Optional<Voznja> getVoznja = voznjaRepository.findById(idVoznja);
        if(getVoznja.isPresent()){
            Voznja v = getVoznja.get();
            return v;
        }

        return null;

    }

    @Override
    public List<Voznja> findAllByIdVozac(Korisnik k) {
        List<Voznja> zavrseneVoznje = voznjaRepository.findAllByStatusVoznje(STATUS_VOZNJE_ZAVRSENO);

        return zavrseneVoznje;

    }

    @Override
    public double sumRacunByIdVozac(int idKorisnik, String statusVoznjeZavrseno) {

        double sum = racunRepository.sumRacunByIdVozac(idKorisnik, STATUS_VOZNJE_ZAVRSENO);

        return sum;
    }
}
