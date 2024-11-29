package br.edu.gov.fatec.estagiando.controllers;

import br.edu.gov.fatec.estagiando.dtos.EmpresaDTO;
import br.edu.gov.fatec.estagiando.models.Empresa;
import br.edu.gov.fatec.estagiando.services.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/empresas")
public class EmpresaController {

    private final EmpresaService empresaService;

    @Autowired
    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    // Endpoint para listar todas as empresas
    @GetMapping
    public String listarEmpresas(Model model) {
        List<EmpresaDTO> empresasDTO = empresaService.listarEmpresas()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        model.addAttribute("empresas", empresasDTO);
        return "empresas/lista";
    }
    
    @GetMapping("/novo")
    public String mostrarFormularioCriacao(Model model) {
        model.addAttribute("empresaDTO", new EmpresaDTO());
        return "empresas/formulario";
    }
    
    @PostMapping("/criar")
    public String criarEmpresa(@ModelAttribute("empresaDTO") EmpresaDTO empresaDTO) {
        Empresa novaEmpresa = empresaService.criarEmpresa(convertToEntity(empresaDTO));
        return "redirect:/empresas";
    }
    
    @GetMapping("/{id}/edit")
    public String updateEmpresaForm(@PathVariable("id") Long id, Model model) {
    Empresa empresa = empresaService.buscarEmpresaPorId(id);
    model.addAttribute("empresa", empresa);
    return "empresas/updateEmpresa";
    } 

    
    @PostMapping("/{id}/atualizar")
    public String atualizarEmpresa(@PathVariable("id") Long id, @ModelAttribute("empresaDTO") EmpresaDTO empresaDTO) {
        Empresa empresaAtualizada = empresaService.atualizarEmpresa(id, convertToEntity(empresaDTO));
        return "redirect:/empresas";
    }
    
    @PostMapping("/{id}/excluir")
    public String excluirEmpresa(@PathVariable("id") Long id) {
        empresaService.excluirEmpresa(id);
        return "redirect:/empresas";
    }

    private EmpresaDTO convertToDTO(Empresa empresa) {
        return new EmpresaDTO(
                empresa.getId(),
                empresa.getNome(),
                empresa.getCnpj(),
                empresa.getEndereco(),
                empresa.getTelefone()
        );
    }

    private Empresa convertToEntity(EmpresaDTO empresaDTO) {
        return new Empresa(
                empresaDTO.getNome(),
                empresaDTO.getCnpj(),
                empresaDTO.getEndereco(),
                empresaDTO.getTelefone()
        );
    }
}
