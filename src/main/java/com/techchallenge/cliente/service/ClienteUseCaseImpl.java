package com.techchallenge.cliente.service;

import com.techchallenge.cliente.model.Cliente;

import com.techchallenge.cliente.repository.ClienteRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log4j2
@Service
public class ClienteUseCaseImpl implements ClienteUseCase {

    @Autowired
    private ClienteRepository clienteRepository;

    public ResponseEntity<String> criarCliente(Cliente cliente) {
        try {
            if (!buscarCliente(cliente.getCpf()).hasBody()) {
                clienteRepository.save(
                        new Cliente(
                                cliente.getCpf(),
                                cliente.getNome(),
                                cliente.getEmail(),
                                cliente.isMarketing()
                        )
                );
                log.info("cliente {} cadastrado", cliente.getNome());
                return new ResponseEntity<>(null, HttpStatus.CREATED);
            } else {
                log.warn("cliente já cadastrado");
                return new ResponseEntity<>(null, HttpStatus.CONFLICT);
            }
        } catch (Exception e) {
            log.error(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Cliente> buscarCliente(String cpf) {
        Optional<Cliente> clienteData_ = clienteRepository.findByCpf(cpf);
        if (clienteData_.isPresent()) {
            return new ResponseEntity<>(clienteData_.get(), HttpStatus.OK);
        } else {
            log.debug("cpf {} não encontrado", cpf);
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
    }

    public ResponseEntity<String> apagarCliente(String cpf) {
        try {
            Cliente clienteData_ = buscarCliente(cpf).getBody();
            clienteRepository.delete(clienteData_);
            log.info("cliente {} deletado", clienteData_.getCpf());
            return new ResponseEntity<>("cliente apagado", HttpStatus.OK);
        } catch (Exception e) {
            log.error(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Cliente> atualizarMarketing(String cpf, boolean marketing) {
        try {
            Cliente clienteData_ = buscarCliente(cpf).getBody();
            clienteData_.setMarketing(marketing);
            clienteRepository.save(clienteData_);
            log.info("conta {}: preferencias de marketing atualizadas", clienteData_.getCpf());
            return new ResponseEntity<>(clienteData_, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Cliente> atualizarEmail(String cpf, String email) {
        try {
            Cliente clienteData_ = buscarCliente(cpf).getBody();
            clienteData_.setEmail(email);
            clienteRepository.save(clienteData_);
            log.info("conta {}: email atualizado atualizado", clienteData_.getCpf());
            return new ResponseEntity<>(clienteData_, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Cliente> atualizarNome(String cpf, String nome) {
        try {
            Cliente clienteData_ = buscarCliente(cpf).getBody();
            clienteData_.setNome(nome);
            clienteRepository.save(clienteData_);
            log.info("conta {}: nome atualizado atualizado", clienteData_.getCpf());
            return new ResponseEntity<>(clienteData_, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
