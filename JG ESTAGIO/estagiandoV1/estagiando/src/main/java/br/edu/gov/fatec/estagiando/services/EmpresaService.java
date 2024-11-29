package br.edu.gov.fatec.estagiando.services;

import br.edu.gov.fatec.estagiando.models.Empresa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpresaService {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Empresa> listarEmpresas() {
        return entityManager.createQuery("SELECT e FROM Empresa e", Empresa.class).getResultList();
    }
   
    public Empresa buscarEmpresaPorId(Long id) {
        return entityManager.find(Empresa.class, id);
    }

    @Transactional
    public Empresa criarEmpresa(Empresa empresa) {
        empresa.setStatus(true);
        entityManager.persist(empresa);
        return empresa;
    }

    @Transactional
    public Empresa atualizarEmpresa(Long id, Empresa empresaAtualizada) {
        Empresa empresaExistente = entityManager.find(Empresa.class, id);
        if (empresaExistente != null) {
            empresaExistente.setNome(empresaAtualizada.getNome());
            empresaExistente.setCnpj(empresaAtualizada.getCnpj());
            empresaExistente.setEndereco(empresaAtualizada.getEndereco());
            empresaExistente.setTelefone(empresaAtualizada.getTelefone());
            entityManager.merge(empresaExistente);
        }
        return empresaExistente;
    }
    
    @Transactional
    public void excluirEmpresa(Long id) {
        Empresa empresa = entityManager.find(Empresa.class, id);
        if (empresa != null) {
            empresa.setStatus(false);
            entityManager.merge(empresa);
        }
    }
}
