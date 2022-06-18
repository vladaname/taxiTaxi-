package com.example.demo.impl;

import com.example.demo.dto.LoginDto;
import com.example.demo.dto.UserCreateDto;
import com.example.demo.model.Korisnik;
import com.example.demo.model.Uloga;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public boolean createUser(UserCreateDto userCreateDto) {

        StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
        Korisnik k = new Korisnik();
        k.setUsername(userCreateDto.getUsername());
        k.setPass(passwordEncryptor.encryptPassword(userCreateDto.getPass()));
        k.setIme(userCreateDto.getIme());
        k.setPrezime(userCreateDto.getPrezime());
        k.setEmail(userCreateDto.getEmail());
        k.setTelefon(userCreateDto.getTelefon());
        k.setObrisan(0);

        Optional<Uloga> findUloga = roleRepository.findById(1);
        if(findUloga.isPresent()){
            Uloga ul = findUloga.get();
            k.setUloga(ul);
        }
        else{
            return false;
        }
        userRepository.saveAndFlush(k);
        return true;
    }

    @Override
    public Korisnik login(LoginDto loginDto) {

        StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
        Korisnik k = userRepository.findByUsername(loginDto.getUsername());
 //       String encryptPass = k.getPass();
        if (loginDto.getPass().equals(k.getPass()) || passwordEncryptor.checkPassword(loginDto.getPass(), k.getPass())) {
            return k;
        }
        return null;
    }

    @Override
    public boolean createVozac(UserCreateDto userCreateDto) {
        StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
        Korisnik k = new Korisnik();
        k.setUsername(userCreateDto.getUsername());
        k.setPass(passwordEncryptor.encryptPassword(userCreateDto.getPass()));
        k.setIme(userCreateDto.getIme());
        k.setPrezime(userCreateDto.getPrezime());
        k.setEmail(userCreateDto.getEmail());
        k.setTelefon(userCreateDto.getTelefon());
        k.setObrisan(0);

        Optional<Uloga> findUloga = roleRepository.findById(2);
        if(findUloga.isPresent()){
            Uloga ul = findUloga.get();
            k.setUloga(ul);
        }
        else{
            return false;
        }
        userRepository.saveAndFlush(k);
        return true;
    }

}
