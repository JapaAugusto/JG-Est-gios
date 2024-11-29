package br.edu.gov.fatec.estagiando.controllers;

import br.edu.gov.fatec.estagiando.models.Universitario;
import br.edu.gov.fatec.estagiando.models.historico;
import br.edu.gov.fatec.estagiando.services.UniversitarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/Universitarios")
public class UniversitarioController {

    @Autowired
    private UniversitarioService UniversitarioService;

    @GetMapping
    public String listarUniversitarios(Model model) {
        List<Universitario> Universitarios = UniversitarioService.findAll();
        model.addAttribute("universitarios", Universitarios);
        return "Universitario/listaUniversitarios";
    }

    @GetMapping("/form")
    public String exibirFormularioCadastro(Model model) {
        model.addAttribute("universitario", new Universitario());
        return "Universitario/formUniversitario";
    }

    @PostMapping("/salvar")
    public String salvarUniversitario(@ModelAttribute @Valid Universitario universitario,
                                      BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {            
            return "Universitario/formUniversitario";
        }
        try {
            Universitario savedUniversitario = UniversitarioService.salvar(universitario);
            if (savedUniversitario == null) {                
                redirectAttributes.addFlashAttribute("errorMessage", "Já existe um Universitario com o mesmo email, cpf, rg ou ra");
            }
            return "redirect:/Universitarios";
        } catch (Exception e) {            
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao salvar o Universitario: " + e.getMessage());
            return "redirect:/Universitarios";
        }
    }

    @GetMapping("/delete/{id}")
    public String deletarUniversitario(@PathVariable Long id) {
        UniversitarioService.deletar(id);
        return "redirect:/Universitarios";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Optional<Universitario> UniversitarioOptional = UniversitarioService.findById(id);
        if (UniversitarioOptional.isPresent()) {
            Universitario Universitario = UniversitarioOptional.get();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formattedDate = Universitario.getDataNascimento() != null ? Universitario.getDataNascimento().format(formatter) : "";
            model.addAttribute("Universitario", Universitario);
            model.addAttribute("formattedDate", formattedDate);
            return "Universitario/update-Universitario";
        } else {
            throw new IllegalArgumentException("Id inválido: " + id);
        }
    }

    @PostMapping("/update/{id}")
    public String updateUniversitario(@PathVariable("id") long id, @Valid Universitario Universitario, BindingResult result, Model model) {
        if (result.hasErrors()) {
            System.out.println("Erro no formulário: " + result.getAllErrors());
            Universitario.setId(id);
            return "Universitario/update-Universitario";
        }
        System.out.println("Salvando Universitario: " + Universitario);
        UniversitarioService.salvar(Universitario);
        return "redirect:/Universitarios";
    }

    @GetMapping("/{id}/notas-diciplina")
    public String exibirHistorico(@PathVariable Long id, Model model) {
        Optional<Universitario> UniversitarioOptional = UniversitarioService.findById(id);
        if (UniversitarioOptional.isPresent()) {
            Universitario Universitario = UniversitarioOptional.get();
            List<historico> Historico = Universitario.getHistorico();
            model.addAttribute("Universitario", Universitario);
            model.addAttribute("Historico", Historico);
            return "Universitario/notas-diciplina";
        } else {
            throw new IllegalArgumentException("Id de Universitario inválido: " + id);
        }
    }


}
