package com.example.frota.service;

import com.example.frota.entity.marca.Marca;
import com.example.frota.repository.MarcaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarcaService {

    private MarcaRepository repository;

    public MarcaService(MarcaRepository repository){
        this.repository = repository;
    }

    public void salvar(Marca marca) {
        repository.save(marca);
    }

    public Marca procurarPorId(Long id) {
        return repository.getReferenceById(id);
    }

    public List<Marca> procurarTodos() {
        return repository.findAll();
    }

    public void deletarPorId(Long id) {
        repository.deleteById(id);
    }
}

