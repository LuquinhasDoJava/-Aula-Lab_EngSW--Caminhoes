package com.example.frota.entity.caminhao;

import com.example.frota.entity.marca.Marca;
import jakarta.validation.constraints.NotBlank;

public record CadastroCaminhao(
        @NotBlank String modelo,
        @NotBlank String placa,
        @NotBlank Marca marca,
        @NotBlank double cargaMaxima,
        @NotBlank int ano) {

}