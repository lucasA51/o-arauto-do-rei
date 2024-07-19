package com.santosltda.oarautodorei.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavigationController {

    @GetMapping("/")
    public String index() {
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
