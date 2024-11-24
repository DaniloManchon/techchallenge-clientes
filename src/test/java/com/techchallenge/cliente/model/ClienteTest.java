package com.techchallenge.cliente.model;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClienteTest {

    @Test
    public void testClienteConstructor() {
        // Dados de entrada
        String cpf = "12345678900";
        String nome = "João Silva";
        String email = "joao@example.com";
        boolean marketing = true;

        // Criação do objeto Cliente
        Cliente cliente = new Cliente(cpf, nome, email, marketing);

        // Verificação de que o objeto foi criado corretamente
        assertEquals(cpf, cliente.getCpf());
        assertEquals(nome, cliente.getNome());
        assertEquals(email, cliente.getEmail());
        assertTrue(cliente.isMarketing());
    }

    @Test
    public void testSettersAndGetters() {
        // Criação de um cliente com dados iniciais
        Cliente cliente = new Cliente("12345678900", "João Silva", "joao@example.com", true);

        // Testando o setter e getter de CPF
        cliente.setCpf("09876543210");
        assertEquals("09876543210", cliente.getCpf());

        // Testando o setter e getter de nome
        cliente.setNome("Carlos Oliveira");
        assertEquals("Carlos Oliveira", cliente.getNome());

        // Testando o setter e getter de email
        cliente.setEmail("carlos@example.com");
        assertEquals("carlos@example.com", cliente.getEmail());

        // Testando o setter e getter de marketing
        cliente.setMarketing(false);
        assertFalse(cliente.isMarketing());
    }

}