package com.techchallenge.cliente.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Entity
public class Cliente {

    @JsonIgnore
    @Id
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
