package com.example.frota.controller;

import com.example.frota.entity.Caminhao;
import com.example.frota.service.CaminhaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/caminhao")
public class CaminhaoController {

    @Autowired
    private CaminhaoService caminhaoService;

    // Método para listar todos os caminhões
    @GetMapping
    public String listarCaminhoes(Model model) {
        List<Caminhao> listaCaminhoes = caminhaoService.listarTodos();
        model.addAttribute("lista", listaCaminhoes);
        return "caminhao/listagem";  // Nome do arquivo HTML para lista de caminhões
    }

    // Método para abrir o formulário de criação/edição de caminhão
    @GetMapping("/formulario")
    public String formulario(@RequestParam(value = "id", required = false) Long id, Model model) {
        if (id != null) {
            // Se o ID foi fornecido, buscamos o caminhão para edição
            Optional<Caminhao> caminhao = caminhaoService.buscarPorId(id);
            if (caminhao.isPresent()) {
                model.addAttribute("caminhao", caminhao.get());
            } else {
                // Se o caminhão não existir, redireciona de volta à lista
                return "caminhao/listagem";
            }
        } else {
            // Se não houver ID, estamos criando um novo caminhão
            model.addAttribute("caminhao", new Caminhao());
        }
        return "caminhao/formulario";  // Nome do arquivo HTML do formulário
    }

    // Método para salvar o caminhão (tanto criação quanto atualização)
    @PostMapping
    public String salvarCaminhao(@ModelAttribute Caminhao caminhao) {
        caminhaoService.salvar(caminhao);  // Chama o serviço para salvar no banco
        return "redirect:/caminhao";  // Redireciona para a lista de caminhões
    }

    // Método para excluir um caminhão
    @PostMapping(params = "_method=delete")
    public String excluirCaminhao(@RequestParam("id") Long id) {
        caminhaoService.deletar(id);  // Chama o serviço para deletar o caminhão
        return "redirect:/caminhao  ";  // Redireciona para a lista de caminhões
    }

}
