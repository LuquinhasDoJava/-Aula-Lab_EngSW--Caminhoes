package com.example.frota.controller;

import com.example.frota.entity.caminhao.AtualizacaoCaminhao;
import com.example.frota.entity.caminhao.Caminhao;
import com.example.frota.service.CaminhaoService;
import com.example.frota.service.MarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;


@Controller
@RequestMapping("/caminhao")
public class CaminhaoController {

    @Autowired
    private CaminhaoService caminhaoService;

    @Autowired
    private MarcaService marcaService;

    @GetMapping
    public String carregaPaginaFormulario ( Model model){
        model.addAttribute("lista", caminhaoService.listarTodos());
        return "caminhao/listagem";
    }

    // Para criação sem passar o ID
    @GetMapping("/formulario")
    public String novoCaminhao(Model model) {
        model.addAttribute("caminhao", new Caminhao());
        model.addAttribute("marcas", marcaService.procurarTodos());
        return "caminhao/formulario";
    }

    //@GetMapping ("/formulario")
    @GetMapping ("/formulario/{id}")
    public String carregaPaginaFormulario (@PathVariable("id") Long id, Model model,
                                           RedirectAttributes redirectAttributes) {
        try {
            if(id != null) {
                Caminhao caminhao = caminhaoService.buscarPorId(id)
                        .orElseThrow(() -> new EntityNotFoundException("Caminhao não encontrado"));
                model.addAttribute("marcas", marcaService.procurarTodos());
                model.addAttribute("caminhao", caminhao);
            }
            return "caminhao/formulario";
        } catch (EntityNotFoundException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/caminhao";
        }
    }

    @PostMapping ("/salvar")
    @Transactional
    public String cadastrar (@Valid @ModelAttribute("caminhao") AtualizacaoCaminhao dados,
                             RedirectAttributes redirectAttributes,
                             BindingResult result,
                             Model model) {

        if (result.hasErrors()) {
            model.addAttribute("marcas", marcaService.procurarTodos());
            return "caminhao/formulario";
        }
        try {
            var marca = marcaService.procurarPorId(dados.marcaId());
            var caminhao = new Caminhao(dados, marca);

            String mensagem = caminhao.getId() != null ?
                    "Caminhao '" + caminhao.getPlaca() + "' atualizado com sucesso!" :
                    "Caminhao '" + caminhao.getPlaca() + "' criado com sucesso!";

            redirectAttributes.addFlashAttribute("message", mensagem);
            return "redirect:caminhao/listagem";

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erro ao salvar caminhao: " + e.getMessage());
            return "redirect:caminhao/listagem" + (dados.id() != null ? "/" + dados.id() : "");
        }
    }

//	@DeleteMapping
//	@Transactional
//	public String excluir (Long id) {
//		caminhaoService.apagarPorId(id);
//		return "redirect:caminhao";
//	}

    @GetMapping("/delete/{id}")
    @Transactional
    public String deleteTutorial(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            caminhaoService.deletar(id);
            redirectAttributes.addFlashAttribute("message", "O caminhao " + id + " foi apagado!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/caminhao";
    }


}