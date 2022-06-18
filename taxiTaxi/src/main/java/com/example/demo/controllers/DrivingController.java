package com.example.demo.controllers;

import com.example.demo.dto.DrivingDto;
import com.example.demo.dto.VozacDto;
import com.example.demo.dto.ZauzmiVoznjuDto;
import com.example.demo.dto.ZavrsiVoznjuDto;
import com.example.demo.model.Korisnik;
import com.example.demo.model.Voznja;
import com.example.demo.service.RacunService;
import com.example.demo.service.VoznjaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.example.demo.impl.VoznjaServiceImpl.STATUS_VOZNJE_ZAVRSENO;

@Controller
public class DrivingController {

    @Autowired
    VoznjaService voznjaService;

    @Autowired
    RacunService racunService;

    @GetMapping("/createDriving")
    public ModelAndView createDriving(){
        return new ModelAndView("createDriving", "drivingDto", new DrivingDto());

    }

    @PostMapping("/createDriving")
    public String createDrivingPost(Model model, @ModelAttribute("drivingDto") DrivingDto drivingDto, HttpServletRequest request) {
        Korisnik k = (Korisnik) request.getSession().getAttribute("korisnik");
        boolean v = voznjaService.createVoznja(drivingDto, k);

        if(v){
            return "messageKreiranaVoznja";
        }
        else{
            return "createDriving";
        }
    }

    @GetMapping("/driverDashboard")
    public ModelAndView driverDashBoard(){
        return new ModelAndView("driverDashboard");
    }

    @GetMapping("/listaKreiranihVoznji")
    public String listaKreiranihVoznji(Model model){
            List<Voznja> lista = voznjaService.listaKreiranihVoznji();
            model.addAttribute("listaSlobodnihVoznji", lista);
            return "listaSlobodnihVoznji";
    }

    @PostMapping("/promeniStatus")
    public String promeniStatusVoznje(Model model, @ModelAttribute("zauzmiVoznjuDto")
            ZauzmiVoznjuDto zauzmiVoznjuDto, HttpServletRequest request) {
        Korisnik k = (Korisnik) request.getSession().getAttribute("vozac");
        System.out.println("korisnik " + k.getIdKorisnik());
            Voznja b = voznjaService.zauzmiVoznju(zauzmiVoznjuDto.getIdVoznja(), k);
        System.out.println("voznja " + b.getIdVoznja());

        if (b != null) {
            ZavrsiVoznjuDto zavrsiVoznjuDto = new ZavrsiVoznjuDto();
            zavrsiVoznjuDto.setIdVoznja(b.getIdVoznja());
                model.addAttribute("poruka", "Vozilo je zauzeto");
                model.addAttribute("zavrsiVoznjuDto", zavrsiVoznjuDto);
                model.addAttribute("voznja", b);
                return "primljenaVoznja";
            } else
                {
                model.addAttribute("poruka", "Greska. Pokusajte ponovo");
                return "redirect:listaKreiranihVoznji";
            }
    }

    @GetMapping("/primljenaVoznja")
    public String primljenaVoznjaGET(Model model, @ModelAttribute("zazmiVoznjuDto") ZauzmiVoznjuDto zauzmiVoznjuDto){
        Voznja getVoznja = voznjaService.getVoznjaById(zauzmiVoznjuDto.getIdVoznja());
        if(getVoznja != null) {
            return "primljenaVoznja";
        }
        else{
            model.addAttribute("poruka", "Greska");
            return "primljenaVoznja";
        }
    }

    @PostMapping("/primljenaVoznja")
    public String primljenaVoznjaPOST(Model model, @ModelAttribute("zazmiVoznjuDto")
            ZauzmiVoznjuDto zauzmiVoznjuDto, HttpServletRequest request){
        System.out.println("Zavrsi voznju: " + zauzmiVoznjuDto.getIdVoznja());

        boolean zv = voznjaService.zavrsiVoznju(zauzmiVoznjuDto);
        if(zv){
            return "primljenaVoznja";
        }
        else{
            return "redirect:listaKreiranihVoznji";
        }
    }

    @GetMapping("/listAllFinishedDrive")
    public String listaAllFinishedDrice(Model model, @ModelAttribute("vozacDto")VozacDto vozacDto, HttpServletRequest request){
        Korisnik k = (Korisnik) request.getSession().getAttribute("vozac");
        List<Voznja> v = voznjaService.findAllByIdVozac(k);

        if(v != null){
            double sum = voznjaService.sumRacunByIdVozac(k.getIdKorisnik(), STATUS_VOZNJE_ZAVRSENO);

            model.addAttribute("sum", sum);
            model.addAttribute("listaVozac", v);
            return "listaVozac";
        }
        else{
            return "createUser";
        }

    }



}
