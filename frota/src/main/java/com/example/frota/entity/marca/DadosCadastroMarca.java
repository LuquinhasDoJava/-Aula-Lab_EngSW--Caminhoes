package com.example.frota.entity.marca;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroMarca(@NotBlank
                                 @NotBlank String nome,
                                 @NotBlank String pais) {


}
