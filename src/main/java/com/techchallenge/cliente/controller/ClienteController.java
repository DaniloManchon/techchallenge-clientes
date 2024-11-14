package com.techchallenge.cliente.controller;


import com.techchallenge.cliente.model.Cliente;
import com.techchallenge.cliente.service.ClienteUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    ClienteUseCase clienteUseCase;

    @PostMapping
    public ResponseEntity<String> criarCliente(@RequestBody Cliente cliente) {
        try {
            return clienteUseCase.criarCliente(cliente);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<Cliente> buscarCliente(@RequestParam("cpf") String cpf) {
        try {
            return clienteUseCase.buscarCliente(cpf);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/marketing")
    public ResponseEntity<Cliente> atualizarMarketing(@RequestParam("cpf") String cpf, @RequestBody boolean marketing) {
        try {
            return clienteUseCase.atualizarMarketing(cpf, marketing);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/email")
    public ResponseEntity<Cliente> atualizarEmail(@RequestParam("cpf") String cpf, @RequestBody String email) {
        try {
            return clienteUseCase.atualizarEmail(cpf, email);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/nome")
    public ResponseEntity<Cliente> atualizarNome(@RequestParam("cpf") String cpf, @RequestBody String nome) {
        try {
            return clienteUseCase.atualizarNome(cpf, nome);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping
    public ResponseEntity<String> apagarCliente(@RequestParam("cpf") String cpf) {
        try {
            return clienteUseCase.apagarCliente(cpf);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
