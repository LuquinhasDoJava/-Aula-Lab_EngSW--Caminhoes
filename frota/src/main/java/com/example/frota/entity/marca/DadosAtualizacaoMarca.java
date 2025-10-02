package com.example.frota.entity.marca;

import jakarta.validation.constraints.NotBlank;

public record DadosAtualizacaoMarca(@NotBlank Long id,
                                    @NotBlank String nome,
                                    @NotBlank String pais) {
}
