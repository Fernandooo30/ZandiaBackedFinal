package com.example.pizandia.controladores;

import com.example.pizandia.entidades.Cliente;
import com.example.pizandia.servicios.ClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/clientes")
@CrossOrigin(origins = "http://localhost:3000") // Permitir solicitudes desde el frontend en localhost:3000
public class ClienteControlador {

    @Autowired
    private ClienteServicio clienteServicio;

    // Obtener todos los clientes
    @GetMapping
    public ResponseEntity<List<Cliente>> getAllClientes() {
        List<Cliente> clientes = clienteServicio.getAllClientes();
        return ResponseEntity.ok(clientes);
    }

    // Obtener cliente por ID
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable("id") Long clienteId) {
        Optional<Cliente> cliente = clienteServicio.getClienteById(clienteId);
        return cliente.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Crear o actualizar cliente
    @PostMapping
    public ResponseEntity<Cliente> saveUpdateCliente(@RequestBody Cliente cliente) {
        Cliente savedCliente = clienteServicio.saveOrUpdateCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCliente);
    }

    // Eliminar cliente por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable("id") Long clienteId) {
        clienteServicio.deleteCliente(clienteId);
        return ResponseEntity.noContent().build();
    }

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
