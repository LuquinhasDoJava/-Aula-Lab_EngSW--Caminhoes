package com.example.frota.marca;

import jakarta.validation.constraints.NotBlank;

public record DadosAtualizacaoMarca(@NotBlank
                                    Long id,
                                    String nome,
                                    String pais) {
}
