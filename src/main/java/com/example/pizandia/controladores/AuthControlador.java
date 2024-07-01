package com.example.pizandia.controladores;

import com.example.pizandia.entidades.Cliente;
import com.example.pizandia.servicios.ClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000") // Permitir solicitudes desde el frontend en localhost:3000
public class AuthControlador {

    @Autowired
    private ClienteServicio clienteServicio;

    // Iniciar sesión (login)
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Cliente cliente) {
        Optional<Cliente> clienteValidado = clienteServicio.validarCliente(cliente.getEmail(), cliente.getContraseña());

        if (clienteValidado.isPresent()) {
            return ResponseEntity.ok(clienteValidado.get());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }
    }
}
