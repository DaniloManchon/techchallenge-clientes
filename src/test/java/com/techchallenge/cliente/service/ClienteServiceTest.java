package com.techchallenge.cliente.service;

import com.techchallenge.cliente.model.Cliente;
import com.techchallenge.cliente.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;



public class ClienteServiceTest {

    Cliente cliente = new Cliente("37451879895", "Danilo", "danilo@email.com", true);

    @Mock
    ClienteRepository clienteRepository;

    @InjectMocks
    ClienteService clienteService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void criarCliente() {
        // Dados de entrada
        String cpf = "36451879895";
        Cliente clienteNovo = this.cliente;

        // Configuracao mock de busca
        when(clienteRepository.findByCpf(cpf)).thenReturn(Optional.empty());
        // Configuracao mock save
        when(clienteRepository.save(clienteNovo)).thenReturn(clienteNovo);
        // Chamada ao metodo
        ResponseEntity<String> response = clienteService.criarCliente(clienteNovo);
        // Verificação
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertNotEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        verify(clienteRepository).save(clienteNovo);

    }

    @Test
    public void buscarCliente() {
        // Dados de entrada
        String cpf = "37451879895";
        Cliente clienteExistente = this.cliente;

        // Configuração do mock
        when(clienteRepository.findByCpf(cpf)).thenReturn(Optional.of(clienteExistente));

        // Chamada ao método
        ResponseEntity<Cliente> response = clienteService.buscarCliente(cpf);

        // Verificação
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(cliente, response.getBody());
    }

    @Test
    public void atualizarMarketing() {
        // Dados de entrada
        String cpf = "37451879895";
        boolean marketingNovo = false;
        Cliente clienteExistente = this.cliente;

        // Configuração do mock para o método clienteRepository.save
        when(clienteRepository.save(clienteExistente)).thenReturn(clienteExistente);
        // Configuração do mock para o método buscarCliente
        when(clienteRepository.findByCpf(cpf)).thenReturn(Optional.of(clienteExistente));
        // Chamada ao método
        ResponseEntity<Cliente> response = clienteService.atualizarMarketing(cpf, marketingNovo);

        // Verificação
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(marketingNovo, response.getBody().isMarketing());
        verify(clienteRepository).save(clienteExistente);  // Verifica se o save foi chamado

    }

    @Test
    public void atualizarEmail() {
        // Dados de entrada
        String cpf = "37451879895";
        String emailNovo = "email@email.com";
        Cliente clienteExistente = this.cliente;

        // Configuração do mock para o método clienteRepository.save
        when(clienteRepository.save(clienteExistente)).thenReturn(clienteExistente);
        // Configuração do mock para o método buscarCliente
        when(clienteRepository.findByCpf(cpf)).thenReturn(Optional.of(clienteExistente));
        // Chamada ao método
        ResponseEntity<Cliente> response = clienteService.atualizarEmail(cpf, emailNovo);

        // Verificação
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(emailNovo, response.getBody().getEmail());
        verify(clienteRepository).save(clienteExistente);  // Verifica se o save foi chamado
    }

    @Test
    public void atualizarNome() {
        // Dados de entrada
        String cpf = "37451879895";
        String nomeNovo = "José Silva";
        Cliente clienteExistente = this.cliente;

        // Configuração do mock para o método clienteRepository.save
        when(clienteRepository.save(clienteExistente)).thenReturn(clienteExistente);
        // Configuração do mock para o método buscarCliente
        when(clienteRepository.findByCpf(cpf)).thenReturn(Optional.of(clienteExistente));
        // Chamada ao método
        ResponseEntity<Cliente> response = clienteService.atualizarNome(cpf, nomeNovo);

        // Verificação
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(nomeNovo, response.getBody().getNome());
        verify(clienteRepository).save(clienteExistente);  // Verifica se o save foi chamado

    }

    @Test
    public void apagarCliente() {
        // Dados de entrada
        String cpf = "37451879895";
        Cliente clienteExistente = this.cliente;

        // Configuração do mock para o método clienteRepository.save
        when(clienteRepository.save(clienteExistente)).thenReturn(clienteExistente);
        // Configuração do mock para o método buscarCliente
        when(clienteRepository.findByCpf(cpf)).thenReturn(Optional.of(clienteExistente));
        // Chamada ao método
        ResponseEntity<String> response = clienteService.apagarCliente(cpf);

        // Verificação
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNotEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        verify(clienteRepository).delete(clienteExistente);  // Verifica se o save foi chamado
    }
}