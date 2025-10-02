package com.example.frota.service;

import com.example.frota.entity.caminhao.Caminhao;
import com.example.frota.repository.CaminhaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CaminhaoService {

    @Autowired
    private CaminhaoRepository caminhaoRepository;

    public List<Caminhao> listarTodos() {
        return caminhaoRepository.findAll();
    }

    public Optional<Caminhao> buscarPorId(Long id) {
        return caminhaoRepository.findById(id);
    }

    public void salvar(Caminhao caminhao) {
        caminhaoRepository.save(caminhao);
    }

    public void deletar(Long id) {
        caminhaoRepository.deleteById(id);
    }
}
