package br.edu.gov.fatec.estagiando.controllers;

import br.edu.gov.fatec.estagiando.models.estagiando;
import br.edu.gov.fatec.estagiando.services.EmpresaService;
import br.edu.gov.fatec.estagiando.services.UniversitarioService;
import br.edu.gov.fatec.estagiando.services.estagiandoService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/estagiandos")
public class estagiandoController {

    @Autowired
    private estagiandoService estagiandoService;

    @Autowired
    private UniversitarioService universitarioService;
    @Autowired
    private EmpresaService empresaService;

    @GetMapping
    public String listarestagiandos(Model model) {
        List<estagiando> estagiandos = estagiandoService.findAll();
        model.addAttribute("estagiandos", estagiandos); 
        return "estagiando/listaestagiandos";  
    }

    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("estagiando", new estagiando());
        model.addAttribute("universitarios", universitarioService.findAll());
        model.addAttribute("empresa", empresaService.listarEmpresas());
        return "estagiando/formestagiando";
    }

    

    @PostMapping("/salvar")
    public String salvarestagiando(@ModelAttribute @Valid estagiando estagiando, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "estagiando/formestagiando";
        }
        
        if (estagiandoService.save(estagiando) == null) {
            result.rejectValue("nome", "error.estagiando", "Já existe uma estagiando com o mesmo nome");
            return "estagiando/formestagiando";
        }
        estagiandoService.save(estagiando);
        return "redirect:/estagiandos";
    }

    @GetMapping("/delete/{id}")
    public String deletarestagiando(@PathVariable Long id) {
        estagiandoService.deleteById(id);
        return "redirect:/estagiandos";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        estagiando estagiando = estagiandoService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de estagiando inválido: " + id));
        model.addAttribute("estagiando", estagiando);
        return "estagiando/update-estagiando";
    }

    @PostMapping("/update/{id}")
    public String updateEstagiando(@PathVariable("id") Long id, @Valid estagiando estagiando, BindingResult result) {
        if (result.hasErrors()) {
            return "estagiando/update-estagiando";
        }
        estagiando.setId(id);        
        estagiandoService.update(estagiando);
        return "redirect:/estagiandos";
    }
}
