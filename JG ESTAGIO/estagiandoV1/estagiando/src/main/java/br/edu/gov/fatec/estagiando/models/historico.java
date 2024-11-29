package br.edu.gov.fatec.estagiando.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class historico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "universitario_id")
    private Universitario Universitario;
    
    private int nota;
    
    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    private int diciplina;

    public int getdiciplina() {
        return diciplina;
    }

    public void setdiciplina(int diciplina) {
        this.diciplina = diciplina;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Universitario getUniversitario() {
        return Universitario;
    }

    public void setUniversitario(Universitario Universitario) {
        this.Universitario = Universitario;
    }
    
}

