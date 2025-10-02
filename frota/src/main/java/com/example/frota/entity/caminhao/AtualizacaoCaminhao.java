package com.example.frota.entity.caminhao;

import jakarta.validation.constraints.NotNull;

public record AtualizacaoCaminhao(
        @NotNull Long id,
        @NotNull String modelo,
        @NotNull String placa,
        @NotNull Long marcaId,
        @NotNull double cargaMaxima,
        @NotNull int ano){

}
