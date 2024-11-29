package br.edu.gov.fatec.estagiando.models;

import java.util.List;

import jakarta.persistence.*;

@Entity
public class estagiando {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;      
    private int cargaHoraria;
    private String areaAtuacao;   

    @ManyToOne
    @JoinColumn(name = "empresa_id", nullable = false)
    private Empresa empresa;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(
            name = "universitario_estagio",
            joinColumns = @JoinColumn(name = "estagio_id"),
            inverseJoinColumns = @JoinColumn(name = "universitario_id")
    )
    private List<Universitario> universitarios;

    @Column(columnDefinition = "bit default 1")
    private boolean status;

    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public String getAreaAtuacao() {
        return areaAtuacao;
    }

    public void setAreaAtuacao(String areaAtuacao) {
        this.areaAtuacao = areaAtuacao;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public List<Universitario> getUniversitarios() {
        return universitarios;
    }

    public void setUniversitarios(List<Universitario> universitarios) {
        this.universitarios = universitarios;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
