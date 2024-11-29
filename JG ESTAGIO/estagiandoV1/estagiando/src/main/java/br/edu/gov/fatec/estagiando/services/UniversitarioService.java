package br.edu.gov.fatec.estagiando.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import br.edu.gov.fatec.estagiando.models.Universitario;
import br.edu.gov.fatec.estagiando.repositories.UniversitarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UniversitarioService {

    @Autowired
    private UniversitarioRepository UniversitarioRepository;

    public Universitario salvar(Universitario Universitario) {
        try {
            Universitario.setStatus(true);
            return UniversitarioRepository.save(Universitario);
        } catch (DataIntegrityViolationException e) {
            return null;
        }
    }

    public List<Universitario> findAll() {
        return UniversitarioRepository.findAllByStatus(true);
    }

    public void deletar(Long id) {
        Optional<Universitario> uni = UniversitarioRepository.findById(id);
        if (!uni.isPresent()) {
            return;
        }
        uni.get().setStatus(false);
        UniversitarioRepository.save(uni.get());
    }

    public Optional<Universitario> findById(Long id) {
        return UniversitarioRepository.findById(id);
    }

    public List<Universitario> getAllUniversitarios() {
        return UniversitarioRepository.findAll();
    }

    public Universitario getUniversitarioById(Long id) {
        Optional<Universitario> Universitario = UniversitarioRepository.findById(id);
        return Universitario.orElseThrow(() -> new RuntimeException("Universitario não encontrado"));
    }
}
