package com.example.frota.marca;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.transaction.Transactional;


@Controller
@RequestMapping("/marca")
public class MarcaController {

    @Autowired
    private MarcaService marcaService;

    @PostMapping
    @Transactional
    public String cadastrar (@Valid DadosCadastroMarca dados) {
        marcaService.salvar(new Marca(dados));
        return "redirect:marca";
    }
    
    @PutMapping
    @Transactional
    public String atualizar (DadosAtualizacaoMarca dados) {
        Marca marca = marcaService.procurarPorId(dados.id());
        marca.atualizarInformacoes(dados);
        return "redirect:marca";
    }

    @GetMapping
    public String carregaPaginaListagem(Model model){
        System.out.println("Acessando /marca/listagem");
        model.addAttribute("lista", marcaService.procurarTodos());
        return "marca/listagem";
    }

    @GetMapping ("/formulario")
    public String carregaPaginaFormulario(Long id, Model model) {
        if(id != null) {
            var marca = marcaService.procurarPorId(id);
            model.addAttribute("marca", marca);
        }
        return "marca/formulario";
    }

    // Método para gravar/atualizar o formulário
    @PostMapping ("/salvar")
    @Transactional
    public String salvarMarca(@ModelAttribute Marca marca, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "marca/form";
        }
        marcaService.salvar(marca);
        redirectAttributes.addFlashAttribute("message", marca.getId() != 0L ? "Marca atualizada com sucesso!" : "Marca salva com sucesso!");
        return "redirect:/marca/listagem";
    }

    @DeleteMapping
    @Transactional
    public String excluir (Long id) {
        marcaService.deletarPorId(id);
        return "redirect:marca";
    }
}

