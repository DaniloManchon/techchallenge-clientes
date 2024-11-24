package com.techchallenge.cliente.controller;

import com.techchallenge.cliente.model.Cliente;
import com.techchallenge.cliente.repository.ClienteRepository;
import com.techchallenge.cliente.service.ClienteUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class ClienteControllerTest {

    @Mock
    private ClienteUseCase clienteUseCase;

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteController clienteController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);  // Inicializa os mocks
    }

    Cliente cliente = new Cliente("37451879895", "Danilo", "danilo@email.com", true);

    @Test
    public void testCriarCliente() {
        Cliente clienteNovo = this.cliente;

        when(clienteUseCase.criarCliente(clienteNovo)).thenReturn(new ResponseEntity<>(null, HttpStatus.CREATED));
        ResponseEntity<String> request = clienteController.criarCliente(clienteNovo);

        assertEquals(HttpStatus.CREATED, request.getStatusCode());
        assertNotEquals(HttpStatus.INTERNAL_SERVER_ERROR,request.getStatusCode());
    }

    @Test
    public void testBuscarCliente() {
        String cpf = "37451879895";
        Cliente clienteExistente = this.cliente;

        when(clienteUseCase.buscarCliente(cpf)).thenReturn(new ResponseEntity<>(clienteExistente, HttpStatus.OK));

        ResponseEntity<Cliente> request = clienteController.buscarCliente(cpf);

        assertEquals(HttpStatus.OK, request.getStatusCode());
        assertNotEquals(HttpStatus.INTERNAL_SERVER_ERROR,request.getStatusCode());
    }

    @Test
    public void testAtualizarMarketing() {
        String cpf = "37451879895";
        boolean marketingNovo = false;
        Cliente clienteExistente = this.cliente;

        when(clienteUseCase.buscarCliente(cpf)).thenReturn(new ResponseEntity<>(clienteExistente, HttpStatus.OK));
        when(clienteUseCase.atualizarMarketing(cpf, marketingNovo)).thenReturn(new ResponseEntity<>(clienteExistente, HttpStatus.OK));

        ResponseEntity<Cliente> request = clienteController.atualizarMarketing(cpf, marketingNovo);

        assertEquals(HttpStatus.OK, request.getStatusCode());
        assertNotEquals(HttpStatus.INTERNAL_SERVER_ERROR,request.getStatusCode());
    }

    @Test
    public void testAtualizarEmail() {
        String cpf = "37451879895";
        String emailNovo = "email@email.com";
        Cliente clienteExistente = this.cliente;

        when(clienteUseCase.buscarCliente(cpf)).thenReturn(new ResponseEntity<>(clienteExistente, HttpStatus.OK));
        when(clienteUseCase.atualizarEmail(cpf,emailNovo)).thenReturn(new ResponseEntity<>(clienteExistente, HttpStatus.OK));

        ResponseEntity<Cliente> request = clienteController.atualizarEmail(cpf, emailNovo);

        assertEquals(HttpStatus.OK, request.getStatusCode());
        assertNotEquals(HttpStatus.INTERNAL_SERVER_ERROR,request.getStatusCode());
    }

    @Test
    public void testAtualizarNome() {
        String cpf = "37451879895";
        String nomeNovo = "José Silva";
        Cliente clienteExistente = this.cliente;

        when(clienteUseCase.buscarCliente(cpf)).thenReturn(new ResponseEntity<>(clienteExistente, HttpStatus.OK));
        when(clienteUseCase.atualizarNome(cpf, nomeNovo)).thenReturn(new ResponseEntity<>(clienteExistente, HttpStatus.OK));

        ResponseEntity<Cliente> request = clienteController.atualizarNome(cpf, nomeNovo);

        assertEquals(HttpStatus.OK, request.getStatusCode());
        assertNotEquals(HttpStatus.INTERNAL_SERVER_ERROR,request.getStatusCode());
    }

    @Test
    public void testApagarCliente() {
        String cpf = "37451879895";
        Cliente clienteExistente = this.cliente;

        when(clienteUseCase.buscarCliente(cpf)).thenReturn(new ResponseEntity<>(clienteExistente, HttpStatus.OK));
        when(clienteUseCase.apagarCliente(cpf)).thenReturn(new ResponseEntity<String>("", HttpStatus.OK));

        ResponseEntity<String> request = clienteController.apagarCliente(cpf);

        assertEquals(HttpStatus.OK, request.getStatusCode());
        assertNotEquals(HttpStatus.INTERNAL_SERVER_ERROR,request.getStatusCode());
    }
}