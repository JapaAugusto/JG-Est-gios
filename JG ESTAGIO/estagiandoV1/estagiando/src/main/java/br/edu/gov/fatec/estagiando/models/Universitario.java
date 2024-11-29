package br.edu.gov.fatec.estagiando.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Universitario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome não pode estar em branco")
    private String nome;

    @NotBlank(message = "O sexo não pode estar em branco")
    private String sexo;

    @NotNull(message = "Data de nascimento é obrigatória")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataNascimento;

    @Column(unique = true)
    @NotBlank(message = "O CPF não pode estar em branco")
    @Size(min = 11, max = 11, message = "O CPF deve conter 11 dígitos")
    @Pattern(regexp = "\\d{11}", message = "O CPF deve conter apenas dígitos e ter 11 caracteres")
    private String cpf;

    @Column(unique = true)
    @NotBlank(message = "O RA não pode estar em branco")
    @Size(min = 10, max = 10, message = "O RA deve conter 10 caracteres")
    @Pattern(regexp = "\\d{10}", message = "O RA deve conter apenas dígitos e ter 10 caracteres")
    private String ra;

    @Column(unique = true)
    @NotBlank(message = "O e-mail não pode estar em branco")
    @Email(message = "O e-mail deve ser válido")
    private String email;

    @Column(unique = true)
    @NotBlank(message = "O RG não pode estar em branco")
    private String rg;

    @Column(columnDefinition = "bit default 1")
    private boolean status;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(
      name = "universitario_estagiando", 
      joinColumns = @JoinColumn(name = "universitario_id"), 
      inverseJoinColumns = @JoinColumn(name = "estagiando_id"))
    private List<estagiando> estagiando;    

    public Universitario() {
        
    }

    public Universitario(String nome, String sexo, LocalDate dataNascimento, String cpf, String ra, String email, String rg) {
        this.nome = nome;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.ra = ra;
        this.email = email;
        this.rg = rg;
    }    


    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public List<historico> getHistorico() {
        List<historico> historico = new ArrayList<>();
        return historico;
    }

    public List<estagiando> getEstagiando() {
        return estagiando;
    }

    public void setEstagiando(List<estagiando> estagiando) {
        this.estagiando = estagiando;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}