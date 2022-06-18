package com.example.demo.controllers;

import com.example.demo.dto.LoginDto;
import com.example.demo.dto.UserCreateDto;
import com.example.demo.model.Korisnik;
import com.example.demo.model.Voznja;
import com.example.demo.service.UserService;
import com.example.demo.service.VoznjaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@Scope("session")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    VoznjaService voznjaService;

    public static final String DISPECER = "dispecer";
    public static final String VOZAC = "vozac";
    public static final String KORISNIK = "korisnik";

    @GetMapping("/login")
    public ModelAndView login(){
        return new ModelAndView("login", "loginDto", new LoginDto());
    }

    @PostMapping("/login")
    public String login(Model model, @ModelAttribute("loginDto") LoginDto loginDto, HttpSession session) {
        Korisnik login = userService.login(loginDto);

        if (login != null && login.getUloga().getNazivUloga().equals(KORISNIK)) {
            session.setAttribute("korisnik", login);
            return "createDriving";
        }
        else if (login != null && login.getUloga().getNazivUloga().equals(DISPECER)) {
            session.setAttribute("dispecer", login);
            return "dispecerDashboard";
        }
        else if (login != null && login.getUloga().getNazivUloga().equals(VOZAC)) {
            session.setAttribute("vozac", login);
            System.out.println(login.getIdKorisnik());
            List<Voznja> lista = voznjaService.listaKreiranihVoznji();
            model.addAttribute("listaSlobodnihVoznji", lista);
            return "driverDashboard";
        }
    return null;

    }

    @GetMapping("/createUser")
    public ModelAndView createUser(){
        return new ModelAndView("createUser", "userCreateDto", new UserCreateDto());
    }

    @PostMapping("/createUser")
    public String createUser(Model model, @ModelAttribute("userCreateDto") UserCreateDto userCreateDto){
        System.out.println("Controller");
        boolean b = userService.createUser(userCreateDto);
        if(b){
            return "login";
        }
        else{
            model.addAttribute("poruka", "Grska pri kreiranju korisnika");
            return "createUser";
        }
    }
    @GetMapping("/createVozac")
    public ModelAndView createVozac(){
        return new ModelAndView("createVozac", "userCreateDto", new UserCreateDto());
    }

    @PostMapping("/createVozac")
    public String createVozac(Model model, @ModelAttribute("userCreateDto") UserCreateDto userCreateDto){
        boolean b = userService.createVozac(userCreateDto);
        System.out.println(b);
        if(b){

            return "login";
        }
        else{
            model.addAttribute("poruka", "Grska pri kreiranju korisnika");
            return "createUser";
        }
    }
}
