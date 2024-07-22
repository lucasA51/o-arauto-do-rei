package com.santosltda.oarautodorei.controller;

import com.santosltda.oarautodorei.domain.Usuario;
import com.santosltda.oarautodorei.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Controller
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioController(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/usuarios/login")
    public String showLoginForm() {
        return "usuarios/login";
    }

    @PostMapping("/usuarios/registrar")
    public String registerUser(@RequestParam String usuario, @RequestParam String senha) throws UnsupportedEncodingException {
        if (usuarioRepository.findByUsuario(usuario).isPresent()) {
            String erro = URLEncoder.encode("Usuário já existente. Escolha um nome de usuário diferente.", StandardCharsets.UTF_8);
            return "redirect:/usuarios/registrar?erro=" + erro;
        }

        Usuario newUser = new Usuario();
        newUser.setUsuario(usuario);
        newUser.setSenha(passwordEncoder.encode(senha)); // Use injected passwordEncoder
        usuarioRepository.save(newUser);

        String sucesso = URLEncoder.encode("Usuário registrado com sucesso!", StandardCharsets.UTF_8);
        return "redirect:/usuarios/registrar?sucesso=" + sucesso;
    }
}
