package com.example.demo.controllers;

import com.example.demo.dto.PricelistDto;
import com.example.demo.model.Korisnik;
import com.example.demo.service.PricelistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@Scope("session")
public class PricelistController {
    @Autowired
    PricelistService pricelistService;

    @GetMapping("/createCenovnik")
    public ModelAndView createCenovnik(){
        return new ModelAndView("createCenovnik", "pricelistDto", new PricelistDto());

    }

    @PostMapping("/createCenovnik")
    public String createCenovnikPost(Model model, @ModelAttribute("pricelistDto") PricelistDto pricelistDto, HttpServletRequest request){
        Korisnik k = (Korisnik) request.getSession().getAttribute("korisnik");
        boolean b = pricelistService.createPrice(pricelistDto, k);

        if(b){
            model.addAttribute("message", "The price is created");
            return "createDriving";
        }
        else{
            model.addAttribute("message", "Error");
            return "createCenovnik";
        }
    }
}
