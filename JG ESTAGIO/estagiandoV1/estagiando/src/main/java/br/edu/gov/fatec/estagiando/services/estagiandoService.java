package br.edu.gov.fatec.estagiando.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.gov.fatec.estagiando.models.estagiando;
import br.edu.gov.fatec.estagiando.repositories.estagiandoRepository;

@Service
public class estagiandoService {

    @Autowired
    private estagiandoRepository estagiandoRepository;

    public List<estagiando> findAll() {
        return estagiandoRepository.findAllByStatus(true);
    }

    public List<estagiando> findHistorico() {
        return estagiandoRepository.findAllByStatus(false);
    }

    public Optional<estagiando> findById(Long id) {
        return estagiandoRepository.findById(id);
    }

    public estagiando save(estagiando estagiando) {        
        estagiando.setAreaAtuacao(truncarTexto(estagiando.getAreaAtuacao(), 255));        
        estagiando.setStatus(true);        

        return estagiandoRepository.save(estagiando);
    }

    public estagiando update(estagiando estagiando) {
        estagiando estag = estagiandoRepository.findById(estagiando.getId()).get();
        estag.setAreaAtuacao(truncarTexto(estagiando.getAreaAtuacao(), 255)); 
        estag.setCargaHoraria(estagiando.getCargaHoraria());       
        estag.setStatus(true);                   

        return estagiandoRepository.save(estag);
    }

    public void deleteById(Long id) {
        Optional<estagiando> estag = estagiandoRepository.findById(id);
        if (!estag.isPresent()) {
            return;
        }
        estag.get().setStatus(false);
        estagiandoRepository.save(estag.get());
    }

    private String truncarTexto(String texto, int tamanhoMaximo) {
        if (texto != null && texto.length() > tamanhoMaximo) {
            return texto.substring(0, tamanhoMaximo);
        }
        return texto;
    }

    public List<estagiando> getAllestagiandos() {
        return estagiandoRepository.findAll();
    }

    public estagiando getestagiandoById(Long id) {
        Optional<estagiando> estagiando = estagiandoRepository.findById(id);
        return estagiando.orElseThrow(() -> new RuntimeException("estagiando não encontrada"));
    }
}

