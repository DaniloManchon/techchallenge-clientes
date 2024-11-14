package com.techchallenge.cliente.service;


import com.techchallenge.cliente.model.Cliente;
import org.springframework.http.ResponseEntity;

public interface ClienteUseCase {
    ResponseEntity<String> criarCliente(Cliente cliente);

    ResponseEntity<Cliente> buscarCliente(String cpf);

    ResponseEntity<String> apagarCliente(String cpf);

    ResponseEntity<Cliente> atualizarMarketing(String cpf, boolean marketing);

    ResponseEntity<Cliente> atualizarEmail(String cpf, String email);

    ResponseEntity<Cliente> atualizarNome(String cpf, String nome);
}
