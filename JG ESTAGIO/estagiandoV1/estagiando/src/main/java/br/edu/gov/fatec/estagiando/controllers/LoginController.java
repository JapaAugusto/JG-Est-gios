package br.edu.gov.fatec.estagiando.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import br.edu.gov.fatec.estagiando.dtos.LoginDto;
import br.edu.gov.fatec.estagiando.models.Login;
import br.edu.gov.fatec.estagiando.models.estagiando;
import br.edu.gov.fatec.estagiando.services.LoginService;
import br.edu.gov.fatec.estagiando.services.estagiandoService;

import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private estagiandoService estagiandoService;

    @GetMapping("/index")
    public String showIndex(Model model) {       
        return "sign-in/index";
    }

    @GetMapping("/about")
    public String showAbout(Model model) {       
        return "sign-in/sobrenos";
    }

    @GetMapping("/historicoEstagio")
    public String mostrarHistorico(Model model) {
        List<estagiando> historicoEstagios = estagiandoService.findHistorico();
        model.addAttribute("historicoEstagios", historicoEstagios);
        return "sign-in/historicoEstagio";
    }

    @GetMapping
    public String showLoginForm(Model model) {
        model.addAttribute("loginForm", new LoginDto());
        return "sign-in/login";
    }    

    @PostMapping("/login")
    public String login(@ModelAttribute LoginDto loginForm, Model model) {
        String username = loginForm.getUsername();
        String password = loginForm.getPassword();
        if (loginService.authenticate(username, password)){
            return "sign-in/index"; 
        } else{
            model.addAttribute("loginError", "Usuário ou senha inválidos.");
            return "sign-in/login";
        }      
        
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("Login", new Login());
        return "sign-in/register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute Login login, Model model) {
        String username = login.getUsername();
        String password = login.getPassword();

        if (loginService.registerNewUser(username, password)) {            
            return "sign-in/login";
        } else {            
            model.addAttribute("registerError", "Falha no registro. Tente novamente.");
            return "sign-in/register";
        }
    }
}
