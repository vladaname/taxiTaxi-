package com.example.demo.service;

import com.example.demo.dto.LoginDto;
import com.example.demo.dto.UserCreateDto;
import com.example.demo.model.Korisnik;

public interface UserService {

    boolean createUser(UserCreateDto userCreateDto);

    Korisnik login(LoginDto loginDto);

    boolean createVozac(UserCreateDto userCreateDto);
}
