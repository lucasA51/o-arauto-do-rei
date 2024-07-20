package com.santosltda.oarautodorei.controller;

import com.santosltda.oarautodorei.domain.BannerHome;
import com.santosltda.oarautodorei.service.BannerHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavigationController {

    @Autowired
    private BannerHomeService bannerHomeService;

    @GetMapping("/")
    public String index(Model model) {
        BannerHome bannerHome = bannerHomeService.getBannerHome(); // Fetch banner data
        model.addAttribute("bannerHome", bannerHome); // Add banner data to the model
        return "index";
    }

    @GetMapping("/noticias-do-reino")
    public String noticiasDoReino() {
        return "noticias-do-reino/index";
    }

    @GetMapping("/economia-e-comercio")
    public String economiaEComercio() {
        return "economia-e-comercio/index";
    }

    @GetMapping("/vida-na-corte")
    public String vidaNaCorte() {
        return "vida-na-corte/index";
    }

    @GetMapping("/cronicas-e-opinioes")
    public String cronicasEOpinioes() {
        return "cronicas-e-opinioes/index";
    }

    @GetMapping("/arte-e-cultura")
    public String arteECultura() {
        return "arte-e-cultura/index";
    }
}
