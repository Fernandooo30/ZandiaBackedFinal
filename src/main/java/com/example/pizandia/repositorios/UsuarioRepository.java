package com.example.pizandia.repositorios;

import com.example.pizandia.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByNombreAndClave(String nombre, String clave);
}
