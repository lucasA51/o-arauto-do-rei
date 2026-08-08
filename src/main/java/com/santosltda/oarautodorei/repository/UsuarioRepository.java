package com.santosltda.oarautodorei.repository;

import com.santosltda.oarautodorei.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsuario(String usuario);
    default Optional<Usuario> findByUsername(String username) {
        return findByUsuario(username);
    }
}

