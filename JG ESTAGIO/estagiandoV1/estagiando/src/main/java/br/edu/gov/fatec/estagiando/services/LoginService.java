package br.edu.gov.fatec.estagiando.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.edu.gov.fatec.estagiando.models.*;
import br.edu.gov.fatec.estagiando.repositories.LoginRepository;

@Service
public class LoginService {
    
    @Autowired
    private final LoginRepository loginRepository;


    public LoginService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }


    public boolean authenticate(String username, String password) {
        Login login = loginRepository.findByUsername(username);
        if (login == null) {
            return false;
        }

        return login.getUsername().equals(username) && login.getPassword().equals(password);
    }


    public boolean registerNewUser(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            return false; 
        }
        Login login = new Login(username, password);
        
        if (loginRepository.findByUsername(username) != null) {
            return false; 
        }              
        loginRepository.save(login);       
        
        return true;
    }
}
