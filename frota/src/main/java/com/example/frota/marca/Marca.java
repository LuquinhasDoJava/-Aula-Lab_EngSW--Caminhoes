package com.example.frota.marca;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

@Entity
@Table(name = "marca")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of ="id")
public class Marca {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String pais;

    public Marca(String nome, String pais) {
        this.nome = nome;
        this.pais = pais;
    }

    public Marca(DadosCadastroMarca dados) {
        this.nome = dados.nome();
        this.pais = dados.pais();
    }
    public void atualizarInformacoes(@Valid DadosAtualizacaoMarca dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
            this.pais = dados.pais();
        }
    }
}