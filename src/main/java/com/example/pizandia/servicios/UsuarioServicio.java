package com.example.pizandia.servicios;

import com.example.pizandia.entidades.Usuario;
import com.example.pizandia.repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServicio {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario validarUsuario(String nombre, String clave) {
        return usuarioRepository.findByNombreAndClave(nombre, clave);
    }
}
