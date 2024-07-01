package com.example.pizandia.servicios;


import com.example.pizandia.entidades.Cliente;
import com.example.pizandia.repositorios.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServicio {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> getClienteById(Long id) {
        return clienteRepository.findById(id);
    }

    public Cliente saveOrUpdateCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public void deleteCliente(Long id) {
        clienteRepository.deleteById(id);
    }

    public Optional<Cliente> findClienteByEmail(String email) {
        return clienteRepository.findByEmail(email);
    }

    public Optional<Cliente> validarCliente(String email, String contraseña) {
        return clienteRepository.findByEmailAndContraseña(email, contraseña);
    }
}
