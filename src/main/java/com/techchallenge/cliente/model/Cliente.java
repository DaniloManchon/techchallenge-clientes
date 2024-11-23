package com.techchallenge.cliente.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String cpf;
    @Column
    private String nome;
    @Column
    private String email;
    @Column
    private boolean marketing;

    public Cliente(String cpf, String nome, String email, boolean marketing) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.marketing = marketing;
    }
}
