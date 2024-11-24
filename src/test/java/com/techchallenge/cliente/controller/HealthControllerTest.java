package com.techchallenge.cliente.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class HealthControllerTest {

    @InjectMocks
    HealthController healthController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void healthCheck() {
        ResponseEntity<String> request = healthController.healthCheck();

        assertEquals(HttpStatus.OK, request.getStatusCode());
        assertNotEquals(HttpStatus.INTERNAL_SERVER_ERROR,request.getStatusCode());
    }
}