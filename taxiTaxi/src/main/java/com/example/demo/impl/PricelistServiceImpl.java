package com.example.demo.impl;

import com.example.demo.dto.PricelistDto;
import com.example.demo.model.Cenovnik;
import com.example.demo.model.Korisnik;
import com.example.demo.model.Uloga;
import com.example.demo.repository.PricelistRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.PricelistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

import javax.xml.ws.ServiceMode;

@Service
public class PricelistServiceImpl implements PricelistService {

    @Autowired
    PricelistRepository pricelistRepository;

    @Autowired
    RoleRepository roleRepository;

    public static final String DISPECER = "dispecer";

    @Override
    public boolean createPrice(PricelistDto pricelistDto, Korisnik k) {

        Optional<Uloga> getUloga = roleRepository.findByNazivUloga(k.getUloga().getNazivUloga());
        Uloga ug = getUloga.get();
        String nazivUloge = ug.getNazivUloga();
        if(nazivUloge.equals(DISPECER)){
            Cenovnik c = new Cenovnik();
            c.setCenaDan(pricelistDto.getCenaDan());
            c.setCenaNoc(pricelistDto.getCenaNoc());
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            c.setDatumPromene(timestamp);
            c.setCenaStart(pricelistDto.getCenaStart());
            pricelistRepository.saveAndFlush(c);
            return true;
        }
        return false;

    }

}
