package com.example.demo.service;

import com.example.demo.dto.PricelistDto;
import com.example.demo.model.Korisnik;

public interface PricelistService {
    boolean createPrice(PricelistDto pricelistDto, Korisnik k);
}
