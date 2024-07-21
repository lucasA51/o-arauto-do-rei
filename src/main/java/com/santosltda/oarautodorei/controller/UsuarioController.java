package com.santosltda.oarautodorei.controller;

import com.santosltda.oarautodorei.domain.Usuario;
import com.santosltda.oarautodorei.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("usuarios/registrar")
    public String showRegisterForm(@RequestParam(required = false) String sucesso, @RequestParam(required = false) String erro, Model model) {
        // Decodificar mensagens de sucesso e erro
        if (sucesso != null) {
            sucesso = decodeParam(sucesso);
        }
        if (erro != null) {
            erro = decodeParam(erro);
        }
        model.addAttribute("sucesso", sucesso);
        model.addAttribute("erro", erro);
        return "usuarios/registrar";
    }

    @PostMapping("usuarios/registrar")
    public String registerUser(@RequestParam String usuario, @RequestParam String senha, Model model) throws UnsupportedEncodingException {
        if (usuarioRepository.findByUsuario(usuario).isPresent()) {
            String erro = URLEncoder.encode("Usuário já existente. Escolha um nome de usuário diferente.", StandardCharsets.UTF_8);
            return "redirect:/usuarios/registrar?erro=" + erro;
        }

        Usuario newUser = new Usuario();
        newUser.setUsuario(usuario);
        newUser.setSenha(senha);
        usuarioRepository.save(newUser);

        String sucesso = URLEncoder.encode("Usuário registrado com sucesso!", StandardCharsets.UTF_8);
        return "redirect:/usuarios/registrar?sucesso=" + sucesso;
    }

    private String decodeParam(String param) {
        try {
            return java.net.URLDecoder.decode(param, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            return param;
        }
    }
}
