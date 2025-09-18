package com.example.frota.service;

import com.example.frota.entity.Caminhao;
import com.example.frota.repository.CaminhaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CaminhaoService {

    @Autowired
    private CaminhaoRepository caminhaoRepository;

    // Método para listar todos os caminhões
    public List<Caminhao> listarTodos() {
        return caminhaoRepository.findAll();
    }

    // Método para buscar um caminhão por ID
    public Optional<Caminhao> buscarPorId(Long id) {
        return caminhaoRepository.findById(id);
    }

    // Método para salvar um caminhão (criar ou atualizar)
    public Caminhao salvar(Caminhao caminhao) {
        return caminhaoRepository.save(caminhao);
    }

    // Método para excluir um caminhão
    public void deletar(Long id) {
        caminhaoRepository.deleteById(id);
    }
}
