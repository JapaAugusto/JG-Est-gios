package br.edu.gov.fatec.estagiando.controllers;


import br.edu.gov.fatec.estagiando.services.UniversitarioService;
import br.edu.gov.fatec.estagiando.services.estagiandoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class IndexController {

    @Autowired
    private UniversitarioService UniversitarioService;

    @Autowired
    private estagiandoService estagiandoService;    

    @GetMapping("/")
    public String showIndex(Model model) {
        model.addAttribute("Universitarios", UniversitarioService.getAllUniversitarios());
        model.addAttribute("estagiandos", estagiandoService.getAllestagiandos());       
       
        return "index";
    }    
}
